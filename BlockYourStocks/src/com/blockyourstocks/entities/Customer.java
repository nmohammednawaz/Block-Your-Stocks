package com.blockyourstocks.entities;

import java.io.Serializable;

public class Customer extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double walletBalance;

	public Customer(double walletBalance,String name, String username, String password, String address, String mobileNumber, String email, String ustatus) {
		super(name, username, password, address, mobileNumber, email, ustatus);
		this.walletBalance = walletBalance;
	}

	public double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}

	@Override
	public String toString() {
		return "Customer [Wallet Balance: " + getWalletBalance()
				+ ", Username: " + getUsername() + ", Address: " + getAddress() + ", Email ID: " + getEmail()
				+  "]";
	}


}
