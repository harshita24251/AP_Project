package edu.univ.erp.api.instructor;

import java.util.ArrayList;
import edu.univ.erp.data.*;

public class SectionDetails{
    public static ArrayList<ArrayList<String>> fetch(){
        return InstructorData.getSectionDetails();
    }
}