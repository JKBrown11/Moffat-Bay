package craftmartTools;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CMServlet
 */
@WebServlet({"/craftmart"})
public class CMServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L; //creates serializable
	private String goHome = "index.jsp"; //creating references for potential calling pages
	private String addEmp = "add.jsp";
       
    //default constructor
    public CMServlet() {
        System.out.println("Servlet CM ran"); 
       
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	/*
	 * Method to handle server request. At this time, only request exists.
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Leaving for future troubleshooting of what I've tried- I might finish this. 
		//String action = (String)request.getParameter("action");
		//String locateReq = request.getContextPath();
		//System.out.println(" Param action: " + action); 
		
		//EmployeeBean newHire = new EmployeeBean();
		//.out.println("FirstName " + newHire.getFirstName());
		//EmployeeBean formBean= (EmployeeBean)request.getAttribute("formBean");
		//System.out.println(formBean.toString());
		
		//Beans are still giving me some grief because I couldn't use some of the features in jsp
		//so I had to do it the long way, but storing things as objects is very convenient. It's like
		//buildable format on the fly. 
		EmployeeBean empBean = new EmployeeBean();
		empBean.setFirstName(request.getParameter("firstName"));
		System.out.println(empBean.getFirstName());
		
		empBean.setLastName(request.getParameter("lastName"));
		System.out.println(empBean.getLastName());
		
		empBean.setEmail(request.getParameter("email"));
		System.out.println(empBean.getEmail());
		
		empBean.setPhone(request.getParameter("phone"));
		System.out.println(empBean.getPhone());
		
		empBean.setStreetAddress(request.getParameter("streetAddress"));
		System.out.println(empBean.getStreetAddress());
		
		empBean.setCity(request.getParameter("city"));
		System.out.println(empBean.getCity());
		
		empBean.setState(request.getParameter("state"));
		System.out.println(empBean.getState());
		
		empBean.setZip(request.getParameter("zip"));
		System.out.println(empBean.getZip());
		
		empBean.setHireDate(request.getParameter("hireDate"));
		System.out.println(empBean.getHireDate());
		
		empBean.setPosition(request.getParameter("position"));
		System.out.println(empBean.getFirstName());
	
		//this sets the new employee as a session object available by name
		//to anything in the session scope.
		request.getSession().setAttribute("empBean", empBean);
		
		
		//I was able to isolate the db access more successfully than in module 8. This gave me
		//the least trouble of everything this time.
		try {
			DataAccess daoBean = new DataAccess();
			daoBean.addBeans(empBean);
			daoBean.setQueryRes();
			request.getSession().setAttribute("printList", daoBean.getQueryRes());
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();}
		catch (SQLException e) {
			e.printStackTrace();}
		
		//Logic complete, send response
		RequestDispatcher rd = request.getRequestDispatcher("/team.jsp");
		rd.forward(request, response);
		System.out.println("forwarded to team.jsp");
		
	}//end doPOst

}
