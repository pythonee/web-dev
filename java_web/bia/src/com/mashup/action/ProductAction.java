package com.mashup.action;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mashup.domain.Comment;
import com.mashup.domain.IpTrack;
import com.mashup.domain.Product;
import com.mashup.domain.ProductPreference;
import com.mashup.service.IAdvertiseService;
import com.mashup.service.ICollectionService;
import com.mashup.service.ICommentService;
import com.mashup.service.IIpTrackService;
import com.mashup.service.IProductPreferenceService;
import com.mashup.service.IProductService;
import com.mashup.service.ISearchService;
import com.mashup.service.ITrackService;

public class ProductAction extends BaseAction {
	IProductService productService;
	ICommentService commentService;
	ISearchService searchService;
	IAdvertiseService advertiseService;
	ITrackService trackService;
	IIpTrackService ipTrackService;
	ICollectionService collectionService;
	IProductPreferenceService preferenceService;

	public ActionForward get(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub
		int productId = Integer.parseInt(request.getParameter("productId"));
		int page = 0;
		if (null != request.getParameter("page")) {
			page = Integer.parseInt(request.getParameter("page").toString());
		}

		productService = (IProductService) getBean("ProductService");
		commentService = (ICommentService) getBean("CommentService");
		searchService = (ISearchService) getBean("SearchService");
		advertiseService = (IAdvertiseService) getBean("AdvertiseService");
		Product product = productService.getProductById(productId);
		request.setAttribute("product", product);
		List commentList = commentService.getCommentByProduct(product);
		// getCommentByPage(commentList,page);
		request.setAttribute("commentList", commentList);
		request.setAttribute("length", commentList.size());
		List relatedProductList = searchService.searchRelated(product
				.getProductName());
		request.setAttribute("relatedProductList", relatedProductList);

		// 将会放到sidebar的广告
		List adList = advertiseService.findAdByPosition("details.jsp");
		log.debug(adList.size());
		int a = 4;
		if (a > adList.size()) {
			a = adList.size();
		}
		request.setAttribute("adList", adList);
		request.setAttribute("adListLength", a);

		// 插入访问记录
		ipTrackService = (IIpTrackService) getBean("IpTrackService");
		String ipAddress = request.getRemoteAddr();
		IpTrack ipTrack = new IpTrack();
		ipTrack.setIpAddress(ipAddress);
		ipTrack.setProductId(productId);
		ipTrack.setTime(getCurrTime());
		ipTrack.setTimes(1);

		
		
		if (ipTrackService.isExisted(ipAddress,productId))
		{
			log.debug("更新跟踪ip访问跟踪");
			IpTrack old = ipTrackService.findByIpAndProductID(ipAddress, productId);
			old.setTimes(old.getTimes()+1);
			ipTrackService.update(old);
		}else {
			log.debug("插入ip访问跟踪");
			ipTrackService.insertIpTrack(ipTrack);
		}

		return mapping.findForward("details");
	}

	public ActionForward getCommentJsonByPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

		int productId = Integer.parseInt(request.getParameter("productId"));
		int page = 0;
		if (null != request.getParameter("page")) {
			page = Integer.parseInt(request.getParameter("page").toString());
		}
		productService = (IProductService) getBean("ProductService");
		commentService = (ICommentService) getBean("CommentService");

		Product product = productService.getProductById(productId);
		// request.setAttribute("product", product);
		List commentList = commentService.getCommentByProduct(product);
		String json = toPageCommentJSON(commentList, page);
		response.getWriter().write(json);
		return mapping.findForward("");
	}

	public String toPageCommentJSON(List list, int page) {
		List pageCommentList = new ArrayList();
		page++;
		for (int i = (page - 1) * 10; i < list.size() && i < page * 10; i++) {
			pageCommentList.add(list.get(i));
		}
		String json = "{\"commentList\":[";
		for (int i = 0; i < pageCommentList.size(); i++) {
			Comment comment = (Comment) pageCommentList.get(i);
			json += "{\"userName\":\"" + comment.getUser().getUsername()
					+ "\",";
			json += "\"commentStr\":\"" + comment.getCommentContent() + "\",";
			json += "\"commentTime\":\"" + comment.getCommentTime().toString()
					+ "\"";
			json += "},";
		}
		if (pageCommentList.size() != 0) {
			json = json.substring(0, json.length() - 1);
		}

		json += "]}";
		// TODO add the size to json
		return json;
	}

	public List getCommentByPage(List list, int page) {
		List pageCommentList = new ArrayList();
		page++;
		for (int i = (page - 1) * 10; i < list.size() && i < page * 10; i++) {
			pageCommentList.add(list.get(i));
		}
		return pageCommentList;
	}

	public ActionForward scoring(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub
		int productId = Integer.parseInt(request.getParameter("productId"));
		Double score = Double.parseDouble(request.getParameter("score"));
		productService = (IProductService) getBean("ProductService");
		preferenceService = (IProductPreferenceService) getBean("ProductPreferenceService");
		Product product = productService.getProductById(productId);
		DecimalFormat df = new DecimalFormat("#.00");
		Double d = (product.getScore() * product.getLevelClick() + score + 0.1)
				/ (product.getLevelClick() + 1);
		product.setScore(Double.parseDouble(df.format(d)));
		product.setLevelClick(product.getLevelClick() + 1);
		productService.updateProduct(product);
		// request.setAttribute("product", product);
		response.getWriter().write(
				"{\"status\":\"ok\",\"score\":\"" + product.getScore() + "\"}");

		ProductPreference productPreference = new ProductPreference();

		productPreference.setPreference(score);
		productPreference.setProduct(product);

		if (getCurrentUser() == null) {
			productPreference.setUser(getDefaultUser());
		} else {
			productPreference.setUser(getCurrentUser());
		}

		productPreference.setTimestamp(getCurrTime());

		preferenceService.insertProductPreference(productPreference);

		log.debug("Insert preference success");

		return mapping.findForward("");
	}

	public ActionForward getClassic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub

		productService = (IProductService) getBean("ProductService");
		List classicList = productService.getClassic();
		request.setAttribute("classicList", classicList);
		log.debug(classicList.size());
		return null;
	}

	public ActionForward getCollectionTop(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

		collectionService = (ICollectionService) getBean("CollectionService");
		List collectionTopList = collectionService.getCollectionTopList();
		request.setAttribute("collectionTopList", collectionTopList);
		log.debug(collectionTopList.size());
		return null;
	}

	public ActionForward getZhengpin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub

		productService = (IProductService) getBean("ProductService");
		List zhengpinList = productService.getZhengpin();
		request.setAttribute("zhengpinList", zhengpinList);
		log.debug(zhengpinList.size());
		return null;
	}

	public ActionForward getFasion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub

		productService = (IProductService) getBean("ProductService");
		List fasionList = productService.getFasion();
		request.setAttribute("fasionList", fasionList);
		log.debug(fasionList.size());
		return null;
	}

	public ActionForward getTopScore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub

		productService = (IProductService) getBean("ProductService");
		List topScoreList = productService.getTopScore();
		request.setAttribute("topScoreList", topScoreList);
		log.debug(topScoreList.size());
		return null;
	}
	
	public ActionForward getAdDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
// TODO Auto-generated method stub
		
		advertiseService = (IAdvertiseService) getBean("AdvertiseService");
		
		//将会放到首页右下脚的广告
		List addownList = advertiseService.findAdByPosition("index.jsp-addown");

		request.setAttribute("addownList", addownList);
		
		return null;
	}
}
