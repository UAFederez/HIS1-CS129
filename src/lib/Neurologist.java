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
public class Neurologist extends Doctor {

    public Neurologist(String fName, String lName) {
        super(fName, lName);
    }


    @Override
    public String getSpecialization() {
        return "Neurology";
    }

    @Override
    public boolean isDescriptionInKeywords(String key) {
        return ("neuro brain alzheimers aneurysm epilepsy seizures " + 
                "spinal cord palsy tumors cerebral").contains(key.toLowerCase());
    }
    
}
