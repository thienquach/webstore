package com.thienq.webstore.service.impl;

import com.thienq.webstore.domain.Product;
import com.thienq.webstore.repository.ProductJPARepository;
import com.thienq.webstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private ProductJPARepository productRepository;

	public void processOrder(String code, int quantity) {
		Product productByCode = productRepository.findByCode(code);
		
		if(productByCode.getUnitsInStock() < quantity){
			throw new IllegalArgumentException("Out of Stock. Available Units in stock " + productByCode.getUnitsInStock()); 
		}
		
		productByCode.setUnitsInStock(productByCode.getUnitsInStock() - quantity);
	}

}
