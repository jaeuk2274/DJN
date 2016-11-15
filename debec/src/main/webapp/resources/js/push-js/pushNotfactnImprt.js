var childWindow2;
//푸시알림 불러오기 템플릿 팝업
function pushImprtTempltListPopup() {
	//푸시 수신자 전체 고객 리스트 조회 팝업
	var popUrl = "pushImprtTempltListPopup.do";
	var popupName= "푸시 알림 발송 - 불러오기";
	var width = 1200;
	var height = 700;
	var leftPosition = (screen.width/2) - (width/2);
		leftPosition += window.screenLeft;//듀얼 모니터일때
	var topPosition = (screen.height/2) - (height/2);
	
	childWindow2 = window.open(popUrl, popupName, "left="+leftPosition+", top="+topPosition+", toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, width="+width+", height="+height);
	
	$('#mainDiv').after("<div class='modal-backdrop fade in' style='bottom: 0; z-index:1040;' id='FadeIn' onclick='fadeInFocus2()'></div>");
	    
    if(childWindow2 == null) {//만약 팝업 차단으로 팝업창이 나타나지 않을 경우
       alert("팝업차단을 해제해주세요!");
    }
    else {
    	childWindow2.focus();            
    }
}

function fadeInFocus2(){
	childWindow2.focus();
}