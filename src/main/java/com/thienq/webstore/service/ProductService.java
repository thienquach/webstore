package com.thienq.webstore.service;

import com.thienq.webstore.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductService {

	List<Product> findAll();
	Page<Product> findAll(Pageable pageable);
	Product findByCode(String code);
	List<Product> findByCategoryName(String category);
	Page<Product> findByCategoryName(String category, Pageable pageable);
	List<Product> findByManufacturer(String manufacturer);
	Page<Product> findByManufacturer(String manufacturer, Pageable pageable);
	Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	void addProduct(Product product);
}
