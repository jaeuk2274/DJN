<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 
* 회원 목록을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        최재욱, 1.0, 2016/08/09 – 초기 작성
* author : 최재욱
* version : 1.0, 2016/08/09  - 초기 작성
* see : 회원 목록 조회 화면.
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
			<form name="custmrPagingFrm">  
				<input type="hidden" name="custmrSechOption" value="${CustmrValueObject.custmrSechOption}"> <!-- 고객 검색 조건 -->
				<input type="hidden" name="custmrSechText" value="${CustmrValueObject.custmrSechText}"> <!-- 고객 검색 검색어 -->
				<input type="hidden" name="custmrSechCardCheck" value="${CustmrValueObject.custmrSechCardCheck}"> <!-- 고객 검색 카드여부 -->
				<input type="hidden" name="currentPageNo" value="${CustmrValueObject.currentPageNo}"> <!-- 현재 페이지 -->	
				<input type="hidden" name="custmrChkGroup"> 
			</form>	
			
			<div class="container">
				<h3>회원 목록</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
				   <h5>
				     <img src="/resources/image/common/home.png"> > 고객 관리 > 
				     <a href="/custmrList.do" style="text-decoration:none"><strong>고객 목록</strong></a>
				   </h5>
				</div>
			</div>
			
			<div class="container form-inline">
				<form name="custmrSechFrm" method="post">
					<table class="table table-bordered">
						<tr>
							<th class="active">
	                        	<h5 class="text-center"><b>개인정보</b></h5>
	                     	</th>
							<td>
								<div class="form-group">
									<select id="custmrSechOption" class="form-control" name="custmrSechOption">
										<option value="id" ${CustmrValueObject.custmrSechOption eq "id" ? "selected" : ""}> 아이디  </option>
										<option value="nme" ${CustmrValueObject.custmrSechOption eq "nme" ? "selected" :""}> 이름  </option>
										<option value="mobl" ${CustmrValueObject.custmrSechOption eq "mobl" ? "selected" :""}> 휴대전화  </option>
									</select>
								</div>						
								<input type="search" class="form-control" id="custmrSechText" name="custmrSechText" >
							</td>
						</tr>
						<tr>
							<th class="active">
	                        	<h5 class="text-center"><b>카드여부</b></h5>
	                     	</th>
							<td>
								<!-- 3항 연산자 사용 값 같으면 자동 체크-->
								<input type="radio" class="radio-inline" name="custmrSechCardCheck" value="all" ${CustmrValueObject.custmrSechCardCheck eq "all" ? "checked" : ""}> 전체
								<input type="radio" class="radio-inline" name="custmrSechCardCheck" value="Y" ${CustmrValueObject.custmrSechCardCheck eq "Y" ? "checked" : ""}> 보유
								<input type="radio" class="radio-inline" name="custmrSechCardCheck" value="N" ${CustmrValueObject.custmrSechCardCheck eq "N" ? "checked" : ""}> 미보유
								<input type="radio" class="radio-inline" name="custmrSechCardCheck" value="R" ${CustmrValueObject.custmrSechCardCheck eq "R" ? "checked" : ""}> 요청
							</td>
						</tr>
					</table>
				</form>
			</div>
			
			<div class="container text-center">
				<input type="button" class="btn btn-default" onclick="javacript:custmrSech()" value="검 색">
			</div>
			
			<div class="form-group"></div>
			
			<div class="container form-inline">
				<select class="form-control col-lg-6 col-sm-4">
					<option value="">보유</option>
				</select>
				<div class="col-lg-6 col-sm-8">
					<button class="btn btn-default" onclick="javascript:groupChange()">일괄 변경</button>
				</div>
			</div>
			
			<div class="container">
				<form name="custmrCheckFrm" method="post">
					<table class="table table-bordered">
						<colgroup>
							<col class="col-md-1 col-sm-1"/>                
						    <col class="col-md-2 col-sm-2"/>          
						    <col class="col-md-3 col-sm-3"/>           
						    <col class="col-md-3 col-sm-2"/>
						    <col class="col-md-2 col-sm-2"/>          
						    <col class="col-md-1 col-sm-2"/>
					   </colgroup>
						<thead>
						  <tr class="active">
	                     	<th class="text-center">
								<input type="checkbox" id="allCheck">
							</th>
							<th class="text-center">
							 	<b>No</b>
							</th>
							<th class="text-center">
							   <b>이름</b>
							</th>
							<th class="text-center">
								<b>아이디</b>
							</th>
							<th class="text-center">
								<b>휴대전화</b>
							</th>
							<th class="text-center">
								<b>카드여부</b>
							</th>
						  </tr>
						 </thead>
						 <c:choose>
							<c:when test="${fn:length(CustmrList) == 0}">
								<tr class="text-center">
									<td colspan="6">
										<h4>등록된 회원이 없습니다</h4>
									</td>
								</tr>
							</c:when>
							<c:when test="${fn:length(CustmrList) != 0}">
								<c:forEach items="${CustmrList}" var="CustmrVO" varStatus="status">	
									<tr onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">							
										<td class="text-center" >
											<input type="checkbox" name="check" value="${CustmrVO.custmrSeq}">
										</td>
										<td class="text-center" onclick="javascript:custmrReadPopup(${CustmrVO.custmrSeq})">
											${CustmrValueObject.totalRecordCount-status.index-(CustmrValueObject.currentPageNo-1)*10}
										</td>
										<td class="text-center" onclick="javascript:custmrReadPopup(${CustmrVO.custmrSeq})">
											${CustmrVO.custmrNme}
										</td>							
										<td class="text-center" onclick="javascript:custmrReadPopup(${CustmrVO.custmrSeq})">
											${CustmrVO.custmrId}
										</td>	 
										<td class="text-center" onclick="javascript:custmrReadPopup(${CustmrVO.custmrSeq})">
											${CustmrVO.custmrMobl}
										</td>
										<td class="text-center" onclick="javascript:custmrReadPopup(${CustmrVO.custmrSeq})"> 
											<c:if test = "${CustmrVO.custmrCardCheck eq 'Y'}">
												보유
											</c:if>
											<c:if test = "${CustmrVO.custmrCardCheck eq 'N'}">
												미보유
											</c:if>
											<c:if test = "${CustmrVO.custmrCardCheck eq 'R'}">
												요청
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>	
					</table>
				</form>
			</div>
			
			<div class="container text-center">
				<nav>
					<ul class="pagination">
						<!-- 무조건 1페이지로 << 버튼 -->
						<c:if test = "${CustmrValueObject.currentPageNo>20}">
							<li>
								<a href="javascript:custmrPaging(1)" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span></a>
							</li>
						</c:if>  
						<!-- 한 단위 앞으로 < 버튼 -->
						<c:if test = "${CustmrValueObject.currentPageNo>10}">
							<li>
								<a href="javascript:custmrPaging(${CustmrValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
								<span aria-hidden="true">&lsaquo;</span></a>
							</li>
						</c:if> 	
									  	
						<c:forEach varStatus="status" begin="${CustmrValueObject.firstPageNoOnPageList}" end="${CustmrValueObject.lastPageNoOnPageList}">
						
							<c:if test="${CustmrValueObject.currentPageNo==status.current}">
								<li class="active">
									<a href="javascript:custmrPaging(${status.current})">${status.current}</a>
								</li>
							</c:if>
							<c:if test="${CustmrValueObject.currentPageNo!=status.current}">
								<li><a href="javascript:custmrPaging(${status.current})">${status.current}</a></li>
							</c:if>                   									
						</c:forEach>	
						
						<!-- 한 단위 뒤로 > 버튼 -->
						<c:if test = "${CustmrValueObject.firstPageNoOnPageList + 10 < CustmrValueObject.totalPageCount}">
							<li>
								<a href="javascript:custmrPaging(${CustmrValueObject.lastPageNoOnPageList+1})" aria-label="Next">
								<span aria-hidden="true">&rsaquo;</span></a>
							</li>
						</c:if>  
						<!-- 무조건 마지막페이지로 >> 버튼 -->
						<c:if test = "${CustmrValueObject.firstPageNoOnPageList + 20 < CustmrValueObject.totalPageCount}">
							<li>
								<a href="javascript:custmrPaging(${CustmrValueObject.totalPageCount})" aria-label="Next">
								<span aria-hidden="true">&raquo;</span></a>
							</li>
						</c:if>  
					</ul>
				</nav>
			</div>
		</div>
		<script>
			// 고객 검색
			function custmrSech() {
				document.custmrSechFrm.action ="/custmrList.do";
				document.custmrSechFrm.submit();
			};
			
			// 고객 페이징
			function custmrPaging(page) {
				document.custmrPagingFrm.currentPageNo.value = page;
				document.custmrPagingFrm.action ="/custmrList.do";
				document.custmrPagingFrm.submit();
			};
			
			// 고객 상세보기
			function custmrReadPopup(custmrSeq) {
				var defH, defW, sTop, sLeft, url;
				defW = 500;
				defH = 550;
				sTop = (screen.height - defH)/2;
				sLeft= (screen.width  - defW)/2;
				url = "/custmrReadPopup.do?custmrSeq="+custmrSeq;
				popWin = window.open(url, "고객 상세보기", "width="+defW+", height="+defH+", top="+sTop+",left="+sLeft+", scrollbars=yes, marginwidth=0, marginheight=0");
	
				popWin.focus();
			};
			
			// 전체 선택, 전체 선택 해제
			$(function(){
			    //전체선택 체크박스 클릭
				$("#allCheck").click(function(){
					//만약 전체 선택 체크박스가 체크된상태일경우
					if($("#allCheck").prop("checked")) {
						//해당화면에 전체 checkbox들을 체크해준다
						$("input[type=checkbox]").prop("checked",true);
					// 전체선택 체크박스가 해제된 경우
					} else {
						//해당화면에 모든 checkbox들의 체크를해제시킨다.
						$("input[type=checkbox]").prop("checked",false);
					}
				})
			});

			// 일괄 카드 여부 변경
			function groupChange() {
				var chkGroup = document.custmrCheckFrm.check;
				var changeGroup=[];
				var cnt=0;
				
				if (chkGroup.length >= 1) {
					for ( var i = 0; i < chkGroup.length; i++) {
						if (chkGroup[i].checked) {
							cnt++;
							changeGroup.push(chkGroup[i].value);
						}
					}
					if (cnt == 0) {
						alert("선택한 체크박스가 없습니다.");
						return;
					}
				} 
				
// 				else {	 
// 					  if (chkGroup.checked) {
// 					   cnt++;
// 					   changeGroup.push(chkGroup.value);
// 					  }
// 					  if (cnt == 0) {
// 					 	alert("선택한 체크박스가 없습니다.");
// 					   	return;
// 					  }
// 				}
				
				var chkChange = confirm("이 "+changeGroup.length+"명의 카드 여부 변경을 하시겠습니까?"); 
				if(chkChange == true) {
					document.custmrPagingFrm.custmrChkGroup.value=changeGroup;
// 					document.custmrPagingFrm.method = "post";
					document.custmrPagingFrm.action ="/changeCustmrCardChkGroup.do";
					document.custmrPagingFrm.submit();		
				}
				
				else {
					return;
				}
			};	
			
			function changeTrColor(trObj, oldColor, newColor) {
				trObj.style.backgroundColor = newColor;
				trObj.onmouseout = function(){
					trObj.style.backgroundColor = oldColor;
				};
			};
		</script>
	</body>
</html>