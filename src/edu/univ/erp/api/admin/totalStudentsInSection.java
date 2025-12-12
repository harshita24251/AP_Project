package edu.univ.erp.api.admin;

import edu.univ.erp.data.*;

public class totalStudentsInSection {
    public static int count(String section_id){
        return AdminData.studentEnrolled(section_id);
    }
}
