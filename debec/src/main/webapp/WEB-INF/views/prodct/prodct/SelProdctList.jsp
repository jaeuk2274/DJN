<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 
* 판매 상품 목록을 보여준다.
* 
* history :
*        하원식, 1.0, 2016/08/09 – 초기 작성
* author : 하원식
* version : 1.0, 2016/08/09  - 초기 작성
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
		
		<!-- 이미지 썸네일 CSS -->
		<link href="/resources/common/css/thumbnail.css" rel="stylesheet" type="text/css"/>
		
		<!-- JQuery 관련 파일들 -->
		<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>	
		
		<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
		<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
		
		<script src="resources/js/prodct-js/prodctList.js?ver=9"></script> 
		<script src="resources/js/prodct-js/catgrChoice.js?ver=5"></script> 
	</head>

	<body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
		<div class="container">
			<div class="container">
				<c:if test="${prodctValueObject.pageType==1}">
					<h3>판매 상품 목록</h3>
					<div style="display:inline-block; margin-top:1%; float:right">
						<h5>
							<img src="/resources/image/common/home.png"> > 상품 관리 > 
							<a href="/selProdctList.do" style="text-decoration:none"><strong>판매 상품 목록</strong></a>
						</h5>
					</div>
				</c:if>
				<c:if test="${prodctValueObject.pageType==2}">
					<h3>판매 중지 상품 목록</h3>
					<div style="display:inline-block; margin-top:1%; float:right">
						<h5>
							<img src="/resources/image/common/home.png"> > 상품 관리 > 
							<a href="/selStopProdctList.do" style="text-decoration:none"><strong>판매 중지 상품 목록</strong></a>
						</h5>
					</div>
				</c:if>
			</div>
			
			<form name="prodctSearchBoxFrm" class="form-inline" >
				<input type="hidden" name="currentPageNo" value="${prodctValueObject.currentPageNo}"> <!-- 현재 페이지 -->
				<input type="hidden" name="prodctSeq" value="0"> <!-- 중분류 목록 -->
				<input type="hidden" name="topCatgrNme" value="${prodctValueObject.topCatgrNme}"> <!-- 중분류 목록 -->
				<input type="hidden" name="midCatgrNme" value="${prodctValueObject.midCatgrNme}"> <!-- 소분류 목록-->
				<input type="hidden" name="selectedTopCatgrNme" value="${prodctValueObject.selectedTopCatgrNme}"> <!-- 선택된 대분류-->
				<input type="hidden" name="selectedMidCatgrNme" value="${prodctValueObject.selectedMidCatgrNme}"> <!-- 선택된 중분류 -->
				<input type="hidden" name="selectedBotCatgrNme" value="${prodctValueObject.selectedBotCatgrNme}"> <!-- 선택된 소분류-->
				<input type="hidden" name="pageType" id="pageType" value="${prodctValueObject.pageType}"> <!-- 현재 리스트 타입 -->
				
				<div class="container form-group">
					<table class="table table-bordered">
						<tr>
							<th class="active text-center">
							분류 선택
							</th>
							<td>
								<select id="topCatgrNme" class="form-control" onchange="javascript:catgrChoice('대',this.value)">
									<option selected>대분류</option>
									<c:forEach var="catgrList" items="${topCatgrArray}" varStatus="status">
										<c:if test="${catgrList ne '전체조회'}">
											<c:if test="${catgrList==prodctValueObject.selectedTopCatgrNme}">
												<option selected>${catgrList}</option>
											</c:if>
											<c:if test="${catgrList!=prodctValueObject.selectedTopCatgrNme}">
												<option>${catgrList}</option>
											</c:if>
										</c:if>
									</c:forEach>
								</select>
								<select id="midCatgrNme" class="form-control" onchange="javascript:catgrChoice('중',this.value)">
									<option selected>중분류</option>
									<c:forEach var="catgrList" items="${midCatgrArray}" varStatus="status">
										<c:if test="${catgrList ne '전체조회'}">
											<c:if test="${catgrList==prodctValueObject.selectedMidCatgrNme}">
												<option selected>${catgrList}</option>
											</c:if>
											<c:if test="${catgrList!=prodctValueObject.selectedMidCatgrNme}">
												<option>${catgrList}</option>
											</c:if>
										</c:if>
									</c:forEach>
								</select>
								<select id="botCatgrNme" class="form-control" name="botCatgrNme">
									<option selected>소분류</option>
									<c:forEach var="catgrList" items="${botCatgrArray}" varStatus="status">
										<c:if test="${catgrList ne '전체조회'}">
											<c:if test="${catgrList==prodctValueObject.selectedBotCatgrNme}">
												<option selected>${catgrList}</option>
											</c:if>
											<c:if test="${catgrList!=prodctValueObject.selectedBotCatgrNme}">
												<option>${catgrList}</option>
											</c:if>
										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th class="active text-center">
							상품명
							</th>
							<td>
								<input type="text" class="form-control" name="prodctSechText" placeholder="search" value="${prodctValueObject.prodctSechText}">
							</td>
						</tr>
						<tr>
							<th class="active text-center">
								판매액
							</th>
							<td>
								<input type="text" class="form-control" name="prodctSechPriceOne" value="${prodctValueObject.prodctSechPriceOne}">
								  원 ~ 
								<input type="text" class="form-control" name="prodctSechPriceTwo" value="${prodctValueObject.prodctSechPriceTwo}"> 원
							</td>
						</tr>
					</table>
				</div>
			</form>
			
			<div class="container text-center">
				<input class="btn btn-default" type="button" value="검색" onclick="javascript:prodctSearch()">
			</div> 
			
			<div class="form-group"></div>
			
			<div class="container text-right">
				<c:if test="${prodctValueObject.pageType==1}">
					<input type="button" class="btn btn-default" id="selProdctCreate" value="등록" >
					<input type="button" class="btn btn-default" id="selProdctDelete"value="판매중지" >
				</c:if>
				<c:if test="${prodctValueObject.pageType==2}">
					<input type="button" class="btn btn-default" id="selStopProdctReSelStrt" value="판매시작" >
					<input type="button" class="btn btn-default" id="selStopProdctDelete"value="삭제" >
				</c:if>
			</div>
			
			<div  class="container">	
				<c:if test="${prodctValueObject.pageType==1}">
					<table class="table table-bordered text-center">
						<colgroup>
							<col class="col-md-1 col-sm-1"/>                
					  		<col class="col-md-1 col-sm-1"/>          
					  		<col class="col-md-1 col-sm-1"/>           
					    	<col class="col-md-6 col-sm-6"/>
					    	<col class="col-md-1 col-sm-1"/>          
					    	<col class="col-md-1 col-sm-1"/>
					    	<col class="col-md-1 col-sm-1"/>
						</colgroup>
						<tr>
							<th class="active text-center">
								<input id="allCheck" type="checkbox">
							</th>
							<th class="active text-center">
							 바코드
							</th>
							<th class="active text-center">
							 이미지
							</th>
							<th class="active text-center">
							 상품이름
							</th>
							<th class="active text-center">
							 판매가
							</th>
							<th class="active text-center">
							 순이익
							</th>
							<th class="active text-center">
							  마진율
							</th>
						</tr>
						<c:choose>
							<c:when test="${fn:length(selProdctList) == 0}">
								<tr>
									<td colspan="7">
										<h4>등록된 상품이 없습니다</h4>
									</td>
								</tr>
							</c:when>
							<c:when test="${fn:length(selProdctList) != 0}">
								<c:forEach var="selProdct" items="${selProdctList}" varStatus="status">
									<tr onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
										<td>
											<input type="checkbox" name="prodctCheck" value="${selProdct.prodctSeq}">
										</td>
										<td onclick="javascript:selProdctRead(${selProdct.prodctSeq},1)">
										   ${selProdct.prodctSeq}
										</td>
										<td>
											<img src="${selProdct.prodctMainImage}" class="thumbnail">
										</td>
										<td onclick="javascript:selProdctRead(${selProdct.prodctSeq},1)">
										  ${selProdct.prodctNme}
										</td>
										<td onclick="javascript:selProdctRead(${selProdct.prodctSeq},1)">
											<fmt:formatNumber value="${selProdct.prodctSelPric}" type="number"/> 원
										</td>
										<td onclick="javascript:selProdctRead(${selProdct.prodctSeq},1)">
											<fmt:formatNumber value="${selProdct.prodctNetinc}" type="number"/> 원
										</td>
										<td onclick="javascript:selProdctRead(${selProdct.prodctSeq},1)">
											<fmt:formatNumber value="${selProdct.prodctMargnrat}" pattern=".00" /> %
									   </td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
				</c:if>    
				
				<c:if test="${prodctValueObject.pageType==2}">
					<table class="table table-bordered text-center">
						<colgroup>
							<col class="col-md-1 col-sm-1"/>                
					  		<col class="col-md-1 col-sm-1"/>          
					  		<col class="col-md-1 col-sm-1"/>           
					    	<col class="col-md-7 col-sm-7"/>
					    	<col class="col-md-1 col-sm-1"/>          
					    	<col class="col-md-1 col-sm-1"/>
						</colgroup>
						<tr align="center">
							<th class="active text-center">
								<input id="allCheck" type="checkbox">
							</th>
							<th class="active text-center">
							 바코드
							</th>
							<th class="active text-center">
							 이미지
							</th>
							<th class="active text-center">
							 상품이름
							</th>
							<th class="active text-center">
							 거래처 명
							</th>
							<th class="active text-center">
							  거래처 번호
							</th>
						</tr>
						<c:choose>
							<c:when test="${fn:length(selStopProdctList) == 0}">
								<tr>
									<td colspan="6">
										<h4>등록된 상품이 없습니다</h4>
									</td>
								</tr>
							</c:when>
							<c:when test="${fn:length(selStopProdctList) != 0}">
								<c:forEach var="selStopProdct" items="${selStopProdctList}" varStatus="status">
									<tr onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
										<td>
											<input type="checkbox" name="prodctCheck" value="${selStopProdct.prodctSeq}">
										</td>
										<td onclick="javascript:selProdctRead(${selStopProdct.prodctSeq},2)">
										   ${selStopProdct.prodctSeq}
										</td>
										<td>
											<img src="${selStopProdct.prodctMainImage}" class="thumbnail">
										</td>
										<td onclick="javascript:selProdctRead(${selStopProdct.prodctSeq},2)">
										  ${selStopProdct.prodctNme}
										</td>
										<td onclick="javascript:selProdctRead(${selStopProdct.prodctSeq},2)">
										   ${selStopProdct.clintNme}
										</td>
										<td onclick="javascript:selProdctRead(${selStopProdct.prodctSeq},2)">
									 	  ${selStopProdct.clintMangrNum}
									   </td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
				</c:if>
			</div>
			
			<div class="container text-center" >
				<nav>
				<ul class="pagination">
				   <!-- 무조건 1페이지로 << 버튼 -->
					<c:if test = "${prodctValueObject.currentPageNo>20}">
						<li>
							<a href="javascript:prodctPaging(1)" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>  
					<!-- 한 단위 앞으로 < 버튼 -->
					<c:if test = "${prodctValueObject.currentPageNo>10}">
						<li>
							<a href="javascript:prodctPaging(${prodctValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
							<span aria-hidden="true">&lsaquo;</span></a>
						</li>
					</c:if>    
				 
					<c:forEach varStatus="status" begin="${prodctValueObject.firstPageNoOnPageList}" end="${prodctValueObject.lastPageNoOnPageList}">
					
						<c:if test="${prodctValueObject.currentPageNo==status.current}">
							<li class="active">
								<a href="javascript:prodctPaging(${status.current})">${status.current}</a>
							</li>
						</c:if>
							<c:if test="${prodctValueObject.currentPageNo!=status.current}">
								<li><a href="javascript:prodctPaging(${status.current})">${status.current}</a></li>
							</c:if>                                              
					</c:forEach>   
				
					<!-- 한 단위 뒤로 > 버튼 -->
					<c:if test = "${prodctValueObject.firstPageNoOnPageList + 10 < prodctValueObject.totalPageCount}">
						<li>
							<a href="javascript:prodctPaging(${prodctValueObject.lastPageNoOnPageList+1})" aria-label="Next">
							<span aria-hidden="true">&rsaquo;</span></a>
						</li>
					</c:if>  
					<!-- 무조건 마지막페이지로 >> 버튼 -->
					<c:if test = "${prodctValueObject.firstPageNoOnPageList + 20 < prodctValueObject.totalPageCount}">
						<li>
							<a href="javascript:prodctPaging(${prodctValueObject.totalPageCount})" aria-label="Next">
							<span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>  
					</ul>
				</nav>
			</div>
		</div>
		<script>
			function changeTrColor(trObj, oldColor, newColor) {
				trObj.style.backgroundColor = newColor;
				trObj.onmouseout = function(){
					trObj.style.backgroundColor = oldColor;
				}
			};
			
			function prodctSearch(num)
			{
				document.prodctSearchBoxFrm.currentPageNo.value = 1;
				$("input[name=selectedTopCatgrNme]").val($("#topCatgrNme").val());
				$("input[name=selectedMidCatgrNme]").val($("#midCatgrNme").val());
				$("input[name=selectedBotCatgrNme]").val($("#botCatgrNme").val());
				if($("#pageType").val()==1)
				{
					document.prodctSearchBoxFrm.action = "/selProdctList.do";
				}
				else if($("#pageType").val()==2)
				{
					document.prodctSearchBoxFrm.action = "/selStopProdctList.do";
				}
				document.prodctSearchBoxFrm.method = "post";
				document.prodctSearchBoxFrm.submit();
			};
			
			function prodctPaging(page)
			{
				document.prodctSearchBoxFrm.currentPageNo.value = page;
				if($("#pageType").val()==1)
				{
					document.prodctSearchBoxFrm.action = "/selProdctList.do";
				}
				else if($("#pageType").val()==2)
				{
					document.prodctSearchBoxFrm.action = "/selStopProdctList.do";
				}
				document.prodctSearchBoxFrm.method = "post";
				document.prodctSearchBoxFrm.submit();
			};
			
			function selProdctRead(prodctSeq,pageType)
			{
				$("input[name=prodctSeq]").val(prodctSeq);
				$("#pageType").val(pageType);
				document.prodctSearchBoxFrm.action ="/selProdctRead.do";
				document.prodctSearchBoxFrm.method = "post";
				document.prodctSearchBoxFrm.submit();
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