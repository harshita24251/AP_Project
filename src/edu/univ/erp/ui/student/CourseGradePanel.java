package edu.univ.erp.ui.student;

import edu.univ.erp.api.catalog.*;
import edu.univ.erp.api.student.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.*;
import edu.univ.erp.ui.common.events.*;
import javax.swing.border.EmptyBorder;
/**
 * for getting components of courses as a JPanel
 */

public class CourseGradePanel extends JPanel{
    public CourseGradePanel(int semesterNo, float width, float height){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //------------------------------------Loading Icon--------------------------------------------
        Icon right_Arrow = new FlatSVGIcon("right-arrow.svg", 0.25f);
        //--------------------------------------------------------------------------------------------
        HashMap<JLabel, Boolean> highlighter = new HashMap<>();

        for (String s : registeredCourses.fetch(semesterNo)){
            JPanel coursePanel = new JPanel(new BorderLayout());
            coursePanel.setPreferredSize(new Dimension(Math.round(width), 170));
            JPanel courseTitle = new JPanel(new BorderLayout());
            courseTitle.setBackground(Color.WHITE);
            courseTitle.setPreferredSize(new Dimension(Math.round(width), 30));

            JLabel semesterLabels = new JLabel(s + " : " + courseName.fetch(s), right_Arrow, JLabel.LEFT);
            semesterLabels.addMouseListener(new changeForegroundCourse(semesterLabels, highlighter, coursePanel, s));
            semesterLabels.setFont(new Font("Roboto Mono", Font.PLAIN, 16));
            semesterLabels.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            courseTitle.add(semesterLabels, BorderLayout.WEST);

            highlighter.put(semesterLabels, false);
            coursePanel.setBackground(Color.WHITE);
            coursePanel.add(courseTitle, BorderLayout.NORTH);
            coursePanel.setMaximumSize(new Dimension(Math.round(width), 30)); //warning: height hardcoded here
            // coursePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            add(coursePanel);
        }

        setBackground(Color.WHITE);
    }
}