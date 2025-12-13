package edu.univ.erp.api.student;

import edu.univ.erp.data.*;

public class CourseCapacity {
    public static int get(String section_id){
        return Section.getCapacity(section_id);
    }
}
