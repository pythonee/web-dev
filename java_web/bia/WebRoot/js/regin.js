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

//ajax密码强度验证
function allNumber(v)
	{
		var reg = /^[0-9]*$/;
		if(reg.test(v))
		{	
			return true;
		}
		return false;
	}
	
	function CharMode(iN){
		if(iN>=48 && iN<=57)//数字
			return 1;
		if(iN>=65 && iN<=90)//大写字母
			return 2;
		if(iN>=97 && iN<=122)//小写
			return 4;
		else
			return 8;//特殊字符
	}

	//计算出当前密码当中一共有多少种模式
	function bitTotal(num){
		var modes=0;
		for(i=0;i<4;i++){
			if(num&1)
				modes++;
			num >>=1;
		}
		return	modes;
	}
		
	//返回密码的强度级别
	function checkStrong(sPW){
		if(sPW.length<6)
			return 0;//密码太短 
		var Modes=0;
		for(i=0;i<sPW.length;i++){
		//测试每一个字符的类别并统计一共有多少种模式.
		Modes|=CharMode(sPW.charCodeAt(i));
		}
		return bitTotal(Modes);
	}

	
	function showStrongPic()
	{
		var v = document.getElementById('password').value;
		var m = checkStrong(v);
		if(m < 2)
		{
			document.getElementById('lowPic').style.display="";
			document.getElementById('midPic').style.display="none";
			document.getElementById('highPic').style.display="none";
		}
		else if(m==2)
		{
			document.getElementById('lowPic').style.display="none";
			document.getElementById('midPic').style.display="";
			document.getElementById('highPic').style.display="none";
		}
		else 
		{
			document.getElementById('lowPic').style.display="none";
			document.getElementById('midPic').style.display="none";
			document.getElementById('highPic').style.display="";
		}
	}
	


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
			document.getElementById('name_re').innerHTML='<img src=images/check_error.gif>';
			document.getElementById('name_re_m').innerHTML='<span class=msg>该用户名已经存在！</span>';
			name_msg(0);
			return false;
		}
		else{
			document.getElementById('name_re').innerHTML='<img src=images/check_right.gif>';
			document.getElementById('name_re_m').innerHTML='<span class=msg2>可以注册！</span>';
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

//密码是否为空检测
function password_check(){
	var u_pass=document.getElementById('u_pass').value;
	if(u_pass=="" || u_pass.length<6){
		document.getElementById('pass_re').innerHTML='<img src=images/check_error.gif>';
		return false;
	}
	else{
		document.getElementById('pass_re').innerHTML='<img src=images/check_right.gif>';
		return true;
	}
}

//确认密码检测
function pass_re(){
	var u_pass=document.getElementById('u_pass').value;
	var pass_re=document.getElementById('u_pass_re').value;
	if (u_pass == ""){
		document.getElementById('pass_re_re').innerHTML='<img src=images/check_error.gif>';
		document.getElementById('pass_re_re_m').innerHTML='<span class=msg>密码不能为空，请重新输入</span>';
		return false;
	}
	else if(u_pass != pass_re){
		document.getElementById('pass_re_re').innerHTML='<img src=images/check_error.gif>';
		document.getElementById('pass_re_re_m').innerHTML='<span class=msg>两次密码不一致，请重新输入</span>';
		return false;
	}
	else{
		document.getElementById('pass_re_re').innerHTML='<img src=images/check_right.gif>';
		document.getElementById('pass_re_re_m').innerHTML='<span class=msg2>填写正确</span>';
		return true;
	}
}


//邮箱格式验证
function isEmail() {
	var u_mail=document.getElementById('u_mail').value;
	if (u_mail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1){
		document.getElementById('mail_re').innerHTML='<img src=images/check_right.gif>';
		document.getElementById('mail_re_m').innerHTML='<span class=msg> </span>';
		email_ajax(u_mail);
	}
	else{
		document.getElementById('mail_re').innerHTML='<img src=images/check_error.gif>';
		document.getElementById('mail_re_m').innerHTML='<span class=msg>请输入正确的邮箱地址</span>';
		return false;
	}
}

function email_ajax(u_mail){
	var email=u_mail;
	var url="ajax.asp?action=email&mail="+ escape(email); 
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
			document.getElementById('mail_re').innerHTML='<img src=images/check_error.gif>';
			document.getElementById('mail_re_m').innerHTML='<span class=msg>该邮箱已被使用，请换一个</span>';
			mail_msg(0);
			return false;
		}
		else{
			document.getElementById('mail_re').innerHTML='<img src=images/check_right.gif>';
			document.getElementById('mail_re_m').innerHTML='<span class=msg2>可以使用</span>';
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
		//alert("名字不能为空");
		return false;
	}
	if (name_use==true){
	//alert("名字已存在，重新输入");
	return false;
	}
	if (password_check()==false){
		//alert("密码必须填写");
		return false;
	}
	if (pass_re()==false){
    	//alert("确认密码错误");
		return false;
	}
	if (isEmail()==false){
		//alert("邮箱地址为空或者错误");
	return false;
	}
	if (mail_use==true){
		//alert("邮箱已经存在，重新输入一个");
		return false;
	}
	return checkMathCaptcha();
	//document.form2.submit();
	//return true;
}
