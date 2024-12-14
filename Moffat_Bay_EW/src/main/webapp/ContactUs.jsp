<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us - Moffat Bay Resort</title>
    <link rel="stylesheet" href="CSS/moffat2.css">
</head>
<body>
<%@ include file="CSS/nav_menu.html"%>
<!-- <a href="/MoffatBay/MoffatHome.html" class="logo"> -->
<!--     <img src="images-refined/mooseIcon.png" alt="Moffat Bay Logo" width="100"> -->
<!-- </a> -->
<!--     <header> -->
<!--         <h1>Moffat Bay Resort</h1> -->
<!--     </header> -->
<!--     <nav class="navbar"> -->
<!--     <a href="/MoffatBay/MoffatHome.html">Home</a> -->
<!--     <a href="/MoffatBay/reservation.html">Book A Trip!</a> -->
<!--     <a href="/MoffatBay/lookup.jsp">Look Up</a> -->
<!--     <a href="/MoffatBay/Attractions.html">Attractions</a> -->
<!--     <a href="/MoffatBay/ContactUs.html">Contact Us</a> -->
<!--     <a href="/MoffatBay/aboutus_jg.html">About Us</a> -->
<!--     <a href="/MoffatBay/login.html">Login</a>  -->
<!--     </nav> -->
    <div class="container">
        <h1>Contact Us</h1>
        <form action="/MoffatBay/MBServlet" method="post">
            <label for="name">Full Name:</label>
            <input type="text" id="name" name="fullname" placeholder="Enter your full name" required>
            
            <label for="email">Email Address:</label>
            <input type="email" id="email" name="email" placeholder="Enter your email address" required>
            
            <label for="phone">Phone Number (Optional):</label>
            <input type="text" id="phone" name="phone" placeholder="Enter your phone number">
            
            <label for="reservation">Reservation ID (Optional):</label>
            <input type="number" id="reservation" name="reservation" placeholder="Enter your reservation ID">
            
            <label for="subject">Subject:</label>
            <input type="text" id="subject" name="subject" placeholder="Enter the subject" required>
            
            <label for="message">Message:</label>
            <textarea id="message" name="message" rows="5" placeholder="Enter your message here" required></textarea>
            
            <input type="hidden" name="myrequest" value="contactUs"/>
            <input type="submit" value="Submit"/>
        </form>
        
        <div class= "response">
        	${errorMessage}
        	${successMsg}
        </div>
        <div class="footer"></div>
    </div>
</body>
</html>