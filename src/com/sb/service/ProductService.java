package com.sb.service;

import java.util.List;
import java.util.Map;

import com.sb.entity.Product;

public interface ProductService {
	// Find the most expensive product in each category.
	public List<Product> findMostExpensiveProduct(List<Product> prods);
	
	// Calculate the average price per category.
	public Map<String, Double> getAveragePricePerCatagory(List<Product> prods);
	
	// Given a budget X, return all possible products that can be purchased without exceeding X (any combination of products).
	public Map<String, Double> getProductsUnderBudget(List<Product> prods, double budget);
	
	// Sort products by price (ascending) and print the sorted list.
	public List<Product> sortProductsByPrice(List<Product> prods);
}
