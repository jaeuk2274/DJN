var childWindow;
	
function pushTempltInsertPopup(){
	var popUrl = "pushTempltInsertPopup.do";
	var popupName= "pushTempltInsertPopup";
	var width = 1200;
	var height = 660;
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
}

function fadeInFocus(){
	childWindow.focus();
}

function allChecking(){
    var aBox = pushTemplt.templtCheck;
    if(aBox.length) {  // 여러 개일 경우
        for(var i = 0; i<aBox.length;i++) {
        	aBox[i].checked=pushTemplt.allCheck.checked;
        }
    } 
    else { // 한 개일 경우
    	aBox.checked=pushTemplt.allCheck.checked;
	}
}


function pushTempltDelete(){
	var chked_length = $("input[name=templtCheck]:checked").length; //체크된 박스 개수
	if(chked_length == 0){
		alert("삭제할 템플릿을 선택해주십시오.")
	}
	else{
		if (confirm("총 " + chked_length + "개의 항목을 삭제하시겠습니까?") == true){    //확인
    		var chked_val = "";
			$(":checkbox[name='templtCheck']:checked").each(function(pi,po){
				chked_val += ","+po.value;
			});
			if(chked_val!="")chked_val = chked_val.substring(1);
			$.ajax({
					type:"POST",
					url:"/pushTempltDelete.do",
					data:chked_val,
					success:function(){
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
}    


function pushNotfcatnTempltSelectDetlPopup(seq) {
	var popUrl = "pushNotfcatnTempltSelectDetlPopup.do?pushTempltSeq="+seq;
	var popupName= "pushNotfcatnTempltSelectDetlPopup";
	var width = 1200;
	var height = 660;
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
}
//템플릿 검색
function pushTempltSearch(page){
	var searchWrd = document.getElementById("searchWrd").value;
	var searchCnd = document.getElementById("searchCnd").value;
	
	document.pushListInfoFrm.currentPageNo.value = page;
	document.pushListInfoFrm.searchCnd.value = searchCnd;
	document.pushListInfoFrm.searchWrd.value = searchWrd;
	
	document.pushListInfoFrm.action = "/pushNotfcatnTempltSelectList.do";
	document.pushListInfoFrm.submit();
	
//	var formData = $('#pushListInfoFrm').serialize();
//	$.ajax({
//		type : "POST",
//		url : "/pushNotfcatnTempltSelectList.do",
//		data : formData,
//		success: function(data) {
//			document.body.innerHTML = data;
//		}
//	});
}	

// 페이징
function pushTempltPaging(page) {
	document.pushListInfoFrm.currentPageNo.value = page;
	
	document.pushListInfoFrm.action = "/pushNotfcatnTempltSelectList.do";
	document.pushListInfoFrm.submit();
	
//	var formData = $('#pushListInfoFrm').serialize();
	
//	$.ajax({
//		type: "POST",
//		url: "pushNotfcatnTempltSelectList.do",
//		data: formData,
//		success: function(data){	
//	       	document.body.innerHTML = data;   
//		}
//	});
}

function templtInsertDsply(){
	var popUrl = "/pushNotfcatnTempltInsertPopup.do";	//팝업창에 출력될 페이지 URL
	var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
	window.open(popUrl,"",popOption);
}