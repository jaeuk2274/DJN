<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 회원 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        최재욱, 1.0, 2016/08/09 – 초기 작성
* author : 최재욱
* version : 1.0, 2016/08/09  - 초기 작성
* see : 회원 목록 조회 화면.
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
		
	</head>
	
	<body>
		<div class="container">
			<h3>회원 선택</h3>
		</div>
		
		<div class="container text-center">
			<h4>회원 리스트</h4>
		</div>
		
		<div class="container">
			<table class="table table-bordered">
				<tr>
					<td>no</td>
					<td>ID</td>
					<td>이름</td>
					<td>성별</td>
					<td>휴대전화</td>
				</tr>
				<c:forEach items="${CustmrList}" var="CustmrVO" varStatus="status">	
					<tr>
						<td><a onclick="javascript:custmrReadPopup(${CustmrVO.custmrSeq})"> ${CustmrValueObject.totalRecordCount-status.index-(CustmrValueObject.currentPageNo-1)*10}</a></td>
						<td> ${CustmrVO.custmrNme}</td>							
						<td> ${CustmrVO.custmrId}</td>	 
						<td> ${CustmrVO.custmrId}</td>
						<td> ${CustmrVO.custmrMobl}</td>
					</tr>
				</c:forEach>	
			</table>
		</div>
	</body>
</html>