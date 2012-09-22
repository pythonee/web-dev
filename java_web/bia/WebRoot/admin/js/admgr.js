
var adLength=0;
var page=0;
var page_items=10;
function adpageselectCallback(page_index, jq){
     
	page=page_index;
	var obj = document.getElementById("adtbody");
    var liObj=obj.getElementsByTagName("tr");
    var i=0;
    for(i=0;i<liObj.length;i++){
    	liObj[i].style.display="none";  
    }
   	page_index++;
	for (i = (page_index - 1) * page_items; i < liObj.length && i < page_index * page_items; i++) {
		liObj[i].style.display="block";  
	}
	
    return false;
}
 
             
function adinitPagination(num_entries,cur_page) {
		
       adLength= num_entries;   
     $("#Pagination").pagination(num_entries, {
         num_edge_entries: 2,
         num_display_entries: 8,
         current_page:cur_page,
         callback: adpageselectCallback,
         items_per_page:page_items
     });
}


function deleteSure(){
 	var a = document.getElementsByName("boxDelete");
 	 for (var i=0; i<a.length; i++)   
     {   
         if (a[i].checked)  
         {   
            var truthBeTold = window.confirm("确定删除所选广告?");
			if(truthBeTold){
				return true;
			}
			return false;
         }   
     }  
 
   //var truthBeTold = window.confirm("确定删除所选广告?");
	//if(truthBeTold){
	//	return true;
	//}
	show('hide','请先选择广告，谢谢！');
	return false;
}

function addSure(){
	var ad_name=document.getElementById("ad_name").value;
	if(ad_name==""){
	
		show('hide','广告名不能够为空！<br/>');
		return false;
	}
	if(ad_name.length>20){
		show('hide','广告名长度不能超过20！<br/>');
		return false;
	}
	var ad_text=document.getElementById("ad_text").value;
	if(ad_text==""){
	
		show('hide','广告描述不能够为空！<br/>');
		return false;
	}
	if(ad_text.length>40){
		show('hide','广告描述长度不能超过40！<br/>');
		return false;
	}
	
	var selectFile=document.getElementById("selectFile").value;
	if(selectFile==""){
	
		show('hide','请选择广告文件！<br/>');
		return false;
	}
	if(selectFile.length>200){
		show('hide','广告文件长度不能超过200！<br/>');
		return false;
	}
	
	var startTime=document.getElementById("startTime").value;
	if(startTime==""){
	
		show('hide','广告投放开始时间不能够为空！<br/>');
		return false;
	}
	if(startTime.length>20){
		show('hide','分类名长度不能超过20！<br/>');
		return false;
	}
	
	var endTime=document.getElementById("endTime").value;
	if(endTime==""){
	
		show('hide','广告投放结束时间不能够为空！<br/>');
		return false;
	}
	if(endTime.length>20){
		show('hide','分类名长度不能超过20！<br/>');
		return false;
	}
	
	var ad_target=document.getElementById("ad_target").value;
	if(ad_target==""){
	
		show('hide','广告链接地址不能够为空！<br/>');
		return false;
	}
	if(ad_target.length>200){
		show('hide','广告链接地址长度不能超过200！<br/>');
		return false;
	}
	
	var truthBeTold = window.confirm("确定增添广告?");
	if(truthBeTold){
		return true;
	}
	return false;
}

function selectPie(){
var pie=document.getElementById("FCF_Pie3D");
pie.style.display="block";
var column=document.getElementById("FCF_Column3D");
column.style.display="none";

}


function selectColumn(){
var pie=document.getElementById("FCF_Pie3D");
pie.style.display="none";
var column=document.getElementById("FCF_Column3D");
column.style.display="block";

}

function showTab(i){
		
	var obj = document.getElementsByTagName("a");
	for(j=0;j<obj.length;j++){
		if(obj[j].className=="tab"){
		obj[j].style.background="url(\"img/tableft1.gif\") no-repeat left top";
		}
	}
	
	var obj3 = document.getElementById("fusionChart");
	var obj2=document.getElementById("addAd");
	var obj1=document.getElementById("adList");

	var tabi=document.getElementById("tab"+i);
	obj1.style.display="none";   
	obj2.style.display="none"; 
	obj3.style.display="none"; 
	if(i==1){
		obj1.style.display="block";  
	}else if(i==2){
		obj2.style.display="block"; 
	}else if(i==3){
		obj3.style.display="block"; 
	}
	
	tabi.style.background="#d5d5d5";
	
}