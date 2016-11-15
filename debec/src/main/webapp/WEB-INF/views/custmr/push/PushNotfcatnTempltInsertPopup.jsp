<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta>
	<title>템플릿 등록</title>
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
	
	<!-- JSTree 관련 파일들 -->
	<script src="/resources/js/jstree/jstree.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="/resources/js/jstree/themes/proton/style.min.css">
	
	</head>
	<script>
		//푸시 알림 제목 입력
		function insertPushTitl(){
			$('#pushViewTitl').html($('#pushTitl').val());
		}
		//푸시 알림 내용 입력
		function insertPushContent(){
			$('#pushViewMesg').html($('#pushMesg').val());
		}
		function pushNotfcatnTempltInsert(){
			var formData = $('#templtInsertPopup').serialize();
			$.ajax({
				type : "POST",
				url : "/TempltInsert.do",
				data : formData,
				success: function(data) {
					if(data == "flag"){
						closeIt();
						opener.parent.location.href = "/pushNotfcatnTempltSelectList.do"
					}
				}
			});
		}
		function closeIt(){
		    window.opener.$("#FadeIn").remove();
		    self.close();
		}; 
	</script>
	<body onBeforeUnload="closeIt()">
		<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
			<h3>푸시 알림 템플릿 등록</h3>
		</div>
		
		<div class="form-group"></div>
	
		<form name="templtInsertPopup" id="templtInsertPopup" method="POST">
			 <div class="container">
				<div style="width:70%; float:left">
					<table class="table table-bordered" style="width:100%; table-layout:fixed; word-break:break-all;">
						<colgroup>
							<col style="width:20%">
							<col style="width:80%">
						</colgroup>						
						<tr>
							<td class="text-center">
								<strong>푸시알림 제목</strong>
							</td>
							<td colspan="2">
								<input type="text" class="form-control" id="pushHedln" name="pushHedln" placeholder="푸시 알림 제목을 입력해주세요." style="width:100%">											
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<strong>푸시팝업/상태창</strong>
							</th>
						</tr>
						<tr>
							<td class="text-center">
								푸시팝업 제목
							</td>
							<td>
								<input type="text" class="form-control" id="pushTitl" name="pushTitl" placeholder="푸시팝업 제목을 입력해주세요." style="width:100%" onkeydown="insertPushTitl()">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea rows="17" style="width:100%;" id="pushMesg" name="pushMesg" onkeydown="insertPushContent()" placeholder="푸시팝업 내용을 입력해주세요."></textarea>
							</td>
						</tr>
					</table>
				</div>
				<div style="width:30%; float:right">
					<table class="table table-bordered" style="width:100%; table-layout:fixed;" background="/resources/image/push/push_view2.png">
						<colgroup>
							<col style="width:33%">
							<col style="width:67%">
						</colgroup>
						<tr style="height:10%">
							<th colspan="2" class="text-center">
								<font color="white">오후 12:00</font>
							</th>
						</tr>
						<tr style="height:10%">
							<td rowspan="2">
								<img src="/resources/image/push/push_view1.png">
							</td>
							<td>
								<font id="pushViewTitl" color="white">푸시팝업 제목을 입력해주세요.</font>
							</td>
						</tr>
						<tr style="height:10%">
							<td>
								<font id="pushViewMesg" color="white">푸시팝업 내용을 입력해주세요.</font>
							</td>
						</tr>
						<tr style="height:70%">
							<td colspan="2">
								<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
							</td>
						</tr>
					</table>
				</div>
			</div>
	    </form>
	    <div class="text-center">
			<input type="button" class="btn btn-default" value="등록" onclick="javascript:pushNotfcatnTempltInsert()">
			<input type="button" class="btn btn-default" value="취소" onclick="closeIt()">
		</div>
   </body>
</html>