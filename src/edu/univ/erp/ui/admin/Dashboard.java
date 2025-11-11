package edu.univ.erp.ui.admin;

import edu.univ.erp.ui.common.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.image.io.*;
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
        float headerHeight = height * 0.1f;
        float headerWidth = width;
        Header header = new Header(headerWidth, headerHeight);

        //------------------------Left Panel-------------------------------
        float navHeight = height * 0.225f;
        float navWidth = width * 0.17f;

        JPanel leftPanel = new JPanel(new BorderLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(Math.round(navWidth), Math.round(height * 0.9f)));


        String[] academic = {"Students", "Faculty", "Admins", "Courses"};

        LeftNavPanel academicSection = new LeftNavPanel("Academics", academic, navWidth, navHeight, 16);

        //-------------------------adding to frame----------------------------
        leftPanel.add(Box.createRigidArea(new Dimension(10, 3)));
        leftPanel.add(academicSection);
        
        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}