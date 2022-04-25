package Model.HisVac;

/**
 * IST 411 Final Project
 * File: HistoricalVacDataUtil.java
 * 
 * Purpose: 
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
    
    private static Statement stmt;
    
    public static void run(Statement stmt){
        HistoricalVacDataUtil.stmt = stmt;
        //createTable();
        determineUpdateNeed();
    }

    
    
    /**
     * 
     */
    private static void createTable(){
       String create = "CREATE TABLE HistoricalVac ( " 
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
    
    private static void determineUpdateNeed(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();  
        
        Date lastUpdate;
       
        String firstDataDate = "2020-12-31";
        String lastDateQuery = "SELECT MAX(EntryDate) AS LastDate FROM "
               + "HistoricalVac";
       
        try{
            ResultSet rs = stmt.executeQuery(lastDateQuery);

            if(rs.next()){
                if(rs.getDate("LastDate") != today){
                    lastUpdate = rs.getDate("LastDate");
                } else{
                    lastUpdate = today;
                }
            }
            else{
                lastUpdate = sdf.parse(firstDataDate);
            }

            if(lastUpdate != today){
                iterateUpdate(lastUpdate, today);
            }

        } catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    
    
    private static void iterateUpdate(Date lastUpdate, Date today){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Calendar c = Calendar.getInstance();
        c.setTime(lastUpdate);

        LocalDate firstDate = LocalDate.parse(sdf.format(lastUpdate));
        LocalDate secondDate = LocalDate.parse(sdf.format(today));
        long cycles = ChronoUnit.DAYS.between(firstDate, secondDate);
        
        String url1 = "https://data.pa.gov/resource/bicw-3gwi.json?date=";
        String url2;
        String url3 = "T00:00:00.000";
        String url;
        
        for (long i = 0; i < cycles; i++){
            c.add(Calendar.DATE, 1);
            url2 = sdf.format(c.getTime());
            url = url1 + url2 + url3;
            updateTable(url);
        }
    }
    
    private static void updateTable(String url){
        
        VacHistorical[] vh;
        try (InputStream inStream = new URL(url).openStream();
             Reader reader = new InputStreamReader(inStream, 
                                                   StandardCharsets.UTF_8)
            ) {        
            // Creates a Gson object to handle Json data
            Gson gson = new Gson();
           
            vh = gson.fromJson(reader, VacHistorical[].class);
            
            for(int i =0; i < vh.length; i++){
                /*System.out.println(i);
                System.out.println(vh[i].getDate());
                System.out.println(vh[i].getCounty());
                System.out.println(vh[i].getPartially_covered());
                System.out.println(vh[i].getFully_covered());
                System.out.println(vh[i].getAdditional_dose1());
                System.out.print("\n\n"); */
                
                String date = vh[i].getDate().substring(0,10);
                String county = vh[i].getCounty();
                String part,
                       full,
                       extra;
                
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
                
                String update = "INSERT INTO HistoricalVac "
                    + "(EntryDate, County, PartiallyCovered, FullyCovered, "
                    + "AdditionalDose) "
                    + "VALUES ('"
                    + date + "', '"
                    + county + "', "
                    + part + ", "
                    + full + ", "
                    + extra + ")";
                
               stmt.execute(update);
            }

        } catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    

}
