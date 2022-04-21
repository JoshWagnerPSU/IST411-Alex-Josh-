package Model.Database;

/**
 * IST 411 Final Project
 * File: DatabaseConnection.java
 * 
 * Purpose: The class acts as a connection to a database
 * 
 * Last Edited On:4/20/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

import java.sql.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DatabaseConnection {
    
    // Private Attributes
    private static Connection con;  // Connection to a database
    private static Statement stmt;  // Statement to communicate with a database
    
    public DatabaseConnection()throws IOException{
 
        // Creates a DatabaseDetails object
        DatabaseDetails dbd = new DatabaseDetails();
        dbd.setDatabaseDetails();
        
        try{
           // Creates a Connection to the database
           con = DriverManager.getConnection(dbd.getUrl(), dbd.getUser(), 
                                             dbd.getPassword() );     
       
            // Creates a Statement object to send queries to the database
            stmt = con.createStatement();
        } catch (Exception e) {
            // Displays the encountered exception
            System.out.println(e);
        }
    } // DatabaseConnection()
    
    
    /**
    * closeConnection() closes the Connection and Statement objects attached to
    *    the database.
    */
    public void closeConnection(){
        try{            
            // Closes the Statement and Connection objects
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    } // closeConnection()

    /**
     * @return the con
     */
    public static Connection getCon() {
        return con;
    }

    /**
     * @return the stmt
     */
    public static Statement getStmt() {
        return stmt;
    }
}
