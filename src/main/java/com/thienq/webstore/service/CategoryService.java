package com.thienq.webstore.service;

import com.thienq.webstore.domain.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
	
	List<Category> findAll();
	Map<String, Category> getCategoryMap();
	Category findOne(Long id);
	void save(Category category);
}
