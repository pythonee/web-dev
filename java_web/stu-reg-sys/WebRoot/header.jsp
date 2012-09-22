<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<html>
	<head>
		<link rel=StyleSheet href="css/layout.css" type="text/css">
		<link rel=StyleSheet href="css/style.css" type="text/css">
	</head>

	<body>
		<div id="header" class="center">
			<div id="logo">
				<img src="image/ee_logo.gif">
			</div>
			<div id="title">
				<h2>
					EE557: Server-Side Development -- Student Registration System
				</h2>
			</div>
			<div class="clear"></div>
			<div id="nav">
				<ul>
					<li>
						<a href="index.jsp"> Index </a>
					</li>
					<c:if test="${ sessionScope.uid  != null }">
					<li>
						<a href="ucenter.jsp">User Home</a>
					</li>
					</c:if>
					<li>
						<a href="signup.jsp"> Signup</a>
					</li>
					<li>
						<a href="require.jsp"> Require  </a>
					</li>
					<li>
						<a href="sql.jsp">SQL</a>
					</li>
					
					<li>
						<a href="report.jsp"> Report </a>
					</li>
					<li>
						<a href="screenshot.jsp"> Screen Shot </a>
					</li>
					<li>
						<a href="admin.jsp"> Admin </a>
					</li>
				</ul>
			</div>
		</div>
	</body>
</html>
