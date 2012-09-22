<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
       
   	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/menu.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/jquery/jquery.bgpos.js"></script>
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
  	
		<div id="lselect">
				<h3 class="select"><span>Select a Design:</span></h3>
				<ul>
				<li><a href="usermgr.do?method=getAll"><strong>用户管理</strong></a></li>
					<li><a href="sensword.do?method=get"><strong>敏感词管理</strong></a></li>
					<li><a href="admgr.do?method=get"><strong>广告管理</strong></a></li>
					<li><a href="resource.jsp"><strong>资源权限管理</strong></a></li>
					<li><a href="track.do?method=getAll"><strong>追踪统计</strong></a></li>
					<li><a href="category.do?method=catemgr"><strong>分类管理</strong></a></li>
					<!-- <li><a href="setup.do?method=goback"><strong>本站配置</strong></a></li> -->
				</ul>
			</div>
  </body>
</html>
