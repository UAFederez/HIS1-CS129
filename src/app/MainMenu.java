/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import lib.CSVScanner;
import lib.Doctor;
import lib.DoctorFactory;
import lib.HISDoctorFactory;
import lib.Schedule;
import lib.TimePoint;

/**
 *
 * @author uafed
 */
public class MainMenu extends javax.swing.JFrame {

    ArrayList<Doctor> doctorData   = readDoctorsFromCSV("csvtest.csv");
    SearchDoctorGUI   searchDoctor = new SearchDoctorGUI();
    
    /**
     * Creates new form PatientMainMenu
     */
    public MainMenu() {
        initComponents();
        
        searchDoctor.setDoctors(doctorData);
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
        jButton7 = new javax.swing.JButton();
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

        jButton2.setText("View My Appointments");

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

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

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

        jButton7.setText("View Consultation History");

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
                .addGroup(doctorOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(doctorOptionsPanelLayout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(doctorOptionsPanelLayout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        doctorOptionsPanelLayout.setVerticalGroup(
            doctorOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doctorOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(doctorOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        adminOptionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("What do you want to do?"));

        jButton10.setText("Add Doctor to Database");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(patientOptionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doctorOptionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminOptionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void viewDoctorInfoAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDoctorInfoAction
        searchDoctor.setVisible(true);
    }//GEN-LAST:event_viewDoctorInfoAction

    
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
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel patientOptionsPanel;
    // End of variables declaration//GEN-END:variables
}