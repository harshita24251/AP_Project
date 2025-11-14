package edu.univ.erp.auth;

public class Session{
    private static String user_id;
    private static Role role

    public static void setCurrentSession(String user_id){
        this.user_id = user_id;
    }

    public static void setCurrentRole(Role role){
        this.role = role;
    }

    public static String getCurrentUser_ID(){
        return user_id;
    }

    public static Role getCurrentRole(){
        return role;
    }
}