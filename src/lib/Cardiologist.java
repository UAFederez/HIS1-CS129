/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

/**
 *
 * @author uafederez
 */
public class Cardiologist extends Doctor {

    public Cardiologist(String fName, String lName) {
        super(fName, lName);
    }


    @Override
    public String getSpecialization() {
        return "Cardiology";
    }

    @Override
    public boolean isDescriptionInKeywords(String key) {
        return ("heart chest blood pressure vessels "   + 
                "cardiovascular cholesterol hypertens " + 
                "coronary").contains(key.toLowerCase());
    }
    
}
