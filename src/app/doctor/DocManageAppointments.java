/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.doctor;

import app.MainGUI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lib.Appointment;
import lib.Schedule;
import lib.TimePoint;

/**
 *
 * @author UAFederez
 */
public class DocManageAppointments extends javax.swing.JFrame {

    private static final SimpleDateFormat sdf        = new SimpleDateFormat("MMM-d-yyyy");
    private ArrayList<Appointment> appointments;
    private Appointment selectedAppt;
    
    /**
     * Creates new form DocManageAppointments
     */
    public DocManageAppointments() {
        initComponents();
        
        viewAppointments1.getAppointmentsTable().getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent lse) {
                    selectedAppt = viewAppointments1.getSelectedAppointment();
                    
                    updateLists();
                    markAsDoneBtn.setEnabled(selectedAppt != null);
                }
        });
        
        scheduleList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                updateDatesList();
                
                if(selectedAppt != null)
                {
                    TimePoint tFrom = selectedAppt.getSchedule().getTimeFrom();
                    TimePoint tTo   = selectedAppt.getSchedule().getTimeTo();
                    SpinnerNumberModel hourModel   = new SpinnerNumberModel(tFrom.getHour(),   tFrom.getHour(),   tTo.getHour(), 1);
                    SpinnerNumberModel minuteModel = new SpinnerNumberModel(tFrom.getMinute(), tFrom.getMinute(), tTo.getMinute(), 1);

                    fromHour.setModel(hourModel);
                    fromMinute.setModel(minuteModel);

                    setTimeBtn.setEnabled(scheduleList.getSelectedIndex() != -1);
                }
            }
        });
        
        dateList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                try {
                    rescheduleBtn.setEnabled(dateList.getSelectedIndex() != -1 &&
                            !sdf.parse(dateList.getSelectedValue()).equals(selectedAppt.getDate()));
                    
                } catch (ParseException ex) {
                    Logger.getLogger(DocManageAppointments.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public ViewAppointments getAppointmentsPanel() {
        return viewAppointments1;
    }
    
    public void updateLists()
    {
        updateSchedulesList();
        updateDatesList();
    }
    
    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
        viewAppointments1.setAppointments(appointments);
    }
    
    private void updateSchedulesList()
    {
        // Update the schedules JList
        DefaultListModel schedules = new DefaultListModel();
        
        if(selectedAppt != null)
        {
            for(Schedule s : viewAppointments1.getDoctorFilter().getSchedules())
                schedules.addElement(s.toString());
        }
        
        scheduleList.setModel(schedules);
        scheduleList.setSelectedIndex(0);
    }
    
    private void updateDatesList()
    {
        // Update the dates jList
        int selectedIndex         = scheduleList.getSelectedIndex();
        DefaultListModel dates    = new DefaultListModel();
        
        if(selectedIndex != -1 && selectedAppt != null)
        {
            Schedule selectedSchedule = viewAppointments1.getDoctorFilter().getSchedules().get(selectedIndex);

            Date[]   dateOptions   = new Date[4];
            Date     dateToday     = new Date();
            Calendar dateIncrement = Calendar.getInstance();


            for(int i = 0, j = 0; i < 30 && j < 4; ++i)
            {
                dateIncrement.add(Calendar.DATE, 1);     // Increment calendar by one day

                // -2 because DAY_OF_WEEK is Sun - 1, Sat 7 while our date system
                // is Monday - 0, Sunday - 6
                if(dateIncrement.get(Calendar.DAY_OF_WEEK) - 2 == selectedSchedule.getDay())
                {
                    dateOptions[j++] = dateIncrement.getTime();
                    dates.addElement(sdf.format(dateIncrement.getTime()));
                }
            }
        }
        
        dateList.setModel(dates);
        dateList.setSelectedIndex(0);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        markAsDoneBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dateList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        scheduleList = new javax.swing.JList<>();
        rescheduleBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fromHour = new javax.swing.JSpinner();
        fromMinute = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        ampmSelect = new javax.swing.JComboBox<>();
        setTimeBtn = new javax.swing.JButton();
        viewAppointments1 = new app.doctor.ViewAppointments();

        markAsDoneBtn.setText("Mark As Done");
        markAsDoneBtn.setEnabled(false);
        markAsDoneBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markAsDoneBtnAction(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Enter a new Schedule"));

        jScrollPane1.setViewportView(dateList);

        jScrollPane2.setViewportView(scheduleList);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane1, jScrollPane2});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        rescheduleBtn.setText("Reschedule");
        rescheduleBtn.setEnabled(false);
        rescheduleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rescheduleBtnActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Set Starting Time"));

        jLabel1.setText("Hour");

        jLabel2.setText("Minute");

        ampmSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ampmSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fromHour, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fromMinute, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fromHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fromMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ampmSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        setTimeBtn.setText("Set Time");
        setTimeBtn.setEnabled(false);
        setTimeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setTimeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewAppointments1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(setTimeBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rescheduleBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(markAsDoneBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(viewAppointments1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(markAsDoneBtn)
                    .addComponent(rescheduleBtn)
                    .addComponent(setTimeBtn))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void markAsDoneBtnAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_markAsDoneBtnAction
        int schedIndex = appointments.indexOf(selectedAppt);
        appointments.remove(schedIndex);
        MainGUI.writeAppointmentsToCSV(appointments);
    }//GEN-LAST:event_markAsDoneBtnAction

    private void rescheduleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rescheduleBtnActionPerformed
        try {
            Date newDate = sdf.parse(dateList.getSelectedValue());
            selectedAppt.setDate(newDate);
            MainGUI.writeAppointmentsToCSV(appointments);
            
            viewAppointments1.refreshTable();
            JOptionPane.showMessageDialog(null, "Appointment Sucessfully Rescheduled");
        } catch (ParseException ex) {
            Logger.getLogger(DocManageAppointments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rescheduleBtnActionPerformed

    private void setTimeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setTimeBtnActionPerformed
        TimePoint timeStart = new TimePoint(Integer.parseInt(fromHour.getValue().toString()),
                                            Integer.parseInt(fromMinute.getValue().toString()));
        selectedAppt.setTimeStart(timeStart);
        MainGUI.writeAppointmentsToCSV(appointments);
            
        viewAppointments1.refreshTable();
        JOptionPane.showMessageDialog(null, "Appointment Start Time Sucessfully Rescheduled");
    }//GEN-LAST:event_setTimeBtnActionPerformed

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
            java.util.logging.Logger.getLogger(DocManageAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DocManageAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DocManageAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DocManageAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DocManageAppointments().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ampmSelect;
    private javax.swing.JList<String> dateList;
    private javax.swing.JSpinner fromHour;
    private javax.swing.JSpinner fromMinute;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton markAsDoneBtn;
    private javax.swing.JButton rescheduleBtn;
    private javax.swing.JList<String> scheduleList;
    private javax.swing.JButton setTimeBtn;
    private app.doctor.ViewAppointments viewAppointments1;
    // End of variables declaration//GEN-END:variables
}
