<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>Teachers Details -- Student Registration System</title>

  </head>
  
  <body>
    <jsp:include page="/header.jsp"></jsp:include>

		<div id="main">
			<div id="teacher">
				<div id="teacherDetails">

					<p color="red">Teacher: ${ teacher.firstName } ${ teacher.lastName }</p>
					
					<h3>
						Some Module He(She) Teach:
					</h3>
					<table class="moduleList"">
						<tr><th width="100px">Title</th><th width="100px">Description</th></tr>
							<c:forEach var="module" items="${moduleList}">
							<tr>
								<td><a href="module.do?action=details&id=${ module.moduleID }">${ module.moduleTitle }</a></td>
								<td>${ module.moduleDesc }</td>
							</tr>
							</c:forEach>	
					</table>				
				</div>
			</div>
		</div>
			
		<jsp:include page="/footer.jsp"></jsp:include>
		
  </body>
</html>
