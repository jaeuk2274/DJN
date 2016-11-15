<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 
* Today 분석내용을 보여주는 JSP입니다.
* 
* history :
*        김대현, 1.0, 2016/08/24 – 초기 작성
* author : 김대현
* version : 1.0, 2016/08/24  - 초기 작성
* see : 관련된 모듈을 기술한다.
// -->
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
		
		<!-- 구글 차트 -->
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script> <!-- 가장 중요한 구글 경로 설정 -->
		
	</head>
	<body>
  		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>	
		
		<div class="container">
			
			<div class="container">
				<h3>Today 리포트</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
					<h5>
						<img src="/resources/image/common/home.png"> > 마감 관리 > 
						<a href="/TodyAnalList.do" style="text-decoration:none">
							<strong>Today 리포트</strong>
						</a>
					</h5>
				</div>
			</div>
			

			<div class="container text-right">
				<input type="button" class="btn btn-default" value="매출현황 등록" style="margin-right: 15px;" onclick="javascript:salsPresntInsertPopup()">
			</div>

			<div class="container">
				<div class="pull-left col-md-6 col-sm-6">
					<!-- Today 실적 -->
					<table class="table table-bordered" style="height: 238px;">
						<tr class="active">
							<td colspan="4">
								<div style="display:inline-block">
									<b>Today 실적</b> 
								</div>
								<div class="pull-right" style="display:inline-block">
									<h5>${selectTodyResult.tody} ${selectTodyResult.day}</h5>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4" >
								<div class="text-left">
									최종 업데이트 일시 : ${selectTodyResult.now}
								</div>
								<div class="text-right">
									<h2> <b><fmt:formatNumber value="${selectTodyResult.salsPric}" type="number"/>원</b> </h2>
								</div>
							</td>
						</tr>
						<tr>
							<td class="active text-center">
								<b>전화주문건수</b>
							</td>
							<td class="text-center">
								<fmt:formatNumber value="${telOrdrCount}" type="number"/>
							</td>
							<td class="active text-center">
								<b>어플주문건수</b>
							</td>
							<td class="text-center">
								<fmt:formatNumber value="${appOrdrCount}" type="number"/>
							</td>
						</tr>
						<tr>
							<td class="active text-center">
								<b>주문취소건수</b>
							</td>
							<td class="text-center">
								<fmt:formatNumber value="${ordrCancelCount}" type="number"/>
							</td>
							<td class="active text-center">
								<b>교환/환불건수</b>
							</td>
							<td class="text-center">
								<c:if test="${exchngRefndCount!=null}">
									<fmt:formatNumber value="${exchngRefndCount}" type="number"/>
								</c:if>
								<c:if test="${exchngRefndCount==null}">
									0
								</c:if>
							</td>
						</tr>
					</table>
				</div>
				<div class="pull-left col-md-6 col-sm-6">
					<!-- 일일 매출 -->
					<div class="table-bordered" id="chart_div" style="height: 238px; max-height:100%">
					</div>
				</div>
			</div>
						
			<div class="container">
				<!-- 오늘 상품 판매량 순위 Top10 -->
				<div class="pull-left col-md-6 col-sm-6">
					<h4>오늘 상품 판매량 순위 Top 10</h4>
					<table class="table table-bordered">
						<tr class="active">
							<th class="text-center">No</th>
							<th class="text-center">상품명</th>
							<th class="text-center">개수</th>
							<th class="text-center">매출액</th>
						</tr>
						<c:choose>
							<c:when test="${fn:length(todySalesList) == 0}">
								<tr class="text-center">
									<td colspan="4">
										<h4>집계된 현황이 없습니다</h4>
									</td>
								</tr>
							</c:when>
							<c:when test="${fn:length(todySalesList) != 0}">
								<c:forEach items="${todySalesList}" var="todySales" varStatus="status">
									<tr onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
										<td class="text-center">${status.index+1}</td>
										<td class="text-center">${todySales.prodctNme}</td>
										<td class="text-center"><fmt:formatNumber value="${todySales.qunt}" type="number"/></td>
										<td class="text-center"><fmt:formatNumber value="${todySales.salsPric}" type="number"/></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
				</div>
				<!-- 급상승 판매상품 순위 -->
				<div class="pull-left col-md-6 col-sm-6">
					<h4>급상승 판매상품 순위</h4>
					<table class="table table-bordered">
						<tr class="active">
							<th class="text-center">No</th>
							<th class="text-center">상품명</th>
							<th class="text-center">개수</th>
							<th class="text-center">매출액</th>
						</tr>
						<c:choose>
							<c:when test="${fn:length(todySalesList) == 0}">
								<tr class="text-center">
									<td colspan="4">
										<h4>집계된 현황이 없습니다</h4>
									</td>
								</tr>
							</c:when>
							<c:when test="${fn:length(todySalesList) != 0}">
								<c:forEach items="${surgeSelProdctList}" var="surgeSelProdct" varStatus="status">
										<tr onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
											<td class="text-center">${status.index+1}</td>
											<td class="text-center">${surgeSelProdct.prodctNme}</td>
											<td class="text-center"><fmt:formatNumber value="${surgeSelProdct.todyOrdrAmont}" type="number"/></td>
											<td class="text-center"><fmt:formatNumber value="${surgeSelProdct.salsPric}" type="number"/></td>
										</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
				</div>
			</div>
		</div>
		<script>
			function changeTrColor(trObj, oldColor, newColor) {
				trObj.style.backgroundColor = newColor;
				trObj.onmouseout = function(){
					trObj.style.backgroundColor = oldColor;
				}
			};
			
			google.charts.load('current', {packages: ['corechart', 'bar']}); //corechart라고 되어 있는 부분을 사용하고자 하는 차트의 이름으로 바꾸면 된다. corechart는 기본적인 차트(pie, bar, column)을 사용하기 위한 이름이다. 
			google.charts.setOnLoadCallback(drawMultSeries); //구글 차트에 옵션과 데이터 값이 있는 메서드를 라이브러리를 통해 그래프를 통계 내줌.
			function drawMultSeries() {
				var data = new google.visualization.DataTable(); //google.visualization.DataTable() 객체 생성
				var salsTime = new Array(); // 시간
				var salsPric = new Array(); // 매출액
				var status = 0; // 반복문에서 조건문의 사이즈로 사용하기 위해 변수 만들어 줌.
				
				salsTime.push('12', '15', '18', '21'); // 제목을 title 배열에 넣어준다.	
				<c:forEach var="salsTime" items="${salsTime}" varStatus="status"> // 컨트롤러에서 쉽게 사용하기 위해 c:forEach를 사용 
					salsPric.push("${salsTime.salsPric}"); 
					status+= 1; // 반복문 조건문의 사이즈로 사용하기 위해 반복이 될 때 마다 +1을 해줌.
				</c:forEach>
				
				data.addColumn('string', '시간'); // DataTable 객체에서 컬럼 값을 정의 해준다.Column은 그래프가 사용 될 데이터 순서를 지정해준다.(데이터 형식과 명칭을 정해줌.)
				data.addColumn('number', '매출액');
				
				for(var i=0; i<status; i++){      
					data.addRows([ // addRows는 그래프 통계 내야하는 데이터 값들을 넣어줘야 한다. 유동적으로 바뀌기 때문에 반복문을 사용한다.
						[salsTime[i], parseInt(salsPric[i])], // 이중 배열을 사용.
					]);
				}
				var options = { // 옵션은 그래프에서 통계 이외의 옵션들을 제공한다. 종류는 엄청 많다.(다 설명하기 어려우므로 구글링 해보시길...)
					title: '일일 매출',
					titlePosition: 'out',
					hAxis: { // 가로축에 대한 옵션 사용
					title: '시간',
					format: 'h:mm a', //번호 형식
					},
					vAxis: { // 세로축에 대한 옵션 사용
					title: '매출액'
					},
				};
				
				var chart = new google.visualization.ColumnChart(document.getElementById('chart_div')); // div id에 구글 통계들을 보내준다.
				chart.draw(data, options); // Google 시각화 및 차트 라이브러리를로드
			};
			
			// 매출현황 등록 팝업
			function salsPresntInsertPopup(){
	// 			document.domain = "mydomain";
	            var popupUrl = "/SalsPresntCreateReadPopup.do";
	            var popupName= "vactnReadPopup";
	            var width = 430;
	            var height = 300;
	            var leftPosition = (screen.width/2) - (width/2);
	               leftPosition += window.screenLeft;//듀얼 모니터일때
	            var topPosition = (screen.height/2) - (height/2);
	            var win = window.open(popupUrl, popupName, "left="+leftPosition+", top="+topPosition+", toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, width="+width+", height="+height);          
	            if(win == null) {//만약 팝업 차단으로 팝업창이 나타나지 않을 경우
	               alert("팝업차단을 해제해주세요!");
	            }
	            else {
	               win.focus();    
	            }
			};

			// 엑셀파일 업로드
// 		      function excelUpload(){
// 		         if($('#excelFile').val() == ''){
// 		            alert('파일을 선택해주세요');
// 		         }
// 		         else{
// 		            opener.name = "parentPage"; // 부모창의 이름 설정
// 	                document.excelFrm.target = "parentPage"; // 타켓을 부모창으로 설정
// 	                document.excelFrm.action="/SalsPresntExcelUpload.do";
// 	                document.excelFrm.submit();
// 	                self.close();
// 		         }
		         
// 		      }
		</script>       
	</body>
</html>