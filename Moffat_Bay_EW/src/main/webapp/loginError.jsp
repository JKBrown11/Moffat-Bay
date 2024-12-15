<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Error</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="CSS/loginpage.css">
</head>

<body class="login">
<%@ include file="CSS/nav_menu.html" %>

	<div class="container" >
		<h2>Something went wrong. See error message below. </h2>
		</br>
		<h3>Error Message:</h3> 
		${errorMessage}
	</div>

</body>
</html>