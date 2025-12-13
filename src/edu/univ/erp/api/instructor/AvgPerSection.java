package edu.univ.erp.api.instructor;

import edu.univ.erp.data.*;
import java.util.HashMap;

public class AvgPerSection {
    public static HashMap<String, Double> get(String Course_ID){
        return InstructorData.getAveragePerSection(Course_ID);
    }
}
