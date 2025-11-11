package edu.univ.erp.ui.common;

import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.border.EmptyBorder;

public class NameCard extends JPanel{
    public NameCard(String Name, String Designation, int namefontSize, int desigfontSize, float width, float height){
        setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        setLayout(new BorderLayout());

        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(Math.round(width * 0.2f), Math.round(height)));

        //----------------------------------------------------------------------------------
        JPanel right = new JPanel(new BorderLayout());
        right.setBorder(new EmptyBorder(10, 0, 0, 0));
        right.setPreferredSize(new Dimension(Math.round(width * 0.2f), Math.round(height)));

        JPanel topright = new JPanel(new BorderLayout());
        topright.setPreferredSize(new Dimension(Math.round(width * 0.2f), Math.round(height * 0.5f)));
        JPanel bottomright = new JPanel(new BorderLayout());
        bottomright.setPreferredSize(new Dimension(Math.round(width * 0.2f), Math.round(height * 0.5f)));
        //----------------------------------------------------------------------------------


        Icon profile = new FlatSVGIcon("profile.svg");
        JLabel icon = new JLabel(profile);

        JLabel name = new JLabel(Name);
        name.setFont(new Font("Roboto Mono", Font.PLAIN, namefontSize));

        JLabel designation = new JLabel(Designation);
        designation.setFont(new Font("Roboto Mono", Font.PLAIN, desigfontSize));

        //--------------------------------adding -------------------------------------------
        this.setBackground(Color.WHITE);
        right.add(topright, BorderLayout.NORTH);
        right.add(bottomright, BorderLayout.SOUTH);
        right.setBackground(Color.WHITE);

        left.add(icon);
        left.setBackground(Color.WHITE);

        topright.add(name, BorderLayout.WEST);
        topright.setBackground(Color.WHITE);

        bottomright.add(designation, BorderLayout.WEST);
        bottomright.setBackground(Color.WHITE);

        this.add(left, BorderLayout.WEST);
        this.add(right, BorderLayout.CENTER);
    }
}