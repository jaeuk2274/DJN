<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!-- 
* 핫딜 상품 진열 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        김동욱, 1.0, 2016/08/09 – 초기 작성
* author : 김동욱
* version : 1.1, 2016/08/14  - 초기 작성
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
			
		<script src="/resources/js/display-js/hotdl.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body onbeforeunload="closeIt()">
		<div class="container">
			<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
				<h3>핫딜 상품 수정</h3>
			</div>
			
			<div class="form-group"></div>
			
			<div class="container form-inline">
				<form id="updateHotdlForm">
					<input type="hidden" name="prodctSeq" value="${HotdlVo.prodctSeq}">
					<table class="table table-bordered">
						<tr>
							<td class="text-center" rowspan="6"><img src="${HotdlVo.img}" style="width:250px; height:250px"></td>
						</tr>
						<tr>
							<th class="active text-center">상품번호</th>
							<td>${HotdlVo.prodctSeq}</td>
						</tr>
						<tr>
							<th class="active text-center">상품명</th>
							<td>${HotdlVo.prodctNme}</td>
						</tr>
						<tr>
							<th class="active text-center">판매 가격</th>
							<td>${HotdlVo.selPric}</td>
						</tr>
						<tr>
							<th class="active text-center">핫딜 가격</th>
							<td><input type="text" class="form-control" name="discntPric" value="${HotdlVo.discntPric}"></td>
						</tr>
						<tr>
							<th class="active text-center">판매 수량</th>
							<td><input type="text" class="form-control" name="amont" value="${HotdlVo.amont}"></td>
						</tr>
					</table>
				</form>
			</div>
			
			<div class="container text-center">
				<input type="button" class="btn btn-default" value="수정" onclick="hotdlUpdate()">
				<input type="button" class="btn btn-default" value="취소" onclick="window.close()">
			</div>
		</div>
		<script>
			function hotdlUpdate(){
				var formData = $("#updateHotdlForm").serialize();
				$.ajax({
					type : "POST",
					url : "/hotdlUpdate.do",
					data : formData,
					success: function(data) {
						self.close();
						opener.parent.location.href = "/hotdlList.do";
					}
				});
			};
			
			function closeIt(){
			    window.opener.$("#FadeIn").remove();
				self.close();
			};
		</script>
	</body>
</html>