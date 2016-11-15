<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<body>	
	<form id="custmrTempFrm" name="custmrTempFrm">
		<input type="hidden" name="currentPageNo" value="${pushVo2.currentPageNo}"> <!-- 현재 페이지 -->
		<div style="width:340px;">
			<table class="table table-bordered text-center" id="tableId">
				<colgroup>
					<col class="col-md-2 col-sm-2"/>                
				    <col class="col-md-5 col-sm-5"/>          
				    <col class="col-md-5 col-sm-5"/>     
			   </colgroup>
				<tr class="active nodrag nodrop" style="cursor:pointer;">
					<th class="text-center"><input type="checkbox" id="allTCheck" onclick="allTChecking()"></th>
					<th class="text-center" onclick="rangeRecpnt(1)">아이디</th>
					<th class="text-center" onclick="rangeRecpnt(2)">이름</th>
				</tr>
				<c:forEach items="${PushRecpntTempAll}" var="pushRecpntTempVo" varStatus="status">	
					<tr onMouseOver="this.style.backgroundColor='#A5B448'" onmouseout="this.style.backgroundColor='#ffffff'" style="cursor:hand">
						<td><input type="checkbox" name="custmrTempCheck" value="${pushRecpntTempVo.custmrSeq}"></td>
						<td>${pushRecpntTempVo.custmrId}</td>
						<td>${pushRecpntTempVo.custmrNme}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
	<div class="text-center" style=" width:340px; margin:0 auto;">
		<ul class="pagination">
			<c:if test = "${pushVo2.currentPageNo>20}">
				<li>
					<a href="javascript:pushRecpntTempPaging(1)" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span></a>
				</li>
			</c:if>  
			<c:if test = "${pushVo2.currentPageNo>10}">
				<li>
					<a href="javascript:pushRecpntTempPaging(${pushVo2.firstPageNoOnPageList-1})" aria-label="Previous">
					<span aria-hidden="true">&lsaquo;</span></a>
				</li>
			</c:if> 	
						  	
			<c:forEach varStatus="status" begin="${pushVo2.firstPageNoOnPageList}" end="${pushVo2.lastPageNoOnPageList}">
			
				<c:if test="${pushVo2.currentPageNo==status.current}">
					<li class="active">
						<a href="javascript:pushRecpntTempPaging(${status.current})">${status.current}</a>
					</li>
				</c:if>
				<c:if test="${pushVo2.currentPageNo!=status.current}">
					<li><a href="javascript:pushRecpntTempPaging(${status.current})">${status.current}</a></li>
				</c:if>                   									
			</c:forEach>	
			<c:if test = "${pushVo2.firstPageNoOnPageList + 10 < pushVo2.totalPageCount}">
				<li>
					<a href="javascript:pushRecpntTempPaging(${pushVo2.lastPageNoOnPageList+1})" aria-label="Next">
					<span aria-hidden="true">&rsaquo;</span></a>
				</li>
			</c:if>  
			<c:if test = "${pushVo2.firstPageNoOnPageList + 20 < pushVo2.totalPageCount}">
				<li>
					<a href="javascript:pushRecpntTempPaging(${pushVo2.totalPageCount})" aria-label="Next">
					<span aria-hidden="true">&raquo;</span></a>
				</li>
			</c:if>  
		</ul>
	</div>	
	<div class="text-center" style=" width:340px; margin:0 auto;">
		<input type="button" class="btn btn-default" value="등록" onclick="insertRecpnt()">
		<input type="button" class="btn btn-default" value="취소" onclick="closeIt()">
	</div>
	<script>
	function pushRecpntTempPaging(page) {
		document.custmrTempFrm.currentPageNo.value = page;
		
    	var formData = $("#custmrTempFrm").serialize();
    	$.ajax({
    		type : "POST",
    		url : "/pushNotfcatnRecpntAllPopupTempTable.do",
    		data : formData,
    		success: function(msg) {
    			alert(msg);
    			document.getElementById('tempTable').innerHTML = msg;	
    		}
    	});
    }
	</script>
</body>