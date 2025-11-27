package edu.univ.erp.api.student;

import edu.univ.erp.data.StudentData;

import java.util.HashMap;

public class UpdatedGrades {
    public static void upgrade(HashMap<String, Double> gradesList, String Course_ID, String Student_ID){
        StudentData.insertGrades(gradesList, Course_ID, Student_ID);
    }
}
