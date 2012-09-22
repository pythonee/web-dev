function setNewProduct(pid,pname,pprice,pscore,psource,purl){
		//alert("a");
		var cookieContent=document.cookie;
		 var cookieHeader = "productList=|";  
		 var Then = new Date()  
 			Then.setTime(Then.getTime() + 24*3600000 ) //24小时  
 			var destroyTime =";expires="+ Then.toGMTString()  ;
		if(cookieContent==null||cookieContent.length==0){
	 		document.cookie = "productList=|"+pid+"@"+pname+"@"+pprice+"@"+pscore+"@"+psource+"@"+purl+destroyTime;
		 }else{
			//alert("b");
			
		 	var beginPosition = cookieContent.indexOf(cookieHeader) ;
		 	if(beginPosition=="-1"){
		 		cookieContent=cookieHeader+destroyTime;
		 		beginPosition = cookieContent.indexOf(cookieHeader);
		 	}
		 	var cookieStr=cookieContent.substring(beginPosition + cookieHeader.length);
		 	//alert("b "+cookieStr);
		 	var productList=cookieStr.split("|");
			var i=0;
			//alert("p "+productList.length);
			for(;i<productList.length;i++){
				if(productList[i]!=null){
					var product=productList[i].split("@");  //(pid,pname,pprice,psource,purl)
					if(product[0]==pid){
						 show("hide","对比栏已有同样商品！");
						return false;//已添加
					}
				}
			}
			//alert("c");
			if(productList!=null){
				if(productList.length<=1){
					if(cookieStr.length<2){//对比栏为空
						document.cookie =cookieHeader+ pid+"@"+pname+"@"+pprice+"@"+pscore+"@"+psource+"@"+purl+destroyTime;
					}else{
						document.cookie =cookieHeader+ pid+"@"+pname+"@"+pprice+"@"+pscore+"@"+psource+"@"+purl+"|"+cookieStr;
					}
					
				}else{
					document.cookie =cookieHeader+ pid+"@"+pname+"@"+pprice+"@"+pscore+"@"+psource+"@"+purl+"|"+cookieStr;
				}	
			}else{
				//alert("e");	 			
		 		document.cookie = "productList=|"+pid+"@"+pname+"@"+pprice+"@"+pscore+"@"+psource+"@"+purl+destroyTime;
				
			}
		 }
		 show("hide","添加商品到对比栏成功！");
}

function showAllProduct(){
	//alert(document.cookie);
	//setNewProduct(2,2,3,3,3);
	var cookieContent=document.cookie;
	var cookieHeader = "productList=|";  
	//alert(document.cookie);
	var str="<table id=\"carList\"><tr><td>商品名</td><td>价格</td><td>评分</td><td>来源</td><td>操作</td></tr>";
	var beginPosition = cookieContent.indexOf(cookieHeader) ;
	if(beginPosition!=-1){
		cookieContent=cookieContent.substring(beginPosition + cookieHeader.length);
		//alert(cookieContent);
		//var str;
		if(cookieContent!=null&&cookieContent.length!=0){
			var productList=cookieContent.split("|");
			//alert(productList.length);
			var i=0;
			var carDiv=document.getElementById("car");
			//str="<table id=\"carList\"><tr><td>商品名</td><td>价格</td><td>评分</td><td>来源</td><td>操作</td></tr>";
			for(;i<productList.length;i++){
				//alert(cookieContent);
				if(productList[i].length>2){
					var product=productList[i].split("@");  //(pid,pname,pprice,psource,purl)
					//alert(cookieContent);
					str+="<tr><td>"+product[1]+"</td><td>"+product[2]+"</td><td>"+product[3]+"</td><td><a href=\""+product[5]+"\" target=\"_blank\">"+product[4]+"</a>"+"</td><td><a href=\"#\" onclick=\"deleteProduct("+product[0]+")\">移除</a></td></tr>";
				}
			}
			
			//str+="</table><a href=\"#\" onclick=\"closeCar()\">关闭</a>";
			//carDiv.innerHTML=str;
			//carDiv.style.display="block";
		}else{
			var carDiv=document.getElementById("car");
			//str="<table id=\"carList\"><tr><td>商品名</td><td>价格</td><td>评分</td><td>来源</td><td>操作</td></tr>";
				str+="<tr><td>暂无商品</td></tr>";
			//str+="</table><a href=\"#\" onclick=\"closeCar()\">关闭</a>";
			//carDiv.innerHTML=str;
			//carDiv.style.display="block";
		}
		//showHidecar("hideCar",str);
		//deleteProduct(2);
		//deleteCookie();
		//alert(document.cookie);
	}else{
		str+="<tr><td>暂无商品</td></tr>";
	}
	
	str+="</table><a href=\"#\" onclick=\"closeCar()\">关闭</a><a href=\"#\" onclick=\"deleteCookie()\">清空</a>";	
	//alert(str);
	showHidecar("hideCar",str);
}

function deleteProduct(pid){
	var cookieContent=document.cookie;
	var cookieHeader = "productList=|";  
	
	var beginPosition = cookieContent.indexOf(cookieHeader) ;
	if(beginPosition!=-1){
		cookieContent=cookieContent.substring(beginPosition + cookieHeader.length);
			 	
		if(cookieContent!=null&&cookieContent.length!=0){
			
			var productList=cookieContent.split("|");
			var i=0;
			//alert(productList.length);
			var str="productList=";
			for(;i<productList.length;i++){
				
				var product=productList[i].split("@");  //(pid,pname,pprice,psource,purl)
				if(product[0]!=pid){
					str+="|"+product[0]+"@"+product[1]+"@"+product[2]+"@"+product[3]+"@"+product[4]+"@"+product[5];
					
				}else{
					if(productList.length==1){
						str+="|";
						
					}
				}
			}
			if(productList.length==1){
				
			}
			//str+=productList[productList.length-1];
			var Then = new Date()  
 			Then.setTime(Then.getTime() + 24*3600000 ) //24小时  
 			var destroyTime =";expires="+ Then.toGMTString()  ;
			str+=destroyTime;
			document.cookie=str;
			
		}
	}
	showAllProduct();
}

function deleteCookie(){
	document.cookie="productList=|"+";expires=0";
	showAllProduct();
}

function closeCar(){
	var carDiv=document.getElementById("hideCar");
	carDiv.style.display="none";
}




var carprox;
var carproy;
var carproxc;
var carproyc;
function showHidecar(id,txt) {
	/*--打开--*/
	clearInterval(carprox);
	clearInterval(carproy);
	//clearInterval(proxc);
	//clearInterval(proyc);
	var o = document.getElementById(id);
	o.style.display = "block";
	o.style.width = "1px";
	o.style.height = "1px";
	carprox = setInterval(function () {
		caropenx(o, 600);
	}, 10);
	
	showCar("car",txt);
	
}
function caropenx(o, x) {
	/*--打开x--*/
	var cx = parseInt(o.style.width);
	if (cx < x) {
		o.style.width = (cx + Math.ceil((x - cx) / 5)) + "px";
	} else {
		clearInterval(carprox);
		carproy = setInterval(function () {
			caropeny(o, 400);
		}, 10);
	}
}
function caropeny(o, y) {
	/*--打开y--*/
	var cy = parseInt(o.style.height);
	if (cy < y) {
		o.style.height = (cy + Math.ceil((y - cy) / 5)) + "px";
	} else {
		clearInterval(carproy);
	}
}


function showCar(id,txt){
	
	document.getElementById(id).innerHTML=txt;
	
}
