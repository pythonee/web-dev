<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>Student Sign Up -- Student register system</title>

	</head>

	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="student.do?action=getAllStudent"></jsp:include>
		<div id="main">
				
			<div id="register_form" class="form_Input">
			
				<c:if test="${requestScope.signupError == true}">
					<div id="error">
						<p><font color="red">${ requestScope.message }</font></p>
					</div>
				</c:if>	
				
				<c:if test="${allStudentSize > 0}">
					<div id="registerTips">
						<h5>System have extisted some users. You may try another:
						<ul>
							<c:forEach var="student" items="${students}">
								<li>[${ student.username }]</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
								
				<form action="student.do?action=signup" method="post">
					<table id="registerForm_Input" class="form_Input">
						<tr>
							<td id="registerForm_Left" class="form_left">
								username:
							</td>
							<td>
								<input type="text" name="username">
							</td>
							<td>
								example:test
							</td>
						</tr>
						<tr>
							<td id="registerForm_Left" class="form_left">
								password:
							</td>
							<td>
								<input type="password" name="password">
							</td>
						</tr>

						<tr>
							<td id="registerForm_Left" class="form_left">
								email:
							</td>
							<td>
								<input type="text" name="email">
							</td>
							<td>example:pythonee@gmail.com</td>
						</tr>
						<tr>
							<td id="registerForm_Left" class="form_left">
								first name:
							</td>
							<td>
								<input type="text" name="firstName" value="Fei">
							</td>
							<td>
								example: Fei
							</td>
						</tr>
						<tr>
							<td id="registerForm_Left" class="form_left">
								last name:
							</td>
							<td>
								<input type="text" name="lastName" value="Wang">
							</td>
							<td>
								example:Wang
							</td>
						</tr>
						<tr>
							<td id="registerForm_Left" class="form_left">
								Country:
							</td>
							<td>
								<select name="country">
									<option value="China">China</option>
									<option value="Ireland">Ireland</option>
								</select>
							</td>
							<td>
								Just for example. I don't list all available countries.
							</td>
						</tr>
						
						<tr>
							<td id="registerForm_Left" class="form_left">
								Birthday:
							</td>
							<td><input type="text" name="birthday" width="10px" value="2010-01-01"> </td><td>example:1987-05-21</td>
						</tr>
						
						<tr>
							<td id="registerForm_Left" class="form_left">
								Gender:
							</td>
							<td>
								<select name="sex">
									<option value="M">
										Male
									</option>
									<option value="W">
										Female
									</option>
								</select>
							</td>
						</tr>
					</table>

					<div id="registe_btnLine" class="btnLine">
						<input type="reset" value="reset" id="registe_reset"
							class="submit">
						<input type="submit" value="signup" id="registe_submit"
							class="submit">
						<div class="clear"></div>
					</div>
				</form>
			</div>
		</div>

		<jsp:include page="/footer.jsp"></jsp:include>

	</body>
</html>
