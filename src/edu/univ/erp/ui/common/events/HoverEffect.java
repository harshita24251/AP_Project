package edu.univ.erp.ui.common.events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class HoverEffect extends MouseAdapter {
    ArrayList<JPanel> subPanels = null;
    Color oldBackground;

    public HoverEffect(ArrayList<JPanel> subPanels){
        oldBackground = subPanels.get(0).getBackground();
        this.subPanels = subPanels;
    }

    public void mouseEntered(MouseEvent e){
        for (JPanel panel : subPanels){
            panel.setBackground(new Color(200, 236, 231));
        }
    }

    public void mouseExited(MouseEvent e){
        for (JPanel panel : subPanels){
            panel.setBackground(oldBackground);
        }
    }
}
