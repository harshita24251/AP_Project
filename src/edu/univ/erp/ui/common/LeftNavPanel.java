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
import edu.univ.erp.ui.common.events.*;
import java.util.HashMap;

public class LeftNavPanel extends JPanel{
    public LeftNavPanel(String panelTitle, String[] selectables, float width, float height, int fontSize, HashMap<String, MouseAdapter> registerMouseClicked){
        setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        //-----------------------Loading Font--------------------
        GraphicsEnvironment ge;
        try{
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Roboto-Light.ttf")));
        }
        catch (IOException | FontFormatException e){}
        //========================================================
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setPreferredSize(new Dimension(Math.round(width), 30));
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
        btnPanel.setPreferredSize(new Dimension(Math.round(width), Math.round(height - 30)));
        //-------------------------Icons--------------------------
        Icon bullet = new FlatSVGIcon("bullets.svg", 0.25f);

        //-------------------------Labels-------------------------
        JLabel title = new JLabel(panelTitle);
        title.setFont(new Font("Roboto Mono", Font.PLAIN, fontSize-4));

        //-----------------------Adding Labels---------------------
        titlePanel.add(title, BorderLayout.WEST);

        for (String s : selectables){
            JLabel putLabel = new JLabel(s, bullet, JLabel.LEFT);
            putLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            putLabel.setFont(new Font("Roboto Mono", Font.PLAIN, fontSize));
            putLabel.addMouseListener(new changeForeground(putLabel));
            putLabel.addMouseListener(registerMouseClicked.get(s));
            btnPanel.add(putLabel);
            btnPanel.add(Box.createRigidArea(new Dimension(30, 10)));
        }

        titlePanel.setBackground(Color.WHITE);
        btnPanel.setBackground(Color.WHITE);

        this.add(titlePanel);
        this.add(btnPanel);
        this.setBackground(Color.WHITE);
        setMaximumSize(new Dimension(700, 200));
        // System.out.println(getMaximumSize().getHeight());
    }
}