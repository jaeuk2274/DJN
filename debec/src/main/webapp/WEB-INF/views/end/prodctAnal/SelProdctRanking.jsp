<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 
* 판매상품 분석내용을 보여주는 JSP입니다.
* 
* history :
*        김대현, 1.0, 2016/09/07 – 초기 작성
* author : 김대현
* version : 1.0, 2016/09/07  - 초기 작성
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
	
		<!-- 카테고리 선택 -->
		<script src="../../../../resources/js/prodct-js/catgrChoice.js?ver=1" type="text/javascript" charset="utf-8"></script> 
<!-- 		<script src="../../../../resources/deal-js/clintRead.js?ver=1" type="text/javascript" charset="utf-8"></script> -->
		
		
		
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
      	var selQunt = new Array(); // 판매수량
        var status = 0; // 반복문에서 조건문의 사이즈로 사용하기 위해 변수 만들어 줌.
        
        <c:forEach var="selQunt" items="${prodctSelQuntList}" varStatus="status"> // 컨트롤러에서 쉽게 사용하기 위해 c:forEach를 사용 
			prodctNme.push("${selQunt.prodctNme}"); // 제목을 title 배열에 넣어준다.
			selQunt.push("${selQunt.qunt}"); // 각 합계를 sales 배열에 넣어준다.
			status+= 1; // 반복문 조건문의 사이즈로 사용하기 위해 반복이 될 때 마다 +1을 해줌.
		</c:forEach>
		data.addColumn('string', '상품명'); // DataTable 객체에서 컬럼 값을 정의 해준다.Column은 그래프가 사용 될 데이터 순서를 지정해준다.(데이터 형식과 명칭을 정해줌.)
        data.addColumn('number', '판매수량');
		
        for(var i=0; i<status; i++){      
            data.addRows([ // addRows는 그래프 통계 내야하는 데이터 값들을 넣어줘야 한다. 유동적으로 바뀌기 때문에 반복문을 사용한다.
               [prodctNme[i], parseInt(selQunt[i])], // 이중 배열을 사용.
            ]);
         }

        var options = { // 옵션은 그래프에서 통계 이외의 옵션들을 제공한다. 종류는 엄청 많다.(다 설명하기 어려우므로 구글링 해보시길...)
			title: '판매수량별 Top 10 상품',
			hAxis: { // 가로축에 대한 옵션 사용
			   title: '일자',
			   format: 'h:mm a', //번호 형식
			},
			vAxis: { // 세로축에 대한 옵션 사용
			   title: '액수'
			}
	    };

        // Instantiate and draw the chart for Sarah's pizza.
        var chart = new google.visualization.PieChart(document.getElementById('selQuntChart'));
        chart.draw(data, options);
      }

      // Callback that draws the pie chart for Anthony's pizza.
      function drawAnthonyChart() {

        // Create the data table for Anthony's pizza.
        var data = new google.visualization.DataTable();
        var prodctNme = new Array(); // 상품명  
	   	var selSum = new Array(); // 판매수량
	    var status = 0; // 반복문에서 조건문의 사이즈로 사용하기 위해 변수 만들어 줌.
	    <c:forEach var="selSum" items="${prodctSelSumList}" varStatus="status"> // 컨트롤러에서 쉽게 사용하기 위해 c:forEach를 사용 
			prodctNme.push("${selSum.prodctNme}"); // 제목을 title 배열에 넣어준다.
			selSum.push("${selSum.selPric}"); // 각 합계를 sales 배열에 넣어준다.
			status+= 1; // 반복문 조건문의 사이즈로 사용하기 위해 반복이 될 때 마다 +1을 해줌.
		</c:forEach>
		data.addColumn('string', '상품명'); // DataTable 객체에서 컬럼 값을 정의 해준다.Column은 그래프가 사용 될 데이터 순서를 지정해준다.(데이터 형식과 명칭을 정해줌.)
	    data.addColumn('number', '판매금액');
	    for(var i=0; i<status; i++){      
			data.addRows([ // addRows는 그래프 통계 내야하는 데이터 값들을 넣어줘야 한다. 유동적으로 바뀌기 때문에 반복문을 사용한다.
			   [prodctNme[i], parseInt(selSum[i])], // 이중 배열을 사용.
			]);
	    }

        // Set options for Anthony's pie chart.
        var options = { // 옵션은 그래프에서 통계 이외의 옵션들을 제공한다. 종류는 엄청 많다.(다 설명하기 어려우므로 구글링 해보시길...)
		   title: '판매금액별 Top 10 상품',
		   hAxis: { // 가로축에 대한 옵션 사용
		      title: '일자',
		      format: 'h:mm a', //번호 형식
		   },
		   vAxis: { // 세로축에 대한 옵션 사용
		      title: '액수'
		   }
		};

        // Instantiate and draw the chart for Anthony's pizza.
        var chart = new google.visualization.PieChart(document.getElementById('selSumChart'));
        chart.draw(data, options);
      }
  
	       
