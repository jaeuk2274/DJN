  	$(document).ready(function() {
  		
	    // 수정
	    $('#updateFestival').click(function(){	
		  		var listLength = document.debecFestivalReadFrm.listLength.value;
		  		var prodctSeq_arry = new Array();
		  		var discntRat_arry = new Array();
		  		

	  		for(var i =1; i<listLength; i++) {
	  			var prodctSeq = document.getElementById('prodctSeq'+i).value;
	  			var discntRat = parseInt(document.getElementById('discntRat'+i).value);
	  			prodctSeq_arry.push(prodctSeq);
	  			discntRat_arry.push(discntRat);

	    }
  			document.debecFestivalReadFrm.prodctSeq_arry.value = prodctSeq_arry;
  			document.debecFestivalReadFrm.discntRat_arry.value = discntRat_arry;
  			document.debecFestivalReadFrm.action = "/debecFestivalUpdate.do";
  			document.debecFestivalReadFrm.method = "post";
  			document.debecFestivalReadFrm.submit();
  	});
	      	
		  //체크박스 일괄 선택
	  	 $("#allCheck").click(function(){
		        //클릭되었으면
		        if($("#allCheck").prop("checked")){
		            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
		            $("input[name=debecFestivalCheck]").prop("checked",true);
		            //클릭이 안되있으면
		        }
		        else{
		            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
		            $("input[name=debecFestivalCheck]").prop("checked",false);
		        }
  		});
	  	 
		//상품 리스트 일괄 삭제
	  	 $('#deleteFestival').click(function(){
		    	var chked_length = $("input[name=debecFestivalCheck]:checked").length; //체크된 박스 개수
		    	if (confirm("총 " + chked_length + "개의 항목을 삭제하시겠습니까?") == true){    //확인
		    		var chked_val = "";
					$(":checkbox[name='debecFestivalCheck']:checked").each(function(pi,po){
						chked_val += ","+po.value;
					});
					if(chked_val!="")chked_val = chked_val.substring(1);
					if(chked_val == ""){
						alert("삭제할 상품을 선택해주십시오.")
					}
					else{
						$.ajax({
							type:"POST",
							url:"/debecFestivalUpdateDelete.do",
							data:chked_val,
							success:function(data){
								location.reload();
							},
							error:function(request,status,error){
						        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
							}
						});
					}		
		    	}
		    	else{
		    		alert("삭제가 취소되었습니다.");	
		    	}		
		    });
		
		  	//할인율 일괄 적용
		  	$('#allApply').click(function(){
		  		var allDiscntRat = document.getElementById('allDiscntRat').value;
		  		for(var i =1; i<10; i++) {
		  			document.getElementById('discntRat'+i).value= parseInt(allDiscntRat);
		  			
		  			var selPric = parseInt(document.getElementById('selPric'+i).value);
		  			var purchsPric = parseInt(document.getElementById('purchsPric'+i).value);
		  			var salePric =parseInt( document.getElementById('salePric'+i).value);
		  			var margnRat = parseInt(document.getElementById('margnRat'+i).value);
		  			
		  			document.getElementById('salePric'+i).value =parseInt(selPric*(100-allDiscntRat)/100) ;
		  			salePric = document.getElementById('salePric'+i).value;
		  			document.getElementById('margnRat'+i).value =parseInt(((parseInt(salePric)-parseInt(purchsPric))/parseInt(salePric))*100);
		  		}
		  	});
	  	
		 	 // 행사기간 달력
		    $.datepicker.setDefaults({
		        dateFormat: 'yy-mm-dd',
		        prevText: '이전 달',
		        nextText: '다음 달',
		        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
		        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
		        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		        showMonthAfterYear: true,
		        yearSuffix: '년'
		    });
		    $(function() {
		        $("#schedlStartDat").datepicker();
		    });
		    $(function() {
		        $("#schedlEndDat").datepicker();
		    });
			 
  	});
	
  	//할인율 적용
  	function singleDiscntRat(status) {			  		
		var selPric = parseInt(document.getElementById('selPric'+status).value);
		var purchsPric = parseInt(document.getElementById('purchsPric'+status).value);
		var salePric =parseInt( document.getElementById('salePric'+status).value);
		var margnRat = parseInt(document.getElementById('margnRat'+status).value);
		var discntRat = parseInt(document.getElementById('discntRat'+status).value);

		document.getElementById('salePric'+status).value =parseInt(selPric*(100-discntRat)/100);
		document.getElementById('margnRat'+status).value =parseInt(((parseInt(salePric)-parseInt(purchsPric))/parseInt(salePric))*100);
  	}
  	
  	//상품 추가 팝업창
  	function prodctCreate()
	{
  		var schedlSeq = document.getElementById('schedlSeq').value;
  		
  		var division = "U";
		var popUrl = "prodctCreatePopupRead.do?schedlSeq="+schedlSeq+"&division="+division;//팝업창에 출력될 페이지 URL
		var popupName= "prodctCreatePopup";
		var width = 1200;
	    var height = 700;
		var leftPosition = (screen.width/2) - width / 2;
		//leftPosition += window.screenLeft;//듀얼 모니터일때
		var topPosition = (screen.height/2) - height / 2;
		var popOption;
		var win = window.open(popUrl, popupName, "left="+leftPosition+",top="+topPosition+",width="+width+",height="+height+", toolbars=no, resizable=no, scrollbars=no");
		
		$('#allDiv').after("<div class='modal-backdrop fade in' style='height:100%; bottom: 0; z-index:1040;' id='FadeIn'></div>");
		if(win == null) {//만약 팝업 차단으로 팝업창이 나타나지 않을 경우
			alert("팝업차단을 해제해주세요!");
		}
		else {
			win.focus();
		}
	}

	// 상품 추가 창 등록 버튼 클릭시
  	function prodctUpdate(){
  		var schedlSeq = document.getElementById('schedlSeq').value;
  		schedlSeq = {"schedlSeq" : schedlSeq};
    	
	 	$.ajax({ 		 		
			type:"POST",
			url:"/debecFestivaProdctlUpdate.do",
			data:schedlSeq,
			success:function(data){
				window.opener.location.reload();
				window.close();
		  		window.opener.$("#FadeIn").remove();
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
  	};
  	
  	//상품 등록 최종 완료
  	function prodctComplete(){
  		document.debecFestivalCreateFrm.action="/debecFestivalCreateProdctRead.do"
  		window.opener.$("#FadeIn").remove();
  		location.reload();
  	};
  	
	function closeIt(){
	    self.close();
	    window.opener.$("#FadeIn").remove();
	 }
	
	// 상품 일괄 추가 팝업창
	function xlsxUpload()
	{
  		var schedlSeq = document.getElementById('schedlSeq').value;
		var division = "U";
		var popUrl = "createXlsxPopup.do?schedlSeq="+schedlSeq+"&division="+division//팝업창에 출력될 페이지 URL
		var popupName= "createXlsxPopup";
		var width = 800;
		var height = 500;
		var leftPosition = (screen.width/2) - width / 2;
		var topPosition = (screen.height/2) - height / 2;
		var popOption;
		var win = window.open(popUrl, popupName, "left="+leftPosition+",top="+topPosition+",width="+width+",height="+height+", toolbars=no, resizable=no, scrollbars=no");
		
		$('#allDiv').after("<div class='modal-backdrop fade in' style='height:100%; bottom: 0; z-index:1040;' id='FadeIn'></div>");

		if(win == null) {//만약 팝업 차단으로 팝업창이 나타나지 않을 경우
			alert("팝업차단을 해제해주세요!");
		}
		else {
			win.focus();
		}
	}	