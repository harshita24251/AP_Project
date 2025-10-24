package edu.univ.erp.util;
import edu.univ.erp.exceptions.*;

/**
getUsername : to get username from email address
getDomain : to get domain from email address

also checks for validity of email, throws InvalidEmailException if not @ found in the email address
*/

public final class Email{
  public static String getUsername(String email) throws InvalidEmailException{
    if (email.contains("@")){
      return email.split("@")[0];
    }
    else{
      throw new InvalidEmailException();
    }
  }

  public static String getDomain(String email) throws InvalidEmailException{
    if (email.contains("@")){
      return email.split("@")[1];
    }
    else{
      throw new InvalidEmailException();
    }
  }
}
