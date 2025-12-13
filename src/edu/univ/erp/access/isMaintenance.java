package edu.univ.erp.access;

public class isMaintenance {

    private static boolean check = false;

    public static void setMaintenance(){
        check = true;
    }

    public static void removeMaintenance(){
        check  = false;
    }

    public static boolean on(){
        return check;
    }
}
