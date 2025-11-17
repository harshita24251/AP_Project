package edu.univ.erp.ui.student;

import edu.univ.erp.ui.common.events.*;
import edu.univ.erp.api.catalog.*;
import edu.univ.erp.api.student.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.border.*;
import java.util.Map;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.*;
import java.sql.*;
import java.util.HashMap;

public class GradePanel extends JPanel{
    public GradePanel(){
        setLayout(new BorderLayout());

        //--------------------------styling tabs-----------------------------------
        UIManager.put("TabbedPane.underlineColor", new Color(78, 178, 165));
        UIManager.put("TabbedPane.inactiveUnderlineColor", new Color(78, 178, 165));
        //---------------------------------------------------------------------------

        JTabbedPane pane = new JTabbedPane();
        for (int i = 1; i < totalSemesters.fetch() + 1; i++){
            pane.addTab(String.format("Semester %s", i), new JButton("ji"));
            pane.setVisible(true);
            pane.setBackground(Color.WHITE);
        }

        //------------------------------------Loading Icon--------------------------------------------
        Icon right_Arrow = new FlatSVGIcon("right-arrow.svg", 0.25f);
        //--------------------------------------------------------------------------------------------

        JPanel master = new JPanel(new BorderLayout());

        HashMap<JLabel, Boolean> highlighter = new HashMap<>();

        JPanel master_ = new JPanel();
        master_.setPreferredSize(new Dimension(Math.round(getWidth()), Math.round(getHeight() * 0.4f)));
        master_.setLayout(new BoxLayout(master_, BoxLayout.Y_AXIS));
        for (String s : registeredCourses.fetch(1)){
            JPanel testing = new JPanel(new BorderLayout());

            JLabel semesterLabels = new JLabel(s + " : " + courseName.fetch(s), right_Arrow, JLabel.LEFT);
            semesterLabels.addMouseListener(new changeForegroundCourse(semesterLabels, highlighter));
            semesterLabels.setFont(new Font("Roboto Mono", Font.PLAIN, 16));
            semesterLabels.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            highlighter.put(semesterLabels, false);
            testing.setPreferredSize(new Dimension(Math.round(getWidth() * 0.5f), Math.round(getHeight() * 0.1f)));
            testing.setBackground(Color.WHITE);
            testing.add(semesterLabels, BorderLayout.WEST);
            testing.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); //warning: height hardcoded here
            // testing.setAlignmentX(Component.LEFT_ALIGNMENT);
            master_.add(testing);
        }

        master_.setBackground(Color.WHITE);
        JScrollPane masterScroller = new JScrollPane(master_);
        masterScroller.setBorder(null);

        //-------------------------------------------------------------------------------------------
        master.add(masterScroller);
        pane.setComponentAt(0, master);
        
        setBackground(Color.WHITE);
        add(pane);
    }
} //#4eb2a5