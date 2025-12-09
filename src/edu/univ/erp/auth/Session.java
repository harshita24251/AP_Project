package edu.univ.erp.auth;

import edu.univ.erp.util.Role;

public class Session{
    private static String user_id = "Shad101"; // hardcoded for testing
    private static Role role = Role.student; // hardcoded for testing

    public static void setCurrentSession(String user_id_){
        user_id = user_id_;
    }

    public static void setCurrentRole(Role role_){
        role = role_;
    }

    public static String getCurrentUser_ID(){
        return user_id;
    }

    public static Role getCurrentRole(){
        return role;
    }
}