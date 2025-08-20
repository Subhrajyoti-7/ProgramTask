package com.sb.main;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.sb.entity.Product;
import com.sb.serviceImpl.ProductServiceImpl;

public class Main {
	
	public static void main(String[] args) {
		
		List<Product> products = Arrays.asList(
			    new Product(1, "Laptop", "Electronics", 1200.0),
			    new Product(2, "Phone", "Electronics", 800.0),
			    new Product(3, "Shirt", "Clothing", 40.0),
			    new Product(4, "Jeans", "Clothing", 60.0),
			    new Product(5, "Book", "Stationery", 15.0)
			);
		
		// Warning for invalid price (Negative)
		for(Product p : products) {
			if(p.getPrice() < 0) {
				System.out.println(p.getName() + " has invalid price value!!!\n");
			}
			
		}
		
		ProductServiceImpl prodService = new ProductServiceImpl();
		List<Product> expProd = prodService.findMostExpensiveProduct(products);
		Map<String, Double> avgPrice = prodService.getAveragePricePerCatagory(products);
		Map<String, Double> budgetProd = prodService.getProductsUnderBudget(products, 100);
		List<Product> sortedProd = prodService.sortProductsByPrice(products);
		
		System.out.println("Most expensive per category:");
		for(Product prod : expProd) {
			System.out.println(prod.getCategory() + " -> " + prod.getName() + " (Rs. " + prod.getPrice() + ")");
		}
		System.out.println();
		
		System.out.println("Average price per category:");
		for(Map.Entry<String, Double> entry : avgPrice.entrySet()) {
			System.out.println(entry.getKey() + " -> " + " (Rs. " + entry.getValue() + ")");
		}
		System.out.println();
		
		System.out.println("Combinations within budget 100:");
		for(Map.Entry<String, Double> entry : budgetProd.entrySet()) {
			System.out.println(entry.getKey() + " (Rs. " + entry.getValue() + ")");
		}
		System.out.println();
		
		System.out.println("Products sorted by price:");
		for(Product p : sortedProd) {
			System.out.println(p.getName() + " (Rs. " + p.getPrice() + ")");
		}
		
	}

}
