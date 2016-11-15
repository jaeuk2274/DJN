<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 개인정보를 수정하는 화면을 보여주는 JSP입니다.
* 
* history :
*        이인호, 1.0, 2016/08/15 – 초기 작성
* author : 이인호
* version : 1.0, 2016/08/15  - 초기 작성
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
		<form name="empListInfo" class="form-inline" enctype="multipart/form-data">
		<c:forEach var="selectEmpRead" items="${selectEmpRead}" varStatus="status">
			<div class="container">
				<input type="hidden" name="empSeq" id="empSeq" value="${selectEmpRead.empSeq}">
				<div class="container">
					<h3>개인 정보 수정</h3>
					<div style="display:inline-block; margin-top:1%; float:right">
						<h5>
							<img src="/resources/image/common/home.png"> > 개인 정보  > 
							<a href="/vactnList.do" style="text-decoration:none">
								<strong>개인 정보 조회</strong>
							</a>
						</h5>
					</div>
				</div>
				<div class="container form-group">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th class="active">
									<h5 class="text-center"><b>이름</b></h5>
								</th>
								<td><input type="text" class="form-control" name="nme" value="${selectEmpRead.nme}"></td>
							</tr>
							<tr>
								<th class="active">
									<h5 class="text-center"><b>주소</b></h5>
								</th>
								<td>
									<div style="margin-bottom: 10px">
										<input type="text" class="form-control" name="postcd" id="postcd" value="${selectEmpRead.postcd}" placeholder="우편번호">
										<input type="button" class="form-control" onclick="javascript:postcode()" value="우편번호 찾기"><br>
									</div>
									<div>
										<input type="text" class="form-control" name="adrs" id="adrs" value="${selectEmpRead.adrs}" placeholder="주소" style="width:310px">
										<input type="text" class="form-control" name="detlAdrs" id="detlAdrs" value="${selectEmpRead.detlAdrs}" placeholder="상세주소" style="width:300px">
									</div>
								</td>
							</tr>
							<tr>
								<th class="active">
									<h5 class="text-center"><b>전화번호</b></h5>
								</th>
								<td><input type="text" class="form-control" name="tel" onfocus="OnCheckPhone(this)" onKeyup="OnCheckPhone(this)" value="${selectEmpRead.tel}"></td>
							</tr>
							<tr>
								<th class="active">
									<h5 class="text-center"><b>휴대전화</b></h5>
								</th>
								<td><input type="text" class="form-control" name="mobl" id="mobl" onfocus="OnCheckPhone(this)" onKeyup="OnCheckPhone(this)" value="${selectEmpRead.mobl}"></td>
							</tr>
							<tr>
								<th class="active">
									<h5 class="text-center"><b>생년월일</b></h5>
								</th>
								<td><c:out value="${selectEmpRead.birth}"/></td>
							</tr>
							<tr>
								<th class="active">
									<h5 class="text-center"><b>입사일</b></h5>
								</th>
								<td><c:out value="${selectEmpRead.hirDat}"/></td>
							</tr>
							<tr>
								<th class="active">
									<h5 class="text-center"><b>직급</b></h5>
								</th>
								<td><c:out value="${selectEmpRead.duty}"/></td>
							</tr>
							<tr>
								<th class="active">
									<h5 class="text-center"><b>업무</b></h5>
								</th>
								<td><c:out value="${selectEmpRead.task}"/></td>
							</tr>
							<tr>
								<th class="active">
									<h5 class="text-center"><b>사진</b></h5>
								</th>
 								<td>
 									<input type="hidden" name="imgRot" id="imgRot"value="${selectEmpRead.imgRot}"/>
 									<input type="file" class="form-control" name="empImg" id="empImg">
 								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="container text-center">
					<input class="btn btn-default" type="button" value="수정 완료"  onclick="javascript:persnlUpdate()">
					<input class="btn btn-default" type="button" value="취소" onclick="javascript:persnlDataRead()">
				</div>
		</div>
		</c:forEach>
		</form>
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
			
			//수정완료
		    function persnlUpdate(){
		    	if ($("#empNme").val() == "") {
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
				else if ($("#mobl").val() == "") {
	            	alert("휴대전화 번호를 입력하세요!");
	            	$("#mobl").focus();
	            	return;
	            }
				else if ($("#imgRot").val() == "") {
	            	alert("사진을 입력하세요!");
	            	return;
	            } 
				else {
			    	document.empListInfo.method="POST";
			    	document.empListInfo.action="/persnlDataUpdate.do";
			    	document.empListInfo.submit();
				}
		    };
		    
		    //취소
		    function persnlDataRead(){
		    	document.empListInfo.action="/pwCheckDataRead.do";
		    	document.empListInfo.submit();
		    };
		</script>
	</body>
</html>