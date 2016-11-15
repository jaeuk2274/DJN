<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 
* (취소되지 않은)주문 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history : 최재욱, 1.0, 2016/08/12 – 초기 작성
* author  : 최재욱
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
				<input type="hidden" name="ordrNumbrSeq" value="0"> <!-- 주문번호-->	
				<input type="hidden" name="backPage" value="주문"> <!-- 돌아가는 페이지-->	
			</form>		
			
			<div class="container">
				<h3>주문 내역 조회</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
				   <h5>
				     <img src="/resources/image/common/home.png"> > 주문 내역 관리 > 
				     <a href="/ordrRecrdList.do" style="text-decoration:none"><strong>주문 내역</strong></a>
				   </h5>
				</div>
			</div>
			
			<div class="container  form-inline">
				<form name="ordrRecrdSechFrm" method="post">
					<input type="hidden" name="ordrStatSeq" value="${OrdrValueObject.ordrStatSeq}"> <!-- 배달상태 -->	
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
			
			<div class="container form-inline">
				<input class="btn btn-default" type="button" value="배달 접수" onclick="javascript:groupChange()"/>
				

<!-- 				<select id="ordrKind" class="form-control" name="ordrKind" onchange="ordrKindChange()"> -->
<%-- 					<option value="normal" ${CustmrValueObject.custmrSechOption eq "id" ? "selected" : ""}> 일반  </option> --%>
<%-- 					<option value="dahamgae" ${CustmrValueObject.custmrSechOption eq "nme" ? "selected" :""}> 공동구매  </option> --%>
<!-- 				</select> -->

				
				<div style=" float:right">
					<input class="btn btn-default" type="button" value="전체" onclick="javascript:ordrRecrdList(0)"/>
					<input class="btn btn-default" type="button" value="주문접수" onclick="javascript:ordrRecrdList(1)"/>
					<input class="btn btn-default" type="button" value="배달중" onclick="javascript:ordrRecrdList(2)"/>
					<input class="btn btn-default" type="button" value="배달완료" onclick="javascript:ordrRecrdList(3)"/>
				</div>
			</div>			
		
			<form name="ordrRecrdCheckFrm" method="post">
				<div class="container">
					<table class="table table-bordered">
						<colgroup>
							<col class="col-md-1 col-xs-1"/>                
							<col class="col-md-1 col-xs-1"/>          
							<col class="col-md-1 col-xs-1"/>           
							<col class="col-md-3 col-xs-3"/>
							<col class="col-md-3 col-xs-3"/>          
							<col class="col-md-1 col-xs-1"/>
							<col class="col-md-1 col-xs-1"/>          
							<col class="col-md-1 col-xs-1"/>
						</colgroup>
						<tr class="active">
							<td class="text-center media-middle">
								<input type="checkbox" id="allCheck">
							</td>
							<th class="text-center">
								<b>주문 번호</b>
							</th>
							<th class="text-center">
								<b>고객명</b>
							</th>
							<th class="text-center">
								<b>주문 접수 시간</b>
							</th>
							<th class="text-center">
								<b>예상 배달 시간</b>
							</th>
							<th class="text-center">
								<b>결제 방법</b>
							</th>
							<th class="text-center">
								<b>수령 방법</b>
							</th>
							<th class="text-center">
								<b>배달 상태</b>
							</th>
						</tr>
						<c:choose>
							<c:when test="${fn:length(OrdrRecrdList) == 0}">
								<tr class="text-center">
									<td colspan="8">
										<h4>주문된 내역이 없습니다</h4>
									</td>
								</tr>
							</c:when>
							<c:when test="${fn:length(OrdrRecrdList) != 0}">
								<c:forEach items="${OrdrRecrdList}" var="ordrRecrd" varStatus="status">	
									<tr onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
										<td class="text-center"><input type="checkbox" name="check" value="${ordrRecrd.ordrNumbrSeq}"></td>
										<td class="text-center" onclick="javascript:ordrRecrdRead(${ordrRecrd.ordrNumbrSeq})"> ${ordrRecrd.ordrNumbrSeq} </td>
										<td class="text-center" onclick="javascript:ordrRecrdRead(${ordrRecrd.ordrNumbrSeq})"> ${ordrRecrd.custmrNme}</td>							
										<td class="text-center" onclick="javascript:ordrRecrdRead(${ordrRecrd.ordrNumbrSeq})"> ${ordrRecrd.ordrDat}</td>	 
										<td class="text-center" onclick="javascript:ordrRecrdRead(${ordrRecrd.ordrNumbrSeq})"> ${ordrRecrd.hopDelvrDat} ${ordrRecrd.hopDelvrTim}</td>
										<td class="text-center" onclick="javascript:ordrRecrdRead(${ordrRecrd.ordrNumbrSeq})"> ${ordrRecrd.pamntMethd}</td>
										<td class="text-center" onclick="javascript:ordrRecrdRead(${ordrRecrd.ordrNumbrSeq})"> ${ordrRecrd.recvMethd}</td>
										<td class="text-center" onclick="javascript:ordrRecrdRead(${ordrRecrd.ordrNumbrSeq})"> ${ordrRecrd.ordrStat}</td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>	
					</table>
				</div>
			</form>
						
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
			function ordrKindChange(){
				var changeOrdrKind = $("#ordrKind").val();
				if(changeOrdrKind == "dahamgae"){
				}
				else{
					location.href="/ordrRecrdList.do";
				}
			}
		
		
		
			// 주문 내역 검색
			function ordrRecrdSech() {
				document.ordrRecrdSechFrm.action ="/ordrRecrdList.do";
				document.ordrRecrdSechFrm.submit();
			};
			
			// 주문 내역 페이징
			function ordrRecrdPaging(page) {
				document.ordrRecrdPagingFrm.currentPageNo.value = page;
				document.ordrRecrdPagingFrm.action ="/ordrRecrdList.do";
				document.ordrRecrdPagingFrm.submit();
			};
			
			// 상태별 주문 조회
			function ordrRecrdList(statSeq) {
				var url = "/ordrRecrdList.do?ordrStatSeq="+statSeq;
				$(location).attr('href',url);
			};	
			
			// 전체 선택, 전체 선택 해제
			$(function(){
			    //전체선택 체크박스 클릭
				$("#allCheck").click(function(){
					//만약 전체 선택 체크박스가 체크된상태일경우
					if($("#allCheck").prop("checked")) {
						//해당화면에 전체 checkbox들을 체크해준다
						$("input[type=checkbox]").prop("checked",true);
					// 전체선택 체크박스가 해제된 경우
					} else {
						//해당화면에 모든 checkbox들의 체크를해제시킨다.
						$("input[type=checkbox]").prop("checked",false);
					}
				})
			});

			// 일괄 배달 접수, 로그인한 직원 정보로 배달원 정보 업데이트
			function groupChange() {
				var chkGroup = document.ordrRecrdCheckFrm.check;
				var changeGroup=[];
				var cnt=0;
				
				if (chkGroup.length >= 1) {
					for ( var i = 0; i < chkGroup.length; i++) {
						if (chkGroup[i].checked) {
							cnt++;
							changeGroup.push(chkGroup[i].value);
						}
					}
					if (cnt == 0) {
						alert("선택한 체크박스가 없습니다.");
						return;
					}
				} 
			
				var chkChange = confirm("이 "+changeGroup.length+"건을 접수하시겠습니까?"); 
				if(chkChange == true) {
					document.ordrRecrdPagingFrm.ordrChkGroup.value=changeGroup;
					document.ordrRecrdPagingFrm.method = "post";
					document.ordrRecrdPagingFrm.action ="/changeOrdrStatGroup.do";
					document.ordrRecrdPagingFrm.submit();		
				}
				
				else {
					return;
				}
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