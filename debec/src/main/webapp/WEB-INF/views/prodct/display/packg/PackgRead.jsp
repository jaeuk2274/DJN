<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!-- 
* 패키지 상세 조회하는 화면을 보여주는 JSP입니다.
* 
* history :0.
*        김동욱, 1.0, 2016/09/08 – 초기 작성
* author : 김동욱
* version : 1.1, 2016/09/08 - 초기 작성
* see : 패키지 상품의 상세 조회를 하는 JSP
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
			
	<!-- 패키지 상세조회 관련 JS -->
	<script src="/resources/js/display-js/packg.read.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div style="margin-bottom: 130px;">
		<c:import url="/TopFrame.do"/>
	</div>
	
	<div class="container" id="mainDiv">
		<div class="container">
			<h3>패키지 정보</h3>
			<div style="display:inline-block; margin-top:1%; float:right">
			   <h5>
			     <img src="../../../../../resources/image/common/home.png"> > 패키지 관리 > 
			     <a href="/packgList.do" style="text-decoration:none"><strong>패키지 목록</strong></a>
			   </h5>
			</div>
		</div>		
			
		<div class="container">
			<form name="packgFrm" id="packgFrm">
				<input type="hidden" id="packgSeq" name="packgSeq" value="${PackgRead.packgSeq}">
				<input type="hidden" id="clintSeq" name="clintSeq" value="0">			
				<input type="hidden" id="chked_val" name="chked_val" value="0">
				
				<table class="table table-bordered">
					<colgroup>
						<col class="col-md-2 col-sm-2"/>                
					    <col class="col-md-2 col-sm-2"/>          
					    <col class="col-md-2 col-sm-2"/>           
					    <col class="col-md-2 col-sm-2"/>
					    <col class="col-md-2 col-sm-2"/>          
					    <col class="col-md-2 col-sm-2"/>
					</colgroup>
					
					<c:if test="${param.packgType == '거래처'}">
						<tr>
							<td class="active">
								<h5 class="text-center"><b>거래처 명</b></h5>
							</td>
							<td>
								<input type="text" class="form-control" id="clintNme" name="clintNme" value="${PackgRead.clintNme}" disabled>
							</td>
							<td class="active">
								<h5 class="text-center"><b>대표자</b></h5>
							</td>
							<td>
								<input type="text" class="form-control" id="clintRepresntatv" name="clintRepresntatv" value="${PackgRead.clintRepresntatv}" disabled>
							</td>
							<td class="active">
								<h5 class="text-center"><b>거래처 번호</b></h5>
							</td>
							<td>
								<input type="text" class="form-control" id="clintNum" name="clintNum" value="${PackgRead.clintNum}" disabled>
							</td>
						</tr>
						<tr>
							<td class="active">
								<h5 class="text-center"><b>팩스</b></h5>
							</td>
							<td>
								<input type="text" class="form-control" id="clintFax" name="clintFax" value="${PackgRead.clintFax}" disabled>
							</td>
							<td class="active">
								<h5 class="text-center"><b>담당자</b></h5>
							</td>
							<td>
								<input type="text" class="form-control" id="clintMangr" name="clintMangr" value="${PackgRead.clintMangr}" disabled>
							</td>
							<td class="active">
								<h5 class="text-center"><b>담당자 번호</b></h5>
							</td>
							<td>
								<input type="text" class="form-control" id="clintMangrNum" name="clintMangrNum" value="${PackgRead.clintMangrNum}" disabled>
							</td>
						</tr>
					</c:if>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>패키지 명</b></h5>
						</td>
						<td colspan="5">															
							<input type="text" class="form-control" id="pakcgNme" name="packgNme" value="${PackgRead.packgNme}" disabled>
						</td>
					</tr>	
					<tr>
						<td class="active">
							<h5 class="text-center"><b>패키지 내용</b></h5>
						</td>
						<td colspan="5">															
							<input type="text" class="form-control" id="packgCnt" name="packgCnt" value="${PackgRead.packgCnt}" disabled>
						</td>
					</tr>
				</table>
				<div class="text-right">
					<input class="btn btn-default" type="button" id="deletePackg" value="삭제" >
					<input style="display:none;" class="btn btn-default" type="button" id="finishPackg" value="완료">
					<input class="btn btn-default" type="button" id="updatePackg" value="수정">
				</div>
			</form>
		</div>
		
		<!-- 메인 상품 리스트 화면 -->
		<div class="container">
			<h3>상품 리스트</h3>
		</div>
		
		<div class="container text-right">
			<input type="button" class="btn btn-warning" value="상품 선택" onclick="prodctPopUp()">
			<input type="button" class="btn btn-warning" value="상품 삭제" id="deleteProdct">
		</div>
		
		<div class="container" id="prodctList">
			<table class="table table-bordered text-center" id="tableId">
				<colgroup>
					<col class="col-md-1 col-sm-1"/>                
				    <col class="col-md-5 col-sm-5"/>          
				    <col class="col-md-2 col-sm-2"/>           
				    <col class="col-md-2 col-sm-2"/>
				    <col class="col-md-2 col-sm-2"/>
				</colgroup>
				<tr class="active" id="tableTh">
					<th class="text-center"><input type="checkbox" id="allCheck"></th>
					<th class="text-center">상품명</th>
					<th class="text-center">매입가</th>
					<th class="text-center">가격</th>
					<th class="text-center">재고</th>
				</tr>
				<c:forEach items="${PackgProdctReadList}" var="packgVo" varStatus="status">
					<tr class="newTr" onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
						<td><input type="checkbox" name="prodctCheck" id="${packgVo.prodctSeq}"></td>
						<td>${packgVo.prodctNme}</td>
						<td>${packgVo.purchsPric}</td>
						<td>${packgVo.selPric}</td>
						<td>${packgVo.qunt}</td>
					</tr>
				</c:forEach>	
			</table>
			<div class="text-right"><strong id="allPric">패키지 총 가격 : ${PackgRead.allPric}원</strong></div>
			<div class="text-center">
				<input type="button" class="btn btn-default" id="insertPackg" value="완료" onclick="finishUpdate()">
				<input type="button" class="btn btn-default" id="cancelPackg" value="취소" onclick="location.href='/packgList.do'">
			</div>
		</div>
	</div>
</body>
</html>