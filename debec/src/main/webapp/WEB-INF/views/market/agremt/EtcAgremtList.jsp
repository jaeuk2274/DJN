<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 이용약관 설정 화면을 보여주는 JSP입니다.
* 
* history :
*        최재욱, 1.0, 2016/08/18 – 초기 작성
* author : 최재욱
* version : 1.0, 2016/08/18  - 초기 작성
* see : 이용약관 설정 화면
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

	<script type="text/javascript" src="/resources/S.E/js/HuskyEZCreator.js?ver=1" charset="utf-8"></script>
</head>

<body>
	<!-- 상단 메뉴바 import -->
	<div style="margin-bottom: 130px;">
		<c:import url="/TopFrame.do"/>
	</div>
	<div class="container">
		<h3>기타 이용약관 설정</h3>
		<div style="display:inline-block; margin-top:1%; float:right">
		   <h5>
		     <img src="/resources/image/common/home.png"> > 이용약관 관리 > 
		     <a href="/EtcAgremtList.do" style="text-decoration:none"><strong>기타 이용약관 설정</strong></a>
		   </h5>
		</div>
	</div>
	
	<!-- action : 에디터에 입력한 html 코드를 전달받을 Controller페이지 URL -->
	<form action="/etcAgremtUpdate.do" method="post" name="agremtUpdateFrm" id="agremtUpdateFrm">
		<input type="hidden" id="useAgremtSeq" name="useAgremtSeq" value="${AgremtVO.useAgremtSeq}">	
		
		<div class="container">
			<div class="container">
				<ul class="nav nav-tabs">		
					<li role="presentation" ${AgremtVO.useAgremtSeq == 1 || AgremtVO.useAgremtSeq == 7 ? "class='active'" : ""}>
						<a href="/EtcAgremtList.do?useAgremtSeq=7">회원가입안내</a>
					</li>
					<li role="presentation" ${AgremtVO.useAgremtSeq == 8 ? "class='active'" : ""}>
						<a href="/EtcAgremtList.do?useAgremtSeq=8">주문안내</a>
					</li>
					<li role="presentation" ${AgremtVO.useAgremtSeq == 9 ? "class='active'" : ""}>
						<a href="/EtcAgremtList.do?useAgremtSeq=9">결제안내</a>
					</li>
					<li role="presentation" ${AgremtVO.useAgremtSeq == 10 ? "class='active'" : ""}>
						<a href="/EtcAgremtList.do?useAgremtSeq=10">배송안내</a>
					</li>
					<li role="presentation" ${AgremtVO.useAgremtSeq == 11 ? "class='active'" : ""}>
						<a href="/EtcAgremtList.do?useAgremtSeq=11">교환안내</a>
					</li>
					<li role="presentation" ${AgremtVO.useAgremtSeq == 12 ? "class='active'" : ""}>
						<a href="/EtcAgremtList.do?useAgremtSeq=12">환불안내</a>
					</li>
					<li role="presentation" ${AgremtVO.useAgremtSeq == 13 ? "class='active'" : ""}>
						<a href="/EtcAgremtList.do?useAgremtSeq=13">적립금 및 포인트 안내</a>
					</li>
					<li role="presentation" ${AgremtVO.useAgremtSeq == 14 ? "class='active'" : ""}>
						<a href="/EtcAgremtList.do?useAgremtSeq=14">배송정보 제공방침 안내</a>
					</li>
				</ul>
													
				
				<c:if test="${AgremtVO.useAgremtSeq == 9 || AgremtVO.useAgremtSeq == 10 || AgremtVO.useAgremtSeq == 11}">
					<ul>
						<table>
							<tr style="height:30px">
								<td style="text-align:center; width:150px">모바일 표시 여부</td>
								<td>
									<input type="radio" value="Y" name="useCheck" ${AgremtVO.useCheck eq "Y" ? "checked" : ""}>표시함 &nbsp
									<input type="radio" value="N" name="useCheck" ${AgremtVO.useCheck eq "N" ? "checked" : ""}>표시안함
								</td>
							</tr>
						</table>
					</ul>
				</c:if>
				<c:if test="${AgremtVO.useAgremtSeq == 14}">
					<ul>
						<table>
							<tr style="height:30px">
								<td style="text-align:center; width:120px">사용 여부</td>
								<td>
									<input type="radio" value="Y" name="useCheck" ${AgremtVO.useCheck eq "Y" ? "checked" : ""}>사용함  &nbsp
									<input type="radio" value="N" name="useCheck" ${AgremtVO.useCheck eq "N" ? "checked" : ""}>사용안함
								</td>
							</tr>
						</table>
					</ul>
				</c:if>
			</div>									
			<div class="container">								
				<textarea name="cnt" id="cnt" rows="20" cols="184">
					<c:if test = "${AgremtVO.showStd eq 'N'}">
						${AgremtVO.cnt}
					</c:if>
					
					<c:if test = "${AgremtVO.showStd eq 'Y'}">
						${AgremtVO.standrd}
					</c:if>
				</textarea>	
			</div>				
		</div>
	
		<div class="container text-center">
			<input type="button" class="btn btn-default" id="savebutton" value="저장"/>
			<input type="button" class="btn btn-default" id="standrd" value="표준약관 적용"/>
		</div>
	</form>	
	
	<!-- 에디터 -->
	<script type="text/javascript">
		$(function(){
			var oEditors = [];
			nhn.husky.EZCreator.createInIFrame({
			    oAppRef: oEditors,
			    elPlaceHolder: "cnt",
			    sSkinURI: "../../../resources/S.E/SmartEditor2Skin.html",
			    fCreator: "createSEditor2"
			});
			
		    $("#savebutton").click(function(){
		        //id가 smarteditor인 textarea에 에디터에서 대입
		        oEditors.getById["cnt"].exec("UPDATE_CONTENTS_FIELD", []);
		        
		        //폼 submit
		        $("#agremtUpdateFrm").submit();
		    });
		        
		    $("#standrd").click(function(){
		    	document.agremtUpdateFrm.action ="/EtcStdAgremtList.do";
				document.agremtUpdateFrm.submit();
				
		    });
		});
	</script>
</body>

</html>