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
	private int age;// The DB has age, it isn't required, do we want it?
	private String hashedPassword;
	private String regPass;
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
		String pwQualifiers="/\\w/";//create regEx for password min 8 chars, lower, upper, number
			
		if (regPass.matches(pwQualifiers)) {
			
			MBEncrpt enc = new MBEncrpt();
			String result= enc.hashItOut(regPass);
			if (result.contains("There was an error. Please try again")){
				//password not valid
				System.out.println("Password failed ");
				this.hashedPassword = null;
			}
			else {
				this.hashedPassword = result;
				this.regPass = null; //delete sensitive data. Destroyed after pw successfully hashed. 
			}
		}
		
	}

	public String getRegPass() {
		return regPass;
	}

	public void setRegPass(String regPass) {
		this.regPass = regPass;
		//auto update hashed pass
		setHashedPassword(regPass);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
	
	

	
}
