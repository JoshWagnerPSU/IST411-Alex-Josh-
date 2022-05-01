package Model.HisDeath;

/**
 * IST 411 Final Project
 * File: HistoricalDeathDataUtil.java
 * 
 * Purpose: This class acts to create a database table and populate it with
 *              data that describes Covid related Death Totals for each County
 *              in Pennsylvania, potentially up to the current day.
 * 
 * Last Edited On:4/20/2022
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

public class HistoricalDeathDataUtil {
    private static Statement stmt;
    
    
    /**
    * 
    * 
    * @param stmt 
    */
    public static void run(Statement stmt){
        HistoricalDeathDataUtil.stmt = stmt;
        //createTable();
        determineUpdateNeed();
    }
    
   
    
    /**
     * 
     */
    private static void createTable(){
       String create = "CREATE TABLE Historical_Death (" 
               + "EntryDate DATE NOT NULL, " 
               + "County VARCHAR(255) NOT NULL, " 
               + "TotalDeaths INT, " 
               + "PRIMARY KEY (EntryDate, County)" 
               + ")";
       
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
       
        String firstDataDate = "2020-02-01 00:00:00.000";
        String lastDateQuery = "SELECT MAX(EntryDate) AS LastDate FROM "
               + "Historical_Death";
       
        try{
            ResultSet rs = stmt.executeQuery(lastDateQuery);
            rs.next();
            
            if(rs.getDate("LastDate") == today){
                lastUpdate = today;
            } else if (rs.getDate("LastDate") == null){
                lastUpdate = sdf.parse(firstDataDate);
            } else {
                lastUpdate = rs.getDate("LastDate");
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
        long cycles = ChronoUnit.MONTHS.between(firstDate, secondDate);
        
        String url1 = "https://data.pa.gov/resource/w932-e7qx.json?date=";
        String url2;
        String url3 = "T00:00:00.000";
        String url;
        
        for (long i = 0; i < cycles; i++){
            c.add(Calendar.MONTH, 1);
            url2 = sdf.format(c.getTime());
            url = url1 + url2 + url3;
            updateTable(url);
        }
    }
    
    private static void updateTable(String url){
        
        DeathHistorical[] dh;
        try (InputStream inStream = new URL(url).openStream();
             Reader reader = new InputStreamReader(inStream, 
                                                   StandardCharsets.UTF_8)
            ) {        
            // Creates a Gson object to handle Json data
            Gson gson = new Gson();
           
            dh = gson.fromJson(reader, DeathHistorical[].class);

            for(int i =0; i < dh.length; i++){
                /*System.out.println(i);
                System.out.println(dh[i].getDate());
                System.out.println(dh[i].getCounty());
                System.out.println(dh[i].getDeaths_cume());
                System.out.print("\n\n"); */
                
                String date = dh[i].getDate().substring(0,10);
                String county = dh[i].getCounty();
                String total = dh[i].getDeaths_cume();
                
                
                String update = "INSERT INTO Historical_Death "
                    + "(EntryDate, County, TotalDeaths) "
                    + "VALUES ('"
                    + date + "', '"
                    + county + "', "
                    + total + ")";
                
               stmt.execute(update);
            }

        } catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}
