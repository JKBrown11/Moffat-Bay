package craftmartTools;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.*;

public class DisplayStaff extends SimpleTagSupport {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private ArrayList<EmployeeBean> queryList= new ArrayList<>();
	
	public void setQueryList(ArrayList<EmployeeBean> staffList) {
		this.queryList=staffList;
	}
	
	@SuppressWarnings("unchecked")
	public void doTag() throws IOException {
		
		//Connect to the 'site' and access session attribute we set for our list
		//from the database in the Servlet
		PageContext pageContext = (PageContext)getJspContext();
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        HttpSession session = request.getSession();
		this.queryList= (ArrayList<EmployeeBean>) session.getAttribute("printList");
		
		//Now that we have the data, lets show it.
		//Technically the header could be in jsp
		JspWriter out = getJspContext().getOut();
		out.write("<table class=\"displayTable\"><tr>"
				+ "<th>First Name </th>" + "<th>Last Name </th>" + "<th>Email </th>" 
				+ "<th>Phone </th>" + "<th>Street address </th>" + "<th>City</th>" 
				+ "<th>State</th>" + "<th>Zip</th>" + "<th>HireDate </th>" + "<th>Position</th></tr>");
			
		for(EmployeeBean empl: queryList) {
			//each employee as a row in the table
			out.write("<tr><td>" + empl.getFirstName() +"</td>"
					+ "<td>" + empl.getLastName() + "</td> "
					+ "<td>" + empl.getEmail() + "</td>"
					+ "<td>" + empl.getPhone() + "</td>"
					+ "<td>"+ empl.getStreetAddress() +"</td>"
					+ "<td>"+ empl.getCity() +"</td>"
					+ "<td>"+ empl.getState() + "</td>"
					+ "<td>"+ empl.getZip() +"</td>"
					+ "<td>"+ empl.getHireDate() +  "</td>"
					+ "<td>"+ empl.getPosition()+ "</td></tr>");
		}//end for
			
		out.write("</table>");
	}//end doTag
		
		
}//end doTag

