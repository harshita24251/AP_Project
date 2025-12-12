package edu.univ.erp.api.student;

import edu.univ.erp.data.StudentData;

public class RollNo {
    public static boolean present(int roll){
        return StudentData.isPresent(roll);
    }
}
