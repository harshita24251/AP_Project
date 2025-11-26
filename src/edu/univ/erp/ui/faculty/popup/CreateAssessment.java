package edu.univ.erp.ui.faculty.popup;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import org.jdatepicker.impl.*;
import org.jdatepicker.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.time.*;
import java.util.Date;
import java.util.Calendar;
import edu.univ.erp.api.instructor.*;

public class CreateAssessment extends JDialog{
    private static int width = 400;
    private static int height = 350;
    public CreateAssessment(String Course_ID){
        setTitle("New Assessment");
        FlatLightLaf.setup();

        setSize(new Dimension(width, height));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        //-----------------------------Inputs-------------------------------
        JTextField forTitle = new JTextField();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        JTextField forStartDate = new JTextField(LocalDateTime.now().format(formatDate));
        forStartDate.setEditable(false);

        JSpinner forEndDate = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor endDate = new JSpinner.DateEditor(forEndDate, "dd-MM-yyyy HH:mm");
        forEndDate.setEditor(endDate);
        endDate.getTextField().setEditable(false);
        endDate.getTextField().setHorizontalAlignment(SwingConstants.LEFT);

        JSpinner forMaxMarks = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JSpinner.NumberEditor maxMarks = new JSpinner.NumberEditor(forMaxMarks, "#");
        forMaxMarks.setEditor(maxMarks);
        maxMarks.getTextField().setEditable(false);
        maxMarks.getTextField().setHorizontalAlignment(SwingConstants.LEFT);

        JSpinner forWeightage = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JSpinner.NumberEditor weightage = new JSpinner.NumberEditor(forWeightage, "#");
        forWeightage.setEditor(weightage);
        weightage.getTextField().setEditable(false);
        weightage.getTextField().setHorizontalAlignment(SwingConstants.LEFT);

        JButton save = new JButton("Save");
        save.addActionListener(new saveEvent(Course_ID, forTitle.getText(), (int) forMaxMarks.getValue(), (int) forWeightage.getValue(), Timestamp.valueOf(forStartDate.getText()), new Timestamp((Date) forEndDate.getValue())));
        JButton close = new JButton("Close");
        close.addActionListener(new closeEvent());

        //------------------------------------------------------------------

        JPanel titlePanel = createList("Title", forTitle);
        JPanel startTimePanel = createList("Start Date", forStartDate);
        JPanel endTimePanel = createList("End Date", forEndDate);
//        JPanel chooseFilePanel = createList("Choose File", );
        JPanel maxMarksPanel = createList("Max. Marks", forMaxMarks);
        JPanel weightagePanel = createList("Weightage", forWeightage);

        JPanel button = new JPanel(new FlowLayout());
        button.setBackground(Color.WHITE);
        button.add(save);
        button.add(close);

        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(titlePanel);
        mainPanel.add(startTimePanel);
        mainPanel.add(endTimePanel);
        mainPanel.add(maxMarksPanel);
        mainPanel.add(weightagePanel);
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

    private class closeEvent implements ActionListener{
        public void actionPerformed(ActionEvent e){
            dispose();
        }
    }

    private class saveEvent implements ActionListener{
        String Course_ID;
        String title;
        int maxMarks;
        int weightage;
        Timestamp start;
        Timestamp end;

        public saveEvent(String Course_ID, String title, int maxMarks, int weightage, Timestamp start, Timestamp end){
            this.Course_ID = Course_ID;
            this.title = title;
            this.maxMarks = maxMarks;
            this.weightage = weightage;
            this.start = start;
            this.end = end;
        }

        public void actionPerformed(ActionEvent e){
            NewAssessments.create(Course_ID, title, maxMarks, weightage, start, end);
        }
    }
}
