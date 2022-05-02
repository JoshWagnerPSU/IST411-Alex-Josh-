package Model.CurDeath;

/**
 * IST 411 Final Project
 * File: DeathGenderCounty.java
 * 
 * Purpose: Represents a JSON object that describes the number of deaths in a 
 *          specific county for a specific gender group.
 * 
 * Last Edited On:4/20/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

public class DeathGenderCounty {
    // Private Attributes
    private String county,
                   sex,
                   count;

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
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the count
     */
    public String getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(String count) {
        this.count = count;
    }  
}