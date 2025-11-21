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
import java.lang.String;

public class ManageCoursesPanel extends JPanel{
    HashMap<JPanel, String> searchByCode = new HashMap<>(); //To search by category
    JLabel courseCount = new JLabel();
    // HashMap<JCheckbox, String> checkBoxName = new HashMap<>();
    HashMap<String, Boolean> checkBoxStatus = new HashMap<>(); //with index CSE, ECE, MTH, BIO, DES, SSH, OTHERS

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
        ofScrollPane.setBackground(Color.WHITE);

        int totalVisibleCourses = 0;
        for (ArrayList<String> arr : allCourseDetails.fetch()){
            JPanel coursePalette = new CoursePalette(width, height * 0.1f, arr.get(3), arr.get(1), arr.get(0), arr.get(2), 16, false);
            ofScrollPane.add(coursePalette);
            if (arr.get(0).substring(0, 3).equals("ECO") || arr.get(0).substring(0, 3).equals("COM")){
                searchByCode.put(coursePalette, "OTH");
            }
            else{
                searchByCode.put(coursePalette, arr.get(0).substring(0, 3));
            }
            totalVisibleCourses++;
        }

        courseCount.setText(String.format("Course Count : %d ", totalVisibleCourses));

        JScrollPane insideCoursePalettePanel = new JScrollPane(ofScrollPane);
        insideCoursePalettePanel.getVerticalScrollBar().setUnitIncrement(9);
        insideCoursePalettePanel.getViewport().setBackground(Color.WHITE);
        coursePalettePanel.add(insideCoursePalettePanel);

        //---------------------------------------Search Panel--------------------------------------------
        JPanel searchBar = new JPanel(new FlowLayout());
        searchBar.setPreferredSize(new Dimension(Math.round(width), Math.round(navBarHeight * 0.3f)));
        searchBar.setBackground(Color.WHITE);

        searchBar.add(courseCount);

            //------------------------------checkbox icon-------------------------------------
            Icon ticked = new FlatSVGIcon("checkbox-ticked.svg", 0.3f);
            Icon unticked = new FlatSVGIcon("checkbox-unticked.svg", 0.3f);

        JCheckBox cse = new JCheckBox("CSE");
        cse.setSelectedIcon(ticked);
        cse.setIcon(unticked);
        searchBar.add(cse);
        cse.addItemListener(new showCatergories("CSE", checkBoxStatus));
        checkBoxStatus.put("CSE", false);

        JCheckBox ece = new JCheckBox("ECE");
        searchBar.add(ece);
        ece.setSelectedIcon(ticked);
        ece.setIcon(unticked);
        checkBoxStatus.put("ECE", false);
        ece.addItemListener(new showCatergories("ECE", checkBoxStatus));
        
        JCheckBox mth = new JCheckBox("MTH");
        searchBar.add(mth);
        mth.setSelectedIcon(ticked);
        mth.setIcon(unticked);
        checkBoxStatus.put("MTH", false);
        mth.addItemListener(new showCatergories("MTH", checkBoxStatus));
        
        JCheckBox bio = new JCheckBox("BIO");
        searchBar.add(bio);
        bio.setSelectedIcon(ticked);
        bio.setIcon(unticked);
        checkBoxStatus.put("BIO", false);
        bio.addItemListener(new showCatergories("BIO", checkBoxStatus));
        
        JCheckBox des = new JCheckBox("DES");
        searchBar.add(des);
        des.setSelectedIcon(ticked);
        des.setIcon(unticked);
        checkBoxStatus.put("DES", false);
        des.addItemListener(new showCatergories("DES", checkBoxStatus));
        
        JCheckBox ssh = new JCheckBox("SSH");
        searchBar.add(ssh);
        ssh.setSelectedIcon(ticked);
        ssh.setIcon(unticked);
        checkBoxStatus.put("SSH", false);
        ssh.addItemListener(new showCatergories("SSH", checkBoxStatus));
        
        JCheckBox others = new JCheckBox("OTHERS");
        searchBar.add(others);
        others.setSelectedIcon(ticked);
        others.setIcon(unticked);
        checkBoxStatus.put("OTH", false);
        others.addItemListener(new showCatergories("OTH", checkBoxStatus));

        //---------------------------------------Sub navBar panels---------------------------------------


        navBar.add(searchBar, BorderLayout.NORTH);
        navBar.add(new CoursePalette(width, navBarHeight * 0.7f, "Course Name", "Course Acronym", "Course Code", "Course Credits", 16, true), BorderLayout.CENTER);
        masterBrowseCourses.add(navBar, BorderLayout.NORTH);
        masterBrowseCourses.add(coursePalettePanel, BorderLayout.CENTER);

        pane.addTab("Browse Courses", masterBrowseCourses);
        
        setBackground(Color.WHITE);
        add(pane, BorderLayout.CENTER);
    }

    private class showCatergories implements ItemListener{
        String code = "";
        HashMap<String,Boolean> checkBoxStatus = new HashMap<>();
        Boolean allUnchecked = true; // if every checkbox is unchecked then full course list should be shown
        int totalVisibleCourses = 0;

        public showCatergories(String code, HashMap<String ,Boolean> checkBoxStatus){
            this.checkBoxStatus = checkBoxStatus;
            this.code = code;
        }

        public void itemStateChanged(ItemEvent e){
            totalVisibleCourses = 0;
            allUnchecked = true;
            checkBoxStatus.put(code, !checkBoxStatus.get(code));
            ArrayList<String> truecheckBoxes = new ArrayList<>();

            for (String checkBox : checkBoxStatus.keySet()){
                if (checkBoxStatus.get(checkBox) == true){
                    truecheckBoxes.add(checkBox);
                    allUnchecked = false;
                }
            }

            for (JPanel panel : searchByCode.keySet()){
                if (truecheckBoxes.contains(searchByCode.get(panel))){
                    panel.setVisible(true);
                    totalVisibleCourses++;
                }
                else{
                    panel.setVisible(false);
                }
            }

            if (allUnchecked == true){
                for (JPanel panel : searchByCode.keySet()){
                    panel.setVisible(true);
                    totalVisibleCourses++;
                }
            }

            courseCount.setText(String.format("Course Count : %d ", totalVisibleCourses));
        }
    }
} //#4eb2a5