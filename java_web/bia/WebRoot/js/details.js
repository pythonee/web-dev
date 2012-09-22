function showTab(i){
		
	var obj = document.getElementsByTagName("a");
	for(j=0;j<obj.length;j++){
		if(obj[j].className=="tab"){
		obj[j].style.background="url(\"img/tableft1.gif\") no-repeat left top";
		}
	}
	
	var obj1 = document.getElementById("comments");
	var obj2=document.getElementById("relationProduct");
	//var obj3=document.getElementById("comparePrice");

	var tabi=document.getElementById("tab"+i);
	obj1.style.display="none";   
	obj2.style.display="none"; 
	//obj3.style.display="none"; 
	if(i==1){
		obj1.style.display="block";  
	}else if(i==2){
		obj2.style.display="block"; 
	}//else if(i==3){
	//	obj3.style.display="block"; 
	//}
	
	tabi.style.background="#d5d5d5";
	
}

function showstars(score){
	var starImg="";
	for(i=1;i<score;i++){
		starImg+="<img src=\"images/star_shine.bmp\" />";
	}
	
	for(i=i-1;i<5;i++){
		starImg+="<img src=\"images/star_black.bmp\" />";
	}
	document.getElementById("stars").innerHTML=starImg;
	document.getElementById("score").innerHTML=score;
}

function showLevel(){
	var levelImg="";
	for(i=1;i<6;i=i+1){
		levelImg+="<a href=\"#\"><img src=\"images/"+i+"star.bmp\" onclick=\"commentLevel("+i+")\" /></a>";
	}
	document.getElementById("level").innerHTML=levelImg;
	
}

function getHTTPObject(){
	var request=false;
	try {
  		request = new XMLHttpRequest();
	} catch (trymicrosoft) {
 		 try {
    		request = new ActiveXObject("Msxml2.XMLHTTP");
 		 } catch (othermicrosoft) {
   			 try {
    			  request = new ActiveXObject("Microsoft.XMLHTTP");
    		} catch (failed) {
     			 request = false;
    		}
  		}
	}
	return request;
}

var levelRequest;
function commentLevel(i){
	levelRequest=getHTTPObject();	
	var productId = document.getElementById("productId").value;
	if (!levelRequest)
  		alert("Error initializing XMLHttpRequest!");
	//request3.open("GET","../deleteNews.do?categoryId="+<%=categoryId %>+"&index="+index,true);
	levelRequest.open("GET","details.do?method=scoring"+"&score="+i+"&productId="+productId,true);
	//alert("product.do?method=scoring"+"&score="+i+"&productId="+productId);
	levelRequest.onreadystatechange=afterCommentLevel;
	levelRequest.send(null);
	
}

function afterCommentLevel(){
	if(levelRequest.readyState==4&&levelRequest.status==200){
		var levelStatusJson=levelRequest.responseText;
		levelStatusJson=JSON.parse(levelStatusJson);
		var text=levelStatusJson.status;
		if(text=="ok"){
			show('hide','评分成功，谢谢您！');
			showstars(levelStatusJson.score);
		}
		
	}else{
		//alert("评论等级失败!");
		//document.getElementById('commentul').innerHTML = "评论正在加载……";
	}
}

function collect(){
	var truthBeTold = window.confirm("确定收藏所选商品?");
	if(truthBeTold){
		addCollection();
	}
}

var collectionRequest;
function addCollection(){
	var userId = document.getElementById("userId").value;
	var productId = document.getElementById("productId").value;
	if(userId==2){
		show('hide','请先登录，谢谢！');
		return false;
	}//没登陆的匿名游客
	collectionRequest=getHTTPObject();	
	if (!collectionRequest)
  		alert("Error initializing XMLHttpRequest!");
	//request3.open("GET","../deleteNews.do?categoryId="+<%=categoryId %>+"&index="+index,true);
	collectionRequest.open("GET","collection.do?method=add"+"&productId="+productId+"&userId="+userId,true);
	
	collectionRequest.onreadystatechange=afterAddCollection;
	collectionRequest.send(null);
}


function afterAddCollection(){

	if(collectionRequest.readyState==4){
		var collectionStatusJson=collectionRequest.responseText;
		collectionStatusJson=JSON.parse(collectionStatusJson);
		var text=collectionStatusJson.status;
		
		if(text=="ok"){
			//alert("添加收藏成功");
			show('hide','收藏成功！');
			
			//var obj = document.getElementById("showinfo");
			//obj.style.display="block";
			//setTimeout(function haha(){document.getElementById("showinfo").style.display="none"},3000);
		
		}
		
	}else{
		//alert("request7");
		//document.getElementById('commentul').innerHTML = "评论正在加载……";
	}
}


