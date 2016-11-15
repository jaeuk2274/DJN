<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<!-- 
* 입고거래장 등록 화면을 보여주는 JSP입니다.
* 
* history :
*        김대현, 1.0, 2016/08/23 – 초기 작성
* author : 김대현
* version : 1.0, 2016/08/23  - 초기 작성
* see : 관련된 모듈을 기술한다.
// -->
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
	<body onunload="reload()">
		<div style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
			<h3>재고 등록</h3>
		</div>
		<form class="form-inline" name="stckInsertFrm" enctype="multipart/form-data" method="POST">
			<input type="hidden" name="stckType" id="stckType" value="${stckValueObject.stckType}" onchange="javascript:recpPopSelfClose()">
			<div class="container form-group">
				<table class="table table-bordered">
					<colgroup>
						<col style="width:35%">
						<col style="width:65%">
					</colgroup>
					<tr>
						<td class="active text-center" colspan="2">
						<img src="../../../../resources/image/common/stckHowToUpload.png" width="100%">
						</td>
					</tr>
					<tr style="height:150px">
						<td class="active text-center">
						파일 선택
						</td>
						<td>
							<input type="file" name = "xlsxFile">						
						</td>
					</tr>
				</table>
				<div class="text-center">
					<input type="button" id="stckInsert" class="btn btn-default" value="완료" >
					<input type="button" id="stckCacl" class="btn btn-default" value="취소">
				</div>
			</div>
		</form>
	<script>
	$(document).ready(function() {
		 $('#stckInsert').click(function()
		{
			$("#stckType").val(1);
			document.stckInsertFrm.action="stckCreate.do";
			document.stckInsertFrm.method="post";
			document.stckInsertFrm.submit();
		});
		 $('#stckCancl').click(function()
		{
			 window.close();
			 
		});
		if($("#stckType").val()==3)
		{
			window.opener.document.location.reload();
			self.close();
		}
	});
	</script>
	</body>
</html>