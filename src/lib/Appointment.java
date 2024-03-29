/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.Date;

/**
 *
 * @author uafederez
 */
public class Appointment {
    
    private final PatientInfo  patient;
    private final Doctor       doctor;
    private final Schedule     schedule;
    private final String       additionalNote;
    private Date               apptDate;
    private TimePoint          timeStart;

    public Appointment(PatientInfo patient, Doctor doctor, Schedule schedule, String note, Date date)
    {
        this.patient        = patient;
        this.doctor         = doctor;
        this.schedule       = schedule;
        this.additionalNote = note;
        this.apptDate       = date;
    }
    
    public Date getDate()
    {
        return apptDate;
    }
    
    public TimePoint getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(TimePoint timeStart) {
        this.timeStart = timeStart;
    }
    
    
    public void setDate(Date newDate)
    {
        this.apptDate = newDate;
    }
    
    public String getNote()
    {
        return additionalNote;
    }
    
    public PatientInfo getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Schedule getSchedule() {
        return schedule;
    }
}
