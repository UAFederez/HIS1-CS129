/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

/**
 *
 * @author uafederez
 */
public class Patient {
    private final String firstName;
    private final String lastName;
    
    // TODO: change these to proper types
    private final String birthday;        
    private final String gender;

    public Patient(String fname, String lname, String bday, String gender)
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

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }
        
}
