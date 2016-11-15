<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 
* 상품 수정을 위한 화면이다.
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

		<!-- 가격 계산  JS 스크립트 -->
		<script src="/resources/js/prodct-js/prodctPricCaclt.js?ver=52"></script>
		
		<!-- 모달 관련 JS 스크립트 -->
		<script src="/resources/js/prodct-js/selProdctPopUp.js?ver=1"></script>
		
		<!-- 카테고리 관련 JS 스크립트 -->
		<script src="/resources/js/prodct-js/catgrChoice.js"></script>
		
		<!-- 이미지 미리보기 관련 스크립트 -->
		<script src="/resources/js/prodct-js/selProdctImg.js?var=7"></script>
	
		<!-- JSTree 관련 파일들 -->
		<script src="/resources/jstree/jstree.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" href="/resources/jstree/themes/proton/style.min.css">
	</head>
	<body onload="javascript:imgNullCheck('${selProdctRead.prodctMainImage}', '${selProdctRead.prodctDetlImageOne}', '${selProdctRead.prodctDetlImageTwo}', '${selProdctRead.prodctDetlImageThree}')">
		
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
		
		
		<div class="container">
			<c:if test="${prodctValueObject.pageType==1}">
				<h3>판매 상품 수정</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
					<h5>
						<img src="/resources/image/common/home.png"> > 상품 관리 > 
						<a href="/selProdctList.do" style="text-decoration:none"><strong>판매 상품 목록</strong></a>
					</h5>
				</div>
			</c:if>
			<c:if test="${prodctValueObject.pageType==2}">
				<h3>판매 중지 상품 수정</h3>
				<div style="display:inline-block; margin-top:1%; float:right">
					<h5>
						<img src="/resources/image/common/home.png"> > 상품 관리 > 
						<a href="/selStopProdctList.do" style="text-decoration:none"><strong>판매 중지 상품 목록</strong></a>
					</h5>
				</div>
			</c:if>
			
			<div class="container" >
				<form id="selProdctFileFrm" name="selProdctFileFrm" enctype="multipart/form-data" method="POST">
					<table class="table table-bordered text-center">
						<tr>
							<td rowspan="4" style="vertical-align:middle;width:5%;">
								<a id="imgLeft" style="display:none"><img src="resources/image/imgLeftArrow.png"></a>
							</td>
							<td rowspan="4">
								<img id="selProdctTempImg" style="width:400px; height:300px;">
							</td>
							<td rowspan="4" style="vertical-align:middle;width:5%;">
								<a id="imgRight" style="display:none"><img src="resources/image/imgRightArrow.png"></a>
							</td>			
						</tr>
						<tr>
							<td colspan="3">
								<input type="file" name="mainProdctImgFile" id="mainProdctImgFile">
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<input type="file" name="detlProdctImgOneFile" id="detlProdctImgOneFile">
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<input type="file" name="detlProdctImgTwoFile" id="detlProdctImgTwoFile">
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<input type="button" class="btn btn-default" id="selProdctImgCheck" value="사진 확인">
							</td>
							<td colspan="3">
								<input type="file" name="detlProdctImgThreeFile" id="detlProdctImgThreeFile">
							</td>
						</tr>
					</table>
					<input type="hidden" name="prodctMainImage" id="mainFile" value="resources\image\common\noImg.jpg">  
					<input type="hidden" name="prodctDetlImageOne" id="detlFile1"value="resources\image\common\noImg.jpg">  
					<input type="hidden" name="prodctDetlImageTwo" id="detlFile2" value="resources\image\common\noImg.jpg">  
					<input type="hidden" name="prodctDetlImageThree" id="detlFile3" value="resources\image\common\noImg.jpg">  
					<input type="hidden" name="prodctFileSeq" id="prodctFileSeq" value="${selProdctRead.prodctSeq}">
					<input type="hidden" name="pageType" id="pageType" value="${prodctValueObject.pageType}"> 
				</form>
			</div>
			
			<form name="selProdctCreateFrm" method="POST" class="form-inline">    
				<input type="hidden" name="pageType" id="pageType" value="${prodctValueObject.pageType}">
				<input type="hidden" name="updateType" id="updateType" value="${selProdctRead.updateType}">
				<div class="container form-group" >
					<input type="hidden" name="dupliCheckSeq" id="dupliCheckSeq" value="${selProdctRead.prodctSeq}">
					<c:if test="${ProdctValueObject.botCatgrNme ne null}">
						<input type="hidden" name="selectedBotCatgrNme" value="${ProdctValueObject.botCatgrNme}"> <!-- 선택된 소분류-->
					</c:if>
					<c:if test="${ProdctValueObject.botCatgrNme eq null}">
						<input type="hidden" name="selectedBotCatgrNme" value="0"> <!-- 선택된 소분류-->
					</c:if>
					
					<c:if test="${reltnProdctRead1.prodctSeq ne null}">
						<input type="hidden" name="reltnProdctSeqOne" id="reltnProdctSeqOne" value="${reltnProdctRead1.prodctSeq}">
					</c:if>
					<c:if test="${reltnProdctRead1.prodctSeq eq null}">
						<input type="hidden" name="reltnProdctSeqOne" id="reltnProdctSeqOne" value="0">
					</c:if>
					
					<c:if test="${reltnProdctRead2.prodctSeq ne null}">
						<input type="hidden" name="reltnProdctSeqTwo" id="reltnProdctSeqTwo" value="${reltnProdctRead2.prodctSeq}">
					</c:if>
					<c:if test="${reltnProdctRead2.prodctSeq eq null}">
						<input type="hidden" name="reltnProdctSeqTwo" id="reltnProdctSeqTwo" value="0">
					</c:if>
					
					<c:if test="${reltnProdctRead3.prodctSeq ne null}">
						<input type="hidden" name="reltnProdctSeqThree" id="reltnProdctSeqThree" value="${reltnProdctRead3.prodctSeq}">
					</c:if>
					<c:if test="${reltnProdctRead3.prodctSeq eq null}">
						<input type="hidden" name="reltnProdctSeqThree" id="reltnProdctSeqThree" value="0">
					</c:if>
					
					<input type="hidden" name="selectedReltnProdct" id="selectedReltnProdct" value="0">
					
					<table  class="table table-bordered text-left">
						<tr>
							<th class="active text-center">
								바코드
							</th>
							<td colspan="3" class="text-left">
								<input type="text" class="form-control" name="prodctSeq" id="prodctSeq" value="${selProdctRead.prodctSeq}" readOnly>
							</td>
						</tr>
						<tr>
							<th class="active text-center">
								상품명
							</th>
							<td colspan="3">
								<input type="text" class="form-control" name="prodctNme" value="${selProdctRead.prodctNme}">
							</td>
						</tr>
						<tr>
							<th class="active text-center">
						 		  카테고리
							</th>
							<td colspan="3">
								<select id="topCatgrNme" class="form-control" onchange="javascript:catgrChoice('대',this.value)">
									<option selected>대분류</option>
									<c:forEach var="catgrList" items="${topCatgrArray}" varStatus="status">
										<c:if test="${catgrList ne '전체조회'}">
											<c:if test="${catgrList==catgrProdctRead.topCatgrNme}">
												<option selected>${catgrList}</option>
											</c:if>
											<c:if test="${catgrList!=catgrProdctRead.topCatgrNme}">
												<option>${catgrList}</option>
											</c:if>
										</c:if>
									</c:forEach>
								</select>
								<select id="midCatgrNme" class="form-control" onchange="javascript:catgrChoice('중',this.value)">
									<option selected>중분류</option>
									<c:forEach var="catgrList" items="${midCatgrArray}" varStatus="status">
										<c:if test="${catgrList ne '전체조회'}">
											<c:if test="${catgrList==catgrProdctRead.midCatgrNme}">
												<option selected>${catgrList}</option>
											</c:if>
											<c:if test="${catgrList!=catgrProdctRead.midCatgrNme}">
												<option>${catgrList}</option>
											</c:if>
										</c:if>
									</c:forEach>
								</select>
								<select id="botCatgrNme" class="form-control" name="botCatgrNme">
									<option selected>소분류</option>
									<c:forEach var="catgrList" items="${botCatgrArray}" varStatus="status">
										<c:if test="${catgrList ne '전체조회'}">
											<c:if test="${catgrList==catgrProdctRead.botCatgrNme}">
												<option selected>${catgrList}</option>
											</c:if>
											<c:if test="${catgrList!=catgrProdctRead.botCatgrNme}">
												<option>${catgrList}</option>
											</c:if>
										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th class="active text-center">
						   		진열여부
							</th>
							<td colspan="3">
								<select name="prodctDisplyCheck" class="form-control" id="prodctDisplyCheck" onchange="schedledOpener(this.value)">
									<c:if test="${selProdctRead.prodctDisplyCheck eq 'Y'}">
										<option selected>판매 중</option>
										<option>판매 중지</option>
										<option>판매 종료</option>
									</c:if>
									<c:if test="${selProdctRead.prodctDisplyCheck eq 'N'}">
										<option >판매 중</option>
										<option selected>판매 중지</option>
										<option>판매 종료</option>
									</c:if>
									<c:if test="${selProdctRead.prodctDisplyCheck eq 'D'}">
										<option>판매 중</option>
										<option>판매 중지</option>
										<option selected>판매 종료</option>
									</c:if>
						      </select>
							</td>
						</tr>
						<tr>
							<th class="active text-center">
							   거래처
							</th>
							<td colspan="3">
								<select class="form-control" id="clintSeq" name="clintSeq">
									<c:forEach var="clintList" items="${clintList}" varStatus="status">
										<c:if test="${clintList.clintNme==selProdctRead.clintNme}">
											<option value="${clintList.clintSeq}" selected>${clintList.clintNme}</option>
										</c:if>
										<c:if test="${clintList.clintNme!=selProdctRead.clintNme}">
											<option value="${clintList.clintSeq}">${clintList.clintNme}</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th class="active text-center">
								판매단가
							</th>
							<td>
								<input type="text" class="form-control" id="prodctSelPric" name="prodctSelPric" value="${selProdctRead.prodctSelPric}"> 원
							</td>
							<th class="active text-center">
								입고단가
							</th>
							<td>
								<input type="text" class="form-control" id="prodctPurchsPric" name="prodctPurchsPric" value="${selProdctRead.prodctPurchsPric}"> 원
							</td>
						</tr>
						<tr>
							<th class="active text-center">
								마진율
							</th>
							<td>
								<input type="text" class="form-control" id="prodctMargnPric" name="prodctMargnPric" value="<fmt:formatNumber value="${selProdctRead.prodctMargnrat}" pattern=".00" /> %" disabled>
							</td>
							<th class="active text-center">
								순이익
							</th>
							<td>
								<input type="text" class="form-control" id="prodctNetincPric" name="prodctNetincPric" value="<fmt:formatNumber value="${selProdctRead.prodctNetinc}" type="number"/> 원" disabled>
							</td>
						</tr>
					</table>
				</div>
				<div class="container">
					<h3>연관 상품</h3>
					<div class="text-right"><input class="btn btn-default" type="button" value="초기화" onclick="javascript:reltnClear()"></div>
					<table class="table table-bordered text-center">
						<tr>
							<td>
								<c:if test="${reltnProdctRead1.prodctMainImage!=null}">
									<img class="reltn" id="reltnProdctImgOne" name="reltnProdctChoice" src="${reltnProdctRead1.prodctMainImage}" onclick="javascript:selectedProdctInsert(1)" width="180" height="180">
								</c:if>
								<c:if test="${reltnProdctRead1.prodctMainImage==null}">
									<img class="reltn" id="reltnProdctImgOne" name="reltnProdctChoice" src="resources/image/common/reltnPlus.png" onclick="javascript:selectedProdctInsert(1)" width="180" height="180">
								</c:if>
							</td>	
							<td>
								<c:if test="${reltnProdctRead2.prodctMainImage!=null}">
									<img class="reltn" id="reltnProdctImgTwo" name="reltnProdctChoice" src="${reltnProdctRead2.prodctMainImage}" onclick="javascript:selectedProdctInsert(2)" width="180" height="180">
								</c:if>
								<c:if test="${reltnProdctRead2.prodctMainImage==null}">
									<img class="reltn" id="reltnProdctImgTwo" name="reltnProdctChoice" src="resources/image/common/reltnPlus.png"onclick="javascript:selectedProdctInsert(2)" width="180" height="180">
								</c:if>
							</td>	
							<td>
								<c:if test="${reltnProdctRead3.prodctMainImage!=null}">
									<img class="reltn" id="reltnProdctImgThree" name="reltnProdctChoice" src="${reltnProdctRead3.prodctMainImage}" onclick="javascript:selectedProdctInsert(3)" width="180" height="180">
								</c:if>
								<c:if test="${reltnProdctRead3.prodctMainImage==null}">
									<img class="reltn" id="reltnProdctImgThree" name="reltnProdctChoice" src="resources/image/common/reltnPlus.png" onclick="javascript:selectedProdctInsert(3)" width="180" height="180">
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
				
				<div id ="schedlDiv" class="container" style="display:block" onload="javascript:schedledOpener(${selProdctRead.prodctDisplyCheck})">
					<table class="table table-bordered text-left">
						<tr>
							<th class="active text-center">
								행사명
							</th>
							<td>
								<select class="form-control" id="schedlSeq" name="schedlSeq">	
									<c:if test="${evntInfoRead.schedlSeq==null}">
										<c:forEach var="debecFestivalList" items="${debecFestivalList}" varStatus="status">
											<c:if test="${debecFestivalList.schedlSeq!=evntInfoRead.schedlSeq}">
											<option value="${debecFestivalList.schedlSeq}">${debecFestivalList.schedlTitl}</option>
											</c:if>
										</c:forEach>
										<option value="0" selected> 선택안함 </option>
										${evntInfoRead.schedlSeq}
									</c:if>
									<c:if test="${evntInfoRead.schedlSeq!=null}">
										<c:forEach var="debecFestivalList" items="${debecFestivalList}" varStatus="status">
											<c:if test="${debecFestivalList.schedlSeq==evntInfoRead.schedlSeq}">
												<option value="${debecFestivalList.schedlSeq}" selected>${debecFestivalList.schedlTitl}</option>
											</c:if>
											<c:if test="${debecFestivalList.schedlSeq!=evntInfoRead.schedlSeq}">
												<option value="${debecFestivalList.schedlSeq}">${debecFestivalList.schedlTitl}</option>
											</c:if>
										</c:forEach>
										<option value="0"> 선택안함 </option>
									</c:if>
								</select>
							</td>
						</tr>
						<tr>
							<th class="active text-center">
								기간
							</th>
							<td>
								<c:if test="${evntInfoRead.schedlSeq==null}">
									<font id="schedlDat">-</font>
								</c:if>
								<c:if test="${evntInfoRead.schedlSeq!=null}">
									<font id="schedlDat">${evntInfoRead.schedlStartDat}  ~  ${evntInfoRead.schedlEndDat}</font>
								</c:if>
							</td>
						</tr>
						<tr>
							<th class="active text-center">
								적용상태
							</th>
							<td>
								<c:choose>
						
									<c:when test="${evntInfoRead.evntStat eq 'Y'}">
									<font id="evntStat">진행중</font>	
									</c:when>
									
									<c:when test="${evntInfoRead.evntStat eq 'N'}">
									<font id="evntStat">종료</font>	
									</c:when>
									
	
									<c:when test="${evntInfoRead.evntStat eq 'D'}">
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
							<td>
								<c:if test="${evntInfoRead.schedlSeq==null}">
									<input type="text" class="form-control" name="discntRat" id="discntRat" value="0" disabled> %
								</c:if>
								<c:if test="${evntInfoRead.schedlSeq!=null}">
									<input type="text" class="form-control" name="discntRat" id="discntRat" value="<fmt:formatNumber value="${evntInfoRead.discntRat}" pattern=".00" />" > %
								</c:if>
							</td>
						</tr>
						<tr>
							<th class="active text-center">
								세일가
							</th>
							<td>
								<c:if test="${evntInfoRead.schedlSeq==null}">
									<input type="text" class="form-control" id="schedlSalePric" value="0" disabled>
								</c:if>
								<c:if test="${evntInfoRead.schedlSeq!=null}">
									<input type="text" class="form-control" id="schedlSalePric" value="<fmt:formatNumber value="${evntInfoRead.prodctSalePric}" type="number"/> 원" disabled>
								</c:if>
							</td>
						</tr>
						<tr>
							<th class="active text-center">
								마진율
							</th>
							<td>
								<c:if test="${evntInfoRead.schedlSeq==null}">
									<input type="text" class="form-control" id="schedlMargnRat" value="0" disabled>
								</c:if>
								<c:if test="${evntInfoRead.schedlSeq!=null}">
									<input type="text" class="form-control" id="schedlMargnRat" value="<fmt:formatNumber value="${evntInfoRead.prodctMargnrat}" pattern=".00" /> %" disabled>
								</c:if>
							</td>
						</tr>
					</table>
				</div>
				<div class="container text-right">
					<input class="btn btn-default" type="button" id="selProdctUpdate" value = "수정 완료">
		 			<input class="btn btn-default" type="button" id="selProdctCancel" value = "취    소">
	 			</div>
			</form>
		</div>
		<script>
			//상품 번호 중복 검사
			var oriProdctSeq = $("#prodctSeq").val()
			function prodctSeqDupliCheck()
			{
				
				var prodctSeq = {"prodctSeq" : $("input[name=prodctSeq]").val()};
				if($("#prodctSeq").val() == "")
				{
					alert("바코드를 입력하신 후에 중복 검사를 해주세요.")
				}
				else
				{
					$.ajax({
						type: "get",
						url: "/prodctSeqDuliCheck.do", //이페이지에서 중복체크를 한다
						data : prodctSeq,
						dataType : "JSON",
						success: function(data)
						{
							var dupliCheck = data["dupliCheck"];
							if(dupliCheck==0)
							{
								$("#dupliCheckSeq").val($("#prodctSeq").val());
								$("#prodctFileSeq").val($("#prodctSeq").val());
								alert("중복된 바코드가 없습니다!")
							}
							else
							{
								alert("다른 바코드를 입력해주세요.");
							}
						},
						error:function(xhr,status,error)
						{ //ajax 오류인경우  
						       alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
						}
					});
				}
			};		
		    
			$(document).ready(function() {      
		 
				$("#schedlSeq").change(function()
				{
					var schedlData = { "schedlSeq" : $("#schedlSeq").val()};
					if($("#schedlSeq").val()==0)
					{
						$("#schedlDat").html("-");
						$("#evntStat").html('');
						$("#discntRat").val("0");
						$("#discntRat").attr("disabled",true);
						$("#schedlSalePric").val("");
						$("#schedlMargnRat").val("");
					}
		  			else
		   			{
						$("#discntRat").attr("disabled",false);
						$.ajax({
							type: "get",
							url: "/schedlProdctDetl.do", //이페이지에서 중복체크를 한다
							data : schedlData,
							dataType : "JSON",
							success: function(data){
								var schedlStartDat = data["schedlStartDat"];
								var schedlEndtDat = data["schedlEndtDat"];
								var evntStat = data["evntStat"];
								$("#schedlDat").html(schedlStartDat + "  ~  " + schedlEndtDat);
		
								if(evntStat == 'Y')
									$("#evntStat").html('진행중');
								if(evntStat == 'R')
									$("#evntStat").html('대기중');
								if(evntStat == 'N')
									$("#evntStat").html('종료');
							},
							error:function(xhr,status,error)
							{ //ajax 오류인경우  
								alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
							}
						});
					}
				});
				
				$("#prodctDisplyCheck").change(function()
				{
					
					if($("#prodctDisplyCheck").val()!="판매 중")
					{
						$("#schedlDat").html("-");
						$("#evntStat").html('');
						$("#discntRat").val("0");
						$("#discntRat").attr("disabled",true);
						$("#schedlSalePric").val("");
						$("#schedlMargnRat").val("");
						$("#schedlDiv").hide();
					}
		  			else
			   		{
		  				$("#schedlSeq").val("0").prop("selected", true);
		  				$("#schedlDat").html("-");
						$("#evntStat").html('');
						$("#discntRat").val("0");
						$("#discntRat").attr("disabled",false);
						$("#schedlSalePric").val("");
						$("#schedlMargnRat").val("");
						$("#schedlDiv").show();
					}
				});
				$("#selProdctCancel").click(function(){
					$("input[name=selectedBotCatgrNme]").val($("#botCatgrNme").val());
					$.ajax({
						type : "POST",
						url : "/tempImgPathCleaner.do",
						success: function(data) {
							location.href = "/selProdctRead.do?pageType="+$("#pageType").val();
							
						},
					});
				});
		
				$("#selProdctUpdate").click(function(){
					if ($("#prodctSeq").val() == "")
					{
						alert("바코드를 입력해주세요.");
				      	$("#prodctSeq").focus();
					}
					else if ($("#dupliCheckSeq").val() == "")
					{
						alert("바코드 중복 검사를 진행해주세요.");
				      	$("#prodctSeq").focus();
					}
					else if ($("#prodctSeq").val() != $("#dupliCheckSeq").val())
					{
						alert("중복 검사를 하신 바코드와 현재 바코드가 일치하지 않습니다.");
				      	$("#prodctSeq").focus();
					}
					else if ($("#prodctNme").val() == "")
					{
						alert("상품명을 입력해주세요.");
				      	$("#prodctNme").focus();
					}
					else if ($("#topCatgrNme").val() == "대분류")
					{
						alert("대분류를 선택해주세요.");
				      	$("#topCatgrNme").focus();
					}
					else if ($("#midCatgrNme").val() == "중분류")
					{
						alert("중분류를 선택해주세요.");
				      	$("#midCatgrNme").focus();
					}
					else if ($("#botCatgrNme").val() == "소분류")
					{
						alert("소분류를 선택해주세요.");
				      	$("#botCatgrNme").focus();
					}
					else if ($("#clintSeq").val() == "0")
					{
						alert("거래처를 선택해주세요.");
				      	$("#clintSeq").focus();
					}
					else if ($("#prodctSelPric").val() == "")
					{
						alert("판매단가를 입력해주세요.");
				      	$("#prodctSelPric").focus();
					}
					else if ($("#prodctPurchsPric").val() == "")
					{
						alert("입고단가를 입력해주세요.");
				      	$("#prodctPurchsPric").focus();
					}
					else if ($("#discntRat").val() == null&& $("#schedlSeq").val()!="선택"&&$("#prodctDisplyCheck").val()=="Y")
					{
						alert("할인율을 입력해주세요.");
				      	$("#discntRat").focus();
					}
					else
					{
						$("input[name=selectedBotCatgrNme]").val($("#botCatgrNme").val());
						var chck = imgChangeChck();
						if(chck!=0)
						{
							var imgFrm = new FormData(document.getElementById('selProdctFileFrm'));
							$.ajax({
								url: "/selProdctImgCreate.do",
								data: imgFrm,
								processData: false,
								contentType: false,
								type: 'POST',
								success:function(data){
									$("#updateType").val(1);
									document.selProdctCreateFrm.action = "/selProdctUpdate.do"
									document.selProdctCreateFrm.method = "post";
									document.selProdctCreateFrm.submit();
								},
								error:function(xhr,status,error)
								{ //ajax 오류인경우  
									alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
								}
							});
						}
						else
						{
							$("#updateType").val(0);
							document.selProdctCreateFrm.action = "/selProdctUpdate.do"
							document.selProdctCreateFrm.method = "post";
							document.selProdctCreateFrm.submit();
						}
					}
				});
		
			});
			
			function selectedProdctInsert(num)
			{
				$('#selectedReltnProdct').val(num);
			};
			
			function schedledOpener(state)
			{
				if(state=="y")
				{
					$("#schedlDiv").show();
				}
				else
				{
					$("#schedlDiv").hide();
					
				}
			};
			function reltnClear()
			{
				$("#reltnProdctImgOne").attr("src","resources/image/common/reltnPlus.png");
				$("#reltnProdctImgTwo").attr("src","resources/image/common/reltnPlus.png");
				$("#reltnProdctImgThree").attr("src","resources/image/common/reltnPlus.png");
				$("#reltnProdctNmeOne").val("선택된 상품 없음");
				$("#reltnProdctNmeTwo").val("선택된 상품 없음");
				$("#reltnProdctNmeThree").val("선택된 상품 없음");
				$("#reltnProdctSelPricOne").val("");
				$("#reltnProdctSelPricTwo").val("");
				$("#reltnProdctSelPricThree").val("");
			}
	    </script>
	</body>
</html>