<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 임시 테이블 JSP 입니다.
* 
* history : 김민아, 1.0, 2016/08/08 - 초기 작성
* author : 김민아
* version : 1.0, 2016/08/08  - 초기 작성
* see : 임시 테이블
//-->  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>대신 장봐주는 남자 - 대.장.남</title>

	</head>
	<body>
		<div id="tempTable">
			<form name="tempPagingFrm" id="tempPagingFrm" method="post">      
				<input type="hidden" name="tempSechText" value="${prodctFestivalValueObject.tempSechText}"> <!-- 검색 상품명 -->
				<input type="hidden" name="currentPageNo" value="${prodctFestivalValueObject.currentPageNo}"> <!-- 현재 페이지 -->
			</form>
			<div style=" width:380px;">
				<div style="text-align:center; margin-bottom:13px;">
					<form name="tempSechFrm" id="tempSechFrm" method="post">  
						<div class="form-inline">
							<input type="text" class="form-control" name="tempSechText"  placeholder="상품명을 입력하세요.">
							<input type="button" class="btn btn-default" value="검색" onclick="javascript:tempSech()">
						</div>	
					</form>
				</div>
				<form id="tempForm">
					<table class="table table-hover text-center">
						<colgroup>
							<col class="col-md-2 col-sm-2"/>
							<col class="col-md-4 col-sm-4"/>
							<col class="col-md-3 col-sm-3"/>
							<col class="col-md-3 col-sm-3"/>
						</colgroup>	
						<tr class="active">
							<th class="text-center"><input type="checkbox" id="tempAllCheck" onclick="allTChecking()"/></th>
							<th class="text-center">상품명</th>
							<th class="text-center">판매가격</th>
							<th class="text-center">재고량</th>
						</tr>
						<c:forEach items="${prodctAdTempList}" var="prodctAdTempList" varStatus="status">	
							<tr id="${prodctAdTempList.prodctSeq}">
								<td><input type="checkbox" name="tempProdctCheck" value="${prodctAdTempList.prodctSeq}"></td>
								<td>${prodctAdTempList.prodctNme}</td>
								<td>${prodctAdTempList.selPric}</td>
								<td>${prodctAdTempList.prodctQunt}</td>
							</tr>
						</c:forEach>
					</table>	
				</form>
				<div class="text-center" style=" width:380px;">
					<ul class="pagination">
						<c:if test = "${prodctFestivalValueObject.currentPageNo>20}">
							<li>
								<a href="javascript:tempPaging(1)" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span></a>
							</li>
						</c:if>  
						<c:if test = "${prodctFestivalValueObject.currentPageNo>10}">
							<li>
								<a href="javascript:tempPaging(${prodctFestivalValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
								<span aria-hidden="true">&lsaquo;</span></a>
							</li>
						</c:if> 									  	
						<c:forEach varStatus="status" begin="${prodctFestivalValueObject.firstPageNoOnPageList}" end="${prodctFestivalValueObject.lastPageNoOnPageList}">		
							<c:if test="${prodctFestivalValueObject.currentPageNo==status.current}">
								<li class="active">
									<a href="javascript:tempPaging(${status.current})">${status.current}</a>
								</li>
							</c:if>
							<c:if test="${prodctFestivalValueObject.currentPageNo!=status.current}">
								<li><a href="javascript:tempPaging(${status.current})">${status.current}</a></li>
							</c:if>                   									
						</c:forEach>	
						<c:if test = "${prodctFestivalValueObject.firstPageNoOnPageList + 10 < prodctFestivalValueObject.totalPageCount}">
							<li>
								<a href="javascript:tempPaging(${prodctFestivalValueObject.lastPageNoOnPageList+1})" aria-label="Next">
								<span aria-hidden="true">&rsaquo;</span></a>
							</li>
						</c:if>  
						<c:if test = "${prodctFestivalValueObject.firstPageNoOnPageList + 20 < prodctFestivalValueObject.totalPageCount}">
							<li>
								<a href="javascript:tempPaging(${prodctFestivalValueObject.totalPageCount})" aria-label="Next">
								<span aria-hidden="true">&raquo;</span></a>
							</li>
						</c:if>  
					</ul>
				</div>	
			</div>
		</div>
	</body>
</html>