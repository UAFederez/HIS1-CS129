/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.admin.FormAddDoctor;
import app.doctor.DocManageAppointments;
import app.patient.PatientRequestAppointment;
import app.patient.PatientViewAppointments;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import lib.*;

/**
 *
 * @author uafed
 */
public class MainMenu extends javax.swing.JFrame {

    private static final SimpleDateFormat  dateFormat   = new SimpleDateFormat("yyyy-MM-dd");
    
    private Account currentLoggedInAcc = null;
    
    ArrayList<Account>     accountData;
    ArrayList<Doctor>      doctorData;
    ArrayList<PatientInfo> patientData;
    ArrayList<Appointment> appointmentsData;
    
    // JFrame Forms (Patient)
    SearchDoctorGUI           searchDoctorForm  = new SearchDoctorGUI();
    PatientRequestAppointment pReqApptForm      = new PatientRequestAppointment();
    PatientViewAppointments   pViewApptForm     = new PatientViewAppointments();
    
    // JFrame Forms (Admin)
    FormAddDoctor             addDoctorForm     = new FormAddDoctor();
    
    // JFrame Forms (Doctor)
    DocManageAppointments     manageApptsForm   = new DocManageAppointments();
    
    /**
     * Creates new form PatientMainMenu
     */
    public MainMenu() {
        initComponents();
        
    }
    
    public void setAccount(Account loggedIn)
    {
        this.currentLoggedInAcc = loggedIn;
        accountNameLabel.setText(currentLoggedInAcc.getUserName());
        currentDateLabel.setText(dateFormat.format(new Date()));
        
        // Set current viewing patient (for view appointments module)
        
        switch(currentLoggedInAcc.getPermission())
        {
            case Account.ADMIN:
            {
                patientOptionsPanel.setVisible(false);
                adminOptionsPanel.setVisible(true);
                doctorOptionsPanel.setVisible(false);
                
                addDoctorForm.setDoctors(doctorData);
                
                setTitle("Admin Main Menu");
            }
            break;
            case Account.DOCTOR:
            {
                patientOptionsPanel.setVisible(false);
                adminOptionsPanel.setVisible(false);
                doctorOptionsPanel.setVisible(true);
                
                manageApptsForm.setAppointments(appointmentsData);
                
                for(Doctor d : doctorData)
                {
                    String name = d.getFirstName() + " " + d.getLastName();
                    
                    if(name.equals(currentLoggedInAcc.getName()))
                        manageApptsForm.getAppointmentsPanel().setDoctorFilter(d);
                }
                manageApptsForm.getAppointmentsPanel().refreshTable(); 
                
                setTitle("Doctor Main Menu");
            }
            break;
            case Account.PATIENT:
            {
                System.out.printf("PATIENT\n");
                for(PatientInfo p : patientData)
                {
                    String name = p.getFirstName() + " " + p.getLastName();
                    
                    System.out.printf("comparing %s to %s\n", name, currentLoggedInAcc.getName());
                    if(currentLoggedInAcc.getName().equals(name))
                    {
                        System.out.println("found PATIENT match!!");
                        pReqApptForm.setCurrentPatient(p);
                    }
                }
                
                pViewApptForm.setAccountFilter(currentLoggedInAcc.getName());
                
                patientOptionsPanel.setVisible(true);
                adminOptionsPanel.setVisible(false);
                doctorOptionsPanel.setVisible(false);
                
                setTitle("Patient Main Menu");
            }
            break;
        }
        pack();
        setLocationRelativeTo(null);
    }
    
