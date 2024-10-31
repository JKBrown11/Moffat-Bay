package craftmartTools;
import javax.annotation.ManagedBean;

/**
 * 
 */
@ManagedBean
public class EmployeeBean  {

	private String firstName;
	private String lastName;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String email;
	private String hireDate;
	private String position;
	//private String query;
	
	
	public EmployeeBean() {
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
	
	public String getStreetAddress() {return streetAddress;	}
	public void setStreetAddress(String streetAddress) {
		if (streetAddress != null)
			this.streetAddress = streetAddress;
	}
	
	public String getCity() {return city;}
	public void setCity(String city) {
		if (city != null)
			this.city = city;
	}
	
	public String getState() {return state;}
	public void setState(String state) {
		if (state != null)
			this.state = state;
	}
	
	public String getZip() {return zip;	}
	public void setZip(String zip) {
		if (zip != null)
			this.zip = zip;}
	
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
	
	public String getHireDate() {return hireDate;}
	public void setHireDate(String hireDate) {
		if (hireDate != null)
			this.hireDate = hireDate;
	}
	
	public String getPosition() {return position;}
	public void setPosition(String position) {
		if (position != null)
			this.position = position;
	}

	
	

	
}
