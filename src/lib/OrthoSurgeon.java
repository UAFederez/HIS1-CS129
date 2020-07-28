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
public class OrthoSurgeon extends Doctor {

    public OrthoSurgeon(String fName, String lName) {
        super(fName, lName);
    }


    @Override
    public String getSpecialization() {
        return "Orthopedia Surgery";
    }

    @Override
    public boolean isDescriptionInKeywords(String key) {
        return ("ortho surgeon tunnel cubital carpal tennis elbow " + 
                "ligament meniscus").contains(key.toLowerCase());
    }
    
}
