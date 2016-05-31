package com.thienq.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {
	
//	@Autowired
//	private CustomerService CustomerService;

	@RequestMapping("/customers")
	private String list(Model model){
//		model.addAttribute("customers", CustomerService.getAllCustomers());
		
		return "customers";
	}
}
