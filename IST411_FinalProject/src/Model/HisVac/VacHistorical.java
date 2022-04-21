package Model.HisVac;

/**
 * IST 411 Final Project
 * File: HistroicalVacCalc.java
 * 
 * Purpose: VacHistorical.java
 * 
 * Last Edited On:4/20/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

public class VacHistorical {
    // Private Attributes
    private String date,
                   county,
                   partially_covered,
                   fully_covered,
                   additional_dose1;
    
    /**
     * 
     */
    public VacHistorical(){
        
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
     * @return the partially_covered
     */
    public String getPartially_covered() {
        return partially_covered;
    }

    /**
     * @param partially_covered the partially_covered to set
     */
    public void setPartially_covered(String partially_covered) {
        this.partially_covered = partially_covered;
    }

    /**
     * @return the fully_covered
     */
    public String getFully_covered() {
        return fully_covered;
    }

    /**
     * @param fully_covered the fully_covered to set
     */
    public void setFully_covered(String fully_covered) {
        this.fully_covered = fully_covered;
    }

    /**
     * @return the additional_dose1
     */
    public String getAdditional_dose1() {
        return additional_dose1;
    }

    /**
     * @param additional_dose1 the additional_dose1 to set
     */
    public void setAdditional_dose1(String additional_dose1) {
        this.additional_dose1 = additional_dose1;
    }
    
    
}
