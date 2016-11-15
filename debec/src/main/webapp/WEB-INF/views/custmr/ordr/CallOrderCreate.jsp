<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 
* 전화 주문을 등록하는 화면을 보여주는 JSP입니다.
* 
* history : 김민아, 1.0, 2016/08/09 – 초기 작성
* author : 김민아
* version : 1.0, 2016/08/09  - 초기 작성
* see : 전화 주문 등록 화면.
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
		
		<!-- 이미지 썸네일 CSS -->
		<link href="/resources/common/css/thumbnail.css" rel="stylesheet" type="text/css"/>
		
		<!-- JQuery 관련 파일들 -->
		<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>   
		
		<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
		<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
		
		<!-- 주소 api -->		
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		
		<!-- 날짜 시간 선택, datetimepicker 위젯(시작) -->
		<link rel="stylesheet" href="//mugifly.github.io/jquery-simple-datetimepicker/jquery.simple-dtpicker.css">
		<script src="//mugifly.github.io/jquery-simple-datetimepicker/jquery.simple-dtpicker.js"></script>
		<!-- 날짜 시간 선택, datetimepicker 위젯(끝) -->
		
		<!-- callorderCreate 관련 JS -->
		<script src="../../../../resources/js/callOrder-js/callOrderCreate.js" type="text/javascript" charset="utf-8"></script>
			
		<style>
			#inputBox-ex {
				display: block; 
				height: 34px;
			    padding: 6px 12px;
			    font-size: 14px;
			    line-height: 1.42857143;
			    color: #555;
			    background-color: #fff;
			    background-image: none;
			    border: 1px solid #ccc;
			    border-radius: 4px;"
	    	}
		</style>
		
	</head>	
	<body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		<div id="allDiv">
			<div class="container">
				<h3>전화주문 등록</h3>
			</div>
			<form name="custmrSechFrm" id="custmrSechFrm" method="post">
				<input type="hidden" id="custmrClassify" value=""> <!-- 고객 분류 -->
				<input type="hidden" id="listLength" name ="listLength" value="${fn:length(prodctTempList)+1}"> <!-- 리스트 길이 -->
				<input type="hidden"  name="prodctNumber_arry">
				<input type="hidden"  name="prodctSeq_arry">
				<c:if test="${custmrValueObject.custmrSeq eq null}">
					<input type="hidden" name="custmrSeq" value="0"> <!-- 비회원 고유값 -->
				</c:if>
				<c:if test="${custmrValueObject.custmrSeq ne null}">
					<input type="hidden" name="custmrSeq" value="${custmrValueObject.custmrSeq}"> <!-- 회원 고유값 -->
				</c:if>
