package edu.univ.erp.domain;

import java.time.LocalDateTime;
import edu.univ.erp.util.*;

public class Section{
    private String course_id;
    private String instructor_id;
    private DayTime day_time;
    private String room;
    private int capacity;
    private int semester;
    private int year; 

    public Section(String course_id, String instructor_id, DayTime day_time, String room, int capacity, int semester, int year){
        this.course_id = course_id;
        this.instructor_id = instructor_id;
        this.day_time = day_time;
        this.room = room;
        this.capacity = capacity;
        this.semester = semester;
        this.year = year;
    }

    public String course_id(){
        return course_id;
    }

    public String instructor_id(){
        return instructor_id;
    }

    public DayTime getDayTime(){
        return day_time;
    }

    public String getRoom(){
        return room;
    }

    public int getCapacity(){
        return capacity;
    }

    public int getSemester(){
        return semester;
    }

    public int getYear(){
        return year;
    }
}