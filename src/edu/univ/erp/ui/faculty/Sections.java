package edu.univ.erp.ui.faculty;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import edu.univ.erp.api.instructor.*;
import java.util.ArrayList;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;

public class Sections extends JPanel{
    public Sections(float width, float height){
        setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //--------------------------styling tabs-----------------------------------
        UIManager.put("TabbedPane.underlineColor", new Color(78, 178, 165));
        UIManager.put("TabbedPane.inactiveUnderlineColor", new Color(78, 178, 165));

        JPanel widgets = new JPanel(new BorderLayout());
        widgets.setPreferredSize(new Dimension(Math.round(width), Math.round(height * 0.05f)));
        widgets.setBorder(new EmptyBorder(10, 20, 0, 20));
        widgets.setBackground(Color.WHITE);

        //---------------------------------------widgets------------------------------------------

        //----------------------------------------------------------------------------------------

        JPanel MainPanel = new JPanel();
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
        MainPanel.setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
//        widgets.setBackground(Color.GRAY);

        ArrayList<ArrayList<String>> sections = SectionDetails.fetch();

        JTabbedPane mainPane = new JTabbedPane();
        MainPanel.add(widgets);

        for (ArrayList<String> arr: sections){
            MainPanel.add(new StudentsListPanel(width, height, "CSE101"));
            mainPane.add(arr.get(1), MainPanel);
        }

        add(mainPane);
        setBackground(Color.WHITE);
    }
}