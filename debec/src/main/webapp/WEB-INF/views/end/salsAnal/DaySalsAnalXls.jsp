<!-- 
* 매출 분석 엑셀 다운로드를 위한 화면 JSP입니다.
* 
* history :
*        최재욱, 1.0, 2016/08/22 – 초기 작성
* author : 최재욱
* version : 1.0, 2016/08/22  - 초기 작성
* see : 엑셀 다운로드를 위한 화면
//-->

<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  --%>


<%@ page language="java" contentType="application/vnd.ms-excel; charset=utf-8"%>
<%response.setHeader("Content-Description", "JSP Generated Data");
response.setHeader("Content-Disposition", "attachment;filename=SalsAnal.xls");%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>일별 매출 화면</title>
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
					<font size="5"><b>매출 분석 Data 관리부</b></font>
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
			<th bgcolor="EBF1DE" style="text-align:center">일자</th>
			<th bgcolor="EBF1DE" style="text-align:center">매입액(원)</th>
			<th bgcolor="EBF1DE" style="text-align:center">매출액(원)</th>
			<th bgcolor="EBF1DE" style="text-align:center">순이익(원)</th>
			<th bgcolor="EBF1DE" style="text-align:center">마진율(%)</th>
		</tr>
		<c:forEach items="${SalsList}" var="SalsVO" varStatus="status">	
			<tr>
				<td style="mso-number-format:'yyyy년 mm월 dd일'">${SalsVO.salsDay}</td>
				<td>${SalsVO.purchsPric}</td>
				<td>${SalsVO.salsPric}</td>
				<td>${SalsVO.netinc}</td>
				<td>${SalsVO.margnRat}</td>	
			</tr>
		</c:forEach>			
	</table>
</body>
</html>