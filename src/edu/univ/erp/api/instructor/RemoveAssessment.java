package edu.univ.erp.api.instructor;

import edu.univ.erp.data.*;

public class RemoveAssessment {
    public static void remove(String Course_ID, String title){
        InstructorData.removeAssessment(Course_ID, title);
    }
}
