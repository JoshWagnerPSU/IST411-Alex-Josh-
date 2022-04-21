package Model.CurDeath;

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
        
    }
    
    
    /**
     * 
     */
    private void pullDGC(){
        
    }
    
    
    /**
     * 
     */
    private void pullDRC(){
        
    }
    
    
    /**
     * 
     * @param location
     * @param option
     * @param suboption
     * @return 
     */
    public String calcDAC(String location, String option, String suboption){
        String answer = "";
        
        return answer;
    }
    
    
    /**
     * 
     * @param location
     * @param option
     * @param suboption
     * @return 
     */
    public String calcDGC(String location, String option, String suboption){
        String answer = "";
        
        return answer;
    }
    
    
    /**
     * 
     * @param location
     * @param option
     * @param suboption
     * @return 
     */
    public String calcDRC(String location, String option, String suboption){
        String answer = "";
        
        return answer;
    }
}
