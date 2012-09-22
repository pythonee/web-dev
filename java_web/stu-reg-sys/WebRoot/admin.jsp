<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Administrator -- you can add test data here</title>
    
  </head>
  
  <body>
    <jsp:include page="/header.jsp"></jsp:include>
    <jsp:include page="teacher.do?action=getAllTeacher"></jsp:include>
	<jsp:include page="programme.do?action=getAllProgramme"></jsp:include>
		<div id="main">
			<div id="admin">
				<div id="message">
					<c:if test="${ requestScope.messasge != null}">
						<p><font color="red">${ messasge }</font></p>
					</c:if>
				</div>
				
				<h3>In the initial database. It contain :	</h3>
					<ul>
						<li>two prgramme -- whu and dcu, pku and dcu collaborative program</li>
						<li>two teacher  -- Hao Gui and Li Li</li>
						<li>one user     -- username:<font color="red">pythonee</font>  password:<font color="red">onedefour</font></li>
						<li>two module   -- EE557 And EE548</li>
					</ul>
				
				<h2>Now you can add some data to test this system, but you may follow the special order.</h2>
				<h3>Step one : add teacher</h3>
				<form action="teacher.do?action=add" method="post">
					<table>
						<tr><td>First Name:</td><td><input type="text" name="teacher_firstName" /></td><td>example:fei</td></tr>
						<tr><td>Last Name:</td><td><input type="text" name="teacher_lastName" /></td><td>example:wang</td></tr>
						<tr><td><input type="submit" value="add" /></td></tr>
					</table>
				</form>
				
				<h3>Step two : add programme</h3>
				<form action="programme.do?action=add" method="post">
					<table>
						<tr><td>Programme Title:</td><td><input type="text" name="programme_title" /></td><td>example:whu and dcu collaboration</td></tr>
						<tr><td>Programme Desc:</td><td><input type="text" name="programme_desc" /></td><td>example: this is description</td></tr>
						<tr><td>Programme Cost:</td><td><input type="text" name="programme_cost" /></td><td>example: 80000</td></tr>
						<tr><td><input type="submit" value="add" /></td></tr>
					</table>
				</form>
				
				<h3>Step three : add module</h3>
				<c:choose>
				<c:when test="${ progrmmeNum==0 }">
					<div id="error">
						<p><font color="red">You should add a new programme first. To add a module require at least one programme</font></p>
					</div>
				</c:when>
				
				<c:when test="${ teacherNum==0 }">
				<div id="error">
						<p><font color="red">You should add a new teacher first. To add a module require at least one teacher</font></p>
					</div>
				</c:when>

				<c:when test="${ progrmmeNum==0 }">
					<div id="error">
						<p><font color="red">You should add a new programme first. To add a module require at least one programme</font></p>
					</div>
				</c:when>
				
				<c:when test="${ teacherNum==0 }">
				<div id="error">
						<p><font color="red">You should add a new teacher first. To add a module require at least one teacher</font></p>
					</div>
				</c:when>
				<c:otherwise>
				<form action="module.do?action=add" method="post">
					<table>
						<tr><td>Module Title:</td><td><input type="text" name="module_title" /></td><td>example:EE569</td></tr>
						<tr><td>Module Desc:</td><td><input type="text" name="module_desc" /></td><td>example: this is decription</td></tr>
						<tr><td>Module Teacher:</td>
							<td>
								<select name="module_teacher">
									<c:if test="${teacherNum == 0}">
										<option value="null">null</option>
									</c:if>
									<c:forEach var="teacher" items="${teacherList}">
										<option value="${ teacher.id }" />${ teacher.firstName }-${ teacher.lastName }</option>
									</c:forEach>
								</select>	
							</td>
						</tr>
						<tr><td>Module Start Date:</td><td><input type="text" name="module_start" value="2010-01-01"/></td><td>example:2010-09-01</td></tr>
						<tr><td>Module Last:</td><td><input type="text" name="module_last" /></td><td>weeks</td><td>example:18</td></tr>
						<tr><td>Module Belong to:</td>
							<td>
								<select name="module_programme">
									<c:if test="${progrmmeNum == 0}">
										<option value="null">null</option>
									</c:if>
									<c:forEach var="programme" items="${programmeList}">
										<option value="${ programme.programmeID }">${ programme.programmeTitle }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr><td><input type="submit" value="add" /></td></tr>
					</table>
				</form>
				</c:otherwise>
				</c:choose>
			</div>
		</div>
	<jsp:include page="/footer.jsp"></jsp:include>
  </body>
</html>
