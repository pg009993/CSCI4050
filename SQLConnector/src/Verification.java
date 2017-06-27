import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Verification {
	   public static void main(String [] args) {
		 //Generate verification code 
	         Random randomNum = new Random(); 
	         int verificationCode = randomNum.nextInt(999999); 
	  
	         System.out.println("CODE: " + verificationCode);   
	   }
	}

	
	