// 날짜 버튼눌렀을 때 (오늘,3,7,1개월,3개월,6개월)
   		function DaySalsAnal(day) {
//    			alert(day);
//    			var url = "/SelProdctAnalList.do?btnDatSech="+day;
// 			$(location).attr('href',url);	
   			document.selProdctSechFrm.selectedTopCatgrNme.value = $("#topCatgrNme").val();
   			document.selProdctSechFrm.selectedMidCatgrNme.value = $("#midCatgrNme").val();
   			document.selProdctSechFrm.selectedBotCatgrNme.value = $("#botCatgrNme").val();
   			document.selProdctSechFrm.prodctNmeSech.value = document.selProdctFrm.prodctNmeSech.value;
   			document.selProdctSechFrm.beginSelPrice.value = document.selProdctFrm.beginSelPrice.value;
   			document.selProdctSechFrm.endSelPrice.value = document.selProdctFrm.endSelPrice.value;
   			document.selProdctSechFrm.btnDatSech.value = day;
   			document.selProdctSechFrm.action="/SelProdctAnalList.do"
   			document.selProdctSechFrm.submit();
   		}
	       
// 검색
		function selProdctSech(){
// 			날짜 유효성검사
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
	        
// 	        alert(parseInt((endDateCompare-startDateCompare)/day) + "일");
	        
	       	if(startDate=="" && endDate == ""){           		
	       	}
	       	else{
	       		 if(startDate=="" || endDate == ""){
            		 alert("시작일과 종료일을 모두 입력해주세요.");
	                 return;	
	             }
	             else if(parseInt((endDateCompare-startDateCompare)/month) > 6){
	                 alert("판매 상품 분석에서는 최대 6개월까지 분석이 가능합니다.");
	                 return;
	             }
	             else if(startDateCompare.getTime() > endDateCompare.getTime()){
                     alert("시작일이 종료일보다 늦을 수 없습니다.");
                     return;
            	 }
	       	}
	       	var beginSelPrice = $("#beginSelPrice").val();
	       	var endSelPrice = $("#endSelPrice").val();
	       	beginSelPrice = Number(beginSelPrice);		// if문에 값비교를 하기위해 넘버값으로 변환
	       	endSelPrice = Number(endSelPrice);			// if문에 값비교를 하기위해 넘버값으로 변환
		   	if(beginSelPrice > endSelPrice){
				alert("상품 판매가를  다시 확인해주세요.");
				return;
		    }
	       
   			document.selProdctSechFrm.beginDatepicker.value = $("#beginDatepicker").val();	
   			document.selProdctSechFrm.endDatepicker.value = $("#endDatepicker").val();
   			document.selProdctSechFrm.selectedTopCatgrNme.value = $("#topCatgrNme").val();
   			document.selProdctSechFrm.selectedMidCatgrNme.value = $("#midCatgrNme").val();
   			document.selProdctSechFrm.selectedBotCatgrNme.value = $("#botCatgrNme").val();
   			document.selProdctSechFrm.prodctNmeSech.value = document.selProdctFrm.prodctNmeSech.value;
   			document.selProdctSechFrm.beginSelPrice.value = document.selProdctFrm.beginSelPrice.value;
   			document.selProdctSechFrm.endSelPrice.value = document.selProdctFrm.endSelPrice.value;
   			document.selProdctSechFrm.action="/SelProdctAnalList.do"
   			document.selProdctSechFrm.submit();
		}

