<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="WEB-INF/displayStaff.tld" prefix="ctag" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Craftmart Team</title>
<style><%@include file="craftmartStyle.css"%></style>
</head>
<body>
	<div class="header">
		<h1>Craftmart team members</h1>
		
	</div>
		
	<jsp:useBean id="daoBean" class="craftmartTools.DataAccess" scope="session"/>
	<jsp:useBean id="staff" class="craftmartTools.EmployeeBean" scope="session"/>
	<p>All employees</p>

	<div class="logo">
<%-- 	#wrong ${daoBean.queryRes[0].firstName} nothing --%>
<%-- 	#wrong <c:forEach var="${staff}" items="${printlist}"> --%>
<!-- 	This doesnt work ft. items doesn't accept runtime expressions 
		lets use the custom tag so we can write it all in java the easy way.-->
		
		<ctag:displayStaff></ctag:displayStaff><!-- tada! -->
		
		<a href="index.jsp">Return Home</a>
	</div>

</body>
</html>