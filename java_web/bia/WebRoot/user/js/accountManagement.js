function checkpwd(){
	
	var newPass=document.getElementById("newPass").value;
	
	
	var conNewPass=document.getElementById("conNewPass").value;
	
	
	var oldPass=document.getElementById("oldPass").value;
	if(oldPass==""){
		show('hide','请填写原先密码！<br/>');
		return false;
	}
	if(newPass==""){
		show('hide','请填写新密码！<br/>');
		return false;
	}

	if(newPass!=conNewPass){
		show('hide','两次密码输入不一致！<br/>');
		return false;
	}
	
	if(newPass.length<6){
		show('hide','新密码长度至少为6！<br/>');
		return false;
	}
		
	var truthBeTold = window.confirm("确定修改密码?");
	if(truthBeTold){
		return true;
	}
	return false;
}
function clickEditEmail(){
	var obj1=document.getElementById("pEmail");
	var obj2=document.getElementById("btnEditEmail");
	var obj3=document.getElementById("btnSureEmail");
	var obj4=document.getElementById("btnCancelEmail");
	var obj5=document.getElementById("txtEmail");
	obj1.style.display="none";   
	obj2.style.display="none"; 
	obj3.style.display="block"; 
	obj4.style.display="block";  
	obj5.style.display="block";
}

function clickSureEmail(){
	var email=document.getElementById("txtEmail").value
	if(email==""){
		show('hide','邮箱不能为空!');
		return false;
	}
	if (email.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1){
		
	}
	else{
		show('hide','邮箱格式不正确!');
		return false;
	}
	
	var obj1=document.getElementById("pEmail");
	var obj2=document.getElementById("btnEditEmail");
	var obj3=document.getElementById("btnSureEmail");
	var obj4=document.getElementById("btnCancelEmail");
	var obj5=document.getElementById("txtEmail");
	
	obj2.style.display="block"; 
	obj3.style.display="none"; 
	obj4.style.display="none";  
	obj5.style.display="none";
	changeEmail();
	obj1.style.display="block";   
}

function clickCancelEmail(){
	var obj1=document.getElementById("pEmail");
	var obj2=document.getElementById("btnEditEmail");
	var obj3=document.getElementById("btnSureEmail");
	var obj4=document.getElementById("btnCancelEmail");
	var obj5=document.getElementById("txtEmail");
	obj5.value=obj1.innerHTML;
	obj1.style.display="block";   
	obj2.style.display="block"; 
	obj3.style.display="none"; 
	obj4.style.display="none";  
	obj5.style.display="none";
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
var getEmailRequest;
function getEmail(){
	getEmailRequest=getHTTPObject();	
	if (!getEmailRequest)
  		alert("Error initializing XMLHttpRequest!");
	//request3.open("GET","../deleteNews.do?categoryId="+<%=categoryId %>+"&index="+index,true);
	getEmailRequest.open("GET","../user.do?method=getEmail",true);
	
	getEmailRequest.onreadystatechange=afterGetEmail;
	getEmailRequest.send(null);
}

function afterGetEmail(){
	
	if(getEmailRequest.readyState==4){
		var emailJson=getEmailRequest.responseText;
		
		emailJson=JSON.parse(emailJson);
		document.getElementById('txtEmail').value = emailJson.email;
		//alert(emailJson.email);
		document.getElementById('pEmail').innerHTML = emailJson.email;
		//clickCancelEmail();		
	}else{
		//alert("request7");
		document.getElementById('txtEmail').value = "获取邮箱失败...";
		document.getElementById('pEmail').innerHTML = "获取邮箱失败...";
	}
}


var changeEmailRequest;
function changeEmail(){
	changeEmailRequest=getHTTPObject();	
	if (!changeEmailRequest)
  		alert("Error initializing XMLHttpRequest!");
	//request3.open("GET","../deleteNews.do?categoryId="+<%=categoryId %>+"&index="+index,true);
	var email=document.getElementById('txtEmail').value;
	
	//alert(email);
	//验证邮件地址是否合法。。
		
	changeEmailRequest.open("GET","../user.do?method=changeEmail&email="+email,true);
	
	changeEmailRequest.onreadystatechange=afterchangeEmail;
	changeEmailRequest.send(null);
}

function afterchangeEmail(){
	
	if(changeEmailRequest.readyState==4){
		var emailJson=changeEmailRequest.responseText;
		
		emailJson=JSON.parse(emailJson);
		document.getElementById('txtEmail').value = emailJson.email;
		document.getElementById('pEmail').innerHTML = emailJson.email;
		
		show('hide','修改邮箱成功！');
		
	}else{
		//alert("request7");
		document.getElementById('txtEmail').value = "获取邮箱失败...";
		document.getElementById('pEmail').innerHTML = "获取邮箱失败...";
	}
}

