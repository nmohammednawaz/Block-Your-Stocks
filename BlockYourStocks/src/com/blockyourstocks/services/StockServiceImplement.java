package com.blockyourstocks.services;

import java.util.Map;

import com.blockyourstocks.entities.Stock;
import com.blockyourstocks.exceptions.StockException;

public class StockServiceImplement implements StockService{

	@Override
	public String addStock(Stock stock, Map<String, Stock> stocks) {
		// TODO Auto-generated method stub
		stocks.put(stock.getName().toUpperCase(), stock);
		return "Stock added successfully";
	}

	@Override
	public void viewAllStocks(Map<String, Stock> stocks) throws StockException {
		// TODO Auto-generated method stub
		if (stocks != null && stocks.size() > 0) {
			for (Map.Entry<String, Stock> me : stocks.entrySet()) {
				System.out.println(me.getValue());
			}
		} else {
			throw new StockException("Stock List is empty");
		}
	}

	@Override
	public void deleteStock(String name, Map<String, Stock> stocks) throws StockException {
		// TODO Auto-generated method stub
		if (stocks != null && stocks.size() > 0) {

			if (stocks.containsKey(name)) {
				stocks.remove(name);
				System.out.println("Stock deleted successfully");
			} else {
				throw new StockException("Stock not found");
			}
		} else {
			throw new StockException("Stock list is empty");
		}
	}

	@Override
	public String updateStock(String name, Stock stock, Map<String, Stock> stocks) throws StockException {
		// TODO Auto-generated method stub
		if (stocks != null && stocks.size() > 0) {
			if (stocks.containsKey(name)) {
				stocks.put(name, stock);
				return "Stock has successfully updated";
			} else {
				throw new StockException("Stock not found");
			}
		} else {
			throw new StockException("Stock list is empty");
		}
	}

}
