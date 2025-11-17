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

public class GradePanel extends JPanel{
    public GradePanel(float width, float height){
        setMaximumSize(new Dimension(Math.round(width), Math.round(height)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //--------------------------styling tabs-----------------------------------
        UIManager.put("TabbedPane.underlineColor", new Color(78, 178, 165));
        UIManager.put("TabbedPane.inactiveUnderlineColor", new Color(78, 178, 165));
        //---------------------------------------------------------------------------

        JTabbedPane pane = new JTabbedPane();
        for (int i = 1; i < totalSemesters.fetch() + 1; i++){
            pane.addTab(String.format("Semester %s", i), new JButton("ok"));
            pane.setVisible(true);
            pane.setBackground(Color.WHITE);

            JScrollPane coursePane = new JScrollPane(new CourseGradePanel(i, width, height));
            coursePane.setBorder(null);
            coursePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            pane.setComponentAt(i-1, coursePane);
            
        }

        
        setBackground(Color.WHITE);
        add(pane);
    }
} //#4eb2a5