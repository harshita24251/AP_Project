import edu.univ.erp.ui.assets.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.imageio.*;
import java.lang.Exception;
import java.io.IOException;
import java.util.Arrays;

public class LeftNavPanel extends JPanel{
    public LeftNavPanel(String panelTitle, String[] selectables, int width, int height, int fontSize){
        this.setPreferredSize(new Dimension(width, height));
        //-----------------------Loading Font--------------------
        GraphicsEnvironment ge;
        try{
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Roboto-Light.ttf")));
        }
        catch (IOException | FontFormatException e){}
        //========================================================
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(width, 30));
        JPanel btnPanel = new JPanel();
        btnPanel.setPreferredSize(new Dimension(width, height - 30));
        //-------------------------Icons--------------------------
        Icon bullet = new FlatSVGIcon("bullet.svg", 0.25f);

        //-------------------------Labels-------------------------
        JLabel title = new JLabel(panelTitle);
        title.setFont("Roboto Mono", Font.PLAIN, fontSize-4);

        //-----------------------Adding Labels---------------------
        titlePanel.add(title);

        for (String s : selectables){
            JLabel putLabel = new JLabel(s, bullet, JLabel.LEFT);
            putLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            putLabel.setFont("Roboto Mono", Font.PLAIN, fontSize);
            btnPanel.add(putLabel);
            btnPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        }

        titlePanel.setBackground(Color.WHITE);
        btnPanel.setBackground(Color.WHITE);
    }
}