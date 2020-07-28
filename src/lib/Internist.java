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
public class Internist extends Doctor {

    public Internist(String fName, String lName) {
        super(fName, lName);
    }


    @Override
    public String getSpecialization() {
        return "Internal Medicine";
    }

    @Override
    public boolean isDescriptionInKeywords(String key) {
        return ("intern diabetes asthma bronchitis " +
                "hypertension blood pressure cholesterol").contains(key.toLowerCase());
    }
    
}
