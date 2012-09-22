<%@ page language="java" pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<title>Your Page Title</title>
		<!-- add your meta tags here -->
		<link href="css/layout.css" rel="stylesheet" type="text/css">
		<!--[if lte IE 6]>
		<script src="js/minmax.js" type="text/javascript"></script>
		<![endif]-->

		<!--[if lte IE 7]>
		<link href="css/patches/patch.css" rel="stylesheet" type="text/css" >
		<![endif]-->
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/main.css">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/reg.css"> 
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/header.css">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/biacar.js"> </script>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/hide.css">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/showHide.js"> </script>

		<script type="text/javascript">
			$(window).load(function(){   
 			loadPage();
        }); 
			$(window).unload(function (){
				unloadPage();
			}
			);
		</script>
	</head>

	<body>
		<div id="header">
			<div id="topnav">

				<!-- end: skip link navigation -->
				<a href="${pageContext.request.contextPath}">&nbsp首页&nbsp&nbsp</a>
				|
				<security:authorize ifNotGranted="ROLE_USER,ROLE_ADMIN">
					<a href="login.do?method=log">&nbsp&nbsp登录&nbsp&nbsp</a> |
				<a href="register.do?method=reg">&nbsp&nbsp注册&nbsp&nbsp</a> |
				</security:authorize>
				<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
					&nbsp&nbsp 欢迎: <security:authentication
						property="principal.username"></security:authentication>&nbsp&nbsp
					<a
						href="${pageContext.request.contextPath}/j_spring_security_logout">&nbsp退出&nbsp&nbsp</a> |
				</security:authorize>
				<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
					<a
						href="${pageContext.request.contextPath}/user/accountManagement.do?method=getAccount">&nbsp&nbsp我的比啊&nbsp&nbsp</a> |
		        </security:authorize>
				<security:authorize ifAllGranted="ROLE_ADMIN">
					<a
						href="${pageContext.request.contextPath}/admin/usermgr.do?method=getAll">&nbsp&nbsp后台管理&nbsp&nbsp</a> |
		        </security:authorize>

				<a href="#" onclick="showAllProduct()">&nbsp&nbsp对比栏&nbsp&nbsp</a>

			</div>
			<div id="logo">
				<html:form styleId="searchform" action="/search.do?method=search"
					method="post" focus="search">
					<table id ="searchBar" border="0">
						<tr>
							<td>
								<img src="${pageContext.request.contextPath}/images/logo.png" />
							</td>	
							<td>
								<html:text property="keyword" styleId="box_search" />
							</td>
							<td>
								<html:submit property="method" styleId="btnsearch" value=" ">
								</html:submit>
								<br>
							</td>							
							<td class="timer">
								<!--Content Start-->
								<!-- <object
									codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0"
									height="100" width="200"
									classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000">
									<param
										value="http://d.lanrentuku.com/down/lanren/flashtime/flashtime-0145.swf"
										name="movie" />
									<param value="high" name="quality" />
									<param value="transparent" name="wmode" />
								</object>
								 -->
								 <EMBED src="http://d.lanrentuku.com/down/lanren/flashtime/flashtime-0145.swf" width="200" height="100" type=application/x-shockwave-flash wmode="transparent" />
								<!--Content End-->
							
							</td>
						</tr>
					</table>
				</html:form>
			</div>
		</div>

		<div>
			<security:authorize ifNotGranted="ROLE_USER,ROLE_ADMIN">
				<input type="hidden" id="userName1" value="匿名游客" />
				<input type="hidden" id="userId" value="2" />
			</security:authorize>
			<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
				<input type="hidden" id="userName1"
					value="<security:authentication property="principal.username" />" />
				<input type="hidden" id="userId"
					value="<security:authentication property="principal.userId" />" />
			</security:authorize>
		</div>

	</body>
</html>