var commentLength;
var page=0;
var page_items=10;
function pageselectCallback(page_index, jq){
       
	page=page_index;
	var obj = document.getElementById("commentsList");
    var liObj=obj.getElementsByTagName("li");
    var i=0;
    for(i=0;i<liObj.length;i++){
    	liObj[i].style.display="none";  
    }
   	page_index++;
	for (i = (page_index - 1) * 10; i < liObj.length && i < page_index * 10; i++) {
		liObj[i].style.display="block";  
	}
    return false;
}
 
             
function initPagination(num_entries,cur_page) {
       commentLength= num_entries;   
     $("#Pagination").pagination(num_entries, {
         num_edge_entries: 2,
         num_display_entries: 8,
         current_page:cur_page,
         callback: pageselectCallback,
         items_per_page:page_items
     });
}


var commentRequest;
function addComment(){

	var commentStr = document.getElementById("commentStr").value;
	if(commentStr==""){
		show('hide','请输入评论内容！');
		return false;
	}
	commentStr=encodeURI(commentStr);
	commentStr=encodeURI(commentStr);
	var userId = document.getElementById("userId").value;
	var productId = document.getElementById("productId").value;
	
	commentRequest=getHTTPObject();	
	if (!commentRequest)
  		alert("Error initializing XMLHttpRequest!");
	//request3.open("GET","../deleteNews.do?categoryId="+<%=categoryId %>+"&index="+index,true);
	commentRequest.open("GET","comment.do?method=add&commentStr="+commentStr+"&productId="+productId+"&userId="+userId,true);
	
	commentRequest.onreadystatechange=afterAddComment;
	commentRequest.send(null);
}

function afterAddComment(){
	var obj = document.getElementById("commentul");
	var userId = document.getElementById("userId").value;
	if(commentRequest.readyState==4){
		var commentJson=commentRequest.responseText;
		commentJson=JSON.parse(commentJson);
		var liString="<li class=\"commentItems\">";
		liString+="<div class=\"userProfile\">";
		liString+="<span class=\"comment-user-img\"><a href=\"\"><img align=\"left\" src=\"images/user.bmp\" /> </a> </span>";

		liString+="<span class=\"comment-user-name\"> <a href=\"#\">"+commentJson.userName+"</a> </span>";
		liString+="</div>";
		liString+="<div class=\"comment\">";
		liString+="<div class=\"comment-meta-in-line\">";
		liString+="<span class=\"commentTime\">评论时间：" +commentJson.commentTime+"</span>";
		liString+="<span class=\"btnAddFriend\"> <a	onclick=\"addFriend("+userId+")\">加为好友 </a> </span>";
		liString+="<div class=\"clear\"></div>";
		liString+="</div>";
		liString+="<div class=\"comment-content\">"+commentJson.commentStr+"</div>";
		liString+="</div></li>";
	
		
	
													
		//alert(obj.innerHTML);
		obj.innerHTML=liString+obj.innerHTML;
		commentLength++;
		document.getElementById("commentStr").value="";
		initPagination(commentLength,0);
		
	}else{
		//alert("request7");
		//document.getElementById('commentul').innerHTML = "评论正在加载……";
	}
}


function   DrawImage(ImgD){ 
	var   image=new   Image(); 
	var   intWidth   =   68; 
	image.src=ImgD.src; 
	if(image.width> intWidth) 
	{     
		ImgD.width=intWidth; 
		ImgD.height=(image.height*intWidth)/image.width; 
	} 
	else 
	{ 
		ImgD.width=image.width; 
		ImgD.height=image.height; 
	} 
} 


var addFriendRequest=false;
function addFriend(friendId){
	var userId = document.getElementById("userId").value;
	if(userId==2){
		show('hide','请先登录，谢谢！');
		return false;
	}//没登陆的匿名游客
	
	addFriendRequest=getHTTPObject();	
	if (!addFriendRequest)
  		alert("Error initializing XMLHttpRequest!");
	//request3.open("GET","../deleteNews.do?categoryId="+<%=categoryId %>+"&index="+index,true);
	addFriendRequest.open("GET","friend.do?method=add&userId="+userId+"&friendId="+friendId,true);
	
	addFriendRequest.onreadystatechange=afterAddFriend;
	addFriendRequest.send(null);
	
}


function afterAddFriend(){
	if(addFriendRequest.readyState==4&&addFriendRequest.status==200){
		var ok=addFriendRequest.responseText;
		if(ok=="ok"){
			show('hide','好友添加成功！');
		}else if(ok=="contain"){
			show('hide','哟呵，你们之前就已经是好友！<br/>');
		}else{
			show('hide','服务器忙，请稍后再添加好友！<br/>');
		}
		
	}else{
		
	}
}