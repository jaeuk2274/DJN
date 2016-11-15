<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!-- 
* 패키지 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :0.
*        김동욱, 1.0, 2016/08/13 – 초기 작성
* author : 김동욱
* version : 1.1, 2016/08/18 - 초기 작성
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
		
	<!-- Don't forget to include jQuery ;) -->
 	<script src="/resources/modal/jquery.modal.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="/resources/modal/jquery.modal.css" type="text/css" />
	
	<script src="/resources/js/display-js/jquery.tablednd.js"></script>
	
	<!-- JSTree 관련 파일들 -->
	<script src="/resources/js/jstree.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="/resources/js/themes/proton/style.min.css">
	<!-- packg 관련 JS -->
	<script src="/resources/js/display-js/clint.js" type="text/javascript" charset="utf-8"></script>
	<script src="/resources/js/display-js/packg.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div style="margin-bottom: 130px;">
		<c:import url="/TopFrame.do"/>
	</div>
		
	<div class="container">
	
		<form name="packgPagingFrm" method="post">      
			<input type="hidden" name="packgSechText" value="${PackgValueObject.packgSechText}"> <!-- 검색 상품명 -->
			<input type="hidden" name="packgSechType" value="${PackgValueObject.packgSechType}"> <!-- 검색 조건  -->
			<input type="hidden" name="packgDisplayCheck" value="${PackgValueObject.packgDisplayCheck}"> <!-- 진열 여부  -->
			<input type="hidden" name="currentPageNo" value="${PackgValueObject.currentPageNo}"> <!-- 현재 페이지 -->
		</form>
	
		<div class="container">
			<h3>패키지 목록</h3>
			<div style="display:inline-block; margin-top:1%; float:right">
			   <h5>
			     <img src="../../../../../resources/image/common/home.png"> > 패키지 관리 > 
			     <a href="/packgList.do" style="text-decoration:none"><strong>패키지 목록</strong></a>
			   </h5>
			</div>
		</div>		
		
		<div class="container">
			<form name="packgSechFrm" class="form-inline">
				<table class="table table-bordered">
					<tr>
						<td class="active">
							<h5 class="text-center"><b>구분</b></h5>
						</td>
						<td>
							<input type="radio" name="packgType" value="all" checked="checked" onclick="packgTypeCheck(this)">전체
							<input type="radio" name="packgType" value="clint" onclick="packgTypeCheck(this)">거래처
							<input type="radio" name="packgType" value="normal" onclick="packgTypeCheck(this)">일반
						</td>
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>진열 여부</b></h5>
						</td>
						<td>
							<input type="radio" name="packgDisplayCheck" value="all" checked="checked">전체
							<input type="radio" name="packgDisplayCheck" value="Y">진열
							<input type="radio" name="packgDisplayCheck" value="N">미진열
						</td>
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>검색어</b></h5>
						</td>
						<td>															
							<select class="form-control" name="packgSechType">
							    <option value="clint" selected>거래처 명</option>
							    <option value="packg">패키지 명</option>
							</select>
							<input type="text" class="form-control" name="packgSechText" id="packgSechText">
						</td>
					</tr>	
				</table>
			</form>
		</div>
		
		<div class="container text-center">
			<input type="button" class="btn btn-default" value="검색" onclick="javascript:packgSech()">
		</div>
		
		<div class="form-group"></div>
		
		<div class="container text-right">
			<input type="button" class="btn btn-warning" id="updatePackgDisplayCheck" value="진열 여부 수정">
			<input type="button" class="btn btn-warning" id="deletePackg" value="삭제">
		</div>
		
		<!-- 메인 상품 리스트 화면 -->
		<div class="container text-left">
			[총 <c:out value="${PackgCount}"></c:out> 개]
		</div>
		
		<div class="container" id="mainDiv">
			<table class="table table-bordered text-center" id="tableId">
				<colgroup>
					<col class="col-md-1 col-sm-1"/>                
				    <col class="col-md-2 col-sm-2"/>          
				    <col class="col-md-2 col-sm-2"/>           
				    <col class="col-md-3 col-sm-3"/>
				    <col class="col-md-2 col-sm-2"/>          
				    <col class="col-md-2 col-sm-2"/>
			   </colgroup>
				<tr class="active nodrag nodrop">
					<th class="text-center"><input type="checkbox" id="allCheck"></th>
					<th class="text-center">구분</th>
					<th class="text-center">거래처 명</th>
					<th class="text-center">패키지 명</th>
					<th class="text-center">총 가격</th>
					<th class="text-center">진열 여부</th>
				</tr>
				<c:forEach items="${PackgList}" var="packgVo" varStatus="status">	
					<tr id="${packgVo.packgSeq}" onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
						<td><input type="checkbox" name="packgCheck" value="${packgVo.packgSeq}"></td>
						<td onclick="packgRead(${packgVo.packgSeq}, '${packgVo.packgType}')">${packgVo.packgType}</td>
						<c:choose>
							<c:when test="${packgVo.clintNme == null}">
								<td onclick="packgRead(${packgVo.packgSeq}, '${packgVo.packgType}')">없음</td>
							</c:when>
							<c:otherwise>
								<td onclick="packgRead(${packgVo.packgSeq}, '${packgVo.packgType}')">${packgVo.clintNme}</td>
							</c:otherwise>
						</c:choose>
						<td onclick="packgRead(${packgVo.packgSeq}, '${packgVo.packgType}')">${packgVo.packgNme}
						<td onclick="packgRead(${packgVo.packgSeq}, '${packgVo.packgType}')">${packgVo.allPric}</td>
						<c:choose>
							<c:when test="${packgVo.packgDisplayCheck == 'Y'}">
								<td onclick="packgRead(${packgVo.packgSeq}, '${packgVo.packgType}')">진열</td>
							</c:when>
							<c:otherwise>
								<td onclick="packgRead(${packgVo.packgSeq}, '${packgVo.packgType}')">미진열</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
			<div class="text-right">
				<button type="button" class="btn btn-default" id="insertPackg" onclick="location.href='/packgCreateRead.do'">등록</button>
			</div>
		</div>
		
		
		<!-- 페이징 -->
		<div class="container text-center">
			<nav>
				<ul class="pagination">
					<c:if test = "${PackgValueObject.currentPageNo>20}">
						<li>
							<a href="javascript:packgPaging(1)" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${PackgValueObject.currentPageNo>10}">
						<li>
							<a href="javascript:packgPaging(${PackgValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
							<span aria-hidden="true">&lsaquo;</span></a>
						</li>
					</c:if> 	
								  	
					<c:forEach varStatus="status" begin="${PackgValueObject.firstPageNoOnPageList}" end="${PackgValueObject.lastPageNoOnPageList}">
					
						<c:if test="${PackgValueObject.currentPageNo==status.current}">
							<li class="active">
								<a href="javascript:packgPaging(${status.current})">${status.current}</a>
							</li>
						</c:if>
						<c:if test="${PackgValueObject.currentPageNo!=status.current}">
							<li><a href="javascript:packgPaging(${status.current})">${status.current}</a></li>
						</c:if>                   									
					</c:forEach>	
					<c:if test = "${PackgValueObject.firstPageNoOnPageList + 10 < PackgValueObject.totalPageCount}">
						<li>
							<a href="javascript:packgPaging(${PackgValueObject.lastPageNoOnPageList+1})" aria-label="Next">
							<span aria-hidden="true">&rsaquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${PackgValueObject.firstPageNoOnPageList + 20 < PackgValueObject.totalPageCount}">
						<li>
							<a href="javascript:packgPaging(${PackgValueObject.totalPageCount})" aria-label="Next">
							<span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>  
				</ul>
			</nav>
		</div> 
	</div>
	<script>
		function packgRead(packgSeq, packgType){
			location.href = "/packgRead.do?packgSeq="+packgSeq+"&packgType="+packgType;
		};

		//리스트 색 변경
		function changeTrColor(trObj, oldColor, newColor) {
			trObj.style.backgroundColor = newColor;
			trObj.onmouseout = function(){
				trObj.style.backgroundColor = oldColor;
			}
		};
	</script>
</body>
</html>