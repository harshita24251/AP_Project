package edu.univ.erp.ui.student;

import edu.univ.erp.ui.common.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.lang.Exception;
import java.io.IOException;
import java.util.Arrays;

public class Dashboard extends JFrame{
    public Dashboard(){
        //-----------------------getting dimensions------------------------
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension size = kit.getScreenSize();
        int width = (int) size.getWidth();
        int height = (int) size.getHeight();

        //-----------------------setting header----------------------------
        float headerHeight = height * 0.08f;
        float headerWidth = width;
        Header header = new Header(headerWidth, headerHeight);

        //------------------------Left Panel-------------------------------
        float navHeight = height * 0.225f;
        float navWidth = width * 0.15f;

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(Math.round(navWidth), Math.round(height * 0.92f)));


        String[] academic = {"Grades", "Manage Course", "Dual Degree"};
        String[] administration = {"Fee Details", "Student Requests", "Hostel Requests"};
        String[] special = {"TA Details", "Project Registration"};

        LeftNavPanel academicSection = new LeftNavPanel("Academics", academic, navWidth-30, navHeight, 16);
        LeftNavPanel administrationSection = new LeftNavPanel("Administration", administration, navWidth-30, navHeight, 16);
        LeftNavPanel specialSection = new LeftNavPanel("Special", special, navWidth-30, navHeight, 16);

        //--------------------------Right Panel-----------------------------
        float rightPanelWidth = width * 0.85f;
        float rightPanelHeight = height * 0.92f;

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(Math.round(rightPanelWidth), Math.round(rightPanelHeight)));

        //------------------------Sub Panels of Right Panel------------------
        float rightTopPanelWidth = rightPanelWidth;
        float rightTopPanelHeight = rightPanelHeight * 0.5f;

        JPanel rightTopPanel = new JPanel(new BorderLayout());
        rightTopPanel.setPreferredSize(new Dimension(Math.round(rightTopPanelWidth), Math.round(rightTopPanelHeight)));
        JPanel rightBottomPanel = new JPanel(new BorderLayout());
        rightBottomPanel.setPreferredSize(new Dimension(Math.round(rightPanelWidth * 0.5f), Math.round(rightPanelHeight * 0.5f)));

        //------------------------Sub Panels of Right Top Panel--------------
        float rightTopLeftPanelWidth = rightTopPanelWidth * 0.5f;
        float rightTopLeftPanelHeight = rightTopPanelHeight;

        JPanel rightTopLeftPanel = new JPanel();
        rightTopLeftPanel.setPreferredSize(new Dimension(Math.round(rightTopLeftPanelWidth), Math.round(rightTopLeftPanelHeight)));

        JPanel rightTopRightPanel = new JPanel(new BorderLayout());
        float rightTopRightPanelWidth = rightTopPanelWidth * 0.5f;
        float rightTopRightPanelHeight = rightTopPanelHeight;
        rightTopRightPanel.setPreferredSize(new Dimension(Math.round(rightTopRightPanelWidth), Math.round(rightTopRightPanelHeight)));

        /**
         * rightTopLeftPanel : Profile card
         */
        NameCard nameOfStudent = new NameCard("Lorem Ipsum", "Student", 15, 15, rightTopLeftPanelWidth, rightTopLeftPanelHeight * 0.15f);


        //---------------------------JFree CGPA Line graph--------------------

        //-------------------------adding to frame----------------------------
        rightTopRightPanel.add(nameOfStudent, BorderLayout.NORTH);
        rightTopRightPanel.add(Box.createRigidArea(new Dimension(10, 3)));
        rightTopRightPanel.setBackground(Color.WHITE);

        leftPanel.add(Box.createRigidArea(new Dimension(10, 3)));
        leftPanel.add(academicSection);
        leftPanel.add(administrationSection);
        leftPanel.add(specialSection);

        rightTopPanel.add(rightTopLeftPanel, BorderLayout.WEST);
        rightTopPanel.add(rightTopRightPanel, BorderLayout.EAST);

        rightPanel.add(rightTopPanel, BorderLayout.NORTH);
        rightPanel.add(rightBottomPanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension((int)size.getWidth(), (int) size.getHeight()));

    }
}