/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

/**
 *
 * @author ashda
 */
public class GeneralDentist extends Doctor {

    public GeneralDentist(String fName, String lName) {
        super(fName, lName);
    }

    @Override
    public String getSpecialization() {
        return "General Dentistry";
    }

    @Override
    public boolean isDescriptionInKeywords(String key) {
        return ("dentist gum root canals teeth crowns" +  
                "fillings veneers bridges oral mouth").contains(key.toLowerCase());
    }

    
}
