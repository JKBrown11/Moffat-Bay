package mbTools;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.*;

public class DisplayReservation extends SimpleTagSupport {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private ArrayList<ReservationBean> displayObjects= new ArrayList<>();
	private CustomerBean loggedIn;
	
	public void setDisplayObjects(ArrayList<ReservationBean> rezList) {
		this.displayObjects= rezList;
	}
	public ArrayList<ReservationBean> getDisplayObjects(){
		return this.displayObjects;
	}
	
	@SuppressWarnings("unchecked")
	public void doTag() throws IOException {
		
		//Connect to the 'site' and access session attribute we set for our list
		System.out.println("custom tag called");
		PageContext pageContext = (PageContext)getJspContext();
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        HttpSession session = request.getSession();
		
        this.loggedIn= (CustomerBean) session.getAttribute("loggedInUser");
        setDisplayObjects(null);
        
        
        if (session.getAttribute("userRezs")!=null) {
           	setDisplayObjects((ArrayList<ReservationBean>) session.getAttribute("userRezs"));
		}
        else if (session.getAttribute("searchResult")!= null) {
        	ReservationBean solo = (ReservationBean) session.getAttribute("searchResult");
        	displayObjects.add(solo);
        }
		else {
			JspWriter out = getJspContext().getOut();
			out.write("Nothing to display.");
		}
        if(this.displayObjects != null) {
	        
		        JspWriter out = getJspContext().getOut();
		        out.write("<table class=\"displayRes\">"
		        		+ "<tr>"
		        		+ "	<td style=\"width:20%\" >Guest Name</td>"
		        		+ "	<td style=\"width:25%\" >Contact</td>"
		        		+ "	<td style=\"width:10%\" >Res. Num.</td>"
		        		+ "	<td style=\"width:20%\">Check-In</td>"
		        		+ "	<td style=\"width:20%\">Check-Out</td>"
		        		+ "	<td style=\"width:5%\">Num. Guests</td>"
		        		+ "	</tr>");
		        
				for(ReservationBean res: this.displayObjects) {
					out.write("<tr>"
							+ "<td>" + loggedIn.getFirstName() + ", " + loggedIn.getLastName() + "</td>"
							+ "<td>" + res.getResOwnerEmail()+ "</td>"
							+ "<td>" + res.getResNumber() + "</td>"
							+ "<td>" + res.getCheckInDate() + "</td>"
							+ "<td>" + res.getCheckOutDate()+ "</td>"
							+ "<td>" + res.getNumGuests() + "</td>"
							+ "</tr>");
				}//end for
				
				out.write("</table");
	        
        }   
	}//end doTag
		
		
}//end doTag

