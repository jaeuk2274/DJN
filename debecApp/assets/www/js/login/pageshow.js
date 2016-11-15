function sessionCheck(){
	if(empSeq == null){
		// 고객 오른쪽 패널
		cusRightPanelOrdrInfo();
		cusRightPanelCartList();
		cusRightPanelHistoryList();
		cusRightPanelEmpList();
		   
		cusLeftPanelEventProdctList();
		cusLeftPanelTopCatgrList();
		cusLeftPanelNme();
	}
	else{
		empLeftPanelEventProdctList();
		empLeftPanelTopCatgrList();
		
		empRightPanelOrdrInfo();
		empRightPanelEmpList();
		empLeftPanelNme();
	}
}

//뒤로가기 앱종료
function onBackKeyDown() {
	navigator.app.exitApp();
}

//index.html 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#empMainPage",function(){
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}

	document.addEventListener("backbutton", onBackKeyDown, false);

});
//배송목록 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#empShipngListPage",function(){
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
});
//실시간주문목록 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#empRealTimeOrdrListPage",function(){
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
});
//주문취소목록 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#empOrdrCancelListPage",function(){
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
});
//커뮤니티 리스트 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#communityListPage",function(){
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
});

//다함께 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#empDahamgaePage",function(){
	sessionCheck();
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
});

//다함께 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#cusDahamgaePage",function(){
	sessionCheck();
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
});

//다함께 상세 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#dahamgaeReadPage",function(){
	sessionCheck();
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
});
//대백제 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#debecjaePage",function(){
	sessionCheck();
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
});
//대백제 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#DebecFestivalPage",function(){
	sessionCheck();
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
});
//너만상 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#onlyYouPage",function(){
	sessionCheck();
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
});
//대백초이스 리스트 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#empDebecChoiceMainPage",function(){
	sessionCheck();
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
});
//대백초이스 리스트 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#cusDebecChoiceMainPage",function(){
	sessionCheck();
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
});
//내 레시피 리스트 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#myRecpListPage",function(){
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
});
//레시피 리스트 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#recpListPage",function(){
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
});
//고객 메인 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#cusMainPage",function(){
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
	document.addEventListener("backbutton", onBackKeyDown, false);
});

//상품 상세조회에서 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#prodctReadPage",function(){
	sessionCheck();
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
});

//검색 상품 리스트에서 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#SearchProdctListPage",function(){
	sessionCheck();
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
});
//고객 주문목록 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#cusOrdrListPage",function(){
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
});
//고객 주문취소목록 페이지의 탭 부분 리로딩하는 스크립트
$(document).on("pageshow","#cusOrdrCancelListPage",function(){
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}	
});