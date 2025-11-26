package edu.univ.erp.ui.student;

import javax.swing.*;
import java.awt.*;
import java.lang.Exception;
import java.io.IOException;
import java.io.File;
import edu.univ.erp.ui.common.events.*;
import java.sql.*;

public class tmp{
    public static void main(String[] args){
        // GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        // try{
        //     ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Roboto-Light.ttf")));

        // }
        // catch (IOException | FontFormatException e){
        //     e.printStackTrace();
        // }

        // String[] s = ge.getAvailableFontFamilyNames();

        // for (String s_ : s){
        //     System.out.println(s_);
        // }

        EventQueue.invokeLater(() -> {
            Dashboard dash = new Dashboard();
            dash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dash.setVisible(true);

        });

    }
}