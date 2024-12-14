<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Page - Moffat Bay Resorts</title>
<!-- <link rel="stylesheet" href="CSS/loginpage.css"> -->
<link rel="stylesheet" href="CSS/moffat2.css"/>
</head>
<body class = "login">
<!-- Converting to JSP and using include for one stop editing of Nav links.  -->
<%@ include file="CSS/nav_menu.html"%>
<!-- <a href="/MoffatBay/MoffatHome.html" class="logo"> -->
<!--     <img src="images-refined/mooseIcon.png" alt="Moffat Bay Logo" width="100"> -->
<!-- </a> -->
<!-- <div class="welcome-message"> -->
<!-- <h2>Welcome to Moffat Bay Resorts</h2> -->
<!-- </div> -->

<!-- <div class="top-tabs"> -->
<!--     <a href="/MoffatBay/MoffatHome.html">Home</a> -->
<!--     <a href="/MoffatBay/reservation.html">Book A Trip!</a> -->
<!--     <a href="/MoffatBay/lookup.jsp">Look Up</a> -->
<!--     <a href="/MoffatBay/Attractions.html">Attractions</a> -->
<!--     <a href="/MoffatBay/ContactUs.jsp">Contact Us</a> -->
<!--     <a href="/MoffatBay/aboutus_jg.html">About Us</a> -->
<!--     <a href="/MoffatBay/login.html">Login</a>    -->
<!-- </div> -->




<div class="login-container">
  <h2>Login</h2>
  <form class="login-form" action="/MoffatBay/MBServlet" method="post">
<!--     <label for="reservation-id">Reservation ID:</label> -->
    <div style="display: flex; gap: 15px;">
<!--       <input type="text" id="reservation-id" name="reservation-id" placeholder="Enter Reservation ID" required> -->
<!--       <button type="button" class="search-button">Search</button> -->
    </div>

    <label for="username">Email/Username:</label>
    <input type="text" id="username" name="email" required/>

    <label for="password">Password:</label>
    <input type="password" id="password" name="regPass" required/>
    
	<input type="hidden" name="myrequest" value="login"/>
    <input type="submit" value="Login"/>
  
  </form>

  <div class="create-account-link">

   
	<br>
    <p>Don't have an account? <a href="/MoffatBay/registration.jsp">Sign Up Here</a></p>

  </div>
</div>



</body>
</html>