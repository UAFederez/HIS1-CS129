/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

/**
 *
 * @author uafed
 */
public class Account {
    public static final int PATIENT = 1;
    public static final int DOCTOR  = 2;
    public static final int ADMIN   = 3;
    
    // Name of the person
    private String name;
    
    private String username;
    private String password;
    private int    permission;
    
    public Account(String name, String uname, String pass, int perm)
    {
        this.name       = name;
        this.username   = uname;
        this.password   = pass;
        this.permission = perm;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getUserName()
    {
        return username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public int getPermission()
    {
        return permission;
    }
    
    public boolean validateLoginCredentials(String uname, String pass)
    {
        return username.equals(uname) && password.equals(pass);
    }
    
    public boolean equals(Account other)
    {
        return username.equals(other.username) && 
               password.equals(other.password);
    }
}
