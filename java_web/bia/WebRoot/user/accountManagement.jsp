<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" >
<title>个人中心</title>
<!-- add your meta tags here -->

	<link href="css/layout.css" rel="stylesheet" type="text/css" >
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/recommend.css">
	<link rel="stylesheet" type="text/css" href="css/accountManagement.css">
	 <link rel="stylesheet" type="text/css" href="../css/hide.css">
      <script type="text/javascript" src="../js/showHide.js"></script>
     
	<script src="js/accountManagement.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/jquery/jquery.min.js"></script>
	<script type="text/javascript">
		
		$(document).ready(function(){      
           	getEmail();
        });
	</script>
<!--[if lte IE 6]>
<script src="js/minmax.js" type="text/javascript"></script>
<![endif]-->

<!--[if lte IE 7]>
<link href="css/patches/patch.css" rel="stylesheet" type="text/css" >
<![endif]-->
</head>
<body>
  <div class="page_margins">
    <div id="border-top">
      <div id="edge-tl"></div>
      <div id="edge-tr"></div>
    </div>
    <div class="page">
     	<jsp:include page="../header.jsp" />
      <div id="main">
        <div id="col1">
          <div id="col1_content" class="clearfix">
            <!-- add your content here -->
            
            <div id="menu"><jsp:include page="menu.jsp" /></div>
          </div>
        </div>
        <div id="col2">
          <div id="col2_content" class="clearfix">
            <!-- add your content here -->
          
            <jsp:include page="sidebar.jsp" />
          </div>
        </div>
        <div id="col3">
          <div id="col3_content" class="clearfix">
            <!-- add your content here -->
          
           <div class="content-title">
									<h3>
										个人中心
									</h3>
		</div>
            	<fieldset> 
			
			<html:form action="/user/accountManagement.do?method=resetPassword" method="post" focus="changePassword" onsubmit="return checkpwd();">
		      <table class="accountTable">
		         <tr>
		          <td class="ptd">旧密码:</td>
		          <td class="txttd"><html:password property="password" styleId="oldPass" styleClass="txtpass" value=""/></td>
		        </tr>
		        <tr>
		          <td class="ptd">新密码:</td>
		          <td class="txttd"><html:password property="newPassword" styleId="newPass" styleClass="txtpass" value=""/></td>
		        </tr>
		        <tr>
		          <td class="ptd">新密码确认:</td>
		          <td class="txttd"><html:password property="newPasswordConfirm" styleId="conNewPass" styleClass="txtpass" value=""/></td>
		        </tr>
		        
		        <tr>
		        <td class="ptd">
		         <html:reset styleId="btn_reset" styleClass="ptd" value=" " />
		        </td>
		          <td class="txttd">
		              <html:submit property="method" styleId="btn_change_pwd" value=" ">
		                <bean:message key="User.ResetPassword"/>
		          </html:submit>
		        </tr>
		      </table>
		      </html:form>
    </fieldset>
     <div class="content-title">
									<h3>
										修改邮箱
									</h3>
		</div>
    <fieldset> 
			
			    <table class="accountTable">
		          <tr>
		          <td  class="ptd">绑定邮箱:</td>
		          <td class="txtemailtd"><p id="pEmail"></p><input id="txtEmail" type="text" name="txtEmail" value=""></td>
		          <td class="edittd"><input id="btnEditEmail" type="button" name="btnEditEmail" value="" onclick="clickEditEmail()" /><input id="btnSureEmail" type="button" name="btnSureEmail" value="" onclick="clickSureEmail()" /></td>
		          <td class="canceltd"><input id="btnCancelEmail" type="button" name="btnCancelEmail" value="" onclick="clickCancelEmail()" /></td>
		        </tr>
		      </table>
    </fieldset>
    						
          </div>
      
       
          <!-- IE Column Clearing -->
          <div id="ie_clearing"> &#160; </div>
        </div>
      </div>
      <!-- begin: #footer -->
      <div id="footer"><jsp:include page="../footer.jsp" /></div>
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
	 
	<c:if test="${!empty requestScope.changepwd}">
		<script type="text/javascript">
			show('hide','密码修改成功');
		</script>
	</c:if>
</body>
</html>
			
