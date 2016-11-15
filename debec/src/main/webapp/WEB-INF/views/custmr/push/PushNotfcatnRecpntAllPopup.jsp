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
	
	<!-- JSTree 관련 파일들 -->
	<script src="/resources/js/jstree/jstree.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="/resources/js/jstree/themes/proton/style.min.css">
	
	<script src="/resources/js/push-js/pushNotfcatnRecpntPopup.js" type="text/javascript" charset="utf-8"></script>
</head>
<body onBeforeUnload="closeIt()">
	<form name="pushRecpntAllInfoFrm" id="pushRecpntAllInfoFrm">      
		<input type="hidden" name="currentPageNo" id="currentPageNo" value="${pushVo.currentPageNo}"> <!-- 현재 페이지 -->
		<input type="hidden" name="searchWrd" value="${pushVo.searchWrd}"> <!-- 검색어 -->
		<input type="hidden" name="searchCnd" value="${pushVo.searchCnd}"> <!-- 검색조건 -->
		<c:choose>
			<c:when test="${pushVo.searchAry != null}">
				<input type="hidden" name="searchAry" id="searchAry" value="${pushVo.searchAry}"> <!-- 정렬조건 -->
				<input type="hidden" name="searchCnt" id="searchCnt" value="${pushVo.searchCnt}"> <!-- 정렬 오름차순,내림차순 -->
			</c:when>
			<c:otherwise>
				<input type="hidden" name="searchAry" id="searchAry" value="0"> <!-- 정렬조건 -->
				<input type="hidden" name="searchCnt" id="searchCnt" value="0"> <!-- 정렬 오름차순,내림차순 -->
			</c:otherwise>
		</c:choose>
	</form>
	
	<form name="pushRecpntAllInfoFrm2" id="pushRecpntAllInfoFrm2">      
		<input type="hidden" name="currentPageNo" id="currentPageNo" value="${pushVo3.currentPageNo}"> <!-- 현재 페이지 -->
		<input type="hidden" name="searchWrd" value="${pushVo3.searchWrd}"> <!-- 검색어 -->
		<input type="hidden" name="searchCnd" value="${pushVo3.searchCnd}"> <!-- 검색조건 -->
		<input type="hidden" name="prodctSeq" value="${pushVo3.prodctSeq}"> <!-- 상품번호 -->
		<c:choose>
			<c:when test="${pushVo3.searchAry != null}">
				<input type="hidden" name="searchAry" id="searchAry2" value="${pushVo3.searchAry}"> <!-- 정렬조건 -->
				<input type="hidden" name="searchCnt" id="searchCnt2" value="${pushVo3.searchCnt}"> <!-- 정렬 오름차순,내림차순 -->
			</c:when>
			<c:otherwise>
				<input type="hidden" name="searchAry" id="searchAry2" value="0"> <!-- 정렬조건 -->
				<input type="hidden" name="searchCnt" id="searchCnt2" value="0"> <!-- 정렬 오름차순,내림차순 -->
			</c:otherwise>
		</c:choose>
	</form>
	<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
		<h3>푸시 알림 수신자 등록</h3>
	</div>
	
	<div class="form-group"></div>
	
	<div class="container form-inline">
		<ul id="tabs" class="nav nav-pills" data-tabs="tabs">
			<li class="active"><a href="#all" data-toggle="tab">전체</a></li>
			<li><a href="#prodct" data-toggle="tab">상품</a></li>
<!-- 			<li><a href="#catgr" data-toggle="tab">카테고리</a></li> -->
<!-- 			<li><a href="#packg" data-toggle="tab">패키지</a></li> -->
<!-- 			<li><a href="#together" data-toggle="tab">공동구매</a></li> -->
		</ul>
		<div class="tab-content">
			<div id="all" class="tab-pane active">
				<div class="form-group"></div>
				
				<table class="table table-bordered">
					<tr>
						<td>															
							<select class="form-control" id="searchCnd" >
							    <option value="0" selected>전체</option>
							    <option value="1">아이디</option>
							    <option value="2">이름</option>
							</select>
							<input type="text" class="form-control" id="searchWrd" style="width:85%">
							<input type="button" class="btn btn-default" value="검색" onclick="pushRecpntSearch(1)">
						</td>
					</tr>
				</table>
				
				<div class="form-group"></div>
				
				<div class="pull-left" id="recpntTable" style="display:inline-block; width:720px;">
					<c:import url="/pushNotfcatnRecpntAllPopupTable.do"/>	
				</div>
				
				<div class="pull-left" style="margin-left:10px; display:inline-block; width:60px; padding-top: 200px;">
					<div style="margin-bottom: 50px;">
						<input type="button" class="btn btn-default" onclick="insertCustmr()" value="추 가">
					</div>
					<div>
						<input type="button" class="btn btn-default" onclick="deleteCustmr()" value="제 거">
					</div>
				</div>
				
				<div class="pull-left" id="tempTable" style="margin-left:10px; display:inline-block; width:340px;">
					<c:import url="/pushNotfcatnRecpntAllPopupTempTable.do"/>
				</div>
			</div>
			
			<div id="prodct" class="tab-pane">
				<div class="form-group"></div>
				
				<table class="table table-bordered">
					<tr>
						<td>															
							<select class="form-control" id="searchCnd2" >
							    <option value="0" selected>전체</option>
							    <option value="1">아이디</option>
							    <option value="2">이름</option>
							</select>
							<input type="text" class="form-control" id="searchWrd2" style="width:85%">
							<input type="button" class="btn btn-default" value="검색" onclick="pushRecpntSearch2(1)">
						</td>
					</tr>
				</table>
				
				<div class="form-group"></div>
				
				<div class="pull-left" style="display:inline-block; max-width:205px">
				<table class="table table-bordered" style="width:205px; max-width:205px">
						<tr>
							<th>카테고리</th>
						</tr>
						<tr style="vertical-align:top">
						<!-- td에 height값을 줘야 JSTree가 고정됨 -->
							<td style="height:500px; max-width:205px"><div id="tree" style="max-height:100%; max-width:205px; overflow:auto;"></div></td>
						</tr>
					</table>
				</div>
				
				<div class="pull-left" id="prodctTable" style="margin-left:10px; display:inline-block; width:195px;">
					<c:import url="/selectProdctAdList2.do"/>	
				</div>
				
				<div class="pull-left" id="recpntProdctTable" style="margin-left:10px; display:inline-block; width:300px;">
					<c:import url="/pushNotfcatnRecpntAllPopupProdctTable.do"/>	
				</div>
				
				<div class="pull-left" style="margin-left:10px; display:inline-block; width:60px; padding-top: 200px;">
					<div style="margin-bottom: 50px;">
						<input type="button" class="btn btn-default" onclick="insertCustmr2()" value="추 가">
					</div>
					<div>
						<input type="button" class="btn btn-default" onclick="deleteCustmr2()" value="제 거">
					</div>
				</div>
				
				<div class="pull-left" id="tempTable2" style="margin-left:10px; display:inline-block; width:340px;">
					<c:import url="/pushNotfcatnRecpntAllPopupProdctTempTable.do"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>