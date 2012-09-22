function addSure(){
	var categoryName = document.getElementById("category").value;
	var tags = document.getElementById("tags").value;
	
	if(categoryName==""||tags==""){
	
		show('hide','分类名和关键字都不能够为空！<br/>');
		return false;
	}
	if(categoryName.length>20){
		show('hide','分类名长度不能超过20！<br/>');
		return false;
	}
	if(tags.length>100){
		show('hide','关键词长度不能超过100！<br/>');
		return false;
	}
	
	var truthBeTold = window.confirm("确定增加分类?");
	if(truthBeTold){
		return true;
	}
	return false;
}

function editSure(){
	var origcategoryId = document.getElementById("origcategoryId").value;
	if(origcategoryId=="-1"){
		show('hide','请先选择一个分类！<br/>');
		return false;
	}
	
	var categoryName = document.getElementById("origcategoryName").value;
	var tags = document.getElementById("origTags").value;
	
	if(categoryName==""||tags==""){
	
		show('hide','分类名和关键字都不能够为空！<br/>');
		return false;
	}
	
	if(categoryName.length>20){
		show('hide','分类名长度不能超过20！<br/>');
		return false;
	}
	if(tags.length>100){
		show('hide','关键词长度不能超过100！<br/>');
		return false;
	}
	
	var truthBeTold = window.confirm("确定编辑分类?");
	if(truthBeTold){
		return true;
	}
	return false;
}
function deleteSure(){
	
	var deletecategoryId = document.getElementById("deletecategoryId").value;
	
	if(deletecategoryId=="-1"){
	
		show('hide','请先选择一个分类！<br/>');
		return false;
	}
	var truthBeTold = window.confirm("确定删除分类?");
	if(truthBeTold){
		return true;
	}
	return false;
}

