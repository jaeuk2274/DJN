<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 회원 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history : 최재욱, 1.0, 2016/08/09 – 초기 작성
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
		
		<!-- 주소 api -->		
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		
		<!-- 날짜 시간 선택, datetimepicker 위젯(시작) -->
		<link rel="stylesheet" href="//mugifly.github.io/jquery-simple-datetimepicker/jquery.simple-dtpicker.css">
		<script src="//mugifly.github.io/jquery-simple-datetimepicker/jquery.simple-dtpicker.js"></script>
		<!-- 날짜 시간 선택, datetimepicker 위젯(끝) -->
		
	</head>	
	<body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
	    <div class="container">
			<div class="container">
				<h3>전화 주문 등록</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
				   <h5>
				     <img src="/resources/image/common/home.png"> > 전화 주문 내역 > 
				     <a href="/custmrList.do" style="text-decoration:none"><strong>전화 주문 등록</strong></a>
				   </h5>
				</div>
			</div>
			<div class="container form-inline">
				<form name="custmrSechFrm" method="post">
					<table class="table table-bordered">
						<tr>
							<td class="active">
	                        	<h5 class="text-center"><b>고객 분류</b></h5>
	                     	</td>
							<td colspan="5">
								<input type="radio" name="custmrClassify" value="회원" ${OrdrValueObject.custmrClassify eq "회원" ? "checked" : ""}> 회원
								<input type="radio" name="custmrClassify" value="비회원" ${OrdrValueObject.custmrClassify eq "비회원" ? "checked" : ""}> 비회원
							</td>
						</tr>
						<c:if test="${OrdrValueObject.custmrClassify eq '비회원'}">
							<tr>
								<td class="active">
	                      		  	<h5 class="text-center"><b>이름</b></h5>
	                     		</td>
								<td>
									<input type="text"  class="form-control">
								</td>
								<td class="active">
	                      		  	<h5 class="text-center"><b>연락처</b></h5>
	                     		</td>
								<td>
									<input type="text"  class="form-control">
								</td>
							</tr>
						</c:if>
						<c:if test="${OrdrValueObject.custmrClassify eq '회원'}">
							<tr>
								<td class="active">
	                      		  	<h5 class="text-center"  class="form-control"><b>고객 선택</b></h5>
	                     		</td>
								<td colspan="5">
									<input type="button" class="form-control" onclick="javascript:custmrChoicePopup()" value="회원 선택">
								</td>
							</tr>
							<tr>
								<td class="active">
	                      		  	<h5 class="text-center"><b>ID</b></h5>
	                     		</td>
								<td> <input type="text" class="form-control" name="" value=""> </td>
								<td class="active">
	                      		  	<h5 class="text-center"><b>이름</b></h5>
	                     		</td>
								<td> <input type="text" class="form-control" name="" value=""> </td>
								<td class="active">
	                      		  	<h5 class="text-center"><b>연락처</b></h5>
	                     		</td>
								<td> <input type="text" class="form-control" name="" value=""> </td>
							</tr>
						</c:if>
						<tr>
							<td class="active">
                      		  	<h5 class="text-center"><b>우편번호</b></h5>
                     		</td>
							<td colspan="5">
								<input type="text" class="form-control" id="sample6_postcode">
								<input type="button" class="btn btn-default" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
							</td>
						</tr>
						<tr>
							<td class="active">
                      		  	<h5 class="text-center"><b>주소</b></h5>
                     		</td>
							<td colspan="5">
								<input type="text" class="form-control" id="sample6_address" name="" value=""> 
								<input type="text" class="form-control" id="sample6_address2" name="" value="">
							</td>
						</tr>
						<tr>
							<td class="active">
                      		  	<h5 class="text-center"><b>수령 방법</b></h5>
                     		</td>
							<td>
								<select class="form-control">
									<option value="">배달</option>
									<option value="">직접 수령</option>
								</select>
							</td>
							<td class="active">
                      		  	<h5 class="text-center"><b>결제 방법</b></h5>
                     		</td>
							<td colspan="3">
								<select class="form-control">
									<option value="">현금</option>
									<option value="">카드</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="active">
                      		  	<h5 class="text-center"><b>희망 수령 시간</b></h5>
                     		</td>
							<td colspan="5">
								<input type="text"class='datetimepicker form-control' value="">
							</td>
						</tr>
						<tr>
							<td class="active">
                      		  	<h5 class="text-center"><b>기타 요구사항</b></h5>
                     		</td>
							<td colspan="5">
								<input type="text" class="form-control" value="">
							</td>
						</tr>
					</table>
				</form>
			</div>
			
			<div class="container">
				<h3>상품리스트</h3>
			</div>
			<div class="container text-right">
				<input type="button" class="btn btn-default" value="수량 수정" onclick="javascript:"/>
				<input type="button" class="btn btn-default" value="상품 선택" onclick="javascript:"/>
				<input type="button" class="btn btn-default" value="상품 삭제" onclick="javascript:"/>
			</div>
			
			<div class="form-group"></div>
			
			<div class="container">
				<table class="table table-bordered">
					 <tr class="active">
						<td class="text-center">
						
						</td>
						<td class="text-center">
							 <b>이미지</b>
						 </td>
						<td class="text-center">
							 <b>상품명</b>
						 </td>
						<td class="text-center">
							<b> 수량</b>
						 </td>
						<td class="text-center">
							 <b>가격</b>
						 </td>
						<td class="text-center">
							 <b>총가격</b>
						 </td>
					</tr>
					<tr>
						<td class="text-center">
						
						</td>
						<td class="text-center">
						
						</td>
						<td class="text-center">
						
						</td>
						<td class="text-center">
						
						</td>
						<td class="text-center">
						
						</td>
						<td class="text-center">
						
						</td>
					</tr>
				</table>
			</div>
			<div class="container text-right">
				<h4><b>총 결제금액 : 0원</b></h4>
			</div>
		</div>
		<script>
			$(document).ready(function(){
				$('input:radio[name="custmrClassify"]').change(function(){
				    window.location="/ordrRecrdCreate.do?custmrClassify="+$(this).val();
				});
			});
		
			// 날짜, 시간 선택 위젯 스크립트
			$(function(){
			  $('.datetimepicker').appendDtpicker({'locale':'ko'});
			});
			// 주소 api
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
		    };
			
			// 회원 선택 모달창
			function custmrChoicePopup() {
				var defH, defW, sTop, sLeft, url;
				defW = 500;
				defH = 550;
				sTop = (screen.height - defH)/2;
				sLeft= (screen.width  - defW)/2;
				url = "/";
				popWin = window.open(url, "고객 상세보기", "width="+defW+", height="+defH+", top="+sTop+",left="+sLeft+", scrollbars=yes, marginwidth=0, marginheight=0");
	
				popWin.focus();
			};
		</script>
	</body>
</html>