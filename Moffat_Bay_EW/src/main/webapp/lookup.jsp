<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" %>
<%@taglib uri="WEB-INF/displayReservation.tld" prefix="ctag" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation Lookup - Moffat Bay Resort</title>
    <link rel="stylesheet" href="CSS/contactus.css"> <!-- Reused the CSS from the contact us page -->
</head>
<body>
    <header>
        <h1>Moffat Bay Resort</h1>
    </header>
    <nav class="navbar">
    <a href="/MoffatBay/MoffatHome.html">Home</a>
    <a href="/MoffatBay/reservation.jsp">Book A Trip!</a>
    <a href="/MoffatBay/lookup.jsp">Look Up</a>
    <a href="#tab3">Activities</a>
    <a href="/MoffatBay/ContactUs.html">Contact Us</a>
    <a href="/MoffatBay/aboutus_jg.html">About Us</a>
    <a href="/MoffatBay/login.html">Login</a> 
    </nav>
    <div class="container">
        <h1>Look Up Reservation</h1>
        
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
		
		<div class = "errorMessages">
			<h3>${errorMessage}</h3>
		</div>
		

        <div class="footer">
        </div>
    </div>
    <div class = "container">
			<table class="displayRes">
			<!-- User session attributes available of: 
			 CustomerBean loggedInUser  
			 ReservationBean searchResult
			 String errorMessage -->
				<tr>
					<td>Guest Name</td>
					<td>Contact</td>
					<td>Reserved Number</td>
					<td>Check-In</td>
					<td>Check-Out</td>
					<td>Room Type</td>
					
				</tr>
				
				<ctag:displayReservation></ctag:displayReservation>


			</table>
		</div>
    
    <!-- I removed your form because I already had one that
    matched my logic and separated them. Rather than one form, 
    I broke it up and required login to search in process but the CSS is lovely. -JB-->
<!--         <form action="insert our php file" method="post"> be sure to add our PHP file here -->
<!--             <label for="reservation_id">Reservation ID (Optional):</label> -->
<!--             <input type="text" id="reservation_id" name="reservation_id" placeholder="Enter your reservation ID"> -->
            
<!--             <label for="email">Email Address:</label> -->
<!--             <input type="email" id="email" name="email" placeholder="Enter your email address" required> -->
            
<!--             <label for="last_name">Last Name:</label> -->
<!--             <input type="text" id="last_name" name="last_name" placeholder="Enter your last name" required> -->
            
<!--             <button type="submit">Lookup Reservation</button> -->
<!--         </form> -->
</body>
</html>