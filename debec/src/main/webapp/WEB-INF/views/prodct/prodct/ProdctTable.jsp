<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 
* 연관 상품 등록 팝업의 게시판 파트 이다.
* 
* history :
*        하원식, 1.0, 2016/08/09 – 초기 작성
* author : 하원식
* version : 1.0, 2016/08/09  - 초기 작성
* see : 관련된 모듈을 기술한다.
//-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
		<!-- 합쳐지고 최소화된 최신 CSS -->
      <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
      
      <!-- 부가적인 테마 -->
      <link href="/resources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
      
      <!-- 이미지 썸네일 CSS -->
      <link href="/resources/common/css/thumbnail.css" rel="stylesheet" type="text/css"/>
      
      
      <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
      <script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<form name="prodctPagingFrm" id="prodctPagingFrm" method="post">      
			<input type="hidden" name="prodctSechText" value="${prodctValueObject.prodctSechText}"> <!-- 검색 상품명 -->
			<input type="hidden" name="currentPageNo" value="${prodctValueObject.currentPageNo}"> <!-- 현재 페이지 -->
		</form>
		<div  style=" overflow:auto; border-top:1px solid; border-bottom:1px solid;">
			<table class="table table-bordered text-center" style="table-layout:fixed; word-break:break-all;">
				<colgroup>
						
					<col class="col-md-4 col-sm-4">
					<col class="col-md-1 col-sm-1">
					<col class="col-md-1 col-sm-1">
				</colgroup>			
				<tr>
					<th class="text-center">상품명</th>
					<th class="text-center">판매가격</th>
					<th class="text-center">재고량</th>
				</tr>
				<c:choose>
					<c:when test="${fn:length(selProdctList) == 0}">
						<tr>
							<td colspan="3">
								<h4>해당하는 상품이 없습니다</h4>
							</td>
						</tr>
					</c:when>
					<c:when test="${fn:length(selProdctList) != 0}">
						<c:forEach items="${selProdctList}" var="selProdct" varStatus="status">	
							<tr onclick="javascript:prodctChoice(${selProdct.prodctSeq})" id="${selProdct.prodctSeq}">
								<td>${selProdct.prodctNme}</td>
								<td>${selProdct.prodctSelPric}원</td>
								<td>${selProdct.prodctStck}</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>				
			</table>
		</div>	
		<div class="container-fluid text-center" >
			<nav>
				<ul class="pagination">
					<c:if test = "${prodctValueObject.currentPageNo>20}">
						<li>
							<a href="javascript:prodctPaging(1)" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${prodctValueObject.currentPageNo>10}">
						<li>
							<a href="javascript:prodctPaging(${prodctValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
							<span aria-hidden="true">&lsaquo;</span></a>
						</li>
					</c:if> 	
								  	
					<c:forEach varStatus="status" begin="${prodctValueObject.firstPageNoOnPageList}" end="${prodctValueObject.lastPageNoOnPageList}">
					
						<c:if test="${prodctValueObject.currentPageNo==status.current}">
							<li class="active">
								<a href="javascript:prodctPaging(${status.current})">${status.current}</a>
							</li>
						</c:if>
						<c:if test="${prodctValueObject.currentPageNo!=status.current}">
							<li><a href="javascript:prodctPaging(${status.current})">${status.current}</a></li>
						</c:if>                   									
					</c:forEach>	
					<c:if test = "${prodctValueObject.firstPageNoOnPageList + 10 < prodctValueObject.totalPageCount}">
						<li>
							<a href="javascript:prodctPaging(${prodctValueObject.lastPageNoOnPageList+1})" aria-label="Next">
							<span aria-hidden="true">&rsaquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${prodctValueObject.firstPageNoOnPageList + 20 < prodctValueObject.totalPageCount}">
						<li>
							<a href="javascript:prodctPaging(${prodctValueObject.totalPageCount})" aria-label="Next">
							<span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>  
				</ul>
			</nav>		
			<form name="prodctSechFrm" id="prodctSechFrm" method="post">  				
				<div class="container-fluid ">
					<input type="text" class="form-control" name="prodctSechText" placeholder="상품명을 입력하세요." style="width:60%;float:left;">
					<input type="button" class="btn btn-default" value="검색" onclick="javascript:prodctSech()" style="float:left;">
					<input type="button" class="btn btn-default" value="닫기" onclick="javascript:prodctCancel()" style="float:right;">
				</div>
			</form>
		</div>
	</div>
</body>
</html>