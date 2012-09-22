<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="css/main.css">
		<link rel="stylesheet" type="text/css" href="css/details.css">
		<link rel="stylesheet" type="text/css" href="css/pagination.css">
		<link rel="stylesheet" type="text/css" href="css/hide.css">

		<script type="text/javascript" src="js/jquery/jquery.min.js"></script>
		<script type="text/javascript"
			src="js/jquery_pagination/jquery.pagination.js"> </script>
		<script type="text/javascript" src="js/json2.js"></script>
		<script src="js/showHide.js" type="text/javascript"></script>
		<script src="js/details.js" type="text/javascript"></script>

		<script type="text/javascript">
 		$(document).ready(function(){   
 			 showLevel();
             initPagination("<bean:write name="length" />",0);
             showstars("<bean:write name="product" property="score" />");
        }); 
    </script>
		<title>商品详细信息</title>
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
							<div id="details-product-title">
								<h2>
									<a class="fleft" href="#"><bean:write name="product"
											property="productName" /> </a>
									<span class="fright color-lightgray font-normal"> <bean:write
											name="product" property="price" />元 </span>
								</h2>
							</div>
							<c:if test="${!empty requestScope.product}">
								<div id="productDetails">
									<div class="left">
										<span id="details-productImg"> <a> <img
													align="left" width="270px" height="360px"
													src="<bean:write name="product" property="productImg"/>" />
										</a> </span>
									</div>
									<div class="right">
										<div class="share">
											<input type="hidden" id="productId"
												value="<bean:write name="product" property="productId"/>" />

											<a href="#" onclick="collect()"> <img
													src="images/collect.bmp"> </a>
										</div>
										<div class="productDesc">
											<bean:write name="product" property="productDesc" />
										</div>

										<div class="details-in-line">
											<div id="stars">
												星星图片
											</div>
											<div id="score">
												评分
											</div>
											<div id="details-productSource">
												来源：
												<a href="<bean:write name="product" property="url"/>"> <bean:write
														name="product" property="source" /> </a>
											</div>
											<div class="clear"></div>
										</div>
										<div id="details-vs">
											<a href="#"
												onclick="setNewProduct(${product.productId},'${product.productName}','${product.price}','${product.score}','${product.source}','${product.url}')">
												<img src="images/vs.bmp"> </a>
										</div>
										<div id="level"></div>
									</div>
									<!-- end of right -->
									<div class="clear"></div>
								</div>
								<!-- //productDetails -->
							<div id="pasale">
								<div class="bbp-content">
										<div class="selected">
											<a href="#">你可能还会喜欢</a>
											<ul class="cls">
												<c:if test="${!empty requestScope.relatedProductList}">
													<logic:iterate id="product" indexId="relatedIndex"
														name="relatedProductList">
														<logic:equal name="relatedIndex" value="0">
															<li class="first">
																<a target="_blank"
																	href="details.do?method=get&productId=${product.productId}"><img
																		src="${product.productImg}" width="100" height="100">${product.productName}<br>
																	<strong>特价${product.price}元</strong> </a>
																<span>6折</span>
															</li>
														</logic:equal>
														<logic:notEqual name="relatedIndex" value="0">
															<logic:notEqual name="relatedIndex" value="5">
																<li>
																	<a target="_blank"
																		href="details.do?method=get&productId=${product.productId}"><img
																			src="${product.productImg}" width="100" height="100">${product.productName}<br>
																		<strong>特价${product.price}元</strong> </a>
																</li>
															</logic:notEqual>
														</logic:notEqual>
														<logic:equal name="relatedIndex" value="5">
															<li class="last">
																<a target="_blank"
																	href="details.do?method=get&productId=${product.productId}"><img
																		src="${product.productImg}" width="100" height="100">${product.productName}<br>
																	<strong>特价${product.price}元</strong> </a>
															</li>
														</logic:equal>
													</logic:iterate>
												</c:if>
											</ul>

										</div>
										</div>
										</div>
							 <div id="productTab">
									<div id="tabs1">
										<ul id="tabsul">
											<li>
												<a id="tab1" class="tab" onclick="showTab(1)"><span>评论</span>
												</a>
											</li>
											<!-- <li>
												<a id="tab2" class="tab" onclick="showTab(2)"><span>相关商品</span>
												</a>
											</li>
											 -->
										</ul>
									</div>
									 
								</div>
								
								<!-- //productTab -->

								<div id="comments">
									<div id="commentsList">
										<ul id="commentul">
											<c:if test="${!empty requestScope.commentList}">
												<logic:iterate id="comment" name="commentList">
													<li class="commentItems">
														<div class="userProfile">
															<span class="comment-user-img"><a href=""><img
																		align="left" src="images/user.bmp" /> </a> </span>
															<span class="comment-user-name"> <a href="#">
																	<bean:write name="comment" property="user.username" />
															</a> </span>
														</div>
														<div class="comment">
															<div class="comment-meta-in-line">
																<span class="commentTime"> 评论时间：<bean:write
																		name="comment" property="commentTime"
																		format="yyyy-MM-dd hh:MM:ss" /> </span>
																<span class="btnAddFriend"> <a
																	onclick="addFriend(<bean:write name="comment"
																		property="user.userId" />)">
																		加为好友 </a> </span>
																<div class="clear"></div>
															</div>

															<div class="comment-content">
																<bean:write name="comment" property="commentContent" />
															</div>
														</div>
													</li>
													<div class="clear"></div>
												</logic:iterate>
											</c:if>
										</ul>
									</div>
									<!-- //commentsList -->
									<div class="page-middle">
										<div id="Pagination" class="pagination">
											分页栏
										</div>
									</div>
									<div id="comment-input">
									<div class="content-comment-title">
										<h3>
											我也来说两句
										</h3>
									</div>
									<div id="commentText">
										<security:authorize ifNotGranted="ROLE_USER,ROLE_ADMIN">
											<input type="hidden" id="userName" value="匿名游客" />
											<input type="hidden" id="userId" value="2" />
										</security:authorize>
										<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
											<input type="hidden" id="userName"
												value="<security:authentication property="principal.username" />" />
											<input type="hidden" id="userId"
												value="<security:authentication property="principal.userId" />" />
										</security:authorize>
										<table>
											<tr>
												<td>
													<textarea id="commentStr" name="commentString"
														rows="2
														cols="40"></textarea>
												</td>
											</tr>
											<tr>
												<td>
													<input id="btnComment" type="button" value="评论"
														onclick="addComment()" />
												</td>
											</tr>
										</table>
									</div>	<!-- //commentText -->
									</div>
								</div>	<!-- //comments -->
								<div id="relationProduct">
									<div id="productList">
										<ul class="chart-dashed-list">
											<c:if test="${!empty requestScope.relatedProductList}">
												<logic:iterate id="result" name="relatedProductList">
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
															<span>等级星星</span>
															<span>评分</span>
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
								<!-- //relationProduct -->
							</c:if>
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
			style="display: none; filter: alpha(opacity =   100); opacity: 1;">
			<div class="infoWIN" id="infoWIN">
				<br />
			</div>
		</div>
		<div id="hideCar"
			style="display: none; filter: alpha(opacity =   100); opacity: 1;">
			<div class="car" id="car">
				<br />
			</div>
		</div>

	</body>
</html>


