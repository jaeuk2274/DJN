<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 직원의 휴가를 상세조회하는 화면을 보여주는 JSP입니다.
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
		<c:forEach var="selectVactnRead" items="${selectVactnRead}" varStatus="status">
		<form name="vactnReadInfo" id="vactnReadInfo" method="post">
			<input type="hidden" name="schedlSeq" id="schedlSeq" value="${selectVactnRead.schedlSeq}">
		</form>
			<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
				<h3>휴가 상세조회</h3>
			</div>

			<div class="form-group"></div>
			
			<div class="container">
				<table class="table table-bordered">
					<tbody>
						<tr>
							<th class="active">이름</th>
							<td><c:out value="${selectVactnRead.nme}"/></td>
						</tr>
						<tr>
							<th class="active">연락처</th>
							<td><c:out value="${selectVactnRead.mobl}"/></td>
						</tr>
						<tr>
							<th class="active">종류</th>
							<td><c:out value="${selectVactnRead.vactnType}"/></td>
						</tr>
						<tr>
							<th class="active">기간</th>
							<td><c:out value="${selectVactnRead.schedlStartDat}"/>&nbsp;&nbsp;~&nbsp;&nbsp;<c:out value="${selectVactnRead.schedlEndDat}"/></td>
						</tr>
						<tr>
							<th class="active">사유</th>
							<td><c:out value="${selectVactnRead.schedlResn}"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:forEach>
		<div class="container text-center">
			<input class="btn btn-default" type="button" value="수정"  onclick="javascript:vactnUpdateForm()">
			<input class="btn btn-default" type="button" value="삭제"  onclick="javascript:vactnDeleteForm()">
		</div>
		<script type="text/javascript">
			function vactnUpdateForm(){
				var schedlSeq = document.getElementById("schedlSeq").value;
				document.vactnReadInfo.schedlSeq.value = schedlSeq;
				document.vactnReadInfo.action = "/vactnUpdateRead.do";
				document.vactnReadInfo.submit();
			}
			
			
			function vactnDeleteForm() {
				   var formData = $("#vactnReadInfo").serialize();
				   $.ajax({
				      type : "POST",
				      url : "/vactnDelete.do",
				      data : formData,
				      success: function(msg) {
				    	  
				      }
				   });
				   self.close();
		         opener.parent.location.reload();
				}
		</script>
	</body>
</html>