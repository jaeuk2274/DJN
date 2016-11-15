<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 
* 직원의 남은 연가 일수를 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        이인호, 1.0, 2016/08/17 – 초기 작성
* author : 이인호
* version : 1.0, 2016/08/17  - 초기 작성
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
	</head>
	<body>
		<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
			<h3>연가 조회</h3>
		</div>
		
		<div class="form-group"></div>
		
		<div class="container">
			<table class="table table-bordered">
				<tr class="active">
					<td>이름</td>
					<td>사용 일수</td>
				</tr>
				<c:choose>
					<c:when test="${fn:length(selectAnlVactnList) == 0}">
						<tr class="text-center">
							<td colspan="2">
								<h4>사용된 연가가 없습니다</h4>
							</td>
						</tr>
					</c:when>
					<c:when test="${fn:length(selectAnlVactnList) != 0}">
						<c:forEach var="selectAnlVactnList" items="${selectAnlVactnList}" varStatus="status">
							<tr>
								<td><c:out value="${selectAnlVactnList.nme}"/></td>
								<td><c:out value="${selectAnlVactnList.term}"/>일</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</table>
		</div>
		<div class="container text-center">
			<input class="btn btn-default" type="button" value="닫 기" onclick="javascript:cancel()">
		</div>
	</body>
	
	<script>
		function cancel(){ 
	  		self.close();
	  	}
	</script>
	
</html>