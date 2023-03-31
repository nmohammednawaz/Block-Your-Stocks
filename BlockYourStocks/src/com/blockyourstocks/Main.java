package com.blockyourstocks;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.blockyourstocks.entities.Customer;
import com.blockyourstocks.entities.Stock;
import com.blockyourstocks.entities.Transaction;
import com.blockyourstocks.exceptions.InvalidDetailsException;
import com.blockyourstocks.exceptions.StockException;
import com.blockyourstocks.exceptions.TransactionException;
import com.blockyourstocks.services.CustomerService;
import com.blockyourstocks.services.CustomerServiceImplement;
import com.blockyourstocks.services.StockService;
import com.blockyourstocks.services.StockServiceImplement;
import com.blockyourstocks.services.TransactionService;
import com.blockyourstocks.services.TransactionServiceImplement;
import com.blockyourstocks.utilities.Admin;
import com.blockyourstocks.utilities.FileExists;
import com.blockyourstocks.utilities.IDGeneration;

public class Main {
	
//	------------------------------------------ Admin Functionalities --------------------------------------------
	
//	****************************************** Admin Login Method **********************************************
	public static void adminLogin(Scanner sc) throws InvalidDetailsException {

		System.out.println("Enter the username");
		String adminUserName = sc.next();
		System.out.println("Enter the password");
		String adminPassword = sc.next();
		if (adminUserName.equals(Admin.username) && adminPassword.equals(Admin.password)) {
			System.out.println("Hey..! Admin, Login successfull...");
		} else {
			throw new InvalidDetailsException("Invalid Credentials");
		}
	}
	
	
//	****************************************** Admin Add Stock Method ******************************************
	public static String adminAddStock(Scanner sc, Map<Integer, Stock> stocks, StockService stockService) {

		String str = null;
		System.out.println("please enter the stock details");
		System.out.println("Enter the stock name");
		String name = sc.next();
		int qty = 500;
		System.out.println("Enter the stock price");
		double price = sc.nextDouble();
		System.out.println("Enter the stock type");
		System.out.println("1. Common Stock \n2. Preferred Stock");
		String stockType = sc.next();

		Stock stock = new Stock(IDGeneration.generateId(stocks.size()), name, qty, price, stockType);

		str = stockService.addStock(stock, stocks);

		return str;

	}
	
	
//	****************************************** Admin View All Stock Method ******************************************
	public static void adminViewAllStocks(Map<Integer, Stock> stocks, StockService stockService)
			throws StockException {
		stockService.viewAllStocks(stocks);
	}
	
	
	
//	****************************************** Admin Delete Stock Method ******************************************
	public static void adminDeleteStock(Scanner sc, Map<Integer, Stock> stocks, StockService stockService)
			throws StockException {

		System.out.println("please enter the id of product to be deleted");
		int id = sc.nextInt();
		stockService.deleteStock(id, stocks);
	}
	
	
//	****************************************** Admin Update Stock Method *******************************************
	public static String adminUpdateStock(Scanner sc, Map<Integer, Stock> stocks, StockService stockService)
			throws StockException {
		String result = null;
		System.out.println("please enter the id of the stock which is to be updated");
		int id = sc.nextInt();
		System.out.println("Enter the updated details ");

		System.out.println("Enter the stock name");
		String name = sc.next();

		System.out.println("Enter the stock qty");
		int qty = sc.nextInt();

		System.out.println("Enter the stock price");
		double price = sc.nextDouble();

		System.out.println("Enter the stock type");
		System.out.println("1. Common Stock \n2. Preferred Stock");
		String stockType = sc.next();

		Stock update = new Stock(id, name, qty, price, stockType);

		result = stockService.updateStock(id, update, stocks);
		
		return result;
	}
	
	
//	****************************************** Admin View All Customers Method *******************************************
	public static void adminViewAllCustomers(Map<String, Customer> customers, CustomerService cusService)
			throws StockException {
		List<Customer> list = cusService.viewAllCustomers(customers);

		for (Customer c : list) {
			System.out.println(c);
		}
	}
	
	private static void adminFunctionality(Scanner sc, Map<Integer, Stock> stocks, Map<String, Customer> customers,
			List<Transaction> transactions) throws InvalidDetailsException, StockException, TransactionException {
		
//		Admin login
		adminLogin(sc);

		StockService stockService = new StockServiceImplement();
		CustomerService cusService = new CustomerServiceImplement();
		TransactionService trnsactionService = new TransactionServiceImplement();
		
		int choice = 0;
		try {
			do {
				System.out.println("Please select the preference");
				System.out.println("1. add the Stock");
				System.out.println("2. view all the stocks");
				System.out.println("3. delete the stock");
				System.out.println("4. update the stock");
				System.out.println("5. view all customers");
				System.out.println("6. view all transactions");
				System.out.println("7. log out");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					String added = adminAddStock(sc, stocks, stockService);
					System.out.println(added + " added successfully...!");
					break;
				case 2:
					adminViewAllStocks(stocks, stockService);
					break;
				case 3:
					adminDeleteStock(sc, stocks, stockService);
					break;
				case 4:
					String upt = adminUpdateStock(sc, stocks, stockService);
					System.out.println(upt);
					break;
				case 5:
					adminViewAllCustomers(customers, cusService);

					break;
				case 6:
//					adminViewAllTransactions(transactions, trnsactionService);
					break;
				case 7:
					System.out.println("admin has successfully logout");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}

			} while (choice <= 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	
	
//	------------------------------------------------- Main Method ---------------------------------------------------

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//file check
		Map<Integer, Stock> stocks = FileExists.stocksFile();
		Map<String, Customer> customers = FileExists.customersFile();
		List<Transaction> transactions = FileExists.transactionsFile();
		
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome to Block Your Stocks...!");
		try {
			int choice = 0;
			do {
				System.out.println("Please enter your preference, " + " '1' --> Admin, '2' --> Customer"
				+ ", '0' --> for exit");
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					adminFunctionality(sc, stocks, customers, transactions);
					break;
				case 2:
//					customerFunctionality(sc, customers, stocks, transactions);
					break;

				case 3:
//					customerSignup(sc, customers);
					break;

				case 0:
					System.out.println("successfully existed from the system");

					break;

				default:
					throw new IllegalArgumentException("Invalid Selection");
				}

			}
			while (choice != 0);

		}catch (Exception e) {
			System.out.println(e.getMessage());
		} 
	}

}
