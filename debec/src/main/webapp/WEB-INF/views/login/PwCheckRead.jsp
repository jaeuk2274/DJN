<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 비밀번호를 재 확인하는 JSP입니다.
* 
* history :
*        이인호, 1.0, 2016/08/15 – 초기 작성
* author : 이인호
* version : 1.0, 2016/08/15  - 초기 작성
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
		<form class="form-inline" method="post" action="/pwCheckDataRead.do">
			<div class="container text-center" style="width:80%; height:86%; right: 200px; position:absolute; background:url(../../resources/image/common/login.png); background-size: cover">
				<div style="top:400px; width:100%; height:1px; position:relative;" align="center">
					<div class="container form-group">
						<img src="../../resources/image/common/debec.png" alt="대백마트 파호점" width="30%">
					</div>
					<div class="container form-group">
						<h3>비밀번호 확 인</h3>
					</div>
					<div class="container form-group">
						<input type="password" class="form-control" name="empPw">
					</div>
					<br><br>
					<div class="container form-group">
						<input type="submit" class="form-control btn btn-default" value="확인">
					</div>
				</div>
			</div>
		</form>
	</body>
</html>