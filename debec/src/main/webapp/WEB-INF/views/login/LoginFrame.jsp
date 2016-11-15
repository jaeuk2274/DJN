<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 로그인 JSP 입니다.
* 
* history : 김민아, 1.0, 2016/08/08 - 초기 작성
* author : 김민아
* version : 1.0, 2016/08/08  - 초기 작성
* see : 로그인
//-->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densityDpi=medium-dpi" />
		<title>대신 장봐주는 남자 - 대.장.남</title>
		
		<!-- 합쳐지고 최소화된 최신 CSS -->
		<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		
		<!-- 부가적인 테마 -->
		<link href="/resources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>

		<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
		<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
	</head>
	
	<body>
		<form method="post" name="loginForm">
			<div style="width:100%; height:100%; position:absolute; background:url(../../resources/image/common/login.png); background-size: cover">
				<div class="container" >
					<div style="top:400px; width:100%; height:1px; position:relative;" align="center"> 
						<table>
							<tr>
								<td><img src="../../resources/image/common/debec.png"></td>
							</tr>
							<tr>
								<td><input class="form-control" type="text" name="empId" placeholder="ID"  maxlength="12" autofocus required onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9,.]/g,'');"></td>
							</tr>
							<tr>
								<td><input class="form-control" type="password" name="empPw" placeholder="Password" maxlength="12" onkeydown='javascript:onEnterSubmit()' required></td>
								<c:if test="${falseMsg!=null}">
										<p style="color: red">${falseMsg}</p>
								</c:if>
							</tr>
							<tr>
								<td><input class=" btn-lg btn btn-default btn-block" type="button" value="로그인" onclick="javascript:login()"></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</form>
		<script type="text/javascript">
			function login() {
				document.loginForm.action = "/login.do";
				document.loginForm.submit();
			};
			
			function onEnterSubmit(){
				var keyCode = window.event.keyCode;
				if(keyCode==13) loginForm.submit();
				document.loginForm.action = "/login.do";
			};
		</script>
	</body>
</html>