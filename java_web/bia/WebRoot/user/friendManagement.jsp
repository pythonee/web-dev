<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<title>用户中心-好友管理</title>
		<!-- add your meta tags here -->

		<link href="css/layout.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="css/main.css">
		<link rel="stylesheet" href="css/pagination.css" />
		<link rel="stylesheet" type="text/css" href="css/friend.css">
		<link rel="stylesheet" type="text/css" href="../css/hide.css">
		<script type="text/javascript" src="../js/showHide.js"></script>

		<script type="text/javascript" src="js/jquery/jquery.min.js"></script>
		<script type="text/javascript"
			src="js/jquery_pagination/jquery.pagination.js"> </script>
		<script type="text/javascript" src="js/friendManagement.js"> </script>
		<script type="text/javascript" src="js/json2.js">
     </script>
		<script type="text/javascript">
		 $(document).ready(function(){      
               
           		getFriendLength();
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
									好友管理
								</h3>
							</div>
							<div id="friendList">
								<table class="friendTable">
									<thead id="friendTableHead">
										<tr>
											<th class="nameth">
												好友昵称
											</th>
											<th class="emailth">
												邮箱
											</th>
											<th class="deleteth">
												删除好友
											</th>
										</tr>
									</thead>
									<tbody id="friendTablebody">

									</tbody>
								</table>
							</div>
							<div id="Pagination" class="pagination">
							</div>
							<p class="filling"></p>
							<div class="content-title">
								<jsp:include page="/recommend.do?method=recommendFriend"></jsp:include>
								<h3 class="entry-title">
									比啊帮您寻找好友
								</h3>
							</div>
							<div id="recommendFriend">
								<table>
								<tr>
									<th class="recommendFriTh">用户名</th>
									<th class="recommendFriTh">邮箱</th>
									<th class="recommendFriTh">用户操作</th>
								</tr>
									<c:forEach var="friend" items="${ recommendFriendList }">
										<tr>
											<td class="recommendFriTd">${ friend.username }</td>
											<td class="recommendFriTd">${ friend.email  }</td>
											<td class="recommendFriTd">
												<a onclick="addFriend(${friend.userId})">
													加为好友
												</a>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
						<!-- IE Column Clearing -->
						<div id="ie_clearing">
							&#160;
						</div>
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
			style="display: none; filter: alpha(opacity =     100); opacity: 1;">
			<div class="infoWIN" id="infoWIN">
				<br />
			</div>
		</div>
	</body>
</html>
