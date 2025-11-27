package edu.univ.erp.ui.faculty.popup;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import edu.univ.erp.data.*;

import java.util.ArrayList;
import java.util.HashMap;
import edu.univ.erp.api.student.*;

public class EditGrades extends JDialog {
    private static int width = 410;
    private static int height = 260;
    public EditGrades(String Course_ID, String Student_ID){
        setTitle(String.format("Grades : %d", StudentRollNo.fetch(Student_ID)));
        FlatLightLaf.setup();

        HashMap<String, ArrayList<Integer>> componentGrades = ComponentWiseScores.fetch(Course_ID, Student_ID);

        height = componentGrades.size() * 130;
        setSize(new Dimension(width, height));
//
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        //-----------------------------Inputs-------------------------------
        JPanel details = new JPanel();
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
        details.setBackground(Color.WHITE);
        mainPanel.add(details);

        JLabel Name = new JLabel(StudentName.fetch(Student_ID));
        Name.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JLabel Roll = new JLabel(String.valueOf(StudentRollNo.fetch(Student_ID)));
        Roll.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JLabel Email = new JLabel(StudentEmail.fetch(Student_ID));
        Email.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        Name.setAlignmentX(Component.CENTER_ALIGNMENT);
        Roll.setAlignmentX(Component.CENTER_ALIGNMENT);
        Email.setAlignmentX(Component.CENTER_ALIGNMENT);

        details.add(Name);
        details.add(Box.createVerticalStrut(3));
        details.add(Roll);
        details.add(Box.createVerticalStrut(3));
        details.add(Email);
        details.add(Box.createVerticalStrut(10));


        JButton save = new JButton("Save");
//        save.addActionListener(new CreateAssessment.saveEvent());
        JButton close = new JButton("Close");
        close.addActionListener(e -> dispose());

        //------------------------------------------------------------------

        JPanel button = new JPanel(new FlowLayout());
        button.setBackground(Color.WHITE);
        button.add(save);
        button.add(close);

        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);

        for (String str : componentGrades.keySet()){
            mainPanel.add(createList(str, new JTextField(componentGrades.get(str).get(0).toString()), String.format(" /%d", componentGrades.get(str).get(1))));
        }
        mainPanel.add(button);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static JPanel createList(String Label, JComponent input, String total_score){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JPanel left = new JPanel(new BorderLayout());
        left.setPreferredSize(new Dimension(Math.round(width* 0.3f), height));
        left.setBackground(Color.WHITE);

        JPanel right = new JPanel(new BorderLayout());
        right.setPreferredSize(new Dimension(Math.round(width * 0.7f), height));
        right.setBackground(Color.WHITE);

        JLabel label = new JLabel(Label);

        left.add(label, BorderLayout.CENTER);
        right.add(input, BorderLayout.CENTER);
        right.add(new JLabel(total_score), BorderLayout.EAST);

        panel.add(left, BorderLayout.WEST);
        panel.add(right, BorderLayout.CENTER);
        return panel;
    }
}
