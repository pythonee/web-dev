function isNull(id){
	var str = document.getElementById(id).value;
	if (str==""){
		document.getElementById(id+'_re').innerHTML='<img src=../images/check_error.gif>';
		document.getElementById(id+'_re_m').innerHTML='<span class=msg>不能为空</span>';
		return false;
	}
	else{
		document.getElementById(id+'_re').innerHTML='<img src=../images/check_right.gif>';
		document.getElementById(id+'_re_m').innerHTML='<span class=msg> </span>';
		return true;
	}
}