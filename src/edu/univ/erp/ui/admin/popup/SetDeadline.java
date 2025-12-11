package edu.univ.erp.ui.admin.popup;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

import edu.univ.erp.api.admin.SectionDate;
import edu.univ.erp.ui.common.popup.Alert;
import org.jdatepicker.impl.*;
import org.jdatepicker.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import java.time.*;

import edu.univ.erp.api.instructor.*;
import edu.univ.erp.events.*;

public class SetDeadline extends JDialog{
    private static int width = 400;
    private static int height = 200;
    public SetDeadline(String Section_ID){
        setTitle("Set Deadline");
        FlatLightLaf.setup();

        setSize(new Dimension(width, height));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        //-----------------------------Inputs-------------------------------
//        JTextField forTitle = new JTextField();

//        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

//        JFormattedTextField forStartDate = new JFormattedTextField(LocalDateTime.now().format(formatDate));
//        forStartDate.setEditable(false);

        JSpinner forStartDate = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor startDate = new JSpinner.DateEditor(forStartDate, "dd-MM-yyyy HH:mm");
        forStartDate.setEditor(startDate);
        startDate.getTextField().setEditable(false);
        startDate.getTextField().setHorizontalAlignment(SwingConstants.LEFT);

        JSpinner forEndDate = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor endDate = new JSpinner.DateEditor(forEndDate, "dd-MM-yyyy HH:mm");
        forEndDate.setEditor(endDate);
        endDate.getTextField().setEditable(false);
        endDate.getTextField().setHorizontalAlignment(SwingConstants.LEFT);

        JButton save = new JButton("Save");
        save.addActionListener(new saveEvent(Section_ID, forStartDate, forEndDate));
        JButton close = new JButton("Close");
        close.addActionListener(new closeEvent());

        //------------------------------------------------------------------

        JPanel startTimePanel = createList("Start Date", forStartDate);
        JPanel endTimePanel = createList("End Date", forEndDate);

        JPanel button = new JPanel(new FlowLayout());
        button.setBackground(Color.WHITE);
        button.add(save);
        button.add(close);

        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(startTimePanel);
        mainPanel.add(endTimePanel);
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
        JSpinner start;
        JSpinner end;

        public saveEvent(String Course_ID, JSpinner start, JSpinner end){
            this.Course_ID = Course_ID;
            this.start = start;
            this.end = end;
        }

        public void actionPerformed(ActionEvent e){
            Date forEndDateTimestamp = (Date)end.getValue(); //new Timestamp(forEndDateTimestamp.getTime()))
            Date forStartDateTimestamp = (Date)start.getValue(); //new Timestamp(forEndDateTimestamp.getTime()))

//            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//            LocalDateTime currentTimestamp = LocalDateTime.parse(start.getTime(), formatDate);
//            String time = currentTimestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

//            arr.add(Timestamp.valueOf(time));
//            arr.add(end);
//            arr.add(Course_ID);

//            NewAssessments.create(Course_ID, title.getText(), (int) maxMarks.getValue() , (int)weightage.getValue(), Timestamp.valueOf(time), new Timestamp(forEndDateTimestamp.getTime()));

            if (forEndDateTimestamp.before(forStartDateTimestamp)) {
                Alert alert = new Alert("End Date cannot be earlier than Start Data!\n Please try again", "Close");
                alert.setfont(new Font("Segoe UI", Font.PLAIN, 13));
                dispose();
            }

            SectionDate.update(Course_ID, new Timestamp(forStartDateTimestamp.getTime()), new Timestamp(forEndDateTimestamp.getTime()));
//            System.out.println(title);

//            RefreshScreen.broadcast(Course_ID);

            dispose();
        }
    }
}
