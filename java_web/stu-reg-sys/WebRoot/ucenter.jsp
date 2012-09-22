<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>Student User Center -- Student Registration System</title>

  </head>
  
  <body>
  
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="/student.do?action=profile"></jsp:include>
		<jsp:include page="/programme.do?action=getAllProgramme"></jsp:include>
		<jsp:include page="/programme.do?action=getMyProgramme"></jsp:include>			
		<jsp:include page="/module.do?action=getMyAllModule"></jsp:include>	
		
		<div id="main">
			<div id="ucenter">
			
			<c:if test="${requestScope.select_module_error == true}">
				<div id="error">
					<h2><font color="red">${ requestScope.message }</font></h2>
				</div>
			</c:if>	
			
			<c:if test="${requestScope.pay_programme_error == true}">
				<div id="error">
					<h2><font color="red">${ requestScope.message }</font></h2>
				</div>
			</c:if>			
			
			<c:choose>
			<c:when test="${ sessionScope.uid != null }">
				<div>
					<h3>
						All programme
					</h3>
					<form action="programme.do?action=pay_programme" method="post">		
					<table class="programmeList">
						<tr><th class="uc_title" width="100px">Title</th><th class="uc_desc" width="100px">Description</th><th class="uc_cost" width="100px">Cost</th><th>Choose</th></tr>
						<c:forEach var="programme" items="${programmeList}">
							<tr>
								<td><a href="programme.do?action=details&id=${ programme.programmeID }">${ programme.programmeTitle }</a></td>
								<td>${ programme.programmeDESC }</td>
								<td>${ programme.cost }</td>
								<td><input type="radio" name="select" value="${ programme.programmeID }"></td>
							</tr>	
						</c:forEach>
					</table>
					
					<table>
					<c:choose>
						<c:when test="${ isPaid==false}">
							<tr><td><input type="submit" value="Pay for it"></td></tr>
						</c:when>
						<c:when test="${ isPaid==true}">
							<tr><td><input type="submit" value="change programme"></td></tr>
						</c:when>
					</c:choose>
					</table>
					</form>
					
					<c:if test="${ isPaid == true }">
						<form action="module.do?action=select_module" method="post">
							<h3>The programme you pay is <font color="red"> ${ programme.programmeTitle }</font></h3>
							<h3>And the <font color="red"> ${ programme.programmeTitle }</font> programme has <font color="red">${ allAvailableModulesSize }</font> optional modules.
								You have choose <font color="red">${ myModuleSize } </font>module. And there are  <font color="red">${ allAvailableModulesSize - myModuleSize}</font> left to you select. 
							</h3>
							<h3>They are:</h3>
							<ul>
								<c:forEach var="map" items="${allAvailableModulesMap}">
									<li><input type="checkbox" name="select_module" value="${ map.key.moduleID }" <c:choose><c:when test="${ map.value }">enabled</c:when><c:otherwise>disabled</c:otherwise></c:choose>>${ map.key.moduleTitle }</li>
								</c:forEach>
							</ul>
							
							<c:if test="${ allAvailableModulesSize - myModuleSize > 0 }">
							<table>
								<tr><td><input type="submit" value="Select Them"></td></tr>
							</table>
						</c:if>
						</form>
					</c:if>
					
					<c:if test="${ myModuleSize > 0}">
						<h3>Here are modules you have choosed</h3>
						<c:forEach var="module" items="${myModules}">
							<ul>
								<li><a href="module.do?action=details&id=${module.moduleID}">${ module.moduleTitle }</a></li>
							</ul>						
						</c:forEach>
					</c:if>
					
					<div id="profile">
					<h3>Your profile</h3>
					<table>	
						<tr><td class="profile_left">username:</td><td>${ student.username }</td></tr>
						<tr><td class="profile_left">first name:</td><td>${ student.firstName }</td></tr>
						<tr><td class="profile_left">last name:</td><td>${ student.lastName }</td></tr>
						<tr><td class="profile_left">email:</td><td>${ student.email }</td></tr>
						<tr><td class="profile_left">gender:</td><td>${ sex }</td></tr>
						<tr><td class="profile_left">birthday:</td><td>${ student.birthday }</td></tr>
					</table>
					</div>				
				</div>		
				</c:when>
				
				<c:otherwise>
					<div>
						<p>you must login first...</p>
					</div>
				</c:otherwise>
				</c:choose>
			</div>
		</div>

		<jsp:include page="/footer.jsp"></jsp:include>

  </body>
</html>
