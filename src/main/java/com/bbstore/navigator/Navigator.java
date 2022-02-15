package com.bbstore.navigator;

import com.bbstore.ui.GUI;

import java.util.HashMap;

public class Navigator {
    private static GUI previousScreen;
    private static GUI currentScreen;
    private static HashMap<String, GUI> routes;

    public static void setRoutes(HashMap<String, GUI> _routes){
        routes = _routes;
    }
    public static void push(String screen){
        if(currentScreen != null) {
            currentScreen.setVisible(false);
        }
        previousScreen = currentScreen;
        currentScreen = routes.get(screen);
        currentScreen.setVisible(true);
    }
    public static void pop(){
        currentScreen.setVisible(false);
        previousScreen.setVisible(true);

        GUI temp = previousScreen;
        previousScreen = currentScreen;
        currentScreen = temp;
    }

}
