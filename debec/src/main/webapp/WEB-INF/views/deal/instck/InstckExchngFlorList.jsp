<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 
* 입고거래장 목록을 보여주는 JSP입니다.
* 
* history :
*        김대현, 1.0, 2016/08/23 – 초기 작성
* author : 김대현
* version : 1.0, 2016/08/23  - 초기 작성
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
	</head>
	<body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		<div class="container">
			<form name="instckPagingFrm" method="post">
				<input type="hidden" name="beginDatepicker" value="${instckVo.beginDatepicker}">	<!-- 시작일 검색조건 -->
				<input type="hidden" name="endDatepicker" value="${instckVo.endDatepicker}">	<!--  마지막일 검색조건 -->
				<input type="hidden" name="sechSelectBox" value="${instckVo.sechSelectBox}">	<!--  검색 셀렉트박스 -->
				<input type="hidden" name="sechText" value="${instckVo.sechText}">				<!--  검색 텍스트필드 -->
				<input type="hidden" name="currentPageNo" value="${instckVo.currentPageNo}"> 	<!-- 현재 페이지 -->
				<input type="hidden" name="clintSeq" value="${instckVo.clintSeq}">				<!-- 거래처 seq -->
				<input type="hidden" name="instckExchngFlorSeq" id ="instckExchngFlorSeq" value="${instckVo.instckExchngFlorSeq}">	<!-- 입고거래장 seq -->
			</form>
			
			<div class="container">
				<h3>입고거래장 목록</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
					<h5>
						<img src="/resources/image/common/home.png"> > 입고 거래장 관리 > 
						<a href="/InstckExchngFlorList.do" style="text-decoration:none"><strong>입고 거래장 목록</strong></a>
					</h5>
				</div>
			</div>
			
			<form name="instckFrm" class="form-group" method="post">		
				<div class="container form-inline">
					<table class="table table-bordered">
						<tr>
							<td class="active">
			                      	<h5 class="text-center"><b>등록일자</b></h5>
			                 </td>
							<td>
								<input type="text" class="form-control" id="beginDatepicker" name="beginDatepicker">
								&nbsp;&nbsp; ~ &nbsp;&nbsp;
								<input type="text" class="form-control" id="endDatepicker" name="endDatepicker">
							</td>
						</tr>
						<tr>
							<td class="active">
			                	<h5 class="text-center"><b>검색어</b></h5>
			                </td>
							<td>
							<!-- 검색   -->
								<select class="form-control" id="instckListSelectBox" name="instckListSelectBox">
									<option value="instck_exchng_flor_titl">제목
									<option value="clint_nme">거래처명
								</select>
								<input type="text" class="form-control" id="instckListSechText" name="instckListSechText">
							</td>
						</tr>
					</table>
				</div>
				
				<div class="container text-center">
					<input type="button" class="btn btn-default" value="검색" onclick="javascript:instckSech()">
				</div>
				
				<div class="form-group"></div>
				
				<!-- 거래처 목록 리스트 -->
				<div class="container form-inline">
					<table class="table table-bordered">
						<colgroup>
							<col class="col-md-1"/>                
						    <col class="col-md-1"/>          
						    <col class="col-md-3"/>           
						    <col class="col-md-2"/>
						    <col class="col-md-2"/>          
						    <col class="col-md-2"/>
						</colgroup>
						<tr class="active">
							<th class="text-center">
								<input type="checkbox" id="allInstckCheck">
							</th>
							<th class="text-center">
								<b>No</b>
							</th>
							<th class="text-center">
								<b>제목</b>
							</th>
							<th class="text-center">
								<b>거래처</b>
							</th>
							<th class="text-center">
								<b>거래 금액</b>
							</th>
							<th class="text-center">
								<b>등록 날짜</b>
							</th>
						</tr>
						<c:forEach items="${instckList}" var="instck" varStatus="status">
							<tr onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
								<td class="text-center">
									<input type="checkBox" name="instckCheck" value="${instck.instckExchngFlorSeq}">
								</td>
								<td class="text-center" onclick="javascript:instckRead(${instck.instckExchngFlorSeq})">
									${instckVo.totalRecordCount-instckVo.firstRecordIndex-status.index}
								</td>
								<td class="text-center" onclick="javascript:instckRead(${instck.instckExchngFlorSeq})">
									${instck.instckExchngFlorTitl}
								</td>
								<td class="text-center" onclick="javascript:instckRead(${instck.instckExchngFlorSeq})">
									${instck.clintNme}
								</td>
								<td class="text-center" onclick="javascript:instckRead(${instck.instckExchngFlorSeq})">
									<fmt:formatNumber value="${instck.instckExchngFlorPric}" type="number"/>
								</td>
								<td class="text-center" onclick="javascript:instckRead(${instck.instckExchngFlorSeq})">
									${instck.instckExchngFlorRegstrtnDat}
								</td>
							</tr>
						</c:forEach>
					</table>
					<div class="text-right">
						<input type="button" class="btn btn-default" value="등록" onclick="javascript:instckCreatePopup()">
						<input type="button" class="btn btn-default" value="삭제" onclick="javascript:instckDelete()">
					</div>
				</div>
			</form>
		
			<!-- 페이징 -->
			<div class="container text-center">
				<nav>
					<ul class="pagination">
						<!-- 무조건 1페이지로 << 버튼 -->
						<c:if test = "${instckVo.currentPageNo>20}">
							<li>
								<a href="javascript:instckPaging(1)" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span></a>
							</li>
						</c:if>  
						<!-- 한 단위 앞으로 < 버튼 -->
						<c:if test = "${instckVo.currentPageNo>10}">
							<li>
								<a href="javascript:instckPaging(${instckVo.firstPageNoOnPageList-1})" aria-label="Previous">
								<span aria-hidden="true">&lsaquo;</span></a>
							</li>
						</c:if> 	
									  	
						<c:forEach varStatus="status" begin="${instckVo.firstPageNoOnPageList}" end="${instckVo.lastPageNoOnPageList}">
							<c:if test="${instckVo.currentPageNo==status.current}">
								<li class="active">
									<a href="javascript:instckPaging(${status.current})">${status.current}</a>
								</li>
							</c:if>
							
							<c:if test="${instckVo.currentPageNo!=status.current}">
								<li>
									<a href="javascript:instckPaging(${status.current})">${status.current}</a>
								</li>
							</c:if>   
						</c:forEach>	
						
						<!-- 한 단위 뒤로 > 버튼 -->
						<c:if test = "${instckVo.firstPageNoOnPageList + 10 < instckVo.totalPageCount}">
							<li>
								<a href="javascript:instckPaging(${instckVo.lastPageNoOnPageList+1})" aria-label="Next">
								<span aria-hidden="true">&rsaquo;</span></a>
							</li>
						</c:if>  
						<!-- 무조건 마지막페이지로 >> 버튼 -->
						<c:if test = "${instckVo.firstPageNoOnPageList + 20 < instckVo.totalPageCount}">
							<li>
								<a href="javascript:instckPaging(${instckVo.totalPageCount})" aria-label="Next">
								<span aria-hidden="true">&raquo;</span></a>
							</li>
						</c:if>  
					</ul>
				</nav>
			</div>	
		</div>
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
		
			//등록일로 검색
			function instckSech(){
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
	           	if(startDate=="" && endDate == ""){           		
	           	}
	           	else{
	           		 if(startDate=="" || endDate == ""){
	                		alert("시작일과 종료일을 입력해주세요.");
	    	                return;	
	   	             }
	   	             else if(startDateCompare.getTime() > endDateCompare.getTime()){
	                        alert("시작일이 종료일보다 늦을 수 없습니다.");
	                        return;
	                   }
	           	}  

				document.instckPagingFrm.beginDatepicker.value=$("#beginDatepicker").val();
				document.instckPagingFrm.endDatepicker.value=$("#endDatepicker").val();
				document.instckPagingFrm.sechSelectBox.value=$("#instckListSelectBox").val();
				document.instckPagingFrm.sechText.value=$("#instckListSechText").val();
				document.instckPagingFrm.currentPageNo.value = 1;
				document.instckPagingFrm.action="/InstckExchngFlorList.do";
				document.instckPagingFrm.submit();
			};
			
			// 입고거래장 목록 리스트 페이징
			function instckPaging(page) {
				document.instckPagingFrm.currentPageNo.value = page;
				document.instckPagingFrm.action="/InstckExchngFlorList.do"
				document.instckPagingFrm.submit();
			};
			
			// 입고거래장 등록 팝업창
			function instckCreatePopup(){
				var popupUrl="/InstckExchngFlorCreateReadPopup.do";
				var popupName= "vactnReadPopup";
	            var width = 700;
				var height = 630;
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
			
			// 입고거래장 상세보기(팝업)
	        function instckRead(instckExchngFlorSeq) {
	            var popupUrl = "InstckExchngFlor_ReadPopup.do";
	            var popupName= "vactnReadPopup";
	            var width = 700;
				var height = 630;
	            var leftPosition = (screen.width/2) - (width/2);
	              	leftPosition += window.screenLeft;//듀얼 모니터일때
	            var topPosition = (screen.height/2) - (height/2);
				var win = window.open(popupUrl, popupName, "left="+leftPosition+", top="+topPosition+", toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, width="+width+", height="+height);

	            document.instckPagingFrm.target = popupName;
	            document.instckPagingFrm.instckExchngFlorSeq.value = instckExchngFlorSeq;
	            document.instckPagingFrm.action = "/InstckExchngFlor_ReadPopup.do";
	            document.instckPagingFrm.submit();
	            if(win == null) {//만약 팝업 차단으로 팝업창이 나타나지 않을 경우
	               alert("팝업차단을 해제해주세요!");
	            }
	            else {
	               win.focus();    
	            }
	         };
	         
			// 입고거래장 전체 체크
	        $(function(){
	            //전체선택 체크박스 클릭
	           $("#allInstckCheck").click(function(){
	              //만약 전체 선택 체크박스가 체크된상태일경우
	              if($("#allInstckCheck").prop("checked")) {
	                 //해당화면에 전체 checkbox들을 체크해준다
	                 $("input[type=checkbox]").prop("checked",true);
	              // 전체선택 체크박스가 해제된 경우
	              } else {
	                 //해당화면에 모든 checkbox들의 체크를해제시킨다.
	                 $("input[type=checkbox]").prop("checked",false);
	              }
	           });
	        });
			// 입고거래장 삭제
			function instckDelete(){
				var checkGroup = document.instckFrm.instckCheck;
				var changeGroup=[];
				var cnt=0;
				
				if(checkGroup.length >= 1){
					for(var i=0; i<checkGroup.length; i++){
						
						if(checkGroup[i].checked){
							
							cnt++;
							changeGroup.push(checkGroup[i].value);
						}
					}
					if(cnt == 0){
						alert("선택한 체크박스가 없습니다.");
						return;
					}
				}
				else{
					if(checkGroup.checked){
						
						cnt++;
						changeGroup.push(checkGroup.value);
					}
					if(cnt==0){
						alert("선택한 체크박스가 없습니다.");
						return;
					}
				}
				document.instckFrm.action="/InstcExchngFlorkDelete.do?changeGroup="+changeGroup;
				document.instckFrm.submit();
			};
			
			function changeTrColor(trObj, oldColor, newColor) {
				trObj.style.backgroundColor = newColor;
				trObj.onmouseout = function(){
					trObj.style.backgroundColor = oldColor;
				};
			};
		</script>
	</body>
</html>