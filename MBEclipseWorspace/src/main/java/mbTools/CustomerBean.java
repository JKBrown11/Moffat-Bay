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
	private String hashedPassword;
	
	//private String query;
	
	
	public CustomerBean() {
		super();
	}
		
	//Getters and setters for all bean props. 
	
	
	public String getFirstName() {return firstName;	}
	public void setFirstName(String firstName) {
		if (firstName != null ) 
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

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String regPass) {
		String pwQualifiers;//create regEx for password min 8 chars, lower, upper, number
			
		if (regPass.matches(pwQualifiers)) {
			
			MBEncrpt enc = new MBEncrpt();
			String result= enc.hashItOut(regPass);
			if (result.contains("There was an error. Please try again")){
				//password not valid
				System.out.println("Password failed ");
				this.hashedPassword = null;
			}
			else this.hashedPassword = result;
		}
		
	}
	
	
	
	
	

	
}