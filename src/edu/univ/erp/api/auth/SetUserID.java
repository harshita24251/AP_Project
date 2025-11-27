package edu.univ.erp.api.auth;

import edu.univ.erp.auth.Session;

public class SetUserID {
    public static void set(String SessionID, String role){
        Session.setCurrentSession(SessionID);
        Session.setCurrentRole(edu.univ.erp.util.Role.valueOf(role));
    }
}
