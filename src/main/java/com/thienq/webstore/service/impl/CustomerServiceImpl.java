package com.thienq.webstore.service.impl;

import com.thienq.webstore.domain.Customer;
import com.thienq.webstore.repository.CustomerJPARepository;
import com.thienq.webstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerJPARepository customerRepository;
	
	public List<Customer> getAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}
	
}
