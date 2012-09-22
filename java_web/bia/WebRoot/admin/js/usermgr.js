
var userLength=0;
var page=0;
var page_items=10;
function uspageselectCallback(page_index, jq){
       
	page=page_index;
	var obj = document.getElementById("usertbody");
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
 
             
function usinitPagination(num_entries,cur_page) {
       userLength= num_entries;   
     $("#Pagination").pagination(num_entries, {
         num_edge_entries: 2,
         num_display_entries: 8,
         current_page:cur_page,
         callback: uspageselectCallback,
         items_per_page:page_items
     });
}


function deleteSure(){
	
 	var a = document.getElementsByName("boxDelete");
 	
 	 for (var i=0; i<a.length; i++)   
     {   
         if (a[i].checked)  
         {   
            var truthBeTold = window.confirm("确定删除所选用户?");
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
	show('hide','请先选择用户，谢谢！');
	return false;
}
