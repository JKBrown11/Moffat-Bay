<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="CSS/reservationpage.css">
<title>Reservation Summary</title>
</head>
<body>
 	<div class="background-image"></div>
 	<div class="resort-name">Moffat Bay Resorts</div>
 
   	<div class="top-tabs">
    <a href="/MoffatBay/MoffatHome.html">Home</a>
    <a href="/MoffatBay/reservation.html">Book A Trip!</a>
    <a href="/MoffatBay/lookup.jsp">Look Up</a>
    <a href="#tab3">Activities</a>
    <a href="/MoffatBay/ContactUs.jsp">Contact Us</a>
    <a href="/MoffatBay/aboutus_jg.html">About Us</a>
    <a href="/MoffatBay/login.html">Login</a>   
  	</div>
  	<div class= "form-grid">
	<div class="content-section">
		<h2>Reservation Summary</h2> 
		Member: ${loggedInUser.getFirstName()}, ${loggedInUser.getLastName()}<br>
		Checking in on : ${resRequest.getCheckInDate()}<br>
		Checking out on: ${resRequest.getCheckOutDate()}<br>
		Bed summary: ${resRequest.getRoomType()}<br>

	<form action="/MoffatBay/MBServlet" method="POST" class="reservationSummary">
		<input type="hidden" name="myrequest" value="confirm" />
		<input type="submit" name="confirm-btn" value="Confirm" />
	</form>
	
	<form action="/MoffatBay/MBServlet" method="POST" class="reservationSummary">
		<input type="hidden" name="myrequest" value="cancel" />
		<input type="submit" name="cancel-btn" value="Cancel" />
	</form>
	${successMsg};
	
	</div> 
	</div>


<!--  Both the reservation and customer objects should be available by el here
and be pretty much loaded. I didn't import passwords from the customer though. Only email.  -->
</body>
</html>