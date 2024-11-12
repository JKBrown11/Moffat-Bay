package mbTools;
// Reference https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MBEncrpt {
	
	//returns our byte digest as a string or our hashedPass
	public String hashItOut(String regPass) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			md.update(regPass.getBytes());
			String hashedIt = md.digest().toString();//very very simple transposition for simplicity
			return hashedIt;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "There was an error. Please try again";
		}
		
	}

}
