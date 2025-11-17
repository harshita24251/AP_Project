package edu.univ.erp.ui.student;

import edu.univ.erp.api.catalog.*;
import edu.univ.erp.api.student.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.*;
import java.sql.*;

/**
 * for getting components of courses as a JPanel
 */

public class CourseGradePanel extends JPanel{
    public CourseGradePanel(String  Course_ID, float width, float height){
        // ArrayList<String> courses = registeredCourses.fetch(i);
        setText(Course_ID);
        setPreferredSize(Math.round(width), Math.round(height));
        add(ComponentGradeCard(Course_ID));
    }
}