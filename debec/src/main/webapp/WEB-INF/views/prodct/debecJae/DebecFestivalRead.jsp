<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 대백제 상세보기 JSP 입니다.
* 
* history : 김민아, 1.0, 2016/08/08 - 초기 작성
* author : 김민아
* version : 1.0, 2016/08/08  - 초기 작성
* see : 대백제 상세보기
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
	</head>
	<body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
		<form name="debecFestivalReadFrm" enctype="multipart/form-data">      
			<input type="hidden" name="schedlSeq" value="${debecFestivalValueObject.schedlSeq}"> <!-- 행사 고유값 -->
		</form>
		
		<div class="container">
			<div class="container">
				<h3>대백제 상세조회</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
					<h5>
						<img src="/resources/image/common/home.png" style="width: 22px;"> > 대백제 관리 > 
						<a href="/debecFestivalList.do" style="text-decoration:none"><strong>대백제 목록</strong></a>
					</h5>
				</div>
			</div>
		
			<div class="container">
				<table class="table table-bordered">
					<colgroup>
						<col class="col-md-4 col-sm-4"/>                
					    <col class="col-md-8 col-sm-8"/>   
					</colgroup>
					<tr>
						<td class="active" colspan="2">
							<h5 class="text-center"><b>배너이미지</b></h5>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<img src="/${debecFestivalValueObject.evntImg}">
						</td>
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>행사명</b></h5>
						</td>
						<td ><c:out value="${debecFestivalValueObject.schedlTitl}"/></td> 
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>기간</b></h5>
						</td>
						<td><c:out value="${debecFestivalValueObject.schedlStartDat}"/><strong>~</strong><c:out value="${debecFestivalValueObject.schedlEndDat}"/></td>
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>적용 상태</b></h5>
						</td>
						<td>						
							<c:if test = "${debecFestivalValueObject.evntStat=='Y'}">
								<c:out value="진행중"/>
							</c:if>
							<c:if test = "${debecFestivalValueObject.evntStat=='N'}">
								<c:out value="종료"/>
							</c:if>
							<c:if test = "${debecFestivalValueObject.evntStat=='R'}">
								<c:out value="대기중"/>
							</c:if>
						</td>
					</tr>
				</table>
			</div>
			
			<div class="container text-right">
				<input class="btn btn-default" type="button" value="목록" onclick="javascript:location.href='/debecFestivalList.do'"/>
				<input class="btn btn-default" type="button" value="수정" onclick="javascript:debecFestivalUpdateRead()"/>
				<input class="btn btn-default" type="button" value="삭제" onclick="javascript:debecFestivalDelete()"/>
			</div>

			<div class="container">
				<h3>상품 리스트</h3>
			</div>
			
			<div class="container">
				<table class="table table-bordered text-center">
					<colgroup>
						<col class="col-md-1 col-sm-1"/>           
					    <col class="col-md-2 col-sm-2"/>
					    <col class="col-md-3 col-sm-3"/>           
					    <col class="col-md-1 col-sm-1"/>
					    <col class="col-md-1 col-sm-1"/>           
					    <col class="col-md-1 col-sm-1"/>
					    <col class="col-md-1 col-sm-1"/>           
					    <col class="col-md-1 col-sm-1"/>
					    <col class="col-md-1 col-sm-1"/>           
					</colgroup>
					<tr class="active">
						<th class="text-center">바코드</th>
						<th class="text-center">이미지</th>
						<th class="text-center">상품명</th>
						<th class="text-center">재고량</th>
						<th class="text-center">진열 여부 </th>
						<th class="text-center">판매가</th>
						<th class="text-center">할인율(%)</th>
						<th class="text-center">세일가</th>
						<th class="text-center">마진율(%)</th>
					</tr>
					<c:forEach var="debecFestivalProdctList" items="${debecFestivalProdctList}" varStatus="status">
					<tr>
						<td class="text-center"><c:out value="${debecFestivalProdctList.prodctSeq}"/></td>
						<td class="text-center"><img src="${debecFestivalProdctList.mainImg}" class="thumbnail"/></td>
						<td class="text-center"><c:out value="${debecFestivalProdctList.prodctNme}"/></td>
						<td class="text-center"><c:out value="${debecFestivalProdctList.prodctQunt}"/></td>
						<td class="text-center">
							<c:if test = "${debecFestivalProdctList.displyCheck=='Y'}">
								<c:out value="진열"/>
							</c:if>
							<c:if test = "${debecFestivalProdctList.displyCheck=='N'}">
								<c:out value="미진열"/>
							</c:if>
						</td>
						<td><c:out value="${debecFestivalProdctList.selPric}"/></td><!-- 정상가 -->
						<td><c:out value="${debecFestivalProdctList.evntDiscntRat}%"/></td><!-- 할인율 -->
						<td><c:out value="${debecFestivalProdctList.salePric}"/></td><!-- 할인가 -->
						<td><c:out value="${debecFestivalProdctList.margnRat}%"/></td><!-- 마진율 -->
					</c:forEach>
				</table>
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
		</div>
		<script>
			<!--행사기간 달력 -->
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
		    });
		    
		   // 대백제 수정화면 전환
			function debecFestivalUpdateRead() {
				document.debecFestivalReadFrm.action = "/debecFestivalUpdateRead.do";
				document.debecFestivalReadFrm.method = "post";
				document.debecFestivalReadFrm.submit();
			};
			
			// 대백제 삭제
			function debecFestivalDelete() {
				document.debecFestivalReadFrm.action = "/debecFestivalSigleDelete.do";
				document.debecFestivalReadFrm.method = "post";
				document.debecFestivalReadFrm.submit();
			};
			
			$(document).ready(function() {
				   //이미지 섬네일
				    var xOffset = 10;
				    var yOffset = 30;
				    $(document).on("mouseover",".thumbnail",function(e){ //마우스 오버시
				        $("body").append("<p id='preview'><img src='"+ $(this).attr("src") +"' width='200px' /></p>"); //보여줄 이미지를 선언                       
				        $("#preview")
				            .css("top",(e.pageY - xOffset) + "px")
				            .css("left",(e.pageX + yOffset) + "px")
				            .fadeIn("fast"); //미리보기 화면 설정 셋팅
				    });     
				    $(document).on("mousemove",".thumbnail",function(e){ //마우스 이동시
				        $("#preview")
				            .css("top",(e.pageY - xOffset) + "px")
				            .css("left",(e.pageX + yOffset) + "px");
				    });     
				    $(document).on("mouseout",".thumbnail",function(){ //마우스 아웃시
				        $("#preview").remove();
				    });
				});
		</script>
	</body>
</html>