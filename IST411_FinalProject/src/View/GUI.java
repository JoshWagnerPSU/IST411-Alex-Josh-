package View;

/**
 * IST 411 Final Project
 * File: RunGUI.java
 * 
 * Purpose: Represents the GUI for the application and acts as the view 
 *          section of the MVC.
 * 
 * Last Edited On:4/30/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

import javax.swing.*;
import java.awt.*;
import java.util.Properties;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class GUI extends JFrame{
    // Private Attributes
    private Container contentPane;
    private JPanel mainScreen,
                   vacScreen,
                   deathScreen;
    private JButton vaccinations,
                    deaths,
                    vacBack,
                    deathBack,
                    vacSearch,
                    deathSearch;
    private JComboBox vacLocation,
                      deathLocation,
                      vacOption,
                      deathOption,
                      vacAge,
                      deathAge,
                      vacRace,
                      deathRace,
                      vacGender,
                      deathGender,
                      deathMonth,
                      deathYear;
    private JLabel vacAgeLabel,
                   vacRaceLabel,
                   vacGenderLabel,
                   begDateLabel,
                   endDateLabel,
                   deathAgeLabel,
                   deathRaceLabel,
                   deathGenderLabel,
                   dateLabel;
    private JDatePickerImpl startDate,
                            endDate;
    private JTextArea vacAnswer,
                      deathAnswer;
    
    
    /**
     * Constructor method that applies settings to the window and calls other 
     *      methods to build the associated screens. 
     */
    public GUI(){
        // Applies settings to window
        setTitle("Pennsylvania Covid 19 Research Tool");                          
        setMinimumSize(new Dimension(540,600));                      
        setResizable(false);
        setVisible(true);                                         
        setLocationRelativeTo(null);            
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        // Sets private attribute
        contentPane = getContentPane();
        
        // Calls methods to build screens
        buildMainScreen();
        buildVacScreen();
        buildDeathScreen();
        
        // Sets the mainScreen as the first screen to appear
        contentPane.add(mainScreen);
        repaint();
        revalidate();
    }
    
    
    /**
     * buildMainScreen() builds the main screen of the application. 
     */
    private void buildMainScreen(){
        // Creates the JPanel to hold the contents of the screne
        mainScreen = new JPanel();
        mainScreen.setLayout(null);
        
        // Creates elements
        JLabel header = new JLabel("Pennsylvania Covid 19 Research Tool");
        JTextArea description = new JTextArea("Welcome!\n\n"
            + "This application is used to report a variety of information "
            + "related to vaccinations\nand deaths related to Covid-19 in "
            + "Pennsylvania.\n\nAll data is sourced directly from data.pa.gov."
            + "\n\nSelect a category to begin...");
        vaccinations = new JButton("Vaccination Data");
        deaths = new JButton("Death Data");
        
        // Applies settings to elements
        header.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        description.setOpaque(false);
        description.setEditable(false);
        description.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        
        // Sets position and sizing for elements
        header.setBounds(10, 10, 400, 25);
        description.setBounds(10, 65, 510, 180);
        vaccinations.setBounds(160, 280, 200, 50);
        deaths.setBounds(160, 350, 200, 50);
        
        // Adds the elements to the JPanel
        mainScreen.add(header);
        mainScreen.add(description);
        mainScreen.add(vaccinations);
        mainScreen.add(deaths);    
    }
    
    
    /**
     * buildVacScreen() builds the vaccination screen of the application. 
     */
    private void buildVacScreen(){
        // Creates the JPanel to hold the contents of the screne
        vacScreen = new JPanel();
        vacScreen.setLayout(null);
        
        // Creates elements
        JLabel header = new JLabel("Vaccination Data");
        JTextArea description = new JTextArea("The below search parameters "
                + "allow you to determine the amount of people\namong different"
                + " groups that are partially vaccinated (1 dose), fully "
                + "vaccinated\n(2 doses), and those who have received an "
                + "additional dose (3 doses).\n\nAll demographic based data "
                + "is pulled from the most up to date data available.\n\n" 
                + "All historical totals are based on records that range "
                + "from 01/01/2021 up to today.");
        JLabel location = new JLabel("County:");
        String[] locations = {" ", "All", "Adams", "Allegheny", "Armstrong", 
            "Beaver", "Bedford", "Berks", "Blair", "Bucks", "Butler", 
            "Cambria", "Carbon", "Centre", "Chester", "Clarion", "Clearfield", 
            "Clinton", "Columbia", "Crawford", "Cumberland", "Dauphin", 
            "Delaware", "Elk", "Erie", "Fayette", "Franklin", "Greene", 
            "Huntingdon", "Indiana", "Jefferson", "Juniata", "Lackawanna", 
            "Lancaster", "Lawrence", "Lebanon", "Lehigh", "Luzerne", 
            "Lycoming", "Mckean", "Mercer", "Mifflin", "Monroe", "Montgomery", 
            "Montour", "Northampton", "Northumberland", "Perry", "Philadelphia", 
            "Pike", "Potter", "Schuylkill", "Snyder", "Somerset", "Susquehanna", 
            "Union", "Venango", "Warren", "Washington", "Wayne", "Westmoreland",
            "Wyoming", "York"};
        vacLocation = new JComboBox(locations);
        JLabel option = new JLabel("Search Option: ");
        String[] options = {" ", "Age", "Race", "Gender", "Total"};
        vacOption = new JComboBox(options);
        vacAgeLabel = new JLabel("Age Group:");
        vacRaceLabel = new JLabel("Race Group:");
        vacGenderLabel = new JLabel("Gender Group: ");
        String[] ages = {" ", "5 to 9", "10 to 14", "15 to 19", "20 to 24", 
            "25 to 29", "30 to 34", "35 to 39", "40 to 44", "45 to 49", "50 to 54",
            "55 to 59", "60 to 64", "65 to 69", "70 to74", "75 to 79", "80 to 84",
            "85 to 89", "90 to 94", "95 to 99", "100 to 104"};
        vacAge = new JComboBox(ages);
        String[] races = {" ", "African-American", "Asian", "Native American", 
            "Pacific Islander", "Multiple/Other", "White"};
        vacRace = new JComboBox(races);
        String[] genders = {" ", "Male", "Female"};
        vacGender = new JComboBox(genders);
        begDateLabel = new JLabel("Beginning Date:");
        endDateLabel = new JLabel("Ending Date:" );
        vacAnswer =new JTextArea();
        vacSearch = new JButton("Search");
        vacBack = new JButton ("Back");
        
        // Date Picker element
        UtilDateModel model1 = new UtilDateModel();
        Properties p1 = new Properties();
        p1.put("text.today", "Today");
        p1.put("text.month", "Month");
        p1.put("text.year", "Year");
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p1);
        startDate = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
        
        // Date Picker element
        UtilDateModel model2 = new UtilDateModel();
        Properties p2 = new Properties();
        p2.put("text.today", "Today");
        p2.put("text.month", "Month");
        p2.put("text.year", "Year");
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
        endDate = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
        
        // Applies settings to elements
        header.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        description.setOpaque(false);
        description.setEditable(false);
        description.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        vacAnswer.setOpaque(false);
        vacAnswer.setEditable(false);
        vacAnswer.setLineWrap(true);
        vacAnswer.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        
        // Sets position and sizing for elements
        header.setBounds(10, 10, 200, 25);
        description.setBounds(10, 55, 510, 150);
        location.setBounds(10, 225, 100, 25);
        vacLocation.setBounds(120, 225, 150, 25);
        option.setBounds(10, 260, 100, 25);
        vacOption.setBounds(120, 260, 150, 25);
        vacAgeLabel.setBounds(10, 295, 100, 25);
        vacAge.setBounds(120, 295, 150, 25);
        vacRaceLabel.setBounds(10, 295, 100, 25);
        vacRace.setBounds(120, 295, 150, 25);
        vacGenderLabel.setBounds(10, 295, 100, 25);
        vacGender.setBounds(120, 295, 150, 25);
        begDateLabel.setBounds(10, 295, 100, 25);
        startDate.setBounds(120, 295, 150, 30);
        endDateLabel.setBounds(290, 295, 80, 25);
        endDate.setBounds(370, 295, 150, 30);
        vacAnswer.setBounds(12, 360, 500, 150);
        vacSearch.setBounds(145, 520, 100, 25);
        vacBack.setBounds(275, 520, 100, 25);
        
        // Adds the elements to the JPanel
        vacScreen.add(header);
        vacScreen.add(description);
        vacScreen.add(location);
        vacScreen.add(vacLocation);
        vacScreen.add(option);
        vacScreen.add(vacOption);
        vacScreen.add(vacAgeLabel);
        vacScreen.add(vacAge);
        vacScreen.add(vacRaceLabel);
        vacScreen.add(vacRace);
        vacScreen.add(vacGenderLabel);
        vacScreen.add(vacGender);
        vacScreen.add(begDateLabel);
        vacScreen.add(startDate);
        vacScreen.add(endDateLabel);
        vacScreen.add(endDate);
        vacScreen.add(getVacAnswer());
        vacScreen.add(vacSearch);
        vacScreen.add(vacBack);
        
        // Hides suboptions
        vacAgeLabel.setVisible(false);
        vacAge.setVisible(false);
        vacRaceLabel.setVisible(false);
        vacRace.setVisible(false);
        vacGenderLabel.setVisible(false);
        vacGender.setVisible(false);
        begDateLabel.setVisible(false);
        startDate.setVisible(false);
        endDateLabel.setVisible(false);
        endDate.setVisible(false);
        
    }
    
    
    /**
     * buildDeathScreen() builds the death screen of the application. 
     */
    private void buildDeathScreen(){
        // Creates the JPanel to hold the contents of the screne
        deathScreen = new JPanel();
        deathScreen.setLayout(null);
        
        // Creates elements
        JLabel header = new JLabel("Death Data");
        JTextArea description = new JTextArea("The below search parameters "
                + "allow you to determine the amount of people\namong "
                + "different groups who have died in relation to Covid-19.\n\n"
                + "All demographic based data is pulled from the most up to "
                + "date data available.\n\nAll historical totals are based "
                + "on records that range from 03/01/2020 up to the\nlast "
                + "month. Current data for the total number of deaths this "
                + "month are not\navailable until the month has concluded. ");
        JLabel location = new JLabel("County:");
        String[] locations = {" ", "All", "Adams", "Allegheny", "Armstrong", 
            "Beaver", "Bedford", "Berks", "Blair", "Bucks", "Butler", 
            "Cambria", "Carbon", "Centre", "Chester", "Clarion", "Clearfield", 
            "Clinton", "Columbia", "Crawford", "Cumberland", "Dauphin", 
            "Delaware", "Elk", "Erie", "Fayette", "Franklin", "Greene", 
            "Huntingdon", "Indiana", "Jefferson", "Juniata", "Lackawanna", 
            "Lancaster", "Lawrence", "Lebanon", "Lehigh", "Luzerne", 
            "Lycoming", "Mckean", "Mercer", "Mifflin", "Monroe", "Montgomery", 
            "Montour", "Northampton", "Northumberland", "Perry", "Philadelphia", 
            "Pike", "Potter", "Schuylkill", "Snyder", "Somerset", "Susquehanna", 
            "Union", "Venango", "Warren", "Washington", "Wayne", "Westmoreland",
            "Wyoming", "York"};
        deathLocation = new JComboBox(locations);
        JLabel option = new JLabel("Search Option: ");
        String[] options = {" ", "Age", "Race", "Gender", "Total"};
        deathOption = new JComboBox(options);
        deathAgeLabel = new JLabel("Age Group:");
        deathRaceLabel = new JLabel("Race Group:");
        deathGenderLabel = new JLabel("Gender Group: ");
        String[] ages = {" ", "0 to 9", "10 to 19", "20 to 29", "30 to 39", 
            "40 to 49", "50 to 59", "60 to 69", "70 to 79", "80 to 89", 
            "90 to 99", "100+"};
        deathAge = new JComboBox(ages);
        String[] races = {" ", "African American", "Asian", "White", 
            "Multiple/Other"};
        deathRace = new JComboBox(races);
        String[] genders = {" ", "Male", "Female"};
        deathGender = new JComboBox(genders);
        dateLabel = new JLabel("Date:");
        String[] months = {" ", "January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November",
            "December"};
        deathMonth = new JComboBox(months);
        String[] years = {" ", "2020", "2021", "2022"};
        deathYear = new JComboBox(years);
        deathAnswer = new JTextArea();
        deathSearch = new JButton("Search");
        deathBack = new JButton ("Back");
        
        // Applies settings to elements
        header.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        description.setOpaque(false);
        description.setEditable(false);
        description.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        deathAnswer.setOpaque(false);
        deathAnswer.setEditable(false);
        deathAnswer.setLineWrap(true);
        deathAnswer.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        
        // Sets position and sizing for elements
        header.setBounds(10, 10, 200, 25);
        description.setBounds(10, 55, 510, 150);
        location.setBounds(10, 255, 100, 25);
        deathLocation.setBounds(120, 255, 150, 25);
        option.setBounds(10, 285, 100, 25);
        deathOption.setBounds(120, 285, 150, 25);
        deathAgeLabel.setBounds(10, 320, 100, 25);
        deathAge.setBounds(120, 320, 150, 25);
        deathRaceLabel.setBounds(10, 320, 100, 25);
        deathRace.setBounds(120, 320, 150, 25);
        deathGenderLabel.setBounds(10, 320, 100, 25);
        deathGender.setBounds(120, 320, 150, 25);
        dateLabel.setBounds(10, 320, 100, 25);
        deathMonth.setBounds(120, 320, 90, 25);
        deathYear.setBounds(220, 320, 75, 25);
        deathAnswer.setBounds(12, 390, 500, 25);
        deathSearch.setBounds(145, 520, 100, 25);
        deathBack.setBounds(275, 520, 100, 25);
        
        // Adds the elements to the JPanel
        deathScreen.add(header);
        deathScreen.add(description);
        deathScreen.add(location);
        deathScreen.add(deathLocation);
        deathScreen.add(option);
        deathScreen.add(deathOption);
        deathScreen.add(deathAgeLabel);
        deathScreen.add(deathAge);
        deathScreen.add(deathRaceLabel);
        deathScreen.add(deathRace);
        deathScreen.add(deathGenderLabel);
        deathScreen.add(deathGender);
        deathScreen.add(dateLabel);
        deathScreen.add(deathMonth);
        deathScreen.add(deathYear);
        deathScreen.add(deathSearch);
        deathScreen.add(deathBack);
        deathScreen.add(deathAnswer);
        
        // Hides suboptions
        deathAgeLabel.setVisible(false);
        deathAge.setVisible(false);
        deathRaceLabel.setVisible(false);
        deathRace.setVisible(false);
        deathGenderLabel.setVisible(false);
        deathGender.setVisible(false);
        dateLabel.setVisible(false);
        deathMonth.setVisible(false);
        deathYear.setVisible(false);
    }
    
    
    /**
     * view() removes the current screen from the window and replaces it with 
     *      another screen.
     * 
     * @param panelToView The screen to view. 
     */
    public void view(JPanel panelToView){
        contentPane.removeAll();
        contentPane.add(panelToView);
        repaint();
        revalidate();
    }
    
    
    /**
     * hideVacSubOptions reset and hides the sub option fields and answer on 
     *      the vaccination screen. 
     */
    public void hideVacSubOptions(){
        // Hides sub options
        vacAgeLabel.setVisible(false);
        vacAge.setVisible(false);
        vacRaceLabel.setVisible(false);
        vacRace.setVisible(false);
        vacGenderLabel.setVisible(false);
        vacGender.setVisible(false);
        begDateLabel.setVisible(false);
        startDate.setVisible(false);
        endDateLabel.setVisible(false);
        endDate.setVisible(false);
        
        // Resets sub options
        vacAge.setSelectedIndex(0);
        vacRace.setSelectedIndex(0);
        vacGender.setSelectedIndex(0);
        startDate.getModel().setValue(null);
        endDate.getModel().setValue(null);
        
        // Resets answer
        getVacAnswer().setText("");
    }
    
    
    /**
     * hideVacSubOptions reset and hides the sub option fields and answer on 
     *      the death screen.
     */
    public void hideDeathSubOptions(){
        // Hides sub options
        deathAgeLabel.setVisible(false);
        deathAge.setVisible(false);
        deathRaceLabel.setVisible(false);
        deathRace.setVisible(false);
        deathGenderLabel.setVisible(false);
        deathGender.setVisible(false);
        dateLabel.setVisible(false);
        deathMonth.setVisible(false);
        deathYear.setVisible(false);
        
        // Resets sub options
        deathAge.setSelectedIndex(0);
        deathRace.setSelectedIndex(0);
        deathGender.setSelectedIndex(0);
        deathMonth.setSelectedIndex(0);
        deathYear.setSelectedIndex(0);
        
        // Resets answer
        getDeathAnswer().setText("");
    }
    
    // Getter and Setter Methods

    /**
     * @return the mainScreen
     */
    public JPanel getMainScreen() {
        return mainScreen;
    }

    /**
     * @param mainScreen the mainScreen to set
     */
    public void setMainScreen(JPanel mainScreen) {
        this.mainScreen = mainScreen;
    }

    /**
     * @return the vacScreen
     */
    public JPanel getVacScreen() {
        return vacScreen;
    }

    /**
     * @param vacScreen the vacScreen to set
     */
    public void setVacScreen(JPanel vacScreen) {
        this.vacScreen = vacScreen;
    }

    /**
     * @return the deathScreen
     */
    public JPanel getDeathScreen() {
        return deathScreen;
    }

    /**
     * @param deathScreen the deathScreen to set
     */
    public void setDeathScreen(JPanel deathScreen) {
        this.deathScreen = deathScreen;
    }

    /**
     * @return the vaccinations
     */
    public JButton getVaccinations() {
        return vaccinations;
    }

    /**
     * @param vaccinations the vaccinations to set
     */
    public void setVaccinations(JButton vaccinations) {
        this.vaccinations = vaccinations;
    }

    /**
     * @return the deaths
     */
    public JButton getDeaths() {
        return deaths;
    }

    /**
     * @param deaths the deaths to set
     */
    public void setDeaths(JButton deaths) {
        this.deaths = deaths;
    }

    /**
     * @return the vacBack
     */
    public JButton getVacBack() {
        return vacBack;
    }

    /**
     * @param vacBack the vacBack to set
     */
    public void setVacBack(JButton vacBack) {
        this.vacBack = vacBack;
    }

    /**
     * @return the deathBack
     */
    public JButton getDeathBack() {
        return deathBack;
    }

    /**
     * @param deathBack the deathBack to set
     */
    public void setDeathBack(JButton deathBack) {
        this.deathBack = deathBack;
    }

    /**
     * @return the vacSearch
     */
    public JButton getVacSearch() {
        return vacSearch;
    }

    /**
     * @param vacSearch the vacSearch to set
     */
    public void setVacSearch(JButton vacSearch) {
        this.vacSearch = vacSearch;
    }

    /**
     * @return the deathSearch
     */
    public JButton getDeathSearch() {
        return deathSearch;
    }

    /**
     * @param deathSearch the deathSearch to set
     */
    public void setDeathSearch(JButton deathSearch) {
        this.deathSearch = deathSearch;
    }

    /**
     * @return the vacLocation
     */
    public JComboBox getVacLocation() {
        return vacLocation;
    }

    /**
     * @param vacLocation the vacLocation to set
     */
    public void setVacLocation(JComboBox vacLocation) {
        this.vacLocation = vacLocation;
    }

    /**
     * @return the deathLocation
     */
    public JComboBox getDeathLocation() {
        return deathLocation;
    }

    /**
     * @param deathLocation the deathLocation to set
     */
    public void setDeathLocation(JComboBox deathLocation) {
        this.deathLocation = deathLocation;
    }

    /**
     * @return the vacOption
     */
    public JComboBox getVacOption() {
        return vacOption;
    }

    /**
     * @param vacOption the vacOption to set
     */
    public void setVacOption(JComboBox vacOption) {
        this.vacOption = vacOption;
    }

    /**
     * @return the deathOption
     */
    public JComboBox getDeathOption() {
        return deathOption;
    }

    /**
     * @param deathOption the deathOption to set
     */
    public void setDeathOption(JComboBox deathOption) {
        this.deathOption = deathOption;
    }

    /**
     * @return the vacAge
     */
    public JComboBox getVacAge() {
        return vacAge;
    }

    /**
     * @param vacAge the vacAge to set
     */
    public void setVacAge(JComboBox vacAge) {
        this.vacAge = vacAge;
    }

    /**
     * @return the deathAge
     */
    public JComboBox getDeathAge() {
        return deathAge;
    }

    /**
     * @param deathAge the deathAge to set
     */
    public void setDeathAge(JComboBox deathAge) {
        this.deathAge = deathAge;
    }

    /**
     * @return the vacRace
     */
    public JComboBox getVacRace() {
        return vacRace;
    }

    /**
     * @param vacRace the vacRace to set
     */
    public void setVacRace(JComboBox vacRace) {
        this.vacRace = vacRace;
    }

    /**
     * @return the deathRace
     */
    public JComboBox getDeathRace() {
        return deathRace;
    }

    /**
     * @param deathRace the deathRace to set
     */
    public void setDeathRace(JComboBox deathRace) {
        this.deathRace = deathRace;
    }

    /**
     * @return the vacGender
     */
    public JComboBox getVacGender() {
        return vacGender;
    }

    /**
     * @param vacGender the vacGender to set
     */
    public void setVacGender(JComboBox vacGender) {
        this.vacGender = vacGender;
    }

    /**
     * @return the deathGender
     */
    public JComboBox getDeathGender() {
        return deathGender;
    }

    /**
     * @param deathGender the deathGender to set
     */
    public void setDeathGender(JComboBox deathGender) {
        this.deathGender = deathGender;
    }

    /**
     * @return the deathMonth
     */
    public JComboBox getDeathMonth() {
        return deathMonth;
    }

    /**
     * @param deathMonth the deathMonth to set
     */
    public void setDeathMonth(JComboBox deathMonth) {
        this.deathMonth = deathMonth;
    }

    /**
     * @return the deathYear
     */
    public JComboBox getDeathYear() {
        return deathYear;
    }

    /**
     * @param deathYear the deathYear to set
     */
    public void setDeathYear(JComboBox deathYear) {
        this.deathYear = deathYear;
    }

    /**
     * @return the vacAgeLabel
     */
    public JLabel getVacAgeLabel() {
        return vacAgeLabel;
    }

    /**
     * @param vacAgeLabel the vacAgeLabel to set
     */
    public void setVacAgeLabel(JLabel vacAgeLabel) {
        this.vacAgeLabel = vacAgeLabel;
    }

    /**
     * @return the vacRaceLabel
     */
    public JLabel getVacRaceLabel() {
        return vacRaceLabel;
    }

    /**
     * @param vacRaceLabel the vacRaceLabel to set
     */
    public void setVacRaceLabel(JLabel vacRaceLabel) {
        this.vacRaceLabel = vacRaceLabel;
    }

    /**
     * @return the vacGenderLabel
     */
    public JLabel getVacGenderLabel() {
        return vacGenderLabel;
    }

    /**
     * @param vacGenderLabel the vacGenderLabel to set
     */
    public void setVacGenderLabel(JLabel vacGenderLabel) {
        this.vacGenderLabel = vacGenderLabel;
    }

    /**
     * @return the begDateLabel
     */
    public JLabel getBegDateLabel() {
        return begDateLabel;
    }

    /**
     * @param begDateLabel the begDateLabel to set
     */
    public void setBegDateLabel(JLabel begDateLabel) {
        this.begDateLabel = begDateLabel;
    }

    /**
     * @return the endDateLabel
     */
    public JLabel getEndDateLabel() {
        return endDateLabel;
    }

    /**
     * @param endDateLabel the endDateLabel to set
     */
    public void setEndDateLabel(JLabel endDateLabel) {
        this.endDateLabel = endDateLabel;
    }

    /**
     * @return the deathAgeLabel
     */
    public JLabel getDeathAgeLabel() {
        return deathAgeLabel;
    }

    /**
     * @param deathAgeLabel the deathAgeLabel to set
     */
    public void setDeathAgeLabel(JLabel deathAgeLabel) {
        this.deathAgeLabel = deathAgeLabel;
    }

    /**
     * @return the deathRaceLabel
     */
    public JLabel getDeathRaceLabel() {
        return deathRaceLabel;
    }

    /**
     * @param deathRaceLabel the deathRaceLabel to set
     */
    public void setDeathRaceLabel(JLabel deathRaceLabel) {
        this.deathRaceLabel = deathRaceLabel;
    }

    /**
     * @return the deathGenderLabel
     */
    public JLabel getDeathGenderLabel() {
        return deathGenderLabel;
    }

    /**
     * @param deathGenderLabel the deathGenderLabel to set
     */
    public void setDeathGenderLabel(JLabel deathGenderLabel) {
        this.deathGenderLabel = deathGenderLabel;
    }

    /**
     * @return the dateLabel
     */
    public JLabel getDateLabel() {
        return dateLabel;
    }

    /**
     * @param dateLabel the dateLabel to set
     */
    public void setDateLabel(JLabel dateLabel) {
        this.dateLabel = dateLabel;
    }

    /**
     * @return the startDate
     */
    public JDatePickerImpl getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(JDatePickerImpl startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public JDatePickerImpl getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(JDatePickerImpl endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the vacAnswer
     */
    public JTextArea getVacAnswer() {
        return vacAnswer;
    }

    /**
     * @param vacAnswer the vacAnswer to set
     */
    public void setVacAnswer(JTextArea vacAnswer) {
        this.vacAnswer = vacAnswer;
    }

    /**
     * @return the deathAnswer
     */
    public JTextArea getDeathAnswer() {
        return deathAnswer;
    }

    /**
     * @param deathAnswer the deathAnswer to set
     */
    public void setDeathAnswer(JTextArea deathAnswer) {
        this.deathAnswer = deathAnswer;
    }
}
