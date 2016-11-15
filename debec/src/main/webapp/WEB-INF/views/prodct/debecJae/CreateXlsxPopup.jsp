<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 
* 상품 일괄 등록 팝업 JSP 입니다.
* 
* history : 김민아, 1.0, 2016/08/08 - 초기 작성
* author : 김민아
* version : 1.0, 2016/08/08  - 초기 작성
* see : 상품 일괄 등록 
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
		<script src="../../../../resources/js/debecFestival-js/debecFestivalUpdate.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../../../resources/js/debecFestival-js/debecFestivalCreate.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../../../resources/js/debecFestival-js/debecFestivalCreate.prodctAdd.js" type="text/javascript" charset="utf-8"></script>
		
	</head>
	<body onbeforeunload="closeIt()">
		<form name="prodctFrm" method="post" enctype="multipart/form-data">
			<input type="hidden" id="schedlSeq" name="schedlSeq" value="${schedlSeq}">
			<div style="background:#403A39; height:60px">
				<div style="padding-top:2%; padding-left:2%"><font style="color:#ffffff; font-size:18px">상품 일괄 등록</font></div>				
			</div>
			<div class="container">				
				<img src="../../../../resources/image/common/prodctHowToUpload.PNG" class="img-responsive" style="margin-top:2%;">
				<div align="center" style="border-radius: 5px; border:1px solid">
					<input type="file" name = "xlsxFile" id = "xlsxFile" style="width:150px">					
				</div>
				<c:choose>
					<c:when test = "${divison=='U'}">
						<input type="button" class="btn btn-default" id="prodctInsert" value="등록" onclick="javascript:excelUploadUpdate()">
					</c:when>
					<c:otherwise>
						<input type="button" class="btn btn-default" id="prodctInsert" value="등록" onclick="javascript:excelUploadCreate()">
					</c:otherwise>
				</c:choose>
				<input type="button" class="btn btn-default" id="prodctCancl" value="취소" onclick="javascript:popupCancel()">
			</div>				
		</form>
	</body>
	<script>
		// 엑셀파일 업로드(등록)
		function excelUploadCreate(){		 
			if($('#xlsxFile').val() == '')
			{
				alert('파일을 선택해주세요');
			}
			else
			{
				document.prodctFrm.action = "/xlsxUploadCreate.do";
				document.prodctFrm.method = "post";
				document.prodctFrm.submit();	
			}
		}
		// 엑셀파일 업로드(수정)
		function excelUploadUpdate(){		 
			if($('#xlsxFile').val() == '')
			{
				alert('파일을 선택해주세요');
			}
			else
			{
				document.prodctFrm.action = "/xlsxUploadUpdate.do";
				document.prodctFrm.method = "post";
				document.prodctFrm.submit();	
			}
		}
		// 팝업 닫기 버튼
		function popupCancel(){
			window.opener.location.reload();
			window.close();
		}
		// 상품 추가 창 엑스 버튼 클릭시
		function closeIt(){
		    self.close();
		    window.opener.$("#FadeIn").remove();
		 };
	</script>
</html>