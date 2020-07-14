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
import lib.DoctorFactory;
import lib.HISDoctorFactory;
import lib.Schedule;
import lib.TimePoint;

/**
 *
 * @author uafederez
 */
public class TestCSVFileIO {
    
    static Random random     = new Random();
    
    public static final String[] SPECIALIZATIONS = {
        "Cardiology", 
        "Gastroentrology"
    };
    
    private static ArrayList<Doctor> createTestDoctors(String[] specializations)
    {
        DoctorFactory  factory        = new HISDoctorFactory();
        ArrayList<Doctor> testDoctors = new ArrayList<>();
        
        for(String spec : specializations)
        {
            Doctor doctor = factory.createDoctor(spec, "fname", "lname (" + spec + ")", null);
            
            int numSchedules = 2 + random.nextInt(3);
            for(int i = 0; i < numSchedules; ++i)
            {
                TimePoint from = new TimePoint(random.nextInt(23), random.nextInt(59));
                TimePoint to   = new TimePoint((from.getHour() + 3) % 24, from.getMinute());
                
                doctor.addSchedule(new Schedule(random.nextInt(6), from, to, true));
            }
            testDoctors.add(doctor);
        }
        
        return testDoctors;
    }
    
    public static void main(String[] args) {
        ArrayList<Doctor> doctors = createTestDoctors(SPECIALIZATIONS);
        
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
        DoctorFactory factory     = new HISDoctorFactory();
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
                    
                    Doctor d = factory.createDoctor(scanner.next(), firstName, lastName, null);
                    d.setConsultationRate(consultRate);
                    
                    //System.out.printf("%s (PHP %.2f fee)\n", "Reading info for Dr." + firstName + " " 
                    //                                       + lastName, consultRate);
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
                    
                    /**
                    System.out.println("\tSchedule for Dr. " + 
                                       lastDoctorAdded.getFirstName() + " " +
                                       lastDoctorAdded.getLastName()  + " " +
                                       sched.toString() + (isByAppt ? " By appointment" : ""));
                    **/
                    
                    lastDoctorAdded.addSchedule(sched);
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        return doctors;
    }
}
