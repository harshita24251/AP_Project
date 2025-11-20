package edu.univ.erp.api.auth;

import edu.univ.erp.data.*;
import java.sql.*;

public class IsUsernamePresent{
    public static Boolean check(String Username){
        Boolean tmp = false;
        try{
            tmp = Auth.searchUsername(Username);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return tmp;
    }
}