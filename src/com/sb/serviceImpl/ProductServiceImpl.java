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
			Map<String, Double> map = new HashMap<>();
			List<Product> list = new ArrayList<>();
			for(Product p : prods) {
				if(map.containsKey(p.getCategory())) {
					if(map.get(p.getCategory()) < p.getPrice())
						map.replace(p.getCategory(), map.get(p.getCategory()), p.getPrice());
				}else {
					map.put(p.getCategory(), p.getPrice());
				}
			}
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
			if(budget <= 0) {
				System.out.println("Budget in invalid!!!");
				return Collections.emptyMap();
			}
			
			Map<String, Double> map = new HashMap<>();
			for(Product p : prods) {
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
			List<Product> list = prods.stream().filter(p -> p.getPrice() >= 0).sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}
