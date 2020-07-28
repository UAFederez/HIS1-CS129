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
public class Pediatrician extends Doctor {

    public Pediatrician(String fName, String lName) {
        super(fName, lName);
    }


    @Override
    public String getSpecialization() {
        return "Pediatrics";
    }

    @Override
    public boolean isDescriptionInKeywords(String key) {
        return ("pediatrician asthma diptheria leukemia measles mumps" + 
                "fever down's polio chickenpox").contains(key.toLowerCase());
    }
    
}
