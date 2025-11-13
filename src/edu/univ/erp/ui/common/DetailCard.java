package edu.univ.erp.ui.common;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class DetailCard extends JPanel{
    public DetailCard(String label, String value, int titlefontSize, int valuefontSize, float width, float height){
        setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        JPanel bottom = new JPanel();

        JLabel topLabel = new JLabel(label);
        topLabel.setFont(new Font("Roboto Mono", Font.PLAIN, titlefontSize));
        topLabel.setForeground(Color.gray);

        JLabel bottomLabel = new JLabel(value);
        bottomLabel.setFont(new Font("Roboto Mono", Font.PLAIN, valuefontSize));

        top.add(topLabel);
        top.setBackground(Color.WHITE);
        top.setBorder(new EmptyBorder(0, 0, -6, 0));
        bottom.add(bottomLabel);
        bottom.setBackground(Color.WHITE);
        bottom.setBorder(new EmptyBorder(-3, 0, 0, 0));

        // this.setBorder(new EmptyBorder(10, 10, 100, 10));
        this.setBackground(Color.WHITE);
        this.add(top, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.CENTER);
    }
}