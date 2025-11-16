package edu.univ.erp.api.catalog;

import edu.univ.erp.data;
import java.util.HashMap;
/**
 * Class to return the semester and its sgpa and HashMap format
 */
public class CGPAPerSemester{
    public static HashMap<Integer, String> fetch(){
        return StudentData.getCGPAPerSemester();
    }
}