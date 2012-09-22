package com.mashup.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryParser.ParseException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.mashup.domain.Sensitiveword;
import com.mashup.service.ISensWordService;

public class SensWordAction extends BaseAction{

	ISensWordService sensWordService;
	
	public ActionForward get(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ParseException
	{
		
		sensWordService=(ISensWordService)getBean("SensWordService");
		List wordList=sensWordService.findAll();
		request.setAttribute("wordList", wordList);
		request.setAttribute("length", wordList.size());
		return mapping.findForward("sensword");
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException
	{
		
		sensWordService=(ISensWordService)getBean("SensWordService");
		Integer sensitiveId=Integer.parseInt(request.getParameter("sensitiveId"));
		
		String sensWord=request.getParameter("sensWord");
		log.debug(sensitiveId+" "+sensWord);
		sensWord=java.net.URLDecoder.decode(sensWord, "utf-8");
		Sensitiveword sensitiveWord=sensWordService.findByWordId(sensitiveId);
		sensitiveWord.setSensitiveWord(sensWord);
		log.debug(sensitiveWord);
		sensWordService.updateSensWord(sensitiveWord);
		log.debug(sensWord);
		response.getWriter().write("ok");
		return mapping.findForward("");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException
	{
		
		sensWordService=(ISensWordService)getBean("SensWordService");
		
		String sensWord=request.getParameter("sensWord");
		
		sensWord=java.net.URLDecoder.decode(sensWord, "utf-8");
		Sensitiveword sensitiveWord=new Sensitiveword();
		sensitiveWord.setSensitiveWord(sensWord);
		sensWordService.insertSensWord(sensitiveWord);
		sensitiveWord = (Sensitiveword)(sensWordService.findByWord(sensWord).get(0));
		String json="{\"sensitiveId\":\""+sensitiveWord.getSensitiveId()+"\",\"sensitiveWord\":\""+sensitiveWord.getSensitiveWord()+"\",\"ok\":\"ok\"}";
		log.debug(json);
		response.getWriter().write(json);
		return mapping.findForward("");
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException
	{
		
		sensWordService=(ISensWordService)getBean("SensWordService");
		String[] boxDelete=request.getParameterValues("boxDelete");
		if(boxDelete!=null){
			for(int i=0;i<boxDelete.length;i++){
				sensWordService.removeSensitivewordById(Integer.parseInt(boxDelete[i]));
			}
			log.debug("delete words "+boxDelete.length);
		}
		List wordList=sensWordService.findAll();
		request.setAttribute("wordList", wordList);
		request.setAttribute("length", wordList.size());
		request.setAttribute("deleteSens", "ok");
		return mapping.findForward("sensword");
	}
}
