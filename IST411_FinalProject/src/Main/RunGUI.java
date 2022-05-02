package Main;

/**
 * IST 411 Final Project
 * File: RunGUI.java
 * 
 * Purpose: This class contains the main method to run the application.
 * 
 * Last Edited On:5/2/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

import Controller.Controller;
import View.GUI;
import java.io.IOException;

public class RunGUI {
    
    /**
     * main() Runs the application.
     * 
     * @param args Not used.
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException{
        GUI gui = new GUI();
        Controller c = new Controller(gui);
    }
}
