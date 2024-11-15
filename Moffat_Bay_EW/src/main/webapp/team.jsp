<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="WEB-INF/displayStaff.tld" prefix="ctag" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Moffat Bay Lodge</title>
<style><%@include file="mbStyle.css"%></style>
</head>
<body>
	<div class="header">
	div class header
		<h1>Page heading</h1>
		
	</div>
<!-- I don't want to lose this, because it could be good to reference for syntax later. 		 -->
	<jsp:useBean id="daoBean" class="mbTools.DataAccess" scope="session"/>
	<jsp:useBean id="staff" class="mbTools.CustomerBean" scope="session"/>
	<p>This page is named team but can be changed</p>

	<div class="logo">

		div class logo
		<ctag:displayStaff></ctag:displayStaff><!-- Custom tag, defined in DisplayStaff.java & displayStaff.tld  -->
		
		<a href="index.jsp">Return Home</a>
	</div>

</body>
</html>