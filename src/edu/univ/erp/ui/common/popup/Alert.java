package edu.univ.erp.ui.common.popup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Alert extends JDialog {
    JPanel btnPane = new JPanel();
    JButton ok;
    JLabel msg;

    public Alert(String Message, String Button){
        setSize(new Dimension(400, 150));
        setBackground(Color.WHITE);
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
        setTitle("Alert");

        JPanel mainMainPanel = new JPanel();
        mainMainPanel.setLayout(new BoxLayout(mainMainPanel, BoxLayout.Y_AXIS));
        JPanel mainPane = new JPanel();
//        JPanel btnPane = new JPanel();

        msg = new JLabel(Message);
        msg.setFont(new Font("Segoe UI", Font.BOLD, 20));

        ok = new JButton(Button);
        ok.addActionListener(new closeEvent());

        mainPane.add(msg);
        btnPane.add(ok);

        mainMainPanel.add(mainPane);
//        mainMainPanel.add(Box.createRigidArea(new Dimension(10, )));
        mainMainPanel.add(btnPane);
//        add(mainPane);
//        add(Box.createRigidArea(new Dimension(10, 10)));
//        add(btnPane);
        add(mainMainPanel);
        setVisible(true);
    }

    private class closeEvent implements ActionListener {
        public void actionPerformed(ActionEvent e){
            dispose();
        }
    }

    public JPanel getBtnPane(){
        return btnPane;
    }

    public JButton getButton(){
        return ok;
    }

    public void disposeIt(){
        dispose();
    }

    public void setfont(Font f){
        msg.setFont(f);
    }

    public void setAction(ActionListener ae){
        ok.addActionListener(ae);
    }
}
