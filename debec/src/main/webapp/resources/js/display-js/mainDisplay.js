var childWindow;
$(document).ready(function() {
	//이미지 섬네일
    var xOffset = 10;
    var yOffset = 30;
    $(document).on("mouseover",".thumbnail",function(e){ //마우스 오버시
        $("body").append("<p id='preview'><img src='"+ $(this).attr("src") +"' width='200px' /></p>"); //보여줄 이미지를 선언                       
        $("#preview")
            .css("top",(e.pageY - xOffset) + "px")
            .css("left",(e.pageX + yOffset) + "px")
            .fadeIn("fast"); //미리보기 화면 설정 셋팅
    });     
    $(document).on("mousemove",".thumbnail",function(e){ //마우스 이동시
        $("#preview")
            .css("top",(e.pageY - xOffset) + "px")
            .css("left",(e.pageX + yOffset) + "px");
    });     
    $(document).on("mouseout",".thumbnail",function(){ //마우스 아웃시
        $("#preview").remove();
    });
      
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
    	    
    //체크박스 삭제
    $('#deleteProdct').click(function(){
    	var chked_length = $("input[name=prodctCheck]:checked").length; //체크된 박스 개수
    	if(chked_length == 0){
    		alert("삭제할 상품을 선택하세요.");
    	}
    	else{
    		if (confirm("총 " + chked_length + "개의 항목을 삭제하시겠습니까?") == true){    //확인
        		var chked_val = ""; 
    			$(":checkbox[name='prodctCheck']:checked").each(function(pi,po){
    				chked_val += ","+po.value;
    			});
    			if(chked_val!="")chked_val = chked_val.substring(1);
				$.ajax({
					type:"POST",
					url:"/deleteMainDisplayProdct.do",
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
    });
	
	//진열순서변경
	var ordered_items;
	$("#tableId").tableDnD({
		onDragClass: "dragRow",
		onDrop: function(table, row){
			ordered_items = $.tableDnD.jsonize();
		}
	});
	
	//진열순서변경 업데이트
	$('#orderConfirm').click(function(){
		if(ordered_items == null){
			alert("변경된 진열 순서가 없습니다.");
		}
		else{
			$.ajax({
				type:"POST",
				url:"/updateMainDisplayOrder.do",
				data:ordered_items,
				contentType: "application/json",
				success:function(data){
					location.reload();
				},
				error:function(request,status,error){
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		}
	});
	//상품추가창 팝업
	$('#insertProdctRead').click(function(){
		var popUrl = "/mainDisplayCreateRead.do";
	    var popupName= "패키지 등록 - 상품 추가";
	    var width = 1200;
	    var height = 700;
	    var leftPosition = (screen.width/2) - (width/2);
	       leftPosition += window.screenLeft;//듀얼 모니터일때
	    var topPosition = (screen.height/2) - (height/2);
	    
	    childWindow = window.open(popUrl, popupName, "left="+leftPosition+", top="+topPosition+", toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, width="+width+", height="+height);

	    $('#mainDiv').after("<div class='modal-backdrop fade in' style='bottom: 0; z-index:1040;' id='FadeIn' onclick='fadeInFocus()'></div>");
	    
	    if(childWindow == null) {//만약 팝업 차단으로 팝업창이 나타나지 않을 경우
	       alert("팝업차단을 해제해주세요!");
	    }
	    else {
	    	childWindow.focus();            
	    }
	});
});

function fadeInFocus(){
	childWindow.focus();
}

// 메인 검색
function mainDisplaySech() {
	document.mainDisplaySechFrm.action ="/mainDisplayList.do";
	document.mainDisplaySechFrm.submit();
}

// 메인 페이징
function mainDisplayPaging(page) {
	document.mainDisplayPagingFrm.currentPageNo.value = page;
	document.mainDisplayPagingFrm.action ="/mainDisplayList.do";
	document.mainDisplayPagingFrm.submit();
}