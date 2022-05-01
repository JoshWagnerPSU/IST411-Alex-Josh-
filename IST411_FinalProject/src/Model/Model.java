package Model;

import Model.Database.DatabaseConnection;
import Model.HisDeath.HistoricalDeathCalc;
import Model.HisDeath.HistoricalDeathCalc2;
import Model.HisDeath.HistoricalDeathDataUtil;
import Model.HisVac.HistoricalVacCalc;
import Model.HisVac.HistoricalVacDataUtil;
import java.io.IOException;

/**
 * IST 411 Final Project
 * File: Model.java
 * 
 * Purpose: 
 * 
 * Last Edited On:4/24/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

public class Model {
    public static void main(String[] args) throws IOException{
        DatabaseConnection dbc = new DatabaseConnection();
        //HistoricalVacDataUtil.run(dbc.getStmt());
        //HistoricalDeathDataUtil.run(dbc.getStmt());
        //System.out.println(HistoricalVacCalc.run(dbc.getStmt(), "Lancaster", "2/2/2022", "3/1/2022"));
        //System.out.println(HistoricalDeathCalc.run(dbc.getStmt(), "York", "5/1/2021", "6/1/2021"));
        System.out.println(HistoricalDeathCalc2.run(dbc.getStmt(), "York", "5/1/2021") );
    }
}
