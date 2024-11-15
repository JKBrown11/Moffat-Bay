package mbTools;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MBServlet
 */
@WebServlet //("/MoffatBay/")
public class MBServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L; //creates serializable
	       
    //default constructor
    public MBServlet() {
        System.out.println("Servlet CM ran"); 
       
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	/*
	 * Method to handle post requests.
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String from = request.getParameter("myrequest");
		System.out.println("The value of hidden input 'myrequest' is " + from);
		
		
		if (from != null) {
			switch (from){
			case "register":
				//the customer beans do  not have input sani as of 11/14/24 1:44 CST
				CustomerBean registerNew = new CustomerBean();
				registerNew.setFirstName(request.getParameter("firstName"));
				registerNew.setLastName(request.getParameter("lastName"));				
				registerNew.setEmail(request.getParameter("email"));
				registerNew.setPhone(request.getParameter("phone"));
				registerNew.setAge(request.getParameter("age"));
				String regPass = request.getParameter("regPass");
				registerNew.setRegPass(regPass);
				System.out.println("Form values received. ");
				
				try {
					//This uses the DataAccess class to load the driver, and connect.
					DataAccess daoBean = new DataAccess();
					daoBean.addBeans(registerNew); //This inserts a new customer to db. Currently shows null for hashed pass. 
				}
				catch (ClassNotFoundException e) {
					e.printStackTrace();}
				catch (SQLException e) {
					e.printStackTrace();}
				
				System.out.println("customer added to db");
				
				RequestDispatcher rd = request.getRequestDispatcher("/login.html");
				rd.forward(request, response);
				
				System.out.println("forwarded to login.html");
				
			case "login":
				
			}
		}
		
		
		
		
		
	
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
		
	}//end doPOst

}
