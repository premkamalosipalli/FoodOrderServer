package com.booking.food_Order.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    private long userId;
    private String firstName = null;
    private String lastName = null;
    private String userName = null; 
    private String role = null;
    private String emailId = null;
    private String phoneNumber = null;
    private String password = null;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getUserId() {
        return userId;
    }


    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "user_name", nullable = false)
    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "role", nullable = false)
    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }
    
    @Column(name = "email_id", nullable = false)
    public String getEmailId() {
        return emailId;
    }


    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Column(name = "phone_number", nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }    

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public User(long userId, String firstName, String lastName, String userName, String role, String emailId,
    String phoneNumber, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.role = role;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


    @Override
    public String toString() {
        return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
                + userName + ", role=" + role + ", emailId=" + emailId + ", phoneNumber=" + phoneNumber + ", password="
                + password + "]";
    }

    public User() {
    }

    
}
