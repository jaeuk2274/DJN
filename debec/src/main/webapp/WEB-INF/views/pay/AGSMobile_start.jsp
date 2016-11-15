<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="kr.co.allthegate.mobile.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi"/>
<META content=telephone=no name=format-detection>
<title>결제 페이지 샘플</title>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<script type="text/javascript" charset="utf-8">
	function doPay(form) {
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 올더게이트 플러그인 설정값을 동적으로 적용하기 JavaScript 코드를 사용하고 있습니다.
		// 상점설정에 맞게 JavaScript 코드를 수정하여 사용하십시오.
		//
		// [1] 일반/무이자 결제여부
		// [2] 일반결제시 할부개월수
		// [3] 무이자결제시 할부개월수 설정
		// [4] 인증여부
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// [1] 일반/무이자 결제여부를 설정합니다.
		//
		// 할부판매의 경우 구매자가 이자수수료를 부담하는 것이 기본입니다. 
		// 그러나, 상점과 올더게이트간의 별도 계약을 통해서 할부이자를 상점측에서 부담할 수 있습니다.
		// 이 경우 구매자는 무이자 할부거래가 가능합니다.
		//
		// 예제)
		// 	(1) 일반결제로 사용할 경우
		// 	form.DeviId.value = "9000400001";
		//
		// 	(2) 무이자결제로 사용할 경우
		// 	form.DeviId.value = "9000400002";
		//
		// 	(3) 만약 결제 금액이 100,000원 미만일 경우 일반할부로, 100,000원 이상일 경우 무이자할부로 사용할 경우
		// 	if(parseInt(form.Amt.value) < 100000)
		//		form.DeviId.value = "9000400001";
		// 	else
		//		form.DeviId.value = "9000400002";
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// [2] 일반 할부기간을 설정합니다.
		// 
		// 일반 할부기간은 2 ~ 12개월까지 가능합니다.
		// 0:일시불, 2:2개월, 3:3개월, ... , 12:12개월
		// 
		// 예제)
		// 	(1) 할부기간을 일시불만 가능하도록 사용할 경우
		// 		form.QuotaInf.value = "0";
		//
		// 	(2) 할부기간을 일시불 ~ 12개월까지 사용할 경우
		//		form.QuotaInf.value = "0:2:3:4:5:6:7:8:9:10:11:12";
		//
		// 	(3) 결제금액이 일정범위안에 있을 경우에만 할부가 가능하게 할 경우
		// 	if((parseInt(form.Amt.value) >= 100000) || (parseInt(form.Amt.value) <= 200000))
		// 		form.QuotaInf.value = "0:2:3:4:5:6:7:8:9:10:11:12";
		// 	else
		// 		form.QuotaInf.value = "0";
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//결제금액이 5만원 미만건을 할부결제로 요청할경우 일시불로 결제
		if(parseInt(form.Amt.value) < 50000)
			form.QuotaInf.value = "0";
		else
			form.QuotaInf.value = "0:2:3:4:5:6:7:8:9:10:11:12";
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// [3] 무이자 할부기간을 설정합니다.
		// (일반결제인 경우에는 본 설정은 적용되지 않습니다.)
		// 
		// 무이자 할부기간은 2 ~ 12개월까지 가능하며, 
		// 올더게이트에서 제한한 할부 개월수까지만 설정해야 합니다.
		// 
		// 100:BC
		// 200:국민
		// 300:외환
		// 400:삼성
		// 500:신한
		// 800:현대
		// 900:롯데
		// 
		// 예제)
		// 	(1) 모든 할부거래를 무이자로 하고 싶을때에는 ALL로 설정
		//	 	form.NointInf.value = "ALL";
		//
		// 	(2) 국민카드 특정개월수만 무이자를 하고 싶을경우 샘플(2:3:4:5:6개월)
		// 		form.NointInf.value = "200-2:3:4:5:6";
		//
		// 	(3) 외환카드 특정개월수만 무이자를 하고 싶을경우 샘플(2:3:4:5:6개월)
		// 		form.NointInf.value = "300-2:3:4:5:6";
		//
		// 	(4) 국민,외환카드 특정개월수만 무이자를 하고 싶을경우 샘플(2:3:4:5:6개월)
		// 		form.NointInf.value = "200-2:3:4:5:6,300-2:3:4:5:6";
		//	
		//	(5) 무이자 할부기간 설정을 하지 않을 경우에는 NONE로 설정
		//		form.NointInf.value = "NONE";
		//
		//	(6) 전카드사 특정개월수만 무이자를 하고 싶은경우(2:3:6개월)
		//		form.NointInf.value = "100-2:3:6,200-2:3:6,300-2:3:6,400-2:3:6,500-2:3:6";
		//
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		 
		//	모든 할부거래를 무이자
		if(form.DeviId.value == "9000400002") {
			form.NointInf.value = "ALL";
		} 
		
		
			
		AllTheGate.pay(document.form);
		return false;
	}

