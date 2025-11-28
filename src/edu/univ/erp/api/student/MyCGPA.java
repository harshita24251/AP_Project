package edu.univ.erp.api.student;

import edu.univ.erp.data.StudentData;
import java.util.HashMap;

public class MyCGPA {
    public static HashMap<Integer, Double> fetch(){
        return StudentData.getCGPA();
    }
}
