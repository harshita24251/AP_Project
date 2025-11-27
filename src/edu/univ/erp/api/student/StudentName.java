package edu.univ.erp.api.student;

import edu.univ.erp.data.StudentData;

public class StudentName {
    public static String fetch(String User_ID){
        return StudentData.fetchName(User_ID);
    }
}
