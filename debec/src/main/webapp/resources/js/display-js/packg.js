$(document).ready(function() {	
	$("#updatePackgDisplayCheck").click(function(){
		var chked_length = $("input[name=packgCheck]:checked").length; //체크된 박스 개수
		if (chked_length == 0){
			alert("수정할 패키지를 선택해주십시오.")
		}
		else{
			if (confirm("총 " + chked_length + "개의 항목을 수정하시겠습니까?") == true){    //확인
	    		var chked_val = "";
				$(":checkbox[name='packgCheck']:checked").each(function(pi,po){
					
					chked_val += ","+po.value;
				});
				if(chked_val!="")chked_val = chked_val.substring(1);
				$.ajax({
					type:"POST",
					url:"/packgDisplayCheckUpdate.do",
					data:chked_val, 
					success:function(data){
						location.reload();
					},
					error:function(request,status,error){
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
				});	
	    	}
	    	else{
	    		alert("진열여부수정이 취소되었습니다.");	
	    	}	
		}
	});
	
	//체크박스 전체클릭
    $("#allCheck").click(function(){
        //클릭되었으면
        if($("#allCheck").prop("checked")){
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
            $("input[name=packgCheck]").prop("checked",true);
            //클릭이 안되있으면
        }else{
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
            $("input[name=packgCheck]").prop("checked",false);
        }
    })
    	    
    //체크박스 삭제
    $('#deletePackg').click(function(){
    	var chked_length = $("input[name=packgCheck]:checked").length; //체크된 박스 개수
    	if (chked_length == 0){
			alert("삭제할 패키지를 선택해주십시오.")
    	}
    	else{
    		if (confirm("총 " + chked_length + "개의 항목을 삭제하시겠습니까?") == true){    //확인
	    		var chked_val = "";
				$(":checkbox[name='packgCheck']:checked").each(function(pi,po){
					chked_val += ","+po.value;
				});
				if(chked_val!="")chked_val = chked_val.substring(1);
				$.ajax({
					type:"POST",
					url:"/deletePackg.do",
					data:chked_val,
					success:function(data){
						location.reload();
					},
					error:function(request,status,error){
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
				});
	    	}
	    	else{
	    		alert("삭제가 취소되었습니다.");	
	    	}
    	}	
    })
});

// 메인 검색
function packgSech() {
	document.packgSechFrm.action ="/packgList.do";
	document.packgSechFrm.submit();
}

// 메인 페이징
function packgPaging(page) {
	document.packgPagingFrm.currentPageNo.value = page;
	document.packgPagingFrm.action ="/packgList.do";
	document.packgPagingFrm.submit();
}

//패키지 구분
function packgTypeCheck(radio){
	var type = radio.value;
	if (type=='normal'){
		$("select[name='packgSechType'] option[value='clint']").remove();
	}
	else {
		if($("select[name='packgSechType'] option:eq(0)").val() != 'clint'){
			$("select[name='packgSechType'] option:eq(0)").before("<option value='clint' selected>거래처 명</option>");
		}
	}
}