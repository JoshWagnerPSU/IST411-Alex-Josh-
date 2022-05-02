package Controller;

/**
 * IST 411 Final Project
 * File: Controller.java
 * 
 * Purpose: This class represents the controller section of the MVC that 
 *          communicates between the various model classes and the 
 *          application's GUI.
 * 
 * Last Edited On:5/2/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

import View.GUI;
import Model.CurDeath.CurrentDeathData;
import Model.CurVac.CurrentVacData;
import Model.Database.DatabaseConnection;
import Model.HisDeath.HistoricalDeathCalc;
import Model.HisDeath.HistoricalDeathDataUtil;
import Model.HisVac.HistoricalVacCalc;
import Model.HisVac.HistoricalVacDataUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Controller implements ActionListener {
    
    // Private Attributes
    private DatabaseConnection dbc;
    private GUI gui;
    private CurrentVacData cvd;
    private CurrentDeathData cdd;
    
    
    /**
     * Constructor method that sets private attributes, gathers/updates data,
     *      and sets up interactivity.
     * 
     * @param gui The GUI the controller communicates with.
     * @throws IOException 
     */
    public Controller(GUI gui) throws IOException{
        // Sets private attributes
        this.gui = gui;
        dbc = new DatabaseConnection();
        cvd = new CurrentVacData(); // Pulls current vaccination data
        cdd = new CurrentDeathData(); // Pulls current death data
        
        // Creates database tables and updates data
        HistoricalVacDataUtil.run(dbc.getStmt()); // Vaccination table
        HistoricalDeathDataUtil.run(dbc.getStmt()); // Death table
        
        setActionListeners();
    }
    
    
    /**
     * setActionListeners() applies action listeners to swing elements in 
     *      the GUI.
     */
    private void setActionListeners(){
        // Main Screen Buttons
        gui.getVaccinations().addActionListener(this);
        gui.getDeaths().addActionListener(this);
        
        // Back Buttons
        gui.getVacBack().addActionListener(this);
        gui.getDeathBack().addActionListener(this);
        
        // Option Dropdown Menus
        gui.getVacOption().addActionListener(this);
        gui.getDeathOption().addActionListener(this);
        
        // Search Buttons
        gui.getVacSearch().addActionListener(this);
        gui.getDeathSearch().addActionListener(this); 
    }

    
    /**
     * actionPerformed() handles user interaction with the GUI.
     * 
     * @param e An action event that occurs when a user interacts with 
     *          an element.
     */
    @Override
    public void actionPerformed(ActionEvent e){ 
        // Local variables for easy refrence                                    
        Object userInput = e.getSource();
        
        // Loads the vacination screen and resets the search fields
        if(userInput == gui.getVaccinations()){
            gui.view(gui.getVacScreen());
            gui.hideVacSubOptions();
            gui.getVacLocation().setSelectedIndex(0);
            gui.getVacOption().setSelectedIndex(0);
        }
        // Loads the death screen and resets the search fields 
        else if (userInput == gui.getDeaths()){
            gui.view(gui.getDeathScreen());
            gui.hideDeathSubOptions();
            gui.getDeathLocation().setSelectedIndex(0);
            gui.getDeathOption().setSelectedIndex(0);
        }
        // Loads the main screen
        else if (userInput == gui.getVacBack()){
            gui.view(gui.getMainScreen());
        }
        // Loads the main screen
        else if (userInput == gui.getDeathBack()){
            gui.view(gui.getMainScreen());
        }
        // Handles when the vaccination dropdown menu is interacted with
        else if (userInput == gui.getVacOption()){
            gui.hideVacSubOptions(); // Hides all suboptions
            
            // Displays the age option
            if(gui.getVacOption().getSelectedItem().equals("Age")){
                gui.getVacAgeLabel().setVisible(true);
                gui.getVacAge().setVisible(true);
            } 
            // Displays the race option
            else if (gui.getVacOption().getSelectedItem().equals("Race")){
                gui.getVacRaceLabel().setVisible(true);
                gui.getVacRace().setVisible(true);
            } 
            // Displays the gender option
            else if (gui.getVacOption().getSelectedItem().equals("Gender")){
                gui.getVacGenderLabel().setVisible(true);
                gui.getVacGender().setVisible(true);
            } 
            // Displays the total option
            else if (gui.getVacOption().getSelectedItem().equals("Total")){
                gui.getBegDateLabel().setVisible(true);
                gui.getStartDate().setVisible(true);
                gui.getEndDateLabel().setVisible(true);
                gui.getEndDate().setVisible(true);
            }
        }
        // Handles when the death dropdown menu is interacted with 
        else if (userInput == gui.getDeathOption()){
            gui.hideDeathSubOptions(); // Hides all suboptions
            
            // Displays the age option
            if(gui.getDeathOption().getSelectedItem().equals("Age")){
                gui.getDeathAgeLabel().setVisible(true);
                gui.getDeathAge().setVisible(true);
            } 
            // Displays the race option
            else if (gui.getDeathOption().getSelectedItem().equals("Race")){
                gui.getDeathRaceLabel().setVisible(true);
                gui.getDeathRace().setVisible(true);
            } 
            // Displays the gender option
            else if (gui.getDeathOption().getSelectedItem().equals("Gender")){
                gui.getDeathGenderLabel().setVisible(true);
                gui.getDeathGender().setVisible(true);
            } 
            // Displays the total option
            else if (gui.getDeathOption().getSelectedItem().equals("Total")){
                gui.getDateLabel().setVisible(true);
                gui.getDeathMonth().setVisible(true);
                gui.getDeathYear().setVisible(true);
            }
        }
        // Handles a vaccination search
        else if (userInput == gui.getVacSearch()){
            calcVacSearch();
        }
        // Handles a death search
        else if (userInput == gui.getDeathSearch()){
            calcDeathSearch();
        }
    }
    
    
    /**
     * calcVacSearch() handles a vaccination search and displays the answer in
     *      the GUI.
     */
    private void calcVacSearch(){
        // Checks if the user hasn't selected a location
        if(gui.getVacLocation().getSelectedIndex() == 0){
            // Displays error
           gui.getVacAnswer().setText("Please Select a Location.");
        } 
        // Checks if the user hasn't selected an option
        else if (gui.getVacOption().getSelectedIndex() == 0){
            // Displays error
            gui.getVacAnswer().setText("Please Select an Option.");   
        } 
        // Else, the user has selected the inital options
        else {           
            // Checks if the user selected the age option
            if(gui.getVacOption().getSelectedItem().equals("Age")){
                // Checks if the user has not selected an age group
                if(gui.getVacAge().getSelectedIndex() == 0){
                    // Displays error
                    gui.getVacAnswer().setText("Please Select an Age Group.");
                } 
                // Checks if the user selected all for location
                else if(gui.getVacLocation().getSelectedItem().equals("All")){
                    // Displays the answer
                    gui.getVacAnswer().setText(
                        cvd.calcVAS(
                            gui.getVacAge().getSelectedItem().toString()
                        )
                    );               
                } 
                // Else, the user has selected a county and age group
                else {
                    // Displays the answer
                    gui.getVacAnswer().setText(
                        cvd.calcVAC(
                            gui.getVacLocation().getSelectedItem().toString(), 
                            gui.getVacAge().getSelectedItem().toString()
                        )
                    );
                }   
            } 
            // Checks if the user selected the race option
            else if (gui.getVacOption().getSelectedItem().equals("Race")){
                // Checks if the user has not selected a race group
                if(gui.getVacRace().getSelectedIndex() == 0){
                    // Displays error
                    gui.getVacAnswer().setText("Please Select a Race Group.");
                } 
                // Checks if the user selected all for location
                else if(gui.getVacLocation().getSelectedItem().equals("All")){
                    // Displays the answer
                    gui.getVacAnswer().setText(
                        cvd.calcVRS(
                            gui.getVacRace().getSelectedItem().toString()
                        )
                    ); 
                } 
                // Else, the user has selected a county and race group
                else {
                    // Displays the answer
                    gui.getVacAnswer().setText(
                        cvd.calcVRC(
                            gui.getVacLocation().getSelectedItem().toString(), 
                            gui.getVacRace().getSelectedItem().toString()
                        )
                    );
                }          
            } 
            // Checks if the user selected the gender option
            else if (gui.getVacOption().getSelectedItem().equals("Gender")){
                // Checks if the user has not selected a gender group
                if(gui.getVacGender().getSelectedIndex() == 0){
                    // Displays error
                    gui.getVacAnswer().setText("Please Select a Gender Group.");
                } 
                // Checks if the user has selected all for location
                else if(gui.getVacLocation().getSelectedItem().equals("All")){
                    // Displays the answer
                    gui.getVacAnswer().setText(
                        cvd.calcVGS(
                            gui.getVacGender().getSelectedItem().toString()
                        )
                    );
                } 
                // Else, the user has selected a county and a gender group
                else {
                    // Displays the answer
                    gui.getVacAnswer().setText(
                        cvd.calcVGC(
                            gui.getVacLocation().getSelectedItem().toString(), 
                            gui.getVacGender().getSelectedItem().toString()
                        )
                    );
                }        
            } 
            // Else, the user selected the total option
            else {
                // Checks if the user has not selected two dates
                if(gui.getStartDate().getModel().getValue() == null ||
                   gui.getEndDate().getModel().getValue() == null     )
                {
                    // Displays error
                    gui.getVacAnswer().setText("Please Select two dates.");
                } 
                // Else, the user has selected both dates
                else {
                    // Tool to format the dates from the JDatePicker
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    // Local variables to hold the search parameters 
                    String location, start, end;
                    // Sets local variables
                    location = gui.getVacLocation().getSelectedItem().
                            toString();
                    start = sdf.format(gui.getStartDate().getModel().
                            getValue());
                    end = sdf.format(gui.getEndDate().getModel().getValue());
                    
                    // Displays the answer
                    gui.getVacAnswer().setText(
                        HistoricalVacCalc.run(
                            dbc.getStmt(), location, start, end
                        )         
                    );
                }      
            }
        }
    }
    
    
    /**
     * calcDeathSearch() handles a death search and displays the answer in
     *      the GUI.
     */
    private void calcDeathSearch(){
        // Checks if the user hasn't selected a location
        if(gui.getDeathLocation().getSelectedIndex() == 0){
           // Displays error
           gui.getDeathAnswer().setText("Please Select a Location.");
        } 
        // Checks if the user hasn't selected an option
        else if (gui.getDeathOption().getSelectedIndex() == 0){
            // Displays error
            gui.getDeathAnswer().setText("Please Select an Option.");     
        } 
        // Else, the user has selected the inital options
        else {           
            // Checks if the user selected the age option
            if(gui.getDeathOption().getSelectedItem().equals("Age")){
                // Checks if the user has not selected an age group
                if(gui.getDeathAge().getSelectedIndex() == 0){
                    // Displays error
                    gui.getDeathAnswer().setText("Please Select an Age Group.");  
                } 
                // Checks if the user selected all for location
                else if(gui.getDeathLocation().getSelectedItem().equals("All")){
                    // Displays the answer
                    gui.getDeathAnswer().setText(
                        cdd.calcDAS(
                            gui.getDeathAge().getSelectedItem().toString()
                        )
                    );               
                } 
                // Else, the user has selected a county and age group
                else {
                    // Displays the answer
                    gui.getDeathAnswer().setText(
                        cdd.calcDAC(
                            gui.getDeathLocation().getSelectedItem().toString(),
                            gui.getDeathAge().getSelectedItem().toString()
                        )
                    );
                }   
            } 
            // Checks if the user selected the race option
            else if (gui.getDeathOption().getSelectedItem().equals("Race")){
                // Checks if the user has not selected a race group
                if(gui.getDeathRace().getSelectedIndex() == 0){
                    // Displays error
                    gui.getDeathAnswer().setText("Please Select a Race Group.");
                } 
                // Checks if the user selected all for location
                else if(gui.getDeathLocation().getSelectedItem().equals("All")){
                    // Displays the answer 
                    gui.getDeathAnswer().setText(
                        cdd.calcDRS(
                            gui.getDeathRace().getSelectedItem().toString()
                        )
                    ); 
                } 
                // Else, the user has selected a county and race group
                else {
                    // Displays the answer 
                    gui.getDeathAnswer().setText(
                        cdd.calcDRC(
                            gui.getDeathLocation().getSelectedItem().toString(),
                            gui.getDeathRace().getSelectedItem().toString()
                        )
                    );
                }          
            } 
            // Checks if the user selected the gender option
            else if (gui.getDeathOption().getSelectedItem().equals("Gender")){
                // Checks if the user has not selected a gender group
                if(gui.getDeathGender().getSelectedIndex() == 0){
                    // Displays error
                    gui.getDeathAnswer().setText("Please Select a Gender Group.");  
                } 
                // Checks if the user has selected all for location
                else if(gui.getDeathLocation().getSelectedItem().equals("All")){
                    // Displays the answer
                    gui.getDeathAnswer().setText(
                        cdd.calcDGS(
                            gui.getDeathGender().getSelectedItem().toString()
                        )
                    );
                } 
                // Else, the user has selected a county and a gender group
                else {
                    // Displays the answer
                    gui.getDeathAnswer().setText(
                        cdd.calcDGC(
                            gui.getDeathLocation().getSelectedItem().toString(),
                            gui.getDeathGender().getSelectedItem().toString()
                        )
                    );
                }        
            } 
            // Else, the user selected the total option
            else {
                // Checks if the user has not selected a date
                if(gui.getDeathMonth().getSelectedIndex() == 0 ||
                   gui.getDeathYear().getSelectedIndex() == 0)
                {
                    // Displays error
                    gui.getDeathAnswer().setText("Please Select a date.");
                } 
                // Else the user has selected a date
                else {
                    // Local variables to hold the search parameters
                    String location = gui.getDeathLocation().getSelectedItem().
                            toString();
                    String month = gui.getDeathMonth().getSelectedItem().
                            toString();
                    String year = gui.getDeathYear().getSelectedItem().
                            toString();
                    
                    // Converts the month field to a number
                    switch (month) {
                        case "January":month = "01"; break;
                        case "February":month = "02"; break;
                        case "March": month = "03"; break;
                        case "April":month = "04"; break;
                        case "May":month = "05"; break;
                        case "June":month = "06"; break;
                        case "July":month = "07"; break;
                        case "August":month = "08"; break;
                        case "September":month = "09"; break;
                        case "October":month = "10"; break;
                        case "November":month = "11"; break;
                        default:month = "12"; break;
                    }
                    
                    // Creates a formatted date from the local variables
                    String date = month + "/01/" + year;
                    
                    // Displays the answer
                    gui.getDeathAnswer().setText(HistoricalDeathCalc.run(
                            dbc.getStmt(), location, date)
                    );
                }      
            }
        }
    }  
}