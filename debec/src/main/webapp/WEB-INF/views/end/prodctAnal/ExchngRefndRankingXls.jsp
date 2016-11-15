<!-- 
* 매출 분석 엑셀 다운로드를 위한 화면 JSP입니다.
* 
* history :
*        김대현, 1.0, 2016/10/02 – 초기 작성
* author : 김대현
* version : 1.0, 2016/10/02  - 초기 작성
* see : 엑셀 다운로드를 위한 화면
//-->


<%@ page language="java" contentType="application/vnd.ms-excel; charset=utf-8"%>
<%response.setHeader("Content-Description", "JSP Generated Data");
response.setHeader("Content-Disposition", "attachment;filename=ExchngRefndAnal.xls");%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>교환/환불 분석</title>
</head>

<body>				
	<div style="width:1600px; align:center">
		<table style="width:1600px">
			<colgroup>
				<col width="200px">
				<col width="200px">
				<col width="200px">
				<col width="200px">
				<col width="200px">
				<col width="200px">
				<col width="200px">
				<col width="200px">
			</colgroup>
			<tr>
				<td colspan="8" style="text-align:center; border-left:0px solid; border-right:0px solid; border-top:0px solid; border-bottom:3px solid; border-color:#FFBB00;"> 
					<font size="5"><b>교환/환불 분석 Data 관리부</b></font>
				</td>
			</tr>
		</table>
	</div>
	<br>
	<table border="1" bordercolor="#7F7F7F" style="width:1000px;">
		<colgroup>
			<col width="200px">
			<col width="200px">
			<col width="200px">
			<col width="200px">
			<col width="200px">
			<col width="200px">
			<col width="200px">
			<col width="200px">
		</colgroup>
		<tr>
			<th bgcolor="EBF1DE" style="text-align:center">순위</th>
			<th bgcolor="EBF1DE" style="text-align:center">상품명</th>
			<th bgcolor="EBF1DE" style="text-align:center">판매가</th>
			<th bgcolor="EBF1DE" style="text-align:center">판매수량</th>
			<th bgcolor="EBF1DE" style="text-align:center">환불수량</th>
			<th bgcolor="EBF1DE" style="text-align:center">순이익</th>
			<th bgcolor="EBF1DE" style="text-align:center">마진율</th>
			<th bgcolor="EBF1DE" style="text-align:center">판매합계</th>
		</tr>
		<c:forEach items="${refndList}" var="refnd" varStatus="status">	
			<tr>
				<td>${status.index+1}</td>
				<td>${refndRanking.prodctNme}</td>
				<td>${refndRanking.selPric}</td>
				<td>${refndRanking.selQunt}</td>
				<td>${refndRanking.refndQunt}</td>
				<td>${refndRanking.profit}</td>
				<td>${refndRanking.margnRat}</td>
				<td>${refndRanking.selSum}</td>
			</tr>
		</c:forEach>			
	</table>
</body>
</html>