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
	
	<!-- Don't forget to include jQuery ;) -->
 	<script src="/resources/js/modal/jquery.modal.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="/resources/js/modal/jquery.modal.css" type="text/css" />
	
	<!-- Table 행 바꾸는 JS -->
	<script src="/resources/js/display-js/jquery.tablednd.js"></script>
	
	<!-- JSTree 관련 JS -->
	<script src="/resources/js/jstree/jstree.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="/resources/js/jstree/themes/proton/style.min.css">
</head>
<body onbeforeunload="closeIt()">
		<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
			<h3>신선 식품 수정</h3>
		</div>
		
		<div class="form-group"></div>
		
		<div class="container form-inline">
			<form id="updateFreshForm" class="form-group">
			<input type="hidden" name="prodctSeq" value="${FreshVo.prodctSeq}">
				<table class="table table-bordered">
					<tr>
						<td class="text-center" rowspan="6"><img src="${FreshVo.img}" style="width:250px; height:250px"></td>
					</tr>
					<tr>
						<th class="active text-center">상품번호</th>
						<td>${FreshVo.prodctSeq}</td>
					</tr>
					<tr>
						<th class="active text-center">상품명</th>
						<td>${FreshVo.prodctNme}</td>
					</tr>
					<tr>
						<th class="active text-center">판매 가격</th>
						<td>${FreshVo.selPric}</td>
					</tr>
					<tr>
						<th class="active text-center">판매 시작 시간</th>
						<td>${FreshVo.selStrtTime}</td>						
					</tr>
					<tr>
						<th class="active text-center">상태</th>
						<td>
							<select class="form-control" name="stat" id="stat">
								<option value="Y" >판매중</option>
								<option value="N">판매중지</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="container text-center">
			<input type="button" class="btn btn-default" value="수정" onclick="freshUpdate()">
			<input type="button" class="btn btn-default" value="취소" onclick="window.close()">
		</div>
	<script>
		function freshUpdate(){
			var formData = $("#updateFreshForm").serialize();
			$.ajax({
				type : "POST",
				url : "/freshUpdate.do",
				data : formData,
				success: function(msg) {
					self.close();
					opener.parent.location.href = "/freshList.do";
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