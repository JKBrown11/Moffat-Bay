<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" %>
<%@taglib uri="WEB-INF/displayReservation.tld" prefix="ctag" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation Lookup - Moffat Bay Resort</title>
    <!-- <link rel="stylesheet" href="CSS/contactus.css"> --> <!-- Reused the CSS from the contact us page -->
    <link rel="stylesheet" href="CSS/moffat2.css">
</head>
<body>
<%@ include file="CSS/nav_menu.html" %>

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
		
		
		

        <div class="footer">
        </div>
    </div>
    <div class = "container">
    	<ctag:displayReservation></ctag:displayReservation>
    	<div class = "errorMessages">
			<h3>${errorMessage}</h3>
		</div>
		
		<!-- User session attributes available of:
        	 CustomerBean loggedInUser
        	 ReservationBean searchResult
        	 String errorMessage 
        	 -->
	</div>
    

</body>
</html>