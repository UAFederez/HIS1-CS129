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
public class Urologist extends Doctor {

    public Urologist(String fName, String lName) {
        super(fName, lName);
    }


    @Override
    public String getSpecialization() {
        return "Urology";
    }

    @Override
    public boolean isDescriptionInKeywords(String key) {
        return ("urology urine prostate bladder erectile urinary tract uti").contains(key.toLowerCase());
    }
    
}
