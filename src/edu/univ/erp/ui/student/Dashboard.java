package edu.univ.erp.ui.student;

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

        //changing panels---------------------------------------------------
        JPanel gradesPanel = new GradePanel(rightPanelWidth, rightPanelHeight);
        JPanel manageCoursesPanel = new ManageCoursesPanel(rightPanelWidth, rightPanelHeight);
        
        HashMap<String, MouseAdapter> registerListener = new HashMap<>();
        registerListener.put("Grades", new goToGrades(gradesPanel));
        registerListener.put("Manage Courses", new goToMangeCourses(manageCoursesPanel));


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

        String[] academic = {"Grades", "Manage Courses", "Dual Degree"};
        String[] administration = {"Fee Details", "Student Requests", "Hostel Requests"};
        String[] special = {"TA Details", "Project Registration"};


        LeftNavPanel academicSection = new LeftNavPanel("Academics", academic, navWidth-30, navHeight, 16, registerListener, highlighter);
        LeftNavPanel administrationSection = new LeftNavPanel("Administration", administration, navWidth-30, navHeight, 16, registerListener, highlighter);
        LeftNavPanel specialSection = new LeftNavPanel("Special", special, navWidth-30, navHeight, 16, registerListener, highlighter);

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

        //------------------------Sub Panels of Right Top Panel--------------
        float rightTopLeftPanelWidth = rightTopPanelWidth * 0.72f; //
        float rightTopLeftPanelHeight = rightTopPanelHeight;

        JPanel rightTopLeftPanel = new JPanel(new BorderLayout());
        rightTopLeftPanel.setPreferredSize(new Dimension(Math.round(rightTopLeftPanelWidth), Math.round(rightTopLeftPanelHeight)));


        float rightTopRightPanelWidth = rightTopPanelWidth * 0.28f; //changed
        float rightTopRightPanelHeight = rightTopPanelHeight;

        JPanel rightTopRightPanel = new JPanel(new BorderLayout());
        rightTopRightPanel.setBackground(Color.BLACK);
        // rightTopRightPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        rightTopRightPanel.setPreferredSize(new Dimension(Math.round(rightTopRightPanelWidth), Math.round(rightTopRightPanelHeight)));

        /**
         * rightTopLeftPanel : Profile card
         */
        JPanel nameOfStudent = new NameCard("Chaitanya Satsangi", "Student", 20, 15, rightTopRightPanelWidth, rightTopRightPanelHeight * 0.25f);

        JPanel details = new JPanel(new GridLayout(2, 2, 0, 0));
        details.setBackground(Color.WHITE);
        details.setPreferredSize(new Dimension(Math.round(rightTopRightPanelWidth), Math.round(rightTopRightPanelHeight * 0.55f)));

        /**
         * Details
         */

        DetailCard rollNo = new DetailCard("Roll No.", "2024158", 10, 15, 100, 100);
        DetailCard contact = new DetailCard("Contact", "9599583800", 10, 15, 100, 100);
        DetailCard branch = new DetailCard("Branch", "B.Tech CSB", 10, 15, 100, 100);
        DetailCard batch = new DetailCard("Batch", "2028", 10, 15, 100, 100);

        // details.setBackground(Color.BLACK);
        details.setBorder(new EmptyBorder(30, 0, 0, 30));
        details.add(rollNo);
        details.add(contact);
        details.add(branch);
        details.add(batch);

        //---------------------------JFree CGPA Line graph--------------------
        double[] xData = {1, 2};
        double[] yData = {7.8, 7.9};

        // XYChart cgpaChart = QuickChart.getChart("CGPA vs Semester", "Semester", "CGPA", " ", xData, yData)
        XYChartBuilder cgpaChart = new XYChartBuilder();
        cgpaChart.xAxisTitle("Semester");
        cgpaChart.yAxisTitle("SGPA");
        // cgpaChart.width(Math.round(rightTopLeftPanelWidth));
        // cgpaChart.height(Math.round(rightTopLeftPanelHeight));

        XYChart converted = cgpaChart.build(); // converting XYChartBuilder object to XYChart
        converted.getStyler().setLegendVisible(false);
        converted.getStyler().setXAxisMin(1.0);
        converted.getStyler().setXAxisMax(10.0);
        converted.getStyler().setYAxisMin(0.0);
        converted.getStyler().setYAxisMax(10.0);
        converted.getStyler().setChartBackgroundColor(Color.WHITE);
        converted.getStyler().setBaseFont(new Font("Roboto Mono", Font.PLAIN, 16));
        // converted.getStyler().setPlotBorderVisible(false);
        converted.addSeries(" ", xData, yData);

        JPanel chart = new XChartPanel<XYChart>(converted);

        //-------------------------event handling-----------------------------

        //-------------------------adding to frame----------------------------

        rightTopLeftPanel.add(chart, BorderLayout.CENTER);
        // rightTopRightPanel.setBorder(new EmptyBorder(60, 0, 0, 0));
        rightTopRightPanel.add(nameOfStudent, BorderLayout.NORTH);
        rightTopRightPanel.add(details, BorderLayout.CENTER);
        rightTopRightPanel.setBackground(Color.WHITE);
        
        JSplitPane split = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            rightTopLeftPanel,
            rightTopRightPanel
        );
        split.setResizeWeight(0.9);
        split.setDividerSize(0);

        // leftPanel.add(Box.createRigidArea(new Dimension(10, 3)));
        leftPanel.add(dashboardLabel);
        leftPanel.add(academicSection);
        leftPanel.add(administrationSection);
        leftPanel.add(specialSection);

        // rightTopPanel.add(rightTopLeftPanel, BorderLayout.WEST);
        // rightTopPanel.add(rightTopRightPanel, BorderLayout.CENTER);
        rightTopPanel.add(split, BorderLayout.CENTER);

        rightPanel.add(Box.createRigidArea(new Dimension(10, 3)));
        rightPanel.add(rightTopPanel, BorderLayout.NORTH);
        rightPanel.add(rightBottomPanel, BorderLayout.CENTER );
        rightPanel.setBackground(Color.WHITE);

        //-----------------adding panels to cardlayout changingLayout----------------
        changerPanel.add(rightPanel, BorderLayout.CENTER);
        changerPanel.add(gradesPanel, BorderLayout.CENTER);
        changerPanel.add(manageCoursesPanel, BorderLayout.CENTER);

        changingLayout.addLayoutComponent(rightPanel, "rightPanel");
        changingLayout.addLayoutComponent(gradesPanel, "gradesPanel");
        changingLayout.addLayoutComponent(manageCoursesPanel, "manageCoursesPanel");
        //---------------------------------------------------------------------------

        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(changerPanel, BorderLayout.CENTER);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension((int)size.getWidth(), (int) size.getHeight()));

    }

    private class goToGrades extends MouseAdapter{
        JPanel toGo;
        public goToGrades(JPanel toGo){
            this.toGo = toGo;
        }
        public void mouseClicked(MouseEvent e){
            changingLayout.show(changerPanel, "gradesPanel");
        }
    }

    private class goToMangeCourses extends MouseAdapter{
        JPanel toGo;
        public goToMangeCourses(JPanel toGo){
            this.toGo = toGo;
        }
        public void mouseClicked(MouseEvent e){
            changingLayout.show(changerPanel, "manageCoursesPanel");
        }
    }
}