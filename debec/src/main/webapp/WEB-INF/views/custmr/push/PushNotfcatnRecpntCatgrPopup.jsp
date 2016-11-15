<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	
	<script src="/resources/js/display-js/jquery.tablednd.js"></script>
	
	<!-- JSTree 관련 JS -->
	<script src="/resources/js/jstree/jstree.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="/resources/js/jstree/themes/proton/style.min.css">
	
	<script src="/resources/js/push-js/pushNotfcatnRecpnt.js"></script>
</head>
<body>
<div id="pushRecpntProdct" class="tab_content">
	<form name="pushRecpntProdctPagingFrm" method="post">      
		<input type="hidden" name="currentPageNo" value="${pushVo.currentPageNo}"> <!-- 현재 페이지 -->
		<input type="hidden" name="searchWrd" value="${pushVo.searchWrd}">
		<input type="hidden" name="searchCnd" value="${pushVo.searchCnd}">
	</form>
	<form name="pushDespRecrdFrm" method="POST">
		<table class="table" style="width: 50%;">
			<tr>
	   			<td onclick="javascript:pushRecpnt()">전체</td>
	   			<td onclick="javascript:pushRecpntProdctPopup()">상품</td>
	   			<td class="active" onclick="javascript:pushRecpntCatgrPopup()">카테고리</td>
	   			<td onclick="javascrirpt:pushRecpntPackgPopup()">패키지</td>
	   			<td onclick="pushRecpntGropPurchsPopup()">공동구매</td>
	   		</tr>
		</table>
	</form>
	<div class="container">
		<div class="pull-left" style="display:inline-block;">
			<table class="table table-bordered" style="width:270px;">
				<tr>
					<th class="active">카테고리</th>
				</tr>
				<tr>
				<!-- td에 height값을 줘야 JSTree가 고정됨 -->
					<td style="height:500px;"><div id="pushTree" style="max-height:100%; overflow:auto"></div></td>
				</tr>
			</table>
		</div>
		
		<div id="pushCategoryList"class="pull-left" style="margin-left:10px; display:inline-block; width:380px;">
			<c:import url="/pushProdctAdList.do"/>	
		</div>
	</div>
</div>
</body>
</html>