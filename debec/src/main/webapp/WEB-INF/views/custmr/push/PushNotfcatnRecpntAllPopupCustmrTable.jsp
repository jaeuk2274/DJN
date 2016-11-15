<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<body>	
	<form id="custmrFrm" name="custmrFrm">
		<div style="width:720px;">
			<table class="table table-bordered text-center" id="tableId">
				<colgroup>
					<col class="col-md-1 col-sm-1"/>                
				    <col class="col-md-2 col-sm-2"/>          
				    <col class="col-md-2 col-sm-2"/>           
				    <col class="col-md-2 col-sm-2"/>
				    <col class="col-md-2 col-sm-2"/>          
				    <col class="col-md-3 col-sm-3"/>
			   </colgroup>
				<tr class="active nodrag nodrop" style="cursor:pointer;">
					<th class="text-center"><input type="checkbox" id="allCCheck" onclick="allCChecking()"></th>
					<th class="text-center" onclick="rangeRecpnt(1)">아이디</th>
					<th class="text-center" onclick="rangeRecpnt(2)">이름</th>
					<th class="text-center" onclick="rangeRecpnt(3)">성별</th>
					<th class="text-center" onclick="rangeRecpnt(4)">주문 횟수</th>
					<th class="text-center" onclick="rangeRecpnt(5)">주문 금액</th>
				</tr>
				<c:forEach items="${PushRecpntAll}" var="pushRecpntVo" varStatus="status">	
					<tr onMouseOver="this.style.backgroundColor='#A5B448'" onmouseout="this.style.backgroundColor='#ffffff'" style="cursor:hand">
						<td><input type="checkbox" name="custmrCheck" value="${pushRecpntVo.custmrSeq}"></td>
						<td>${pushRecpntVo.custmrId}</td>
						<td>${pushRecpntVo.custmrNme}</td>
						<td>${pushRecpntVo.custmrGendr}</td>
						<td><fmt:formatNumber value="${pushRecpntVo.ordrAmont}" type="number"/></td>
						<td><fmt:formatNumber value="${pushRecpntVo.allPric}" type="number"/></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
	<div class="text-center" style=" width:720px; margin:0 auto;">
		<ul class="pagination">
			<c:if test = "${pushVo.currentPageNo>20}">
				<li>
					<a href="javascript:pushRecpntPaging(1)" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span></a>
				</li>
			</c:if>  
			<c:if test = "${pushVo.currentPageNo>10}">
				<li>
					<a href="javascript:pushRecpntPaging(${pushVo.firstPageNoOnPageList-1})" aria-label="Previous">
					<span aria-hidden="true">&lsaquo;</span></a>
				</li>
			</c:if> 	
						  	
			<c:forEach varStatus="status" begin="${pushVo.firstPageNoOnPageList}" end="${pushVo.lastPageNoOnPageList}">
			
				<c:if test="${pushVo.currentPageNo==status.current}">
					<li class="active">
						<a href="javascript:pushRecpntPaging(${status.current})">${status.current}</a>
					</li>
				</c:if>
				<c:if test="${pushVo.currentPageNo!=status.current}">
					<li><a href="javascript:pushRecpntPaging(${status.current})">${status.current}</a></li>
				</c:if>                   									
			</c:forEach>	
			<c:if test = "${pushVo.firstPageNoOnPageList + 10 < pushVo.totalPageCount}">
				<li>
					<a href="javascript:pushRecpntPaging(${pushVo.lastPageNoOnPageList+1})" aria-label="Next">
					<span aria-hidden="true">&rsaquo;</span></a>
				</li>
			</c:if>  
			<c:if test = "${pushVo.firstPageNoOnPageList + 20 < pushVo.totalPageCount}">
				<li>
					<a href="javascript:pushRecpntPaging(${pushVo.totalPageCount})" aria-label="Next">
					<span aria-hidden="true">&raquo;</span></a>
				</li>
			</c:if>  
		</ul>
	</div>
</body>