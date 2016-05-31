package com.thienq.webstore.controller.management;

import com.thienq.webstore.domain.Category;
import com.thienq.webstore.domain.Product;
import com.thienq.webstore.editor.CategoryEditor;
import com.thienq.webstore.service.CategoryService;
import com.thienq.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/management/products")
public class ProductManagementController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder){
		binder.setDisallowedFields("unitsInOrder", "discontinued");
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
	}
	
	@RequestMapping
	public String getAddNewProductForm(Model model, @RequestParam(required=false, defaultValue="0") String page ){
		Pageable pageable = new PageRequest( Math.max(0, Integer.parseInt(page) - 1), 5, Direction.DESC, "lastUpdatedDate");
		Page<Product> productPage = productService.findAll(pageable);
		model.addAttribute("productPage", productPage);
		
		int currentIndex = productPage.getNumber() + 1;
		int beginIndex = Math.max(1, currentIndex - 5);
		int endIndex = Math.min(beginIndex + 10, productPage.getTotalPages());
		
		Map<String, String> pageAtributes = new HashMap<>();
		pageAtributes.put("currentIndex", String.valueOf(currentIndex));
		pageAtributes.put("beginIndex", String.valueOf(beginIndex));
		pageAtributes.put("endIndex", String.valueOf(endIndex));
		
		model.addAllAttributes(pageAtributes);
		
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
			
		model.addAttribute("categories", categoryService.findAll());
		return "management/product_management";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, BindingResult result){
		//Check if the FormBean contains disallowed fields from WebDataBinder
		String[] suppressFields = result.getSuppressedFields();
		if(suppressFields.length > 0){
			throw new RuntimeException("Attempting to bind disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressFields));
		}
		productService.addProduct(newProduct);
		return "redirect:/management/products";
	}
}
