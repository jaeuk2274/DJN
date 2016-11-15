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
	</head>
	<body>
		<form name="custmrPagingFrm">  
			<div class="container">    
				<input type="hidden" name="custmrSeq" value="${CustmrReadVO.custmrSeq}"> <!-- 고객 seq --> 
			</div>
		</form>		
	
		<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
			<h3>회원 상세보기</h3>
		</div>
		
		<dl></dl>
	
		<div class="container text-center">
			<h4 class="text-center"><b>${CustmrReadVO.custmrNme}님 회원 정보</b></h4>
		</div>
		
		<dl></dl>
		
		<div class="container">
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
					<td> ${CustmrReadVO.custmrMobl} </td>
				</tr>
				<tr>
					<th class="active"> 주소 </th>
					<td> ${CustmrReadVO.custmrAdrs} ${CustmrReadVO.custmrDetalAdrs}</td>
				</tr>
				<tr>
					<th class="active"> 생년월일 </th>
					<td>  ${CustmrReadVO.custmrBirth} </td>
				</tr>
				<tr>
					<th class="active"> 이메일 </th>
					<td> ${CustmrReadVO.custmrEml} </td>
				</tr>
				<tr>
					<th class="active"> 카드여부 </th>
					<td> 
						<c:if test = "${CustmrReadVO.custmrCardCheck eq 'Y'}">
							보유
						</c:if>
						<c:if test = "${CustmrReadVO.custmrCardCheck eq 'N'}">
							미보유
						</c:if>
						<c:if test = "${CustmrReadVO.custmrCardCheck eq 'R'}">
							요청
						</c:if>
					</td>
				</tr>
			</table>
			<div class=" text-center">
				<input type="button" class="btn btn-default" onclick="javacript:custmrUpdatePopup(${CustmrReadVO.custmrSeq})" value="수정">
				<input type="button" class="btn btn-default" onclick="javacript:custmrDelete(${CustmrReadVO.custmrSeq}, '${CustmrReadVO.custmrNme}')" value="삭제">
			</div>
		</div>
		<script>
			// 고객 수정화면 이동
			function custmrUpdatePopup(custmrSeq) {
				var url = "/custmrUpdatePopup.do?custmrSeq="+custmrSeq;
				$(location).attr('href',url);
			};
			
			// 창이 onunload 되면 부모장 reload
			function parentReload() {
				opener.parent.location.reload();			
			};
			
			function custmrDelete(custmrSeq, custmrNme) {
				var chkDelete = confirm(custmrNme+"님의 정보를 삭제하시겠습니까?");
				if(chkDelete == true) {
					$.ajax({
						type:"POST",
						data:{"custmrSeq":custmrSeq},
// 						url:"/custmrDelete.do?custmrSeq="+custmrSeq,
						url:"/custmrDelete.do",
						success:function(data){
							window.opener.document.location.reload();
						},
						error:function(request,status,error){
							window.opener.document.location.reload();
						}
					});	
					self.close();
				}
				else {
					return;
				}
			};			
		</script>
	</body>
</html>