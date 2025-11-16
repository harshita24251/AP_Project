package edu.univ.erp.ui.student;

import edu.univ.erp.api.catalog.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.*;
import java.sql.*;

public class GradePanel extends JPanel{
    public GradePanel(){
        setLayout(new BorderLayout());

        HashMap<Integer, String> semesterCGPA = CGPAPerSemester.fetch();
        System.out.println(semesterCGPA.size());

        JTabbedPane pane = new JTabbedPane();
        for (int i = 1; i < 5; i++){ // semesterCGPA.size() + 1
            pane.addTab(String.format("Semester %s", i), new JButton("works!!"));
            pane.setVisible(true);
        }

        add(pane);
    }
} //#4eb2a5