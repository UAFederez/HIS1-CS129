/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.Date;

/**
 *
 * @author uafederez
 */
public class PatientInfo {
    private final String firstName;
    private final String lastName;
    
    // TODO: change these to proper types
    private final Date   birthday;        
    private final String gender;

    public PatientInfo(String fname, String lname, Date bday, String gender)
    {
        this.firstName = fname;
        this.lastName  = lname;
        this.birthday  = bday;
        this.gender    = gender;
    }
   
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }
        
}
