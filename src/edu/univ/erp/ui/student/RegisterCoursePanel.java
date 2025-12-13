package edu.univ.erp.ui.student;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import edu.univ.erp.api.student.*;
import edu.univ.erp.api.admin.*;
import edu.univ.erp.api.catalog.*;
import edu.univ.erp.ui.common.CoursePalette;
import edu.univ.erp.ui.common.popup.Alert;
import edu.univ.erp.ui.student.popup.*;
import edu.univ.erp.api.enrollment.*;
import edu.univ.erp.access.*;

public class RegisterCoursePanel extends JPanel {
    private float width;
    private float height;
    private String studentId;
    private JPanel coursesPanel;
    private JButton registerButton;
    private JButton dropButton;
    private HashMap<JCheckBox, ArrayList<String>> selectedCourses = new HashMap<>();
    private HashMap<String, JLabel> sectionStatusLabels = new HashMap<>();
    private HashMap<String, Integer> sectionCapacity = new HashMap<>();

    // assume you’ll pass semester later dynamically
    private int currentSemester = Semesters.current(); // or pass from constructor if needed

    public RegisterCoursePanel(float width, float height, String studentId) {
        this.width = width;
        this.height = height;
        this.studentId = studentId;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(15, 20, 10, 20));
        topPanel.setBackground(Color.WHITE);

        JLabel msg = new JLabel("You may register for or drop courses before the registration deadline.");
        msg.setFont(new Font("Roboto Mono", Font.PLAIN, 16));
        topPanel.add(msg, BorderLayout.WEST);

