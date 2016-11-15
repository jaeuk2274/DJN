<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- JQuery 관련 파일들 -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="/resources/js/push-js/pushNotfcatnRecpnt.js"></script>
<script src="/resources/js/catgrChoice.js"></script>
</head>
<body>
<div id="pushRecpntGropPurchs" class="tab_content">
	<form name="pushRecpntGropPurchsPagingFrm" method="post">      
		<input type="hidden" name="currentPageNo" value="${pushVo.currentPageNo}"> <!-- 현재 페이지 -->
		<input type="hidden" name="searchWrd" value="${pushVo.searchWrd}">
		<input type="hidden" name="searchDateStart" value="${pushVo.searchDateStart}">
		<input type="hidden" name="searchDateEnd" value="${pushVo.searchDateEnd}">
		<input type="hidden" name="stat" value="${pushVo.stat}">
	</form>
	<form name="pushDespRecrdFrm" method="POST">
		<table class="table" style="width: 50%;">
			<tr>
	   			<td onclick="javascript:pushRecpnt()">전체</td>
	   			<td onclick="javascript:pushRecpntProdctPopup()">상품</td>
   				<td onclick="javascript:pushRecpntCatgrPopup()">카테고리</td>
	   			<td onclick="javascrirpt:pushRecpntPackgPopup()">패키지</td>
	   			<td class="active" onclick="pushRecpntGropPurchsPopup()">공동구매</td>
	   		</tr>
		</table>
	</form>
	<form name="pushRecpntGropPurchsFrm" id="pushRecpntGropPurchsFrm" method="POST">
      	<div style="width: 80%;" align="center">
	       	<table class="table">
	       		<tr>
	       			<td>
	       				기간
	       			</td>
	       			<td>
	       				<input type="date" name="searchDateStart" id="searchDateStart" class="form-control">
	       			</td>
	       			<td>
	       				~
	       			</td>
	       			<td>
	       				<input type="date" name="searchDateEnd" id="searchDateEnd" class="form-control">
	       			</td>
	       		</tr>
	       		<tr>
	       			<td>
	       				제목
	       			</td>
	       			<td>
	       				<input type="search" class="form-control" id="searchWrd" name="searchWrd">
	       			</td>
	       		</tr>
	       		<tr>
	       			<td>
	       				공동구매 상태
	       			</td>
	       			<td>
	       				<select name="stat" id="stat">
	       					<option value="성공">성공</option>
	       					<option value="실패">실패</option>
	       				</select>
	       			</td>
	       		</tr>
	       	</table>
		  	<div>
				<input type="button" value="검색" class="btn btn-primary" onclick="javascript:pushGropPurchsSearch()">
		  	</div>
		</div>
		</form>
		<form name="pushRecpntGropPurchs" method="POST">
		<input type="hidden" name="gropPurchsSeq" id="gropPurchsSeq">
	    <table class="table">
	     	<tr>
	      		<th>공동구매 제목</th>
	      		<th colspan="3">공둥구매 시간</th>
	      		<th>참여자 수</th>
	      		<th>공동구매 상태</th>
	      	</tr>
	      	<c:forEach var="pushRecpntGropPurchs" items="${pushRecpntGropPurchs}" varStatus="status">
	      	<tr onclick="javascript:pushRecpntGropPurchsAplctnCustmr(${pushRecpntGropPurchs.gropPurchsSeq})">
	      		<td>${pushRecpntGropPurchs.titl}</td>
	      		<td>${pushRecpntGropPurchs.ordrStarDat}</td>
	      		<td>~</td>
	      		<td>${pushRecpntGropPurchs.ordrEndDat}</td>
	      		<td>${pushRecpntGropPurchs.ordrNumbrSeq}</td>
	      		<td>${pushRecpntGropPurchs.stat}</td>
	      	</tr>
	      	</c:forEach>
	     </table>
	     <!-- 페이징 -->
		<div class="container text-center">
			<nav>
				<ul class="pagination">
					<c:if test = "${pushVo.currentPageNo>20}">
						<li>
							<a href="javascript:pushPaging(1)" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${pushVo.currentPageNo>10}">
						<li>
							<a href="javascript:pushPaging(${pushVo.firstPageNoOnPageList-1})" aria-label="Previous">
							<span aria-hidden="true">&lsaquo;</span></a>
						</li>
					</c:if> 	
								  	
					<c:forEach varStatus="status" begin="${pushVo.firstPageNoOnPageList}" end="${pushVo.lastPageNoOnPageList}">
					
						<c:if test="${pushVo.currentPageNo==status.current}">
							<li class="active">
								<a href="javascript:pushPaging(${status.current})">${status.current}</a>
							</li>
						</c:if>
						<c:if test="${pushVo.currentPageNo!=status.current}">
							<li><a href="javascript:pushPaging(${status.current})">${status.current}</a></li>
						</c:if>                   									
					</c:forEach>	
					<c:if test = "${pushVo.firstPageNoOnPageList + 10 < pushVo.totalPageCount}">
						<li>
							<a href="javascript:pushPaging(${pushVo.lastPageNoOnPageList+1})" aria-label="Next">
							<span aria-hidden="true">&rsaquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${pushVo.firstPageNoOnPageList + 20 < pushVo.totalPageCount}">
						<li>
							<a href="javascript:pushPaging(${pushVo.totalPageCount})" aria-label="Next">
							<span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>  
				</ul>
			</nav>
		</div>
	</form>
	<form name="pushRecpntGropPurchsCustmrPagingFrm" method="POST">
	<input type="hidden" name="currentPageNo" value="${pushVoRecpntProdctCustmr.currentPageNo}"> <!-- 현재 페이지 -->
		<div>
			<table class="table">
				<tr>
					<th><input type="checkbox" id="allCheck"></th>
					<th>아이디</th>
					<th>이름</th>
					<th>신청수량</th>
					<th>총구매금액</th>
				</tr>
				<c:forEach var="pushRecpntGropPurchsAplctnCustmr" items="${pushRecpntGropPurchsAplctnCustmr}" varStatus="status">
					<tr>
						<td><input type="checkbox" value="${pushRecpntGropPurchsAplctnCustmr.custmrSeq}"></td>
						<td>${pushRecpntGropPurchsAplctnCustmr.custmrId}</td>
						<td>${pushRecpntGropPurchsAplctnCustmr.custmrNme}</td>
						<td>${pushRecpntGropPurchsAplctnCustmr.ordrAmont}</td>
						<td>${pushRecpntGropPurchsAplctnCustmr.selPric}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<!-- 페이징 -->
		<div class="container text-center">
			<nav>
				<ul class="pagination">
					<c:if test = "${pushRecpntGropPurchsAplctnCustmrVo.currentPageNo>20}">
						<li>
							<a href="javascript:pushCustmrPaging(1)" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${pushRecpntGropPurchsAplctnCustmrVo.currentPageNo>10}">
						<li>
							<a href="javascript:pushCustmrPaging(${pushRecpntGropPurchsAplctnCustmrVo.firstPageNoOnPageList-1})" aria-label="Previous">
							<span aria-hidden="true">&lsaquo;</span></a>
						</li>
					</c:if> 	
								  	
					<c:forEach varStatus="status" begin="${pushRecpntGropPurchsAplctnCustmrVo.firstPageNoOnPageList}" end="${pushRecpntGropPurchsAplctnCustmrVo.lastPageNoOnPageList}">
					
						<c:if test="${pushRecpntGropPurchsAplctnCustmrVo.currentPageNo==status.current}">
							<li class="active">
								<a href="javascript:pushCustmrPaging(${status.current})">${status.current}</a>
							</li>
						</c:if>
						<c:if test="${pushRecpntGropPurchsAplctnCustmrVo.currentPageNo!=status.current}">
							<li><a href="javascript:pushCustmrPaging(${status.current})">${status.current}</a></li>
						</c:if>                   									
					</c:forEach>	
					<c:if test = "${pushRecpntGropPurchsAplctnCustmrVo.firstPageNoOnPageList + 10 < pushRecpntGropPurchsAplctnCustmrVo.totalPageCount}">
						<li>
							<a href="javascript:pushCustmrPaging(${pushVo.lastPageNoOnPageList+1})" aria-label="Next">
							<span aria-hidden="true">&rsaquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${pushRecpntGropPurchsAplctnCustmrVo.firstPageNoOnPageList + 20 < pushVo.totalPageCount}">
						<li>
							<a href="javascript:pushCustmrPaging(${pushRecpntGropPurchsAplctnCustmrVo.totalPageCount})" aria-label="Next">
							<span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>  
				</ul>
			</nav>
		</div>
 	</form>
