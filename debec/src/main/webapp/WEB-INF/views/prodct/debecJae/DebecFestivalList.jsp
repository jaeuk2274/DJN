<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 
* 대백제 조회 JSP 입니다.
* 
* history : 김민아, 1.0, 2016/08/08 - 초기 작성
* author : 김민아
* version : 1.0, 2016/08/08  - 초기 작성
* see : 대백제 조회 
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
		
		<script src="/resources/jquery.tablednd.js"></script>
		<!-- a 태그 밑줄 뜨지 않도록 하는 스타일 -->

		
	</head>
	
	<body>	
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
		<form name="debecFestivalPagingFrm" method="post">      
			<input type="hidden" name="currentPageNo" value="${debecFestivalValueObject.currentPageNo}"> <!-- 현재 페이지 -->
			<input type="hidden" name="debecFestivalChkGroup" value=""> 
			<input type="hidden" name="schedlSeq" value="${debecFestivalValueObject.schedlSeq}">
			<input type="hidden" name="schedlStartDat" value="${debecFestivalValueObject.schedlStartDat}">
			<input type="hidden" name="schedlEndDat" value="${debecFestivalValueObject.schedlEndDat}">
			<input type="hidden" name="schedlTitl" value="${debecFestivalValueObject.schedlTitl}">		
			<input type="hidden" name="evntStat" value="${debecFestivalValueObject.evntStat}">				
		</form>
	
		<div class="container">
			<h3>대백제 목록</h3>
			<div style="display:inline-block; margin-top:1%; float:right">
				<h5>
					<img src="/resources/image/common/home.png" style="width: 22px;"> > 대백제 관리 > 
					<a href="/debecFestivalList.do" style="text-decoration:none"><strong>대백제 목록</strong></a>
				</h5>
			</div>
		</div>
		
		<div class="container form-inline">
			<form name="debecFestivalSechFrm">
				<table class="table table-bordered">
					<tr>
						<td class="active ">
							<h5 class="text-center"><b>기 간</b></h5>
						</td>
						<td>
							<input type="text" class="form-control" id="schedlStartDat" name="schedlStartDat">
							<input type="text" class="form-control" id="schedlEndDat" name="schedlEndDat">					
						</td>
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>행사명</b></h5>
						</td>
						<td><input type="text" class="form-control" id="schedlTitl" name="schedlTitl" placeholder="행사명을 입력하세요."></td>
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>적용 상태</b></h5>
						</td>
						<td>
							<select id="evntStat" class="form-control" name="evntStat"> 
								<option value="선택">선택</option>
								<option value="진행중">진행중</option>
								<option value="대기중">대기중</option>
								<option value="종료">종료</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
			<div class="container text-center">
				<input type="button" class="btn btn-default" value="검색" onclick="javascript:debecFestivalSech()"/>
			</div>
		</div>
			
		<div class="form-group"></div>
		
		<form name="debecFestivalCheckFrm" method="post">      
			<div class="container">
				<table class="table table-bordered text-center">
					<colgroup>
						<col class="col-md-1 col-sm-1"/>
						<col class="col-md-1 col-sm-1"/>                 
					    <col class="col-md-3 col-sm-3"/>          
					    <col class="col-md-2 col-sm-2"/>           
					    <col class="col-md-2 col-sm-2"/>
					    <col class="col-md-1 col-sm-1"/>          
					    <col class="col-md-2 col-sm-2"/>
					   </colgroup>
					<tr class="active"> 
						<th class="text-center"><input type="checkbox" id="allCheck"></th>
						<th class="text-center">No</th>
						<th class="text-center">행사명</th>
						<th class="text-center">시작 기간</th>
						<th class="text-center">종료 기간</th>
						<th class="text-center">상품 수 </th>
						<th class="text-center">적용 상태</th>
					</tr>
					<c:choose>
						<c:when test="${fn:length(debecFestivalList) == 0}">
							<tr>
								<td colspan="6">
									<h4>등록된 대백제가 없습니다</h4>
								</td>
							</tr>
						</c:when>
						<c:when test="${fn:length(debecFestivalList) != 0}">
							<c:forEach var="debecFestivalList" items="${debecFestivalList}" varStatus="status">
								<tr id="${debecFestivalList.schedlSeq}" onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
									<td>
										<input type="checkbox" name="debecFestivalCheck" value="${debecFestivalList.schedlSeq}">
									</td>
									<td onclick="javascript:debecFestivalRead(${debecFestivalList.schedlSeq})">
										<c:out value=""/>${debecFestivalList.totalRecordCount-status.index-(debecFestivalList.currentPageNo-1)*10}
									</td>
									<td onclick="javascript:debecFestivalRead(${debecFestivalList.schedlSeq})">
										<c:out value="${debecFestivalList.schedlTitl}"/>
									</td>
									<td onclick="javascript:debecFestivalRead(${debecFestivalList.schedlSeq})">
										<c:out value="${debecFestivalList.schedlStartDat}"/>
									</td>
									<td onclick="javascript:debecFestivalRead(${debecFestivalList.schedlSeq})">
										<c:out value="${debecFestivalList.schedlEndDat}"/>
									</td>
									<td onclick="javascript:debecFestivalRead(${debecFestivalList.schedlSeq})">
										<c:out value="${debecFestivalList.prodctCount}"/>
								</td>
									<td onclick="javascript:debecFestivalRead(${debecFestivalList.schedlSeq})">
										<c:if test = "${debecFestivalList.evntStat=='Y'}">
											<c:out value="진행중"/>
										</c:if>
										<c:if test = "${debecFestivalList.evntStat=='N'}">
											<c:out value="종료"/>
										</c:if>
										<c:if test = "${debecFestivalList.evntStat=='R'}">
											<c:out value="대기중"/>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</table>
			</div>
		</form>
		
		<div class="container text-right">
			<input type="button" class="btn btn-default" id="" value="행사 등록" onclick="location.href='debecFestivalCreateRead.do'"/>
			<input type="button" class="btn btn-default" id="deleteFestival" value="행사 삭제">
		</div>
		
		<!-- 페이징 -->
		<div class="container text-center">
			<nav>
				<ul class="pagination">
					<c:if test = "${debecFestivalValueObject.currentPageNo>20}">
						<li>
							<a href="javascript:debecFestivalPaging(1)" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${debecFestivalValueObject.currentPageNo>10}">
						<li>
							<a href="javascript:debecFestivalPaging(${debecFestivalValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
							<span aria-hidden="true">&lsaquo;</span></a>
						</li>
					</c:if> 	
								  	
					<c:forEach varStatus="status" begin="${debecFestivalValueObject.firstPageNoOnPageList}" end="${debecFestivalValueObject.lastPageNoOnPageList}">
					
						<c:if test="${debecFestivalValueObject.currentPageNo==status.current}">
							<li class="active">
								<a href="javascript:debecFestivalPaging(${status.current})">${status.current}</a>
							</li>
						</c:if>
						<c:if test="${debecFestivalValueObject.currentPageNo!=status.current}">
							<li><a href="javascript:debecFestivalPaging(${status.current})">${status.current}</a></li>
						</c:if>                   									
					</c:forEach>	
					<c:if test = "${debecFestivalValueObject.firstPageNoOnPageList + 10 < debecFestivalValueObject.totalPageCount}">
						<li>
							<a href="javascript:debecFestivalPaging(${debecFestivalValueObject.lastPageNoOnPageList+1})" aria-label="Next">
							<span aria-hidden="true">&rsaquo;</span></a>
						</li>
					</c:if>  
					<c:if test = "${debecFestivalValueObject.firstPageNoOnPageList + 20 < debecFestivalValueObject.totalPageCount}">
						<li>
							<a href="javascript:debecFestivalPaging(${debecFestivalValueObject.totalPageCount})" aria-label="Next">
							<span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>  
				</ul>
			</nav>
		</div> 
		<script type="text/javascript">	
			// 메인 검색
			function debecFestivalSech() {
				document.debecFestivalSechFrm.action ="/debecFestivalList.do";
				document.debecFestivalSechFrm.submit();
			};
			
			// 메인 페이징
			function debecFestivalPaging(page) {
				document.debecFestivalPagingFrm.currentPageNo.value = page;
				document.debecFestivalPagingFrm.action ="/debecFestivalList.do";
				document.debecFestivalPagingFrm.submit();
			};
			
			// 행사 상세보기
			function debecFestivalRead(schedlSeq) { 
				document.debecFestivalPagingFrm.schedlSeq.value = schedlSeq;
				document.debecFestivalPagingFrm.action = "/debecFestivalRead.do";
				document.debecFestivalPagingFrm.submit();
			};

			//리스트 색 변경
			function changeTrColor(trObj, oldColor, newColor) {
				trObj.style.backgroundColor = newColor;
				trObj.onmouseout = function(){
					trObj.style.backgroundColor = oldColor;
				}
			};
			
			
			//체크박스 전체클릭
			$(document).ready(function() {
			    $("#allCheck").click(function(){
			        //클릭되었으면
			        if($("#allCheck").prop("checked")){
			            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
			            $("input[name=debecFestivalCheck]").prop("checked",true);
	
			            //클릭이 안되있으면
			        }else{
			            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
			            $("input[name=debecFestivalCheck]").prop("checked",false);
			        }
				});
	
			    	    
				// 대백제 행사 일괄 삭제
			    $('#deleteFestival').click(function(){
				    	var chked_length = $("input[name=debecFestivalCheck]:checked").length; //체크된 박스 개수
				    	if (confirm("총 " + chked_length + "개의 항목을 삭제하시겠습니까?") == true){    //확인
				    		var chked_val = "";
							$(":checkbox[name='debecFestivalCheck']:checked").each(function(pi,po){
								chked_val += ","+po.value;
							});
							if(chked_val!="")chked_val = chked_val.substring(1);
							if(chked_val == ""){
								alert("삭제할 행사를 선택해주십시오.")
							}
							
							else{
								$.ajax({
									type:"POST",
									url:"/debecFestivalDelete.do",
									data:chked_val,
									success:function(data){
										location.reload();
									},
									error:function(request,status,error){
								        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
									}
								});
							}		
				    	}
				    	else{
				    		alert("삭제가 취소되었습니다.");	
				    	}		
				    });
	
			    
		    	// 행사기간 달력
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
			        $("#schedlStartDat").datepicker();
			    });
			    $(function() {
			        $("#schedlEndDat").datepicker();
			    });
			});
		</script>
	</body>
</html>