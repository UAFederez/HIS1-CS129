/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import lib.*;

/**
 *
 * @author uafederez
 */
public class TestAppointmentCLI {
    
    private static final int MAX_APPTS_PER_TIME_SLOT = 3;
    private static final Random random = new Random();
    
    
    private static final SimpleDateFormat  dateFormat   = new SimpleDateFormat("yyyy-MM-dd");
    private static final ArrayList<Doctor> doctors      = TestCSVFileIO.readDoctorsFromCSV("csvtest.csv");
    private static ArrayList<Appointment>  appointments = readAppointmentsFromCSV("appointments.csv", doctors);
    //private static ArrayList<Appointment>  appointments = new ArrayList<>();
    
    /**
     * TODO: IMPORTANT! Should we consider the specific day? I mean if we, for example,
     *       3 appointments for some Doctors 9AM slot on Monday, that doesn't take into
     *       account the specific date of that Monday. It will turn out that the Doctor's
     *       Monday appointments will only be 3 in total ever. Need your opinions on this
     **/
    public static void main(String[] args)
    {
        boolean repeat = true;
        while(repeat)
        {
            Scanner input = new Scanner(System.in);
            System.out.println("Hello!");

            // Step 1: Enter patient details
            String[] name   = { "PatientFName", "PatientLName" };
            // See Date's constructors for mm, dd, yy initialization
            Date birthday   = new Date();   
            String gender   = "[TestGender]";
            PatientInfo patient = new PatientInfo(name[0], name[1], birthday, gender);

            // Step 2: Select a Specialization
            for(int i = 0; i < TestCSVFileIO.SPECIALIZATIONS.length; ++i)
                System.out.printf("    [%d] %s\n", i, TestCSVFileIO.SPECIALIZATIONS[i]);
            System.out.print("Select specialization: ");
            int spec = input.nextInt();

            ArrayList<Doctor> matchingDoctors = new ArrayList<>();

            // Step 3: Select a doctor with said specialization
            for(int i = 0, j = 0; i < doctors.size(); ++i)
            {
                Doctor currentDoc       = doctors.get(i);
                boolean hasMatchingSpec = currentDoc.getSpecialization().equalsIgnoreCase(TestCSVFileIO.SPECIALIZATIONS[spec]);

                if(hasMatchingSpec)
                {
                    System.out.printf("    [%d] Dr. %s %s\n", j++, currentDoc.getFirstName(),
                                                                   currentDoc.getLastName());
                    matchingDoctors.add(currentDoc);
                }
            }
            System.out.print("Select a Doctor: ");
            
            // Step 4: Select a schedule from that doctor
            Doctor selectedDoctor         = matchingDoctors.get(input.nextInt());
            ArrayList<Schedule> schedules = selectedDoctor.getSchedules();

            for(int i = 0; i < schedules.size(); ++i)
                System.out.printf("    [%d] %s\n", i, schedules.get(i).toString());
            System.out.print("Select a Schedule: ");
            Schedule schedule = schedules.get(input.nextInt());

            // Step 5: Check if schedules are full?
            Appointment newAppt      = new Appointment(patient, selectedDoctor, schedule, "hello", new Date());
            boolean hasAvailableSlot = true;
            int filledSlots          = 0;

            for(Appointment appt : appointments)
            {
                if(newAppt.getDoctor().equals(appt.getDoctor()) &&
                   newAppt.getSchedule().equals(appt.getSchedule()))
                {
                    System.out.printf("    Patient(%s, %s) with Dr(%s, %s) at %s\n", 
                                        appt.getPatient().getFirstName(),
                                        appt.getPatient().getLastName(),
                                        appt.getDoctor().getFirstName(),
                                        appt.getDoctor().getLastName(),
                                        appt.getSchedule().toString());
                    
                    ++filledSlots;

                    if(filledSlots == MAX_APPTS_PER_TIME_SLOT)
                    {
                        hasAvailableSlot = false;
                        break;
                    }
                }
            }

            if(hasAvailableSlot)
            {
                System.err.println("Successfully setup an appointment!");
                appointments.add(newAppt);
            }
            else
                System.err.println("Sorry, no more slots left for sched: " + schedule.toString());
            
            System.out.print("Repeat? (y/n): ");
            repeat = (input.next().equalsIgnoreCase("y"));
        }
        
        writeAppointmentsToCSV(appointments);
    }
    