</div>
<script>
function pushGropPurchsSearch() {
	alert("검색조건 : " + $("#searchCnd").val());
	alert("검색내용 : " + $("#searchWrd").val());
	document.pushRecpntGropPurchsFrm.action ="/pushRecpntGropPurchsPopup.do";
	document.pushRecpntGropPurchsFrm.submit();
}
// 페이징
function pushPaging(page) {
	document.pushRecpntGropPurchsPagingFrm.currentPageNo.value = page;
	document.pushRecpntGropPurchsPagingFrm.action ="/pushRecpntGropPurchsPopup.do";
	document.pushRecpntGropPurchsPagingFrm.submit();
}
function pushCustmrPaging(page) {
	document.pushRecpntGropPurchsCustmrPagingFrm.currentPageNo.value = page;
	document.pushRecpntGropPurchsCustmrPagingFrm.action ="/pushRecpntGropPurchsAplctnCustmr.do";
	document.pushRecpntGropPurchsCustmrPagingFrm.submit();
}
$(function(){
//전체선택 체크박스 클릭
	$("#allCheck").click(function(){
		alert("전체체크확인용");
		//만약 전체 선택 체크박스가 체크된상태일경우
		if($("#allCheck").prop("checked")) {
			//해당화면에 전체 checkbox들을 체크해준다
			$("input[type=checkbox]").prop("checked",true);
			// 전체선택 체크박스가 해제된 경우
		} 
		else {
			//해당화면에 모든 checkbox들의 체크를해제시킨다.
			$("input[type=checkbox]").prop("checked",false);
		}
	})
});
</script>
</body>
</html>