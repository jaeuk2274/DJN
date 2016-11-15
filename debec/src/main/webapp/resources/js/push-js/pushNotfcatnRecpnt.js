//푸시 알림 제목 입력
function insertPushTitl(){
	$('#pushViewTitl').html($('#sendTitl').val());
}
//푸시 알림 내용 입력
function insertPushContent(){
	$('#pushViewMesg').html($('#sendMesg').val());
}

var childWindow;
//푸시 수신자 전체 고객 리스트 조회 팝업
function pushRecpnt(){
	var popUrl = "pushNotfcatnRecpntAllPopup.do";
	var popupName= "pushNotfcatnRecpntAllPopup";
	var width = 1200;
	var height = 800;
	var leftPosition = (screen.width/2) - (width/2);
		leftPosition += window.screenLeft;//듀얼 모니터일때
	var topPosition = (screen.height/2) - (height/2);
	
	childWindow = window.open(popUrl, popupName, "left="+leftPosition+", top="+topPosition+", toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, width="+width+", height="+height);
	
	$('#mainDiv').after("<div class='modal-backdrop fade in' style='bottom: 0; z-index:1040; height: 1230px;' id='FadeIn' onclick='fadeInFocus()'></div>");
	
	if(childWindow == null) {//만약 팝업 차단으로 팝업창이 나타나지 않을 경우
       alert("팝업차단을 해제해주세요!");
    }
    else {
    	childWindow.focus();            
    }
}

//푸시 알림 발송 내역 다시 보내기 팝업
function pushDespRecrdRetry(despRecrdSeq){
	var popUrl = "pushNotfcatnDespRecrdDetlPopup.do?despRecrdSeq="+despRecrdSeq;
	var popupName= "pushNotfcatnDespRecrdDetlPopup";
	var width = 1200;
	var height = 820;
	var leftPosition = (screen.width/2) - (width/2);
		leftPosition += window.screenLeft;//듀얼 모니터일때
	var topPosition = (screen.height/2) - (height/2);
	
	childWindow = window.open(popUrl, popupName, "left="+leftPosition+", top="+topPosition+", toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, width="+width+", height="+height);
	
	$('#mainDiv').after("<div class='modal-backdrop fade in' style='bottom: 0; z-index:1040; height: 1230px;' id='FadeIn' onclick='fadeInFocus()'></div>");
	
	if(childWindow == null) {//만약 팝업 차단으로 팝업창이 나타나지 않을 경우
       alert("팝업차단을 해제해주세요!");
    }
    else {
    	childWindow.focus();            
    }
}

//푸시알림 불러오기 템플릿 팝업
function pushImprtTempltListPopup() {
	//푸시 수신자 전체 고객 리스트 조회 팝업
	var popUrl = "pushImprtTempltListPopup.do?searchType=0";
	var popupName= "푸시 알림 발송 - 불러오기";
	var width = 800;
	var height = 800;
	var leftPosition = (screen.width/2) - (width/2);
		leftPosition += window.screenLeft;//듀얼 모니터일때
	var topPosition = (screen.height/2) - (height/2);
	
	childWindow = window.open(popUrl, popupName, "left="+leftPosition+", top="+topPosition+", toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, width="+width+", height="+height);
	
	$('#mainDiv').after("<div class='modal-backdrop fade in' style='bottom: 0; z-index:1040; height: 1230px;' id='FadeIn' onclick='fadeInFocus()'></div>");
	    
    if(childWindow == null) {//만약 팝업 차단으로 팝업창이 나타나지 않을 경우
       alert("팝업차단을 해제해주세요!");
    }
    else {
    	childWindow.focus();            
    }
}

function fadeInFocus(){
	childWindow.focus();
}

//푸시 수신자 상품 팝업
function pushRecpntProdctPopup(){
	document.pushDespRecrdFrm.action="/pushNotfcatnRecpntProdctPopup.do";
	document.pushDespRecrdFrm.submit();
}
//푸시 카테고리 리스트 조화
function pushRecpntCatgrPopup(){
	document.pushDespRecrdFrm.action="/pushRecpntCatgrPopup.do";
	document.pushDespRecrdFrm.submit();
}
function AllChoc(){
	$("#PushRecpntAllcustmrId").val();
	$("#PushRecpntAllcustmrNme").val();
	$("#PushRecpntAllordrNumbrSeq").val();
	$("#PushRecpntAllselPric").val();
	$("#PushRecpntAllcustmrToken").val();
}

//푸시 상품선택 고객 조회
function prodctChoc(prodctSeq) {
	alert("테스트");
	document.getElementById("prodctSeq").value = prodctSeq;
	var formData = $("#pushRecpntProdctFrm").serialize();
	$.ajax({
      type: "POST",
      url: "/pushRecpntProdctCustmr.do",
      data: formData,
      success: function(data){//이페이지에서 중복체크를 한다
    	var pushRecpntProdctCustmrList = data["pushRecpntProdctCustmr"];
    	var pushVoRecpntProdctCustmr = data["pushVoRecpntProdctCustmr"];

    	document.getElementById('pushRecpntProdctCustmrList').innerHTML = pushRecpntProdctCustmrList;
    	document.getElementById('pushVoRecpntProdctCustmr').innerHTML = pushVoRecpntProdctCustmr;   
      },
       error:function(xhr,status,error)
       { 
          alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
       }
  });
}
//푸시 패키지 리스트 조화
function pushRecpntPackgPopup(){
	document.pushDespRecrdFrm.action="/pushRecpntPackgPopup.do";
	document.pushDespRecrdFrm.submit();
}
//푸시 패키지 신청 고객
function pushRecpntPackgAplctnCustmrChoc(packgSeq) {
	document.getElementById("packgSeq").value = packgSeq;
	var formData = $("#pushRecpntPackgFrm").serialize();
	$.ajax({
      type: "POST",
      url: "/pushRecpntPackgAplctnCustmr.do",
      data: formData,
      success: function(data){//이페이지에서 중복체크를 한다
		var pushRecpntPackgCustmr = data["pushRecpntPackgCustmr"];
		var pushRecpntPackgPushVo = data["pushRecpntPackgPushVo"];

		document.getElementById('pushRecpntPackgCustmr').innerHTML = pushRecpntPackgCustmr;
    	document.getElementById('pushRecpntPackgPushVo').innerHTML = pushRecpntPackgPushVo;
      },
      error:function(xhr,status,error)
      { 
         alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
      }
  });
}

