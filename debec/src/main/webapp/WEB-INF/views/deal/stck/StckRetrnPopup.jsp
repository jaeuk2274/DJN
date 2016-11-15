<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
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
		
		<script src="resources/js/stck-js/stckRetrn.js"></script> 
	</head>
	<body>
		<form name="stckRetrnFrm">
			<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
				<h3>반품 등록</h3>
			</div>
			
			<div class="form-group"></div>
			
			<div class="container text-center">
				<table class="table table-bordered">
					<colgroup>
						<col class="col-md-6 col-sm-4"/>                
					    <col class="col-md-3 col-sm-4"/>          
					    <col class="col-md-3 col-sm-4"/> 
					</colgroup>
					<tr  class="active">
						<td class="text-center">
							<h5 class="text-center"><b>상품명</b></h5>
						</td>
						<td class="text-center">
							<h5 class="text-center"><b>기존 수량</b></h5>
						</td>
						<td class="text-center">
							<h5 class="text-center"><b>반품 수량</b></h5>
						</td>
					</tr>
					<c:forEach var="selProdct" items="${selProdctList}" varStatus="status">
						<tr class="text-center">
							<td>
								${selProdct.prodctNme}
								<input type="hidden" name="prodctCheck" value="${selProdct.prodctSeq}">
							</td>
							<td><input type="text" class="form-control text-right" name="${selProdct.prodctSeq}" value="${selProdct.prodctStck}"></td>
							<td><input type="text" class="form-control text-right" name="${selProdct.prodctSeq}" id="${selProdct.prodctSeq}" value="0"></td>
						</tr>
					</c:forEach>
				</table>
					<input type="button" class="btn btn-default" id="stckRetrnSend" value="완료">
					<input type="button" class="btn btn-default" id="stckRetrnCancl" value="취소">
			</div>
		</form>
	</body>
</html>