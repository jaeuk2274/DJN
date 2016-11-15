<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 연관 상품 등록을 위한 팝업이다.
* 
* history :
*        하원식, 1.0, 2016/08/09 – 초기 작성
* author : 하원식
* version : 1.0, 2016/08/09  - 초기 작성
* see : 관련된 모듈을 기술한다.
//-->
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
	
		<!-- 팝업 관련 JS 스크립트 -->
		<script src="/resources/js/prodct-js/selProdctPopUp.js?ver=3"></script>
		<!-- 카테고리 관련 JS 스크립트 -->
		<script src="/resources/js/prodct-js/catgrChoice.js"></script>
	
		<!-- JSTree 관련 파일들 -->
		<script src="/resources/js/jstree/jstree.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" href="/resources/js/jstree/themes/proton/style.min.css">
	</head>
	<body>
		<div>
			<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
				<h3>연관 상품 등록</h3>
			</div>
			<aside style="display:inline-block; float:left;">						
				<div>
					<table class="table table-bordered" style="width:270px;">
						<tr>
							<th>카테고리</th>
						</tr>
						<tr>
						<!-- td에 height값을 줘야 JSTree가 고정됨 -->
							<td style="height:490px;"><div id="tree" style="max-height:100%; overflow:auto"></div></td>
						</tr>
					</table>
				</div>
			</aside>
			<div id="prodctTable" style="width:450px; display:inline-block; float:left;">
				<c:import url="/reltnProdctAdList.do"/>	
			</div>
		</div>
	</body>
</html>