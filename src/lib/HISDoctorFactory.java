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
        if("gastroentrology".equalsIgnoreCase(specialization))
            doctor = new Gastroentrologist(fname, lname);
        else if("cardiology".equalsIgnoreCase(specialization))
            doctor = new Cardiologist(fname, lname);
        
        if(schedules != null)
        {
            for(Schedule s : schedules)
                doctor.addSchedule(s);
        }
        
        return doctor;
    }
}
