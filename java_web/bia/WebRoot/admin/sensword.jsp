<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" >
<title>敏感词管理</title>
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
    <link rel="stylesheet" href="css/sens.css" />
     <link rel="stylesheet" type="text/css" href="../css/hide.css">
      <script type="text/javascript" src="../js/showHide.js"></script>
     <script type="text/javascript" src="../js/jquery/jquery.min.js"></script>
     <script type="text/javascript" src="../js/jquery_pagination/jquery.pagination.js"> </script>
	 <script type="text/javascript" src="js/sens.js"> </script>	
	  <script type="text/javascript" src="../js/json2.js"></script>
	  
	 <script>
	 $(document).ready(function(){   
             seinitPagination("<bean:write name="length" />",0);
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
										敏感词管理
									</h3>
								</div>
			<fieldset id="wordfs"> 
			<legend></legend>
			<form action="sensword.do?method=delete" method="post" onsubmit="return deleteSure();">
			<table id="wordTable" border="0">
		
				<thead>
					<tr>
						<th class="deleteth"><input type="submit" value=" " id="btn_batch_delete" /></th>
						<th class="wordth">敏感词</th>
						<th class="editth">编辑选项</th>
						<th class="cancelth"></th>
					</tr>
				</thead>
				<tbody id="wordtbody">
				<c:if test="${!empty requestScope.wordList}">
				<logic:iterate   id= "word"   name= "wordList"> 
		        <tr>
		        	<td class="deletetd"><input type="checkbox" name="boxDelete" value="${word.sensitiveId}" /></td>
		          <td class="wordtd"><p id="pWord${word.sensitiveId}">${word.sensitiveWord}</p><input id="txtWord${word.sensitiveId}" class="txtWord" type="text" name="txtWord" value="${word.sensitiveWord}" /></td>
		          <td class="edittd"><input id="btnEditWord${word.sensitiveId}" type="button" class="btnEditWord" name="btnEditWord" value=" " onclick="clickEditWord(${word.sensitiveId})" /><input id="btnSureWord${word.sensitiveId}" class="btnSure" type="button" name="btnSureWord" value=" " onclick="clickSureWord(${word.sensitiveId})" /></td>
		          <td class="canceltd"><input id="btnCancelWord${word.sensitiveId}" type="button" class="btnSandC" name="btnCancelWord" value=" " onclick="clickCancelWord(${word.sensitiveId})" /></td>
		        </tr>
		         </logic:iterate>
		        </c:if>
		        </tbody>
		      </table>
				</form>
			<div id="Pagination" class="pagination">
    			分页栏
       		 	</div>
       		 
			</fieldset>
			</div>
				<div>
       		 	<div class="content-title">
									<h3>
										增加敏感词
									</h3>
					</div>
       		 	<div id="addDiv">
       		 	<table id="addWordTable" border="0">
       		 	<tr>
       		 	<td class="wordtd">
       		 	<input id="addWordtxt" class="addWordtxt" type="text" />
       		 	</td>
       		 	<td class="addtd">
       		 	<input id="btnAdd" type="button" value=" " onClick="addWord()" />
       		 	</td>
       		 	</tr>
       		 	</table>
       		 	</div>
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
	<c:if test="${!empty requestScope.deleteSens}">
		<script type="text/javascript">
			show('hide','敏感词删除成功!');
		</script>
	</c:if>
</body>
</html>
																																																																																																																																																						


