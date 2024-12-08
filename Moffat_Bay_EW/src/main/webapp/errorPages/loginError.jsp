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
    <a href="/MoffatBay/reservation.html">Book A Trip!</a>
    <a href="/MoffatBay/lookup.jsp">Look Up</a>
    <a href="#tab3">Activities</a>
    <a href="/MoffatBay/ContactUs.html">Contact Us</a>
    <a href="/MoffatBay/aboutus_jg.html">About Us</a>
    <a href="/MoffatBay/login.html">Login</a>   
  </div>

<div class="container" >
	<br>
	<h3>Error Message:</h3> 
	${errorMessage}
</div>

</body>
</html>