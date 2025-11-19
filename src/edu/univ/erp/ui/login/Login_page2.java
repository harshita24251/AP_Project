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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Login_page2 {
    public Login_page2() {
        JFrame frame = new JFrame();
        frame.setTitle("dashBoard login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit krne pe close
        


        // ------full size screen--------------//
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screensize.width;
        int screenHeight = screensize.height;
        frame.setSize(screenWidth,screenHeight); // size of frame
        // frame.setResizable(false); // cant cahnge size of frame
        frame.setLocationRelativeTo(null);
   ///     frame.setLayout(new GridLayout(1,2)); // 2 sections m divide 1 row 2 ocloumns

          //-----outer main panel------//
          JPanel outpanel = new JPanel();
          outpanel.setBackground(new Color(245,245,245));///light grey balc
          outpanel.setBorder(BorderFactory.createEmptyBorder(45,45,45,45));
          
          JPanel mainpanel = new JPanel(new GridLayout(1,2,0,0));
          mainpanel.setOpaque(false); // transparent background to see guy

        //-------left panel---------------//
        JPanel leftpanel = new JPanel(new BorderLayout());
        // leftpanel.setLayout(new BorderLayout());
        leftpanel.setOpaque(false);

       // ImageIcon image = new ImageIcon("image.png");
        ImageIcon iimage = new ImageIcon("image.png");
        Image scaledicon = iimage.getImage().getScaledInstance(screenWidth/2-80,screenHeight-120 ,Image.SCALE_SMOOTH);
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
          ImageIcon logo = new ImageIcon("iiitdlogo.png");
        Image logoicon = logo.getImage().getScaledInstance(screenWidth/8,screenHeight/10 ,Image.SCALE_SMOOTH);
        ImageIcon scaleimage2 = new ImageIcon(logoicon);  /// ab in sabse image ka size fix kr diyaImage 
        JLabel final_logo = new JLabel(scaleimage2); 


         JTextField username = new JTextField();
         username.setMaximumSize(new Dimension(400,40));
         username.setFont(new Font("SansSerif",Font.PLAIN,16));
         username.setBorder(BorderFactory.createTitledBorder("username"));

         JPasswordField password = new JPasswordField();
         password.setMaximumSize(new Dimension(400,40));
         password.setFont(new Font("SansSerif",Font.PLAIN,16));
         password.setBorder((BorderFactory.createTitledBorder("Password")));

        JButton loginbuttton = new JButton("login");
        loginbuttton.setBackground(new Color(0,153,153));
        loginbuttton.setForeground(Color.WHITE);
        loginbuttton.setFont(new Font("SansSerif",Font.BOLD,16));
        loginbuttton.setFocusPainted(false);
        loginbuttton.setBorder(BorderFactory.createEmptyBorder(10, 10,10,10));
        loginbuttton.setMaximumSize(new Dimension(400,45));
        loginbuttton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       
        JLabel forgot = new JLabel("Forgot password?");
        forgot.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgot.setFont(new Font("SansSerif",Font.PLAIN,14));
        forgot.setForeground(Color.GRAY);
        
       // wrapper panel to align bottom-right
JPanel forgotPanel = new JPanel(new BorderLayout());
forgotPanel.setOpaque(false);
forgotPanel.add(forgot, BorderLayout.EAST);
forgotPanel.setMaximumSize(new Dimension(400, 40));

       JPanel loginCard = new JPanel();
       loginCard.setLayout(new BoxLayout(loginCard, BoxLayout.Y_AXIS));
       loginCard.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

       loginCard.setBackground(Color.WHITE);

       loginCard.add(Box.createVerticalGlue());
       loginCard.add(final_logo);
       loginCard.add(Box.createRigidArea(new Dimension(0,40)));
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
        frame.add(outpanel);
        frame.setVisible(true);






    }
    
}