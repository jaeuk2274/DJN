<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!-- 
* 패키지 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :0.
*        김동욱, 1.0, 2016/08/13 – 초기 작성
* author : 김동욱
* version : 1.1, 2016/08/18 - 초기 작성
* see : 관련된 모듈을 기술한다.
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
	
	<script src="/resources/js/display-js/packg.create.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div style="margin-bottom: 130px;">
		<c:import url="/TopFrame.do"/>
	</div>
	
	<div class="container" id="mainDiv">
		<div class="container">
			<h3>패키지 등록</h3>
			<div style="display:inline-block; margin-top:1%; float:right">
			   <h5>
			     <img src="../../../../../resources/image/common/home.png"> > 패키지 관리 > 
			     <a href="/packgList.do" style="text-decoration:none"><strong>패키지 목록</strong></a>
			   </h5>
			</div>
		</div>	
		
		<div class="container">	
			<form name="packgFrm" id="packgFrm">
				<input type="hidden" id="clintSeq" name="clintSeq" value="0">			
				<input type="hidden" id="chked_val" name="chked_val" value="0">
				<div class="table-responsive">
					<table class="table table-bordered">
						<colgroup>
							<col class="col-md-2 col-sm-2"/>                
						    <col class="col-md-2 col-sm-2"/>          
						    <col class="col-md-2 col-sm-2"/>           
						    <col class="col-md-2 col-sm-2"/>
						    <col class="col-md-2 col-sm-2"/>          
						    <col class="col-md-2 col-sm-2"/>
						</colgroup>
						<tr id="firstTr">
							<td class="active">
								<h5 class="text-center"><b>구분</b></h5>
							</td>
							<td colspan="5">
								<input type="radio" name="packgType" value="거래처" checked="checked" onclick="packgTypeCheck(this)">거래처
								<input type="radio" name="packgType" value="일반" onclick="packgTypeCheck(this)">일반
								<input type="button" class="btn btn-default" value="거래처 선택" id="clintChoice">
							</td>
						</tr>
						<tr id="secondTr">
							<td class="active">
								<h5 class="text-center"><b>거래처 명</b></h5>
							</td>
							<td id="clintNme"></td>
							<td class="active">
								<h5 class="text-center"><b>대표자</b></h5>
							</td>
							<td id="clintRepresntatv"></td>
							<td class="active">
								<h5 class="text-center"><b>거래처 번호</b></h5>
							</td>
							<td id="clintNum"></td>
						</tr>
						<tr id="thirdTr">
							<td class="active">
								<h5 class="text-center"><b>팩스</b></h5>
							</td>
							<td id="clintFax"></td>
							<td class="active">
								<h5 class="text-center"><b>담당자</b></h5>
							</td>
							<td id="clintMangr"></td>
							<td class="active">
								<h5 class="text-center"><b>담당자 번호</b></h5>
							</td>
							<td id="clintMangrNum"></td>
						</tr>
						<tr>
							<td class="active">
								<h5 class="text-center"><b>패키지 명</b></h5>
							</td>
							<td colspan="5">															
								<input type="text" class="form-control" name="packgNme">
							</td>
						</tr>	
						<tr>
							<td class="active">
								<h5 class="text-center"><b>패키지 내용</b></h5>
							</td>
							<td colspan="5">															
								<input type="text" class="form-control" name="packgCnt">
							</td>
						</tr>
					</table>
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
		
		<div class="container">
			<div id="prodctList">
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
				</table>
				<div class="text-right"><strong id="allPric">패키지 총 가격 : 0원</strong></div>
				<div class="text-center">
					<input type="button" class="btn btn-default" id="insertPackg" value="등록" onclick="insertPackg()">
					<input type="button" class="btn btn-default" id="cancelPackg" value="취소" onclick="location.href='/packgList.do'">
				</div>
			</div>
		</div>
	</div>
</body>
</html>