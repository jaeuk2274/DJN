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
	
	<!-- 이미지 썸네일 CSS -->
	<link href="/resources/common/css/thumbnail.css" rel="stylesheet" type="text/css"/>
	
	<!-- JQuery 관련 파일들 -->
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>   
	
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
	<!-- JSTree 관련 JS -->
	<script src="/resources/js/jstree/jstree.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="/resources/js/jstree/themes/proton/style.min.css">
	
	<script src="/resources/js/display-js/togthr.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div style="margin-bottom: 130px;">
		<c:import url="/TopFrame.do"/>
	</div>
	
	<div class="container">
		<div class="container">
			<h3>다함께 상품 진열 목록</h3>
			<div style="display:inline-block; margin-top:1%; float:right">
				<h5>
					<img src="/resources/image/common/home.png"> > 다함께 관리 > 
					<a href="/empList.do" style="text-decoration:none"><strong>다함께 상품 진열 목록</strong></a>
				</h5>
			</div>
		</div>
		
		<form name="togthrPagingFrm" method="POST">
			<input type="hidden" name="currentPageNo" value="${togthrValueObject.currentPageNo}">
		</form>
		<form name="togthrDetailFrm" method="POST">
			<input type="hidden" name="gropPurchsProdctSeq" id="gropPurchsProdctSeq">
		</form>
		
		<div class="container">
			<form name="gropPurchsSearch" method="POST">
				<table class="table table-bordered form-inline">
					<colgroup>
					    <col class="col-md-2 col-xs-2"/>          
					    <col class="col-md-10 col-xs-10"/>           
					</colgroup>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>상품명</b></h5>
						</td>
						<td><input type="search" name="prodctNme" class="form-control"></td>
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>판매가</b></h5>
						</td>
						<td class="form-inline">
							<input type="search" class="form-control" name="firstSelPric">원
							 <font>~</font>
							<input type="search" class="form-control" name="LastSelPric">원
						</td>
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>상태</b></h5>
						</td>
						<td>
							<select class="form-control" name="stat">
								<option value="0">
									전체
								</option>
								<option value="1">
									모집중
								</option>
								<option value="2">
									배송중
								</option>
								<option value="3">
									배송완료
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>마감일</b></h5>
						</td>
						<td class="form-inline">
							<input type="text" class="form-control" id="ordrStarDat" name="ordrStarDat">일
							<font>~</font>
							<input type="text" class="form-control" id="ordrEndDat" name="ordrEndDat">일
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div class="container text-center">
			<input type="button" class="btn btn-default" value="검색" onclick="grorPurchsSearch()">
		</div>
		
		<div class="form-group"></div>
		
		<div class="container text-right">
			<input type="button" class="btn btn-default" value="상품추가" onclick="prodctAdd()">
			<input type="button" class="btn btn-default" value="상품삭제" id="togthrDelete">
		</div>
		
		<div class="container">
			<form name="togthrProdctFrm" method="POST">
				<table class="table table-bordered text-center">
					<colgroup>
					    <col class="col-md-1 col-xs-1"/>
					    <col class="col-md-3 col-xs-3"/>
					    <col class="col-md-1 col-xs-1"/>                
					    <col class="col-md-3 col-xs-3"/>          
					    <col class="col-md-1 col-xs-1"/>           
					    <col class="col-md-1 col-xs-1"/>
					    <col class="col-md-1 col-xs-1"/>
					    <col class="col-md-1 col-xs-1"/>
					</colgroup>
					<tr class="active">
						<th class="text-center"><input type="checkbox" id="allCheck"></th>
						<th class="text-center">상품명</th>
						<th class="text-center">판매가격</th>
						<th class="text-center">주문기간(마감)</th>
						<th class="text-center">최소<br>주문수량</th>
						<th class="text-center">최대<br>주문수량</th>
						<th class="text-center">현재<br>주문수량</th>
						<th class="text-center">상태</th>
					</tr>
					<c:forEach var="togthrProdctList" items="${togthrProdctList}" varStatus="status">
						<tr onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
							<td><input type="checkbox" name="togthrChk" value="${togthrProdctList.gropPurchsProdctSeq}"></td>
							<td onclick="togthrDetail(${togthrProdctList.gropPurchsProdctSeq})">${togthrProdctList.prodctNme}</td>
							<td onclick="togthrDetail(${togthrProdctList.gropPurchsProdctSeq})">${togthrProdctList.selPric}</td>
							<td onclick="togthrDetail(${togthrProdctList.gropPurchsProdctSeq})">${togthrProdctList.ordrStarDat}~${togthrProdctList.ordrEndDat}</td>
							<td onclick="togthrDetail(${togthrProdctList.gropPurchsProdctSeq})">${togthrProdctList.minOrdrAmont}</td>
							<td onclick="togthrDetail(${togthrProdctList.gropPurchsProdctSeq})">${togthrProdctList.maxOrdrAmont}</td>
							<td onclick="togthrDetail(${togthrProdctList.gropPurchsProdctSeq})">11</td>
							<td onclick="togthrDetail(${togthrProdctList.gropPurchsProdctSeq})">${togthrProdctList.stat}</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</div>
		<div style="text-align:center;">
			<div class="container text-center" style="width:50%">
				<nav>
					<ul class="pagination">
						<c:if test = "${togthrValueObject.currentPageNo>20}">
							<li>
								<a href="javascript:togthrPaging(1)" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span></a>
							</li>
						</c:if>  
						<c:if test = "${togthrValueObject.currentPageNo>10}">
							<li>
								<a href="javascript:togthrPaging(${togthrValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
								<span aria-hidden="true">&lsaquo;</span></a>
							</li>
						</c:if> 	
									  	
						<c:forEach varStatus="status" begin="${togthrValueObject.firstPageNoOnPageList}" end="${togthrValueObject.lastPageNoOnPageList}">
						
							<c:if test="${togthrValueObject.currentPageNo==status.current}">
								<li class="active">
									<a href="javascript:togthrPaging(${status.current})">${status.current}</a>
								</li>
							</c:if>
							<c:if test="${togthrValueObject.currentPageNo!=status.current}">
								<li><a href="javascript:togthrPaging(${status.current})">${status.current}</a></li>
							</c:if>                   									
						</c:forEach>	
						<c:if test = "${togthrValueObject.firstPageNoOnPageList + 10 < togthrValueObject.totalPageCount}">
							<li>
								<a href="javascript:togthrPaging(${togthrValueObject.lastPageNoOnPageList+1})" aria-label="Next">
								<span aria-hidden="true">&rsaquo;</span></a>
							</li>
						</c:if>  
						<c:if test = "${togthrValueObject.firstPageNoOnPageList + 20 < togthrValueObject.totalPageCount}">
							<li>
								<a href="javascript:togthrPaging(${togthrValueObject.totalPageCount})" aria-label="Next">
								<span aria-hidden="true">&raquo;</span></a>
							</li>
						</c:if>  
					</ul>
				</nav>
			</div>
		</div>
	</div>
	<script>
		// datepicker 위젯 
		// 날짜 선택 위젯, import 밑에 나와야됨
		$.datepicker.setDefaults({
	        dateFormat: 'yy-mm-dd',
	        prevText: '이전 달',
	        nextText: '다음 달',
	        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	        showMonthAfterYear: true,
	        yearSuffix: '년'
	    });
	
	    $(function() {
	        $("#ordrStarDat").datepicker();
	        $("#ordrEndDat").datepicker();
	    });

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