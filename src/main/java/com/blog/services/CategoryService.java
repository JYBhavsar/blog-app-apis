package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto, Integer CategoryId);
	void deleteCategory(Integer CategoryId);
	CategoryDto getCategory(Integer CategoryId);
	List<CategoryDto> getCategories();
	
}
