package edu.univ.erp.ui.common;

import java.awt.*;
import java.awt.image.*;
import java.awt.Image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.File;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.lang.Exception;
import java.io.IOException;
import java.util.Arrays;

public class Header extends JPanel{
    public Header(float width, float height, MouseAdapter m){
        this.setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        setLayout(new BorderLayout());

        //-------------------Putting IIITD Logo--------------
        Icon iiitLogo = new FlatSVGIcon("iiitd_logo.svg", 0.8f);

        Icon power = new FlatSVGIcon("power.svg", 0.45f);
        
        //-------------------Logo Label----------------------
        JLabel logoLabel = new JLabel(iiitLogo);
     
        JPanel leftLogoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 10));
        leftLogoPanel.add(logoLabel);
        leftLogoPanel.setBackground(Color.WHITE);

        JLabel logout = new JLabel("Logout", power, JLabel.LEFT);
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logout.setFont(new Font("Roboto Mono", Font.PLAIN, 16));
        logout.setBorder(new EmptyBorder(0, 0, 0, 30));
        logout.addMouseListener(new changeForegroundColor(logout));
        logout.addMouseListener(m);
//        logout.setBorder();

        this.add(leftLogoPanel, BorderLayout.WEST);
        this.add(logout, BorderLayout.EAST);
        this.setBackground(Color.WHITE);
    }

    private class changeForegroundColor extends MouseAdapter{
        JLabel label;
        public changeForegroundColor(JLabel label){
            this.label = label;
        }

        public void mouseEntered(MouseEvent e){
            label.setForeground(Color.RED);
        }

        public void mouseExited(MouseEvent e){
            label.setForeground(Color.BLACK);
        }
    }
}