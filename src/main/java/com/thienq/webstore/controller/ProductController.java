package com.thienq.webstore.controller;

import com.thienq.webstore.service.CategoryService;
import com.thienq.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/products")
	public String list(Model model) {
		model.addAttribute("products", productService.findAll());

		return "products";
	}

	@RequestMapping("/products/all")
	public String allProducts(Model model) {
		return list(model);
	}

	@RequestMapping("/{category}")
	public String getProductsByCategory(Model model,
			@PathVariable String category) {
		model.addAttribute("products",
				productService.findByCategoryName(category));

		return "products";
	}

	@RequestMapping("/filter/{ByCriteria}")
	public String getProductsByFilter(
			@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams,
			Model model) {
		model.addAttribute("products",
				productService.getProductsByFilter(filterParams));

		return "products";
	}

	@RequestMapping("/product")
	public String getProductByCode(@RequestParam String code, Model model) {
		model.addAttribute("product", productService.findByCode(code));

		return "product";
	}
	
	
	
	

}
