<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!-- 
* 특정 상품의 상세 정보와 입고 내역을 상세보기 하는 JSP 입니다.
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
		
		<script src="/resources/jquery.tablednd.js"></script>
	</head>
	<body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
		<div class="container" >
			<form name="prodctSeqFrm">
				<input type="hidden" name="prodctSeq" value="${selProdctRead.prodctSeq}">
				<input type="hidden" name="currentPageNo" value="${selProdctRead.prodctSeq}">
			</form>
			
         	<div class="container">
				<h3>재고 품목 상세보기</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
					<h5>
						<img src="/resources/image/common/home.png"> > 거래관리 > 
						<a href="/stckList.do" style="text-decoration:none"><strong>재고 목록</strong></a>
					</h5>
				</div>
			</div>
			
         	<div class="container">
				<h3>상품 정보</h3>
			</div>
			
			<div class="container">
				<div class="text-right" >
					<input class="btn btn-default" type="button" value="목록보기" onclick="javascript:stckList()">
	            </div>
	           
				<table class="table table-bordered">
					<colgroup>
					    <col class="col-md-3 col-sm-3"/>           
					    <col class="col-md-3 col-sm-3"/>
					    <col class="col-md-3 col-sm-3"/>          
					    <col class="col-md-3 col-sm-3"/>
					</colgroup>
					<tr >
						<td>
							<img id="selProdctTempImg" src="${selProdctRead.prodctMainImage}" width="100%" height="180px" >
						</td>
						<td>
							<img id="selProdctTempImg" src="${selProdctRead.prodctDetlImageOne}" width="100%" height="180px">
						</td>
						<td>
							<img id="selProdctTempImg" src="${selProdctRead.prodctDetlImageTwo}" width="100%" height="180px">
						</td>
						<td>
							<img id="selProdctTempImg" src="${selProdctRead.prodctDetlImageThree}"width="100%" height="180px" >
						</td>
                     </tr>
					<tr >
						<td class="active">
                        	<h5 class="text-center"><b>바코드</b></h5>
						</td>
						<td>
							${selProdctRead.prodctSeq}      
						</td>
						<td class="active">
						   <h5 class="text-center"><b>상품명</b></h5>
						</td>
						<td>
                           ${selProdctRead.prodctNme}
						</td>
                     </tr>
                     <tr>
                     	<td class="active">
							<h5 class="text-center"><b>카테고리</b></h5>
						</td>
						<td> 
							${catgrProdctRead.topCatgrNme} - ${catgrProdctRead.midCatgrNme} - ${catgrProdctRead.botCatgrNme}
						</td>
						<td class="active">
							<h5 class="text-center"><b>진열여부</b></h5>
						</td>
						<td>
							<c:if test="${selProdctRead.prodctDisplyCheck eq 'Y'}">
								판매 중
							</c:if>
							<c:if test="${selProdctRead.prodctDisplyCheck eq 'N'}">
								판매 중지
							</c:if>
							<c:if test="${selProdctRead.prodctDisplyCheck eq 'D'}">
								판매 종료
							</c:if>
  						</td>
					</tr>
					<tr>
						<td class="active">
						<h5 class="text-center"><b>재고량</b></h5>
						</td>
						<td> 
						<fmt:formatNumber value="${selProdctRead.prodctStck}" type="number"/> 원
						</td>
						<td class="active">
						 <h5 class="text-center"><b>판매단가</b></h5>
						</td>
						<td> 
						<fmt:formatNumber value="${selProdctRead.prodctSelPric}" type="number"/> 원
						</td>
					</tr>
					<tr>
						<td class="active">
						<h5 class="text-center"><b>거래처</b></h5>
						</td>
						<td>
							${selProdctRead.clintNme}
     
						</td>
						<td class="active">
						<h5 class="text-center"><b>거래처 전화번호</b></h5>
						</td>
						<td>
							${selProdctRead.clintMangrNum}
     
						</td>
					</tr>
					<tr>
						<td class="active">
							<h5 class="text-center"><b>마진율</b></h5>
						</td>
						<td>
						<fmt:formatNumber value="${selProdctRead.prodctMargnrat}" pattern=".00"/> %
						</td>
						<td class="active">
						   <h5 class="text-center"><b>순이익</b></h5>
						</td>
						<td>
						<fmt:formatNumber value="${selProdctRead.prodctNetinc}" type="number"/> 원
						</td>
					</tr>
				</table>
			</div>
			<div class="container" >
				<table class="table table-bordered text-center">
					<tr>
						<td class="active">
							<h5 class="text-center"><b>번호</b></h5>
						</td>
						<td class="active">
							<h5 class="text-center"><b>상품명</b></h5>
						</td>
						<td class="active">
							<h5 class="text-center"><b>거래처</b></h5>
						</td>
						<td class="active">
							<h5 class="text-center"><b>입고량</b></h5>
						</td>
						<td class="active">
							<h5 class="text-center"><b>단가</b></h5>
						</td>
						<td class="active">
							<h5 class="text-center"><b>금액</b></h5>
						</td>
						<td class="active">
							<h5 class="text-center"><b>입고일</b></h5>
						</td>
					</tr>
					<c:choose>
						<c:when test="${fn:length(instckRecrdList) == 0}">
							<tr>
								<td colspan="7">
									<h4>등록된 입고 내역이 없습니다</h4>
								</td>
							</tr>
						</c:when>
						<c:when test="${fn:length(instckRecrdList) != 0}">
							<c:forEach var="instck" items="${instckRecrdList}" varStatus="status">
								<tr onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
									<td>
										${instck.prodctSeq}
									</td>
									<td>
										${instck.prodctNme}
									</td>
									<td>
										${instck.clintNme}
									</td>
									<td class="text-right">
										<fmt:formatNumber value="${instck.qunt}" type="number"/> 원
									</td>
									<td class="text-right">
										<fmt:formatNumber value="${instck.purchsPric}" type="number"/> 원
									</td>
									<td class="text-right">
										<fmt:formatNumber value="${instck.totlPric}" type="number"/> 원
									</td>
									<td>
										${instck.dat}
									</td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</table>
			</div>				
			<div class="container text-center" >
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
			</div>
		</div>
	</body>
		<script>
			$(document).ready(function() {   
			
			});
		</script>
		<script>
			function stckPaging(page) {
				document.prodctSeqFrm.currentPageNo.value = page;
				document.prodctSeqFrm.action = "/stckRead.do";
				document.prodctSeqFrm.method = "post";
				document.prodctSeqFrm.submit();
			};
			function stckList()	{
				location.href="stckList.do";
			};
			
			//리스트 색 변경
			function changeTrColor(trObj, oldColor, newColor) {
				trObj.style.backgroundColor = newColor;
				trObj.onmouseout = function(){
					trObj.style.backgroundColor = oldColor;
				}
			};
		</script>
</html>