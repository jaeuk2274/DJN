<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!-- 
* 핫딜 상품 진열 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        김동욱, 1.0, 2016/08/09 – 초기 작성
* author : 김동욱
* version : 1.1, 2016/08/14  - 초기 작성
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
				
		<!-- 핫딜 관련 JS -->
		<script src="/resources/js/display-js/hotdl.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
	
		<div class="container">
			<form name="hotdlPagingFrm" method="post">      
				<input type="hidden" name="hotdlSechText" value="${hotdlValueObject.hotdlSechText}"> <!-- 검색 상품명 -->
				<input type="hidden" name="hotdlMinPric" value="${hotdlValueObject.hotdlMinPric}"> <!-- 최소 판매가 -->
				<input type="hidden" name="hotdlMaxPric" value="${hotdlValueObject.hotdlMaxPric}"> <!-- 최대 판매가 -->
				<input type="hidden" name="currentPageNo" value="${hotdlValueObject.currentPageNo}"> <!-- 현재 페이지 -->
			</form>	
			
			<div class="container">
				<h3>핫딜 상품 목록</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
				   <h5>
				     <img src="/resources/image/common/home.png"> > 진열 관리 > 
				     <a href="/hotdlList.do" style="text-decoration:none"><strong>핫딜 상품 목록</strong></a>
				   </h5>
				</div>
			</div>		
			
			<form name="hotdlSechFrm" class="form-inline">
				<div class="container form-group">
					<table class="table table-bordered">
						<tr>
							<td class="active">
								<h5 class="text-center"><b>상품명</b></h5>
							</td>
							<td>
								<input type="text" class="form-control" id="hotdlSechText" name="hotdlSechText" placeholder="상품명을 입력하세요.">
							</td>
						</tr>
						<tr>
							<td class="active">
								<h5 class="text-center"><b>할인가격</b></h5>
							</td>
							<td>
								<input type="text" class="form-control" id="hotdlMinPric" name="hotdlMinPric" placeholder="최소 할인가를 입력하세요.">
								 ~ 
								<input type="text" class="form-control" id="hotdlMaxPric" name="hotdlMaxPric" placeholder="최대 할인가를 입력하세요.">원
							</td>
						</tr>	
					</table>
				</div>
			</form>
			
			<div class="container text-center">
				<input type="button" class="btn btn-default" value="검색" onclick="javascript:hotdlSech()">
			</div>
			
			<!-- 메인 상품 리스트 화면 -->
			<div class="container">
				[총 <c:out value="${HotdlCount}"></c:out> 개]
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
							<th class="text-center">남은 수량</th>
							<th class="text-center">판매가격</th>
							<th class="text-center">할인가격</th>
						</tr>
						<c:forEach items="${HotdlList}" var="hotdlVo" varStatus="status">	
							<tr id="${hotdlVo.prodctSeq}" onMouseOver="this.style.backgroundColor='#F4FFFD'" onmouseout="this.style.backgroundColor='#ffffff'">
								<td class="text-center"><input type="checkbox" name="prodctCheck" value="${hotdlVo.prodctSeq}"></td>
								<td class="text-center" onclick="javascript:updateHotdl(this.parentNode.id)"><img src="${hotdlVo.img}" class="thumbnail"></td>
								<td class="text-center" onclick="javascript:updateHotdl(this.parentNode.id)">${hotdlVo.prodctNme}</td>
								<td class="text-center" onclick="javascript:updateHotdl(this.parentNode.id)">${hotdlVo.remngAmont}</td>
								<td class="text-center" onclick="javascript:updateHotdl(this.parentNode.id)">${hotdlVo.selPric}</td>
								<td class="text-center" onclick="javascript:updateHotdl(this.parentNode.id)">${hotdlVo.discntPric}</td>
							</tr>
						</c:forEach>
					</table>			
					<div class="text-right">
						<input type="button" class="btn btn-default" id="insertProdctRead" value="등록">
						<input type="button" class="btn btn-default" id="deleteProdct" value="삭제">
					</div>
				</div>
			</div>
			
			<!-- 페이징 -->
			<div class="container text-center">
				<nav>
					<ul class="pagination">
						<c:if test = "${hotdlValueObject.currentPageNo>20}">
							<li>
								<a href="javascript:hotdlPaging(1)" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span></a>
							</li>
						</c:if>  
						<c:if test = "${hotdlValueObject.currentPageNo>10}">
							<li>
								<a href="javascript:hotdlPaging(${hotdlValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
								<span aria-hidden="true">&lsaquo;</span></a>
							</li>
						</c:if> 	
									  	
						<c:forEach varStatus="status" begin="${hotdlValueObject.firstPageNoOnPageList}" end="${hotdlValueObject.lastPageNoOnPageList}">
						
							<c:if test="${hotdlValueObject.currentPageNo==status.current}">
								<li class="active">
									<a href="javascript:hotdlPaging(${status.current})">${status.current}</a>
								</li>
							</c:if>
							<c:if test="${hotdlValueObject.currentPageNo!=status.current}">
								<li><a href="javascript:hotdlPaging(${status.current})">${status.current}</a></li>
							</c:if>                   									
						</c:forEach>	
						<c:if test = "${hotdlValueObject.firstPageNoOnPageList + 10 < hotdlValueObject.totalPageCount}">
							<li>
								<a href="javascript:hotdlPaging(${hotdlValueObject.lastPageNoOnPageList+1})" aria-label="Next">
								<span aria-hidden="true">&rsaquo;</span></a>
							</li>
						</c:if>  
						<c:if test = "${hotdlValueObject.firstPageNoOnPageList + 20 < hotdlValueObject.totalPageCount}">
							<li>
								<a href="javascript:hotdlPaging(${hotdlValueObject.totalPageCount})" aria-label="Next">
								<span aria-hidden="true">&raquo;</span></a>
							</li>
						</c:if>  
					</ul>
				</nav>
			</div> 
		</div>
	</body>
</html>