<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 직원 추천 화면을 보여주는 JSP입니다.
* 
* history :
*        시상일, 1.0, 2016/09/08 – 초기 작성
* author : 시상일
* version : 1.0, 2016/09/08  - 초기 작성
* see : 직원 추천 화면
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
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		<div class="container">
			<div class="text-left">
				<h3>
					추천합시다<br>
					이번 달,추천하고 싶은 동료를 뽑아주세요!<br>
					투표결과는 '이달의 대장남' 선정에 반영됩니다.
				</h3>
			</div>
		</div>
		<div class="container">
			<div class="container">
				<c:forEach var="empList" items="${empList}" varStatus="status">
					<a href="javascript:recmndEmp(${empList.empSeq})">
						<div class="col-md-3 col-xs-4 text-center" style="display:inline-block;">
							<div>
								<img src="${empList.empImg}" style="height:200px;">
							</div>
							<div>
								<h4>${empList.empNme}</h4>
							</div>
						</div>
					</a>
				</c:forEach>
			</div>
		</div>
		<script>
			function recmndEmp(empSeq){
				$.ajax({
					type:"POST",
					url:"/memRecmnChck.do",
					success:function(data)
					{
						if(data == 1){
							alert("이미 추천하셨습니다.");
						}
						else if(data==0){
							if (confirm("정말 추천하시겠습니까??") == true){    //확인
							window.location.href="/recmndEmp.do?empSeq="+empSeq;
							alert("추천되었습니다.")
							}
							else{   //취소
							return;
							}
						}
					}
				});
			};
		</script>   
	</body>
</html>