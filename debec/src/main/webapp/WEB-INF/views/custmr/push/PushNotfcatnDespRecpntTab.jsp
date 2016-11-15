<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Insert title here</title>
<!-- Don't forget to include jQuery ;) -->
<script src="/resources/jquery-modal-master/jquery.modal.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="/resources/jquery-modal-master/jquery.modal.css" type="text/css" />

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script src="/resources/jquery.tablednd.js"></script>

<!-- 가격 계산  JS 스크립트 -->
<script src="/resources/js/prodctPricCaclt.js"></script>
<!-- 모달 관련 JS 스크립트 -->
<script src="/resources/js/sellProdctModal.js"></script>
<!-- 카테고리 관련 JS 스크립트 -->
<script src="/resources/js/catgrChoice.js"></script>
<!-- 이미지 미리보기 관련 스크립트 -->
<script src="/resources/js/sellProdctImg.js"></script>

<!-- JSTree 관련 파일들 -->
<script src="/resources/jstree/jstree.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="/resources/jstree/themes/proton/style.min.css">
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- JQuery 관련 파일들 -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="/resources/js/push-js/pushNotfcatnRecpnt.js"></script>

<style>
ul.tabs {
    margin: 0;
    padding: 0;
    float: left;
    list-style: none;
    height: 32px;
    border-bottom: 1px solid #eee;
    border-left: 1px solid #eee;
    width: 100%;
    font-family:"dotum";
    font-size:12px;
/*     color:#EDEDED; */
}
ul.tabs li {
    float: left;
    text-align:center;
    cursor: pointer;
    width:82px;
    height: 31px;
    line-height: 31px;
    border: 1px solid #eee;
    border-left: none;
    font-weight: bold;
    background: #fafafa;
    overflow: hidden;
    position: relative;
/*     color:#EDEDED; */
}
ul.tabs li.active {
    background: #403A39;
    border-bottom: 1px solid #FFFFFF;
    color:#EDEDED;
}
.tab_container {
    border: 1px solid #eee;
    border-top: none;
    clear: both;
    float: left;
    width: 80%;
    background: #FFFFFF;
}
.tab_content {
    padding: 5px;
    font-size: 15px;
    display: none;
}
.tab_container .tab_content ul {
    width:100%;
    margin:0px;
    padding:0px;
}
.tab_container .tab_content ul li {
    padding:5px;
    list-style:none
}
;
 #container {
    width: 249px;
    margin: 0 auto;
}
</style>

      <script type="text/javascript">
// 			$(document).ready(function() {	
				$('#pushRecpnt').click(function(){
					var popUrl = "pushModalAllSearchPopup.do";
					var popupName= "pushModalAllSearchPopup";
					var width = 1600;
					var height = 500;
					var leftPosition = (screen.width/2) - (width/2);
						leftPosition += window.screenLeft;//듀얼 모니터일때
					var topPosition = (screen.height/2) - (height/2);
					
					var win = window.open(popUrl, popupName, "left="+leftPosition+", top="+topPosition+", toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, width="+width+", height="+height);
					
					document.frm.goalDtlSeq.value = goalDtlSeq;
					document.frm.target = popupName;
					document.frm.action=popUrl;
					document.frm.submit();
				});
