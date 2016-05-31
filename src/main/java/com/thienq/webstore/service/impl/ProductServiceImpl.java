package com.thienq.webstore.service.impl;

import com.thienq.webstore.domain.Product;
import com.thienq.webstore.repository.ProductJPARepository;
import com.thienq.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductJPARepository productRepository;
	
	
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}
	
	public Page<Product> findAll(Pageable pageable){
		return productRepository.findAll(pageable);
	}

	public Product findByCode(String code) {
		return productRepository.findByCode(code);
	}

	public Page<Product> findByCategoryName(String category, Pageable pageable) {
//		Pageable pageable = new PageRequest(0, 10, Direction.ASC, "name");
		return productRepository.findByCategoryName(category, pageable);
	}

	public Set<Product> getProductsByFilter(
			Map<String, List<String>> filterParams) {
		return (Set<Product>) productRepository.findAll();
	}

	public Page<Product> findByManufacturer(String manufacturer, Pageable pageable) {
//		Pageable pageable = new PageRequest(0, 10, Direction.ASC, "name");
		return productRepository.findByManufacturer(manufacturer, pageable);
	}

	@Override
	public void addProduct(Product product) {
		product.setCreatedDate(new Date(System.currentTimeMillis()));
		product.setLastUpdatedDate(new Date(System.currentTimeMillis()));
		productRepository.save(product);
	}

	@Override
	public List<Product> findByCategoryName(String category) {
		return productRepository.findByCategoryName(category);
	}

	@Override
	public List<Product> findByManufacturer(String manufacturer) {
		return productRepository.findByManufacturer(manufacturer);
	}
	
}
