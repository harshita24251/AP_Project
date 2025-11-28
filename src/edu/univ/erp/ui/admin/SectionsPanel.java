package edu.univ.erp.ui.admin;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.sun.tools.javac.Main;
import edu.univ.erp.ui.admin.popup.AddSection;
import edu.univ.erp.ui.admin.popup.AddStudents;
import edu.univ.erp.ui.faculty.Assessments;
import edu.univ.erp.ui.faculty.StudentsListPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SectionsPanel extends JPanel{
    public SectionsPanel(float width, float height){
//        setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        //---------------------------------------widgets------------------------------------------
        Icon plus = new FlatSVGIcon("plus.svg", 0.25f);

        JButton addCourse = new JButton("Add Section", plus);
        addCourse.addActionListener(new addEvent());
//        createAssessment.addActionListener(new Assessments.createAssessmentEvent(arr.get(1)));

        //-----------------------------column Heading----------------------------------

        JPanel main = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
//        main.setMaximumSize(new Dimension(Math.round(width), Math.round(height)));
//        main.setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        main.setBackground(Color.WHITE);

        JPanel leftMargin = createList(Math.round(0.02f * width), Math.round(height * 0.03f));
        leftMargin.setBackground(Color.WHITE);
        JPanel no = createList(Math.round(0.04f * width), Math.round(height * 0.03f));
        JPanel sectionid = createList(Math.round(0.10f * width), Math.round(height * 0.03f));
        JPanel courseid = createList(Math.round(0.10f * width), Math.round(height * 0.03f));
        JPanel insid = createList(Math.round(0.18f * width), Math.round(height * 0.03f));
        JPanel daytime = createList(Math.round(0.08f * width), Math.round(height * 0.03f));
        JPanel duration = createList(Math.round(0.08f * width), Math.round(height * 0.03f));
        JPanel room = createList(Math.round(0.07f * width), Math.round(height * 0.03f));
        JPanel capacity = createList(Math.round(0.07f * width), Math.round(height * 0.03f));
        JPanel semester = createList(Math.round(0.07f * width), Math.round(height * 0.03f));
        JPanel year = createList(Math.round(0.07f * width), Math.round(height * 0.03f));

        JLabel noLabel = createLabel("");
        JLabel sectionLabel = createLabel("Section ID");
        JLabel courseLabel = createLabel("Course ID");
        JLabel instructorLabel = createLabel("Instructor ID");
        JLabel daytimeLabel = createLabel("Day Time");
        JLabel durationLabel = createLabel("Duration");
        JLabel roomLabel = createLabel("Room");
        JLabel capacityLabel = createLabel("Capacity");
        JLabel semesterLabel = createLabel("Semester");
        JLabel yearLabel = createLabel("Year");


        no.add(noLabel, BorderLayout.CENTER);
        sectionid.add(sectionLabel, BorderLayout.CENTER);
        courseid.add(courseLabel, BorderLayout.CENTER);
        insid.add(instructorLabel, BorderLayout.CENTER);
        semester.add(semesterLabel, BorderLayout.CENTER);
        daytime.add(daytimeLabel, BorderLayout.CENTER);
        duration.add(durationLabel, BorderLayout.CENTER);
        room.add(roomLabel, BorderLayout.CENTER);
        capacity.add(capacityLabel, BorderLayout.CENTER);
        year.add(yearLabel, BorderLayout.CENTER);

//        main.add(leftMargin);
        main.add(no);
        main.add(sectionid);
        main.add(courseid);
        main.add(insid);
        main.add(daytime);
        main.add(duration);
        main.add(room);
        main.add(capacity);
        main.add(semester);
        main.add(year);

        JPanel widgets = new JPanel(new BorderLayout());
        widgets.setPreferredSize(new Dimension(Math.round(width), Math.round(height * 0.05f)));
        widgets.setBorder(new EmptyBorder(10, 20, 0, 20));
        widgets.setBackground(Color.WHITE);
        widgets.add(addCourse, BorderLayout.WEST);

        JPanel belowWidget = new JPanel(new BorderLayout());
//        belowWidget.setPreferredSize(new Dimension(Math.round(width), Math.round(height * 0.95f)));

        JPanel sectionListAll = new edu.univ.erp.ui.admin.SectionsListPanel(width, height);

        JScrollPane courseScroll = new JScrollPane(sectionListAll);
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

    private class addEvent implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new AddSection();
        }
    }
}