<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<title>广告管理</title>
		<!-- add your meta tags here -->

		<link rel="stylesheet" type="text/css" href="css/main.css">
		<link rel="stylesheet" href="css/pagination.css" />
		<link rel="stylesheet" href="css/ad.css" />
		
		<script type="text/javascript" src="js/jquery/jquery.min.js"></script>
		<script type="text/javascript"
			src="js/jquery_pagination/jquery.pagination.js"> </script>
		<script type="text/javascript" src="js/admgr.js"> </script>
		<script type="text/javascript" src="js/ajax.js"> </script>
		<script type="text/javascript" src="../js/json2.js"></script>
		
		<link rel="stylesheet" href="fusionChart/Style.css" type="text/css" /> 
		<script language="JavaScript" src="fusionChart/FusionCharts.js"></script> 

		<link type="text/css" href="css/redmond/jquery-ui-1.7.2.custom.css"
			rel="stylesheet" />
		<!-- <script type="text/javascript" src="js/jquery/jquery-1.3.2.min.js"></script>  -->
		<script type="text/javascript"
			src="js/jquery/jquery-ui-1.7.2.custom.min.js"></script>

		<script>
	 	 	$(document).ready(function(){   
	 		
	 		adinitPagination("<bean:write name="length" />",0);
	 			
				// Datepicker
				$('#startTime').datepicker({ dateFormat: 'yy-mm-dd'  });
				$('#endTime').datepicker({ dateFormat: 'yy-mm-dd'  });
				
				// var chart = new FusionCharts("fusionChart/Pie3D.swf", "ChartId", "500", "300", "0", "0");
		  		// chart.setDataXML('<bean:write name="adStrXML" />');	
		  		// alert("<bean:write name="adStrXML" />");	   
		  		// chart.render("FCF_Pie3D");
		  		
        }); 
     </script>

		<link href="css/layout.css" rel="stylesheet" type="text/css">
		<!--[if lte IE 6]>
<script src="js/minmax.js" type="text/javascript"></script>
<![endif]-->

		<!--[if lte IE 7]>
