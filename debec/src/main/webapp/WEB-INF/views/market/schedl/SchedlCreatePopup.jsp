<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!-- 
* 일정을 수정하는 화면을 보여주는 JSP입니다.
* 
* history :
*        최재욱, 1.0, 2016/08/17 – 초기 작성
* author : 최재욱
* version : 1.0, 2016/08/17  - 초기 작성
* see : 일정관리 일정 수정 화면(팝업)
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
					
		<!-- datepicker 위젯 -->			
		<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>	
					
		<style> 
	      input[id="cb1"] + label {
	        display: inline-block;
	        width: 14px;
	        height: 14px;
	        background-color: #bcbcbc;
	        border-radius: 7px;
	        cursor: pointer;
	      }
	      input[id="cb1"]:checked + label {
	        background-color: blue;
	        
	      }
	      input[id="cb1"] {
	        display: none;
	      }
	      
	      input[id="cb2"] + label {
	        display: inline-block;
	        width: 14px;
	        height: 14px;
	        background-color: #bcbcbc;
	        border-radius: 7px;
	        cursor: pointer;
	      }
	      input[id="cb2"]:checked + label {
	        background-color: red;
	      }
	      input[id="cb2"] {
	        display: none;
	      }
	     
	      input[id="cb3"] + label {
	        display: inline-block;
	        width: 14px;
	        height: 14px;
	        background-color: #bcbcbc;
	        border-radius: 7px;
	        cursor: pointer;
	      }
	      input[id="cb3"]:checked + label {
	        background-color: orange;
	      }
	      input[id="cb3"] {
	        display: none;
	      }
	      
	      input[id="cb4"] + label {
	        display: inline-block;
	        width: 14px;
	        height: 14px;
	        background-color: #bcbcbc;
	        border-radius: 7px;
	        cursor: pointer;
	      }
	      input[id="cb4"]:checked + label {
	        background-color: yellow;
	      }
	      input[id="cb4"] {
	        display: none;
	      }
	      
	      input[id="cb5"] + label {
	        display: inline-block;
	        width: 14px;
	        height: 14px;
	        background-color: #bcbcbc;
	        border-radius: 7px;
	        cursor: pointer;
	      }
	      input[id="cb5"]:checked + label {
	        background-color: green;
	      }
	      input[id="cb5"] {
	        display: none;
	      }
		</style>
	
	</head>
	<body>
		<div style="color: #fff; background-color: #000; border-radius:4px; padding:3px 8px;">
			<h3>일정 등록</h3>
		</div>
		
		<div class="form-group"></div>
		<div class="container">
			<form class="form-inline" id="schedlCreateFrm" name="schedlCreateFrm">
				<div class="container form-group">
					<table class="table table-bordered">
						<tr>
							<td class="active text-center">
								<h5 class="text-center"><b>일정</b></h5>
							</td>
							<td> <input type="text" class="form-control" name="title"> </td>
						</tr>
						<tr>
							<td class="active text-center">
								<h5 class="text-center"><b>날짜</b></h5>
							</td>
							<td> 
								<input type="text" class="form-control" id="datepicker1" name="start" value=""> 
								~  
								<input type="text" class="form-control" id="datepicker2" name="end" value="">
							</td>
						</tr>				
						<tr>						
							<td class="active text-center">
								<h5 class="text-center"><b>색상</b></h5>
							</td>
							<td> 
								<div align="center" style="float:left; border:1px solid blue; width:50px;">
									<input type="checkbox" id="cb1" name="color" value="blue" onClick="chkBoxCheck(0)">
			  						<label for="cb1"></label><br><p style="margin-top:-5px">blue</p>
								</div>
								
								<div align="center" style="float:left; border:1px solid red; width:50px;">
								 	<input type="checkbox" id="cb2" name="color" value="red" onClick="chkBoxCheck(1)">
			  					 	<label for="cb2"></label><br><p style="margin-top:-5px">red</p>
								</div>
								<div align="center" style="float:left; border:1px solid orange; width:50px;">
								 	<input type="checkbox" id="cb3" name="color" value="orange" onClick="chkBoxCheck(2)">
			  					 	<label for="cb3"></label><br><p style="margin-top:-5px">orange</p>
								</div>
								<div align="center" style="float:left; border:1px solid yellow; width:50px;">
								 	<input type="checkbox" id="cb4" name="color" value="yellow" onClick="chkBoxCheck(3)">
			  					 	<label for="cb4"></label><br><p style="margin-top:-5px">yellow</p>
								</div>
								<div align="center" style="float:left; border:1px solid green; width:50px;">
								 	<input type="checkbox" id="cb5" name="color" value="green" onClick="chkBoxCheck(4)">
			  					 	<label for="cb5"></label><br><p style="margin-top:-5px">green</p>
								</div>
							</td>						
						</tr>
						<tr>
							<td class="active text-center">
								<h5 class="text-center"><b>반복 설정</b></h5>
							</td>
							<td> 
								<input type="radio" name="schedlRept" value="Not" checked> 사용안함
								<input type="radio" name="schedlRept" value="Day"> 일
								<input type="radio" name="schedlRept" value="Week"> 주
								<input type="radio" name="schedlRept" value="Month"> 월
								<br>
								반복기간 <input type="text" id="datepicker3" name="schedlReptDat" value=""> 일 까지
							</td>
						</tr>						
						<tr>
							<td class="active text-center">
								<h5 class="text-center"><b>내용</b></h5>
							</td>
							<td> <textarea name="schedlCnt"  rows="3" cols="74" > </textarea></td>
						</tr>
					</table>
				</div>
			</form>
			
			
			<div class="container text-center">
				<input type="button" class="btn btn-default"  onclick="javacript:SchedlCreate()" value="등록">
				<input type="button" class="btn btn-default"  onclick="self.close();" value="취소">
			</div>
		</div>
		
		<script>
			// 일정 등록
			function SchedlCreate() {
				var formData = $("#schedlCreateFrm").serialize();
				 $.ajax({
                     type : "POST",
                     url : "/SchedlCreate.do",
                     data : formData,
                     success: function(msg) {                    	
                     }
                  });
				opener.parent.location.reload();
				window.close();
// 				self.close();
			};
					
			// 날짜 선택 위젯
			$.datepicker.setDefaults({
		        dateFormat: 'yy-mm-dd',
		        prevText: '이전 달',
		        nextText: '다음 달',
		        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
		        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
		        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		        showMonthAfterYear: true,
		        yearSuffix: '년'
		    });
		    $(function() {
		        $("#datepicker1").datepicker();
		        $("#datepicker2").datepicker();
		        $("#datepicker3").datepicker();
		    });
		    
		    // 색깔 체크박스 하나만 선택되도록
		    function chkBoxCheck(intChkNumber) {
	    	   // 체크박스 갯수만큼 반복문을 돌면서
	    	   for (j = 0; j < 5; j++) {
	    	        // 체크박스가 선택되어 있으면
	    	        if (eval("document.schedlCreateFrm.color[" + j + "].checked") == true) {
	    	             // 우선 체크박스의 속성을 선택되지 않음으로 하고
	    	             document.schedlCreateFrm.color[j].checked = false;
	    	             // 사용자가 클릭한 체크박스와 프로그래밍적으로 돌고 있는
	    	           // 체크박스의 번호가 같으면
	    	             if (j == intChkNumber) {
	    	                  // 이런 경우만 체크박스가 선택되도록 한다.
	    	                  document.schedlCreateFrm.color[j].checked = true;
	    	             }
	    	       }
	    	   }
	    	};
	    </script>
	</body>
</html>