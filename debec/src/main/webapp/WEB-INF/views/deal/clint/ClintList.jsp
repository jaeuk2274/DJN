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
		
   </head>
   <body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		<div class="container">
	   		<form name="clintPagingFrm" method="post">      
	   			<input type="hidden" name="clintListSelectBox" value="${clintVo.clintListSelectBox}">		<!-- 검색조건 -->
	   			<input type="hidden" name="clintListSearchText" value="${clintVo.clintListSearchText}">	<!--  검색어 -->
				<input type="hidden" name="currentPageNo" value="${clintVo.currentPageNo}"> <!-- 현재 페이지 -->
				<input type="hidden" name="clintSeq" value="${clintVo.clintSeq}">
	      	</form>
	      	
			<div class="container">
				<h3>거래처 목록</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
				   <h5>
				     <img src="/resources/image/common/home.png"> > 거래처관리 > 
				     <a href="/ClintList.do" style="text-decoration:none"><strong>거래처 목록</strong></a>
				   </h5>
				</div>
			</div>
			<div class="form-inline">
				<form name="clintFrm" method="post">
					<div class="container form-group">
						<table class="table table-bordered">
							<tr>
								<th class="active">
			                      	<h5 class="text-center">(통합)거래처 검색</h5>
			                   	</th>
								<td>
									<!-- 검색   -->
									<select class="form-control" id="clintListSelectBox" name="clintListSelectBox">
										<option value="clint_nme">거래처명
										<option value="clint_mangr">담당자
									</select>
									<input type="text" class="form-control" id="clintListSearchText" name="clintListSearchText" style="width:400px">
								</td>
							</tr>
						</table>
					</div>
						
					<div class="container text-center">
						<input type="button" class="btn btn-default" value="검색" onclick="javascript:clintListSearch()">
					</div>
					
					<div class="form-group"></div>
					
					<!-- 거래처 목록 리스트 -->
					<div class="container">
						<table class="table table-bordered">
							<colgroup>
								<col class="col-md-1"/>                
							    <col class="col-md-3"/>          
							    <col class="col-md-2"/>           
							    <col class="col-md-2"/>
							    <col class="col-md-3"/>          
							    <col class="col-md-1"/>
						   </colgroup>
							<tr class="active">
								<th class="text-center">
									<input type="checkbox" id="allClintCheck">
								</th>
								<th class="text-center">
									거래처명
								</th>
								<th class="text-center">
									담당자
								</th>
								<th class="text-center">
									담당자 번호
								</th>
								<th class="text-center">
									계좌 번호
								</th>
								<th class="text-center">
									부가세
								</th>
							</tr>
							<c:choose>
								<c:when test="${fn:length(clintList) == 0}">
									<tr class="text-center">
										<td colspan="6">
											<h4>등록된 거래처가 없습니다</h4>
										</td>
									</tr>
								</c:when>
								<c:when test="${fn:length(clintList) != 0}">
									<c:forEach items="${clintList}" var="clint" varStatus="status">
										<tr onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
											<td class="text-center"><input type="checkbox" name="clintCheck" value="${clint.clintSeq}"></td>
											<td class="text-center" onclick="javascript:clintRead(${clint.clintSeq})">${clint.clintNme}</td>
											<td class="text-center" onclick="javascript:clintRead(${clint.clintSeq})">${clint.clintMangr}</td>
											<td class="text-center" onclick="javascript:clintRead(${clint.clintSeq})">${clint.clintMangrNum}</td>
											<td class="text-center" onclick="javascript:clintRead(${clint.clintSeq})">${clint.clintAcoutNum}</td>
											<td class="text-center" onclick="javascript:clintRead(${clint.clintSeq})">${clint.clintSurtaxCheck}</td>
										</tr>
									</c:forEach>
								</c:when>
							</c:choose>
						</table>
					</div>
				</form>
				
				<div class="container text-right">
					<input type="button" class="btn btn-default" value="거래처 등록" onclick="javascript:clintCret()">
					<input type="button" class="btn btn-default" value="삭 제" onclick="javascript:clintDelete()">
				</div>
				
				<!-- 페이징 -->
				<div class="container text-center">
					<nav>
						<ul class="pagination">
							<!-- 무조건 1페이지로 << 버튼 -->
							<c:if test = "${clintVo.currentPageNo>20}">
								<li>
									<a href="javascript:clintPaging(1)" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span></a>
								</li>
							</c:if>  
							<!-- 한 단위 앞으로 < 버튼 -->
							<c:if test = "${clintVo.currentPageNo>10}">
								<li>
									<a href="javascript:clintPaging(${clintVo.firstPageNoOnPageList-1})" aria-label="Previous">
									<span aria-hidden="true">&lsaquo;</span></a>
								</li>
							</c:if> 	
										  	
							<c:forEach varStatus="status" begin="${clintVo.firstPageNoOnPageList}" end="${clintVo.lastPageNoOnPageList}">
							
								<c:if test="${clintVo.currentPageNo==status.current}">
									<li class="active">
										<a href="javascript:clintPaging(${status.current})">${status.current}</a>
									</li>
								</c:if>
								
								<c:if test="${clintVo.currentPageNo!=status.current}">
									<li>
										<a href="javascript:clintPaging(${status.current})">${status.current}</a>
									</li>
								</c:if>   
								                									
							</c:forEach>	
							
							<!-- 한 단위 뒤로 > 버튼 -->
							<c:if test = "${clintVo.firstPageNoOnPageList + 10 < clintVo.totalPageCount}">
								<li>
									<a href="javascript:clintPaging(${clintVo.lastPageNoOnPageList+1})" aria-label="Next">
									<span aria-hidden="true">&rsaquo;</span></a>
								</li>
							</c:if>  
							<!-- 무조건 마지막페이지로 >> 버튼 -->
							<c:if test = "${clintVo.firstPageNoOnPageList + 20 < clintVo.totalPageCount}">
								<li>
									<a href="javascript:clintPaging(${clintVo.totalPageCount})" aria-label="Next">
									<span aria-hidden="true">&raquo;</span></a>
								</li>
							</c:if>  
						</ul>
					</nav>
				</div>	
			</div>
		</div>
		<script>
			// 	검색
			function clintListSearch(){
				document.clintPagingFrm.clintListSelectBox.value = $("#clintListSelectBox").val();
				document.clintPagingFrm.clintListSearchText.value = $("#clintListSearchText").val();
				document.clintPagingFrm.currentPageNo.value = 1;
				document.clintPagingFrm.action ="/ClintList.do";
				document.clintPagingFrm.submit();
			};
			
			// 거래처 페이징
			function clintPaging(page) {
				document.clintPagingFrm.currentPageNo.value = page;
				document.clintPagingFrm.action ="/ClintList.do";
				document.clintPagingFrm.submit();
			};
			
			// 상세보기
			function clintRead(clintSeq){
				document.clintPagingFrm.clintSeq.value=clintSeq;
				document.clintPagingFrm.action="/ClintRead.do";
				document.clintPagingFrm.submit();
	// 			location.href="/ClintRead.do?clintSeq="+clintSeq;
			};	 
			// 거래처 등록
			function clintCret(){
				location.href="/ClintCreateRead.do";	
			};
			// 거래처 전체 체크
	         $(function(){
	             //전체선택 체크박스 클릭
	            $("#allClintCheck").click(function(){
	               //만약 전체 선택 체크박스가 체크된상태일경우
	               if($("#allClintCheck").prop("checked")) {
	                  //해당화면에 전체 checkbox들을 체크해준다
	                  $("input[type=checkbox]").prop("checked",true);
	               // 전체선택 체크박스가 해제된 경우
	               } else {
	                  //해당화면에 모든 checkbox들의 체크를해제시킨다.
	                  $("input[type=checkbox]").prop("checked",false);
	               }
	            });
	         });
			 // 거래처 삭제
	 		function clintDelete(){
	 			var checkGroup = document.clintFrm.clintCheck;
// 	 			alert(checkGroup + " 체크한 항목들");
	 			var changeGroup=[];
	 			var cnt=0;
	 			
	 			if(checkGroup.length >= 1){
// 	 				alert("if들어옴");
	 				for(var i=0; i<checkGroup.length; i++){
	 					
	 					if(checkGroup[i].checked){
	 						
	 						cnt++;
	 						changeGroup.push(checkGroup[i].value);
	 					}
	 				}
	 				if(cnt == 0){
	 					alert("선택한 체크박스가 없습니다.");
	 					return;
	 				}
	 			}
	 			else{
// 	 				alert("else if 들어옴");
	 				if(checkGroup.checked){
	 					
	 					cnt++;
	 					changeGroup.push(checkGroup.value);
	 				}
	 				if(cnt==0){
	 					alert("선택한 체크박스가 없습니다.");
	 					return;
	 				}
	 			}
// 	 		 	alert(changeGroup);
	 			document.clintFrm.action="/ClintDelete.do?changeGroup="+changeGroup;
	 			document.clintFrm.submit();
	 			
	 		};
	 		
	 		function changeTrColor(trObj, oldColor, newColor) {
				trObj.style.backgroundColor = newColor;
				trObj.onmouseout = function(){
					trObj.style.backgroundColor = oldColor;
				}
			};
		</script>
	</body>
</html>