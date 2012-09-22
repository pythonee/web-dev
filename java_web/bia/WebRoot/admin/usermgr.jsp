<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" >
<title>用户管理</title>
<!-- add your meta tags here -->

<link href="css/layout.css" rel="stylesheet" type="text/css" >
<!--[if lte IE 6]>
<script src="js/minmax.js" type="text/javascript"></script>
<![endif]-->

<!--[if lte IE 7]>
<link href="css/patches/patch.css" rel="stylesheet" type="text/css" >
<![endif]-->

	<link rel="stylesheet" type="text/css" href="css/main.css">
		<link rel="stylesheet" href="css/pagination.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/usermgr.css" />
		<script type="text/javascript" src="../js/jquery/jquery.min.js"></script>
		<script type="text/javascript"
			src="../js/jquery_pagination/jquery.pagination.js"> </script>
		<script type="text/javascript" src="../js/json2.js"></script>
		<script type="text/javascript" src="js/usermgr.js"></script>
		<script>
    $(document).ready(function(){   
             usinitPagination("<bean:write name="length" />",0);
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
      <jsp:include page="../header.jsp" />
      <div id="main">
        <div id="col1">
          <div id="col1_content" class="clearfix">
            <!-- add your content here -->
           
            <div id="menu"><jsp:include page="menu.jsp" /></div>
          </div>
        </div>
        <div id="col3">
          <div id="col3_content" class="clearfix">
            <!-- add your content here -->
          
            <div id="wordList">
            <div class="content-title">
									<h3>
										用户管理
									</h3>
					</div>
						<fieldset id="userfs">
							
							<form action="usermgr.do?method=deleteUser" method="post"
								onsubmit="return deleteSure();">
								<table id="userTable" border="0">
								
									<thead>
										<tr>
											
											<th class="nameth">
												用户名
											</th>
											<th class="emailth">
												邮箱
											</th>
											<th class="deleteth">
												<input type="submit" value=" " id="btn_batch_delete" />
											</th>
										</tr>
									</thead>
									<tbody id="usertbody">
										<c:if test="${!empty requestScope.userList}">
											<logic:iterate id="user" name="userList">
												<tr>
													
													<td class="nametd">
														<p id="pUser${user.userId}">
															${user.username}
														</p>
													</td>
													<td class="emailtd">
														<p id="pUser${user.userId}">
															${user.email}
														</p>
													</td>
													<td class="deletetd">
														<input type="checkbox" name="boxDelete"
															value="${user.userId}" />
													</td>
												</tr>
											</logic:iterate>
										</c:if>
									</tbody>
								</table>
							</form>
							<div id="Pagination" class="pagination">
								分页栏
							</div>
							<br/>
						</fieldset>
					</div>
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
	<c:if test="${!empty requestScope.deleteUser}">
		<script type="text/javascript">
			show('hide','用户删除成功!');
		</script>
	</c:if>
</body>
</html>
																																																																																																																																																						

