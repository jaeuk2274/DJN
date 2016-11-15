<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 
* 교환환불순위 내용을 보여주는 JSP입니다.
* 
* history :
*        김대현, 1.0, 2016/10/01 – 초기 작성
* author : 김대현
* version : 1.0, 2016/10/01  - 초기 작성
* see : 관련된 모듈을 기술한다.
// -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>교환/환불 순위</title>
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
	
	<!--     구글차트 -->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      // Load Charts and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Draw the pie chart for Sarah's pizza when Charts is loaded.
      google.charts.setOnLoadCallback(drawSarahChart);

      // Draw the pie chart for the Anthony's pizza when Charts is loaded.
      google.charts.setOnLoadCallback(drawAnthonyChart);

      // Callback that draws the pie chart for Sarah's pizza.
      function drawSarahChart() {
        // Create the data table for Sarah's pizza.
        var data = new google.visualization.DataTable();
        var prodctNme = new Array(); // 상품명  
      	var refndQunt = new Array(); // 환불수량
        var status = 0; // 반복문에서 조건문의 사이즈로 사용하기 위해 변수 만들어 줌.
        <c:forEach var="refndQunt" items="${refndQuntList}" varStatus="status"> // 컨트롤러에서 쉽게 사용하기 위해 c:forEach를 사용 
			prodctNme.push("${refndQunt.prodctNme}"); // 제목을 title 배열에 넣어준다.
			refndQunt.push("${refndQunt.qunt}"); // 각 합계를 sales 배열에 넣어준다.
			status+= 1; // 반복문 조건문의 사이즈로 사용하기 위해 반복이 될 때 마다 +1을 해줌.
		</c:forEach>
		data.addColumn('string', '상품명'); // DataTable 객체에서 컬럼 값을 정의 해준다.Column은 그래프가 사용 될 데이터 순서를 지정해준다.(데이터 형식과 명칭을 정해줌.)
        data.addColumn('number', '환불수량');
        for(var i=0; i<status; i++){      
            data.addRows([ // addRows는 그래프 통계 내야하는 데이터 값들을 넣어줘야 한다. 유동적으로 바뀌기 때문에 반복문을 사용한다.
               [prodctNme[i], parseInt(refndQunt[i])], // 이중 배열을 사용.
            ]);
         }
        var options = { // 옵션은 그래프에서 통계 이외의 옵션들을 제공한다. 종류는 엄청 많다.(다 설명하기 어려우므로 구글링 해보시길...)
			title: '교환/환불 TOP 10(환불수량)',
			hAxis: { // 가로축에 대한 옵션 사용
			   title: '상품명',
			   format: 'h:mm a', //번호 형식
			},
			vAxis: { // 세로축에 대한 옵션 사용
			   title: '환불수량'
			}
	    };
        // Instantiate and draw the chart for Sarah's pizza.
        var chart = new google.visualization.PieChart(document.getElementById('refndQuntChart'));
        chart.draw(data, options);
      }

      // Callback that draws the pie chart for Anthony's pizza.
      function drawAnthonyChart() {

        // Create the data table for Anthony's pizza.
        var data = new google.visualization.DataTable();
        var prodctNme = new Array(); // 상품명  
	   	var refndRat = new Array(); // 판매수량
	    var status = 0; // 반복문에서 조건문의 사이즈로 사용하기 위해 변수 만들어 줌.
	    <c:forEach var="refndRat" items="${refndRatList}" varStatus="status"> // 컨트롤러에서 쉽게 사용하기 위해 c:forEach를 사용 
			prodctNme.push("${refndRat.prodctNme}"); // 제목을 title 배열에 넣어준다.
			refndRat.push("${refndRat.refndRat}"); // 각 합계를 sales 배열에 넣어준다.
			status+= 1; // 반복문 조건문의 사이즈로 사용하기 위해 반복이 될 때 마다 +1을 해줌.
		</c:forEach>
		data.addColumn('string', '상품명'); // DataTable 객체에서 컬럼 값을 정의 해준다.Column은 그래프가 사용 될 데이터 순서를 지정해준다.(데이터 형식과 명칭을 정해줌.)
	    data.addColumn('number', '환불비율');
	    for(var i=0; i<status; i++){      
			data.addRows([ // addRows는 그래프 통계 내야하는 데이터 값들을 넣어줘야 한다. 유동적으로 바뀌기 때문에 반복문을 사용한다.
			   [prodctNme[i], parseInt(refndRat[i])], // 이중 배열을 사용.
			]);
	    }

        // Set options for Anthony's pie chart.
        var options = { // 옵션은 그래프에서 통계 이외의 옵션들을 제공한다. 종류는 엄청 많다.(다 설명하기 어려우므로 구글링 해보시길...)
		   title: '교환/환불 TOP10(환불비율)',
		   hAxis: { // 가로축에 대한 옵션 사용
		      title: '상품명',
		      format: 'h:mm a', //번호 형식
		   },
		   vAxis: { // 세로축에 대한 옵션 사용
		      title: '환불비율'
		   }
		};

        // Instantiate and draw the chart for Anthony's pizza.
        var chart = new google.visualization.PieChart(document.getElementById('refndRatioChart'));
        chart.draw(data, options);
      }
  
	       
