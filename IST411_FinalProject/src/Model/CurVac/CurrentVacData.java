package Model.CurVac;

import com.google.gson.Gson;
import java.awt.List;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


/**
 * IST 411 Final Project
 * File: 
 * 
 * Purpose: 
 * 
 * Last Edited On:4/20/2022
 * Last Edited By: Alex Koontz
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

public class CurrentVacData {

    //VacAgeCounty[] vac = new VacAgeCounty[68];
    //VacGenderCounty[] vgc = new VacGenderCounty[68];
    //VacRaceCounty[] vrc = new VacRaceCounty[68];
    //VacAgeState[] vas = new VacAgeState[21];
    //VacGenderState[] vgs = new VacGenderState[3];
    //VacRaceState[] vrs = new VacRaceState[7];
    
    public static void main(String[] args){
        CurrentVacData();
    }
    
    public static void CurrentVacData(){
        pullData();
    }

    public static void pullData() {
        pullVAC();
        pullVGC();
        pullVRC();
        pullVAS();
        pullVGS();
        pullVRS();
    }

    public static void pullVAC() {
        //url string for api
        String vacURL = "https://data.pa.gov/resource/niuh-2xe3.json";
        //try to connect to api
        try (InputStream is = new URL(vacURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            VacAgeCounty[] vac = gson.fromJson(reader, VacAgeCounty[].class);

            //for(int i = 0;i < vac.length; i++){
                System.out.println(vac.length);
            //}
        } catch(Exception e){System.out.println(e);}
    }

    public static void pullVGC() {
        //url string for api
        String vgcURL = "https://data.pa.gov/resource/jweg-3ezy.json";
        //try to connect to api
        try (InputStream is = new URL(vgcURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            VacGenderCounty[] vgc = gson.fromJson(reader, VacGenderCounty[].class);

            //for(int i = 0;i < vgc.length; i++){
                System.out.println(vgc.length);
            //}
        } catch(Exception e){System.out.println(e);}
    }

    public static void pullVRC() {
        //url string for api
        String vrcURL = "https://data.pa.gov/resource/x5z9-57ub.json";
        //try to connect to api
        try (InputStream is = new URL(vrcURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            VacRaceCounty[] vrc = gson.fromJson(reader, VacRaceCounty[].class);

            //for(int i = 0;i < vgc.length; i++){
                System.out.println(vrc.length);
            //}
        } catch(Exception e){System.out.println(e);}
    }

    public static void pullVAS() {
        //url string for api
        String vasURL = "https://data.pa.gov/resource/xy2e-dqvt.json";
        //try to connect to api
        try (InputStream is = new URL(vasURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            VacAgeState[] vas = gson.fromJson(reader, VacAgeState[].class);

            //for(int i = 0;i < vas.length; i++){
                System.out.println(vas.length);
            //}
        } catch(Exception e){System.out.println(e);}
    }

    public static void pullVGS() {
        //url string for api
        String vgsURL = "https://data.pa.gov/resource/id8t-dnk6.json";
        //try to connect to api
        try (InputStream is = new URL(vgsURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            VacGenderState[] vgs = gson.fromJson(reader, VacGenderState[].class);

            //for(int i = 0;i < vgs.length; i++){
                System.out.println(vgs.length);
            //}
        } catch(Exception e){System.out.println(e);}
    }

    public static void pullVRS() {
        //url string for api
        String vrsURL = "https://data.pa.gov/resource/e384-bs7r.json";
        //try to connect to api
        try (InputStream is = new URL(vrsURL).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            VacRaceState[] vrs = gson.fromJson(reader, VacRaceState[].class);

            //for(int i = 0;i < vrs.length; i++){
                System.out.println(vrs.length);
            //}
        } catch(Exception e){System.out.println(e);}
    }

    //Start of calculations
    public static String calcVAC(String location, String option, String suboption) {
        String answer = "";
        
        
        
        
        return answer;
    }

    public static String calcVGC(String location, String option, String suboption) {
        String answer = "";
        
        return answer;
    }

    public static String calcVRC(String location, String option, String suboption) {
        String answer = "";
        
        return answer;
    }

    public static String calcVAS(String location, String option, String suboption) {
        String answer = "";
        
        return answer;
    }

    public static String calcVGS(String location, String option, String suboption) {
        String answer = "";
        
        return answer;
    }

    public static String calcVRS(String location, String option, String suboption) {
        String answer = "";
        
        return answer;
    }

}
