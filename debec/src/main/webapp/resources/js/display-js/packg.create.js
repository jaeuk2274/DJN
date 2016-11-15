function prodctPopUp(){
	
	var clintSeq = $('#clintSeq').val();
    var popUrl = "/packgCreateTable.do?clintSeq="+clintSeq;
    var popupName= "패키지 등록 - 상품 추가";
    var width = 1200;
    var height = 700;
    var leftPosition = (screen.width/2) - (width/2);
       leftPosition += window.screenLeft;//듀얼 모니터일때
    var topPosition = (screen.height/2) - (height/2);
    
    var win = window.open(popUrl, popupName, "left="+leftPosition+", top="+topPosition+", toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, width="+width+", height="+height);

    $('#mainDiv').after("<div class='modal-backdrop fade in' style='bottom: 0; z-index:1040;' id='FadeIn'></div>");
    
    if(win == null) {//만약 팝업 차단으로 팝업창이 나타나지 않을 경우
       alert("팝업차단을 해제해주세요!");
    }
    else {
       win.focus();             
    }
 };
 

//임시 테이블 추가 후 완료하고 상품 리스트에 상품 추가
function insertPackgProdct(){
	$('.newTr').remove();
	$.ajax({
		type:"POST",
		url:"/selectPackgProdctAdTempList2.do",
		success:function(data){
			var allPric = 0;
			$.each(data, function(i, vo){
				$('#tableTh').after("<tr class='newTr'><td><input type='checkbox' name='prodctCheck' id='"+vo.prodctSeq+"'></td>"
				+ "<td>"+vo.prodctNme+"</td>"
				+ "<td>"+vo.purchsPric+"</td>"
				+ "<td>"+vo.selPric+"</td>"
				+ "<td>"+vo.qunt+"</td></tr>");
				allPric = parseInt(allPric) + parseInt(vo.selPric);
			});
			allPric = "패키지 총 가격 : " + allPric + "원";
			$('#allPric').html(allPric);
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

//패키지 등록
function insertPackg(){
	var chked_val = "";
	var clintSeq = $('#clintSeq').val();
	var chked_length = $(":checkbox[name='prodctCheck']").length;
	if(chked_length > 3){
		alert("한 패키지에 등록할 수 있는 상품은 최대 3개입니다.");
	}
	else{
		$(":checkbox[name='prodctCheck']").each(function(pi,po){
			chked_val += ","+po.id;
		});
		if(chked_val!="")chked_val = chked_val.substring(1);
		$('#chked_val').val(chked_val);
		var formData = $('#packgFrm').serialize();
		$.ajax({
			type:"POST",
			url:"/packgCreate.do",
			data:formData,
			success:function(data){
				location.href = "/packgList.do"
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
}

//거래처 선택 후 상품추가 및 거래처 정보 입력
function insertClintProdct(clintSeq){
	var clintSeq = "clintSeq="+clintSeq;
	$.ajax({
		type:"POST",
		url:"/packgClintRead.do",
		data:clintSeq,
		success:function(data){
			$('#clintNme').html(data.clintNme);
			$('#clintRepresntatv').html(data.clintRepresntatv);
			$('#clintNum').html(data.clintNum);
			$('#clintFax').html(data.clintFax);
			$('#clintMangr').html(data.clintMangr);
			$('#clintMangrNum').html(data.clintMangrNum);
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
	$('.newTr').remove();
	$.ajax({
		type:"POST",
		url:"/clintProdctList.do",
		data:clintSeq,
		success:function(data){
			var allPric = 0;
			$.each(data.reverse(), function(i, vo){
				$('#tableTh').after("<tr class='newTr'><td><input type='checkbox' name='prodctCheck' id='"+vo.prodctSeq+"'></td>"
						+ "<td>"+vo.prodctNme+"</td>"
						+ "<td>"+vo.purchsPric+"</td>"
						+ "<td>"+vo.selPric+"</td>"
						+ "<td>"+vo.qunt+"</td></tr>");
				allPric = parseInt(allPric) + parseInt(vo.selPric);
			});
			allPric = "패키지 총 가격 : " + allPric + "원";
			$('#allPric').html(allPric);
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

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
    })
    	    
    //체크박스 삭제
    $('#deleteProdct').click(function(){
    	var chked_length = $("input[name=prodctCheck]:checked").length; //체크된 박스 개수
    	if(chked_length == 0){
    		alert("삭제할 상품을 선택해주십시오.");
    	}
    	else{
    		if (confirm("총 " + chked_length + "개의 항목을 삭제하시겠습니까?") == true){    //확인
	    		var chked_val = "";
				$(":checkbox[name='prodctCheck']:checked").each(function(pi,po){
					var id = "#"+po.id;
					$(id).parent().parent().remove();
				});
	    	}
	    	else{
	    		alert("삭제가 취소되었습니다.");	
	    	}
    	}	
    })
    
	//거래처 선택 팝업창
	$('#clintChoice').click(function(){
		var popUrl = "/packgClintList.do";
        var popupName= "패키지 등록 - 거래처 선택";
        var width = 750;
        var height = 750;
        var leftPosition = (screen.width/2) - (width/2);
           leftPosition += window.screenLeft;//듀얼 모니터일때
        var topPosition = (screen.height/2) - (height/2);
        
        var win = window.open(popUrl, popupName, "left="+leftPosition+", top="+topPosition+", toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, width="+width+", height="+height);

        $('#mainDiv').after("<div class='modal-backdrop fade in' style='bottom: 0; z-index:1040;' id='FadeIn'></div>");
        
        if(win == null) {//만약 팝업 차단으로 팝업창이 나타나지 않을 경우
           alert("팝업차단을 해제해주세요!");
        }
        else {
           win.focus();            
        }
	});
});

//패키지 구분
function packgTypeCheck(radio){
	var type = radio.value;
	if (type=='거래처'){
		$('#clintChoice').css('display','inline');
		$('#firstTr').after("<tr id='secondTr'>"
				+ "<td style='text-align:center'>거래처 명</td><td id='clintNme'></td>"
				+ "<td style='text-align:center'>대표자</td><td id='clintRepresntatv'></td>"
				+ "<td style='text-align:center'>거래처 번호</td><td id='clintNum'></td></tr>"
				+ "<tr id='thirdTr'>"
				+ "<td style='text-align:center'>팩스</td><td id='clintFax'></td>"
				+ "<td style='text-align:center'>담당자</td><td id='clintMangr'></td>"
				+ "<td style='text-align:center'>담당자 번호</td><td id='clintMangrNum'></td></tr>");
	}
	else {
		$('#clintChoice').css('display','none');
		$('#secondTr').remove();
		$('#thirdTr').remove();
		$('#packgType').val("일반");
	}
}