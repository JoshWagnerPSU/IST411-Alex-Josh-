package Model.HisVac;

/**
 * IST 411 Final Project
 * File: HistoricalVacDataUtil.java
 * 
 * Purpose:This class acts to create a database table and populate it with
 *              data that describes Covid related Vaccination Totals for each
 *              County in Pennsylvania, potentially up to the current day.
 * 
 * Last Edited On:4/24/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class HistoricalVacDataUtil {
    // Private Attribute
    private static Statement stmt;
    
    
    /**
     * run() sets the private attribute, creates the database table, and 
     *      determines if new data needs to be added.
     * 
     * @param stmt Database Statement object. 
     */
    public static void run(Statement stmt){
        HistoricalVacDataUtil.stmt = stmt;
        createTable();
        determineUpdateNeed();
    }

    
    /**
     * createTable() creates the database table.
     */
    private static void createTable(){
       String create = "CREATE TABLE Historical_Vac ( " 
               + "EntryDate DATE NOT NULL, " 
               + "County VARCHAR(255) NOT NULL, " 
               + "PartiallyCovered INT, " 
               + "FullyCovered INT, " 
               + "AdditionalDose INT, " 
               + "PRIMARY KEY (EntryDate, County) " 
               + " )";
       try{
           stmt.execute(create);
       } catch (Exception e){
           System.out.println(e.toString());
       }
    }
    
    /**
     * determineUpdateNeed() determines if new data needs to be added to the 
     *      database table.
     */
    private static void determineUpdateNeed(){
        // Local variables
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();  
        Date lastUpdate;
        String firstDataDate = "2020-12-31";
        String lastDateQuery = "SELECT MAX(EntryDate) AS LastDate FROM "
               + "Historical_Vac";    
        try{
            // Determiens last update
            ResultSet rs = stmt.executeQuery(lastDateQuery);
            rs.next();

            /*
             * Checks when the date of the last update was, if its today the 
             *      the lastUpdate variable is set to today, if there is no
             *      last date the lastUpdate is set to the local variable,
             *      otherwise the last date is set to the returned value
             */
            if(rs.getDate("LastDate") == today){
                lastUpdate = today;
            } else if (rs.getDate("LastDate") == null){
                lastUpdate = sdf.parse(firstDataDate);
            } else {
                lastUpdate = rs.getDate("LastDate");
            }

            // Checks if the last update wasn't today
            if(lastUpdate != today){
                // Calls method to add new data to tables
                iterateUpdate(lastUpdate, today);
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
    
    
    /**
     * iterateUpdate() calls method to update table with custom urls a number 
     *      of times equal to the number of days between the last update and
     *      today.
     * 
     * @param lastUpdate The last date of a table entry.
     * @param today Today's date.
     */
    private static void iterateUpdate(Date lastUpdate, Date today){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(lastUpdate);
        
        // Determiens the number of days between entries (i.e. iterations)
        LocalDate firstDate = LocalDate.parse(sdf.format(lastUpdate));
        LocalDate secondDate = LocalDate.parse(sdf.format(today));
        long cycles = ChronoUnit.DAYS.between(firstDate, secondDate);
        
        // Local varialbes to generate custom url
        String url1 = "https://data.pa.gov/resource/bicw-3gwi.json?date=";
        String url2;
        String url3 = "T00:00:00.000";
        String url;
        
        // Loops through each iteration and adds a new entry using a custom url
        for (long i = 0; i < cycles; i++){
            c.add(Calendar.DATE, 1);
            url2 = sdf.format(c.getTime());
            url = url1 + url2 + url3;
            updateTable(url);
        }
    }
    
    
    /**
     * updateTable() adds a new vaccination entry to the database table.
     * 
     * @param url The web address of the api.
     */
    private static void updateTable(String url){
        
        VacHistorical[] vh; // Array of custom vaccination JSON objects
        try (InputStream inStream = new URL(url).openStream();
             Reader reader = new InputStreamReader(inStream, 
                                                   StandardCharsets.UTF_8)
            ) {        
            // Creates a Gson object to handle JSON data
            Gson gson = new Gson();   
            vh = gson.fromJson(reader, VacHistorical[].class);
              
            for(int i =0; i < vh.length; i++){ // Loops through array
                // Local variables to hold data values
                String date = vh[i].getDate().substring(0,10);
                String county = vh[i].getCounty();
                String part,
                       full,
                       extra;
                
                // Sets local variables and ensures no value is null
                if(vh[i].getPartially_covered() != null){
                    part = vh[i].getPartially_covered();
                } else {
                    part = "0";
                }
                
                if(vh[i].getFully_covered() != null){
                    full = vh[i].getFully_covered();
                } else {
                    full = "0";
                }
                
                if(vh[i].getAdditional_dose1() != null){
                    extra = vh[i].getAdditional_dose1();
                } else {
                    extra = "0";
                }
                
                // String to add new entry
                String update = "INSERT INTO Historical_Vac "
                    + "(EntryDate, County, PartiallyCovered, FullyCovered, "
                    + "AdditionalDose) "
                    + "VALUES ('"
                    + date + "', '"
                    + county + "', "
                    + part + ", "
                    + full + ", "
                    + extra + ")";
                
               stmt.execute(update); // Adds new entry to table
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
