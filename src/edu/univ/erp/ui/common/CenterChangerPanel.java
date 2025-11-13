package edu.univ.erp.ui.common;

import javax.swing.*;
import java.awt.*;

/**
 * this panel appears in the middle of the window and helps change page
 */

public class CenterChangerPanel extends JPanel{
    public CenterChangerPanel(float width, float height){
        setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        setLayout(new BorderLayout());
    }
}