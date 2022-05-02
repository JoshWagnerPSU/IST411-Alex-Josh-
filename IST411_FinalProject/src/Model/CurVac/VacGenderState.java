package Model.CurVac;

/**
 * IST 411 Final Project
 * File: VacGenderState.java
 * 
 * Purpose: Represents a JSON object that describes vaccination totals 
 *          throughout Pennsylvania for a specific gender group.
 * 
 * Last Edited On:4/20/2022
 * Last Edited By: Alex Koontz
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

public class VacGenderState {

    private String gender,
            partially_covered,
            fully_covered,
            additional_dose;

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
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
     * @return the additional_dose
     */
    public String getAdditional_dose() {
        return additional_dose;
    }

    /**
     * @param additional_dose the additional_dose to set
     */
    public void setAdditional_dose(String additional_dose) {
        this.additional_dose = additional_dose;
    }
}
