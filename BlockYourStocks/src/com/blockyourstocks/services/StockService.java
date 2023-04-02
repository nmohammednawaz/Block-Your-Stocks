package com.blockyourstocks.services;

import java.util.Map;

import com.blockyourstocks.entities.Stock;
import com.blockyourstocks.exceptions.StockException;


public interface StockService {
	public String addStock(Stock stock, Map<String, Stock> stocks);

	public void viewAllStocks(Map<String, Stock> stocks) throws StockException;

	public void deleteStock(String name, Map<String, Stock> stocks) throws StockException;

	public String updateStock(String name, Stock stock, Map<String, Stock> stocks) throws StockException;

	
}