// 엑셀 다운로드
		function xlsDownload(){
			document.selProdctSechFrm.action = "/SelProdct.do";
			document.selProdctSechFrm.submit();
		};
   	  </script>
	</head>
	<body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		<form name="selProdctSechFrm" method="post">
			<input type="hidden" name="beginDatepicker" value="${prodctAnalVo.beginDatepicker}" >	<!-- 기간 시작일로 검색 -->
	   		<input type="hidden" name="endDatepicker" value="${prodctAnalVo.endDatepicker}">		<!-- 기간 마지막일로 검색 -->
	   		<input type="hidden" name="selectedTopCatgrNme" value="${prodctAnalVo.selectedTopCatgrNme}">		<!-- 대분류 -->
	   		<input type="hidden" name="selectedMidCatgrNme" value="${prodctAnalVo.selectedMidCatgrNme}">		<!-- 중분류 -->
	   		<input type="hidden" name="selectedBotCatgrNme" value="${prodctAnalVo.selectedBotCatgrNme}">		<!-- 소분류 -->
	   		<input type="hidden" name="prodctNmeSech" value="${prodctAnalVo.prodctNmeSech}">					<!-- 상품명 -->
	   		<input type="hidden" name="beginSelPrice" value="${prodctAnalVo.beginSelPrice}">			<!-- 시작상품판매가 -->
	   		<input type="hidden" name="endSelPrice" value="${prodctAnalVo.endSelPrice}">				<!-- 끝상품판매가 -->
			<input type="hidden" name="btnDatSech" value="${prodctAnalVo.btnDatSech}">			<!-- 버튼으로 기간검색 -->
		</form>
	   
	   <form name="selProdctFrm" method="post">      
		<div class="container">
			<h3>판매 상품 분석</h3>
			<div style="display:inline-block; margin-top:1%; float:right">
				<h5>
					<img src="/resources/image/common/home.png"> > 마감 관리 > 
					<a href="/SelProdctAnalList.do" style="text-decoration:none"><strong>상품 분석</strong></a>
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
						<input type="button" class="btn btn-default" value="오늘" onclick="javascirpt:DaySalsAnal('0일')">
						<input type="button" class="btn btn-default" value="3일" onclick="javascirpt:DaySalsAnal('3일')">
						<input type="button" class="btn btn-default" value="7일" onclick="javascirpt:DaySalsAnal('7일')">
						<input type="button" class="btn btn-default" value="1개월" onclick="javascirpt:DaySalsAnal('1개월')">
						<input type="button" class="btn btn-default" value="3개월" onclick="javascirpt:DaySalsAnal('3개월')">
						<input type="button" class="btn btn-default" value="6개월" onclick="javascirpt:DaySalsAnal('6개월')"> 
						<input type="text" class="form-control" id="beginDatepicker" name="startDatSech" style="display: inline-block; width: auto; vertical-align: middle;"> ~
						<input type="text" class="form-control" id="endDatepicker" name="endDatSech" style="display: inline-block; width: auto; vertical-align: middle;">
					</td>
				</tr>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>상품 분류</b></h5>
					</td>
					<td>
						<select name="topCatgrNme" id="topCatgrNme" class="searchSelectBox form-control" onchange="javascript:catgrChoice('대',this.value)" style="display: inline-block; width: auto; vertical-align: middle;">
							<option selected>대분류</option>
							<c:forEach var="catgrList" items="${topCatgrArray}" varStatus="status">
								<c:if test="${catgrList==ProdctValueObject.selectedTopCatgrNme}">
									<option selected>${catgrList}</option>
								</c:if>
								<c:if test="${catgrList!=ProdctValueObject.selectedTopCatgrNme}">
									<option>${catgrList}</option>
								</c:if>
							</c:forEach>
						</select>
						<select name="midCatgrNme" id="midCatgrNme" class="searchSelectBox form-control" onchange="javascript:catgrChoice('중',this.value)" style="display: inline-block; width: auto; vertical-align: middle;">
							<option selected>중분류</option>
							<c:forEach var="catgrList" items="${midCatgrArray}" varStatus="status">
								<c:if test="${catgrList==ProdctValueObject.selectedMidCatgrNme}">
									<option selected>${catgrList}</option>
								</c:if>
								<c:if test="${catgrList!=ProdctValueObject.selectedMidCatgrNme}">
									<option>${catgrList}</option>
								</c:if>
							</c:forEach>
						</select>
						<select name="botCatgrNme" class="searchSelectBox form-control" id="botCatgrNme" name="prodctSechOption" style="display: inline-block; width: auto; vertical-align: middle;">
							<option selected>소분류</option>
							<c:forEach var="catgrList" items="${botCatgrArray}" varStatus="status">
								<c:if test="${catgrList==ProdctValueObject.selectedBotCatgrNme}">
									<option selected>${catgrList}</option>
								</c:if>
								<c:if test="${catgrList!=ProdctValueObject.selectedBotCatgrNme}">
									<option>${catgrList}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="active">
						<h5 class="text-center"><b>상품명</b></h5>
					</td>
					<td>
						<input type="text" class="form-control" name="prodctNmeSech" style="display: inline-block; width: auto; vertical-align: middle;">
					</td>
				</tr>
				<tr>
					<td class="active">
							<h5 class="text-center"><b>상품 판매가</b></h5>
					</td>
					<td>
						<input type="text" name="beginSelPrice" id="beginSelPrice" class="form-control" value="0" style="display: inline-block; width: auto; vertical-align: middle;"> &nbsp; ~ &nbsp; 
						<input type="text" name="endSelPrice" id="endSelPrice" class="form-control" value="0" style="display: inline-block; width: auto; vertical-align: middle;">원
					</td>
				</tr>
			</table>
		</div>
			
			<div class="container text-center">
				<input type="button" class="btn btn-default" value="검색" onclick="javascript:selProdctSech()">
			</div>
			
