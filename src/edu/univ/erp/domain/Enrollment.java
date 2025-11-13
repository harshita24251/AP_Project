package edu.univ.erp.domain;

import edu.univ.erp.util;

public class Enrollment{
    private String enrollment_id;
    private String student_id;
    private String section_id;
    private Status status;

    public Enrollment(String enrollment_id, String student_id, String section_id, Status status){
        this.enrollment_id = enrollment_id;
        this.student_id = student_id;
        this.section_id = section_id;
        this.status = status;
    }

    public String getEnrollmentID(){
        return enrollment_id;
    }

    public String getStudentID(){
        return student_id;
    }

    public String getSectionID(){
        return section_id;
    }

    public Status getStatus(){
        return status;
    }
}