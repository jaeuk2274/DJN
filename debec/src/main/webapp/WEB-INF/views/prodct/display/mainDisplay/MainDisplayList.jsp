<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!-- 
* 메인 진열 상품 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        김동욱, 1.0, 2016/08/09 – 초기 작성
* author : 김동욱
* version : 1.2, 2016/08/16  - 초기 작성
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
		
		<!-- 이미지 썸네일 CSS -->
		<link href="/resources/common/css/thumbnail.css" rel="stylesheet" type="text/css"/>
		
		<!-- JQuery 관련 파일들 -->
		<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>	
		
		<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
		<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
	
		<!-- Table 행 바꾸는 JS -->
		<script src="/resources/js/display-js/jquery-tablednd.js" type="text/javascript" charset="utf-8"></script>
		
		<!-- mainDisplay 관련 JS -->
		<script src="/resources/js/display-js/mainDisplay.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body id="mainDisplay">
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
		<div class="container">
			<!-- 페이징 관련 -->
			<form name="mainDisplayPagingFrm" method="post">      
				<input type="hidden" name="mainDisplaySechText" value="${MainDisplayValueObject.mainDisplaySechText}"> <!-- 검색 상품명 -->
				<input type="hidden" name="mainDisplayMinPric" value="${MainDisplayValueObject.mainDisplayMinPric}"> <!-- 최소 판매가 -->
				<input type="hidden" name="mainDisplayMaxPric" value="${MainDisplayValueObject.mainDisplayMaxPric}"> <!-- 최대 판매가 -->
				<input type="hidden" name="currentPageNo" value="${MainDisplayValueObject.currentPageNo}"> <!-- 현재 페이지 -->
			</form>
			
			<div class="container">
				<h3>메인 진열 상품 목록</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
				   <h5>
				     <img src="../../../../../resources/image/common/home.png"> > 진열 관리 > 
				     <a href="/mainDisplayList.do" style="text-decoration:none"><strong>메인 진열 상품 목록</strong></a>
				   </h5>
				</div>
			</div>		
			
			<form name="mainDisplaySechFrm" class="form-inline">
				<div class="container form-group">
					<table class="table table-bordered">
						<tr>
							<td class="active text-center">
								<h5 class="text-center"><b>상품명</b></h5>
							</td>
							<td>
								<input type="text" class="form-control" id="mainDisplaySechText" name="mainDisplaySechText" placeholder="상품명을 입력하세요.">
							</td>
						</tr>
						<tr>
							<td class="active text-center">
								<h5 class="text-center"><b>판매가</b></h5>
							</td>
							<td>
								<input type="text" class="form-control" id="mainDisplayMinPric" name="mainDisplayMinPric" placeholder="최소 판매가를 입력하세요.">
								 ~ 
								<input type="text" class="form-control" id="mainDisplayMaxPric" name="mainDisplayMaxPric" placeholder="최대 판매가를 입력하세요.">원
							</td>
						</tr>	
					</table>
				</div>
			</form>
			
			
			<div class="container text-center">
				<input type="button" class="btn btn-default" value="검색" onclick="javascript:mainDisplaySech()">
			</div>
			
			<div class="form-group"></div>
			
			<div class="container text-right">
				<input type="button" class="btn btn-warning" id="insertProdctRead" value="상품 추가">
			</div>
			
			<!-- 메인 상품 리스트 화면 -->
			<div class="container">
					[총 <c:out value="${MainDisplayCount}"></c:out> 개]
			</div>
			<div class="container">
				<div id="mainDiv">
					<table class="table table-bordered" id="tableId">
						<tr class="nodrag nodrop active">
							<th class="text-center"><input type="checkbox" id="allCheck"></th>
							<th class="text-center">진열 순위</th>
							<th class="text-center">이미지</th>
							<th class="text-center">상품번호</th>
							<th class="text-center">상품명</th>
							<th class="text-center">판매가격</th>
							<th class="text-center">행사가격</th>
							<th class="text-center">판매량</th>
						</tr>
						<c:forEach items="${MainDisplayList}" var="MainDisplayVo" varStatus="status">	
							<tr id="${MainDisplayVo.prodctSeq}" onmouseover="this.style.backgroundColor='#F4FFFD'" onmouseout="this.style.backgroundColor='#ffffff'">
								<td class="text-center"><input type="checkbox" name="prodctCheck" value="${MainDisplayVo.prodctSeq}"></td>
								<td class="text-center">${MainDisplayVo.seqnc}</td>
								<td class="text-center"><img src="${MainDisplayVo.img}" class="thumbnail"></td>
								<td class="text-center">${MainDisplayVo.prodctSeq}</td>
								<td class="text-center"><a href="/selProdctRead.do?prodctSeq=${MainDisplayVo.prodctSeq}&pageType=1">${MainDisplayVo.prodctNme}</a></td>
								<td class="text-center">${MainDisplayVo.selPric}</td>
								<td class="text-center">${MainDisplayVo.evntPric}</td>
								<td class="text-center">${MainDisplayVo.selAmount}</td>
							</tr>
						</c:forEach>
					</table>
					<div class="text-right">
						<input type="button" class="btn btn-default" id="orderConfirm" value="확인">
						<input type="button" class="btn btn-default" id="deleteProdct" value="삭제">
					</div>
				</div>
			</div>
		</div>
	</body>
</html>