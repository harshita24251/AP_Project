package edu.univ.erp.ui.faculty;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import edu.univ.erp.api.instructor.*;
import edu.univ.erp.domain.*;
import java.util.ArrayList;
import java.util.HashMap;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import edu.univ.erp.ui.common.events.*;
import edu.univ.erp.ui.faculty.popup.CreateAssessment;

/**
 * serial no | checkbox(for deletion) | edit | title | start_date | end_date(deadline) | attachments | max. marks
 */

public class AssessmentsListPanel extends JPanel{
    private ArrayList<ArrayList<Object>> listOfAssessments;
    private HashMap<JCheckBox, JPanel> checkboxes= new HashMap<>();

    public AssessmentsListPanel(float width, float height_, String Course_ID){
        FlatLightLaf.setup();

        //--------------------------------styles----------------------------------
//        UIManager.put("Table.rowHeight", 40);

        setPreferredSize(new Dimension(Math.round(width), Math.round(height_)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        listOfAssessments = getComponents.fetch(Course_ID);

        //--------------------------------------Icons-----------------------------------------
        Icon clip = new FlatSVGIcon("clip.svg", 0.25f);
        Icon  clipLight = new FlatSVGIcon("clipLight.svg", 0.25f);

        Icon editIcon = new FlatSVGIcon("edit.svg", 0.25f);
        Icon editIconLight = new FlatSVGIcon("editLight.svg", 0.25f);

        Icon ticked = new FlatSVGIcon("checkbox-ticked.svg", 0.32f);
        Icon tickedLight = new FlatSVGIcon("tickedLight.svg", 0.32f);

        Icon unticked = new FlatSVGIcon("checkbox-unticked.svg", 0.32f);
        Icon untickedLight = new FlatSVGIcon("untickedLight.svg", 0.32f);

        //---------------------------------for table title-----------------------------------

        //-----------------------------------------------------------------------------------


        int x = Math.round(0.06f * width);
        int y = Math.round(0.15f * width);
        int t = Math.round(0.30f * width);
        float height = height_ * 0.04f;

        boolean columnHeading = true;

        for (ArrayList<Object> arr : listOfAssessments) {
            ArrayList<JPanel> forHoverEffect = new ArrayList<>();

            // -------------------------------------------------------------


            // -------------------------------------------------------------
            if (columnHeading){
                JPanel main = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
                main.setMaximumSize(new Dimension(Math.round(width), Math.round(height)));
                main.setBackground(Color.WHITE);

                JPanel no = new JPanel(new BorderLayout());
                no.setPreferredSize(new Dimension(x - 20, Math.round(height)));
                no.setBackground(Color.WHITE);

                JPanel delete = new JPanel(new BorderLayout());
                delete.setPreferredSize(new Dimension(x - 10, Math.round(height)));
                delete.setBackground(Color.WHITE);

                JPanel edit = new JPanel(new BorderLayout());
                edit.setPreferredSize(new Dimension(x - 20, Math.round(height)));
                edit.setBackground(Color.WHITE);

                JPanel title = new JPanel(new BorderLayout());
                title.setPreferredSize(new Dimension(t, Math.round(height)));
                title.setBackground(Color.WHITE);

                JPanel start_date = new JPanel(new BorderLayout());
                start_date.setPreferredSize(new Dimension(y, Math.round(height)));
                start_date.setBackground(Color.WHITE);

                JPanel end_date = new JPanel(new BorderLayout());
                end_date.setPreferredSize(new Dimension(y, Math.round(height)));
                end_date.setBackground(Color.WHITE);

                JPanel attachments = new JPanel(new BorderLayout());
                attachments.setPreferredSize(new Dimension(x - 30, Math.round(height)));
                attachments.setBackground(Color.WHITE);

                JPanel maxMarks = new JPanel(new BorderLayout());
                maxMarks.setPreferredSize(new Dimension(x, Math.round(height)));
                maxMarks.setBackground(Color.WHITE);

                JPanel weightage = new JPanel(new BorderLayout());
                weightage.setPreferredSize(new Dimension(x, Math.round(height)));
                weightage.setBackground(Color.WHITE);

                JLabel a = new JLabel("");
                JLabel b = new JLabel("");
                JLabel k = new JLabel("");
                JLabel c = new JLabel("Title");
                JLabel d = new JLabel("Start_time");
                JLabel e = new JLabel("End_time");
                JLabel f = new JLabel("Files");
                JLabel g = new JLabel("Max. Marks");
                JLabel h = new JLabel("Weightage");

                no.add(a);
                delete.add(b);
                edit.add(k);
                title.add(c);
                start_date.add(d);
                end_date.add(e);
                attachments.add(f);
                maxMarks.add(g);
                weightage.add(h);

                columnHeading = false;

                main.add(no);
                main.add(delete);
                main.add(edit);
                main.add(title);
                main.add(start_date);
                main.add(end_date);
                main.add(attachments);
                main.add(maxMarks);
                main.add(weightage);

                add(main);
            }

            JPanel main = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            main.setMaximumSize(new Dimension(Math.round(width), Math.round(height)));
            main.setBackground(Color.WHITE);
            forHoverEffect.add(main);

            JPanel no = new JPanel(new BorderLayout());
            no.setPreferredSize(new Dimension(x - 20, Math.round(height)));
            no.setBackground(Color.WHITE);
            forHoverEffect.add(no);

            JPanel delete = new JPanel(new BorderLayout());
            delete.setPreferredSize(new Dimension(x - 10, Math.round(height)));
            delete.setBackground(Color.WHITE);
            forHoverEffect.add(delete);

            JPanel edit = new JPanel(new BorderLayout());
            edit.setPreferredSize(new Dimension(x - 20, Math.round(height)));
            edit.setBackground(Color.WHITE);
            forHoverEffect.add(edit);

            JPanel title = new JPanel(new BorderLayout());
            title.setPreferredSize(new Dimension(t, Math.round(height)));
            title.setBackground(Color.WHITE);
            forHoverEffect.add(title);

            JPanel start_date = new JPanel(new BorderLayout());
            start_date.setPreferredSize(new Dimension(y, Math.round(height)));
            start_date.setBackground(Color.WHITE);
            forHoverEffect.add(start_date);

            JPanel end_date = new JPanel(new BorderLayout());
            end_date.setPreferredSize(new Dimension(y, Math.round(height)));
            end_date.setBackground(Color.WHITE);
            forHoverEffect.add(end_date);

            JPanel attachments = new JPanel(new BorderLayout());
            attachments.setPreferredSize(new Dimension(x - 30, Math.round(height)));
            attachments.setBackground(Color.WHITE);
            forHoverEffect.add(attachments);

            JPanel maxMarks = new JPanel(new BorderLayout());
            maxMarks.setPreferredSize(new Dimension(x, Math.round(height)));
            maxMarks.setBackground(Color.WHITE);
            forHoverEffect.add(maxMarks);

            JPanel weightage = new JPanel(new BorderLayout());
            weightage.setPreferredSize(new Dimension(x, Math.round(height)));
            weightage.setBackground(Color.WHITE);
            forHoverEffect.add(weightage);

            JLabel noLabel = new JLabel((String) arr.get(0), SwingConstants.CENTER);
            no.add(noLabel, BorderLayout.CENTER);

            JCheckBox check = new JCheckBox();
            check.setIcon(unticked);
            check.setSelectedIcon(ticked);
            check.setOpaque(false);
            checkboxes.put(check, main);
//            check.addMouseListener(new hoverEffect(forHoverEffect));
            delete.add(check, BorderLayout.CENTER);


            JButton editButton = new JButton(editIcon);
            editButton.setFocusPainted(false);
            editButton.setBorderPainted(false);
            editButton.setContentAreaFilled(false);
            edit.add(editButton, BorderLayout.CENTER);
            editButton.addMouseListener(new ButtonHover(editButton, editIconLight));

            JLabel assessmentTitle = new JLabel((String) arr.get(1));
            title.add(assessmentTitle, BorderLayout.CENTER);

            JLabel startDateLabel = new JLabel((String) arr.get(4));
            start_date.add(startDateLabel, BorderLayout.CENTER);

            JLabel endDateLabel = new JLabel((String) arr.get(5));
            end_date.add(endDateLabel, BorderLayout.CENTER);

            JButton attachmentButton = new JButton(clip);
            attachmentButton.setFocusPainted(false);
            attachmentButton.setBorderPainted(false);
            attachmentButton.setContentAreaFilled(false);
            attachments.add(attachmentButton, BorderLayout.CENTER);
            attachmentButton.addMouseListener(new ButtonHover(attachmentButton, clipLight));

            JLabel maxMarksLabel = new JLabel((String) arr.get(2), SwingConstants.CENTER);
            maxMarks.add(maxMarksLabel, BorderLayout.CENTER);

            JLabel weightageLabel = new JLabel((String) arr.get(3), SwingConstants.CENTER);
            weightage.add(weightageLabel, BorderLayout.CENTER);

            main.add(no);
            main.add(delete);
            main.add(edit);
            main.add(title);
            main.add(start_date);
            main.add(end_date);
            main.add(attachments);
            main.add(maxMarks);
            main.add(weightage);

            main.addMouseListener(new HoverEffect(forHoverEffect));
            add(main);
        }

        //----------------------------------------------------------------------------------



//        JTable tableOfAssessments = new JTable(new tableModel());
//        tableOfAssessments.setFont(new Font("Roboto Mono", Font.PLAIN, 16));
//        tableOfAssessments.setShowGrid(true);
//        tableOfAssessments.setGridColor(Color.BLACK);
//        tableOfAssessments.setRowHeight(40);
//        // tableOfAssessments.setIntercellSpacing(new Dimension(10, 10));
//
//        JScrollPane tableScrollPane = new JScrollPane();
//        tableScrollPane.setViewportView(tableOfAssessments);
//        tableScrollPane.setBorder(null);
//
//        TableColumn column0 = tableOfAssessments.getColumnModel().getColumn(0);
//        TableColumn column1 = tableOfAssessments.getColumnModel().getColumn(1);
//        TableColumn column2 = tableOfAssessments.getColumnModel().getColumn(2);
//        TableColumn column3 = tableOfAssessments.getColumnModel().getColumn(3);
//        TableColumn column4 = tableOfAssessments.getColumnModel().getColumn(4);
//        TableColumn column5 = tableOfAssessments.getColumnModel().getColumn(5);
//        TableColumn column6 = tableOfAssessments.getColumnModel().getColumn(6);
//
//        column0.setMaxWidth(20);
//        column1.setMaxWidth(30);
//        column2.setPreferredWidth(150);
//
//        add(tableScrollPane, BorderLayout.CENTER);
////        add(tableOfAssessments);
//    }
//
//    private class tableModel extends AbstractTableModel{
//        public int getColumnCount(){
//            return 7;
//        }
//
//        public int getRowCount(){
//            return listOfAssessments.size();
//        }
//
//        public Object getValueAt(int r, int c){
//            return listOfAssessments.get(r).get(c);
//        }
//
//        public String getColumnName(int column){
//            if (column == 0) return " ";
//            else if (column == 1) return "";
//            else if (column == 2) return "Title";
//            else if (column == 3) return "Title";
//            else if (column == 4) return "Total Score";
//            else if (column == 5) return "Weightage";
//            else if (column == 6) return "Start_Date";
//            else if (column == 7) return "End_Date";
//            else return "Files";
//        }
//
//        public Class<?> getColumnClass(int column){
//            if (column == 1){
//                return Boolean.class; //puts checkbox there
//            }
//            return Object.class;
//        }
    }

    public HashMap<JCheckBox, JPanel> getCheckBoxObjects(){
        return checkboxes;
    }
}