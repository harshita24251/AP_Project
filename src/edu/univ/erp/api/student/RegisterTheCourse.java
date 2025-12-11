package edu.univ.erp.api.student;

import edu.univ.erp.data.StudentData;
import edu.univ.erp.data.*;

public class RegisterTheCourse {
    public static void register(String section_id){
        Enrollment.newEnrollment(section_id);
    }
}
