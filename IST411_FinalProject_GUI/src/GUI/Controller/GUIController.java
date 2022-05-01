package GUI.Controller;

import GUI.View.GUI;
import Model.Database.DatabaseConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class GUIController implements ActionListener {
    
    // Private Attributes
    private DatabaseConnection dbc;
    private GUI gui;
    
    public GUIController(GUI gui) throws IOException{
        this.gui = gui;
        dbc = new DatabaseConnection();
        setActionListeners();
    }
    
    private void setActionListeners(){
        gui.getVaccinations().addActionListener(this);
        gui.getDeaths().addActionListener(this);
        
        gui.getVacBack().addActionListener(this);
        gui.getDeathBack().addActionListener(this);
        
        gui.getVacOption().addActionListener(this);
        gui.getDeathOption().addActionListener(this);
        
        gui.getVacSearch().addActionListener(this);
        gui.getDeathSearch().addActionListener(this); 
    }

    @Override
    public void actionPerformed(ActionEvent e){ 
        // Local variables for easy refrence                                    
        Object userInput = e.getSource();
        
        if(userInput == gui.getVaccinations()){
            gui.view(gui.getVacScreen());
        }
        else if (userInput == gui.getDeaths()){
            gui.view(gui.getDeathScreen());
        }
        else if (userInput == gui.getVacBack()){
            gui.view(gui.getMainScreen());
        }
        else if (userInput == gui.getDeathBack()){
            gui.view(gui.getMainScreen());
        }
        else if (userInput == gui.getVacOption()){
            gui.hideVacSubOptions();
            
            if(gui.getVacOption().getSelectedItem().equals("Age")){
                gui.getVacAgeLabel().setVisible(true);
                gui.getVacAge().setVisible(true);
            } else if (gui.getVacOption().getSelectedItem().equals("Race")){
                gui.getVacRaceLabel().setVisible(true);
                gui.getVacRace().setVisible(true);
            } else if (gui.getVacOption().getSelectedItem().equals("Gender")){
                gui.getVacGenderLabel().setVisible(true);
                gui.getVacGender().setVisible(true);
            } else if (gui.getVacOption().getSelectedItem().equals("Total")){
                gui.getBegDateLabel().setVisible(true);
                gui.getStartDate().setVisible(true);
                gui.getEndDateLabel().setVisible(true);
                gui.getEndDate().setVisible(true);
            }
        }
        else if (userInput == gui.getDeathOption()){
            gui.hideDeathSubOptions();
            
            if(gui.getDeathOption().getSelectedItem().equals("Age")){
                gui.getDeathAgeLabel().setVisible(true);
                gui.getDeathAge().setVisible(true);
            } else if (gui.getDeathOption().getSelectedItem().equals("Race")){
                gui.getDeathRaceLabel().setVisible(true);
                gui.getDeathRace().setVisible(true);
            } else if (gui.getDeathOption().getSelectedItem().equals("Gender")){
                gui.getDeathGenderLabel().setVisible(true);
                gui.getDeathGender().setVisible(true);
            } else if (gui.getDeathOption().getSelectedItem().equals("Total")){
                gui.getDateLabel().setVisible(true);
                gui.getDeathMonth().setVisible(true);
                gui.getDeathYear().setVisible(true);
            }
        }
        else if (userInput == gui.getVacSearch()){
            calcVacSearch();
        }
        else if (userInput == gui.getDeathSearch()){
            calcDeathSearch();
        }
    }
    
    private void calcVacSearch(){
        
    }
    
    private void calcDeathSearch(){
        
    }
    
}
