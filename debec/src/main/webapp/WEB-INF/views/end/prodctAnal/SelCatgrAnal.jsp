<!-- 
* 판매상품 분석내용을 보여주는 JSP입니다.
* 
* history :
*        하원식, 1.0, 2016/09/07 – 초기 작성
* author : 하원식
* version : 1.0, 2016/09/07  - 초기 작성
* see : 관련된 모듈을 기술한다.
// -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>판매분류 분석</title>
		<!-- 합쳐지고 최소화된 최신 CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		
		<!-- 부가적인 테마 -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
		
		<!-- JQuery 관련 파일들 -->
		<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>	
		
		<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
		<!-- 카테고리 선택 -->
		<script src="../../../../resources/js/prodct-js/catgrChoice.js?ver=1" type="text/javascript" charset="utf-8"></script> 
		
		
		<!-- 구글 차트 -->
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script> <!-- 가장 중요한 구글 경로 설정 -->
   		<script type="text/javascript">
	       google.charts.load('current', {packages: ['corechart', 'bar']}); //corechart라고 되어 있는 부분을 사용하고자 하는 차트의 이름으로 바꾸면 된다. corechart는 기본적인 차트(pie, bar, column)을 사용하기 위한 이름이다. 
	       google.charts.setOnLoadCallback(catgrSelQuntDrawer); //구글 차트에 옵션과 데이터 값이 있는 메서드를 라이브러리를 통해 그래프를 통계 내줌.
	       function catgrSelQuntDrawer() {
	      	var data = new google.visualization.DataTable(); //google.visualization.DataTable() 객체 생성
	      	var catgrNme = new Array(); // 상품명  
	      	var relQunt = new Array(); // 판매수량
	        var status = 0; // 반복문에서 조건문의 사이즈로 사용하기 위해 변수 만들어 줌.
			<c:forEach var="selQunt" items="${catgrSelQuntList}" varStatus="status"> // 컨트롤러에서 쉽게 사용하기 위해 c:forEach를 사용 
			catgrNme.push("${selQunt.catgrNme}"); // 제목을 title 배열에 넣어준다.
				relQunt.push("${selQunt.relQunt}"); // 각 합계를 sales 배열에 넣어준다.
				status+= 1; // 반복문 조건문의 사이즈로 사용하기 위해 반복이 될 때 마다 +1을 해줌.
			</c:forEach>
	          data.addColumn('string', '상품명'); // DataTable 객체에서 컬럼 값을 정의 해준다.Column은 그래프가 사용 될 데이터 순서를 지정해준다.(데이터 형식과 명칭을 정해줌.)
	          data.addColumn('number', '판매수량');
	          
	          for(var i=0; i<status; i++){      
	             data.addRows([ // addRows는 그래프 통계 내야하는 데이터 값들을 넣어줘야 한다. 유동적으로 바뀌기 때문에 반복문을 사용한다.
	                [catgrNme[i], parseInt(relQunt[i])], // 이중 배열을 사용.
	             ]);
	          }
	          var options = { // 옵션은 그래프에서 통계 이외의 옵션들을 제공한다. 종류는 엄청 많다.(다 설명하기 어려우므로 구글링 해보시길...)
	             title: '판매븐류 별 판매수량  Top 10 상품',
	             hAxis: { // 가로축에 대한 옵션 사용
	                title: '일자',
	                format: 'h:mm a', //번호 형식
	             },
	             vAxis: { // 세로축에 대한 옵션 사용
	                title: '액수'
	             }
	          };
	          var chart = new google.visualization.PieChart(document.getElementById('catgrSelQunt')); // div id에 구글 통계들을 보내준다.
	          chart.draw(data, options); // Google 시각화 및 차트 라이브러리를로드
	          
	       }
	       
		   google.charts.setOnLoadCallback(catgrSelPricDrawer); //구글 차트에 옵션과 데이터 값이 있는 메서드를 라이브러리를 통해 그래프를 통계 내줌.
		    function catgrSelPricDrawer() {
		   	var data = new google.visualization.DataTable(); //google.visualization.DataTable() 객체 생성
		   	var catgrNme = new Array(); // 상품명  
		   	var retrnConTnTotalSelPric = new Array(); // 판매수량
		    var status = 0; // 반복문에서 조건문의 사이즈로 사용하기 위해 변수 만들어 줌.
			<c:forEach var="selSum" items="${catgrSelSumList}" varStatus="status"> // 컨트롤러에서 쉽게 사용하기 위해 c:forEach를 사용 
				catgrNme.push("${selSum.catgrNme}"); // 제목을 title 배열에 넣어준다.
				retrnConTnTotalSelPric.push("${selSum.retrnConTnTotalSelPric}"); // 각 합계를 sales 배열에 넣어준다.
				status+= 1; // 반복문 조건문의 사이즈로 사용하기 위해 반복이 될 때 마다 +1을 해줌.
			</c:forEach>
		       data.addColumn('string', '상품명'); // DataTable 객체에서 컬럼 값을 정의 해준다.Column은 그래프가 사용 될 데이터 순서를 지정해준다.(데이터 형식과 명칭을 정해줌.)
		       data.addColumn('number', '판매금액');
		       for(var i=0; i<status; i++){      
		          data.addRows([ // addRows는 그래프 통계 내야하는 데이터 값들을 넣어줘야 한다. 유동적으로 바뀌기 때문에 반복문을 사용한다.
		             [catgrNme[i], parseInt(retrnConTnTotalSelPric[i])], // 이중 배열을 사용.
		          ]);
		       }
		       var options = { // 옵션은 그래프에서 통계 이외의 옵션들을 제공한다. 종류는 엄청 많다.(다 설명하기 어려우므로 구글링 해보시길...)
		          title: '판매븐류 별 판매금액  Top 10 상품',
		          hAxis: { // 가로축에 대한 옵션 사용
		             title: '일자',
		             format: 'h:mm a', //번호 형식
		          },
		          vAxis: { // 세로축에 대한 옵션 사용
		             title: '액수'
		          }
		       };
		       var chart = new google.visualization.PieChart(document.getElementById('catgrSelPric')); // div id에 구글 통계들을 보내준다.
		       chart.draw(data, options); // Google 시각화 및 차트 라이브러리를로드
		    }
	       
