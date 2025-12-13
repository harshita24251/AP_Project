package edu.univ.erp.ui.faculty;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import edu.univ.erp.ui.common.events.*;
import edu.univ.erp.api.student.*;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import edu.univ.erp.api.instructor.StudentsEnrolled;
import edu.univ.erp.ui.common.popup.Alert;
import edu.univ.erp.ui.faculty.popup.EditGrades;
import edu.univ.erp.access.*;

public class StudentsListPanel extends JPanel{
    private ArrayList<ArrayList<Object>> listOfStudents;
    private String Course_ID;

    public StudentsListPanel(float width, float height_, String Course_ID){
        this.Course_ID = Course_ID;
        FlatLightLaf.setup();

        //--------------------------------styles----------------------------------
        setPreferredSize(new Dimension(Math.round(width), Math.round(height_)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        listOfStudents = StudentsEnrolled.fetch(Course_ID);

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
            JPanel roll = createList(Math.round(0.08f * width), Math.round(height));
            JPanel name = createList(Math.round(0.18f * width), Math.round(height));
            JPanel program = createList(Math.round(0.10f * width), Math.round(height));
            JPanel semester = createList(Math.round(0.08f * width), Math.round(height));
            JPanel email = createList(Math.round(0.22f * width), Math.round(height));
            JPanel viewgrades = createList(Math.round(0.07f * width), Math.round(height));

            JLabel noLabel = createLabel("");
            JLabel rollLabel = createLabel("Roll No.");
            JLabel nameLabel = createLabel("Name");
            JLabel programLabel = createLabel("Program");
            JLabel semesterLabel = createLabel("Semester");
            JLabel emailLabel = createLabel("Email");
            JLabel viewLabel = createLabel("View/Edit Grades");

            no.add(noLabel, BorderLayout.CENTER);
            roll.add(rollLabel, BorderLayout.CENTER);
            name.add(nameLabel, BorderLayout.CENTER);
            program.add(programLabel, BorderLayout.CENTER);
            semester.add(semesterLabel, BorderLayout.CENTER);
            email.add(emailLabel, BorderLayout.CENTER);
            viewgrades.add(viewLabel, BorderLayout.CENTER);

            main.add(leftMargin);
            main.add(no);
            main.add(roll);
            main.add(name);
            main.add(program);
            main.add(semester);
            main.add(email);
            main.add(viewgrades);

            add(main);
        }

        for (ArrayList<Object> arr : listOfStudents) {
            ArrayList<JPanel> forHoverEffect = new ArrayList<>();

            JPanel main = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            main.setMaximumSize(new Dimension(Math.round(width), Math.round(height)));
            main.setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
            main.setBackground(Color.WHITE);
            forHoverEffect.add(main);

            JPanel leftMargin = createList(Math.round(0.02f * width), Math.round(height));
            leftMargin.setBackground(Color.WHITE);
            forHoverEffect.add(leftMargin);

            JPanel no = createList(Math.round(0.04f * width), Math.round(height));
            forHoverEffect.add(no);
            JPanel roll = createList(Math.round(0.08f * width), Math.round(height));
            forHoverEffect.add(roll);
            JPanel name = createList(Math.round(0.18f * width), Math.round(height));
            forHoverEffect.add(name);
            JPanel program = createList(Math.round(0.10f * width), Math.round(height));
            forHoverEffect.add(program);
            JPanel semester = createList(Math.round(0.08f * width), Math.round(height));
            forHoverEffect.add(semester);
            JPanel email = createList(Math.round(0.22f * width), Math.round(height));
            forHoverEffect.add(email);
            JPanel viewgrades = createList(Math.round(0.05f * width), Math.round(height));
            forHoverEffect.add(viewgrades);

            JLabel noLabel = createLabel(arr.get(0));
            JLabel rollLabel = createLabel(arr.get(2));
            JLabel nameLabel = createLabel(arr.get(3));
            JLabel programLabel = createLabel(arr.get(4));
            JLabel semesterLabel = createLabel(arr.get(5));
            JLabel emailLabel = createLabel(arr.get(6));

            JButton viewButton = new JButton(eye);
            viewButton.setFocusPainted(false);
            viewButton.setBorderPainted(false);
            viewButton.setContentAreaFilled(false);
            viewButton.addMouseListener(new ButtonHover(viewButton, eyeLight));
            viewButton.addActionListener(new OpenSavePopup((String) arr.get(1)));
//            System.out.println((String) arr.get(1));

            no.add(noLabel, BorderLayout.CENTER);
            roll.add(rollLabel, BorderLayout.CENTER);
            name.add(nameLabel, BorderLayout.CENTER);
            program.add(programLabel, BorderLayout.CENTER);
            semester.add(semesterLabel, BorderLayout.CENTER);
            email.add(emailLabel, BorderLayout.CENTER);
            viewgrades.add(viewButton, BorderLayout.CENTER);

            main.add(leftMargin);
            main.add(no);
            main.add(roll);
            main.add(name);
            main.add(program);
            main.add(semester);
            main.add(email);
            main.add(viewgrades);

            main.addMouseListener(new HoverEffect(forHoverEffect));
            add(main);
        }
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

    public class OpenSavePopup implements ActionListener{
        String Student_ID;
        public OpenSavePopup(String Student_ID){
            this.Student_ID = Student_ID;
        }

        public void actionPerformed(ActionEvent e){
            JDialog editGrades = new EditGrades(Course_ID, Student_ID);
            setVisible(true);
        }
    }
}
