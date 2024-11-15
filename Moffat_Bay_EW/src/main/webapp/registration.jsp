<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration Page - Moffat Bay Resorts</title>
<link rel="stylesheet" href="registrationpage.css">
</head>
<body>

<div class="welcome-message">
  Welcome to Moffat Bay Resorts
</div>

<div class="registration-container">
  <h2>Create an Account</h2>
  <form class="registration-form" action="/MoffatBay/MBServlet" method="post">
  	  	
  	<label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" placeholder="First" required>
    
    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" placeholder="Last" required>
  	
    <label for="username">Username (Email):</label>
    <input type="email" id="username" name="email" placeholder="Username must be the same as email address" required>

	<label for="phone">Phone Number (enter the -):</label>
    <input type="text" id="phone" name="phone" placeholder="xxx-xxx-xxxx" required>
    
    <label for="age">Age:</label>
    <input type="number" id="age" name="age" placeholder="99" required>
    
    <label for="password">Password:</label>
    <input type="password" id="password" name="regPass" placeholder="Create a password" required>

    <label for="confirm-password">Confirm Password:</label>
    <input type="password" id="confirm-password" name="repeatPass" placeholder="Confirm your password" required>

	<input type="hidden" name="myrequest" value="register"/>
    <input type="submit" value="Register">
  </form>

  <div class="login-link">
    <p>Already have an account? <a href="/login">Login here</a></p>
  </div>
</div>

<div class="top-tabs">
  <a href="#">Home</a>
  <a href="#">About Us</a>
  <a href="#">Contact</a>
</div>

</body>
</html>
