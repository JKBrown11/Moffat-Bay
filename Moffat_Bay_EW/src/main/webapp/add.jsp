<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.Date" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Moffat Bay Lodge</title>
<style><%@include file="mbStyle.css"%></style>
</head>
<body>
	<div class="header">
	div class header
		<h3>Page Heading </h3>
	
	</div>

	<div class="logo">
	div class logo
		<h3>Moffat Bay</h3><br/>
		<div class=form>
			
			<jsp:useBean id="formBean" class="mbTools.CustomerBean" scope="session"></jsp:useBean>
			
			<form action="addTeam.html" method="post">
				
				<p>Please enter <b>all</b> information and click submit to register as a new customer.</p>
				First name: <input type="text" name="firstName"  /><br/>
				Last name: <input type="text" name="lastName"  /><br/>
				Email: <input type="text" name="email"  /><br/>
				Phone: <input type="text" name="phone" value="xxx-xxx-xxxx"  /><br/>
				Password: <input type="password" name="regPass"  /><br/>
				Repeat Password: <input type="password" name="repeatPass"  /><br/>
			
				<jsp:setProperty name="formBean" property="*"/>
				<input type="submit" value="Submit"/>
				
			</form>
			
			
		</div>	<!--  end class form -->
		
	</div> <!-- end of class logo -->
	
</body>
</html>