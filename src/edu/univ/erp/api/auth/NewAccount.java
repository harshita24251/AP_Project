package edu.univ.erp.api.auth;

import edu.univ.erp.data.Auth;

import java.util.ArrayList;

public class NewAccount {
    public static void insert(ArrayList<String> arr){
        Auth.insertDetails(arr);
    }
}