// 			});
			//탭
			$(function () {
			    $(".tab_content").hide();
			    $(".tab_content:first").show();

			    $("ul.tabs li").click(function () {
			        $("ul.tabs li").removeClass("active").css("color", "#333");
			        //$(this).addClass("active").css({"color": "darkred","font-weight": "bolder"});
			        $(this).addClass("active").css("color", "#FFFFFF");
			        $(".tab_content").hide()
			        var activeTab = $(this).attr("rel");
			        $("#" + activeTab).fadeIn()
			    });
			});
	</script>
	<script type="text/javascript">
		function pushRecpntAllSearch() {
			alert("제발요");
			document.pushForm.action="/pushModalAllSearch.do";
			document.pushForm.submit();
		}
		
		function pushRecpntAllOptn(){
			alert("체크박스 전체 석택");
		    var pushRecv ="";
			var pushArray = new Array();
			
			<c:forEach var="PushRecpntAll" items="${PushRecpntAll}" varStatus="status">
		    pushRecv= "#"+"${PushRecpntAll.pushNotfcatnSeq}";
		  	var pushRec = pushRecv;
		    if($("input[type=checkbox]").is(":checked")){
		    	pushArray.push("${PushRecpntAll.pushNotfcatnSeq}")
		    }
		    </c:forEach>
		    alert("뜨는지 확인"+pushArray[1]);
			$.ajax({
	            type: "POST",
	            url: "/pushTempltDelete.do",
	            data: {
	            	"pushAllDelete" : "0",
	            	"pushAllDelete" : pushArray
	            	},
//		            dataType : "JSON",
	            success: function(data){//이페이지에서 중복체크를 한다
	            	alert(data);
	                window.location.reload();
	            },
	             error:function(xhr,status,error)
	             { 
	                alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
	             }
	        });
			var pushRecv ="";
			var pushArray = new Array();
		}
		
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
		})
	</script>
	<script type="text/javascript">
	</script>
	
   </head>
   <body id="pushContainer">
	<div id="container">
	    <ul class="tabs">
	        <li class="active" rel="pushRecpntAll" onclick="javascript:pushRecpntAll()">전체</li>
	        <li rel="pushRecpntProdct" onclick="javascript:pushRecpntProdct()">상품</li>
	        <li rel="pushRecpntCatgr">카테고리</li>
	        <li rel="pushRecpntPackg">패키지</li>
	        <li rel="pushRecpntGropPurchs">공동구매</li>
	    </ul>
	    <div class="tab_container">
	    	<!-- #tab1 -->
	        <div id="pushRecpntAll" class="tab_content">
	        	<form name="pushForm">
		        	<table class="table">
		        		<tr>
		        			<td>
			        			<select id="searchCnd" name="searchCnd">
		      						<c:choose>
										<c:when test="${pushValueObject.searchCnd == 0}">
											<option selected="selected" value="0">전체</option>
					        				<option value="1">아이디</option>
					        				<option value="2">이름</option>
					        				<option value="3">성별</option>
					        				<option value="4">주문횟수</option>
					        				<option value="5">주문금액</option>
										</c:when>
										<c:when test="${pushValueObject.searchCnd == 1}">
											<option value="0">전체</option>
					        				<option selected="selected" value="1">아이디</option>
					        				<option value="2">이름</option>
					        				<option value="3">성별</option>
					        				<option value="4">주문횟수</option>
					        				<option value="5">주문금액</option>
										</c:when>
										<c:when test="${pushValueObject.searchCnd == 2}">
											<option value="0">전체</option>
					        				<option value="1">아이디</option>
					        				<option selected="selected" value="2">이름</option>
					        				<option value="3">성별</option>
					        				<option value="4">주문횟수</option>
					        				<option value="5">주문금액</option>
										</c:when>
										<c:when test="${pushValueObject.searchCnd == 3}">
											<option value="0">전체</option>
					        				<option value="1">아이디</option>
					        				<option value="2">이름</option>
					        				<option selected="selected" value="3">성별</option>
					        				<option value="4">주문횟수</option>
					        				<option value="5">주문금액</option>
										</c:when>
										<c:when test="${pushValueObject.searchCnd == 4}">
											<option value="0">전체</option>
					        				<option value="1">아이디</option>
					        				<option value="2">이름</option>
					        				<option value="3">성별</option>
					        				<option selected="selected" value="4">주문횟수</option>
					        				<option value="5">주문금액</option>
										</c:when>
										<c:when test="${pushValueObject.searchCnd == 5}">
											<option value="0">전체</option>
					        				<option value="1">아이디</option>
					        				<option value="2">이름</option>
					        				<option value="3">성별</option>
					        				<option value="4">주문횟수</option>
					        				<option selected="selected" value="5">주문금액</option>
										</c:when>
									</c:choose>
		      					</select>
		        			</td>
		        			<td>
		        				<input type="text" name="searchWrd" id="allSearch">
		        			</td>
		        			<td>
		        				<input type="button" value="검색" class="btn btn-default" onclick="javascript:pushRecpntAllSearch()">
		        			</td>
		        		</tr>
		        	</table>
	        	</form>
	        	<table class="table">
	        		<tr>
	        			<td colspan="5">
	        				<select>
			        			<option>전체</option>
			        			<option>아이디</option>
			        			<option>이름</option>
			        			<option>성별</option>
			        			<option>주문횟수</option>
			        			<option>주문금액</option>
	        				</select>
	        			</td>
	        			<td>
	        				<input type="button" value="전체선택" class="btn btn-success" onclick="javascript:pushRecpntAllOptn()">
	        			</td>
	        		</tr>
	        		<tr>
		        		<th><input type="checkbox" id="allCheck"></th>
		        		<th>아이디</th>
		        		<th>이름</th>
		        		<th>성별</th>
		        		<th>주문 횟수</th>
		        		<th>주문 금액</th>
		        	</tr>
		        	<c:forEach var="pushRecpntAll" items="${PushRecpntAll}" varStatus="status">
		        	<tr>
		        		<td><input type="checkbox" value="${pushRecpntAll.custmrSeq}"></td>
		        		<td>${pushRecpntAll.custmrId}</td>
		        		<td>${pushRecpntAll.custmrNme}</td>
		        		<td>${pushRecpntAll.custmrGendr}</td>
		        		<td>${pushRecpntAll.ordrNumbrSeq}</td>
		        		<td>${pushRecpntAll.selPric}</td>
		        	</tr>
		        	</c:forEach>
	        	</table>
	        	<!-- 페이징 -->
				<div class="container text-center">
					<nav>
						<ul class="pagination">
							<c:if test = "${pushRecpntAllpushVo.currentPageNo>20}">
								<li>
									<a href="javascript:pushPaging(1)" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span></a>
								</li>
							</c:if>  
							<c:if test = "${pushRecpntAllpushVo.currentPageNo>10}">
								<li>
									<a href="javascript:pushPaging(${pushRecpntAllpushVo.firstPageNoOnPageList-1})" aria-label="Previous">
									<span aria-hidden="true">&lsaquo;</span></a>
								</li>
							</c:if> 	
										  	
							<c:forEach varStatus="status" begin="${pushRecpntAllpushVo.firstPageNoOnPageList}" end="${pushRecpntAllpushVo.lastPageNoOnPageList}">
							
								<c:if test="${pushRecpntAllpushVo.currentPageNo==status.current}">
									<li class="active">
										<a href="javascript:pushPaging(${status.current})">${status.current}</a>
									</li>
								</c:if>
								<c:if test="${pushRecpntAllpushVo.currentPageNo!=status.current}">
									<li><a href="javascript:pushPaging(${status.current})">${status.current}</a></li>
								</c:if>                   									
							</c:forEach>	
							<c:if test = "${pushRecpntAllpushVo.firstPageNoOnPageList + 10 < pushRecpntAllpushVo.totalPageCount}">
								<li>
									<a href="javascript:pushPaging(${pushRecpntAllpushVo.lastPageNoOnPageList+1})" aria-label="Next">
									<span aria-hidden="true">&rsaquo;</span></a>
								</li>
							</c:if>  
							<c:if test = "${pushRecpntAllpushVo.firstPageNoOnPageList + 20 < pushRecpntAllpushVo.totalPageCount}">
								<li>
									<a href="javascript:pushPaging(${pushRecpntAllpushVo.totalPageCount})" aria-label="Next">
									<span aria-hidden="true">&raquo;</span></a>
								</li>
							</c:if>  
						</ul>
					</nav>
				</div>
	        </div>
	        <!-- #tab2 -->
	        <div id="pushRecpntProdct" class="tab_content">
	        	<form name="pushRecpntProdctFrm" method="POST">
	        		<div style="width: 90%; margin-left: 5%; margin-right: 5%;">
			        	<div style="width: 100%;">
				        	<table class="table">
				        		<tr>
				        			<td>
					        			<select id="searchCnd" name="searchCnd">
				      						<c:choose>
												<c:when test="${pushValueObject.searchCnd == 0}">
													<option selected="selected" value="0">전체</option>
							        				<option value="1">아이디</option>
							        				<option value="2">이름</option>
							        				<option value="3">성별</option>
							        				<option value="4">주문횟수</option>
							        				<option value="5">주문금액</option>
												</c:when>
												<c:when test="${pushValueObject.searchCnd == 1}">
													<option value="0">전체</option>
							        				<option selected="selected" value="1">아이디</option>
							        				<option value="2">이름</option>
							        				<option value="3">성별</option>
							        				<option value="4">주문횟수</option>
							        				<option value="5">주문금액</option>
												</c:when>
												<c:when test="${pushValueObject.searchCnd == 2}">
													<option value="0">전체</option>
							        				<option value="1">아이디</option>
							        				<option selected="selected" value="2">이름</option>
							        				<option value="3">성별</option>
							        				<option value="4">주문횟수</option>
							        				<option value="5">주문금액</option>
												</c:when>
												<c:when test="${pushValueObject.searchCnd == 3}">
													<option value="0">전체</option>
							        				<option value="1">아이디</option>
							        				<option value="2">이름</option>
							        				<option selected="selected" value="3">성별</option>
							        				<option value="4">주문횟수</option>
							        				<option value="5">주문금액</option>
												</c:when>
												<c:when test="${pushValueObject.searchCnd == 4}">
													<option value="0">전체</option>
							        				<option value="1">아이디</option>
							        				<option value="2">이름</option>
							        				<option value="3">성별</option>
							        				<option selected="selected" value="4">주문횟수</option>
							        				<option value="5">주문금액</option>
												</c:when>
												<c:when test="${pushValueObject.searchCnd == 5}">
													<option value="0">전체</option>
							        				<option value="1">아이디</option>
							        				<option value="2">이름</option>
							        				<option value="3">성별</option>
							        				<option value="4">주문횟수</option>
							        				<option selected="selected" value="5">주문금액</option>
												</c:when>
											</c:choose>
				      					</select>
				        			</td>
				        			<td>
				        				<input type="text" name="searchWrd" id="allSearch">
				        			</td>
				        			<td>
				        				<input type="button" value="검색" class="btn btn-default" onclick="javascript:pushRecpntAllSearch()">
				        			</td>
				        		</tr>
				        	</table>
				        </div>
			        	<div style="display: inline-block; width: 35%;">
				        	<table class="table">
				        		<tr>
					        		<th>거래처</th>
					        		<th>상품이름</th>
					        	</tr>
					        	<c:forEach var="pushRecpntProdct" items="${pushRecpntProdct}" varStatus="status">
					        	<input type="hidden" value="${pushRecpntProdct.prodctSeq}">
					        	<input type="hidden" value="${pushRecpntProdct.clintSeq}">
					        	<tr onclick="javascript:prodctChoc(${pushRecpntProdct.prodctSeq})">
									<td>${pushRecpntProdct.clintNme}</td>
					        		<td>${pushRecpntProdct.prodctNme}</td>
					        	</tr>
					        	</c:forEach>
				        	</table>
				        </div>
				        <!-- 페이징 -->
						<div class="container text-center">
							<nav>
								<ul class="pagination">
									<c:if test = "${pushVoRecpntProdctCustmr.currentPageNo>20}">
										<li>
											<a href="javascript:pushPaging(1)" aria-label="Previous">
											<span aria-hidden="true">&laquo;</span></a>
										</li>
									</c:if>  
									<c:if test = "${pushVoRecpntProdctCustmr.currentPageNo>10}">
										<li>
											<a href="javascript:pushPaging(${pushVoRecpntProdctCustmr.firstPageNoOnPageList-1})" aria-label="Previous">
											<span aria-hidden="true">&lsaquo;</span></a>
										</li>
									</c:if> 	
												  	
									<c:forEach varStatus="status" begin="${pushVo.firstPageNoOnPageList}" end="${pushVo.lastPageNoOnPageList}">
									
										<c:if test="${pushVoRecpntProdctCustmr.currentPageNo==status.current}">
											<li class="active">
												<a href="javascript:pushPaging(${status.current})">${status.current}</a>
											</li>
										</c:if>
										<c:if test="${pushVoRecpntProdctCustmr.currentPageNo!=status.current}">
											<li><a href="javascript:pushPaging(${status.current})">${status.current}</a></li>
										</c:if>                   									
									</c:forEach>	
									<c:if test = "${pushVoRecpntProdctCustmr.firstPageNoOnPageList + 10 < pushVoRecpntProdctCustmr.totalPageCount}">
										<li>
											<a href="javascript:pushPaging(${pushVo.lastPageNoOnPageList+1})" aria-label="Next">
											<span aria-hidden="true">&rsaquo;</span></a>
										</li>
									</c:if>  
									<c:if test = "${pushVoRecpntProdctCustmr.firstPageNoOnPageList + 20 < pushVoRecpntProdctCustmr.totalPageCount}">
										<li>
											<a href="javascript:pushPaging(${pushVoRecpntProdctCustmr.totalPageCount})" aria-label="Next">
											<span aria-hidden="true">&raquo;</span></a>
										</li>
									</c:if>  
								</ul>
							</nav>
						</div>
			        	<div style="display:inline-block; width: 65%;" id="pushRecpntProdctCustmr">
				        </div>
				        <!-- 페이징 -->
						<div class="container text-center" id="pushVoRecpntProdctCustmr">
							<nav>
								<ul class="pagination">
									<c:if test = "${pushVoRecpntProdctCustmr.currentPageNo>20}">
										<li>
											<a href="javascript:pushPaging(1)" aria-label="Previous">
											<span aria-hidden="true">&laquo;</span></a>
										</li>
									</c:if>  
									<c:if test = "${pushVoRecpntProdctCustmr.currentPageNo>10}">
										<li>
											<a href="javascript:pushPaging(${pushVoRecpntProdctCustmr.firstPageNoOnPageList-1})" aria-label="Previous">
											<span aria-hidden="true">&lsaquo;</span></a>
										</li>
									</c:if> 	
												  	
									<c:forEach varStatus="status" begin="${pushVo.firstPageNoOnPageList}" end="${pushVo.lastPageNoOnPageList}">
									
										<c:if test="${pushVoRecpntProdctCustmr.currentPageNo==status.current}">
											<li class="active">
												<a href="javascript:pushPaging(${status.current})">${status.current}</a>
											</li>
										</c:if>
										<c:if test="${pushVoRecpntProdctCustmr.currentPageNo!=status.current}">
											<li><a href="javascript:pushPaging(${status.current})">${status.current}</a></li>
										</c:if>                   									
									</c:forEach>	
									<c:if test = "${pushVoRecpntProdctCustmr.firstPageNoOnPageList + 10 < pushVoRecpntProdctCustmr.totalPageCount}">
										<li>
											<a href="javascript:pushPaging(${pushVo.lastPageNoOnPageList+1})" aria-label="Next">
											<span aria-hidden="true">&rsaquo;</span></a>
										</li>
									</c:if>  
									<c:if test = "${pushVoRecpntProdctCustmr.firstPageNoOnPageList + 20 < pushVoRecpntProdctCustmr.totalPageCount}">
										<li>
											<a href="javascript:pushPaging(${pushVoRecpntProdctCustmr.totalPageCount})" aria-label="Next">
											<span aria-hidden="true">&raquo;</span></a>
										</li>
									</c:if>  
								</ul>
							</nav>
						</div>
				    </div>
	        	</form>
	        </div>
	        <!-- #tab3 -->
	        <div id="pushRecpntCatgr" class="tab_content">
	        <!-- 상품 추가 모달 -->
			<div id="insertProdctModal" style="display:none;">
				<aside>						
					<div style="width:20%; height:100%; display:inline-block; float:left">
						<table class="table table-bordered text-center" >
							<tr>
								<th>카테고리</th>
							</tr>
							<tr>
								<!-- td에 height값을 줘야 JSTree가 고정됨 -->
								<td style="height:600px" style="align:left;"><div id="tree" style="max-height:100%; overflow:auto"></div></td>
							</tr>
						</table>
					</div>
				</aside>
				<c:import url="/ReltnProdctAdList.do"/>	
			</div>
	        </div>
	        <!-- #tab4 -->
	        <div id="pushRecpntPackg" class="tab_content">
	        	<form name="pushRecpntPackgFrm" method="POST">
		        	<table class="table">
		        		<tr>
		        			<td>
			        			<select id="searchCnd" name="searchCnd">
		      						<c:choose>
										<c:when test="${pushValueObject.searchCnd == 0}">
											<option selected="selected" value="0">전체</option>
					        				<option value="1">아이디</option>
					        				<option value="2">이름</option>
					        				<option value="3">성별</option>
					        				<option value="4">주문횟수</option>
					        				<option value="5">주문금액</option>
										</c:when>
										<c:when test="${pushValueObject.searchCnd == 1}">
											<option value="0">전체</option>
					        				<option selected="selected" value="1">아이디</option>
					        				<option value="2">이름</option>
					        				<option value="3">성별</option>
					        				<option value="4">주문횟수</option>
					        				<option value="5">주문금액</option>
										</c:when>
										<c:when test="${pushValueObject.searchCnd == 2}">
											<option value="0">전체</option>
					        				<option value="1">아이디</option>
					        				<option selected="selected" value="2">이름</option>
					        				<option value="3">성별</option>
					        				<option value="4">주문횟수</option>
					        				<option value="5">주문금액</option>
										</c:when>
										<c:when test="${pushValueObject.searchCnd == 3}">
											<option value="0">전체</option>
					        				<option value="1">아이디</option>
					        				<option value="2">이름</option>
					        				<option selected="selected" value="3">성별</option>
					        				<option value="4">주문횟수</option>
					        				<option value="5">주문금액</option>
										</c:when>
										<c:when test="${pushValueObject.searchCnd == 4}">
											<option value="0">전체</option>
					        				<option value="1">아이디</option>
					        				<option value="2">이름</option>
					        				<option value="3">성별</option>
					        				<option selected="selected" value="4">주문횟수</option>
					        				<option value="5">주문금액</option>
										</c:when>
										<c:when test="${pushValueObject.searchCnd == 5}">
											<option value="0">전체</option>
					        				<option value="1">아이디</option>
					        				<option value="2">이름</option>
					        				<option value="3">성별</option>
					        				<option value="4">주문횟수</option>
					        				<option selected="selected" value="5">주문금액</option>
										</c:when>
									</c:choose>
		      					</select>
		        			</td>
		        			<td>
		        				<input type="text" name="searchWrd" id="allSearch">
		        			</td>
		        			<td>
		        				<input type="button" value="검색" class="btn btn-default" onclick="javascript:pushRecpntAllSearch()">
		        			</td>
		        		</tr>
		        	</table>
		        	<table class="table">
		        		<tr>
			        		<th>구분</th>
			        		<th>거래처 명</th>
			        		<th>패키지 명</th>
			        		<th>총 가격</th>
			        		<th>진열 여부</th>
			        	</tr>
			        	<c:forEach var="pushRecpntPackg" items="${pushRecpntPackg}" varStatus="status">
			        	<tr onclick="javascript:pushRecpntPackgAplctnCustmr(${pushRecpntPackg.packgSeq})">
			        		<td>${pushRecpntPackg.packgType}</td>
			        		<td>${pushRecpntPackg.clintNme}</td>
			        		<td>${pushRecpntPackg.packgNme}</td>
			        		<td>${pushRecpntPackg.allPric}</td>
			        		<td>${pushRecpntPackg.packgDisplayCheck}</td>
			        	</tr>
			        	</c:forEach>
		        	</table>
		        	<!-- 페이징 -->
					<div class="container text-center">
						<nav>
							<ul class="pagination">
								<c:if test = "${pushVo.currentPageNo>20}">
									<li>
										<a href="javascript:pushPaging(1)" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span></a>
									</li>
								</c:if>  
								<c:if test = "${pushVo.currentPageNo>10}">
									<li>
										<a href="javascript:pushPaging(${pushVo.firstPageNoOnPageList-1})" aria-label="Previous">
										<span aria-hidden="true">&lsaquo;</span></a>
									</li>
								</c:if> 	
											  	
								<c:forEach varStatus="status" begin="${pushVo.firstPageNoOnPageList}" end="${pushVo.lastPageNoOnPageList}">
								
									<c:if test="${pushVo.currentPageNo==status.current}">
										<li class="active">
											<a href="javascript:pushPaging(${status.current})">${status.current}</a>
										</li>
									</c:if>
									<c:if test="${pushVo.currentPageNo!=status.current}">
										<li><a href="javascript:pushPaging(${status.current})">${status.current}</a></li>
									</c:if>                   									
								</c:forEach>	
								<c:if test = "${pushVo.firstPageNoOnPageList + 10 < pushVo.totalPageCount}">
									<li>
										<a href="javascript:pushPaging(${pushVo.lastPageNoOnPageList+1})" aria-label="Next">
										<span aria-hidden="true">&rsaquo;</span></a>
									</li>
								</c:if>  
								<c:if test = "${pushVo.firstPageNoOnPageList + 20 < pushVo.totalPageCount}">
									<li>
										<a href="javascript:pushPaging(${pushVo.totalPageCount})" aria-label="Next">
										<span aria-hidden="true">&raquo;</span></a>
									</li>
								</c:if>  
							</ul>
						</nav>
					</div>
					<div id="pushRecpntPackgAplctnCustmr">
					</div>
	        	</form>
	        </div>
	        <!-- #tab5 -->
	        <div id="pushRecpntGropPurchs" class="tab_content">
	        	<form name="pushRecpntGropPurchsFrm" method="POST">
		        	<table class="table">
		        		<tr>
		        			<td>
			        			<select id="searchCnd" name="searchCnd">
		      						<c:choose>
										<c:when test="${pushValueObject.searchCnd == 0}">
											<option selected="selected" value="0">전체</option>
					        				<option value="1">아이디</option>
					        				<option value="2">이름</option>
					        				<option value="3">성별</option>
					        				<option value="4">주문횟수</option>
					        				<option value="5">주문금액</option>
										</c:when>
										<c:when test="${pushValueObject.searchCnd == 1}">
											<option value="0">전체</option>
					        				<option selected="selected" value="1">아이디</option>
					        				<option value="2">이름</option>
					        				<option value="3">성별</option>
					        				<option value="4">주문횟수</option>
					        				<option value="5">주문금액</option>
										</c:when>
										<c:when test="${pushValueObject.searchCnd == 2}">
											<option value="0">전체</option>
					        				<option value="1">아이디</option>
					        				<option selected="selected" value="2">이름</option>
					        				<option value="3">성별</option>
					        				<option value="4">주문횟수</option>
					        				<option value="5">주문금액</option>
										</c:when>
										<c:when test="${pushValueObject.searchCnd == 3}">
											<option value="0">전체</option>
					        				<option value="1">아이디</option>
					        				<option value="2">이름</option>
					        				<option selected="selected" value="3">성별</option>
					        				<option value="4">주문횟수</option>
					        				<option value="5">주문금액</option>
										</c:when>
										<c:when test="${pushValueObject.searchCnd == 4}">
											<option value="0">전체</option>
					        				<option value="1">아이디</option>
					        				<option value="2">이름</option>
					        				<option value="3">성별</option>
					        				<option selected="selected" value="4">주문횟수</option>
					        				<option value="5">주문금액</option>
										</c:when>
										<c:when test="${pushValueObject.searchCnd == 5}">
											<option value="0">전체</option>
					        				<option value="1">아이디</option>
					        				<option value="2">이름</option>
					        				<option value="3">성별</option>
					        				<option value="4">주문횟수</option>
					        				<option selected="selected" value="5">주문금액</option>
										</c:when>
									</c:choose>
		      					</select>
		        			</td>
		        			<td>
		        				<input type="text" name="searchWrd" id="allSearch">
		        			</td>
		        			<td>
		        				<input type="button" value="검색" class="btn btn-default" onclick="javascript:pushRecpntAllSearch()">
		        			</td>
		        		</tr>
		        	</table>
		        	<table class="table">
		        		<tr>
			        		<th>공동구매 제목</th>
			        		<th colspan="3">공둥구매 시간</th>
			        		<th>참여자 수</th>
			        		<th>공동구매 상태</th>
			        	</tr>
			        	<c:forEach var="pushRecpntGropPurchs" items="${pushRecpntGropPurchs}" varStatus="status">
			        	<tr onclick="javascript:pushRecpntGropPurchsAplctnCustmr(${pushRecpntGropPurchs.gropPurchsSeq})">
			        		<td>${pushRecpntGropPurchs.titl}</td>
			        		<td>${pushRecpntGropPurchs.ordrStarDat}</td>
			        		<td>~</td>
			        		<td>${pushRecpntGropPurchs.ordrEndDat}</td>
			        		<td>${pushRecpntGropPurchs.ordrNumbrSeq}</td>
			        		<td>${pushRecpntGropPurchs.stat}</td>
			        	</tr>
			        	</c:forEach>
		        	</table>
		        	<!-- 페이징 -->
					<div class="container text-center">
						<nav>
							<ul class="pagination">
								<c:if test = "${pushVo.currentPageNo>20}">
									<li>
										<a href="javascript:pushPaging(1)" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span></a>
									</li>
								</c:if>  
								<c:if test = "${pushVo.currentPageNo>10}">
									<li>
										<a href="javascript:pushPaging(${pushVo.firstPageNoOnPageList-1})" aria-label="Previous">
										<span aria-hidden="true">&lsaquo;</span></a>
									</li>
								</c:if> 	
											  	
								<c:forEach varStatus="status" begin="${pushVo.firstPageNoOnPageList}" end="${pushVo.lastPageNoOnPageList}">
								
									<c:if test="${pushVo.currentPageNo==status.current}">
										<li class="active">
											<a href="javascript:pushPaging(${status.current})">${status.current}</a>
										</li>
									</c:if>
									<c:if test="${pushVo.currentPageNo!=status.current}">
										<li><a href="javascript:pushPaging(${status.current})">${status.current}</a></li>
									</c:if>                   									
								</c:forEach>	
								<c:if test = "${pushVo.firstPageNoOnPageList + 10 < pushVo.totalPageCount}">
									<li>
										<a href="javascript:pushPaging(${pushVo.lastPageNoOnPageList+1})" aria-label="Next">
										<span aria-hidden="true">&rsaquo;</span></a>
									</li>
								</c:if>  
								<c:if test = "${pushVo.firstPageNoOnPageList + 20 < pushVo.totalPageCount}">
									<li>
										<a href="javascript:pushPaging(${pushVo.totalPageCount})" aria-label="Next">
										<span aria-hidden="true">&raquo;</span></a>
									</li>
								</c:if>  
							</ul>
						</nav>
					</div>
					<div id="pushRecpntGropPurchsAplctnCustmr">
					</div>
	        	</form>
	        </div>
	    </div>
	    <!-- .tab_container -->
	<!-- #container -->
</div>
   </body>
</html>