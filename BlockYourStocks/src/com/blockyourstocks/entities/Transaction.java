package com.blockyourstocks.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String email;
	private String stockName;
	private int qty;
	private double price;
	private double total;
	private LocalDate dt;
	
	public Transaction() {
		
	}


	public String getpName() {
		return stockName;
	}


	public void setpName(String sName) {
		this.stockName = sName;
	}


	public Transaction(String username, String email, String stockName, int qty, double price, double total,
			LocalDate dt) {
		super();
		this.username = username;
		this.email = email;
		this.stockName = stockName;
		this.qty = qty;
		this.price = price;
		this.total = total;
		this.dt = dt;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public LocalDate getDt() {
		return dt;
	}

	public void setDt(LocalDate dt) {
		this.dt = dt;
	}


	@Override
	public String toString() {
		return "Transaction [username: " + username + ", email: " + email + ", stockName: "
				+ stockName + ", qty: " + qty + ", price: " + price + ", total: " + total + ", date: " + dt + "]";
	}

}
