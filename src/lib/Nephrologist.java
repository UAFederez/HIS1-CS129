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
public class Nephrologist extends Doctor {

    public Nephrologist(String fName, String lName) {
        super(fName, lName);
    }


    @Override
    public String getSpecialization() {
        return "Nephrology";
    }

    @Override
    public boolean isDescriptionInKeywords(String key) {
        return ("nephrolog kidney stones ").contains(key.toLowerCase());
    }
    
}
