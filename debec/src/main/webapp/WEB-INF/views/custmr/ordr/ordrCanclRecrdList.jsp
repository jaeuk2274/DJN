<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!-- 
* (취소되지 않은)주문 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        최재욱, 1.0, 2016/08/12 – 초기 작성
* author : 최재욱
* version : 1.0, 2016/08/12  - 초기 작성
* see : (취소되지 않은)주문 목록을 조회 화면
//-->
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
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
	    <div class="container">  	
			<form name="ordrRecrdPagingFrm">  
				<input type="hidden" name="ordrSechText" value="${OrdrValueObject.ordrSechText}"> <!-- 주문 고객명 검색어 -->
				<input type="hidden" name="ordrSechRecvMethd" value="${OrdrValueObject.ordrSechRecvMethd}"> <!-- 주문 검색 수령 방식 -->
				<input type="hidden" name="ordrSechPamntMethd" value="${OrdrValueObject.ordrSechPamntMethd}"> <!-- 주문 검색 결제 조건 -->
				<input type="hidden" name="currentPageNo" value="${OrdrValueObject.currentPageNo}"> <!-- 현재 페이지 -->	
				<input type="hidden" name="ordrChkGroup"> 
				<input type="hidden" name="ordrStatSeq" value="${OrdrValueObject.ordrStatSeq}"> <!-- 배달상태 -->	
				<input type="hidden" name="ordrNumbrSeq" value="0"> <!-- 주문번호  -->
				<input type="hidden" name="backPage" value="주문취소"> <!-- 돌아가는 페이지-->	
			</form>		
			
			<div class="container">
				<h3>주문 취소 내역 조회</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
				   <h5>
				     <img src="/resources/image/common/home.png"> > 주문 내역 관리 >  
				     <a href="/ordrCanclRecrdList.do" style="text-decoration:none"><strong>주문 취소 내역</strong></a>
				   </h5>
				</div>
			</div>
			
			<div class="container form-inline">
				<form name="ordrRecrdSechFrm" method="post">
					<table class="table table-bordered">
						<tr>
							<th class="active">
		                       	<h5 class="text-center"><b>고객명</b></h5>
		                   	</th>
							<td>
								<input type="text" class="form-control" id="ordrSechText" name="ordrSechText" value="">
							</td>
						</tr>
						<tr>
							<th class="active">
		                       	<h5 class="text-center"><b>수령 방법</b></h5>
		                   	</th>
							<td>
								<!-- 3항 연산자 사용 값 같으면 자동 체크-->
								<input type="radio" class="radio-inline" name="ordrSechRecvMethd" value="all" ${OrdrValueObject.ordrSechRecvMethd eq "all" ? "checked" : ""}> 전체
								<input type="radio" class="radio-inline" name="ordrSechRecvMethd" value="배달" ${OrdrValueObject.ordrSechRecvMethd eq "배달" ? "checked" : ""}> 배달
								<input type="radio" class="radio-inline" name="ordrSechRecvMethd" value="수령" ${OrdrValueObject.ordrSechRecvMethd eq "수령" ? "checked" : ""}> 수령
							</td>
						</tr>
						<tr>
							<th class="active">
		                       	<h5 class="text-center"><b>결제 방법</b></h5>
		                   	</th>
							<td>
								<!-- 3항 연산자 사용 값 같으면 자동 체크-->
								<input type="radio" class="radio-inline" name="ordrSechPamntMethd" value="all" ${OrdrValueObject.ordrSechPamntMethd eq "all" ? "checked" : ""}> 전체
								<input type="radio" class="radio-inline" name="ordrSechPamntMethd" value="카드" ${OrdrValueObject.ordrSechPamntMethd eq "카드" ? "checked" : ""}> 카드
								<input type="radio" class="radio-inline" name="ordrSechPamntMethd" value="현금" ${OrdrValueObject.ordrSechPamntMethd eq "현금" ? "checked" : ""}> 현금
							</td>
						</tr>
					</table>
				</form>
			</div>	
			
			<div class="container text-center">
				<input type="button" class="btn btn-default" onclick="javacript:ordrRecrdSech()" value="검 색">
			</div>
		
			<div class="form-group"></div>
			
			<div class="container">
				<form name="ordrRecrdCheckFrm" method="post">
					<table class="table table-bordered">
						<colgroup>
							<col class="col-md-2 col-xs-2"/>                
							<col class="col-md-1 col-xs-1"/>          
							<col class="col-md-3 col-xs-3"/>           
							<col class="col-md-3 col-xs-3"/>
							<col class="col-md-1 col-xs-1"/>          
							<col class="col-md-1 col-xs-1"/>
						</colgroup>
					    <thead>
							<tr class="active">
								<th class="text-center">주문 번호</th>
								<th class="text-center">고객명</th>
								<th class="text-center">주문 접수 시간</th>
								<th class="text-center">예상 배달 시간</th>
								<th class="text-center">결제 방법</th>
								<th class="text-center">수령 방법</th>
							</tr>
						</thead>
						<c:choose>
							<c:when test="${fn:length(OrdrRecrdList) == 0}">
								<tr class="text-center">
									<td colspan="8">
										<h4>주문 취소된 내역이 없습니다</h4>
									</td>
								</tr>
							</c:when>
							<c:when test="${fn:length(OrdrRecrdList) != 0}">
								<c:forEach items="${OrdrRecrdList}" var="ordrRecrd" varStatus="status">	
									<tr onclick="javascript:ordrRecrdRead(${ordrRecrd.ordrNumbrSeq})" onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
										<td class="text-center"> ${ordrRecrd.ordrNumbrSeq}</td>
										<td class="text-center"> ${ordrRecrd.custmrNme}</td>							
										<td class="text-center"> ${ordrRecrd.ordrDat}</td>	 
										<td class="text-center"> ${ordrRecrd.hopDelvrDat} ${ordrRecrd.hopDelvrTim}</td>
										<td class="text-center"> ${ordrRecrd.pamntMethd}</td>
										<td class="text-center"> ${ordrRecrd.recvMethd}</td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>	
					</table>
				</form>
			</div>	
								
			<div class="container text-center">
				<nav>
					<ul class="pagination">
						<!-- 무조건 1페이지로 << 버튼 -->
						<c:if test = "${OrdrValueObject.currentPageNo>20}">
							<li>
								<a href="javascript:ordrRecrdPaging(1)" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span></a>
							</li>
						</c:if>  
						<!-- 한 단위 앞으로 < 버튼 -->
						<c:if test = "${OrdrValueObject.currentPageNo>10}">
							<li>
								<a href="javascript:ordrRecrdPaging(${OrdrValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
								<span aria-hidden="true">&lsaquo;</span></a>
							</li>
						</c:if> 	
									  	
						<c:forEach varStatus="status" begin="${OrdrValueObject.firstPageNoOnPageList}" end="${OrdrValueObject.lastPageNoOnPageList}">
						
							<c:if test="${OrdrValueObject.currentPageNo==status.current}">
								<li class="active">
									<a href="javascript:ordrRecrdPaging(${status.current})">${status.current}</a>
								</li>
							</c:if>
							<c:if test="${OrdrValueObject.currentPageNo!=status.current}">
								<li><a href="javascript:ordrRecrdPaging(${status.current})">${status.current}</a></li>
							</c:if>                   									
						</c:forEach>	
								
						<!-- 한 단위 뒤로 > 버튼 -->
						<c:if test = "${OrdrValueObject.firstPageNoOnPageList + 10 < OrdrValueObject.totalPageCount}">
							<li>
								<a href="javascript:ordrRecrdPaging(${OrdrValueObject.lastPageNoOnPageList+1})" aria-label="Next">
								<span aria-hidden="true">&rsaquo;</span></a>
							</li>
						</c:if>  
						<!-- 무조건 마지막페이지로 >> 버튼 -->
						<c:if test = "${OrdrValueObject.firstPageNoOnPageList + 20 < OrdrValueObject.totalPageCount}">
							<li>
								<a href="javascript:ordrRecrdPaging(${OrdrValueObject.totalPageCount})" aria-label="Next">
								<span aria-hidden="true">&raquo;</span></a>
							</li>
						</c:if>  
					</ul>
				</nav>
			</div>
		</div>
		<script>
			// 주문 내역 검색
			function ordrRecrdSech() {
				document.ordrRecrdSechFrm.action ="/ordrCanclRecrdList.do";
				document.ordrRecrdSechFrm.submit();
			};
			
			// 주문 내역 페이징
			function ordrRecrdPaging(page) {
				document.ordrRecrdPagingFrm.currentPageNo.value = page;
				document.ordrRecrdPagingFrm.action ="/ordrCanclRecrdList.do";
				document.ordrRecrdPagingFrm.submit();
			};
			
			// 주문내역 상세조회
			function ordrRecrdRead(ordrNumbrSeq) {
				document.ordrRecrdPagingFrm.ordrNumbrSeq.value=ordrNumbrSeq;
				document.ordrRecrdPagingFrm.action ="/ordrRecrdRead.do";
				document.ordrRecrdPagingFrm.submit();	
			};
			
			function changeTrColor(trObj, oldColor, newColor) {
				trObj.style.backgroundColor = newColor;
				trObj.onmouseout = function(){
					trObj.style.backgroundColor = oldColor;
				}
			};
		</script>
	</body>
</html>