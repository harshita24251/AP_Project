package edu.univ.erp.ui.admin.popup;

import com.formdev.flatlaf.FlatLightLaf;
import edu.univ.erp.api.auth.NewAccount;
import edu.univ.erp.api.student.*;
import edu.univ.erp.events.ListenOnSave;
import edu.univ.erp.events.RefreshScreen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import erp.UUIDGenerator;

public class AddStudents extends JDialog {
    private static int width = 410;
    private static int height = 380;
    private ListenOnSave listener;

    ArrayList<JComponent> studentData = new ArrayList<>();
    public AddStudents(ListenOnSave listener){
        this.listener = listener;
//        setTitle(String.format("Grades : %d", StudentRollNo.fetch(Student_ID)));
        FlatLightLaf.setup();

//        HashMap<String, ArrayList<Integer>> componentGrades = ComponentWiseScores.fetch(Course_ID, Student_ID);

//        int size = componentGrades.size();
//        if (size == 1) height = 220;
//        else
//            height = size * 110;
//
        setSize(new Dimension(width, height));
        setTitle("New Student");
//
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        //-----------------------------Inputs-------------------------------
//        JPanel details = new JPanel();
//        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
//        details.setBackground(Color.WHITE);
//        mainPanel.add(details);
//
//        JLabel Name = new JLabel("Name");
//        Name.setFont(new Font("Segoe UI", Font.BOLD, 14));
//        JLabel Roll = new JLabel("Roll No.");
//        Roll.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        JLabel Email = new JLabel("Email ID");
//        Email.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        Name.setAlignmentX(Component.CENTER_ALIGNMENT);
//        Roll.setAlignmentX(Component.CENTER_ALIGNMENT);
//        Email.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        details.add(Name);
//        details.add(Box.createVerticalStrut(3));
//        details.add(Roll);
//        details.add(Box.createVerticalStrut(3));
//        details.add(Email);
//        details.add(Box.createVerticalStrut(10));


        JButton save = new JButton("Save");
        save.addActionListener(new saveEvent());
        JButton close = new JButton("Close");
        close.addActionListener(e -> dispose());

        //------------------------------------------------------------------

        JPanel button = new JPanel(new FlowLayout());
        button.setBackground(Color.WHITE);
        button.add(save);
        button.add(close);

        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);

//        for (String str : componentGrades.keySet()){
//            JTextField tmp = new JTextField(componentGrades.get(str).get(0).toString());
//            mainPanel.add(createList(str, tmp, String.format(" /%d", componentGrades.get(str).get(1))));
//            componentScores.put(str, tmp);
//        }

        JTextField roll = new JTextField();
        JTextField program = new JTextField();
//        JTextField semester = new JTextField();
        JSpinner forSemester = new JSpinner(new SpinnerNumberModel(1, 1, 8, 1));
        JTextField email = new JTextField();
        JTextField contact = new JTextField();
        JTextField name = new JTextField();
        JTextField password = new JTextField();

        studentData.add(roll);
        studentData.add(program);
        studentData.add(forSemester);
        studentData.add(email);
        studentData.add(name);
        studentData.add(password);
        studentData.add(contact);

        mainPanel.add(createList("Name", name));
        mainPanel.add(createList("Roll No.", roll));
        mainPanel.add(createList("Program", program));
        mainPanel.add(createList("Semester", forSemester));
        mainPanel.add(createList("Email", email));
        mainPanel.add(createList("Contact", contact));
        mainPanel.add(createList("Create Password", password));

        mainPanel.add(button);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static JPanel createList(String Label, JComponent input){
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

        panel.add(left, BorderLayout.WEST);
        panel.add(right, BorderLayout.CENTER);
        return panel;
    }

    private void saved(String rollNo) {
        if (listener != null) {
            listener.saved(rollNo);
        }
        dispose();
    }

    private class saveEvent implements ActionListener{
        public void actionPerformed(ActionEvent e){
            ArrayList<String> arr = new ArrayList<>();

            for (JComponent text : studentData){
                if (text instanceof  JTextField){
                    arr.add(((JTextField) text).getText());
                }
                else if (text instanceof JSpinner){
                    arr.add(((JSpinner) text).getValue().toString());
                }
            }

            arr.add(UUIDGenerator.generate(arr.get(4)));

            ArrayList<String> toInsert = new ArrayList<>();
            toInsert.add(arr.get(7));
            toInsert.add(arr.get(3));
            toInsert.add("student");
            toInsert.add(arr.get(5));

            NewAccount.insert(toInsert);

//            RefreshScreen.broadcast(arr.get(0));
            NewStudent.add(arr);
            saved(arr.get(0));
//            dispose();
        }
    }
}
