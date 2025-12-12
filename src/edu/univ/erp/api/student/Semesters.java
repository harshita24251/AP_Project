package edu.univ.erp.api.student;

import edu.univ.erp.data.*;

public class Semesters {
    public static int current(){
        return StudentData.getTotalSemesters();
    }
}
