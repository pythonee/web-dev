package com.mashup.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryParser.ParseException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import com.mashup.domain.Adcategory;
import com.mashup.domain.Advertise;
import com.mashup.domain.Category;
import com.mashup.search.CategoryNode;
import com.mashup.service.IAdvertiseService;
import com.mashup.service.ICategoryService;
import com.mashup.service.ISensWordService;

public class AdvertiseAction extends BaseAction {
	IAdvertiseService advertiseService;

	public ActionForward get(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub

		advertiseService = (IAdvertiseService) getBean("AdvertiseService");
		List adList = advertiseService.findAll();
		for(int i=0;i<adList.size();i++){
			Advertise ad=(Advertise)adList.get(i);
			if(ad.getAdPostion().equals("index.jsp")){
				ad.setAdPostion(ad.getAdPostion()+"首页右上角");
			}else if(ad.getAdPostion().equals("index.jsp-adtop")){
				ad.setAdPostion("index.jsp首页正中");
			}else if(ad.getAdPostion().equals("index.jsp-addown")){
				ad.setAdPostion("index.jsp首页右下脚");
			}else if(ad.getAdPostion().equals("details.jsp")){
				ad.setAdPostion(ad.getAdPostion()+"具体商品显示页面");
			}else if(ad.getAdPostion().equals("search.jsp")){
				ad.setAdPostion(ad.getAdPostion()+"搜索结果页面");
			}else if(ad.getAdPostion().equals("user/accountManagement.jsp")){
				ad.setAdPostion(ad.getAdPostion()+"用户个人中心页面");
			}else if(ad.getAdPostion().equals("user/commodityCollection")){
				ad.setAdPostion(ad.getAdPostion()+"用户商品收藏页面");
			}else if(ad.getAdPostion().equals("user/friendManagement.jsp")){
				ad.setAdPostion(ad.getAdPostion()+"用户好友管理页面");
			}else if(ad.getAdPostion().equals("login.jsp")){
				ad.setAdPostion(ad.getAdPostion()+"登陆页面");
			}else if(ad.getAdPostion().equals("register.jsp")){
				ad.setAdPostion(ad.getAdPostion()+"注册页面");
			}else if(ad.getAdPostion().equals("forgot.jsp")){
				ad.setAdPostion(ad.getAdPostion()+"找回密码页面");
			}else if(ad.getAdPostion().equals("activateUser.jsp")){
				ad.setAdPostion(ad.getAdPostion()+"激活用户页面");
			}
		}
		request.setAttribute("adList", adList);
		request.setAttribute("length", adList.size());
		String index_jsp="index.jsp";
		String index_jsp_top="index.jsp-adtop";
		String index_jsp_down="index.jsp-addown";
		String details_jsp="details.jsp";
		String search_jsp="search.jsp";
		String accountManagement_jsp="user/accountManagement.jsp";
		String collection_jsp="user/commodityCollection.jsp";
		String friend_jsp="user/friendManagement.jsp";
		String login_jsp="login.jsp";
		String register_jsp="register.jsp";
		String forgot_jsp="forgot.jsp";
		String activeUser_jsp="activateUser.jsp";
		
		List indexList=advertiseService.findAdByPosition(index_jsp);
		List index_top_List=advertiseService.findAdByPosition(index_jsp_top);
		List index_down_List=advertiseService.findAdByPosition(index_jsp_down);
		List detailsList=advertiseService.findAdByPosition(details_jsp);
		List searchList=advertiseService.findAdByPosition(search_jsp);
		
		List accountList=advertiseService.findAdByPosition(accountManagement_jsp);
		List collectionList=advertiseService.findAdByPosition(collection_jsp);
		List friendList=advertiseService.findAdByPosition(friend_jsp);
		List loginList=advertiseService.findAdByPosition(login_jsp);
		List registerList=advertiseService.findAdByPosition(register_jsp);
		List forgotList=advertiseService.findAdByPosition(forgot_jsp);
		List activeUserList=advertiseService.findAdByPosition(activeUser_jsp);
		   String adStrXML="";

		   adStrXML += "<chart caption='页面广告统计' xAxisName='页面' yAxisName='数量' showValues='0' formatNumberScale='0' showBorder='1'>";
		   
           adStrXML += "<set name='index.jsp首页右上角' value='"+(indexList==null?0:indexList.size())+"' color='AFD8F8'/>";
           adStrXML += "<set name='index.jsp首页正中' value='"+(index_top_List==null?0:index_top_List.size())+"' color='9D080D'/>";
           adStrXML += "<set name='index.jsp首页右下脚' value='"+(index_down_List==null?0:index_down_List.size())+"' color='A186BE'/>";
           adStrXML += "<set name='search.jsp高级搜索页面' value='"+(detailsList==null?0:detailsList.size())+"' color='F6BD0F'/>";
           adStrXML += "<set name='details.jsp具体商品显示页面' value='"+(searchList==null?0:searchList.size())+"' color='8BBA00'/>";
          
           adStrXML += "<set name='accountManagement.jsp用户个人中心页面' value='"+(accountList==null?0:accountList.size())+"' color='008E8E'/>";
           adStrXML += "<set name='commodityCollection.jsp用户商品收藏页面' value='"+(collectionList==null?0:collectionList.size())+"' color='D64646'/>";
           adStrXML += "<set name='friendManagement.jsp用户好友管理页面' value='"+(friendList==null?0:friendList.size())+"' color='8E468E'/>";
           adStrXML += "<set name='login.jsp登陆页面' value='"+(loginList==null?0:loginList.size())+"' color='588526'/>";
           adStrXML += "<set name='register.jsp注册页面' value='"+(registerList==null?0:registerList.size())+"' color='B3AA00'/>";
           adStrXML += "<set name='forgot.jsp找回密码页面' value='"+(forgotList==null?0:forgotList.size())+"' color='008ED6'/>";
           adStrXML += "<set name='activateUser.jsp激活用户页面' value='"+(activeUserList==null?0:activeUserList.size())+"' color='8BBA00'/>";
          // strXML += "<set name='Jun' value='960' color='D64646'/>";
          // strXML += "<set name='Jul' value='629' color='8E468E'/>";
          // strXML += "<set name='Aug' value='622' color='588526'/>";
          // strXML += "<set name='Sep' value='376' color='B3AA00'/>";
          // strXML += "<set name='Oct' value='494' color='008ED6'/>";
          // strXML += "<set name='Nov' value='761' color='9D080D'/>";
          // strXML += "<set name='Dec' value='960' color='A186BE'/>";
           adStrXML += "</chart>";
          request.setAttribute("adStrXML", adStrXML);
          
		return mapping.findForward("admgr");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException, IOException {

		advertiseService = (IAdvertiseService) getBean("AdvertiseService");
		String[] boxDelete = request.getParameterValues("boxDelete");
		if (boxDelete != null) {
			for (int i = 0; i < boxDelete.length; i++) {
				advertiseService.removeAdvertiseById(Integer
						.parseInt(boxDelete[i]));
			}
			log.debug("delete ads " + boxDelete.length);
		}else{
			log.debug("delete ads size 0!!!!!!!!!!!");
		
		}
		//List adList = advertiseService.findAll();
		//request.setAttribute("adList", adList);
		//request.setAttribute("length", adList.size());
		request.setAttribute("deleteAd", "ok");
		
		
		return get(mapping,form,request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException, IOException, java.text.ParseException {
		DynaActionForm adForm = (DynaActionForm) form;
		String adName = adForm.getString("adName");
		String adDesc = adForm.getString("adDesc");
		Integer adCategoryId = Integer.parseInt(adForm
				.getString("adCategoryId"));
		String adPostion = adForm.getString("adPostion");
		String target = adForm.getString("target");
		String startTime = adForm.getString("startTime");
		String endTime = adForm.getString("endTime");
		String adPath = "";
		Hashtable files = adForm.getMultipartRequestHandler().getFileElements();
		for (Enumeration e = files.keys(); e.hasMoreElements();) {
			FormFile formfile = (FormFile) files.get((String) e.nextElement());
			if (formfile != null && formfile.getFileSize() > 0) {
				upload(formfile, request);
				adPath = "images/adimages/" + formfile.getFileName().trim();
				adPath = new String(adPath.getBytes(), "UTF-8");
				log.debug("images/adimages/" + formfile.getFileName().trim());
				break;
			}
		}

		Adcategory adcategory = new Adcategory();
		adcategory.setAdCategoryId(adCategoryId);
		Advertise advertise = new Advertise();
		advertise.setAdName(adName);
		advertise.setAdDesc(adDesc);
		advertise.setAdPostion(adPostion);
		advertise.setTarget(target);
		DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		Date sDate = dt.parse(startTime);
		Date eDate = dt.parse(endTime);
		advertise.setStartTime(sDate);
		advertise.setEndTime(eDate);
		advertise.setAdcategory(adcategory);
		advertise.setAdPath(adPath);
		advertiseService = (IAdvertiseService) getBean("AdvertiseService");
		advertiseService.insertAdvertise(advertise);
		//List adList = advertiseService.findAll();
		//request.setAttribute("adList", adList);
		//request.setAttribute("length", adList.size());
		request.setAttribute("addAd", "ok");
		return get(mapping,form,request, response);
	}

	public void upload(FormFile file, HttpServletRequest request) {
		if ("".equals(file.getFileName()) || null == file.getFileName()) {
			return;
		}

		// 得到当前网站的绝对路径
		String path = this.getServlet().getServletContext().getRealPath("/");
		FileOutputStream fileOutput;
		try {
			// 文件操作
			fileOutput = new FileOutputStream(path + "images/adimages/"
					+ file.getFileName().trim());

			fileOutput.write(file.getFileData());

			fileOutput.flush();

			fileOutput.close();

			log.debug("广告上传成功");

		}

		catch (FileNotFoundException e) {
			log.debug("广告上传失败");

		} catch (IOException e) {
			log.debug("广告上传失败");
		}
	}
	
	public ActionForward getSideAd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// TODO Auto-generated method stub
		
		advertiseService = (IAdvertiseService) getBean("AdvertiseService");
		String postion=request.getParameter("position");
		//将会放到sidebar的广告
		List adList = advertiseService.findAdByPosition(postion);
		log.debug(adList.size());
		int a=4;
		if(a>adList.size()){
			a=adList.size();
		}
		request.setAttribute("adList", adList);
		
		request.setAttribute("adListLength", a);
		return null;
	}
}
