package edu.univ.erp.domain;

public class Grade{
    private String enrollment_id;
    private String component;
    private int score;
    private int final_grade;

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