</script>  
<%
	///////////////////////////////////////////////////////
	//
	// 금액 위변조를 막기 위해, 
	// 가격 정보 (Amt) 의 경우 JavaScript로 변경할 수 없습니다.
	// 반드시 ServerScript(asp,php,jsp)에서 가격정보를 세팅한 후 Form에 입력하여 주세요.
	//
	///////////////////////////////////////////////////////
	
	String amt = "1004";
	String dutyfree = "0"; //신용카드 결제용 면세 금액 (amt 중 면세 금액 설정)
	String store_id = "aegis";
	
	//올더게이트 
	String strAegis = "https://www.allthegate.com";
	String strCsrf = "csrf.real.js";
	
	String http_host = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort(); 
%>
<script type="text/javascript" charset="utf-8" src="<%= strAegis %>/payment/mobilev2/csrf/<%= strCsrf %>"></script> 
</head>  
<form method="post" action="<%= strAegis %>/payment/mobilev2/intro.jsp" name="form" >
<body onload="doPay(document.form)">
<div class="top_div">
	<table>
		<tr>
			<td width="90%">올더게이트 결제요청</td>
			<td>AlltheGate</td>
		</tr>
	</table>
</div>


<div class="content_div" id="agree_div">
	<br>
	<table class="table table-bordered">
		
<!-- 		<tr> -->
<!-- 			<td>주문번호</td> -->
<!-- 			<td> -->
			<input type="hidden" name="OrdNo" value="1000000001"/>
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>상품명</td> -->
<!-- 			<td> -->
			<input type="hidden" name="ProdNm"  value="${buyProdctNmeCount}"/>
<!-- 			</td> -->
<!-- 		</tr> -->
		<tr>
			<td>가격</td>
			<td><input type="text" name="Amt" value="${pricSum}"/>원</td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td>면세금액</td> -->
<%-- 			<td><input type="text" name="DutyFree" value="<%=dutyfree%>"/></td> --%>
<!-- 		</tr> -->
		<tr>
			<td>구매자이름</td>
			<td><input type="text" name="OrdNm"  value="${custmrNme}"/></td>
		</tr>
		<tr>
			<td>상점이름</td>
			<td><input type="text" name="StoreNm"  value="대백마트 파호점"/></td>
		</tr>
		<tr>
			<td>휴대폰번호</td>
			<td><input type="text" name="OrdPhone"  value="${custmrMobl}"/></td>
		</tr>
		<tr>
			<td>이메일</td> 
			<td><input type="text" name="UserEmail"  value="test@naver.com"/></td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td>결제방법</td> -->
<!-- 			<td> -->
<!-- 				<select name="Job"> -->
<!-- 					<option value="card">신용카드</option> -->
<!-- 				</select> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>상점아이디</td> -->
<!-- 			<td> -->
			<input type="hidden" name="StoreId" maxlength=20 value="<%=store_id%>"/>
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>상점URL</td> -->
<!-- 			<td> -->
			<input type="hidden"  name="MallUrl" value="<%=http_host%>"/>
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>회원아이디</td> -->
<!-- 			<td> -->
			<input type="hidden"  name="UserId" maxlength=20 value="test">
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>주문자주소</td> -->
<!-- 			<td><input type="text"  name="OrdAddr" value="서울시 강남구 청담동"></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>수신자명</td> -->
<!-- 			<td> -->
			<input type="hidden"  name="RcpNm" value="김길동">
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>수신자연락처</td> -->
<!-- 			<td> -->
			<input type="hidden"  name="RcpPhone" value="02-111-2222">
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>배송지주소</td> -->
<!-- 			<td> -->
			<input type="hidden"  name="DlvAddr" value="서울시 강남구 청담동">
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>기타요구사항</td> -->
<!-- 			<td> -->
			<input type="hidden"  name="Remark" value="오후에 배송요망">
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>카드사선택</td> -->
<!-- 			<td><input type="text"  name="CardSelect"  value=""></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>성공 URL</td> -->
<!-- 			<td> -->

			<input type="hidden"  name="RtnUrl" value="<%=http_host%>/test.do">
