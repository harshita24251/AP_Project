package edu.univ.erp.events;

import java.util.ArrayList;

public class RefreshScreen {
    public static ArrayList<ListenOnSave> listener = new ArrayList<>();

    public static void addListener(ListenOnSave l){
        listener.add(l);
    }

    public static void broadcast(String id){
        for (ListenOnSave lst : listener){
            lst.saved(id);
        }
    }
}
