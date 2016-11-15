<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 회원 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        최재욱, 1.0, 2016/08/09 – 초기 작성
* author : 최재욱
* version : 1.0, 2016/08/09  - 초기 작성
* see : 회원 목록 조회 화면.
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
		
		<!-- datepicker 위젯 -->			
		<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
	</head>
	<body>
		<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
			<h3>회원 수정하기</h3>
		</div>
			
		<div class="form-group"></div>
		
		<div class="container text-center">
			<h4>${CustmrReadVO.custmrNme}님 회원 정보</h4>
		</div>
		
		<div class="form-group"></div>
		
		<form name="custmrUpdateFrm" method="post">
			<div class="container">
				<input type="hidden" name="custmrSeq" value="${CustmrReadVO.custmrSeq}"> <!-- 고객 seq --> 
				<table class="table table-bordered">
					<tr>
						<th class="active"> 이름 </th>
						<td> ${CustmrReadVO.custmrNme} </td>
					</tr>
					<tr>
						<th class="active"> 아이디 </th>
						<td> ${CustmrReadVO.custmrId} </td>
					</tr>
					<tr>
						<th class="active"> 성별 </th>
						<td> ${CustmrReadVO.custmrGendr} </td>
					</tr>
					<tr>
						<th class="active"> 휴대전화 </th>
						<td> <input type="tel" class="form-control" name="custmrMobl" value="${CustmrReadVO.custmrMobl}">  </td>
					</tr>
					<tr>
						<th class="active"> 주소 </th>
						<td> 
							<ul class="list-inline" >
							  <li><input type="text" class="form-control" style="width:160px" id="sample6_postcode" name="custmrPostcd" value="${CustmrReadVO.custmrPostcd}"> </li>
							  <li><button class="btn btn-default" onclick="sample6_execDaumPostcode()">우편번호 찾기</button></li>
							</ul>		
							<input type="text" class="form-control" id="sample6_address" name="custmrAdrs" value="${CustmrReadVO.custmrAdrs}">
							<input type="text" class="form-control" id="sample6_address2" name="custmrDetalAdrs" value="${CustmrReadVO.custmrDetalAdrs}">
						</td>
					</tr>
					<tr>
						<th class="active"> 생년월일 </th>
						<td>
							<input type="text" class="form-control" id="datepicker1" name="custmrBirth" value="${CustmrReadVO.custmrBirth}">
						</td>
					</tr>
					<tr>
						<th class="active"> 이메일 </th>
						<td> <input type="email" class="form-control" name="custmrEml" value="${CustmrReadVO.custmrEml}"> </td>
					</tr>
					<tr>
						<th class="active"> 카드여부 </th>
						<td> 
							<select class="form-control col-lg-6 col-sm-4" name="custmrCardCheck">
								<option value="Y" ${CustmrReadVO.custmrCardCheck eq "Y" ? "selected" : ""}> 보유 </option>
								<option value="N" ${CustmrReadVO.custmrCardCheck eq "N" ? "selected" :""}> 미보유 </option>
								<option value="R" ${CustmrReadVO.custmrCardCheck eq "R" ? "selected" :""}> 요청 </option>
							</select>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<div class="container text-center">
			<button class="btn btn-default" onclick="javacript:custmrUpdate()">수정 완료</button>
			<button class="btn btn-default" onclick="window.location='/custmrReadPopup.do?custmrSeq=${CustmrReadVO.custmrSeq}'">취소</button>
		</div>
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script>
			function sample6_execDaumPostcode() {
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
		                document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
		                document.getElementById('sample6_address').value = fullAddr;
	
		                // 커서를 상세주소 필드로 이동한다.
		                document.getElementById('sample6_address2').focus();
		            }
		        }).open();
		    }
			
			function custmrUpdate() {
				document.custmrUpdateFrm.action ="/custmrUpdate.do";
				document.custmrUpdateFrm.submit();
			}
			
			function cancel(custmrSeq) {
				window.location="/custmrReadPopup.do?custmrSeq="+custmrSeq;			
			}
			
			// 날짜 선택 위젯
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
		        $("#datepicker1").datepicker();
		    });
		</script>
	</body>
</html>