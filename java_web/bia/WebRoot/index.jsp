<%@ page language="java" pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<title>比啊--优秀的商品搜索</title>
		<!-- add your meta tags here -->
		<link href="css/index.css" rel="stylesheet" type="text/css" />
		<link href="css/hotList.css" rel="stylesheet" type="text/css" />
		<link href="css/adTop.css" rel="stylesheet" type="text/css" />
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
							<jsp:include page="/details.do?method=getAdDown"></jsp:include>
							<ul class="">
										<c:if test="${!empty requestScope.addownList}">
											<logic:iterate id="ad" indexId="adIndex"
												name="addownList">
												<logic:equal name="adIndex" value="0">
												
													<li class="last">
														<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
									codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0"
									width="250px" height="226px">
									<param name="movie" value="http://www.masamaso.com/index.shtml">
									<param name="quality" value="high">
									<embed
										src="${ad.adPath}"
										quality="high"
										pluginspage="http://www.macromedia.com/go/getflashplayer"
										type="application/x-shockwave-flash" >
									</embed>
								</object>
													</li>
													</logic:equal>
											<logic:equal name="adIndex" value="1">
												
													<li class="last">
														<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
									codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0"
									width="250px" height="150px">
									<param name="movie" value="http://www.masamaso.com/index.shtml">
									<param name="quality" value="high">
									<embed
										src="${ad.adPath}"
										quality="high"
										pluginspage="http://www.macromedia.com/go/getflashplayer"
										type="application/x-shockwave-flash" >
									</embed>
								</object>
													</li>
													</logic:equal>
													
											</logic:iterate>
										</c:if>
									</ul>
						</div>
					</div>
					<div id="col3">
						<div id="col3_content" class="clearfix">
							<!-- add your content here -->
							<div id="topAd">
								<div id="sale_slide">
									<div class="hd">
										<span></span>
									</div>
									<div class="adcontent">
										<div class="con_main">
											<div id="slide">
												<div class="middle">
													<div id="shop_slider" class="shop-notice-pic">
														<div id="shop_slider_imgs" class="slide_imgs">
															<jsp:include page="/index.do?method=getAdTop"></jsp:include>
															<c:if test="${!empty requestScope.adTopList}">
																<logic:iterate id="advertise" name="adTopList">
																	<div class="slideItem">
																		<a href="${advertise.target}" target="_blank"
																			title="SPORTICA"> <img height="200" width="524"
																				src="${advertise.adPath}" /> </a>

																	</div>
																</logic:iterate>
															</c:if>


														</div>

														<div class="slide-tags-t">
															<ol id="slide-tags-x">
																<c:if test="${!empty requestScope.adTopList}">
																<logic:iterate id="advertise" name="adTopList">
																<li style="display: none;">
																	${advertise.adDesc}
																</li>
																</logic:iterate>
																</c:if>
																
															</ol>
															<ol id="slide-tags-n">
																<li class="first selected">
																	1
																</li>
																<li class="">
																	2
																</li>
																<li class="">
																	3
																</li>
																<li class="">
																	4
																</li>
															</ol>

														</div>
													</div>
												</div>
											</div>
											<div id="instantcard">
												<div id="hp-notice" class="mod">
													<div class="mod-content">
														<div class="adhd">
															<h3>
																公告栏
															</h3>
														</div>
														<div class="adbd">
															<span class="img"> <a
																href="http://co.youa.baidu.com/content/help/"
																target="_blank" width="51" height="50"><img alt="公告"
																		src="http://co.youa.baidu.com/picture/r/mall/ya_nt.gif">
															</a> </span>
															<span>比啊正式上线！<br> 欢迎大家的光临！ <br>
																我们的目标是打造全国最专业的商品搜索平台 </span>
														</div>
													</div>

												</div>

												<div>
													<div class="btn">

														<a
															href="${pageContext.request.contextPath}/register.do?method=reg"><img
																src="images/btn_wantreg.jpg" width="93px"> </a>
														<a
															href="${pageContext.request.contextPath}/login.do?method=log"><img
																src="images/btn_wantlogin.jpg" width="93px"> </a>
													</div>

												</div>
											</div>
										</div>

								</div>
							</div>

							<script type="text/javascript"
								src="http://co.youa.baidu.com/picture/p/js/core-mini.js?v=001.js"></script>
							<script type="text/javascript"> 