// 날짜 버튼눌렀을 때 (오늘,3,7,1개월,3개월,6개월)
   		function DaySalsAnal(day) {
   	   		if($("#topCatgrNme").val()!="대분류")
   	   	   	{
	           	$("input[name=selectedTopCatgrNme]").val($("#topCatgrNme").val());
	    		$("input[name=selectedMidCatgrNme]").val($("#midCatgrNme").val());
	    		$("input[name='startDatSech']").val(""); // 종료날짜
	    		$("input[name='endDatSech']").val(""); // 종료날짜
	    		$("input[name='btnDatSech']").val(day); // 종료날짜
	   			document.SelCatgrAnal.action ="/SelCatgrAnal.do/";
				document.SelCatgrAnal.submit(); 		
   	   	   	}
   	   		else
   	   	   	{
	   			var url = "/SelCatgrAnal.do?btnDatSech="+day;
				$(location).attr('href',url);	
   	   	   	}
   		}
	       
// 검색
		function selProdctSech(){
			// 날짜 유효성검사
   			var today = new Date();
            var year = today.getFullYear();
            var month = today.getMonth() + 1;
            var day = today.getDate();      
            var startDate = $("input[name='startDatSech']").val(); // 시작날짜
            var startDateArr = startDate.split('-');                   
            var endDate = $("input[name='endDatSech']").val(); // 종료날짜
            var endDateArr = endDate.split('-');                 
            var present = new Date(year, month, day);//오늘 날짜 
            var startDateCompare = new Date(startDateArr[0], startDateArr[1], startDateArr[2]); //시작 날짜
            var endDateCompare = new Date(endDateArr[0], endDateArr[1], endDateArr[2]); //종료 날짜
            var day = 1000*60*60*24;             
            var month = day*30;
            var year = month*12;               
           	if(startDate == ""&& endDate == ""){     
           	}
           	else{
           		 if(startDate == ""||endDate == ""){
                		alert("시작일과 종료일을 입력해주세요.");
    	                return;	
   	             }
	             else if(parseInt((endDateCompare-startDateCompare)/month) > 6){
		                alert("판매 분류 분석에서는 최대 6개월까지일까지 분석이 가능합니다.");
		                return;
            	}
   	             else if(startDateCompare.getTime() > endDateCompare.getTime()){
                        alert("시작일이 종료일보다 늦을 수 없습니다.");
                        return;
                 }
   	             else
 	             {
   		    		$("input[name='btnDatSech']").val(""); // 종료날짜 
 	             }
           	}
           	$("input[name=selectedTopCatgrNme]").val($("#topCatgrNme").val());
    		$("input[name=selectedMidCatgrNme]").val($("#midCatgrNme").val());
    		$("input[name=selectedBotCatgrNme]").val($("#botCatgrNme").val());
   			document.SelCatgrAnal.action ="/SelCatgrAnal.do";
			document.SelCatgrAnal.submit(); 			
   		
	
			
		}

		// 엑셀 다운로드
		function xlsDownload(){
			document.SelCatgrAnal.action = "/SelCatgrXls.do";
			document.SelCatgrAnal.submit();
		};
   	  </script>
	 
		
	</head>
	<body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do?var=1"/>
		</div>
	   
	 	<form name="SelCatgrAnal" method="post">      
			
		 	<div class="container">
				<h3>판매 분류 분석</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
					<h5>
						<img src="/resources/image/common/home.png"> > 마감 관리 > 
						<a href="/SelProdctAnalList.do" style="text-decoration:none"><strong>상품 분석</strong></a>
					</h5>
				</div>	
			</div>	
    		<input type="hidden" name="selectedTopCatgrNme" value="${prodctAnalValueObject.selectedTopCatgrNme}"> <!-- 선택된 대분류-->
    		<input type="hidden" name="selectedMidCatgrNme" value="${prodctAnalValueObject.selectedMidCatgrNme}"> <!-- 선택된 중분류 -->
	   		<input type="hidden" name="btnDatSech" value="${prodctAnalValueObject.btnDatSech}">		<!-- 기간 마지막일로 검색 -->
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
							<input type="text" class="form-control" id="startDatSech" name="startDatSech" style="display: inline-block; width: auto; vertical-align: middle;" value="${prodctAnalValueObject.startDatSech}"> ~
							<input type="text" class="form-control" id="endDatSech" name="endDatSech" style="display: inline-block; width: auto; vertical-align: middle;" value="${prodctAnalValueObject.endDatSech}">
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
									<c:if test="${catgrList ne '전체조회'}">
										<c:if test="${catgrList==prodctAnalValueObject.selectedTopCatgrNme}">
											<option selected>${catgrList}</option>
										</c:if>
										<c:if test="${catgrList!=prodctAnalValueObject.selectedTopCatgrNme}">
											<option>${catgrList}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</select>
							<select name="midCatgrNme" id="midCatgrNme" class="searchSelectBox form-control" onchange="javascript:catgrChoice('중',this.value)" style="display: inline-block; width: auto; vertical-align: middle;">
								<option selected>중분류</option>
								<c:forEach var="catgrList" items="${midCatgrArray}" varStatus="status">			
									<c:if test="${catgrList ne '전체조회'}">
										<c:if test="${catgrList==prodctAnalValueObject.selectedMidCatgrNme}">
											<option selected>${catgrList}</option>
										</c:if>
										<c:if test="${catgrList!=prodctAnalValueObject.selectedMidCatgrNme}">
											<option>${catgrList}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<div class="container text-center">
			<input class="btn btn-default" type="button" value="검색" onclick="javscript:selProdctSech()">
		</div>
		<div class="container">
			<h5>* 통계 그래프</h5>
		</div>
		<div class="container text-center" style="border:1px solid;">
				<div id="catgrSelQunt" style="width:50%; height:500px;float:left;"></div>
				<div id="catgrSelPric" style="width:50%; height:500px;float:right;"></div>
		</div><br>
