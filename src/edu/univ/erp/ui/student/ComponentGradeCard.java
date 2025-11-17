package edu.univ.erp.ui.student;

import edu.univ.erp.api.catalog.*;
import edu.univ.erp.api.student.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.HashMap;

class ComponentGradeCard extends JPanel{
    public ComponentGradeCard(String Course_ID){

        HashMap<String, Double> grade = componentWiseGrades.fetch(Course_ID); //fetching how much obtained in component
        HashMap<String, Integer> components = courseComponents.fetch(Course_ID); //fetching components e.g. quiz, midsem etc

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
        courseComponents.setEnabled(false);
        courseComponents.setFont(new Font("Roboto Mono", Font.PLAIN, 16));

        setBackground(Color.WHITE);
        add(new JScrollPane(courseComponents));
    }
}