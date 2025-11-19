package edu.univ.erp.ui.login;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.*;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;

public class Login_page2 extends JFrame{
    public Login_page2() {
        FlatLightLaf.setup();
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit krne pe close
        


        // ------full size screen--------------//
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = Math.round(screensize.width * 0.75f);
        int screenHeight = Math.round(screensize.height * 0.75f);
        setSize(screenWidth, screenHeight); // size of frame
        setResizable(false);
        // frame.setResizable(false); // cant cahnge size of frame
        setLocationRelativeTo(null);
   ///     frame.setLayout(new GridLayout(1,2)); // 2 sections m divide 1 row 2 ocloumns

          //-----outer main panel------//
          JPanel outpanel = new JPanel();
          outpanel.setBackground(new Color(245,245,245));///light grey balc
          outpanel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
          
          JPanel mainpanel = new JPanel(new GridLayout(1,2,0,0));
          mainpanel.setOpaque(false); // transparent background to see guy

        //-------left panel---------------//
        JPanel leftpanel = new JPanel(new BorderLayout());
        // leftpanel.setLayout(new BorderLayout());
        leftpanel.setOpaque(false);

       // ImageIcon image = new ImageIcon("image.png");
        ImageIcon iimage = new ImageIcon("lib/assets/iiitdrndblock.jpeg");
        Image scaledicon = iimage.getImage().getScaledInstance(screenWidth/2-30,screenHeight-80 ,Image.SCALE_SMOOTH);
        ImageIcon scaleimage = new ImageIcon(scaledicon);  /// ab in sabse image ka size fix kr diyaImage 
        JLabel imageLabel = new JLabel(scaleimage);
         imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        leftpanel.add(imageLabel,BorderLayout.CENTER);//center me bcz tabhi pura 500*600 ka area cover ho jayega


        JPanel rightpanel = new JPanel(new GridBagLayout());
        rightpanel.setBackground(Color.WHITE);
        // rightpanel.setLayout(new GridBagLayout());
        
        mainpanel.add(leftpanel);
       
          
        //-----right panel--------------//
        Icon logo = new FlatSVGIcon("iiitd_logo_login.svg");
        //   ImageIcon logo = new ImageIcon("lib/assets/iiitdrndblock.jpeg");
        // Image logoicon = logo.getImage().getScaledInstance(screenWidth/8,screenHeight/10 ,Image.SCALE_SMOOTH);
        // ImageIcon scaleimage2 = new ImageIcon(logoicon);  /// ab in sabse image ka size fix kr diyaImage 
        JLabel final_logo = new JLabel(logo); 


         JTextField username = new JTextField();
         username.setMaximumSize(new Dimension(450,40));
         username.setFont(new Font("SansSerif",Font.PLAIN,16));
         username.setBorder(BorderFactory.createTitledBorder("Username"));

         JPasswordField password = new JPasswordField();
         password.setMaximumSize(new Dimension(450,40));
         password.setFont(new Font("SansSerif",Font.PLAIN,16));
         password.setBorder((BorderFactory.createTitledBorder("Password")));

        JButton loginbuttton = new JButton("Login");
        loginbuttton.setBackground(new Color(0,153,153));
        loginbuttton.setForeground(Color.WHITE);
        loginbuttton.setFont(new Font("Roboto Mono",Font.BOLD,16));
        loginbuttton.setFocusPainted(false);
        loginbuttton.setBorder(BorderFactory.createEmptyBorder(10, 10,10,10));
        loginbuttton.setMaximumSize(new Dimension(400,45));
        loginbuttton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       
        JLabel forgot = new JLabel("Forgot password?");
        // forgot.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgot.setFont(new Font("SansSerif",Font.PLAIN,14));
        forgot.setForeground(Color.GRAY);
        
        //---------------------------------logo Panel-----------------------------------
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.add(final_logo, BorderLayout.CENTER);
        logoPanel.setPreferredSize(new Dimension(300, 140));
        logoPanel.setBackground(Color.WHITE);

       // wrapper panel to align bottom-right
JPanel forgotPanel = new JPanel(new BorderLayout());
forgotPanel.setOpaque(false);
forgotPanel.add(forgot, BorderLayout.EAST);
forgotPanel.setMaximumSize(new Dimension(400, 40));

       JPanel loginCard = new JPanel();
       loginCard.setLayout(new BoxLayout(loginCard, BoxLayout.Y_AXIS));
       loginCard.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20)); //

       loginCard.setBackground(Color.WHITE);

       loginCard.add(Box.createVerticalGlue());
       loginCard.add(logoPanel);
       loginCard.add(Box.createRigidArea(new Dimension(0,40))); //
       loginCard.add(username);
       loginCard.add(Box.createRigidArea(new Dimension(0,20)));
       loginCard.add(password);
       loginCard.add(Box.createRigidArea(new Dimension(0,30)));
       loginCard.add(loginbuttton);
        loginCard.add(Box.createRigidArea(new Dimension(0,5)));
       loginCard.add(forgotPanel);
    loginCard.add(Box.createVerticalGlue());

        rightpanel.add(loginCard);

 mainpanel.add(rightpanel);
        outpanel.add(mainpanel,BorderLayout.CENTER);
        add(outpanel);
        setVisible(true);






    }
    
}