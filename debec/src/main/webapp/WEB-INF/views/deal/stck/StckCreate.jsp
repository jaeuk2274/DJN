<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 재고 정보를 일괄 등록하는 JSP 입니다.
* 
* history :
*        하원식, 1.0, 2016/08/16 – 초기 작성
* author :  하원식
* version : 1.6, 2016/08/27  - 디자인 완성
* see : 관련된 모듈을 기술한다.
//--> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8}">
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
		
		<form name="stckInsertFrm" method="post" enctype="multipart/form-data">
			<div class="container">
				<div class="container">
					<h3>재고 일괄 등록</h3>
					<div style="display:inline-block; margin-top:1%; float:right">
					   <h5>
					     <img src="/resources/image/common/home.png"> > 재고 관리 > 
					     <a href="/stckList.do" style="text-decoration:none"><strong>재고 목록</strong></a>
					   </h5>
					</div>
				</div>
				<div class="container">
					<img src="../../../../resources/image/common/stckHowToUpload.png" class="img-responsive">
					<div >
						<h4>재고 일괄 등록</h4>
					</div>
					<div >
						<input type="file" name = "xlsxFile">
					</div>
				</div>
				<div class="container text-center">
					<input type="button" class="btn btn-default" id="stckInsert" value="등록">
					<input type="button" class="btn btn-default" id="stckCancl" value="취소">
				</div>
			
			
<!-- 				<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;"> -->
<!-- 					<h3>재고 일괄 등록</h3> -->
<!-- 				</div> -->
				
<!-- 				<div class="container"> -->
<!-- 					<img src="resources/image/stckHowToUpload.jpg" style="width:700px;"> -->
<!-- 					<div > -->
<!-- 						<h5>재고 등록하기</h5> -->
<!-- 					</div> -->
<!-- 					<div > -->
<!-- 						<input type="file" name = "xlsxFile" style="font-size:15px;"> -->
<!-- 					</div> -->
<!-- 				</div> -->
				
<!-- 				<div class="container text-center"> -->
<!-- 					<input class="btn btn-default" type="button" id="stckInsert"  value="등록"> -->
<!-- 					<input class="btn btn-default" type="button" id="stckCancl"  value="취소"> -->
<!-- 				</div> -->
			</div>
		</form>
	</body>
	<script>
	$(document).ready(function() {
		 $('#stckInsert').click(function()
		{
			document.stckInsertFrm.action = "/stckCreate.do";
			document.stckInsertFrm.method = "post";
			document.stckInsertFrm.submit();
			 
		});
		 $('#stckCancl').click(function()
		{
			location.href="/stckList.do";
			 
		});
	});
	</script>
</html>