//푸시 공용 구매 리스트 조화
function pushRecpntGropPurchsPopup(){
	document.pushDespRecrdFrm.action="/pushRecpntGropPurchsPopup.do";
	document.pushDespRecrdFrm.submit();
}
//푸시 공용구매 신청 고객
function pushRecpntGropPurchsAplctnCustmr(gropPurchsSeq) {
	alert("값뜨는지 확인"+gropPurchsSeq);
  	document.getElementById("gropPurchsSeq").value = gropPurchsSeq;
  	var formData = $("#pushRecpntGropPurchsFrm").serialize();
  	$.ajax({
    type: "POST",
    url: "/pushRecpntGropPurchsAplctnCustmr.do",
    data: formData,
    success: function(data){//이페이지에서 중복체크를 한다
    	var pushRecpntGropPurchsAplctnCustmr = data["pushRecpntGropPurchsAplctnCustmr"];
		var pushRecpntGropPurchsAplctnCustmrVo = data["pushRecpntGropPurchsAplctnCustmrVo"];

		document.getElementById('pushRecpntGropPurchsAplctnCustmr').innerHTML = pushRecpntGropPurchsAplctnCustmr;
		document.getElementById('pushRecpntGropPurchsAplctnCustmrVo').innerHTML = pushRecpntGropPurchsAplctnCustmrVo;
      },
       error:function(xhr,status,error)
       { 
          alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
       }
  });
}

//체크 되어 있는 값 추출
function AllChoc(){
	$("input[name=pushAllCheck]:checked").each(function() {
		var test = $(this).val();
		$("#custmrTokenPush").val(test);
		alert("테스트"+test);
	});
	opener.parent.location.reload();
};

//템플릿 내용  추가
function insertPushTemplt(pushTitl,pushMesg,pushHedln,regstrtnDat){
	$('#sendTitl').val(pushTitl);
	$('#sendMesg').val(pushMesg);
	$('#sendHedln').val(pushHedln);
	$('#pushTemplt').val(pushTitl+" ("+regstrtnDat+")");
	$('#pushViewTitl').html(pushTitl);
	$('#pushViewMesg').html(pushMesg);
}

//총 인원수 추가
function insertRecpnt(data){
	$('#custmrCount').html("총 " + data + " 명");
}

//푸시 내역 추가
function insertPushHistory(){
	var formData = "pushTitl="+$('#sendTitl').val()
					+ "&pushMesg="+$('#sendMesg').val()
					+ "&pushUrl="+$('#sendUrl').val()
					+ "&pushHedln="+$('#sendHedln').val();
	$.ajax({
		type: "POST",
		data : formData,
		url: "/pushHistoryCreate.do",
		success: function(data){	
		}
	});
}

//푸시 발송
function insertPush(){
	insertPushHistory();
	document.pushDespRecrdFrm.action="/pushDespList.do";
	document.pushDespRecrdFrm.submit();
	
	alert("푸시 알림 발송이 완료되었습니다.");
}

function closeIt(){
	opener.parent.location.href = "/pushNotfcatnDespRecrdList.do"
    window.opener.$("#FadeIn").remove();
    self.close();
}

//푸시 재발송
function insertPushRetry(){
	insertPushHistory();
	var formData = $('#pushDespRecrdFrm').serialize();
	$.ajax({
		type: "POST",
		data : formData,
		url: "/rePushDespList.do",
		success: function(msg){	
			if(msg == "flag"){
				closeIt();
			}
			else{
				closeIt()
			}
		}
	});
}


//작성내용 저장
function insertPushDesp(){
	insertPushHistory();
	alert("현재 작성 중인 푸시 알림이 저장되었습니다.");
	location.reload();
}

//푸시 상세조회
function selectPushDesp(seq){
	$('#pushTemplt').val('선택 안함');
	if (seq == '0'){
		$('#sendTitl').val('');
		$('#sendHedln').val('');
		$('#sendMesg').val('');
		$('#sendUrl').val('');
		$('#pushViewTitl').html("푸시팝업 제목을 입력해주세요.");
		$('#pushViewMesg').html("푸시팝업 내용을 입력해주세요.");
	}
	else{
		$.ajax({
			type: "POST",
			data : {"pushSeq":seq},
			url: "/pushHistoryDetlRead.do",
			error: function(){
				alert("Select failed.");
			},
			success: function(data){	
				$('#sendTitl').val(data.pushTitl);
				$('#sendHedln').val(data.pushHedln);
				$('#sendMesg').val(data.pushMesg);
				$('#sendUrl').val(data.pushUrl);
				$('#pushViewTitl').html(data.pushTitl);
				$('#pushViewMesg').html(data.pushMesg);
			}
		});
	}
}