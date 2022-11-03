package com.micro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.model.Category;
import com.micro.repository.CategoryRepo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	public void addCategorories(Category category) {
		categoryRepo.save(category);
	}
	
	public List<Category> getAllCategories(){
		
		return categoryRepo.findAll();
		
	}
	
	public void deleteCategoryByID(int id) {
		categoryRepo.deleteById(id);
	}
	public Optional<Category> getCategoryById(int id) {
		return categoryRepo.findById(id);
	}
	

}
