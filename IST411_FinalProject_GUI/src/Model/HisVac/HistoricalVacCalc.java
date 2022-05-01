package Model.HisVac;

/**
 * IST 411 Final Project
 * File: HistroicalVacCalc.java
 * 
 * Purpose: This class handles a search for Covid vaccination totals in PA 
 *          between two dates based on data in a database.
 *          
 *          This class acts as part of the Model in the MVC.
 * 
 * Last Edited On:4/30/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoricalVacCalc {
    // Private Attributes
    private static Statement stmt;              // Database Connection
    private static final SimpleDateFormat sdf = // Standard date format tool
            new SimpleDateFormat("MM/dd/yyyy");
   
    /**
     * run() is a static method that takes text representing a location, start
     *      date, and end date, converts the last two parameters to Date 
     *      objects, validates that the dates submitted are valid, uses the 
     *      calculate() method to generate an answer from a database, and 
     *      returns a final answer as a formatted String that details the 
     *      different vaccination totals for a location in Pennsylvania 
     *      between two dates.
     * 
     * @param stmt Statement object to communicate with a database.
     * @param location String representing the location being searched.
     * @param begDateText String representing the beginning date of a search.
     * @param endDateText String representing the ending date of a search.
     * @return String representing a detailed answer about different vaccination
     *      totals for a location in Pennsylvania between two dates.
     */
    public static String run(Statement stmt, String location, 
        String begDateText, String endDateText){
        
        HistoricalVacCalc.stmt = stmt; // Sets private attribute
        
        String output;  // The final detailed answer to be returned   
        Date start,     // The starting date of the search
             end;       // The ending date of the search

        try{
            String firstText = "01/01/2021";    // First database entry
            Date first = sdf.parse(firstText); // Converts to Date object
            Date last = new Date(); // Last database entry set as today's date
           
            // Set start and end dates of search
            if (begDateText.isEmpty()){ // Checks if begDateText is empty
                start = first;          // Sets start to the first possible date
            } else {                    // Else, the begDateText is not empty
               start = sdf.parse(begDateText); // Sets start to begDateText
            }
                      
            if (endDateText.isEmpty()){ // Checks if endDateText is empty
                end = last;             // Sets end to today's date
            } else {                    // Else, the endDateText is not empty
                end = sdf.parse(endDateText); // Sets end to endDate Text
            }
            
            // Checks if the ending date comes before the beginning date
            if (end.compareTo(start) == -1){
                output = "Input Error: Ending Date Cannot Come Before "
                        + "Beginning Date";
            } 
            // Checks if the beginning date is set in the future
            else if (start.compareTo(last) == 1) {
                output = "Error: You have selected a Beginning Date in the "
                        + "future";
            } 
            // Checks if the ending date is before the first possible entry
            else if (end.compareTo(first) == -1) {
                output = "Error: You have selected an Ending Date prior to "
                        + "when our records began (01/01/2021).";
            } 
            // Else, valid search dates are being used 
            else {        
                // Checks if the start and end date are the same
                if(start.compareTo(end) == 0){
                    // Formats beginning of output for single date
                    output = "On " + sdf.format(start); 
                } else {
                    // Formats beginning of output for two dates
                    output = "Between " + sdf.format(start) + " and " 
                            + sdf.format(end);
                }
                
                // Calls calculate() to add detailed answer to output
                output += calculate(location, start, end);
                
                // Checks if start was before first possible entry
                if(start.compareTo(first) == -1){
                    // Adds note to end of output
                    output += "\nNOTE: Your Beginning Date was prior to when "
                            + "our records began (01/01/2021).";
                }
                
                // Checks if end was after today
                if (last.compareTo(end) == -1){
                    // Adds note to end of output
                    output += "\nNote: Your Ending Date was in the future.";
                }
            }     
        } catch (Exception e){
            System.out.println(e.toString());
            output = "Calculation Error"; // Sets output to an error message
        }

        return output; // Returns the detailed answer
    }
    
    
    /**
     * calculate() takes a location and two date objects to query a vaccination
     *      database and return a formatted String that details the different 
     *      vaccination totals for a location in Pennsylvania between two dates.
     * 
     * @param location String representing the location being searched.
     * @param start Date representing the beginning date of a search.
     * @param end Date representing the ending date of a search.
     * @return String representing a detailed answer about different vaccination
     *      totals for a location in Pennsylvania between two dates.
     */
    private static String calculate(String location, Date start, Date end){
        
        String query; // Query to gather data
        int part = 0, full = 0, extra = 0; // Holds vacciniaation values
        
        // Checks if the user is asking about all counties
        if (location.equals("State")){
            // Sets query to search for entries of all counties between dates
            query = "SELECT * FROM Historical_Vac WHERE EntryDate BETWEEN '" 
                    + sdf.format(start) + "' AND '" + sdf.format(end) + "'";
        } else {
            // Sets query to search for entires for county between dates
            query = "SELECT * FROM Historical_Vac WHERE County = '" + location 
                    + "' AND EntryDate BETWEEN '" + sdf.format(start) 
                    + "' AND '" + sdf.format(end) + "'";
        }
        
        try {
            ResultSet rs = stmt.executeQuery(query); // Runs query
            
            while (rs.next()){ // Loops through results
                // Adds values to local variable totals
                part += rs.getInt("PartiallyCovered");
                full += rs.getInt("FullyCovered");
                extra += rs.getInt("AdditionalDose");
            }        
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        // Local variables to format data into String text
        String output, partOutput, fullOutput, extraOutput, locationOutput;

        if (part == 1){ // Checks if only 1 person was partially vaccinated
            partOutput = "1 person was"; // Formats text for single person
        } else {
            partOutput = part + " people were"; // Formats text for multiple 
        }

        if (full == 1){ // Checks if only 1 person was fully vaccinated
            fullOutput = "1 person was"; // Formats text for single person
        } else {
            fullOutput = full + " people were"; // Formats text for multiple
        }

        if (extra == 1){ // Checks if only 1 person recieved an extra dose
            extraOutput = "1 person"; // Formats text for single person
        } else {
            extraOutput = full + " people"; // Formats text for multiple
        }

        if (location.equals("State")){ // Checks if the location was all of PA
            locationOutput = "Pennsylvania"; // Formats text for all of PA
        } else {
            locationOutput = location + " County"; // Formats text for county
        }

        // Compiles local Sting variables to create a final answer
        output = " "+ partOutput + " partially vaccinated, " + fullOutput 
                + " fully vaccinated, " + "and " + extraOutput + " received "
                + "an additional dose throughout " + locationOutput + ".";  
  
        return output; // Returns answer
    }
}