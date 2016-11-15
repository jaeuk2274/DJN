<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 
* 입고거래장 목록을 팝업으로 보여주는 JSP입니다.
* 
* history :
*        김대현, 1.0, 2016/08/11 – 초기 작성
* author : 김대현
* version : 1.0, 2016/08/09  - 초기 작성
* see : 관련된 모듈을 기술한다.
// -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>대신 장봐주는 남자 - 대.장.남</title>
		
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
	</head>
	<body>
		<div style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
			<h3>입고거래장 목록</h3>
		</div>
		
		<div class="form-group"></div>
		
		<div class="container form-inline">
			<form name="instckPagingFrm" method="post">      
	   			<input type="hidden" name="beginDatepicker" value="${instckVo.beginDatepicker}">		<!-- 검색조건 -->
	   			<input type="hidden" name="endDatepicker" value="${instckVo.endDatepicker}">	<!--  검색조건 -->
				<input type="hidden" name="currentPageNo" value="${instckVo.currentPageNo}"> <!-- 현재 페이지 -->
				<input type="hidden" name="clintSeq" value="${instckVo.clintSeq}">	<!-- 거래처 seq -->
				<input type="hidden" name="instckExchngFlorSeq" value="0">	<!-- 입고거래장 seq -->
	      	</form>
	      	
			<table class="table table-bordered">
				<tr>
					<td class="active">
						<b>등록일</b>
					</td>
					<td>
						<!-- 검색 -->
						<div class="form-group" style="z-index:100;">
							<input type="text" class="form-control" id="beginDatepicker" name="beginDatepicker">
							&nbsp;&nbsp; ~ &nbsp;&nbsp;
							<input type="text" class="form-control" id="endDatepicker" name="endDatepicker">
						</div>
					</td>
				</tr>
			</table>
		</div>
			
		<div class="container text-center">
			<input type="button" class="btn btn-default" value="조회" onclick="javascript:instckSech(${instckVo.clintSeq})">
		</div>
		
		<div class="form-group"></div>
			
		<div class="container">
			<table  class="table table-bordered">
				<tr class="active">
					<td class="text-center">
						<b>No</b>
					</td>
					<td class="text-center">
						<b>제목</b>
					</td>
					<td class="text-center">
						<b>거래금액</b>
					</td>
					<td class="text-center">
						<b>등록일</b>
					</td>
				</tr>
				<c:choose>
					<c:when test="${fn:length(instckList) == 0}">
						<tr class="text-center">
							<td colspan="6">
								<h4>등록된 입고 거래장이 없습니다</h4>
							</td>
						</tr>
					</c:when>
					<c:when test="${fn:length(instckList) != 0}">
						<c:forEach items="${instckList}" var="instck" varStatus="status">
							<tr onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
								<td class="text-center" onclick="javascript:instckExchngFlorReadPopup(${instck.instckExchngFlorSeq})">
									${instckVo.totalRecordCount-instckVo.firstRecordIndex-status.index}
								</td>
								<td class="text-center" onclick="javascript:instckExchngFlorReadPopup(${instck.instckExchngFlorSeq})">
									${instck.instckExchngFlorTitl}
								</td>
								<td class="text-center" onclick="javascript:instckExchngFlorReadPopup(${instck.instckExchngFlorSeq})">
									<fmt:formatNumber value="${instck.instckExchngFlorPric}" type="number"/>
								</td>
								<td class="text-center" onclick="javascript:instckExchngFlorReadPopup(${instck.instckExchngFlorSeq})">
									${instck.instckExchngFlorRegstrtnDat}
								</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</table>
		</div>
			
		<!-- 페이징 -->
		<div class="container text-center">
			<nav>
				<ul class="pagination">
					<!-- 무조건 1페이지로 << 버튼 -->
					<c:if test = "${instckVo.currentPageNo>20}">
						<li>
							<a href="javascript:instckPaging(1)" aria-label="Previous" style="z-index:1;">
							<span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>  
					<!-- 한 단위 앞으로 < 버튼 -->
					<c:if test = "${instckVo.currentPageNo>10}">
						<li>
							<a href="javascript:instckPaging(${instckVo.firstPageNoOnPageList-1})" aria-label="Previous" style="z-index:1;">
							<span aria-hidden="true">&lsaquo;</span></a>
						</li>
					</c:if> 	
					<!-- 	페이징 -->
					<c:forEach varStatus="status" begin="${instckVo.firstPageNoOnPageList}" end="${instckVo.lastPageNoOnPageList}">
					
						<c:if test="${instckVo.currentPageNo==status.current}">
							<li class="active">
								<a href="javascript:instckPaging(${status.current}, ${instckVo.clintSeq})" style="z-index:1;">${status.current}</a>
							</li>
						</c:if>
						
						<c:if test="${instckVo.currentPageNo!=status.current}">
							<li>
								<a href="javascript:instckPaging(${status.current}, ${instckVo.clintSeq})" style="z-index:1;">${status.current}</a>
							</li>
						</c:if>   
						                									
					</c:forEach>	
					
					<!-- 한 단위 뒤로 > 버튼 -->
					<c:if test = "${instckVo.firstPageNoOnPageList + 10 < instckVo.totalPageCount}">
						<li>
							<a href="javascript:instckPaging(${instckVo.lastPageNoOnPageList+1})" aria-label="Next" style="z-index:1;">
							<span aria-hidden="true">&rsaquo;</span></a>
						</li>
					</c:if>  
					<!-- 무조건 마지막페이지로 >> 버튼 -->
					<c:if test = "${instckVo.firstPageNoOnPageList + 20 < instckVo.totalPageCount}">
						<li>
							<a href="javascript:instckPaging(${instckVo.totalPageCount})" aria-label="Next" style="z-index:1;">
							<span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>  
				</ul>
			</nav>
		</div>

		<script>
			//F5키 막아놓기
			window.onkeydown = function() {
				var kcode = event.keyCode;
				if(kcode == 116) event.returnValue = false;
			}
		
			// 검색 조건 날짜
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
				$("#beginDatepicker").datepicker();
				$("#endDatepicker").datepicker();
			});
	
			// 등록일로 검색
			function instckSech(clintSeq){
				// 날짜 유효성검사
	   			var today = new Date();
	            var year = today.getFullYear();
	            var month = today.getMonth() + 1;
	            var day = today.getDate();    
	            
	            var startDate = $("#beginDatepicker").val(); // 시작날짜
	            var startDateArr = startDate.split('-');   
	            var endDate = $("#endDatepicker").val(); // 종료날짜
	            var endDateArr = endDate.split('-');                 
	            var present = new Date(year, month, day);//오늘 날짜 
	            var startDateCompare = new Date(startDateArr[0], startDateArr[1], startDateArr[2]); //시작 날짜
	            var endDateCompare = new Date(endDateArr[0], endDateArr[1], endDateArr[2]); //종료 날짜
	            var day = 1000*60*60*24;                 
	           	if(startDate=="" && endDate == ""){           		
	           	}
	           	else{
	           		 if(startDate=="" || endDate == ""){
	                		alert("시작일과 종료일을 입력해주세요.");
	    	                return;	
	   	             }
	   	             else if(startDateCompare.getTime() > endDateCompare.getTime()){
	                        alert("시작일이 종료일보다 늦을 수 없습니다.");
	                        return;
	                   }
	           	}  
				document.instckPagingFrm.beginDatepicker.value=$("#beginDatepicker").val();
				document.instckPagingFrm.endDatepicker.value=$("#endDatepicker").val();
				document.instckPagingFrm.currentPageNo.value = 1;
				document.instckPagingFrm.action="/InstckExchngFlorPopup.do?clintSeq="+clintSeq;
				document.instckPagingFrm.submit();
			};
			
			// 입고거래장 리스트 페이징
			function instckPaging(page, clintSeq) {
				document.instckPagingFrm.currentPageNo.value = page;
				document.instckPagingFrm.action="/InstckExchngFlorPopup.do?clintSeq="+clintSeq;
				document.instckPagingFrm.submit();
			};
			
			// 입고거래장 상세조회
			function instckExchngFlorReadPopup(instckExchngFlorSeq){
				document.instckPagingFrm.instckExchngFlorSeq.value=instckExchngFlorSeq;
				document.instckPagingFrm.action="/InstckExchngFlorReadPopup.do";
				document.instckPagingFrm.submit();
				
			};
			
			function changeTrColor(trObj, oldColor, newColor) {
				trObj.style.backgroundColor = newColor;
				trObj.onmouseout = function(){
					trObj.style.backgroundColor = oldColor;
				};
			};
		</script>
	</body>
</html>