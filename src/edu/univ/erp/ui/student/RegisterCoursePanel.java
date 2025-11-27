package edu.univ.erp.ui.student;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import edu.univ.erp.ui.common.CoursePalette;

public class RegisterCoursePanel extends JPanel {
    private float width;
    private float height;
    private String studentId;
    private JPanel coursesPanel;
    private JButton registerButton;
    private HashMap<JCheckBox, String> selectedCourses = new HashMap<>();

    public RegisterCoursePanel(float width, float height, String studentId){
        this.width = width;
        this.height = height;
        this.studentId = studentId;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(15, 20, 10, 20));
        topPanel.setBackground(Color.WHITE);

        JLabel msg = new JLabel("You may register for courses before the registration deadline.");
        msg.setFont(new Font("Roboto Mono", Font.PLAIN, 16));
        topPanel.add(msg, BorderLayout.WEST);

        registerButton = new JButton("Register");
        registerButton.setEnabled(false);
        registerButton.setFocusPainted(false);
        registerButton.setBackground(new Color(78,178,165));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Roboto Mono", Font.PLAIN, 16));
        registerButton.setBorder(new EmptyBorder(8, 20, 8, 20));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ArrayList<String> courses = new ArrayList<>();
                for (JCheckBox box : selectedCourses.keySet()){
                    if (box.isSelected()) courses.add(selectedCourses.get(box));
                }
                if (courses.size() == 0){
                    JOptionPane.showMessageDialog(null,"No courses selected.");
                    return;
                }
                registerCourses(studentId, courses);
                reloadCourses();
            }
        });
        topPanel.add(registerButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        coursesPanel = new JPanel();
        coursesPanel.setLayout(new BoxLayout(coursesPanel, BoxLayout.Y_AXIS));
        coursesPanel.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(coursesPanel);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);

        reloadCourses();
    }

    private void reloadCourses(){
        coursesPanel.removeAll();
        selectedCourses.clear();
        registerButton.setEnabled(false);

        ArrayList<HashMap<String, String>> data = fetchCourses(studentId);
        for (HashMap<String, String> course : data){
            JPanel outerPanel = new JPanel(new BorderLayout());
            outerPanel.setBackground(Color.WHITE);
            outerPanel.setBorder(new EmptyBorder(0,20,0,20));
            outerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

            CoursePalette palette = new CoursePalette(
                    width-100, 40,
                    course.get("course_name"),
                    course.get("course_acronym"),
                    course.get("course_code"),
                    course.get("course_credits"),
                    16, false
            );

            JCheckBox select = new JCheckBox();
            select.setOpaque(false);
            select.setCursor(new Cursor(Cursor.HAND_CURSOR));
            selectedCourses.put(select, course.get("course_id"));
            select.addItemListener(new ItemListener(){
                public void itemStateChanged(ItemEvent e){
                    boolean any = false;
                    for (JCheckBox chk : selectedCourses.keySet()){
                        if (chk.isSelected()){any=true;break;}
                    }
                    registerButton.setEnabled(any);
                }
            });

            JPanel rightPanel = new JPanel(new BorderLayout());
            rightPanel.setOpaque(false);
            rightPanel.setBorder(new EmptyBorder(0,10,0,10));
            rightPanel.add(select, BorderLayout.CENTER);

            outerPanel.add(palette, BorderLayout.CENTER);
            outerPanel.add(rightPanel, BorderLayout.EAST);

            coursesPanel.add(outerPanel);
        }

        coursesPanel.revalidate();
        coursesPanel.repaint();
    }

    private ArrayList<HashMap<String, String>> fetchCourses(String studentId){
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        HashMap<String, String> c1 = new HashMap<>();
        c1.put("course_id","CSE101");
        c1.put("course_name","Introduction to Programming");
        c1.put("course_acronym","IOP");
        c1.put("course_code","CSE101");
        c1.put("course_credits","4");
        list.add(c1);

        HashMap<String, String> c2 = new HashMap<>();
        c2.put("course_id","BIO102");
        c2.put("course_name","Foundations of Biology");
        c2.put("course_acronym","FOB");
        c2.put("course_code","BIO102");
        c2.put("course_credits","4");
        list.add(c2);

        HashMap<String, String> c3 = new HashMap<>();
        c3.put("course_id","MTH100");
        c3.put("course_name","Linear Algebra");
        c3.put("course_acronym","LA");
        c3.put("course_code","MTH100");
        c3.put("course_credits","3");
        list.add(c3);

        return list;
    }

    private void registerCourses(String studentId, ArrayList<String> courses){
        StringBuilder sb = new StringBuilder();
        for (String id : courses) sb.append(id).append(", ");
        if (sb.length() > 2) sb.setLength(sb.length() - 2);
        JOptionPane.showMessageDialog(null,"Registered successfully for: "+sb.toString());
    }
}
