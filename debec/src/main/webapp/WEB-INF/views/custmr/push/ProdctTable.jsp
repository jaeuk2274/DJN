<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>대신 장봐주는 남자 - 대.장.남</title>
	<script type="text/javascript">
	//상품테이블 검색
	function prodctSech() {
		var formData = $("#prodctSechFrm").serialize();
		$.ajax({
			type : "POST",
			url : "/selectProdctAdList20.do",
			data : formData,
			success: function(msg) {
				document.getElementById('prodctTable').innerHTML = msg;			}
		});
	}

	//상품테이블 페이징
	function prodctPaging(page) {
		document.prodctPagingFrm.currentPageNo.value = page;
		var formData = $("#prodctPagingFrm").serialize();
		$.ajax({
			type : "POST",
			url : "/selectProdctAdList2.do",
			data : formData,
			success: function(msg) {
				document.getElementById('prodctTable').innerHTML = msg;	
			}
		});
	}
	
	function sechProdct(prodctSeq){
		document.pushRecpntAllInfoFrm2.prodctSeq.value = prodctSeq;
		pushRecpntSearch2(1);
	}
	</script>
</head>
<body>
	<div id="prodctTable">
		<form name="prodctPagingFrm" id="prodctPagingFrm" method="post">      
			<input type="hidden" name="prodctSechText" value="${MainDisplayValueObject2.prodctSechText}"> <!-- 검색 상품명 -->
			<input type="hidden" name="currentPageNo" value="${MainDisplayValueObject2.currentPageNo}"> <!-- 현재 페이지 -->
			<input type="hidden" name="catgrSeq" value="${MainDisplayValueObject2.catgrSeq}"> <!-- 카테고리 고유값 -->
		</form> 
		<div style="width:195px;">			
			<form id="prodctForm">
				<table class="table table-bordered text-center">
					<colgroup>
						<col class="col-md-4 col-sm-12"/>
					</colgroup>					
					<tr class="active nodrag nodrop">
						<th class="text-center">상품명</th>
					</tr>
					<c:forEach items="${ProdctAdList}" var="MainDisplayVo" varStatus="status">	
						<tr style="cursor:pointer" onclick="sechProdct(${MainDisplayVo.prodctSeq})" onMouseOver="this.style.backgroundColor='#A5B448'" onmouseout="this.style.backgroundColor='#ffffff'">
							<td>${MainDisplayVo.prodctNme}</td>
						</tr>
					</c:forEach>				
				</table>	
			</form>
		</div>
		<div class="text-center" style=" width:195px;">
			<ul class="pagination">
				<c:if test = "${MainDisplayValueObject2.currentPageNo>20}">
					<li>
						<a href="javascript:prodctPaging(1)" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:if>  
				<c:if test = "${MainDisplayValueObject2.currentPageNo>10}">
					<li>
						<a href="javascript:prodctPaging(${MainDisplayValueObject2.firstPageNoOnPageList-1})" aria-label="Previous">
						<span aria-hidden="true">&lsaquo;</span></a>
					</li>
				</c:if> 	
							  	
				<c:forEach varStatus="status" begin="${MainDisplayValueObject2.firstPageNoOnPageList}" end="${MainDisplayValueObject2.lastPageNoOnPageList}">
				
					<c:if test="${MainDisplayValueObject2.currentPageNo==status.current}">
						<li class="active">
							<a href="javascript:prodctPaging(${status.current})">${status.current}</a>
						</li>
					</c:if>
					<c:if test="${MainDisplayValueObject2.currentPageNo!=status.current}">
						<li><a href="javascript:prodctPaging(${status.current})">${status.current}</a></li>
					</c:if>                   									
				</c:forEach>	
				<c:if test = "${MainDisplayValueObject2.firstPageNoOnPageList + 10 < MainDisplayValueObject2.totalPageCount}">
					<li>
						<a href="javascript:prodctPaging(${MainDisplayValueObject2.lastPageNoOnPageList+1})" aria-label="Next">
						<span aria-hidden="true">&rsaquo;</span></a>
					</li>
				</c:if>  
				<c:if test = "${MainDisplayValueObject2.firstPageNoOnPageList + 20 < MainDisplayValueObject2.totalPageCount}">
					<li>
						<a href="javascript:prodctPaging(${MainDisplayValueObject2.totalPageCount})" aria-label="Next">
						<span aria-hidden="true">&raquo;</span></a>
					</li>
				</c:if>  
			</ul>
		</div>		
		<div style="text-align:center;">
			<form name="prodctSechFrm" id="prodctSechFrm" method="post" onsubmit="return false">  				
				<div class="form-inline">
					<input type="hidden" name="catgrSeq" value="${MainDisplayValueObject2.catgrSeq}"> <!-- 카테고리 고유값 -->
					<input type="text" class="form-control" name="prodctSechText" placeholder="상품명을 입력하세요." onKeyPress="if(event.keyCode=='13') prodctSech()">
					<input type="button" class="btn btn-default" value="검색" onclick="javascript:prodctSech()">
				</div>
			</form>
		</div>
	</div>
</body>
</html>