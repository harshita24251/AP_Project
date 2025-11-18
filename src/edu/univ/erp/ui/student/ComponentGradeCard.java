package edu.univ.erp.ui.student;

import edu.univ.erp.api.catalog.*;
import edu.univ.erp.api.student.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.HashMap;

public class ComponentGradeCard extends JPanel{
    public ComponentGradeCard(String Course_ID){
        setLayout(new BorderLayout());

        HashMap<String, Double> grade = componentWiseGrades.fetch(Course_ID); //fetching how much obtained in component
        HashMap<String, Integer> components = courseComponents.fetch(Course_ID); //fetching components e.g. quiz, midsem etc

        String[] columns = {"Component", "Marks Obtained", "Weightage(%)"};
        Object[][] rowData = new Object[components.size() + 1][3];

        int index = 0;
        int total_marks_scored = 0;

        for (String key : components.keySet()){
            rowData[index][0] = key;
            rowData[index][1] = grade.get(key);
            total_marks_scored += grade.get(key);
            rowData[index][2] = components.get(key);
            index++;
        }

        rowData[index][0] = "Total";
        rowData[index][1] = total_marks_scored;
        rowData[index][2] = 100;

        JTable courseComponents = new JTable(rowData, columns);
        courseComponents.setEnabled(false);
        courseComponents.setFont(new Font("Roboto Mono", Font.PLAIN, 15));

        setBackground(Color.WHITE);
        JScrollPane master = new JScrollPane(courseComponents);
        master.setEnabled(false);
        add(master, BorderLayout.CENTER);
    }
}