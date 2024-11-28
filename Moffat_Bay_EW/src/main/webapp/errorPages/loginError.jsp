<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
<h2>Something went wrong. See error message below. </h2>
<link rel="stylesheet" href="CSS/loginpage.css">
</head>

<body>

  <div class="top-tabs">
    <a href="/MoffatBay/MoffatHome.html">Home</a>
    <a href="/MoffatBay/reservation.jsp">Book A Trip!</a>
    <a href="#tab3">Activities</a>
    <a href="#tab4">Contact Us</a>
    <a href="/MoffatBay/login.html">Login</a>   
  </div>

<div class="container" >
	<br>
	<h3>Error Message:</h3> 
	${errorMessage}
</div>

</body>
</html>