<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 
* 직원 휴가목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        이인호, 1.0, 2016/08/16 – 초기 작성
* author : 이인호
* version : 1.1, 2016/08/17  - 남은 연가 조회 및 상세조회 구현
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
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
		<div class="container">
			<form name="vactnListInfo" method="post">
				<input type="hidden" name="schedlSeq" value="0"> <!-- 휴가고유값(숫자값) -->
				<input type="hidden" name="currentPageNo" id="currentPageNo" value="${empValueObject.currentPageNo}"> <!-- 페이지(숫자값) -->
				<input type="hidden" name="totalPageCount" value="${empValueObject.totalPageCount}">
				<input type="hidden" name="searchDuty" value="${empValueObject.searchDuty}"> <!-- 직책 검색조건(숫자값) -->
				<input type="hidden" name="searchType" value="${empValueObject.searchType}"> <!-- 종류 검색조건(숫자값) -->
				<input type="hidden" name="searchStartDat" value="${empValueObject.searchStartDat}"> <!-- 시작일 검색조건(숫자값) -->
				<input type="hidden" name="searchEndDat" value="${empValueObject.searchEndDat}"> <!-- 종료일 검색조건(숫자값) -->
				<input type="hidden" name="searchWrd" value="${empValueObject.searchWrd}"> <!-- 검색단어(문자값) -->
				<input type="hidden" name="searchAry" value="${empValueObject.searchAry}"> <!-- 명함정렬조건(숫자값) -->
			</form>       
			           
			<div class="container">
				<h3>휴가 목록</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
					<h5>
						<img src="/resources/image/common/home.png"> > 휴가 관리 > 
						<a href="/vactnList.do" style="text-decoration:none">
							<strong>휴가 목록</strong>
						</a>
					</h5>
				</div>
			</div>
			
			<div class="container form-inline">
				<table class="table table-bordered">
					<tbody>
						<tr>
							<th class="active">
								<h5 class="text-center"><b>이름 조회</b></h5>
							</th>
							<td>
								<input type="search" class="form-control"  id="searchWrd">
							</td>
						</tr>
						<tr>
							<th class="active">
								<h5 class="text-center"><b>직급 조회</b></h5>
							</th>
							<td>
								<c:choose>
									<c:when test="${empValueObject.searchDuty == 0}">
										<input type="radio" class="radio-inline" name="duty" id="searchDuty" checked="checked" value="0">전체
										<input type="radio" class="radio-inline" name="duty" id="searchDuty" value="1">사장
										<input type="radio" class="radio-inline" name="duty" id="searchDuty" value="2">점장
										<input type="radio" class="radio-inline" name="duty" id="searchDuty" value="3">직원
									</c:when>
									<c:when test="${empValueObject.searchDuty == 1}">
										<input type="radio" class="radio-inline" name="duty" id="searchDuty" value="0">전체
										<input type="radio" class="radio-inline" name="duty" id="searchDuty" checked="checked" value="1">사장
										<input type="radio" class="radio-inline" name="duty" id="searchDuty" value="2">점장
										<input type="radio" class="radio-inline" name="duty" id="searchDuty" value="3">직원
									</c:when>
									<c:when test="${empValueObject.searchDuty == 2}">
										<input type="radio" class="radio-inline" name="duty" id="searchDuty" value="0">전체
										<input type="radio" class="radio-inline" name="duty" id="searchDuty" value="1">사장
										<input type="radio" class="radio-inline" name="duty" id="searchDuty" checked="checked" value="2">점장
										<input type="radio" class="radio-inline" name="duty" id="searchDuty" value="3">직원
									</c:when>
									<c:when test="${empValueObject.searchDuty == 3}">
										<input type="radio" class="radio-inline" name="duty" id="searchDuty" value="0">전체
										<input type="radio" class="radio-inline" name="duty" id="searchDuty" value="1">사장
										<input type="radio" class="radio-inline" name="duty" id="searchDuty" value="2">점장
										<input type="radio" class="radio-inline" name="duty" id="searchDuty" checked="checked" value="3">직원
									</c:when>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th class="active">
								<h5 class="text-center"><b>종류 조회</b></h5>
							</th>
							<td>
								<c:choose>
									<c:when test="${empValueObject.searchType == 0}">
										<input type="radio" class="radio-inline" name="type" id="searchType" checked="checked" value="0">전체
										<input type="radio" class="radio-inline" name="type" id="searchType" value="1">연가
										<input type="radio" class="radio-inline" name="type" id="searchType" value="2">휴가
										<input type="radio" class="radio-inline" name="type" id="searchType" value="3">병가
									</c:when>
									<c:when test="${empValueObject.searchType == 1}">
										<input type="radio" class="radio-inline" name="type" id="searchType" value="0">전체
										<input type="radio" class="radio-inline" name="type" id="searchType" checked="checked" value="1">연가
										<input type="radio" class="radio-inline" name="type" id="searchType" value="2">휴가
										<input type="radio" class="radio-inline" name="type" id="searchType" value="3">병가
									</c:when>
									<c:when test="${empValueObject.searchType == 2}">
										<input type="radio" class="radio-inline" name="type" id="searchType" value="0">전체
										<input type="radio" class="radio-inline" name="type" id="searchType" value="1">연가
										<input type="radio" class="radio-inline" name="type" id="searchType" checked="checked" value="2">휴가
										<input type="radio" class="radio-inline" name="type" id="searchType" value="3">병가
									</c:when>
									<c:when test="${empValueObject.searchType == 3}">
										<input type="radio" class="radio-inline" name="type" value="0">전체&nbsp;&nbsp;
										<input type="radio" class="radio-inline" name="type" value="1">연가&nbsp;&nbsp;
										<input type="radio" class="radio-inline" name="type" value="2">휴가&nbsp;&nbsp;
										<input type="radio" class="radio-inline" name="type" checked="checked" value="3">병가
									</c:when>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th class="active">
								<h5 class="text-center"><b>기간 조회</b></h5>
							</th>
							<td class="form-inline">
								<input type="text" class="form-control" name="searchStartDat" id="searchStartDat" value="${empValueObject.searchStartDat}">
									 <font>~</font>
								<input type="text" class="form-control" name="searchEndDat" id="searchEndDat" value="${empValueObject.searchEndDat}">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="container text-center">
				<input type="button" class="btn btn-default" value="검색" id="btn_search">
			</div>
			
			<div class="form-group"></div>
			
			<div class="container form-inline">
				<select id="searchAry" class="form-control">
					<c:choose>
						<c:when test="${empValueObject.searchAry == 0}">
							<option value="0" selected="selected">날짜</option>
							<option value="1">이름</option>
							<option value="2">종류</option>
						</c:when>
						<c:when test="${empValueObject.searchAry == 1}">
							<option value="0">날짜</option>
							<option value="1" selected="selected">이름</option>
							<option value="2">종류</option>
						</c:when>
						<c:when test="${empValueObject.searchAry == 2}">
							<option value="0">날짜</option>
							<option value="1">이름</option>
							<option value="2" selected="selected">종류</option>
						</c:when>
					</c:choose>
				</select>
				<input type="button" class="btn btn-default form-control" value="연가 확인" onclick="javascript:anlVactnListPopup()">
				<input type="button" class="btn btn-default form-control" value="삭제" id="deleteSchedl">
			</div>
			<div class="container">
				<table class="table table-bordered text-center">
					<colgroup>
					    <col class="col-md-1 col-xs-1"/>
					    <col class="col-md-2 col-xs-2"/>
					    <col class="col-md-1 col-xs-1"/>                
					    <col class="col-md-2 col-xs-2"/>          
					    <col class="col-md-2 col-xs-2"/>           
					    <col class="col-md-3 col-xs-3"/>
					  </colgroup>
					<thead>
						<tr class="active">
							<td class="text-center media-middle">
								<input type="checkbox" id="allSchedlCheck">
							</td>
							<th class="text-center">
								<b>이름</b>
							</th>
							<th class="text-center">
								<b>종류</b>
							</th>
							<th class="text-center">
								<b>시작일</b>
							</th>
							<th class="text-center">
								<b>종료일</b>
							</th>
							<th class="text-center">
								<b>사유</b>
							</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${fn:length(selectVactnList) == 0}">
								<tr>
									<td colspan="6">
										<h4>등록된 휴가가 없습니다</h4>
									</td>
								</tr>
							</c:when>
							<c:when test="${fn:length(selectVactnList) != 0}">
								<c:forEach var="selectVactnList" items="${selectVactnList}" varStatus="status">
									<tr onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
										<td>
											<input type="checkbox" name="schedlCheck" value="${selectVactnList.schedlSeq}">
										</td>
										<td onclick="javascript:vactnReadPopup(${selectVactnList.schedlSeq})">
											<c:out value="${selectVactnList.nme}"/>
										</td>
										<td onclick="javascript:vactnReadPopup(${selectVactnList.schedlSeq})">
											<c:out value="${selectVactnList.vactnType}"/>
										</td>
										<td onclick="javascript:vactnReadPopup(${selectVactnList.schedlSeq})">
											<c:out value="${selectVactnList.schedlStartDat}"/>
										</td>
										<td onclick="javascript:vactnReadPopup(${selectVactnList.schedlSeq})">
											<c:out value="${selectVactnList.schedlEndDat}"/>
										</td>
										<td onclick="javascript:vactnReadPopup(${selectVactnList.schedlSeq})">
											<c:out value="${selectVactnList.schedlResn}"/>
										</td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</tbody>
				</table>
			</div>
			<div class="container text-center">
				<nav>
					<ul class="pagination">
						<!-- 무조건 1페이지로 << 버튼 -->
						<c:if test = "${empValueObject.currentPageNo>20}">
							<li>
								<a href="javascript:vactnListPagingForm(1)" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span></a>
							</li>
						</c:if>  
						<!-- 한 단위 앞으로 < 버튼 -->
						<c:if test = "${empValueObject.currentPageNo>10}">
							<li>
								<a href="javascript:vactnListPagingForm(${empValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
								<span aria-hidden="true">&lsaquo;</span></a>
							</li>
						</c:if> 	
						<c:forEach varStatus="status" begin="${empValueObject.firstPageNoOnPageList}" end="${empValueObject.lastPageNoOnPageList}">
							<c:if test="${empValueObject.currentPageNo==status.current}">
								<li class="active">
									<a href="javascript:vactnListPagingForm(${status.current})">${status.current}</a>
								</li>
							</c:if>
							<c:if test="${empValueObject.currentPageNo!=status.current}">
								<li><a href="javascript:vactnListPagingForm(${status.current})">${status.current}</a></li>
							</c:if>                   									
						</c:forEach>	
						<!-- 한 단위 뒤로 > 버튼 -->
						<c:if test = "${empValueObject.firstPageNoOnPageList + 10 < empValueObject.totalPageCount}">
							<li>
								<a href="javascript:vactnListPagingForm(${empValueObject.lastPageNoOnPageList+1})" aria-label="Next">
								<span aria-hidden="true">&rsaquo;</span></a>
							</li>
						</c:if>  
						<!-- 무조건 마지막페이지로 >> 버튼 -->
						<c:if test = "${empValueObject.firstPageNoOnPageList + 20 < empValueObject.totalPageCount}">
							<li>
								<a href="javascript:vactnListPagingForm(${empValueObject.totalPageCount})" aria-label="Next">
								<span aria-hidden="true">&raquo;</span></a>
							</li>
						</c:if>  
					</ul>
				</nav>
			</div>
		</div>
	</body>
	<script>
		//페이징
		function vactnListPagingForm(pageNo){
			var searchDuty = document.getElementById("searchDuty").value;
			var searchType = document.getElementById("searchType").value;
			var searchStartDat = document.getElementById("searchStartDat").value;
			var searchEndDat = document.getElementById("searchEndDat").value;
			var searchWrd = document.getElementById("searchWrd").value;
			var searchAry = document.getElementById("searchAry").value;
			
			document.vactnListInfo.currentPageNo.value = pageNo;
 			document.vactnListInfo.searchDuty.value = searchDuty;
			document.vactnListInfo.searchType.value = searchType;
			document.vactnListInfo.searchStartDat.value = searchStartDat;
			document.vactnListInfo.searchEndDat.value = searchEndDat;
			document.vactnListInfo.searchWrd.value = searchWrd;
			document.vactnListInfo.searchAry.value = searchAry;
			
			document.vactnListInfo.action = "/vactnList.do";
			document.vactnListInfo.submit();
		};
		
		//휴가 상세보기 팝업
		function vactnReadPopup(schedlSeq) {
			var popUrl = "vactnReadPopup.do";
			var popupName= "vactnReadPopup";
			var width = 460;
			var height = 400;
			var leftPosition = (screen.width/2) - (width/2);
				leftPosition += window.screenLeft;//듀얼 모니터일때
			var topPosition = (screen.height/2) - (height/2);
			
			var win = window.open(popUrl, popupName, "left="+leftPosition+", top="+topPosition+", toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, width="+width+", height="+height);
			
			document.vactnListInfo.target = popupName;
			document.vactnListInfo.schedlSeq.value = schedlSeq;
			document.vactnListInfo.action = "/vactnReadPopup.do";
			document.vactnListInfo.submit();
			
			if(win == null) {//만약 팝업 차단으로 팝업창이 나타나지 않을 경우
				alert("팝업차단을 해제해주세요!");
			}
			else {
				win.focus();	         
			}
		};
		
		//연가 사용일수 팝업
		function anlVactnListPopup() {
			var popUrl = "anlVactnListPopup.do";
			var popupName= "anlVactnListPopup";
			var width = 460;
			var height = 400;
			var leftPosition = (screen.width/2) - (width/2);
				leftPosition += window.screenLeft;//듀얼 모니터일때
			var topPosition = (screen.height/2) - (height/2);
			
			var win = window.open(popUrl, popupName, "left="+leftPosition+", top="+topPosition+", toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, width="+width+", height="+height);
			
			document.vactnListInfo.target = popupName;
			document.vactnListInfo.action = "/anlVactnListPopup.do";
			document.vactnListInfo.submit();
			
			if(win == null) {//만약 팝업 차단으로 팝업창이 나타나지 않을 경우
				alert("팝업차단을 해제해주세요!");
			}
			else {
			win.focus();	         
			}
		};
		
		//리스트 색 변경
		function changeTrColor(trObj, oldColor, newColor) {
			trObj.style.backgroundColor = newColor;
			trObj.onmouseout = function(){
				trObj.style.backgroundColor = oldColor;
			}
		};
		
		$(document).ready(function(){
			//체크박스 전체클릭
		    $("#allSchedlCheck").click(function(){
		        //클릭되었으면
		        if($("#allSchedlCheck").prop("checked")){
		            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
		            $("input[name=schedlCheck]").prop("checked",true);
		            //클릭이 안되있으면
		        }else{
		            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
		            $("input[name=schedlCheck]").prop("checked",false);
		        }
		    });
		    	    
		    //체크박스 삭제
		    $('#deleteSchedl').click(function(){
		    	var chked_length = $("input[name=schedlCheck]:checked").length; //체크된 박스 개수
		    	if (confirm("총 " + chked_length + "개의 항목을 삭제하시겠습니까?") == true){    //확인
		    		var chked_val = "";
					$(":checkbox[name='schedlCheck']:checked").each(function(pi,po){
						chked_val += ","+po.value;
					});
					if(chked_val!="")chked_val = chked_val.substring(1);
					if(chked_val == ""){
						alert("삭제할 휴가를 선택해주십시오.")
					}
					else{
						$.ajax({
							type:"POST",
							url:"/vactnListDelete.do",
							data:chked_val,
							success:function(data){
								document.location.reload();
							},
							error:function(request,status,error){
						        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
							}
						});
					}		
		    	}
		    	else{
		    		alert("삭제가 취소되었습니다.");	
		    	}
						
		    });
			
			
			$('#searchAry').change(function() {
				var url = "/vactnList.do";    
                var searchDuty = $("[name='duty']:checked").val();
                var searchType = $("[name='type']:checked").val();
                var searchStartDat = $("#searchStartDat").val();
                var searchEndDat = $("#searchEndDat").val();
                var searchWrd = $("#searchWrd").val();
                var searchAry = $("#searchAry").val();
                
                $("[name='searchDuty']").val(searchDuty);
				$("[name='searchType']").val(searchType);
				$("[name='searchStartDat']").val(searchStartDat);
				$("[name='searchEndDat']").val(searchEndDat);
				$("[name='searchWrd']").val(searchWrd);
				$("[name='searchAry']").val(searchAry);
				
                $("[name='vactnListInfo']").attr('action',url);
                $("[name='vactnListInfo']").submit();
			});
			
			
		    $('#btn_search').click(function() {
                var url = "/vactnList.do";    
                var searchDuty = $("[name='duty']:checked").val();
                var searchType = $("[name='type']:checked").val();
                var searchStartDat = $("#searchStartDat").val();
                var searchEndDat = $("#searchEndDat").val();
                var searchWrd = $("#searchWrd").val();
                var searchAry = $("#searchAry").val();
                
                $("[name='searchDuty']").val(searchDuty);
				$("[name='searchType']").val(searchType);
				$("[name='searchStartDat']").val(searchStartDat);
				$("[name='searchEndDat']").val(searchEndDat);
				$("[name='searchWrd']").val(searchWrd);
				$("[name='searchAry']").val(searchAry);
				
				var formData = $("#vactnListInfo").serialize();
				
				var startDate = $( "[name='searchStartDat']" ).val();
				var startDateArr = startDate.split('-');
				       
				var endDate = $( "[name='searchEndDat']" ).val();
				var endDateArr = endDate.split('-');
				      
				var startDateCompare = new Date(startDateArr[0], startDateArr[1], startDateArr[2]); //시작 날짜
				var endDateCompare = new Date(endDateArr[0], endDateArr[1], endDateArr[2]); //종료 날짜
				
		        if(startDateCompare.getTime() > endDateCompare.getTime()){
			         alert("기간을 확인해 주십시오.");
			         return;
			    }
		        else {				
               		$("[name='vactnListInfo']").attr('action',url);
                	$("[name='vactnListInfo']").submit();
		        }
             });
         });

		// datepicker 위젯 
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
	        $("#searchStartDat").datepicker();
	        $("#searchEndDat").datepicker();
	    }); 
	
	</script>
</html>