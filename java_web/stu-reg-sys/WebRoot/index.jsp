<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>EE557: Server-Side Development -- Student Registration System</title>
	</head>

	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="/programme.do?action=getAllProgramme"></jsp:include>
		<jsp:include page="/module.do?action=getAllModule"></jsp:include>
		<jsp:include page="/teacher.do?action=getAllTeacher"></jsp:include>
		
		<div id="main">
		<c:choose>
		<c:when test="${InitError}">
			<div id="InitError">
				<p>You haven't initial the system. You can click <a href="init.do">this link</a> to initial the system(mostly database).</p>
			</div>
		</c:when>
		<c:otherwise>	
			<div id="left">
				<c:if test="${InitSuccess}"><p><b><font color="darkgreen">init success. Now you can go to admin page to add some module and register a account and then select programme. Or you can login by the default user( username:pythonee and password: ondefour) to test this system.</font></b></p></c:if>
				<h4>Test user</h4>
				<p>username:<font color="red">pythonee</font></p>
				<p>password:<font color="red">onedefour</font></p>
				
				
				<h4>There are <c:out value="${allProgrmmeNum}"></c:out> programmes in our site</h4>
		
				<ul>
					<c:forEach var="programme" items="${programmeList}">
						<li><a href="programme.do?action=details&id=${ programme.programmeID }">${ programme.programmeTitle }</a></li>
					</c:forEach>
				</ul>
				
				<h4>There are <c:out value="${allModuleNum}"></c:out> modules in our site</h4>
				<ul>
					<c:forEach var="module" items="${moduleList}">
						<li><a href="module.do?action=details&id=${ module.moduleID }">${ module.moduleTitle }</a></li>
					</c:forEach>
				</ul>
				
				<h4>There are <c:out value="${allTeacherNum}"></c:out> teachers in our site</h4>
				<ul>
					<c:forEach var="teacher" items="${teacherList}">
						<li><a href="teacher.do?action=details&id=${ teacher.id }">${ teacher.firstName } - ${ teacher.lastName }</a></li>
					</c:forEach>
				</ul>
		
			</div>
			<div id="right">
			<c:choose>
			<c:when test="${ sessionScope.uid  == null }">
				<div id="login_Title" class="sidebar_Title">
					<h2>
						you can login in here..
					</h2>
				</div>
				<div id="login">
					<c:if test="${requestScope.loginError == true}">
						<div id="error">
							<p><font color="red">${ requestScope.message }</font></p>
						</div>
					</c:if>
					<form action="student.do?action=login" method="post">
						<table id="loginForm_Input" class="form_Input">
							<tr>
								<td id="loginForm_Left" class="form_left">
									username:
								</td>
								<td>
									<input type="text" name="username">
								</td>
							</tr>
							<tr>
								<td id="loginForm_Left" class="form_left">
									password:
								</td>
								<td>
									<input type="password" name="password">
								</td>
							</tr>
						</table>
						<div id="login_btnLine" class="btnLine">
							<input type="submit" value="login" id="login_submit"
								class="submit">
							<input type="reset" value="reset" id="login_reset" class="submit">
							<div class="clear"></div>
						</div>
					</form>
				</div>

				<div id="registe">
					<p>you don't have account?</p>
					<div class="signup_Btn"><a href="signup.jsp">Create account</a></div>
				</div>
				
				</c:when>
				<c:otherwise>
					<div id="registe">
						<p>login success</p>
						<p><a href="student.do?action=logout">log out</a></p>
						<p><a href="ucenter.jsp">enter personal user center<a><p>
					</div>
				</c:otherwise>
				</c:choose>
			</div>
			<div class="clear"></div>
			</c:otherwise>
			</c:choose>
		</div>

		<jsp:include page="/footer.jsp"></jsp:include>
	</body>
</html>
