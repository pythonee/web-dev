
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" >
<title>用户中心-商品收藏</title>
<!-- add your meta tags here -->

<link href="css/layout.css" rel="stylesheet" type="text/css" >
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/collection.css">
	<link rel="stylesheet" href="css/pagination.css" />
   <link rel="stylesheet" type="text/css" href="../css/hide.css">
      <script type="text/javascript" src="../js/showHide.js"></script>
     
     <script type="text/javascript" src="js/jquery/jquery.min.js"></script>
     <script type="text/javascript" src="js/jquery_pagination/jquery.pagination.js"> </script>
     <script type="text/javascript" src="js/commodityCollection.js"> </script>
     <script type="text/javascript" src="js/json2.js"></script>
     	 <script type="text/javascript">
        	 $(document).ready(function(){      
                
           		getCollectionLength();
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
										收藏管理
									</h3>
		</div>
            <div id="collectionList">
			<table class="collcTable">
		    <thead>
		       <tr>
			   	<th class="nameth">商品链接</th>
				<th class="deleteth">删除收藏</th>
			   </tr>
	        </thead>
	         <tbody id="collectionTablebody">
				
		     </tbody>
		</table>
			
			</div>
			<div id="Pagination" class="pagination">
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
</body>
</html>
			
