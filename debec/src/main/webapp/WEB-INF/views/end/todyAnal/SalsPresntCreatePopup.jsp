<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<body>
		<div style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
			<h3>엑셀 업로드</h3>
		</div>
		
		<div class="form-group"></div>
		
		<div class="container">
			<form name="excelFrm" enctype="multipart/form-data" method="post">
				<div class="container">
					<table class="table table-bordered">
						<colgroup>
							<col class="col-xs-2">
							<col class="col-xs-4">
						</colgroup>
						<tr>
							<td class="active text-center">엑셀<br>업로드</td>
							<td><input type="file" id="excelFile" name="fileName"></td>
						</tr>
					</table>
				</div>
				
				<div class="form-group"></div>
				
				<div class="container text-center">
					<input type="button" class="btn btn-default" value="업로드" onclick="javascript:excelUpload()">
					<input type="button" class="btn btn-default" value="닫기"  onclick="javascript:popupCancel()">
				</div>
			</form>
		</div>
		<script>
			//F5키 막아놓기
		   window.onkeydown = function() {
		      var kcode = event.keyCode;
		      if(kcode == 116) event.returnValue = false;
		   }

			// 엑셀파일 업로드
			function excelUpload(){
				if($('#excelFile').val() == ''){
					alert('파일을 선택해주세요');
				}
				else{
					window.opener.name = "parentPage"; // 부모창의 이름 설정
	                document.excelFrm.target = "parentPage"; // 타켓을 부모창으로 설정
	                document.excelFrm.action="/SalsPresntExcelUpload.do";
	                document.excelFrm.submit();
	                self.close();
				}
			};

			// 팝업 닫기 버튼
			function popupCancel(){
				window.close();
			};
		</script>
	</body>
</html>