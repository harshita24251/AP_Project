package edu.univ.erp.api.student;

import edu.univ.erp.data.StudentData;

public class StudentProgram {
    public static String fetch(){
        return StudentData.fetchProgram();
    }
}
