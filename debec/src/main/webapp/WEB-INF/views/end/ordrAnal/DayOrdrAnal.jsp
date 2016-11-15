<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 
* 일별 주문 화면을 보여주는 JSP입니다.
* 
* history :
*        시상일, 1.0, 2016/08/24 – 초기 작성
* author : 시상일
* version : 1.0, 2016/08/24  - 초기 작성
* see : 일별 주문 화면
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
		
		<!-- 카테고리 선택 -->
		<script src="resources/js/catgrChoice.js?ver=1"></script> 
		
		<!-- 구글 차트 -->
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script> <!-- 가장 중요한 구글 경로 설정 -->
		
		<!-- datepicker 위젯 -->
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
	</head>
	<body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>	
		
			<form name="xlsDownFrm">	
				<input type="hidden" name="btnDatSech" value="${SalsValueObject.btnDatSech}"> <!-- 선택되었던 버튼 일자 -->
			</form>
			
			
			<div class="container">
				<h3>일별 주문 분석</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
					<h5>
						<img src="/resources/image/common/home.png"> > 주문 분석 > 
						<a href="/DayOrdrAnal.do" style="text-decoration:none">
							<strong>일별 주문 분석</strong>
						</a>
					</h5>
				</div>
			</div>
		
			<form name="ordrSechFrm" class="form-inline">				
				<input type="hidden" name="btnDatSech" value="${SalsValueObject.btnDatSech}"> <!-- 버튼 일자 선택-->
				<div class="container">
					<table class="table table-bordered">
						<tr>
							<td class="active text-center">
								<b>기간</b>
							</td>
							<td>
								<input type="button" class="form-control btn btn-default" value="3일" onclick="javascirpt:DayOrdrAnal('3day')">
								<input type="button" class="form-control btn btn-default" value="7일" onclick="javascirpt:DayOrdrAnal('7day')">
								<input type="button" class="form-control btn btn-default" value="14일" onclick="javascirpt:DayOrdrAnal('14day')">
								<input type="text" class="form-control" id="datepicker1" name="startDatSech" value="${SalsValueObject.startDatSech}"> ~
								<input type="text" class="form-control" id="datepicker2" name="endDatSech" value="${SalsValueObject.endDatSech}"> 					
							</td>
						</tr>
					</table>
				</div>
			</form>
			
			<div class="container text-center">
				<input type="button" class="btn btn-default" value="검색" onclick="javascript:ordrSech()">
			</div>
			
			
			<!-- 그래프 div -->
			<div id="chart_div" style="height: 500px;">
			</div>		
				
			<div class="container text-right">
				<input type="button" class="btn btn-default" value="엑셀 다운로드" onclick="javascript:xlsDownload();">
			</div>
								
			<form name="ordrFrm">								
				<div class="container">	
					<table class="table table-bordered" id="tblMain">
						<tr class="active">
							<td class="text-center">
								<b>일자</b>
							</td>
							<td class="text-center">
								<b>전화 주문수</b>
							</td>
							<td class="text-center">
								<b>전화 판매액</b>
							</td>
							<td class="text-center">
								<b>앱 주문수</b>
							</td>
							<td class="text-center">
								<b>앱 판매액</b>
							</td>
						</tr>
						<c:choose>
							<c:when test="${fn:length(ordrList) == 0}">
								<tr class="text-center">
									<td colspan="5">
										<h4>등록된 주문 현황이 없습니다</h4>
									</td>
								</tr>
							</c:when>
							<c:when test="${fn:length(ordrList) != 0}">
								<c:forEach items="${ordrList}" var="ordrList" varStatus="status">	
									<tr onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
										<td class="text-center">${ordrList.salsDay}</td>
										<td class="text-center">${ordrList.calOrdrCont}</td>
										<td class="text-center">${ordrList.calSalsPric}</td>
										<td class="text-center">${ordrList.appOrdrCont}</td>
										<td class="text-center">${ordrList.appSalsPric}</td>	
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>			
					</table>
				</div>	
			</form>
		<script>
			function changeTrColor(trObj, oldColor, newColor) {
				trObj.style.backgroundColor = newColor;
				trObj.onmouseout = function(){
					trObj.style.backgroundColor = oldColor;
				}
			};
			
			// 날짜 선택 위젯, import 밑에 나와야됨
			$.datepicker.setDefaults({
		        dateFormat: 'yy-mm-dd',
		        prevText: '이전 달',
		        nextText: '다음 달',
		        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
		        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
		        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		        showMonthAfterYear: true,
		        yearSuffix: '년'
		    });
		
		    $(function() {
		        $("#datepicker1").datepicker();
		        $("#datepicker2").datepicker();
		    }); 
		    
			google.charts.load('current', {packages: ['corechart', 'bar']}); //corechart라고 되어 있는 부분을 사용하고자 하는 차트의 이름으로 바꾸면 된다. corechart는 기본적인 차트(pie, bar, column)을 사용하기 위한 이름이다. 
			google.charts.setOnLoadCallback(drawMultSeries); //구글 차트에 옵션과 데이터 값이 있는 메서드를 라이브러리를 통해 그래프를 통계 내줌.
			
			function drawMultSeries() {
				var data = new google.visualization.DataTable(); //google.visualization.DataTable() 객체 생성
				var salsDay = new Array(); // 일자
				var calOrdrCont = new Array(); // 전화주문 수
				var appOrdrCont = new Array(); // 앱 주문수
				var bear = 0; // 반복문에서 조건문의 사이즈로 사용하기 위해 변수 만들어 줌.
				
				<c:forEach var="ordrList" items="${ordrList}" varStatus="status"> // 컨트롤러에서 쉽게 사용하기 위해 c:forEach를 사용 
					salsDay.push("${ordrList.salsDay}"); // 제목을 title 배열에 넣어준다.
					calOrdrCont.push("${ordrList.calOrdrCont}"); // 각 합계를 sales 배열에 넣어준다.
					appOrdrCont.push("${ordrList.appOrdrCont}"); 
					bear+= 1; // 반복문 조건문의 사이즈로 사용하기 위해 반복이 될 때 마다 +1을 해줌.
				</c:forEach>
				
		          data.addColumn('string', '월'); // DataTable 객체에서 컬럼 값을 정의 해준다.Column은 그래프가 사용 될 데이터 순서를 지정해준다.(데이터 형식과 명칭을 정해줌.)
		          data.addColumn('number', '전화 주문');
		          data.addColumn('number', '앱 주문');
				
				for(var i=0; i<bear; i++){      
					data.addRows([ // addRows는 그래프 통계 내야하는 데이터 값들을 넣어줘야 한다. 유동적으로 바뀌기 때문에 반복문을 사용한다.
						[salsDay[i], parseInt(calOrdrCont[i]), parseInt(appOrdrCont[i])], // 이중 배열을 사용.
					]);
				}
				var options = { // 옵션은 그래프에서 통계 이외의 옵션들을 제공한다. 종류는 엄청 많다.(다 설명하기 어려우므로 구글링 해보시길...)
					title: '일별 주문 분석',
					hAxis: { // 가로축에 대한 옵션 사용
					title: '일자',
					format: 'h:mm a', //번호 형식
					},
					vAxis: { // 세로축에 대한 옵션 사용
					title: '주문수'
					}
				};
				
				var chart = new google.visualization.LineChart(document.getElementById('chart_div')); // div id에 구글 통계들을 보내준다.
				chart.draw(data, options); // Google 시각화 및 차트 라이브러리를로드
			};
	
			// 엑셀 다운로드
			function xlsDownload(){
				document.xlsDownFrm.action = "/DayOrdrAnalXls.do";
				document.xlsDownFrm.submit();
			};
			
			// 날짜 버튼눌렀을 때 (1,3,7,14일)
	   		function DayOrdrAnal(day) {
	   			var url = "/DayOrdrAnal.do?btnDatSech="+day;
				$(location).attr('href',url);	
	   		};
	   		
	   		// 검색 버튼
	   		function ordrSech(){	
	   			// 날짜 유효성검사
	   			var today = new Date();
	            var year = today.getFullYear();
	            var month = today.getMonth() + 1;
	            var day = today.getDate();      
	            var startDate = $( "input[name='startDatSech']" ).val(); // 시작날짜
	            var startDateArr = startDate.split('-');                   
	            var endDate = $( "input[name='endDatSech']" ).val(); // 종료날짜
	            var endDateArr = endDate.split('-');                 
	            var present = new Date(year, month, day);//오늘 날짜 
	            var startDateCompare = new Date(startDateArr[0], startDateArr[1], startDateArr[2]); //시작 날짜
	            var endDateCompare = new Date(endDateArr[0], endDateArr[1], endDateArr[2]); //종료 날짜
	            var day = 1000*60*60*24;                 
	           	if(startDate=="" && endDate == ""){           		
	           	}
	           	else{
	           		 if(startDate=="" || endDate == ""){
	                		alert("시작일과 종료일을 입력해주세요.");
	    	                return;	
	   	             }
	   	             else if(parseInt((endDateCompare-startDateCompare)/day) > 30){
	   	                 alert("일별 주문에서는 최대 30일까지 분석이 가능합니다.");
	   	                 return;
	   	             }
	
	   	             else if(startDateCompare.getTime() > endDateCompare.getTime()){
	                        alert("시작일이 종료일보다 늦을 수 없습니다.");
	                        return;
	                   }
	           	}   
	   			document.ordrSechFrm.action ="/DayOrdrAnal.do";
				document.ordrSechFrm.submit(); 			
	   		};
	    </script>	
	</body>
</html>