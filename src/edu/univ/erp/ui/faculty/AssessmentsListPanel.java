package edu.univ.erp.ui.faculty;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import edu.univ.erp.api.instructor.*;
import edu.univ.erp.domain.*;
import java.util.ArrayList;

/**
 * serial no | checkbox(for deletion) | edit | title | start_date | end_date(deadline) | attachments | max. marks
 */

public AssessmentsListPanel extends JPanel{
    public ArrayList<ArrayList<String>> listOfAssessments;

    public AssessmentsListPanel(float width, float height, String Course_ID){
        setPreferredSize(new Dimension(Math.round(width), Math.round(height)))
        setBackground(Color.WHITE);

        listOfAssessments = getComponents.fetch(Course_ID);

        JTabel tableOfAssessments = new JTabel();
    }

    private class tabelModel extends AbstractTabelModel{
        public int getColumnCount(){
            return 8;
        }

        public int getRowCount(){
            return listOfAssessments.size();
        
        }

        public String getValueAt(int r, int c){
            
        }
    }
}