<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<title>追踪统计</title>
		<!-- add your meta tags here -->

		<link href="css/layout.css" rel="stylesheet" type="text/css">
		<!--[if lte IE 6]>
<script src="js/minmax.js" type="text/javascript"></script>
<![endif]-->

		<!--[if lte IE 7]>
<link href="css/patches/patch.css" rel="stylesheet" type="text/css" >
<![endif]-->

		<link rel="stylesheet" type="text/css" href="css/main.css">
		<link rel="stylesheet" type="text/css" href="css/track.css">
		<link rel="stylesheet" href="css/pagination.css" />
		<script type="text/javascript" src="../js/jquery/jquery.min.js"></script>
		<script type="text/javascript"
			src="../js/jquery_pagination/jquery.pagination.js"> </script>
		<script type="text/javascript" src="../js/json2.js"></script>
		<script type="text/javascript" src="js/track.js"></script>

		<link rel="stylesheet" href="fusionChart/Style.css" type="text/css" />
		<script language="JavaScript" src="fusionChart/FusionCharts.js"></script>

		<script>
    $(document).ready(function(){   
             trinitPagination("<bean:write name="length" />",0);
        });
    </script>
	</head>
	<body>
		<div class="page_margins">
			<div id="border-top">
				<div id="edge-tl"></div>
				<div id="edge-tr"></div>
			</div>
			<div class="page">
				<jsp:include page="../header.jsp" />
				<div id="main">
					<div id="col1">
						<div id="col1_content" class="clearfix">
							<!-- add your content here -->

							<div id="menu"><jsp:include page="menu.jsp" /></div>
						</div>
					</div>
					<div id="col3">
						<div id="col3_content" class="clearfix">
							<!-- add your content here -->
							<div class="content-title">
									<h3>
										追踪统计
									</h3>
					</div>
							<div id="productTab">
								<div id="tabs1">
									<ul id="tabsul">
										<li>
											<a id="tab1" class="tab" onmouseover="showTab(1)"><span>页面次数统计</span>
											</a>
										</li>
										<li>
											<a id="tab2" class="tab" onmouseover="showTab(2)"><span>访问时间统计</span>
											</a>
										</li>
										<li>
											<a id="tab3" class="tab" onmouseover="showTab(3)"><span>具体访问</span>
											</a>
										</li>
										
									</ul>
								</div>
							</div>

							<div id="trackList">
								<fieldset id="trackfs">
									<legend>
										
									</legend>
									<form action="" method="post">
										<table id="trackTable" border="0">
											<thead>
												<tr>
													<th class="numth">
														记录号
													</th>
													<th class="ipth">
														IP地址
													</th>
													<th class="nameth">
														用户名
													</th>
													<th class="pageth">
														访问的页面
													</th>
													<th class="timeth">
														访问时间(s)
													</th>
												</tr>
											</thead>
											<tbody id="tracktbody">
												<c:if test="${!empty requestScope.trackList}">
													<logic:iterate id="track" name="trackList">
														<tr>
															<td class="numtd">
																<p id="pTrack${track.trackId}">
																	${track.trackId}
																</p>
															</td>
															<td class="iptd">
																<p id="pTrack${track.ipAdress}">
																	${track.ipAdress}
																</p>
															</td>
															<td class="nametd">
																<p id="pTrack${track.userName}">
																	${track.userName}
																</p>
															</td>
															<td class="pagetd">
																<p id="pTrack${track.pageName}">
																	${track.pageName}
																</p>
															</td>
															<td class="timetd">
																<p id="pTrack${track.time}">
																	${track.time}
																</p>
															</td>
														</tr>
													</logic:iterate>
												</c:if>
											</tbody>
										</table>
									</form>
									<div id="Pagination" class="pagination">
										分页栏
									</div>
									<br />
								</fieldset>
							</div>
							<div id="visitFusionChart">
							<%
								String traStrXML = request.getAttribute("traStrXML").toString();
							%>
							<input type="radio" name="static" value="column"
								onclick="selectColumn()" checked>
							柱状统计
							<input type="radio" name="static" value="pie"
								onclick="selectPie()">
							柄状统计
							<br>

							<div id="FCF_Pie3D">

								<jsp:include page="fusionChart/FusionChartsHTMLRenderer.jsp"
									flush="true">
									<jsp:param name="chartSWF" value="fusionChart/Pie3D.swf" />
									<jsp:param name="strURL" value="" />
									<jsp:param name="strXML" value="<%=traStrXML%>" />
									<jsp:param name="chartId" value="myNext" />
									<jsp:param name="chartWidth" value="700" />
									<jsp:param name="chartHeight" value="400" />
									<jsp:param name="debugMode" value="false" />
								</jsp:include>
							</div>
							<div id="FCF_Column3D">
								<jsp:include page="fusionChart/FusionChartsHTMLRenderer.jsp"
									flush="true">
									<jsp:param name="chartSWF" value="fusionChart/Column3D.swf" />
									<jsp:param name="strURL" value="" />
									<jsp:param name="strXML" value="<%=traStrXML%>" />
									<jsp:param name="chartId" value="myNext" />
									<jsp:param name="chartWidth" value="700" />
									<jsp:param name="chartHeight" value="400" />
									<jsp:param name="debugMode" value="false" />
								</jsp:include>
							</div>
						</div>
						<div id="timeFusionChart">
							<%
								String timeStrXML = request.getAttribute("timeStrXML").toString();
							%>
							<input type="radio" name="static2" value="column"
								onclick="selectTimeColumn()" checked>
							柱状统计
							<input type="radio" name="static2" value="pie"
								onclick="selectTimePie()">
							柄状统计
							<br>

							<div id="time_FCF_Pie3D">

								<jsp:include page="fusionChart/FusionChartsHTMLRenderer.jsp"
									flush="true">
									<jsp:param name="chartSWF" value="fusionChart/Pie3D.swf" />
									<jsp:param name="strURL" value="" />
									<jsp:param name="strXML" value="<%=timeStrXML%>" />
									<jsp:param name="chartId" value="myNext" />
									<jsp:param name="chartWidth" value="700" />
									<jsp:param name="chartHeight" value="400" />
									<jsp:param name="debugMode" value="false" />
								</jsp:include>
							</div>
							<div id="time_FCF_Column3D">
								<jsp:include page="fusionChart/FusionChartsHTMLRenderer.jsp"
									flush="true">
									<jsp:param name="chartSWF" value="fusionChart/Column3D.swf" />
									<jsp:param name="strURL" value="" />
									<jsp:param name="strXML" value="<%=timeStrXML%>" />
									<jsp:param name="chartId" value="myNext" />
									<jsp:param name="chartWidth" value="700" />
									<jsp:param name="chartHeight" value="400" />
									<jsp:param name="debugMode" value="false" />
								</jsp:include>
							</div>
						</div>
						</div>
						<!-- IE Column Clearing -->
						<div id="ie_clearing">
							&#160;
						</div>
						
					</div>
				</div>
				<!-- begin: #footer -->
				<div id="footer"><jsp:include page="../footer.jsp" /></div>
			</div>
			<div id="border-bottom">
				<div id="edge-bl"></div>
				<div id="edge-br"></div>
			</div>
		</div>
	</body>
</html>



