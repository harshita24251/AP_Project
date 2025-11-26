package edu.univ.erp.ui.faculty;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import edu.univ.erp.api.instructor.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import edu.univ.erp.events.ListenOnSave;
import edu.univ.erp.events.RefreshScreen;
import edu.univ.erp.ui.faculty.popup.CreateAssessment;

public class Assessments extends JPanel implements ListenOnSave {
    private HashMap<String, JPanel> revalidations;
    private float width;
    private float height;

    public Assessments(float width, float height){
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        revalidations = new HashMap<>();
         //--------------------------styling tabs-----------------------------------
        UIManager.put("TabbedPane.underlineColor", new Color(78, 178, 165));
        UIManager.put("TabbedPane.inactiveUnderlineColor", new Color(78, 178, 165));

        JPanel widgets = new JPanel(new BorderLayout());
        widgets.setPreferredSize(new Dimension(Math.round(width), Math.round(height * 0.05f)));
        widgets.setBorder(new EmptyBorder(10, 20, 0, 20));
        widgets.setBackground(Color.WHITE);

        //---------------------------------------widgets------------------------------------------
        Icon plus = new FlatSVGIcon("plus.svg", 0.25f);
        Icon  cross = new FlatSVGIcon("cross.svg", 0.25f);

        JButton createAssessment = new JButton("New Assessment", plus);
        widgets.add(createAssessment, BorderLayout.WEST);

//        widgets.remove(createAssessment);

//        JButton deleteAssessment = new JButton("Remove", cross);
//        deleteAssessment.addActionListener(new createAssessmentEvent(Co));
//        widgets.add(deleteAssessment, BorderLayout.WEST);
//        deleteAssessment.setVisible(false);

//        widgets.add(createAssessment, BorderLayout.WEST);
        createAssessment.revalidate();
        createAssessment.repaint();
        //----------------------------------------------------------------------------------------

        JPanel MainPanel = new JPanel();
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
        MainPanel.setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
//        widgets.setBackground(Color.GRAY);

        ArrayList<ArrayList<String>> sections = SectionDetails.fetch();

        JTabbedPane mainPane = new JTabbedPane();
        MainPanel.add(widgets);

        for (ArrayList<String> arr: sections){
            JPanel tmp = new AssessmentsListPanel(width, height, arr.get(1));
            revalidations.put(arr.get(1), tmp);
            MainPanel.add(tmp);
            createAssessment.addActionListener(new createAssessmentEvent(arr.get(1)));
            mainPane.add(arr.get(1), MainPanel);
        }

        RefreshScreen.addListener(this);

        add(mainPane);
        setBackground(Color.WHITE);
    }

    public void saved(String Course_ID){
        JPanel oldPanel = revalidations.get(Course_ID);
        Container parentPanel = oldPanel.getParent();
        parentPanel.remove(oldPanel);

        JPanel newPanel = new AssessmentsListPanel(width, height, Course_ID);

        revalidations.put(Course_ID, newPanel);
        parentPanel.add(newPanel);

        parentPanel.revalidate();
        parentPanel.repaint();
    }

    private class listenCheckbox implements ItemListener{
        public void itemStateChanged(ItemEvent e){

        }
    }

    private class createAssessmentEvent implements ActionListener{
        String Course_ID;

        public createAssessmentEvent(String Course_ID){
            this.Course_ID = Course_ID;
        }

        public void actionPerformed(ActionEvent e){
            // will be used to add new assessments
            JDialog openDialog = new CreateAssessment(Course_ID);
            openDialog.setVisible(true);
        }
    }
}