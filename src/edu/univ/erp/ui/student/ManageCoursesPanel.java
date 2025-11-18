package edu.univ.erp.ui.student;

import edu.univ.erp.ui.common.events.*;
import edu.univ.erp.ui.common.*;
import edu.univ.erp.api.catalog.*;
import edu.univ.erp.api.student.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.border.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.*;
import java.util.HashMap;

public class ManageCoursesPanel extends JPanel{
    public ManageCoursesPanel(float width, float height){
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(Math.round(width), Math.round(height)));

        //--------------------------styling tabs-----------------------------------
        UIManager.put("TabbedPane.underlineColor", new Color(78, 178, 165));
        UIManager.put("TabbedPane.inactiveUnderlineColor", new Color(78, 178, 165));

        //---------------------------------------------------------------------------
        JTabbedPane pane = new JTabbedPane();
        pane.setVisible(true);
        pane.setBackground(Color.WHITE);

        //---------------------------------------Browse Courses Panel------------------------------------
        JPanel masterBrowseCourses = new JPanel(new BorderLayout()); //parent panel
        masterBrowseCourses.setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        masterBrowseCourses.setBackground(Color.WHITE);

        // navBar : have checkboxes and a search bar
        float navBarHeight = height * 0.1f;
        JPanel navBar = new JPanel(new BorderLayout());
        navBar.setPreferredSize(new Dimension(Math.round(width), Math.round(navBarHeight)));
        navBar.setBackground(Color.WHITE);

        JPanel coursePalettePanel = new JPanel(new BorderLayout());
        coursePalettePanel.setPreferredSize(new Dimension(Math.round(width), Math.round(height * 0.9f)));

        JPanel ofScrollPane = new JPanel();
        ofScrollPane.setLayout(new BoxLayout(ofScrollPane, BoxLayout.Y_AXIS));

        for (ArrayList<String> arr : allCourseDetails.fetch()){
            ofScrollPane.add(new CoursePalette(width, height * 0.1f, arr.get(3), arr.get(1), arr.get(0), arr.get(2), 16, false));
            ofScrollPane.setBorder(new EmptyBorder(10, 0, 0, 0));
        }
        for (ArrayList<String> arr : allCourseDetails.fetch()){
            ofScrollPane.add(new CoursePalette(width, height * 0.1f, arr.get(3), arr.get(1), arr.get(0), arr.get(2), 16, false));
            ofScrollPane.setBorder(new EmptyBorder(10, 0, 0, 0));
        }
        for (ArrayList<String> arr : allCourseDetails.fetch()){
            ofScrollPane.add(new CoursePalette(width, height * 0.1f, arr.get(3), arr.get(1), arr.get(0), arr.get(2), 16, false));
            ofScrollPane.setBorder(new EmptyBorder(10, 0, 0, 0));
        }

        JScrollPane insideCoursePalettePanel = new JScrollPane(ofScrollPane);
        insideCoursePalettePanel.getVerticalScrollBar().setUnitIncrement(9);
        coursePalettePanel.add(insideCoursePalettePanel);

        //---------------------------------------Sub navBar panels---------------------------------------
        JPanel searchBar = new JPanel(new BorderLayout());
        searchBar.setPreferredSize(new Dimension(Math.round(width), Math.round(navBarHeight * 0.3f)));
        searchBar.setBackground(Color.WHITE);


        navBar.add(searchBar, BorderLayout.NORTH);
        navBar.add(new CoursePalette(width, navBarHeight * 0.7f, "Course Name", "Course Acronym", "Course Code", "Course Credits", 16, true), BorderLayout.CENTER);
        masterBrowseCourses.add(navBar, BorderLayout.NORTH);
        masterBrowseCourses.add(coursePalettePanel, BorderLayout.CENTER);

        pane.addTab("Browse Courses", masterBrowseCourses);
        
        setBackground(Color.WHITE);
        add(pane, BorderLayout.CENTER);
    }
} //#4eb2a5