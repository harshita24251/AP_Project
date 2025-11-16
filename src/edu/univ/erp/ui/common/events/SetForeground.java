package edu.univ.erp.ui.common.events;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SetForeground extends MouseAdapter{
    JLabel label;

    public SetForeground(JLabel label){
        this.label = label; 
    }
    
    public void mouseClicked(MouseEvent e){
        label.setForeground(new Color(78, 178, 165)); //IIITD official color
    }
}