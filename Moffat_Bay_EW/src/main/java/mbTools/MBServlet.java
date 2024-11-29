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

		//Utilize hidden inputs to denote post request source
		String from = request.getParameter("myrequest");

		//If there is a request value received:
		if (from != null) {
			switch (from){

			//and the request value is register
/* REGISTER*/
			case "register":
				System.out.println("servlet case acknowledged as register");
				//the customer beans do  not have input sani as of 11/14/24 1:44 CST
				//pull the data from the form, using an object.
				CustomerBean registerNew = new CustomerBean();
				System.out.println("empty customer created");

				//Adding input sani 11/25 1:42pm
				//Store values to inspect before using
				String rawFirstName = request.getParameter("firstName");
				System.out.println("rawFirstName pulled as: " + rawFirstName);
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
				System.out.println("flags set to false for initial, errors blank until a failure");

				String alphaOnly = "[a-zA-Z']+"; //abc only, verified on regEx101
				String tenDigitPhone = "[0-9]{10}";
				String dashedPhone = "(?:[0-9]{3}-[0-9]{3}-[0-9]{4})"; //verified on regEx101 to accept 123-234-5678 format string
				String emailSyntax = "(\\w*)@(\\w*)\\.(\\w{3})";
				//regEx for password min 8chars, lower case,  upper case, and number
				String pwQualifiers="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$";

				//Validate name fields only contain alpha word characters.
/*first name*/	if (rawFirstName.matches(alphaOnly)) {
					System.out.println("raw input matched regex for first name");
					registerNew.setFirstName(rawFirstName);
					firstNameFlag = true;
					System.out.println("first name true");
				}
				else {
					nameErr= "First and last names should only contain characters that occur in words";
					HttpSession session = request.getSession();
					session.setAttribute("nameErr", nameErr);
				}

/*last name*/	if (rawLastName.matches(alphaOnly)) {
					System.out.println("raw input matched regex for last name");
					registerNew.setLastName(rawLastName);
					lastNameFlag = true;
					System.out.println("last name true");
				}
				else {
					nameErr= "First and last names should only contain characters that occur in words";
					HttpSession session = request.getSession();
					session.setAttribute("nameErr", nameErr);
				}


				//Validate email resembles traditional format
/*email*/		if (rawEmail.matches(emailSyntax)) {
					System.out.println("raw input matched regex for email");
					registerNew.setEmail(rawEmail);
					email = true;
					System.out.println("email true");
				}
				else {
					emailErr = "Your email should follow a traditional word@word.com/edu/gov pattern";
					HttpSession session = request.getSession();
					session.setAttribute("emailErr", emailErr);
				}

				//Check if phone looks normal containing only numbers and dashes
/*phone*/		if (rawPhone.matches(tenDigitPhone) || rawPhone.matches(dashedPhone)) {
					registerNew.setPhone(rawPhone);
					phone = true;
					System.out.println("phone true");
				}
				else {
					phoneErr= "Your phone number should be entered in the patter of XXX-XXX-XXXX";
					HttpSession session = request.getSession();
					session.setAttribute("phoneErr", phoneErr);
				}

				//Only allow accounts for 18 or older, excepting irrationally large ages.
/*age*/			if ( intAge >= 18 && intAge <= 105) {
					registerNew.setAge(intAge);
					age = true;
					System.out.println("age true");
				}
				else {
					ageErr = "We are unable to process an account due to your age.";
					HttpSession session = request.getSession();
					session.setAttribute("ageErr", ageErr);
				}

				//Verify password meets standard before conversion to show err.
/*regPass*/		if (rawRegPass.matches(pwQualifiers)) {
					registerNew.setRegPass(rawRegPass);
					regPass = true;
					System.out.println("regPass true");
				}
				else {
					regPassErr = "Your password must contain at least one upper case, one lower case, and one number character in addition to being 8 or more characters.";
					HttpSession session = request.getSession();
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

					RequestDispatcher rd = request.getRequestDispatcher("/login.html");
					rd.forward(request, response);

					System.out.println("forwarded to login.html");
					break;
				}
				//Else, return to registration with error messages now appearing.
				else {
					System.out.println("At least one value failed");
					System.out.println("Value of Flags: ");//email age phone regpass
					System.out.println("firstNameFlag " + firstNameFlag);
					System.out.println("lastNameFlag " + lastNameFlag);
					System.out.println("email flag " + email);
					System.out.println("phone flag " + phone);
					System.out.println("age flag " + age);
					System.out.println("regPass flag " + regPass);
					RequestDispatcher rd = request.getRequestDispatcher("/registration.jsp");
					rd.forward(request, response);

					System.out.println("forwarded to registration for error messages.html");
					break;
				}


				//and the request message is login
/*LOGIN*/	case "login":

				// get values from form
				String userEmail = request.getParameter("email");
				String unhashed= request.getParameter("regPass");
				System.out.println("The value of unhashed is: " + unhashed);

				//Check to see if they work
				MBValidator vali = new MBValidator();
				boolean success = vali.validateLogin(userEmail, unhashed);
				System.out.println("Results of login: " + success);

				//if login didn't work
				if (!success) {
					//Login failed
					//set session error message to retrieve on error page
					String errorMessage = "Your login was not successful due to incorrect email or password.";
					HttpSession session = request.getSession();
					session.setAttribute("errorMessage", errorMessage);
					RequestDispatcher errPage = request.getRequestDispatcher("errorPages/loginError.jsp");
					errPage.forward(request, response);

					//System.out.println("forwarded to loginError.jsp");
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
					HttpSession userSess = request.getSession();
					userSess.setAttribute("loggedInUser", loggedInUser);

					RequestDispatcher loginSuccess = request.getRequestDispatcher("/reservation.html");
					loginSuccess.forward(request, response);

					//System.out.println("forwarded to reservation.html");
					break;
				}//end else

/*BOOK*/	case "book":
				//System.out.println("Case acknowledged book request");
				HttpSession userSess = request.getSession();

				//This session value is set when a user logs in. If it has null values, no-one has logged in.
				CustomerBean customer = (CustomerBean) userSess.getAttribute("loggedInUser");
				try {
					if (customer.getEmail()== null) {//no email saved in session for user

					}
				}
				catch (NullPointerException e) {
					e.printStackTrace();
					//error, send to login
					String errorMessage = "You must be logged in to book a trip.";
					userSess.setAttribute("errorMessage", errorMessage);
					RequestDispatcher errPage = request.getRequestDispatcher("errorPages/loginError.jsp");
					errPage.forward(request, response);
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
					MBValidator mbVali = new MBValidator();
					boolean available = mbVali.validateStay(resRequest, customer);


					if (available) {
						//send to reservation confirmation
						userSess.setAttribute("resRequest", resRequest);
						RequestDispatcher confirm = request.getRequestDispatcher("reservationSummary.jsp");
						confirm.forward(request, response);
						break;
					}
					else {
						//send back to reservation page with error message
						String errorMessage = "The bed you selected is not available for the beginning of your stay.";
						userSess.setAttribute("errorMessage", errorMessage);
						RequestDispatcher errPage = request.getRequestDispatcher("errorPages/loginError.jsp");
						errPage.forward(request, response);
						break;
					}}
				else {
					String errorMessage = "You must be logged in to make a reservation.";
					userSess.setAttribute("errorMessage", errorMessage);
					RequestDispatcher errPage = request.getRequestDispatcher("errorPages/loginError.jsp");
					errPage.forward(request, response);
					break;
				}
				

/*CONFIRM*/	case "confirm":
				System.out.println("summary confirmed running");
				//pull in the reservation & guest info
				userSess = request.getSession();
				ReservationBean stayRequest = (ReservationBean) userSess.getAttribute("resRequest");
				CustomerBean loggedInUser = (CustomerBean) userSess.getAttribute("loggedInUser");
				//actually make reservation
				MBValidator mbvali = new MBValidator();
				String resultMessage = mbvali.confirmReservation(stayRequest, loggedInUser);
				if(resultMessage.contains("error")){
					//Alert customer reservation not made
					//and break before success message
					break;
				}

				//display success message somewhere?
				RequestDispatcher sendConfirm = request.getRequestDispatcher("successPage.html");
				sendConfirm.forward(request, response);
				break;

/*CANCEL*/	case "cancel":

				//redirect to reservation screen
				RequestDispatcher errPage = request.getRequestDispatcher("reservation.html");
				errPage.forward(request, response);
				break;
				
				
/*SRCH*/	case "searchByResNum":
/*byRes#*/		HttpSession searchSess = request.getSession();
				RequestDispatcher rd;
				String errorMessage = "";
				CustomerBean searchUser = (CustomerBean) searchSess.getAttribute("loggedInUser");
				if (searchUser==null) {
					//redirect to errorpage
					errorMessage = "You must be logged in to search for your reservation.";
					searchSess.setAttribute("errorMessage", errorMessage);
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
						searchSess.setAttribute("errorMessage", errorMessage);
						rd = request.getRequestDispatcher("lookup.jsp");
						rd.forward(request, response);
						break;
					}
					try {
						DataAccess searchDAO = new DataAccess();
						ReservationBean searchResult = searchDAO.searchReservationNumber(resNum, searchUser);
						//String testEmail = searchResult.getResOwnerEmail();
						if (searchUser.getEmail().equals(searchResult.getResOwnerEmail())) {//If emails match
							searchSess.setAttribute("searchResult", searchResult);//Make the results available
							
							rd = request.getRequestDispatcher("lookup.jsp");
							rd.forward(request, response);
							break;
						}
						else {
							errorMessage = "The email you logged in with doesn't match this reservation.";
							searchSess.setAttribute("errorMessage", errorMessage);
							rd = request.getRequestDispatcher("lookup.jsp");
							rd.forward(request, response);
							break;
						}
						
					}catch (Exception e) {
						e.printStackTrace();
						errorMessage = "That search had faulty results.";
						searchSess.setAttribute("errorMessage", errorMessage);
						rd = request.getRequestDispatcher("lookup.jsp");
						rd.forward(request, response);
						break;
					}
				}//end else
				
/*SRCH*/	case "searchByEmail":
/*by email*/	HttpSession selfSrch = request.getSession();
				RequestDispatcher srchrd;
				String errorM = "";
				CustomerBean searcher = (CustomerBean) selfSrch.getAttribute("loggedInUser");
				if(searcher==null) {
					errorM = "You must be logged in to search for a reservation.";
					selfSrch.setAttribute("errorMessage", errorM);
					srchrd = request.getRequestDispatcher("lookup.jsp");
					srchrd.forward(request, response);
					break;
				}
				else if ((searcher.getEmail()).equals(request.getParameter("email")) ) {
					//ok to search
					try {
						DataAccess emailSrchDAO = new DataAccess();
						ArrayList<ReservationBean> userRezs = emailSrchDAO.searchUserEmail(searcher);
						selfSrch.setAttribute("userRezs", userRezs);
						srchrd=request.getRequestDispatcher("lookup.jsp");
						srchrd.forward(request, response);
					}
					catch(Exception e) {e.printStackTrace(); break;}
				}
				else {
					errorM = "You can only look at your own reservations";
					selfSrch.setAttribute("errorMessage", errorM);
					srchrd = request.getRequestDispatcher("lookup.jsp");
					srchrd.forward(request, response);
					break;
				}
				
				//search by email
				//display all user reservations. 
				
			}//end switch
		}//end ifs



	}//end doPost
}//end Class








