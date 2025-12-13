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
import edu.univ.erp.ui.common.popup.*;
import edu.univ.erp.ui.faculty.popup.RemoveAssessmentDialog;
import edu.univ.erp.access.*;
import edu.univ.erp.ui.faculty.popup.ViewGrades;

public class Assessments extends JPanel implements ListenOnSave {

    private HashMap<String, JPanel> revalidations;
    private HashMap<String, HashMap<JCheckBox, JPanel>> checkBoxes = new HashMap<>();
    private ArrayList<JButton> showHideButton = new ArrayList<>();
    private HashMap<String, JPanel> widgetMap = new HashMap<>();
    private HashMap<String, HashMap<JPanel, String>> removeByTitles = new HashMap<>();

    private HashMap<String, JButton> addButton = new HashMap<>();
    private HashMap<String, JButton> removeButton = new HashMap<>();

    private float width;
    private float height;

    RemoveAssessmentDialog rm;

    public Assessments(float width, float height){
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        revalidations = new HashMap<>();
         //--------------------------styling tabs-----------------------------------
        UIManager.put("TabbedPane.underlineColor", new Color(78, 178, 165));
        UIManager.put("TabbedPane.inactiveUnderlineColor", new Color(78, 178, 165));

        //---------------------------------------widgets------------------------------------------
        Icon plus = new FlatSVGIcon("plus.svg", 0.25f);
        Icon  cross = new FlatSVGIcon("cross.svg", 0.25f);


//        widgets.remove(createAssessment);

//        JButton deleteAssessment = new JButton("Remove", cross);
//        deleteAssessment.addActionListener(new createAssessmentEvent(Co));
//        widgets.add(deleteAssessment, BorderLayout.WEST);
//        deleteAssessment.setVisible(false);

//        widgets.add(createAssessment, BorderLayout.WEST);
//        createAssessment.revalidate();
//        createAssessment.repaint();
        //----------------------------------------------------------------------------------------

//        widgets.setBackground(Color.GRAY);

        ArrayList<ArrayList<String>> sections = SectionDetails.fetch();

        JTabbedPane mainPane = new JTabbedPane();

        for (ArrayList<String> arr: sections){
            JPanel widgets = new JPanel(new BorderLayout());
            widgets.setPreferredSize(new Dimension(Math.round(width), Math.round(height * 0.05f)));
            widgets.setBorder(new EmptyBorder(10, 20, 0, 20));
            widgets.setBackground(Color.WHITE);
            widgetMap.put(arr.get(1), widgets);

            JButton createAssessment = new JButton("New Assessment", plus);
            JButton viewGrades = new JButton("View Grade");
            viewGrades.addActionListener(new seeGradesPerSection(arr.get(1)));
            createAssessment.addActionListener(new createAssessmentEvent(arr.get(1)));

            JButton deleteAssessment = new JButton("Remove", cross);
//            deleteAssessment.addActionListener(new removeAssessmentEvent(arr.get(1), widgetMap.get(arr.get(1)), createAssessment, deleteAssessment));
            deleteAssessment.addActionListener(new removeDone(new removeAssessmentEvent(arr.get(1), widgetMap.get(arr.get(1)), createAssessment, deleteAssessment)));

            addButton.put(arr.get(1), createAssessment);
            removeButton.put(arr.get(1), deleteAssessment);

            widgets.add(createAssessment, BorderLayout.WEST);
            widgets.add(viewGrades, BorderLayout.EAST);

            JPanel MainPanel = new JPanel();
            MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
            MainPanel.setBackground(Color.WHITE);
            MainPanel.setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
            MainPanel.add(widgets);

            AssessmentsListPanel tmp = new AssessmentsListPanel(width, height, arr.get(1));
            System.out.println(tmp);

            removeByTitles.put(arr.get(1), tmp.getTitleMap());

            HashMap<JCheckBox, JPanel> hs = tmp.getCheckBoxObjects();
            checkBoxes.put(arr.get(1), hs);
            for (JCheckBox check : hs.keySet()){
                check.addItemListener(new listenCheckbox(arr.get(1), widgetMap.get(arr.get(1)), createAssessment, deleteAssessment));
            }

            System.out.println(arr.get(1));

            revalidations.put(arr.get(1), tmp);
            MainPanel.add(tmp);
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

        AssessmentsListPanel newPanel = new AssessmentsListPanel(width, height, Course_ID);
        HashMap<JCheckBox, JPanel> hs = newPanel.getCheckBoxObjects();
        checkBoxes.put(Course_ID, hs);
        for (JCheckBox check : hs.keySet()){
            check.addItemListener(new listenCheckbox(Course_ID, widgetMap.get(Course_ID), addButton.get(Course_ID), removeButton.get(Course_ID)));
        }
        removeByTitles.put(Course_ID, newPanel.getTitleMap());

        revalidations.put(Course_ID, newPanel);
        parentPanel.add(newPanel);

        parentPanel.revalidate();
        parentPanel.repaint();
    }

    private class listenCheckbox implements ItemListener{
        JButton adder;
        JButton remover;
        JPanel widget;
        String Course_ID;

        public listenCheckbox(String Course_ID, JPanel widget, JButton adder, JButton remover){
            this.widget = widget;
            this.adder = adder;
            this.remover = remover;
            this.Course_ID = Course_ID;
        }

        public void itemStateChanged(ItemEvent e){
            boolean allDisabled = true;
            for (JCheckBox checkbox : checkBoxes.get(Course_ID).keySet()){
                if (checkbox.isSelected()){
                    allDisabled = false;
                    break;
                }
            }

            if (allDisabled == true){
                widget.remove(remover);
                widget.add(adder, BorderLayout.WEST);
            }
            else{
                widget.remove(adder);
                widget.add(remover, BorderLayout.WEST);
            }

            widget.revalidate();
            widget.repaint();
        }
    }

    private class createAssessmentEvent implements ActionListener{
        String Course_ID;

        public createAssessmentEvent(String Course_ID){
            this.Course_ID = Course_ID;
        }

        public void actionPerformed(ActionEvent e){
            if (!isMaintenance.on()){
                JDialog openDialog = new CreateAssessment(Course_ID);
                openDialog.setVisible(true);
            }
            else{
                Alert A = new Alert("Maintenance Undergoing, Can't Create Assessment", "Close");
                A.setfont(new Font("Segoe UI", Font.PLAIN, 15));
            }
        }
    }

    private class removeAssessmentEvent implements ActionListener{
        JButton adder;
        JButton remover;
        JPanel widget;
        String Course_ID;


        public removeAssessmentEvent(String Course_ID, JPanel widget, JButton adder, JButton remover){
            this.widget = widget;
            this.adder = adder;
            this.remover = remover;
            this.Course_ID = Course_ID;
        }

        public void actionPerformed(ActionEvent e){

            if (!isMaintenance.on()){
                for (JCheckBox chk : checkBoxes.get(Course_ID).keySet()) {
                    if (chk.isSelected()) {
                        RemoveAssessment.remove(Course_ID, removeByTitles.get(Course_ID).get(checkBoxes.get(Course_ID).get(chk)));
                        checkBoxes.get(Course_ID).get(chk).setVisible(false);
                    }
                }

                widget.remove(remover);
                widget.add(adder, BorderLayout.WEST);

                widget.revalidate();
                widget.repaint();
            }
            else{
                Alert A = new Alert("Maintenance Undergoing, Can't Remove Assessment", "Close");
                A.setfont(new Font("Segoe UI", Font.PLAIN, 15));
            }
        }
    }

    private class removeDone implements ActionListener{
        ActionListener ae;
        public removeDone(ActionListener ae){
            this.ae = ae;
        }

        public void actionPerformed(ActionEvent e){
            rm = new RemoveAssessmentDialog();
            rm.setAction(ae);
        }
    }

    private class seeGradesPerSection implements ActionListener{
        String Course_ID;
        public seeGradesPerSection(String Course_ID){
            this.Course_ID = Course_ID;
        }

        public void actionPerformed(ActionEvent e){
            new ViewGrades(Course_ID);
        }
    }
}