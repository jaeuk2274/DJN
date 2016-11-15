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
	<body onunload="reload()">
		<div style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
			<h3>입고거래장 등록</h3>
		</div>
		
		<div class="form-group"></div>
		
		<form class="form-inline" name="instckFrm" enctype="multipart/form-data" method="POST">
			<div class="container form-group">
				<table class="table table-bordered">
					<colgroup>
						<col style="width:25%">
						<col style="width:55%">
						<col style="width:20%">
					</colgroup>
					<tr>
						<td class="active text-center">
							<b>제목</b>
						</td>
						<td><input type="text" class="form-control" name="instckExchngFlorTitl"></td>
					</tr>
					<tr>
						<td class="active text-center">
							<b>거래처</b>
						</td>
						<td>
							<select class="form-control" id="clintSeq" name="clintSeq">
								<c:forEach items="${clintNmeList}" var="clint" varStatus="status">
									<option value="${clint.clintSeq}">${clint.clintNme}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="active text-center">
							<b>거래금액</b>
						</td>
						<td><input type="text" class="form-control" name="instckExchngFlorPric"></td>
					</tr>
					<tr>
						<td class="active text-center">
							<b>이미지 추가1</b>
						</td>
						<td><input type="file" class="form-control" name="img1"></td>
					</tr>
					<tr>
						<td class="active text-center">
							<b>이미지 추가2</b>
						</td>
						<td><input type="file" class="form-control" name="img2"></td>
					</tr>
					<tr>
						<td class="active text-center">
							<b>이미지 추가3</b>
						</td>
						<td>
							<input type="file" class="form-control" name="img3">
						</td>
					</tr>
					<tr style="height:150px">
						<td colspan="2">
							<textarea name="instckExchngFlorCnt" class="form-control" placeholder="내용을 입력하세요." style="resize: none; height:100%;"></textarea>
						</td>
					</tr>
				</table>
				<div class="text-center">
					<input type="button" class="btn btn-default" value="완료" onclick="javascript:instckCreate()">
					<input type="button" class="btn btn-default" value="취소" onclick="javascript:popupCancel()">
				</div>
			</div>
		</form>
		<script>
			//F5키 막아놓기
			window.onkeydown = function() {
				var kcode = event.keyCode;
				if(kcode == 116) event.returnValue = false;
			}
			
			function instckCreate(){
				window.opener.name = "parentPage";
				document.instckFrm.target = "parentPage";
				document.instckFrm.action="/InstckExchngFlorCreate.do";
				document.instckFrm.submit();
				self.close();
			};
			
			$('#clintSeq').selectize({
				maxItems: 1
			});
			
			function reload(){
				opener.parent.location.reload();		// 팝업의 부모창 새로고침
			}
			
			function popupCancel(){
				window.close();
			}
		</script>
	</body>
</html>