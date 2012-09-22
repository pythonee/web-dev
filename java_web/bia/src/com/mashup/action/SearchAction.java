package com.mashup.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.validator.DynaValidatorForm;

import com.mashup.domain.Category;
import com.mashup.search.CategoryNode;
import com.mashup.service.IAdvertiseService;
import com.mashup.service.ICategoryService;
import com.mashup.service.ISearchService;


public class SearchAction extends BaseAction{

	ISearchService searchService;
	ICategoryService categoryService;
	IAdvertiseService advertiseService;
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ParseException
	{
		
		DynaActionForm searchForm = (DynaActionForm) form;
		String keyword=searchForm.getString("keyword");
		log.debug(keyword);
		searchService=(ISearchService)getBean("SearchService");
		categoryService = (ICategoryService) getBean("CategoryService");
		advertiseService = (IAdvertiseService) getBean("AdvertiseService");
		List resultList=searchService.search(keyword);
		// TODO Auto-generated method stub
		//获取索引
		//建立查询
		//搜索排序
		//封装？还是直接lucene的数据结构也可以直接返回？
		categoryService = (ICategoryService) getBean("CategoryService");
		List firstCategoryList=categoryService.findByFatherId(-1);
		
		request.setAttribute("firstCategoryList", firstCategoryList);
		
		request.setAttribute("resultList",resultList);
		request.setAttribute("length", resultList.size());
		
		//将会放到sidebar的广告
		List adList = advertiseService.findAdByPosition("search.jsp");
		log.debug(adList.size());
		int a=4;
		if(a>adList.size()){
			a=adList.size();
		}
		request.setAttribute("adList", adList);
		
		request.setAttribute("adListLength", a);
		
		return mapping.findForward("search");
	}
	public ActionForward adSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		// TODO Auto-generated method stub
		DynaActionForm searchForm = (DynaActionForm) form;
		searchService=(ISearchService)getBean("SearchService");
		List resultList=searchService.adSearch(searchForm);
		
		//获取索引
		//建立查询
		//搜索排序
		//封装？还是直接lucene的数据结构也可以直接返回？
		categoryService = (ICategoryService) getBean("CategoryService");
		advertiseService = (IAdvertiseService) getBean("AdvertiseService");
		List firstCategoryList=categoryService.findByFatherId(-1);
		
		request.setAttribute("firstCategoryList", firstCategoryList);
		request.setAttribute("resultList",resultList);
		request.setAttribute("length", resultList.size());
		
		//将会放到sidebar的广告
		List adList = advertiseService.findAdByPosition("search.jsp");
		log.debug(adList.size());
		int a=4;
		if(a>adList.size()){
			a=adList.size();
		}
		request.setAttribute("adList", adList);
		
		request.setAttribute("adListLength", a);
		
		return mapping.findForward("search");
	}
	public ActionForward caSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		// TODO Auto-generated method stub
		DynaActionForm searchForm = (DynaActionForm) form;
		searchService=(ISearchService)getBean("SearchService");
		String categoryId=request.getParameter("categoryId").toString();
		List resultList=searchService.caSearch(categoryId);
		
		//获取索引
		//建立查询
		//搜索排序
		//封装？还是直接lucene的数据结构也可以直接返回？
		categoryService = (ICategoryService) getBean("CategoryService");
		advertiseService = (IAdvertiseService) getBean("AdvertiseService");
		List firstCategoryList=categoryService.findByFatherId(-1);
		
		request.setAttribute("firstCategoryList", firstCategoryList);
		request.setAttribute("resultList",resultList);
		request.setAttribute("length", resultList.size());
		
		//将会放到sidebar的广告
		List adList = advertiseService.findAdByPosition("search.jsp");
		log.debug(adList.size());
		int a=4;
		if(a>adList.size()){
			a=adList.size();
		}
		request.setAttribute("adList", adList);
		
		request.setAttribute("adListLength", a);
		
		return mapping.findForward("search");
	}
}
