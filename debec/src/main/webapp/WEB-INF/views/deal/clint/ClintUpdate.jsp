<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!-- 
* 거래처정보를 수정하는 화면을 보여주는 JSP입니다.
* 
* history :
*        김대현, 1.0, 2016/08/17 – 초기 작성
* author : 김대현
* version : 1.0, 2016/08/17  - 초기 작성
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
		<!-- 우편번호 -->
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		
	</head>
	<body>
		<div style="margin-bottom:130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		<div class="container">
			<form  class="form-inline" name="clintInfoFrm" method="post">
				<input type="hidden" value="${clintVo.clintSeq}" name="clintSeq">
	<%-- 			<input type="hidden" value="${clintVo.clintNme}" name="clintNme"> --%>
	<%-- 			<input type="hidden" value="${clintVo.clintRepresntatv}" name="clintRepresntatv"> --%>
	<%-- 			<input type="hidden" value="${clintVo.clintMangr}" name="clintMangr"> --%>
	<%-- 			<input type="hidden" value="${clintVo.clintNum}" name="clintNum"> --%>
	<%-- 			<input type="hidden" value="${clintVo.clintMangrNum}" name="clintMangrNum"> --%>
	<%-- 			<input type="hidden" value="${clintVo.clintFax}" name="clintFax"> --%>
	<%-- 			<input type="hidden" value="${clintVo.clintBankAcout}" name="clintBankAcout"> --%>
	<%-- 			<input type="hidden" value="${clintVo.clintAcoutNum}" name="clintAcoutNum"> --%>
	<%-- 			<input type="hidden" value="${clintVo.clintSurtaxCheck}" name="clintSurtaxCheck"> --%>
	<%-- 			<input type="hidden" value="${clintVo.clintPostcd}" name="clintPostcd"> --%>
	<%-- 			<input type="hidden" value="${clintVo.clintAdrs}" name="clintAdrs"> --%>
	<%-- 			<input type="hidden" value="${clintVo.clintDetl}" name="clintDetl"> --%>
				
				
				<div class="container">
					<h3>거래처 정보 수정</h3>
					<div style="display:inline-block; margin-top:1%; float:right">
					   <h5>
					     <img src="/resources/image/common/home.png"> > 거래처관리 > 
					     <a href="/ClintList.do" style="text-decoration:none"><strong>거래처 목록</strong></a>
					   </h5>
					</div>
				</div>
				<div class="container form-group">
					<table class="table table-bordered">
						<colgroup>
							<col class="col-md-2"/>                
						    <col class="col-md-2"/>          
						    <col class="col-md-2"/>           
						    <col class="col-md-2"/>
						    <col class="col-md-2"/>          
						    <col class="col-md-2"/>
					   </colgroup>
						<tr>
							<td class="active">
		                      	<h5 class="text-center"><b>거래처명</b></h5>
		                   	</td>
							<td><input type="text" class="form-control" value="${clintVo.clintNme}" name="clintNme"></td>
							<td class="active">
		                      	<h5 class="text-center"><b>대표자</b></h5>
		                   	</td>
							<td><input type="text" class="form-control" value="${clintVo.clintRepresntatv}" name="clintRepresntatv"></td>
							<td class="active">
		                      	<h5 class="text-center"><b>담당자</b></h5>
		                   	</td>
							<td><input type="text" class="form-control" value="${clintVo.clintMangr}" name="clintMangr"></td>
						</tr>
						<tr>
							<td class="active">
		                      	<h5 class="text-center"><b>거래처 번호</b></h5>
		                   	</td>
							<td><input type="text" class="form-control" value="${clintVo.clintNum}" name="clintNum"></td>
							<td class="active">
		                      	<h5 class="text-center"><b>담당자 번호</b></h5>
		                   	</td>
							<td><input type="text" class="form-control" value="${clintVo.clintMangrNum}" name="clintMangrNum"></td>
							<td class="active">
		                      	<h5 class="text-center"><b>팩스</b></h5>
		                   	</td>
							<td><input type="text" class="form-control" value="${clintVo.clintFax}" name="clintFax"></td>
						</tr>
						<tr>
							<td class="active">
		                      	<h5 class="text-center"><b>계좌 은행</b></h5>
		                   	</td>
							<td><input type="text" class="form-control" value="${clintVo.clintBankAcout}" name="clintBankAcout"></td>
							<td class="active">
		                      	<h5 class="text-center"><b>계좌 번호</b></h5>
		                   	</td>
							<td><input type="text" class="form-control" value="${clintVo.clintAcoutNum}" name="clintAcoutNum"></td>
							<td class="active">
		                      	<h5 class="text-center"><b>부가세</b></h5>
		                   	</td>
							<td><input type="text" class="form-control" value="${clintVo.clintSurtaxCheck}" name="clintSurtaxCheck"></td>
						</tr>
						<tr>
							<td class="active">
		                      	<h5 class="text-center"><b>주소</b></h5>
		                   	</td>
							<td colspan="5">
								<input type="text" class="form-control" id="postcode" value="${clintVo.clintPostcd}" name="clintPostcd" placeholder="우편번호">
								<input type="button" class="form-control" onclick="javascript:execDaumPostcode()" value="우편번호 찾기">
								<input type="text" class="form-control" id="address" value="${clintVo.clintAdrs}" name="clintAdrs" placeholder="주소" style="width:310px">
								<input type="text" class="form-control" id="address2" value="${clintVo.clintDetl}" name="clintDetl" placeholder="상세주소" style="width:300px">
							</td>
						</tr>
					</table>
					</div>
			</form>
			<div class="container text-center">
				<input type="button" class="btn btn-default" value="완 료" onclick="javascript:clintUpdate()">
				<input type="button" class="btn btn-default" value="취 소" onclick="javascript:clintUpdateCancel()">
			</div>
		</div>
		<script>
		    function execDaumPostcode() {
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
		                document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
		                document.getElementById('address').value = fullAddr;
		
		                // 커서를 상세주소 필드로 이동한다.
		                document.getElementById('address2').focus();
		            }
		        }).open();
		    };
		    
			// 거래처 정보 수정완료
		    function clintUpdate(){
		    	document.clintInfoFrm.action="/ClintUpdate.do";
		    	document.clintInfoFrm.submit();
		    };
		    
			// 거래처 정보 수정취소		    
		    function clintUpdateCancel(){
		    	document.clintInfoFrm.action="/ClintRead.do";
		    	document.clintInfoFrm.submit();
		    };
		</script>
	</body>
</html>




