<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 
* 푸시알림 템플릿을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        이정호, 1.0, 2016/08/11 – 초기 작성
* author : 이정호
* version : 1.1, 2016/08/15  - 페이징 추가
* see : 관련된 모듈을 기술한다.
//-->

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	
	<!-- 부가적인 테마 -->
	<link href="/resources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>

	<!-- JQuery 관련 파일들 -->
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>	
	
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
	
	<!-- JSTree 관련 파일들 -->
	<script src="/resources/js/jstree/jstree.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="/resources/js/jstree/themes/proton/style.min.css">
	
	<script type="text/javascript">
	function closeIt(){
	    window.opener.$("#FadeIn").remove();
	    self.close();
	}; 
	</script>
	<script type="text/javascript">
	function insertPushTemplt(pushTitl,pushMesg,pushHedln,regstrtnDat){
		opener.insertPushTemplt(pushTitl,pushMesg,pushHedln,regstrtnDat);
		closeIt();
	}
	
	//템플릿 검색
	function pushTempltSearch(page){
		var searchWrd = document.getElementById("searchWrd").value;
		var searchCnd = document.getElementById("searchCnd").value;
		
		document.pushListInfoFrm.currentPageNo.value = page;
		document.pushListInfoFrm.searchCnd.value = searchCnd;
		document.pushListInfoFrm.searchWrd.value = searchWrd;
		
		var formData = $('#pushListInfoFrm').serialize();
		$.ajax({
			type : "POST",
			url : "/pushImprtTempltTable.do",
			data : formData,
			success: function(data) {
		       	$('#templt').html(data); 
			}
		});
	}
	
	//발송내역 검색
	function pushDespSearch(page){
		var searchWrd = document.getElementById("searchWrd2").value;
		var searchCnd = document.getElementById("searchCnd2").value;
		
		document.pushListInfoFrm2.currentPageNo.value = page;
		document.pushListInfoFrm2.searchCnd.value = searchCnd;
		document.pushListInfoFrm2.searchWrd.value = searchWrd;
		
		var formData = $('#pushListInfoFrm2').serialize();
		$.ajax({
			type : "POST",
			url : "/pushImprtTempltDespTable.do",
			data : formData,
			success: function(data) {
		       	$('#desp').html(data);
			}
		});
	}
	
	// 페이징
	function pushTempltPaging(page) {
		document.pushListInfoFrm.currentPageNo.value = page;
		
		var formData = $('#pushListInfoFrm').serialize();
		
		$.ajax({
			type: "POST",
			url: "pushImprtTempltTable.do",
			data: formData,
			error: function(){
				alert("Select failed.");
			},
			success: function(data){	
		       	$('#templt').html(data);  
			}
		});
	}
	
	// 페이징
	function pushDespPaging(page) {
		document.pushListInfoFrm2.currentPageNo.value = page;
		
		var formData = $('#pushListInfoFrm2').serialize();
		
		$.ajax({
			type: "POST",
			url: "pushImprtTempltDespTable.do",
			data: formData,
			error: function(){
				alert("Select failed.");
			},
			success: function(data){	
		       	$('#desp').html(data);   
			}
		});
	}
	
	//템플릿 체크박스 전체체크
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
	
	//발송내역 체크박스 전체체크
	function allChecking2(){
	    var aBox = pushDesp.despCheck;
	    if(aBox.length) {  // 여러 개일 경우
	        for(var i = 0; i<aBox.length;i++) {
	        	aBox[i].checked=pushDesp.allCheck2.checked;
	        }
	    } 
	    else { // 한 개일 경우
	    	aBox.checked=pushDesp.allCheck2.checked;
		}
	}
	</script>
</head>
<body onBeforeUnload="closeIt()">
	<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
		<h3>푸시 알림 불러오기</h3>
	</div>
	
	<div class="form-group"></div>
	
	<div class="container form-inline">
		<ul id="tabs" class="nav nav-pills" data-tabs="tabs">
			<li id="templtLi" class="active"><a href="#templt" data-toggle="tab">템플릿</a></li>
			<li id="despLi" ><a id="despA" href="#desp" data-toggle="tab">발송내역</a></li>
		</ul>
		<div class="tab-content">
			<div id="templt" class="tab-pane active">
				<c:import url="/pushImprtTempltTable.do"></c:import>
			</div>
			
			<div id="desp" class="tab-pane">
				<c:import url="/pushImprtTempltDespTable.do"></c:import>
			</div>
		</div>
	</div>
</body>
</html>