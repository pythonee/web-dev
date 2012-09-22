
var prox;
var proy;
var proxc;
var proyc;
function show(id,txt) {
	/*--打开--*/
	clearInterval(prox);
	clearInterval(proy);
	//clearInterval(proxc);
	//clearInterval(proyc);
	var o = document.getElementById(id);
	o.style.display = "block";
	o.style.width = "1px";
	o.style.height = "1px";
	prox = setInterval(function () {
		openx(o, 350);
	}, 10);
	showResult(id,txt);
}
function openx(o, x) {
	/*--打开x--*/
	var cx = parseInt(o.style.width);
	if (cx < x) {
		o.style.width = (cx + Math.ceil((x - cx) / 5)) + "px";
	} else {
		clearInterval(prox);
		proy = setInterval(function () {
			openy(o, 200);
		}, 10);
	}
}
function openy(o, y) {
	/*--打开y--*/
	var cy = parseInt(o.style.height);
	if (cy < y) {
		o.style.height = (cy + Math.ceil((y - cy) / 5)) + "px";
	} else {
		clearInterval(proy);
	}
}

//关闭
/*function closeed(id) {
	document.getElementById('infoWIN').innerHTML='';
	clearInterval(prox);
	clearInterval(proy);
	clearInterval(proxc);
	clearInterval(proyc);
	var o = document.getElementById(id);
	if (o.style.display == "block") {
		proyc = setInterval(function () {
			closey(o);
		}, 10);
	}
}
function closey(o) {
	var cy = parseInt(o.style.height);
	if (cy > 0) {
		o.style.height = (cy - Math.ceil(cy / 5)) + "px";
	} else {
		clearInterval(proyc);
		proxc = setInterval(function () {
			closex(o);
		}, 10);
	}
}
function closex(o) {
	var cx = parseInt(o.style.width);
	if (cx > 0) {
		o.style.width = (cx - Math.ceil(cx / 5)) + "px";
	} else {
		clearInterval(proxc);
		o.style.display = "none";
	}
}
*/


//收藏成功
var showSec=2;
function showResult(id,txt){
	document.getElementById('infoWIN').style.display = "block";
	if(showSec>0){
	document.getElementById('infoWIN').innerHTML=txt+showSec+'秒后自动关闭';
	showSec--;
	window.setTimeout(function(){ showResult('"+hide+"',txt);},1000);
	}
	else {
	document.getElementById('infoWIN').style.display = "none";
	
	showSec=2;
	//closeed('hide');
	}
}