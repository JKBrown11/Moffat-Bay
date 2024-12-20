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
<!-- Converting to JSP and using include for one stop editing of Nav links. 
Login transfer to jsp and header element successful, removing old code -->
<%@ include file="CSS/nav_menu.html"%>

<div class="login-container">
  <h2>Login</h2>
  <form class="login-form" action="/MoffatBay/MBServlet" method="post">

    <div style="display: flex; gap: 15px;">

    </div>

    <label for="username">Email/Username:</label>
    <input type="text" id="username" name="email" required/>

    <label for="password">Password:</label>
    <input type="password" id="password" name="regPass" required/>
    
	<input type="hidden" name="myrequest" value="login"/>
    <input class="search-button" type="submit" value="Login"/>
  
  </form>

  <div class="create-account-link">

   
	<br>
    <p>Don't have an account? <a href="/MoffatBay/registration.jsp">Sign Up Here</a></p>

  </div>
</div>



</body>
</html>
