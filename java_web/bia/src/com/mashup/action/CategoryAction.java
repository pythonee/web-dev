package com.mashup.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.mashup.domain.Advertise;
import com.mashup.domain.Category;
import com.mashup.domain.Friend;
import com.mashup.service.IAdvertiseService;
import com.mashup.service.ICategoryService;
import com.mashup.search.CategoryNode;
public class CategoryAction  extends BaseAction{
	ICategoryService categoryService;
	IAdvertiseService advertiseService;
	public ActionForward get(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// TODO Auto-generated method stub
		categoryService = (ICategoryService) getBean("CategoryService");
		advertiseService = (IAdvertiseService) getBean("AdvertiseService");
		
		List allCategoryNodeList=new ArrayList();
		List firstCategoryList=categoryService.findByFatherId(-1);
		ArrayList list=new ArrayList();
		for(int i=0;i<firstCategoryList.size();i++){
			CategoryNode categoryNode=new CategoryNode((Category)firstCategoryList.get(i));
			List secondCategoryList=categoryService.findByFatherId(categoryNode.getCategoryId());
			categoryNode.categoryNodeList=secondCategoryList;
			allCategoryNodeList.add(categoryNode);
		}
		request.setAttribute("allCategoryNodeList", allCategoryNodeList);
		
		//将会放到首页sidebar的广告
		List adList = advertiseService.findSideBarAd();
		int a=4;
		if(a>adList.size()){
			a=adList.size();
		}
		request.setAttribute("indexAdList", adList);
		
		request.setAttribute("adListLength", a);
		return null;
	}
	
