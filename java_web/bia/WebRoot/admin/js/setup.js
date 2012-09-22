function getHTTPObject(){
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
	return request;
}

function start(){
	
}

function stop(){

}

function saveWeek(){
	
}

function saveHour(){
	
}