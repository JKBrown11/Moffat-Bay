package mbTools;

import java.sql.SQLException;

public class MBValidator {
	
	
	//Validate customer fields
	// if(firstName != null && firstName.matches("/\\w*\\s*\\w*/g"))// This should allow for one or two first names separated by white space.
	
	
	//Validate reservation occurrences per day, limit 2 each type
	
	
	//Validate login attempt
	public String validateLogin(String tryEmail, String tryPassword) {
		boolean validEmail = false;
		boolean validPass = false;

		
		//validate email
		if (tryEmail.matches("/\\w*@[a-zA-Z]*\\.com/")) {
			validEmail = true;
			//validate password
			try {
				DataAccess valiDAO = new DataAccess();
				
			} catch (ClassNotFoundException e) {
				System.out.println("Class Not Found Error");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("SQL Error");
				e.printStackTrace();
			}
		}//end if
		
		else return "Invalid email address.";
			
		return "still in testing";
	}

}
