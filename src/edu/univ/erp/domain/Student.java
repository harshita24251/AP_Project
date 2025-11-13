package edu.univ.erp.domain;

public class Student{
    private String user_ID;
    private int roll_no;
    private String program;
    private int year;
    private String email_id;

    public Student(String user_ID, int roll_no, String program, int year, String email_id){
        this.user_ID = user_ID;
        this.roll_no = roll_no;
        this.program = program;
        this.year = year;
        this.email_id = email_id;
    }

    public String getUser_ID(){
        return user_ID;
    }

    public int getRollNo(){
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