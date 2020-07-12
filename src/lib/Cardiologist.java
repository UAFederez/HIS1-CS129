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
public class Cardiologist extends Doctor {

    public Cardiologist(String fName, String lName) {
        super(fName, lName);
    }

    @Override
    public void computeRate() {
        int min = 450;
        int max = 900;
        
        consultationRate = (min + randomGenerator.nextInt(max - min)) * (1 + 0.12);
    }

    @Override
    public String getSpecialization() {
        return "Cardiologist";
    }
    
}
