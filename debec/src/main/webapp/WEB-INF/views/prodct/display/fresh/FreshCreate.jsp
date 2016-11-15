  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

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

	<!-- JSTree 관련 JS -->
	<script src="/resources/js/jstree/jstree.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="/resources/js/jstree/themes/proton/style.min.css">
	
	<!-- 신선식품 상품추가 관련 JS -->
	<script src="/resources/js/display-js/fresh.prodctAdd.js" type="text/javascript" charset="utf-8"></script>
</head>
<body onBeforeUnload="closeIt()">
	<form name="prodctPagingFrm" id="prodctPagingFrm" method="post">      
		<input type="hidden" name="prodctSechText" value="${FreshValueObject.prodctSechText}"> <!-- 검색 상품명 -->
		<input type="hidden" name="currentPageNo" value="${FreshValueObject.currentPageNo}"> <!-- 현재 페이지 -->
	</form>
	<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
		<h3>신선 식품 등록</h3>
	</div>
	
	<div class="form-group"></div>
	
	<div class="container form-inline">
		<div class="pull-left" style="display:inline-block;">
			<table class="table table-bordered" style="width:270px;">
				<tr>
					<th>카테고리</th>
				</tr>
				<tr style="vertical-align:top">
				<!-- td에 height값을 줘야 JSTree가 고정됨 -->
					<td style="height:500px;"><div id="tree" style="max-height:100%; overflow:auto"></div></td>
				</tr>
			</table>
		</div>
		
		<div class="pull-left" id="prodctTable" style="margin-left:10px; display:inline-block; width:420px;">
			<c:import url="/freshAdList.do"/>
		</div>
		
		<div class="pull-left" style="margin-left:10px; display:inline-block; width:420px;">
			<form id="freshAdForm">
				<input type="hidden" value="" id="prodctSeq" name="prodctSeq">
				<table class="table table-bodered">
					<colgroup>
						<col style="width:40%">
						<col style="width:60%">
					</colgroup>
					<tr>
						<td colspan="2" style="height:250px; text-align:center">
							<img src="" id="prodctImg" style="width:200px; height:200px">
						</td>
					</tr>
					<tr>
						<th class="active text-center">상품번호</th>
						<td id="prodctSeq2"></td>
					</tr>
					<tr>
						<th class="active text-center">상품명</th>
						<td id="prodctNme"></td>
					</tr>
					<tr>
						<th class="active text-center">판매 가격</th>
						<td id="selPric"></td>
					</tr>
					<tr>
						<th class="active text-center">판매 시작 시간</th>
						<td>
							<input type="text" class="form-control" id="rtcInput" size="20" readonly="readonly" />	
						</td>
					</tr>
					<tr>
						<th class="active text-center">상태</th>
						<td>
							<select class="form-control" name="stat" id="stat">
						        <option value="Y" selected>판매중</option>
						        <option value="N">판매중지</option>
						    </select>
						</td>
					</tr>
				</table>
				<div class="text-center">
					<input type="button" class="btn btn-default" value="등록" onclick="insertProdct()">
					<input type="button" class="btn btn-default" value="취소" onclick="cancelProdct()">
				</div>
			</form>
		</div>
	</div>
</body>
</html>