package mbTools;
// Reference https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MBEncrypt {
	
	//returns our byte digest as a string or our hashedPass
	public String hashItOut(String regPass) {
		System.out.println("Running hashItOut");
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			md.update(regPass.getBytes());
			byte[] myDigest = md.digest();
			BigInteger bigIntDigest= new BigInteger(1, myDigest);
			String hashedIt = bigIntDigest.toString();
			
			return hashedIt;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "There was an error. Please try again";
		}
		
	}

}
