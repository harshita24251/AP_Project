package edu.univ.erp.api.enrollment;

import edu.univ.erp.data.*;

public class thisEnrollment {
    public static void remove(String section){
        Enrollment.deleteEnrollment(section);
    }

    public static boolean enrolled(String section){
        return Enrollment.isEnrolled(section);
    }
}
