<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">	
	<title>대신 장봐주는 남자 - 대.장.남</title>
</head>
<body>
	<div id="tempTable">
		<form name="tempPagingFrm" id="tempPagingFrm" method="post">      
			<input type="hidden" name="tempSechText" value="${PackgValueObject.tempSechText}"> <!-- 검색 상품명 -->
			<input type="hidden" name="currentPageNo" value="${PackgValueObject.currentPageNo}"> <!-- 현재 페이지 -->
		</form>
		<div style="width:380px;">
			<div style="text-align:center; margin-bottom:13px;">
				<form name="tempSechFrm" id="tempSechFrm" method="post" onsubmit="return false">  
					<div class="form-inline">
						<input type="text" class="form-control" name="tempSechText" class="form-control" placeholder="상품명을 입력하세요." onKeyPress="if(event.keyCode=='13') tempSech()">
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
					<tr>
						<th class="text-center"><input type="checkbox" id="tempAllCheck" onclick="allTChecking()"/></th>
						<th class="text-center">상품명</th>
						<th class="text-center">판매가격</th>
						<th class="text-center">재고량</th>
					</tr>
					<c:forEach items="${PackgProdctAdTempList}" var="PackgVo" varStatus="status">	
						<tr id="${PackgVo.prodctSeq}" onMouseOver="this.style.backgroundColor='#A5B448'" onmouseout="this.style.backgroundColor='#ffffff'">
							<td><input type="checkbox" name="tempProdctCheck" value="${PackgVo.prodctSeq}"></td>
							<td style="text-overflow:ellipsis; overflow:hidden; white-space:nowrap;">${PackgVo.prodctNme}</td>
							<td>${PackgVo.selPric}</td>
							<td>${PackgVo.qunt}</td>
						</tr>
					</c:forEach>				
				</table>	
			</form>
		</div>
		<div class="text-center" style=" width:380px;">
			<ul class="pagination">
				<c:if test = "${PackgValueObject.currentPageNo>20}">
					<li>
						<a href="javascript:tempPaging(1)" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:if>  
				<c:if test = "${PackgValueObject.currentPageNo>10}">
					<li>
						<a href="javascript:tempPaging(${PackgValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
						<span aria-hidden="true">&lsaquo;</span></a>
					</li>
				</c:if> 	
							  	
				<c:forEach varStatus="status" begin="${PackgValueObject.firstPageNoOnPageList}" end="${PackgValueObject.lastPageNoOnPageList}">
				
					<c:if test="${PackgValueObject.currentPageNo==status.current}">
						<li class="active">
							<a href="javascript:tempPaging(${status.current})">${status.current}</a>
						</li>
					</c:if>
					<c:if test="${PackgValueObject.currentPageNo!=status.current}">
						<li><a href="javascript:tempPaging(${status.current})">${status.current}</a></li>
					</c:if>                   									
				</c:forEach>	
				<c:if test = "${PackgValueObject.firstPageNoOnPageList + 10 < PackgValueObject.totalPageCount}">
					<li>
						<a href="javascript:tempPaging(${PackgValueObject.lastPageNoOnPageList+1})" aria-label="Next">
						<span aria-hidden="true">&rsaquo;</span></a>
					</li>
				</c:if>  
				<c:if test = "${PackgValueObject.firstPageNoOnPageList + 20 < PackgValueObject.totalPageCount}">
					<li>
						<a href="javascript:tempPaging(${PackgValueObject.totalPageCount})" aria-label="Next">
						<span aria-hidden="true">&raquo;</span></a>
					</li>
				</c:if>  
			</ul>
		</div>
	</div>
</body>
</html>