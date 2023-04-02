package com.blockyourstocks.services;

import java.util.List;

import com.blockyourstocks.entities.*;
import com.blockyourstocks.exceptions.TransactionException;

public interface TransactionService {
	
	public List<Transaction> viewCustomerTransactions(String email, List<Transaction> transactions)throws TransactionException;
	
	public List<Transaction> viewAllTransactions(List<Transaction> transactions) throws TransactionException;
}
