package Model.HisDeath;

/**
 * IST 411 Final Project
 * File: DeathHistorical.java
 * 
 * Purpose: 
 * 
 * Last Edited On:4/20/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

public class DeathHistorical {
    // Private Attributes
    private String county;

    private String date;

    private String deaths_cume;
    
    public DeathHistorical(){
        
    }

    /**
     * @return the county
     */
    public String getCounty() {
        return county;
    }

    /**
     * @param county the county to set
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the deaths_cume
     */
    public String getDeaths_cume() {
        return deaths_cume;
    }

    /**
     * @param deaths_cume the deaths_cume to set
     */
    public void setDeaths_cume(String deaths_cume) {
        this.deaths_cume = deaths_cume;
    }
    
    
}
