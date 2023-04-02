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
	
//	**************************************** Admin Login Method *********************************************
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
	
//	*************************************** Admin Add Stock Method ******************************************
	public static String adminAddStock(Scanner sc, Map<String, Stock> stocks, StockService stockService) {

		String str = null;
		System.out.println("please enter the stock details");
		System.out.println("Enter the stock name");
		String name = sc.next();
		int qty = 500;
		System.out.println("Enter the stock price");
		double price = sc.nextDouble();
		System.out.println("Enter the stock type");
		System.out.println("1. Common Stock \n2. Preferred Stock");
		int stockTypeSelect = sc.nextInt();
		String stockType = null;
		if(stockTypeSelect == 1) {
			stockType = "Common Stock";
		}else {
			stockType = "Preferred Stock";
		}
		
		Stock stock = new Stock(name, qty, price, stockType);

		str = stockService.addStock(stock, stocks);

		return str;

	}
	
//	************************************ Admin View All Stock Method ****************************************
	public static void adminViewAllStocks(Map<String, Stock> stocks, StockService stockService)
			throws StockException {
		stockService.viewAllStocks(stocks);
	}
	
//	************************************* Admin Delete Stock Method *****************************************
	public static void adminDeleteStock(Scanner sc, Map<String, Stock> stocks, StockService stockService)
			throws StockException {

		System.out.println("please enter the id of stock to be deleted");
		String name = sc.next();
		stockService.deleteStock(name.toUpperCase(), stocks);
	}
	
//	************************************* Admin Update Stock Method *****************************************
	public static String adminUpdateStock(Scanner sc, Map<String, Stock> stocks, StockService stockService)
			throws StockException {
		String result = null;

		System.out.println("please enter the name of the stock which is to be updated");
		String name = sc.next();
		System.out.println("Enter the updated details ");

		System.out.println("Enter the stock qty");
		int qty = sc.nextInt();

		System.out.println("Enter the stock price");
		double price = sc.nextDouble();

		System.out.println("Enter the stock type");
		System.out.println("1. Common \n2. Preferred");
		String stockType = sc.next();

		Stock update = new Stock(name, qty, price, stockType);

		result = stockService.updateStock(name.toUpperCase(), update, stocks);
		
		return result;
	}
	
//	********************************** Admin View All Customers Method **************************************
	public static void adminViewAllCustomers(Map<String, Customer> customers, CustomerService cusService)
			throws StockException {
		List<Customer> list = cusService.viewAllCustomers(customers);

		for (Customer c : list) {
			System.out.println(c);
		}
	}

//	********************************* Admin View All Transaction Method *************************************
	public static void adminViewAllTransactions(List<Transaction> transactions, TransactionService transactionService)
			throws TransactionException {
		List<Transaction> allTransactions = transactionService.viewAllTransactions(transactions);

		for (Transaction tr : allTransactions) {
			System.out.println(tr);
		}

	}
	
