package com.thienq.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index(Model model){
		model.addAttribute("greeting", "Welcome to Web Store");
		model.addAttribute("tagline", "The one and only amazing store");
		
		return "index";
	}
}
