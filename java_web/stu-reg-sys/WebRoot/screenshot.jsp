<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Screen shot -- There is some screen shots in this system</title>
  </head>
  
  <body>
    <jsp:include page="/header.jsp"></jsp:include>
	<div id="main">
		<div id="screenshot">
		<ul>
			<li><h2><font color="darkgreen">When user login but the username doesn't exist</font></h2>
			<img src="screenshot/usernotexist.png" />
			</li>
			<li><h2><font color="darkgreen">When user login but the password is uncorrect</font></h2>
			<img src="screenshot/passworderr.png" />
			</li>
			<li><h2><font color="darkgreen">after user login success </font> </h2></li>
			<img src="screenshot/loginsuccess.png" />
			<li><h2><font color="darkgreen">after user pay for a programme</font></h2>
			<img src="screenshot/AfterPay.png" border="1px solid #CCC"/>
			<li><h2><font color="darkgreen">after user select module</font></h2>
			<img src="screenshot/AfterSelectModule.png" border="1px solid #CCC"/>
			<li><h2><font color="darkgreen">unit test</font></h2>
			<img src="screenshot/unitTest.png" width="980px"/>
		</ul>
		</div>
	</div>
  	<jsp:include page="/footer.jsp"></jsp:include>
  </body>
</html>
