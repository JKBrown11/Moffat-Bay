<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Moffat Bay Lodge - Book Your Trip</title>
    <style><%@include file="mbStyle.css"%></style>
    
</head>
<body>

<header>
    <h1>Moffat Bay Lodge</h1>
</header>

<div class="nav">
    <a href="#">Home</a>
    <a href="#">Activities</a>
    <a href="#">Book Your Trip</a>
    <a href="#">Trip Look Up</a>
</div>

<div class="container">
    <h2>Book Your Trip!</h2>
    <p>You must have an account to book a trip. You will be prompted to login or register as necessary before your trip is confirmed.</p>
    
    <form action="bookingServlet" method="POST">
        <div>
            <label for="checkInDate">Check-in Date:</label><br>
            <input type="date" id="checkInDate" name="checkInDate" class="calendar-input" required>
        </div>

        <div>
            <label for="checkOutDate">Check-out Date:</label><br>
            <input type="date" id="checkOutDate" name="checkOutDate" class="calendar-input" required>
        </div>
        
        <div>
            <label for="guests">Number of Guests:</label><br>
            <select id="guests" name="guests" class="dropdown" required>
                <option value="">Select number of guests</option>
                <option value="1">1 Guest</option>
                <option value="2">2 Guests</option>
                <option value="3">3 Guests</option>
                <option value="4">4+ Guests</option>

            </select>
        </div>

        <div class="bed-options">
            <h3>Select Your Room Type:</h3>
            <label class="bed-option">
                <input type="radio" name="bedType" value="2 Full Beds" required> 
                2 Full Beds<br>$120 / Night
            </label>
            <label class="bed-option">
                <input type="radio" name="bedType" value="Queen Bed" required>
                Queen Bed<br>$135 / Night
            </label>
            <label class="bed-option">
                <input type="radio" name="bedType" value="2 Queen Beds" required>
                2 Queen Beds<br>$150 / Night
            </label>
            <label class="bed-option">
                <input type="radio" name="bedType" value="King Bed" required>
                King Bed<br>$160 / Night
            </label>
        </div>

        <button type="submit" style="padding: 10px 20px; font-size: 16px; margin-top: 20px;">Book Now</button>
    </form>
</div>

<footer>
    <p>Moffat Bay Lodge, 123 Moffat Street</p>
    <p>Need Directions?</p>
	<img src="/main/webapp/WEB-INF/images/cover.jpg" alt="Logo" />
</footer>

</body>
</html>