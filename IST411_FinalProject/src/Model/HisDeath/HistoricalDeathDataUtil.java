package Model.HisDeath;

/**
 * IST 411 Final Project
 * File: HistoricalDeathDataUtil.java
 * 
 * Purpose: 
 * 
 * Last Edited On:4/20/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

import java.sql.*;

public class HistoricalDeathDataUtil {
    
    /**
    * 
    * 
    * @param stmt 
    */
    public static void run(Statement stmt){
       
        
       // Logic to check if table exists, if it doesnt...
       createTable();
       
       // Logic to check if the last date in the table is today's date, if not....
       updateTable();

    }
    
    
    /**
     * 
     */
    private static void createTable(){
       
    }
    
    private static void updateTable(){
        // Logic to determine the last date in the table and set it as...
       String lastEntry = "";
       
       // Logic to determine today's date and set it as....
       String currentDate = "";
       
       // Logic using for loop to call pullData to insert records to table...
       String insertQuery = pullData(lastEntry, currentDate);
    }
    
    
    /**
     * 
     * @param lastEntry
     * @param currentDate
     * @return 
     */
    private static String pullData(String lastEntry, String currentDate){
        String insertQuery = "";
        
        return insertQuery;
    }
}
