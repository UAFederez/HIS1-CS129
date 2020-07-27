package lib;

import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author uafederez
 */
public abstract class Doctor {
    
    protected double consultationRate;
    private final String firstName;
    private final String lastName;
    private ArrayList<Schedule> schedules;
    
    private Account account;
    
    protected final Random randomGenerator = new Random();
    
    public Doctor(String fName, String lName)
    {
        this.firstName = fName;
        this.lastName  = lName;
        this.schedules = new ArrayList<>();
    }
    
    public Account getAccount()
    {
        return account;
    }
    
    public void setAccount(Account acc)
    {
        this.account = acc;
    }
    
    public double getConsultationRate() {
        return consultationRate;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void addSchedule(Schedule newSchedule) {
        newSchedule.setAssociatedDoctor(this);
        schedules.add(newSchedule);
    }
    
    // TODO: Should probably change this for removeSchedule(), addSchedule(), and modifySchedule()
    //       instead of just giving access to the entire array, but will work for now
    public final ArrayList<Schedule> getSchedules() {
        return schedules;
    }
    
    public void setConsultationRate(double newRate) {
        this.consultationRate = newRate;
    }
    
    public boolean equals(Doctor other) {
        return firstName.equals(other.firstName) &&
               lastName.equals(other.lastName)   &&
               getSpecialization().equals(other.getSpecialization());
    }
    
    public abstract void    computeRate();
    public abstract String  getSpecialization();
    public abstract boolean isDescriptionInKeywords(String key);
}
