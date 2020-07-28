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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import lib.Account;
import lib.Appointment;
import lib.CSVScanner;
import lib.Doctor;
import lib.DoctorFactory;
import lib.HISDoctorFactory;
import lib.PatientInfo;
import lib.Schedule;
import lib.TimePoint;

/**
 *
 * @author uafed
 */
public class MainGUI extends javax.swing.JFrame {
    
    private static final SimpleDateFormat  dateFormat   = new SimpleDateFormat("yyyy-MM-dd");
    
    ArrayList<Account>     accounts         = readAccountsFromCSV("accounts.csv");
    ArrayList<PatientInfo> patientData      = readPatientsFromCSV("patients.csv");
    ArrayList<Doctor>      doctorData       = readDoctorsFromCSV("csvtest.csv");
    ArrayList<Appointment> appointmentsData = readAppointmentsFromCSV("appointments.csv", doctorData);
    
    MainMenu mainMenu           = new MainMenu();
    
    /**
     * TODO:
     * Load accounts from file
     * Load doctors from file
     * Load patients from file
     * match accounts with doctors and patients
     */
    
    private static ArrayList<Account> readAccountsFromCSV(String path)
    {
        ArrayList<Account> accts = new ArrayList<>();
        File inputFile = new File(path);
        
        System.out.println("Existing appointments in: " + path);
        try(CSVScanner inputCSV = new CSVScanner(inputFile))
        {
            while(inputCSV.hasNext())
            {
                int    permission = inputCSV.nextInt();
                String name       = inputCSV.next();
                String username   = inputCSV.next();
                String password   = inputCSV.next();
                
                Account acc = new Account(name, username, password, permission);
                accts.add(acc);
            }
        } catch(IOException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        return accts;
    }
    
    private static ArrayList<PatientInfo> readPatientsFromCSV(String path)
    {
        ArrayList<PatientInfo> patients = new ArrayList<>();
        File inputFile = new File(path);
        
        System.out.println("Existing patients in: " + path);
        try(CSVScanner inputCSV = new CSVScanner(inputFile))
        {
            while(inputCSV.hasNext())
            {
                String firstName = inputCSV.next();
                String lastName  = inputCSV.next();
                Date   birthday  = dateFormat.parse(inputCSV.next());
                String gender    = inputCSV.next();
                
                patients.add(new PatientInfo(firstName, lastName, birthday, gender));
            }
        } catch(IOException | ParseException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        return patients;
    }
    
    public static void writeAppointmentsToCSV(ArrayList<Appointment> appointments)
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
                                               
                outputCSV.printf("%s,%s,\"%s\",%s,%s,%s,%s,%s,%s,%s,\"%s\"\n", 
                                  p.getFirstName(), 
                                  p.getLastName(), 
                                  dateFormat.format(p.getBirthday()),       
                                  p.getSex(),         
                                  d.getFirstName(), 
                                  d.getLastName(), 
                                  d.getSpecialization(), 
                                  csvSched,
                                  dateFormat.format(appt.getDate()),
                                  (appt.getTimeStart() == null ? "null" : appt.getTimeStart().getHour() + ":" + appt.getTimeStart().getMinute()),
                                  appt.getNote());
                System.out.printf("%s,%s,\"%s\",%s,%s,%s,%s,%s,%s,%s,\"%s\"\n", 
                                  p.getFirstName(), 
                                  p.getLastName(), 
                                  dateFormat.format(p.getBirthday()),       
                                  p.getSex(),         
                                  d.getFirstName(), 
                                  d.getLastName(), 
                                  d.getSpecialization(), 
                                  csvSched,
                                  dateFormat.format(appt.getDate()),
                                  (appt.getTimeStart() == null ? "null" : appt.getTimeStart().getHour() + ":" + appt.getTimeStart().getMinute()),
                                  appt.getNote());
            }
            
            System.out.println("Appointments have been written to " + outputFile.getName());
        } catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
    public static void writeAccountsToCSV(ArrayList<Account> accounts, String path)
    {
        File outputFile = new File(path);
        
        try(PrintWriter outputCSV = new PrintWriter(outputFile))
        {
            for(Account acc : accounts)
            {
                if(acc.getPermission() != Account.ADMIN)
                {
                    outputCSV.printf("%d,%s,%s,%s\n", acc.getPermission(),
                                                      acc.getName(),
                                                      acc.getUserName(),
                                                      acc.getPassword());
                }
            }
        } catch(IOException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    private static void writePatientsToCSV(ArrayList<PatientInfo> patients, String path)
    {
        File outputFile = new File(path);
        
        try(PrintWriter outputCSV = new PrintWriter(outputFile))
        {
            for(PatientInfo info : patients)
            {
                outputCSV.printf("%s,%s,%s,%s\n", info.getFirstName(), info.getLastName(),
                                                  dateFormat.format(info.getBirthday()),
                                                  info.getSex());
            }
        } catch(IOException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Creates new form MainGUI
     */
    public MainGUI() {
        initComponents();
        
        accounts.add(new Account("admin", "admin", "1234", Account.ADMIN));
        
        mainMenu.setPatients(patientData);
        mainMenu.setDoctors(doctorData);
        mainMenu.setAppointments(appointmentsData);
        mainMenu.setAccounts(accounts);
        dateSpinner.setModel(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, dateFormat.toPattern()));
        
        /**
        DoctorFactory fac = new HISDoctorFactory();
        
        String[] specs = new String[] {
            "Gastroenterology",
            "Cardiology",
            "General Dentistry",
            "General Practice",
            "Gynecology",
            "Hematology",
            "Internal Medicine",
            "Nephrology",
            "Neurology",
            "Ophthalmology",
            "Orthopedia Surgery",
            "Pediatrics",
            "Psychology",
            "Urology"
        };
        
        ArrayList<Doctor> testFac = new ArrayList<>();
        
        for(String spec : specs)
        {
            Doctor d = fac.createDoctor(spec, "fname", "laname", null);
            testFac.add(d);
            if(!d.getSpecialization().equals(spec))
                System.err.println("Invalid spec: " + d.getSpecialization() + " " + spec);
        }
        
        System.out.println("done");
        **/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        loginPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        loginUserName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        loginPassword = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        registerPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        patientFName = new javax.swing.JTextField();
        patientLName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        patientUserName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        patientPassword = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        sexComboBox = new javax.swing.JComboBox<>();
        dateSpinner = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login / Sign up");

        loginPanel.setToolTipText("");

        jLabel1.setText("Username:");

        jLabel2.setText("Password:");

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonAction(evt);
            }
        });

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loginUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(loginPassword)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(loginUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Log in Existing Account", loginPanel);

        jLabel3.setText("First name: ");

        jLabel4.setText("Last name:");

        jLabel5.setText("Birthday:");

        jLabel6.setText("Username:");

        jLabel7.setText("Password");

        jButton2.setText("Sign Up");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpPatientAction(evt);
            }
        });

        jLabel8.setText("General Information:");

        jLabel9.setText("Account Login Details");

        jLabel10.setText("Sex:");

        sexComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        sexComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexComboBoxActionPerformed(evt);
            }
        });

        jLabel11.setText("(YYYY-MM-DD)");

        javax.swing.GroupLayout registerPanelLayout = new javax.swing.GroupLayout(registerPanel);
        registerPanel.setLayout(registerPanelLayout);
        registerPanelLayout.setHorizontalGroup(
            registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(patientUserName)
                            .addComponent(patientPassword)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateSpinner)
                            .addComponent(patientFName)
                            .addComponent(patientLName)
                            .addGroup(registerPanelLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 155, Short.MAX_VALUE))
                            .addComponent(sexComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        registerPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel4, jLabel5});

        registerPanelLayout.setVerticalGroup(
            registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(patientFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(patientLName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sexComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(patientUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(patientPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Register New Patient", registerPanel);

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Hospital Information System");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private boolean validateSignUpInput()
    {
        boolean isValidFirstName = !patientFName.getText().isEmpty();
        boolean isValidLastName  = !patientLName.getText().isEmpty();
        boolean isValidUsername  = !patientUserName.getText().isEmpty();
        
        String pass              = Arrays.toString(patientPassword.getPassword());
        boolean isValidPassword  = !pass.isEmpty();
        
        for(char ch : patientFName.getText().toCharArray())
        {
            if(Character.isDigit(ch))
            {
                isValidFirstName = false;
                break;
            }
        }
        
        for(char ch : patientLName.getText().toCharArray())
        {
            if(Character.isDigit(ch))
            {
                isValidLastName = false;
                break;
            }
        }
        
        return isValidFirstName && isValidLastName &&
               isValidUsername  && isValidPassword;
    }
    
    private void signUpPatientAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpPatientAction
        if(validateSignUpInput())
        {
            Account newAcc = new Account(patientFName.getText() + " " + patientLName.getText(),
                                         patientUserName.getText(), 
                                         new String(patientPassword.getPassword()), 
                                         Account.PATIENT);

            boolean isAccountAlreadyExisting = false;

            for(Account acc : accounts)
            {
                if(newAcc.equals(acc))
                {
                    isAccountAlreadyExisting = true;
                    break;
                }
            }

            if(isAccountAlreadyExisting)
            {
                JOptionPane.showMessageDialog(null, "Account already exists!");
            } else
            {
                accounts.add(newAcc);
                writeAccountsToCSV(accounts, "accounts.csv");

                try {
                    patientData.add(new PatientInfo(patientFName.getText(),
                            patientLName.getText(),
                            dateFormat.parse(dateFormat.format(dateSpinner.getValue())),
                            sexComboBox.getSelectedItem().toString()));
                } catch (ParseException ex) {
                    Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                writePatientsToCSV(patientData, "patients.csv");

                JOptionPane.showMessageDialog(null, "Patient account successfully created!");
            }
        } else
            JOptionPane.showMessageDialog(null, "Error: please recheck your input!");
    }//GEN-LAST:event_signUpPatientAction

    private void loginButtonAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonAction
        
        
        Account matchingAccount = null;
        for(Account acc : accounts)
        {
            if(acc.validateLoginCredentials(loginUserName.getText(), 
                                            new String(loginPassword.getPassword())))
            {
                matchingAccount = acc;
                System.out.println(acc.toString());
                System.out.println("found match");
                break;
            }
        }
        
        if(matchingAccount != null)
        {
            setVisible(false);
            mainMenu.setVisible(true);
            mainMenu.setAccount(matchingAccount);
        } else
        {
            JOptionPane.showMessageDialog(null, "Invalid account!");
        }
    }//GEN-LAST:event_loginButtonAction

    private void sexComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sexComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }
    
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
                PatientInfo patient   = new PatientInfo(patientFname, patientLname, birthday, gender);
                
                String doctorFName    = inputCSV.next();
                String doctorLName    = inputCSV.next();
                String specialization = inputCSV.next();
                
                Doctor doctorInCSV    = factory.createDoctor(specialization, doctorFName, doctorLName, null);
                Schedule schedInCSV   = new Schedule(inputCSV.next());
                
                Doctor   assocDoctor  = null;
                Schedule assocSched   = null;
                
                Date   apptDate       = dateFormat.parse(inputCSV.next());
                
                String timeStartStr   = inputCSV.next();
                TimePoint timeStart   = null;
                
                if(!timeStartStr.equals("null"))
                {
                    String[] timeString = timeStartStr.split(":");
                    timeStart = new TimePoint(Integer.parseInt(timeString[0]), 
                                              Integer.parseInt(timeString[1]));
                }
                String note           = inputCSV.next();
                
                
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
                
                Appointment appt = new Appointment(patient, assocDoctor, assocSched, note, apptDate);
                appt.setTimeStart(timeStart);
                
                appointments.add(appt);
            }
        } catch(IOException | ParseException e)
        {
            System.err.println(e.getMessage());
        }
        System.out.println("====================================================");
        
        return appointments;
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
                    
                    TimePoint timeFrom   = new TimePoint(Integer.parseInt(timeFromStr[0]), 
                                                         Integer.parseInt(timeFromStr[1]));
                    TimePoint timeTo     = new TimePoint(Integer.parseInt(timeToStr[0]), 
                                                         Integer.parseInt(timeToStr[1]));
                    
                    Schedule sched = new Schedule(dayOfWeek, timeFrom, timeTo);
                    
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
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner dateSpinner;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPasswordField loginPassword;
    private javax.swing.JTextField loginUserName;
    private javax.swing.JTextField patientFName;
    private javax.swing.JTextField patientLName;
    private javax.swing.JPasswordField patientPassword;
    private javax.swing.JTextField patientUserName;
    private javax.swing.JPanel registerPanel;
    private javax.swing.JComboBox<String> sexComboBox;
    // End of variables declaration//GEN-END:variables
}
