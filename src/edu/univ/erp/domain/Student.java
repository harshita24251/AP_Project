package edu.univ.erp.domain;

public class Student{
    private String user_ID;
    private int roll_no;
    private String program;
    private int year;
    private String email_id;

    public String getUser_ID(){
        return user_ID;
    }

    public String getRollNo(){
        return roll_no;
    }

    public String getProgram(){
        return program;
    }

    public int getYear(){
        return year;
    }

    public String getEmailId(){
        return email_id;
    }
}