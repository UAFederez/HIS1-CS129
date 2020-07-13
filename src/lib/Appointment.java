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
public class Appointment {
    
    private final Patient  patient;
    private final Doctor   doctor;
    private final Schedule schedule;
    
    public Appointment(Patient patient, Doctor doctor, Schedule schedule)
    {
        this.patient  = patient;
        this.doctor   = doctor;
        this.schedule = schedule;
    }
    
    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Schedule getSchedule() {
        return schedule;
    }
}
