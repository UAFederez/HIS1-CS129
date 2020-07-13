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
public class DoctorFactory {
    public Doctor create(String specialization, String fname, String lname)
    {
        Doctor doctor = null;
        
        if("gastroentrology".equalsIgnoreCase(specialization))
            doctor = new Gastroentrologist(fname, lname);
        else if("cardiology".equalsIgnoreCase(specialization))
            doctor = new Cardiologist(fname, lname);
        
        doctor.computeRate();
        
        return doctor;
    }
}
