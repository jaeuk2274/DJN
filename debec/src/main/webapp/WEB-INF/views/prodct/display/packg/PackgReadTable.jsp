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
	
	<!-- JSTree 관련 파일들 -->
	<script src="/resources/js/jstree/jstree.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="/resources/js/jstree/themes/proton/style.min.css">
	
	<!-- 패키지 상세조회의 상품추가 관련 JS -->
	<script src="/resources/js/display-js/packg.read.prodctAdd.js" type="text/javascript" charset="utf-8"></script>
</head>
	<body onbeforeunload="closeIt()">
		<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
			<h3>패키지 상품 선택</h3>
		</div>
		
		<div class="form-group"></div>
		
		<div class="container">
			<div class="pull-left" style="display:inline-block;">
				<table class="table table-bordered" style="width:270px;">
					<tr>
						<th>카테고리</th>
					</tr>
					<tr style="vertical-align:top">
					<!-- td에 height값을 줘야 JSTree가 고정됨 -->
						<td style="height:500px"><div id="tree" style="max-height:100%; overflow:auto;"></div></td>
					</tr>
				</table>
			</div>
			
			<div class="pull-left" id="prodctTable" style="margin-left:10px; display:inline-block; width:380px;">
				<c:import url="/selectPackgProdctReadAdList.do"></c:import>
			</div>
		
			<div class="pull-left" style="margin-left:10px; display:inline-block; width:60px; padding-top: 200px;">
				<div style="margin-bottom: 50px;">
					<input type="button" class="btn btn-default" id="plus" value="추 가">
				</div>
				<div>
					<input type="button" class="btn btn-default" id="minus" value="제 거">
				</div>
			</div>
			
			<div class="pull-left" id="tempTable" style="margin-left:10px; display:inline-block; width:380px;">
				<c:import url="/selectPackgProdctAdTempList.do"></c:import>
			</div>
		</div>
			
		<div class="container text-right">
			<input type="button" class="btn btn-default" value="등록" onclick="insertProdct()">
			<input type="button" class="btn btn-default" value="취소" onclick="closeIt()">
		</div>
	</body>
</html>