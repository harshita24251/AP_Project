package edu.univ.erp.ui.common.events;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import com.formdev.flatlaf.extras.FlatSVGIcon;

/**
 * highlighter HashMap is to group labels so that only one is shown selected at a time
 */

public class changeForegroundCourse extends MouseAdapter{
    JLabel label;
    Color oldForeground;
    HashMap<JLabel, Boolean> highlighter = new HashMap<>();
    Icon right_arrow = new FlatSVGIcon("right-arrow.svg", 0.25f);
    Icon down_arrow = new FlatSVGIcon("down-arrow.svg", 0.25f);

    public changeForegroundCourse(JLabel label, HashMap<JLabel, Boolean> highlighter){
        this.label = label;
        oldForeground = this.label.getForeground();
        this.highlighter = highlighter;
    }
    
    public void mouseEntered(MouseEvent e){
        label.setForeground(new Color(78, 178, 165)); //IIITD official color
    }

    public void mouseExited(MouseEvent e){
        if (highlighter.get(e.getComponent()).equals(false))
            label.setForeground(oldForeground);
    }

    public void mouseClicked(MouseEvent e){
        for (JLabel label : highlighter.keySet()){
            if (e.getComponent() != label){
                label.setForeground(oldForeground);
                highlighter.put(label, false);
                label.setIcon(right_arrow);
            }
        }
        label.setIcon(down_arrow);
        label.setForeground(new Color(78, 178, 165));
        highlighter.put(label, true);
    }
}