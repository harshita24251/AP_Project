package edu.univ.erp.ui.admin.popup;

import com.formdev.flatlaf.FlatLightLaf;
import edu.univ.erp.api.admin.AllFacultyData;
import edu.univ.erp.api.admin.NewSection;
import edu.univ.erp.api.auth.NewAccount;
import edu.univ.erp.api.student.NewStudent;
import edu.univ.erp.events.ListenOnSave;
import edu.univ.erp.ui.common.popup.Alert;
import erp.UUIDGenerator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import edu.univ.erp.api.catalog.*;

public class AddSection extends JDialog{
    private static int width = 700;
    private static int height = 500;
    private ListenOnSave listener;

    ArrayList<JComponent> sectionData = new ArrayList<>();
    ArrayList<ArrayList<String>> allCourses = allCourseDetails.fetch();
    ArrayList<ArrayList<String>> allFaculty = AllFacultyData.fetch();

    public AddSection(ListenOnSave listener){
        FlatLightLaf.setup();
        this.listener = listener;
        setSize(new Dimension(width, height));
        setTitle("New Section");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        //-----------------------------button--------------------------------
        JButton save = new JButton("Save");
        save.addActionListener(new saveEvent());
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
        JComboBox<String> courseID = new JComboBox<>();
        JComboBox<String> facultyID = new JComboBox<>();
        JComboBox<String> day = new JComboBox<>(new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"});
        JComboBox<String> time = new JComboBox<>();
        JComboBox<String> duration = new JComboBox<>();
        JComboBox<String> room = new JComboBox<>();
        JTextField capacity = new JTextField();
        capacity.addKeyListener(new restrictNumerical());
        JSpinner semester = new JSpinner(new SpinnerNumberModel(1, 1, 8, 1));
        JSpinner year = new JSpinner(new SpinnerNumberModel(2024, 2024, 2047, 1));

//        JComboBox<String> dayMenu = new JComboBox<>();
//        day.putClientProperty("JComponent.roundRect", true);
//        day.add(dayMenu);

        //------------------------------------------------------------------------
//        ArrayList<ArrayList<String>> allCourses = allCourseDetails.fetch();
        String[] array = new String[allCourses.size()];
        int index = 0;

        for (ArrayList<String> a : allCourses){
            courseID.addItem(a.get(0) + " : " + a.get(3));
        }
//        JComboBox<String> courseIDs = new JComboBox<>(array);
//        courseIDs.putClientProperty("JComponent.roundRect", true);
//        courseID.addItem(array);
        //-------------------------------------------------------------------------

        //------------------------------------------------------------------------
//        ArrayList<ArrayList<String>> allFaculty = AllFacultyData.fetch();
        String[] arrayFaculty = new String[allCourses.size()];
        index = 0;

        for (ArrayList<String> a : allFaculty){
            facultyID.addItem(a.get(1) + " : " + a.get(2));
        }
//        JComboBox<String> facultyIDs = new JComboBox<>(arrayFaculty);
//        facultyID.putClientProperty("JComponent.roundRect", true);
//        facultyID.add(facultyIDs);
        //-------------------------------------------------------------------------
        String[] arrayTime = {"08:30 - 10:00", "09:00 - 10:30", "09:30 - 11:00", "10:00 - 11:30", "10:30 - 12:00", "11:00 - 12:30", "11:30 - 01:00", "12:00 - 01:30", "12:30 - 02:00", "01:00 - 02:30", "01:30 - 03:00", "02:00 - 03:30", "02:30 - 04:00", "03:00 - 04:30", "03:30 - 05:00", "04:00 - 05:30", "04:30 - 06:00"};
//        JComboBox<String> timeArray = new JComboBox<>(arrayTime);
//        time.putClientProperty("JComponent.roundRect", true);
        for (String str : arrayTime){
            time.addItem(str);
        }

        //-------------------------------------------------------------------------
        String[] arrayRoom = {"LHC", "Old Acad", "RnD"};
//        JComboBox<String> roomArray = new JComboBox<>(arrayRoom);
//        time.putClientProperty("JComponent.roundRect", true);
//        room.add(roomArray);
        for (String str : arrayRoom){
            room.addItem(str);
        }

        //------------------------------------------------------------------------
        String[] arrayDur = {"45.0", "60.0", "90.0", "120.0"};
//        JComboBox<String> durArray = new JComboBox<>(arrayDur);
//        time.putClientProperty("JComponent.roundRect", true);
//        duration.add(durArray);
        for (String str : arrayDur){
            duration.addItem(str);
        }

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
        mainPanel.add(createList("Duration (Mins)", duration));
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

    private void saved(String sectionID) {
        if (listener != null) {
            listener.saved(sectionID);
        }
        dispose();
    }


    private class saveEvent implements ActionListener {
        public void actionPerformed(ActionEvent e){
            ArrayList<String> arr = new ArrayList<>();

            for (JComponent text : sectionData){
                if (text instanceof  JTextField){
                    String inp = ((JTextField) text).getText();

                    if (Integer.valueOf(inp) <= 0){
                        Alert negValue = new Alert("Capacity cannot less than or equal to 0.", "Close");
                        negValue.setfont(new Font("Segoe UI", Font.PLAIN, 15));
                        arr.add("");
                        dispose();
                    }

                    arr.add(((JTextField) text).getText());
                }
                else if (text instanceof JSpinner){
                    arr.add(((JSpinner) text).getValue().toString());
                }
                else if (text instanceof JComboBox){
                    arr.add((String) ((JComboBox<?>) text).getSelectedItem());
                }
            }

            arr.add(SectionIdGenerator.generateSectionId()); //randomly generated section ID
//
//            ArrayList<String> toInsert = new ArrayList<>();
//            toInsert.add(arr.get(7));
//            toInsert.add(arr.get(3));
//            toInsert.add("student");
//            toInsert.add(arr.get(5));
//
//            NewAccount.insert(toInsert);

            NewSection.add(arr);
            Alert A = new Alert("You need to set deadline so that students can register", "Close");
            A.setfont(new Font("Segoe UI", Font.PLAIN, 12));

//            RefreshScreen.broadcast(arr.get(0));
//            NewStudent.add(arr);
            saved(arr.get(9)); //check here
//            saved("sectionPanel");
//            dispose();
        }
    }

    private class restrictNumerical extends KeyAdapter {
        public void keyTyped(KeyEvent e){
            char c = e.getKeyChar();
            if (!Character.isDigit(c) && c != '\b') {
                e.consume(); // ignore non-numeric input
            }
        }
    }
}
