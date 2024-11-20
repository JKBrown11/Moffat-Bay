package mbTools;

import java.sql.SQLException;

public class MBValidator {
	
	
	//Validate customer fields
	// if(firstName != null && firstName.matches("/\\w*\\s*\\w*/g"))// This should allow for one or two first names separated by white space.
	
	
	//Validate reservation occurrences per day, limit 2 each type
	
	
	//Validate login attempt
	public boolean validateLogin(String tryEmail, String tryPassword) {
		System.out.println("running validateLogin");
		boolean validEmail = false;
		boolean validPass = false;
		boolean loginSuccess = false;

		//hash user entered password for comparison
		MBEncrypt enc = new MBEncrypt();
		String hashedTest = enc.hashItOut(tryPassword);
		System.out.println(hashedTest);
		
		//validate email
		if (tryEmail.matches("(\\w*)@(\\w*)\\.\\w{3}")) {
			System.out.println("Email passed regEx");
			//validEmail = true;
			//validate password
			try {
				DataAccess valiDAO = new DataAccess();
				//execute select for email:  select hashedPass from customer_data where email='ore54321223o@whitefudge.com';
				String findEmail = "select hashedPass from customer_data where email = '" + tryEmail + "'" ;
				String queryRes = valiDAO.queryEmailpw(findEmail);
				if (queryRes!=null) { 
					validEmail=true;
					System.out.println("queryRes for email not null: "+ queryRes);
				}
				else return false;
				if(queryRes == hashedTest) {
					validPass = true;
					System.out.println("email query returned matching pass hash");
				}
				else return false;
				if (validEmail && validPass) {
					loginSuccess = true;
					System.out.println("Login email and pass valid");
					return loginSuccess;
				}
				else return false;
			} catch (ClassNotFoundException e) {
				System.out.println("Class Not Found Error");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("SQL Error");
				e.printStackTrace();
			}
		}//end if
		
		else return loginSuccess;
		
		return loginSuccess;
		
	}

}
