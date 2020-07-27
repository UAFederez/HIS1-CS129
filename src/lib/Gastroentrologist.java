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
public class Gastroentrologist extends Doctor {

    public Gastroentrologist(String fName, String lName) {
        super(fName, lName);
    }

    @Override
    public void computeRate() {
        consultationRate = (450 + randomGenerator.nextInt(700 - 450)) * (1 + 0.12);
    }

    @Override
    public String getSpecialization() {
        return "Gastroentrology";
    }

    @Override
    public boolean isDescriptionInKeywords(String key) {
       return ("gastro stomach liver colon intestine"   +  
                "bowel").contains(key.toLowerCase());
    }
    
}
