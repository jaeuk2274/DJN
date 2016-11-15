<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 
* 입고거래장 상세조회를 팝업으로 보여주는 JSP입니다. (거래처 상세보기 - 입고거래장 - 상세보기)
* 
* history :
*        김대현, 1.0, 2016/08/15 – 초기 작성
* author : 김대현
* version : 1.0, 2016/08/15  - 초기 작성
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
	</head>
	<body onload="imgDownload()">
		<div style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
			<h3>입고거래장 세부내용</h3>
		</div>
		
		<div class="form-group"></div>
		
		<div class="container" id="otherTbles" style="display:none">
			<table class="table table-bordered">
			<colgroup>
				<col class="col-sm-2">
				<col class="col-sm-10">
			</colgroup>
				<tr>
					<td class="active text-center">
						<b>제목</b>
					</td>
					<td>
						${instckVo.instckExchngFlorTitl}
					</td>
				</tr>
				<tr>
					<td class="active text-center">
						<b>거래처</b>
					</td>
					<td>
						${instckVo.clintNme}
					</td>
				</tr>
				<tr>
					<td class="active text-center">
						<b>거래금액</b>
					</td>
					<td>
						<fmt:formatNumber value="${instckVo.instckExchngFlorPric}" type="number"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						${instckVo.instckExchngFlorCnt}
						<table class="table table-bordered" style="padding:0px; margin:0px"> 
						<c:if test="${instckVo.instckExchngFlorImg1 != null}">
							<tr style="height:150px">
								<td><img style="max-width:100%; height:auto;" src="${instckVo.instckExchngFlorImg1}?ver=1"></td>
							</tr>
							<tr>
								<td class="active text-center"> 이미지1 </td>
							</tr>
						</c:if>
						<c:if test="${instckVo.instckExchngFlorImg2 != null}">
							<tr style="height:150px">
								<td><img style="max-width:100%; height:auto;" src="${instckVo.instckExchngFlorImg2}?ver=1"></td>
							</tr>
							<tr>
								<td class="active text-center"> 이미지2 </td>
							</tr>
						</c:if>
						<c:if test="${instckVo.instckExchngFlorImg3 != null}">
							<tr style="height:150px">
								<td><img style="max-width:100%; height:auto;" src="${instckVo.instckExchngFlorImg3}?ver=1"></td>
							</tr>
							<tr>
								<td class="active text-center"> 이미지3 </td>
							</tr>
						</c:if>
						</table>
					</td>
				</tr>
			</table>
		</div>
		
	   	<div class="container" id="chromeTbles" style="display:none">
			<table class="table table-bordered">
			<colgroup>
				<col class="col-sm-2">
				<col class="col-sm-10">
			</colgroup>
				<tr>
					<td class="active text-center">
						<b>제목</b>
					</td>
					<td>
						${instckVo.instckExchngFlorTitl}
					</td>
				</tr>
				<tr>
					<td class="active text-center">
						<b>거래처</b>
					</td>
					<td>
						${instckVo.clintNme}
					</td>
				</tr>
				<tr>
					<td class="active text-center">
						<b>거래금액</b>
					</td>
					<td>
						<fmt:formatNumber value="${instckVo.instckExchngFlorPric}" type="number"/>
					</td>
				</tr>
				<tr>
					<td class="active text-center">
						<b>이미지1</b>
					</td>
					<td>
						${instckVo.instckExchngFlorImg1}
						<a href="${instckVo.instckExchngFlorImg1}" download>
							<img src="resources/image/common/download.png" class="pull-right" style="width:30px; height:30px;">
						</a>
					</td>
				</tr>
				<tr>
					<td class="active text-center">
						<b>이미지2</b>
					</td>
					<td>
						${instckVo.instckExchngFlorImg2}
						<a href="${instckVo.instckExchngFlorImg2}" download>
							<img src="resources/image/common/download.png" class="pull-right" style="width:30px; height:30px;">
						</a>
					</td>
				</tr>
				<tr>
					<td class="active text-center">
						<b>이미지3</b>
					</td>
					<td>
						${instckVo.instckExchngFlorImg3}
						<a href="${instckVo.instckExchngFlorImg3}" download>
							<img src="resources/image/common/download.png" class="pull-right" style="width:30px; height:30px;">
						</a>
					</td>
				</tr>
				<tr >
					<td colspan="2">
						${instckVo.instckExchngFlorCnt}
						<table class="table table-bordered" style="padding:0px; margin:0px"> 
						<c:if test="${instckVo.instckExchngFlorImg1 != null}">
							<tr style="height:150px">
								<td><img style="max-width:100%; height:auto;" src="${instckVo.instckExchngFlorImg1}?ver=1"></td>
							</tr>
							<tr>
								<td class="active text-center"> 이미지1 </td>
							</tr>
						</c:if>
						<c:if test="${instckVo.instckExchngFlorImg2 != null}">
							<tr style="height:150px">
								<td><img style="max-width:100%; height:auto;" src="${instckVo.instckExchngFlorImg2}?ver=1"></td>
							</tr>
							<tr>
								<td class="active text-center"> 이미지2 </td>
							</tr>
						</c:if>
						<c:if test="${instckVo.instckExchngFlorImg3 != null}">
							<tr style="height:150px">
								<td><img style="max-width:100%; height:auto;" src="${instckVo.instckExchngFlorImg3}?ver=1"></td>
							</tr>
							<tr>
								<td class="active text-center"> 이미지3 </td>
							</tr>
						</c:if>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<div class="container text-center">
			<input type="button" class="btn btn-default" value="돌아가기" onclick="back()">
		</div>
		<script>
			//F5키 막아놓기
			window.onkeydown = function() {
				var kcode = event.keyCode;
				if(kcode == 116) event.returnValue = false;
			}
		
			//브라우저 구분
			function imgDownload(){
				var Browser = {chk : navigator.userAgent.toLowerCase()}
				Browser = {ie : Browser.chk.indexOf('msie') != -1
						,ie6 : Browser.chk.indexOf('msie 6') != -1
						,ie7 : Browser.chk.indexOf('msie 7') != -1
						,ie8 : Browser.chk.indexOf('msie 8') != -1
						,ie9 : Browser.chk.indexOf('msie 9') != -1
						,ie10 : Browser.chk.indexOf('msie 10') != -1
						,opera : !!window.opera
						,safari : Browser.chk.indexOf('safari') != -1
						,safari3 : Browser.chk.indexOf('applewebkir/5') != -1
						,mac : Browser.chk.indexOf('mac') != -1
						,chrome : Browser.chk.indexOf('chrome') != -1
						,firefox : Browser.chk.indexOf('firefox') != -1 }
				if (Browser.chrome){
// 					alert("크롬입니다.");
					var chromeTbles = document.getElementById("chromeTbles");
					chromeTbles.style.display="block";
				}
				else {
// 					alert("크롬이 아닙니다.");
					var otherTbles = document.getElementById("otherTbles");
					otherTbles.style.display="block";
				}
			}
			function back(){
				var clintSeq = ${instckVo.clintSeq};
				window.location.href="/InstckExchngFlorPopup.do?clintSeq="+clintSeq;
			}
		</script>
	</body>
</html>