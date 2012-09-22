<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" >
<title>分类管理</title>
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
      <link rel="stylesheet" href="css/cate.css" />
     <link rel="stylesheet" type="text/css" href="../css/hide.css">
     <script type="text/javascript" src="../js/jquery/jquery.min.js"></script>
     <script type="text/javascript" src="../js/jquery_pagination/jquery.pagination.js"> </script>
	 <script type="text/javascript" src="js/catemgr.js"> </script>
	 <script type="text/javascript" src="js/ajax.js"> </script>	
	  <script type="text/javascript" src="../js/json2.js"></script>
	  <script src="../js/showHide.js" type="text/javascript"></script>
	 <script>
		
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
							<div class="content-title">
								<h3>
									增加分类
								</h3>
							</div>
							<div id="addList">
			<fieldset id="catefs"> 
			<legend></legend>
			<html:form action="admin/category.do?method=add" method="post" onsubmit="return addSure();">
			<table class="cateTable" border="0">
			
			<tr id="fatherCate">
				<td class="ptd">选择父分类</td>
				<td class="catetd">
				<html:hidden property="categoryId" styleId="categoryId" value="-1" ></html:hidden>
				<html:select property="firstCategoryList" onchange="CatChange(this.value,1)">
				   <option value="-1">设为一级分类
				   <c:if test="${!empty requestScope.FCategoryList}">
				    <logic:iterate   id= "FCategory"   name= "FCategoryList"> 
			        <option value="<bean:write name= "FCategory" property="categoryId" />"><bean:write name= "FCategory" property="categoryName" />
			        </logic:iterate>
			       </c:if>
	 			</html:select>
			</td>
			<td id="secondCat" class="catetd">
			
			</td>
			<td id="thirdCat" class="catetd">
			
			</td>
			<td id="fourthCat" class="catetd">
			
			</td>
			</tr>
			<tr>
			<td class="ptd">分类名</td>
			<td class="txt_name"><html:text property="categoryName" styleId="category" onblur="isNull('category')" value="" /></td>
			<td valign="top" class="startd"><span class="star" id="category_re">*</span></td>
			<td valign="top" id="category_re_m" class="warntd"></td>
			</tr>
			<tr>
			<td class="ptd">标签关键字</td>
			<td class="tagtd"><html:textarea property="tags" styleId="tags" onblur="isNull('tags')" value="" rows="5" cols="28" /></td>
			<td valign="top"class="startd"><span class="star" id="tags_re">*</span></td>
			<td valign="top" id="tags_re_m" class="warntd"></td>
			</tr>
			<tr>
			<td>
				<html:reset  styleClass="btnresetclass" value=" " />
			</td>
			<td>
				<html:submit property="method" styleId="addCate" value=" ">
					<bean:message key="Category.Add" />
				</html:submit>

			</td>
			</tr>
		      </table>
			</html:form>
			</fieldset>
			</div>
			
			<div id="deleteList">
			<div class="content-title">
									<h3>
										删除分类
									</h3>
								</div>
			<fieldset id="catefs"> 
			<legend></legend>
			<html:form action="admin/category.do?method=delete" method="post" onsubmit="return deleteSure();">
			<table class="cateTable" border="0">
	
			<tr id="deletefatherCate">
				<td class="ptd">选择分类</td>
				<td class="catetd">
				<html:hidden property="deletecategoryId" styleId="deletecategoryId" value="-1" ></html:hidden>
				<html:select property="firstCategoryList" onchange="deleteCatChange(this.value,1)">
				    <option value="-1">不继续细化分类
				   <c:if test="${!empty requestScope.FCategoryList}">
				    <logic:iterate   id= "FCategory"   name= "FCategoryList"> 
			        <option value="<bean:write name= "FCategory" property="categoryId" />"><bean:write name= "FCategory" property="categoryName" />
			        </logic:iterate>
			       </c:if>
	 			</html:select>
			</td>
			<td id="deletesecondCat" class="catetd">
			
			</td>
			<td id="deletethirdCat" class="catetd">
			
			</td>
			<td id="deletefourthCat" class="catetd">
			
			</td>
			</tr>
			<tr>
			<td>
				<html:reset styleClass="btnresetclass" value=" " />
			</td>
			<td>
				<html:submit property="method" styleId="deleteCate" value=" ">
					<bean:message key="Category.Delete" />
				</html:submit>

			</td>
			</tr>
		      </table>
			</html:form>
			</fieldset>
			</div>
			
			<div id="editList">
			<div class="content-title">
									<h3>
										分类编辑
									</h3>
								</div>
			<fieldset id="catefs"> 
			<legend></legend>
			<html:form action="admin/category.do?method=edit" method="post" onsubmit="return editSure();">
			<table class="cateTable" border="0">
			
			<tr id="origfatherCate">
				<td class="ptd">选择分类</td>
				<td class="catetd">
				<html:hidden property="origcategoryId" styleId="origcategoryId" value="-1" ></html:hidden>
				<html:select property="firstCategoryList" onchange="EditCatChange(this.value,1)">
				    <option value="-1">不编辑
				   <c:if test="${!empty requestScope.FCategoryList}">
				    <logic:iterate   id= "FCategory"   name= "FCategoryList"> 
			        <option value="<bean:write name= "FCategory" property="categoryId" />"><bean:write name= "FCategory" property="categoryName" />
			        </logic:iterate>
			       </c:if>
	 			</html:select>
			</td>
			<td id="origsecondCat" class="catetd">
			
			</td>
			<td id="origthirdCat" class="catetd">
			
			</td>
			<td id="origfourthCat" class="catetd">
			
			</td>
			</tr>
			<tr>
			<td class="ptd">分类名</td>
			<td class="txt_name"><html:text property="categoryName" styleId="origcategoryName" onblur="isNull('origcategoryName')" value="" /></td>
			<td valign="top" class="startd"><span class="star" id="origcategoryName_re">*</span></td>
			<td valign="top" id="origcategoryName_re_m" class="warntd"></td>
			</tr>
			<tr>
			<td class="ptd">标签关键字</td>
			<td class="tagtd"><html:textarea property="tags" styleId="origTags" onblur="isNull('origTags')" value="" rows="5" cols="28" /></td>
			<td valign="top" class="startd"><span class="star" id="origTags_re">*</span></td>
			<td valign="top" id="origTags_re_m" class="warntd"></td>
			</tr>
				<tr id="adaptfatherCate">
				<td  class="ptd">调整到父分类</td>
				<td class="catetd">
				<html:hidden property="adaptcategoryId" styleId="adaptcategoryId" value="-1" ></html:hidden>
				<html:select property="firstCategoryList" onchange="AdaptCatChange(this.value,1)">
				   <option value="-1">设为一级分类
				   <c:if test="${!empty requestScope.FCategoryList}">
				    <logic:iterate   id= "FCategory"   name= "FCategoryList"> 
			        <option value="<bean:write name= "FCategory" property="categoryId" />"><bean:write name= "FCategory" property="categoryName" />
			        </logic:iterate>
			       </c:if>
	 			</html:select>
			</td>
			<td id="adaptsecondCat" class="catetd">
			
			</td>
			<td id="adaptthirdCat" class="catetd">
			
			</td>
			<td id="adaptfourthCat" class="catetd">
			
			</td>
			</tr>
			<tr>
			<td>
				<html:reset styleClass="btnresetclass" value=" " />
			</td>
			<td>
				<html:submit property="method" styleId="editCate" value=" ">
					<bean:message key="Category.Edit" />
				</html:submit>

			</td>
			</tr>
		      </table>
			</html:form>
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
	 
	<c:if test="${!empty requestScope.addCate}">
		<script type="text/javascript">
			show('hide','添加分类成功');
		</script>
	</c:if>
	<c:if test="${!empty requestScope.editCate}">
		<script type="text/javascript">
			show('hide','编辑分类成功');
		</script>
	</c:if>
	<c:if test="${!empty requestScope.deleteCate}">
		<script type="text/javascript">
			show('hide','删除分类成功');
		</script>
	</c:if>
</body>
</html>
																																																																																																																																																						

