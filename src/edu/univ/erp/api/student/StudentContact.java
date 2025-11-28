package edu.univ.erp.api.student;

import edu.univ.erp.data.StudentData;

public class StudentContact {
    public static String fetch(String User_ID){
        return StudentData.fetchContact(User_ID);
    }
}
