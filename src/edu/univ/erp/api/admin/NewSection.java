package edu.univ.erp.api.admin;

import java.util.ArrayList;
import edu.univ.erp.data.*;

public class NewSection {
    public static void add(ArrayList<String> arr){
        AdminData.insertSectionData(arr);
    }
}
