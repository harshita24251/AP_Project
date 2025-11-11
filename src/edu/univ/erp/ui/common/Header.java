package edu.univ.erp.ui.common;

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

public class Header extends JPanel{
    public Header(float width, float height){
        this.setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        setLayout(new BorderLayout());

        //-------------------Putting IIITD Logo--------------
        Icon iiitLogo = new FlatSVGIcon("iiitd_logo.svg", 0.8f);
        
        //-------------------Logo Label----------------------
        JLabel logoLabel = new JLabel(iiitLogo);
     
        JPanel leftLogoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 10));
        leftLogoPanel.add(logoLabel);
        leftLogoPanel.setBackground(Color.WHITE);

        this.add(leftLogoPanel, BorderLayout.WEST);
        this.setBackground(Color.WHITE);
    }
}