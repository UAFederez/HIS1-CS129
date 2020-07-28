/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import lib.Doctor;
import lib.Schedule;

/**
 *
 * @author uafed
 */
public class ViewDoctorDatabase extends javax.swing.JFrame {

    private ArrayList<Doctor> doctors = null;
    
    /**
     * Creates new form ViewDoctorDatabase
     */
    public ViewDoctorDatabase() {
        initComponents();
        
        sortedSchedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listDoctors = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listRateTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        specializationTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sortedSchedTable = new javax.swing.JTable();

        setTitle("View Doctor Database");

        listDoctors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Specialization"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(listDoctors);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("List Doctors", jPanel1);

        listRateTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Specialization", "Consultation Rate"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(listRateTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("List wth Consultation Rates", jPanel4);

        jScrollPane2.setEnabled(false);

        specializationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Specialization", "Full Name"
            }
        ));
        specializationTable.setRowHeight(24);
        jScrollPane2.setViewportView(specializationTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("List By Specialization", jPanel2);

        sortedSchedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Day of The Week", "First Name", "Last Name", "Specialization", "Schedule Time"
            }
        ));
        sortedSchedTable.setEnabled(false);
        sortedSchedTable.setRowHeight(24);
        jScrollPane1.setViewportView(sortedSchedTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("List by Schedules Sorted", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void setDoctors(ArrayList<Doctor> doctors)
    {
        this.doctors = doctors;
    }
    
    public void refreshTables()
    {
        updateListDoctorsTable();
        updateConsultRateTable();
        updateSpecializationsTable();
        updateSortedSchedTable();
    }
    
    private void updateListDoctorsTable() {
        DefaultTableModel model = (DefaultTableModel) listDoctors.getModel();
        model.setRowCount(0);
        
        for(Doctor d : doctors)
        {   
            String dfname  = d.getFirstName();
            String dlname  = d.getLastName();
            String special = d.getSpecialization();
            
            model = (DefaultTableModel) listDoctors.getModel();
            model.insertRow(model.getRowCount(), new Object[]{dfname, dlname, special});
        }
        
    }
    
    private void updateConsultRateTable() {
        DefaultTableModel model = (DefaultTableModel) listRateTable.getModel();
        model.setRowCount(0);
        
        for(Doctor d : doctors)
        {
            model.insertRow(model.getRowCount(), new Object[]{d.getFirstName(), 
                                                              d.getLastName(),
                                                              d.getSpecialization(),
                                                              "PHP " + d.getConsultationRate()});
        }
    }
        
    private void updateSpecializationsTable()
    {
        Collections.sort(doctors, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor t, Doctor t1) {
                return t1.getSpecialization().compareToIgnoreCase(t.getSpecialization());
            }
        });
        
        DefaultTableModel tm = (DefaultTableModel) specializationTable.getModel();
        
        tm.setRowCount(0);
        
        String currentSpecialization  = "";
        String previousSpecialization = "";
        String doctorsList            = "";
        
        for(Doctor d : doctors) {
            currentSpecialization  = d.getSpecialization();
            Object[] row = new Object[]{ null, "Dr. " + d.getFirstName() + " " + d.getLastName() };
            
            if(!currentSpecialization.equals(previousSpecialization))
                row[0] = currentSpecialization;
            
            tm.addRow(row);
            previousSpecialization = currentSpecialization;
        }
        
        specializationTable.setModel(tm);
    }
    
    private void updateSortedSchedTable()
    {
        DefaultTableModel   tm        = (DefaultTableModel) sortedSchedTable.getModel();
        ArrayList<Schedule> schedules = new ArrayList<>();
        
        tm.setRowCount(0);
        
        // Populate the schedules array
        for(Doctor d : doctors) 
        {
            for(Schedule s : d.getSchedules())
                schedules.add(s);
        }
        
        Collections.sort(schedules);
        
        int currentDay  =  0;
        int previousDay = -1;
        
        String[] str = { 
            "Monday",   "Tuesday", "Wednesday", 
            "Thursday", "Friday",  "Saturday", 
            "Sunday"
        };
        
        for(Schedule s : schedules) {
            Doctor doc = s.getAssociatedDoctor();
            currentDay = s.getDay();
            
            Object[] row = new Object[]{
                null,
                doc.getFirstName(),
                doc.getLastName(),
                doc.getSpecialization(),
                
                s.getTimeFrom().getHourString() + ":" + s.getTimeFrom().getMinuteString() + " - " +
                s.getTimeTo().getHourString()   + ":" + s.getTimeTo().getMinuteString()
            };
            
            if(currentDay != previousDay)
                row[0] = str[currentDay];
            
            tm.addRow(row);
            
            previousDay = currentDay;
        }
        
        sortedSchedTable.setModel(tm);
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewDoctorDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewDoctorDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewDoctorDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewDoctorDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewDoctorDatabase().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable listDoctors;
    private javax.swing.JTable listRateTable;
    private javax.swing.JTable sortedSchedTable;
    private javax.swing.JTable specializationTable;
    // End of variables declaration//GEN-END:variables
}