<!-- 			그래프 -->
			<div class="container">
				<h5>* 통계 그래프</h5>
			</div>
			<div class="container" style="border:1px solid">
				<div id="selQuntChart" style="width:50%; height:500px; float:left"></div>
				<div id="selSumChart" style="width:50%; height:500px; float:right"></div>
			</div><br>
<!-- 			엑셀 다운로드 -->
			<div class="container">
				<div class="text-right">
					<input type="button" class="btn btn-default" value="엑셀 다운로드" onclick="javascript:xlsDownload();">
				</div>	
			</div>
			
			<div class="container">
				<table class="table table-bordered text-center">
					<tr class="active">
						<td class="text-center">순위</td>
						<td class="text-center">상품명</td>
						<td class="text-center">판매가</td>
						<td class="text-center">재고수량</td>
						<td class="text-center">환불수량</td>
						<td class="text-center">판매수량</td>
						<td class="text-center">판매합계</td>
					</tr>
					<c:choose>
						<c:when test="${fn:length(prodctRankingList) == 0}">
							<tr class="text-center">
								<td colspan="7">
									<h4>판매한 상품이 없습니다</h4>
								</td>
							</tr>
						</c:when>
						<c:when test="${fn:length(prodctRankingList) != 0}">
							<c:forEach var="prodctRanking" items="${prodctRankingList}" varStatus="status">
								<tr>
									<td class="text-center">${status.index+1}</td>
									<td class="text-left">${prodctRanking.prodctNme}</td>
									<td class="text-right"><fmt:formatNumber value="${prodctRanking.selPric}" type="number"/></td>
									<td class="text-right"><fmt:formatNumber value="${prodctRanking.stckQunt}" type="number"/></td>
									<td class="text-right"><fmt:formatNumber value="${prodctRanking.refndQunt}" type="number"/></td>
									<td class="text-right"><fmt:formatNumber value="${prodctRanking.selQunt}" type="number"/></td>
									<td class="text-right"><fmt:formatNumber value="${prodctRanking.selSum}" type="number"/></td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</table>		
			</div>
	    </form>
		<!-- datepicker 위젯 -->
		<script>	
			// 검색 조건 날짜
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