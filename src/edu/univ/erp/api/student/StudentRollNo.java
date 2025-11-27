package edu.univ.erp.api.student;

import edu.univ.erp.data.StudentData;

public class StudentRollNo {
    public static int fetch(String User_ID){
        return StudentData.fetchRollNo(User_ID);
    }
}
