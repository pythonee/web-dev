package com.mashup.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mashup.domain.Comment;
import com.mashup.domain.Product;
import com.mashup.domain.User;
import com.mashup.service.ICommentFilter;
import com.mashup.service.ICommentService;
import com.mashup.service.IProductService;
import com.mashup.service.ISensWordService;
import com.mashup.service.IUserService;
import com.mashup.service.impl.CommentFilter;

public class CommentAction extends BaseAction {
	IProductService productService;
	ICommentService commentService;
	IUserService userService;
	ISensWordService sensWordService;

	public String toCommentJSON(Comment comment) {

		String json = "";

		json += "{\"userName\":\"" + comment.getUser().getUsername() + "\",";
		json += "\"commentStr\":\"" + comment.getCommentContent() + "\",";
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-mm-dd hh-mm-ss");
		Date date = comment.getCommentTime();
		String commentTime = bartDateFormat.format(date);
		// commentTime+=date.getYear()+"-"+date.getMonth()+"-"+date.getDate()+"
		// "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		json += "\"commentTime\":\"" + commentTime + "\"";
		json += "}";

		// TODO add the size to json
		return json;
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub
		int userId = Integer.parseInt(request.getParameter("userId"));
		String commentStr = request.getParameter("commentStr").toString();
		commentStr = java.net.URLDecoder.decode(commentStr, "utf-8");
		int productId = Integer.parseInt(request.getParameter("productId"));
		commentService = (ICommentService) getBean("CommentService");
		productService = (IProductService) getBean("ProductService");
		userService = (IUserService) getBean("UserService");
		sensWordService = (ISensWordService) getBean("SensWordService");
		List wordList = sensWordService.findAll();
		log.debug("wordList size " + wordList.size());
		Comment comment = new Comment();
		comment.setProduct(productService.getProductById(productId));
		comment.setUser(userService.getUserById(userId));
		comment.setCommentContent(commentStr);
		comment.setCommentTime(new Date());
		log.debug("comment created");
		ICommentFilter commentFilter = new CommentFilter(wordList);
		log.debug("commentFilter created");
		comment = commentFilter.Filter(comment);
		log.debug(comment.getCommentContent());
		String responseText = "";
		responseText = this.toCommentJSON(comment);
		commentService.insertComment(comment);

		response.getWriter().write(responseText);
		return mapping.findForward("");
	}

	public ActionForward freshComment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DataSource dataSource = (DataSource) getBean("DataSource");
		userService = (IUserService) getBean("UserService");

		String query = "SELECT commentContent,productId,userId FROM comment order by commentTime DESC limit 10";

		List<Comment> topItems = new ArrayList<Comment>();

		Connection conn = null;

		try {
			conn = (Connection) dataSource.getConnection();
			Statement getTopCommentStam = conn.createStatement();
			ResultSet resultSet = getTopCommentStam.executeQuery(query);

			while (resultSet.next()) {
				Comment temp = new Comment();
				Product product = new Product();
				User user = new User();
				String content = resultSet.getString(1);
				if (content.length() >= 25) {
					content = content.substring(0, 25);
				}
				product.setProductId(resultSet.getInt(2));
				user.setUsername(userService.getUserById(resultSet.getInt(3))
						.getUsername());
				if (content.length() > 15) {
					content = content.substring(0, 15);
				}
				temp.setCommentContent(content);
				temp.setProduct(product);
				temp.setUser(user);

				topItems.add(temp);
			}

			request.setAttribute("siderbar_CommentList", topItems);
			resultSet.close();
			getTopCommentStam.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (conn != null) {
				conn.close();
			}

		}

		return null;
	}

}
