<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 기타이용약관 설정 화면을 보여주는 JSP입니다.
* 
* history :
*        최재욱, 1.0, 2016/08/21 – 초기 작성
* author : 최재욱
* version : 1.0, 2016/08/21  - 초기 작성
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
	<div style="margin-bottom: 130px;">
		<c:import url="/TopFrame.do"/>
	</div>	
  
	<div class="container">
		<h3>이용약관 설정</h3>
		<div style="display:inline-block; margin-top:1%; float:right">
		   <h5>
		     <img src="/resources/image/common/home.png"> > 이용약관 관리 > 
		     <a href="/UseAgremtList.do" style="text-decoration:none"><strong>이용약관 설정</strong></a>
		   </h5>
		</div>
	</div>
	
	<!-- action : 에디터에 입력한 html 코드를 전달받을 Controller페이지 URL -->
	<form action="/agremtUpdate.do" method="post" name="agremtUpdateFrm" id="agremtUpdateFrm">
		<input type="hidden" id="useAgremtSeq" name="useAgremtSeq" value="${AgremtVO.useAgremtSeq}">	
		
		<div class="container">
			<div class="container">
				<ul class="nav nav-tabs">		
					<li role="presentation" ${AgremtVO.useAgremtSeq == 0 || AgremtVO.useAgremtSeq == 1 ? "class='active'" : ""}>
						<a href="/UseAgremtList.do?useAgremtSeq=1">쇼핑몰 이용약관</a>
					</li>
					<li role="presentation" ${AgremtVO.useAgremtSeq == 2 || AgremtVO.useAgremtSeq == 3 || AgremtVO.useAgremtSeq == 4 || AgremtVO.useAgremtSeq == 5 ? "class='active'" : ""}>
						<a href="/UseAgremtList.do?useAgremtSeq=2">개인정보 취급방침</a>
					</li>
					<li role="presentation" ${AgremtVO.useAgremtSeq == 6 ? "class='active'" : ""}>
						<a href="/UseAgremtList.do?useAgremtSeq=6">청약철회방침 사용자동의설정</a>
					</li>
				</ul>
													
				<c:if test="${AgremtVO.useAgremtSeq == 2 || AgremtVO.useAgremtSeq == 3 || AgremtVO.useAgremtSeq == 4 || AgremtVO.useAgremtSeq == 5}">
					<ul>
						<table>
							<!-- 개인정보 취급방침 -->
							<tr style="height:30px;">
								<td style="text-align:center;">
									<a href="/UseAgremtList.do?useAgremtSeq=2">
									<c:if test="${AgremtVO.useAgremtSeq == 2}">
										<b>
									</c:if>
										&nbsp&nbsp 개인정보 취급방침 전체내용 &nbsp&nbsp
									<c:if test="${AgremtVO.useAgremtSeq == 2}">
										</b>
									</c:if>
									</a>
								</td>
								<td style="text-align:center;">
									<a href="/UseAgremtList.do?useAgremtSeq=3">
									<c:if test="${AgremtVO.useAgremtSeq == 3}">
										<b>
									</c:if>
										&nbsp&nbsp 개인정보 수집 및 이용 동의 [회원가입시] &nbsp&nbsp
									<c:if test="${AgremtVO.useAgremtSeq == 3}">
										</b>
									</c:if>
									</a>					
								</td>
									<td style="text-align:center;">
									<a href="/UseAgremtList.do?useAgremtSeq=4">
									<c:if test="${AgremtVO.useAgremtSeq == 4}">
										<b>
									</c:if>
										&nbsp&nbsp 개인정보 수집 및 이용 동의 [비회원 구매시] &nbsp&nbsp
									<c:if test="${AgremtVO.useAgremtSeq == 4}">
										</b>
									</c:if>
									</a>
								</td>
									<td style="text-align:center;">
									<a href="/UseAgremtList.do?useAgremtSeq=5">
									<c:if test="${AgremtVO.useAgremtSeq == 5}">
										<b>
									</c:if>
										&nbsp&nbsp 개인정보 수집 및 이용 동의 [비회원 게시판 글 작성시] &nbsp&nbsp
									<c:if test="${AgremtVO.useAgremtSeq == 5}">
										</b>
									</c:if>
									</a>						
								</td>
							</tr>
						</table>
					</ul>
				</c:if>
				<c:if test="${AgremtVO.useAgremtSeq == 6}">
					<ul>
						<table>
							<!-- 청약철회방침 사용자동의설정 -->
							<tr style="height:30px">
								<td style="text-align:center; width:170px">청약철회방침 사용여부</td>
								<td>
									<input type="radio" value="Y" name="useCheck" ${AgremtVO.useCheck eq "Y" ? "checked" : ""}> 사용함  &nbsp
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
			<input type="button" class="btn btn-default"  id="savebutton" value="저장"/>
			<input type="button" class="btn btn-default"  id="standrd" value="표준약관 적용"/>
		</div>
	</form>	
	<!-- 에디터 -->
	<script type="text/javascript">
		$(function(){
			var oEditors = [];
			nhn.husky.EZCreator.createInIFrame({
			    oAppRef: oEditors,
			    elPlaceHolder: "cnt",
			    sSkinURI: "../../../resources/S.E/SmartEditor2Skin.html?ver=1",
			    fCreator: "createSEditor2"
			});
			
		    $("#savebutton").click(function(){
		        //id가 smarteditor인 textarea에 에디터에서 대입
		        oEditors.getById["cnt"].exec("UPDATE_CONTENTS_FIELD", []);
		        
		        //폼 submit
		        $("#agremtUpdateFrm").submit();
		    });

		    $("#standrd").click(function(){
		    	document.agremtUpdateFrm.action ="/UseStdAgremtList.do";
				document.agremtUpdateFrm.submit();		
		    });
		});
	</script>
</body>

</html>