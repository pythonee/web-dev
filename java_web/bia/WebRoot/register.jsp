<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<title>Your Page Title</title>
		<!-- add your meta tags here -->

		<link href="css/layout.css" rel="stylesheet" type="text/css" />
		<!--[if lte IE 7]>
<link href="css/patches/patch.css" rel="stylesheet" type="text/css" />
<![endif]-->
		<script type="text/javascript" src="js/regin.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.min.js"></script>
		<script type="text/javascript">
 			//var alpha=new Array("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
  	 		$(document).ready(function(){   
             getMathCaptcha();
      		  }); 
  		//getJHCaptcha();
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
								<h3>用户注册</h3>
							</div>
								<div class="register-form">
								<html:form action="/user.do?method=register" method="post"
									focus="register" onsubmit="return tijiao();">
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
												密码:
											</td>
											<td class="user-form-input">
												<html:password property="password" styleId="u_pass"
													maxlength="12" onblur="password_check()"
													onkeyup="showStrongPic()" />
											</td>
											<td valign="top">
												<span class="star" id="pass_re">*</span>
											</td>
											<td>
												<span id="lowPic" style="display: "><img
														src="images/password/gread_l_x.gif" /> </span>
												<span id="midPic" style="display: none"><img
														src="images/password/gread_m_x.gif" /> </span>
												<span id="highPic" style="display: none"><img
														src="images/password/gread_h_x.gif" /> </span>
											</td>
											<td id="pass_re_m">
												<br>
											</td>
										</tr>
										<tr>
											<td class="user-form-left">
												密码确认:
											</td>
											<td class="user-form-input">
												<html:password property="passwordConfirm"
													styleId="u_pass_re" maxlength="12" onblur="pass_re()" />
											</td>
											<td valign="top">
												<span class="star" id="pass_re_re">*</span>
											</td>
											<td id="pass_re_re_m">
												请您再输入一次密码
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
												格式:123567@qq.com
											</td>
										</tr>
										<tr>
											<td class="user-form-left">
												<div id="imgMathCaptcha"></div>
											</td>
											<td class="user-form-input">
												<input type="text" id="txtMathCaptcha"
													styleId="txtMathCaptcha" onblur="checkMathCaptcha()" />
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
											<td class="user-form-left">
										
										</td>
										<td>	<input type="reset" value=" " class="btnreset"/>
											<html:submit property="method" styleClass="btnreg" value=" ">
												<bean:message key="User.Register" />
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
	</body>
</html>


