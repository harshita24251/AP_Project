package edu.univ.erp.ui.common;

import javax.swing.*;
import java.awt.*;

public class DetailCard extends JFrame{
    public DetailCard(String label, String value, int fontSize, float width, float height){
        setPreferredSize(new Dimesion(Math.round(width), Math.round(height)));

        JPanel top = new JPanel();
        JPanel bottom = new JPanel();

        JLabel topLabel = new JLabel(label);
        topLabel.setFont("Roboto Mono", Font.PLAIN, fontSize);
        topLabel.setForeground(Color.gray);

        JLabel bottomLabel = new JLabel(value);
        bottomLabel.setFont("Roboto Mono", Font.PLAIN, fontSize);

        top.add(topLabel);
        bottom.add(bottomLabel);
    }
}