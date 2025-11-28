package edu.univ.erp.ui.admin.popup;

import com.formdev.flatlaf.FlatLightLaf;
import edu.univ.erp.api.auth.NewAccount;
import edu.univ.erp.api.student.NewStudent;
import erp.UUIDGenerator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddSection extends JDialog{
    private static int width = 410;
    private static int height = 500;

    ArrayList<JComponent> sectionData = new ArrayList<>();

    public AddSection(){
        FlatLightLaf.setup();
        setSize(new Dimension(width, height));
        setTitle("New Section");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        //-----------------------------button--------------------------------
        JButton save = new JButton("Save");
//        save.addActionListener(new AddStudents.saveEvent());
        JButton close = new JButton("Close");
        close.addActionListener(e -> dispose());

        JPanel button = new JPanel(new FlowLayout());
        button.setBackground(Color.WHITE);
        button.add(save);
        button.add(close);

        //-------------------------------------------------------------------
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);

        //---------------------------fields--------------------------
//        JTextField roll = new JTextField();
        JMenuBar courseID = new JMenuBar();
        JMenuBar facultyID = new JMenuBar();
        JMenuBar day = new JMenuBar();
        JMenuBar time = new JMenuBar();
        JMenuBar duration = new JMenuBar();
        JMenuBar room = new JMenuBar();
        JTextField capacity = new JTextField();
        JSpinner semester = new JSpinner(new SpinnerNumberModel(1, 1, 8, 1));
        JSpinner year = new JSpinner(new SpinnerNumberModel(2024, 2024, 2047, 1));

        sectionData.add(courseID);
        sectionData.add(facultyID);
        sectionData.add(day);
        sectionData.add(time);
        sectionData.add(duration);
        sectionData.add(room);
        sectionData.add(capacity);
        sectionData.add(semester);
        sectionData.add(year);

        mainPanel.add(createList("Course ID", courseID));
        mainPanel.add(createList("Faculty ID", facultyID));
        mainPanel.add(createList("Day", day));
        mainPanel.add(createList("Time", time));
        mainPanel.add(createList("Duration", duration));
        mainPanel.add(createList("Room", room));
        mainPanel.add(createList("Capacity", capacity));
        mainPanel.add(createList("Semester", semester));
        mainPanel.add(createList("Year", year));

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

//    private class saveEvent implements ActionListener {
//        public void actionPerformed(ActionEvent e){
//            ArrayList<String> arr = new ArrayList<>();
//
//            for (JComponent text : studentData){
//                if (text instanceof  JTextField){
//                    arr.add(((JTextField) text).getText());
//                }
//                else if (text instanceof JSpinner){
//                    arr.add(((JSpinner) text).getValue().toString());
//                }
//            }
//
//            arr.add(UUIDGenerator.generate(arr.get(4)));
//
//            ArrayList<String> toInsert = new ArrayList<>();
//            toInsert.add(arr.get(7));
//            toInsert.add(arr.get(3));
//            toInsert.add("student");
//            toInsert.add(arr.get(5));
//
//            NewAccount.insert(toInsert);
//
////            RefreshScreen.broadcast(arr.get(0));
//            NewStudent.add(arr);
//            saved(arr.get(0));
////            dispose();
//        }
//    }
}
