
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
       
   	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/user/css/menu.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/user/js/jquery/jquery.bgpos.js"></script>
  <script type="text/javascript">
 		
        $(function(){
 			$('#menuul a')
		.css( {backgroundPosition: "-20px 35px"} )
		.mouseover(function(){
			$(this).stop().animate({backgroundPosition:"(-20px 94px)"}, {duration:500})
		})
		.mouseout(function(){
			$(this).stop().animate({backgroundPosition:"(40px 35px)"}, {duration:200, complete:function(){
				$(this).css({backgroundPosition: "-20px 35px"})
			}})
		})
        }); 
    </script>
  </head>
 
  <body>
  		<!-- 
		<ul id="menuul">
			<li class="menuli">
			<div class="me">
					<a href="accountManagement.do?method=getAccount">账号管理</a>
				</div>
			</li>
			<li class="menuli">
			<div class="me">
				<a href="commodityCollection.do?method=get">商品收藏</a>
				</div>
			</li>
			<li class="menuli">
			<div class="me">
				<a href="friendManagement.do?method=get">好友管理</a>
				</div>
			</li>
			
		</ul>
		 -->
		<div id="lselect">
				<h3 class="select"><span>Select a Design:</span></h3>
				<ul>
					<li><a href="accountManagement.do?method=getAccount">个人中心</a></li>
					<li><a href="commodityCollection.do?method=get">商品收藏</a></li>
					<li><a href="friendManagement.do?method=get">好友管理</a></li>
				</ul>
			</div>
  </body>
</html>

