	


	//샘플 이미지 가져오기
	$(document).ready(function(){
		
	  var gbl_data;

	  $.ajax({
	   url : "/getFileList",
	   async: false,
	   type : 'POST',    
	   dataType: "json",    
	   success : function(data){
	   gbl_data = data;
	   
	   },
	   error: function(data){console.log("오류 발생"); console.log("data: "+data);},
	   beforeSend: function() {
	   },
	   complete: function() {

	   }       
	  });  
	  
	  //이미지 html에 넣는 부분
	  console.log(gbl_data.sampleImgList[0].path);
	  var imgArray = new Array(gbl_data.sampleImgList.length);
	 
	  for(int j=0; j<=gbl_data.sampleImgList.length; j++){
		  imgArray[j] = gbl_data.sampleImgList[j].path;
	  }
		/*imgArray[0] = "images/20190915073324_casual1.png";
		imgArray[1] = "images/20190915073341_casual2.png";
		imgArray[2] = "images/20190915073355_chic1.png"
		imgArray[3] = "images/20190915073413_chic2.png";
		imgArray[4] = "images/20190915073434_formal1.png"
		imgArray[5] = "images/20190915073449_formal2.png";
		*/
		var numArr = shuffleRandom();
		var i=0
		console.log(numArr);
			for(var num of numArr){
				
				if(typeof num == 'undefined'){	
					continue;
				}
				else{
					var objImg = document.getElementById("styleImg"+i);
					objImg.src = imgArray[num];
					i++;
				}
				
			};
	  
	 });
	
	
	
	function shuffleRandom(){
        var numArr = new Array(6);
        var temp;
        var rnum;
       
        //전달받은 매개변수 n만큼 배열 생성
        for(var i=0; i<6; i++){
            numArr.push(i);
        }
 
        //값을 서로 섞기
        for(var i=0; i< numArr.length ; i++)
        {
            rnum = Math.floor(Math.random() *6); //난수발생
            temp = numArr[i];
            numArr[i] = numArr[rnum];
            numArr[rnum] = temp;
        }
 
        return numArr;
}
	
			


	 