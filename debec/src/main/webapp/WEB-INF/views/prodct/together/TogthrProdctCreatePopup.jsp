<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!-- 
* 메인 진열 상품 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        이정호, 1.0, 2016/09/04 – 초기 작성
* author : 이정호
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
	
<!-- 	<!-- Table 행 바꾸는 JS -->
<!-- 	<script src="/resources/js/display-js/jquery-tablednd.js" type="text/javascript" charset="utf-8"></script> -->
	
	<!-- JSTree 관련 JS -->
	<script src="/resources/js/jstree/jstree.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="/resources/js/jstree/themes/proton/style.min.css">
</head>
<body>
	<div style="width:380px;">
		<form name="purchsProdctInsert" method="POST" id="purchsProdctInsert">
			<input type="hidden" class="form-control" name="prodctMainImage" id="togthProdctMainImage">
			<input type="hidden" class="form-control" name="prodctDetlImageOne" id="togthProdctDetlImageOne">
			<input type="hidden" class="form-control" name="prodctDetlImageTwo" id="togthProdctDetlImageTwo">
			<input type="hidden" class="form-control" name="prodctDetlImageThree" id="togthProdctDetlImageThree">
			<table class="table table-bordered">
				<colgroup>
					<col class="col-md-4 col-xs-4"/>          
				    <col class="col-md-8 col-xs-8"/>           
				</colgroup>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>바코드</b></h5>
					</td>
					<td><input type="text" class="form-control" readonly="readonly" name="prodctSeq" id="GropPurchsprodctSeq"></td>
				</tr>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>상품명</b></h5>
					</td>
					<td><input type="text" class="form-control" readonly="readonly" name="prodctNme" id="prodctNme"></td>
				</tr>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>제목</b></h5>
					</td>
					<td><input type="text" class="form-control" name="titl" id="togthInsertTitl"></td>
				</tr>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>주문기간</b></h5>
					</td>
					<td>
						<input type="date" class="form-control" name="ordrStarDat" id="togthInsertOrdrStarDat">
						<font>~</font>
						<input type="date" class="form-control" name="ordrEndDat" id="togthInsertOrdrEndDat">
					</td>
				</tr>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>최소 주문수량</b></h5>
					</td>
					<td><input type="text" class="form-control" name="minOrdrAmont" id="togthInsertMinOrdrAmont">개</td>
				</tr>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>최대 주문수량</b></h5>
					</td>
					<td><input type="text" class="form-control" name="maxOrdrAmont" id="togthInsertMaxOrdrAmont">개</td>
				</tr>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>기존가격</b></h5>
					</td>
					<td><input type="text" class="form-control" readonly="readonly" name="existPric" id="existPric"></td>
				</tr>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>판매가격</b></h5>
					</td>
					<td><input type="text" class="form-control" name="selPric" id="togthInsertSelPric"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>