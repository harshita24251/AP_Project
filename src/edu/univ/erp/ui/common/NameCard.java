package edu.univ.erp.ui.common;

import edu.univ.erp.ui.assets.*;
import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;

public class NameCard extends JPanel{
    public NameCard(String Name, String Designation, int fontSize, float width, float height){
        setPreferredSize(new Dimension(Math.round(width), Math.round(height)));
        setLayout(new BorderLayout());

        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(Math.round(width * 0.2f), Math.round(height)));

        //----------------------------------------------------------------------------------
        JPanel right = new JPanel(new BorderLayout());
        right.setPreferredSize(new Dimension(Math.round(width * 0.2f), Math.round(height)));

        JPanel topright = new JPanel();
        topright.setPreferredSize(new Dimension(Math.round(width * 0.2f), Math.round(height * 0.5f)));
        JPanel bottomright = new JPanel();
        bottomright.setPreferredSize(new Dimension(Math.round(width * 0.2f), Math.round(height * 0.5f)));
        //----------------------------------------------------------------------------------


        Icon profile = new FlatSVGIcon("profile.svg", 0.25f);
        JLabel icon = new JLabel(profile);

        JLabel name = new JLabel(Name);
        name.setFont(new Font("Roboto Mono", Font.PLAIN, fontSize));

        JLabel designation = new JLabel(Designation);
        designation.setFont(new Font("Roboto Mono", Font.PLAIN, fontSize));

        //--------------------------------adding -------------------------------------------
        add(right, BorderLayout.CENTER);
        add(left, BorderLayout.WEST);

        right.add(topright, BorderLayout.NORTH);
        right.add(bottomright, BorderLayout.SOUTH);

        left.add(icon);
        topright.add(name);
        bottomright.add(designation);
    }
}