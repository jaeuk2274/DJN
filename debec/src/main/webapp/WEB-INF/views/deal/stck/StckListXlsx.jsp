<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%


response.setHeader("Content-Description", "JSP Generated Data");
response.setHeader("Content-Disposition", "attachment;filename=stckList.xls");%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
* 특정 상품의 재고 내역을 조회하고 이를 EXCEL로 저장하는 JSP 입니다.
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
		<title>재고 목록</title>
	</head>

	<body>
		<div style="width:1000px; align:center">
			<table style="width:1000px">
				<colgroup>
					<col width="200px">
					<col width="200px">
					<col width="200px">
					<col width="200px">
					<col width="200px">
				</colgroup>
				<tr>
					<td colspan="5" style="text-align:center; border-left:0px solid; border-right:0px solid; border-top:0px solid; border-bottom:3px solid; border-color:#FFBB00;"> 
						<font size="5"><b>재고 관리 Data 관리부</b></font>
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
			</colgroup>
			<tr align="center">
				<th bgcolor="EBF1DE" style="text-align:center">상품명</th>
				<th bgcolor="EBF1DE" style="text-align:center">상품수량</th>
				<th bgcolor="EBF1DE" style="text-align:center">입고 단가</th>
				<th bgcolor="EBF1DE" style="text-align:center">거래처</th>
				<th bgcolor="EBF1DE" style="text-align:center">거래처 전화번호</th>
			</tr>
			<c:forEach var="stck" items="${stckList}" varStatus="status">
				<tr>
					<td>${stck.prodctNme}</td>
					<td>${stck.qunt}</td>
					<td>${stck.purchsPric}</td>
					<td>${stck.clintNme}</td>
					<td>${stck.clintMangrNum}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>