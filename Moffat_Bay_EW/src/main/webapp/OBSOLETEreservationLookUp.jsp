<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/loginpage.css">

</head>
<body>
<div class="top-tabs">
    <a href="/MoffatBay/MoffatHome.html">Home</a>
    <a href="/MoffatBay/reservation.html">Book Now!</a>
    <a href="#tab3">Activities</a>
    <a href="#tab4">Contact Us</a>
    <a href="aboutus_jg.html">About Us</a>
    <a href="/MoffatBay/login.html">Login</a>  
</div>

<div class="login-container">
	<form class = "searchForm" action = "/MoffatBay/MBServlet" method = "POST">
		<label for="searchNumer">Reservation Number: </label>
		<input type="number" name="searchNumber"/>
		<input type="hidden" name="myrequest" value="searchByResNum"/>
		<input type="submit" value="Search"/>
	</form>
	
	<form class = "searchForm" action ="/MoffatBay/MBServlet" method = "POST">
		<label for="searchEmail">User email: </label>
		<input type="email" name="searchEmail"/>
		<input type="hidden" name="myrequest" value="searchByEmail"/>
		<input type="submit" value="Search"/>
	</form>
</div>
</body>
</html>