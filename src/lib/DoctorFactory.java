/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.ArrayList;

/**
 *
 * @author uafederez
 */
public abstract class DoctorFactory {
    /**
     * 
     * Creates a doctor object from the factory given the details as well as an
     * optional list of schedules. Schedules are optional because they may be 
     * assigned to the doctor on creation or otherwise assigned later on during
     * the program.
     * 
     * @param specialization    Doctor's specialization ("gastroentrology", "cardiology", etc.)
     * @param fname             Doctor's first name
     * @param lname             Doctor's last name
     * @param schedules         (Optional) List of schedules of this doctor, set to null if not yet available
     * @return                  A doctor of the proper specialization type with the schedules if given.
     **/
    public abstract Doctor createDoctor(String specialization, 
                                        String fname, 
                                        String lname, 
                                        ArrayList<Schedule> schedules);
}
