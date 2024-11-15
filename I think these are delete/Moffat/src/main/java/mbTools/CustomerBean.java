package mbTools;
import javax.annotation.ManagedBean;

/**
 * 
 */
@ManagedBean
public class CustomerBean  {

	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String hireDate;
	private String position;
	//private String query;
	
	
	public CustomerBean() {
		super();
	}
		
	//Getters and setters for all bean props. 
	//I need to add more filtering for character length but it's good for now
	
	public String getFirstName() {return firstName;	}
	public void setFirstName(String firstName) {
		if (firstName != null)
			this.firstName = firstName;
	}
	
	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {
		if (lastName!=null)
			this.lastName = lastName;
	}
	
	
	public String getPhone() {return phone;}
	public void setPhone(String phone) {
		if (phone != null)
			this.phone = phone;
	}
	
	public String getEmail() {return email;	}
	public void setEmail(String email) {
		if (email != null)
			this.email = email;
	}
	
	
	
	
	

	
}
