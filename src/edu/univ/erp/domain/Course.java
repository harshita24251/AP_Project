package edu.univ.erp.domain;

public class Course{
    private String course_id;
    private String title;
    private int credits;

    public Course(String course_id, String title, int credits){
        this.course_id = course_id;
        this.title = title;
        this.credits = credits;
    }

    public String getCourse_ID(){
        return course_id;
    }

    public String getTitle(){
        return title;
    }

    public int getCredits(){
        return credits;
    }
}