// 날짜 버튼눌렀을 때 (오늘,3,7,1개월,3개월,6개월)
   		function dayRefndSech(day) {
//    			alert(day);
//    			var url = "/ExchngRefndAnalList.do?btnDatSech="+day;
// 			$(location).attr('href',url);	

   			document.exchngRefndSechFrm.prodctNmeSech.value = document.exchngRefndFrm.prodctNmeSech.value;		// 상품명 검색
   			document.exchngRefndSechFrm.btnDatSech.value = day;		// 상품명 검색
   			document.exchngRefndSechFrm.action="/ExchngRefndAnalList.do"
   			document.exchngRefndSechFrm.submit();
   		}

// 검색
		function refndSech(){
			// 날짜 유효성검사
   			var today = new Date();
            var year = today.getFullYear();
            var month = today.getMonth() + 1;
            var day = today.getDate();      
            var startDate = $("#beginDatepicker").val(); // 시작날짜
            var startDateArr = startDate.split('-');   
            var endDate = $("#endDatepicker").val(); // 종료날짜
            var endDateArr = endDate.split('-');   
            var present = new Date(year, month, day);//오늘 날짜 
            var startDateCompare = new Date(startDateArr[0], startDateArr[1], startDateArr[2]); //시작 날짜
            var endDateCompare = new Date(endDateArr[0], endDateArr[1], endDateArr[2]); //종료 날짜

            var day = 1000*60*60*24;
            var month = day*30;
            var year = month*12;
            
//             alert(parseInt((endDateCompare-startDateCompare)/day) + "일");
            
           	if(startDate=="" && endDate == ""){           		
           	}
           	else{
           		 if(startDate=="" || endDate == ""){
					 alert("시작일과 종료일을 모두 입력해주세요.");
  	                 return;	
   	             }
   	             else if(parseInt((endDateCompare-startDateCompare)/month) > 6){
   	                 alert("교환/환불 분석은 최대 6개월까지 분석이 가능합니다.");
   	                 return;
   	             }
   	             else if(startDateCompare.getTime() > endDateCompare.getTime()){
                     alert("시작일이 종료일보다 늦을 수 없습니다.");
                     return;
                 }
           	}   
   			document.exchngRefndSechFrm.beginDatepicker.value = $("#beginDatepicker").val();	
   			document.exchngRefndSechFrm.endDatepicker.value = $("#endDatepicker").val();
   			document.exchngRefndSechFrm.prodctNmeSech.value = document.exchngRefndFrm.prodctNmeSech.value;		// 상품명 검색
   			document.exchngRefndSechFrm.action="/ExchngRefndAnalList.do"
   			document.exchngRefndSechFrm.submit();
		}

// 엑셀 다운로드
		function xlsDownload(){
			document.exchngRefndSechFrm.action = "/RefndXls.do";
			document.exchngRefndSechFrm.submit();
		};

	</script>
