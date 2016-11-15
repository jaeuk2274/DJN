<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<form name="pushListInfoFrm2" id="pushListInfoFrm2" method="post">
		<input type="hidden" name="searchCnd" value="${pushVo2.searchCnd}"> <!-- 검색조건(숫자값) -->
		<input type="hidden" name="searchWrd" value="${pushVo2.searchWrd}"> <!-- 검색단어(문자값) -->
		<input type="hidden" name="searchAry" value="${pushVo2.searchAry}"> <!-- 정렬조건(숫자값) -->
		<input type="hidden" name="currentPageNo" value="${pushVo2.currentPageNo}"> <!-- 현재 페이지 -->
	</form>
	<div class="form-group"></div>
		<table class="table table-bordered">
			<tr>
				<td>															
					<select class="form-control" id="searchCnd2">
					    <option value="0" selected>전체</option>
					    <option value="1">제목</option>
					    <option value="2">등록자</option>
					</select>
					<input type="text" class="form-control" id="searchWrd2"  style="width:78%">
					<input type="button" class="btn btn-default" value="검색" onclick="pushDespSearch(1)">
				</td>
			</tr>
		</table>
	<div class="form-group"></div>
	
	<div class="pull-left" id="recpntTable2" style="display:inline-block; width:720px;">
		<form name="pushDesp" method="POST">
			<table class="table table-bordered text-center" id="tableId2">
				<colgroup>
					<col class="col-md-1 col-sm-1"/>               
					<col class="col-md-3 col-sm-3"/>          
					<col class="col-md-5 col-sm-5"/>           
					<col class="col-md-3 col-sm-3"/>
				</colgroup>
				<tr class="active nodrag nodrop" style="cursor:pointer;">
					<th class="text-center"><input type="checkbox" id="allCheck2" onclick="allChecking2()"></th>
					<th class="text-center">등록일</th>
					<th class="text-center">발송내역 제목</th>
					<th class="text-center">등록자</th>
				</tr>
				<c:forEach var="pushNotfcatnList2" items="${pushNotfcatnList2}" varStatus="status">
					<tr onMouseOver="this.style.backgroundColor='#A5B448'" onmouseout="this.style.backgroundColor='#ffffff'" style="cursor:hand">
						<td><input type="checkbox" name="despCheck" value="${pushNotfcatnList2.despRecrdSeq}"></td>
						<td>${pushNotfcatnList2.pushDat}</td>
						<td style="cursor:hand" onclick="insertPushTemplt('${pushNotfcatnList2.pushTitl}','${pushNotfcatnList2.pushMesg}','${pushNotfcatnList2.pushHedln}','${pushNotfcatnList2.pushDat}')">${pushNotfcatnList2.pushTitl}</td>
						<td>${pushNotfcatnList2.regstr}</td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>
	<!-- 페이징 -->
	<div class="text-center" style=" width:420px; margin:0 auto;">
		<ul class="pagination">
			<c:if test = "${pushVo2.currentPageNo>20}">
				<li>
					<a href="javascript:pushDespPaging(1)" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span></a>
				</li>
			</c:if>  
			<c:if test = "${pushVo2.currentPageNo>10}">
				<li>
					<a href="javascript:pushDespPaging(${pushVo2.firstPageNoOnPageList-1})" aria-label="Previous">
					<span aria-hidden="true">&lsaquo;</span></a>
				</li>
			</c:if> 	
						  	
			<c:forEach varStatus="status" begin="${pushVo2.firstPageNoOnPageList}" end="${pushVo2.lastPageNoOnPageList}">
			
				<c:if test="${pushVo2.currentPageNo==status.current}">
					<li class="active">
						<a href="javascript:pushDespPaging(${status.current})">${status.current}</a>
					</li>
				</c:if>
				<c:if test="${pushVo2.currentPageNo!=status.current}">
					<li><a href="javascript:pushDespPaging(${status.current})">${status.current}</a></li>
				</c:if>                   									
			</c:forEach>	
			<c:if test = "${pushVo2.firstPageNoOnPageList + 10 < pushVo2.totalPageCount}">
				<li>
					<a href="javascript:pushDespPaging(${pushVo2.lastPageNoOnPageList+1})" aria-label="Next">
					<span aria-hidden="true">&rsaquo;</span></a>
				</li>
			</c:if>  
			<c:if test = "${pushVo2.firstPageNoOnPageList + 20 < pushVo2.totalPageCount}">
				<li>
					<a href="javascript:pushDespPaging(${pushVo2.totalPageCount})" aria-label="Next">
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