var empSeq;
var empId;
var empPw;
var custmrSeq;
var custmrId;
var custmrPw;

$(document).on('pageshow', function(event, data){ //"cusMainPage" 라는 page가 로딩됐을 때 실행이 되는 함수
	if ($.mobile.activePage.attr('id') == "cusMainPage") {	
		if(localStorage.getItem('custmrSeq')!=null){
			custmrSeq = localStorage.getItem('custmrSeq');
			custmrId = localStorage.getItem('custmrId');
			custmrPw = localStorage.getItem('custmrPw');
		}
		else if(sessionStorage.getItem('custmrSeq')!=null){
			custmrSeq = sessionStorage.getItem('custmrSeq');
			custmrId = sessionStorage.getItem('custmrId');
			custmrPw = sessionStorage.getItem('custmrPw');
		}
	}
	if ($.mobile.activePage.attr('id') == "empMainPage") {	
		if(localStorage.getItem('empSeq')!=null){
			empSeq = localStorage.getItem('empSeq');
			empId = localStorage.getItem('empId');
			empPw = localStorage.getItem('empPw');
		}
		else if(sessionStorage.getItem('empSeq')!=null){
			empSeq = sessionStorage.getItem('empSeq');
			empId = sessionStorage.getItem('empId');
			empPw = sessionStorage.getItem('empPw');
		}
	}
});


$(document).on('pageshow', function(event, data){
	// 고객 메인
	if ($.mobile.activePage.attr('id') == "cusMainPage") {	
		$('#ordrRecrdCard').click(function(){
			$.mobile.changePage("MyPageCusOrdrList.html");
		});

		$('#cartCard').click(function(){
			$.mobile.changePage("MyPageCusCart.html");
		});

		$('#eventProdctCard').click(function(){
			$.mobile.changePage("MarketCusDebecFestival.html");
		});

		$('#freshFodCard').click(function(){
			$.mobile.changePage("MarketCusDebecChoiceMain.html");
		});

		$('#hotdlCard').click(function(){
			$.mobile.changePage("MarketCusDebecChoiceMain.html");
		});
			
		$('#packgCard').click(function(){
			$.mobile.changePage("MarketCusDebecChoiceMain.html");
		});

		$('#onlyYouCard').click(function(){
			$.mobile.changePage("MarketCusOnlyYou.html");
		});

		$('#dahamgaeCard').click(function(){
			$.mobile.changePage("MarketCusDahamgae.html");
		});
	}
	
	// 직원 메인
	else if ($.mobile.activePage.attr('id') == "empMainPage") {	
		$('#ordrRecrdCard').css('display','none');
		$('#cartCard').css('display','none');
		
		$('#eventProdctCard').click(function(){
			$.mobile.changePage("MarketEmpDebecFestival.html");
		});

		$('#freshFodCard').click(function(){
			$.mobile.changePage("MarketCusDebecChoiceMain.html");
		});

		$('#hotdlCard').click(function(){
			$.mobile.changePage("MarketCusDebecChoiceMain.html");
		});
			
		$('#packgCard').click(function(){
			$.mobile.changePage("MarketCusDebecChoiceMain.html");
		});

		$('#onlyYouCard').click(function(){
			$.mobile.changePage("MarketEmpOnlyYou.html");
		});

		$('#dahamgaeCard').click(function(){
			$.mobile.changePage("MarketEmpDahamgae.html");
		});
	}
});

function homeReturn(){
	alert(empSeq + " : empSeq");
	alert(custmrSeq + " : custmrSeq");
	if(empSeq != undefined){
		alert("직원화면");
		$.mobile.changePage("MainEmpFrame.html");
	}
	else if(custmrSeq != undefined){
		alert("고객화면");
		$.mobile.changePage("MainCusFrame.html");
	}
}