//	************************************ Admin Functionality method *****************************************
	private static void adminFunctionality(Scanner sc, Map<String, Stock> stocks, Map<String, Customer> customers,
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
					System.out.println(added);
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
//					throw new IllegalArgumentException("Unexpected value: " + choice);
					System.out.println("Unexpected value: " + choice);
					break;
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
		System.out.println("Fisrt name:");
		String fName = sc.next();
		System.out.println("Last name:");
		String lName = sc.next();
		System.out.println("Username:");
		String username = sc.next();
		System.out.println("Password:");
		String pass = sc.next();
		System.out.println("Address:");
		String address = sc.next();
		System.out.println("Mobile Number:");
		String mobNum = sc.next()
;		System.out.println("Email Id:");
		String email = sc.next();
		System.out.println("Enter the balance to be added into the wallet");
		double balance = sc.nextDouble();
		String userStatus = "Active";
		String name = fName + " " + lName;
		Customer cus = new Customer(balance, name, username, pass, address, mobNum, email, userStatus);

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
	public static void customerViewAllStocks(Map<String, Stock> stocks, StockService stockService)
			throws StockException {
		stockService.viewAllStocks(stocks);
	}

//	**************************************** Customer Buy Stock Method ***************************************
	public static String customerBuyStock(Scanner sc, String email, Map<String, Stock> stocks,
			Map<String, Customer> customers, List<Transaction> transactions, CustomerService cusService)
			throws InvalidDetailsException, StockException {
		System.out.println("Enter the Stock name which you want to buy");
		String name = sc.next();
		System.out.println("enter the quantity you want to buy");
		int qty = sc.nextInt();
		
	    boolean result = cusService.buyStock(name.toUpperCase(), qty, email, stocks, customers, transactions);
	    if(result) {
	    	return "You have successfully purchased the stock";
	    }else {
	    	return "Oops...!\nUnfortunately you cannot buy a stock since your account is Inactive..!";
	    }
		
		

	}
	
//	**************************************** Customer sell Stock Method ***************************************
	public static String customerSellStock(Scanner sc, String email, Map<String, Stock> stocks,
			Map<String, Customer> customers, List<Transaction> transactions, CustomerService cusService)
			throws InvalidDetailsException, StockException {
		System.out.println("Enter the Stock name which you want to sell");
		String name = sc.next();
		System.out.println("enter the quantity you want to sell");
		int qty = sc.nextInt();
		cusService.sellStock(name.toUpperCase(), qty, email, stocks, customers, transactions);

		return "You have successfully sold the stock";

	}
//	
//	**************************************** Customer Add Money Method ***************************************
	public static String customerAddMoneyToWallet(Scanner sc, String email, Map<String, Customer> customers,
			CustomerService cusService) {
		System.out.println("please enter the amount");
		double money = sc.nextDouble();
		boolean added = cusService.addMoneyToWallet(money, email, customers);
		if(added) {
			return "Amount of " + money + " successfully added to your wallet";
		}else {
			return "Amount of " + money + " couldn't add!";
		}
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
		System.out.println("name : " + cus.getName());
		System.out.println("username : " + cus.getUsername());
		System.out.println("address : " + cus.getAddress());
		System.out.println("mobile number : " + cus.getNum());
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
			Map<String, Stock> stocks, List<Transaction> transactions)
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
				System.out.println("1. view all stocks");
				System.out.println("2. buy a stock");
				System.out.println("3. sell a stock");
				System.out.println("4. add money to a wallet");
				System.out.println("5. view wallet balance");
				System.out.println("6. view my details");
				System.out.println("7. view my transactions");
				System.out.println("8. logout");
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
					String sold = customerSellStock(sc, email, stocks, customers, transactions, cusService);
					System.out.println(sold);
					break;
				case 4:
					String moneyAdded = customerAddMoneyToWallet(sc, email, customers, cusService);
					System.out.println(moneyAdded);
					break;
				case 5:
					double walletBalance = customerViewWalletBalance(email, customers, cusService);
					System.out.println("Wallet balance is: " + walletBalance);
					break;
				case 6:
					customerViewMyDetails(email, customers, cusService);
					break;
				case 7:
					customerViewCustomerTransactions(email, transactions, transactionService);
					break;
				case 8:
					System.out.println("you have successsfully logout");
					break;
				default:
					System.out.println("invalid choice");
					break;
				}

			} while (choice <= 7);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
//	=================================== End Of Customer Functionalities Method =====================================
	
	
//	------------------------------------------------- Main Method ---------------------------------------------------

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//file check
		Map<String, Stock> stocks = FileExists.stocksFile();
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
//					throw new IllegalArgumentException("Invalid Selection");
					System.out.println("Invalid Selection");
					break;
					
				}

			}
			while (choice != 0);

		}catch (Exception e) {
			System.out.println(e.getMessage());
		} 
	}

//	============================================= End Of MAin Method ================================================
}
