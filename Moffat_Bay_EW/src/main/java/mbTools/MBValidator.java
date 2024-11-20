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
				if(queryRes.equals(hashedTest)) {
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
	
	public boolean validateStay(ReservationBean stayRequest, CustomerBean customer){
	
	//get all dates, check all
	
	//create query to search database. Should return integer value.
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
			return false; //validateStay is false
		}
		else {
			//reservation is available
			//login requirement enforced in servlet
			
			//pull user email to link to proper customer account
			String newReservationQuery = "INSERT into mblodge.reservations(email, check_in_date, check_out_date,"
					+ "bed_size, party_size) VALUES ('" + customer.getEmail() + "', '" 
					+ stayRequest.getCheckInDate() + "', '" + stayRequest.getCheckOutDate() + "', '" 
					+ stayRequest.getRoomType() + "', '" + stayRequest.getNumGuests() + "')";
			System.out.println("The insert query is: " + newReservationQuery);
			
			//call update function with valiDAO
			valiDAO.makeReservationUpdate(newReservationQuery);
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

}
