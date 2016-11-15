<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 직원 정보를 등록하는 화면을 보여주는 JSP입니다.
* 
* history :
*        이인호, 1.0, 2016/08/10 – 초기 작성
* author : 이인호
* version : 1.1, 2016/08/10  - 페이징 추가
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
		
		<!-- 우편 번호 찾기 API -->
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	</head>
	<body>                    
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
		<div class="container">
			<form class="form-inline" name="empListInfo" id="empListInfo" enctype="multipart/form-data">	
				<div class="container">			
					<h3>직원 등록</h3>
					<div style="display:inline-block; margin-top:1%; float:right">
						<h5>
							<img src="/resources/image/common/home.png"> > 직원 관리 > 
							<a href="/empList.do" style="text-decoration:none"><strong>직원 목록</strong></a>
						</h5>
					</div>
				</div>
				
				<div class="container text-left">
					<h3>기본 정보</h3>
					<font class="text-danger">＊필수 입력 정보입니다</font>
				</div>
				
				<div class="container form-group">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th class="active">
									<p class="text-center"><font class="text-danger">＊</font><b>아이디</b></p>
								</th>
								<td class="form-inline"> 
									<input type="text" class="form-control" name="id" id="empId">
									<input type="button" class="form-control" value="중복확인" onclick="javascript:idCheck()">
									<div id="loadtext"></div>
								</td>
							</tr>
							<tr>
								<th class="active">
									<p class="text-center"><font class="text-danger">＊</font><b>비밀번호</b></p>
								</th>
								<td><input type="password" class="form-control" name="pw" id="empPw"></td>
							</tr>
							<tr>
								<th class="active">
									<p class="text-center"><font class="text-danger">＊</font><b>비밀번호 확인</b></p>
								</th>
								<td><input type="password" class="form-control" name="pw_1" id="empPwRe"></td>
							</tr>
							<tr>
								<th class="active">
									<p class="text-center"><font class="text-danger">＊</font><b>이름</b></p>
								</th>
								<td><input type="text" class="form-control" name="nme" id="empNme"></td>
							</tr>
							<tr>
								<th class="active">
									<p class="text-center"><b>성별</b></p>
								</th>
								<td>
									<select name="gendr" class="form-control">
										<option value="남자">남자</option>
										<option value="여자">여자</option>
									</select>
								</td>
							</tr>
							<tr>
								<th class="active">
									<p class="text-center"><font class="text-danger">＊</font><b>주소</b></p>
								</th>
								<td>
									<div style="margin-bottom: 10px">
		 								<input type="text" class="form-control" name="postcd" id="postcd" placeholder="우편번호">
										<input type="button"  class="form-control" onclick="postcode()" value="우편번호 찾기">
									</div>
									<div>
										<input type="text" class="form-control" name="adrs" id="adrs" placeholder="주소" style="width:310px">
										<input type="text" class="form-control" name="detlAdrs" id="detlAdrs" placeholder="상세주소">
									</div>
								</td>
							</tr>
							<tr>
								<th class="active">
									<p class="text-center"><font class="text-danger">＊</font><b>생년월일</b></p>
								</th>
								<td><input type="text" class="form-control" name="birth" id="birth"></td>
							</tr>
							<tr>
								<th class="active">
									<p class="text-center"><font class="text-danger">＊</font><b>입사일</b></p>
								</th>
								<td><input type="text" class="form-control" name="hirDat" id="hirDat"></td>
							</tr>
							<tr>
								<th class="active">
									<p class="text-center"><b>전화번호</b></p>
								</th>
								<td><input type="text" class="form-control" name="tel" onfocus="OnCheckPhone(this)" onKeyup="OnCheckPhone(this)"></td>
							</tr>
							<tr>
								<th class="active">
									<p class="text-center"><font class="text-danger">＊</font><b>휴대전화</b></p>
								</th>
								<td><input type="text" class="form-control" name="mobl" id="mobl" onfocus="OnCheckPhone(this)" onKeyup="OnCheckPhone(this)"></td>
							</tr>
							<tr>
								<th class="active">
									<p class="text-center"><b>직급</b></p>
								</th>
 								<td >
									<select name="duty" class="form-control">
										<option value="사장">사장</option>
										<option value="점장">점장</option>
										<option value="직원">직원</option>
									</select>
								</td>
							</tr>
							<tr>
								<th class="active">
									<p class="text-center"><b>업무</b></p>
								</th>
								<td><input type="text" class="form-control" name="task"></td>
							</tr>
							<tr>
								<th class="active">
										<p class="text-center"><font class="text-danger">＊</font><b>사진</b></p>
									</th> 
 								<td class="form-inline">
										<input type="file" class="form-control" name="empImg" id="empImg">
									</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<div class="container text-left">
				 	<h3>권한 정보</h3>
				</div>
				
				<div class="container form-group">
					<table class="table table-bordered">
						<thead>
							<tr class="active">
								<th class="text-center">매장관리</th>
								<th class="text-center"><input type="checkbox" name="Athrty1"></th>
								<th class="text-center">거래관리</th>
								<th class="text-center"><input type="checkbox" name="Athrty2"></th>
								<th class="text-center">상품관리</th>
								<th class="text-center"><input type="checkbox" name="Athrty3"></th>
								<th class="text-center">고객관리</th>
								<th class="text-center"><input type="checkbox" name="Athrty4"></th>
								<th class="text-center">마감관리</th>
								<th class="text-center"><input type="checkbox" name="Athrty5"></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>직원 관리</td>
								<td class="text-center"><input type="checkbox" class="Athrty1" name="empAthrty" value="on"></td>
								<td>거래처 관리</td>
								<td class="text-center"><input type="checkbox" class="Athrty2" name="clintAthrty" value="on"></td>
								<td>상품 관리</td>
								<td class="text-center"><input type="checkbox" class="Athrty3" name="prodctAthrty" value="on"></td>
								<td>회원 관리</td>
								<td class="text-center"><input type="checkbox" class="Athrty4" name="cutmrAthrty" value="on"></td>
								<td>TODAY 리포트</td>
								<td class="text-center"><input type="checkbox" class="Athrty5" name="todayAthrty" value="on"></td>
							</tr>
							<tr>
								<td>휴가 관리</td>
								<td class="text-center"><input type="checkbox" class="Athrty1" name="vactnAthrty" value="on"></td>
								<td>입고 거래장 관리</td>
								<td class="text-center"><input type="checkbox" class="Athrty2" name="exchngFlorAthrty" value="on"></td>
								<td>행사 관리</td>
								<td class="text-center"><input type="checkbox" class="Athrty3" name="evntAthrty" value="on"></td>
								<td>푸쉬 알림</td>
								<td class="text-center"><input type="checkbox" class="Athrty4" name="pushNotfcatnAthrty" value="on"></td>
								<td>상품 분석</td>
								<td class="text-center"><input type="checkbox" class="Athrty5" name="prodctAnlysAthrty" value="on"></td>
							</tr>
							<tr>
								<td>일정 관리</td>
								<td class="text-center"><input type="checkbox" class="Athrty1" name="schedlAthrty" value="on"></td>
								<td>재고 관리</td>
								<td class="text-center"><input type="checkbox" class="Athrty2" name="stckAthrty" value="on"></td>
								<td>다함께 관리</td>
								<td class="text-center"><input type="checkbox" class="Athrty3" name="togetherAthrty" value="on"></td>
								<td>주문 내역 관리</td>
								<td class="text-center"><input type="checkbox" class="Athrty4" name="ordrRecrdAthrty" value="on"></td>
								<td>주문 분석</td>
								<td class="text-center"><input type="checkbox" class="Athrty5" name="ordrAnlysAthrty" value="on"></td>
							</tr>
							<tr>
								<td>이용약관 관리</td>
								<td class="text-center"><input type="checkbox" class="Athrty1" name="useAgremtAthrty" value="on"></td>
								<td colspan="6"></td>
								<td>매출 분석</td>
								<td class="text-center"><input type="checkbox" class="Athrty5" name="salsAnlysAthrty" value="on"></td>
							</tr>
							<tr>
								<td>대신 장봐주는 남자</td>
								<td class="text-center"><input type="checkbox" class="Athrty1" name="DJNAthrty" value="on"></td>
								<td colspan="8"></td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<div class="container text-center">
					<input type="button" class="btn btn-default" value="등록"  onclick="javascript:insertMembership()">
					<input type="button"  class="btn btn-default" value="취소" onclick="javascript:location.href='/empList.do'">
				</div>
			</form>
		</div>
		
		<script>
			//아이디 중복 체크 AJAX
			function idCheck(){
				if ($("#empId").val() == "") {
			    	var text = '<font style="color:red"><c:out value="아이디를 꼭 입력하세요."/></font>';
					text += '<input type="hidden" id="idCheckResult" name="idCheckResult" value="1">'
					$("#loadtext").html(text);
	        		$("#empId").focus();
			    	return;
			    }
				else if ($("#empId").val().length <= 3|| $("#empId").val().length >12) {
	         		var text = '<font style="color:red"><c:out value="아이디를 4~12자로  입력하세요."/></font>';
					text += '<input type="hidden" id="idCheckResult" name="idCheckResult" value="1">'
					$("#loadtext").html(text);
	        		$("#empId").focus();
	        		return;
	        	}
				//아이디 유효성 검사 (영문소문자 및 숫자만 허용)
				var j;
	          	for (j=0; j<$("#empId").val().length; j++) {
	         		ch=$("#empId").val().charAt(j);
	           		if (!(ch>='0' && ch<='9') && !(ch>='a' && ch<='z')) {
		                $("#empId").focus();
		                var text = '<font style="color:red"><c:out value="아이디는 영문 소문자 및 숫자만 입력 가능합니다."/></font>';
						text += '<input type="hidden" id="idCheckResult" name="idCheckResult" value="1">'
						$("#loadtext").html(text);
		                return;
	             	}
	          	};

				var formData = $("#empListInfo").serialize();
				$.ajax({
					type: "get",
					url: "idCheck.do", //이페이지에서 중복체크를 한다
					data : formData,
					dataType : "JSON",
					
					success: function(data){
					var idCheck = data["idCheck"];
					if(idCheck != 0){
						var text = '<font style="color:red"><c:out value="아이디가 존재합니다."/></font>';
							text += '<input type="hidden" id="idCheckResult" name="idCheckResult" value="1">'
							$("#loadtext").html(text); //해당 내용을 보여준다
					}
					else{
						var	text = '<font style="color:blue"><c:out value="사용가능한 아이디입니다."/></font>';
							text += '<input type="hidden" id="idCheckResult" name="idCheckResult" value="0">'
							$("#loadtext").html(text); //해당 내용을 보여준다
					}
					
					},
				    error:function(xhr,status,error){ //ajax 오류인경우  
			            alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
			   		}
				});
			};
			
			//등록 유효성 검사
			function insertMembership(){
				var j;
	          	for (j=0; j<$("#empId").val().length; j++) {
	         		ch=$("#empId").val().charAt(j);
	           		if (!(ch>='0' && ch<='9') && !(ch>='a' && ch<='z')) {
	           			alert("아이디는 영문 소문자 및 숫자만 입력 가능합니다.");
		                $("#empId").focus();
		                return;
	             	}
	          	};
	          	
		    	if ($("#empId").val() == "") {
		      	  alert("아이디를 입력하세요.");
		      	  $("#empId").focus();
		      	  return;
		        }
		        else if ($("#empId").val().length <= 3|| $("#empId").val().length >12) {
	         	   alert("아이디를 4~12자로  입력하세요.");
	        	   $("#empId").val("");
	        	   $("#empId").focus();
	        	   return;
		        } 
		    	else if($("#idCheckResult").val() == "1") {
		 			alert("아이디 중복확인을 해주세요.");
		 			document.empListInfo.id.focus();
		 			return;
		 		}
	       	  	else if ($("#empPw").val() == "") {
	            	alert("비밀번호를 입력하세요!");
	            	$("#empPw").focus();
	            	return;
	         	}
	       		else if ($("#empPw").val().length <= 6|| $("#empPw").val().length >12) {
	            	alert("비밀번호를 6~12자로  입력하세요.");
	            	$("#empPw").focus();
	            	return;
	         	}
	            else if ($("#empPwRe").val() == "") {
	            	alert("비밀번호 확인을 입력하세요!");
	            	$("#empPwRe").focus();
	            	return;
	            } 
	            else if ($("#empPw").val() != $("#empPwRe").val()) {
	            	alert("비밀번호와 비밀번호 확인이 일치하지않습니다.");
	            	$("#empPw").val("");
	            	$("#empPwRe").val("");
	            	$("#empPw").focus();
	            	return;
	            } 
				else if ($("#empNme").val() == "") {
	            	alert("이름을 입력하세요!");
	            	$("#empNme").focus();
	            	return;
	            }
				else if ($("#postcd").val() == "") {
	            	alert("우편번호를 입력하세요!");
	            	$("#postcd").focus();
	            	return;
	            } 
				else if ($("#adrs").val() == "") {
	            	alert("주소를 입력하세요!");
	            	$("#adrs").focus();
	            	return;
	            } 
				else if ($("#birth").val() == "") {
	            	alert("생년월일을 입력하세요!");
	            	$("#birth").focus();
	            	return;
	            } 
				else if ($("#hirDat").val() == "") {
	            	alert("입사일을 입력하세요!");
	            	$("#hirDat").focus();
	            	return;
	            } 
				else if ($("#mobl").val() == "") {
	            	alert("휴대전화 번호를 입력하세요!");
	            	$("#mobl").focus();
	            	return;
	            }
				else if ($("#empImg").val() == "") {
	            	alert("사진을 입력하세요!");
	            	$("#empImg").focus();
	            	return;
	            } 
				else {
	           		document.empListInfo.method="POST";
	           		document.empListInfo.action = "/empCreate.do";
	    			document.empListInfo.submit();
	            }
			};
			
			//전화번호 자동 하이픈 넣기
			function OnCheckPhone(oTa) { 
			    var oForm = oTa.form ; 
			    var sMsg = oTa.value ; 
			    var onlynum = "" ; 
			    var imsi=0; 
			    onlynum = RemoveDash2(sMsg);  //하이픈 입력시 자동으로 삭제함 
			    onlynum =  checkDigit(onlynum);  // 숫자만 입력받게 함 
			    var retValue = ""; 

			    if(event.keyCode != 12 ) { 
			        if(onlynum.substring(0,2) == 02) {  // 서울전화번호일 경우  10자리까지만 나타나고 그 이상의 자리수는 자동삭제 
			                if (GetMsgLen(onlynum) <= 1) oTa.value = onlynum ; 
			                if (GetMsgLen(onlynum) == 2) oTa.value = onlynum + "-"; 
			                if (GetMsgLen(onlynum) == 4) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,3) ; 
			                if (GetMsgLen(onlynum) == 4) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,4) ; 
			                if (GetMsgLen(onlynum) == 5) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,5) ; 
			                if (GetMsgLen(onlynum) == 6) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,6) ; 
			                if (GetMsgLen(onlynum) == 7) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,5) + "-" + onlynum.substring(5,7) ; ; 
			                if (GetMsgLen(onlynum) == 8) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,6) + "-" + onlynum.substring(6,8) ; 
			                if (GetMsgLen(onlynum) == 9) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,5) + "-" + onlynum.substring(5,9) ; 
			                if (GetMsgLen(onlynum) == 10) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,6) + "-" + onlynum.substring(6,10) ; 
			                if (GetMsgLen(onlynum) == 11) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,6) + "-" + onlynum.substring(6,10) ; 
			                if (GetMsgLen(onlynum) == 12) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,6) + "-" + onlynum.substring(6,10) ; 
			        } 
			        if(onlynum.substring(0,2) == 05 ) {  // 05로 시작되는 번호 체크 
			            if(onlynum.substring(2,3) == 0 ) {  // 050으로 시작되는지 따지기 위한 조건문 
			                    if (GetMsgLen(onlynum) <= 3) oTa.value = onlynum ; 
			                    if (GetMsgLen(onlynum) == 4) oTa.value = onlynum + "-"; 
			                    if (GetMsgLen(onlynum) == 5) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,5) ; 
			                    if (GetMsgLen(onlynum) == 6) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,6) ; 
			                    if (GetMsgLen(onlynum) == 7) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,7) ; 
			                    if (GetMsgLen(onlynum) == 8) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,8) ; 
			                    if (GetMsgLen(onlynum) == 9) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,7) + "-" + onlynum.substring(7,9) ; ; 
			                    if (GetMsgLen(onlynum) == 10) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,8) + "-" + onlynum.substring(8,10) ; 
			                    if (GetMsgLen(onlynum) == 11) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,7) + "-" + onlynum.substring(7,11) ; 
			                    if (GetMsgLen(onlynum) == 12) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,8) + "-" + onlynum.substring(8,12) ; 
			                    if (GetMsgLen(onlynum) == 13) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,8) + "-" + onlynum.substring(8,12) ; 
			          } else { 
			                if (GetMsgLen(onlynum) <= 2) oTa.value = onlynum ; 
			                if (GetMsgLen(onlynum) == 3) oTa.value = onlynum + "-"; 
			                if (GetMsgLen(onlynum) == 4) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,4) ; 
			                if (GetMsgLen(onlynum) == 5) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,5) ; 
			                if (GetMsgLen(onlynum) == 6) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) ; 
			                if (GetMsgLen(onlynum) == 7) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) ; 
			                if (GetMsgLen(onlynum) == 8) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,8) ; ; 
			                if (GetMsgLen(onlynum) == 9) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,9) ; 
			                if (GetMsgLen(onlynum) == 10) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,10) ; 
			                if (GetMsgLen(onlynum) == 11) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,11) ; 
			                if (GetMsgLen(onlynum) == 12) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,11) ; 
			          } 
			        } 

			        if(onlynum.substring(0,2) == 01) {  //휴대폰일 경우 
			            if (GetMsgLen(onlynum) <= 2) oTa.value = onlynum ; 
			            if (GetMsgLen(onlynum) == 3) oTa.value = onlynum + "-"; 
			            if (GetMsgLen(onlynum) == 4) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,4) ; 
			            if (GetMsgLen(onlynum) == 5) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,5) ; 
			            if (GetMsgLen(onlynum) == 6) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) ; 
			            if (GetMsgLen(onlynum) == 7) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) ; 
			            if (GetMsgLen(onlynum) == 8) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,8) ; 
			            if (GetMsgLen(onlynum) == 9) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,9) ; 
			            if (GetMsgLen(onlynum) == 10) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,10) ; 
			            if (GetMsgLen(onlynum) == 11) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,11) ; 
			            if (GetMsgLen(onlynum) == 12) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,11) ; 
			        } 
			    } 
			};
			
			//하이픈 자동 제거
			function RemoveDash2(sNo) { 
			var reNo = "" 
			for(var i=0; i<sNo.length; i++) { 
			    if ( sNo.charAt(i) != "-" ) { 
			    reNo += sNo.charAt(i) 
			    } 
			} 
			return reNo 
			};

			function GetMsgLen(sMsg) { // 0-127 1byte, 128~ 2byte 
			var count = 0 
			    for(var i=0; i<sMsg.length; i++) { 
			        if ( sMsg.charCodeAt(i) > 127 ) { 
			            count += 2 
			        } 
			        else { 
			            count++ 
			        } 
			    } 
			return count 
			};
			
			//숫자 확인
			function checkDigit(num) { 
			    var Digit = "1234567890"; 
			    var string = num; 
			    var len = string.length 
			    var retVal = ""; 

			    for (i = 0; i < len; i++) 
			    { 
			        if (Digit.indexOf(string.substring(i, i+1)) >= 0) 
			        { 
			            retVal = retVal + string.substring(i, i+1); 
			        } 
			    } 
			    return retVal; 
			};
		
		    function postcode() {
		        new daum.Postcode({
		            oncomplete: function(data) {
		                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
		
		                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var fullAddr = ''; // 최종 주소 변수
		                var extraAddr = ''; // 조합형 주소 변수
		
		                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
		                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
		                    fullAddr = data.roadAddress;
		
		                } else { // 사용자가 지번 주소를 선택했을 경우(J)
		                    fullAddr = data.jibunAddress;
		                }
		
		                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
		                if(data.userSelectedType === 'R'){
		                    //법정동명이 있을 경우 추가한다.
		                    if(data.bname !== ''){
		                        extraAddr += data.bname;
		                    }
		                    // 건물명이 있을 경우 추가한다.
		                    if(data.buildingName !== ''){
		                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                    }
		                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
		                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
		                }
		
		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                document.getElementById('postcd').value = data.zonecode; //5자리 새우편번호 사용
		                document.getElementById('adrs').value = fullAddr;
		
		                // 커서를 상세주소 필드로 이동한다.
		                document.getElementById('detlAdrs').focus();
		            }
		        }).open();
		    };

		    $(document).ready(function() {   
		        //체크박스 전체클릭
		         $("input:checkbox[name=Athrty1]").click(function(){
					var vals = $("input:checkbox[name=Athrty1]").attr("name");
					if($("input:checkbox[name=Athrty1]").prop("checked")) {
						$("input:checkbox[class='Athrty1']").prop("checked",true);
					}
					else{
						 $("input:checkbox[class='Athrty1']").prop("checked",false);
					}
		         });
		         $("input:checkbox[name=Athrty2]").click(function(){
					var vals = $("input:checkbox[name=Athrty2]").attr("name");
					if($("input:checkbox[name=Athrty2]").prop("checked")) {
						$("input:checkbox[class='Athrty2']").prop("checked",true);
					}
					else{
						 $("input:checkbox[class='Athrty2']").prop("checked",false);
					}
		         });
		         $("input:checkbox[name=Athrty3]").click(function(){
					var vals = $("input:checkbox[name=Athrty3]").attr("name");
					if($("input:checkbox[name=Athrty3]").prop("checked")) {
						$("input:checkbox[class='Athrty3']").prop("checked",true);
					}
					else{
						 $("input:checkbox[class='Athrty3']").prop("checked",false);
					}
		         });
		         $("input:checkbox[name=Athrty4]").click(function(){
					var vals = $("input:checkbox[name=Athrty4]").attr("name");
					if($("input:checkbox[name=Athrty4]").prop("checked")) {
						$("input:checkbox[class='Athrty4']").prop("checked",true);
					}
					else{
						 $("input:checkbox[class='Athrty4']").prop("checked",false);
					}
		         });
		         $("input:checkbox[name=Athrty5]").click(function(){
					var vals = $("input:checkbox[name=Athrty5]").attr("name");
					if($("input:checkbox[name=Athrty5]").prop("checked")) {
						$("input:checkbox[class='Athrty5']").prop("checked",true);
					}
					else{
						 $("input:checkbox[class='Athrty5']").prop("checked",false);
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
		        $("#birth").datepicker();
		        $("#hirDat").datepicker();
		    }); 
		</script>
	</body>
</html>