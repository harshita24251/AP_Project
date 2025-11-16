package edu.univ.erp.ui.student;

import edu.univ.erp.api.catalog.*;
import java.awt.*;
import java.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import com.formdev.flatlaf.extras.FlatSVGIcon;

public class GradePanel extends JPanel{
    public GradePanel(){
        setLayout(new BorderLayout());

        HashMap<Integer, String> semesterCGPA = CGPAPerSemester.fetch();

        for (int i = 1; i < semesterCGPA.size() + 1; i++){
            JTabbedPane pane = new JTabbedPane();
            pane.add(String.format("Semester %s", i), new JLabel("works!!"));
        }
    }
} //#4eb2a5