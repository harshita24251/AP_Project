package edu.univ.erp.ui.faculty;

import javax.security.auth.login.CredentialException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import edu.univ.erp.api.instructor.*;
import java.util.ArrayList;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;

public class Assessments extends JPanel{
    public Assessments(float width, float height){
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
        Icon plus = new FlatSVGIcon("plus.svg", 0.25f);
        Icon  cross = new FlatSVGIcon("cross.svg", 0.25f);

        JButton createAssessment = new JButton("New Assessment", plus);
        widgets.add(createAssessment, BorderLayout.WEST);

        widgets.remove(createAssessment);

        JButton deleteAssessment = new JButton("Remove", cross);
        widgets.add(deleteAssessment, BorderLayout.WEST);
//        deleteAssessment.setVisible(false);

//        widgets.add(createAssessment, BorderLayout.WEST);
        createAssessment.revalidate();
        createAssessment.repaint();
        //----------------------------------------------------------------------------------------

        JPanel MainPanel = new JPanel();
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
        MainPanel.setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
//        widgets.setBackground(Color.GRAY);

        ArrayList<ArrayList<String>> sections = SectionDetails.fetch();

        JTabbedPane mainPane = new JTabbedPane();
        MainPanel.add(widgets);

        for (ArrayList<String> arr: sections){
            MainPanel.add(new AssessmentsListPanel(width, height, arr.get(1)));
            mainPane.add(arr.get(1), MainPanel);
        }

        add(mainPane);
        setBackground(Color.WHITE);
    }
}