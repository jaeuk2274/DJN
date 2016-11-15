<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<div id="prodctTable">
		<div style="width:380px;">	
			<div style="text-align:center; margin-bottom:13px; margin-right: 50px;">
				<form name="TogthProdctPagingFrm" id="TogthProdctPagingFrm" method="POST">
					<input type="hidden" name="currentPageNo" id="TogthCurrentPageNo" value="${prodctValueObject.currentPageNo}"> <!-- 현재 페이지 -->
					<input type="hidden" name="prodctSechText" value="${prodctValueObject.prodctSechText}"> <!-- 검색 상품명 -->
				</form>
				<form name="prodctSechFrm" id="prodctSechFrm" method="post">  					
					<div class="form-inline">
						<input type="text" class="form-control" name="prodctSechText" placeholder="상품명을 입력하세요.">
						<input type="button" class="btn btn-default" value="검색" onclick="togthSearch()">
					</div>
				</form>
			</div>
			<div id="tempTable" style="margin-right: 50px;">
				<table class="table table-bordered" id="togthTable">
					<colgroup>
						<col class="col-md-1 col-sm-1"/>
						<col class="col-md-6 col-sm-6"/>
						<col class="col-md-3 col-sm-3"/>
						<col class="col-md-2 col-sm-2"/>
					</colgroup>
					<tr>
<!-- 						<th>번호</th> -->
						<th>상품명</th>
						<th>판매가격</th>
						<th>재고량</th>
					</tr>
					<c:forEach items="${togthrProdctList}" var="togthrProdctList" varStatus="status">
						
						<tr onMouseOver="this.style.backgroundColor='#A5B448'" onmouseout="this.style.backgroundColor='#ffffff'" id="${togthrProdctList.prodctSeq}" onclick="prodctAddDetail(${togthrProdctList.prodctSeq})">
<%-- 							<td>${status.step}<input type="hidden" name="realProdctCheck" id="realProdctCheck" value="${togthrProdctList.prodctSeq}"></td> --%>
							<td>${togthrProdctList.prodctNme}</td>
							<td>${togthrProdctList.prodctSelPric}</td>
							<td>${togthrProdctList.qunt}
							</td>
						</tr>
						<input type="hidden" value="${togthrProdctList.prodctMainImage}" name="prodctMainImage" id="prodctMainImage">
						<input type="hidden" value="${togthrProdctList.prodctDetlImageOne}" name="prodctDetlImageOne" id="prodctDetlImageOne">
						<input type="hidden" value="${togthrProdctList.prodctDetlImageTwo}" name="prodctDetlImageTwo" id="prodctDetlImageTwo">
						<input type="hidden" value="${togthrProdctList.prodctDetlImageThree}" name="prodctDetlImageThree" id="prodctDetlImageThree">
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="text-center" style="width:380px; margin-right: 35px;">
			<nav>
				<ul class="pagination">
					<c:if test = "${prodctValueObject.currentPageNo>20}">
						<li>
							<a href="javascript:togthProdctPaging(1)" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${prodctValueObject.currentPageNo>10}">
						<li>
							<a href="javascript:togthProdctPaging(${prodctValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
							<span aria-hidden="true">&lsaquo;</span></a>
						</li>
					</c:if> 	
								  	
					<c:forEach varStatus="status" begin="${prodctValueObject.firstPageNoOnPageList}" end="${prodctValueObject.lastPageNoOnPageList}">
					
						<c:if test="${prodctValueObject.currentPageNo==status.current}">
							<li class="active">
								<a href="javascript:togthProdctPaging(${status.current})">${status.current}</a>
							</li>
						</c:if>
						<c:if test="${prodctValueObject.currentPageNo!=status.current}">
							<li><a href="javascript:togthProdctPaging(${status.current})">${status.current}</a></li>
						</c:if>                   									
					</c:forEach>	
					<c:if test = "${prodctValueObject.firstPageNoOnPageList + 10 < prodctValueObject.totalPageCount}">
						<li>
							<a href="javascript:togthProdctPaging(${prodctValueObject.lastPageNoOnPageList+1})" aria-label="Next">
							<span aria-hidden="true">&rsaquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${prodctValueObject.firstPageNoOnPageList + 20 < prodctValueObject.totalPageCount}">
						<li>
							<a href="javascript:togthProdctPaging(${prodctValueObject.totalPageCount})" aria-label="Next">
							<span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>
	</div>
</body>
</html>