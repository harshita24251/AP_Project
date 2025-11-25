package edu.univ.erp.api.instructor;

import java.util.ArrayList;
import edu.univ.erp.data.*;

public class StudentsEnrolled {
    public static ArrayList<ArrayList<Object>> fetch(String Course_ID){
        return InstructorData.getStudentsEnrolled(Course_ID);
    }
}
