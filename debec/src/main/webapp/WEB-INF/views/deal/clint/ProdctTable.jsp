<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<!-- 
* 상품추가 테이블화면(왼쪽)을 보여주는 JSP입니다. (상품테이블)
* 
* history :
*        김대현, 1.0, 2016/08/18 – 초기 작성
* author : 김대현
* version : 1.0, 2016/08/18  - 초기 작성
* see : 관련된 모듈을 기술한다.
// -->
<html>
<head>
	<meta charset="UTF-8">
	<title>대신 장봐주는 남자 - 대.장.남</title>
	
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	
	<!-- 부가적인 테마 -->
	<link href="/resources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
	
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
   <div id="prodctTable">
	<form name="prodctPagingFrm" id="prodctPagingFrm" method="post">
		<input type="hidden" name="catgrSeq" value="${clintVo.catgrSeq}">	<!-- 카테고리 seq -->
		<input type="hidden" name="prodctSechText" value="${clintVo.prodctSechText}">	<!-- 검색조건 (상품명 )-->
		<input type="hidden" name="currentPageNo" value="${clintVo.currentPageNo}">		<!-- 현재 페이지 -->
	</form>
	
		<div style="width:380px;">
			<div style="text-align:center; margin-bottom:13px;">
				<form name="prodctSechFrm" id="prodctSechFrm" method="post">
					<div class="form-inline">  
						<input type="hidden" name="catgrSeq" value="${clintVo.catgrSeq}">	<!-- 카테고리 seq -->
						<input type="text" class="form-control" id="prodctSechText" name="prodctSechText" placeholder="상품명을 입력하세요.">
						<input type="button" class="btn btn-default" value="검색" onclick="javascript:prodctSech()">
					</div>
				</form>
			</div>
			<form id="prodctForm">
				<table class="table table-bordered">
					<colgroup>
						<col style="width:15%">
						<col style="width:85%">
					</colgroup>
					<tr>
						<th><input type="checkbox" id="prodctAllCheck" onclick="allPChecking()"/></th>
						<th>상품명</th>
					</tr>
					<c:forEach items="${selectProdctAdList}" var="selectProdctAdList" varStatus="status">
						<tr id="${selectProdctAdList.prodctSeq}">
							<td><input type="checkbox" name="realProdctCheck" value="${selectProdctAdList.prodctSeq}"></td>
							<td>${selectProdctAdList.prodctNme}</td>
						</tr>
					</c:forEach>
				</table>
			</form>
			<!-- 	페이징 -->
			<div class="text-center">
				<ul class="pagination">
					<c:if test = "${clintVo.currentPageNo>20}">
						<li>
							<a href="javascript:prodctPaging(1)" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${clintVo.currentPageNo>10}">
						<li>
							<a href="javascript:prodctPaging(${clintVo.firstPageNoOnPageList-1})" aria-label="Previous">
							<span aria-hidden="true">&lsaquo;</span></a>
						</li>
					</c:if> 	
					<c:forEach varStatus="status" begin="${clintVo.firstPageNoOnPageList}" end="${clintVo.lastPageNoOnPageList}">
					
						<c:if test="${clintVo.currentPageNo==status.current}">
							<li class="active">
								<a href="javascript:prodctPaging(${status.current})">${status.current}</a>
							</li>
						</c:if>
						<c:if test="${clintVo.currentPageNo!=status.current}">
							<li><a href="javascript:prodctPaging(${status.current})">${status.current}</a></li>
						</c:if>                   									
					</c:forEach>	
					<c:if test = "${clintVo.firstPageNoOnPageList + 10 < clintVo.totalPageCount}">
						<li>
							<a href="javascript:prodctPaging(${clintVo.lastPageNoOnPageList+1})" aria-label="Next">
							<span aria-hidden="true">&rsaquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${clintVo.firstPageNoOnPageList + 20 < clintVo.totalPageCount}">
						<li>
							<a href="javascript:prodctPaging(${clintVo.totalPageCount})" aria-label="Next">
							<span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>  
				</ul>
			</div>
		</div>
	</div>		
	<script>
		// 검색
		function prodctSech(){
			var formData = $("#prodctSechFrm").serialize();		//  폼 아이디가  prodctSechFrm 인 것 안에 데이터를 받는 방식 
			$.ajax({
				type : "POST",							// POST 방식
				url : "/SelectProdctAddList.do",		// 닷두로 이동
				data : formData,						// formData를 가지고 감
				success: function(msg){					// 컨트롤러에서 닷두가 돌고 리턴값을 받음(리턴값을 msg라고 받고 msg는 리턴시키는 대상의 소스임)
					document.getElementById('prodctTable').innerHTML = msg;
				}
			});
		}
	
		// 페이징
		function prodctPaging(page){
			document.prodctPagingFrm.currentPageNo.value = page;
			var formData = $("#prodctPagingFrm").serialize();
			$.ajax({
				type : "POST",
				url : "/SelectProdctAddList.do",
				data : formData,
				success: function(msg){
					document.getElementById('prodctTable').innerHTML = msg;
				}
			});
		}
	</script>
</body>
</html>








