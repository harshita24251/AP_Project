package edu.univ.erp.api.instructor;

import java.util.ArrayList;
import edu.univ.erp.data.*;
import edu.univ.erp.domain.*;

public class getComponents{
    public static ArrayList<ArrayList<Object>> fetch(String Course_ID){
        return InstructorData.getMyCourseComponents(Course_ID);
    }
}