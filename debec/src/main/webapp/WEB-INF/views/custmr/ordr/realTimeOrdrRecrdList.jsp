<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 
* 실시간 주문내역 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history : 최재욱, 1.0, 2016/08/11 – 초기 작성
* author  : 최재욱
* version : 1.0, 2016/08/11  - 초기 작성
* see : 회원 목록 조회 화면.
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
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		    
	    <div class="container" >
			<div class="container">
				<h3>실시간 주문내역</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
				   <h5>
				     <img src="/resources/image/common/home.png"> > 주문 내역 관리 > 
				     <a href="/realTimeOrdrRecrdList.do" style="text-decoration:none"><strong>실시간 주문내역</strong></a>
				   </h5>
				</div>
			</div>
			
			<div class="form-group"></div>
			
			<div class="container text-right" >
				<button class="btn btn-default" onclick="javascript:realTimeOrdrRecrdList(0)">전체</button>
				<button class="btn btn-default" onclick="javascript:realTimeOrdrRecrdList(1)">주문 접수</button>
				<button class="btn btn-default" onclick="javascript:realTimeOrdrRecrdList(4)">주문 취소</button><br>
			</div>
			
			<div class="form-group"></div>
			
			<div class="container">
				<c:choose>
					<c:when test="${fn:length(RealTimeOrdrRecrdList) == 0}">
						<table class="table table-bordered text-center">
							<colgroup>
								<col class="col-md-2"/>                
								<col class="col-md-2"/>          
								<col class="col-md-4"/>           
								<col class="col-md-2"/>
								<col class="col-md-2"/>          
							</colgroup>
						  	<thead>
								<tr class="active">
									<th style="word-break:break-all" class="text-center">주문일자 (주문번호)</th>
									<th style="word-break:break-all" class="text-center" colspan="2">상품명</th>
									<th style="word-break:break-all" class="text-center">구매자</th>
									<th style="word-break:break-all" class="text-center">결제방법</th>
								</tr>
							</thead>
								<tr>
									<td colspan="5">
										<h4>등록된 주문 내역이 없습니다</h4>
									</td>
								</tr>
						</table>
					</c:when>
					<c:when test="${fn:length(RealTimeOrdrRecrdList) != 0}">
						<c:forEach items="${RealTimeOrdrRecrdList}" var="ordrVO" varStatus="status">	
							<table class="table table-bordered">
								<colgroup>
									<col class="col-md-2"/>                
									<col class="col-md-2"/>          
									<col class="col-md-4"/>           
									<col class="col-md-2"/>
									<col class="col-md-2"/>          
								</colgroup>
							  <thead>
								<tr class="active">
									<th style="word-break:break-all" class="text-center">주문일자 (주문번호)</th>
									<th style="word-break:break-all" class="text-center" colspan="2">상품명</th>
									<th style="word-break:break-all" class="text-center">구매자</th>
									<th style="word-break:break-all" class="text-center">결제방법</th>
								</tr>
							</thead>
								<tr>
									<td class="text-center" rowspan="3">
										${ordrVO.ordrDat} (${ordrVO.ordrNumbrSeq}) <br>
										총 주문금액 : <fmt:formatNumber value="${ordrVO.pricSum}" type="number"/> 원  <br><br>
										<input type="button" class="btn btn-default" onclick="javascript:ordrRecrdRead(${ordrVO.ordrNumbrSeq})" value="상세조회">
									</td>
									<td class="text-center"  rowspan="3"> 
										<img src="${ordrVO.cartImg1}" width="99%" height="80">
									</td>
									<td class="text-center"  rowspan="3"> 
										${ordrVO.firstProdctNme} 외  ${ordrVO.prodctCount}종
<%-- 										<c:if test = "${ordrVO.prodctCount-1 != 0}"> --%>
<%-- 											외  ${ordrVO.prodctCount-1}건 --%>
<%-- 										</c:if> --%>
									</td>
									<td class="text-center" > ${ordrVO.custmrNme} </td>
									<td class="text-center" > ${ordrVO.pamntMethd} </td>
								</tr>
								<tr class="active">
									<th class="text-center">수령방법</th>
									<th class="text-center">주문상태</th>
								</tr>
								<tr>
									<td class="text-center" > ${ordrVO.recvMethd} </td>
									<td class="text-center" > ${ordrVO.ordrStat} </td>
								</tr>
							</table>
						</c:forEach>
					</c:when>
				</c:choose>
			</div>
		</div>
		<script>
		function realTimeOrdrRecrdList(statSeq) {
			var url = "/realTimeOrdrRecrdList.do?ordrStatSeq="+statSeq;
			$(location).attr('href',url);
		};	
		
		function ordrRecrdRead(ordrNumbrSeq) {
			var url = "/ordrRecrdRead.do?ordrNumbrSeq="+ordrNumbrSeq;
			$(location).attr('href',url);		
		};
	
		$(document).ready(function() {
			setTimeout("refresh()", 10000); // 10000ms(10초)가 경과하면 refresh() 함수를 실행합니다.
		});
		
		function refresh(){
		location.href = "/realTimeOrdrRecrdList.do"; // 실시간 주문내역 조회
		};		 
		</script>
	</body>
</html>