package edu.univ.erp.ui.faculty;

import edu.univ.erp.ui.common.*;
import edu.univ.erp.ui.common.events.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.lang.Exception;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.border.EmptyBorder;
import org.knowm.xchart.*;
import org.knowm.xchart.style.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Dashboard extends JFrame{
    private JPanel changerPanel; //Panel which changes when button is clicked
    private CardLayout changingLayout;
    public Dashboard(){
        FlatLightLaf.setup();
        //-----------------------getting dimensions------------------------
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension size = kit.getScreenSize();
        int width = (int) size.getWidth();
        int height = (int) size.getHeight();

        //-----------------------setting header----------------------------
        float headerHeight = height * 0.08f;
        float headerWidth = width;
        Header header = new Header(headerWidth, headerHeight);

        //--------------------------Right Panel-----------------------------
        float rightPanelWidth = width * 0.85f;
        float rightPanelHeight = height * 0.92f; //0.92f

        // //changing panels---------------------------------------------------
        // JPanel gradesPanel = new GradePanel(rightPanelWidth, rightPanelHeight);
        // JPanel manageCoursesPanel = new ManageCoursesPanel(rightPanelWidth, rightPanelHeight);
        
        HashMap<String, MouseAdapter> registerListener = new HashMap<>();
        // registerListener.put("Grades", new goToGrades(gradesPanel));
        // registerListener.put("Manage Courses", new goToMangeCourses(manageCoursesPanel));


        //------------------------Left Panel-------------------------------
        float navHeight = height * 0.225f;
        float navWidth = width * 0.15f;

        

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(Math.round(navWidth), Math.round(height * 0.92f)));
        leftPanel.setBackground(Color.WHITE);

        
        HashMap<JLabel, Boolean> highlighter = new HashMap<>(); //Used to highlight the clicked label
        
        Icon icon = new FlatSVGIcon("dashboard.svg", 0.36f);
        JPanel dashboardLabel = new JPanel(new BorderLayout());
        JLabel dashboardName = new JLabel("Dashboard", icon, JLabel.LEFT);
        dashboardName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dashboardName.setFont(new Font("Roboto Mono", Font.PLAIN, 16));
        dashboardLabel.setBorder(new EmptyBorder(15, 0, 0, 0));
        dashboardLabel.add(dashboardName, BorderLayout.WEST);
        dashboardLabel.setBackground(Color.WHITE);
        dashboardLabel.setMaximumSize(new Dimension(198, 35));
        highlighter.put(dashboardName, false);
        dashboardName.addMouseListener(new changeForeground(dashboardName, highlighter));
        dashboardName.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                changingLayout.show(changerPanel, "rightPanel");
            }
        });
        //----------------------------------Changing Panels-----------------------------
        JPanel studentsPanel = new StudentsPanel(rightPanelWidth, rightPanelHeight);
        registerListener.put("Students", new goToStudents());

        JPanel coursesPanel = new CoursesPanel(rightPanelWidth, rightPanelHeight);
        registerListener.put("Courses", new goToCourses());

        String[] academic = {"Students", "Assessments", "My Courses"};

        LeftNavPanel academicSection = new LeftNavPanel("Academics", academic, navWidth-30, navHeight, 16, registerListener, highlighter);

        //-------------------------------------------------------------------

        changingLayout = new CardLayout();
        changerPanel = new CenterChangerPanel(rightPanelWidth, rightPanelHeight);
        changerPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        changerPanel.setLayout(changingLayout);
        changerPanel.setBackground(Color.WHITE);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(Math.round(rightPanelWidth), Math.round(rightPanelHeight)));

        //------------------------Sub Panels of Right Panel------------------
        float rightTopPanelWidth = rightPanelWidth;
        float rightTopPanelHeight = rightPanelHeight * 0.3f;

        JPanel rightTopPanel = new JPanel(new BorderLayout());
        rightTopPanel.setPreferredSize(new Dimension(Math.round(rightTopPanelWidth), Math.round(rightTopPanelHeight)));
        JPanel rightBottomPanel = new JPanel(new BorderLayout());
        rightBottomPanel.setPreferredSize(new Dimension(Math.round(rightPanelWidth), Math.round(rightPanelHeight * 0.5f)));



        //-------------------------event handling-----------------------------

        //-------------------------adding to frame----------------------------

        // leftPanel.add(Box.createRigidArea(new Dimension(10, 3)));
        leftPanel.add(dashboardLabel);
        leftPanel.add(academicSection);

        // rightTopPanel.add(rightTopLeftPanel, BorderLayout.WEST);
        // rightTopPanel.add(rightTopRightPanel, BorderLayout.CENTER);
        // rightTopPanel.add(split, BorderLayout.CENTER);

        rightPanel.add(Box.createRigidArea(new Dimension(10, 3)));
        rightPanel.add(rightTopPanel, BorderLayout.NORTH);
        rightPanel.add(rightBottomPanel, BorderLayout.CENTER );
        rightPanel.setBackground(Color.WHITE);

        //-----------------adding panels to cardlayout changingLayout----------------
        changerPanel.add(rightPanel, BorderLayout.CENTER);
        changerPanel.add(studentsPanel, BorderLayout.CENTER);
        changerPanel.add(coursesPanel, BorderLayout.CENTER);
        // changerPanel.add(manageCoursesPanel, BorderLayout.CENTER);

        changingLayout.addLayoutComponent(rightPanel, "rightPanel");
        changingLayout.addLayoutComponent(studentsPanel, "studentsPanel");
        changingLayout.addLayoutComponent(coursesPanel, "coursesPanel");
        // changingLayout.addLayoutComponent(manageCoursesPanel, "manageCoursesPanel");
        //---------------------------------------------------------------------------

        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(changerPanel, BorderLayout.CENTER);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension((int)size.getWidth(), (int) size.getHeight()));

    }

    private class goToStudents extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            changingLayout.show(changerPanel, "studentsPanel");
        }
    }

    private class goToCourses extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            changingLayout.show(changerPanel, "coursesPanel");
        }
    }

    // private class goToMangeCourses extends MouseAdapter{
    //     JPanel toGo;
    //     public goToMangeCourses(JPanel toGo){
    //         this.toGo = toGo;
    //     }
    //     public void mouseClicked(MouseEvent e){
    //         changingLayout.show(changerPanel, "manageCoursesPanel");
    //     }
    // }
}