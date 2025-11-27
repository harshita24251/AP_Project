package edu.univ.erp.api.student;

import edu.univ.erp.data.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;

public class ComponentWiseScores{
    public static HashMap<String, ArrayList<Integer>> fetch(String Course_ID, String Student_ID){
        HashMap<String, ArrayList<Integer>> tmp = null;

        tmp = StudentData.getCourseComponentScores(Course_ID, Student_ID);

        return tmp;
    }
}