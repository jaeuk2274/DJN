<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	
<title>Insert title here</title>
</head>
<body>
	<div id="prodctTable">
		<form name="PustSearchFrm" id="PushSearchFrm" method="post">      
			<input type="hidden" name="prodctSeq" id="prodctSeq"> <!-- 상품seq -->
		</form>
		<form name="PushProdctPagingFrm" id="PushProdctPagingFrm" method="POST">
			<input type="hidden" name="currentPageNo" id="PushCurrentPageNo" value="${prodctValueObject.currentPageNo}"> <!-- 현재 페이지 -->
			<input type="hidden" name="prodctSechText" value="${prodctValueObject.prodctSechText}"> <!-- 검색 상품명 -->
		</form>
		<div style="width:380px;">	
			<div style="text-align:center; margin-bottom:13px;">
				<form name="prodctSechFrm" id="prodctSechFrm" method="post">  					
					<div class="form-inline">
						<input type="text" class="form-control" name="prodctSechText" placeholder="상품명을 입력하세요.">
						<input type="button" class="btn btn-default" value="검색" onclick=pushhSearch()">
					</div>
				</form>
			</div>
		
			<form id="prodctForm">
				<div id="tempTable">
					<table class="table table-bordered">
						<colgroup>
							<col class="col-md-1 col-sm-1"/>
							<col class="col-md-6 col-sm-6"/>
							<col class="col-md-3 col-sm-3"/>
							<col class="col-md-2 col-sm-2"/>
						</colgroup>				
						<tr>
							<th><input type="checkbox" id="prodctAllCheck" onclick="allPChecking()"/></th>
							<th>상품명</th>
							<th>판매가격</th>
							<th>재고량</th>
						</tr>
						<c:forEach items="${pushProdctList}" var="pushProdctList" varStatus="status">
							<tr onMouseOver="this.style.backgroundColor='#A5B448'" onmouseout="this.style.backgroundColor='#ffffff'" id="${pushProdctList.prodctSeq}" onclick="prodctAddDetail(${pushProdctList.prodctSeq})">
								<td><input type="checkbox" name="realProdctCheck" id="realProdctCheck" value="${pushProdctList.prodctSeq}"></td>
								<td>${pushProdctList.prodctNme}</td>
								<td>${pushProdctList.prodctSelPric}</td>
								<td>${pushProdctList.qunt}</td>
							</tr>
						</c:forEach>
					</table>
				</div>	
			</form>
		</div>	
		<div class="text-center" style="width:380px;">
			<nav>
				<ul class="pagination">
					<c:if test = "${prodctValueObject.currentPageNo>20}">
						<li>
							<a href="javascript:pushProdctPaging(1)" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${prodctValueObject.currentPageNo>10}">
						<li>
							<a href="javascript:pushProdctPaging(${prodctValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
							<span aria-hidden="true">&lsaquo;</span></a>
						</li>
					</c:if> 	
								  	
					<c:forEach varStatus="status" begin="${prodctValueObject.firstPageNoOnPageList}" end="${prodctValueObject.lastPageNoOnPageList}">
					
						<c:if test="${prodctValueObject.currentPageNo==status.current}">
							<li class="active">
								<a href="javascript:pushProdctPaging(${status.current})">${status.current}</a>
							</li>
						</c:if>
						<c:if test="${prodctValueObject.currentPageNo!=status.current}">
							<li><a href="javascript:pushProdctPaging(${status.current})">${status.current}</a></li>
						</c:if>                   									
					</c:forEach>	
					<c:if test = "${prodctValueObject.firstPageNoOnPageList + 10 < prodctValueObject.totalPageCount}">
						<li>
							<a href="javascript:pushProdctPaging(${prodctValueObject.lastPageNoOnPageList+1})" aria-label="Next">
							<span aria-hidden="true">&rsaquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${prodctValueObject.firstPageNoOnPageList + 20 < prodctValueObject.totalPageCount}">
						<li>
							<a href="javascript:pushProdctPaging(${prodctValueObject.totalPageCount})" aria-label="Next">
							<span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>  
				</ul>
			</nav>
		</div>
	</div>
</body>
</html>