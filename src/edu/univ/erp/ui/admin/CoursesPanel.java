package edu.univ.erp.ui.admin;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.sun.tools.javac.Main;
import edu.univ.erp.ui.faculty.Assessments;
import edu.univ.erp.ui.faculty.StudentsListPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CoursesPanel extends JPanel{
    public CoursesPanel(float width, float height){
//        setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        //---------------------------------------widgets------------------------------------------
        Icon plus = new FlatSVGIcon("plus.svg", 0.25f);

        JButton addCourse = new JButton("Add Course", plus);
//        createAssessment.addActionListener(new Assessments.createAssessmentEvent(arr.get(1)));

        //-----------------------------column Heading----------------------------------

            JPanel main = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
//            main.setMaximumSize(new Dimension(Math.round(width), Math.round(height)));
//            main.setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
            main.setBackground(Color.WHITE);

            JPanel leftMargin = createList(Math.round(0.02f * width), Math.round(height * 0.05f));
            leftMargin.setBackground(Color.WHITE);
            JPanel no = createList(Math.round(0.04f * width), Math.round(height * 0.03f));
            JPanel roll = createList(Math.round(0.08f * width), Math.round(height * 0.03f));
            JPanel name = createList(Math.round(0.18f * width), Math.round(height * 0.03f));
            JPanel department = createList(Math.round(0.19f * width), Math.round(height * 0.03f));
            JPanel email = createList(Math.round(0.22f * width), Math.round(height * 0.03f));

            JLabel noLabel = createLabel("");
            JLabel rollLabel = createLabel("Course Code");
            JLabel nameLabel = createLabel("Acronym");
            JLabel departmentLabel = createLabel("Credits");
            JLabel emailLabel = createLabel("Title");

            no.add(noLabel, BorderLayout.CENTER);
            roll.add(rollLabel, BorderLayout.CENTER);
            name.add(nameLabel, BorderLayout.CENTER);
            department.add(departmentLabel, BorderLayout.CENTER);
            email.add(emailLabel, BorderLayout.CENTER);

            main.add(leftMargin);
            main.add(no);
            main.add(roll);
            main.add(name);
            main.add(department);
            main.add(email);

        JPanel widgets = new JPanel(new BorderLayout());
        widgets.setPreferredSize(new Dimension(Math.round(width), Math.round(height * 0.05f)));
        widgets.setBorder(new EmptyBorder(10, 20, 0, 20));
        widgets.setBackground(Color.WHITE);
        widgets.add(addCourse, BorderLayout.WEST);

        JPanel belowWidget = new JPanel(new BorderLayout());
//        belowWidget.setPreferredSize(new Dimension(Math.round(width), Math.round(height * 0.95f)));

        JPanel courseListAll = new edu.univ.erp.ui.admin.CourseListPanel(width, height);

        JScrollPane courseScroll = new JScrollPane(courseListAll);
        courseScroll.setBorder(null);
        courseScroll.getVerticalScrollBar().setUnitIncrement(9);
        courseScroll.getViewport().setBackground(Color.WHITE);

        belowWidget.add(main, BorderLayout.NORTH);
        belowWidget.add(courseScroll, BorderLayout.CENTER);

        JPanel MainPanel = new JPanel();
//        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
        MainPanel.setLayout(new BorderLayout());
        MainPanel.setBackground(Color.WHITE);
//        MainPanel.setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        MainPanel.add(widgets, BorderLayout.NORTH);
        MainPanel.add(belowWidget, BorderLayout.CENTER);

        add(MainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static JLabel createLabel(Object content){
        JLabel label = new JLabel((String) content);
        return label;
    }

    public static JPanel createList(int width, int height){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBackground(Color.WHITE);

        return panel;
    }
}