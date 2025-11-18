package edu.univ.erp.ui.common;

import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.border.*;

/**
 * Course Code, Course Acronym, Course Id, Credits
 * 
 * This is arranged as a parent panel has two panels
 */

public class CoursePalette extends JPanel{
    public CoursePalette(float width, float height, String name, String acronym, String id, String credits, int fontsize, boolean foreground){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        //----------------------------Left Right Panels------------------------------
        float leftPanelWidth = width * 0.6f;
        float rightPanelWidth = width * 0.4f;

        JPanel left = new JPanel(new BorderLayout());
        left.setPreferredSize(new Dimension(Math.round(leftPanelWidth), Math.round(height)));

        JPanel right = new JPanel(new BorderLayout());
        right.setPreferredSize(new Dimension(Math.round(rightPanelWidth), Math.round(height)));


        //--------------------------------Border-=----------------------------------
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1, true);

        // ------------------------------Sub left panels-----------------------------
        float courseNamePanelWidth = width * 0.4f;
        float courseAcronymPanelWidth = width * 0.2f;
        
        JPanel courseName = new JPanel(new BorderLayout());
        courseName.setPreferredSize(new Dimension(Math.round(courseNamePanelWidth), Math.round(height)));
        courseName.setBorder(lineBorder);
        courseName.setBackground(Color.WHITE);

        JPanel courseAcronym = new JPanel(new BorderLayout());
        courseAcronym.setPreferredSize(new Dimension(Math.round(courseAcronymPanelWidth), Math.round(height)));
        courseAcronym.setBorder(lineBorder);
        courseAcronym.setBackground(Color.WHITE);

        JSplitPane subLeft = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, courseName, courseAcronym);
        subLeft.setResizeWeight(0.2);
        subLeft.setDividerSize(0);

        //-------------------------------Sub right panels----------------------------
        float courseCodePanelWidth = width * 0.2f;
        float courseCreditsPanelWidth = width * 0.2f;

        JPanel courseCode = new JPanel(new BorderLayout());
        courseCode.setPreferredSize(new Dimension(Math.round(courseCodePanelWidth), Math.round(height)));
        courseCode.setBorder(lineBorder);
        courseCode.setBackground(Color.WHITE);

        JPanel courseCredits = new JPanel(new BorderLayout());
        courseCredits.setPreferredSize(new Dimension(Math.round(courseCreditsPanelWidth), Math.round(height)));
        courseCredits.setBorder(lineBorder);
        courseCredits.setBackground(Color.WHITE);

        JSplitPane subRight = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, courseCode, courseCredits);
        subRight.setResizeWeight(0.5);
        subRight.setDividerSize(0);

        //-----------------------------------Labels-----------------------------------
        JLabel courseNameLabel = new JLabel(String.format("<html> %s </html>", name)); //html tag is used to help text wrap when out of space
        courseNameLabel.setFont(new Font("Roboto Mono", Font.PLAIN, fontsize));
        if (foreground) courseNameLabel.setForeground(new Color(78, 178, 165));

        JLabel courseAcronymLabel = new JLabel(acronym);
        courseAcronymLabel.setFont(new Font("Roboto Mono", Font.PLAIN, fontsize));
        if (foreground) courseAcronymLabel.setForeground(new Color(78, 178, 165));

        JLabel courseCodeLabel = new JLabel(id);
        courseCodeLabel.setFont(new Font("Roboto Mono", Font.PLAIN, fontsize));
        if (foreground) courseCodeLabel.setForeground(new Color(78, 178, 165));

        JLabel courseCreditsLabel = new JLabel(credits);
        courseCreditsLabel.setFont(new Font("Roboto Mono", Font.PLAIN, fontsize));
        if (foreground) courseCreditsLabel.setForeground(new Color(78, 178, 165));

        //-----------------------------------Adding-----------------------------------
        courseName.add(courseNameLabel, BorderLayout.CENTER);
        courseAcronym.add(courseAcronymLabel, BorderLayout.CENTER);
        courseCode.add(courseCodeLabel, BorderLayout.CENTER);
        courseCredits.add(courseCreditsLabel, BorderLayout.CENTER);

        left.add(subLeft, BorderLayout.CENTER);
        right.add(subRight, BorderLayout.CENTER);

        JSplitPane leftRight = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
        leftRight.setResizeWeight(0.4);
        leftRight.setDividerSize(0);

        add(leftRight, BorderLayout.CENTER);
    }
}