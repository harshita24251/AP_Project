package edu.univ.erp.api.auth;

import edu.univ.erp.data.*;
import java.sql.*; 

public class IsPasswordValid{
    public static Boolean check(String Username, char[] Password){
        Boolean tmp = false;
        try{
            tmp = Auth.verifyPassword(Username, Password);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return tmp;
    }
}