package com.mashup.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mashup.domain.Friend;
import com.mashup.service.IAdvertiseService;
import com.mashup.service.IFriendService;
import com.mashup.service.IUserService;
import com.mashup.domain.User;
import org.springframework.security.context.SecurityContextHolder;

public class FriendAction extends BaseAction{
	IFriendService friendService;
	IAdvertiseService advertiseService;
	IUserService userService;
	public ActionForward getFriendListByPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// TODO Auto-generated method stub
		int page=Integer.parseInt(request.getParameter("page"));
		
		//UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//userDetails.getUsername();

		User user= getCurrentUser();
		
		friendService = (IFriendService) getBean("FriendService");
		//List friendList=friendService.findByPageAndUserId(page, user.getUserId());
		List allFiendList=friendService.findByUserId(user.getUserId());
		String responseText="";
		responseText=this.toPageFriendJSON(allFiendList,page);
		response.getWriter().write(responseText);
		return mapping.findForward("");
	}
	
	public String toPageFriendJSON(List list,int page){
		List pageFriendList=new ArrayList();
		page++;
		for (int i = (page - 1) * 10; i < list.size()&& i < page * 10; i++) {
			pageFriendList.add(list.get(i));
		}
		String json="{\"userList\":[";
		for(int i=0;i<pageFriendList.size();i++){
			Friend friend=(Friend)pageFriendList.get(i);
			json+="{\"userId\":\""+friend.getUserByFriendId().getUserId()+"\",";
			json+="\"username\":\""+friend.getUserByFriendId().getUsername()+"\",";
			json+="\"email\":\""+friend.getUserByFriendId().getEmail()+"\",";
			json+="\"relationId\":\""+friend.getRelationId()+"\"";
			json+="},";
		}
		if(pageFriendList.size()!=0){
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
		int relationId=Integer.parseInt(request.getParameter("relationId"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		User user=getCurrentUser();	
		
		friendService = (IFriendService) getBean("FriendService");
		friendService.removeFriendById(relationId);
		List allFriendList=friendService.findByUserId(user.getUserId());
		
		String responseText="";
		responseText=this.toPageFriendJSON(allFriendList,page);
		response.getWriter().write(responseText);
		return mapping.findForward("");
	}
	public ActionForward getFriendLength(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		User user=getCurrentUser();
		
		friendService = (IFriendService) getBean("FriendService");
		//List friendList=friendService.findByPageAndUserId(page, user.getUserId());
		List allFiendList=friendService.findByUserId(user.getUserId());
		String responseText="";
		responseText="{\"length\":\""+allFiendList.size()+"\"}";
		response.getWriter().write(responseText);
		return mapping.findForward("");
	}
	
	public ActionForward get(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		advertiseService = (IAdvertiseService) getBean("AdvertiseService");
		//将会放到userCenter.jsp的sidebar的广告
		List adList = advertiseService.findAdByPosition("user/friendManagement.jsp");
		log.debug(adList.size());
		int a=4;
		if(a>adList.size()){
			a=adList.size();
		}
		request.setAttribute("adList", adList);
		
		request.setAttribute("adListLength", a);

		return mapping.findForward("friendManagement");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try {
			friendService = (IFriendService) getBean("FriendService");
			userService = (IUserService) getBean("UserService");
			Integer userId=Integer.parseInt(request.getParameter("userId"));
			User user=userService.getUserById(userId);
			
			Integer friendId=Integer.parseInt(request.getParameter("friendId"));
			User friendUser=userService.getUserById(friendId);
			log.debug("userId "+user.getUserId()+" "+"friendId "+friendUser.getUserId());
			Friend friend=new Friend();
			friend.setUserByFriendId(friendUser);
			friend.setUserByUserId(user);
			
			
			if(!friendService.isExsit(friend)){
				friendService.insertFriend(friend);
				response.getWriter().write("ok");
			}else{
				
				response.getWriter().write("contain");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			response.getWriter().write("failed");
		}
		

		return null;
	}
}
