<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.Date" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Onboarding Form</title>
<style><%@include file="craftmartStyle.css"%></style>
</head>
<body>
	<div class="header">
		<h3>New Employee Information </h3>
	
	</div>

	<div class="logo">
		<h3>Craftmart</h3><br/>
		<div class=form>
			
			<jsp:useBean id="formBean" class="craftmartTools.EmployeeBean" scope="session"></jsp:useBean>
			
			<form action="addTeam.html" method="post">
				<!-- addTeam.html  IS WHAT WORKS, TESTING OTHER  -->
				<p>Please enter <b>all</b> employee information and click submit.</p>
				First name: <input type="text" name="firstName"  /><br/>
				Last name: <input type="text" name="lastName"  /><br/>
				Email: <input type="text" name="email"  /><br/>
				Phone: <input type="text" name="phone" value="xxx-xxx-xxxx"  /><br/>
				Street Address: <input type="text" name="streetAddress"  /><br/>
				City: <input type="text" name="city"  />
				State: <input type="text" name="state" value="NY"/><br/>
				ZIP Code:<input type="text" name="zip"  /><br/>
				Hire Date: <input type="date" name="hireDate" value="YYYY-MM-DD"  /><br/>
				Position: <input type="text" name="position"  /><br/><br/>
				
				<jsp:setProperty name="formBean" property="*"/>
				<input type="submit" value="Submit"/>
				
			</form>
			
			
		</div>	<!--  end class form -->
		
	</div> <!-- end of class logo -->
	
</body>
</html>