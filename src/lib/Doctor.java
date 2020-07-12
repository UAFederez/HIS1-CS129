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
    
    protected final Random randomGenerator = new Random();
    
    public Doctor(String fName, String lName)
    {
        this.firstName = fName;
        this.lastName  = lName;
        this.schedules = new ArrayList<>();
    }
    
    public double getRate() {
        return consultationRate;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void addSchedule(Schedule newSchedule) {
        schedules.add(newSchedule);
    }
    
    // TODO: Should probably change this for removeSchedule(), addSchedule(), and modifySchedule()
    //       instead of just giving access to the entire array, but will work for now
    public final ArrayList<Schedule> getSchedules() {
        return schedules;
    }
    
    public void setRate(double newRate) {
        this.consultationRate = newRate;
    }
    
    public abstract void    computeRate();
    public abstract String  getSpecialization();
}