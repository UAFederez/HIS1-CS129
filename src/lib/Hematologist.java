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
public class Hematologist extends Doctor {

    public Hematologist(String fName, String lName) {
        super(fName, lName);
    }


    @Override
    public String getSpecialization() {
        return "Hematology";
    }

    @Override
    public boolean isDescriptionInKeywords(String key) {
        return ("hematologsy hematologist blood leukemia fever fatigue anemia headache " + 
                "diziness clot hemophilia bleeding nosebleed").contains(key.toLowerCase());
    }
    
}
