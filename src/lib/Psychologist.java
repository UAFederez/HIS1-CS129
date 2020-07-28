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
public class Psychologist extends Doctor {

    public Psychologist(String fName, String lName) {
        super(fName, lName);
    }


    @Override
    public String getSpecialization() {
        return "Psychology";
    }

    @Override
    public boolean isDescriptionInKeywords(String key) {
        return ("psycholog anxiety panic depression obsessive compulsive ocd " + 
                "bipolar mood personality ptsd post traumatic stress schizophrenia").contains(key.toLowerCase());
    }
    
}
