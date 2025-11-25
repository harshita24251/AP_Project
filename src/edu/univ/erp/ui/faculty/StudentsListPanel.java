package edu.univ.erp.ui.faculty;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import edu.univ.erp.api.instructor.StudentsEnrolled;

public class StudentsListPanel extends JPanel{
    private ArrayList<ArrayList<Object>> listOfStudents;

    public StudentsListPanel(float width, float height_, String Course_ID){
        FlatLightLaf.setup();

        //--------------------------------styles----------------------------------
        setPreferredSize(new Dimension(Math.round(width), Math.round(height_)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        listOfStudents = StudentsEnrolled.fetch(Course_ID);
        System.out.println(listOfStudents.size());

        float height = height_ * 0.04f; // Reduced from 0.065f for sleeker rows

        for (ArrayList<Object> arr : listOfStudents){
            JPanel main = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            main.setMaximumSize(new Dimension(Math.round(width), Math.round(height)));
            main.setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
            main.setBackground(Color.WHITE);

            // Re-distributed widths:
            // ID column (name) reduced from 0.27 to 0.12
            // Student Name column (program) increased from 0.14 to 0.30
            JPanel no = createList(Math.round(0.04f * width), Math.round(height));
            JPanel roll = createList(Math.round(0.06f * width), Math.round(height));
            JPanel name = createList(Math.round(0.12f * width), Math.round(height));
            JPanel program = createList(Math.round(0.30f * width), Math.round(height));
            JPanel semester = createList(Math.round(0.08f * width), Math.round(height));
            JPanel email = createList(Math.round(0.25f * width), Math.round(height));
            JPanel viewgrades = createList(Math.round(0.15f * width), Math.round(height));

            JLabel noLabel = createLabel(arr.get(0));
            JLabel rollLabel = createLabel(arr.get(1));
            JLabel nameLabel = createLabel(arr.get(2));
            JLabel programLabel = createLabel(arr.get(3));
            JLabel semesterLabel = createLabel(arr.get(4));
            JLabel emailLabel = createLabel(arr.get(5));

            no.add(noLabel, BorderLayout.CENTER);
            roll.add(rollLabel, BorderLayout.CENTER);
            name.add(nameLabel, BorderLayout.CENTER);
            program.add(programLabel, BorderLayout.CENTER);
            semester.add(semesterLabel, BorderLayout.CENTER);
            email.add(emailLabel, BorderLayout.CENTER);

            main.add(no);
            main.add(roll);
            main.add(name);
            main.add(program);
            main.add(semester);
            main.add(email);
            main.add(viewgrades);

            add(main);
        }


    }

    public static JPanel createList(int width, int height){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBackground(Color.GRAY);

        return panel;
    }

    public static JLabel createLabel(Object content){
        JLabel label = new JLabel((String) content);
        return label;
    }
}
