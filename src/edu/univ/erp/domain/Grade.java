package edu.univ.erp.domain;

public class Grade{
    private String enrollment_id;
    private String component;
    private int score;
    private int final_grade;

    public Grade(String enrollment_id, String component, int score, int final_grade){
        this.enrollment_id = enrollment_id;
        this.component = component;
        this.score = score;
        this.final_grade = final_grade;
    }

    public String getEnrollmentID(){
        return enrollment_id;
    }

    public String getComponent(){
        return component;
    }

    public int getScore(){
        return score;
    }

    public int getFinalScore(){
        return final_grade;
    }
}