<%-- 			<input type="hidden"  name="RtnUrl" value="<%=http_host%>/AGSMobile_approve.do"> --%>
<!-- 			<input type="hidden"  name="RtnUrl" value="file://android_asset/www/view/login/LoginFrame.html"> -->
<!-- 			</td> -->
<!-- 		</tr> -->
		
<!-- 		<tr> -->
<!-- 			<td>앱 URL Scheme (독자앱일 경우)</td> -->
<!-- 			<td> -->
<!-- 				<input type="hidden"  name="AppRtnScheme" value=""> -->
				<!--  네이버 예시 :  naversearchapp://inappbrowser?url= -->
<!-- 				file:///android_asset/www/view/index.html -->
<!-- 				<br/> -->
<!-- 				AppRtnScheme + RtnUrl을 합친 값으로 다시 앱을 호출합니다.<br/> -->
<!-- 				독자앱이 아닌경우 빈값으로 세팅 -->
<!-- 			</td> -->
<!-- 		</tr> -->
		
<!-- 		<tr> -->
<!-- 			<td>취소 URL</td> -->
<!-- 			<td> -->
			<input type="hidden"  name="CancelUrl" value="<%=http_host%>/test.do">
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>추가사용필드1</td> -->
<!-- 			<td><input type="text"  name="Column1" maxlength="200" value="상점정보입력1"></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>추가사용필드2</td> -->
<!-- 			<td><input type="text"  name="Column2" maxlength="200" value="상점정보입력2"></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>추가사용필드3</td> -->
<!-- 			<td><input type="text"  name="Column3" maxlength="200" value="상점정보입력3"></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td colspan="2">가상계좌 결제 사용 변수</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>통보페이지</td> -->
<!-- 			<td> -->
			<input type="hidden" name="MallPage" maxlength="100" value="<%=http_host%>/test.do">
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>입금예정일 (YYYYMMDD)</td> -->
<!-- 			<td><input type=text name="VIRTUAL_DEPODT" maxlength=8 value=""></td> -->
<!-- 		<tr> -->
<!-- 			<td colspan="2">핸드폰 결제 사용 변수</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>CP아이디</td> -->
<!-- 			<td><input type="text" name="HP_ID" maxlength="10" value=""></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>CP비밀번호</td> -->
<!-- 			<td><input type="text" name="HP_PWD" maxlength="10" value=""></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>SUB-CP아이디</td> -->
<!-- 			<td><input type="text" name="HP_SUBID" maxlength="10" value=""></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>상품코드</td> -->
<!-- 			<td><input type="text" name="ProdCode" maxlength="10" value=""></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>상품종류</td> -->
<!-- 			<td> -->
<!-- 				<select name="HP_UNITType"> -->
<!-- 					<option value="1">디지털:1 -->
<!-- 					<option value="2">실물:2 -->
<!-- 				</select> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!--             <td>상품제공기간</td> -->
<!--             <td> -->
            <input type="hidden" name="SubjectData" value="금액;상품명;2016.10.01~30">
<!--             </td> -->
<!--         </tr> -->
		 
	</table>		
	
	<input type="hidden" name=DeviId value="9000400001">			
	<input type="hidden" name=QuotaInf value="0">			
	<input type="hidden" name=NointInf value="NONE">
	
	<div class="text-center width-100">
		<input type="button" value="결제 확인" class="ok_btn" onclick="doPay(document.form)"/>
	</div>
</div>

</form>

</body>
</html>