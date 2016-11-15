<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!-- 
* 신선 식품 진열 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        김동욱, 1.0, 2016/08/09 – 초기 작성
* author : 김동욱
* version : 1.1, 2016/08/18  - 초기 작성
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
		
		<!-- 이미지 썸네일 CSS -->
		<link href="/resources/common/css/thumbnail.css" rel="stylesheet" type="text/css"/>
		
		<!-- 신선 식품 관련 JS -->
		<script src="/resources/js/display-js/fresh.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
		<div class="container">
			<form name="freshPagingFrm" method="post">      
				<input type="hidden" name="freshSechText" value="${freshValueObject.freshSechText}"> <!-- 검색 상품명 -->
				<input type="hidden" name="freshMinPric" value="${freshValueObject.freshMinPric}"> <!-- 최소 판매가 -->
				<input type="hidden" name="freshMaxPric" value="${freshValueObject.freshMaxPric}"> <!-- 최대 판매가 -->
				<input type="hidden" name="currentPageNo" value="${freshValueObject.currentPageNo}"> <!-- 현재 페이지 -->
			</form>	
			
			<div class="container">
				<h3>신선 식품 목록</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
				   <h5>
				     <img src="/resources/image/common/home.png"> > 진열 관리 > 
				     <a href="/hotdlList.do" style="text-decoration:none"><strong>신선 식품 목록</strong></a>
				   </h5>
				</div>
			</div>	

			<form name="freshSechFrm" class="form-inline">
				<div class="container form-group">
					<table class="table table-bordered">
						<tr>
							<td class="active text-center">
								<h5 class="text-center"><b>상품명</b></h5>
							</td>
							<td>
								<input type="text" class="form-control" id="freshSechText" name="freshSechText" placeholder="상품명을 입력하세요.">
							</td>
						</tr>
						<tr>
							<td class="active text-center">
								<h5 class="text-center"><b>할인 가격</b></h5>
							</td>
							<td>
								<input type="text" class="form-control" id="freshMinPric" name="freshMinPric" placeholder="최소 할인가를 입력하세요.">
								 ~ 
								<input type="text" class="form-control" id="freshMaxPric" name="freshMaxPric" placeholder="최대 할인가를 입력하세요.">원
							</td>
						</tr>	
					</table>
				</div>
			</form>
		
			<div class="container text-center">
				<input type="button" class="btn btn-default" value="검색" onclick="javascript:freshSech()">
			</div>
						
			<!-- 메인 상품 리스트 화면 -->
			<div class="container">
				[총 <c:out value="${FreshCount}"></c:out> 개]
			</div>
			<div class="container">
				<div id="mainDiv">
					<table class="table table-bordered" id="tableId" >
						<colgroup>
							<col style="wdith:5%">
							<col style="width:20%">
							<col style="width:35%">
							<col style="width:10%">
							<col style="width:15%">
							<col style="width:15%">
						</colgroup>
						<tr class="nodrag nodrop active">
							<th class="text-center"><input type="checkbox" id="allCheck"></th>
							<th class="text-center">이미지</th>
							<th class="text-center">상품명</th>
							<th class="text-center">판매가격</th>
							<th class="text-center">판매 시작 시간</th>
							<th class="text-center">상태</th>
						</tr>
						<c:forEach items="${FreshList}" var="freshVo" varStatus="status">	
							<tr onclick="updateFresh(${freshVo.prodctSeq})" onMouseOver="this.style.backgroundColor='#F4FFFD'" onmouseout="this.style.backgroundColor='#ffffff'">
								<td class="text-center"><input type="checkbox" name="prodctCheck" value="${freshVo.prodctSeq}" onclick="event.cancelBubble = true"></td>
								<td class="text-center"><img src="${freshVo.img}" class="thumbnail"></td>
								<td class="text-center">${freshVo.prodctNme}</td>
								<td class="text-center">${freshVo.selPric}</td>
								<td class="text-center">${freshVo.selStrtTime}</td>
								<c:choose>
									<c:when test="${freshVo.stat=='Y'}">
										<td class="text-center">판매중</td>	
									</c:when>
									<c:otherwise>
										<td class="text-center">판매중지</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</table>			
					<div class="text-right">
						<button type="button" class="btn btn-default" id="insertProdctRead">등록</button>
						<button type="button" class="btn btn-default" id="deleteProdct">삭제</button>
					</div>
				</div>
			</div>
			
			<!-- 페이징 -->
			<div class="container text-center">
				<nav>
					<ul class="pagination">
						<c:if test = "${freshValueObject.currentPageNo>20}">
							<li>
								<a href="javascript:freshPaging(1)" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span></a>
							</li>
						</c:if>  
						<c:if test = "${freshValueObject.currentPageNo>10}">
							<li>
								<a href="javascript:freshPaging(${freshValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
								<span aria-hidden="true">&lsaquo;</span></a>
							</li>
						</c:if> 	
									  	
						<c:forEach varStatus="status" begin="${freshValueObject.firstPageNoOnPageList}" end="${freshValueObject.lastPageNoOnPageList}">
						
							<c:if test="${freshValueObject.currentPageNo==status.current}">
								<li class="active">
									<a href="javascript:freshPaging(${status.current})">${status.current}</a>
								</li>
							</c:if>
							<c:if test="${freshValueObject.currentPageNo!=status.current}">
								<li><a href="javascript:freshPaging(${status.current})">${status.current}</a></li>
							</c:if>                   									
						</c:forEach>	
						<c:if test = "${freshValueObject.firstPageNoOnPageList + 10 < freshValueObject.totalPageCount}">
							<li>
								<a href="javascript:freshPaging(${freshValueObject.lastPageNoOnPageList+1})" aria-label="Next">
								<span aria-hidden="true">&rsaquo;</span></a>
							</li>
						</c:if>  
						<c:if test = "${freshValueObject.firstPageNoOnPageList + 20 < freshValueObject.totalPageCount}">
							<li>
								<a href="javascript:freshPaging(${freshValueObject.totalPageCount})" aria-label="Next">
								<span aria-hidden="true">&raquo;</span></a>
							</li>
						</c:if>  
					</ul>
				</nav>
			</div> 
		</div>
	</body>
</html>