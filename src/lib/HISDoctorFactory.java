/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.ArrayList;

/**
 *  Concrete DoctorFactory subclass
 * @author uafederez
 */
public class HISDoctorFactory extends DoctorFactory {
    Doctor doctor = null;
    
    @Override
    public Doctor createDoctor(String specialization, 
                               String fname, 
                               String lname,
                               ArrayList<Schedule> schedules)
    {   
        if("gastroenterology".equalsIgnoreCase(specialization))
            doctor = new Gastroenterologist(fname, lname);
        else if("cardiology".equalsIgnoreCase(specialization))
            doctor = new Cardiologist(fname, lname);
        else if("general dentistry".equalsIgnoreCase(specialization))
            doctor = new GeneralDentist(fname, lname);
        else if("general practice".equalsIgnoreCase(specialization))
            doctor = new GeneralPractitioner(fname, lname);
        else if("gynecology".equalsIgnoreCase(specialization))
            doctor = new Gynecologist(fname, lname);
        else if("hematology".equalsIgnoreCase(specialization))
            doctor = new Hematologist(fname, lname);
        else if("internal medicine".equalsIgnoreCase(specialization))
            doctor = new Internist(fname, lname);
        else if("nephrology".equalsIgnoreCase(specialization))
            doctor = new Nephrologist(fname, lname);
        else if("neurology".equalsIgnoreCase(specialization))
            doctor = new Neurologist(fname, lname);
        else if("ophthalmology".equalsIgnoreCase(specialization))
            doctor = new Ophthalmologist(fname, lname);
        else if("orthopedia surgery".equalsIgnoreCase(specialization))
            doctor = new OrthoSurgeon(fname, lname);
        else if("pediatrics".equalsIgnoreCase(specialization))
            doctor = new Pediatrician(fname, lname);
        else if("psychology".equalsIgnoreCase(specialization))
            doctor = new Psychologist(fname, lname);
        else if("urology".equalsIgnoreCase(specialization))
            doctor = new Urologist(fname, lname);
        
        if(schedules != null)
        {
            for(Schedule s : schedules)
                doctor.addSchedule(s);
        }
        
        return doctor;
    }
}
