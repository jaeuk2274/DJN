<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!-- 
* 월간일정 리스트 화면을 보여주는 JSP입니다.
* 
* history :
*        최재욱, 1.0, 2016/08/16 – 초기 작성
* author : 최재욱
* version : 1.0, 2016/08/16  - 초기 작성
* see : 일정관리 일정 수정 화면(팝업)
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
		
		<!-- Don't forget to include jQuery ;) -->
	 	<script src="/resources/js/modal/jquery.modal.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" href="/resources/js/modal/jquery.modal.css" type="text/css" />
		
		<!-- fullcalendar 관련 파일들 -->
		<link href="/resources/fullcalendar/fullcalendar.min.css?ver=1" rel="stylesheet" type="text/css" />
		<link href="/resources/fullcalendar/fullcalendar.print.css" rel="stylesheet" media='print'/>
		<script src="/resources/fullcalendar/lib/moment.min.js"></script>	
		<script src="/resources/fullcalendar/fullcalendar.js" type="text/javascript"></script>	
		<script src="/resources/fullcalendar/lib/jquery-ui.custom.min.js" type="text/javascript"></script>
		<script src="/resources/fullcalendar/lang-all.js"></script>
		
	</head>
	<body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
		<div class="container">
			<div class="container">
				<h3>월간 일정</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
				   <h5>
				     <img src="/resources/image/common/home.png"> > 일정 관리 > 
				     <a href="/SchedlList.do" style="text-decoration:none"><strong>월간 일정</strong></a>
				   </h5>
				</div>
			</div>	
		
			<!-- 상단 메뉴바 import -->
			<form name="schedlSechFrm">
				<div class="container text-right">		
					<input type="button" class="btn btn-default" value="인쇄" onClick="fnPrint()" /> 			
				</div>	
			</form>	
			
			<div class ="container" id ='selectArea'>
				<div id='calendar'>
				
				</div>
			</div>
		</div>
		<script>	
			$(document).ready(function() {	
				$.ajax({
					type: "POST",
					dataType: "JSON",
					url: "/SchedlListRead.do",
					error:function(request,status,error){
						alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					},
					success: function(data){			
						$('#calendar').fullCalendar({
							header: {
								left: 'today',
								center: 'prevYear, prev, title, next, nextYear',
								right: 'month'
							},
							lang: 'ko',
							buttonIcons: true, // 버튼 아이콘을 보여준다, 이전달, 다음달 등
							selectable: true,
							selectHelper: true,
							
							// 빈 공간 클릭했을 때, 일정 등록
							select: function(start, end) {
								var defH, defW, sTop, sLeft, url;
								defW = 700;
								defH = 550;
								sTop = (screen.height - defH)/2;
								sLeft= (screen.width  - defW)/2;
								url = "/SchedlCreatePopup.do";
								popWin = window.open(url, "일정 등록", "width="+defW+", height="+defH+", top="+sTop+",left="+sLeft+", scrollbars=yes, marginwidth=0, marginheight=0");
								popWin.focus();
							},
							
							// 일정 클릭시
							eventClick: function(calEvent) {
						        // 클릭시 테두리선 red로 바꿈
						        $(this).css('border-color', 'red');
						        var defH, defW, sTop, sLeft, url;
								defW = 700;
								defH = 550;
								sTop = (screen.height - defH)/2;
								sLeft= (screen.width  - defW)/2;
								url = "/SchedlReadPopup.do?id=" + calEvent.id;
								popWin = window.open(url, "일정 상세보기", "width="+defW+", height="+defH+", top="+sTop+",left="+sLeft+", scrollbars=yes, marginwidth=0, marginheight=0");
								popWin.focus();
						    },
						    
						    // 드래그로 끌어서 일정 날짜 수정 (일정상 나중에 시간남거나 필요하다면 하겠습니다. 꼭 필요한 기능은 아니므로)
		// 					eventDrop: function(event, delta, revertFunc) {
		// 						if (!confirm(event.title + "의 일정을 정말로 바꾸시겠습니까? ")) {
		// 							revertFunc();
		// 						}
		// 					},
		
							editable: true, //수정 가능하게 해주는 부분.
							eventLimit: true, // allow "more" link when too many events
							events: data,
						});	
					}	
				});		
			});
			var fnPrint = function() {
				document.body.innerHTML = selectArea.innerHTML;
				window.print();
			};
		</script>
	</body>
</html>