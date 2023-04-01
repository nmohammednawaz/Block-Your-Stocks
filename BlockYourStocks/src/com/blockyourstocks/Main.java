package com.blockyourstocks;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.blockyourstocks.entities.*;
import com.blockyourstocks.exceptions.*;
import com.blockyourstocks.services.*;
import com.blockyourstocks.utilities.*;


public class Main {
	
//	--------------------------------------- Admin Functionalities -------------------------------------------------
	
//	***************************************** Admin Login Method ********************************************
	public static void adminLogin(Scanner sc) throws InvalidDetailsException {

		System.out.println("Enter the username :");
		String adminUserName = sc.next();
		System.out.println("Enter the password :");
		String adminPassword = sc.next();
		if (adminUserName.equals(Admin.username) && adminPassword.equals(Admin.password)) {
			System.out.println("Hey..! Admin, Login successfull...");
		} else {
			throw new InvalidDetailsException("Invalid Credentials");
		}
	}
	
//	**************************************** Admin Add Stock Method *****************************************
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
	
//	************************************** Admin View All Stock Method **************************************
	public static void adminViewAllStocks(Map<Integer, Stock> stocks, StockService stockService)
			throws StockException {
		stockService.viewAllStocks(stocks);
	}
	
//	*************************************** Admin Delete Stock Method ***************************************
	public static void adminDeleteStock(Scanner sc, Map<Integer, Stock> stocks, StockService stockService)
			throws StockException {

		System.out.println("please enter the id of product to be deleted");
		int id = sc.nextInt();
		stockService.deleteStock(id, stocks);
	}
	
//	*************************************** Admin Update Stock Method ***************************************
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
	
//	****************************************** Admin View All Customers Method ******************************************
	public static void adminViewAllCustomers(Map<String, Customer> customers, CustomerService cusService)
			throws StockException {
		List<Customer> list = cusService.viewAllCustomers(customers);

		for (Customer c : list) {
			System.out.println(c);
		}
	}

//	************************************ Admin View All Transaction Method **********************************
	public static void adminViewAllTransactions(List<Transaction> transactions, TransactionService transactionService)
			throws TransactionException {
		List<Transaction> allTransactions = transactionService.viewAllTransactions(transactions);

		for (Transaction tr : allTransactions) {
			System.out.println(tr);
		}

	}
	
//	*************************************** Admin Functionality method **************************************
	private static void adminFunctionality(Scanner sc, Map<Integer, Stock> stocks, Map<String, Customer> customers,
			List<Transaction> transactions) throws InvalidDetailsException, StockException, TransactionException {
		
//		Caling Admin login Method
		adminLogin(sc);

		StockService stockService = new StockServiceImplement();
		CustomerService cusService = new CustomerServiceImplement();
		TransactionService transactionService = new TransactionServiceImplement();
		
		int choice = 0;
		try {
			do {
				System.out.println("Please select your preference");
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
					adminViewAllTransactions(transactions, transactionService);
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

//	====================================== End Of Admin Fucntionalities ===========================================
	
	
//	---------------------------------------- Customer Functionalities ---------------------------------------------
	
//	**************************************** Customer Register Method ****************************************
	public static void customerRegister(Scanner sc, Map<String, Customer> customers) throws DuplicateDataException {
		System.out.println("please enter the following details to Register");
		System.out.println("please enter the user name");
		String name = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		System.out.println("enter the address");
		String address = sc.next();
		System.out.println("Enter the email id");
		String email = sc.next();
		System.out.println("Enter the balance to be added into the wallet");
		double balance = sc.nextDouble();
		Customer cus = new Customer(balance, name, pass, address, email);

		CustomerService cusService = new CustomerServiceImplement();
		cusService.register(cus, customers);
		System.out.println("Registration Successfull...!");

	}
	
//	***************************************** Customer Login Method ******************************************
	public static void customerLogin(String email,String pass, Map<String, Customer> customers, CustomerService cusService)
			throws InvalidDetailsException {
		cusService.login(email, pass,customers);
		System.out.println("Customer has successfully login");

	}
	
//	************************************** Customer View All Stock Method ************************************
	public static void customerViewAllStocks(Map<Integer, Stock> stocks, StockService stockService)
			throws StockException {
		stockService.viewAllStocks(stocks);
	}

//	**************************************** Customer Buy Stock Method ***************************************
	public static String customerBuyStock(Scanner sc, String email, Map<Integer, Stock> stocks,
			Map<String, Customer> customers, List<Transaction> transactions, CustomerService cusService)
			throws InvalidDetailsException, StockException {
		System.out.println("Enter the Stock id");
		int id = sc.nextInt();
		System.out.println("enter the quantity you want to buy");
		int qty = sc.nextInt();
		cusService.buyStock(id, qty, email, stocks, customers, transactions);

		return "You have successfully bought the product";

	}
	
//	**************************************** Customer Add Money Method ***************************************
	public static String customerAddMoneyToWallet(Scanner sc, String email, Map<String, Customer> customers,
			CustomerService cusService) {
		System.out.println("please enter the amount");
		double money = sc.nextDouble();
		boolean added = cusService.addMoneyToWallet(money, email, customers);

		return "Amount of " + money + " successfully added to your wallet";
	}
	
//	**************************************** Customer view wallet Method *************************************
	public static double customerViewWalletBalance(String email, Map<String, Customer> customers,
			CustomerService cusService) {
		double walletBalance = cusService.viewWalletBalance(email, customers);
		return walletBalance;
	}
	
//	**************************************** Customer view profile Method ************************************
	public static void customerViewMyDetails(String email, Map<String, Customer> customers,
			CustomerService cusService) {
		Customer cus = cusService.viewCustomerDetails(email, customers);
		System.out.println("name : " + cus.getUsername());
		System.out.println("address : " + cus.getAddress());
		System.out.println("email : " + cus.getEmail());
		System.out.println("wallet balance : " + cus.getWalletBalance());
	}
	
//	********************************* Customer Transaction Details Method ************************************
	public static void customerViewCustomerTransactions(String email, List<Transaction> transactions,
			TransactionService trnsactionService) throws TransactionException {
		List<Transaction> myTransactions = trnsactionService.viewCustomerTransactions(email, transactions);

		for (Transaction tr : myTransactions) {
			System.out.println(tr);
		}
	}
	
//	************************************* Customer Functionality method **************************************
	public static void customerFunctionality(Scanner sc, Map<String, Customer> customers,
			Map<Integer, Stock> stocks, List<Transaction> transactions)
			throws InvalidDetailsException, TransactionException {

		CustomerService cusService = new CustomerServiceImplement();
		StockService stockService = new StockServiceImplement();
		TransactionService transactionService = new TransactionServiceImplement();

		// Customer login
		System.out.println("please enter the following details to login");
		System.out.println("please enter the email");
		String email = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		customerLogin(email,pass, customers, cusService);

		try {
			int choice = 0;
			do {
				System.out.println("Please select your preference");
				System.out.println("1. view all products");
				System.out.println("2. buy a product");
				System.out.println("3. add money to a wallet");
				System.out.println("4. view wallet balance");
				System.out.println("5. view my details");
				System.out.println("6. view my transactions");
				System.out.println("7. logout");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					customerViewAllStocks(stocks, stockService);
					break;
				case 2:
					String result = customerBuyStock(sc, email, stocks, customers, transactions, cusService);
					System.out.println(result);
					break;
				case 3:
					String moneyAdded = customerAddMoneyToWallet(sc, email, customers, cusService);
					System.out.println(moneyAdded);
					break;
				case 4:
					double walletBalance = customerViewWalletBalance(email, customers, cusService);
					System.out.println("Wallet balance is: " + walletBalance);
					break;
				case 5:
					customerViewMyDetails(email, customers, cusService);
					break;
				case 6:
					customerViewCustomerTransactions(email, transactions, transactionService);
					break;
				case 7:
					System.out.println("you have successsfully logout");
					break;
				default:
					System.out.println("invalid choice");
					break;
				}

			} while (choice <= 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
//	=================================== End Of Customer Functionalities Method =====================================
	
	
	
	
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
				System.out.println("Please enter your preference,");
				System.out.println("1. Admin \n2. Customer Login \n3. Customer Register \n0. for exit");
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					adminFunctionality(sc, stocks, customers, transactions);
					break;
				case 2:
					customerFunctionality(sc, customers, stocks, transactions);
					break;

				case 3:
					customerRegister(sc, customers);
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

//	============================================= End Of MAin Method ================================================
}
