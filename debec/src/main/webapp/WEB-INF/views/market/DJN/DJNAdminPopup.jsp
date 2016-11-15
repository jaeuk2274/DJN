<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 이달의 대장남 관리 팝업화면을 보여주는 JSP입니다.
* 
* history :
*        시상일, 1.0, 2016/09/08 – 초기 작성
* author : 시상일
* version : 1.0, 2016/09/08  - 초기 작성
* see : 이달의 대장남 관리 팝업화면
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
		
		<script>
			function popupClose(){
				window.open("about:blank", "_self").close();
			}
			
		</script>
	</head>
	<body>
		<div style="background:#403A39; height:40px">
			<font color="white">고객 추천 사유</font>
		</div>
		<br>
		<div align="center" style="margin:0px">
		
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script> <!-- 가장 중요한 구글 경로 설정 -->
		<script type="text/javascript">
			google.charts.load('current', {packages: ['corechart', 'bar']}); //corechart라고 되어 있는 부분을 사용하고자 하는 차트의 이름으로 바꾸면 된다. corechart는 기본적인 차트(pie, bar, column)을 사용하기 위한 이름이다. 
			google.charts.setOnLoadCallback(drawMultSeries); //구글 차트에 옵션과 데이터 값이 있는 메서드를 라이브러리를 통해 그래프를 통계 내줌.
			
			function drawMultSeries() {
				var data = new google.visualization.DataTable(); //google.visualization.DataTable() 객체 생성
				var title = new Array(); // 정호는 데이터베이스의 데이터인 제목을 배열을 사용하기 위해 title을 배열 형태로 객체 생성 
				var sales = new Array(); // 정호는 데이터베이스의 데이터인 합계를 배열을 사용하기 위해 sales을 배열 형태로 객체 생성
				var bear = 0; // 반복문에서 조건문의 사이즈로 사용하기 위해 변수 만들어 줌.
				
				<c:forEach var="recmndResn" items="${recmndResn}" varStatus="status"> // 정호는 컨트롤러에서 쉽게 사용하기 위해 c:forEach를 사용 
					title.push("${recmndResn.recmndResn}"); // 제목을 title 배열에 넣어준다.
					sales.push("${recmndResn.recmndResnCont}"); // 각 합계를 sales 배열에 넣어준다.
					bear+= 1; // 반복문 조건문의 사이즈로 사용하기 위해 반복이 될 때 마다 +1을 해줌.
				</c:forEach>
				
				data.addColumn('string', '제목'); // DataTable 객체에서 컬럼 값을 정의 해준다.Column은 그래프가 사용 될 데이터 순서를 지정해준다.(데이터 형식과 명칭을 정해줌.)
				data.addColumn('number', '횟수');
				for(var i=0; i<bear; i++){		
					data.addRows([ // addRows는 그래프 통계 내야하는 데이터 값들을 넣어줘야 한다. 유동적으로 바뀌기 때문에 반복문을 사용한다.
						[title[i], parseInt(sales[i])], // 이중 배열을 사용.
					]);
				}
				var options = { // 옵션은 그래프에서 통계 이외의 옵션들을 제공한다. 종류는 엄청 많다.(다 설명하기 어려우므로 구글링 해보시길...)
					title: '고객 추천 사유',
					hAxis: { // 가로축에 대한 옵션 사용
						title: '종류',
						format: 'h:mm a', //번호 형식
					},
					vAxis: { // 세로축에 대한 옵션 사용
						title: '횟수'
					}
				};
				
				var chart = new google.visualization.PieChart(document.getElementById('chart_div')); // div id에 구글 통계들을 보내준다.
				chart.draw(data, options); // Google 시각화 및 차트 라이브러리를로드
			}
		 </script>
		 
			<div id="chart_div" style="width:1000px; height:450px"></div>
			<br>
			<input type="button" value="확인" onclick="javascript:popupClose()">
		</div>
	</body>
</html>