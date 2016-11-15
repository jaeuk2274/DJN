<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<form name="pushListInfoFrm" id="pushListInfoFrm" method="post">
		<input type="hidden" name="searchCnd" value="${pushVo.searchCnd}"> <!-- 검색조건(숫자값) -->
		<input type="hidden" name="searchWrd" value="${pushVo.searchWrd}"> <!-- 검색단어(문자값) -->
		<input type="hidden" name="searchAry" value="${pushVo.searchAry}"> <!-- 정렬조건(숫자값) -->
		<input type="hidden" name="currentPageNo" value="${pushVo.currentPageNo}"> <!-- 현재 페이지 -->
	</form>
	<div class="form-group"></div>
		<table class="table table-bordered">
			<tr>
				<td>															
					<select class="form-control" id="searchCnd">
					    <option value="0" selected>전체</option>
					    <option value="1">제목</option>
					    <option value="2">등록자</option>
					</select>
					<input type="text" class="form-control" id="searchWrd"  style="width:78%">
					<input type="button" class="btn btn-default" value="검색" onclick="pushTempltSearch(1)">
				</td>
			</tr>
		</table>
	<div class="form-group"></div>
	
	<div class="pull-left" id="recpntTable" style="display:inline-block; width:720px;">
		<form name="pushTemplt" method="POST">
			<table class="table table-bordered text-center" id="tableId">
				<colgroup>
					<col class="col-md-1 col-sm-1"/>               
					<col class="col-md-3 col-sm-3"/>          
					<col class="col-md-5 col-sm-5"/>           
					<col class="col-md-3 col-sm-3"/>
				</colgroup>
				<tr class="active nodrag nodrop" style="cursor:pointer;">
					<th class="text-center"><input type="checkbox" id="allCheck" onclick="allChecking()"></th>
					<th class="text-center">등록일</th>
					<th class="text-center">템플릿 제목</th>
					<th class="text-center">등록자</th>
				</tr>
				<c:forEach var="pushNotfcatnList" items="${pushNotfcatnList}" varStatus="status">
					<tr onMouseOver="this.style.backgroundColor='#A5B448'" onmouseout="this.style.backgroundColor='#ffffff'" style="cursor:hand">
						<td><input type="checkbox" name="templtCheck" value="${pushNotfcatnList.pushTempltSeq}"></td>
						<td>${pushNotfcatnList.regstrtnDat}</td>
						<td style="cursor:hand" onclick="insertPushTemplt('${pushNotfcatnList.pushTitl}','${pushNotfcatnList.pushMesg}','${pushNotfcatnList.pushHedln}','${pushNotfcatnList.regstrtnDat}')">${pushNotfcatnList.pushTitl}</td>
						<td>${pushNotfcatnList.regstr}</td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>
	<!-- 페이징 -->
	<div class="text-center" style=" width:420px; margin:0 auto;">
		<ul class="pagination">
			<c:if test = "${pushVo.currentPageNo>20}">
				<li>
					<a href="javascript:pushTempltPaging(1)" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span></a>
				</li>
			</c:if>  
			<c:if test = "${pushVo.currentPageNo>10}">
				<li>
					<a href="javascript:pushTempltPaging(${pushVo.firstPageNoOnPageList-1})" aria-label="Previous">
					<span aria-hidden="true">&lsaquo;</span></a>
				</li>
			</c:if> 	
						  	
			<c:forEach varStatus="status" begin="${pushVo.firstPageNoOnPageList}" end="${pushVo.lastPageNoOnPageList}">
			
				<c:if test="${pushVo.currentPageNo==status.current}">
					<li class="active">
						<a href="javascript:pushTempltPaging(${status.current})">${status.current}</a>
					</li>
				</c:if>
				<c:if test="${pushVo.currentPageNo!=status.current}">
					<li><a href="javascript:pushTempltPaging(${status.current})">${status.current}</a></li>
				</c:if>                   									
			</c:forEach>	
			<c:if test = "${pushVo.firstPageNoOnPageList + 10 < pushVo.totalPageCount}">
				<li>
					<a href="javascript:pushTempltPaging(${pushVo.lastPageNoOnPageList+1})" aria-label="Next">
					<span aria-hidden="true">&rsaquo;</span></a>
				</li>
			</c:if>  
			<c:if test = "${pushVo.firstPageNoOnPageList + 20 < pushVo.totalPageCount}">
				<li>
					<a href="javascript:pushTempltPaging(${pushVo.totalPageCount})" aria-label="Next">
					<span aria-hidden="true">&raquo;</span></a>
				</li>
			</c:if>  
		</ul>
	</div> 
	<div class="text-center">
		<input type="button" value="닫기" class="btn btn-default" onclick="closeIt()">
	</div>
</body>
</html>