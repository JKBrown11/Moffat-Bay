<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!--  USE THIS RESERVATION PAGE. KEEPING OTHER UNTIL WE CONFIRM WE DON'T NEED ANY OF THE CODE. I transferred many of the variable names from the 
jsp version because they match the java. This may potentially end up as jsp too, but for now, it's ok. JB 11/20/24 13:11 CST -->

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Moffat Bay Reservation</title>
  <link rel="stylesheet" href="CSS/reservationpage.css">
</head>
<body>
<%@ include file="CSS/nav_menu.html"%>
<!--   <div class="background-image"></div> -->

<!--   <a href="/MoffatBay/MoffatHome.html" class="logo"> -->
<!--     <img src="images-refined/mooseIcon.png" alt="Moffat Bay Logo" width="100"> -->
<!--   </a> -->

<!--   <div class="resort-name">Moffat Bay Resorts</div> -->

<!--   <div class="top-tabs"> -->
<!--     <a href="/MoffatBay/MoffatHome.html">Home</a> -->
<!--     <a href="/MoffatBay/reservation.html">Book A Trip!</a> -->
<!--     <a href="/MoffatBay/lookup.jsp">Look Up</a> -->
<!--     <a href="/MoffatBay/Attractions.jsp">Attractions</a> -->
<!--     <a href="/MoffatBay/ContactUs.jsp">Contact Us</a> -->
<!--     <a href="/MoffatBay/aboutus_jg.html">About Us</a> -->
<!--     <a href="/MoffatBay/login.html">Login</a>   -->
<!--   </div> -->

  <div class="content-section">
    <h1>Reserve Your Stay at Moffat Bay Resort</h1>
    <h3> Remember, you must be logged in to reserve a stay.</h3>
    <form action="/MoffatBay/MBServlet" method="POST" class="reservation-form">
      <div class="form-grid">
        <div class="form-item">
          <label for="roomType">Room Type:</label>
          <select id="roomType" name="roomType" required>
            <option value="2FULL">Standard - 2 Full - $120/night</option>
            <option value="QUEEN">Deluxe - 1 Queen - $135/night</option>
            <option value="2QUEEN">Formal - 2 Queen - $150/night</option>
            <option value="KING">Presidential - 1 King - $160/night</option>
          </select>
        </div>
        <div class="form-item">
            <div class="check-in">
            	<label for="checkInDate">Check-in Date:</label><br>
            	<input type="date" id="checkInDate" name="checkInDate" class="calendar-input" required>
        	</div>

        	<div class="check-out">
            	<label for="checkOutDate">Check-out Date:</label><br>
            	<input type="date" id="checkOutDate" name="checkOutDate" class="calendar-input" required>
        	</div>
        </div>
        <div class="form-item">
          <label for="numNights">Number of guests:</label>
          <input type="number" id="numGuests" name="numGuests" min="1" max="5" required>
        </div>

      </div>
      <input type="hidden" name="myrequest" value="book"/>
      <button type="submit" class="reservation-btn">Request Reservation</button>
    </form>
  </div>

  <a href="/MoffatBay/MoffatHome.html" class="back-link">&larr; Back to Home</a>
  <a href="/MoffatBay/lookup.jsp" class="back-link">&rarr; Look up another reservation</a>
</body>
</html>
