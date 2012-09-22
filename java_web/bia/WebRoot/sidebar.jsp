<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/track.js"></script>
		<link rel="stylesheet" type="text/css" href="css/sidebar.css">
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript" src="js/flashContents.js"></script>
		<script src="js/sidebar.js" type="text/javascript"></script>
	</head>

	<body>
		<div id="adv">
			<div id="ad_title" class="sidebar_title">
				最新上市产品推荐
			</div>
			<div id="sidebar_middle-flash">
				<div id="sidebar_middle-flash-content">
					<c:if test="${!empty requestScope.adList}">
						<logic:iterate id="ad" indexId="index" name="adList">
							<logic:equal name="index" value="0">
								<script type="text/javascript">
					 img1=new Image ();img1.src='${ad.adPath}';
					 url1=new Image ();url1.src='${ad.target}';
					 </script>
							</logic:equal>
							<logic:equal name="index" value="1">
								<script type="text/javascript">
						img2=new Image ();img2.src='${ad.adPath}';
					 	url2=new Image ();url2.src='${ad.target}';
					 	 </script>
							</logic:equal>
							<logic:equal name="index" value="2">
								<script type="text/javascript">
					 img3=new Image ();img3.src='${ad.adPath}';
					  url3=new Image ();url3.src='${ad.target}';
					   </script>
							</logic:equal>
							<logic:equal name="index" value="3">
								<script type="text/javascript">
					img4=new Image ();img4.src='${ad.adPath}';
					 url4=new Image ();url4.src='${ad.target}';
					  </script>
							</logic:equal>
						</logic:iterate>
					</c:if>
					<!-- 以下为主页广告 -->
					<c:if test="${empty requestScope.adList}">
						<jsp:include page="/index.do?method=get"></jsp:include>
						<c:if test="${!empty requestScope.indexAdList}">
							<logic:iterate id="ad" indexId="index" name="indexAdList">
								<logic:equal name="index" value="0">
									<script type="text/javascript">
									 img1=new Image ();img1.src='${ad.adPath}';
									 url1=new Image ();url1.src='${ad.target}';
									 </script>
								</logic:equal>
								<logic:equal name="index" value="1">
									<script type="text/javascript">
									img2=new Image ();img2.src='${ad.adPath}';
								 	url2=new Image ();url2.src='${ad.target}';
								 	 </script>
								</logic:equal>
								<logic:equal name="index" value="2">
								<script type="text/javascript">
								 img3=new Image ();img3.src='${ad.adPath}';
								  url3=new Image ();url3.src='${ad.target}';
								   </script>
									</logic:equal>
								<logic:equal name="index" value="3">
									<script type="text/javascript">
								img4=new Image ();img4.src='${ad.adPath}';
								 url4=new Image ();url4.src='${ad.target}';
								  </script>
								</logic:equal>
							</logic:iterate>
						</c:if>
					</c:if>
					<script type="text/javascript">
			       var widths=250;
			       var heights=160;
			       var counts=4;
			     function setAdSize(size){
			     	counts=size;
			     }
			     setAdSize(${adListLength});
			      /////////////////////////////
			       document.write('<style>');
			       document.write('.axx{padding:1px 7px;border-left:#cccccc 1px solid;}');
			       document.write('a.axx:link,a.axx:visited{text-decoration:none;color:#fff;line-height:12px;font:9px sans-serif;background-color:#666;}');
			       document.write('a.axx:active,a.axx:hover{text-decoration:none;color:#fff;line-height:12px;font:9px sans-serif;background-color:#999;}');
			       document.write('.bxx{padding:1px 7px;border-left:#cccccc 1px solid;}');
			       document.write('a.bxx:link,a.bxx:visited{text-decoration:none;color:#fff;line-height:12px;font:9px sans-serif;background-color:#D34600;}');
			       document.write('a.bxx:active,a.bxx:hover{text-decoration:none;color:#fff;line-height:12px;font:9px sans-serif;background-color:#D34600;}');
			       document.write('</style>');
			       document.write('<div style="width:'+widths+'px;height:'+heights+'px;overflow:hidden;text-overflow:clip;">');
			       document.write('<div><a id="url"><img id="pic" style="border:0px;filter:progid:dximagetransform.microsoft.wipe(gradientsize=1.0,wipestyle=4, motion=forward)" width='+widths+' height='+heights+' /></a></div>');
			       document.write('<div style="filter:alpha(style=1,opacity=10,finishOpacity=80);background: #888888;width:100%-2px;text-align:right;top:-12px;position:relative;margin:1px;height:12px;padding:0px;margin:0px;border:0px;">');
			       for(var i=1;i<counts+1;i++){
			       document.write('<a href="javascript:changeimg('+i+');" id="xxjdjj'+i+'" class="axx" target="_self">'+i+'</a>');
			       }
			       document.write('</div></div>');
			       change_img();
			                
				</script>
				</div>
			</div>
		</div>

		<div id="recommend">
			<jsp:include page="/recommend.do?method=hotHighestScore"></jsp:include>
			<div class="corner">
				<div id="cornerl"></div>
				<div id="cornerline"></div>
				<div id="cornerr"></div>
			</div>
			<div id="recommand_title" class="sidebar_title">
				比啊热门商品推荐
			</div>
			<div id="sidebar_productList" class="sidebar_list">
				<ul id="sidebar_product_ul" class="sidebar_ul">
					<c:if test="${!empty requestScope.productList}">
						<logic:iterate id="product" indexId="reIndex" name="productList">
							<logic:equal name="reIndex" value="0">
								<li class="sidebar_li_selected"
									onmouseover="this.className='sidebar_li_selected';"
									onmouseout="this.className='sidebar_li';">
									<span class="num"><%=Integer.parseInt(reIndex.toString()) + 1%>
									</span>
									<a class="url"
										href="details.do?method=get&productId=${product.productId}"
										target="_blank"> <span class="sidebar_item_img"> <img
												align="left" src="${product.productImg}"
												onload="DrawImage(this);" /> </span> <span class="sidebar_left">${product.productName}</span><span
										class="sidebar_right">${ product.price }元</span> </a>
									<div class="clear"></div>
								</li>
							</logic:equal>
							<logic:notEqual name="reIndex" value="0">
								<li class="sidebar_li"
									onmouseover="this.className='sidebar_li_selected';"
									onmouseout="this.className='sidebar_li';">
									<span class="num"><%=Integer.parseInt(reIndex.toString()) + 1%>
									</span>
									<a href="details.do?method=get&productId=${product.productId}"
										target="_blank"> <span class="sidebar_item_img"> <img
												align="left" src="${product.productImg}"
												onload="DrawImage(this);" /> </span> <span class="sidebar_left">${product.productName}</span><span
										class="sidebar_right">${ product.price }元</span> </a>
									<div class="clear"></div>
								</li>
							</logic:notEqual>

						</logic:iterate>
					</c:if>
				</ul>
			</div>
		</div>

		<div id="freshComment">
			<jsp:include page="/comment.do?method=freshComment"></jsp:include>
			<div id="freshComment_title" class="sidebar_title">
				用户最新评论商品
			</div>
			<div id="sidebar_commentList" class="sidebar_list">
				<ul id="sidebar_comment_ul" class="sidebar_ul">
					<c:forEach var="item" items="${siderbar_CommentList}">
						<li class="sidebar_li">
							<span class="sidebar_left"><a
								href="details.do?method=get&productId=${item.product.productId}"
								target="_blank">${ item.commentContent }</a> </span>
							<span class="sidebar_right">${ item.user.username }</span>
							<div class="clear"></div>

						</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="favorite">
			<jsp:include page="/collection.do?method=hotCollection"></jsp:include>
			<div id="favorite_title" class="sidebar_title">
				被大家广泛收藏的商品
			</div>
			<div id="sidebar_favoriteList" class="sidebar_list">
				<!-- <ul id="sidebar_favorite_ul" class="sidebar_ul">
					<c:forEach var="item" items="${collectionList}">
						<li class="sidebar_li">
							<span class="sidebar_left"><a
								href="details.do?method=get&productId=${item.productId}"
								target="_blank">${item.productName}…</a> </span>
							<span class="sidebar_right">${item.price }元</span>
							<div class="clear"></div>
						</li>
					</c:forEach>
				</ul>
				 -->
				
				<ul id="sidebar_product_ul" class="sidebar_ul">
					<c:if test="${!empty requestScope.collectionList}">
						<logic:iterate id="product" indexId="reIndex" name="collectionList">
							<logic:equal name="reIndex" value="0">
								<li class="sidebar_li_selected"
									onmouseover="this.className='sidebar_li_selected';"
									onmouseout="this.className='sidebar_li';">
									<span class="num"><%=Integer.parseInt(reIndex.toString()) + 1%>
									</span>
									<a class="url"
										href="details.do?method=get&productId=${product.productId}"
										target="_blank"> <span class="sidebar_item_img"> <img
												align="left" src="${product.productImg}"
												onload="DrawImage(this);" /> </span> <span class="sidebar_left">${product.productName}…</span><span
										class="sidebar_right">${ product.price }元</span> </a>
									<div class="clear"></div>
								</li>
							</logic:equal>
							<logic:notEqual name="reIndex" value="0">
								<li class="sidebar_li"
									onmouseover="this.className='sidebar_li_selected';"
									onmouseout="this.className='sidebar_li';">
									<span class="num"><%=Integer.parseInt(reIndex.toString()) + 1%>
									</span>
									<a href="details.do?method=get&productId=${product.productId}"
										target="_blank"> <span class="sidebar_item_img"> <img
												align="left" src="${product.productImg}"
												onload="DrawImage(this);" /> </span> <span class="sidebar_left">${product.productName}…</span><span
										class="sidebar_right">${ product.price }元</span> </a>
									<div class="clear"></div>
								</li>
							</logic:notEqual>

						</logic:iterate>
					</c:if>
				</ul>
		
			</div>
			
		</div>
	</body>
</html>
