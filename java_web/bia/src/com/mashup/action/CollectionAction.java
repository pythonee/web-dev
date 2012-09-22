package com.mashup.action;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mashup.domain.User;
import com.mashup.domain.Product;
import com.mashup.domain.Collection;
import com.mashup.service.IAdvertiseService;
import com.mashup.service.ICollectionService;
import com.mashup.service.IProductService;

public class CollectionAction extends BaseAction{
	
	ICollectionService collectionService;
	IProductService productService;
	IAdvertiseService advertiseService;
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// TODO Auto-generated method stub
		int productId=Integer.parseInt(request.getParameter("productId"));
		
		User user = getCurrentUser();
		
		collectionService = (ICollectionService) getBean("CollectionService");
		productService = (IProductService) getBean("ProductService");
		Product product=productService.getProductById(productId);
		Collection collection=new Collection();
		collection.setProduct(product);
		collection.setUserId(user.getUserId());
		collectionService.insertCollection(collection);
		String responseText="{\"status\":\"ok\"}";
		response.getWriter().write(responseText);
		return mapping.findForward("");
	}	
	
/////////////////////////////////////////////////////////
	public ActionForward getCollectionListByPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// TODO Auto-generated method stub
		int page=Integer.parseInt(request.getParameter("page"));
		
		User user= getCurrentUser();
		
		collectionService = (ICollectionService) getBean("CollectionService");
		//List friendList=friendService.findByPageAndUserId(page, user.getUserId());
		List allCollectionList=collectionService.findByUserId(user.getUserId());
		String responseText="";
		responseText=this.toPageCollectionJSON(allCollectionList,page);
		response.getWriter().write(responseText);
		return mapping.findForward("");
	}
	
	public String toPageCollectionJSON(List list,int page){
		List pageCollectionList=new ArrayList();
		page++;
		for (int i = (page - 1) * 10; i < list.size()&& i < page * 10; i++) {
			pageCollectionList.add(list.get(i));
		}
		String json="{\"collectionList\":[";
		for(int i=0;i<pageCollectionList.size();i++){
			Collection collection=(Collection)pageCollectionList.get(i);
			json+="{\"collectionId\":\""+collection.getCollectionId()+"\",";
			json+="\"productName\":\""+collection.getProduct().getProductName()+"\",";
			json+="\"productId\":\""+collection.getProduct().getProductId()+"\"";
			json+="},";
		}
		if(pageCollectionList.size()!=0){
			json=json.substring(0, json.length()-1);
		}
		
		json+="],\"length\":\""+list.size()+"\"}";
		//TODO add the size to json
		return json;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// TODO Auto-generated method stub
		int collectionId=Integer.parseInt(request.getParameter("collectionId"));
		int page=Integer.parseInt(request.getParameter("page"));
		User user= getCurrentUser();
		collectionService = (ICollectionService) getBean("CollectionService");
		collectionService.removeCollectionById(collectionId);
		List allCollectionList=collectionService.findByUserId(user.getUserId());
		
		String responseText="";
		responseText=this.toPageCollectionJSON(allCollectionList,page);
		response.getWriter().write(responseText);
		return mapping.findForward("");
	}
	public ActionForward getCollectionLength(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		User user=getCurrentUser();
		
		collectionService = (ICollectionService) getBean("CollectionService");
		//List friendList=friendService.findByPageAndUserId(page, user.getUserId());
		List allCollectionList=collectionService.findByUserId(user.getUserId());
		String responseText="";
		responseText="{\"length\":\""+allCollectionList.size()+"\"}";
		response.getWriter().write(responseText);
		return mapping.findForward("");
	}
	
	public ActionForward get(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		advertiseService = (IAdvertiseService) getBean("AdvertiseService");
		//将会放到commodityCollection.jsp的sidebar的广告
		List adList = advertiseService.findAdByPosition("user/commodityCollection.jsp");
		log.debug(adList.size());
		int a=4;
		if(a>adList.size()){
			a=adList.size();
		}
		request.setAttribute("adList", adList);
		
		request.setAttribute("adListLength", a);

		return mapping.findForward("commodityCollection");
	}
	
	public ActionForward hotCollection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException,SQLException
	{
		DataSource dataSource = (DataSource)getBean("DataSource");
		String query = "SELECT productId, COUNT(productId) AS number FROM collection Group by productId order by number desc limit 10";
		Connection conn = null;
		List<Product> topItems = new ArrayList<Product>();
		productService = (IProductService)getBean("ProductService");
		
		try
		{
			conn = (Connection)dataSource.getConnection();
			Statement getTopItemsIdStam = conn.createStatement();
			ResultSet resultSet = getTopItemsIdStam.executeQuery(query);
			
			while (resultSet.next())
			{
				Product product = productService.getProductById(resultSet.getInt(1));
				
				if (product.getProductName().length() > 15) {
					product.setProductName(product.getProductName().substring(0,15));
				}
				
				log.debug("collection id: " + resultSet.getInt(1));
				
				topItems.add(product);			
			}
			
			request.setAttribute("collectionList", topItems);
			log.debug("collection top items size: " + topItems.size());
			resultSet.close();
			getTopItemsIdStam.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}finally{
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	} 
}