        // ---------- Register Button ----------
        registerButton = new JButton("Register");
        registerButton.setEnabled(false);
        registerButton.setFocusPainted(false);
        registerButton.setBackground(new Color(78, 178, 165));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Roboto Mono", Font.PLAIN, 16));
        registerButton.setBorder(new EmptyBorder(8, 20, 8, 20));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        registerButton.addActionListener(e -> {
            if (!isMaintenance.on()){
                ArrayList<String> sections = new ArrayList<>();
                HashMap<String, String> deadlines = new HashMap<>();
                HashMap<String, String> whichCourse = new HashMap<>();

                for (JCheckBox box : selectedCourses.keySet()) {
                    if (box.isSelected()) {
                        sections.add(selectedCourses.get(box).get(1));
                        deadlines.put(selectedCourses.get(box).get(1), selectedCourses.get(box).get(2));
                        whichCourse.put(selectedCourses.get(box).get(1), selectedCourses.get(box).get(0));
                    }
                }
                if (sections.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No courses selected.");
                    return;
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String nowStr = LocalDateTime.now().format(formatter);
                boolean didRegister = true;

                for (String section : sections) {
                    if (Timestamp.valueOf(deadlines.get(section)).before(Timestamp.valueOf(nowStr))) {
                        Alert A = new Alert(String.format("Deadline passed for course '%s', cannot register!", whichCourse.get(section)), "Close");
                        A.setfont(new Font("Segoe UI", Font.PLAIN, 15));
                        didRegister = false;
                        break;
                    }

                    System.out.println(section + CourseCapacity.get(section));
                    if (totalStudentsInSection.count(section) == CourseCapacity.get(section)){
                        Alert A = new Alert("Course Capacity Full, Cannot Register", "Close");
                        A.setfont(new Font("Segoe UI", Font.PLAIN, 16));
                        didRegister = false;
                        break;
                    }

                    RegisterTheCourse.register(section);

                    JLabel label = sectionStatusLabels.get(section);
                    if (label != null) {
                        label.setText("Registered");
                        label.setForeground(new Color(0,128,0));
                    }
                }

                if (didRegister) {
                    JOptionPane.showMessageDialog(null, "Registered successfully!");
                }
            }
            else{
                Alert A = new Alert("Maintenance Undergoing, Can't Register", "Close");
                A.setfont(new Font("Segoe UI", Font.PLAIN, 15));
            }

        });

        dropButton = new JButton("Drop");
        dropButton.setEnabled(false);
        dropButton.setFocusPainted(false);
        dropButton.setBackground(new Color(200,70,70));
        dropButton.setForeground(Color.WHITE);
        dropButton.setFont(new Font("Roboto Mono", Font.PLAIN, 16));
        dropButton.setBorder(new EmptyBorder(8, 20, 8, 20));
        dropButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        dropButton.addActionListener(e -> {
            if (!isMaintenance.on()){

                ArrayList<String> sections = new ArrayList<>();
                HashMap<String, String> deadlines = new HashMap<>();
                HashMap<String, String> whichCourse = new HashMap<>();

                for (JCheckBox box : selectedCourses.keySet()) {
                    if (box.isSelected()) {
                        sections.add(selectedCourses.get(box).get(1));
                        deadlines.put(selectedCourses.get(box).get(1), selectedCourses.get(box).get(2));
                        whichCourse.put(selectedCourses.get(box).get(1), selectedCourses.get(box).get(0));
                    }
                }
                if (sections.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No courses selected.");
                    return;
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String nowStr = LocalDateTime.now().format(formatter);
                boolean didDrop = true;

                for (String section : sections) {
                    if (Timestamp.valueOf(deadlines.get(section)).before(Timestamp.valueOf(nowStr))) {
                        Alert A = new Alert(String.format("Deadline passed for course '%s', cannot drop!", whichCourse.get(section)), "Close");
                        A.setfont(new Font("Segoe UI", Font.PLAIN, 15));
                        didDrop = false;
                        break;
                    }

                    thisEnrollment.remove(section);
                    JLabel label = sectionStatusLabels.get(section);
                    if (label != null) {
                        label.setText("Not Registered");
                        label.setForeground(new Color(80,80,80));
                    }
                }

                if (didDrop) {
                    JOptionPane.showMessageDialog(null, "Dropped successfully!");
                }
            }
            else{
                Alert A = new Alert("Maintenance Undergoing, Can't Drop", "Close");
                A.setfont(new Font("Segoe UI", Font.PLAIN, 15));
            }
        });

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnPanel.setOpaque(false);
        btnPanel.add(registerButton);
        btnPanel.add(dropButton);
        topPanel.add(btnPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        coursesPanel = new JPanel();
        coursesPanel.setLayout(new BoxLayout(coursesPanel, BoxLayout.Y_AXIS));
        coursesPanel.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(coursesPanel);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);

        loadCoursesOnce();
    }

    private void loadCoursesOnce() {
        coursesPanel.removeAll();
        selectedCourses.clear();
        sectionStatusLabels.clear();
        registerButton.setEnabled(false);
        dropButton.setEnabled(false);

        ArrayList<HashMap<String, String>> available = AvailableCourses.fetch();
        ArrayList<String> registeredIDs = registeredCourses.fetch(currentSemester);

        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        for (HashMap<String, String> course : available) {
            JPanel outerPanel = new JPanel();
            outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.Y_AXIS));
            outerPanel.setBackground(Color.WHITE);
            outerPanel.setBorder(new EmptyBorder(5, 20, 5, 20));
            outerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

            ArrayList<String> details = allCourseDetails.fetch(course.get("Course ID"));
            CoursePalette palette = new CoursePalette(
                    width - 100, 40,
                    details.get(3),
                    details.get(1),
                    course.get("Course ID"),
                    details.get(2),
                    16, false
            );

            JLabel statusLabel = new JLabel();
            statusLabel.setFont(new Font("Roboto Mono", Font.BOLD, 13));

            String sectionId = course.get("Section ID");
            String endDate = course.get("end_date");

            boolean isRegistered = registeredIDs.contains(course.get("Course ID"));
            boolean deadlinePassed = Timestamp.valueOf(endDate).before(Timestamp.valueOf(currentTime));

            if (isRegistered)
                statusLabel.setText("Registered");
            else if (deadlinePassed)
                statusLabel.setText("Deadline Passed");
            else
                statusLabel.setText("Not Registered");

            if (statusLabel.getText().equals("Registered"))
                statusLabel.setForeground(new Color(0, 128, 0));
            else if (statusLabel.getText().equals("Deadline Passed"))
                statusLabel.setForeground(Color.RED);
            else
                statusLabel.setForeground(new Color(80, 80, 80));

            sectionStatusLabels.put(sectionId, statusLabel);

            JCheckBox select = new JCheckBox();
            select.setOpaque(false);
            select.setCursor(new Cursor(Cursor.HAND_CURSOR));
//            select.setEnabled(!isRegistered); // disable checkbox for already registered

            ArrayList<String> courseSection = new ArrayList<>();
            courseSection.add(course.get("Course ID"));
            courseSection.add(sectionId); // 1 index
            courseSection.add(endDate);

            selectedCourses.put(select, courseSection);
            select.addItemListener(e -> {
                boolean any = selectedCourses.keySet().stream().anyMatch(JCheckBox::isSelected);
                registerButton.setEnabled(any);
                dropButton.setEnabled(any);
            });

            Icon icon = new FlatSVGIcon("info.svg", 0.36f);
            JLabel iconLabel = new JLabel(icon, JLabel.LEFT);
            iconLabel.addMouseListener(new seeInfo(course.get("instructor_id"), course.get("start_date"), course.get("end_date"), course.get("Capacity"), course.get("Section ID")));
            iconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            iconLabel.setBorder(new EmptyBorder(0, 0, 0, 10));

            JPanel topRow = new JPanel(new BorderLayout());
            topRow.setOpaque(false);
            topRow.add(palette, BorderLayout.CENTER);

            JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
            rightPanel.setOpaque(false);
            rightPanel.add(iconLabel);
            rightPanel.add(select);
            topRow.add(rightPanel, BorderLayout.EAST);

            JPanel statusPanel = new JPanel(new BorderLayout());
            statusPanel.setOpaque(false);
            statusPanel.setBorder(new EmptyBorder(2, 5, 0, 0));
            statusPanel.add(statusLabel, BorderLayout.WEST);

            outerPanel.add(topRow);
            outerPanel.add(statusPanel);
            coursesPanel.add(outerPanel);
        }

        coursesPanel.revalidate();
        coursesPanel.repaint();
    }

    private class seeInfo extends MouseAdapter {
        String instructor;
        String start;
        String end;
        String section_id;
        String capacity;

        public seeInfo(String instructor, String start, String end, String capacity,String section_id) {
            this.instructor = instructor;
            this.start = start;
            this.end = end;
            this.section_id = section_id;
            this.capacity = capacity;
        }

        public void mouseClicked(MouseEvent e) {
            new RegisterableCourseInfo(instructor, start, end, capacity, String.valueOf(totalStudentsInSection.count(section_id)));
        }
    }

    public static Timestamp getCurrentSqlTimestamp() {
        Instant now = Instant.now();
        return Timestamp.from(now);
    }
}
