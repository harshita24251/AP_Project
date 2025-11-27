package edu.univ.erp.api.student;

import edu.univ.erp.data.StudentData;

public class StudentEmail {
    public static String fetch(String User_ID){
        return StudentData.fetchEmailId(User_ID);
    }
}
