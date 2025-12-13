package edu.univ.erp.ui.admin;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.sun.tools.javac.Main;
import edu.univ.erp.events.ListenOnSave;
import edu.univ.erp.ui.admin.popup.AddStudents;
import edu.univ.erp.ui.common.popup.Alert;
import edu.univ.erp.ui.faculty.Assessments;
import edu.univ.erp.ui.faculty.StudentsListPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import edu.univ.erp.access.*;

public class StudentsPanel extends JPanel implements ListenOnSave {
    private JPanel MainPanel;
    private JPanel studentsListAll;
    private float width;
    private float height;

    public StudentsPanel(float width, float height){
        setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        this.width = width;
        this.height = height;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        //---------------------------------------widgets------------------------------------------
        Icon plus = new FlatSVGIcon("plus.svg", 0.25f);

        JButton addStudent = new JButton("Add Student", plus);
        addStudent.addActionListener(new addEvent());
//        createAssessment.addActionListener(new Assessments.createAssessmentEvent(arr.get(1)));

        JPanel widgets = new JPanel(new BorderLayout());
        widgets.setPreferredSize(new Dimension(Math.round(width), Math.round(height * 0.05f)));
        widgets.setBorder(new EmptyBorder(10, 20, 0, 20));
        widgets.setBackground(Color.WHITE);
        widgets.add(addStudent, BorderLayout.WEST);

        studentsListAll = new edu.univ.erp.ui.admin.StudentsListPanel(width, height);

        MainPanel = new JPanel();
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
        MainPanel.setBackground(Color.WHITE);
        MainPanel.setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        MainPanel.add(widgets);
        MainPanel.add(studentsListAll);

        add(MainPanel, BorderLayout.CENTER);
//        add(studentsListAll, BorderLayout.CENTER);
        setVisible(true);
    }

    private class addEvent implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (!isMaintenance.on()){
                new AddStudents(StudentsPanel.this);
            }
            else{
                Alert A = new Alert("Maintenance Undergoing, Can't Add", "Close");
                A.setfont(new Font("Segoe UI", Font.PLAIN, 15));
            }
        }
    }

    public void saved(String rollNo){
        MainPanel.remove(studentsListAll);
        MainPanel.add(new edu.univ.erp.ui.admin.StudentsListPanel(width, height));

        MainPanel.revalidate();
        MainPanel.repaint();
    }
}