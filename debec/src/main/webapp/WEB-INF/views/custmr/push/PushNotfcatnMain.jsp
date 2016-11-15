<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- 
* 푸시알림 메인을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        이정호, 1.0, 2016/08/23 – 초기 작성
* author : 이정호
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
		
	<script src="/resources/js/push-js/pushNotfcatnRecpnt.js"></script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script> <!-- 가장 중요한 구글 경로 설정 -->
</head>
<body>
	<div style="margin-bottom: 130px;">
		<c:import url="/TopFrame.do"/>
	</div>
		
	<div class="container">
		<div class="container">
			<h3>푸시 알림 메인</h3>
			<div style="display:inline-block; margin-top:1%; float:right">
			   <h5>
			     <img src="../../../../../resources/image/common/home.png"> > 푸시 알림 관리 > 
			     <a href="/pushNotfcatnMain.do" style="text-decoration:none"><strong>푸시 알림 메인</strong></a>
			   </h5>
			</div>
		</div>		
		
		<div class="form-group"></div>
		
		<div class="container">
			<table class="table table-bordered">
				<tr class="active">
					<th colspan="2" class="text-center">
						일간
					</th>
					<th colspan="2" class="text-center">
						주간
					</th>
					<th colspan="2" class="text-center">
						월간
					</th>
				</tr>
				<tr>
					<c:choose>
						<c:when test="${pushNotfcatnDayStatstcs.despRecrdSeq == null}">
							<td rowspan="3" class="text-center">총발송<br>0</td>
							<td class="text-center">성공 0 (<fmt:formatNumber value="0" pattern="0"/>%)</td>
						</c:when>
						<c:otherwise>
							<td rowspan="3" class="text-center">총발송<br>${pushNotfcatnDayStatstcs.despRecrdSeq}</td>
							<td class="text-center">성공 ${pushNotfcatnDayStatstcs.sucsCont} (<fmt:formatNumber value="${(pushNotfcatnDayStatstcs.sucsCont/(pushNotfcatnDayStatstcs.sucsCont + pushNotfcatnDayStatstcs.falCont))*100}" pattern="0"/>%)</td>					
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${pushNotfcatnWekStatstcs.despRecrdSeq == null}">
							<td rowspan="3" class="text-center">총발송<br>0</td>
							<td class="text-center">성공 0 (<fmt:formatNumber value="0" pattern="0"/>%)</td>
						</c:when>
						<c:otherwise>
							<td rowspan="3" class="text-center">총발송<br>${pushNotfcatnWekStatstcs.despRecrdSeq}</td>
							<td class="text-center">성공 ${pushNotfcatnWekStatstcs.sucsCont} (<fmt:formatNumber value="${(pushNotfcatnWekStatstcs.sucsCont/(pushNotfcatnWekStatstcs.sucsCont + pushNotfcatnWekStatstcs.falCont))*100}" pattern="0"/>%)</td>					
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${pushNotfcatnMonthStatstcs.despRecrdSeq == null}">
							<td rowspan="3" class="text-center">총발송<br>0</td>
							<td class="text-center">성공 0 (<fmt:formatNumber value="0" pattern="0"/>%)</td>
						</c:when>
						<c:otherwise>
							<td rowspan="3" class="text-center">총발송<br>${pushNotfcatnMonthStatstcs.despRecrdSeq}</td>
							<td class="text-center">성공 ${pushNotfcatnMonthStatstcs.sucsCont} (<fmt:formatNumber value="${(pushNotfcatnMonthStatstcs.sucsCont/(pushNotfcatnMonthStatstcs.sucsCont + pushNotfcatnMonthStatstcs.falCont))*100}" pattern="0"/>%)</td>					
						</c:otherwise>
					</c:choose>					
				</tr>
				<tr>
					<c:choose>
						<c:when test="${pushNotfcatnDayStatstcs.despRecrdSeq == null}">
							<td class="text-center">실패 0 (<fmt:formatNumber value="0" pattern="0"/>%)</td>
						</c:when>
						<c:otherwise>
							<td class="text-center">실패 ${pushNotfcatnDayStatstcs.falCont} (<fmt:formatNumber value="${(pushNotfcatnDayStatstcs.falCont/(pushNotfcatnDayStatstcs.sucsCont + pushNotfcatnDayStatstcs.falCont))*100}" pattern="0"/>%)</td>					
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${pushNotfcatnWekStatstcs.despRecrdSeq == null}">
							<td class="text-center">실패 0 (<fmt:formatNumber value="0" pattern="0"/>%)</td>
						</c:when>
						<c:otherwise>
							<td class="text-center">실패 ${pushNotfcatnWekStatstcs.falCont} (<fmt:formatNumber value="${(pushNotfcatnWekStatstcs.falCont/(pushNotfcatnWekStatstcs.sucsCont + pushNotfcatnWekStatstcs.falCont))*100}" pattern="0"/>%)</td>					
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${pushNotfcatnMonthStatstcs.despRecrdSeq == null}">
							<td class="text-center">실패 0 (<fmt:formatNumber value="0" pattern="0"/>%)</td>
						</c:when>
						<c:otherwise>
							<td class="text-center">실패 ${pushNotfcatnMonthStatstcs.falCont} (<fmt:formatNumber value="${(pushNotfcatnMonthStatstcs.falCont/(pushNotfcatnMonthStatstcs.sucsCont + pushNotfcatnMonthStatstcs.falCont))*100}" pattern="0"/>%)</td>					
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<c:choose>
						<c:when test="${pushNotfcatnDayStatstcs.despRecrdSeq == null}">
							<td class="text-center">오픈 0 (<fmt:formatNumber value="0" pattern="0"/>%)</td>
						</c:when>
						<c:otherwise>
							<td class="text-center">오픈 ${pushNotfcatnDayStatstcs.openCont} (<fmt:formatNumber value="${(pushNotfcatnDayStatstcs.openCont/(pushNotfcatnDayStatstcs.sucsCont + pushNotfcatnDayStatstcs.falCont))*100}" pattern="0"/>%)</td>					
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${pushNotfcatnWekStatstcs.despRecrdSeq == null}">
							<td class="text-center">오픈 0 (<fmt:formatNumber value="0" pattern="0"/>%)</td>
						</c:when>
						<c:otherwise>
							<td class="text-center">오픈 ${pushNotfcatnWekStatstcs.openCont} (<fmt:formatNumber value="${(pushNotfcatnWekStatstcs.openCont/(pushNotfcatnWekStatstcs.sucsCont + pushNotfcatnWekStatstcs.falCont))*100}" pattern="0"/>%)</td>					
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${pushNotfcatnMonthStatstcs.despRecrdSeq == null}">
							<td class="text-center">오픈 0 (<fmt:formatNumber value="0" pattern="0"/>%)</td>
						</c:when>
						<c:otherwise>
							<td class="text-center">오픈 ${pushNotfcatnMonthStatstcs.openCont} (<fmt:formatNumber value="${(pushNotfcatnMonthStatstcs.openCont/(pushNotfcatnMonthStatstcs.sucsCont + pushNotfcatnMonthStatstcs.falCont))*100}" pattern="0"/>%)</td>					
						</c:otherwise>
					</c:choose>
				</tr>
			</table>
		</div>
		
		<div class="container"  style="margin-top:10%">
			<form name="graphFrm" method="POST">
				<input type="hidden" id="dateOption" name="dateOption" value="${pushVo.dateOption}">
			</form>
			<h4 style="text-align:center"><strong>푸시 알림 내역</strong></h4>		
			<h5 style="text-align:right">
				<c:choose>
					<c:when test="${pushVo.dateOption == 1}">
						<select name="pushNotfcatnStatstcsGraph" id="pushNotfcatnStatstcsGraph" onchange="javascript:pushNotfcatnStatstcsGraph()">
							<option value="1" selected>일일</option>
							<option value="7">주중</option>
							<option value="30">한달</option>			
						</select>
					</c:when>
					<c:when test="${pushVo.dateOption == 7}">
						<select name="pushNotfcatnStatstcsGraph" id="pushNotfcatnStatstcsGraph" onchange="javascript:pushNotfcatnStatstcsGraph()">
							<option value="1">일일</option>
							<option value="7" selected>주중</option>
							<option value="30">한달</option>			
						</select>
					</c:when>		
					<c:otherwise>
						<select name="pushNotfcatnStatstcsGraph" id="pushNotfcatnStatstcsGraph" onchange="javascript:pushNotfcatnStatstcsGraph()">
							<option value="1">일일</option>
							<option value="7">주중</option>
							<option value="30" selected>한달</option>			
						</select>
					</c:otherwise>
				</c:choose>
			</h5>		
			
			<div id="chart_div" style="width:100%; height:450px; margin:0 auto">
			</div>
