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
public class GeneralPractitioner extends Doctor {

    public GeneralPractitioner(String fName, String lName) {
        super(fName, lName);
    }


    @Override
    public String getSpecialization() {
        return "General Practice";
    }

    @Override
    public boolean isDescriptionInKeywords(String key) {
        return ("general health any").contains(key.toLowerCase());
    }
    
}
