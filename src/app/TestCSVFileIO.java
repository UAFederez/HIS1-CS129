/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import lib.CSVScanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import lib.Cardiologist;
import lib.Doctor;
import lib.Schedule;
import lib.TimePoint;

/**
 *
 * @author uafederez
 */
public class TestCSVFileIO {
    
    static Random rand     = new Random();
    
    public static Doctor createDoctor(String type) {
        Doctor d = null;
        
        // Note: based on what I've read, this is essentially how DoctorFactory 
        //       will be implemented just w/o input values yet.
        if(type.equals("Cardiologist"))
            d = new Cardiologist("fname", "lname");
        
        return d;
    }
    
    /**
     * 
     * @param type Specialization of doctor to create
     * @return A doctor with the appropriate specialization
     */
    public static Doctor generateDoctor(String type) 
    {
        Doctor d = createDoctor(type);
        
        if(d != null)
        {
            d.computeRate();
            d.addSchedule(new Schedule(2, new TimePoint(9, 00), new TimePoint(12, 0), false));
            d.addSchedule(new Schedule(4, new TimePoint(9, 00), new TimePoint(12, 0), false));
        }
        
        return d;
    }
    
    public static void main(String[] args) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        
        // For now just generate 10 cardiologists
        for(int i = 0; i < 10; ++i)
        {
            Doctor d = generateDoctor("Cardiologist");
            doctors.add(d);
        }
        
        writeDoctorsToCSV(doctors);
        readDoctorsFromCSV("csvtest.csv");

    }
    
    // TODO: probably encapsulate this in a CSVWriter since we have a CSVScanner, but
    //       it works for now.
    public static void writeDoctorsToCSV(ArrayList<Doctor> doctors) 
    {
        File outputCSV = new File("csvtest.csv");
    
        try (PrintWriter writer = new PrintWriter(outputCSV)) 
        {
            for(Doctor d : doctors) 
            {
                /**
                 * TODO: This 0, or 1, means that it's either a DOCTOR or a SCHEDULE
                 *       respectively, useful for when it comes to reading the file 
                 *       (see readDoctorsFromCSV) though I'm not sure if this is 
                 *       the best solution
                **/      
                writer.println("0," + 
                               d.getFirstName() + "," + 
                               d.getLastName()  + "," +
                               d.getConsultationRate()      + "," + 
                               d.getSpecialization());
                
                for(Schedule sched : d.getSchedules())
                {
                    writer.println("1," + sched.getDay() + "," +
                                    sched.getTimeFrom().getHourString()   + ":" +
                                    sched.getTimeFrom().getMinuteString() + "," +
                                    sched.getTimeTo().getHourString()     + ":" +
                                    sched.getTimeTo().getMinuteString()   + "," +
                                    (sched.isByAppointment() ? 1 : 0));
                }
            }
            
            System.out.println("Finished writing to " + outputCSV.getName());
        } catch(FileNotFoundException e) 
        {
            System.err.println("Error: File not found " + e.getMessage());
        }
    }
    
    public static ArrayList<Doctor> readDoctorsFromCSV(String path)
    {
        ArrayList<Doctor> doctors = new ArrayList<>();
        
        try(CSVScanner scanner = new CSVScanner(new File(path), ",", "\"")) {
            
            while(scanner.hasNext())
            {
                // Is the current line a doctor ("0,...") or a schedule ("1,...")
                boolean isDoctor = (scanner.nextInt() == 0);
                
                if(isDoctor) 
                {
                    String firstName   = scanner.next();
                    String lastName    = scanner.next();
                    double consultRate = scanner.nextDouble();
                    
                    Doctor d = createDoctor(scanner.next());
                    d.setConsultationRate(consultRate);
                    
                    System.out.printf("%s (PHP %.2f fee)\n", "Reading info for Dr." + firstName + " " 
                                                             + lastName, consultRate);
                    doctors.add(d);
                } else 
                {
                    Doctor lastDoctorAdded = doctors.get(doctors.size() - 1);
                    
                    int dayOfWeek        = scanner.nextInt();
                    String timeFromStr[] = scanner.next().split(":");
                    String timeToStr[]   = scanner.next().split(":");
                    boolean isByAppt     = (scanner.nextInt() == 1);
                    
                    TimePoint timeFrom   = new TimePoint(Integer.parseInt(timeFromStr[0]), 
                                                         Integer.parseInt(timeFromStr[1]));
                    TimePoint timeTo     = new TimePoint(Integer.parseInt(timeToStr[0]), 
                                                         Integer.parseInt(timeToStr[1]));
                    
                    Schedule sched = new Schedule(dayOfWeek, timeFrom, timeTo, isByAppt);
                    
                    System.out.println("\tSchedule for Dr. " + 
                                       lastDoctorAdded.getFirstName() + " " +
                                       lastDoctorAdded.getLastName()  + " " +
                                       sched.toString() + (isByAppt ? "By appointment" : ""));
                    
                    lastDoctorAdded.addSchedule(sched);
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        return doctors;
    }
}
