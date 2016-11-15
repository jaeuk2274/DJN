<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 
* 상품 추가 팝업 JSP 입니다.
* 
* history : 김민아, 1.0, 2016/08/08 - 초기 작성
* author : 김민아
* version : 1.0, 2016/08/08  - 초기 작성
* see : 대백제 수정 화면
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
	
	<!-- 이미지 썸네일 CSS -->
	<link href="/resources/common/css/thumbnail.css" rel="stylesheet" type="text/css"/>
	
	<!-- JQuery 관련 파일들 -->
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>   
      
	 <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	 <script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
	
	<!-- 카테고리 관련 JS 스크립트 -->
	<script src="/resources/js/prodct-js/catgrChoice.js"></script>
	
	<!-- JSTree 관련 파일들 -->
	<script src="/resources/js/jstree/jstree.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="/resources/js/jstree/themes/proton/style.min.css">
	<!-- debecFestivalCreate 관련 JS -->
	<script src="../../../../resources/js/debecFestival-js/debecFestivalUpdate.js" type="text/javascript" charset="utf-8"></script>
	<script src="../../../../resources/js/debecFestival-js/debecFestivalCreate.js" type="text/javascript" charset="utf-8"></script>
	<script src="../../../../resources/js/debecFestival-js/debecFestivalCreate.prodctAdd.js" type="text/javascript" charset="utf-8"></script>
		

	</head>
	<body onbeforeunload="closeIt()">
		<div class="container" style="color:#ffffff; font-size:18px; background-color: #403A39; border-radius:4px; padding:3px 8px;">
			<h3>대백제 상품 등록</h3>
		</div>	
		<div class="form-group">
		</div>
		<form name="prodctFrm">
			<input type="hidden" id="schedlSeq" name="schedlSeq" value="${schedlSeq}">
			<div class="container" id="insertProdct">		
				<div class="pull-left" style="display:inline-block;">
					<table class="table table-bordered" style="width:270px;">
						<tr>
							<th>카테고리</th>
						</tr>
						<tr>
						<!-- td에 height값을 줘야 JSTree가 고정됨 -->
							<td style="height:500px; vertical-align: top;"><div id="tree" style="max-height:100%; overflow:auto"></div></td>
						</tr>
					</table>
				</div>	
				<div id="prodctAdList"  class="pull-left" style="margin-left:10px; display:inline-block; width:380px;">
					<c:import url="/prodctAddList.do"/>	
				</div>
				<div class="pull-left" style="margin-left:10px; display:inline-block; width:60px; padding-top: 200px;">
					<div style="margin-bottom: 50px;">
						<input class="btn btn-default" type="button" id="plus" value="추가">
					</div>
					<div>
						<input class="btn btn-default" type="button" id="minus" value="제거">
					</div>
				</div>		
				<div class="pull-left" style="margin-left:10px; display:inline-block; width:380px;">
					<c:import url="/prodctAdTempList.do"/>
				</div>			
			</div>		
			<div class="container text-right">
				<c:choose>
					<c:when test = "${divison=='U'}">
						<input type="button" class="btn btn-default" value="등록" style="width:150px;" onclick="javascript:prodctUpdate()">
					</c:when>
					<c:otherwise>
						<input type="button" class="btn btn-default" value="확인" style="width:150px;" onclick="javascript:prodctCancel()">
					</c:otherwise>
				</c:choose>
			</div>
		</form>
	</body>
</html>