<link href="css/patches/patch.css" rel="stylesheet" type="text/css" >
<![endif]-->


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
										广告管理
									</h3>
								</div>
							<div id="productTab">
								<div id="tabs1">
									<ul id="tabsul">
										<li>
											<a id="tab1" class="tab" onclick="showTab(1)"><span>广告删除</span>
											</a>
										</li>
										<li>
											<a id="tab2" class="tab" onclick="showTab(2)"><span>广告添加</span>
											</a>
										</li>
										<li>
											<a id="tab3" class="tab" onclick="showTab(3)"><span>广告统计</span>
											</a>
										</li>
									</ul>
								</div>
							</div>

							<div id="adList">
								<fieldset id="adfs">
									<legend>
										
									</legend>
									<div class="ad-items">
										<form action="admgr.do?method=delete" method="post"
											onsubmit="return deleteSure();">
											
												<table id="mytable">
												
												<thead>
													<tr>
														
														<th class="adName">
															广告描述
														</th>
														<th class="adposition">
															投放页面
														</th>
														<th class="time">
															开始时间
														</th>
														<th class="time">
															截止时间
														</th>
														<th class="deleteth">
															<input type="submit" value=" " id="btn_batch_delete" />
														</th>
													</tr>
												</thead>
												<tbody id="adtbody">
													<c:if test="${!empty requestScope.adList}">
														<logic:iterate id="ad" name="adList">
															<tr>
																
																<td class="adName">
																	${ad.adName}
																</td>
																<td class="adposition">
																	${ad.adPostion}
																</td>
																<td class="starttime">
																	${ad.startTime}
																</td>
																<td class="endtime">
																	${ad.endTime}
																</td>
																<td class="deletetd">
																	<input type="checkbox" name="boxDelete"
																		value="${ad.advertiseId}" />
																</td>
															</tr>
														</logic:iterate>
													</c:if>
												</tbody>
											</table>
										</form>
										
									</div>
									<!-- //ad-items-->
								</fieldset>
								
								<div id="Pagination" class="pagination">
									分页栏
								</div>
							</div>
							<!-- //adList -->
							<div id="addAd">
								
								<fieldset id="addfs">
									<legend>
										
									</legend>
									<div class="ad-item">
										<html:form action="/admin/admgr.do?method=add" method="post"
											enctype="multipart/form-data" onsubmit="return addSure();">
											<table class="addadTable">
												<tbody id="adbody">
													<tr>
														<td class="p_txt">
															广告名称：
														</td>
														<td class="txt_ad">
															<html:text property="adName" styleId="ad_name" styleClass="txtp"
																onblur="isNull('ad_name')" value="" />
														</td>
														<td valign="top" class="startd">
															<span class="star" id="ad_name_re">*</span>
														</td>
														<td valign="top"  id="ad_name_re_m" class="warntd">
															
														</td>
													</tr>
													<tr>
														<td class="p_txt">
															广告描述：
														</td>
														<td class="txt_ad">
															<html:textarea property="adDesc" styleId="ad_text"
																onblur="isNull('ad_text')" value="" rows="5" cols="28"/>
														</td>
														<td valign="top" class="startd">
															<span class="star" id="ad_text_re">*</span>
														</td>
														<td valign="top" id="ad_text_re_m" class="warntd">
															
														</td>
													</tr>
													<tr>
														<td class="p_txt">
															广告类型：
														</td>
														<td class="txt_ad">
															<html:select property="adCategoryId">
																<option value="2">
																	图片
																<option value="1">
																	flash
															</html:select>
														</td>
														<td class="lefttd">
															
														</td>
													</tr>
													<tr>
														<td class="p_txt">
															上传资源：
														</td>
														<td class="txt_ad">
															<input type="file" name="TheFile" id="selectFile"
																value=" " />
														</td>
														<td class="lefttd">
															
														</td>
													</tr>
													<tr>
														<td class="p_txt">
															开始时间：
														</td>
														<td class="txt_ad">
															<input type="text" name="startTime" id="startTime" class="txtp"
																readonly="readonly" />

														</td>
														<td class="lefttd">
															
														</td>
													</tr>
													<tr>
														<td class="p_txt">
															结束时间：
														</td>
														<td class="txt_ad">
															<input type="text" name="endTime" id="endTime" class="txtp"
																readonly="readonly" />
														</td>
														<td class="lefttd">
															
														</td>
													</tr>
													<tr>
														<td class="p_txt">
															投放页面：
														</td>
														<td class="txt_ad">
															<html:select property="adPostion">
																<option value="index.jsp">
																	首页-右上角广告位
																<option value="index.jsp-adtop">
																	首页-正中广告位
																	<option value="index.jsp-addown">
																	首页-右下脚广告位
																<option value="details.jsp">
																	商品显示页面-右边广告位
																<option value="search.jsp">
																	高级搜索页面-右边广告位
																<option value="activateUser.jsp">
																	激活用户页面-右边广告位
																<option value="login.jsp">
																	登陆页面-右边广告位
																<option value="register.jsp">
																	注册页面-右边广告位
																<option value="forgot.jsp">
																	找回密码页面-右边广告位
																<option value="user/commodityCollection.jsp">
																	用户中心-商品收藏页面-右边广告位
																<option value="user/friendManagement.jsp">
																	用户中心-好友管理页面-右边广告位
																<option value="user/accountManagement.jsp">
																	用户中心-账号管理页面-右边广告位
																	
															</html:select>
														</td>
														<td class="lefttd">
															
														</td>
													</tr>
													<tr>
														<td class="p_txt">
															链接地址：
														</td>
														<td class="txt_ad">
															<html:text property="target" styleId="ad_target" styleClass="txtp"
																onblur="isNull('ad_target')" value="" />
														</td>
														<td valign="top" class="startd">
															<span class="star" id="ad_target_re">*</span>
														</td>
														<td valign="top" id="ad_target_re_m" class="warntd">
															
														</td>
													</tr>
													<tr>
														<td>
															<html:reset value=" " styleId="btnreset" />
														</td>
														<td class="txt_ad">
															<html:submit property="method" styleId="btnAdd" value=" ">
																<bean:message key="Ad.Add" />
															</html:submit>

														</td>
													</tr>
												</tbody>
											</table>
										</html:form>
									</div>
									<!-- //ad-items-->
								</fieldset>
							</div>
							<!-- //addAd -->
							<div id="fusionChart">
								<%
									String adStrXML=request.getAttribute("adStrXML").toString();
								 %>
									<input type="radio" name="static" value="column"
									onclick="selectColumn()" checked>
								柱状统计
								<input type="radio" name="static" value="pie"
									onclick="selectPie()">
								柄状统计
								<br>

								<div id="FCF_Pie3D">
								
								<jsp:include page="fusionChart/FusionChartsHTMLRenderer.jsp" flush="true">
					                <jsp:param name="chartSWF" value="fusionChart/Pie3D.swf" />
					                <jsp:param name="strURL" value="" />
					                <jsp:param name="strXML" value="<%=adStrXML%>" />
					                <jsp:param name="chartId" value="myNext" />
					                <jsp:param name="chartWidth" value="700" />
					                <jsp:param name="chartHeight" value="400" />
					                <jsp:param name="debugMode" value="false" />
					            </jsp:include>
								</div>
								<div id="FCF_Column3D">
									<jsp:include page="fusionChart/FusionChartsHTMLRenderer.jsp" flush="true">
					                <jsp:param name="chartSWF" value="fusionChart/Column3D.swf" />
					                <jsp:param name="strURL" value="" />
					                <jsp:param name="strXML" value="<%=adStrXML%>" />
					                <jsp:param name="chartId" value="myNext" />
					                <jsp:param name="chartWidth" value="700" />
					                <jsp:param name="chartHeight" value="400" />
					                <jsp:param name="debugMode" value="false" />
					            </jsp:include>
								</div>
							</div>
							<!-- //end fusionChart -->
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
		<div id="hide"
			style="display: none; filter: alpha(opacity =   100); opacity: 1;">
			<div class="infoWIN" id="infoWIN">
				<br />
			</div>
		</div>

		<c:if test="${!empty requestScope.deleteAd}">
			<script type="text/javascript">
			show('hide','广告删除成功!');
		</script>
		</c:if>
		<c:if test="${!empty requestScope.addAd}">
			<script type="text/javascript">
			show('hide','广告添加成功!');
		</script>
		</c:if>
	</body>
</html>

