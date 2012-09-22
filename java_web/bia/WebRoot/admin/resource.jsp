<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<title>Your Page Title</title>
	<!-- add your meta tags here -->

	<link href="css/layout.css" rel="stylesheet" type="text/css">
	<!--[if lte IE 6]>
	<script src="js/minmax.js" type="text/javascript"></script>
	<![endif]-->
	
		<!--[if lte IE 7]>
	<link href="css/patches/patch.css" rel="stylesheet"  type="text/css" >
	<![endif]-->
	<link href="css/resource.css" rel="stylesheet" type="text/css">
	<script src="js/resource.js" type="text/javascript"></script>
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
										资源权限管理
									</h3>
					</div>
						<jsp:include page="/admin/resource.do?method=load"></jsp:include>
						
						<html:form action="admin/resource.do?method=add">
							<table>
								<tr>
									<td class="p_name">
										名字:
									</td>
									<td class="txt_name">
										<html:text property="name"></html:text>
									</td>
								</tr>
								<tr>
									<td class="p_right">
										权限:
									</td>
									<td class="txt_right">
										<html:select property="role">
											<c:forEach var="role" items="${roles}">
												<html:option value="role.name">
													<c:out value="${role.roleName}"></c:out>
												</html:option>
											</c:forEach>
										</html:select>
									</td>
								</tr>
								<tr>
									<td class="p_url">
										资源URL:
									</td>
									<td class="txt_url">
										<html:text property="value"></html:text>
									</td>
								</tr>
								<tr>
									<td class="p_des">
										描述:
									</td>
									<td class="txt_des">
										<html:text property="desc"></html:text>
									</td>
								</tr>
								<tr>
								<td class="">
									<html:reset styleClass="btnreset" value=" "></html:reset>
								</td>
									<td class="">
										<html:submit styleClass="btnsure" value=" "></html:submit>
									</td>
								</tr>
							</table>
						</html:form>
						<h3>
							管理
						</h3>
						<html:form action="/admin/resource.do?method=batchRemove">
							<table>
							<tr>
								<th class="resTh">选择</th>
								<th class="resTh">资源名</th>
								<th class="resTh">资源类型</th>
								<th class="resTh">保护链接</th>
								<th class="resTh">删除</th>
							</tr>
								<c:if test="${!empty requestScope.resources}">
									<logic:iterate id="resource" name="resources">
										<tr>
											<td class="checktd">
												<html:checkbox property="select" value="${resource.resourceId}"></html:checkbox>
											</td>
											<td class="nametd">
												${resource.resourceName}
											</td>
											<td class="typetd">
												${resource.resourceType }
											</td>
											<td class="valuetd" id="resValue" onclick="edit(this)">
												${resource.resourceValue }
											</td>
											<td class="deletetd">
												<html:link href="resource.do?method=del&id=${resource.resourceId}">delete</html:link>
											</td>
										</tr>
									</logic:iterate>
								</c:if>
							</table>
							
							<div class=""><html:submit value="批量删除" /></div>
							
						</html:form>
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
</html:html>