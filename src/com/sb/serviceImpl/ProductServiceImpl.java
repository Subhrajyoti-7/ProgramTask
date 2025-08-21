package com.sb.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sb.entity.Product;
import com.sb.service.ProductService;

public class ProductServiceImpl implements ProductService{

	@Override
	public List<Product> findMostExpensiveProduct(List<Product> prods) {
		try {
			// Checks whether the list is empty
			if(prods.isEmpty()) {
				System.out.println("No products available");
				return prods;
			}

			// HashMap I will use to store the product(category -> key and price -> value)
			Map<String, Double> map = new HashMap<>();

			// Instead of sending Map, I use List<Product> as return so that it is easy to get details of product
			List<Product> list = new ArrayList<>();
			for(Product p : prods) {
				// If the key is available means I've already added the item in Map
				if(map.containsKey(p.getCategory())) {
					// If already existed price is smaller than the new price then I'll update the price
					if(map.get(p.getCategory()) < p.getPrice())
						map.replace(p.getCategory(), map.get(p.getCategory()), p.getPrice());
				}else {
					// If not in the Map, add as a new entry
					map.put(p.getCategory(), p.getPrice());
				}
			}

			// Adding items from Map to List
			for(Product p : prods) {
				if(p.getPrice() == map.get(p.getCategory())) {
					list.add(p);
				}
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public Map<String, Double> getAveragePricePerCatagory(List<Product> prods) {
		try {
			// Checks whether the list is empty
			if(prods.isEmpty()) {
				System.out.println("No products available");
				return Collections.emptyMap();
			}
			// Here I used Map(Category -> key & Average -> value).
			// Used stream() API to get each item and group them according to their Category
			// then for each category calculate the average
			// and store them into a Map
			Map<String, Double> map = prods.stream().filter(p -> p.getPrice() >= 0)
					.collect(Collectors.groupingBy(p -> p.getCategory(), Collectors.averagingDouble(p -> p.getPrice())));
			
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyMap();
		}
	}

	@Override
	public Map<String, Double> getProductsUnderBudget(List<Product> prods, double budget) {
		try {
			// Checks whether the list is empty
			if(prods.isEmpty()) {
				System.out.println("No products available");
				return Collections.emptyMap();
			}
			// Checks whether the budget is invalid or not (Negative)
			if(budget <= 0) {
				System.out.println("Budget in invalid!!!");
				return Collections.emptyMap();
			}

			// Find all products which are suitable under the budget and store them into Map;
			Map<String, Double> map = new HashMap<>();
			for(Product p : prods) {
				// Checks the price under the budget and also for invalid values
				if(p.getPrice() <= budget && p.getPrice() >= 0)
					map.put(p.getName(), p.getPrice());
			}
			
			return map;
						
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyMap();
		}
	}

	@Override
	public List<Product> sortProductsByPrice(List<Product> prods) {
		try {
			// Checks whether the list is empty
			if(prods.isEmpty()) {
				System.out.println("No products available");
				return prods;
			}
			// Used Stream() API to fetch items as a stream
			// and filter them, if there is some negative values in the price
			// then sort them using Comparator according to their price
			// and then collect them into a List
			List<Product> list = prods.stream().filter(p -> p.getPrice() >= 0).sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}
