<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		
		<!-- 합쳐지고 최소화된 최신 CSS -->
		<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
      
		<!-- 부가적인 테마 -->
		<link href="/resources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
		
		<!-- 이미지 썸네일 CSS -->
		<link href="/resources/common/css/thumbnail.css" rel="stylesheet" type="text/css"/>
		
		<!-- JQuery 관련 파일들 -->
		<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>   
		
		<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
		<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
		
		<script>
			// 메인 검색
			function custmrSech() {
				document.custmrSechFrm.action ="/custmrChoicePopup.do";
				document.custmrSechFrm.submit();
			}
			
			// 메인 페이징
			function custmrPaging(page) {
				document.custmrPagingFrm.currentPageNo.value = page;
				document.custmrPagingFrm.action ="/custmrChoicePopup.do";
				document.custmrPagingFrm.submit();
			} 
			
			// 고객 선택
			function custmrChoice(custmrSeq) {
				opener.document.custmrSechFrm.custmrSeq.value = custmrSeq;
				opener.document.custmrSechFrm.custmrClassify.value ="회원";
				opener.location.href="javascript:custmrChoice();";
				self.close();
	         };
			
		</script>
	</head>
	<body>
		<div class="container">
			<h3>회원 선택</h3>
		</div>
		<form name="custmrPagingFrm" id="custmrPagingFrm" method="post">      
			<input type="hidden" name="custmrSechText" value="${custmrValueObject.custmrSechText}"> <!-- 검색 상품명 -->
			<input type="hidden" name="currentPageNo" value="${custmrValueObject.currentPageNo}"> <!-- 현재 페이지 -->
			<input type="hidden" name="custmrSeq" value="1"> <!-- 회원 고유값 -->
		</form>
		<div class="container">
			<table class="table table-bordered">
				<tr class="active">
					<th> ID </th>
					<th> 이름 </th>
					<th> 성별 </th>
					<th> 연락처 </th>
					<th>선택</th>
				</tr>
				<c:forEach var="custmrList" items="${custmrList}" varStatus="status">
					<tr id="${custmrList.custmrSeq}">
						<td><c:out value="${custmrList.custmrId}"/></td>
						<td><c:out value="${custmrList.custmrNme}"/></td>
						<td><c:out value="${custmrList.custmrGendr}"/></td>
						<td><c:out value="${custmrList.custmrMobl}"/></td>
						<td><input class="btn btn-default" type="button" value="선택" onclick="javascript:custmrChoice(${custmrList.custmrSeq})" ></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<!-- 페이징 -->
		<div class="container text-center">
			<nav>
				<ul class="pagination">
					<c:if test = "${custmrValueObject.currentPageNo>20}">
						<li>
							<a href="javascript:custmrPaging(1)" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${custmrValueObject.currentPageNo>10}">
						<li>
							<a href="javascript:custmrPaging(${custmrValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
							<span aria-hidden="true">&lsaquo;</span></a>
						</li>
					</c:if> 		  	
					<c:forEach varStatus="status" begin="${custmrValueObject.firstPageNoOnPageList}" end="${custmrValueObject.lastPageNoOnPageList}">
						<c:if test="${custmrValueObject.currentPageNo==status.current}">
							<li class="active">
								<a href="javascript:custmrPaging(${status.current})">${status.current}</a>
							</li>
						</c:if>
						<c:if test="${custmrValueObject.currentPageNo!=status.current}">
							<li><a href="javascript:custmrPaging(${status.current})">${status.current}</a></li>
						</c:if>                   									
					</c:forEach>	
					<c:if test = "${custmrValueObject.firstPageNoOnPageList + 10 < custmrValueObject.totalPageCount}">
						<li>
							<a href="javascript:custmrPaging(${custmrValueObject.lastPageNoOnPageList+1})" aria-label="Next">
							<span aria-hidden="true">&rsaquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${custmrValueObject.firstPageNoOnPageList + 20 < custmrValueObject.totalPageCount}">
						<li>
							<a href="javascript:custmrPaging(${custmrValueObject.totalPageCount})" aria-label="Next">
							<span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>  
				</ul>
			</nav>
		</div> 
		<!-- 검색 -->
		<div style="text-align:center; margin-bottom:13px;">
			<form name="custmrSechFrm" id="custmrSechFrm" method="post">  
				<div>
					<select name="custmrSechOption" class="form-control" style="width:20%; display:inline-block"">
						<option value="ID">ID</option>
						<option value="이름">이름</option>
						<option value="연락처">연락처</option>
					</select>
					<input type="text" name="custmrSechText" class="form-control" style="width:40%; display:inline-block" placeholder="검색어를 입력하세요.">
					<input class="btn btn-default" style="width:20%; display:inline-block"  type="button" value="검색" onclick="javascript:custmrSech()">
				</div>	
			</form>
		</div>
		<!-- 선택 -->
	</body>
</html>