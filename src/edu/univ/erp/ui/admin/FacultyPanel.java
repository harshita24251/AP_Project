package edu.univ.erp.ui.admin;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.sun.tools.javac.Main;
import edu.univ.erp.events.ListenOnSave;
import edu.univ.erp.ui.admin.popup.AddFaculty;
import edu.univ.erp.ui.admin.popup.AddStudents;
import edu.univ.erp.ui.common.popup.Alert;
import edu.univ.erp.ui.faculty.Assessments;
import edu.univ.erp.ui.faculty.StudentsListPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import edu.univ.erp.access.*;

public class FacultyPanel extends JPanel implements ListenOnSave {
    JPanel MainPanel;
    JPanel facultyListAll;
    float width;
    float height;

    public FacultyPanel(float width, float height){
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        //---------------------------------------widgets------------------------------------------
        Icon plus = new FlatSVGIcon("plus.svg", 0.25f);

        JButton addFaculty = new JButton("Add Faculty", plus);
        addFaculty.addActionListener(new addEvent());
//        createAssessment.addActionListener(new Assessments.createAssessmentEvent(arr.get(1)));

        JPanel widgets = new JPanel(new BorderLayout());
        widgets.setPreferredSize(new Dimension(Math.round(width), Math.round(height * 0.05f)));
        widgets.setBorder(new EmptyBorder(10, 20, 0, 20));
        widgets.setBackground(Color.WHITE);
        widgets.add(addFaculty, BorderLayout.WEST);

        facultyListAll = new edu.univ.erp.ui.admin.FacultyListPanel(width, height);

        MainPanel = new JPanel();
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
        MainPanel.setBackground(Color.WHITE);
        MainPanel.setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        MainPanel.add(widgets);
        MainPanel.add(facultyListAll);

        add(MainPanel, BorderLayout.CENTER);
//        add(studentsListAll, BorderLayout.CENTER);
        setVisible(true);
    }

    private class addEvent implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (!isMaintenance.on()){
                new AddFaculty(FacultyPanel.this);
            }
            else{
                Alert A = new Alert("Maintenance Undergoing, Can't Add", "Close");
                A.setfont(new Font("Segoe UI", Font.PLAIN, 15));
            }
        }
    }

    public void saved(String userID){
        MainPanel.remove(facultyListAll);
        MainPanel.add(new edu.univ.erp.ui.admin.FacultyListPanel(width, height));

        MainPanel.revalidate();
        MainPanel.repaint();
    }
}