    // Functions to Link the data from the files
    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctorData = doctors;
        pReqApptForm.setDoctors(doctorData);
        searchDoctorForm.setDoctors(doctorData);
    }
    
    public void setAccounts(ArrayList<Account> accounts) {
        this.accountData = accounts;
        addDoctorForm.setAccounts(accounts);
    }
    
    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointmentsData = appointments;
        pViewApptForm.setAppointments(appointments);
        pReqApptForm.setAppointments(appointments);
    }
    
    public void setPatients(ArrayList<PatientInfo> patients){
        this.patientData = patients;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        patientOptionsPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        accountNameLabel = new javax.swing.JLabel();
        currentDateLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        doctorOptionsPanel = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        adminOptionsPanel = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Patient Main Menu");

        patientOptionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("What do you want to do?"));

        jButton1.setText("Book For A Consultation");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reqConsultationBtnAction(evt);
            }
        });

        jButton2.setText("View My Appointments");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientViewApptsAction(evt);
            }
        });

        jButton4.setText("View Doctors' Information");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDoctorInfoAction(evt);
            }
        });

        javax.swing.GroupLayout patientOptionsPanelLayout = new javax.swing.GroupLayout(patientOptionsPanel);
        patientOptionsPanel.setLayout(patientOptionsPanelLayout);
        patientOptionsPanelLayout.setHorizontalGroup(
            patientOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patientOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(patientOptionsPanelLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(patientOptionsPanelLayout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        patientOptionsPanelLayout.setVerticalGroup(
            patientOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patientOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Logged in as: ");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Current Date:");

        accountNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        accountNameLabel.setText("jLabel3");

        currentDateLabel.setForeground(new java.awt.Color(255, 255, 255));
        currentDateLabel.setText("jLabel3");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Hospital Information System");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(accountNameLabel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentDateLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(accountNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(currentDateLabel))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        doctorOptionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("What do you want to do?"));

        jButton5.setText("Manage My Appointments");
        jButton5.setToolTipText("");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageApptsAction(evt);
            }
        });

        jButton8.setText("View Doctors' Information");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDoctorInfoAction(evt);
            }
        });

        javax.swing.GroupLayout doctorOptionsPanelLayout = new javax.swing.GroupLayout(doctorOptionsPanel);
        doctorOptionsPanel.setLayout(doctorOptionsPanelLayout);
        doctorOptionsPanelLayout.setHorizontalGroup(
            doctorOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doctorOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        doctorOptionsPanelLayout.setVerticalGroup(
            doctorOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doctorOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(doctorOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        adminOptionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("What do you want to do?"));

        jButton10.setText("Add Doctor to Database");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDoctorAction(evt);
            }
        });

        jButton11.setText("View Doctors' Information");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDoctorInfoAction(evt);
            }
        });

        jButton12.setText("Search for A Doctor");

        javax.swing.GroupLayout adminOptionsPanelLayout = new javax.swing.GroupLayout(adminOptionsPanel);
        adminOptionsPanel.setLayout(adminOptionsPanelLayout);
        adminOptionsPanelLayout.setHorizontalGroup(
            adminOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(adminOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adminOptionsPanelLayout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(adminOptionsPanelLayout.createSequentialGroup()
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        adminOptionsPanelLayout.setVerticalGroup(
            adminOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(adminOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton12)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(patientOptionsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(doctorOptionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(adminOptionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(patientOptionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(doctorOptionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(adminOptionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void viewDoctorInfoAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDoctorInfoAction
        searchDoctorForm.getSearchPanel().refreshResultsTable();
        searchDoctorForm.setVisible(true);
    }//GEN-LAST:event_viewDoctorInfoAction

    private void reqConsultationBtnAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reqConsultationBtnAction
        pReqApptForm.getSearchPanel().refreshResultsTable();
        pReqApptForm.setVisible(true);
    }//GEN-LAST:event_reqConsultationBtnAction

    private void patientViewApptsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientViewApptsAction
        pViewApptForm.setVisible(true);
        pViewApptForm.refreshTable();
    }//GEN-LAST:event_patientViewApptsAction

    private void addDoctorAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDoctorAction
        addDoctorForm.setVisible(true);
    }//GEN-LAST:event_addDoctorAction

    private void manageApptsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageApptsAction
        manageApptsForm.getAppointmentsPanel().refreshTable();
        manageApptsForm.setVisible(true);
    }//GEN-LAST:event_manageApptsAction

    
    // TODO: probably encapsulate this in a CSVWriter since we have a CSVScanner, but
    //       it works for now.
    private static void writeDoctorsToCSV(ArrayList<Doctor> doctors) 
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
    
    private static ArrayList<Doctor> readDoctorsFromCSV(String path)
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
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accountNameLabel;
    private javax.swing.JPanel adminOptionsPanel;
    private javax.swing.JLabel currentDateLabel;
    private javax.swing.JPanel doctorOptionsPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel patientOptionsPanel;
    // End of variables declaration//GEN-END:variables
}
