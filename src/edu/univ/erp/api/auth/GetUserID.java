package edu.univ.erp.api.auth;

import edu.univ.erp.data.Auth;

public class GetUserID {
    public static String fetch(String User_ID){
        return Auth.getID(User_ID);
    }
}
