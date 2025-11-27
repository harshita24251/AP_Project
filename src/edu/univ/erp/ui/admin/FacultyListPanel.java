package edu.univ.erp.ui.admin;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import edu.univ.erp.api.admin.AllFacultyData;
import edu.univ.erp.api.admin.AllStudentsDetails;
import edu.univ.erp.api.instructor.StudentsEnrolled;
import edu.univ.erp.ui.common.events.ButtonHover;
import edu.univ.erp.ui.common.events.HoverEffect;
import edu.univ.erp.ui.faculty.popup.EditGrades;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FacultyListPanel extends JPanel{

    public FacultyListPanel(float width, float height_){
        FlatLightLaf.setup();


        //--------------------------------styles----------------------------------
        setPreferredSize(new Dimension(Math.round(width), Math.round(height_)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        ArrayList<ArrayList<String>> listOfFaculty = AllFacultyData.fetch();

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
            JPanel department = createList(Math.round(0.18f * width), Math.round(height));
            JPanel email = createList(Math.round(0.22f * width), Math.round(height));
            JPanel viewgrades = createList(Math.round(0.07f * width), Math.round(height));

            JLabel noLabel = createLabel("");
            JLabel rollLabel = createLabel("Faculty ID");
            JLabel nameLabel = createLabel("Name");
            JLabel departmentLabel = createLabel("Department");
            JLabel emailLabel = createLabel("Email");
            JLabel viewLabel = createLabel("Reg. Sections");

            no.add(noLabel, BorderLayout.CENTER);
            roll.add(rollLabel, BorderLayout.CENTER);
            name.add(nameLabel, BorderLayout.CENTER);
            department.add(departmentLabel, BorderLayout.CENTER);
            email.add(emailLabel, BorderLayout.CENTER);
            viewgrades.add(viewLabel, BorderLayout.CENTER);

            main.add(leftMargin);
            main.add(no);
            main.add(roll);
            main.add(name);
            main.add(department);
            main.add(email);
            main.add(viewgrades);

            add(main);
        }

        for (ArrayList<String> arr : listOfFaculty) {
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
            JPanel department = createList(Math.round(0.18f * width), Math.round(height));
            forHoverEffect.add(department);
            JPanel email = createList(Math.round(0.22f * width), Math.round(height));
            forHoverEffect.add(email);
            JPanel viewgrades = createList(Math.round(0.05f * width), Math.round(height));
            forHoverEffect.add(viewgrades);

            JLabel noLabel = createLabel(arr.get(0));
            JLabel rollLabel = createLabel(arr.get(1));
            JLabel nameLabel = createLabel(arr.get(2));
            JLabel departmentLabel = createLabel(arr.get(3));
            JLabel emailLabel = createLabel(arr.get(4));

            JButton viewButton = new JButton(eye);
            viewButton.setFocusPainted(false);
            viewButton.setBorderPainted(false);
            viewButton.setContentAreaFilled(false);
            viewButton.addMouseListener(new ButtonHover(viewButton, eyeLight));
//            viewButton.addActionListener(new OpenSavePopup((String) arr.get(1)));

            no.add(noLabel, BorderLayout.CENTER);
            roll.add(rollLabel, BorderLayout.CENTER);
            name.add(nameLabel, BorderLayout.CENTER);
            department.add(departmentLabel, BorderLayout.CENTER);
            email.add(emailLabel, BorderLayout.CENTER);
            viewgrades.add(viewButton, BorderLayout.CENTER);

            main.add(leftMargin);
            main.add(no);
            main.add(roll);
            main.add(name);
            main.add(department);
            main.add(email);
            main.add(viewgrades);

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
}
