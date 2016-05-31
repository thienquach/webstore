package com.thienq.webstore.editor;

import com.thienq.webstore.domain.Category;
import com.thienq.webstore.service.CategoryService;

import java.beans.PropertyEditorSupport;

public class CategoryEditor extends PropertyEditorSupport {
	
	private CategoryService categoryService;
	
	public CategoryEditor(CategoryService categoryService){
		this.categoryService = categoryService;
	}
	
	@Override
	public void setAsText(String text){
		Category category = categoryService.findOne(Long.parseLong(text));
		setValue(category);
	}

}
