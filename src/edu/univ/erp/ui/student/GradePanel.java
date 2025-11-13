package edu.univ.erp.ui.student;

import java.awt.*;
import java.swing.*;
import java.awt.event.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;

public class GradePanel extends JPanel{
    public GradePanel(int totalSemester){
        setLayout(new BorderLayout());
        //--------------------------Panels-----------------------------
        JPanel semesterPanel = new JPanel(new BoxLayout(BoxLayout.Y_AXIS));
        JPanel coursePanel = new coursePanel();

        //-------------------------Icon Loading-----------------------
        Icon rightArrow = new FlatSVGIcon("right-arrow.svg", 0.25f);

        //-----------------------Semester Adding-----------------------
        for (int i = 1; i < totalSemester + 1; i++){
            JLabel isemester = new JLabel(String.format("Semester %d", i));
            semester.add(isemester);
        }

        //-------------------------------------------------------------
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, semesterPanel, coursePanel);
        split.setResizeWeight(0.3);

        add(split);
    }
} //#4eb2a5