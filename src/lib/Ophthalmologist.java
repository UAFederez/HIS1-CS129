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
public class Ophthalmologist extends Doctor {

    public Ophthalmologist(String fName, String lName) {
        super(fName, lName);
    }


    @Override
    public String getSpecialization() {
        return "Ophthalmology";
    }

    @Override
    public boolean isDescriptionInKeywords(String key) {
        return ("opthalmologist eye retinopathy cataract retina vision " +
                "glaucoma").contains(key.toLowerCase());
    }
    
}
