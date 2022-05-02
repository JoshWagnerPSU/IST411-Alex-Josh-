package Model.Database;

/**
 * IST 411 Final Project
 * File: DatabaseDetails.java
 * 
 * Purpose: This class represents the necessary details to make a connection to 
 *          a database and is used to establish said connection to a database.
 * 
 *          This class has private instance variables and standard getter
 *          methods for the instance variables url, user, and password. 
 * 
 *          This class also has a default constructor method and a method that 
 *          updates the instance variables based on a local external file. 
 * 
 * Last Edited On:4/20/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class DatabaseDetails {
    // Private Attributes
    private String className;   // Represents the java database driver
    private String url;         // Represents the address of the database
    private String user;        // Represents a username for the database
    private String password;    // Represents a password for the username
     
    
    /**
     * DatabaseConnection() is a default constructor method that instantiates
     *   the object's private attributes to null values.
     */
    public DatabaseDetails(){
        String className = null;
        String url = null;
        String user = null;
        String password = null;
    } // DatabaseDetails()
    
    
    /**
     * Standard getter method for the private instance variable url.
     * 
     * @return url - Represents the address of the database being connected to.
     */
    public String getUrl(){
        return url;
    } // getUrl()
    
    
    /**
     * Standard getter method for the private instance variable user.
     * 
     * @return user - Represents a username for the database.
     */
    public String getUser(){
        return user;
    } // getUser()
    
    
    /**
     * Standard getter method for the private instance variable password.
     * 
     * @return password - Represents a password associated with the username for
     *      the database.
     */
    public String getPassword(){
        return password;
    } // getPassword()
    
    
    /**
     * setDatabaseDetails() sets the private instance variables to the database
     *   details contained within a local file. 
     */
    public void setDatabaseDetails(){
        try {
            // Creates InputStream to input data
            InputStream in;

            /* 
             * Instantiates InputStream with a local file containing details
             *   about the database to be connected to. The file can be updated
             *   without changing the java code.
             */
            in = ClassLoader.getSystemResourceAsStream("db.properties");
            
            // Creates ResourceBundle to store the details about the database
            ResourceBundle resources;
            
            // Instantiates ResourceBundle with the InputStream
            resources = new PropertyResourceBundle(in);
            
            // Closes the InputStream
            in.close();
            
            // Sets the instance variables to the details of the local file
            className = resources.getString("jdbc.driver");
            url = resources.getString("jdbc.url");
            user = resources.getString("jdbc.user");
            password = resources.getString("jdbc.password");
            
            // Displays that the database is connected and the database address
            //System.out.println("Connected to: "+ url);
        } catch (Exception exp) {
             // Displays the encountered exception
            System.out.println("Couldn't load resources." + exp);
            System.exit(-1);
        }

        try {
            // Sets the class to driver detailed in the local file
            Class.forName(className);
        } catch (Exception e) {
            // Displays the encountered exception
            System.out.println("Failed to load  driver.");
        }
    } // setDatabaseDetails()
} // DatabaseDetails()