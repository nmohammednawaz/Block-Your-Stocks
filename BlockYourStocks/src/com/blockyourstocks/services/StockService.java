package com.blockyourstocks.services;

import java.util.Map;

import com.blockyourstocks.entities.Stock;
import com.blockyourstocks.exceptions.StockException;


public interface StockService {
	public String addStock(Stock stock, Map<Integer, Stock> stocks);

	public void viewAllStocks(Map<Integer, Stock> stocks) throws StockException;

	public void deleteStock(int id, Map<Integer, Stock> stocks) throws StockException;

	public String updateStock(int id, Stock stock, Map<Integer, Stock> stocks) throws StockException;

	
}
