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
	<body onunload="parentReload()">

		<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
			<h3>거래처 변경</h3>
		</div>

		<dl></dl>
		
		<form id="clintUpdateFrm" name="clintUpdateFrm">  
			<input type="hidden" id="clintBridgChkGroup" name="clintBridgChkGroup" value="${clintVo.chkList}"> <!-- 선택된거래처 브릿지 seq --> 
			<div class="container">
				<table class="table table-bordered">
					<tr>
						<th class="active">
	                       	<h5 class="text-center"><b>거래처</b></h5>
	                    </th>
						<td>
							<select id="clintSeq" name="clintSeq" class="form-control">
								<c:forEach items="${clintNmeList}" var="clint" varStatus="status">
									<option value="${clint.clintSeq}" ${clintVo.clintSeq eq clint.clintSeq ? "selected" : ""}>${clint.clintNme}</option>
								</c:forEach>		
							</select>
						</td>
					</tr>
				</table>
				<div class= "text-center">
					<input type="button" class="btn btn-default" onclick="clintProductUpdate()" value="변경">
					<input type="button" class="btn btn-default" onclick="javacript:cancel()" value="취소">
				</div>
			</div>			
		</form>	
		
		<script>
			//F5키 막아놓기
			window.onkeydown = function() {
				var kcode = event.keyCode;
				if(kcode == 116) event.returnValue = false;
			}
			
			// 거래처 상품 거래처 변경
			function clintProductUpdate() {			
				var group = $('#clintBridgChkGroup').val();
				group = group.substr(1, group.length-2);
				$('#clintBridgChkGroup').val(group);				
				document.clintUpdateFrm.action ="/ClintProductUpdate.do";
				document.clintUpdateFrm.submit();
			}			
			
			// 창이 onunload 되면 부모장 reload
			function parentReload() {
				opener.parent.location.reload();
				window.close();
			};
			
			function cancel() {
				window.close();
			};		

			
		</script>
		
	</body>
</html>