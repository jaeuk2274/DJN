<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 
* 푸시알림 템플릿을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        이정호, 1.0, 2016/08/11 – 초기 작성
* author : 이정호
* version : 1.1, 2016/08/15  - 페이징 추가
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
		
	<!-- JQuery 관련 파일들 -->
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>   
	
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
		
	<script src="/resources/js/push-js/pushNotfactTemplt.js"></script>
</head>
<body>
	<!-- 상단 메뉴바 import -->
	<div style="margin-bottom: 130px;">
		<c:import url="/TopFrame.do"/>
	</div>	
	
	<div class="container">
		<form name="pushListInfoFrm" id="pushListInfoFrm" method="post">
			<input type="hidden" name="searchCnd" value="${pushVo.searchCnd}"> <!-- 검색조건(숫자값) -->
			<input type="hidden" name="searchWrd" value="${pushVo.searchWrd}"> <!-- 검색단어(문자값) -->
			<input type="hidden" name="searchAry" value="${pushVo.searchAry}"> <!-- 정렬조건(숫자값) -->
			<input type="hidden" name="currentPageNo" value="${pushVo.currentPageNo}"> <!-- 현재 페이지 -->
		</form>
		
		<div class="container">
			<h3>푸시 알림 템플릿</h3>
			<div style="display:inline-block; margin-top:1%; float:right">
			   <h5>
			     <img src="../../../../../resources/image/common/home.png"> > 푸시 알림 관리 > 
			     <a href="/pushNotfcatnTempltSelectList.do" style="text-decoration:none"><strong>푸시 알림 템플릿</strong></a>
			   </h5>
			</div>
		</div>
	
		<form name="pushTempltSearchBox" class="form-inline">
			<div class="container form-group">
				<table class="table table-bordered">
					<tr>
						<td>															
							<select class="form-control" id="searchCnd" >
							    <option value="0" selected>전체</option>
							    <option value="1">제목</option>
							    <option value="2">등록자</option>
							</select>
							<input type="text" class="form-control" id="searchWrd" style="width:86%">
							<input type="button" class="btn btn-default" value="검색" onclick="pushTempltSearch(1)">
						</td>
					</tr>
				</table>
			</div>
		</form>
		
		<div class="form-group"></div>
		
		<div class="container" id="mainDiv">
			<form name="pushTemplt" method="POST">
				<table class="table table-bordered">
					<tr class="active">
						<th class="text-center"><input type="checkbox" id="allCheck" onclick="allChecking()"></th>
						<th class="text-center">등록일</th>
						<th class="text-center">템플릿 제목</th>
						<th class="text-center">등록자</th>
					</tr>
					<c:forEach var="pushNotfcatnList" items="${pushNotfcatnList}" varStatus="status">
						<tr onmouseover="this.style.backgroundColor='#F4FFFD'" onmouseout="this.style.backgroundColor='#ffffff'">
							<td class="text-center"><input type="checkbox" value="${pushNotfcatnList.pushTempltSeq}" name="templtCheck"></td>
							<td class="text-center">${pushNotfcatnList.regstrtnDat}</td>
							<td class="text-center" onclick="javascript:pushNotfcatnTempltSelectDetlPopup(${pushNotfcatnList.pushTempltSeq})">${pushNotfcatnList.pushTitl}</td>
							<td class="text-center">${pushNotfcatnList.regstr}</td>
						</tr>
					</c:forEach>
				</table>
			</form>
			
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
			<div class="text-right">
				<input type="button" class="btn btn-default" id="templtInsertDsplyButton" onclick="javascript:pushTempltInsertPopup()" value="템플릿 등록">
				<input type="button" class="btn btn-default" onclick="pushTempltDelete()" value="삭제">
			</div>
		</div>		
	</div>	
   </body>
</html>