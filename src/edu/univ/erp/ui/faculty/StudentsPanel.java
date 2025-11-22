package edu.univ.erp.ui.faculty;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentsPanel extends JPanel{
    public StudentsPanel(float width, float height){
        setPreferredSize(new Dimension(Math.round(width), Math.round(height)));

        JTabbedPane mainPane = new JTabbedPane();
        mainPane.add("Students", new JButton("hi"));

        setBackground(Color.WHITE);
        add(mainPane);
    }
}