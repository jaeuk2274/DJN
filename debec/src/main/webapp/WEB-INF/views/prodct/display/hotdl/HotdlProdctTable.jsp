<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>대신 장봐주는 남자 - 대.장.남</title>
</head>
<body>
	<div id="prodctTable">
	<form name="prodctPagingFrm" id="prodctPagingFrm" method="post">      
		<input type="hidden" name="prodctSechText" value="${HotdlValueObject.prodctSechText}"> <!-- 검색 상품명 -->
		<input type="hidden" name="currentPageNo" value="${HotdlValueObject.currentPageNo}"> <!-- 현재 페이지 -->
		<input type="hidden" name="catgrSeq" value="${HotdlValueObject.catgrSeq}"> <!-- 카테고리 고유값 -->
	</form>
		<div style="width:420px;">
			<div style="text-align:center; margin-bottom:13px;">
				<form name="prodctSechFrm" id="prodctSechFrm" method="post" onsubmit="return false">  				
					<div class="form-inline">
						<input type="hidden" name="catgrSeq" value="${HotdlValueObject.catgrSeq}"> <!-- 카테고리 고유값 -->
						<input type="text" class="form-control" name="prodctSechText" placeholder="상품명을 입력하세요." onKeyPress="if(event.keyCode=='13') prodctSech()">
						<input type="button" class="btn btn-default" value="검색" onclick="javascript:prodctSech()">
					</div>
				</form>
			</div>
			<form id="prodctForm">
				<table class="table table-bordered">
					<colgroup>
						<col class="col-md-6 col-sm-6"/>
						<col class="col-md-4 col-sm-4"/>
						<col class="col-md-2 col-sm-2"/>
					</colgroup>			
					<tr>
						<th>상품명</th>
						<th>판매가격</th>
						<th>재고량</th>
					</tr>
					<c:forEach items="${ProdctAdList}" var="HotdlValueObject" varStatus="status">	
						<tr id="${HotdlValueObject.prodctSeq}" onclick="javascript:insertInfo(this.id)" onMouseOver=this.style.backgroundColor="#A5B448" onmouseout=this.style.backgroundColor="#ffffff">
							<td>${HotdlValueObject.prodctNme}</td>
							<td>${HotdlValueObject.selPric}</td>
							<td>${HotdlValueObject.qunt}</td>
						</tr>
						<input type="hidden" value="${HotdlValueObject.img}">
					</c:forEach>				
				</table>	
			</form>
		</div>
		<div class="text-center" style=" width:420px;">
			<ul class="pagination">
				<c:if test = "${HotdlValueObject.currentPageNo>20}">
					<li>
						<a href="javascript:prodctPaging(1)" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:if>  
				<c:if test = "${HotdlValueObject.currentPageNo>10}">
					<li>
						<a href="javascript:prodctPaging(${HotdlValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
						<span aria-hidden="true">&lsaquo;</span></a>
					</li>
				</c:if> 	
							  	
				<c:forEach varStatus="status" begin="${HotdlValueObject.firstPageNoOnPageList}" end="${HotdlValueObject.lastPageNoOnPageList}">
				
					<c:if test="${HotdlValueObject.currentPageNo==status.current}">
						<li class="active">
							<a href="javascript:prodctPaging(${status.current})">${status.current}</a>
						</li>
					</c:if>
					<c:if test="${HotdlValueObject.currentPageNo!=status.current}">
						<li><a href="javascript:prodctPaging(${status.current})">${status.current}</a></li>
					</c:if>                   									
				</c:forEach>	
				<c:if test = "${HotdlValueObject.firstPageNoOnPageList + 10 < HotdlValueObject.totalPageCount}">
					<li>
						<a href="javascript:prodctPaging(${HotdlValueObject.lastPageNoOnPageList+1})" aria-label="Next">
						<span aria-hidden="true">&rsaquo;</span></a>
					</li>
				</c:if>  
				<c:if test = "${HotdlValueObject.firstPageNoOnPageList + 20 < HotdlValueObject.totalPageCount}">
					<li>
						<a href="javascript:prodctPaging(${HotdlValueObject.totalPageCount})" aria-label="Next">
						<span aria-hidden="true">&raquo;</span></a>
					</li>
				</c:if>  
			</ul>
		</div>
	</div>
</body>
</html>