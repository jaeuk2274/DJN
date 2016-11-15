<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!-- 
* 패키지의 거래처 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        김대현, 1.0, 2016/08/19 – 초기 작성
* author : 김동욱
* version : 1.0, 2016/08/19  - 초기 작성
* see : 관련된 모듈을 기술한다.
// -->
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
	
	<!-- packg 관련 JS -->
	<script src="/resources/js/display-js/clint.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body onbeforeunload="closeIt()">
		<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
			<h3>거래처 선택</h3>
		</div>
		
		<div class="form-group"></div>
		 
		<div class="container form-inline">
			<form name="clintSechFrm" id="clintSechFrm" method="post">
				<table>
					<colgroup>
						<col style="width:30%"/>                
					    <col style="width:70%"/>          
					    <col style="width:10%"/>           
					</colgroup>
					<tr>
						<td>
							<select class="form-control" id="clintListSelectBox" name="clintListSelectBox">
								<option value="clint_nme">거래처명
								<option value="clint_mangr">담당자
							</select>
						</td>
						<td>
							<input type="text" class="form-control" id="clintListSearchText" name="clintListSearchText">
						</td>
						<td>
							<input type="button" class="btn btn-default" value="검색" onclick="javascript:clintListSearch()">
						</td>
					</tr>
				</table>
			</form>
		</div>

		<div class="form-inline" id="clintTable">
		   <form name="clintPagingFrm" id="clintPagingFrm" method="post">      
				<input type="hidden" name="clintListSearchText" value="${ClintValueObjevt.clintListSearchText}"> <!-- 검색 상품명 -->
				<input type="hidden" name="currentPageNo" value="${ClintValueObject.currentPageNo}"> <!-- 현재 페이지 -->
			</form>
			
			<!-- 거래처 목록 리스트 -->
			<div class="container">
				<table class="table table-bordered">
					<colgroup>
						<col class="col-md-3 col-sm-3"/>                
					    <col class="col-md-2 col-sm-2"/>          
					    <col class="col-md-3 col-sm-3"/>           
					    <col class="col-md-3 col-sm-3"/>
					    <col class="col-md-1 col-sm-1"/>
					</colgroup>
					<tr class="active">
						<th class="text-center">거래처명</th>
						<th class="text-center">담당자</th>
						<th class="text-center">담당자 번호</th>
						<th class="text-center">계좌번호</th>
						<th class="text-center">부가세</th>
					</tr>
					<c:forEach items="${ClintList}" var="clintVo" varStatus="status">
						<tr onclick="clickClint(${clintVo.clintSeq})" onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
							<td class="text-center">${clintVo.clintNme}</td>
							<td>${clintVo.clintMangr}</td>
							<td>${clintVo.clintMangrNum}</td>
							<td>${clintVo.clintAcoutNum}</td>
							<td class="text-center">${clintVo.clintSurtaxCheck}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="container text-center">
				<ul class="pagination">
					<c:if test = "${ClintValueObject.currentPageNo>20}">
						<li>
							<a href="javascript:clintPaging(1)" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${ClintValueObject.currentPageNo>10}">
						<li>
							<a href="javascript:clintPaging(${ClintValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
							<span aria-hidden="true">&lsaquo;</span></a>
						</li>
					</c:if> 	
								  	
					<c:forEach varStatus="status" begin="${ClintValueObject.firstPageNoOnPageList}" end="${ClintValueObject.lastPageNoOnPageList}">
					
						<c:if test="${ClintValueObject.currentPageNo==status.current}">
							<li class="active">
								<a href="javascript:clintPaging(${status.current})">${status.current}</a>
							</li>
						</c:if>
						<c:if test="${ClintValueObject.currentPageNo!=status.current}">
							<li><a href="javascript:clintPaging(${status.current})">${status.current}</a></li>
						</c:if>                   									
					</c:forEach>	
					<c:if test = "${ClintValueObject.firstPageNoOnPageList + 10 < ClintValueObject.totalPageCount}">
						<li>
							<a href="javascript:clintPaging(${ClintValueObject.lastPageNoOnPageList+1})" aria-label="Next">
							<span aria-hidden="true">&rsaquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${ClintValueObject.firstPageNoOnPageList + 20 < ClintValueObject.totalPageCount}">
						<li>
							<a href="javascript:clintPaging(${ClintValueObject.totalPageCount})" aria-label="Next">
							<span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>  
				</ul>
			</div>
		</div>
   </body>
</html>