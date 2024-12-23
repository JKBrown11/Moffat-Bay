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
	
	// As of 11/25 on reservationSummary branch, sanitizing occurs in servlet.
	
	/*						*/
	/*      firstName      */
	/*						*/
	public String getFirstName() {return this.firstName; }
	public void setFirstName(String firstName) {
		if (firstName != null ) 
			this.firstName = firstName;
	}
	
	/*						*/
	/*      lastName      */
	/*						*/
	public String getLastName() {return lastName; }
	public void setLastName(String lastName) {
		if (lastName!=null)
			this.lastName = lastName;
	}
	
	/*					*/
	/*      Phone      */
	/*					*/
	public String getPhone() {return this.phone; }
	public void setPhone(String phone) {
		if (phone != null)
			this.phone = phone;
	}

	/*				*/
	/*     Email      */
	/*				*/
	public String getEmail() {return this.email; }
	public void setEmail(String email) {
		if (email != null)
			this.email = email;
	}

	/*						*/
	/*      hashedPass      */
	/*						*/
	public String getHashedPassword() {return this.hashedPassword;}
	public void setHashedPassword(String regPass) {
		//Help from : https://formulashq.com/the-ultimate-guide-to-regex-for-password-validation/
		//and regex101.com 
		String pwQualifiers="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$";//create regEx for password min 8 chars, lower, upper, number
		
		System.out.println("Checking for error points");	
		if (regPass.matches(pwQualifiers)) {
			System.out.println("Password passed reg Ex");
			MBEncrypt enc = new MBEncrypt();
			System.out.println("Encrypt object created");
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

	/*					*/
	/*      RegPass      */
	/*					*/
	public String getRegPass() {return this.regPass; }
	public void setRegPass(String regPass) {
		System.out.println("Ran setRegPass");
		this.regPass = regPass;
		//auto update hashed pass
		setHashedPassword(regPass);
		System.out.println("ran setHashedPassword");
	}

	/*				*/
	/*      Age      */
	/*				*/
	public int getAge() {return age;}
	public void setAge(String age) {
		this.age = Integer.parseInt(age);
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
	

	
}
