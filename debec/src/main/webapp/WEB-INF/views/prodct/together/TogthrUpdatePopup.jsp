<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!-- 
* 메인 진열 상품 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        김동욱, 1.0, 2016/08/09 – 초기 작성
* author : 김동욱
* version : 1.2, 2016/08/16  - 초기 작성
* see : 관련된 모듈을 기술한다.
//-->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>대신 장봐주는 남자 - 대.장.남</title>
	
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
	
	<script src="/resources/js/display-js/togthr.js" type="text/javascript" charset="utf-8"></script>
	
	<script type="text/javascript">
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
	</script>
</head>
<body>
	<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
		<h3>다함께 상품 수정</h3>
	</div>
	
	<div class="form-group"></div>
	
	<div class="container">
		<form class="form-inline" name="purchsProdctUpdate" method="POST">
		<input type="hidden" value="${togthrUpdage.gropPurchsProdctSeq}" name="gropPurchsProdctSeq">
			<table class="table table-bordered form-inline">
				<colgroup>
				    <col class="col-md-4 col-xs-4"/>          
				    <col class="col-md-8 col-xs-8"/>               
				</colgroup>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>바코드</b></h5>
					</td>
					<td><input type="text" class="form-control" value="${togthrUpdage.gropPurchsSeq}" name="gropPurchsSeq" readonly="readonly"></td>
				</tr>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>상품명</b></h5>
					</td>
					<td><input type="text" class="form-control" value="${togthrUpdage.prodctNme}" name="prodctNme" readonly="readonly"></td>
				</tr>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>제목</b></h5>
					</td>
					<td><input type="text" class="form-control" value="${togthrUpdage.titl}" name="titl"></td>
				</tr>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>주문기간</b></h5>
					</td>
					<td class="form-inline">
						<input type="text" class="form-control" id="ordrStarDat" value="${togthrUpdage.ordrStarDat}" name="ordrStarDat">
						<font>~</font>
						<input type="text" class="form-control" id="ordrEndDat" value="${togthrUpdage.ordrEndDat}" name="ordrEndDat">
					</td>
				</tr>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>최소 주문수량</b></h5>
					</td>
					<td><input type="text" class="form-control" value="${togthrUpdage.minOrdrAmont}" name="minOrdrAmont">개</td>
				</tr>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>최대 주문수량</b></h5>
					</td>
					<td><input type="text" class="form-control" value="${togthrUpdage.maxOrdrAmont}" name="maxOrdrAmont">개</td>
				</tr>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>기존가격</b></h5>
					</td>
					<td><input type="text" class="form-control" value="${togthrUpdage.existPric}" name="existPric" readonly="readonly"></td>
				</tr>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>판매가격</b></h5>
					</td>
					<td><input type="text" class="form-control" value="${togthrUpdage.selPric}" name="selPric"></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="container text-center">
		<input type="button" class="btn btn-default" value="수정완료" onclick="togthrUpdate()">
		<input type="button" class="btn btn-default" value="취소" onclick="cancle()">
	</div>
</body>
</html>