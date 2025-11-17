package edu.univ.erp.ui.common.events;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import edu.univ.erp.ui.student.ComponentGradeCard;

/**
 * highlighter HashMap is to group labels so that only one is shown selected at a time
 */

public class changeForegroundCourse extends MouseAdapter{
    JLabel label;
    JPanel coursePanel;
    String Course_ID;
    Color oldForeground;
    Dimension oldMaxSize;
    Boolean arrow; //true if it is right_arrow
    HashMap<JLabel, Boolean> highlighter = new HashMap<>();
    Icon right_arrow = new FlatSVGIcon("right-arrow.svg", 0.25f);
    Icon down_arrow = new FlatSVGIcon("down-arrow.svg", 0.25f);
    JPanel componentPanel;

    public changeForegroundCourse(JLabel label, HashMap<JLabel, Boolean> highlighter, JPanel coursePanel, String Course_ID){
        this.label = label;
        oldForeground = this.label.getForeground();
        oldMaxSize = new Dimension(Integer.MAX_VALUE, 30); //warning : hardcoded values used here
        this.highlighter = highlighter;
        this.coursePanel = coursePanel;
        this.arrow = true;
        this.Course_ID = Course_ID;
        componentPanel = new ComponentGradeCard(Course_ID);
        coursePanel.add(componentPanel, BorderLayout.CENTER);
        componentPanel.setVisible(false);
    }
    
    public void mouseEntered(MouseEvent e){
        label.setForeground(new Color(78, 178, 165)); //IIITD official color
    }

    public void mouseExited(MouseEvent e){
        if (highlighter.get(e.getComponent()).equals(false))
            label.setForeground(oldForeground);
    }

    public void mouseClicked(MouseEvent e){
        if (arrow == false){
            coursePanel.setMaximumSize(oldMaxSize);
            componentPanel.setVisible(false);
            coursePanel.revalidate();
            label.setIcon(right_arrow);
            arrow = true;
        }
        else{
            coursePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150)); //warning : hardcoded values used here
            componentPanel.setVisible(true);
            coursePanel.revalidate();
            label.setIcon(down_arrow);
            arrow = false;
        }
        for (JLabel label : highlighter.keySet()){
            if (e.getComponent() != label){
                label.setForeground(oldForeground);
                highlighter.put(label, false);
            }
        }
        label.setForeground(new Color(78, 178, 165));
        highlighter.put(label, true);
    }
}