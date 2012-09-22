
var commentLength;
var page=0;
var page_items=10;
function pageselectCallback(page_index, jq){
       
	page=page_index;
	var obj = document.getElementById("searchResults");
    var liObj=obj.getElementsByTagName("li");
    var i=0;
    for(i=0;i<liObj.length;i++){
    	liObj[i].style.display="none";  
    }
   	page_index++;
	for (i = (page_index - 1) * 10; i < liObj.length && i < page_index * 10; i++) {
		liObj[i].style.display="block";  
	}
    return false;
}
 
             
function initPagination(num_entries,cur_page) {
       commentLength= num_entries;   
     $("#Pagination").pagination(num_entries, {
         num_edge_entries: 2,
         num_display_entries: 8,
         current_page:cur_page,
         callback: pageselectCallback,
         items_per_page:page_items
     });
}

var cateLevel=false;
function CatChange(categoryId,level){
		cateLevel=level;
		if(categoryId=="-1"){
			document.getElementById("secondCat").innerHTML="";
	 		document.getElementById("thirdCat").innerHTML="";
	 		document.getElementById("fourthCat").innerHTML="";
		}
	 document.getElementById("categoryId").value=categoryId;
	 //alert(document.getElementById("categoryId").value);
	 if(categoryId!=-1){
		 category_ajax(categoryId);
	 }
	 
}
var categoryRequest = false; 
function category_xml()
{
	try { 
	  categoryRequest = new XMLHttpRequest(); 
	} catch (trymicrosoft) { 
		  try { 
		    categoryRequest = new ActiveXObject("Msxml2.XMLHTTP"); 
		  } catch (othermicrosoft) { 
		    try { 
		      categoryRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
		    } catch (failed) { 
		      categoryRequest = false; 
		    }   
		  } 
	} 
	if (!categoryRequest) 
	  alert("Error initializing XMLHttpRequest!"); 
}

function category_ajax(categoryId){
	var url="category.do?method=getByFatherId&fatherId="+ escape(categoryId); 
	category_xml();
	categoryRequest.open("GET", url, true); 
	categoryRequest.setRequestHeader("content-type","text/xml");
	categoryRequest.onreadystatechange = afterCategory_ajax; 
	categoryRequest.setRequestHeader("If-Modified-Since","0");
	categoryRequest.send(null); 
}

function afterCategory_ajax(){
	if(categoryRequest.readyState==4 && categoryRequest.status==200)//返回完成
	{
		
		var categoryListJson=categoryRequest.responseText;
		
		categoryListJson=JSON.parse(categoryListJson);
		
		if (categoryListJson.length>1){//返回长度大于一
			
			//var select="<select name=\"secondCategoryList\" onChange=\"CatChange(this.value)\">";
			//for(i=0;i<categoryListJson.categoryList.length;i=i+1){
			//	select+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
			//}
 			//select+="</select>";
 			
 			// document.getElementById("secondCat").innerHTML=select;
			//return false;
			
			
			//////////////////
			if(cateLevel==1){
	 			var select="<select name=\"secondCategoryList\" onChange=\"CatChange(this.value,2)\">";
				
				for(i=0;i<categoryListJson.categoryList.length;i=i+1){
					select+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
				}
	 			select+="</select>";
 				 document.getElementById("secondCat").innerHTML=select;
 				 document.getElementById("thirdCat").innerHTML="";
 				 document.getElementById("fourthCat").innerHTML="";
 			 }else if(cateLevel==2){
	 			 var select="<select name=\"secondCategoryList\" onChange=\"CatChange(this.value,3)\">";
				
				for(i=0;i<categoryListJson.categoryList.length;i=i+1){
					select+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
				}
	 			select+="</select>";
 			 	document.getElementById("thirdCat").innerHTML=select;
 			 	document.getElementById("fourthCat").innerHTML="";
 			 }else if(cateLevel==3){
	 			 var select="<select name=\"secondCategoryList\" onChange=\"CatChange(this.value,4)\">";
				
				for(i=0;i<categoryListJson.categoryList.length;i=i+1){
					select+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
				}
	 			select+="</select>";
 			 	document.getElementById("fourthCat").innerHTML=select;
 			 }
			return false;
		}
		else{
			return true;
		}
	}
}


function   DrawImage(ImgD){ 
	var   image=new   Image(); 
	var   intWidth   =   68; 
	image.src=ImgD.src; 
	if(image.width> intWidth) 
	{     
		ImgD.width=intWidth; 
		ImgD.height=(image.height*intWidth)/image.width; 
	} 
	else 
	{ 
		ImgD.width=image.width; 
		ImgD.height=image.height; 
	} 
} 


function showstars(score){
alert("d");
	var starImg="";
	for(i=1;i<score;i++){
		starImg+="<img src=\"images/star_shine.bmp\" />";
	}
	
	for(i=i-1;i<5;i++){
		starImg+="<img src=\"images/star_black.bmp\" />";
	}
	document.getElementById("stars").innerHTML=starImg;
	
}
