package com.mashup.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.mashup.domain.Resources;
import com.mashup.service.IResourceService;
import com.mashup.service.IRoleService;

import com.mashup.domain.Role;

public class ResourceManageAction extends BaseAction{

	private IResourceService resourceService;
	private IRoleService roleService;
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		resourceService = (IResourceService)getBean("ResourceService");
		
		DynaActionForm resForm = (DynaActionForm)form;
		String resName = resForm.getString("name");
		String resValue = resForm.getString("value");
		String resType = "URL";
		String resDesc = resForm.getString("desc");
		String resRole = resForm.getString("role");
		
		log.debug(resName);
		log.debug(resValue);
		log.debug(resDesc);

		
		Resources resource = new Resources();
		
		resource.setResourceName(resName);
		resource.setResourceType(resType);
		resource.setResourceValue(resValue);
		resource.setResourceDesc(resDesc);
		resource.setResourcesRoles(null);
		resourceService.insertResource(resource);
				
		return mapping.findForward("resource");
	}
	
	
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		log.debug(id);
		resourceService = (IResourceService) getBean("ResourceService");
		resourceService.deleteResource(id);
		log.debug("delete resource success");
		return mapping.findForward("resource");
	}
	
	public ActionForward batchRemove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String[] params = request.getParameterValues("select");
		
		List<Integer> ids = new ArrayList<Integer>();
		
		for (String str : params) {
			ids.add(Integer.parseInt(str));
			log.debug(Integer.parseInt(str));
		}
		
		resourceService = (IResourceService)getBean("ResourceService");
		resourceService.batchRemove(ids);
		
		return mapping.findForward("resource");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("resource");
	}
	
	public ActionForward findAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		resourceService = (IResourceService)getBean("ResourceService");
		List<Resources> resources = resourceService.findAll();
		request.setAttribute("resources", resources);
		return mapping.findForward("resource");
	}
	
	public ActionForward load(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		resourceService = (IResourceService)getBean("ResourceService");
		List<Resources> resources = resourceService.findAll();
		roleService = (IRoleService)getBean("RoleService");
		List<Role> roles = roleService.findAll();
		List<String> types = resourceService.getResourcesType();
		request.setAttribute("resources", resources);
		request.setAttribute("roles", roles);
		request.setAttribute("types", types);
		return null;
	}
}
