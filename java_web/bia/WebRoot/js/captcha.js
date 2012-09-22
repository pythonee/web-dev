var alpha3=new Array(0,1,2,3,4,5,6,7,8,9);
var alpha2=new Array("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
var alpha1=new Array(1,2,4,8,16,32,64);
var acts=new Array("+","-");
var captcha1=0;
function getJHCaptcha(){
	var a=alpha1[Math.round(Math.random()*6)];
	var act1= acts[Math.round(Math.random()*1)];	
	var b=alpha1[Math.round(Math.random()*6)];
	var act2= acts[Math.round(Math.random()*1)];
	var c=alpha1[Math.round(Math.random()*6)];
	var act3= acts[Math.round(Math.random()*1)];
	var d=alpha1[Math.round(Math.random()*6)];
	var act4= acts[Math.round(Math.random()*1)];
	var e=alpha1[Math.round(Math.random()*6)];
	var act5= acts[Math.round(Math.random()*1)];
	var f=alpha1[Math.round(Math.random()*6)];	
	//验证码规则：+为并集，-为差集
	if(act1=="+"){captcha=a|b;}else if(act1=="-"){captcha=a&(~b); }
	if(act2=="+"){captcha=captcha|c;}else if(act2=="-"){captcha=captcha&(~c); }
	if(act3=="+"){captcha=captcha|d;}else if(act3=="-"){captcha=captcha&(~d); }
	if(act4=="+"){captcha=captcha|e;}else if(act4=="-"){captcha=captcha&(~e); }
	if(act5=="+"){captcha=captcha|f;}else if(act5=="-"){captcha=captcha&(~f); }
	alert(a+act1+b+act2+c+act3+d+act4+e+act5+f+"="+captcha);
	document.getElementById('imgCaptcha').innerHTML=alpha2[a]+act1+alpha2[b]+act2+alpha2[c]+act3+alpha2[d]+act4+alpha2[e]+act5+alpha2[f];
	//$('#imgCaptcha').empty().append("好友列表"+(page_index+1));
}

var captcha2;
function getAlphaCaptcha(){
	var a=alpha2[Math.round(Math.random()*25)];
	//var act1= acts[Math.round(Math.random()*1)];	
	var b=alpha2[Math.round(Math.random()*25)];
	//var act2= acts[Math.round(Math.random()*1)];
	var c=alpha2[Math.round(Math.random()*25)];
	//var act3= acts[Math.round(Math.random()*1)];
	var d=alpha2[Math.round(Math.random()*25)];
	//var act4= acts[Math.round(Math.random()*1)];
	//var e=alpha2[Math.round(Math.random()*25)];
	//var act5= acts[Math.round(Math.random()*1)];
	//var f=alpha2[Math.round(Math.random()*25)];	
	//验证码规则：+为并集，-为差集
	//if(act1=="+"){captcha=a|b;}else if(act1=="-"){captcha=a&(~b); }
	//if(act2=="+"){captcha=captcha|c;}else if(act2=="-"){captcha=captcha&(~c); }
	//if(act3=="+"){captcha=captcha|d;}else if(act3=="-"){captcha=captcha&(~d); }
	//if(act4=="+"){captcha=captcha|e;}else if(act4=="-"){captcha=captcha&(~e); }
	//if(act5=="+"){captcha=captcha|f;}else if(act5=="-"){captcha=captcha&(~f); }
	//alert(a+act1+b+act2+c+act3+d+act4+e+act5+f+"="+captcha);
	captcha2=a+b+c+d;
	var captchaHtml="";
	captchaHtml+="<img src=\"images/captcha/"+a+".bmp\">";
	captchaHtml+="<img src=\"images/captcha/"+b+".bmp\">";
	captchaHtml+="<img src=\"images/captcha/"+c+".bmp\">";
	captchaHtml+="<img src=\"images/captcha/"+d+".bmp\">";
	//captcha="<img src=\"/images/"+a+".bmp\">";
	//captcha="<img src=\"/images/"+a+".bmp\">";
	document.getElementById('imgAlphaCaptcha').innerHTML=captchaHtml;
}

function checkAlphaCaptcha(){
	var captchaString=document.getElementById('txtAlphaCaptcha').value;
	alert("验证码输入");
 	alert(captchaString+0);
 	if (captchaString.length!=0){
 	
 		 if (captchaString.trim()!=""){
 		 	
			if(captcha2==captchaString){
				alert("验证码输入正确");
				return true;
			}
 		 }
	}
	alert("验证码输入错误");
	getAlphaCaptcha();
	return false;
}

String.prototype.trim= function()  
{  
    // 用正则表达式将前后空格  
    // 用空字符串替代。  
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
	//alert("验证码输入");
 	//alert(captchaString+0);
 	if (captchaString.length!=0){
 	
 		 if (captchaString.trim()!=""){
 		 	//alert(captcha3+"  "+captchaString);
			if(captcha3==captchaString){
				//salert("验证码输入正确");
				return true;
			}
 		 }
	}
	alert("验证码输入错误");
	getMathCaptcha();
	return false;
}

