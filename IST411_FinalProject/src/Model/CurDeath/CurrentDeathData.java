package Model.CurDeath;

/**
 * IST 411 Final Project
 * File: CurrentDeathData.java
 * 
 * Purpose: Represents the current death data in Pennsylvania, made up of
 *          custom object arrays, and handles search calculations.
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

public class CurrentDeathData {
    // Private Attributes
    private DeathAgeCounty[] dac;
    private DeathGenderCounty[] dgc;
    private DeathRaceCounty[] drc;
    
    
    /**
     * Constructor method that pulls all current death data.
     */
    public CurrentDeathData(){
       pullData();
    }
    
    
    /**
     * pullData() calls related methods to pull specific types of death data.
     */
    private void pullData(){
        pullDAC();
        pullDGC();
        pullDRC();
        
    }
    
    
    /**
     * pullDAC() pulls death data related to age groups based on county.
     */
    private void pullDAC(){
        //url string for api
        String dacURL = "https://data.pa.gov/resource/jtyj-ug4y.json";
        //try to connect to api
        try (InputStream is = new URL(dacURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            dac = gson.fromJson(reader, DeathAgeCounty[].class);
        } catch(Exception e){System.out.println(e);}
    }
    
    
    /**
     * pullDGC() pulls death data related to gender groups based on county.
     */
    private void pullDGC(){
        //url string for api
        String dgcURL = "https://data.pa.gov/resource/het5-dwm7.json";
        //try to connect to api
        try (InputStream is = new URL(dgcURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            dgc = gson.fromJson(reader, DeathGenderCounty[].class);
        } catch(Exception e){System.out.println(e);}
    }
    
    
    /**
     * pullDRC() pulls death data related to race groups based on county.
     */
    private void pullDRC(){
        //url string for api
        String drcURL = "https://data.pa.gov/resource/7ix4-a8g6.json";
        //try to connect to api
        try (InputStream is = new URL(drcURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            drc = gson.fromJson(reader, DeathRaceCounty[].class);
        } catch(Exception e){System.out.println(e);}
    }
    
    
    /**
     * calcDAC() determines a death total for a specific location and age group.
     * 
     * @param location The county being searched.
     * @param suboption The age group be searched.
     * @return A total of deaths based on search parameters.
     */
    public String calcDAC(String location, String suboption){
        String answer = "";
        
        // Determines age group
        if(suboption.equals("0 to 9")){
            suboption = "0-9";
        } else if(suboption.equals("10 to 19")){
            suboption = "10-19";
        } else if(suboption.equals("20 to 29")){
            suboption = "20-29";
        } else if(suboption.equals("30 to 39")){
            suboption = "30-39";
        } else if(suboption.equals("40 to 49")){
            suboption = "40-49";
        } else if(suboption.equals("50 to 59")){
            suboption = "50-59";
        } else if(suboption.equals("60 to 69")){
            suboption = "60-69";
        } else if(suboption.equals("70 to 79")){
            suboption = "70-79";
        } else if(suboption.equals("80 to 89")){
            suboption = "80-89";
        } else if(suboption.equals("90 to 99")){
            suboption = "90-99";
        } else if(suboption.equals("100+")){
            suboption = "100+";
        }
        
        for(int i = 0; i < dac.length; i++){ // Loops through dac data
            String currentCounty = dac[i].getCounty(); // Reference variable
            // Checks if the county matches the search
            if(currentCounty.equals(location)){
                // Checks if the age group matches the search
                if(dac[i].getAge().equals(suboption)){
                    //Set deaths to the JSON data of specified county and age
                    String deaths = dac[i].getCount();
                    //Null values exist in the dataset. Prevents output of null
                    if(deaths == null){
                        deaths = "Data not available with current dataset.";
                    }
                    answer = "Deaths: " + deaths; // Formats answer
                }       
            }
        }
        return answer;
    }
    
    
    /**
     * calcDGC() determines a death total for a specific location and gender 
     *      group.
     * 
     * @param location The county being searched.
     * @param suboption The age group be searched.
     * @return A total of deaths based on search parameters.
     */
    public String calcDGC(String location, String suboption){
        String answer = "";
        
        for(int i = 0; i < dgc.length; i++){ // Loops through dgc data
            String currentCounty = dgc[i].getCounty(); // Reference variable
            // Checks if the county matches the search
            if(currentCounty.equals(location)){
                // Checks if the gender group matches the search
                if(dgc[i].getSex().equals(suboption)){
                    //Set deaths to the JSON data of specified county and gender
                    String deaths = dgc[i].getCount();
                    //Null values exist in the dataset. Prevents output of null
                    if(deaths == null){
                        deaths = "Data not available with current dataset.";
                    }
                    answer = "Deaths: " + deaths; // Formats answer
                }
            }
        }
        return answer;
    }
    
    
    /**
     * calcDRC() determines a death total for a specific location and race 
     *      group.
     * 
     * @param location The county being searched.
     * @param suboption The age group be searched.
     * @return A total of deaths based on search parameters.
     */
    public String calcDRC(String location, String suboption){
        String answer = "";
        
        for(int i = 0; i < drc.length; i++){ // Loops through dgc data
            String currentCounty = drc[i].getCounty();
            // Checks if the county matches the search
            if(currentCounty.equals(location)){
                // Checks if the race group matches the search
                if(drc[i].getRace().equals(suboption)){
                    //Set deaths to the JSON data of specified county and race
                    String deaths = drc[i].getCount();
                    //Null values exist in the dataset. Prevents output of null
                    if(deaths == null){
                        deaths = "Data not available with current dataset.";
                    }
                    answer = "Deaths: " + deaths; // Formats answer
                }
            }
        }
        return answer;
    }
    
    
    /**
     * calcDAS() determines a death total for the entire state based on a 
     *      age group.
     * 
     * @param suboption The age group being searched.
     * @return A total of deaths in the state based on age group searched.
     */
    public String calcDAS(String suboption){
        String answer = "";
        int totalCount = 0;
        
        // Determines age group
        if(suboption.equals("0 to 9")){
            suboption = "0-9";
        } else if(suboption.equals("10 to 19")){
            suboption = "10-19";
        } else if(suboption.equals("20 to 29")){
            suboption = "20-29";
        } else if(suboption.equals("30 to 39")){
            suboption = "30-39";
        } else if(suboption.equals("40 to 49")){
            suboption = "40-49";
        } else if(suboption.equals("50 to 59")){
            suboption = "50-59";
        } else if(suboption.equals("60 to 69")){
            suboption = "60-69";
        } else if(suboption.equals("70 to 79")){
            suboption = "70-79";
        } else if(suboption.equals("80 to 89")){
            suboption = "80-89";
        } else if(suboption.equals("90 to 99")){
            suboption = "90-99";
        } else if(suboption.equals("100+")){
            suboption = "100+";
        }
        
        for(int i = 0; i < dac.length; i++){ // Loops through dac data
            if(dac[i].getAge().equals(suboption)){ // If age group matches
                try{ // Adds deaths to totalCount
                    totalCount += Integer.valueOf(dac[i].getCount());
                } catch(Exception e){}
            }
        }
        answer = "Deaths: " + String.valueOf(totalCount); // Formats answer
        
        return answer;
    }
    
    
    /**
     * calcDGS() determines a death total for the entire state based on a 
     *      gender group.
     * 
     * @param suboption The gender group being searched.
     * @return A total of deaths in the state based on gender group searched.
     */
    public String calcDGS(String suboption){
        String answer = "";
        int totalCount = 0;
        
        for(int i = 0; i < dgc.length; i++){ // Loops through dgc data
            if(dgc[i].getSex().equals(suboption)){ // If gender group matches
                try{ // Adds deaths to totalCount
                    totalCount += Integer.valueOf(dgc[i].getCount());
                } catch(Exception e){}
            }
        }
        answer = "Deaths: " + String.valueOf(totalCount); // Formats answer
        
        return answer;
    }
    
    
    /**
     * calcDRS() determines a death total for the entire state based on a 
     *      race group.
     * 
     * @param suboption The race group being searched.
     * @return A total of deaths in the state based on race group searched.
     */
    public String calcDRS(String suboption){
        String answer = "";
        int totalCount = 0;
        
        for(int i = 0; i < drc.length; i++){ // Loops through drc data
            if(drc[i].getRace().equals(suboption)){ // If race group matches
                try{ // Adds deaths to totalCount
                    totalCount += Integer.valueOf(drc[i].getCount());
                } catch(Exception e){}
            }
        }
        answer = "Deaths: " + String.valueOf(totalCount); // Formats answer
        
        return answer;
    }
}