    /**
     * 
     * @param path      The file path name of the CSV file of the appointments
     * @param doctors   The ArrayList<Doctor> to cross-reference for the schedule, MUST be valid
     * @return 
     */
    private static ArrayList<Appointment> readAppointmentsFromCSV(String path, ArrayList<Doctor> doctors)
    {
        DoctorFactory factory   = new HISDoctorFactory();
        
        ArrayList<Appointment> appointments = new ArrayList<>();
        
        File inputFile          = new File(path);
        
        System.out.println("Existing appointments in: " + path);
        try(CSVScanner inputCSV = new CSVScanner(inputFile))
        {
            while(inputCSV.hasNext())
            {
                String patientFname   = inputCSV.next();
                String patientLname   = inputCSV.next();
                Date   birthday       = dateFormat.parse(inputCSV.next());
                String gender         = inputCSV.next();
                PatientInfo patient       = new PatientInfo(patientFname, patientLname, birthday, gender);
                
                String doctorFName    = inputCSV.next();
                String doctorLName    = inputCSV.next();
                String specialization = inputCSV.next();
                
                Doctor doctorInCSV    = factory.createDoctor(specialization, doctorFName, doctorLName, null);
                Schedule schedInCSV   = new Schedule(inputCSV.next());
                
                Doctor   assocDoctor  = null;
                Schedule assocSched   = null;
                
                System.out.println(schedInCSV.toString());
                
                for(Doctor d : doctors)
                {
                    if(d.equals(doctorInCSV))
                    {
                        assocDoctor = d;
                        break;
                    }
                }
                
                for(Schedule s : assocDoctor.getSchedules())
                {
                    if(s.equals(schedInCSV))
                    {
                        assocSched = s;
                        break;
                    }
                }
                
                appointments.add(new Appointment(patient, assocDoctor, assocSched, "hello", new Date()));
            }
        } catch(IOException | ParseException e)
        {
            System.err.println(e.getMessage());
        }
        System.out.println("====================================================");
        
        return appointments;
    }
    
    private static void writeAppointmentsToCSV(ArrayList<Appointment> appointments)
    { 
        File outputFile = new File("appointments.csv");
        try(PrintWriter outputCSV = new PrintWriter(outputFile))
        {
            for(Appointment appt : appointments)
            {
                PatientInfo  p = appt.getPatient();
                Doctor   d = appt.getDoctor();
                Schedule s = appt.getSchedule();
                
                String csvSched = s.getDay() + " " + s.getTimeFrom().getHourString()   + ":" +
                                                     s.getTimeFrom().getMinuteString() + "-" +
                                                     s.getTimeTo().getHourString()     + ":" +
                                                     s.getTimeTo().getMinuteString();
                                               
                outputCSV.printf("%s,%s,\"%s\",%s,%s,%s,%s,%s\n", 
                                  p.getFirstName(), p.getLastName(), dateFormat.format(p.getBirthday()),       
                                  p.getSex(),         d.getFirstName(), d.getLastName(), 
                                  d.getSpecialization(), csvSched);
                System.out.printf("%s,%s,\"%s\",%s,%s,%s,%s,%s\n", 
                                  p.getFirstName(), p.getLastName(), p.getBirthday(),       p.getSex(),
                                  d.getFirstName(), d.getLastName(), d.getSpecialization(), csvSched);
            }
            
            System.out.println("Appointments have been written to " + outputFile.getName());
        } catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
