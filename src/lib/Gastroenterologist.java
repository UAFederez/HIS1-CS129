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
public class Gastroenterologist extends Doctor {

    public Gastroenterologist(String fName, String lName) {
        super(fName, lName);
    }


    @Override
    public String getSpecialization() {
        return "Gastroenterology";
    }

    @Override
    public boolean isDescriptionInKeywords(String key) {
       return ("gastro stomach liver colon intestine"   +  
                "bowel digestion digestive").contains(key.toLowerCase());
    }
    
}
