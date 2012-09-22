package com.mashup.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

public class SetupAction extends BaseAction {

	public ActionForward goback(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean started = false;

		ServletContext ctx = request.getSession().getServletContext();
		StdSchedulerFactory factory = (StdSchedulerFactory) ctx
				.getAttribute(StdSchedulerFactory.DEFAULT_INSTANCE_ID);
		Scheduler scheduler = factory.getDefaultScheduler();
		log.debug("启动时间"
				+ scheduler.getTrigger("startTime", scheduler.DEFAULT_GROUP)
						.getStartTime());

		if (scheduler.isStarted())
			started = true;

		request.setAttribute("started", started);

		return mapping.findForward("setup");
	}
}
