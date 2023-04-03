package com.blockyourstocks.entities;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String username;
	private String password;
	private String address;
	private String mobileNumber;
	private String email;
	private String uStatus;

	public User() {
		super();
	}

	public User(String name, String username, String password, String address, String mobileNumber, String email, String ustatus) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.uStatus = ustatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public String getAddress() {
		return address;
	}
	
	public String getUserStatus() {
		return uStatus; 
	}
	
	public void setUSerStatus(String uStatus) {
		this.uStatus = uStatus;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getNum() {
		return mobileNumber;
	}
	public void setNum(String mobNum) {
		this.mobileNumber = mobNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [name: " + name + ", username: " + username + ", password: " + password + ", address: " + address + "mobile number: " + mobileNumber + ", email: " + email
				+ ", Account Status: "+ uStatus + "]";
	}

}
