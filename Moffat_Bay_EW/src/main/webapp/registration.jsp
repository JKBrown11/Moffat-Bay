<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration Page - Moffat Bay Resorts</title>
<link rel="stylesheet" href="CSS/registrationpage.css">
</head>
<body>

<div class="welcome-message">
<h2>Welcome to Moffat Bay Resorts</h2>
</div>

<div class="top-tabs">
    <a href="/MoffatBay/MoffatHome.html">Home</a>
    <a href="/MoffatBay/reservation.html">Book Now!</a>
    <a href="#tab3">Activities</a>
    <a href="#tab4">Contact Us</a>
    <a href="/MoffatBay/aboutus_jg.html">About Us</a>
    <a href="/MoffatBay/login.html">Login</a>  
</div>

<div class="registration-container">
  <h2>Create an Account</h2>
  <form class="registration-form" action="/MoffatBay/MBServlet" method="POST">
  	  	
  	<label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" placeholder="First" required>
    <span class = "errorMessages">${nameErr}</span>
    
    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" placeholder="Last" required>
  	<span class = "errorMessages">${nameErr}</span>
  	
    <label for="username">Username (Email):</label>
    <input type="email" id="username" name="email" placeholder="Username must be the same as email address" required>
	<span class = "errorMessages">${emailErr}</span>
	
	<label for="phone">Phone Number (enter the -):</label>
    <input type="text" id="phone" name="phone" placeholder="xxx-xxx-xxxx" required>
    <span class = "errorMessages">${phoneErr}</span>
    
    <label for="age">Age:</label>
    <input type="number" id="age" name="age" placeholder="99" required>
    <span class = "errorMessages">${ageErr}</span>
    
    <label for="password">Password:</label>
    <input type="password" id="password" name="regPass" placeholder="Create a password" required>
	<span class = "errorMessages">${regPassErr}</span>
	
    <label for="confirm-password">Confirm Password:</label>
    <input type="password" id="confirm-password" name="repeatPass" placeholder="Confirm your password" required>

	<input type="hidden" name="myrequest" value="register"/>
    <input type="submit" value="Register">
  </form>

  <div class="login-link">
    <p>Already have an account? <a href="/MoffatBay/login.html">Login here</a></p>
  </div>
</div>



</body>
</html>
