package Model.HisDeath;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * IST 411 Final Project
 * File: HistoricalDeathCalc.java
 * 
 * Purpose: This class handles a search for total Covid related deaths in PA 
 *          for a month based on data in a database.
 *          
 *          This class acts as part of the Model in the MVC.
 * 
 * Last Edited On:4/30/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

public class HistoricalDeathCalc2 {
    // Private Attributes
    private static Statement stmt;              // Database Connection
    private static final SimpleDateFormat sdf = // Standard date format tool
            new SimpleDateFormat("MM/dd/yyyy");
   
    /**
     * run() is a static method that takes text representing a location and
     *      a date, converts the date text to a date object, validates that the 
     *      date submitted is valid, uses the calculate() method to generate an 
     *      answer from a database, and returns a final answer as a formatted 
     *      String that describes the total amount of Covid related deaths for 
     *      a month.
     * 
     * @param stmt Statement object to communicate with a database.
     * @param location String representing the location being searched.
     * @param dateText String representing the date of a search.
     * @return String representing a detailed answer about total Covid related
     *      deaths for a location in Pennsylvania for a month.
     */
    public static String run(Statement stmt, String location, 
        String dateText){
        
        HistoricalDeathCalc2.stmt = stmt; // Sets private attribute
        Date searchDate;                  // The date of the search
             
        String output,          // The final detailed answer to be returned
               outputEnd = "",  // Possible notes to add to the output   
               month,           // Month of data
               year;            // Year of data
        
        // Tool to retrieve year from Date object
        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

        try{
            String firstText = "03/01/2020";    // First database entry
            Date first = sdf.parse(firstText);  // Converts to Date object
            Date today = new Date();            // Today's date
            
            // Reformat's today's date to be the first of the month
            String todayText = sdf.format(today);
            String todayDate = todayText.substring(0,3) + "01" 
                    + todayText.substring(5,10);
            today = sdf.parse(todayDate);
            
            // Creates Date to represent the last accurate month of data
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            c.add(Calendar.MONTH, -1);
            Date last = c.getTime();
            
            searchDate = sdf.parse(dateText); // Sets searchDate to dateText
 
            // Sets String variables for month and year
            Calendar cs = Calendar.getInstance();
            cs.setTime(searchDate);
            month = cs.getDisplayName(
                    Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            year = sdfYear.format(searchDate);
            
            // Validation
            // Checks if the search date comes before the first possible entry
            if (searchDate.before(first)){
                output = "Input Error: You have selected a date prior to when "
                        + "our records began (03/01/2020).";
            } 
            // Checks if the search date comes after the last possible entry
            else if (searchDate.after(last)) {
                output = "Error: You have selected a date beyond our records. "
                        + "Totals for the current month and beyond are not yet "
                        + "known.";
            } 
            // Else, a valid search date is being used 
            else {
                // Formats the beginning of the output
                output = "By " + month + " of " + year + ","; 

                // Calls calculate() to add detailed answer to output
                output += calculate(location, searchDate); 
            }     
        } catch (Exception e){
            System.out.println(e.toString());
            output = "Calculation Error"; // Sets output to an error message
        }

        return output + outputEnd; // Returns the detailed answer
    }
    
    
    /**
     * calculate() takes a location and two date objects to query a death
     *      database and return a formatted String that details the total 
     *      Covid related deaths for a location in Pennsylvania between 
     *      two months.
     * 
     * @param location String representing the location being searched.
     * @param searchDAte Date representing the month of a search.
     * @return String representing a detailed answer about total Covid related
     *      deaths for a location in Pennsylvania between two months.
     */
    private static String calculate(String location, Date searchDate){

        String query; // Query to gather data
        int total = 0; // Holds death values
        
        // Checks if the user is asking about all counties
        if (location.equals("State")){
            // Sets query to search entries of all counties on date
            query = "SELECT * FROM Historical_Death WHERE EntryDate = '" 
                    + sdf.format(searchDate) + "'";
        } else {
            // Sets query to search for entires for county on date
            query = "SELECT * FROM Historical_Death WHERE County = '" 
                    + location + "' AND EntryDate = '" 
                    + sdf.format(searchDate) + "'";
        }
        
        try {
            ResultSet rs = stmt.executeQuery(query); // Runs query
            
            // Loops through results and adds to total for the month
            while(rs.next()){
                total += rs.getInt("TotalDeaths");
            }    
        } catch (Exception e) {
            System.out.println(e.toString());
        }
 
        // Local variables to format data into String text
        String output, locationOutput;
        
        if (location.equals("State")){ // Checks if the location was all of PA
            locationOutput = "Pennsylvania"; // Formats text for all of PA
        } else {
            locationOutput = location + " County"; // Formats text for county
        }

        // Compiles local Sting variables to create a final answer
        output = " "+ total + " people had died due to Covid 19 throughout " 
                + locationOutput + ".";  
  
        return output; // Returns answer
    }
}