<!-- 			<div style="width: 1000px;"> -->
<!-- 				<table style="width: 100%" class="table"> -->
<!-- 					<tr> -->
<!-- 						<th>등록일</th> -->
<!-- 						<th>보낸 횟수</th> -->
<!-- 						<th>성공한 횟수</th> -->
<!-- 						<th>열어본 수</th> -->
<!-- 						<th>실패한 수</th> -->
<!-- 					</tr> -->
<!-- 					<tr align="center"> -->
<!-- 						<td> -->
<!-- 							전체 -->
<!-- 						</td> -->
<!-- 						<td> -->
<%-- 							${pushNotfcatnStatstcsGraphSum.despRecrdSeq} --%>
<!-- 						</td> -->
<!-- 						<td> -->
<%-- 							${pushNotfcatnStatstcsGraphSum.sucsCont} --%>
<%-- 							(<fmt:formatNumber value="${(pushNotfcatnStatstcsGraphSum.sucsCont/pushNotfcatnStatstcsGraphSum.despRecrdSeq)*100}" pattern="0"/>%) --%>
<!-- 						</td> -->
<!-- 						<td> -->
<%-- 							${pushNotfcatnStatstcsGraphSum.openCont} --%>
<%-- 							(<fmt:formatNumber value="${(pushNotfcatnStatstcsGraphSum.openCont/pushNotfcatnStatstcsGraphSum.despRecrdSeq)*100}" pattern="0"/>%) --%>
<!-- 						</td> -->
<!-- 						<td> -->
<%-- 							${pushNotfcatnStatstcsGraphSum.falCont} --%>
<%-- 							(<fmt:formatNumber value="${(pushNotfcatnStatstcsGraphSum.falCont/pushNotfcatnStatstcsGraphSum.despRecrdSeq)*100}" pattern="0"/>%) --%>
<!-- 						</td> -->
<!-- 					</tr> -->
<%-- 					<c:forEach var="pushNotfcatnStatstcsGraph" items="${pushNotfcatnStatstcsGraph}" varStatus="status"> --%>
<!-- 					<tr align="center"> -->
<!-- 						<td> -->
<%-- 							${pushNotfcatnStatstcsGraph.regstrtnDat} --%>
<!-- 						</td> -->
<!-- 						<td> -->
<%-- 							${pushNotfcatnStatstcsGraph.despRecrdSeq} --%>
<!-- 						</td> -->
<!-- 						<td> -->
<%-- 							${pushNotfcatnStatstcsGraph.sucsCont} --%>
<%-- 							(<fmt:formatNumber value="${(pushNotfcatnStatstcsGraph.sucsCont/pushNotfcatnStatstcsGraph.despRecrdSeq)*100}" pattern="0"/>%) --%>
<!-- 						</td> -->
<!-- 						<td> -->
<%-- 							${pushNotfcatnStatstcsGraph.openCont} --%>
<%-- 							(<fmt:formatNumber value="${(pushNotfcatnStatstcsGraph.openCont/pushNotfcatnStatstcsGraph.despRecrdSeq)*100}" pattern="0"/>%) --%>
<!-- 						</td> -->
<!-- 						<td> -->
<%-- 							${pushNotfcatnStatstcsGraph.falCont} --%>
<%-- 							(<fmt:formatNumber value="${(pushNotfcatnStatstcsGraph.falCont/pushNotfcatnStatstcsGraph.despRecrdSeq)*100}" pattern="0"/>%) --%>
<!-- 						</td> -->
<!-- 					</tr> -->
<%-- 					</c:forEach> --%>
<!-- 				</table> -->
<!-- 			</div> -->
		</div>			
	</div>
