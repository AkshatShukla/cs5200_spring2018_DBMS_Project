package com.dbms.project.moovi.data.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    @Temporal(value = TemporalType.DATE)
    private Date dob;
    
    @OneToMany(mappedBy = "AUser")
    @JsonIgnore
    private List<Address> userAddresses;
    
    @OneToMany(mappedBy = "PUser")
    @JsonIgnore
    private List<Phone> userPhoneNumbers;

	public List<Phone> getUserPhoneNumbers() {
		return userPhoneNumbers;
	}

	public void setUserPhoneNumbers(List<Phone> userPhoneNumbers) {
		this.userPhoneNumbers = userPhoneNumbers;
	}

	public List<Address> getUserAddresses() {
		return userAddresses;
	}

	public void setUserAddresses(List<Address> userAddresses) {
		this.userAddresses = userAddresses;
	}

	public User() {
        super();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
