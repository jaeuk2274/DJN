<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		<c:forEach var="selectEmpRead" items="${selectEmpRead}" varStatus="status">
			<form name="empListInfo" method="post">
				<input type="hidden" name="empSeq" id="empSeq" value="${selectEmpRead.empSeq}">
			</form>
		<div class="container">
			<form method="post" action="empCreate.do" enctype="multipart/form-data">
				<div class="container">
					<h3>개인 정보 조회</h3>
					<div style="display:inline-block; margin-top:1%; float:right">
						<h5>
							<img src="/resources/image/common/home.png"> > 개인 정보  > 
							<a href="/vactnList.do" style="text-decoration:none">
								<strong>개인 정보 조회</strong>
							</a>
						</h5>
					</div>
				</div>
				<div class="container">
					<h3>기본 정보</h3>
				</div>
				<div class="container form-group">
				<table class="table table-bordered">
					<tr>
						<td rowspan="5" style="width:200px; height:260px">
							<img src="../../../${selectEmpRead.imgRot}" class="img-responsive img-rounded" style="height:100%">
						</td>
						<td class="active">
							<h5 class="text-center"><b>이름</b></h5>
						</td>
						<td><c:out value="${selectEmpRead.nme}"/></td>
						<td class="active">
							<h5 class="text-center"><b>성별</b></h5>
						</td>
						<td><c:out value="${selectEmpRead.gendr}"/></td>
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>전화번호</b></h5>
						</td>
						<td><c:out value="${selectEmpRead.tel}"/></td>
						<td class="active">
							<h5 class="text-center"><b>휴대전화</b></h5>
						</td>
						<td><c:out value="${selectEmpRead.mobl}"/></td>
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>생년월일</b></h5>
						</td>
						<td><c:out value="${selectEmpRead.birth}"/></td>
						<td class="active">
							<h5 class="text-center"><b>입사일</b></h5>
						</td>
						<td><c:out value="${selectEmpRead.hirDat}"/></td>
						
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>직급</b></h5>
						</td>
						<td><c:out value="${selectEmpRead.duty}"/></td>
						<td class="active">
							<h5 class="text-center"><b>업무</b></h5>
						</td>
						<td><c:out value="${selectEmpRead.task}"/></td>
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>주소</b></h5>
						</td>
						<td colspan="3">
							<c:out value="${selectEmpRead.postcd}"/>
							<c:out value="${selectEmpRead.adrs}"/>&nbsp;
							<c:out value="${selectEmpRead.detlAdrs}"/>
						</td>
					</tr>
				</table>
			</div>
		<!-- 메뉴 권한 -->
			<div class="container">
				<h3>권한 정보</h3>
			</div>
			<div class="container form-group">
				<table class="table table-bordered">
					<thead>
						<tr class="active">
							<th class="text-center" colspan="2">매장관리</th>
							<th class="text-center" colspan="2">거래관리</th>
							<th class="text-center" colspan="2">상품관리</th>
							<th class="text-center" colspan="2">고객관리</th>
							<th class="text-center" colspan="2">마감관리</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="active">직원 관리</td>
							<c:choose>
								<c:when test="${selectEmpRead.empAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.empAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
							<td class="active">거래처 관리</td>
							<c:choose>
								<c:when test="${selectEmpRead.clintAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.clintAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
							<td class="active">상품 관리</td>
							<c:choose>
								<c:when test="${selectEmpRead.prodctAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.prodctAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
							<td class="active">회원 관리</td>
							<c:choose>
								<c:when test="${selectEmpRead.cutmrAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.cutmrAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
							<td class="active">TODAY 리포트</td>
							<c:choose>
								<c:when test="${selectEmpRead.todayAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.todayAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
						</tr>
						<tr>
							<td class="active">휴가 관리</td>
							<c:choose>
								<c:when test="${selectEmpRead.vactnAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.vactnAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
							<td class="active">입고 거래장 관리</td>
							<c:choose>
								<c:when test="${selectEmpRead.exchngFlorAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.exchngFlorAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
							<td class="active">행사 관리</td>
							<c:choose>
								<c:when test="${selectEmpRead.evntAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.evntAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
							<td class="active">푸쉬 알림</td>
							<c:choose>
								<c:when test="${selectEmpRead.pushNotfcatnAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.pushNotfcatnAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
							<td class="active">상품 분석</td>
							<c:choose>
								<c:when test="${selectEmpRead.prodctAnlysAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.prodctAnlysAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
						</tr>
						<tr>
							<td class="active">일정 관리</td>
							<c:choose>
								<c:when test="${selectEmpRead.schedlAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.schedlAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
							<td class="active">재고 관리</td>
							<c:choose>
								<c:when test="${selectEmpRead.stckAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.stckAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
							<td class="active">공동구매 관리</td>
							<c:choose>
								<c:when test="${selectEmpRead.togetherAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.togetherAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
							<td class="active">주문 내역 관리</td>
							<c:choose>
								<c:when test="${selectEmpRead.ordrRecrdAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.ordrRecrdAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
							<td class="active">주문 분석</td>
							<c:choose>
								<c:when test="${selectEmpRead.ordrAnlysAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.ordrAnlysAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
						</tr>
						<tr>
							<td class="active">이용약관 관리</td>
							<c:choose>
								<c:when test="${selectEmpRead.useAgremtAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.useAgremtAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
							<td colspan="6"></td>
							<td class="active">매출 분석</td>
							<c:choose>
								<c:when test="${selectEmpRead.salsAnlysAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.salsAnlysAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
						</tr>
						<tr>
							<td class="active">대신 장봐주는 남자</td>
							<c:choose>
								<c:when test="${selectEmpRead.DJNAthrty == 'on'}">
									<td><input type="checkbox" disabled="disabled" checked="checked"></td>
								</c:when>
								<c:when test="${selectEmpRead.DJNAthrty == 'N'}">
									<td><input type="checkbox" disabled="disabled"></td>
								</c:when>
							</c:choose>
							<td colspan="8"></td>
						</tr>
					</tbody>
				</table>
			</div>	
				<div class="container text-center">
					<input class="btn btn-default" type="button" id="btn_pwUpdate" value="비밀번호 수정">
					<input class="btn btn-default" type="button" id="btn_update" value="수정">
				</div>
			</form>
		</div>
		</c:forEach>
		
		<div id="updatePWModal" style="display:none">
			<div class="text-center">
				<form id="updatePWInfo" name="updatePWInfo">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th class="active">새 비밀번호</th>
								<td><input type="password" class="form-control" id="pw" name="pw" placeholder=""></td>
							</tr>
							<tr>
								<th class="active">새 비밀번호 확인</th>
								<td><input type="password" class="form-control" id="pwCheck" name="pwCheck" placeholder=""></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</body>
	
	<script type="text/javascript">	
		$(document).ready(function(){
		    $('#btn_update').click(function() {
		    	var url = "persnlDataRead.do";    
		    	$(location).attr('href',url);
		    });
		    $('#btn_cnacel').click(function() {
		    	var url = "persnlDataRead.do";    
		    	$(location).attr('href',url);
		    });

		    $('#btn_pwUpdate').click(function() {
				var width = document.body.scrollWidth*0.25//(문서 전체의 크기)500
				var height = document.body.scrollHeight*0.24 //300
				$('#updatePWModal').dialog({
				   modal: true, //뒷배경의 활성화 여부
				   draggable: false, //창의 드래그 여부
				   hide: "fade", //창이 닫길 때 효과
				   resizable: false, //리사이징 여부
				   show: "fade", //창이 열릴 때 효과
				   title: "비밀번호 변경",
				   width: width,
				   height: height,
				   maxWidth: width,
				   maxHeight: height,
				   //버튼종류
				   buttons: [
				      {
				         //버튼텍스트
				         text: "변경하기",
				         //클릭이벤트발생시 동작
				         click: function() {
				        	var pw = $("#pw").val();
							var pwcheck = $("#pwCheck").val();
				        	if(pw!=pwcheck){
								alert("비밀번호가 다릅니다.");
							}
							else if ($("#pw").val().length < 6 || $("#pw").val().length >12) {
				            	alert("비밀번호를 6~12자로  입력하세요.");
				            	$("#empPw").focus();
				            	return;
					        }
							else if(pw==pwcheck){
								var formData = $("#updatePWInfo").serialize();
					        	$.ajax({
									type : "POST",
								    url : "/pwUpdate.do",
								    data : formData,
								    success: function(msg) {
								    	$("#pwCheck").val("");
										$("#pw").val("");
								      }
								});
					        	alert("비밀번호가 수정되었습니다.");
					        	$( this ).dialog( "close" );
				        	}
				         }
				      },
				      {
				         //버튼텍스트
				         text: "취소",
				         //클릭이벤트발생시 동작
				         click: function() {
				        	$("#pwCheck").val("");
							$("#pw").val("");
				            $( this ).dialog( "close" );
				         }
				      }
				   ]
				});
				$('.ui-dialog-buttonset').children('button').attr('class', 'btn btn-default');
			});
		});
	</script>
</html>