	public ActionForward getByFatherId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// TODO Auto-generated method stub
		categoryService = (ICategoryService) getBean("CategoryService");
		Integer fatherId=Integer.parseInt(request.getParameter("fatherId").toString());
		List categoryList=categoryService.findByFatherId(fatherId);
		Category category=categoryService.getCategoryById(fatherId);
		String jsonString=toJson(categoryList,category);
		response.getWriter().write(jsonString);
		return mapping.findForward("");
	}

	public String toJson(List list,Category category){
		String json="{\"categoryList\":[";
		if(list!=null&&list.size()!=0){
			json+="{\"categoryId\":\""+((Category)list.get(0)).getFatherId()+"\",";
			json+="\"categoryName\":\"不限\"},";
			for(int i=0;i<list.size();i++){
				Category category1=(Category)list.get(i);
				json+="{\"categoryId\":\""+category1.getCategoryId()+"\",";
				json+="\"categoryName\":\""+category1.getCategoryName()+"\",";
				json+="\"tags\":\""+category1.getTags()+"\"";
				
				json+="},";
			}
			if(list.size()!=0){
				json=json.substring(0, json.length()-1);
			}
		}
		json+="],\"length\":\""+(list.size()+1)+"\",\"fatherName\":\""+category.getCategoryName()+"\",\"tags\":\""+category.getTags()+"\"}";
		return json;
	}
	
	public String toJsonAd(List list){
		String json="{\"adList\":[";
		if(list!=null&&list.size()!=0){
			for(int i=0;i<list.size()&&i<4;i++){
				Advertise advertise=(Advertise)list.get(i);
				json+="{\"adPath\":\""+advertise.getAdPath()+"\",";
				json+="\"target\":\""+advertise.getTarget()+"\"";
				json+="},";
			}
			if(list.size()!=0){
				json=json.substring(0, json.length()-1);
			}
		}
		int a=4;
		if(a>list.size()){
			a=list.size();
		}
		json+="],\"length\":\""+a+"\"}";
		return json;
	}
	public ActionForward catemgr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// TODO Auto-generated method stub
		categoryService = (ICategoryService) getBean("CategoryService");
		List FCategoryList=categoryService.findByFatherId(-1);
		
		request.setAttribute("FCategoryList", FCategoryList);
		request.setAttribute("length", FCategoryList.size());
		
		List allCategoryNodeList=new ArrayList();
		List firstCategoryList=categoryService.findByFatherId(-1);
		ArrayList list=new ArrayList();
		for(int i=0;i<firstCategoryList.size();i++){
			CategoryNode categoryNode=new CategoryNode((Category)firstCategoryList.get(i));
			List secondCategoryList=categoryService.findByFatherId(categoryNode.getCategoryId());
			categoryNode.categoryNodeList=secondCategoryList;
			allCategoryNodeList.add(categoryNode);
		}
		request.setAttribute("allCategoryNodeList", allCategoryNodeList);
		return mapping.findForward("catemgr");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		DynaActionForm cateForm = (DynaActionForm) form;
		// TODO Auto-generated method stub
		categoryService = (ICategoryService) getBean("CategoryService");
		String categoryName=cateForm.getString("categoryName");
		String tags=cateForm.getString("tags");
		Integer fatherId=Integer.parseInt(cateForm.getString("categoryId"));
		Category category=new Category();
		category.setCategoryName(categoryName);
		category.setTags(tags);
		category.setFatherId(fatherId);

		categoryService.insertCategory(category);
		List FCategoryList=categoryService.findByFatherId(-1);
		request.setAttribute("FCategoryList", FCategoryList);
		request.setAttribute("length", FCategoryList.size());
		
		List allCategoryNodeList=new ArrayList();
		List firstCategoryList=categoryService.findByFatherId(-1);
		ArrayList list=new ArrayList();
		for(int i=0;i<firstCategoryList.size();i++){
			CategoryNode categoryNode=new CategoryNode((Category)firstCategoryList.get(i));
			List secondCategoryList=categoryService.findByFatherId(categoryNode.getCategoryId());
			categoryNode.categoryNodeList=secondCategoryList;
			allCategoryNodeList.add(categoryNode);
		}
		request.setAttribute("allCategoryNodeList", allCategoryNodeList);
		
		request.setAttribute("addCate", "ok");
		return mapping.findForward("catemgr");
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		DynaActionForm cateForm = (DynaActionForm) form;
		// TODO Auto-generated method stub
		categoryService = (ICategoryService) getBean("CategoryService");
		String categoryName=cateForm.getString("categoryName");
		String tags=cateForm.getString("tags");
		Integer fatherId=Integer.parseInt(cateForm.getString("adaptcategoryId"));
		Integer categoryId=Integer.parseInt(cateForm.getString("origcategoryId"));
		Category category=categoryService.getCategoryById(categoryId);
		category.setCategoryName(categoryName);
		category.setTags(tags);
		category.setFatherId(fatherId);

		categoryService.updateCategory(category);
		
		List FCategoryList=categoryService.findByFatherId(-1);
		request.setAttribute("FCategoryList", FCategoryList);
		request.setAttribute("length", FCategoryList.size());
		
		List allCategoryNodeList=new ArrayList();
		List firstCategoryList=categoryService.findByFatherId(-1);
		ArrayList list=new ArrayList();
		for(int i=0;i<firstCategoryList.size();i++){
			CategoryNode categoryNode=new CategoryNode((Category)firstCategoryList.get(i));
			List secondCategoryList=categoryService.findByFatherId(categoryNode.getCategoryId());
			categoryNode.categoryNodeList=secondCategoryList;
			allCategoryNodeList.add(categoryNode);
		}
		request.setAttribute("allCategoryNodeList", allCategoryNodeList);
		request.setAttribute("editCate", "ok");
		
		return mapping.findForward("catemgr");
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		DynaActionForm cateForm = (DynaActionForm) form;
		// TODO Auto-generated method stub
		categoryService = (ICategoryService) getBean("CategoryService");
		
		Integer categoryId=Integer.parseInt(cateForm.getString("deletecategoryId"));
		categoryService.removeCategoryById(categoryId);
		
		List FCategoryList=categoryService.findByFatherId(-1);
		request.setAttribute("FCategoryList", FCategoryList);
		request.setAttribute("length", FCategoryList.size());
		
		List allCategoryNodeList=new ArrayList();
		List firstCategoryList=categoryService.findByFatherId(-1);
		ArrayList list=new ArrayList();
		for(int i=0;i<firstCategoryList.size();i++){
			CategoryNode categoryNode=new CategoryNode((Category)firstCategoryList.get(i));
			List secondCategoryList=categoryService.findByFatherId(categoryNode.getCategoryId());
			categoryNode.categoryNodeList=secondCategoryList;
			allCategoryNodeList.add(categoryNode);
		}
		request.setAttribute("allCategoryNodeList", allCategoryNodeList);
		
		request.setAttribute("deleteCate", "ok");
		return mapping.findForward("catemgr");
	}
	
	public ActionForward getIndexCate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// TODO Auto-generated method stub
		categoryService = (ICategoryService) getBean("CategoryService");
		advertiseService = (IAdvertiseService) getBean("AdvertiseService");
		
		List allCategoryNodeList=new ArrayList();
		List firstCategoryList=categoryService.findByFatherId(-1);
		ArrayList list=new ArrayList();
		for(int i=0;i<firstCategoryList.size();i++){
			CategoryNode categoryNode=new CategoryNode((Category)firstCategoryList.get(i));
			List secondCategoryList=categoryService.findByFatherId(categoryNode.getCategoryId());
			categoryNode.categoryNodeList=secondCategoryList;
			allCategoryNodeList.add(categoryNode);
		}
		request.setAttribute("allCategoryNodeList", allCategoryNodeList);
		
		//将会放到首页sidebar的广告
		List adList = advertiseService.findSideBarAd();

		int a=4;
		if(a>adList.size()){
			a=adList.size();
		}
		request.setAttribute("adList", adList);
		
		request.setAttribute("adListLength", a);
		return null;
	}
	public ActionForward getAdTop(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// TODO Auto-generated method stub
		
		advertiseService = (IAdvertiseService) getBean("AdvertiseService");
		
		//将会放到首页正中的广告
		List adTopList = advertiseService.findAdByPosition("index.jsp-adtop");

		request.setAttribute("adTopList", adTopList);
		
		return null;
	}
	
	
}
