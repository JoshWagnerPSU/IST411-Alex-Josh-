package GUI.View;

/**
 * IST 411 Final Project
 * File: RunGUI.java
 * 
 * Purpose: 
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
    
    public GUI(){
        setTitle("Covid 19 Research Tool");                          
        setMinimumSize(new Dimension(540,600));                      
        setResizable(false);
        setVisible(true);                                         
        setLocationRelativeTo(null);            
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        contentPane = getContentPane();
        buildMainScreen();
        buildVacScreen();
        buildDeathScreen();
        
        contentPane.add(mainScreen);
        repaint();
        revalidate();
    }
    
    private void buildMainScreen(){
        mainScreen = new JPanel();
        mainScreen.setLayout(null);
        
        JLabel header = new JLabel("Covid 19 Research Tool");
        JTextArea description = new JTextArea("Description goes here.");
        vaccinations = new JButton("Vaccination Data");
        deaths = new JButton("Death Data");
        
        header.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        description.setOpaque(false);
        description.setEditable(false);
        description.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        header.setBounds(10, 10, 200, 25);
        description.setBounds(10, 45, 510, 300);
        vaccinations.setBounds(60, 355, 200, 200);
        deaths.setBounds(270, 355, 200, 200);
        
        mainScreen.add(header);
        mainScreen.add(description);
        mainScreen.add(vaccinations);
        mainScreen.add(deaths);    
    }
    
    private void buildVacScreen(){
        vacScreen = new JPanel();
        vacScreen.setLayout(null);
        
        JLabel header = new JLabel("Vaccination Data");
        JTextArea description = new JTextArea("Description goes here.");
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
            "Wyoming", "York", "Out-of-State"};
        vacLocation = new JComboBox(locations);
        JLabel option = new JLabel("Search Option: ");
        String[] options = {" ", "Age", "Race", "Gender", "Total"};
        vacOption = new JComboBox(options);
        vacAgeLabel = new JLabel("Age Group:");
        vacRaceLabel = new JLabel("Race Group:");
        vacGenderLabel = new JLabel("Gender Group: ");
        String[] ages = {" ", "5 - 9", "10 - 14", "15 - 19", "20 - 24", 
            "25 - 29", "30 - 34", "35 - 39", "40 - 44", "45 - 49", "50 - 54",
            "55 - 59", "60 - 64", "65 - 69", "70 - 74", "75 - 79", "80 - 84",
            "85 - 89", "90 - 94", "95 - 99", "100 - 104"};
        vacAge = new JComboBox(ages);
        String[] races = {" ", "African American", "Asian", "Native American", 
            "Pacific Islander", "Multiple/Other", "White", "Unknown"};
        vacRace = new JComboBox(races);
        String[] genders = {" ", "Male", "Female", "Both/Other/Unknown"};
        vacGender = new JComboBox(genders);
        begDateLabel = new JLabel("Beginning Date:");
        endDateLabel = new JLabel("Ending Date:" );
        vacSearch = new JButton("Search");
        vacBack = new JButton ("Back");
        
        UtilDateModel model1 = new UtilDateModel();
        Properties p1 = new Properties();
        p1.put("text.today", "Today");
        p1.put("text.month", "Month");
        p1.put("text.year", "Year");
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p1);
        startDate = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
        
        UtilDateModel model2 = new UtilDateModel();
        Properties p2 = new Properties();
        p2.put("text.today", "Today");
        p2.put("text.month", "Month");
        p2.put("text.year", "Year");
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
        endDate = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
        
        header.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        description.setOpaque(false);
        description.setEditable(false);
        description.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        header.setBounds(10, 10, 200, 25);
        description.setBounds(10, 45, 510, 100);
        location.setBounds(10, 155, 100, 25);
        vacLocation.setBounds(120, 155, 150, 25);
        option.setBounds(10, 190, 100, 25);
        vacOption.setBounds(120, 190, 150, 25);
        vacAgeLabel.setBounds(10, 225, 100, 25);
        vacAge.setBounds(120, 225, 150, 25);
        vacRaceLabel.setBounds(10, 225, 100, 25);
        vacRace.setBounds(120, 225, 150, 25);
        vacGenderLabel.setBounds(10, 225, 100, 25);
        vacGender.setBounds(120, 225, 150, 25);
        begDateLabel.setBounds(10, 225, 100, 25);
        startDate.setBounds(120, 225, 150, 30);
        endDateLabel.setBounds(290, 225, 80, 25);
        endDate.setBounds(370, 225, 150, 30);
        vacSearch.setBounds(10, 300, 100, 25);
        vacBack.setBounds(120, 300, 100, 25);
        
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
        vacScreen.add(vacSearch);
        vacScreen.add(vacBack);
        
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
    
    private void buildDeathScreen(){
        deathScreen = new JPanel();
        deathScreen.setLayout(null);
        
        JLabel header = new JLabel("Death Data");
        JTextArea description = new JTextArea("Description goes here.");
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
            "Wyoming", "York", "Out-of-State"};
        deathLocation = new JComboBox(locations);
        JLabel option = new JLabel("Search Option: ");
        String[] options = {" ", "Age", "Race", "Gender", "Total"};
        deathOption = new JComboBox(options);
        deathAgeLabel = new JLabel("Age Group:");
        deathRaceLabel = new JLabel("Race Group:");
        deathGenderLabel = new JLabel("Gender Group: ");
        String[] ages = {" ", "5 - 9", "10 - 14", "15 - 19", "20 - 24", 
            "25 - 29", "30 - 34", "35 - 39", "40 - 44", "45 - 49", "50 - 54",
            "55 - 59", "60 - 64", "65 - 69", "70 - 74", "75 - 79", "80 - 84",
            "85 - 89", "90 - 94", "95 - 99", "100 - 104"};
        deathAge = new JComboBox(ages);
        String[] races = {" ", "African American", "Asian", "Native American", 
            "Pacific Islander", "Multiple/Other", "White", "Unknown"};
        deathRace = new JComboBox(races);
        String[] genders = {" ", "Male", "Female", "Both/Other/Unknown"};
        deathGender = new JComboBox(genders);
        dateLabel = new JLabel("Date:");
        String[] months = {" ", "January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November",
            "December"};
        deathMonth = new JComboBox(months);
        String[] years = {" ", "2020", "2021", "2022"};
        deathYear = new JComboBox(years);
        deathSearch = new JButton("Search");
        deathBack = new JButton ("Back");
        
        header.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        description.setOpaque(false);
        description.setEditable(false);
        description.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        header.setBounds(10, 10, 200, 25);
        description.setBounds(10, 45, 510, 100);
        location.setBounds(10, 155, 100, 25);
        deathLocation.setBounds(120, 155, 150, 25);
        option.setBounds(10, 190, 100, 25);
        deathOption.setBounds(120, 190, 150, 25);
        deathAgeLabel.setBounds(10, 225, 100, 25);
        deathAge.setBounds(120, 225, 150, 25);
        deathRaceLabel.setBounds(10, 225, 100, 25);
        deathRace.setBounds(120, 225, 150, 25);
        deathGenderLabel.setBounds(10, 225, 100, 25);
        deathGender.setBounds(120, 225, 150, 25);
        dateLabel.setBounds(10, 225, 100, 25);
        deathMonth.setBounds(120, 225, 90, 25);
        deathYear.setBounds(220, 225, 75, 25);
        deathSearch.setBounds(10, 300, 100, 25);
        deathBack.setBounds(120, 300, 100, 25);
        
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
    
    public void view(JPanel panelToView){
        contentPane.removeAll();
        contentPane.add(panelToView);
        repaint();
        revalidate();
    }
    
    public void hideVacSubOptions(){
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
        
        vacAge.setSelectedIndex(0);
        vacRace.setSelectedIndex(0);
        vacGender.setSelectedIndex(0);
        startDate.getModel().setValue(null);
        endDate.getModel().setValue(null);
    }
    
    public void hideDeathSubOptions(){
        deathAgeLabel.setVisible(false);
        deathAge.setVisible(false);
        deathRaceLabel.setVisible(false);
        deathRace.setVisible(false);
        deathGenderLabel.setVisible(false);
        deathGender.setVisible(false);
        dateLabel.setVisible(false);
        deathMonth.setVisible(false);
        deathYear.setVisible(false);
        
        deathAge.setSelectedIndex(0);
        deathRace.setSelectedIndex(0);
        deathGender.setSelectedIndex(0);
        deathMonth.setSelectedIndex(0);
        deathYear.setSelectedIndex(0);
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
    
    
    
}
