<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 직원 정보를 수정하는 화면을 보여주는 JSP입니다.
* 
* history :
*        이인호, 1.0, 2016/08/12 – 초기 작성
* author : 이인호
* version : 1.0, 2016/08/12  - 초기 작성
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
		
		<!-- 우편번호 찾기 -->
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	</head>
	<body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
		<div class="container">
			<!-- 직원 정보 -->
			<div class="container">
				<h3>직원 정보 수정</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
					<h5>
						<img src="/resources/image/common/home.png"> > 직원 관리 > 
						<a href="/empList.do" style="text-decoration:none"><strong>직원 목록</strong></a>
					</h5>
				</div>
			</div>
			
			
			<form name="empListInfo" class="form-inline" enctype="multipart/form-data" method="post">
				<c:forEach var="selectEmpRead" items="${selectEmpRead}" varStatus="status">
				<input type="hidden" name="empSeq" id="empSeq" value="${selectEmpRead.empSeq}">
				<div class="container form-group">
					<table class="table table-bordered">
						<tr>
							<td class="active">
								<h5 class="text-center"><b>아이디</b></h5>
							</td>
							<td><input type="text" class="form-control" name="id" id="empId"  value="${selectEmpRead.id}"/></td>
							<td class="active">
								<h5 class="text-center"><b>이름</b></h5>
							</td>
							<td><input type="text" class="form-control" name="nme" id="empNme" value="${selectEmpRead.nme}"/></td>
							<td class="active">
								<h5 class="text-center"><b>생년월일</b></h5>
							</td>
							<td><input type="date" class="form-control" name="birth" id="birth"  value="${selectEmpRead.birth}"/></td>
						</tr>
						<tr>
							<td class="active">
								<h5 class="text-center"><b>성별</b></h5>
							</td>
							<td><c:out value="${selectEmpRead.gendr}"/></td>
							<td class="active">
								<h5 class="text-center"><b>전화번호</b></h5>
							</td>
							<td><input type="text" class="form-control" name="tel" value="${selectEmpRead.tel}" onfocus="OnCheckPhone(this)" onKeyup="OnCheckPhone(this)"/></td>
							<td class="active">
								<h5 class="text-center"><b>휴대전화</b></h5>
							</td>
							<td><input type="text" class="form-control" name="mobl" id="mobl" value="${selectEmpRead.mobl}" onfocus="OnCheckPhone(this)" onKeyup="OnCheckPhone(this)"/></td>
						</tr>
						<tr>
							<td class="active">
								<h5 class="text-center"><b>입사일</b></h5>
							</td>
							<td><input type="text" class="form-control" name="hirDat" id="hirDat" value="${selectEmpRead.hirDat}"/></td>
							<td class="active">
								<h5 class="text-center"><b>직급</b></h5>
							</td>
							<td>
							<select class="form-control" name="duty">
								<c:choose>
									<c:when test="${selectEmpRead.duty == '사장'}">
										<option value="사장" selected="selected">사장</option>
										<option value="점장">점장</option>
										<option value="직원">직원</option>
									</c:when>
									<c:when test="${selectEmpRead.duty == '점장'}">
										<option value="사장">사장</option>
										<option value="점장" selected="selected">점장</option>
										<option value="직원">직원</option>
									</c:when>
									<c:when test="${selectEmpRead.duty == '직원'}">
										<option value="사장">사장</option>
										<option value="점장">점장</option>
										<option value="직원" selected="selected">직원</option>
									</c:when>
								</c:choose>
							</select>
							</td>
							<td class="active">
								<h5 class="text-center"><b>업무</b></h5>
							</td>
							<td><input type="text" class="form-control" name="task" value="${selectEmpRead.task}"/></td>
						</tr>
						<tr>
							<td class="active">
								<h5 class="text-center"><b>주소</b></h5>
							</td>
							<td colspan="5">
								<div style="margin-bottom:10px">
									<input type="text" class="form-control" name="postcd" id="postcd" placeholder="우편번호"  value="${selectEmpRead.postcd}">
									<input type="button" class="form-control" onclick="postcode()" value="우편번호 찾기">
								</div>
								<div>
									<input type="text" class="form-control" name="adrs" id="adrs" placeholder="주소" style="width:350px" value="${selectEmpRead.adrs}">
									<input type="text" class="form-control" name="detlAdrs" id="detlAdrs" placeholder="상세주소" style="width:350px" value="${selectEmpRead.detlAdrs}">
								</div>
							</td>
						</tr>
						<tr>
							<td class="active">
								<h5 class="text-center"><b>사진</b></h5>
							</td>
							<td colspan="5">
								<input type="hidden" class="form-control" name="imgRot" id="imgRot" value="${selectEmpRead.imgRot}"/>
								<input type="file" class="form-control" name="empImg" id="empImg">
							</td>
						</tr>
					</table>
				</div>
				
				<!-- 메뉴 권한 -->
				<div class="container">
				 	<h3>메뉴 권한 관리</h3>
				</div>
				<div class="container">
					<table class="table table-bordered">
						<thead>
							<tr class="active">
								<th class="text-center" colspan="2">매장관리</th>
								<th class="text-center" colspan="2">거래관리</th>
								<th class="text-center" colspan="2">상품관리</th>
								<th class="text-center" colspan="2">고객관리</th>
								<th class="text-center" colspan="2">마감관리</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>직원 관리</td>
								<c:choose>
									<c:when test="${selectEmpRead.empAthrty == 'on'}">
										<td class="text-center"><input type="checkbox"  name="empAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.empAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="empAthrty"></td>
									</c:when>
								</c:choose>
								<td>거래처 관리</td>
								<c:choose>
									<c:when test="${selectEmpRead.clintAthrty == 'on'}">
										<td class="text-center"><input type="checkbox" name="clintAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.clintAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="clintAthrty"></td>
									</c:when>
								</c:choose>
								<td>상품 관리</td>
								<c:choose>
									<c:when test="${selectEmpRead.prodctAthrty == 'on'}">
										<td class="text-center"><input type="checkbox" name="prodctAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.prodctAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="prodctAthrty"></td>
									</c:when>
								</c:choose>
								<td>회원 관리</td>
								<c:choose>
									<c:when test="${selectEmpRead.cutmrAthrty == 'on'}">
										<td class="text-center"><input type="checkbox" name="cutmrAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.cutmrAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="cutmrAthrty"></td>
									</c:when>
								</c:choose>
								<td>TODAY 리포트</td>
								<c:choose>
									<c:when test="${selectEmpRead.todayAthrty == 'on'}">
										<td class="text-center"><input type="checkbox" name="todayAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.todayAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="todayAthrty"></td>
									</c:when>
								</c:choose>
							</tr>
							<tr>
								<td>휴가 관리</td>
								<c:choose>
									<c:when test="${selectEmpRead.vactnAthrty == 'on'}">
										<td class="text-center"><input type="checkbox" name="vactnAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.vactnAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="vactnAthrty"></td>
									</c:when>
								</c:choose>
								<td>입고 거래장 관리</td>
								<c:choose>
									<c:when test="${selectEmpRead.exchngFlorAthrty == 'on'}">
										<td class="text-center"><input type="checkbox" name="exchngFlorAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.exchngFlorAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="exchngFlorAthrty"></td>
									</c:when>
								</c:choose>
								<td>행사 관리</td>
								<c:choose>
									<c:when test="${selectEmpRead.evntAthrty == 'on'}">
										<td class="text-center"><input type="checkbox" name="evntAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.evntAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="evntAthrty"></td>
									</c:when>
								</c:choose>
								<td>푸쉬 알림</td>
								<c:choose>
									<c:when test="${selectEmpRead.pushNotfcatnAthrty == 'on'}">
										<td class="text-center"><input type="checkbox" name="pushNotfcatnAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.pushNotfcatnAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="pushNotfcatnAthrty"></td>
									</c:when>
								</c:choose>
								<td>상품 분석</td>
								<c:choose>
									<c:when test="${selectEmpRead.prodctAnlysAthrty == 'on'}">
										<td class="text-center"><input type="checkbox" name="prodctAnlysAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.prodctAnlysAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="prodctAnlysAthrty"></td>
									</c:when>
								</c:choose>
							</tr>
							<tr>
								<td>일정 관리</td>
								<c:choose>
									<c:when test="${selectEmpRead.schedlAthrty == 'on'}">
										<td class="text-center"><input type="checkbox" name="schedlAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.schedlAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="schedlAthrty"></td>
									</c:when>
								</c:choose>
								<td>재고 관리</td>
								<c:choose>
									<c:when test="${selectEmpRead.stckAthrty == 'on'}">
										<td class="text-center"><input type="checkbox" name="stckAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.stckAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="stckAthrty"></td>
									</c:when>
								</c:choose>
								<td>다함께 관리</td>
								<c:choose>
									<c:when test="${selectEmpRead.togetherAthrty == 'on'}">
										<td class="text-center"><input type="checkbox" name="togetherAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.togetherAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="togetherAthrty"></td>
									</c:when>
								</c:choose>
								<td>주문 내역 관리</td>
								<c:choose>
									<c:when test="${selectEmpRead.ordrRecrdAthrty == 'on'}">
										<td class="text-center"><input type="checkbox" name="ordrRecrdAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.ordrRecrdAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="ordrRecrdAthrty"></td>
									</c:when>
								</c:choose>
								<td>주문 분석</td>
								<c:choose>
									<c:when test="${selectEmpRead.ordrAnlysAthrty == 'on'}">
										<td class="text-center"><input type="checkbox" name="ordrAnlysAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.ordrAnlysAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="ordrAnlysAthrty"></td>
									</c:when>
								</c:choose>
							</tr>
							<tr>
								<td>이용약관 관리</td>
								<c:choose>
									<c:when test="${selectEmpRead.useAgremtAthrty == 'on'}">
										<td class="text-center"><input type="checkbox" name="useAgremtAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.useAgremtAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="useAgremtAthrty"></td>
									</c:when>
								</c:choose>
								<td colspan="6"></td>
								<td>매출 분석</td>
								<c:choose>
									<c:when test="${selectEmpRead.salsAnlysAthrty == 'on'}">
										<td class="text-center"><input type="checkbox" name="salsAnlysAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.salsAnlysAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="salsAnlysAthrty"></td>
									</c:when>
								</c:choose>
							</tr>
							<tr>
								<td>대신 장봐주는 남자</td>
								<c:choose>
									<c:when test="${selectEmpRead.DJNAthrty == 'on'}">
										<td class="text-center"><input type="checkbox" name="DJNAthrty" checked="checked"></td>
									</c:when>
									<c:when test="${selectEmpRead.DJNAthrty == 'N'}">
										<td class="text-center"><input type="checkbox" name="DJNAthrty"></td>
									</c:when>
								</c:choose>
								<td colspan="8"></td>
							</tr>
						</tbody>
					</table>
				</div>
				</c:forEach>
				<div class="container text-center">
					<input type="button" class="btn btn-default" value="완  료" onclick="javascript:empUpdate();">
					<input type="button" class="btn btn-default" value="취  소" id="btn_cancel">
				</div>
			</form>
		</div>
		<script>
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
		    
		    function empUpdate() {
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
		      	  alert("아이디를 입력하세요!");
		      	  $("#empId").focus();
		      	  return;
		        }
		        else if ($("#empId").val().length <= 3|| $("#empId").val().length >12) {
		         	   alert("아이디를 4자리 이상 입력하세요!");
		        	    $("#empId").val("");
		        	    $("#empId").focus();
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
				else {
			    	document.empListInfo.method="POST";
	           		document.empListInfo.action = "/empUpdate.do";
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
				 	
			//제이쿼리
			$(document).ready(function(){
	            $('#btn_cancel').click(function() {
	                var url = "/empRead.do";    
	                var empSeq = $("#empSeq").val();
	                $(":hidden[name='empSeq']").val(empSeq);
	                $("[name='empListInfo']").attr('action',url);
	                $("[name='empListInfo']").submit();
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