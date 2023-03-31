package com.blockyourstocks.entities;

import java.util.Objects;

public class Stock {
	private int id;
	private String name;
	private int qty;
	private double price;
	private String stockType;

	public Stock() {
		super();
	}

	public Stock(int id, String name, int qty, double price, String stocktype) {
		super();
		this.id = id;
		this.name = name;
		this.qty = qty;
		this.price = price;
		this.stockType = stocktype;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "Stock [id=" + id + ", name=" + name + ", qty=" + qty + ", price=" + price + ", stockType=" + stockType
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(stockType, id, name, price, qty);
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
		return Objects.equals(stockType, other.stockType) && id == other.id && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && qty == other.qty;
	}
}
