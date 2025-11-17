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

        //--------------------------coloring tabs-----------------------------------
        UIManager.put("TabbedPane.underlineColor", new Color(78, 178, 165));
        UIManager.put("TabbedPane.underlineHeight", 1);
        UIManager.put("TabbedPane.inactiveUnderlineColor", new Color(78, 178, 165));
        //---------------------------------------------------------------------------

        JTabbedPane pane = new JTabbedPane();
        for (int i = 1; i < totalSemesters.fetch() + 1; i++){ // semesterCGPA.size() + 1
            pane.addTab(String.format("Semester %s", i), new JButton("works!!"));
            pane.setVisible(true);
        }
        
        add(pane);
    }
} //#4eb2a5