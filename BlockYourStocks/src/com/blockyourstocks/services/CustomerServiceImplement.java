package com.blockyourstocks.services;

import java.util.List;
import java.util.Map;

import com.blockyourstocks.entities.Customer;
import com.blockyourstocks.entities.Stock;
import com.blockyourstocks.entities.Transaction;
import com.blockyourstocks.exceptions.DuplicateDataException;
import com.blockyourstocks.exceptions.InvalidDetailsException;
import com.blockyourstocks.exceptions.StockException;

public class CustomerServiceImplement implements CustomerService{

	@Override
	public boolean login(String email, String password, Map<String, Customer> customers)
			throws InvalidDetailsException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void register(Customer cus, Map<String, Customer> customers) throws DuplicateDataException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean buyStock(int id, int qty, String email, Map<Integer, Stock> stocks, Map<String, Customer> customers,
			List<Transaction> transactions) throws InvalidDetailsException, StockException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addMoneyToWallet(double amount, String email, Map<String, Customer> customers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double viewWalletBalance(String email, Map<String, Customer> customers) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Customer viewCustomerDetails(String email, Map<String, Customer> customers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> viewAllCustomers(Map<String, Customer> customers) throws StockException {
		// TODO Auto-generated method stub
		return null;
	}

}
