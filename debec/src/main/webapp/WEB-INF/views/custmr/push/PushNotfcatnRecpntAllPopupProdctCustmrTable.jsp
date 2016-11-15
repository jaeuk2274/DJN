<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<body>	
	<form id="custmrFrm2" name="custmrFrm2">
		<div style="width:300px;">
			<table class="table table-bordered text-center" id="tableId">
				<colgroup>
					<col class="col-md-1 col-sm-1"/>                
				    <col class="col-md-2 col-sm-3"/>          
				    <col class="col-md-2 col-sm-3"/>           
				    <col class="col-md-2 col-sm-3"/>
				    <col class="col-md-2 col-sm-2"/>    
			   </colgroup>
				<tr class="active nodrag nodrop" style="cursor:pointer;">
					<th class="text-center"><input type="checkbox" id="allCCheck2" onclick="allCChecking2()"></th>
					<th class="text-center" onclick="rangeRecpnt(1)">아이디</th>
					<th class="text-center" onclick="rangeRecpnt(2)">이름</th>
					<th class="text-center" onclick="rangeRecpnt(3)">성별</th>
					<th class="text-center" onclick="rangeRecpnt(4)">주문 횟수</th>
				</tr>
				<c:forEach items="${PushRecpntAllProdct}" var="pushRecpntVo" varStatus="status">	
					<tr onMouseOver="this.style.backgroundColor='#A5B448'" onmouseout="this.style.backgroundColor='#ffffff'" style="cursor:hand">
						<td><input type="checkbox" name="custmrCheck2" value="${pushRecpntVo.custmrSeq}"></td>
						<td>${pushRecpntVo.custmrId}</td>
						<td>${pushRecpntVo.custmrNme}</td>
						<td>${pushRecpntVo.custmrGendr}</td>
						<td><fmt:formatNumber value="${pushRecpntVo.ordrAmont}" type="number"/></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
	<div class="text-center" style=" width:300px; margin:0 auto;">
		<ul class="pagination">
			<c:if test = "${pushVo3.currentPageNo>20}">
				<li>
					<a href="javascript:pushRecpntPaging2(1)" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span></a>
				</li>
			</c:if>  
			<c:if test = "${pushVo3.currentPageNo>10}">
				<li>
					<a href="javascript:pushRecpntPaging2(${pushVo3.firstPageNoOnPageList-1})" aria-label="Previous">
					<span aria-hidden="true">&lsaquo;</span></a>
				</li>
			</c:if> 	
						  	
			<c:forEach varStatus="status" begin="${pushVo3.firstPageNoOnPageList}" end="${pushVo3.lastPageNoOnPageList}">
			
				<c:if test="${pushVo3.currentPageNo==status.current}">
					<li class="active">
						<a href="javascript:pushRecpntPaging2(${status.current})">${status.current}</a>
					</li>
				</c:if>
				<c:if test="${pushVo3.currentPageNo!=status.current}">
					<li><a href="javascript:pushRecpntPaging2(${status.current})">${status.current}</a></li>
				</c:if>                   									
			</c:forEach>	
			<c:if test = "${pushVo3.firstPageNoOnPageList + 10 < pushVo3.totalPageCount}">
				<li>
					<a href="javascript:pushRecpntPaging2(${pushVo3.lastPageNoOnPageList+1})" aria-label="Next">
					<span aria-hidden="true">&rsaquo;</span></a>
				</li>
			</c:if>  
			<c:if test = "${pushVo3.firstPageNoOnPageList + 20 < pushVo3.totalPageCount}">
				<li>
					<a href="javascript:pushRecpntPaging2(${pushVo3.totalPageCount})" aria-label="Next">
					<span aria-hidden="true">&raquo;</span></a>
				</li>
			</c:if>  
		</ul>
	</div>
</body>