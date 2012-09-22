var startTime;
var endTime;
var time;
var xmlHttpRequest;
var pageName;
var userName;

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
function loadPage() {
	//alert("开始1");
	var now = new Date();
	var day = now.getDate();
	var hour = now.getHours();
	var minute = now.getMinutes();
	var second = now.getSeconds();
	startTime = hour * 3600 + minute * 60 + second;
	//alert("结束1");
}
function getPageName() {
	var strUrl = location.href;
	var arrUrl = strUrl.split("/");
	var strPage = arrUrl[arrUrl.length - 1];

	return strPage;
}
function unloadPage() {
	//alert("开始2");
	var now = new Date();
	var day = now.getDate();
	var hour = now.getHours();
	var minute = now.getMinutes();
	var second = now.getSeconds();
	endTime =hour * 3600 + minute * 60 + second;
	time = endTime - startTime;      
               
    //1.创建XMLHttpRequest组建   
	xmlHttpRequest = getHTTPObject();
	if (!xmlHttpRequest)
  		alert("Error initializing XMLHttpRequest!");
	userName =document.getElementById("userName1").value;
	//alert(userName);
	userName=encodeURI(userName);
	userName=encodeURI(userName);
	pageName=getPageName();
	var url = "track.do?method=insertTrack&userName=" + userName + "&time="+time+ "&pageName=" + pageName ;             
    //2.设置回调函数   
	xmlHttpRequest.onreadystatechange = trackback;              
    //3.初始化XMLHttpRequest组建   
	xmlHttpRequest.open("GET", url, true);              
    //4.发送请求   
	xmlHttpRequest.send(null);
	//alert(userName+" "+pageName+" "+time);
	//alert("结束2");
}

function trackback()  
{  
  /*if(xmlHttpRequest.readystate == 4) //successful  
  {  
    if(xmlHttpRequest.status==200) //OK  
    {  
      alert("successful");  
    }  
    else
    {  
      alert("error!");  
    }  
  }  
  else  
  {  
    alert("failed");  
  } 
  */ 
}  
