<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- 
* 입고거래장 수정화면을 보여주는 JSP입니다.
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
	<body>
		<div style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
			<h3>입고거래장 수정</h3>
		</div>
		
		<div class="form-group"></div>
		
		<form class="form-inline"  name="instckFrm" enctype="multipart/form-data" method="POST">
			<input type="hidden" name="instckExchngFlorSeq" value="${instckVo.instckExchngFlorSeq}">
				<div class="container form-group">
					<table class="table table-bordered">
						<tr>
							<td class="active text-center">
								<b>제목</b>
							</td>
							<td><input type="text" class="form-control" name="instckExchngFlorTitl" value="${instckVo.instckExchngFlorTitl}"></td>
						</tr>
						<tr>
							
							<td class="active text-center">
								<b>거래처</b>
							</td>
							<td>
								<select class="form-control" id="clintSeq" name="clintSeq" style="width:300px">
									<c:forEach items="${clintNmeList}" var="clint" varStatus="status">
										<option value="${clint.clintSeq}"  ${instckVo.clintSeq eq clint.clintSeq ? "selected" : ""}>${clint.clintNme}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td class="active text-center">
								<b>거래금액</b>
							</td>
							<td>
								<input type="text" class="form-control" name="instckExchngFlorPric" value="${instckVo.instckExchngFlorPric}">
							</td>
						</tr>
						<tr>
							<td class="active text-center">
								<b>이미지1</b>
							</td>
							<td>
								<input type="text" class="form-control" name="instckExchngFlorImg1" value="${instckVo.instckExchngFlorImg1}">
								<input type="file" class="form-control" name="img1">
							</td>
						</tr>
						<tr>
							<td class="active text-center">
								<b>이미지2</b>
							</td>
							<td>
								<input type="text" class="form-control" name="instckExchngFlorImg2" value="${instckVo.instckExchngFlorImg2}">
								<input type="file" class="form-control" name="img2">
							</td>
						</tr>
						<tr>
							<td class="active text-center">
								<b>이미지3</b>
							</td>
							<td>
								<input type="text" class="form-control" name="instckExchngFlorImg3" value="${instckVo.instckExchngFlorImg3}">
								<input type="file" class="form-control" name="img3">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea class="form-control" name="instckExchngFlorCnt" placeholder="내용을 입력하세요."  style="resize: none; height:100%;">${instckVo.instckExchngFlorCnt}</textarea>
							</td>
						</tr>
						<tr style="height:150px">
							<td colspan="2">
								<table class="table table-bordered" style="padding:0px; margin:0px"> 
								<c:if test="${instckVo.instckExchngFlorImg1 != null}">
									<tr style="height:150px">
										<td><img style="max-width:100%; height:auto;" src="${instckVo.instckExchngFlorImg1}?ver=1"></td>
									</tr>
									<tr>
										<td class="active text-center"> 이미지1 </td>
									</tr>
								</c:if>
								<c:if test="${instckVo.instckExchngFlorImg2 != null}">
									<tr style="height:150px">
										<td><img style="max-width:100%; height:auto;" src="${instckVo.instckExchngFlorImg2}?ver=1"></td>
									</tr>
									<tr>
										<td class="active text-center"> 이미지2 </td>
									</tr>
								</c:if>
								<c:if test="${instckVo.instckExchngFlorImg3 != null}">
									<tr style="height:150px">
										<td><img style="max-width:100%; height:auto;" src="${instckVo.instckExchngFlorImg3}?ver=1"></td>
									</tr>
									<tr>
										<td class="active text-center"> 이미지3 </td>
									</tr>
								</c:if>
								</table>
							</td>
						</tr>
					</table>
				</div>	
			</form>
			<div class="text-center">
				<input type="button" class="btn btn-default" value="완 료" onclick="javascript:instckUpdate()">
				<input type="button" class="btn btn-default" value="취 소" onclick="javascript:instckCancel()">
			</div>
		
		<script>
			//F5키 막아놓기
			window.onkeydown = function() {
				var kcode = event.keyCode;
				if(kcode == 116) event.returnValue = false;
			}
		
			// 	입고거래장 수정 완료
			function instckUpdate(){
				window.opener.name = "parentPage"; // 부모창의 이름 설정
                document.instckFrm.target = "parentPage"; // 타켓을 부모창으로 설정
				document.instckFrm.action="/InstckExchngFlorUpdate.do";
				document.instckFrm.submit();
				self.close();
			};	
			// 	입고거래장 수정 취소
			function instckCancel(){
				document.instckFrm.action="/InstckExchngFlor_ReadPopup.do";
				document.instckFrm.submit();
			};
			
			$('#clintSeq').selectize({
				maxItems:1
			});
		</script>
	</body>
</html>