<!-- 			엑셀 다운로드 -->
		<div class="container">
			<div class="text-right">
				<input type="button" class="btn btn-default" value="엑셀 다운로드" onclick="javascript:xlsDownload();">
			</div>	
		</div>
		
		<div class="container">
			<table class="table table-bordered">
				<tr class="active">
					<td class="text-center">순위</td>
					<td class="text-center">분류명</td>
					<td class="text-center">판매가</td>
					<td class="text-center">환불수량</td>
					<td class="text-center">판매수량</td>
					<td class="text-center">순이익</td>
					<td class="text-center">마진율</td>
					<td class="text-center">판매합계</td>
				</tr>
				<c:choose>
					<c:when test="${fn:length(catgrSelSumList) == 0}">
						<tr class="text-center">
							<td colspan="8">
								<h4>판매한 상품이 없습니다</h4>
							</td>
						</tr>
					</c:when>
					<c:when test="${fn:length(catgrSelSumList) != 0}">
						<c:forEach var="selSum" items="${catgrSelSumList}" varStatus="status">
							<tr>
								<td class="text-center">${status.count}</td>
								<td class="text-left">${selSum.catgrNme}</td>
								<td class="text-right"><fmt:formatNumber value="${selSum.selPric}" type="number"/> 원</td>
								<td class="text-right"><fmt:formatNumber value="${selSum.retrnQunt}" type="number"/> 개</td>
								<td class="text-right"><fmt:formatNumber value="${selSum.relQunt}" type="number"/> 개</td>
								<td class="text-right"><fmt:formatNumber value="${selSum.netInc}" type="number"/> 원</td>
								<td class="text-right"><fmt:formatNumber value="${selSum.marginRat}" pattern=".00" /> %</td>
								<td class="text-right"><fmt:formatNumber value="${selSum.totalSelPric}" type="number"/> 원</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</table>		
		</div>
		
		
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
				$("#startDatSech").datepicker();
				$("#endDatSech").datepicker();
			});
 	    </script>
	</body>
</html>