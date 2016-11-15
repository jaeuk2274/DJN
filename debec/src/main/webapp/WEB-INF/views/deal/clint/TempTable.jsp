<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!-- 
* 상품추가 테이블화면(오른쪽)을 보여주는 JSP입니다. (임시테이블)
* 
* history :
*        김대현, 1.0, 2016/08/18 – 초기 작성
* author : 김대현
* version : 1.0, 2016/08/18  - 초기 작성
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

	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="tempTable">
		<form name="tempPagingFrm" id="tempPagingFrm" method="post">
			<input type="hidden" name="tempSechText" value="${clintVo.tempSechText}">		<!-- 검색어(상품명) -->
			<input type="hidden" name="currentPageNo" value="${clintVo.currentPageNo}">		<!-- 현재페이지 -->
		</form>
		
		<div style="width:380px;">
			<div style="text-align:center; margin-bottom:13px;">
				<form name="tempSechFrm" id="tempSechFrm" method="post">  
					<div class="form-inline">
						<input type="text" class="form-control" id="tempSechText" name="tempSechText" placeholder="상품명을 입력하세요.">
						<input type="button" class="btn btn-default" value="검색" onclick="javascript:tempSech()">
					</div>	
				</form>
			</div>
			
			<form id="tempForm">
				<table class="table table-bordered">
					<colgroup>
						<col style="width:15%">
						<col style="width:85%">
					</colgroup>
					<tr>
						<th><input type="checkbox" id=tempAllCheck onclick="allTChecking()"/></th>
						<th>상품명</th>
					</tr>
					<c:forEach items="${selectProdctAdTempList}" var="selectProdctAdTempList" varStatus="status">
						<tr id="${selectProdctAdTempList.prodctSeq}">
							<td><input type="checkbox" name="tempProdctCheck" value="${selectProdctAdTempList.prodctSeq}"></td>
							<td> ${selectProdctAdTempList.prodctNme}</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</div>	
		
		<!-- 페이징 -->
		<div class="text-center" style=" width:380px;">
			<ul class="pagination">
				<c:if test = "${clintVo.currentPageNo>20}">
					<li>
						<a href="javascript:tempPaging(1)" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:if> 
				<c:if test = "${clintVo.currentPageNo>10}">
					<li>
						<a href="javascript:tempPaging(${clintVo.firstPageNoOnPageList-1})" aria-label="Previous">
						<span aria-hidden="true">&lsaquo;</span></a>
					</li>
				</c:if> 	
				<c:forEach varStatus="status" begin="${clintVo.firstPageNoOnPageList}" end="${clintVo.lastPageNoOnPageList}">
					<c:if test="${clintVo.currentPageNo==status.current}">
						<li class="active">
							<a href="javascript:tempPaging(${status.current})">${status.current}</a>
						</li>
					</c:if>
					<c:if test="${clintVo.currentPageNo!=status.current}">
						<li><a href="javascript:tempPaging(${status.current})">${status.current}</a></li>
					</c:if>                   									
				</c:forEach>
				<c:if test = "${clintVo.firstPageNoOnPageList + 10 < clintVo.totalPageCount}">
					<li>
						<a href="javascript:tempPaging(${clintVo.lastPageNoOnPageList+1})" aria-label="Next">
						<span aria-hidden="true">&rsaquo;</span></a>
					</li>
				</c:if>  
				<c:if test = "${clintVo.firstPageNoOnPageList + 20 < clintVo.totalPageCount}">
					<li>
						<a href="javascript:tempPaging(${clintVo.totalPageCount})" aria-label="Next">
						<span aria-hidden="true">&raquo;</span></a>
					</li>
				</c:if>  
			</ul>
		</div>
	</div>		
	<script>
	// 검색
	// 		document.tempPagingFrm.tempSechText.value=$('#tempSechText').val();
	// 		document.tempPagingFrm.currentPageNo=1;
	// 		document.tempPagingFrm.action="/SelectProdctAddTempList.do";
	// 		document.tempPagingFrm.submit();
		function tempSech(){
			var formData = $("#tempSechFrm").serialize();
			alert(formData);
			$.ajax({
				type : "POST",
				url : "/SelectProdctAddTempList.do",
				data : formData,
				success: function(msg){
					alert(msg);
					document.getElementById('tempTable').innerHTML = msg;
					alert("@@@@@");
				}
			});
		}
	
	//페이징
	// 		document.tempPagingFrm.currentPageNo.value = page;
	// 		document.tempPagingFrm.action="/SelectProdctAddTempList.do";
	// 		document.tempPagingFrm.submit();
		function tempPaging(page){
			document.tempPagingFrm.currentPageNo.value=page;
			var formData = $("#tempPagingFrm").serialize();
			alert(formData + "   ajax로 컨트롤러에 보내는 값(post형식)")
			$.ajax({
				type : "POST",
				url : "/SelectProdctAddTempList.do",
				data : formData,
				success : function(msg){
					alert(msg);
					document.getElementById('tempTable').innerHTML = msg;
					alert("임시테이블 페이징");
				}
			});
		}
	</script>
</body>
</html>