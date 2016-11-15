<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 이달의 대장남 화면을 보여주는 JSP입니다.
* 
* history :
*        시상일, 1.0, 2016/09/08 – 초기 작성
* author : 시상일
* version : 1.0, 2016/09/08  - 초기 작성
* see : 이달의 대장남화면
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
		
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>   
		   
		<style>
			table.ui-datepicker-calendar { display:none; }
		</style>
	</head>
	<body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
		<div class="container">
		
			<div class="container">
				<h3>이달의 대장남</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
					<h5>
						<img src="/resources/image/common/home.png"> > 대신 장봐주는 남자 > 
						<a href="/DJN.do" style="text-decoration:none">
						<strong>이달의 대장남</strong>
						</a>
					</h5>
				</div>
			</div>
			
			<form name="abc" method="post"  class="form-inline text-center">
				<div class="container form-group">
					<input type="text" class="form-control" id="today" size="7" maxlength="7" value="${tYearMonth}" />
					<input type="hidden" id="selectMon" name="selectMon" value="">
					<input type="submit" class="form-control btn btn-default" value="확인" onclick="monDJN()">
					
					<table class="table table-bordered" style="width:500px; margin-right:auto; margin-left:auto;">
						<tr>
							<td colspan="2" >
								<img src="${djnVo.empImg}" style="height: 450px;"/>
							</td>
						</tr>
						<tr>
							<td class="active text-center">
								<b>이름</b>
							</td>
							<td>
								<b><c:out value="${djnVo.empNme}"/></b>
							</td>
						</tr>
						<tr>
							<td class="active text-center">
								<b>직책/업무</b>
							</td>
							<td>
								<c:out  value="${djnVo.empDuty}"/>/<c:out  value="${djnVo.empTask}"/>
							</td>
						</tr>
						<tr>
							<td class="active text-center">
								<b>총포인트</b>
							</td>
							<td>
								${djnVo.totPoint}
							</td>
						</tr>
					</table>
				</div>
			</form>
			
			<div class="container">   
				<table class="table table-bordered">
					<colgroup>
						<col class="col-md-6 col-sm-6"/>                
						<col class="col-md-6 col-sm-6"/>          
					</colgroup>
					<tr>
						<td>
							<div id="barchart_values">
							</div>
						</td>
						<td>
							<div id="chart_div">
							</div>
						</td>   
					</tr>
				</table>
			</div>   
			<div class="container text-left">
				<h5>대장남 포인트 선정기준</h5>
			</div>
			
			<form method="post" name="djnPointPerct">
				<div class="container text-center form-group">
					<table class="table table-bordered">
						<tr>
							<th class="active text-center">
								고객칭찬
							</th>
							<td>
								<c:out value="${djnPointPerct.custmrPrais}"/>% 
							</td>
							<th class="active text-center">
							 	 고객추천
							</th>
							<td>
								<c:out value="${djnPointPerct.custmrRecmnd}"/>% 
							</td>
							<th class="active text-center">
							 	 배달횟수
							</th>
							<td>
								<c:out value="${djnPointPerct.deliCont}"/>%
							</td>
							<th class="active text-center">
							 	직원추천
							</th>
							<td>
								<c:out value="${djnPointPerct.empRecmnd}"/>% 
							</td>
						</tr>
					</table>
					<div class="text-center">
					<p>- 1%의 비율 당 1점의 점수를 부여</p>
					</div>
				</div>
			</form>
		</div>
		<script>
			//월별 대장남 조회
			function monDJN(){
				var today = $("#today").val();
				document.abc.selectMon.value = today;
				document.abc.action = "/monDJN.do";
				document.abc.submit();
			};
			   
			$(document).ready(function () {
				$.datepicker.regional['ko'] = {
					closeText: '닫기',
					prevText: '이전달',
					nextText: '다음달',
					currentText: '오늘',
					monthNames: ['1월(JAN)','2월(FEB)','3월(MAR)','4월(APR)','5월(MAY)','6월(JUN)',
					'7월(JUL)','8월(AUG)','9월(SEP)','10월(OCT)','11월(NOV)','12월(DEC)'],
					monthNamesShort: ['1월','2월','3월','4월','5월','6월',
					'7월','8월','9월','10월','11월','12월'],
					dayNames: ['일','월','화','수','목','금','토'],
					dayNamesShort: ['일','월','화','수','목','금','토'],
					dayNamesMin: ['일','월','화','수','목','금','토'],
					weekHeader: 'Wk',
					dateFormat: 'yy-mm-dd',
					firstDay: 0,
					isRTL: false,
					showMonthAfterYear: true,
					yearSuffix: '',
					changeMonth: true,
					changeYear: true,
					showButtonPanel: true,
					yearRange: 'c-99:c+99',
				};
				$.datepicker.setDefaults($.datepicker.regional['ko']);
				
				var datepicker_default = {
					openText : true,
					currentText: "이번달",
					changeMonth: true,
					changeYear: true,
					showButtonPanel: true,
					yearRange: 'c-99:c+99',
					showOtherMonths: true,
					selectOtherMonths: true
				}
				
				datepicker_default.closeText = "선택";
				datepicker_default.dateFormat = "yy-mm";
				datepicker_default.onClose = function (dateText, inst) {
					var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
					var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
					$(this).datepicker( "option", "defaultDate", new Date(year, month, 1) );
					$(this).datepicker('setDate', new Date(year, month, 1));
				}
				
				datepicker_default.beforeShow = function () {
					var selectDate = $("#today").val().split("-");
					var year = Number(selectDate[0]);
					var month = Number(selectDate[1]) - 1;
					$(this).datepicker( "option", "defaultDate", new Date(year, month, 1) );
				}
				$("#today").datepicker(datepicker_default);
			});
			
			/*구글차트1 시작  */
			google.charts.setOnLoadCallback(drawMultSeries); //구글 차트에 옵션과 데이터 값이 있는 메서드를 라이브러리를 통해 그래프를 통계 내줌.
			   
			function drawMultSeries() {
				var data = new google.visualization.DataTable(); //google.visualization.DataTable() 객체 생성
				var title = new Array(); // 정호는 데이터베이스의 데이터인 제목을 배열을 사용하기 위해 title을 배열 형태로 객체 생성 
				var sales = new Array(); // 정호는 데이터베이스의 데이터인 합계를 배열을 사용하기 위해 sales을 배열 형태로 객체 생성
				var bear = 0; // 반복문에서 조건문의 사이즈로 사용하기 위해 변수 만들어 줌.
				      
				<c:forEach var="djnRecmndResn" items="${djnRecmndResn}" varStatus="status"> // 정호는 컨트롤러에서 쉽게 사용하기 위해 c:forEach를 사용 
					title.push("${djnRecmndResn.recmndResn}"); // 제목을 title 배열에 넣어준다.
					sales.push("${djnRecmndResn.recmndResnCont}"); // 각 합계를 sales 배열에 넣어준다.
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
					height: 400,
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
			/*구글차트1 끝  */
			
			/*구글차트2 시작  */
			google.charts.load("current", {packages:["corechart"]});
			google.charts.setOnLoadCallback(drawChart);
			function drawChart() {
				var empRecmndPoint =${djnVo.empRecmndPoint};
				var custmrRecmndPoint = ${djnVo.custmrRecmndPoint};
				var custmrPraisPoint = ${djnVo.custmrPraisPoint};
				var deliContPoint = ${djnVo.deliContPoint};
				if(empRecmndPoint==null || custmrRecmndPoint==null || custmrPraisPoint==null || deliContPoint==null){
					var empRecmndPoint =0;
					var custmrRecmndPoint = 0;
					var custmrPraisPoint = 0;
					var deliContPoint = 0;
					var data = google.visualization.arrayToDataTable([
       					["Element", "포인트", { role: "style" } ],
       					["직원추천", empRecmndPoint, "#b87333"],
       					["고객추천", custmrRecmndPoint, "silver"],
       					["고객칭찬", custmrPraisPoint, "gold"],
       					["배달횟수", deliContPoint, "color: #e5e4e2"]
       					]);
       					
       					var view = new google.visualization.DataView(data);
       					view.setColumns([0, 1,
       					{ calc: "stringify",
       					sourceColumn: 1,
       					type: "string",
       					role: "annotation" },
       					2]);
       					
       					var options = {
       						title: "이달의 대장남 포인트",
       						height: 400,
       					};
       					var chart = new google.visualization.BarChart(document.getElementById("barchart_values"));
       					chart.draw(view, options);
				}
				else{
					var data = google.visualization.arrayToDataTable([
	 					["Element", "포인트", { role: "style" } ],
	 					["직원추천", empRecmndPoint, "#b87333"],
	 					["고객추천", custmrRecmndPoint, "silver"],
	 					["고객칭찬", custmrPraisPoint, "gold"],
	 					["배달횟수", deliContPoint, "color: #e5e4e2"]
	 					]);
	 					
	 					var view = new google.visualization.DataView(data);
	 					view.setColumns([0, 1,
	 					{ calc: "stringify",
	 					sourceColumn: 1,
	 					type: "string",
	 					role: "annotation" },
	 					2]);
	 					
	 					var options = {
	 						title: "이달의 대장남 포인트",
	 						height: 400,
	 					};
	 					var chart = new google.visualization.BarChart(document.getElementById("barchart_values"));
	 					chart.draw(view, options);
					}
			}
			/*구글차트2 끝  */
		</script>
	</body>
</html>      