package com.blockyourstocks.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.blockyourstocks.entities.*;
import com.blockyourstocks.exceptions.*;

public class CustomerServiceImplement implements CustomerService{

	@Override
	public void register(Customer cus, Map<String, Customer> customers) throws DuplicateDataException {

		if (customers.containsKey(cus.getEmail())) {
			throw new DuplicateDataException("Customer already exists , please login");
		} else {

			customers.put(cus.getEmail(), cus);
		}

	}

	@Override
	public boolean login(String email,String password, Map<String, Customer> customers) throws InvalidDetailsException {

		if (customers.containsKey(email) ) {
			
			if(customers.get(email).getPassword().equals(password)) {
				return true;
			}
			else {
				throw new InvalidDetailsException("Invalid Credentials");
			}
			
		} else {
			throw new InvalidDetailsException("you have not sign up yet, please register");
		}

	}

	@Override
	public boolean buyStock(String name, int qty, String email, Map<String, Stock> stocks,
			Map<String, Customer> customers, List<Transaction> transactions)
			throws InvalidDetailsException, StockException {

		if (stocks.size() == 0)
			throw new StockException("Stock list is empty");

		if (stocks.containsKey(name)) {

			Stock stock = stocks.get(name);

			if (stock.getQty() >= qty) {

				Customer cus = customers.get(email);
				if(cus.getUserStatus().equals("Inactive")) {
					System.out.println("Oops...!\nUnfortunately you cannot buy a stock since your account is Inactive..!");
					return false;
				}

				double buyingPrice = qty * stock.getPrice();

				if (cus.getWalletBalance() >= buyingPrice) {
					cus.setWalletBalance(cus.getWalletBalance() - buyingPrice);

					stock.setQty(stock.getQty() - qty);

					stocks.put(name, stock);

					Transaction tr = new Transaction(cus.getUsername(), email,stock.getName(), qty, stock.getPrice(),
							stock.getPrice() * qty, LocalDate.now());

					transactions.add(tr);

				} else {
					throw new InvalidDetailsException("wallet balance is not sufficient");
				}

			} else {
				throw new InvalidDetailsException("Stock quantity is not suffiecient");
			}

		} else {
			throw new InvalidDetailsException("Stock not available with name: " + name);
		}

		return false;
	}
	
	@Override
	public boolean sellStock(String name, int qty, String email, Map<String, Stock> stocks,
			Map<String, Customer> customers, List<Transaction> transactions)
			throws InvalidDetailsException, StockException {

		if (stocks.size() == 0)
			throw new StockException("Stock list is empty");

		if (stocks.containsKey(name)) {

			Stock stock = stocks.get(name);
			Customer cus = customers.get(email);
			double sellingPrice = qty * stock.getPrice();
			
			cus.setWalletBalance(cus.getWalletBalance() + sellingPrice);

			stock.setQty(stock.getQty() + qty);

			stocks.put(name, stock);

			Transaction tr = new Transaction(cus.getUsername(), email,stock.getName(), qty, stock.getPrice(),
							stock.getPrice() * qty, LocalDate.now());

			transactions.add(tr);
		} else {
			throw new InvalidDetailsException("Stock not available with name: " + name);
		}

		return false;
	}

	@Override
	public boolean addMoneyToWallet(double amount, String email, Map<String, Customer> customers) {
		// TODO Auto-generated method stub

		Customer cus = customers.get(email);

		cus.setWalletBalance(cus.getWalletBalance() + amount);

		customers.put(email, cus);

		return true;
	}

	@Override
	public double viewWalletBalance(String email, Map<String, Customer> customers) {
		// TODO Auto-generated method stub

		Customer cus = customers.get(email);

		return cus.getWalletBalance();
	}

	@Override
	public Customer viewCustomerDetails(String email, Map<String, Customer> customers) {

		if (customers.containsKey(email)) {

			return customers.get(email);

		}

		return null;
	}

	@Override
	public List<Customer> viewAllCustomers(Map<String, Customer> customers) throws StockException {
		// TODO Auto-generated method stub
		List<Customer> list = null;

		if (customers != null && customers.size() > 0) {
			Collection<Customer> coll = customers.values();
			list = new ArrayList<>(coll);
		} else {
			throw new StockException("Customer list is empty");
		}

		return list;
	}

}
