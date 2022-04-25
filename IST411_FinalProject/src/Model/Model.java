package Model;

import Model.Database.DatabaseConnection;
import Model.HisDeath.HistoricalDeathDataUtil;
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
    }
}