<script>
google.charts.load('current', {packages: ['corechart', 'bar']}); //corechart라고 되어 있는 부분을 사용하고자 하는 차트의 이름으로 바꾸면 된다. corechart는 기본적인 차트(pie, bar, column)을 사용하기 위한 이름이다. 
google.charts.setOnLoadCallback(drawMultSeries); //구글 차트에 옵션과 데이터 값이 있는 메서드를 라이브러리를 통해 그래프를 통계 내줌.

function drawMultSeries() {
	var data = new google.visualization.DataTable(); //google.visualization.DataTable() 객체 생성
	var pushNotfcatnStatstcsGraphDate = new Array(); // 정호는 데이터베이스의 데이터인 제목을 배열을 사용하기 위해 title을 배열 형태로 객체 생성 
	var pushNotfcatnSucsStatstcsGraph = new Array(); // 정호는 데이터베이스의 데이터인 합계를 배열을 사용하기 위해 sales을 배열 형태로 객체 생성
	var pushNotfcatnFalStatstcsGraph = new Array();
	var pushNotfcatnOpenStatstcsGraph = new Array();
	var plusOne = 0; // 반복문에서 조건문의 사이즈로 사용하기 위해 변수 만들어 줌.
	
	<c:forEach var="pushNotfcatnStatstcsGraph" items="${pushNotfcatnStatstcsGraph}" varStatus="status"> // 정호는 컨트롤러에서 쉽게 사용하기 위해 c:forEach를 사용 
		pushNotfcatnStatstcsGraphDate.push("${pushNotfcatnStatstcsGraph.pushDat}"); // 제목을 title 배열에 넣어준다.
		pushNotfcatnSucsStatstcsGraph.push("${pushNotfcatnStatstcsGraph.sucsCont}"); // 각 합계를 sales 배열에 넣어준다.
		pushNotfcatnFalStatstcsGraph.push("${pushNotfcatnStatstcsGraph.falCont}"); // 각 합계를 sales 배열에 넣어준다.
		pushNotfcatnOpenStatstcsGraph.push("${pushNotfcatnStatstcsGraph.openCont}"); // 각 합계를 sales 배열에 넣어준다.
		plusOne += 1; // 반복문 조건문의 사이즈로 사용하기 위해 반복이 될 때 마다 +1을 해줌.
	</c:forEach>
	
	data.addColumn('string', '날짜'); // DataTable 객체에서 컬럼 값을 정의 해준다.Column은 그래프가 사용 될 데이터 순서를 지정해준다.(데이터 형식과 명칭을 정해줌.)
	data.addColumn('number', '성공한 횟수');
	data.addColumn('number', '실패한 힛수');
	data.addColumn('number', '열어본 횟수');
	for(var i=0; i<plusOne; i++){		
		data.addRows([ // addRows는 그래프 통계 내야하는 데이터 값들을 넣어줘야 한다. 유동적으로 바뀌기 때문에 반복문을 사용한다.
			[pushNotfcatnStatstcsGraphDate[i]
			, parseInt(pushNotfcatnSucsStatstcsGraph[i])
			, parseInt(pushNotfcatnFalStatstcsGraph[i])
			, parseInt(pushNotfcatnOpenStatstcsGraph[i])], // 이중 배열을 사용.
		]);
	};
	var options = { // 옵션은 그래프에서 통계 이외의 옵션들을 제공한다. 종류는 엄청 많다.(다 설명하기 어려우므로 구글링 해보시길...)
		titlePosition: 'none',
		hAxis: { // 가로축에 대한 옵션 사용
			title: '날짜',
			format: 'h:mm a', //번호 형식
		},
		vAxis: { // 세로축에 대한 옵션 사용
			title: '횟수',
			format: 'decimal'
		}
	};
	
	var chart = new google.visualization.ColumnChart(document.getElementById('chart_div')); // div id에 구글 통계들을 보내준다.
	chart.draw(data, options); // Google 시각화 및 차트 라이브러리를로드
	}
	;
	function pushNotfcatnStatstcsGraph(){
		document.getElementById("dateOption").value = document.getElementById("pushNotfcatnStatstcsGraph").value;
// 		document.getElementById("dateOption").value = document.getElementById("pushNotfcatnStatstcsGraph").value;
		document.graphFrm.action="/pushNotfcatnMain.do";
		document.graphFrm.submit();
		
	};
</script> 
</body>
</html>