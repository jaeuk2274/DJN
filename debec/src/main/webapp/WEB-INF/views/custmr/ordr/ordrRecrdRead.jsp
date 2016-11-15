<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 
* 주문 내역 상세조회하는 화면을 보여주는 JSP입니다.
* 
* history : 최재욱, 1.0, 2016/08/12 – 초기 작성
* author  : 최재욱
* version : 1.0, 2016/08/12  - 초기 작성
* see : 회원 목록 조회 화면.
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
			<form name="backFrm">
				<input type="hidden" name="ordrSechText" value="${OrdrValueObject.ordrSechText}"> <!-- 주문 고객명 검색어 -->
				<input type="hidden" name="ordrSechRecvMethd" value="${OrdrValueObject.ordrSechRecvMethd}"> <!-- 주문 검색 수령 방식 -->
				<input type="hidden" name="ordrSechPamntMethd" value="${OrdrValueObject.ordrSechPamntMethd}"> <!-- 주문 검색 결제 조건 -->
				<input type="hidden" name="currentPageNo" value="${OrdrValueObject.currentPageNo}"> <!-- 현재 페이지 -->	
				<input type="hidden" name="ordrStatSeq" value="${OrdrValueObject.ordrStatSeq}"> <!-- 배달상태 -->	
				<input type="hidden" name="backPage" value="${OrdrValueObject.backPage}"> <!-- 돌아가는 페이지 (실시간, 주문, 주문취소) -->	
				
				<input type="hidden" name="ordrStat" value="${OrdrRecrdVO.ordrStat}"> <!-- 이 주문의 상태 (주문접수,배달중,배달완료) -->	
				<input type="hidden" name="ordrNumbrSeq" value="${OrdrRecrdVO.ordrNumbrSeq}"> <!-- 주문번호 -->	
				<input type="hidden" name="imgChkGrop">	
			</form>
			
			<div class="container">		
				<c:if test = "${OrdrRecrdVO.ordrStat eq '주문취소'}">
					<h3>주문 취소 상세조회</h3>
				</c:if>
				<c:if test = "${OrdrRecrdVO.ordrStat != '주문취소'}">
					<h3>주문 상세조회</h3>
				</c:if>
				
				<div style="display:inline-block; margin-top:1%; float:right">
				   <h5>
				     <img src="/resources/image/common/home.png"> > 주문 내역 관리 > 
				     <a href="/ordrRecrdList.do" style="text-decoration:none"><strong>주문 내역</strong></a>
				   </h5>
				</div>
			</div>
			
			<div class="container">
				<h3>주문 정보<input class="btn btn-default pull-right" type="button" value="돌아가기" onclick="javascript:backPage()"/></h3> 		
			</div>
			
			<div class="container">
				<form id="selProdctFileFrm" name="selProdctFileFrm" enctype="multipart/form-data" method="POST">
					<input type="hidden" name="ordrSechText" value="${OrdrValueObject.ordrSechText}"> <!-- 주문 고객명 검색어 -->
					<input type="hidden" name="ordrSechRecvMethd" value="${OrdrValueObject.ordrSechRecvMethd}"> <!-- 주문 검색 수령 방식 -->
					<input type="hidden" name="ordrSechPamntMethd" value="${OrdrValueObject.ordrSechPamntMethd}"> <!-- 주문 검색 결제 조건 -->
					<input type="hidden" name="currentPageNo" value="${OrdrValueObject.currentPageNo}"> <!-- 현재 페이지 -->	
					<input type="hidden" name="ordrStatSeq" value="${OrdrValueObject.ordrStatSeq}"> <!-- 배달상태 -->	
					<input type="hidden" name="backPage" value="${OrdrValueObject.backPage}"> <!-- 돌아가는 페이지 (실시간, 주문, 주문취소) -->	
					<input type="hidden" name="ordrStat" value="${OrdrRecrdVO.ordrStat}"> <!-- 이 주문의 상태 (주문접수,배달중,배달완료) -->	
					<input type="hidden" name="ordrNumbrSeq" value="${OrdrRecrdVO.ordrNumbrSeq}"> <!-- 주문번호 -->	
				
					<table class="table table-bordered" style="height:200px">
						<colgroup>
		                  	<col class="col-md-4 col-sm-4"/>                
		                  	<col class="col-md-4 col-sm-4"/>          
		                    <col class="col-md-4 col-sm-4"/>           
	                    </colgroup>
						<c:if test = "${OrdrRecrdVO.ordrStat != '주문취소'}">
							<tr style="height:25px">
								<td> 
									${OrdrRecrdVO.cartImg1 eq null ? "" : "<input type='checkbox' name='check' value='1'>"}
								</td>
								<td> 
									${OrdrRecrdVO.cartImg2 eq null ? "" : "<input type='checkbox' name='check' value='2'>"}
								</td>
								<td> 
									${OrdrRecrdVO.cartImg3 eq null ? "" : "<input type='checkbox' name='check' value='3'>"}
								</td>
							</tr>
						</c:if>
						<tr>
							<td style="word-break:break-all">  
								<div class="text-center">
									<img src="${OrdrRecrdVO.cartImg1}" style="width:99%; height:200px">
								</div> 
							</td>
							<td style="word-break:break-all"> 
								<div class="text-center">
									<img src="${OrdrRecrdVO.cartImg2}" style="width:99%; height:200px">
								</div> 
							</td>
							<td style="word-break:break-all"> 
								<div class="text-center">
									<img src="${OrdrRecrdVO.cartImg3}" style="width:99%; height:200px">
								</div>
							</td>
						</tr>
						<c:if test = "${OrdrRecrdVO.ordrStat != '주문취소'}">	
							<tr>
								<td colspan="3">
									<div class="form-inline" >
										<input type="file" class="form-control col-lg-6 col-sm-4" name="cart1">
										<input type="button" class="btn btn-default" value="사진 등록" onclick="imgInsert()"/>
										<input type="button" class="btn btn-default" value="사진 삭제" onclick="imgDelete()"/>
									</div>
								</td>
							</tr>
						</c:if>				
					</table>
				</form>
			</div>
		
			<div class="container">
				<table class="table table-bordered">
					<colgroup>
	                  	<col class="col-md-3 col-sm-3"/>                
	                  	<col class="col-md-3 col-sm-3"/>          
	                    <col class="col-md-3 col-sm-3"/>
	                    <col class="col-md-3 col-sm-3"/>          
                    </colgroup>
					<tr>
						<th class="active text-center"> 상품주문번호 </th>
						<td colspan="3"> ${OrdrRecrdVO.ordrNumbrSeq}</td>					
					</tr>	
					<tr>
						<th class="active text-center"> 주문자 </th>
						<td> ${OrdrRecrdVO.custmrNme} </td>	
						<th class="active text-center"> 주문자 휴대전화 </th>
						<td> ${OrdrRecrdVO.custmrMobl} </td>					
					</tr>	
					<tr>
						<th class="active text-center"> 수령자 </th>
						<td> ${OrdrRecrdVO.recvrNme} </td>	
						<th class="active text-center"> 수령자 휴대전화 </th>
						<td> ${OrdrRecrdVO.recvrMobl} </td>					
					</tr>			
					<tr>
						<th class="active text-center"> 주소 </th>
						<td colspan="3"> (${OrdrRecrdVO.recvrPostcd})  ${OrdrRecrdVO.recvrAddrss} ${OrdrRecrdVO.recvrDetlAddress} </td>					
					</tr>		
					<tr>
						<th class="active text-center"> 주문접수시간 </th>
						<td> ${OrdrRecrdVO.ordrDat} </td>	
						<th class="active text-center"> 희망배달시간 </th>
						<td> ${OrdrRecrdVO.hopDelvrDat} ${OrdrRecrdVO.hopDelvrTim}</td>					
					</tr>	
					<tr>
						<th class="active text-center"> 결제방법 </th>
						<td> ${OrdrRecrdVO.pamntMethd} </td>	
						<th class="active text-center"> 수령방법 </th>
						<td> ${OrdrRecrdVO.recvMethd} </td>					
					</tr>		
				</table>
			</div>
			
			<div class="container">
				<c:if test = "${OrdrRecrdVO.ordrStat != '주문취소'}">	
					<div class="text-right" >
						<c:if test="${OrdrRecrdVO.ordrStat == '주문접수'}">
							<button class="btn btn-default" onclick="ordrStatChange()">주문접수 -> 배달중</button>
						</c:if>
						<c:if test="${OrdrRecrdVO.ordrStat == '배달중'}">
							<button class="btn btn-default" onclick="ordrStatChange()">배달중 -> 배달완료</button>
						</c:if>			
					</div>			
					
					<table class="table table-bordered">
						<colgroup>
		                  	<col class="col-md-3 col-sm-3"/>                
		                  	<col class="col-md-3 col-sm-3"/>          
		                    <col class="col-md-6 col-sm-6"/>           
	                    </colgroup>
						<tr>				
							<td rowspan="2" style="word-break:break-all"> 
								<div style="width:100%; height:200px">
									<img src="${OrdrRecrdVO.empImg}" style="width:100%; height:100%">
								</div>
							</td>
							<th class="active text-center"> 배달원 이름 </th>
							<td> ${OrdrRecrdVO.empNme} </td>
						</tr>
						<tr>
							<th class="active text-center"> 연락처 </th>
							<td> ${OrdrRecrdVO.empMobl} </td>
						</tr>
					</table>
					<table class="table table-bordered">
						<colgroup>
		                  	<col class="col-md-4 col-sm-4"/>                
		                  	<col class="col-md-4 col-sm-4"/>          
		                    <col class="col-md-4 col-sm-4"/>           
	                    </colgroup>
						<tr>				
							<td class="text-center">
								${OrdrRecrdVO.ordrStat eq "주문접수" ? "<img src='resources/image/common/ordrReceiptSelect.png'>" : "<img src='resources/image/common/ordrReceipt.png'>"}	
								<br>주문접수
							</td>
							<td class="text-center">
								${OrdrRecrdVO.ordrStat eq "배달중" ? "<img src='resources/image/common/shippingSelect.png'>" : "<img src='resources/image/common/shipping.png'>"}
								<br>배달중
							</td>
							<td class="text-center">
								${OrdrRecrdVO.ordrStat eq "배달완료" ? "<img src='resources/image/common/shippingCompleteSelect.png'>" : "<img src='resources/image/common/shippingComplete.png'>"}
								<br>배달완료 
							</td>
						</tr>
					</table>
				</c:if>
			</div>
		
			<div class="container">
				<table class="table table-bordered">
					<colgroup>
	                  	<col class="col-md-3 col-sm-3"/>                
	                  	<col class="col-md-3 col-sm-3"/>          
	                    <col class="col-md-3 col-sm-3"/>
	                    <col class="col-md-3 col-sm-3"/>          
                    </colgroup>
					<thead>
					  <tr class="active">
						<th class="text-center">상품명</th>
						<th class="text-center">판매가</th>
						<th class="text-center">수량</th>
						<th class="text-center">구매가</th>
					  </tr>
					</thead>
					<c:forEach items="${OrdrRecrdProdctList}" var="OrdrRecrdProdctVO" varStatus="status">	
						<tr>
							<td class="text-center">
								 ${OrdrRecrdProdctVO.prodctNme}
							</td>
							<td class="text-center">
								<fmt:formatNumber value="${OrdrRecrdProdctVO.selPric}" type="number"/> 원
							</td>
							<td class="text-center">
								 ${OrdrRecrdProdctVO.ordrAmont}
							</td>
							<td class="text-center">
								<fmt:formatNumber value="${OrdrRecrdProdctVO.pricSum}" type="number"/> 원
							</td>
						</tr>
					</c:forEach>
					<tr>
						<th class="active text-center">합계</th>
						<td colspan="3" class="text-right"> <fmt:formatNumber value="${OrdrRecrdVO.pricSum}" type="number"/> 원 </td>
					</tr>
				</table>
			</div>
		</div>
		<script>
			// 주문 상태별 조회
			function realTimeOrdrRecrdList(statSeq) {
				var url = "/realTimeOrdrRecrdList.do?ordrStatSeq="+statSeq;
				$(location).attr('href',url);
			};	
			
			// 돌아가기
			function backPage() {	
				location.href="/ordrRecrdList.do";
				
// 				var backPage = document.backFrm.backPage.value;
// 				if(backPage == "실시간") {
// 					var url = "/realTimeOrdrRecrdList.do";
// 					$(location).attr('href',url);
// 				}
// 				else if(backPage == "주문") {
// 					document.backFrm.action ="/ordrRecrdList.do";
// 					document.backFrm.submit();	
// 				}
// 				else if(backPage == "주문취소") {
// 					document.backFrm.action ="/ordrRecrdList.do";
// 					document.backFrm.submit();	
// 				}	
			};
			
			// 배달 상태 변경
			function ordrStatChange() {
				document.backFrm.action ="/changeOrdrStat.do";
				document.backFrm.submit();	
				
			};

			// 이미지 등록
			function imgInsert() {
				document.selProdctFileFrm.action ="/imgInsert.do";
				document.selProdctFileFrm.submit();	
			};
			
			// 이미지 삭제
			function imgDelete() {
				var chkGroup = document.selProdctFileFrm.check;
				var changeGroup=[];
				var cnt=0;			
				if (chkGroup.length > 1) {
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
				else if (chkGroup.length = 1){
					changeGroup.push(chkGroup.value);
				}
				else{
					alert("선택한 체크박스가 없습니다.");
					return;
				}
				document.backFrm.imgChkGrop.value=changeGroup;
				document.backFrm.action ="/imgDelete.do";
				document.backFrm.submit();	
				
			};
		</script>
	</body>
</html>







