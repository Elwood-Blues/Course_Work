/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connecText;

import java.sql.PreparedStatement;

/**
 *
 * @author Peter Braband, Michael Ellison, Alexa Chiu, Grant Page, Abigal Reed
 */
public class user {
 
    // create variables here
    private String userName;
    private String password;
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    
    // accessors and mutators
    public String getUserName() {
        return userName;
    }
     
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String phoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String email() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setFirst(String firstName) {
        this.firstName = firstName;
    }
    
    public String firstName() {
        return firstName;
    }
    
    public void setLast(String lastName) {
        this.lastName = lastName;
    }
    
    public String getLast(){
        return this.lastName;
    }
}

