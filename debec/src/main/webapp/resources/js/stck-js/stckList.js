$(document).ready(function() {
	var stckState = 1; // 현재 등록 수정 선택 상황 (평시엔 "1", 수정을 누른 상태일 땐 "2")
	var chkedProdct = new Array(); // 체크된 상품 Array
	var changedQunt = new Array();
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

    $('#stckCreate').click(function()
	{
    	if(stckState == 1)
    	{
    		window.open("/stckCreateRead.do",'popup', 'width=800, height=620, left=0, top=0, toolbar=no, location=no, directories=no, status=no, menubar=no, resizable=no, scrollbars=no, copyhistory=no');

    	}
    	else if(stckState == 2)
    	{
    		for(var i=0;i<chkedProdct.length;i++)
    		{
    			changedQunt[i] = $("#"+chkedProdct[i]).val();

				$("#"+chkedProdct[i]).removeAttr("disabled");
    		}
			$.ajax({
				type:"POST",
				url:"/stckUpdate.do",
				data:{
					"prodctSeq":chkedProdct
					, "qunt":changedQunt
				},
				success:function(dummy)
				{
					for(var i=0;i<chkedProdct.length;i++)
		    		{
		    			changedQunt[i] = $("#"+chkedProdct[i]).val();

						$("#"+chkedProdct[i]).attr("disabled");
						$("#"+chkedProdct[i]).css("border","0px solid");
		    		}
		    		$('#stckCreate').val("등록")
					$('#stckUpdate').val("수정")
					stckState=1;
				}
			});	
				
    	}
	});

    $('#stckDown').click(function(){
    	var chked_length = $("input[name=prodctCheck]:checked").length; //체크된 박스 개수
    	if (confirm("총 " + chked_length + "개의 항목에 대해서 다운로드 하시겠습니까?") == true && chked_length>=1){    //확인

    		document.stckTableFrm.action = "stckXlxDown.do";
			document.stckTableFrm.method = "post";
    		document.stckTableFrm.submit();
    	}
    	else if(chked_length==0)
    		alert("1개 이상 선택해주십시오..");	
    	else{
    		alert("반품이 취소되었습니다.");	
    	}
    	
    });
    //판매 상품 체크박스 삭제
    $('#stckUpdate').click(function(){
    	if(stckState == 1)
    	{
    		chkedProdct = new Array(); // 체크된 상품 Array
    		changedQunt = new Array();
	    	var chked_length = $("input[name=prodctCheck]:checked").length; //체크된 박스 개수
	    	if (confirm("총 " + chked_length + "개 항목의 재고를  수정하시겠습니까?") == true && chked_length>=1){    //확인
	    		var chked_val = new Array();
				$(":checkbox[name='prodctCheck']:checked").each(function(pi,po){
					$("#"+po.value).removeAttr("disabled");
					$("#"+po.value).css("border","1px solid");
				
					chkedProdct.push(po.value);
				});
				$('#stckCreate').val("완료")
				$('#stckUpdate').val("취소")
				stckState=2;
				$("input[name=prodctCheck]").prop("checked",false);
					
	    	}
	    	else if(chked_length==0)
	    		alert("1개 이상 선택해주십시오..");	
	    	else{
	    		alert("수정이 취소되었습니다.");	
	    	}
    	}
    	else if(stckState == 2)
    	{
    		$('#stckCreate').val("등록")
			$('#stckUpdate').val("수정")
			stckState=1;
    		chked_length ="";
    	}
				
    });
    $('#stckRetrn').click(function(){
    	var chked_length = $("input[name=prodctCheck]:checked").length; //체크된 박스 개수
    	
    	if (confirm("총 " + chked_length + "개의 항목를 반품 하시겠습니까?") == true && chked_length>=1){    //확인
    		var chked_val = new Array();
    		chkedProdct=new Array();
    		$(":checkbox[name='prodctCheck']:checked").each(function(pi,po){
				chkedProdct.push(po.value);
			});
    		$.ajax({
				type:"POST",
				url:"/stckRetrnSeqSaver.do",
				data:{
					"prodctSeq":chkedProdct
					,"dummy":0
				},
				success:function(dummy)
				{
					var heightSize=230+(parseInt(chked_length)*45);
					chkedProdct=new Array();
		    		var popUrl = "/stckRetrnPopup.do";
		        	var popOption = "width=580, height="+heightSize+",  toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, ";    //공유 팝업창 옵션(optoin)
		        	window.open(popUrl,"stckRetrnPopup",popOption);
				}
    		});
    	}
    	else if(chked_length==0)
    		alert("1개 이상 선택해주십시오..");	
    	else{
    		alert("반품이 취소되었습니다.");	
    	}
    	
    });

});