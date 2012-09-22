var collectionLength;
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
function getCollectionByPage(page){
	request=getHTTPObject();	
	if (!request)
  		alert("Error initializing XMLHttpRequest!");
	request.open("GET","../collection.do?method=getCollectionListByPage&page="+page,true);
	
	request.onreadystatechange=afterGetCollectionByPage;
	request.send(null);
}


function afterGetCollectionByPage(){
	if(request.readyState==4){
		var collectionJson=request.responseText;
		collectionJson=JSON.parse(collectionJson);
		//alert(collectionJson);
		var collectionTableBody="";
		for(var i=0;i<collectionJson.collectionList.length;++i){
			collectionTableBody+="<tr class=\"collection\">";
			collectionTableBody+="<td class=\"productName\"><a href=\"/bia/details.do?productId="+collectionJson.collectionList[i].productId+"\">"+collectionJson.collectionList[i].productName+"</a></td><td class=\"btn\"><input class=\"btnDelete\" type=\"button\" onClick=\"deleteCollection("+collectionJson.collectionList[i].collectionId+")\" value=\" \"></td>";
			collectionTableBody+="</tr>";
		}
		
		document.getElementById('collectionTablebody').innerHTML = collectionTableBody;
		//$('#friendTableBody').empty().append("bkb")
		
	}else{
		//alert("request7");
		document.getElementById('collectionTablebody').innerHTML = "收藏商品正在加载……";
	}
}


var collectionRequest;
function deleteCollection(collectionId){
	var truthBeTold = window.confirm("确定删除所选收藏商品?");
	if(truthBeTold){
		collectionRequest=getHTTPObject();	
		if (!collectionRequest)
	  		alert("Error initializing XMLHttpRequest!");
		collectionRequest.open("GET","../collection.do?method=delete&collectionId="+collectionId+"&page="+page,true);
		collectionRequest.onreadystatechange=afterDeleteCollection;
		collectionRequest.send(null);
	}
}

 
function afterDeleteCollection(){
	if(collectionRequest.readyState==4){
		//alert(friendRequest.responseText);
		var collectionJson=collectionRequest.responseText;
		collectionJson=JSON.parse(collectionJson);
		var collectionTableBody="";
		collectionLength=collectionJson.length;
		//alert("request2");
		//////////////////////////////////////////////////////
		//initPagination(friendLength);
		//alert("request3");
		if(collectionLength/page_items<page){page=page-1;}
		//pageselectCallback(page,null);
		initPagination(collectionLength,page);
		//pageselectCallback(page,null);
		/////////////////////////////////////////////
		for(var i=0;i<collectionJson.collectionList.length;++i){
			collectionTableBody+="<tr class=\"collection\">";
			collectionTableBody+="<td class=\"productName\">"+collectionJson.collectionList[i].productName+"</td><td class=\"btn\"><input class=\"btnDelete\" type=\"button\" onClick=\"deleteCollection("+collectionJson.collectionList[i].collectionId+")\" value=\" \"></td>";
			collectionTableBody+="</tr>";
		}
		
		document.getElementById('collectionTablebody').innerHTML = collectionTableBody;
		show('hide','删除收藏商品成功！');
		//$('#friendTableBody').empty().append("bkb")
	}else{
		//alert("request1");
		document.getElementById('collectionTablebody').innerHTML = "收藏商品正在加载……";
	}
}



var lengthRequest;
function getCollectionLength(){
	lengthRequest=getHTTPObject();	
	if (!lengthRequest)
  		alert("Error initializing XMLHttpRequest!");
	//request3.open("GET","../deleteNews.do?categoryId="+<%=categoryId %>+"&index="+index,true);
	lengthRequest.open("GET","../collection.do?method=getCollectionLength",true);
	
	lengthRequest.onreadystatechange=afterGetCollectionLength;
	lengthRequest.send(null);
}

function afterGetCollectionLength(){
	if(lengthRequest.readyState==4){
		var collectionJson=lengthRequest.responseText;
		//alert(lengthRequest.responseText);	
		collectionJson=JSON.parse(collectionJson);
		collectionLength=collectionJson.length;
		//alert(friendLength);
		initPagination(collectionLength,page);
		//alert(lengthRequest.responseText);		
	}else{
		document.getElementById('collectionTablebody').innerHTML = "收藏商品正在加载……";
	}
}
function pageselectCallback(page_index, jq){
				//$('#friendList').empty().append("好友列表"+(page_index+1));
				//异步请求数据
				page=page_index;
              	getCollectionByPage(page_index);
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
            
