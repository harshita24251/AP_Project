package edu.univ.erp.domain;

public class Enrollment{
    private String enrollment_id;
    private String student_id;
    private String section_id;
    private String status;

    public String getEnrollmentID(){
        return enrollment_id;
    }

    public String getStudentID(){
        return student_id;
    }

    public String getSectionID(){
        return section_id;
    }

    public String getStatus(){
        return status;
    }
}