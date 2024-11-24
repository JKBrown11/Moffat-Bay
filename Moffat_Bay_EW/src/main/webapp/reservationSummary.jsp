<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="mbStyle.css">
</head>
<meta charset="UTF-8">
<title>Reservation Summary</title>
</head>
<body>
<h1>Reservation Summary page</h1>
${loggedInUser.getFirstName()}, 
${loggedInUser.getLastName()}<br>
Checking in on : ${resRequest.getCheckInDate()}

<!--  Both the reservation and customer objects should be available by el here
and be pretty much loaded. I didn't import passwords from the customer though. Only email.  -->
</body>
</html>