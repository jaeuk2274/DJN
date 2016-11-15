<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 
* 상품 상세보기를 위한 화면이다.
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
		
		<!-- 이미지 미리보기 관련 스크립트 -->
		<script src="/resources/js/prodct-js/selProdctImg.js?ver=1000"></script>
	</head>
	<body onload="javascript:imgNullCheck('${selProdctRead.prodctMainImage}', '${selProdctRead.prodctDetlImageOne}', '${selProdctRead.prodctDetlImageTwo}', '${selProdctRead.prodctDetlImageThree}')">
		<input type="hidden" name="prodctMainImage" id="mainFile" value="/resources/image/common/noImg.png">  
		<input type="hidden" name="prodctDetlImageOne" id="detlFile1"value="/resources/image/common/noImg.png">  
		<input type="hidden" name="prodctDetlImageTwo" id="detlFile2" value="/resources/image/common/noImg.png">  
		<input type="hidden" name="prodctDetlImageThree" id="detlFile3" value="/resources/image/common/noImg.png"> 
		
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
		<div class="container">
	
			<div class="container">
				<c:if test="${prodctValueObject.pageType==1}">
					<h3>판매 상품 조회</h3>
					<div style="display:inline-block; margin-top:1%; float:right">
						<h5>
							<img src="/resources/image/common/home.png"> > 상품 관리 > 
							<a href="/selProdctList.do" style="text-decoration:none"><strong>판매 상품 목록</strong></a>
						</h5>
					</div>
				</c:if>
				<c:if test="${prodctValueObject.pageType==2}">
					<h3>판매 중지 상품 조회</h3>
					<div style="display:inline-block; margin-top:1%; float:right">
						<h5>
							<img src="/resources/image/common/home.png"> > 상품 관리 > 
							<a href="/selStopProdctList.do" style="text-decoration:none"><strong>판매 중지 상품 목록</strong></a>
						</h5>
					</div>
				</c:if>
			</div>
	
			<form name="selProdctDetlFrm">
				<div class="container">
						<input type="hidden" name="prodctSeq" id="prodctSeq" value="${selProdctRead.prodctSeq}">
						<input type="hidden" name="topCatgrNme"  value="${catgrProdctRead.topCatgrNme}">
						<input type="hidden" name="midCatgrNme"  value="${catgrProdctRead.midCatgrNme}">
						<input type="hidden" name="botCatgrNme"  value="${catgrProdctRead.botCatgrNme}">
						<input type="hidden" name="selectedTopCatgrNme" value="${prodctValueObject.selectedTopCatgrNme}"> <!-- 선택된 대분류-->
						<input type="hidden" name="selectedMidCatgrNme" value="${prodctValueObject.selectedMidCatgrNme}"> <!-- 선택된 중분류 -->
						<input type="hidden" name="selectedBotCatgrNme" value="${prodctValueObject.selectedBotCatgrNme}"> <!-- 선택된 소분류-->
						<input type="hidden" name="pageType" id="pageType" value="${prodctValueObject.pageType}">
				
		        	<div class="container" >			
						<table class="table table-bordered text-center">
							<colgroup>
								<col class="col-md-1"/> 
								<col class="col-md-4"/>                
								<col class="col-md-1"/>
								<col class="col-md-2"/> 
								<col class="col-md-1"/>                
								<col class="col-md-2"/>          
								<col class="col-md-1"/>           
							</colgroup>
							<tr>
								<td rowspan="8" style="vertical-align:middle;width:5%;">
									<a id="imgLeft" style="display:none"><img src="../../../../resources/image/imgLeftArrow.png"></a>
								</td>
								<td rowspan="8">
									<img id="selProdctTempImg" style="width:400px; height:300px">
								</td>
								<td rowspan="8" style="vertical-align:middle;width:5%;">
									<a id="imgRight" style="display:none"><img src="../../../../resources/image/imgRightArrow.png"></a>
								</td>
							</tr>
							<tr>
							   <th class="active">
								  바코드
							   </th>
							   <td class="text-left" colspan="3">
							      ${selProdctRead.prodctSeq}      
							   </td>
							</tr>
							<tr>
							   <th class="active">
								   상품명
							   </th>
							   <td class="text-left"colspan="3">
								${selProdctRead.prodctNme}
							   </td>
							</tr>
							<tr>
							   <th class="active">
							   카테고리
							   </th>
							   <td class="text-left" colspan="3">
							      ${catgrProdctRead.topCatgrNme} > ${catgrProdctRead.midCatgrNme} > ${catgrProdctRead.botCatgrNme}
							   </td>
							</tr>
							<tr>
								<th class="active">
								  진열여부
								</th>
								<td  class="text-left" colspan="3">
						  			<c:choose>
						
										<c:when test="${selProdctRead.prodctDisplyCheck eq 'Y'}">
										<font id="prodctDisplyCheck">판매 중</font>	
										</c:when>
										
										<c:when test="${selProdctRead.prodctDisplyCheck eq 'N'}">
										<font id="prodctDisplyCheck">판매 중지</font>	
										</c:when>
										
										<c:when test="${selProdctRead.prodctDisplyCheck eq 'D'}">
										<font id="prodctDisplyCheck">판매 종료</font>	
										</c:when>
										
										<c:otherwise>
										<font id="prodctDisplyCheck"></font>
										</c:otherwise>
										
									</c:choose>
								</td>
							</tr>
							<tr>
								<th class="active">
								 거래처
								</th>
								<td class="text-left" colspan="3">
									${selProdctRead.clintNme}
								</td>
							</tr>
							<tr>
								<th class="active">
							      판매단가
								</th>
								<td class="text-left" > 
									<fmt:formatNumber value="${selProdctRead.prodctSelPric}" type="number"/> 원
								</td>
								<th class="active">
									입고단가
								</th>
								<td class="text-left" >
									<fmt:formatNumber value="${selProdctRead.prodctPurchsPric}" type="number"/> 원
								</td>
							</tr>
							<tr>
								<th class="active">
									마진율
								</th>
								<td class="text-left" >
									<fmt:formatNumber value="${selProdctRead.prodctMargnrat}" pattern=".00" /> %
								</td>
								<th class="active">
									순이익
								</th>
								<td class="text-left" >
									<fmt:formatNumber value="${selProdctRead.prodctNetinc}" type="number"/> 원
								</td>
							</tr>
						</table>
						<div class="text-right">
							<input type="button" class="btn btn-default" id="selProdctUpdate" value ="수정">			
							<c:if test="${prodctValueObject.pageType==1}">
								<input type="button" class="btn btn-default" id="selProdctDelete" value ="판매중지">
							</c:if>					
							<c:if test="${prodctValueObject.pageType==2}">	
								<input type="button" class="btn btn-default" id="selStopProdctDelete" value ="삭제">
							</c:if>
							<input type="button" class="btn btn-default" id="selProdctCancel" value ="뒤로가기">
  						</div>
					</div>
					
					<div class="form-group"></div>
					
					<div class="container">
						<table class="table table-bordered text-center">
							<tr>
								<th class="active text-center" colspan="3">
									연관상품
								</th>
							</tr>
							<tr>
								<td>
									<c:if test="${reltnProdctRead1.prodctMainImage!=null}">
										<img class="reltn" id="reltnProdctImgOne" name="reltnProdctChoice" src="${reltnProdctRead1.prodctMainImage}" width="180" height="180">
									</c:if>
									<c:if test="${reltnProdctRead1.prodctMainImage==null}">
										<img class="reltn" id="reltnProdctImgOne" name="reltnProdctChoice" src="../../../../resources/image/common/noImg.png" width="180" height="180">
									</c:if>
								</td>	
								<td>
									<c:if test="${reltnProdctRead2.prodctMainImage!=null}">
										<img class="reltn" id="reltnProdctImgTwo" name="reltnProdctChoice" src="${reltnProdctRead2.prodctMainImage}" width="180" height="180">
									</c:if>
									<c:if test="${reltnProdctRead2.prodctMainImage==null}">
										<img class="reltn" id="reltnProdctImgTwo" name="reltnProdctChoice" src="../../../../resources/image/common/noImg.png" width="180" height="180">
									</c:if>
								</td>	
								<td>
									<c:if test="${reltnProdctRead3.prodctMainImage!=null}">
										<img class="reltn" id="reltnProdctImgThree" name="reltnProdctChoice" src="${reltnProdctRead3.prodctMainImage}" width="180" height="180">
									</c:if>
									<c:if test="${reltnProdctRead3.prodctMainImage==null}">
										<img class="reltn" id="reltnProdctImgThree" name="reltnProdctChoice" src="../../../../resources/image/common/noImg.png" width="180" height="180">
									</c:if>
								</td>
							</tr>
							<tr>
								<td>
									<c:if test="${reltnProdctRead1.prodctNme!=null}">
										<input type="text" class="form-control" id="reltnProdctNmeOne" value="${reltnProdctRead1.prodctNme}" readonly>
									</c:if>
									<c:if test="${reltnProdctRead1.prodctNme==null}">
										<input type="text" class="form-control" id="reltnProdctNmeOne" value="선택된 상품 없음" readonly>
									</c:if>
								</td>
								<td>
									<c:if test="${reltnProdctRead2.prodctNme!=null}">
										<input type="text" class="form-control" id="reltnProdctNmeTwo" value="${reltnProdctRead2.prodctNme}" readonly>
									</c:if>
									<c:if test="${reltnProdctRead2.prodctNme==null}">
										<input type="text" class="form-control" id="reltnProdctNmeTwo" value="선택된 상품 없음" readonly>
									</c:if>
								</td>
								<td>
									<c:if test="${reltnProdctRead3.prodctNme!=null}">
										<input type="text" class="form-control" id="reltnProdctNmeThree" value="${reltnProdctRead3.prodctNme}" readonly>
									</c:if>
									<c:if test="${reltnProdctRead3.prodctNme==null}">
										<input type="text" class="form-control" id="reltnProdctNmeThree" value="선택된 상품 없음" readonly>
									</c:if>
								</td>
							</tr>
							<tr>
								<td>
									<c:if test="${reltnProdctRead1.prodctSelPric!= null}">
										<input type="text" class="form-control" id="reltnProdctSelPricOne" value="<fmt:formatNumber value="${reltnProdctRead1.prodctSelPric}" type="number"/> 원" readonly>
									</c:if>
									<c:if test="${reltnProdctRead1.prodctSelPric == null}">
										<input type="text" class="form-control" id="reltnProdctSelPricOne" value="" readonly>
									</c:if>
								</td>
								<td>
									<c:if test="${reltnProdctRead2.prodctSelPric != null}">
										<input type="text" class="form-control" id="reltnProdctSelPricTwo" value="<fmt:formatNumber value="${reltnProdctRead2.prodctSelPric}" type="number"/> 원" readonly>
									</c:if>
									<c:if test="${reltnProdctRead2.prodctSelPric == null}">
										<input type="text" class="form-control" id="reltnProdctSelPricTwo" value="" readonly>
									</c:if>
								</td>
								<td>
									<c:if test="${reltnProdctRead3.prodctSelPric != null}">	
										<input type="text" class="form-control" id="reltnProdctSelPricThree" value="<fmt:formatNumber value="${reltnProdctRead3.prodctSelPric}" type="number"/> 원" readonly>
									</c:if>
									<c:if test="${reltnProdctRead3.prodctSelPric == null}">
										<input type="text" class="form-control" id="reltnProdctSelPricThree" value="" readonly>
									</c:if>
								</td>
							</tr>
						</table>	
					</div>
					
					<div class="container">
						<c:if test="${evntInfoRead.schedlSeq>0}">
							<table class="table table-bordered">
								<tr>
									<th class="active text-center">
										행사면
									</th>
									<td class="text-left" >
										${evntInfoRead.schedlTitl}
									</td>
								</tr>
								<tr>
									<th class="active text-center">
										기간
									</th>
								   <td  class="text-left" >
								      <font id="schedlDat">${evntInfoRead.schedlStartDat}  ~  ${evntInfoRead.schedlEndDat}</font>
								   </td>
								</tr>
								<tr>
									<th class="active text-center">
										적용상태
									</th>
									<td class="text-left" >
							  			<c:choose>
							
											<c:when test="${evntInfoRead.evntStat eq 'Y'}">
											<font id="evntStat">진행중</font>	
											</c:when>
											
											<c:when test="${evntInfoRead.evntStat eq 'N'}">
											<font id="evntStat">종료</font>	
											</c:when>
											
											<c:when test="${evntInfoRead.evntStat eq 'R'}">
											<font id="evntStat">대기중</font>	
											</c:when>
											
											<c:otherwise>
											<font id="evntStat"></font>
											</c:otherwise>
											
										</c:choose>
									</td>
								</tr>
								<tr>
									<th class="active text-center">
								  	 할인율
								   </th>
								   <td class="text-left" >
								      <fmt:formatNumber value="${evntInfoRead.discntRat}" pattern=".00" /> %
								   </td>
								</tr>
								<tr>
									<th class="active text-center">
								  	 세일가
								   </th>
									<td class="text-left" >
								      <fmt:formatNumber value="${evntInfoRead.prodctSalePric}" type="number"/> 원
								   </td>
								</tr>
								<tr>
									<th class="active text-center">
									   마진율
									</th>
									<td class="text-left" >
										<fmt:formatNumber value="${evntInfoRead.prodctMargnrat}" pattern=".00" /> %
									</td>
								</tr>
							</table>
						</c:if>
					</div>
				</div>
			</form>
		</div>
	</body>
	<script>
	$(document).ready(function() {   
		$("#selProdctDelete").click(function(){
			document.selProdctDetlFrm.action="/selProdctReadDelete.do"
			document.selProdctDetlFrm.method="POST"
			document.selProdctDetlFrm.submit();
		});
		$("#selStopProdctDelete").click(function(){
			document.selProdctDetlFrm.action="/selStopProdctReadDelete.do"
			document.selProdctDetlFrm.method="POST"
			document.selProdctDetlFrm.submit();
		});

		$("#selProdctUpdate").click(function(){
			document.selProdctDetlFrm.action="/selProdctUpdateRead.do"
			document.selProdctDetlFrm.method="POST"
			document.selProdctDetlFrm.submit();
		});
		$("#selProdctCancel").click(function(){
			var url=""
			$("#prodctSeq").val(0)
			if($("#pageType").val()==1)
				url = "/selProdctList.do"
			else
				url = "/selStopProdctList.do"
			document.selProdctDetlFrm.action=url
			document.selProdctDetlFrm.method="POST"
			document.selProdctDetlFrm.submit();
		});
	});
	</script>
</html>