/**
 * 
 */
package com.mashup.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mashup.domain.IpTrack;
import com.mashup.domain.Track;
import com.mashup.service.IIpTrackService;
import com.mashup.service.ITrackService;

/**
 * @author Administrator
 * 
 */
public class TrackAction extends BaseAction {
	ITrackService trackService;
	IIpTrackService ipTrackService;

	public ActionForward getAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		log.debug("begin");
		trackService = (ITrackService) getBean("TrackService");
		List trackList = trackService.findAll();
		request.setAttribute("trackList", trackList);
		request.setAttribute("length", trackList.size());

		// 生成统计图形

		// 访问时间统计
		String[] colors1 = { "A186BE", "9D080D", "588526", "8E468E", "D64646",
				"008E8E", "FF8E46", "8BBA00", "F6BD0F", "AFD8F8", "AFD8F8",
				"AFD8F8", "AFD8F8", "AFD8F8", "AFD8F8", "AFD8F8", "AFD8F8",
				"AFD8F8", "AFD8F8", "AFD8F8" };
		String[] colors2 = { "AFD8F8", "AFD8F8", "AFD8F8", "AFD8F8", "AFD8F8",
				"AFD8F8", "AFD8F8", "AFD8F8", "AFD8F8", "AFD8F8", "A186BE",
				"9D080D", "588526", "8E468E", "D64646", "008E8E", "FF8E46",
				"8BBA00", "F6BD0F", "AFD8F8" };
		String timeStrXML = "";
		String strXML = "";

		timeStrXML += "<chart caption='页面访问时间统计' xAxisName='页面' yAxisName='时间(s)' showValues='0' formatNumberScale='0' showBorder='1'>";
		strXML += "<chart caption='页面访问次数统计' xAxisName='页面' yAxisName='次数(s)' showValues='0' formatNumberScale='0' showBorder='1'>";
		DataSource dataSource = (DataSource) getBean("DataSource");

		String timeQuery = "SELECT pageName, SUM(time) FROM track GROUP BY pageName";
		String clickQuery = "SELECT pageName, COUNT(*) FROM track GROUP BY pageName";
		try {
			Connection conn = (Connection) dataSource.getConnection();
			Statement getPageTime = conn.createStatement();
			ResultSet resultSet = getPageTime.executeQuery(timeQuery);
			int i = 0;
			while (resultSet.next()) {
				timeStrXML += "<set name='" + resultSet.getString(1)
						+ "' value='" + resultSet.getString(2) + "' color='"
						+ colors1[i] + "'/>";
				i++;
				log
						.debug(resultSet.getString(1) + " "
								+ resultSet.getString(2));
			}
			resultSet = getPageTime.executeQuery(clickQuery);
			i = 19;
			while (resultSet.next()) {
				strXML += "<set name='" + resultSet.getString(1) + "' value='"
						+ resultSet.getString(2) + "' color='" + colors2[i]
						+ "'/>";
				i--;
				log
						.debug(resultSet.getString(1) + " "
								+ resultSet.getString(2));
			}
			resultSet.close();
			getPageTime.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		timeStrXML += "</chart>";
		strXML += "</chart>";
		request.setAttribute("traStrXML", strXML);
		request.setAttribute("timeStrXML", timeStrXML);
		return mapping.findForward("track");
	}

	public ActionForward insertTrack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		trackService = (ITrackService) getBean("TrackService");
		String ipAdress = request.getRemoteAddr();
		String userName = request.getParameter("userName");
		userName = java.net.URLDecoder.decode(userName, "UTF-8");
		String pageName = request.getParameter("pageName");
		int time;
		try {
			time = Integer.parseInt(request.getParameter("time"));
		} catch (Exception e) {
			time = 3;// 如果出现异常，则直接给time赋值为3
		}

		if (pageName != null && pageName.startsWith("details")
				&& pageName.contains("productId")) {
			log.debug("label!!!");
			ipTrackService = (IIpTrackService) getBean("IpTrackService");
			int productId = Integer.parseInt(request.getParameter("productId"));
			IpTrack ipTrack = new IpTrack();
			ipTrack.setIpAddress(ipAdress);
			ipTrack.setProductId(productId);
			log.debug("获取产品ID：" + productId);
			ipTrackService.insertIpTrack(ipTrack);
		}

		if (pageName.equals("") || pageName.startsWith("index"))
			pageName = "index.jsp";
		if (pageName.startsWith("login"))
			pageName = "login.jsp";
		if (pageName.startsWith("register"))
			pageName = "register.jsp";
		if (pageName.startsWith("details"))
			pageName = "details.jsp";
		if (pageName.contains(".do"))
			pageName = pageName.substring(0, pageName.indexOf(".do")) + ".jsp";

		Track track = new Track();
		track.setIpAdress(ipAdress);
		track.setUserName(userName);
		track.setPageName(pageName);
		track.setTime(time);

		log.debug("获取：IP地址 " + ipAdress + " 用户名 " + userName + " 页面 "
				+ pageName + " 停留时间 " + time + "秒");
		trackService.insertRecord(track);

		response.getWriter().write("Ok");
		return mapping.findForward("");
	}
}
