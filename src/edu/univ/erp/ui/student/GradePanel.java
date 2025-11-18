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


            JPanel masterPanel = new JPanel(new BorderLayout()); //parent panel to accomodate coursePane and bottomBar
            
            //---------------------------------Course Pane----------------------------------
            JScrollPane coursePane = new JScrollPane(new CourseGradePanel(i, width, height));
            coursePane.setBorder(null);
            coursePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


            //----------------------------------Bottom Bar----------------------------------
            JPanel bottomBar = new JPanel(new BorderLayout()); // For downloading the transcipt and other useful operations
            bottomBar.setPreferredSize(new Dimension(Math.round(width), Math.round(height * 0.09f)));
            bottomBar.setBackground(Color.WHITE);

            //--------------------------------SubBottom Panels------------------------------
            JPanel cgpaSGPA = new JPanel(new BorderLayout());
            cgpaSGPA.setBackground(Color.WHITE);
            JPanel downloadPanel = new JPanel(new BorderLayout());
            downloadPanel.setBackground(Color.WHITE);

                //------------------------------PDF Icon------------------------------------
                Icon pdfIcon = new FlatSVGIcon("pdf-document.svg", 0.45f);
                JButton download = new JButton("Download Transcript", pdfIcon);

                //-------------------------CGPA and SGPA Label-----------------------------
                JLabel cgsgLabel = new JLabel("  CGPA : 9.00 | SGPA : 9.00  ");
                cgsgLabel.setFont(new Font("Roboto Mono", Font.PLAIN, 20));
            
            downloadPanel.add(download, BorderLayout.CENTER);
            cgpaSGPA.add(cgsgLabel, BorderLayout.CENTER);

            //-----------------------------------Adding-------------------------------------
            Border lineBorder = BorderFactory.createLineBorder(new Color(181, 179, 179), 1, true);
            bottomBar.setBorder(new EmptyBorder(0, 20, 20, 20));
            cgpaSGPA.setBorder(lineBorder);
            // cgpaSGPA.setBorder(new EmptyBorder(0, 10, 0, 10));
            bottomBar.add(cgpaSGPA, BorderLayout.WEST);
            bottomBar.add(download, BorderLayout.EAST);
            
            masterPanel.add(coursePane, BorderLayout.CENTER);
            masterPanel.add(bottomBar, BorderLayout.SOUTH);
            pane.setComponentAt(i-1, masterPanel);
            
        }

        
        setBackground(Color.WHITE);
        add(pane);
    }
} //#4eb2a5