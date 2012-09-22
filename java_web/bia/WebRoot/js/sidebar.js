
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

 function DrawImage120_65(ImgD){
		ImgD.width=120; 
		ImgD.height=65; 
}