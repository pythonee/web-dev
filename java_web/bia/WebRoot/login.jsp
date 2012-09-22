<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>用户登录</title>
		<script type="text/javascript" src="js/login.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.min.js"></script>
		<script type="text/javascript">
 			//var alpha=new Array("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
  	 		
			$(document).ready(function(){   
             getMathCaptcha();
      		  }); 
 </script>
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

							<div class="content-title">
								<h3>用户登陆</h3>
							</div>
							<c:if test="${param.error==true}">
								<div class="error">
									${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
								</div>
							</c:if>

							<div class="login-form">
								<form
									action="${pageContext.request.contextPath}/j_spring_security_check">
									<table border="0">
										<tr>
											<td class="user-form-left">
												用户名:
											</td>
											<td class="user-form-input">
												<input type="text" 
													   name="j_username" 
													   value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}"
													   id="username" 
													   onblur="isName()" />
											</td>
											<td valign="top" width=5%>
												<span class="star" id="name_re">*</span>
											</td>
											<td width=40% id="name_re_m">
												<br>
											</td>
										</tr>
										<tr>
											<td class="user-form-left">
												密码:
											</td>
											<td class="user-form-input">
												<input type="password" name="j_password"
													   id="u_pass" onblur="password_check()" />
											</td>
											<td valign="top">
												<span class="star" id="pass_re">*</span>
											</td>
											<td width=50% id="pass_re_m">
												<br>
											</td>
										</tr>
										<tr>
											<td class="user-form-left">
												<div id="imgMathCaptcha"></div>
											</td>
											<td class="user-form-input">
												<input type="text" 
													   id="txtMathCaptcha"
													   styleId="txtMathCaptcha" 
													   onblur="checkMathCaptcha()" />
											</td>
											<td valign="top">
												<span class="star" id="CheckCode_re">*</span>
											</td>
											<td>
												<div id="next">
													<a href="#" onclick="getMathCaptcha();">换一张</a>
												</div>
											</td>
										</tr>
										<tr>
											<td><br></td>
											<td>
												<input type="checkbox" name="_spring_security_remember_me" />
												两周之内不必登陆
											</td>
										</tr>
										<tr>
											<td class="user-form-left">
											
											</td>
											<td class="btnlogintd"><input type="reset" value=" "  class="btnreset"/>
												<input type="submit" value=" " class="btnlog" />
											<a href="forgot.do?method=forgot">忘记密码</a>
											</td>
											
										</tr>
									</table>
									
								</form>
							</div>
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
	</body>
</html>


