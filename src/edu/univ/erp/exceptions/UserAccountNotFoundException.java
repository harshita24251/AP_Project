package src.edu.univ.erp.exceptions;
import java.lang.Exception;

/**
Thrown when the username entered by the user is not found in the database.
*/

public class UserAccountNotFoundException extends Exception{
	public UserAccountNotFoundException(){
		super();
	}
}
