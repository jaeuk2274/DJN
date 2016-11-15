<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 
* 직원 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        이인호, 1.0, 2016/08/09 – 초기 작성
* author : 이인호
* version : 1.1, 2016/08/10  - 페이징 추가
* see : 관련된 모듈을 기술한다.
//-->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>대신 장봐주는 남자 - 대.장.남</title>
		
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
	</head>
	<body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
		<div class="container">
			<form name="empListInfo" method="post">
				<input type="hidden" name="empSeq" value="0"> <!-- 직원고유값(숫자값) -->
				<input type="hidden" name="currentPageNo" id="currentPageNo" value="${empValueObject.currentPageNo}"> <!-- 페이지(숫자값) -->
				<input type="hidden" value="${empValueObject.totalPageCount}">
				<input type="hidden" name="searchCnd" value="${empValueObject.searchCnd}"> <!-- 검색조건(숫자값) -->
				<input type="hidden" name="searchWrd" value="${empValueObject.searchWrd}"> <!-- 검색단어(문자값) -->
				<input type="hidden" name="searchAry" value="${empValueObject.searchAry}"> <!-- 명함정렬조건(숫자값) -->
			</form>
			
			<div class="container">
				<h3>직원 조회</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
					<h5>
						<img src="/resources/image/common/home.png"> > 직원 관리 > 
						<a href="/empList.do" style="text-decoration:none"><strong>직원 목록</strong></a>
					</h5>
				</div>
			</div>
			
			<div class="container form-inline">
				<table class="table table-bordered">
					<tbody>
						<tr>
							<th class="active"><h5 class="text-center"><b>직급 조회</b></h5></th>
							<td>
								<select id="searchCnd" class="form-control">
									<c:choose>
										<c:when test="${empValueObject.searchCnd == 0}">
											<option value="0" selected="selected">이름</option>
											<option value="1">직급</option>
										</c:when>
										<c:when test="${empValueObject.searchCnd == 1}">
											<option value="0">이름</option>
											<option value="1" selected="selected">직급</option>
										</c:when>
									</c:choose>
								</select>
								<input type="search" class="form-control"  id="searchWrd">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="container text-center">
				<input type="button" class="btn btn-default" value="검색" id="btn_search">
			</div>
			
			<div class="form-group"></div>
			
			<div class="container form-inline">
				<select id="searchAry" class="form-control" onchange="javascript:empListPagingForm(1)">
					<c:choose>
						<c:when test="${empValueObject.searchAry == 0}">
							<option value="0" selected="selected">이름순</option>
							<option value="1">직급순</option>
						</c:when>
						<c:when test="${empValueObject.searchAry == 1}">
							<option value="0">이름순</option>
							<option value="1" selected="selected">직급순</option>
						</c:when>
					</c:choose>
				</select>
			</div>
			<div class="container">
				<table class="table table-bordered text-center">
					<colgroup>
						<col class="col-md-1 col-sm-1"/>                
					    <col class="col-md-3 col-sm-3"/>          
					    <col class="col-md-3 col-sm-3"/>           
					    <col class="col-md-1 col-sm-1"/>
					    <col class="col-md-1 col-sm-1"/>          
					    <col class="col-md-3 col-sm-3"/>
					</colgroup>
					<thead>
						<tr class="active">
							<th class="text-center">번호</th>
							<th class="text-center">이름</th>
							<th class="text-center">전화</th>
							<th class="text-center">직급</th>
							<th class="text-center">업무</th>
							<th class="text-center">생일</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${fn:length(selectEmpList) == 0}">
								<tr>
									<td colspan="6">
										<h4>등록된 직원이 없습니다</h4>
									</td>
								</tr>
							</c:when>
							<c:when test="${fn:length(selectEmpList) != 0}">
								<c:forEach var="selectEmpList" items="${selectEmpList}" varStatus="status">
										<input type="hidden" name="empSeq" id="empSeq" value="${selectEmpList.empSeq}">
										<tr onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
											<td onclick="javascript:selectEmpReadForm(${selectEmpList.empSeq})">
												<c:out value="${status.count}"/>
											</td>
											<td onclick="javascript:selectEmpReadForm(${selectEmpList.empSeq})">
												<c:out value="${selectEmpList.nme}"/>
											</td>
											<td onclick="javascript:selectEmpReadForm(${selectEmpList.empSeq})">
												<c:out value="${selectEmpList.mobl}"/>
											</td>
											<td onclick="javascript:selectEmpReadForm(${selectEmpList.empSeq})">
												<c:out value="${selectEmpList.duty}"/>
											</td>
											<td onclick="javascript:selectEmpReadForm(${selectEmpList.empSeq})">
												<c:out value="${selectEmpList.task}"/>
											</td>
											<td onclick="javascript:selectEmpReadForm(${selectEmpList.empSeq})">
												<c:out value="${selectEmpList.birth}"/>
											</td>
										</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</tbody>
				</table>
			</div>
			<div class="container text-right">
				<input class="btn btn-default" type="button" value="등록" ID="btn_creat">
			</div>
		
			<div class="container text-center">
				<ul class="pagination">
					<!-- 무조건 1페이지로 << 버튼 -->
					<c:if test = "${empValueObject.currentPageNo>20}">
						<li>
							<a href="javascript:empListPagingForm(1)" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>  
					<!-- 한 단위 앞으로 < 버튼 -->
					<c:if test = "${empValueObject.currentPageNo>10}">
						<li>
							<a href="javascript:empListPagingForm(${empValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
							<span aria-hidden="true">&lsaquo;</span></a>
						</li>
					</c:if> 	
					<c:forEach varStatus="status" begin="${empValueObject.firstPageNoOnPageList}" end="${empValueObject.lastPageNoOnPageList}">
						<c:if test="${empValueObject.currentPageNo==status.current}">
							<li class="active">
								<a href="javascript:empListPagingForm(${status.current})">${status.current}</a>
							</li>
						</c:if>
						<c:if test="${empValueObject.currentPageNo!=status.current}">
							<li><a href="javascript:empListPagingForm(${status.current})">${status.current}</a></li>
						</c:if>                   									
					</c:forEach>	
					<!-- 한 단위 뒤로 > 버튼 -->
					<c:if test = "${empValueObject.firstPageNoOnPageList + 10 < empValueObject.totalPageCount}">
						<li>
							<a href="javascript:empListPagingForm(${empValueObject.lastPageNoOnPageList+1})" aria-label="Next">
							<span aria-hidden="true">&rsaquo;</span></a>
						</li>
					</c:if>  
					<!-- 무조건 마지막페이지로 >> 버튼 -->
					<c:if test = "${empValueObject.firstPageNoOnPageList + 20 < empValueObject.totalPageCount}">
						<li>
							<a href="javascript:empListPagingForm(${empValueObject.totalPageCount})" aria-label="Next">
							<span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>  
				</ul>
			</div>
		</div>
		<script>
			//검색
			function empListForm(pageNo) {
				var searchAry = document.getElementById("searchAry").value;
				var searchWrd = document.getElementById("searchWrd").value;
				var searchCnd = document.getElementById("searchCnd").value;
				if (document.getElementById("searchWrd").value==""){
					alert("검색어를 입력하세요");
				}
				else{
					document.empListInfo.currentPageNo.value = pageNo;
		 			document.empListInfo.searchAry.value = searchAry;
					document.empListInfo.searchWrd.value = searchWrd;
					document.empListInfo.searchCnd.value = searchCnd;
					document.empListInfo.action = "/empList.do";
					document.empListInfo.submit();
				}
			};
			
			//페이징
			function empListPagingForm(pageNo){
				var searchAry = document.getElementById("searchAry").value;
				var searchWrd = document.getElementById("searchWrd").value;
				var searchCnd = document.getElementById("searchCnd").value;
				document.empListInfo.currentPageNo.value = pageNo;
	 			document.empListInfo.searchAry.value = searchAry;
				document.empListInfo.searchWrd.value = searchWrd;
				document.empListInfo.searchCnd.value = searchCnd;
				document.empListInfo.action = "/empList.do";
				document.empListInfo.submit();
			};
			
			//직원 정보 상세보기
			function selectEmpReadForm(empSeq) {
				document.empListInfo.empSeq.value = empSeq;
	        	document.empListInfo.action = "/empRead.do";
				document.empListInfo.submit();
			};
			
			//리스트 색 변경
			function changeTrColor(trObj, oldColor, newColor) {
				trObj.style.backgroundColor = newColor;
				trObj.onmouseout = function(){
					trObj.style.backgroundColor = oldColor;
				}
			};
			
			$(document).ready(function(){
			    $('#btn_creat').click(function() {
			    	var url = "empCreateRead.do";    
			    	$(location).attr('href',url);
			    });
			    
			    $('#btn_search').click(function() {
	                var url = "/empList.do";    
	                var searchAry = $("#searchAry").val();
	                var searchWrd = $("#searchWrd").val();
	                var searchCnd = $("#searchCnd").val();
	                $("[name='searchAry']").val(searchAry);
					$("[name='searchWrd']").val(searchWrd);
					$("[name='searchCnd']").val(searchCnd);
	                $("[name='empListInfo']").attr('action',url);
	                $("[name='empListInfo']").submit();
	             });
	         });
		</script>	
	</body>
</html>