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
 *          between two months based on data in a database.
 *          
 *          This class acts as part of the Model in the MVC.
 * 
 * Last Edited On:4/30/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

public class HistoricalDeathCalc {
    // Private Attributes
    private static Statement stmt;              // Database Connection
    private static final SimpleDateFormat sdf = // Standard date format tool
            new SimpleDateFormat("MM/dd/yyyy");
   
    /**
     * run() is a static method that takes text representing a location, start
     *      date, and end date, converts the last two parameters to Date 
     *      objects, validates that the dates submitted are valid, uses the 
     *      calculate() method to generate an answer from a database, and 
     *      returns a final answer as a formatted String that describes the  
     *      total amount of Covid related deaths between two months.
     * 
     * @param stmt Statement object to communicate with a database.
     * @param location String representing the location being searched.
     * @param begDateText String representing the beginning date of a search.
     * @param endDateText String representing the ending date of a search.
     * @return String representing a detailed answer about total Covid related
     *      deaths for a location in Pennsylvania between two months.
     */
    public static String run(Statement stmt, String location, 
        String begDateText, String endDateText){
        
        HistoricalDeathCalc.stmt = stmt; // Sets private attribute
        Date start,             // The starting date of the search
             end;               // The ending date of the search
             
        String output,          // The final detailed answer to be returned
               outputEnd = "",  // Possible notes to add to the output   
               startMonth,      // Starting month of data
               endMonth,        // Ending month of data
               startYear,       // Starting year of data
               endYear;         // Ending year of data
        
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
            
            // Sets start date of search
            if (begDateText.isEmpty()){ // Checks if begDateText is empty
                start = first;          // Sets start to the first possible date
            } else {                    // Else, the begDateText is not empty
               start = sdf.parse(begDateText); // Sets start to begDateText
            }
            
            // Sets String variables for starting month and year
            Calendar cs = Calendar.getInstance();
            cs.setTime(start);
            startMonth = cs.getDisplayName(
                    Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            startYear = sdfYear.format(start);
            
            // Sets end date of search
            if (endDateText.isEmpty()){ // Checks if endDateText is empty
                end = last;             // Sets end to today's date
            } else {                    // Else, the endDateText is not empty
                end = sdf.parse(endDateText); // Sets end to endDate Text
            }
            
            // Sets String variables for ending month and year
            Calendar ce = Calendar.getInstance();
            ce.setTime(end);
            endMonth = ce.getDisplayName(
                    Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            endYear = sdfYear.format(end);

            // Validation
            // Checks if the ending date comes before the beginning date
            if (end.compareTo(start) == -1){
                output = "Input Error: Ending Date Cannot Come Before "
                        + "Beginning Date";
            } 
            // Checks if the beginning date is set in the future
            else if (start.compareTo(today) >= 0) {
                output = "Error: You have selected a Beginning Date beyond our "
                            + "records. Totals for the current month and beyond"
                            + " are not yet known.";
            } 
            // Checks if the ending date is before the first possible entry
            else if (end.compareTo(first) == -1) {
                output = "Error: You have selected an Ending Date prior to "
                        + "when our records began (03/01/2020).";
            } 
            // Else, valid search dates are being used 
            else {
                // Checks if the start and end date are the same
                if(start.compareTo(end) == 0){
                    // Formats beginning of output for single month
                    output = "In " + startMonth + " of " + startYear + ","; 
                } else {
                    // Formats beginning of output for a range of months
                    output = "Between " + startMonth + " of " + startYear 
                            + " and " + endMonth + " of " + endYear + ",";
                }
                
                // Checks if start was before first possible entry
                if(start.compareTo(first) == -1){
                    start = first; // Sets start search to first possible date
                    // Adds note to end of output
                    outputEnd += "\nNOTE: Your Beginning Date was prior to "
                            + "when our records began (03/01/2020).";
                }
                
                // Checks if end was after last possible entry
                if (end.compareTo(last) >= 0){
                    end = last; // Sets end to last possible date
                    // Adds note to end of output
                    outputEnd += "\nNote: Your Ending Date was beyond our "
                            + "records. Totals for the current month and beyond"
                            + " are not yet known.";
                }

                // Calls calculate() to add detailed answer to output
                output += calculate(location, start, end); 
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
     * @param start Date representing the beginning month of a search.
     * @param end Date representing the ending month of a search.
     * @return String representing a detailed answer about total Covid related
     *      deaths for a location in Pennsylvania between two months.
     */
    private static String calculate(String location, Date start, Date end){

        String query; // Query to gather data
        int totalStart = 0, totalEnd = 0, total=0; // Holds death values
        
        try {
 
            String firstText = "03/01/2020";    // First database entry
            Date first = sdf.parse(firstText); // Converts to Date object
             
            // Creates a date one month prior to the first date
            Calendar cmb = Calendar.getInstance();        
            cmb.setTime(start);
            cmb.add(Calendar.MONTH, -1);
            Date monthBefore = cmb.getTime(); 
            
            // Checks if the user is asking about all counties
            if (location.equals("State")){
                // Sets query to search entries of all counties between dates
                query = "SELECT * FROM Historical_Death WHERE EntryDate = '" 
                        + sdf.format(monthBefore) + "' OR EntryDate = '" 
                        + sdf.format(end) + "'";
            } else {
                // Sets query to search for entires for county between dates
                query = "SELECT * FROM Historical_Death WHERE County = '" 
                        + location + "' AND (EntryDate = '" 
                        + sdf.format(monthBefore) + "' OR EntryDate = '" 
                        + sdf.format(end) + "')";
            }
            
            ResultSet rs = stmt.executeQuery(query); // Runs query
            
            /* 
             * Checks if the start and end dates are the same month and if 
             *      that month is the first month
             */
            if (start.equals(end) && end.equals(first)){
                    // Loops through and calculates total deaths for the month
                    while (rs.next()){
                        if (rs.getDate("EntryDate").compareTo(end) == 0){
                            totalEnd += rs.getInt("TotalDeaths");
                        }
                    }
            } else {
                while (rs.next()) {
                   // Checks if current Date is the starting value and sets it
                   if(rs.getDate("EntryDate").equals(monthBefore)){
                        totalStart += rs.getInt("TotalDeaths");
                    }
                   
                   // Checks if the current Date is the ending value and sets it
                   if (rs.getDate("EntryDate").equals(end)){
                       totalEnd += rs.getInt("TotalDeaths");
                   }
                }
            }

            total = totalEnd - totalStart; // Calculates total deaths
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
        output = " "+ total + " people died due to Covid 19 throughout " 
                + locationOutput + ".";  
  
        return output; // Returns answer
    }
}
