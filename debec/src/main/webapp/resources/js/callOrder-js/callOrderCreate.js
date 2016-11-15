
	$(document).ready(function(){
		
		//회원 비회원 라디오 선택
		$('input:radio[name="custmrClassify"]').change(function(){
		    window.location="/callOrderCreateRead.do?custmrClassify="+$(this).val();
		});
		
		 //배송지 선택시 배송지 내용 조회
	    $('#shipngPlc').change(function(){
	    	var shipngPlc= document.getElementById('shipngPlc').value;
	    	var shipngPlcSeq = {"shipngPlcSeq" : shipngPlc};
     
		 	$.ajax({ 		 		
			type:"POST",
			url:"/shipngPlcRead.do",
			data:shipngPlcSeq,
			success:function(data){
				document.getElementById('sample6_address').value = data.shipngPlcAdrs;
				document.getElementById('sample6_postcode').value = data.shipngPlcPostcod;
				document.getElementById('sample6_address2').value = data.shipngPlcDetalAdrs;			
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	});
	    
	  //체크박스 일괄 선택
  	 $("#allCheck").click(function(){
	        //클릭되었으면
	        if($("#allCheck").prop("checked")){
	            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
	            $("input[name=checkbox]").prop("checked",true);
	            //클릭이 안되있으면
	        }
	        else{
	            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
	            $("input[name=checkbox]").prop("checked",false);
	        }
	});
  	 
	//상품 리스트 일괄 삭제
  	 $('#deleteProdct').click(function(){
	    	var chked_length = $("input[name=checkbox]:checked").length; //체크된 박스 개수
	    	if (confirm("총 " + chked_length + "개의 항목을 삭제하시겠습니까?") == true){    //확인
	    		var chked_val = "";
				$(":checkbox[name='checkbox']:checked").each(function(pi,po){
					chked_val += ","+po.value;
				});
				if(chked_val!="")chked_val = chked_val.substring(1);
				if(chked_val == ""){
					alert("삭제할 상품을 선택해주십시오.")
				}
				else{
					$.ajax({
						type:"POST",
						url:"/callOrderProdctDelete.do",
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
 
	});
	
	// 날짜, 시간 선택 위젯 스크립트
	$(function(){
	  $('.datetimepicker').appendDtpicker({'locale':'ko'});
	});
	
	// 주소 api
	function sample6_execDaumPostcode() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var fullAddr = ''; // 최종 주소 변수
	            var extraAddr = ''; // 조합형 주소 변수
	
	            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                fullAddr = data.roadAddress;
	
	            } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                fullAddr = data.jibunAddress;
	            }
	
	            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
	            if(data.userSelectedType === 'R'){
	                //법정동명이 있을 경우 추가한다.
	                if(data.bname !== ''){
	                    extraAddr += data.bname;
	                }
	                // 건물명이 있을 경우 추가한다.
	                if(data.buildingName !== ''){
	                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
	                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
	            }
	
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
	            document.getElementById('sample6_address').value = fullAddr;
	
	            // 커서를 상세주소 필드로 이동한다.
	            document.getElementById('sample6_address2').focus();
	        }
	    }).open();
	}

	// 회원 선택 모달창
	function custmrChoicePopup() {
		var defH, defW, sTop, sLeft, url;
		defW = 600;
		defH = 750;
		sTop = (screen.height - defH)/2;
		sLeft= (screen.width  - defW)/2;
		url = "/custmrChoicePopup.do";
		popWin = window.open(url, "고객 상세보기", "width="+defW+", height="+defH+", top="+sTop+",left="+sLeft+", scrollbars=yes, marginwidth=0, marginheight=0");
		
		if(win == null) {
			alert("팝업차단을 해제해주세요!");
		}
		else {
			win.focus();
		}
	};
	
	// 회원 선택 후 회원정보 조회
	function custmrChoice() {
	 	document.custmrSechFrm.action = "/callOrderCreateRead.do";
	 	document.custmrSechFrm.method = "post";
	 	document.custmrSechFrm.submit();
	}
	
	// 상품 추가 팝업창
	function prodctCreate()
	{
		var popUrl = "callOrdrProdctCreatePopup.do";//팝업창에 출력될 페이지 URL
		var popupName= "callOrdrProdctCreatePopup";
		var width = 1300;
		var height = 650;
		var leftPosition = (screen.width/2) - width / 2;
		var topPosition = (screen.height/2) - height / 2;
		var popOption;
		var win = window.open(popUrl, popupName, "left="+leftPosition+",top="+topPosition+",width="+width+",height="+height+", toolbars=no, resizable=no, scrollbars=no");
		
		$('#allDiv').after("<div class='modal-backdrop fade in' style='bottom: 0; z-index:1040;' id='FadeIn'></div>");
	
		if(win == null) {//만약 팝업 차단으로 팝업창이 나타나지 않을 경우
			alert("팝업차단을 해제해주세요!");
		}
		else {
			win.focus();
		}
	}	
	
	// 상품 추가 창 닫기 버튼 클릭시
	function prodctCancel(){
		window.opener.location.reload();
		window.close();
		window.opener.$("#FadeIn").remove();
	};
	
	// 상품 추가 창 엑스 버튼 클릭시
	function closeIt(){
	    self.close();
	    window.opener.$("#FadeIn").remove();
	 };
	 
	 // 전화주문 최종 등록
	 function callOrderCreate() {
	    if ($("#custmrId").val() == ""){
	        alert("아이디를 입력해주세요.");
	        $("#custmrId").focus();
	         return;
	     } 
	    else if($("#recvrNme").val() == ""){
	        alert("이름을 입력해주세요.");
	        $("#recvrNme").focus();
	         return;
	     }  
	    else if($("#recvrMobl").val() == ""){
	        alert("연락처를 입력해주세요.");
	        $("#recvrMobl").focus();
	         return;
	     }  
	    else if($("#recvrPostcd").val() == ""){
	        alert("우편번호를 입력해주세요.");
	        $("#recvrPostcd").focus();
	         return;
	     } 
	    else if($("#sample6_address").val() == ""){
	        alert("주소를 입력해주세요.");
	        $("#sample6_address").focus();
	         return;
	     } 
	    else if($("#sample6_address2").val() == ""){
	        alert("상세주소를 입력해주세요");
	        $("#sample6_address2").focus();
	         return;
	     } 
	    else if($("#recvrMethd").val() == ""){
	        alert("수령방법을 써주세요.");
	        $("#recvrMethd").focus();
	         return;
	     } 
	    else if($("#pamntMethd").val() == ""){
	        alert("결제방법을 써주세요.");
	        $("#pamntMethd").focus();
	         return;
	     } 
		 
		var formData = $("#custmrSechFrm").serialize();
	 	$.ajax({ 		 		
			type:"POST",
			url:"/callOrderCreate.do",
			data:formData,
			success:function(data){
		  		var listLength = document.custmrSechFrm.listLength.value;
		  		var prodctSeq_arry = new Array();
		  		var prodctNumber_arry = new Array();
		  		
				for(var i =1; i<listLength; i++) {
					var prodctSeq = document.getElementById('prodctSeq'+i).value;
					var prodctNumber = document.getElementById('prodctNumber'+i).value;
					
					prodctSeq_arry.push(prodctSeq);
					prodctNumber_arry.push(prodctNumber);
			
				}
				document.custmrSechFrm.prodctNumber_arry.value = prodctNumber_arry;
				document.custmrSechFrm.prodctSeq_arry.value = prodctSeq_arry;
				document.custmrSechFrm.action = "/ callOrderProdctTempUpdate.do";
				document.custmrSechFrm.method = "post";
				document.custmrSechFrm.submit();
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	 }
	  
	// 상품 리스트 수량 입력시
	function prodctNumber(status) {			  		
		var prodctNumber = parseInt(document.getElementById('prodctNumber'+status).value);
		var prodctSelPric = parseInt(document.getElementById('prodctSelPric'+status).value);
		var allPrice =parseInt( document.getElementById('allPrice'+status).value);
		var won =$("h3").val();
		
		document.getElementById('allPrice'+status).value =parseInt(prodctNumber*prodctSelPric);
		document.getElementById('won').value =parseInt(prodctNumber*prodctSelPric);
	};
	