package edu.univ.erp.domain;

public class Setting{
    private boolean maintenance;

    public Setting(boolean maintenance){
        this.maintenance = maintenance;
    }

    public static boolean isMaintenanceMode(){
        return maintenance;
    }
}