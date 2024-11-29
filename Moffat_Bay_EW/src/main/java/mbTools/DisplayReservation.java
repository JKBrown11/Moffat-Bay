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
	
	@SuppressWarnings("unchecked")
	public void doTag() throws IOException {
		
		//Connect to the 'site' and access session attribute we set for our list
		
		PageContext pageContext = (PageContext)getJspContext();
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        HttpSession session = request.getSession();
		
        this.loggedIn= (CustomerBean) session.getAttribute("loggedInUser");
        
        if (session.getAttribute("userRezs")!=null) {
           	setDisplayObjects((ArrayList<ReservationBean>) session.getAttribute("userRezs"));
		}
        else if (session.getAttribute("searchResult")!= null) {
        	ReservationBean solo = (ReservationBean) session.getAttribute("searchResult");
        	displayObjects.add(solo);
        }
		else {
			JspWriter out = getJspContext().getOut();
			out.write("Something went wrong!");
			
		}
        JspWriter out = getJspContext().getOut();	
		for(ReservationBean res: displayObjects) {
			
			out.write("<tr>"
					+ "<td>" + loggedIn.getFirstName() + ", " + loggedIn.getLastName() + "</td>"
					+ "<td>" + res.getResOwnerEmail()+ "</td>"
					+ "<td>" + res.getResNumber() + "</td>"
					+ "<td>" + res.getCheckInDate() + "</td>"
					+ "<td>" + res.getCheckOutDate()+ "</td>"
					+ "<td>" + res.getNumGuests() + "</td>"
					+ "</tr>");
		}//end for
			
		
	}//end doTag
		
		
}//end doTag

