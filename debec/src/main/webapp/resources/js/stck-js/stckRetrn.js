$(document).ready(function() {
    
    //판매 상품 체크박스 삭제
    $('#stckRetrnSend').click(function(){
    	var counter = 0;
    	var prodctLength = $("input[name=prodctCheck]").length;
  
    	var prodctSeq = new Array;
    	var qunt = new Array;
    	
    	for(var i = 0; i<prodctLength; i++)
		{
    		prodctSeq[i] = document.getElementsByName("prodctCheck")[i].value;
    		qunt[i] = $("#"+prodctSeq[i]).val();
    		var before = document.getElementsByName(prodctSeq[i])[0].value;
    		var after = document.getElementsByName(prodctSeq[i])[1].value;
    		if(after>before)
    		{
    			counter +=1;
    		}
		}
    	if(counter==0)
    	{
	    	$.ajax({
				type:"POST",
				url:"/stckRetrnUpdate.do",
				data:{
					"prodctSeq":prodctSeq
					, "qunt":qunt
				},
				success:function(dummy)
				{
					window.opener.location.reload();
			    	window.close();
				}
			});	
    	}
    	else
		{
    		alert("반품 수량이 기존 수량보다 많습니다!")
		}
    });
    $('#stckRetrnCancl').click(function(){
    	window.close();
    });

});