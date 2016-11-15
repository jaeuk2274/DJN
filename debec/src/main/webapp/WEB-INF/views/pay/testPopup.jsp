<!-- 
* 회원 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        최재욱, 1.0, 2016/08/09 – 초기 작성
* author : 최재욱
* version : 1.0, 2016/08/09  - 초기 작성
* see : 회원 목록 조회 화면.
//-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>대신 장봐주는 남자 - 대.장.남</title>
		
		<!-- 합쳐지고 최소화된 최신 CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		
		<!-- 부가적인 테마 -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
		
		<!-- JQuery 관련 파일들 -->
		<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>	
		
		<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
					
	</head>
	<body onload="alltheGateDemo()">
		<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
			<h3>결제 승인 화면 (올더게이트 테스트 버전)</h3>
		</div>

		<div class="container text-center">
			<h4 class="text-center"><b>결제 테스트 팝업</b></h4>
		</div>
			테스트 버전 이용.. <br>
			올더게이트 데모버전 사이트로 이동합니다.
		<dl></dl>
		
		<div class="container">

		</div>
		<script>
			// 올더게이트 테스트 데모버전 사이트 이동
			function alltheGateDemo() {
				window.close();
				window.open('http://www.allthegate.com/ags/download/download_05.jsp');
			};
	
		</script>
	</body>
</html>