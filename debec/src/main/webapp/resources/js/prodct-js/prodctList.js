$(document).ready(function() {
	//체크박스 전체클릭
    $("#allCheck").click(function(){
        //클릭되었으면
        if($("#allCheck").prop("checked")){
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
            $("input[name=prodctCheck]").prop("checked",true);
            //클릭이 안되있으면
        }else{
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
            $("input[name=prodctCheck]").prop("checked",false);
        }
    });

    $('#selProdctCreate').click(function()
	{
    	location.href="/selProdctCreateRead.do";
	});	    
    //판매 상품 체크박스 삭제
    $('#selProdctDelete').click(function(){
    	var chked_length = $("input[name=prodctCheck]:checked").length; //체크된 박스 개수
    	if (confirm("총 " + chked_length + "개의 항목을 삭제하시겠습니까?") == true){    //확인
    		var chked_val = "";
			$(":checkbox[name='prodctCheck']:checked").each(function(pi,po){
				chked_val += ","+po.value;
			});
			if(chked_val!="")chked_val = chked_val.substring(1);
			if(chked_val == ""){
				alert("삭제할 상품을 선택해주십시오.")
			}
			else{
				$.ajax({
					type:"POST",
					url:"/selProdctDelete.do",
					data:chked_val,
				});
				location.reload();
			}		
    	}
    	else{
    		alert("삭제가 취소되었습니다.");	
    	}
				
    });

    

    $('#selStopProdctDelete').click(function(){
    	var chked_length = $("input[name=prodctCheck]:checked").length; //체크된 박스 개수
    	if (confirm("총 " + chked_length + "개의 항목을 삭제하시겠습니까?") == true){    //확인
    		var chked_val = "";
			$(":checkbox[name='prodctCheck']:checked").each(function(pi,po){
				chked_val += ","+po.value;
			});
			if(chked_val!="")chked_val = chked_val.substring(1);
			if(chked_val == ""){
				alert("삭제할 상품을 선택해주십시오.")
			}
			else{
				$.ajax({
					type:"POST",
					url:"/selStopProdctDelete.do",
					data:chked_val,
				});
				location.reload();
			}		
    	}
    	else{
    		alert("삭제가 취소되었습니다.");	
    	}
				
    });
    $('#selStopProdctReSelStrt').click(function(){
    	var chked_length = $("input[name=prodctCheck]:checked").length; //체크된 박스 개수
    	if (confirm("총 " + chked_length + "개의 항목을 다시 진열 중 상태로 변경하시겠습니까?") == true){    //확인
    		var chked_val = "";
			$(":checkbox[name='prodctCheck']:checked").each(function(pi,po){
				chked_val += ","+po.value;
			});
			if(chked_val!="")chked_val = chked_val.substring(1);
			if(chked_val == ""){
				alert("진열할 상품을 선택해주십시오.")
			}
			else{
				$.ajax({
					type:"POST",
					url:"/selStopProdctReSelStrt.do",
					data:chked_val,
				});
				location.reload();
			}		
    	}
    	else{
    		alert("상품 상태 변경 요청이 취소되었습니다.");	
    	}
				
    });
});