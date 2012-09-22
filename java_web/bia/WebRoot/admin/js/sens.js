
var sensWordLength=0;
var page=0;
var page_items=10;
function sepageselectCallback(page_index, jq){
       
	page=page_index;
	var obj = document.getElementById("wordtbody");
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
 
             
function seinitPagination(num_entries,cur_page) {
       sensWordLength= num_entries;   
     $("#Pagination").pagination(num_entries, {
         num_edge_entries: 2,
         num_display_entries: 8,
         current_page:cur_page,
         callback: sepageselectCallback,
         items_per_page:page_items
     });
}

function clickEditWord(sensitiveId){
	var obj1=document.getElementById("pWord"+sensitiveId);
	var obj2=document.getElementById("btnEditWord"+sensitiveId);
	var obj3=document.getElementById("btnSureWord"+sensitiveId);
	var obj4=document.getElementById("btnCancelWord"+sensitiveId);
	var obj5=document.getElementById("txtWord"+sensitiveId);
	obj1.style.display="none";   
	obj2.style.display="none"; 
	obj3.style.display="block"; 
	obj4.style.display="block";  
	obj5.style.display="block";
}

function clickSureWord(sensitiveId){
	
	var obj1=document.getElementById("pWord"+sensitiveId);
	var obj2=document.getElementById("btnEditWord"+sensitiveId);
	var obj3=document.getElementById("btnSureWord"+sensitiveId);
	var obj4=document.getElementById("btnCancelWord"+sensitiveId);
	var obj5=document.getElementById("txtWord"+sensitiveId);
	var txtWord=obj5.value;
	
	if(txtWord==""){
		show('hide','敏感词不能为空！');
		return false;
	}
	if(txtWord.length>8){
		show('hide','敏感词长度不能大于8！');
		return false;
	}
	obj1.innerHTML=obj5.value;
	
	obj2.style.display="block"; 
	obj3.style.display="none"; 
	obj4.style.display="none";  
	obj5.style.display="none";
	
	changeWord(sensitiveId,txtWord);
	
	obj1.style.display="block";   
}

function clickCancelWord(sensitiveId){
	var obj1=document.getElementById("pWord"+sensitiveId);
	var obj2=document.getElementById("btnEditWord"+sensitiveId);
	var obj3=document.getElementById("btnSureWord"+sensitiveId);
	var obj4=document.getElementById("btnCancelWord"+sensitiveId);
	var obj5=document.getElementById("txtWord"+sensitiveId);
	obj5.value=obj1.innerHTML;
	obj1.style.display="block";   
	obj2.style.display="block"; 
	obj3.style.display="none"; 
	obj4.style.display="none";  
	obj5.style.display="none";
}

var changeWordRequest = false; 
var addWordRequest=false;
function word_xml()
{
	var request=false;
	try { 
	  request = new XMLHttpRequest(); 
	} catch (trymicrosoft) { 
		  try { 
		    request = new ActiveXObject("Msxml2.XMLHTTP"); 
		  } catch (othermicrosoft) { 
		    try { 
		      request = new ActiveXObject("Microsoft.XMLHTTP"); 
		    } catch (failed) { 
		      request = false; 
		    }   
		  } 
	} 
	if (!request) 
	  alert("Error initializing XMLHttpRequest!"); 
	return request;
}

function changeWord(sensitiveId,txtWord){
	txtWord=encodeURI(txtWord);
	txtWord=encodeURI(txtWord);
	var url="sensword.do?method=update&sensitiveId="+ escape(sensitiveId)+"&sensWord="+txtWord; 
	changeWordRequest=word_xml();
	changeWordRequest.open("GET", url, true); 
	changeWordRequest.setRequestHeader("content-type","text/xml");
	changeWordRequest.onreadystatechange = afterChangeWord; 
	changeWordRequest.setRequestHeader("If-Modified-Since","0");
	changeWordRequest.send(null); 
}

function afterChangeWord(){
	if(changeWordRequest.readyState==4 && changeWordRequest.status==200)//返回完成
	{
		var ok=changeWordRequest.responseText;		
		if(ok=="ok"){
			show('hide','修改敏感词成功！');
		}
	}
}

function addWord(){
	var txtWord=document.getElementById("addWordtxt").value;
	if(txtWord==""){
		show('hide','敏感词不能为空！');
		return false;
	}
	if(txtWord.length>8){
		show('hide','敏感词长度不能大于8！');
		return false;
	}
	txtWord=encodeURI(txtWord);
	txtWord=encodeURI(txtWord);
	var url="sensword.do?method=add&sensWord="+txtWord; 
	addWordRequest=word_xml();
	addWordRequest.open("GET", url, true); 
	addWordRequest.setRequestHeader("content-type","text/xml");
	addWordRequest.onreadystatechange = afterAddWord; 
	addWordRequest.setRequestHeader("If-Modified-Since","0");
	addWordRequest.send(null); 
}

function afterAddWord(){
	if(addWordRequest.readyState==4 && addWordRequest.status==200)//返回完成
	{
		var wordJson=addWordRequest.responseText;
		wordJson=JSON.parse(wordJson);
		
		if(wordJson.ok=="ok"){
			show('hide','添加敏感词成功！');
			var obj = document.getElementById("wordtbody");
			var origTable=obj.innerHTML;
			var newTr="<tr>";
			newTr+="<td><input type=\"checkbox\" name=\"boxDelete\" value=\""+wordJson.sensitiveId+"\" /></td>";
		    newTr+="<td><p id=\"pWord"+wordJson.sensitiveId+"\">"+wordJson.sensitiveWord+"</p><input id=\"txtWord"+wordJson.sensitiveId+"\" class=\"txtWord\" type=\"text\" name=\"txtWord\" value=\""+wordJson.sensitiveWord+"\" /></td>";
		    newTr+="<td><input id=\"btnEditWord"+wordJson.sensitiveId+"\" type=\"button\" name=\"btnEditWord\" class=\"btnEditWord\" value=\" \" onclick=\"clickEditWord("+wordJson.sensitiveId+")\" /><input id=\"btnSureWord"+wordJson.sensitiveId+"\" class=\"btnSure\" type=\"button\" name=\"btnSureWord\" value=\" \" onclick=\"clickSureWord("+wordJson.sensitiveId+")\" /></td>";
		    newTr+="<td><input id=\"btnCancelWord"+wordJson.sensitiveId+"\" type=\"button\" class=\"btnSandC\" name=\"btnCancelWord\" value=\" \" onclick=\"clickCancelWord("+wordJson.sensitiveId+")\" /></td>";
		    newTr+="</tr>";
			origTable=newTr+origTable;
			obj.innerHTML=origTable;
			document.getElementById("addWordtxt").value="";
			sensWordLength++;
			seinitPagination(sensWordLength,0);
		}
	}
}



function deleteSure(){
	
 	var a = document.getElementsByName("boxDelete");
 	
 	 for (var i=0; i<a.length; i++)   
     {   
         if (a[i].checked)  
         {   
            var truthBeTold = window.confirm("确定删除所选敏感词?");
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
	show('hide','请先选择敏感词，谢谢！');
	return false;
}
