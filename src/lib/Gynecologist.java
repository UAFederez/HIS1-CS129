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
public class Gynecologist extends Doctor {

    public Gynecologist(String fName, String lName) {
        super(fName, lName);
    }


    @Override
    public String getSpecialization() {
        return "Gynecology";
    }

    @Override
    public boolean isDescriptionInKeywords(String key) {
        return ("gynecologist obstetricians pregnancy vagina uterus" +  
                "born laparoscopy dysmenorrhea infertile").contains(key.toLowerCase());
    }
    
}
