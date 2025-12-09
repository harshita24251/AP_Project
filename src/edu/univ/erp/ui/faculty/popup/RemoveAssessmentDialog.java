package edu.univ.erp.ui.faculty.popup;

import edu.univ.erp.ui.common.popup.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class RemoveAssessmentDialog{
    private boolean isContinue = false;
    private boolean isPressed = false;
    private Alert alertWindow = new Alert("Are you sure to continue?", "Yes");

    public RemoveAssessmentDialog(){

        JButton no = new JButton("No");
        no.addActionListener(new closeEvent());

        alertWindow.setfont(new Font("Segoe UI", Font.PLAIN, 15));

        alertWindow.getBtnPane().add(no); //gets the button panel object to add a new button
    }

    private class closeEvent implements ActionListener {
        public void actionPerformed(ActionEvent e){
            isPressed = true;
            alertWindow.disposeIt();
        }
    }

    private class continueEvent implements ActionListener{
        public void actionPerformed(ActionEvent e){
            isContinue = true;
            isPressed = true;
        }
    }

    public boolean getRemoveRequest(){
        return isContinue;
    }

    public boolean getPressedStatus(){
        return isPressed;
    }

    public void setAction(ActionListener ae){
        alertWindow.setAction(ae);
    }
}
