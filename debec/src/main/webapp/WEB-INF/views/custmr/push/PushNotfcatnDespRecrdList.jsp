<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 
* 푸시알림 템플릿을 조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        이정호, 1.0, 2016/08/24 – 초기 작성
* author : 이정호
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
		
	<script src="/resources/js/push-js/pushNotfcatnRecpnt.js"></script>
	<script type="text/javascript">
	//페이징
	function pushPaging(page) {		
		document.pushListInfo.currentPageNo.value = page;
		document.pushListInfo.action ="/pushNotfcatnDespRecrdList.do";
		document.pushListInfo.submit();
	}
	//검색
	function pushNotfcatnDespRecrdSearch(page){
		var searchWrd = document.getElementById("searchWrd").value;
		var searchCnd = document.getElementById("searchCnd").value;
		
		document.pushListInfo.currentPageNo.value = page;
		document.pushListInfo.searchCnd.value = searchCnd;
		document.pushListInfo.searchWrd.value = searchWrd;
		document.pushListInfo.action ="/pushNotfcatnDespRecrdList.do";
		document.pushListInfo.submit();
	}
	//다시보내기
	function pushNotfcatnReDesp(despRecrdSeq) {
		document.getElementById("despRecrdSeq").value = despRecrdSeq;
		document.frm.action="/pushNotfcatnReDesp.do";
		document.frm.submit();
	}
	$(function(){
	//전체선택 체크박스 클릭
		$("#allCheck").click(function(){
			//만약 전체 선택 체크박스가 체크된상태일경우
			if($("#allCheck").prop("checked")) {
				//해당화면에 전체 checkbox들을 체크해준다
				$("input[type=checkbox]").prop("checked",true);
				// 전체선택 체크박스가 해제된 경우
			} 
			else {
				//해당화면에 모든 checkbox들의 체크를해제시킨다.
				$("input[type=checkbox]").prop("checked",false);
			}
		})
	});
	
	//체크박스 삭제
	function pushReplaceDelete() {
		var chked_length = $("input[name=check]:checked").length; //체크된 박스 개수
		if(chked_length == 0){
			alert("삭제할 발송내역을 선택해주십시오.")
		}
		else{
			if (confirm("총 " + chked_length + "개의 항목을 삭제하시겠습니까?") == true){    //확인
				var chked_val = "";
				$(":checkbox[name='check']:checked").each(function(pi,po){
					chked_val += ","+po.value;
				});
				if(chked_val!="")chked_val = chked_val.substring(1);

				$.ajax({
					type:"POST",
					url:"/pushNotfcatnDespRecrdDelete.do",
					data:chked_val,
					success:function(){
						location.reload();
					},
					error:function(request,status,error){
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
				});
			}
			else{
				alert("삭제가 취소되었습니다.");	
			}
		}
	};
	</script>
</head>
<body>
	<!-- 상단 메뉴바 import -->
	<div style="margin-bottom: 130px;">
	    <c:import url="/TopFrame.do"/>
	</div>	
	<div class="container">
		<form name="pushListInfo" method="POST">
			<input type="hidden" name="searchWrd" value="${pushVo.searchWrd}"> <!-- 검색단어(문자값) -->
			<input type="hidden" name="searchCnd" value="${pushVo.searchCnd}"> <!-- 정렬조건(숫자값) -->
			<input type="hidden" name="currentPageNo" value="${pushVo.currentPageNo}"> <!-- 현재 페이지 -->
		</form>
		
		<div class="container">
			<h3>푸시 알림 발송 내역</h3>
			<div style="display:inline-block; margin-top:1%; float:right">
			   <h5>
			     <img src="../../../../../resources/image/common/home.png"> > 푸시 알림 관리 > 
			     <a href="/pushNotfcatnDespRecrdList.do" style="text-decoration:none"><strong>푸시 알림 발송 내역</strong></a>
			   </h5>
			</div>
		</div>
		
		<form name="frm" method="POST" class="form-inline">
			<input type="hidden" name="pushChkGroup">
			<input type="hidden" name="despRecrdSeq" id="despRecrdSeq"> <!-- 다시 보내기 -->
			<div class="container form-group">
				<table class="table table-bordered">
					<tr>
						<td>															
							<select class="form-control" id="searchCnd" >
							    <option value="0" selected>전체</option>
							    <option value="1">제목</option>
							    <option value="2">등록자</option>
							</select>
							<input type="text" class="form-control" id="searchWrd" style="width:86%">
							<input type="button" class="btn btn-default" value="검색" onclick="pushNotfcatnDespRecrdSearch(1)">
						</td>
					</tr>
				</table>
			</div>
		</form>
		
		<div class="form-group"></div>
		
	 	<div class="container" id="mainDiv">
	       	<table class="table table-bordered">
	       		<tr class="active">
	        		<th class="text-center"><input type="checkbox" id="allCheck"></th>
	        		<th class="text-center">발송일</th>
	        		<th class="text-center">푸시 알림 제목</th>
	        		<th class="text-center">등록자</th>
	        		<th class="text-center">성공</th>
	        		<th class="text-center">실패한 수</th>
	        		<th class="text-center">열어본 수</th>
	        		<th class="text-center">다시보내기</th>
	        	</tr>
	        	<c:forEach var="pushNotfcatnDespRecrdList" items="${pushNotfcatnDespRecrdList}" varStatus="status">
		        	<tr onmouseover="this.style.backgroundColor='#F4FFFD'" onmouseout="this.style.backgroundColor='#ffffff'">
		        		<td class="text-center"><input type="checkbox" value="${pushNotfcatnDespRecrdList.despRecrdSeq}" name="check"></td>
		        		<td class="text-center">${pushNotfcatnDespRecrdList.pushDat}</td>
		        		<td class="text-center">${pushNotfcatnDespRecrdList.pushTitl}</td>
		        		<td class="text-center">${pushNotfcatnDespRecrdList.regstr}</td>
		        		<c:choose>
		        			<c:when test="${pushNotfcatnDespRecrdList.sucsCont == 0}">
		        				<td class="text-center">
		        					0 (<fmt:formatNumber value="0" pattern="0"/>%)
		        				</td>
		        			</c:when>
		        			<c:otherwise>		        			
				        		<td class="text-center">
				        			${pushNotfcatnDespRecrdList.sucsCont}
				        			(<fmt:formatNumber value="${(pushNotfcatnDespRecrdList.sucsCont/(pushNotfcatnDespRecrdList.sucsCont + pushNotfcatnDespRecrdList.falCont))*100}" pattern="0"/>%)
				        		</td>
		        			</c:otherwise>
		        		</c:choose>
		        		<c:choose>
		        			<c:when test="${pushNotfcatnDespRecrdList.falCont == 0}">
		        				<td class="text-center">
		        					0 (<fmt:formatNumber value="0" pattern="0"/>%)
		        				</td>
		        			</c:when>
		        			<c:otherwise>		        				
				        		<td class="text-center">
				        			${pushNotfcatnDespRecrdList.falCont}
				        			(<fmt:formatNumber value="${(pushNotfcatnDespRecrdList.falCont/(pushNotfcatnDespRecrdList.sucsCont + pushNotfcatnDespRecrdList.falCont))*100}" pattern="0"/>%)
				        		</td>
		        			</c:otherwise>
		        		</c:choose>
		        		<c:choose>
		        			<c:when test="${pushNotfcatnDespRecrdList.openCont == 0}">
		        				<td class="text-center">
		        					0 (<fmt:formatNumber value="0" pattern="0"/>%)
		        				</td>
		        			</c:when>
		        			<c:otherwise>		        				
				        		<td class="text-center">
				        			${pushNotfcatnDespRecrdList.openCont}
				        			(<fmt:formatNumber value="${(pushNotfcatnDespRecrdList.openCont/(pushNotfcatnDespRecrdList.sucsCont + pushNotfcatnDespRecrdList.falCont))*100}" pattern="0"/>%)
				        		</td>
		        			</c:otherwise>
		        		</c:choose>
		        		<td class="text-center"><input type="button" class="btn btn-default" value="전송" onclick="pushDespRecrdRetry(${pushNotfcatnDespRecrdList.despRecrdSeq})"></td>
		        	</tr>
	        	</c:forEach>
	       	</table>
			<div class="text-center" style=" width:420px; margin:0 auto;">
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
			</div>		
			<div class="text-right">
				<input type="button" class="btn btn-default" onclick="pushReplaceDelete()" value="삭제">
			</div>		
		</div>	
	</div>
</body>
</html>