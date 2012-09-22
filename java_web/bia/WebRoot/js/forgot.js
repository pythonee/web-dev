var alpha3=new Array(0,1,2,3,4,5,6,7,8,9);
var acts=new Array("+","-");

//创建ajax对象
var name_re = false; 
   function name_xml()
   {
   try { 
     name_re = new XMLHttpRequest(); 
   } catch (trymicrosoft) { 
     try { 
       name_re = new ActiveXObject("Msxml2.XMLHTTP"); 
     } catch (othermicrosoft) { 
       try { 
         name_re = new ActiveXObject("Microsoft.XMLHTTP"); 
       } catch (failed) { 
         name_re = false; 
       }   
     } 
   } 
   if (!name_re) 
     alert("Error initializing XMLHttpRequest!"); 
}

var name_use;
var mail_use;


//ajax验证码验证
String.prototype.trim= function()  
{  
    // 用正则表达式将前后空格用空字符串替代。  
    return this.replace(/(^\s*)|(\s*$)/g, "");  
}


var captcha3;
function getMathCaptcha(){
	var a=alpha3[Math.round(Math.random()*9)];
	var act1= acts[Math.round(Math.random()*1)];	
	var b=alpha3[Math.round(Math.random()*9)];
	var act2= acts[Math.round(Math.random()*1)];
	var c=alpha3[Math.round(Math.random()*9)];
	var act3= acts[Math.round(Math.random()*1)];
	var d=alpha3[Math.round(Math.random()*9)];
	
	//var act4= acts[Math.round(Math.random()*1)];
	//var e=alpha2[Math.round(Math.random()*25)];
	//var act5= acts[Math.round(Math.random()*1)];
	//var f=alpha2[Math.round(Math.random()*25)];	
	//验证码规则：+为并集，-为差集
	if(act1=="+"){captcha3=a+b;}else if(act1=="-"){captcha3=a-b; }
	if(act2=="+"){captcha3=captcha3+c;}else if(act2=="-"){captcha3=captcha3-c; }
	if(act3=="+"){captcha3=captcha3+d;}else if(act3=="-"){captcha3=captcha3-d; }
	//if(act4=="+"){captcha=captcha|e;}else if(act4=="-"){captcha=captcha&(~e); }
	//if(act5=="+"){captcha=captcha|f;}else if(act5=="-"){captcha=captcha&(~f); }
	//alert(a+act1+b+act2+c+act3+d+act4+e+act5+f+"="+captcha);
	//captcha3=a+b+c+d;
	//alert(captcha3);
	var mathCaptchaHtml="";
	
	mathCaptchaHtml+="<img src=\"images/captcha/"+a+".bmp\">";
	mathCaptchaHtml+="<img src=\"images/captcha/"+act1+".bmp\">";
	mathCaptchaHtml+="<img src=\"images/captcha/"+b+".bmp\">";
	mathCaptchaHtml+="<img src=\"images/captcha/"+act2+".bmp\">";
	mathCaptchaHtml+="<img src=\"images/captcha/"+c+".bmp\">";
	mathCaptchaHtml+="<img src=\"images/captcha/"+act3+".bmp\">";
	mathCaptchaHtml+="<img src=\"images/captcha/"+d+".bmp\">";
	mathCaptchaHtml+="<img src=\"images/captcha/=.bmp\">";
	//captcha="<img src=\"/images/"+a+".bmp\">";
	//captcha="<img src=\"/images/"+a+".bmp\">";
	document.getElementById('imgMathCaptcha').innerHTML=mathCaptchaHtml;
}


function checkMathCaptcha(){
	var captchaString=document.getElementById('txtMathCaptcha').value;
 	if (captchaString.length!=0){	
 		 if (captchaString.trim()!=""){
			if(captcha3==captchaString){
				document.getElementById('CheckCode_re').innerHTML='<img src=images/check_right.gif>';
				return true;
			}
 		 }
	}
	document.getElementById('CheckCode_re').innerHTML='<img src=images/check_error.gif>';
	getMathCaptcha();
	return false;
}



//ajax用户名验证
function isName(){
	var u_name = document.getElementById('username').value;
	if (u_name==""){
		document.getElementById('name_re').innerHTML='<img src=images/check_error.gif>';
		document.getElementById('name_re_m').innerHTML='<span class=msg>用户名不能为空</span>';
		return false;
	}
	else{
		document.getElementById('name_re').innerHTML='<img src=images/check_right.gif>';
		document.getElementById('name_re_m').innerHTML='<span class=msg> </span>';
		user_ajax(u_name)
	}
}

function user_ajax(u_name){
	var name=u_name;
	var url="user.do?method=findUser&username="+ escape(name); 
	name_xml();
	name_re.open("GET", url, true); 
	name_re.setRequestHeader("content-type","text/xml");
	name_re.onreadystatechange = name_requst; 
	name_re.setRequestHeader("If-Modified-Since","0");
	name_re.send(null); 
}

function name_requst(){
	if(name_re.readyState==4 && name_re.status==200)//返回完成
	{
		var msg=name_re.responseText;
		if (msg=="yes"){
			document.getElementById('name_re').innerHTML='<img src=images/check_right.gif>';
			document.getElementById('name_re_m').innerHTML='<span class=msg> </span>';
			name_msg(0);
			return false;
		}
		else{
			document.getElementById('name_re').innerHTML='<img src=images/check_error.gif>';
			document.getElementById('name_re_m').innerHTML='<span class=msg>该用户不存在！</span>';
			name_msg(1);
			return true;
		}
	}
}
function name_msg(n){
	var n=n;
	if(n==0){
		name_use=true;
	}
	else{
		name_use=false;
	}
}

//匹配用户
function isEmail() {
	var username=document.getElementById('username').value;
	var u_mail=document.getElementById('u_mail').value;
	if (u_mail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1){
		document.getElementById('mail_re').innerHTML='<img src=images/check_right.gif>';
		document.getElementById('mail_re_m').innerHTML='<span class=msg> </span>';
		match_user(username,u_mail);
	}
	else{
		document.getElementById('mail_re').innerHTML='<img src=images/check_error.gif>';
		document.getElementById('mail_re_m').innerHTML='<span class=msg>请输入正确的邮箱格式</span>';
		return false;
	}
}

function match_user(username,u_mail){
	var email=u_mail;
	var url="user.do?method=matchUser&username="+ escape(username)+"&email="+escape(email); 
     name_xml();
     name_re.open("GET", url, true); 
     name_re.setRequestHeader("content-type","text/xml");
     name_re.onreadystatechange = mail_requst; 
	 name_re.setRequestHeader("If-Modified-Since","0");
     name_re.send(null); 
}

function mail_requst(){
	if(name_re.readyState==4 && name_re.status==200)//返回完成
	{
		var msg=name_re.responseText;
		if (msg=="yes"){
			document.getElementById('mail_re').innerHTML='<img src=images/check_right.gif>';
			document.getElementById('mail_re_m').innerHTML='<span class=msg> </span>';
			mail_msg(0);
			return false;
		}
		else{
			document.getElementById('mail_re').innerHTML='<img src=images/check_error.gif>';
			document.getElementById('mail_re_m').innerHTML='<span class=msg>匹配错误</span>';
			mail_msg(1);
			return true;
		}
	}
}
function mail_msg(n){
	var n=n;
	if(n==0){
		mail_use=true;
	}
	else{
		mail_use=false; 
	}
}

//全表单提交验证
function tijiao(){
	if (isName()==false){
		return false;
	}
	if (name_use==false){
		return false;
	}
	if (mail_use==false){
		return false;
	}
	return checkMathCaptcha();
}
