<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 대백제 등록1 JSP 입니다.
* 
* history : 김민아, 1.0, 2016/08/08 - 초기 작성
* author : 김민아
* version : 1.0, 2016/08/08  - 초기 작성
* see : 대백제 등록 
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
		
		<!-- 이미지 썸네일 CSS -->
		<link href="/resources/common/css/thumbnail.css" rel="stylesheet" type="text/css"/>
		
		<!-- JQuery 관련 파일들 -->
		<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>   
		
		<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
		<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
		
		<!-- debecFestivalCreate 관련 JS -->
		<script src="../../../resources/js/debecFestival-js/debecFestivalCreate.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../../resources/js/debecFestival-js/debecFestivalCreate.prodctAdd.js" type="text/javascript" charset="utf-8"></script>
	</head>
	
	<body id="debecFestivalCreate">
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
		<form name="debecFestivalCreateFrm" enctype="multipart/form-data">     
			<div class="container">
				<h3>대백제 등록</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
					<h5>
						<img src="/resources/image/common/home.png" style="width: 22px;"> > 대백제 관리 > 
						<a href="/debecFestivalList.do" style="text-decoration:none"><strong>대백제 목록</strong></a>
					</h5>
				</div>
			</div>
		
			<!-- 대백제 상세정보 -->
			<div class="container form-inline">
				<table class="table table-bordered">
					<colgroup>
						<col class="col-md-4 col-sm-4"/>                
					    <col class="col-md-8 col-sm-8"/>   
					</colgroup>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>배너이미지</b></h5>
						</td>
						<td>
							<input type="file" id = "evntImgFile" name="evntImgFile">
						</td>
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>행사명</b></h5>
						</td>
						<td>
							<input type="text" class="form-control" id="schedlTitl" name="schedlTitl">
						</td>
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>기간</b></h5>
						</td>
						<td>
							<input type="text" class="form-control" id="schedlStartDat" name="schedlStartDat">
							<input type="text" class="form-control" id="schedlEndDat" name="schedlEndDat">					
						</td>
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>적용 상태</b></h5>
						</td>
						<td>
							<select id="evntStat" class="form-control" name="evntStat">
								<option value="Y">진행중</option>
								<option value="R">대기중</option>
								<option value="N">종료</option>
							</select>
						</td>
					</tr>
				</table>
			</div>
		</form>
		
		<div class="container text-center">
			<input class="btn btn-default" type="button" value="등록" onclick="javascript:debecFestivalCreate()"/>
			<input class="btn btn-default" type="button" value="취소" onclick="javascript:location.href='/debecFestivalList.do'"/>
		</div>
	</body>
</html>