//	거래처 상세보기 페이징
function clintReadPaging(page) {
//	var clintSeq = document.clintReadFrm.clintSeq.value();
	document.clintReadFrm.currentPageNo.value = page;
	document.clintReadFrm.action ="/ClintRead.do";
	document.clintReadFrm.submit();
	}

// 상세조회된 거래처 수정
	function clintReadUpdate(){
		document.clintReadFrm.action="/ClintUpdateRead.do";
		document.clintReadFrm.submit();
	}
// 상세조회된 거래처 삭제
	function clintReadDelete(clintSeq){
		var yes = confirm("정말 삭제하시겠습니까?");
		if(yes){
			location.href="/ClintReadDelete.do?clintSeq="+clintSeq;
		}
	}
// 입고거래장 팝업창  
	function instckExchngFlorPopup(clintSeq){
		var popupUrl="/InstckExchngFlorPopup.do?clintSeq="+clintSeq;
		var popupOption = "width=800, height=500, scrollbars=yes, menubar=yes, location=yes";
		window.open(popupUrl, "popup", popupOption);
	}
// 입고내역 팝업창
	function instckRecrdListPopup(clintSeq){
		var popupUrl = "/instckRecrdListPopup.do?clintSeq="+clintSeq;
		var popupOption = "width=800, height=500, scrollbars=yes, menubar=yes, location=yes";
		window.open(popupUrl, "popup", popupOption);
	}
	
