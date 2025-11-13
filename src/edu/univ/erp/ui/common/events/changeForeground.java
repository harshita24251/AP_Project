package edu.univ.erp.ui.common.events;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class changeForeground extends MouseAdapter{
    JLabel label;
    Color oldForeground;
    public changeForeground(JLabel label){
        this.label = label;
        oldForeground = this.label.getForeground();    
    }
    
    public void mouseEntered(MouseEvent e){
        label.setForeground(new Color(78, 178, 165)); //IIITD official color
    }

    public void mouseExited(MouseEvent e){
        label.setForeground(oldForeground);
    }
}