package edu.univ.erp.ui.student;

import edu.univ.erp.api.catalog.*;
import edu.univ.erp.api.student.*;
import edu.univ.erp.auth.Session;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.HashMap;

public class ComponentGradeCard extends JPanel{
    HashMap<String, Double> gradesToPdf = new HashMap<>();

    public ComponentGradeCard(String Course_ID){
        setLayout(new BorderLayout());

        HashMap<String, Double> grade = componentWiseGrades.fetch(Course_ID, Session.getCurrentUser_ID()); //fetching how much obtained in component
        HashMap<String, Integer> components = courseComponents.fetch(Course_ID); //fetching components e.g. quiz, midsem etc

        String[] columns = {"Component", "Marks Obtained", "Weightage(%)"};
        Object[][] rowData = new Object[components.size() + 1][3];

        int index = 0;
        int total_marks_scored = 0;

        for (String key : components.keySet()){
//            System.out.println(String.format("%s, %d", Course_ID, components.size()));
            rowData[index][0] = key;
//            System.out.println(key);
            rowData[index][1] = grade.get(key);
//            System.out.println(rowData[index][1]);
            gradesToPdf.put(key, (Double) rowData[index][1]);
            total_marks_scored += grade.get(key); //problem here
//            System.out.println(total_marks_scored);
            rowData[index][2] = components.get(key);
//            System.out.println("OK");
            index++;
        }

        rowData[index][0] = "Total";
        rowData[index][1] = total_marks_scored;
        rowData[index][2] = 100;
        gradesToPdf.put("Total", (double)total_marks_scored);

        JTable courseComponents = new JTable(rowData, columns);
        courseComponents.setEnabled(false);
        courseComponents.setFont(new Font("Roboto Mono", Font.PLAIN, 15));

        setBackground(Color.WHITE);
        JScrollPane master = new JScrollPane(courseComponents);
        master.setEnabled(false);
        add(master, BorderLayout.CENTER);
    }

    public HashMap<String, Double> getComponentContent(){
        return gradesToPdf;
    }
}