package edu.univ.erp.api.student;

import edu.univ.erp.data.*;

import java.util.ArrayList;
import java.util.HashMap;

public class AvailableCourses {
    public static ArrayList<HashMap<String, String>> fetch(){
        return StudentData.getAvailableCourses();
    }
}
