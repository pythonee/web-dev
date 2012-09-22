<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>Programme Details -- Student Registration System</title>

	</head>

	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		
		<div id="main">
			<div id="ucenter">

				<div id="programmeDetails">
					<h1>
						Programme Details
					</h1>
					
					<table class="programmeList"">
						<tr><th class="uc_title" width="100px">Title</th><th class="uc_desc" width="100px">Description</th><th class="uc_cost" width="100px">Cost</th></tr>
							<tr>
								<td><a href="programme.do?action=details&&id=${ programme.programmeID }">${ programme.programmeTitle }</a></td>
								<td>${ programme.programmeDESC }</td>
								<td>${ programme.cost }</td>
							</tr>	
					</table>
					
					<h2>
						Available Modules of Programme
					</h2>
					
					<ul>
						<c:forEach var="map" items="${moduleOfProgramme}">
							<li>
								<h3><a href="module.do?action=details&id=${ map.key.moduleID }">${ map.key.moduleTitle }</a></h3>
								<h4>module description</h4>
								<p>${ map.key.moduleDesc }</p>
								<h4>module teacher</h4>
								<a href="teacher.do?action=details&id=${ map.value.id}">${ map.value.firstName } ${ map.value.lastName }</a>
								<h4>module start</h4>
								<p>${ map.key.moduleStart }</p>
							</li>
						</c:forEach>
					</ul>
					
				</div>
			</div>
		</div>
			
		<jsp:include page="/footer.jsp"></jsp:include>
	</body>
</html>
