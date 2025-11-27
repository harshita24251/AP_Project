package edu.univ.erp.api.admin;

import edu.univ.erp.data.AdminData;

import java.util.ArrayList;

public class AllSectionDetails {
    public static ArrayList<ArrayList<String>> fetch(){
        return AdminData.getAllSections();
    }
}
