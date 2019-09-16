	


	//샘플 이미지 가져오기
	$(document).ready(function(){
		//좋아하는 스타일 목록
		var FavoriteStyleArr = new Array(10);

	  $.ajax({
	   url : "/getFileList",
	   async: true,
	   type : 'POST',    
	   dataType: "json",    
	   success : function(data){
		 
	   setImgArray(data);
	   },
	   error: function(data){console.log("오류 발생"); console.log("data: "+data);},
	   beforeSend: function() {
	   },
	   complete: function() {

	   }       
	  });  
	  
	  //이미지 html에 넣는 부분
	  function setImgArray(data){
		  
		  console.log(data);
		  var length = data.sampleImgList.length;
		  var sampleDataArray = new Array();
		 
		  //데이터 샘플을 배열에 저장
		  for(var i=0; i< length-1; i++){
	
			  var sampleData = data.sampleImgList[i];
			  sampleDataArray[i] = sampleData;

		  }
		  //배열 값 중 6개를 랜덤으로 선택한 뒤 지정된 위치에 넣음
			var numArr = shuffleRandom();
			var j=0
			console.log(numArr);
				for(var num of numArr){
						var objImg = document.getElementById("styleImg"+j);
						var styleName = sampleDataArray[num].name;
						objImg.src = sampleDataArray[num].path;
						$("<p>"+styleName+"</p>").prependTo($("#styleName"+j));
						j++;
				}
		  
		 }
	 
	  
	  });
	
	
	  
	

	function shuffleRandom(){
        var numArr = new Array(6);
        
        for(var i =0; i < 6; i++){
        	numArr[i] = Math.floor(Math.random()*17);
        	
        	for(var j=0; j<i; j++){
        		if(numArr[j] == numArr[i]) {
        			i--;
        			console.log("중복값. 뒤로 돌아갑니다.");
        			console.log(numArr);
        			break;
        		}
        	}
        	
        }
   	 	return numArr;
	}
	
//	$(document).on('click',".styleSample", function(event){
//		var styleName = $(this).children('label').html();
//		console.log(styleName);
//		
//	});
//	
	function saveFavoriteStyle(){
		var curNum;
		var styleName = $(this).html;
		console.log(styleName)
	}
