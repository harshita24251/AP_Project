package edu.univ.erp.ui.faculty;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CoursesPanel extends JPanel{
    public CoursesPanel(float width, float height){
        setPreferredSize(new Dimension(Math.round(width), Math.round(height)));

        JTabbedPane mainPane = new JTabbedPane();

        setBackground(Color.WHITE);
        add(mainPane);
    }
}