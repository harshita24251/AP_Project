package edu.univ.erp.ui.student;

import edu.univ.erp.api.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.HashMap;

class CourseGradeCard extends JPanel{
    public CourseGradeCard(Course_ID){

        HashMap<String, Double> grade = student.componentWiseGrades.fetch(Course_ID); //fetching how much obtained in component
        HashMap<String, Integer> components = catalog.courseComponents.fetch(Course_ID); //fetching components e.g. quiz, midsem etc

        String[] columns = {"Component", "Marks Obtained", "Weightage(%)"};
        Object[][] rowData = new Object[components.size()][3];

        int index = 0;

        for (String key : components.keySet()){
            rowData[index][0] = key;
            rowData[index][1] = grade.get(key);
            rowData[index][2] = components.get(key);
            index++;
        }

        JTable courseComponents = new JTable(rowData, columns);

        add(courseComponents);
    }
}