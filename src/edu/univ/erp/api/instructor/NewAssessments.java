package edu.univ.erp.api.instructor;

import edu.univ.erp.data.*;

import java.sql.Time;
import java.sql.Timestamp;

public class NewAssessments {
    public static void create(String Course_ID, String title, int Marks, int weightage, Timestamp start, Timestamp end){
        InstructorData.addNewAssessment(Course_ID, title, Marks, weightage, start, end);
    }
}
