<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<title>搜索结果</title>
		<!-- add your meta tags here -->

		<link href="css/layout.css" rel="stylesheet" type="text/css" />
		<!--[if lte IE 7]>
		<link href="css/patches/patch.css" rel="stylesheet" type="text/css" />
		<![endif]-->
		<link rel="stylesheet" type="text/css" href="css/search.css">
		<link rel="stylesheet" type="text/css" href="css/pagination.css">

		<script type="text/javascript" src="js/jquery/jquery.min.js"></script>
		<script type="text/javascript"
			src="js/jquery_pagination/jquery.pagination.js"> </script>
		<script type="text/javascript" src="js/search.js"></script>
		<script type="text/javascript" src="js/json2.js"></script>
		<script type="text/javascript">
 		$(document).ready(function(){   
             initPagination("<bean:write name="length" />",0);
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

							<div id="searchCondition">
								<div class="content-title">
									<h3>
										高级搜索
									</h3>
								</div>
								<fieldset id="searchfs">
									<html:form action="/search.do?method=adSearch" method="post"
										focus="search">
										<table class="searchTable">
											<tr>

												<td class="adword">
													<html:text property="keyword" styleClass="txt_adword"></html:text>
												</td>
												<td class="btn_ad">
													<html:submit property="method" styleId="btnAdSearch"
														value=" ">
														<bean:message key="Search.AdSearch" />
													</html:submit>
												</td>
											</tr>
											<tr>
												<td class="p_price">
													价格
												</td>
												<td class="txt_price">
													<html:text property="price1" size="10" />
													元至
													<html:text property="price2" size="10" />
													元
												</td>
												<td class="p_score">
													评分
													<html:select property="score">
														<html:option value="0">不限&nbsp&nbsp&nbsp&nbsp</html:option>
														<html:option value="1">1分以上</html:option>
														<html:option value="2">2分以上</html:option>
														<html:option value="3">3分以上</html:option>
														<html:option value="4">4分以上</html:option>
													</html:select>
												</td>
											</tr>
											<tr>
												<td class="p_store">
													商城
												</td>
												<td class="txt_store">
													<html:select property="store">
														<html:option value="不限">不限&nbsp&nbsp&nbsp </html:option>
														<html:option value="淘宝">淘宝&nbsp&nbsp&nbsp   </html:option>
														<html:option value="京东">京东&nbsp&nbsp&nbsp   </html:option>
														<html:option value="有">有啊&nbsp&nbsp&nbsp  </html:option>
														<html:option value="易趣">易趣&nbsp&nbsp&nbsp </html:option>
													</html:select>
													<html:hidden property="categoryId" styleId="categoryId"
														value="-1" />
												</td>
												<td class="txt_sort">
													按
													<html:select property="sort">
														<html:option value="0">相关性&nbsp&nbsp&nbsp&nbsp</html:option>
														<html:option value="1">价格从高到低</html:option>
														<html:option value="2">价格从低到高</html:option>
														<html:option value="3">评分从高到低</html:option>
														<html:option value="4">评分从低到高</html:option>
													</html:select>
													排序
												</td>
											</tr>
										</table>

										<table class="cateTableSelete">
											<tr>
												<td class="p_cate">
													选择分类
												</td>
												<td class="txt_fcate">
													<html:select property="firstCategoryList"
														onchange="CatChange(this.value,1)">
														<option value="-1">
															不限
															<c:if test="${!empty requestScope.firstCategoryList}">
																<logic:iterate id="firstCategory"
																	name="firstCategoryList">
																	<option
																		value="<bean:write name= "firstCategory" property="categoryId" />">
																		<bean:write name="firstCategory"
																			property="categoryName" />
																</logic:iterate>
															</c:if>
													</html:select>
												</td>
												<td id="secondCat" class="txt_scate">

												</td>
												<td id="thirdCat" class="txt_tcate">

												</td>
												<td id="fourthCat" class="txt_focate">

												</td>
											</tr>
										</table>
									</html:form>
								</fieldset>
							</div>
							<div id="searchResults">
								<div class="content-title">
									<h3>
										搜索结果
									</h3>
								</div>
								<div id="relationProduct">
									<div id="productList">
										<ul class="chart-dashed-list">
											<c:if test="${!empty requestScope.resultList}">
												<logic:iterate id="result" name="resultList">
													<li class="relatedProductItems">
														<div class="relatedProductImg">
															<a
																href="details.do?method=get&productId=${result.productId}">
																<img align="left" src="${result.productImg}"
																	onload="DrawImage(this);" /> </a>
														</div>
														<div class="relatedProductInfo">
															<div class="relatedProductLink">
																<a
																	href="details.do?method=get&productId=${result.productId}" style="font-size:16px;">${result.productName}
																</a>
															</div>
															<div class="relatedProductPrice">
																<bean:write name="result" property="price" />
																元
															</div>
															<div class="clear"></div>
														</div>
														<div class="relatedProductDesc">
															<bean:write name="result" property="productDesc" />
														</div>
														<div class="clear"></div>
														<div class="relatedProductScore">
															<span class="stars">等级星星</span>
															<span class="score">评分:${result.score}</span>
															<span> 来源： <a
																href="<bean:write name="result" property="url"/>"> <bean:write
																		name="result" property="source" /> </a> </span>
														</div>
													</li>
												</logic:iterate>
											</c:if>
										</ul>
									</div>
								</div>


								<div id="Pagination" class="pagination">
									分页栏
								</div>

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
		<div id="hide"
			style="display: none; filter: alpha(opacity =     100); opacity: 1;">
			<div class="infoWIN" id="infoWIN">
				<br />
			</div>
		</div>
	</body>
</html>


