/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.patient;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lib.Doctor;

/**
 *
 * @author uafed
 */
public class PatientRequestAppointment extends javax.swing.JPanel {

    private final ArrayList<Doctor> doctors;
    private Doctor selectedDoctor;
    /**
     * Creates new form PatientRequestAppointment
     */
    public PatientRequestAppointment(ArrayList<Doctor> doctors) {
        initComponents();
        
        this.doctors = doctors;
        searchDoctor1.setDoctors(doctors);
        
        searchDoctor1.getResultsTable().getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent lse) {
                    selectedDoctor = searchDoctor1.getSelectedDoctor();
                    reqApptBtn.setEnabled(selectedDoctor != null);
                }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchDoctor1 = new app.SearchDoctorPanel();
        reqApptBtn = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(770, 640));

        reqApptBtn.setText("Request Appointment");
        reqApptBtn.setEnabled(false);
        reqApptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reqApptBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(searchDoctor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reqApptBtn)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(searchDoctor1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reqApptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void reqApptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reqApptBtnActionPerformed
        
    }//GEN-LAST:event_reqApptBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton reqApptBtn;
    private app.SearchDoctorPanel searchDoctor1;
    // End of variables declaration//GEN-END:variables
}
