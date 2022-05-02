package Model.CurVac;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;


/**
 * IST 411 Final Project
 * File: CurrentVacData.java
 * 
 * Purpose: Represents the current vaccination data in Pennsylvania, made up of
 *          custom object arrays, and handles search calculations.
 * 
 * Last Edited On:4/20/2022
 * Last Edited By: Alex Koontz
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

public class CurrentVacData {
    // Private Attributes
    private VacAgeCounty[] vac;
    private VacGenderCounty[] vgc;
    private VacRaceCounty[] vrc;
    private VacAgeState[] vas;
    private VacGenderState[] vgs;
    private VacRaceState[] vrs;
    
    
    /**
     * Constructor method that pulls all current vaccination data.
     */
    public CurrentVacData(){
        pullData();
    }

    
    /**
     * pullData() calls related methods to pull specific types of 
     *      vaccination data.
     */
    private void pullData() {
        pullVAC();
        pullVGC();
        pullVRC();
        pullVAS();
        pullVGS();
        pullVRS();
    }

    
    /**
     * pullVAC() pulls vaccination data related to age groups based on county.
     */
    public void pullVAC() {
        //url string for api
        String vacURL = "https://data.pa.gov/resource/niuh-2xe3.json";
        //try to connect to api
        try (InputStream is = new URL(vacURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            vac = gson.fromJson(reader, VacAgeCounty[].class);
        } catch(Exception e){System.out.println(e);}
    }

    
    /**
     * pullVGC() pulls vaccination data related to gender groups based 
     *      on county.
     */
    public void pullVGC() {
        //url string for api
        String vgcURL = "https://data.pa.gov/resource/jweg-3ezy.json";
        //try to connect to api
        try (InputStream is = new URL(vgcURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            vgc = gson.fromJson(reader, VacGenderCounty[].class);
        } catch(Exception e){System.out.println(e);}
    }

    
    /**
     * pullVRC() pulls vaccination data related to race groups based on county.
     */
    public void pullVRC() {
        //url string for api
        String vrcURL = "https://data.pa.gov/resource/x5z9-57ub.json";
        //try to connect to api
        try (InputStream is = new URL(vrcURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            vrc = gson.fromJson(reader, VacRaceCounty[].class);
        } catch(Exception e){System.out.println(e);}
    }

    
    /**
     * pullVAS() pulls vaccination data related to age groups based on 
     *      the state.
     */
    public void pullVAS() {
        //url string for api
        String vasURL = "https://data.pa.gov/resource/xy2e-dqvt.json";
        //try to connect to api
        try (InputStream is = new URL(vasURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            vas = gson.fromJson(reader, VacAgeState[].class);
        } catch(Exception e){System.out.println(e);}
    }

    
    /**
     * pullVGS() pulls vaccination data related to gender groups based on 
     *      the state.
     */
    public void pullVGS() {
        //url string for api
        String vgsURL = "https://data.pa.gov/resource/id8t-dnk6.json";
        //try to connect to api
        try (InputStream is = new URL(vgsURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            vgs = gson.fromJson(reader, VacGenderState[].class);
        } catch(Exception e){System.out.println(e);}
    }

    
    /**
     * pullVRS() pulls vaccination data related to race groups based on 
     *      the state.
     */
    public void pullVRS() {
        //url string for api
        String vrsURL = "https://data.pa.gov/resource/e384-bs7r.json";
        //try to connect to api
        try (InputStream is = new URL(vrsURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            vrs = gson.fromJson(reader, VacRaceState[].class);
        } catch(Exception e){System.out.println(e);}
    }

    /**
     * calcVAC() determines vaccination totals for a specific county and 
     *      age group.
     * 
     * @param location The county being searched.
     * @param suboption The age group be searched.
     * @return Vaccination totals based on search parameters.
     */
    public String calcVAC(String location, String suboption) {
        String answer = "";
        
        //Uppercase conversion to match JSON data
        location = location.toUpperCase();
        
        //Total current vaccinations for a certain county by age
        for(int i = 0; i < vac.length; i++){
            String currentCounty = vac[i].getCounty();
            
            if(currentCounty.equals(location)){
                if(suboption.equals("5 to 9")){
                    String partially = vac[i].
                            getPartially_covered_age_group_5_9();
                    String fully = vac[i].getFully_covered_age_group_5_9();
                    String additional = "Data not available with current "
                            + "dataset";
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                } else if(suboption.equals("10 to 14")){
                    String partially = vac[i].
                            getPartially_covered_age_group_10_14();
                    String fully = vac[i].getFully_covered_age_group_10_14();
                    String additional = vac[i].
                            getAdditional_dose_age_group_10_14();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("15 to 19")){
                    String partially = vac[i].getPartiallycovered_15_19();
                    String fully = vac[i].getFullycovered_15_19();
                    String additional = vac[i].
                            getAdditional_dose_age_group_15_19();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("20 to 24")){
                    String partially = vac[i].getPartiallycovered_20_24();
                    String fully = vac[i].getFullycovered_20_24();
                    String additional = vac[i].
                            getAdditional_dose_age_group_20_24();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("25 to 29")){
                    String partially = vac[i].getPartiallycovered_25_29();
                    String fully = vac[i].getFullycovered_25_29();
                    String additional = vac[i].
                            getAdditional_dose_age_group_25_29();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("30 to 34")){
                    String partially = vac[i].getPartiallycovered_30_34();
                    String fully = vac[i].getFullycovered_30_34();
                    String additional = vac[i].
                            getAdditional_dose_age_group_30_34();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("35 to 39")){
                    String partially = vac[i].getPartiallycovered_25_39();
                    String fully = vac[i].getFullycovered_35_39();
                    String additional = vac[i].
                            getAdditional_dose_age_group_35_39();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("40 to 44")){
                    String partially = vac[i].getPartiallycovered_40_44();
                    String fully = vac[i].getFullycovered_40_44();
                    String additional = vac[i].
                            getAdditional_dose_age_group_40_44();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("45 to 49")){
                    String partially = vac[i].getPartiallycovered_45_49();
                    String fully = vac[i].getFullycovered_45_49();
                    String additional = vac[i].
                            getAdditional_dose_age_group_45_49();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("50 to 54")){
                    String partially = vac[i].getPartiallycovered_50_54();
                    String fully = vac[i].getFullycovered_50_54();
                    String additional = vac[i].
                            getAdditional_dose_age_group_50_54();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("55 to 59")){
                    String partially = vac[i].getPartiallycovered_55_59();
                    String fully = vac[i].getFullycovered_55_59();
                    String additional = vac[i].
                            getAdditional_dose_age_group_55_59();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("60 to 64")){
                    String partially = vac[i].getPartiallycovered_60_64();
                    String fully = vac[i].getFullycovered_60_64();
                    String additional = vac[i].
                            getAdditional_dose_age_group_60_64();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("65 to 69")){
                    String partially = vac[i].getPartiallycovered_65_69();
                    String fully = vac[i].getFullycovered_65_69();
                    String additional = vac[i].
                            getAdditional_dose_age_group_65_69();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("70 to 74")){
                    String partially = vac[i].getPartiallycovered_70_74();
                    String fully = vac[i].getFullycovered_70_74();
                    String additional = vac[i].
                            getAdditional_dose_age_group_70_74();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("75 to 79")){
                    String partially = vac[i].getPartiallycovered_75_79();
                    String fully = vac[i].getFullycovered_75_79();
                    String additional = vac[i].
                            getAdditional_dose_age_group_75_79();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("80 to 84")){
                    String partially = vac[i].getPartiallycovered_80_84();
                    String fully = vac[i].getFullycovered_80_84();
                    String additional = vac[i].
                            getAdditional_dose_age_group_80_84();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("85 to 89")){
                    String partially = vac[i].getPartiallycovered_85_89();
                    String fully = vac[i].getFullycovered_85_89();
                    String additional = vac[i].
                            getAdditional_dose_age_group_85_89();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("90 to 94")){
                    String partially = vac[i].getPartiallycovered_90_94();
                    String fully = vac[i].getFullycovered_90_94();
                    String additional = vac[i].
                            getAdditional_dose_age_group_90_94();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("95 to 99")){
                    String partially = vac[i].getPartiallycovered_95_99();
                    String fully = vac[i].getFullycovered_95_99();
                    String additional = vac[i].
                            getAdditional_dose_age_group_95_99();
                    
                   answer = "Partially Vaccinated: " + partially + "\nFully "
                           + "Vaccinated: " + fully + "\nAdditional Dose: " 
                           + additional;
                
                } else if(suboption.equals("100 to 104")){
                    String partially = "Data not available with current "
                            + "dataset";
                    String fully = vac[i].getFullycovered_100_104();
                    String additional = vac[i].
                            getAdditional_dose_age_group_100_104();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                }
            }
        }
        return answer;
    }

    
    /**
     * calcVGC() determines vaccination totals for a specific county and 
     *      gender group.
     * 
     * @param location The county being searched.
     * @param suboption The gender group be searched.
     * @return Vaccination totals based on search parameters.
     */
    public String calcVGC(String location, String suboption) {
        String answer = "";
        
        for(int i = 0; i < vgc.length; i++){// Loops through all vgc data
            String currentCounty = vgc[i].getZip_county_desc();
            //If location and group matches, adds answer
            if(currentCounty.equals(location)){
                if(suboption.equals("Male")){
                    String partially = vgc[i].getPartially_covered_male();
                    String fully = vgc[i].getFully_covered_male();
                    String additional = vgc[i].getAdditional_dose_male();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                } else if (suboption.equals("Female")){
                    String partially = vgc[i].getPartially_covered_female();
                    String fully = vgc[i].getFully_covered_female();
                    String additional = vgc[i].getAdditional_dose_female();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                }
            }
        }
        return answer;
    }

    
    /**
     * calcVGC() determines vaccination totals for a specific county and 
     *      race group.
     * 
     * @param location The county being searched.
     * @param suboption The race group be searched.
     * @return Vaccination totals based on search parameters.
     */
    public String calcVRC(String location, String suboption) {
        String answer = "";
        
        for(int i = 0; i < vrc.length; i++){ // Loops through all vrc data
        String currentCounty = vrc[i].getCounty();
            
            //If location and group matches, adds answer
            if(currentCounty.equals(location)){
                if(suboption.equals("African-American")){
                    String partially = vrc[i].
                            getPartially_covered_african_american();
                    String fully = vrc[i].getFully_covered_african_american();
                    String additional = vrc[i].
                            getAdditional_dose_african_american();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                } else if(suboption.equals("Asian")){
                    String partially = vrc[i].getPartially_covered_asian();
                    String fully = vrc[i].getFully_covered_asian();
                    String additional = vrc[i].getAdditional_dose_asian();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                } else if(suboption.equals("Native American")){
                    String partially = vrc[i].
                            getPartially_covered_native_american();
                    String fully = vrc[i].getFully_covered_native_american();
                    String additional = vrc[i].
                            getAdditional_dose_native_american();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("Pacific Islander")){
                    String partially = vrc[i].
                            getPartially_covered_pacific_islander();
                    String fully = vrc[i].getFully_covered_pacific_islander();
                    String additional = vrc[i].
                            getAdditional_dose_pacifica_islander();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                
                } else if(suboption.equals("White")){
                    String partially = vrc[i].getPartially_covered_white();
                    String fully = vrc[i].getFully_covered_white();
                    String additional = vrc[i].getAdditional_dose_white();
                    
                    answer = "Partially Vaccinated: " + partially + "\nFully "
                            + "Vaccinated: " + fully + "\nAdditional Dose: " 
                            + additional;
                }
            }
        }
        return answer;
    }

    
    /**
     * calcVAS() determines vaccination totals for the entire state based on
     *      age group.
     * 
     * @param suboption
     * @return Vaccination totals based on search parameters. 
     */
    public String calcVAS(String suboption) {
        String answer = "";
        
        //Format conversion for age group input string
        if(suboption.equals("5 to 9")){
            suboption = "5-9";
        } else if(suboption.equals("10 to 14")){
            suboption = "10-14";
        } else if(suboption.equals("15 to 19")){
            suboption = "15-19";
        } else if(suboption.equals("20 to 24")){
            suboption = "20-24";
        } else if(suboption.equals("25 to 29")){
            suboption = "25-29";
        } else if(suboption.equals("30 to 34")){
            suboption = "30-34";
        } else if(suboption.equals("35 to 39")){
            suboption = "35-39";
        } else if(suboption.equals("40 to 44")){
            suboption = "40-44";
        } else if(suboption.equals("45 to 49")){
            suboption = "45-49";
        } else if(suboption.equals("50 to 54")){
            suboption = "50-54";
        } else if(suboption.equals("55 to 59")){
            suboption = "55-59";
        } else if(suboption.equals("60 to 64")){
            suboption = "60-64";
        } else if(suboption.equals("65 to 69")){
            suboption = "65-69";
        } else if(suboption.equals("70 to 74")){
            suboption = "70-74";
        } else if(suboption.equals("75 to 79")){
            suboption = "75-79";
        } else if(suboption.equals("80 to 84")){
            suboption = "80-84";
        } else if(suboption.equals("85 to 89")){
            suboption = "85-89";
        } else if(suboption.equals("90 to 94")){
            suboption = "90-94";
        } else if(suboption.equals("95 to 99")){
            suboption = "95-99";
        } else if(suboption.equals("100 to 104")){
            suboption = "100-104";
        }
        
        for(int i = 0; i < vas.length; i++){ // Loops through all vas data
            
            String currentAgeGroup = vas[i].getAge_group();
            
            //If group matches, adds to answer
            if(currentAgeGroup.equals(suboption)){
                String partially = vas[i].getPartially_covered();
                String fully = vas[i].getFully_covered();
                String additional = vas[i].getAdditional_dose();

                answer = "Partially Vaccinated: " + partially + "\nFully "
                        + "Vaccinated: " + fully + "\nAdditional Dose: " 
                        + additional;
            }
        }
        return answer;
    }

    
    /**
     * calcVGs() determines vaccination totals for the entire state based on
     *      gender group.
     * 
     * @param suboption The gender group be searched.
     * @return Vaccination totals based on search parameters. 
     */
    public String calcVGS(String suboption) {
        String answer = "";
        
        for(int i = 0; i < vgs.length; i++){
            
            String currentGenderGroup = vgs[i].getGender();
            
            //If group matches, adds to answer
            if(currentGenderGroup.equals(suboption)){
                String partially = vgs[i].getPartially_covered();
                String fully = vgs[i].getFully_covered();
                String additional = vgs[i].getAdditional_dose();
                    
                answer = "Partially Vaccinated: " + partially + "\nFully "
                        + "Vaccinated: " + fully + "\nAdditional Dose: " 
                        + additional;
            }
        }
        
        return answer;
    }

    
    /**
     * calcVRS() determines vaccination totals for the entire state based on
     *      race group.
     * 
     * @param suboption The race group be searched.
     * @return Vaccination totals based on search parameters. 
     */
    public String calcVRS(String suboption) {
        String answer = "";
        
        for(int i = 0; i < vrs.length; i++){ // Loops through all vrs data
            
            String currentRaceGroup = vrs[i].getRace();
            
            //If group matches, adds to answer
            if(currentRaceGroup.equals(suboption)){
                String partially = vrs[i].getPartially_covered();
                String fully = vrs[i].getFully_covered();
                String additional = vrs[i].getAdditional_dose();
                    
                answer = "Partially Vaccinated: " + partially + "\nFully "
                        + "Vaccinated: " + fully + "\nAdditional Dose: " 
                        + additional;
            }
        }
        return answer;
    }
}
