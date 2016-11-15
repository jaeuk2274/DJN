<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 
* 직원 정보를 상세 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        이인호, 1.0, 2016/08/10 – 초기 작성
* author : 이인호
* version : 1.0, 2016/08/10  - 초기 작성
* see : 관련된 모듈을 기술한다.
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
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
		<div class="container">
			<!-- 직원 정보 -->
			<div class="container">
				<h3>직원 상세보기</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
					<h5>
						<img src="/resources/image/common/home.png"> > 직원 관리 > 
						<a href="/empList.do" style="text-decoration:none"><strong>직원 목록</strong></a>
					</h5>
				</div>
			</div>
			
			<c:forEach var="selectEmpRead" items="${selectEmpRead}" varStatus="status">
			<form name="empListInfo" class="form-inline" method="post">
				<input type="hidden" name="empSeq" id="empSeq" value="${selectEmpRead.empSeq}">
				<input type="hidden" name="schedlSeq" value="0"> <!-- 휴가고유값(숫자값) -->
				<input type="hidden" name="currentPageNo" id="currentPageNo" value="${empValueObject.currentPageNo}"> <!-- 페이지(숫자값) -->
			</form>
			
			<div class="container form-group">
				<table class="table table-bordered">
					<tr>
						<td rowspan="4" style="width:200px; height:260px">
							<img src="../../../../${selectEmpRead.imgRot}" class="img-responsive img-rounded" style="width:100%; height:100%">
						</td>
						<td class="active">
							<h5 class="text-center"><b>아이디</b></h5>
						</td>
						<td><c:out value="${selectEmpRead.id}"/></td>
						<td class="active">
							<h5 class="text-center"><b>이름</b></h5>
						</td>
						<td><c:out value="${selectEmpRead.nme}"/></td>
						<td class="active">
							<h5 class="text-center"><b>생년월일</b></h5>
						</td>
						<td><c:out value="${selectEmpRead.birth}"/></td>
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>성별</b></h5>
						</td>
						<td><c:out value="${selectEmpRead.gendr}"/></td>
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
							<h5 class="text-center"><b>입사일</b></h5>
						</td>
						<td><c:out value="${selectEmpRead.hirDat}"/></td>
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
						<td colspan="5">
							<c:out value="${selectEmpRead.postcd}"/>
							<c:out value="${selectEmpRead.adrs}"/>&nbsp;
							<c:out value="${selectEmpRead.detlAdrs}"/>
						</td>
					</tr>
				</table>
			</div>
			
			<!-- 메뉴 권한 -->
			<div class="container">
				<h3>메뉴 권한 관리</h3>
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
			<div class="container text-right">
				<input class="btn btn-default" type="button" value="비밀번호 수정" id="pwUpdate">
				<input class="btn btn-default" type="button" value="수  정" id="btn_update">
				<input class="btn btn-default" type="button" value="삭  제" id="btn_delete">
			</div>
			</c:forEach>
			
			<!-- 직원 휴가 내역 -->
			<div class="container">
				<h3>직원 휴가 내역</h3>
			</div>
			<div class="container form-group">
				<table class="table table-bordered text-center">
					<thead>
						<tr class="active">
							<th class="text-center">종류</th>
							<th class="text-center">시작일자</th>
							<th class="text-center">종료일자</th>
							<th class="text-center">사유</th>
						</tr>
					</thead>
					<tbody>
					<c:choose>
						<c:when test="${fn:length(selectVactnList) == 0}">
							<tr class="text-center">
								<td colspan="4">
									<h4>등록된 휴가가 없습니다</h4>
								</td>
							</tr>
						</c:when>
						<c:when test="${fn:length(selectVactnList) != 0}">
							<c:forEach var="selectVactnList" items="${selectVactnList}" varStatus="status">
								<tr>
									<td><a href="javascript:vactnReadPopup(${selectVactnList.schedlSeq})"><c:out value="${selectVactnList.vactnType}"/></a></td>
									<td><c:out value="${selectVactnList.schedlStartDat}"/></td>
									<td><c:out value="${selectVactnList.schedlEndDat}"/></td>
									<td><c:out value="${selectVactnList.schedlResn}"/></td>
								</tr>
							</c:forEach>
						</c:when>
						</c:choose>
					</tbody>
				</table>
			</div>
			<div class="container text-center">
				<ul class="pagination">
					<!-- 무조건 1페이지로 << 버튼 -->
					<c:if test = "${empValueObject.currentPageNo>20}">
						<li>
							<a href="javascript:empReadPagingForm(1)" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>  
					<!-- 한 단위 앞으로 < 버튼 -->
					<c:if test = "${empValueObject.currentPageNo>10}">
						<li>
							<a href="javascript:empReadPagingForm(${empValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
							<span aria-hidden="true">&lsaquo;</span></a>
						</li>
					</c:if> 	
					<c:forEach varStatus="status" begin="${empValueObject.firstPageNoOnPageList}" end="${empValueObject.lastPageNoOnPageList}">
						<c:if test="${empValueObject.currentPageNo==status.current}">
							<li class="active">
								<a href="javascript:empReadPagingForm(${status.current})">${status.current}</a>
							</li>
						</c:if>
						<c:if test="${empValueObject.currentPageNo!=status.current}">
							<li><a href="javascript:empReadPagingForm(${status.current})">${status.current}</a></li>
						</c:if>                   									
					</c:forEach>	
					<!-- 한 단위 뒤로 > 버튼 -->
					<c:if test = "${empValueObject.firstPageNoOnPageList + 10 < empValueObject.totalPageCount}">
						<li>
							<a href="javascript:empReadPagingForm(${empValueObject.lastPageNoOnPageList+1})" aria-label="Next">
							<span aria-hidden="true">&rsaquo;</span></a>
						</li>
					</c:if>  
					<!-- 무조건 마지막페이지로 >> 버튼 -->
					<c:if test = "${empValueObject.firstPageNoOnPageList + 20 < empValueObject.totalPageCount}">
						<li>
							<a href="javascript:empReadPagingForm(${empValueObject.totalPageCount})" aria-label="Next">
							<span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>  
				</ul>
			</div>
		</div>
		
		<!-- 비밀번호 수정 -->
		<div id="empPWUpdateModal" style="display:none;">
			<aside>
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
			</aside>
		</div>
		
		<script>	
			function vactnReadPopup(schedlSeq) {
				var popUrl = "vactnReadPopup.do";
				var popupName= "vactnReadPopup";
				var width = 600;
				var height = 300;
				var leftPosition = (screen.width/2) - (width/2);
					leftPosition += window.screenLeft;//듀얼 모니터일때
				var topPosition = (screen.height/2) - (height/2);
				
				var win = window.open(popUrl, popupName, "left="+leftPosition+", top="+topPosition+", toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, width="+width+", height="+height);
				
				document.empListInfo.target = popupName;
				document.empListInfo.schedlSeq.value = schedlSeq;
				document.empListInfo.action = "/vactnReadPopup.do";
				document.empListInfo.submit();
				
				if(win == null) {//만약 팝업 차단으로 팝업창이 나타나지 않을 경우
					alert("팝업차단을 해제해주세요!");
				}
				else {
					win.focus();	         
				}
			};
			
			function empReadPagingForm(pageNo){
				var empSeq = document.getElementById("empSeq").value;
				document.empListInfo.currentPageNo.value = pageNo;
	 			document.empListInfo.empSeq.value = empSeq;
				document.empListInfo.action = "/empRead.do";
				document.empListInfo.submit();
			};
			
			//제이쿼리
			$(document).ready(function(){
				
	            $('#btn_update').click(function() {
	                var url = "/empUpdateRead.do";    
	                var empSeq = $("#empSeq").val();
	                $(":hidden[name='empSeq']").val(empSeq);
	                $("[name='empListInfo']").attr('action',url);
	                $("[name='empListInfo']").submit();
	             });
	            
	            $('#btn_delete').click(function() {
	                var url = "/empDelete.do";    
	                var empSeq = $("#empSeq").val();
	                $(":hidden[name='empSeq']").val(empSeq);
	                $("[name='empListInfo']").attr('action',url);
	                $("[name='empListInfo']").submit();
	             });
	            
			    $('#pwUpdate').click(function(){
			    	var width = document.body.scrollWidth*0.25//(문서 전체의 크기)420
					var height = document.body.scrollHeight*0.24 //220
					$('#empPWUpdateModal').dialog({
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
												type:"POST",
												url:"/empPWUpdate.do",
												data:formData,
												success:function(data){
													$("#pwCheck").val("");
													$("#pw").val("");
												},
												error:function(request,status,error){
											        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
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
	</body>
</html>