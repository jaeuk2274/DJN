<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 
* 거래처 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        김대현, 1.0, 2016/08/09 – 초기 작성
* author : 김대현
* version : 1.0, 2016/08/09  - 초기 작성
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
		
		<!-- JSTree 관련 파일들 -->
		<script src="/resources/js/jstree/jstree.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" href="/resources/js/jstree/themes/proton/style.min.css">	
			
		<!-- ClintRead 관련 JS -->
		<script type="text/javascript" charset="utf-8" src="/resources/js/deal-js/clintRead.prodctAdd.js?ver=1"></script>
	</head>
	<body onunload="prodctCancel()">
	<input type="hidden" name="clintSeq" id="clintSeq" value="${ClintVo.clintSeq}">		<!-- 검색어(상품명) -->
		<div class="container" style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
			<h3>거래처 상품 등록</h3>
		</div>
		
		<div class="form-group"></div>
		
		<div class="container form-inline">
			<div class="pull-left" style="display:inline-block;">
				<table class="table table-bordered" style="width:270px;">
					<tr>
						<th class="active">카테고리</th>
					</tr>
					<tr>
						<td style="height:500px; vertical-align: top;"><div id="tree" style="max-height:100%; overflow:auto"></div></td>
					</tr>
				</table>
			</div>
			
			<div class="pull-left" style="margin-left:10px; display:inline-block; width:380px; height:540px;">
				<c:import url="/SelectProdctAddList.do"/>
			</div>
			
			<div class="pull-left" style="margin-left:10px; display:inline-block; width:60px; padding-top: 200px;">
				<div style="margin-bottom: 50px;">
					<input type="button" class="btn btn-default" id="plus" value="추 가">
				</div>
				<div>
					<input type="button" class="btn btn-default" id="minus" value="제 거">
				</div>
			</div>
			
			<div class="pull-left" style="margin-left:10px; display:inline-block; width:380px; height:540px;">
				<c:import url="/SelectProdctAddTempList.do"/>
			</div>
			
			<div class="container text-right">
				<input  type="button" class="btn btn-default" value="완료" style="width:150px;" onclick="javascript:prodctAdd()">
				<input  type="button" class="btn btn-default" value="취소" style="width:150px;" onclick="javascript:prodctCancel()">
			</div>
		</div>
		<script>
			//F5키 막아놓기
			window.onkeydown = function() {
				var kcode = event.keyCode;
				if(kcode == 116) event.returnValue = false;
			}

			// 완료 - 상품 추가창에서 완료.
			function prodctAdd(){
				var clintSeq = $('#clintSeq').val();
				$.ajax({
					type:"POST",
					url:"/ProdctAddCreate.do",			// 닷두입력해야함 (모달창에서 완료눌렀을 때)
					data: {"clintSeq" : clintSeq},
					success:function(data){
						self.close();
						opener.parent.location.reload();			// 새로고침
						
					},
					error:function(request,status,error){
						alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
				});
			}
			
			// 취소 - 상품 추가창에서 취소(임시테이블 삭제)
			function prodctCancel(){
				$.ajax({
					type:"POST",
					url:"/deleteTempTable.do",			
					success:function(data){
						self.close();
					},
					error:function(request,status,error){
						alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
				});
	    	}
	    	
			$(document).ready(function() {	
				//카테고리 리스트 조회
				$.ajax({
					type: "POST",
					dataType: "JSON",
					url: "/selectCatgrList.do",
					success: function(data){
						$('#tree').jstree({
							'plugins': ["wholerow"],
							'core' : {
								"multiple" : false,
							    "animation" : 0,
								'data' : data,
									'themes' : {
										'name' : 'proton',
										'responsive' : true
									}
							}
						});
					},
					error: function(){
				      alert("Select failed.");
				   	}
				});
				
				//카테고리 선택 시 조회
				$('#tree').on("changed.jstree", function (e, data) {
				   var catgrSeq = "catgrSeq="+data.selected;
				   alert(catgrSeq);
				   $.ajax({
				      type: "GET",
				      data : catgrSeq,
				      url: "/SelectProdctAddList.do",
				      error: function(){
				         alert("Select failed.");
				      },
				      success: function(data){   
				      		document.getElementById('prodctTable').innerHTML = data;   
				      }
				   });
				});
			});
		</script>
	</body>
</html>