<!-- 고객 정보 -->
				<div class="container">
					<table class="table table-bordered">
					<c:if test="${callOrderValueObject.custmrClassify eq '회원'}">
						<colgroup>
							<col class="col-md-2 col-sm-2"/>           
						    <col class="col-md-2 col-sm-2"/>
						    <col class="col-md-2 col-sm-2"/>           
						    <col class="col-md-2 col-sm-2"/>
						    <col class="col-md-2 col-sm-2"/>           
						    <col class="col-md-2 col-sm-2"/>       
						</colgroup>
					</c:if>
					<c:if test="${callOrderValueObject.custmrClassify eq '비회원'}">
						<colgroup>
							<col class="col-md-2 col-sm-2"/>           
						    <col class="col-md-4 col-sm-4"/>
						    <col class="col-md-2 col-sm-2"/>           
						    <col class="col-md-4 col-sm-4"/>			      
						</colgroup>
					</c:if>
						<tr>
							<th class="active"><h6 class="text-center"><b>고객 분류</b></h6></th>
							<td colspan="5">
								<input type="radio" name="custmrClassify" value="회원" ${callOrderValueObject.custmrClassify eq "회원" ? "checked" : ""}> 회원
								<input type="radio" name="custmrClassify" value="비회원" ${callOrderValueObject.custmrClassify eq "비회원" ? "checked" : ""}> 비회원
							</td>
						</tr>
						<c:if test="${callOrderValueObject.custmrClassify eq '비회원'}">
							<tr>
								<th class="active"><h6 class="text-center"><b>이름</b></h6></th>
								<td>
									<input type="text" class="form-control"  id="recvrNme" name="recvrNme" style="width:40%">
								</td>
								<th class="active" class="text-center"><h6 class="text-center"><b>연락처</b></h6></th>
								<td>
									<input type="text" class="form-control"  id="recvrMobl" name="recvrMobl" style="width:50%">
								</td>
							</tr>
						</c:if>
						<c:if test="${callOrderValueObject.custmrClassify eq '회원'}">
							<tr>
								<th class="active" class="text-center"><h6 class="text-center"><b>고객 선택</b></h6></th>
								<td colspan="5">
									<input class="btn btn-default" type="button" onclick="javascript:custmrChoicePopup()" value="회원 선택">
								</td>
							</tr>
							<tr>
								<th class="active" class="text-center"><h6 class="text-center"><b>ID</b></h6></th>
								<td> <input type="text" class="form-control" id="custmrId" name="" value="${custmrValueObject.custmrId}"> </td>
								<th class="active" class="text-center"><h6 class="text-center"><b>이름</b></h6></th>
								<td> <input type="text" class="form-control" id="recvrNme" value="${custmrValueObject.custmrNme}" name="recvrNme"> </td>
								<th class="active" class="text-center"><h6 class="text-center"><b>연락처</b></h6></th>
								<td> <input type="text" class="form-control"  id="recvrMobl" value="${custmrValueObject.custmrMobl}" name="recvrMobl"> </td>
							</tr>
							<tr>
								<th class="active"><h6 class="text-center"><b>배송지 선택</b></h6></th>
								<td colspan="5">
									<select id="shipngPlc" name="shipngPlc" class="form-control">
										<option value="직접 입력">직접입력</option>
											<c:forEach var="shippingPlaceList" items="${shippingPlaceList}" varStatus="status">
												<option value="${shippingPlaceList.shipngPlcSeq}">${shippingPlaceList.shipngPlcNme}</option>
											</c:forEach>
									</select>
								</td>			
							</tr>
						</c:if>
						
						<tr>
							<th class="active"><h6 class="text-center"><b>우편번호</b></h6></th>
							<td colspan="5">
								<input id="sample6_postcode" type="text" class="form-control" id="recvrPostcd" name="recvrPostcd" value="${callOrderValueObject.shipngPlcPostcod}" style="width:20%; float:left">
								<input class="btn btn-default" type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" style="float:left; margin-left:10px">
							</td>
						</tr>
						<tr>
							<th class="active" class="text-center"><h6 class="text-center"><b>주소</b></h6></th>
							<td colspan="5">
								<input type="text" class="form-control" id="sample6_address" value="${callOrderValueObject.shipngPlcAdrs}" name="recvrAddrss" style="width:60%; margin-bottom:5px"> 
								<input type="text" class="form-control" id="sample6_address2" value="${callOrderValueObject.shipngPlcDetalAdrs}" name="recvrDetlAddress" style="width:60%">
							</td>
						</tr>
						<tr>
							<th class="active"><h6 class="text-center"><b>수령방법</b></h6></th>
							<td>
								<select class="form-control" id="recvrMethd" name="recvrMethd" style="width:100%">
									<option value="배달">배달</option>
									<option value="직접 수령">직접 수령</option>
								</select>
							</td>
							<th class="active" ><h6 class="text-center"><b>결제방법</b></h6></th>
							<td colspan="3">
								<select class="form-control" id="pamntMethd" name="pamntMethd" style="width:30%">
									<option value="현금">현금</option>
									<option value="카드">카드</option>
								</select>
							</td>
						</tr>
						<tr>
							<th class="active"><h6 class="text-center"><b>희망 수령 시간</b></h6></th>
							<td colspan="5">
								<input type="text" id="inputBox-ex" class='datetimepicker' value="" name="hopDelvrTim" >
							</td>
						</tr>
						<tr>
							<th class="active"><h6 class="text-center"><b>기타 요구사항</b></h6></th>
							<td colspan="5">
								<input type="text" class="form-control" value="기타" style="width:600px" id="reqmnt" name="reqmnt">
							</td>
						</tr>
					</table>
				</div>
			</form>
			
<!-- 주문 상품 정보 -->		
			<div class="container">
				<h3>상품리스트</h3>
			</div>
			<div class="container text-right">
				<input class="btn btn-default" type="button" value="상품 선택" onclick="javascript:prodctCreate()" style="margin-bottom:10px"/>
				<input class="btn btn-default" type="button" value="상품 삭제" id="deleteProdct" style="margin-bottom:10px"/>
			</div>
			<div class="container">
				<table class="table table-bordered">
					<tr class="active">
						<th class="text-center"><input type="checkbox" id="allCheck"></th>
						<th class="text-center"> 이미지 </th>
						<th class="text-center"> 상품명 </th>
						<th class="text-center"> 가격 </th>
						<th class="text-center"> 수량 </th>						
						<th class="text-center"> 총가격 </th>
					</tr>
					<c:forEach var="prodctTempList" items="${prodctTempList}" varStatus="status">
					<tr>
						<td class="text-center"><input type="checkbox" name="checkbox" value="${prodctTempList.prodctSeq}"></td>
						<td class="text-center"><img class="thumbnail" src="${prodctTempList.prodctMainImage}"/></td>
						<td class="text-center" style="width:25%"><c:out value="${prodctTempList.prodctNme}"/></td>
						<td class="text-center" style="width:15%"><input type="text" class="form-control" id="prodctSelPric${status.count}" name="prodctSelPric"  value="${prodctTempList.prodctSelPric}" disabled></td>
						<td class="text-center" style="width:15%"><input type="text" class="form-control" id="prodctNumber${status.count}" name="prodctNumber" value="1" style="width:90%" onkeyup="javascript:prodctNumber(${status.count})" ></td>
						<td class="text-center" style="width:15%"><input type="text" class="form-control"  id="allPrice${status.count}" name="allPrice" value="${prodctTempList.prodctSelPric}" style="width:90%" disabled>
						<input type="hidden" id="prodctSeq${status.count}" name="prodctSeq" value="${prodctTempList.prodctSeq}" > <!-- 상품 고유값 --></td>
					<tr>	
					</c:forEach>
				</table>
			</div>
			<div class="container text-center">
				<button class="btn btn-default" onclick="javascript:callOrderCreate()" style="width:90px">등록</button>
			</div>
		</div>
	</body>
</html>