var commentLength;
var page=0;
var page_items=10;
function capageselectCallback(page_index, jq){
       
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
 
             
function cainitPagination(num_entries,cur_page) {
       commentLength= num_entries;   
     $("#Pagination").pagination(num_entries, {
         num_edge_entries: 2,
         num_display_entries: 8,
         current_page:cur_page,
         callback: capageselectCallback,
         items_per_page:page_items
     });
}


var cateLevel=false;
function CatChange(categoryId,level){
	
	if(categoryId=="-1"){
		 document.getElementById("secondCat").innerHTML="";
 		 document.getElementById("thirdCat").innerHTML="";
 		 document.getElementById("fourthCat").innerHTML="";
	}
	cateLevel=level;
	// alert(categoryId);
	if(categoryId!=0){
	 document.getElementById("categoryId").value=categoryId;
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
		if(categoryListJson.length==1){
			if(cateLevel==1){
	 			
 				 document.getElementById("secondCat").innerHTML="";
 				 document.getElementById("thirdCat").innerHTML="";
 				 document.getElementById("fourthCat").innerHTML="";
 			 }else if(cateLevel==2){
	 			
 			 	document.getElementById("thirdCat").innerHTML="";
 			 	document.getElementById("fourthCat").innerHTML="";
 			 }else if(cateLevel==3){
	 			
 			 	document.getElementById("fourthCat").innerHTML="";
 			 }
		}
		if (categoryListJson.length>1){//返回长度大于一
			
 			if(cateLevel==1){
	 			var select="<select name=\"secondCategoryList\" onChange=\"CatChange(this.value,2)\">";
				select+="<option value=\"0\">不继续细化分类";
				for(i=1;i<categoryListJson.categoryList.length;i=i+1){
					select+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
				}
	 			select+="</select>";
 				 document.getElementById("secondCat").innerHTML=select;
 				 document.getElementById("thirdCat").innerHTML="";
 				 document.getElementById("fourthCat").innerHTML="";
 			 }else if(cateLevel==2){
	 			 var select="<select name=\"secondCategoryList\" onChange=\"CatChange(this.value,3)\">";
				select+="<option value=\"0\">不继续细化分类";
				for(i=1;i<categoryListJson.categoryList.length;i=i+1){
					select+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
				}
	 			select+="</select>";
 			 	document.getElementById("thirdCat").innerHTML=select;
 			 	document.getElementById("fourthCat").innerHTML="";
 			 }else if(cateLevel==3){
	 			 var select="<select name=\"secondCategoryList\" onChange=\"CatChange(this.value,4)\">";
				select+="<option value=\"0\">不继续细化分类";
				for(i=1;i<categoryListJson.categoryList.length;i=i+1){
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

//////////////////////////////////////////////////////


var origcateLevel=false;
function EditCatChange(categoryId,level){
	origcateLevel=level;
	adaptcateLevel=level;
	if(categoryId=="-1"){
		document.getElementById("origsecondCat").innerHTML="";
 		document.getElementById("origthirdCat").innerHTML="";
 		document.getElementById("origfourthCat").innerHTML="";
 		document.getElementById("origcategoryName").value="";
 		document.getElementById("origTags").value="";
	}
	// alert(categoryId);
	if(categoryId!=0){
	 document.getElementById("origcategoryId").value=categoryId;
	 document.getElementById("adaptcategoryId").value=categoryId;
	 origcategory_ajax(categoryId);
	 }
}


var origcategoryRequest = false; 
function arigcategory_xml()
{
	try { 
	  origcategoryRequest = new XMLHttpRequest(); 
	} catch (trymicrosoft) { 
		  try { 
		    origcategoryRequest = new ActiveXObject("Msxml2.XMLHTTP"); 
		  } catch (othermicrosoft) { 
		    try { 
		      origcategoryRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
		    } catch (failed) { 
		      origcategoryRequest = false; 
		    }   
		  } 
	} 
	if (!origcategoryRequest) 
	  alert("Error initializing XMLHttpRequest!"); 
}

function origcategory_ajax(categoryId){
	var url="category.do?method=getByFatherId&fatherId="+ escape(categoryId); 
	arigcategory_xml();
	origcategoryRequest.open("GET", url, true); 
	origcategoryRequest.setRequestHeader("content-type","text/xml");
	origcategoryRequest.onreadystatechange = afterorigCategory_ajax; 
	origcategoryRequest.setRequestHeader("If-Modified-Since","0");
	origcategoryRequest.send(null); 
}

function afterorigCategory_ajax(){
	if(origcategoryRequest.readyState==4 && origcategoryRequest.status==200)//返回完成
	{
		
		var categoryListJson=origcategoryRequest.responseText;
		
		categoryListJson=JSON.parse(categoryListJson);
		if(categoryListJson.length==1){
			if(origcateLevel==1){
	 			
 				 document.getElementById("origsecondCat").innerHTML="";
 				 document.getElementById("origthirdCat").innerHTML="";
 				 document.getElementById("origfourthCat").innerHTML="";
 				 
 				  document.getElementById("adaptsecondCat").innerHTML="";
 				 document.getElementById("adaptthirdCat").innerHTML="";
 				 document.getElementById("adaptfourthCat").innerHTML="";
 			 }else if(origcateLevel==2){
	 			
 			 	document.getElementById("origthirdCat").innerHTML="";
 			 	document.getElementById("origfourthCat").innerHTML="";
 			 	
 			 	document.getElementById("adaptthirdCat").innerHTML="";
 				 document.getElementById("adaptfourthCat").innerHTML="";
 			 }else if(origcateLevel==3){
	 			
 			 	document.getElementById("origfourthCat").innerHTML="";
 			 	 document.getElementById("adaptfourthCat").innerHTML="";
 			 }
 			 document.getElementById("origcategoryName").value=categoryListJson.fatherName;
 			 document.getElementById("origTags").value=categoryListJson.tags;
		}
		if (categoryListJson.length>1){//返回长度大于一
			
 			if(origcateLevel==1){
	 			var select="<select name=\"secondCategoryList\" onChange=\"EditCatChange(this.value,2)\">";
				select+="<option value=\"0\">不继续细化分类";
				for(i=1;i<categoryListJson.categoryList.length;i=i+1){
					select+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
				}
	 			select+="</select>";
 				 document.getElementById("origsecondCat").innerHTML=select;
 				 document.getElementById("origthirdCat").innerHTML="";
 				 document.getElementById("origfourthCat").innerHTML="";
 				 
 				 
 				 var adaptselect="<select name=\"secondCategoryList\" onChange=\"AdaptCatChange(this.value,2)\">";
				adaptselect+="<option value=\"0\">不继续细化分类";
				for(i=1;i<categoryListJson.categoryList.length;i=i+1){
					adaptselect+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
				}
	 			adaptselect+="</select>";
 				 document.getElementById("adaptsecondCat").innerHTML=adaptselect;
 				 document.getElementById("adaptthirdCat").innerHTML="";
 				 document.getElementById("adaptfourthCat").innerHTML="";
 				 
 				 
 				 
 				 
 			 }else if(origcateLevel==2){
	 			 var select="<select name=\"secondCategoryList\" onChange=\"EditCatChange(this.value,3)\">";
				select+="<option value=\"0\">不继续细化分类";
				for(i=1;i<categoryListJson.categoryList.length;i=i+1){
					select+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
				}
	 			select+="</select>";
 			 	document.getElementById("origthirdCat").innerHTML=select;
 			 	document.getElementById("origfourthCat").innerHTML="";
 			 	
 			 	
 			 	 var adaptselect="<select name=\"secondCategoryList\" onChange=\"AdaptCatChange(this.value,3)\">";
				adaptselect+="<option value=\"0\">不继续细化分类";
				for(i=1;i<categoryListJson.categoryList.length;i=i+1){
					adaptselect+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
				}
	 			adaptselect+="</select>";
 				 document.getElementById("adaptthirdCat").innerHTML=adaptselect;
 				 document.getElementById("adaptfourthCat").innerHTML="";
 				 
 			 }else if(origcateLevel==3){
	 			 var select="<select name=\"secondCategoryList\" onChange=\"EditCatChange(this.value,4)\">";
				select+="<option value=\"0\">不继续细化分类";
				for(i=1;i<categoryListJson.categoryList.length;i=i+1){
					select+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
				}
	 			select+="</select>";
 			 	document.getElementById("origfourthCat").innerHTML=select;
 			 	
 			 	
 			 	 var adaptselect="<select name=\"secondCategoryList\" onChange=\"AdaptCatChange(this.value,4)\">";
				adaptselect+="<option value=\"0\">不继续细化分类";
				for(i=1;i<categoryListJson.categoryList.length;i=i+1){
					adaptselect+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
				}
	 			adaptselect+="</select>";
 				 document.getElementById("adaptfourthCat").innerHTML=adaptselect;
 				 
 			 }
 			 document.getElementById("origcategoryName").value=categoryListJson.fatherName;
 			 document.getElementById("origTags").value=categoryListJson.tags;
			return false;
		}
		else{
			return true;
		}
	}
}



/////////////////////////////////////////
var adaptcateLevel=false;
function AdaptCatChange(categoryId,level){
	adaptcateLevel=level;
	// alert(categoryId);
	if(categoryId=="-1"){
		 document.getElementById("adaptsecondCat").innerHTML="";
 		 document.getElementById("adaptthirdCat").innerHTML="";
 		 document.getElementById("adaptfourthCat").innerHTML="";
	}
	
	if(categoryId!=0){
	 document.getElementById("adaptcategoryId").value=categoryId;
	 adaptcategory_ajax(categoryId);
	 }
}


var adaptcategoryRequest = false; 
function adaptcategory_xml()
{
	try { 
	  adaptcategoryRequest = new XMLHttpRequest(); 
	} catch (trymicrosoft) { 
		  try { 
		    adaptcategoryRequest = new ActiveXObject("Msxml2.XMLHTTP"); 
		  } catch (othermicrosoft) { 
		    try { 
		      adaptcategoryRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
		    } catch (failed) { 
		      adaptcategoryRequest = false; 
		    }   
		  } 
	} 
	if (!adaptcategoryRequest) 
	  alert("Error initializing XMLHttpRequest!"); 
}

function adaptcategory_ajax(categoryId){
	var url="category.do?method=getByFatherId&fatherId="+ escape(categoryId); 
	adaptcategory_xml();
	adaptcategoryRequest.open("GET", url, true); 
	adaptcategoryRequest.setRequestHeader("content-type","text/xml");
	adaptcategoryRequest.onreadystatechange = afteradaptCategory_ajax; 
	adaptcategoryRequest.setRequestHeader("If-Modified-Since","0");
	adaptcategoryRequest.send(null); 
}

function afteradaptCategory_ajax(){
	if(adaptcategoryRequest.readyState==4 && adaptcategoryRequest.status==200)//返回完成
	{
		
		var categoryListJson=adaptcategoryRequest.responseText;
		
		categoryListJson=JSON.parse(categoryListJson);
		if(categoryListJson.length==1){
			if(adaptcateLevel==1){
	 			
 				 document.getElementById("adaptsecondCat").innerHTML="";
 				 document.getElementById("adaptthirdCat").innerHTML="";
 				 document.getElementById("adaptfourthCat").innerHTML="";
 			 }else if(adaptcateLevel==2){
	 			
 			 	document.getElementById("adaptthirdCat").innerHTML="";
 			 	document.getElementById("adaptfourthCat").innerHTML="";
 			 }else if(adaptcateLevel==3){
	 			
 			 	document.getElementById("adaptfourthCat").innerHTML="";
 			 }
		}
		if (categoryListJson.length>1){//返回长度大于一
			
 			if(adaptcateLevel==1){
	 			var select="<select name=\"secondCategoryList\" onChange=\"AdaptCatChange(this.value,2)\">";
				select+="<option value=\"0\">不继续细化分类";
				for(i=1;i<categoryListJson.categoryList.length;i=i+1){
					select+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
				}
	 			select+="</select>";
 				 document.getElementById("adaptsecondCat").innerHTML=select;
 				 document.getElementById("adaptthirdCat").innerHTML="";
 				 document.getElementById("adaptfourthCat").innerHTML="";
 				 
 			 }else if(adaptcateLevel==2){
	 			 var select="<select name=\"secondCategoryList\" onChange=\"AdaptCatChange(this.value,3)\">";
				select+="<option value=\"0\">不继续细化分类";
				for(i=1;i<categoryListJson.categoryList.length;i=i+1){
					select+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
				}
	 			select+="</select>";
 			 	document.getElementById("adaptthirdCat").innerHTML=select;
 			 	document.getElementById("adaptfourthCat").innerHTML="";
 			 }else if(adaptcateLevel==3){
	 			 var select="<select name=\"secondCategoryList\" onChange=\"AdaptCatChange(this.value,4)\">";
				select+="<option value=\"0\">不继续细化分类";
				for(i=1;i<categoryListJson.categoryList.length;i=i+1){
					select+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
				}
	 			select+="</select>";
 			 	document.getElementById("adaptfourthCat").innerHTML=select;
 			 }
 			 return false;
		}
		else{
			return true;
		}
	}
}

///////////////////////////////////////////////////////////////////


var deletecateLevel=false;
function deleteCatChange(categoryId,level){
	deletecateLevel=level;
	//alert(categoryId);
	if(categoryId=="-1"){
		document.getElementById("deletesecondCat").innerHTML="";
 		document.getElementById("deletethirdCat").innerHTML="";
 		document.getElementById("deletefourthCat").innerHTML="";
	}
	if(categoryId!=0){
	 document.getElementById("deletecategoryId").value=categoryId;
	 deletecategory_ajax(categoryId);
	 }
	 
}
var deletecategoryRequest = false; 
function deletecategory_xml()
{
	try { 
	  deletecategoryRequest = new XMLHttpRequest(); 
	} catch (trymicrosoft) { 
		  try { 
		    deletecategoryRequest = new ActiveXObject("Msxml2.XMLHTTP"); 
		  } catch (othermicrosoft) { 
		    try { 
		      deletecategoryRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
		    } catch (failed) { 
		      deletecategoryRequest = false; 
		    }   
		  } 
	} 
	if (!deletecategoryRequest) 
	  alert("Error initializing XMLHttpRequest!"); 
}

function deletecategory_ajax(categoryId){
	var url="category.do?method=getByFatherId&fatherId="+ escape(categoryId); 
	deletecategory_xml();
	deletecategoryRequest.open("GET", url, true); 
	deletecategoryRequest.setRequestHeader("content-type","text/xml");
	deletecategoryRequest.onreadystatechange = afterdeleteCategory_ajax; 
	deletecategoryRequest.setRequestHeader("If-Modified-Since","0");
	deletecategoryRequest.send(null); 
}

function afterdeleteCategory_ajax(){
	if(deletecategoryRequest.readyState==4 && deletecategoryRequest.status==200)//返回完成
	{
		
		var categoryListJson=deletecategoryRequest.responseText;
		
		categoryListJson=JSON.parse(categoryListJson);
		if(categoryListJson.length==1){
			if(deletecateLevel==1){
	 			
 				 document.getElementById("deletesecondCat").innerHTML="";
 				 document.getElementById("deletethirdCat").innerHTML="";
 				 document.getElementById("deletefourthCat").innerHTML="";
 			 }else if(deletecateLevel==2){
	 			
 			 	document.getElementById("deletethirdCat").innerHTML="";
 			 	document.getElementById("deletefourthCat").innerHTML="";
 			 }else if(deletecateLevel==3){
	 			
 			 	document.getElementById("deletefourthCat").innerHTML="";
 			 }
		}
		if (categoryListJson.length>1){//返回长度大于一
			
 			if(deletecateLevel==1){
	 			var select="<select name=\"secondCategoryList\" onChange=\"deleteCatChange(this.value,2)\">";
				select+="<option value=\"0\">不继续细化分类";
				for(i=1;i<categoryListJson.categoryList.length;i=i+1){
					select+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
				}
	 			select+="</select>";
 				 document.getElementById("deletesecondCat").innerHTML=select;
 				 document.getElementById("deletethirdCat").innerHTML="";
 				 document.getElementById("deletefourthCat").innerHTML="";
 			 }else if(deletecateLevel==2){
	 			 var select="<select name=\"secondCategoryList\" onChange=\"deleteCatChange(this.value,3)\">";
				select+="<option value=\"0\">不继续细化分类";
				for(i=1;i<categoryListJson.categoryList.length;i=i+1){
					select+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
				}
	 			select+="</select>";
 			 	document.getElementById("deletethirdCat").innerHTML=select;
 			 	document.getElementById("deletefourthCat").innerHTML="";
 			 }else if(deletecateLevel==3){
	 			 var select="<select name=\"secondCategoryList\" onChange=\"deleteCatChange(this.value,4)\">";
				select+="<option value=\"0\">不继续细化分类";
				for(i=1;i<categoryListJson.categoryList.length;i=i+1){
					select+="<option value=\""+categoryListJson.categoryList[i].categoryId+"\">"+categoryListJson.categoryList[i].categoryName;
				}
	 			select+="</select>";
 			 	document.getElementById("deletefourthCat").innerHTML=select;
 			 }
			return false;
		}
		else{
			return true;
		}
	}
}

