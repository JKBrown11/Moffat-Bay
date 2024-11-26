package mbTools;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

public class MBValidator {
	
	public MBValidator() {
		System.out.println("Validator Object Created");
	}
	//Validate customer fields
	// if(firstName != null && firstName.matches("/\\w*\\s\\w*/"))// This should allow for one or two first names separated by white space.
	
	
	//Validate reservation occurrences per day, limit 2 each type
	
	/* Validate login attempt 
	 * @param tryEmail - email entered from user on login page
	 * @param tryPassword - password entered from user on login page
	*/
	public boolean validateLogin(String tryEmail, String tryPassword) {
		System.out.println("running validateLogin");
		
		//set default value of flags as false, no one is logged in automatically
		boolean validEmail = false;
		boolean validPass = false;
		boolean loginSuccess = false;

		//hash user entered password for comparison
		MBEncrypt enc = new MBEncrypt();
		String hashedTest = enc.hashItOut(tryPassword);
		System.out.println(hashedTest);
		
		//validate email
		// Is it in the format of an email?
		if (tryEmail.matches("(\\w*)@(\\w*)\\.\\w{3}")) {
			System.out.println("Email passed regEx");
			
			// Does it match with a valid password in the database
			try {
				DataAccess valiDAO = new DataAccess();
				//execute select for email:  select hashedPass from customer_data where email='oreo@whitefudge.com';
				String findEmail = "select hashedPass from customer_data where email = '" + tryEmail + "'" ;
				String queryRes = valiDAO.queryEmailpw(findEmail);
				
				//Did we get the result we expected?
				if (queryRes!=null) { 
					validEmail=true;
					System.out.println("queryRes for email not null: "+ queryRes);
				}
				else {valiDAO.disconn(); return false;}
				
				//does the pw hashed here match the pw hashed in db?
				if(queryRes.equals(hashedTest)) {
					validPass = true;
					System.out.println("email query returned matching pass hash");
				}
				else return false;
				
				//if both are true, the user is logged in
				if (validEmail && validPass) {
					loginSuccess = true;
					System.out.println("Login email and pass valid");
					
					valiDAO.disconn(); 
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
	
	/* 
	 * Validate reservation availability
	 * @param ReservationBean stayRequest - the requested bed and days from the user
	 * @param CustomerBean customer - the user logged in to the session 
	 * */
	public boolean validateStay(ReservationBean stayRequest, CustomerBean customer){
	
	//get all dates, check all?
	
	//create query to search database for repeating room types on specified date. Should return integer value.
	String searchNight = "SELECT COUNT( CASE WHEN check_in_date = '"+ stayRequest.getCheckInDate() + 
			"' AND bed_size = '" + stayRequest.getRoomType() + "' THEN reservation_number END) AS date_and_bed FROM reservations";
		
	//query this string
	try{
		DataAccess valiDAO = new DataAccess();
		int count = valiDAO.makeQueryCount(searchNight);
		//check result for availability ==2, no
		//limit reservations to two per day per room type
		if (count >= 2) {
			//There are no more rooms that day
			System.out.println("Reservation rejected for room availability");
			valiDAO.disconn();
			return false; //validateStay is false
		}
		else {
			valiDAO.disconn();
			return true;
			
			//check success
			
			//forward to reservation summary
		}
		
	}
	catch(ClassNotFoundException e) {
		e.printStackTrace();
	}
	catch(SQLException sql) {
		sql.printStackTrace();
	}
	
	
	return false;
	}
	
	public String confirmReservation(ReservationBean stayRequest, CustomerBean customer) {
		
		//reservation was available
		//login requirement enforced in servlet
		
		//pull user email to link to proper customer account
		String newReservationQuery = "INSERT into mblodge.reservations(email, check_in_date, check_out_date,"
				+ "bed_size, party_size) VALUES ('" + customer.getEmail() + "', '" 
				+ stayRequest.getCheckInDate() + "', '" + stayRequest.getCheckOutDate() + "', '" 
				+ stayRequest.getRoomType() + "', '" + stayRequest.getNumGuests() + "')";
		System.out.println("The insert query is: " + newReservationQuery);
		
		//call update function with valiDAO
		try {
			DataAccess valiDAO = new DataAccess();
			valiDAO.makeReservationUpdate(newReservationQuery);
			return "success";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}

}
