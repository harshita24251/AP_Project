package edu.univ.erp.api.auth;

import edu.univ.erp.data.*;
import java.sql.*;

public class Role{
    public static String fetch(String Username){
        String tmp = "";
        try{
            tmp = Auth.getRole(Username);
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return tmp;
    }
}
