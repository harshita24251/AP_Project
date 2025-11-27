package edu.univ.erp.ui.admin;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.sun.tools.javac.Main;
import edu.univ.erp.ui.faculty.Assessments;
import edu.univ.erp.ui.faculty.StudentsListPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FacultyPanel extends JPanel{
    public FacultyPanel(float width, float height){
        setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        //---------------------------------------widgets------------------------------------------
        Icon plus = new FlatSVGIcon("plus.svg", 0.25f);

        JButton addFaculty = new JButton("Add Faculty", plus);
//        createAssessment.addActionListener(new Assessments.createAssessmentEvent(arr.get(1)));

        JPanel widgets = new JPanel(new BorderLayout());
        widgets.setPreferredSize(new Dimension(Math.round(width), Math.round(height * 0.05f)));
        widgets.setBorder(new EmptyBorder(10, 20, 0, 20));
        widgets.setBackground(Color.WHITE);
        widgets.add(addFaculty, BorderLayout.WEST);

        JPanel facultyListAll = new edu.univ.erp.ui.admin.FacultyListPanel(width, height);

        JPanel MainPanel = new JPanel();
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
        MainPanel.setBackground(Color.WHITE);
        MainPanel.setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        MainPanel.add(widgets);
        MainPanel.add(facultyListAll);

        add(MainPanel, BorderLayout.CENTER);
//        add(studentsListAll, BorderLayout.CENTER);
        setVisible(true);
    }
}