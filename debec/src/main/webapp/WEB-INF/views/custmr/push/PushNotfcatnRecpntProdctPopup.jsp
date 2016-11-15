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
<div id="pushRecpntProdct" class="tab_content">
<form name="pushRecpntProdctPagingFrm" method="post">      
	<input type="hidden" name="currentPageNo" value="${pushVo.currentPageNo}"> <!-- 현재 페이지 -->
	<input type="hidden" name="searchWrd" value="${pushVo.searchWrd}">
	<input type="hidden" name="searchCnd" value="${pushVo.searchCnd}">
</form>
<form name="pushDespRecrdFrm" method="POST">
	<table class="table" style="width: 50%;">
		<tr>
   			<td onclick="javascript:pushRecpnt()">전체</td>
   			<td class="active" onclick="javascript:pushRecpntProdctPopup()">상품</td>
   			<td onclick="javascript:pushRecpntCatgrPopup()">카테고리</td>
   			<td onclick="javascrirpt:pushRecpntPackgPopup()">패키지</td>
   			<td onclick="pushRecpntGropPurchsPopup()">공동구매</td>
   		</tr>
	</table>
</form>
<div style="width: 90%; margin-left: 5%; margin-right: 5%;">
	<form name="pushProdctInfo" method="POST">
      	<div style="width: 100%;">
	       	<table class="table">
	       		<tr>
	       			<td>
	        			<select id="searchCnd" name="searchCnd">
	   						<c:choose>
								<c:when test="${pushValueObject.searchCnd == 0}">
									<option selected="selected" value="0">전체</option>
			        				<option value="1">거래처</option>
			        				<option value="2">상품이름</option>
								</c:when>
								<c:when test="${pushValueObject.searchCnd == 1}">
									<option value="0">전체</option>
			        				<option selected="selected" value="1">거래처</option>
			        				<option value="2">상품이름</option>
								</c:when>
								<c:when test="${pushValueObject.searchCnd == 2}">
									<option value="0">전체</option>
			        				<option value="1">거래처</option>
			        				<option selected="selected" value="2">상품이름</option>
								</c:when>
							</c:choose>
	   					</select>
	       			</td>
	       			<td>
	       				<input type="text" name="searchWrd" id="allSearch">
	       			</td>
	       			<td>
	       				<input type="button" value="검색" class="btn btn-default" onclick="javascript:pushRecpntProdctSearch()">
	       			</td>
	       		</tr>
	       	</table>
		</div>
	</form>
	<form name="pushRecpntProdctFrm" id="pushRecpntProdctFrm" method="POST">
		<input type="hidden" name="prodctSeq" id="prodctSeq">
      	<div style="display: inline-block; width: 35%;">
	       	<table class="table">
	       		<tr>
	        		<th>거래처</th>
	        		<th>상품이름</th>
	        	</tr>
	        	<c:forEach var="pushRecpntProdct" items="${pushRecpntProdct}" varStatus="status">
	        	<input type="hidden" value="${pushRecpntProdct.prodctSeq}">
	        	<input type="hidden" value="${pushRecpntProdct.clintSeq}">
	        	<tr onclick="javascript:prodctChoc(${pushRecpntProdct.prodctSeq})">
					<td>${pushRecpntProdct.clintNme}</td>
	        		<td>${pushRecpntProdct.prodctNme}</td>
	        	</tr>
	        	</c:forEach>
	       	</table>
      	 </div>
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
    <form name="pushRecpntProdctCustmrPagingFrm" method="POST">
    <input type="hidden" name="currentPageNo" value="${pushVoRecpntProdctCustmr.currentPageNo}"> <!-- 현재 페이지 -->
      	<div style="display:inline-block; width: 65%;" id="pushRecpntProdctCustmr">
			<table class="table">
				<tr>
					<td>
						<select>
							<option/>
							<option/>
							<option/>
							<option/>
						</select>
					</td>
					<td>
						<input type="button" value="전체선택">
					</td>
				</tr>
				<tr>
					<th><input type="checkbox"  id="allCheck"></th>
					<th>아이디</th>
					<th>이름</th>
					<th>주문 횟수</th>
					<th>주문 금액</th>
				</tr>
				<c:forEach var="pushRecpntProdctCustmr" items="${pushRecpntProdctCustmr}" varStatus="status">
					<tr>
						<td><input type="checkbox"></td>
						<td>${pushRecpntProdctCustmr.custmrId}</td>
						<td>${pushRecpntProdctCustmr.custmrNme}</td>
						<td>${pushRecpntProdctCustmr.ordrAmont}</td>
						<td>${pushRecpntProdctCustmr.selPric}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<!-- 페이징 -->
		<div class="container text-center">
			<nav>
				<ul class="pagination">
					<c:if test = "${pushVoRecpntProdctCustmr.currentPageNo>20}">
						<li>
							<a href="javascript:pushCustmrPaging(1)" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${pushVoRecpntProdctCustmr.currentPageNo>10}">
						<li>
							<a href="javascript:pushCustmrPaging(${pushVoRecpntProdctCustmr.firstPageNoOnPageList-1})" aria-label="Previous">
							<span aria-hidden="true">&lsaquo;</span></a>
						</li>
					</c:if> 	
								  	
					<c:forEach varStatus="status" begin="${pushVoRecpntProdctCustmr.firstPageNoOnPageList}" end="${pushVoRecpntProdctCustmr.lastPageNoOnPageList}">
					
						<c:if test="${pushVoRecpntProdctCustmr.currentPageNo==status.current}">
							<li class="active">
								<a href="javascript:pushCustmrPaging(${status.current})">${status.current}</a>
							</li>
						</c:if>
						<c:if test="${pushVoRecpntProdctCustmr.currentPageNo!=status.current}">
							<li><a href="javascript:pushCustmrPaging(${status.current})">${status.current}</a></li>
						</c:if>                   									
					</c:forEach>	
					<c:if test = "${pushVoRecpntProdctCustmr.firstPageNoOnPageList + 10 < pushVoRecpntProdctCustmr.totalPageCount}">
						<li>
							<a href="javascript:pushCustmrPaging(${pushVoRecpntProdctCustmr.lastPageNoOnPageList+1})" aria-label="Next">
							<span aria-hidden="true">&rsaquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${pushVoRecpntProdctCustmr.firstPageNoOnPageList + 20 < pushVoRecpntProdctCustmr.totalPageCount}">
						<li>
							<a href="javascript:pushCustmrPaging(${pushVoRecpntProdctCustmr.totalPageCount})" aria-label="Next">
							<span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>  
				</ul>
			</nav>
		</div>
	</form>
	<div>
		<input type="button" value="등록">
		<input type="button" value="취소" onclick="pushCancle()">
	</div>
</div>

</div>
<script>
function pushRecpntProdctSearch() {
	alert("검색조건 : " + $("#searchCnd").val());
	alert("검색내용 : " + $("#searchWrd").val());
	document.pushProdctInfo.action ="/pushNotfcatnRecpntProdctPopup.do";
	document.pushProdctInfo.submit();
}
// 페이징
function pushPaging(page) {
	document.pushRecpntProdctPagingFrm.currentPageNo.value = page;
	document.pushRecpntProdctPagingFrm.action ="/pushNotfcatnRecpntProdctPopup.do";
	document.pushRecpntProdctPagingFrm.submit();
}
function pushCustmrPaging(page) {
	document.pushRecpntProdctCustmrPagingFrm.currentPageNo.value = page;
	document.pushRecpntProdctCustmrPagingFrm.action ="/pushRecpntProdctCustmr.do";
	document.pushRecpntProdctCustmrPagingFrm.submit();
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