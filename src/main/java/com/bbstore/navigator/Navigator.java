package com.bbstore.navigator;

import com.bbstore.ui.GUI;

import java.util.HashMap;

public class Navigator {
    private static GUI previousScreen;
    private static GUI currentScreen;
    private static GUI popUpScreen;
    private static HashMap<String, GUI> routes;

    public static void setRoutes(HashMap<String, GUI> _routes){
        routes = _routes;
    }
    public static void push(String screen){
        if(currentScreen != null) {
            currentScreen.dispose();
        }
        previousScreen = currentScreen;
        currentScreen = routes.get(screen);
        currentScreen.setVisible(true);
    }
    public static void pop(){
        if(popUpScreen != null){
            closePopUp();
            return;
        }
        currentScreen.setVisible(false);
        previousScreen.setVisible(true);

        GUI temp = previousScreen;
        previousScreen = currentScreen;
        currentScreen = temp;
    }
    public static void openPopUp(String popUp){
        if(popUpScreen != null){
            popUpScreen.dispose();
        }
        popUpScreen = routes.get(popUp);
        popUpScreen.setVisible(true);
    }
    private static void closePopUp(){
        popUpScreen.dispose();
        popUpScreen = null;
    }

}
