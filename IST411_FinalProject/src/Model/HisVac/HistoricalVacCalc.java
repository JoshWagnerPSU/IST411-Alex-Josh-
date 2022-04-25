package Model.HisVac;

/**
 * IST 411 Final Project
 * File: HistroicalVacCalc.java
 * 
 * Purpose: HistoricalVacCalc.java
 * 
 * Last Edited On:4/20/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

import java.sql.*;

public class HistoricalVacCalc {
    
    private static Statement stmt;
    
    /**
     * 
     * 
     * @param stmt
     * @param location
     * @param option
     * @param suboption
     * @return 
     */
    public static String run(Statement stmt, String location, 
        String begDateText, String endDateText){
        
        HistoricalVacCalc.stmt = stmt;
        
        
        String answer = "";
        
        return answer;
    }
}
