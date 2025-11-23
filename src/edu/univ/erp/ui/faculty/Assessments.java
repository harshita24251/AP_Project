package edu.univ.erp.ui.faculty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import edu.univ.erp.api.instructor.*;
import java.util.ArrayList;

public class Assessments extends JPanel{
    public Assessments(float width, float height){
        setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

         //--------------------------styling tabs-----------------------------------
        UIManager.put("TabbedPane.underlineColor", new Color(78, 178, 165));
        UIManager.put("TabbedPane.inactiveUnderlineColor", new Color(78, 178, 165));

        ArrayList<ArrayList<String>> sections = SectionDetails.fetch();

        JTabbedPane mainPane = new JTabbedPane();
        for (ArrayList<String> arr: sections){
            mainPane.add(arr.get(1), new JLabel("yes"));
        }

        add(mainPane);
        setBackground(Color.WHITE);
    }
}