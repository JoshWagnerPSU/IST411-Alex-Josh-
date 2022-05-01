package Model.CurDeath;

import Model.CurVac.VacAgeCounty;
import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * IST 411 Final Project
 * File: CurrentDeathData.java
 * 
 * Purpose: 
 * 
 * Last Edited On:4/20/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

public class CurrentDeathData {
    // Private Attributes
    private DeathAgeCounty[] dac;
    private DeathGenderCounty[] dgc;
    private DeathRaceCounty[] drc;
    
    /**
     * 
     */
    public CurrentDeathData(){
       pullData();
       System.out.println("Death Age County Test:");
       System.out.println(calcDAC("Delaware", "40 to 49"));
       System.out.println("Death Gender County Test:");
       System.out.println(calcDGC("York", "Male"));
       System.out.println("Death Race County Test:");
       System.out.println(calcDRC("York", "White"));
       
       System.out.println("\nDeath Age State Test:");
       System.out.println(calcDAS("60 to 69"));
       System.out.println("Death Gender State Test:");
       System.out.println(calcDGS("Female"));
       System.out.println("Death Race State Test:");
       System.out.println(calcDRS("African American"));
    }
    
    
    /**
     * 
     */
    private void pullData(){
        pullDAC();
        pullDGC();
        pullDRC();
        
    }
    
    
    /**
     * 
     */
    private void pullDAC(){
        //url string for api
        String dacURL = "https://data.pa.gov/resource/jtyj-ug4y.json";
        //try to connect to api
        try (InputStream is = new URL(dacURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            dac = gson.fromJson(reader, DeathAgeCounty[].class);
            
            //for(int i = 0;i < dac.length; i++){
                System.out.println(dac.length);
            //}
        } catch(Exception e){System.out.println(e);}
    }
    
    
    /**
     * 
     */
    private void pullDGC(){
        //url string for api
        String dgcURL = "https://data.pa.gov/resource/het5-dwm7.json";
        //try to connect to api
        try (InputStream is = new URL(dgcURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            dgc = gson.fromJson(reader, DeathGenderCounty[].class);
            
            //for(int i = 0;i < dac.length; i++){
                System.out.println(dgc.length);
            //}
        } catch(Exception e){System.out.println(e);}
    }
    
    
    /**
     * 
     */
    private void pullDRC(){
        //url string for api
        String drcURL = "https://data.pa.gov/resource/7ix4-a8g6.json";
        //try to connect to api
        try (InputStream is = new URL(drcURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            drc = gson.fromJson(reader, DeathRaceCounty[].class);
            
            //for(int i = 0;i < dac.length; i++){
                System.out.println(drc.length);
            //}
        } catch(Exception e){System.out.println(e);}
    }
    
    
    /**
     * 
     * @param location
     * @param suboption
     * @return 
     */
    public String calcDAC(String location, String suboption){
        String answer = "";
        
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
        
        for(int i = 0; i < dac.length; i++){
            String currentCounty = dac[i].getCounty();

            if(currentCounty.equals(location)){
                
                if(dac[i].getAge().equals(suboption)){
                    //Set deaths equal to the JSON data of specified county and age range
                    String deaths = dac[i].getCount();
                    
                    //Null values exist in the dataset. This prevents output of a null.
                    if(deaths == null){
                        deaths = "Data not available with current dataset.";
                    }
                    
                    answer = "Deaths: " + deaths;
                }
                
            }
        }
        
        
        return answer;
    }
    
    
    /**
     * 
     * @param location
     * @param suboption
     * @return 
     */
    public String calcDGC(String location, String suboption){
        String answer = "";
        
        for(int i = 0; i < dgc.length; i++){
            String currentCounty = dgc[i].getCounty();

            if(currentCounty.equals(location)){
                if(dgc[i].getSex().equals(suboption)){
                    String deaths = dgc[i].getCount();
                    
                    if(deaths == null){
                        deaths = "Data not available with current dataset.";
                    }
                    
                    answer = "Deaths: " + deaths;
                }
                
            }
        }
        
        return answer;
    }
    
    
    /**
     * 
     * @param location
     * @param suboption
     * @return 
     */
    public String calcDRC(String location, String suboption){
        String answer = "";
        
        for(int i = 0; i < drc.length; i++){
            String currentCounty = drc[i].getCounty();

            if(currentCounty.equals(location)){
                if(drc[i].getRace().equals(suboption)){
                    String deaths = drc[i].getCount();
                    
                    if(deaths == null){
                        deaths = "Data not available with current dataset.";
                    }
                    
                    answer = "Deaths: " + deaths;
                }
                
            }
        }
        
        return answer;
    }
    
    public String calcDAS(String suboption){
        String answer = "";
        int totalCount = 0;
        
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
        
        for(int i = 0; i < dac.length; i++){
            if(dac[i].getAge().equals(suboption)){
                try{
                    totalCount += Integer.valueOf(dac[i].getCount());
                } catch(Exception e){}
            }
        }
        
        answer = String.valueOf(totalCount);
        
        return answer;
    }
    
    public String calcDGS(String suboption){
        String answer = "";
        int totalCount = 0;
        
        for(int i = 0; i < dgc.length; i++){
            if(dgc[i].getSex().equals(suboption)){
                try{
                    totalCount += Integer.valueOf(dgc[i].getCount());
                } catch(Exception e){}
            }
        }
        
        answer = String.valueOf(totalCount);
        
        return answer;
    }
    
    public String calcDRS(String suboption){
        String answer = "";
        int totalCount = 0;
        
        for(int i = 0; i < drc.length; i++){
            if(drc[i].getRace().equals(suboption)){
                try{
                    totalCount += Integer.valueOf(drc[i].getCount());
                } catch(Exception e){}
            }
        }
        
        answer = String.valueOf(totalCount);
        
        return answer;
    }
}
