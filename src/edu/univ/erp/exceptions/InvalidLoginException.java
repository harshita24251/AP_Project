package src.edu.univ.erp.exceptions;
import java.lang.Exception;

/**
Thrown when the user adds wrong password for a username in auth_DB.
*/

public class InvalidLoginException extends Exception{
	public InvalidLoginException(){
		super();
	}
}
