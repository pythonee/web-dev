var trackLength=0;
var page=0;
var page_items=10;
function trpageselectCallback(page_index, jq){
       
	page=page_index;
	var obj = document.getElementById("tracktbody");
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
 
             
function trinitPagination(num_entries,cur_page) {
       userLength= num_entries;   
     $("#Pagination").pagination(num_entries, {
         num_edge_entries: 2,
         num_display_entries: 8,
         current_page:cur_page,
         callback: trpageselectCallback,
         items_per_page:page_items
     });
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


function selectTimePie(){
var pie=document.getElementById("time_FCF_Pie3D");
pie.style.display="block";
var column=document.getElementById("time_FCF_Column3D");
column.style.display="none";

}


function selectTimeColumn(){
var pie=document.getElementById("time_FCF_Pie3D");
pie.style.display="none";
var column=document.getElementById("time_FCF_Column3D");
column.style.display="block";

}


function showTab(i){
		
	var obj = document.getElementsByTagName("a");
	for(j=0;j<obj.length;j++){
		if(obj[j].className=="tab"){
		obj[j].style.background="url(\"img/tableft1.gif\") no-repeat left top";
		}
	}
	
	var obj1 = document.getElementById("visitFusionChart");
	var obj2=document.getElementById("timeFusionChart");
	var obj3=document.getElementById("trackList");
	
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