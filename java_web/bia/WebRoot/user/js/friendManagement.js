var friendLength;
var page=0;
var page_items=10;
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

var request;
function getFriendByPage(page){
	request=getHTTPObject();	
	if (!request)
  		alert("Error initializing XMLHttpRequest!");
	//request3.open("GET","../deleteNews.do?categoryId="+<%=categoryId %>+"&index="+index,true);
	request.open("GET","../friend.do?method=getFriendListByPage&page="+page,true);
	
	request.onreadystatechange=afterGetFriendByPage;
	request.send(null);
}


function afterGetFriendByPage(){
	if(request.readyState==4){
		var friendJson=request.responseText;
		friendJson=JSON.parse(friendJson);
		var friendTableBody="";;
		for(var i=0;i<friendJson.userList.length;++i){
			friendTableBody+="<tr class=\"user\">";
			friendTableBody+="<td class=\"username\">"+friendJson.userList[i].username+"</td><td class=\"email\">"+friendJson.userList[i].email+"</td><td class=\"btn\"><input class=\"btnDelete\" type=\"button\" onClick=\"deleteFriend("+friendJson.userList[i].relationId+")\" value=\" \"></td>";
			friendTableBody+="</tr>";
		}
		
		document.getElementById('friendTablebody').innerHTML = friendTableBody;
		//$('#friendTableBody').empty().append("bkb")
		
	}else{
		//alert("request7");
		document.getElementById('friendTablebody').innerHTML = "好友正在加载……";
	}
}


var friendRequest;
function deleteFriend(relationId){
	var truthBeTold = window.confirm("确定删除所选好友?");
	if(truthBeTold){
		friendRequest=getHTTPObject();	
		if (!friendRequest)
	  		alert("Error initializing XMLHttpRequest!");
		friendRequest.open("GET","../friend.do?method=delete&relationId="+relationId+"&page="+page,true);
		friendRequest.onreadystatechange=afterDeleteFriend;
		friendRequest.send(null);
	}
}

 
function afterDeleteFriend(){
	if(friendRequest.readyState==4){
		//alert(friendRequest.responseText);
		var friendJson=friendRequest.responseText;
		friendJson=JSON.parse(friendJson);
		var friendTableBody="";
		friendLength=friendJson.length;
		//alert("request2");
		//////////////////////////////////////////////////////
		//initPagination(friendLength);
		//alert("request3");
		if(friendLength/page_items<page){page=page-1;}
		//pageselectCallback(page,null);
		initPagination(friendLength,page);
		//pageselectCallback(page,null);
		/////////////////////////////////////////////
		for(var i=0;i<friendJson.userList.length;++i){
			friendTableBody+="<tr class=\"user\">";
			friendTableBody+="<td class=\"username\">"+friendJson.userList[i].username+"</td><td class=\"email\">"+friendJson.userList[i].email+"</td><td class=\"btn\"><input class=\"btnDelete\" type=\"button\" onClick=\"deleteFriend("+friendJson.userList[i].relationId+")\" value=\"删除\"></td>";
			friendTableBody+="</tr>";
		}
		
		document.getElementById('friendTablebody').innerHTML = friendTableBody;
		show('hide','删除好友成功！');
	}else{
		//alert("request1");
		document.getElementById('friendTablebody').innerHTML = "好友正在加载……";
	}
}



var lengthRequest;
function getFriendLength(){
	lengthRequest=getHTTPObject();	
	if (!lengthRequest)
  		alert("Error initializing XMLHttpRequest!");
	//request3.open("GET","../deleteNews.do?categoryId="+<%=categoryId %>+"&index="+index,true);
	lengthRequest.open("GET","../friend.do?method=getFriendLength",true);
	
	lengthRequest.onreadystatechange=afterGetFriendLength;
	lengthRequest.send(null);
}

function afterGetFriendLength(){
	if(lengthRequest.readyState==4){
		var friendJson=lengthRequest.responseText;
		//alert(lengthRequest.responseText);	
		friendJson=JSON.parse(friendJson);
		friendLength=friendJson.length;
		//alert(friendLength);
		initPagination(friendLength,page);
		//alert(lengthRequest.responseText);		
	}else{
		document.getElementById('friendTablebody').innerHTML = "好友正在加载……";
	}
}
function pageselectCallback(page_index, jq){
				//$('#friendList').empty().append("好友列表"+(page_index+1));
				//异步请求数据
				page=page_index;
              	getFriendByPage(page_index);
                return false;
            }
           
            /** 
             * Callback function for the AJAX content loader.
             */
             
function initPagination(num_entries,cur_page) {
               //var num_entries = $('#hiddenresult div.result').length;
               //var num_entries =200;
                // Create pagination element
     
     $("#Pagination").pagination(num_entries, {
         num_edge_entries: 2,
         num_display_entries: 8,
         current_page:cur_page,
         callback: pageselectCallback,
         items_per_page:page_items
     });
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
	addFriendRequest.open("GET","../friend.do?method=add&userId="+userId+"&friendId="+friendId,true);
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
