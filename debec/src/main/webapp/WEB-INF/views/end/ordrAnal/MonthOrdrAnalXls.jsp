<!-- 
* 주문 분석 엑셀 다운로드를 위한 화면 JSP입니다.
* 
* history :
*        시상일, 1.0, 2016/08/24 – 초기 작성
* author : 시상일
* version : 1.0, 2016/08/24  - 초기 작성
* see : 엑셀 다운로드를 위한 화면
//-->

<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  --%>


<%@ page language="java" contentType="application/vnd.ms-excel; charset=utf-8"%>
<%response.setHeader("Content-Description", "JSP Generated Data");
response.setHeader("Content-Disposition", "attachment;filename=OrdrAnal.xls");%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>월별 주문 화면</title>
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
					<font size="5"><b>주문 분석 Data 관리부</b></font>
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
			<tr>
				<td>일자</td>
				<td>전화 주문수</td>
				<td>전화 판매액</td>
				<td>앱 주문수</td>
				<td>앱 판매액</td>
			</tr>
			<c:forEach items="${ordrList}" var="ordrList" varStatus="status">	
				<tr>
					<td>${ordrList.salsDay}</td>
					<td>${ordrList.calOrdrCont}</td>
					<td>${ordrList.calSalsPric}</td>
					<td>${ordrList.appOrdrCont}</td>
					<td>${ordrList.appSalsPric}</td>	
				</tr>
			</c:forEach>				
		</table>
</body>
</html>