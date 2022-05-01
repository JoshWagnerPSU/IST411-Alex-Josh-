package Main;

/**
 * IST 411 Final Project
 * File: RunGUI.java
 * 
 * Purpose: 
 * 
 * Last Edited On:4/30/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

import GUI.Controller.GUIController;
import GUI.View.GUI;
import java.io.IOException;

public class RunGUI {
    public static void main(String[] args) throws IOException{
        GUI gui = new GUI();
        GUIController gc = new GUIController(gui);
    }
}
