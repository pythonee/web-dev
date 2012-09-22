<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Module Details -- Student Registration System</title>
  </head>

	<body>
	<jsp:include page="/header.jsp"></jsp:include>
	
		<div id="main">
			<div id="module">
				<h3>Module Details</h3>
				
				<ul>
					<li> <h4>Module Title : ${ module.moduleTitle }</h4></li>
					<li> <h4>Teacher: </h4><p><a href="teacher.do?action=details&id=${teacher.id}">${ teacher.firstName }-${ teacher.lastName }</a></p></li>
					<li> <h4>Start Date:</h4><p>${ module.moduleStart } </p></li>	
					<li> <h4>Last Weeks:</h4><p>${ module.moduleLast } weeks </p></li>
					<li> <h4>Description</h4><p> ${ module.moduleDesc } </p></li>
				</ul>
				
				<h4>It was teached in below programmes</h4>
				<ul>
					<c:forEach var="programme" items="${programmeList}" >
						<li><a href="programme.do?action=details&id=${programme.programmeID}">${ programme.programmeTitle }</li>
					</c:forEach>
				</ul>
			
			</div>
		</div>
			
		<jsp:include page="/footer.jsp"></jsp:include>	
	</body>
</html>
