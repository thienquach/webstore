package com.thienq.webstore.management.controller;

import com.thienq.webstore.domain.Category;
import com.thienq.webstore.domain.Product;
import com.thienq.webstore.editor.CategoryEditor;
import com.thienq.webstore.service.CategoryService;
import com.thienq.webstore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/management/products")
public class ProductManagementController {
	private Logger log = LoggerFactory.getLogger(ProductManagementController.class);
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private HttpServletRequest request;
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder){
		binder.setDisallowedFields("unitsInOrder", "discontinued");
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
	}
	
	@RequestMapping
	public String showAllProducts(Model model, @RequestParam(required=false, defaultValue="0") String page ){
		Pageable pageable = new PageRequest( Math.max(0, Integer.parseInt(page) - 1), 5, Direction.DESC, "lastUpdatedDate", "id");
		Page<Product> productPage = productService.findAll(pageable);
		model.addAttribute("productPage", productPage);
		
		int currentIndex = productPage.getNumber() + 1;
		int beginIndex = Math.max(1, currentIndex - 5);
		int endIndex = Math.min(beginIndex + 10, productPage.getTotalPages());
		
		Map<String, String> pageAttributes = new HashMap<>();
		pageAttributes.put("currentIndex", String.valueOf(currentIndex));
		pageAttributes.put("beginIndex", String.valueOf(beginIndex));
		pageAttributes.put("endIndex", String.valueOf(endIndex));
		
		model.addAllAttributes(pageAttributes);

		return "managementProducts";
	}

	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model){
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);

		model.addAttribute("categories", categoryService.findAll());
		return "managementAddProduct";
	}

	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, BindingResult result){
		//Check if the FormBean contains disallowed fields from WebDataBinder
		String[] suppressFields = result.getSuppressedFields();
		if(suppressFields.length > 0){
			throw new RuntimeException("Attempting to bind disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressFields));
		}
		if(newProduct.getDescription() != null && newProduct.getDescription() != null && !newProduct.getDescription().trim().isEmpty()){
			try{
				String productDescriptionDir = "/product_description/";
				String realPathToUpload = request.getServletContext().getRealPath(productDescriptionDir);

				if(! new File(realPathToUpload).exists()){
					new File(realPathToUpload).mkdir();
				}
				log.info("realPathToUpload = {}", realPathToUpload);

				String fileName = newProduct.getCode();
				String filePath = realPathToUpload + fileName + ".html";

				try (PrintStream ps = new PrintStream(filePath)){
					ps.println(newProduct.getDescription());
				}
				newProduct.setDescription(filePath);
			}catch(Exception e){

			}
		}
		productService.addProduct(newProduct);
		return "redirect:/managementProducts";
	}

	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public String getProductDetail(Model model, @PathVariable String code){
		Product product = productService.findByCode(code);

		model.addAttribute("product", product);

		return "managementProduct";
	}
}
