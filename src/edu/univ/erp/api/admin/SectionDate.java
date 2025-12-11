package edu.univ.erp.api.admin;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import edu.univ.erp.data.*;
import edu.univ.erp.domain.Admin;

public class SectionDate {
    public static void update(String Section_Id, Timestamp start, Timestamp end){
        AdminData.updateSectionDate(Section_Id, start, end);
    }
}
