package edu.univ.erp.ui.common.events;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonHover extends MouseAdapter{
    JButton button = null;
    Icon icon = null;
    Icon originalIcon = null;

    public ButtonHover(JButton tmp, Icon icon){
        this.button = tmp;
        this.originalIcon = tmp.getIcon();
        this.icon = icon;
    }

    public void mouseEntered(MouseEvent e){
        button.setIcon(icon);
    }

    public void mouseExited(MouseEvent e){
        button.setIcon(originalIcon);
    }
}
