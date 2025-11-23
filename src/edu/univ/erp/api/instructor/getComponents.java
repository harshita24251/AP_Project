package edu.univ.erp.api.instructor;

import java.util.ArrayList;
import edu.univ.erp.data.*;
import edu.univ.erp.domain;

public class SectionDetails{
    public static ArrayList<String, Pair<Integer, Integer>> fetch(String Course_ID){
        return InstructorData.getMyCourseComponents(Course_ID);
    }
}