</head>
<body>
	<div style="margin-bottom: 130px;">
		<c:import url="/TopFrame.do"/>
	</div>
	<form name="exchngRefndSechFrm" method="post">
		<input type="hidden" name="beginDatepicker" value="${prodctAnalVo.beginDatepicker}">	<!-- 기간 시작일로 검색 -->
   		<input type="hidden" name="endDatepicker" value="${prodctAnalVo.endDatepicker}">		<!-- 기간 마지막일로 검색 -->
   		<input type="hidden" name="prodctNmeSech" value="${prodctAnalVo.prodctNmeSech}">					<!-- 상품명 -->
		<input type="hidden" name="btnDatSech" value="${prodctAnalVo.btnDatSech}">			<!-- 버튼으로 기간검색 -->
	</form>
	
	<form name="exchngRefndFrm" method="post">      
		<div class="container">
			<h3>교환/환불 분석</h3>
			<div style="display:inline-block; margin-top:1%; float:right">
				<h5>
					<img src="/resources/image/common/home.png"> > 마감 관리 > 
					<a href="/ExchngRefndAnalList.do" style="text-decoration:none"><strong>교환/환불 분석</strong></a>
				</h5>
			</div>	
		</div>	
				
		<div class="container">
			<table class="table table-bordered">
				<tr>
					<td class="active">
						<h5 class="text-center"><b>기간</b></h5>
					</td>
					<td>
						<input type="button" class="btn btn-default" value="오늘" onclick="javascirpt:dayRefndSech('0일')">
						<input type="button" class="btn btn-default" value="3일" onclick="javascirpt:dayRefndSech('3일')">
						<input type="button" class="btn btn-default" value="7일" onclick="javascirpt:dayRefndSech('7일')">
						<input type="button" class="btn btn-default" value="1개월" onclick="javascirpt:dayRefndSech('1개월')">
						<input type="button" class="btn btn-default" value="3개월" onclick="javascirpt:dayRefndSech('3개월')">
						<input type="button" class="btn btn-default" value="6개월" onclick="javascirpt:dayRefndSech('6개월')"> 
						<input type="text" class="form-control" id="beginDatepicker" name="beginDatepicker" style="display: inline-block; width: auto; vertical-align: middle;"> ~
						<input type="text" class="form-control" id="endDatepicker" name="endDatepicker" style="display: inline-block; width: auto; vertical-align: middle;">
					</td>
				</tr>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>상품명</b></h5>
					</td>
					<td>
						<input type="text" name="prodctNmeSech" class="form-control" style="width:auto">
					</td>
				</tr>
			</table>
		</div>
		<div class="container text-center" align="center">
			<input type="button" class="btn btn-default" value="검색" onclick="javascript:refndSech()">
		</div>
		
<!-- 			그래프 -->
		<div class="container">
			<h5>* 통계 그래프</h5>
		</div>
		<div class="container" style="border:1px solid">
			<div id="refndQuntChart" style="width:50%; height:500px; float:left"></div>
			<div id="refndRatioChart" style="width:50%; height:500px; float:right"></div>
		</div><br>
<!-- 		엑셀다운로드 -->
		<div class="container">
			<div class="text-right">
				<input type="button" class="btn btn-default" value="엑셀 다운로드" onclick="javascript:xlsDownload();">
			</div>	
		</div>
		<div class="container">
			<table class="table table-bordered text-center">
				<tr class="active">
					<td class="text-center"><b>순위</b></td>
					<td class="text-center"><b>상품명</b></td>
					<td class="text-center"><b>판매가</b></td>
					<td class="text-center"><b>판매수량</b></td>
					<td class="text-center"><b>환불수량</b></td>
					<td class="text-center"><b>순이익</b></td>
					<td class="text-center"><b>마진율</b></td>
					<td class="text-center"><b>판매합계</b></td>
				</tr>
				<c:choose>
					<c:when test="${fn:length(refndRankingList) == 0}">
						<tr class="text-center">
							<td colspan="8">
								<h4>등록된 환불 상품이 없습니다</h4>
							</td>
						</tr>
					</c:when>
					<c:when test="${fn:length(refndRankingList) != 0}">
						<c:forEach var="refndRanking" items="${refndRankingList}" varStatus="status">
							<tr>
								<td class="text-center">${status.index+1}</td>
								<td class="text-left">${refndRanking.prodctNme}</td>
								<td class="text-right"><fmt:formatNumber value="${refndRanking.selPric}" type="number"/></td>
								<td class="text-right"><fmt:formatNumber value="${refndRanking.selQunt}" type="number"/></td>
								<td class="text-right"><fmt:formatNumber value="${refndRanking.refndQunt}" type="number"/></td>
								<td class="text-right"><fmt:formatNumber value="${refndRanking.profit}" type="number"/></td>
								<td class="text-right">${refndRanking.margnRat}%</td>
								<td class="text-right"><fmt:formatNumber value="${refndRanking.selSum}" type="number"/></td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</table>
		</div>
   </form>
	   <!-- datepicker 위젯 -->
		<script>	
// 			검색 조건 날짜
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
				$("#beginDatepicker").datepicker();
				$("#endDatepicker").datepicker();
			});
 	    </script>
</body>
</html>








