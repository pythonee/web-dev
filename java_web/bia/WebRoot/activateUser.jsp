<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		 <script>
			var time=0; 
    		function gotoUserCenter(){ 
    			time++; 
				if(time==3){ 
				//document.location.href="${pageContext.request.contextPath}/j_spring_security_check";
				document.activateForm.submit();
			} 
			document.getElementById("go_title").innerHTML="<font color=red>"+(3-time)+ "</font>秒后自动跳转到用户中心"; 
			} 
			window.setInterval( "gotoUserCenter() ",1000); 
		</script>
		
		
		<title>用户激活</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="refresh" content="300; url=user/userCenter.jsp">

		<link href="css/layout.css" rel="stylesheet" type="text/css" />
		<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
		-->
	</head>
	<body>
		<div class="page_margins">
			<div id="border-top">
				<div id="edge-tl"></div>
				<div id="edge-tr"></div>
			</div>
			<div class="page">
				<jsp:include page="header.jsp" />
				<div id="teaser">
					
				</div>
				<div id="main">
					<div id="col1">
						<div id="col1_content" class="clearfix">
							<!-- add your content here -->
							
							<jsp:include page="sidebar.jsp" />
						</div>
					</div>
					<div id="col3">
						<div id="col3_content" class="clearfix">
							<!-- add your content here -->

		<form name="activateForm" 
			action="${pageContext.request.contextPath}/j_spring_security_check"
			style="width: 560px; text-align: center;display:none;">
			<fieldset>
				<legend>
					登陆
				</legend>
				<table border="0">
					<tr>
						<td width=25%>
							用户名:
						</td>
						<td>
							<input type="text" name="j_username" style="width: 150px;"
								value="${username}"
								id="username" />
						</td>
						<td valign="top" width=5%>
							<span class="star" id="name_re">*</span>
						</td>
						<td width=40% id="name_re_m">
							<br>
						</td>
					</tr>
					<tr>
						<td>
							密码:
						</td>
						<td>
							<input type="text" name="j_password" style="width: 150px;"
								id="u_pass" value="${password}" />
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="checkbox" name="_spring_security_remember_me" />
							两周之内不必登陆
						</td>
					</tr>
					<tr>
						<td>
							<input type="reset" value="重置" />
						</td>
						<td>
							<input type="submit" value="登陆" />
							<a href="forgot.jsp">忘记密码</a>

						</td>

					</tr>
				</table>
			</fieldset>
		</form>



		<table>
			<tr>
				<td id="go_title">
					3秒后自动跳转到用户中心
				</td>
			</tr>
		</table>
							
							
						</div>
						<!-- IE Column Clearing -->
						<div id="ie_clearing">
						</div>
					</div>
				</div>
				<!-- begin: #footer -->
				<div id="footer"><jsp:include page="footer.jsp" />
				</div>
			</div>
			<div id="border-bottom">
				<div id="edge-bl"></div>
				<div id="edge-br"></div>
			</div>
		</div>
		<div id="hide"
		style="display: none; filter: alpha(opacity = 100); opacity: 1;">
		<div class="infoWIN" id="infoWIN">
			<br />
		</div>
	</div>
	</body>
</html>