//pic slide
(function(){
	var s1 = new PicSlide({
		container:$("shop_slider_imgs"),
		pics:Dom.getElementsByClassName("slideItem",$("shop_slider_imgs")),
		pages:$("slide-tags-n").getElementsByTagName("li"),
		interval:5000,
		eventType:"mouseover",
		effect:"fade"
	});
	s1.run();
	var lis = $("slide-tags-x").getElementsByTagName("li");
	Dom.show([s1.pics[0],lis[0]]);
	CustEvent.observe(s1,"picChange",function(n){
		Dom.getArray(lis).each(function(el){Dom.hide(el)});
		Dom.show(lis[n]);
	});
})();
 
 </script>

							<script type="text/javascript">__stat = new Image().src = 'http://img.youa.baidu.com/st/stat.jpg?pl=cms&ss=sale&fp=index&rf=' + encodeURIComponent(document.referrer)+'&rn='+Math.random();</script>
</div>
							<!-- //end adtop -->
							<div id="hotList">

								<div id="cuxiao2">
									<jsp:include page="/details.do?method=getTopScore"></jsp:include>
									<ul class="">
										<c:if test="${!empty requestScope.topScoreList}">
											<logic:iterate id="product" indexId="topScoreIndex"
												name="topScoreList">

												<logic:notEqual name="topScoreIndex" value="3">
													<li class="">
														<a target="_blank"
															href="details.do?method=get&productId=${product.productId}"><img
																src="${product.productImg}" width="170" height="118" />
														</a>
														<span class="hot">Hot</span>
													</li>
												</logic:notEqual>
												<logic:equal name="topScoreIndex" value="3">
													<li class="last">
														<a target="_blank"
															href="details.do?method=get&productId=${product.productId}"><img
																src="${product.productImg}" width="170" height="118" />
														</a>
														<span class="hot">Hot</span>
													</li>
												</logic:equal>
											</logic:iterate>
										</c:if>
									</ul>
								</div>


								<!-- 年终特价促销推荐单品 -->
								<div id="pasale">
									<ul class="bbp-nav cls" id="pasaleul">
										<li class="first selected">
											<a href="javascript:void(0)">热门收藏</a>
										</li>
										<li class="">
											<a href="javascript:void(0)">正品特价</a>
										</li>
										<li class="">
											<a href="javascript:void(0)">时尚新款</a>
										</li>
										<li class="">
											<a href="javascript:void(0)">晒经典</a><span>new</span>
										</li>
									</ul>
									<div class="bbp-content">
										<div class="selected">
											<jsp:include page="/details.do?method=getCollectionTop"></jsp:include>
											<ul class="cls">
												<c:if test="${!empty requestScope.collectionTopList}">
													<logic:iterate id="product" indexId="classicIndex"
														name="collectionTopList">
														<logic:equal name="collectionIndex" value="0">
															<li class="first">
																<a target="_blank"
																	href="details.do?method=get&productId=${product.productId}"><img
																		src="${product.productImg}" width="100" height="100">${product.productName}<br>
																	<strong>特价${product.price}元</strong> </a>
																<span>6折</span>
															</li>
														</logic:equal>
														<logic:notEqual name="collectionIndex" value="0">
															<logic:notEqual name="collectionIndex" value="5">
																<li>
																	<a target="_blank"
																		href="details.do?method=get&productId=${product.productId}"><img
																			src="${product.productImg}" width="100" height="100">${product.productName}<br>
																		<strong>特价${product.price}元</strong> </a>
																</li>
															</logic:notEqual>
														</logic:notEqual>
														<logic:equal name="collectionIndex" value="5">
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

										<div class="">
											<jsp:include page="/details.do?method=getZhengpin"></jsp:include>
											<ul class="cls">
												<c:if test="${!empty requestScope.zhengpinList}">
													<logic:iterate id="product" indexId="zhengpinIndex"
														name="zhengpinList">
														<logic:equal name="zhengpinIndex" value="0">
															<li class="first">
																<a target="_blank"
																	href="details.do?method=get&productId=${product.productId}"><img
																		src="${product.productImg}" width="100" height="100">${product.productName}<br>
																	<strong>特价${product.price}元</strong> </a>
																<span>6折</span>
															</li>
														</logic:equal>
														<logic:notEqual name="zhengpinIndex" value="0">
															<logic:notEqual name="zhengpinIndex" value="5">
																<li>
																	<a target="_blank"
																		href="details.do?method=get&productId=${product.productId}"><img
																			src="${product.productImg}" width="100" height="100">${product.productName}<br>
																		<strong>特价${product.price}元</strong> </a>
																</li>
															</logic:notEqual>
														</logic:notEqual>
														<logic:equal name="zhengpinIndex" value="5">
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

										<div class="">
											<jsp:include page="/details.do?method=getFasion"></jsp:include>
											<ul class="cls">
												<c:if test="${!empty requestScope.fasionList}">
													<logic:iterate id="product" indexId="fasionIndex"
														name="fasionList">
														<logic:equal name="fasionIndex" value="0">
															<li class="first">
																<a target="_blank"
																	href="details.do?method=get&productId=${product.productId}"><img
																		src="${product.productImg}" width="100" height="100">${product.productName}<br>
																	<strong>特价${product.price}元</strong> </a>
																<span>6折</span>
															</li>
														</logic:equal>
														<logic:notEqual name="fasionIndex" value="0">
															<logic:notEqual name="fasionIndex" value="5">
																<li>
																	<a target="_blank"
																		href="details.do?method=get&productId=${product.productId}"><img
																			src="${product.productImg}" width="100" height="100">${product.productName}<br>
																		<strong>特价${product.price}元</strong> </a>
																</li>
															</logic:notEqual>
														</logic:notEqual>
														<logic:equal name="fasionIndex" value="5">
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

										<div class="">
											<jsp:include page="/details.do?method=getClassic"></jsp:include>
											<ul class="cls">
												<c:if test="${!empty requestScope.classicList}">
													<logic:iterate id="product" indexId="classicIndex"
														name="classicList">
														<logic:equal name="classicIndex" value="0">
															<li class="first">
																<a target="_blank"
																	href="details.do?method=get&productId=${product.productId}"><img
																		src="${product.productImg}" width="100" height="100">${product.productName}<br>
																	<strong>特价${product.price}元</strong> </a>
																<span>6折</span>
															</li>
														</logic:equal>
														<logic:notEqual name="classicIndex" value="0">
															<logic:notEqual name="classicIndex" value="5">
																<li>
																	<a target="_blank"
																		href="details.do?method=get&productId=${product.productId}"><img
																			src="${product.productImg}" width="100" height="100">${product.productName}<br>
																		<strong>特价${product.price}元</strong> </a>
																</li>
															</logic:notEqual>
														</logic:notEqual>
														<logic:equal name="classicIndex" value="5">
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
								<!-- //年终特价促销推荐单品 -->
							
								<script type="text/javascript">
		//<![CDATA[
		
		var container1=$("pasale");
		var slider1=new PicSlide({container:container1,pics:Dom.getElementsByClassName("bbp-content",container1)[0].getElementsByTagName("div"),pages:Dom.getElementsByClassName("bbp-nav",container1)[0].getElementsByTagName("li"),interval:5000,eventType:"mouseover"});
		slider1.run();
		//]]></script>
							</div>
							<!-- //hotList -->
							<div id="middle-flash">
								<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
									codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0"
									width="770" height="135px">
									<param name="movie" value="http://www.masamaso.com/index.shtml">
									<param name="quality" value="high">
									<embed
										src="http://img1.126.net/channel4/006860/maso750120_100505a.swf"
										quality="high"
										pluginspage="http://www.macromedia.com/go/getflashplayer"
										type="application/x-shockwave-flash" width="770"
										height="135px">
									</embed>
								</object>
							</div>
							<div id="categoryList">
								<jsp:include page="/category.do?method=getIndexCate"></jsp:include>

								<fieldset id="searchfs">
									<div class="tit">
										<img src="images/bg_cate.png" />
									</div>

									<div class="cat-items">
										<c:if test="${!empty requestScope.allCategoryNodeList}">
											<nested:iterate name="allCategoryNodeList"
												indexId="cateIndex" id="categoryNode1">
												<div class="item">
													<span class="item-title"> <a
														href="search.do?method=caSearch&categoryId=<nested:write name="categoryNode1"
																	property="categoryId" />">
															<bean:write name="categoryNode1" property="categoryName" />
													</a> </span>
													<div class="itemImage">
														<img
															src="images/cate/cate<bean:write name="cateIndex"/>.gif"
															onload="DrawImage120_65(this);" />
													</div>
													<ul>
														<nested:define name="categoryNode1" id="categoryNode2" />
														<nested:iterate name="categoryNode2"
															property="categoryNodeList" id="categoryNode">
															<li>
																&nbsp;
																<a
																	href="search.do?method=caSearch&categoryId=<nested:write name="categoryNode"
																	property="categoryId" />">
																	<nested:write name="categoryNode"
																		property="categoryName" /> </a>
															</li>
														</nested:iterate>
													</ul>
													<div class="clear"></div>
												</div>
												<div class="clear"></div>
												<!-- //item -->
											</nested:iterate>
										</c:if>
									</div>
									<!-- //cat-items-->
								</fieldset>
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
	</body>
</html>


