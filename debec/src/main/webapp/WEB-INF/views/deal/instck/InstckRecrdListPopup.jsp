<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 
* 상품입고내역을 팝업으로 보여주는 JSP입니다.(거래처관리 - 상세보기 - 상품입고내역)
* 
* history :
*        김대현, 1.0, 2016/08/16 – 초기 작성
* author : 김대현
* version : 1.0, 2016/08/16  - 초기 작성
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
			<h3>입고내역</h3>
		</div>
		
		<div class="form-group"></div>
		
		<div class="container form-inline">
			<form name="instckPagingFrm" method="post">      
	   			<input type="hidden" name="beginDatepicker" value="${instckVo.beginDatepicker}">	<!-- 입고일로 검색 -->
	   			<input type="hidden" name="endDatepicker" value="${instckVo.endDatepicker}">		<!-- 입고일로 검색 -->
	   			<input type="hidden" name="prodctNmeText" value="${instckVo.prodctNmeText}">		<!-- 상품명으로 검색  -->
				<input type="hidden" name="clintSeq" value="${instckVo.clintSeq}">			<!-- 거래처 seq -->
				<input type="hidden" name="currentPageNo" value="${instckVo.currentPageNo}"> 	<!-- 현재 페이지 -->
	      	</form>
		
			<table class="table table-bordered">
				<tr>
					<td class="active">
						<b>입고일</b>
					</td>
					<td>
						<!-- 검색 -->
						<div class="form-group">
							<input type="text" class="form-control" id="beginDatepicker" name="beginDatepicker">
							&nbsp;&nbsp; ~ &nbsp;&nbsp;
							<input type="text" class="form-control" id="endDatepicker" name="endDatepicker">
						</div>
					</td>
				</tr>
				<tr>
					<td class="active">상품명</td>
					<td>
						<input type="text" class="form-control" id="prodctNmeText" name="prodctNmeText" width="300">
					</td>
				</tr>
			</table>
		</div>
			
		<div class="container text-center">
			<input type="button" class="btn btn-default" value="조회" onclick="javscript:instckSech()">
		</div>
		
		<div class="form-group"></div>
		
		<div class="container">
			<table class="table table-bordered">
				<tr class="active">
					<td class="text-center">
						<b>No</b>
					</td>
					<td class="text-center">
						<b>상품명</b>
					</td>
					<td class="text-center">
						<b>입고량</b>
					</td>
					<td class="text-center">
						<b>단가</b>
					</td>
					<td class="text-center">
						<b>금액</b>
					</td>
					<td class="text-center">
						<b>입고일</b>
					</td>
				</tr>
				<c:choose>
					<c:when test="${fn:length(instckRecrdList) == 0}">
						<tr class="text-center">
							<td colspan="6">
								<h4>입고된 상품이 없습니다</h4>
							</td>
						</tr>
					</c:when>
					<c:when test="${fn:length(instckRecrdList) != 0}">
						<c:forEach items="${instckRecrdList}" var="instck" varStatus="status">
							<tr onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
								<td class="text-center">
									${instckVo.totalRecordCount-instckVo.firstRecordIndex-status.index}
								</td>
								<td class="text-center">
									${instck.prodctNme}
								</td>
								<td class="text-center">
									<fmt:formatNumber value="${instck.qunt}" type="number"/>
								</td>
								<td class="text-center">
									<fmt:formatNumber value="${instck.purchsPric}" type="number"/>
								</td>
								<td class="text-center">
									<fmt:formatNumber value="${instck.price}" type="number"/>
								</td>
								<td class="text-center">
									${instck.dat}
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
								<a href="javascript:instckPaging(1)" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span></a>
							</li>
						</c:if>  
						<!-- 한 단위 앞으로 < 버튼 -->
						<c:if test = "${instckVo.currentPageNo>10}">
							<li>
								<a href="javascript:instckPaging(${instckVo.firstPageNoOnPageList-1})" aria-label="Previous">
								<span aria-hidden="true">&lsaquo;</span></a>
							</li>
						</c:if> 	
						<!-- 	페이징 -->
						<c:forEach varStatus="status" begin="${instckVo.firstPageNoOnPageList}" end="${instckVo.lastPageNoOnPageList}">
						
							<c:if test="${instckVo.currentPageNo==status.current}">
								<li class="active">
									<a href="javascript:instckPaging(${status.current}, ${instckVo.clintSeq})">${status.current}</a>
								</li>
							</c:if>
							
							<c:if test="${instckVo.currentPageNo!=status.current}">
								<li>
									<a href="javascript:instckPaging(${status.current}, ${instckVo.clintSeq})">${status.current}</a>
								</li>
							</c:if>   
							                									
						</c:forEach>	
						
						<!-- 한 단위 뒤로 > 버튼 -->
						<c:if test = "${instckVo.firstPageNoOnPageList + 10 < instckVo.totalPageCount}">
							<li>
								<a href="javascript:instckPaging(${instckVo.lastPageNoOnPageList+1})" aria-label="Next">
								<span aria-hidden="true">&rsaquo;</span></a>
							</li>
						</c:if>  
						<!-- 무조건 마지막페이지로 >> 버튼 -->
						<c:if test = "${instckVo.firstPageNoOnPageList + 20 < instckVo.totalPageCount}">
							<li>
								<a href="javascript:instckPaging(${instckVo.totalPageCount})" aria-label="Next">
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
	
			// 입고내역 검색
			function instckSech(){
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
				
				// if로 입고일로 검색했을때랑 
				// 상품명으로 검색했을 때
				// 둘다 검색했을 때 조건줘야할듯
				document.instckPagingFrm.beginDatepicker.value = $("#beginDatepicker").val();
				document.instckPagingFrm.endDatepicker.value = $("#endDatepicker").val();
				document.instckPagingFrm.prodctNmeText.value = $("#prodctNmeText").val();
				document.instckPagingFrm.currentPageNo.value = 1;
				document.instckPagingFrm.action="/instckRecrdListPopup.do";
				document.instckPagingFrm.submit();
			};
			
			// 입고내역 리스트 페이징
			function instckPaging(page, clintSeq) {
				document.instckPagingFrm.currentPageNo.value = page;
				document.instckPagingFrm.action="/instckRecrdListPopup.do?clintSeq="+clintSeq;
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