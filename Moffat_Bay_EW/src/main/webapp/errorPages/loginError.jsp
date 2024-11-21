<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
<h2>Something went wrong. Reservation not accepted. </h2>
<link rel="stylesheet" href="CSS/Moffat2.css">
</head>

<body>

  <div class="top-tabs">
    <a href="/MoffatBay/MoffatHome.html">Home</a>
    <a href="/MoffatBay/reservation.jsp">Book A Trip!</a>
    <a href="#tab3">Activities</a>
    <a href="#tab4">Contact Us</a>
    <a href="/MoffatBay/login.html">Login</a>   
  </div>

Login Failed<br>
Error Message: 
${errorMessage}

</body>
</html>