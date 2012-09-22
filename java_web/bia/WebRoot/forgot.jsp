<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<script type="text/javascript" src="js/forgot.js"></script>
	<script type="text/javascript">
 			//var alpha=new Array("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
  	 		window.onload = function(){
			//getAlphaCaptcha();
			getMathCaptcha();
		}
	</script>
	<title>找回密码</title>
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
								<h3>找回密码</h3>
							</div>
							<div class="login-form">
							<html:form action="/user.do?method=forgotPasswd" method="post"
								focus="forgot" onsubmit="return tijiao();">
								<table border="0">
									<tr>
										<td class="user-form-left">
											用户名:
										</td>
										<td class="user-form-input">
											<html:text property="username" styleId="username"
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
											邮箱:
										</td>
										<td class="user-form-input">
											<html:text property="email" styleId="u_mail"
												onblur="isEmail()" />
										</td>
										<td valign="top">
											<span class="star" id="mail_re">*</span>
										</td>
										<td id="mail_re_m">
											<br>
										</td>
									</tr>
									<tr>
										<td class="user-form-left">
											<div id="imgMathCaptcha"></div>
										</td>
										<td class="user-form-input">
											<html:text property="txtMathCaptcha" styleId="txtMathCaptcha"
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
									<tr>
										<td class="user-form-left">
										
										</td>
										<td>	<html:reset value=" "  styleClass="btnreset"/>
											<html:submit property="method" styleClass="btnforgot" value=" ">
												<bean:message key="User.ForgotPasswd" />
											</html:submit>
										</td>
										<td></td>
										<td></td>
									</tr>
								</table>
							</html:form>
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
		<div id="hide"
		style="display: none; filter: alpha(opacity = 100); opacity: 1;">
		<div class="infoWIN" id="infoWIN">
			<br />
		</div>
	</div>
	</body>
</html>


