package edu.univ.erp.ui.faculty.popup;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import edu.univ.erp.api.instructor.*;

public class ViewGrades extends JDialog{
    private static int width = 410;
    private static int height = 200;

    public ViewGrades(String Course_ID){
//        setTitle(String.format("Grades : %d", StudentRollNo.fetch(Student_ID)));
//        System.out.println("runnning");
        FlatLightLaf.setup();

//        HashMap<String, ArrayList<Integer>> componentGrades = ComponentWiseScores.fetch(Course_ID, Student_ID);

//        int size = componentGrades.size();
//        if (size == 1) height = 220;
//        else
//            height = size * 110;
//
        setSize(new Dimension(width, height));
        setTitle(String.format("%s Grades Average", Course_ID));
//
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        //-----------------------------Inputs-------------------------------
//        JPanel details = new JPanel();
//        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
//        details.setBackground(Color.WHITE);
//        mainPanel.add(details);
//
//        JLabel Name = new JLabel("Name");
//        Name.setFont(new Font("Segoe UI", Font.BOLD, 14));
//        JLabel Roll = new JLabel("Roll No.");
//        Roll.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        JLabel Email = new JLabel("Email ID");
//        Email.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        Name.setAlignmentX(Component.CENTER_ALIGNMENT);
//        Roll.setAlignmentX(Component.CENTER_ALIGNMENT);
//        Email.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        details.add(Name);
//        details.add(Box.createVerticalStrut(3));
//        details.add(Roll);
//        details.add(Box.createVerticalStrut(3));
//        details.add(Email);
//        details.add(Box.createVerticalStrut(10));

        JButton close = new JButton("Close");
        close.addActionListener(e -> dispose());

        //------------------------------------------------------------------

        JPanel button = new JPanel(new FlowLayout());
        button.setBackground(Color.WHITE);
        button.add(close);

        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);

//        for (String str : componentGrades.keySet()){
//            JTextField tmp = new JTextField(componentGrades.get(str).get(0).toString());
//            mainPanel.add(createList(str, tmp, String.format(" /%d", componentGrades.get(str).get(1))));
//            componentScores.put(str, tmp);
//        }

        JLabel course = new JLabel(Course_ID);
//        JLabel start = new JLabel(start_date);
//        JLabel end = new JLabel(end_date);

        mainPanel.add(createList("Course ID : ", course));
//        mainPanel.add(createList("Total Students : ", start));

        HashMap<String, Double> hs = AvgPerSection.get(Course_ID);
        for (String component : hs.keySet()){
            mainPanel.add(createList(component + " (Avg.)", new JLabel(String.valueOf(hs.get(component)))));
        }

//        mainPanel.add(createList("Deadline to Register", end));
//        mainPanel.add(createList("Capacity", new JLabel( registered+ "/" + capacity)));
//        mainPanel.add(createList("Students Registered", end));

        mainPanel.add(button);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static JPanel createList(String Label, JComponent input){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JPanel left = new JPanel(new BorderLayout());
        left.setPreferredSize(new Dimension(Math.round(width* 0.3f), height));
        left.setBackground(Color.WHITE);

        JPanel right = new JPanel(new BorderLayout());
        right.setPreferredSize(new Dimension(Math.round(width * 0.7f), height));
        right.setBackground(Color.WHITE);

        JLabel label = new JLabel(Label);

        left.add(label, BorderLayout.CENTER);
        right.add(input, BorderLayout.CENTER);

        panel.add(left, BorderLayout.WEST);
        panel.add(right, BorderLayout.CENTER);
        return panel;
    }
}
