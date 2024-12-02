package mbTools;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MBServlet
 */
@WebServlet //("/MoffatBay/")
public class MBServlet extends HttpServlet {

	private static final long serialVersionUID = 1L; //creates serializable

    //default constructor
    public MBServlet() {
        //System.out.println("Servlet CM ran");
       //testing point
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/*
	 * Method to handle post requests.
	 * */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Setting up some reusable objects for servlet processing 
		String errorMessage = "";
		String successMsg = "";
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		MBValidator validator = new MBValidator();
		
		
		//Utilize hidden inputs to denote post request source
		String from = request.getParameter("myrequest");
		//If there is a request value received:
		if (from != null) {
			switch (from){

			//and the request value is register
/* REGISTER*/
			case "register":
				
				//pull the data from the form, using an object.
				CustomerBean registerNew = new CustomerBean();
				System.out.println("empty customer created");
				//MBValidator validated = new MBValidator();
				
				//Store values to inspect before using
				String rawFirstName = request.getParameter("firstName");
				String rawLastName = request.getParameter("lastName");
				String rawEmail = request.getParameter("email");
				String rawPhone = request.getParameter("phone");
				int intAge = Integer.parseInt(request.getParameter("age"));
				String rawRegPass = request.getParameter("regPass");
				System.out.println("values retreived from form");

				boolean firstNameFlag = false;
				boolean lastNameFlag = false;
				boolean email = false;
				boolean phone= false;
				boolean age = false;
				boolean regPass = false;
				String nameErr = "";
				String emailErr = "";
				String phoneErr= "";
				String ageErr = "";
				String regPassErr = "";
				
				//Input sani moved to Validator class as of 12/2
/*first name*/	

				firstNameFlag = validator.checkFirstName(rawFirstName);
				if (firstNameFlag) {
					registerNew.setFirstName(rawFirstName);
				}
				else {
					nameErr= "First and last names should only contain characters that occur in words";
					session.setAttribute("nameErr", nameErr);
				}
				

/*last name*/	lastNameFlag = validator.checkLastName(rawLastName);
				if (lastNameFlag) {
					registerNew.setLastName(rawLastName);
				}
				else {
					nameErr= "First and last names should only contain characters that occur in words";
					session.setAttribute("nameErr", nameErr);
				}


/*email*/		email = validator.checkEmailInput(rawEmail);
				if (email) {
					registerNew.setEmail(rawEmail);
				}
				else {
					emailErr = "Your email should follow a traditional word@word.com/edu/gov pattern";
					session.setAttribute("emailErr", emailErr);
				}

				//Check if phone looks normal containing only numbers and dashes
/*phone*/		phone = validator.checkPhoneInput(rawPhone);
				if (phone) {
					registerNew.setPhone(rawPhone);
				}
				else {
					phoneErr= "Your phone number should be entered in the patter of XXX-XXX-XXXX";
					session.setAttribute("phoneErr", phoneErr);
				}

				//Only allow accounts for 18 or older, excepting irrationally large ages.
				// Only used once, so no function created. 
/*age*/			if ( intAge >= 18 && intAge <= 105) {
					registerNew.setAge(intAge);
					age = true;
					System.out.println("age true");
				}
				else {
					ageErr = "We are unable to process an account due to your age.";
					session.setAttribute("ageErr", ageErr);
				}

				//Verify password meets standard before conversion to show err.
/*regPass*/		regPass = validator.checkRegPass(rawRegPass);
				if (regPass) {
					registerNew.setRegPass(rawRegPass);
				}
				else {
					regPassErr = "Your password must contain at least one upper case, one lower case, and one number character in addition to being 8 or more characters.";
					session.setAttribute("regPassErr", regPassErr);
				}

				//Data sanitized. Check our flags.
				//if all good, make an account.
/*All true*/	if (firstNameFlag && lastNameFlag && email && phone && age && regPass) {
					System.out.println("All flaggs true");
					//System.out.println("Form values received. ");
					////testing point
					try {
						//This uses the DataAccess class to load the driver, and connect.
						DataAccess daoBean = new DataAccess();
						daoBean.addBeans(registerNew); //This inserts a new customer to db.
					}
					catch (ClassNotFoundException e) {
						e.printStackTrace();}
					catch (SQLException e) {
						e.printStackTrace();}

					System.out.println("customer added to db");

					rd = request.getRequestDispatcher("/login.html");
					rd.forward(request, response);

					break;
				}
				//Else, return to registration with error messages now appearing.
				else {
					
					rd = request.getRequestDispatcher("/registration.jsp");
					rd.forward(request, response);

					break;
				}

//---------------------------------------------------------------

				//and the request message is login
/*LOGIN*/	case "login":

				// get values from form
				String userEmail = request.getParameter("email");
				String unhashed= request.getParameter("regPass");
				System.out.println("The value of unhashed is: " + unhashed);

				//Check to see if they work
				//MBValidator vali = new MBValidator();
				boolean success = validator.validateLogin(userEmail, unhashed);
				System.out.println("Results of login: " + success);

				//if login didn't work
				if (!success) {
					//Login failed
					//set session error message to retrieve on error page
					errorMessage = "Your login was not successful due to incorrect email or password.";
					session.setAttribute("errorMessage", errorMessage);
					rd = request.getRequestDispatcher("errorPages/loginError.jsp");
					rd.forward(request, response);

					break;
				}
				else {//login successful, add user to session as bean
					CustomerBean loggedInUser = new CustomerBean();
					loggedInUser.setEmail(userEmail);


					try {
						DataAccess loginDAO = new DataAccess();
						loggedInUser = loginDAO.loadCustomer(loggedInUser);
					}
					catch(Exception e) {
						e.printStackTrace();}

					//set attribute as customer bean for reservation summary
					session.setAttribute("loggedInUser", loggedInUser);

					rd = request.getRequestDispatcher("/reservation.html");
					rd.forward(request, response);

					//System.out.println("forwarded to reservation.html");
					break;
				}//end else

//-------------------------------------------------------------------------				
				
/*BOOK*/	case "book":
				//System.out.println("Case acknowledged book request");
				
				//This session value is set when a user logs in. If it has null values, no-one has logged in.
				CustomerBean customer = (CustomerBean) session.getAttribute("loggedInUser");
				try {
					if (customer.getEmail()== null) {//no email saved in session for user

					}
				}
				catch (NullPointerException e) {
					e.printStackTrace();
					//error, send to login
					errorMessage = "You must be logged in to book a trip.";
					session.setAttribute("errorMessage", errorMessage);
					rd = request.getRequestDispatcher("errorPages/loginError.jsp");
					rd.forward(request, response);
					break;
				}
				catch (Exception ex) {ex.printStackTrace();}

				//If the user is available in the session, collect reservation information
				if (customer != null) {

					String roomType = request.getParameter("roomType");
					String checkIn = request.getParameter("checkInDate");
					String checkOut = request.getParameter("checkOutDate");
					Integer numGuests = Integer.parseInt(request.getParameter("numGuests"));

					ReservationBean resRequest = new ReservationBean();
					resRequest.setRoomType(roomType);
					resRequest.setCheckInDate(checkIn);
					resRequest.setCheckOutDate(checkOut);
					resRequest.setNumGuests(numGuests);

					System.out.println("Reservation bean ready for checking");

					//Validate reservation dates for availability
					
					boolean available = validator.validateStay(resRequest, customer);


					if (available) {
						//send to reservation confirmation
						session.setAttribute("resRequest", resRequest);
						rd = request.getRequestDispatcher("reservationSummary.jsp");
						rd.forward(request, response);
						break;
					}
					else {
						//send back to reservation page with error message
						errorMessage = "The bed you selected is not available for the beginning of your stay.";
						session.setAttribute("errorMessage", errorMessage);
						rd = request.getRequestDispatcher("errorPages/loginError.jsp");
						rd.forward(request, response);
						break;
					}}
				else {
					errorMessage = "You must be logged in to make a reservation.";
					session.setAttribute("errorMessage", errorMessage);
					rd = request.getRequestDispatcher("errorPages/loginError.jsp");
					rd.forward(request, response);
					break;
				}

//--------------------------------------------------------------------------				

/*CONFIRM*/	case "confirm":
				System.out.println("summary confirmed ");
				//pull in the reservation & guest info
				session = request.getSession();
				ReservationBean stayRequest = (ReservationBean) session.getAttribute("resRequest");
				CustomerBean loggedInUser = (CustomerBean) session.getAttribute("loggedInUser");
				//actually make reservation
				
				String resultMessage = validator.confirmReservation(stayRequest, loggedInUser);
				if(resultMessage.contains("error")){
					//Alert customer reservation not made
					//and break before success message
					break;
				}
				
				successMsg= "<div class=\"alert\">"
						+ "  <span class=\"closeAlert\" onclick=\"this.parentElement.style.display='none';\">&times;</span>"
						+ "  Success!"
						+ "</div>";
				session.setAttribute("successMsg", successMsg);
				//display success message somewhere?
				rd = request.getRequestDispatcher("reservationSummary.jsp");
				rd.forward(request, response);
				break;

//------------------------------------------------------------------				
				
/*CANCEL*/	case "cancel":

				//redirect to reservation screen
				rd = request.getRequestDispatcher("reservation.html");
				rd.forward(request, response);
				break;
				
//-------------------------------------------------------------------
				
/*SRCH*/	case "searchByResNum":
/*byRes#*/		//errorMessage = "";
				CustomerBean searchUser = (CustomerBean) session.getAttribute("loggedInUser");
				if (searchUser==null) {
					//redirect to errorpage
					errorMessage = "You must be logged in to search for your reservation.";
					session.setAttribute("errorMessage", errorMessage);
					rd = request.getRequestDispatcher("lookup.jsp");
					rd.forward(request, response);
					break;
				}
				else {
					//search for reservation with loggedin user and res number
					// with DAO  
					int resNum = Integer.parseInt(request.getParameter("searchNumber"));
					if (resNum <= 0) {
						errorMessage = "You must enter a valid reservation number.";
						session.setAttribute("errorMessage", errorMessage);
						rd = request.getRequestDispatcher("lookup.jsp");
						rd.forward(request, response);
						break;
					}
					try {
						DataAccess searchDAO = new DataAccess();
						ReservationBean searchResult = searchDAO.searchReservationNumber(resNum, searchUser);
						//String testEmail = searchResult.getResOwnerEmail();
						if (searchUser.getEmail().equals(searchResult.getResOwnerEmail())) {//If emails match
							session.setAttribute("searchResult", searchResult);//Make the results available
							session.setAttribute("insertTagWhenReady", "<ctag:displayReservation></ctag:displayReservation>");
							rd = request.getRequestDispatcher("lookup.jsp");
							rd.forward(request, response);
							break;
						}
						else {
							errorMessage = "The email you logged in with doesn't match this reservation.";
							session.setAttribute("errorMessage", errorMessage);
							rd = request.getRequestDispatcher("lookup.jsp");
							rd.forward(request, response);
							break;
						}
						
					}catch (Exception e) {
						e.printStackTrace();
						errorMessage = "That search had faulty results.";
						session.setAttribute("errorMessage", errorMessage);
						rd = request.getRequestDispatcher("lookup.jsp");
						rd.forward(request, response);
						break;
					}
				}//end else
				
//------------------------------------------------------------------------				
				
/*SRCH*/	case "searchByEmail":
/*by email*/	String errorM = "";
				CustomerBean searcher = (CustomerBean) session.getAttribute("loggedInUser");
				String enteredEmail = request.getParameter("searchEmail");
				if(searcher==null) {
					errorM = "You must be logged in to search for a reservation.";
					session.setAttribute("errorMessage", errorM);
					rd = request.getRequestDispatcher("lookup.jsp");
					rd.forward(request, response);
					break;
				}
				else if (enteredEmail.equals(searcher.getEmail())) {
					//ok to search					System.out.println("search matches login");
					try {
						DataAccess emailSrchDAO = new DataAccess();
						ArrayList<ReservationBean> userRezs = emailSrchDAO.searchUserEmail(searcher);
						System.out.println("all bean results added, back to servlet");
						session.setAttribute("userRezs", userRezs);
						System.out.println("bean list set in session");
						rd=request.getRequestDispatcher("lookup.jsp");
						rd.forward(request, response);
					}
					catch(Exception e) {e.printStackTrace(); break;}
				}
				else {
					errorM = "You can only look at your own reservations";
					session.setAttribute("errorMessage", errorM);
					rd = request.getRequestDispatcher("lookup.jsp");
					rd.forward(request, response);
					break;
				}
				
//-------------------------------------------------------------------------
			case "contactUs":
				
				//ALL UNFILTERED
				//Consider moving register logic to validator as intended to
				// reuse here
				String rawName = request.getParameter("name");
				String rawConEmail= request.getParameter("email");
				String rawConPhone = request.getParameter("phone");
				String rawResNum = request.getParameter("reservation");
				String rawSubj = request.getParameter("subject");
				String rawMess = request.getParameter("message");
				
				//Create message class/object-check
				//Create message table in db & Moffat- check 
				//filter filter- not check, need to migrate top section
				
				//set a new object to give to DAO for easy inputs
				MessageBean newMess = new MessageBean();
				if (validator.checkFirstName(rawName)) {
					newMess.setFilteredFullName(rawName);
				}	
				if (validator.checkEmailInput(rawConEmail)) {
					newMess.setFilteredEmail(rawConEmail);
				}	
				if(validator.checkPhoneInput(rawConPhone)) {
					newMess.setFilteredPhone(rawConPhone);
				}
				newMess.setFilteredResNum(rawResNum);
				newMess.setFilteredSubj(rawSubj);
				newMess.setFilteredMess(rawMess);
				
				
				//fails if there is a ';' in the message body due to non-escape
				try {
					DataAccess messageDAO = new DataAccess();
					String summary = messageDAO.addMessage(newMess);
					if (summary.contains("error")) {
						//error
						session.setAttribute("errorMessage", "We were unable to submit your message.");
						
					}
					else {
						//successful submit
						session.setAttribute("successMsg", "Message received! We will reach out as soon as possible.");
					}
				}
				catch (Exception e) {
					e.printStackTrace();
					session.setAttribute("errorMessage", "We are unable to process the request at this time.");
				}
				rd = request.getRequestDispatcher("ContactUs.jsp");
				rd.forward(request, response);
				break;
				
			}//end switch
		}//end ifs



	}//end doPost
}//end Class








