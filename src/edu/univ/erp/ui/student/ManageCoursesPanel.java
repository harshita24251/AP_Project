package edu.univ.erp.ui.student;

import edu.univ.erp.ui.common.events.*;
import edu.univ.erp.api.catalog.*;
import edu.univ.erp.api.student.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.border.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.*;
import java.util.HashMap;

public class ManageCoursesPanel extends JPanel{
    public ManageCoursesPanel(float width, float height){
        setMaximumSize(new Dimension(Math.round(width), Math.round(height)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //--------------------------styling tabs-----------------------------------
        UIManager.put("TabbedPane.underlineColor", new Color(78, 178, 165));
        UIManager.put("TabbedPane.inactiveUnderlineColor", new Color(78, 178, 165));

        //---------------------------------------------------------------------------
        JTabbedPane pane = new JTabbedPane();
        pane.addTab("Browse Courses", null);
        pane.addTab("Register Courses", null);
        pane.setVisible(true);
        pane.setBackground(Color.WHITE);
        JPanel masterPanel = new JPanel(new BorderLayout()); //parent panel
        pane.setComponentAt(0, masterPanel);
        
        setBackground(Color.WHITE);
        add(pane);
    }
} //#4eb2a5