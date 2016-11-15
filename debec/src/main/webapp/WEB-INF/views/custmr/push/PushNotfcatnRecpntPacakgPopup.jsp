<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<div id="pushRecpntPackg" class="tab_content">
<form name="pushRecpntPackgPagingFrm" method="post">      
	<input type="hidden" name="currentPageNo" value="${pushVo.currentPageNo}"> <!-- 현재 페이지 -->
	<input type="hidden" name="searchWrd" value="${pushVo.searchWrd}">
	<input type="hidden" name="searchCnd" value="${pushVo.searchCnd}">
</form>
<form name="pushDespRecrdFrm" method="POST">
	<table class="table" style="width: 50%;">
		<tr>
   			<td onclick="javascript:pushRecpnt()">전체</td>
   			<td onclick="javascript:pushRecpntProdctPopup()">상품</td>
   			<td onclick="javascript:pushRecpntCatgrPopup()">카테고리</td>
   			<td class="active" onclick="javascrirpt:pushRecpntPackgPopup()">패키지</td>
   			<td onclick="pushRecpntGropPurchsPopup()">공동구매</td>
   		</tr>
	</table>
</form>
   	<form name="pushRecpntPackgFrm" id="pushRecpntPackgFrm" method="POST">
      	<table class="table">
       		<tr>
       			<td>
        			<select id="searchCnd" name="searchCnd">
     						<c:choose>
							<c:when test="${pushValueObject.searchCnd == 0}">
								<option selected="selected" value="0">전체</option>
		        				<option value="1">거래처 명</option>
		        				<option value="2">패키지 명</option>
							</c:when>
							<c:when test="${pushValueObject.searchCnd == 1}">
								<option value="0">전체</option>
		        				<option selected="selected" value="1">거래처 명</option>
		        				<option value="2">패키지 명</option>
							</c:when>
							<c:when test="${pushValueObject.searchCnd == 2}">
								<option value="0">전체</option>
		        				<option value="1">거래처 명</option>
		        				<option selected="selected" value="2">패키지 명</option>
							</c:when>
						</c:choose>
   					</select>
       			</td>
       			<td>
       				<input type="text" name="searchWrd" id="allSearch">
       			</td>
       			<td>
     				<input type="button" value="검색" class="btn btn-default" onclick="javascript:pushRecpntPackgSearch()">
       			</td>
      		</tr>
       	</table>
   	</form>
	<table class="table">
		<c:choose>
	        <c:when test="${fn:length(pushRecpntPackg) == 0}">
	           <tr class="text-center">
	              <td colspan="2">
	                 <h4>사용된 연가가 없습니다</h4>
	              </td>
	           </tr>
	        </c:when>
         	<c:when test="${fn:length(selectAnlVactnList) != 0}">
	       		<tr>
	        		<th>구분</th>
	        		<th>거래처 명</th>
	        		<th>패키지 명</th>
	        		<th>총 가격</th>
	        		<th>진열 여부</th>
        		</tr>
        		<c:forEach var="pushRecpntPackg" items="${pushRecpntPackg}" varStatus="status">
		        	<tr onclick="javascript:pushRecpntPackgAplctnCustmrChoc(${pushRecpntPackg.packgSeq})">
		        		<td>${pushRecpntPackg.packgType}</td>
		        		<td>${pushRecpntPackg.clintNme}</td>
		        		<td>${pushRecpntPackg.packgNme}</td>
		        		<td>${pushRecpntPackg.allPric}</td>
		        		<td>${pushRecpntPackg.packgDisplayCheck}</td>
        			</tr>
        		</c:forEach>
			</c:when>
		</c:choose>
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
		<form name="pushRecpntPackgCustmrPagingFrm" method="POST">
		<input type="hidden" id="packgSeq" name="packgSeq"> 
		<input type="hidden" name="currentPageNo" value="${pushRecpntPackgAplctnCustmr.currentPageNo}"> <!-- 현재 페이지 -->
			<div id="pushRecpntPackgAplctnCustmr">
			<table class="table">
				<tr>
					<th><input type="checkbox"></th>
					<th>아이디</th>
					<th>이름</th>
					<th>주문 횟수</th>
					<th>주문 금액</th>
				</tr>
				<c:forEach var="pushRecpntPackgAplctnCustmr" items="${pushRecpntPackgAplctnCustmr}" varStatus="status">
					<tr>
						<td><input type="checkbox" value=${pushRecpntPackgAplctnCustmr.custmrSeq}></td>
						<td>${pushRecpntPackgAplctnCustmr.custmrSeq.custmrId}</td>
						<td>${pushRecpntPackgAplctnCustmr.custmrNme}</td>
						<td>${pushRecpntPackgAplctnCustmr.ordrAmont}</td>
						<td>${pushRecpntPackgAplctnCustmr.selPric}</td>
					</tr>
				</c:forEach>
				</table>
			</div>
			<!-- 페이징 -->
			<div class="container text-center">
				<nav>
					<ul class="pagination">
						<c:if test = "${pushRecpntPackgAplctnPushVo.currentPageNo>20}">
							<li>
								<a href="javascript:pushCustmrPaging(1)" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span></a>
							</li>
						</c:if>  
						<c:if test = "${pushRecpntPackgAplctnPushVo.currentPageNo>10}">
							<li>
								<a href="javascript:pushCustmrPaging(${pushRecpntPackgAplctnPushVo.firstPageNoOnPageList-1})" aria-label="Previous">
								<span aria-hidden="true">&lsaquo;</span></a>
							</li>
						</c:if> 	
									  	
						<c:forEach varStatus="status" begin="${pushRecpntPackgAplctnPushVo.firstPageNoOnPageList}" end="${pushRecpntPackgAplctnPushVo.lastPageNoOnPageList}">
							<c:if test="${pushRecpntPackgAplctnPushVo.currentPageNo==status.current}">
								<li class="active">
									<a href="javascript:pushCustmrPaging(${status.current})">${status.current}</a>
								</li>
							</c:if>
							<c:if test="${pushRecpntPackgAplctnPushVo.currentPageNo!=status.current}">
								<li><a href="javascript:pushCustmrPaging(${status.current})">${status.current}</a></li>
							</c:if>                   									
						</c:forEach>	
						<c:if test = "${pushRecpntPackgAplctnPushVo.firstPageNoOnPageList + 10 < pushRecpntPackgAplctnPushVo.totalPageCount}">
							<li>
								<a href="javascript:pushCustmrPaging(${pushRecpntPackgAplctnPushVo.lastPageNoOnPageList+1})" aria-label="Next">
								<span aria-hidden="true">&rsaquo;</span></a>
							</li>
						</c:if>  
						<c:if test = "${pushRecpntPackgAplctnPushVo.firstPageNoOnPageList + 20 < pushRecpntPackgAplctnPushVo.totalPageCount}">
							<li>
								<a href="javascript:pushCustmrPaging(${pushRecpntPackgAplctnPushVo.totalPageCount})" aria-label="Next">
								<span aria-hidden="true">&raquo;</span></a>
							</li>
						</c:if>  
					</ul>
				</nav>
			</div>
		</form>
</div>
<script>
function pushRecpntPackgSearch() {
	alert("검색조건 : " + $("#searchCnd").val());
	alert("검색내용 : " + $("#searchWrd").val());
	document.pushRecpntPackgFrm.action ="/pushRecpntPackgPopup.do";
	document.pushRecpntPackgFrm.submit();
}
// 페이징
function pushPaging(page) {
	document.pushRecpntPackgPagingFrm.currentPageNo.value = page;
	document.pushRecpntPackgPagingFrm.action ="/pushRecpntPackgPopup.do";
	document.pushRecpntPackgPagingFrm.submit();
}
function pushCustmrPaging(page) {
	document.pushRecpntPackgCustmrPagingFrm.currentPageNo.value = page;
	document.pushRecpntPackgCustmrPagingFrm.action ="/pushRecpntPackgAplctnCustmr.do";
	document.pushRecpntPackgCustmrPagingFrm.submit();
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