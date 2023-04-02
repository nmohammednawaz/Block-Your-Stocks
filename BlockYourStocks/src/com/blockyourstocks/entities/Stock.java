package com.blockyourstocks.entities;

import java.io.Serializable;
import java.util.Objects;

public class Stock implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int qty;
	private double price;
	private String stockType;

	public Stock() {
		super();
	}

	public Stock(String name, int qty, double price, String stocktype) {
		super();
		this.name = name.toUpperCase();
		this.qty = qty;
		this.price = price;
		this.stockType = stocktype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getstockType() {
		return stockType;
	}

	public void setstockType(String stockType) {
		this.stockType = stockType;
	}

	@Override
	public String toString() {
		return "Stock [name: " + name + ", quantity: " + qty + ", price: " + price + ", stockType: " + stockType
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(stockType, name, price, qty);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return Objects.equals(stockType, other.stockType) && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && qty == other.qty;
	}
}
