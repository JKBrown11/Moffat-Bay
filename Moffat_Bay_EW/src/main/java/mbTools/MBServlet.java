package mbTools;

import java.io.IOException;
import java.sql.SQLException;
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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	/*
	 * Method to handle post requests.
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Utilize hidden inputs to denote post request source		
		String from = request.getParameter("myrequest");
		//System.out.println("The value of hidden input 'myrequest' is " + from);
		//testing point
		
		//If there is a request value received:
		if (from != null) {
			switch (from){
			//and it is register
			case "register":
				//the customer beans do  not have input sani as of 11/14/24 1:44 CST
				//pull the data from the form, using an object.
				CustomerBean registerNew = new CustomerBean();
				registerNew.setFirstName(request.getParameter("firstName"));
				registerNew.setLastName(request.getParameter("lastName"));				
				registerNew.setEmail(request.getParameter("email"));
				registerNew.setPhone(request.getParameter("phone"));
				registerNew.setAge(request.getParameter("age"));
				String regPass = request.getParameter("regPass");
				registerNew.setRegPass(regPass);
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
			
				//and it is login
			case "login":
				// get values from form
				String email = request.getParameter("email");
				String unhashed= request.getParameter("regPass");
				System.out.println("The value of unhashed is: " + unhashed);
				
				//Check to see if they work
				MBValidator vali = new MBValidator();
				boolean success = vali.validateLogin(email, unhashed);
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
					loggedInUser.setEmail(email); 
					
					
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
				
			case "book":
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
				
				}//end switch
			}//end ifs
		
			
		
	}//end doPost	
}//end Class		
		
		
		
	
		//this sets the new employee as a session object available by name
		//to anything in the session scope.
		//request.getSession().setAttribute("empBean", registerNew);
		
		
		//I was able to isolate the db access more successfully than in module 8. This gave me
		//the least trouble of everything this time.
//		try {
//			DataAccess daoBean = new DataAccess();
//			daoBean.addBeans(registerNew);
//			daoBean.setQueryRes();
//			request.getSession().setAttribute("printList", daoBean.getQueryRes());
//		}
//		catch (ClassNotFoundException e) {
//			e.printStackTrace();}
//		catch (SQLException e) {
//			e.printStackTrace();}
//		
//		//Logic complete, send response
//		RequestDispatcher rd = request.getRequestDispatcher("/team.jsp");
//		rd.forward(request, response);
//		System.out.println("forwarded to team.jsp");
		



