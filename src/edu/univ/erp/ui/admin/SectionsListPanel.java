package edu.univ.erp.ui.admin;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import edu.univ.erp.api.admin.AllSectionDetails;
import edu.univ.erp.api.admin.AllStudentsDetails;
import edu.univ.erp.api.admin.SectionDate;
import edu.univ.erp.api.instructor.StudentsEnrolled;
import edu.univ.erp.ui.admin.popup.SetDeadline;
import edu.univ.erp.ui.common.events.ButtonHover;
import edu.univ.erp.ui.common.events.HoverEffect;
import edu.univ.erp.ui.faculty.popup.EditGrades;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SectionsListPanel extends JPanel{

    public SectionsListPanel(float width, float height_){
        FlatLightLaf.setup();


        //--------------------------------styles----------------------------------
//        setPreferredSize(new Dimension(Math.round(width), Math.round(height_)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        ArrayList<ArrayList<String>> listOfSections = AllSectionDetails.fetch();
//        System.out.println(listOfStudents.size());

        float height = height_ * 0.04f;

        //--------------------------------------Icons-----------------------------------------
        Icon eye = new FlatSVGIcon("eye.svg", 0.25f);
        Icon  eyeLight = new FlatSVGIcon("eye_light.svg", 0.25f);

        //-----------------------------column Heading----------------------------------
        {
            JPanel main = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            main.setMaximumSize(new Dimension(Math.round(width), Math.round(height)));
            main.setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
            main.setBackground(Color.WHITE);

            JPanel leftMargin = createList(Math.round(0.02f * width), Math.round(height));
            leftMargin.setBackground(Color.WHITE);
            JPanel no = createList(Math.round(0.04f * width), Math.round(height));
            JPanel sectionid = createList(Math.round(0.08f * width), Math.round(height));
            JPanel courseid = createList(Math.round(0.10f * width), Math.round(height));
            JPanel insid = createList(Math.round(0.18f * width), Math.round(height));
            JPanel daytime = createList(Math.round(0.08f * width), Math.round(height));
            JPanel duration = createList(Math.round(0.08f * width), Math.round(height));
            JPanel room = createList(Math.round(0.07f * width), Math.round(height));
            JPanel capacity = createList(Math.round(0.07f * width), Math.round(height));
            JPanel semester = createList(Math.round(0.07f * width), Math.round(height));
            JPanel year = createList(Math.round(0.07f * width), Math.round(height));
            JPanel deadline = createList(Math.round(0.07f * width), Math.round(height));

            JLabel noLabel = createLabel("");
            JLabel sectionLabel = createLabel("Section ID");
            JLabel courseLabel = createLabel("Course ID");
            JLabel instructorLabel = createLabel("Instructor ID");
            JLabel daytimeLabel = createLabel("Start Time");
            JLabel durationLabel = createLabel("Duration (Mins)");
            JLabel roomLabel = createLabel("Room");
            JLabel capacityLabel = createLabel("Capacity");
            JLabel semesterLabel = createLabel("Semester");
            JLabel yearLabel = createLabel("Year");
            JLabel deadlineLabel = createLabel("Deadline");


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
            deadline.add(deadlineLabel);

            main.add(leftMargin);
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
            main.add(deadline);

            add(main);
        }

        for (ArrayList<String> arr : listOfSections) {
            ArrayList<JPanel> forHoverEffect = new ArrayList<>();

            JPanel main = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            main.setMaximumSize(new Dimension(Math.round(width), Math.round(height)));
            main.setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
            main.setBackground(Color.WHITE);

            JPanel leftMargin = createList(Math.round(0.02f * width), Math.round(height));
            leftMargin.setBackground(Color.WHITE);
            JPanel no = createList(Math.round(0.04f * width), Math.round(height));
            forHoverEffect.add(no);
            JPanel sectionid = createList(Math.round(0.08f * width), Math.round(height));
            forHoverEffect.add(sectionid);
            JPanel courseid = createList(Math.round(0.10f * width), Math.round(height));
            forHoverEffect.add(courseid);
            JPanel insid = createList(Math.round(0.18f * width), Math.round(height));
            forHoverEffect.add(insid);
            JPanel daytime = createList(Math.round(0.08f * width), Math.round(height));
            forHoverEffect.add(daytime);
            JPanel duration = createList(Math.round(0.08f * width), Math.round(height));
            forHoverEffect.add(duration);
            JPanel room = createList(Math.round(0.07f * width), Math.round(height));
            forHoverEffect.add(room);
            JPanel capacity = createList(Math.round(0.07f * width), Math.round(height));
            forHoverEffect.add(capacity);
            JPanel semester = createList(Math.round(0.07f * width), Math.round(height));
            forHoverEffect.add(semester);
            JPanel year = createList(Math.round(0.07f * width), Math.round(height));
            forHoverEffect.add(year);
            JPanel deadline = createList(Math.round(0.07f * width), Math.round(height));
            forHoverEffect.add(deadline);

            JLabel noLabel = createLabel(arr.get(0));
            JLabel sectionLabel = createLabel(arr.get(1));
            JLabel courseLabel = createLabel(arr.get(2));
            JLabel instructorLabel = createLabel(arr.get(3));
            JLabel daytimeLabel = createLabel(arr.get(4));
            JLabel durationLabel = createLabel(arr.get(9));
            JLabel roomLabel = createLabel(arr.get(5));
            JLabel capacityLabel = createLabel(arr.get(6));
            JLabel semesterLabel = createLabel(arr.get(7));
            JLabel yearLabel = createLabel(arr.get(8));
            JButton deadlineBtn = new JButton("Deadline");
            deadlineBtn.addActionListener(new deadlineSetEvent(arr.get(1)));
//            deadlineBtn.setBorder(new EmptyBorder(10, 10, 10, 10));


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
            deadline.add(deadlineBtn);

            main.add(leftMargin);
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
            main.add(deadline);
            forHoverEffect.add(main);

            main.addMouseListener(new HoverEffect(forHoverEffect));
            add(main);

        }
        setVisible(true);
    }

    public static JPanel createList(int width, int height){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBackground(Color.WHITE);

        return panel;
    }

    public static JLabel createLabel(Object content){
        JLabel label = new JLabel((String) content);
        return label;
    }

//    public class OpenSavePopup implements ActionListener{
//        String Student_ID;
//        public OpenSavePopup(String Student_ID){
//            this.Student_ID = Student_ID;
//        }
//
//        public void actionPerformed(ActionEvent e){
//            JDialog editGrades = new EditGrades(Course_ID, Student_ID);
//            setVisible(true);
//        }
//    }

    private class deadlineSetEvent implements ActionListener{
        String Section_ID;

        public deadlineSetEvent(String s){
            Section_ID = s;
        }
        public void actionPerformed(ActionEvent e){
            new SetDeadline(Section_ID);
        }
    }
}
