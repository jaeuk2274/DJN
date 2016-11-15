<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<!-- 
* 푸시알람 발송 목록을 조회하는 화면을 보여주는 JSP 입니다.

* 
* history :
*        이정호, 1.0, 2016/08/09 – 초기 작성
* author : 이정호
* version : 1.1, 2016/08/09  - 초기 작성
* author : 이정호
* version : 1.2, 2016/08/22  - 수신자 전체 수정
* author : 이정호
* version : 1.3, 2016/08/23  - 수신자 상품, 패키지, 공동구매 수정
* see : 관련된 모듈을 기술한다.
//-->

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
</head>
<body>
	<!-- 상단 메뉴바 import -->
	<div style="margin-bottom: 130px;">
	    <c:import url="/TopFrame.do"/>
	</div>	
	
	<div class="container">
		<h3>푸시 알림 발송</h3>
		<div style="display:inline-block; margin-top:1%; float:right">
		   <h5>
		     <img src="../../../../../resources/image/common/home.png"> > 푸시 알림 관리 > 
		     <a href="/mainDisplayList.do" style="text-decoration:none"><strong>푸시 알림 발송</strong></a>
		   </h5>
		</div>
	</div>
	
	<form name="pushDespRecrdFrm" class="form-inline">
		<input type="hidden" name="type" value="despList">
		<div class="container">
			<table class="table table-bordered">
				<colgroup>
					<col style="width:20%"/>
					<col style="width:70%"/>
					<col style="width:10%"/>
				</colgroup>	
				<tr>
					<th class="active">
						<h5 class="text-center"><b>푸시알림 불러오기</b></h5>
					</th>
					<td>
						<input type="text" class="form-control" id="pushTemplt" name="" value="선택 안함" style="width:100%" readonly>
					</td>
					<td class="text-center">
						<input type="button" class="btn btn-default" id="" value="불러오기"  onclick="javascript:pushImprtTempltListPopup()">
					</td>
				</tr>
				<tr>
					<th class="active">
						<h5 class="text-center"><b>최근 푸시알림 내역 불러오기</b></h5>
					</th>
					<td colspan="2">
						<select class="form-control" style="width:100%" onchange="selectPushDesp(this.value)">
							<option value="0" selected>선택 안함</option>
							<c:forEach items="${pushHistoryList}" var="pushHistoryList" varStatus="status">
								<option value="${pushHistoryList.pushSeq}">${pushHistoryList.pushTitl} (${pushHistoryList.pushDat})</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th class="active">
						<h5 class="text-center"><b>푸시알림 제목</b></h5>
					</th>
					<td colspan="2">
						<input type="text" class="form-control" id="sendHedln" name="pushHedln" placeholder="푸시알림 제목을 입력해주세요." style="width:100%">											
					</td>
				</tr>	
			</table>
		</div>
		
		<div class="container">
			<div style="width:70%; float:left">
				<table class="table table-bordered" style="width:100%; table-layout:fixed; word-break:break-all;">
					<colgroup>
						<col style="width:20%">
						<col style="width:80%">
					</colgroup>
					<tr>
						<th colspan="2">
							<strong>푸시팝업/상태창</strong>
						</th>
					</tr>
					<tr>
						<td class="text-center">
							푸시팝업 제목
						</td>
						<td>
							<input type="text" class="form-control" id="sendTitl" name="sendTitl" placeholder="푸시팝업 제목을 입력해주세요." style="width:100%" onkeydown="insertPushTitl()">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea rows="17" style="width:100%;" id="sendMesg" name="sendMesg" onkeydown="insertPushContent()" placeholder="푸시팝업 내용을 입력해주세요."></textarea>
						</td>
					</tr>
					<tr>
						<th colspan="2">
							<strong>푸시 알림 연결 URL</strong>
						</th>
					</tr>
					<tr>
						<td colspan="2">
							<input type="text" class="form-control" id="sendUrl" name="sendUrl" placeholder="푸시 알림 연결 URL을 입력해주세요." style="width:100%">
						</td>
					</tr>
				</table>
			</div>
			<div style="width:30%; float:right">
				<table class="table table-bordered" style="width:100%; table-layout:fixed;" background="/resources/image/push/push_view2.png">
					<colgroup>
						<col style="width:33%">
						<col style="width:67%">
					</colgroup>
					<tr style="height:10%">
						<th colspan="2" class="text-center">
							<font color="white">오후 12:00</font>
						</th>
					</tr>
					<tr style="height:10%">
						<td rowspan="2">
							<img src="/resources/image/push/push_view1.png">
						</td>
						<td>
							<font id="pushViewTitl" color="white">푸시팝업 제목을 입력해주세요.</font>
						</td>
					</tr>
					<tr style="height:10%">
						<td>
							<font id="pushViewMesg" color="white">푸시팝업 내용을 입력해주세요.</font>
						</td>
					</tr>
					<tr style="height:70%">
						<td colspan="2">
							<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="container" id="mainDiv">
			<table class="table table-bordered" style="width:100%; table-layout:fixed; word-break:break-all;">
				<colgroup>
					<col style="width:20%">
					<col style="width:80%">
				</colgroup>
				<tr>
					<th colspan="2">
						<strong>타겟</strong>
					</th>
				</tr>
				<tr>
					<td class="active text-center">
						수신자 설정
					</td>
					<td>
						<font id="custmrCount">총 0 명</font>
						<input type="button"  class="btn btn-default btn-xs" value="불러오기" onclick="javascript:pushRecpnt()">
					</td>
				</tr>
			</table>
		</div>
		<div class="container">
<!-- 			<table class="table table-bordered" style="width:100%;"> -->
<%-- 				<colgroup> --%>
<%-- 					<col style="width:20%"> --%>
<%-- 					<col style="width:5%"> --%>
<%-- 					<col style="width:75%"> --%>
<%-- 				</colgroup> --%>
<!-- 				<tr> -->
<!-- 					<th colspan="3"> -->
<!-- 						<strong>시간 설정</strong> -->
<!-- 					</th> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td rowspan="3" class="active text-center"> -->
<!-- 						추가 설정 -->
<!-- 					</td> -->
<!-- 					<td colspan="2"> -->
<!-- 						<input type="radio" name="time" value="1" checked> 즉시발송 -->
<!-- 						<input type="radio" name="time" value="2"> 예약발송 -->
<!-- 						<input type="radio" name="time" value="3"> 정기발송 -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td><input type="checkbox" name="term" value="1">유효기간</td> -->
<!-- 					<td> -->
<!-- 						푸시알림 발송 후  -->
<!-- 						<input type="date" class="form-control" id="" name="" placeholder=""> -->
<!-- 						<input type="time" class="form-control" id="" name="" placeholder=""> -->
<!-- 						까지 메시지 노출 -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td colspan="2"> -->
<!-- 						<span><input type="checkbox" name="term" value="1">푸시 분할 발송</span> -->
<!-- 						<span>(대량 푸시 알람의 앱 과부하를 막기 위해 분할 발송합니다.)</span> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
			
			<div class="text-center">
				<input type="button" value="작성내용 저장" onclick="insertPushDesp()" class="btn btn-default">
				<input type="button" value="발송" class="btn btn-success" onclick="insertPush()">
			</div>
		</div>
	</form>
</body>
</html>