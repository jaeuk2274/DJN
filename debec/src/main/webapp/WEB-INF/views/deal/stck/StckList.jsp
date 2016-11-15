<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!-- 
* 특정 상품의 재고 내역을 조회하는 JSP 입니다.
* 
* history :
*        하원식, 1.0, 2016/08/16 – 초기 작성
* author :  하원식
* version : 1.6, 2016/08/27  - 디자인 완성
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
      
		<!-- 이미지 썸네일 CSS -->
		<link href="/resources/common/css/thumbnail.css" rel="stylesheet" type="text/css"/>
					
		<script src="resources/js/stck-js/stckList.js?var=2"></script> 
		<script src="resources/js/prodct-js/catgrChoice.js"></script> 
	</head>
	<body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
		<div class="container">
		
			<div class="container">
				<h3>재고 목록</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
				   <h5>
				     <img src="/resources/image/common/home.png"> > 재고 관리 > 
				     <a href="/stckList.do" style="text-decoration:none"><strong>재고 목록</strong></a>
				   </h5>
				</div>
			</div>
			
			<form name="stckSearchBoxFrm" class="form-inline">
				<input type="hidden" name="currentPageNo" value="${stckValueObject.currentPageNo}"> <!-- 현재 페이지 -->
				<input type="hidden" name="prodctSeq" value="0">
				<input type="hidden" name="topCatgrNme" value="${ProdctValueObject.topCatgrNme}"> <!-- 중분류 목록 -->
				<input type="hidden" name="midCatgrNme" value="${ProdctValueObject.midCatgrNme}"> <!-- 소분류 목록-->
				<input type="hidden" name="selectedTopCatgrNme" value="${ProdctValueObject.selectedTopCatgrNme}"> <!-- 선택된 대분류-->
				<input type="hidden" name="selectedMidCatgrNme" value="${ProdctValueObject.selectedMidCatgrNme}"> <!-- 선택된 중분류 -->
				<input type="hidden" name="selectedBotCatgrNme" value="${ProdctValueObject.selectedBotCatgrNme}"> <!-- 선택된 소분류-->
				
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
								<input type="text" class="form-control" name="prodctSechText" placeholder="search" value="${ProdctValueObject.prodctSechText}">
							</td>
						</tr>
						<tr>
							<th class="active text-center">
								재고 수량
							</th>
							<td>
								<input type="text" name="firstQunt" class="form-control" value="${ProdctValueObject.prodctSechPriceOne}"> 원
								  개 ~ 
								<input type="text" name="secondQunt" class="form-control" value="${ProdctValueObject.prodctSechPriceTwo}"> 원
							</td>
						</tr>
					</table>
				</div>
			</form>
			
			<div class="container text-center">
				<input type="button" class="btn btn-default" value="검색" onclick="javascript:stckSearch()">		
			</div>
			
			<div class="container text-right" >
				<input type="button" class="btn btn-default" id="stckRetrn" value="반품">
				<input type="button" class="btn btn-default" id="stckDown" value="Download" >
			</div>
			
			<div class="container">	
				<form name="stckTableFrm">
					<table class="table table-bordered text-center" >
						<colgroup>
							<col class="col-md-1 col-sm-1"/> 
							<col class="col-md-1 col-sm-1"/>               
					  		<col class="col-md-4 col-sm-4"/>          
					  		<col class="col-md-1 col-sm-1"/>           
					    	<col class="col-md-1 col-sm-1"/>
					    	<col class="col-md-2 col-sm-2"/>          
					    	<col class="col-md-2 col-sm-2"/>
						</colgroup>
						<tr>
							<th class="active text-center">
								<input id="allCheck" type="checkbox">
							</th>
							<th class="active text-center">
							 이미지
							</th>
							<th class="active text-center">
							 상품명
							</th>
							<th class="active text-center">
							 상품 수량
							</th>
							<th class="active text-center">
							입고 단가
							</th>
							<th class="active text-center">
							 거래처
							</th>
							<th class="active text-center">
							  거래처 전화번호
							</th>
						</tr>
						<c:choose>
							<c:when test="${fn:length(stckList) == 0}">
								<tr class="text-center">
									<td colspan="6">
										<h4>등록된 거래처가 없습니다</h4>
									</td>
								</tr>
							</c:when>
							<c:when test="${fn:length(stckList) != 0}">
								<c:forEach var="stck" items="${stckList}" varStatus="status">
									<tr onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
										<td class="text-center">
											<input type="checkbox" name="prodctCheck" value="${stck.prodctSeq}">
										</td>
										<td class="text-center">
										   <img src="${stck.prodctMainImage}" class="thumbnail">
										</td>
										<td class="text-center" onclick="javascript:stckRead(${stck.prodctSeq})">
										   ${stck.prodctNme}
										</td>
										<td class="text-center" onclick="javascript:stckRead(${stck.prodctSeq})">
										   <fmt:formatNumber value="${stck.qunt}" type="number"/> 개
										</td>
										<td class="text-center" onclick="javascript:stckRead(${stck.prodctSeq})">
										   <fmt:formatNumber value="${stck.purchsPric}" type="number"/> 원
										</td>
										<td class="text-center" onclick="javascript:stckRead(${stck.prodctSeq})">
										   ${stck.clintNme}
										</td>
										<td class="text-center" onclick="javascript:stckRead(${stck.prodctSeq})">
									 	  ${stck.clintMangrNum}
									   </td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
				</form>
			</div>	
				
			<div class="container text-right">
				<input type="button" class="btn btn-default" id="stckCreate" value="등록">
				<input type="button" class="btn btn-default" id="stckUpdate"value="수정">
			</div>
				
			<div class="container text-center">
				<nav style="margin-top:-10px;">
				<ul class="pagination">
				   <!-- 무조건 1페이지로 << 버튼 -->
					<c:if test = "${stckValueObject.currentPageNo>20}">
						<li>
							<a href="javascript:stckPaging(1)" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>  
					<!-- 한 단위 앞으로 < 버튼 -->
					<c:if test = "${stckValueObject.currentPageNo>10}">
						<li>
							<a href="javascript:stckPaging(${stckValueObject.firstPageNoOnPageList-1})" aria-label="Previous">
							<span aria-hidden="true">&lsaquo;</span></a>
						</li>
					</c:if>    
				 
					<c:forEach varStatus="status" begin="${stckValueObject.firstPageNoOnPageList}" end="${stckValueObject.lastPageNoOnPageList}">
					
						<c:if test="${stckValueObject.currentPageNo==status.current}">
							<li class="active">
								<a href="javascript:stckPaging(${status.current})">${status.current}</a>
							</li>
						</c:if>
							<c:if test="${stckValueObject.currentPageNo!=status.current}">
								<li><a href="javascript:stckPaging(${status.current})">${status.current}</a></li>
							</c:if>                                              
					</c:forEach>   
				
					<!-- 한 단위 뒤로 > 버튼 -->
					<c:if test = "${stckValueObject.firstPageNoOnPageList + 10 < stckValueObject.totalPageCount}">
						<li>
							<a href="javascript:stckPaging(${stckValueObject.lastPageNoOnPageList+1})" aria-label="Next">
							<span aria-hidden="true">&rsaquo;</span></a>
						</li>
					</c:if>  
					<!-- 무조건 마지막페이지로 >> 버튼 -->
					<c:if test = "${stckValueObject.firstPageNoOnPageList + 20 < stckValueObject.totalPageCount}">
						<li>
							<a href="javascript:stckPaging(${stckValueObject.totalPageCount})" aria-label="Next">
							<span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>  
					</ul>
				</nav>
			</div>
		</div>
		<script>
			function stckSearch(num)
			{
				document.stckSearchBoxFrm.currentPageNo.value = 1;
				$("input[name=selectedTopCatgrNme]").val($("#topCatgrNme").val());
				$("input[name=selectedMidCatgrNme]").val($("#midCatgrNme").val());
				$("input[name=selectedBotCatgrNme]").val($("#botCatgrNme").val());
				document.stckSearchBoxFrm.action = "/stckList.do";
				document.stckSearchBoxFrm.method = "post";
				document.stckSearchBoxFrm.submit();
			};
			
			function stckPaging(page)
			{
				document.stckSearchBoxFrm.currentPageNo.value = page;
				$("input[name=selectedTopCatgrNme]").val($("#topCatgrNme").val());
				$("input[name=selectedMidCatgrNme]").val($("#midCatgrNme").val());
				$("input[name=selectedBotCatgrNme]").val($("#botCatgrNme").val());
				document.stckSearchBoxFrm.action = "/stckList.do";
				document.stckSearchBoxFrm.method = "post";
				document.stckSearchBoxFrm.submit();
			};
			
			function stckRead(prodctSeq)
			{
				$("input[name=prodctSeq]").val(prodctSeq);
				document.stckSearchBoxFrm.action ="/stckRead.do";
				document.stckSearchBoxFrm.method = "post";
				document.stckSearchBoxFrm.submit();
			};

			function changeTrColor(trObj, oldColor, newColor) {
				trObj.style.backgroundColor = newColor;
				trObj.onmouseout = function(){
					trObj.style.backgroundColor = oldColor;
				}
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