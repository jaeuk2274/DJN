<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 
* 대백제 등록2 JSP 입니다.
* 
* history : 김민아, 1.0, 2016/08/08 - 초기 작성
* author : 김민아
* version : 1.0, 2016/08/08  - 초기 작성
* see : 대백제 등록 
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
				
		<!-- debecFestivalCreate 관련 JS -->
		<script src="../../../resources/js/debecFestival-js/debecFestivalCreate.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../../resources/js/debecFestival-js/debecFestivalCreate.prodctAdd.js" type="text/javascript" charset="utf-8"></script>			
	</head>
	
	<body id="debecFestivalCreate">
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
	
		<div  class="container" id="allDiv">
			<form name="debecFestivalCreateFrm">    
				<input type="hidden"  name="prodctSeq_arry">
				<input type="hidden"  name="discntRat_arry">
				<input type="hidden" id="listLength" name ="listLength" value="${fn:length(debecFestivalProdctList)+1}"> <!-- 리스트 길이 -->
				
				<div class="container">
					<h3>대백제 등록</h3>
					<div style="display:inline-block; margin-top:1%; float:right">
						<h5>
							<img src="/resources/image/common/home.png" style="width: 22px;"> > 대백제 관리 > 
							<a href="/debecFestivalList.do" style="text-decoration:none"><strong>대백제 목록</strong></a>
						</h5>
					</div>
				</div>
				
				<div class="container">
					<h3>상품 리스트</h3>
				</div>
				
				<div class="container form-inline text-right">
					<strong>전체 할인율(%)</strong>
					<input type="text" class="form-control" id="allDiscntRat" >
					<input type="button" class="btn btn-default"value="일괄 적용"  id="allApply"/>
					<input type="button" class="btn btn-default" id="insertProdct" value="상품 추가" onclick="javascript:prodctCreate()"/>
					<input type="button" class="btn btn-default" value="상품 삭제"id="deleteFestival"/>
				</div>
				
				<!-- 상품 리스트 -->
				<div class="container">
					<table class="table table-bordered text-center">
						<colgroup>
							<col class="col-md-1 col-sm-1"/>  
							<col class="col-md-1 col-sm-1"/>           
						    <col class="col-md-2 col-sm-2"/>
						    <col class="col-md-2 col-sm-2"/>           
						    <col class="col-md-1 col-sm-1"/>
						    <col class="col-md-1 col-sm-1"/>           
						    <col class="col-md-1 col-sm-1"/>
						    <col class="col-md-1 col-sm-1"/>           
						    <col class="col-md-1 col-sm-1"/>
						    <col class="col-md-1 col-sm-1"/>           
						</colgroup>
						
						<tr class="active">
							<th class="text-center"><input type="checkbox" id="allCheck"></th>
							<th class="text-center">바코드</th>
							<th class="text-center">이미지</th>
							<th class="text-center">상품명</th>
							<th class="text-center">재고량</th>
							<th class="text-center">진열 여부 </th>
							<th class="text-center">판매가</th>
							<th class="text-center">할인율(%)</th>
							<th class="text-center">세일가</th>
							<th class="text-center">마진율(%)</th>
						</tr>
						<c:forEach var="debecFestivalProdctList" items="${debecFestivalProdctList}" varStatus="status">
							<tr>
								<td><input type="checkbox" name="debecFestivalCheck" value="${debecFestivalProdctList.prodctSeq}"></td>
								<td><c:out value="${debecFestivalProdctList.prodctSeq}"/></td>
								<td><img src="${debecFestivalProdctList.mainImg}"/></td>
								<td><c:out value="${debecFestivalProdctList.prodctNme}"/></td>
								<td><c:out value="${debecFestivalProdctList.prodctQunt}"/></td>
								<td>
									<c:if test = "${debecFestivalProdctList.displyCheck=='Y'}">
										<c:out value="진열"/>
									</c:if>
									<c:if test = "${debecFestivalProdctList.displyCheck=='N'}">
										<c:out value="미진열"/>
									</c:if>
								</td>
								<td><input type="text" class="form-control" id="selPric${status.count}" value="${debecFestivalProdctList.selPric}" disabled/></td><!-- 정상가 -->
								<td><input type="text" class="form-control" id="discntRat${status.count}" name="discntRat" value="0" onkeyup="javascript:singleDiscntRat(${status.count})" /></td><!-- 할인율 -->
								<td><input type="text" class="form-control" id="salePric${status.count}"  name="salePric" value="0"  disabled/></td><!-- 세일가 -->
								<td>
									<input type="text" class="form-control" id="margnRat${status.count}" name="margnRat" value="${debecFestivalProdctList.margnRat}" readonly/><!-- 마진율 -->
									<input type="hidden" id="purchsPric${status.count}" name="purchsPric" value="${debecFestivalProdctList.purchsPric}"> <!-- 입고가격 -->
									<input type="hidden" id="prodctSeq${status.count}" name="prodctSeq" value="${debecFestivalProdctList.prodctSeq}"> <!-- 상품 고유값 -->
								</td>	
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="container text-right">
					<input class="btn btn-default" type="button" value="엑셀업로드" onclick="javascript:xlsxUpload()"/>
				</div>
				<div class="container text-center">
					<input class="btn btn-default" type="button" value="등록" onclick="javascript:debecFestivalCreateTwo()"/>
					<input class="btn btn-default" type="button" value="취소" onclick="javascript:location.href='/prodctCreateCancel.do'"/>
				</div>
			</form>
		</div>
	</body>
</html>