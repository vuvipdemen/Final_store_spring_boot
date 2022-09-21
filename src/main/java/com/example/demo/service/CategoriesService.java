package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Categories;
import com.example.demo.repository.CategoriesRepo;

@Service
public class CategoriesService {
	@Autowired
	public CategoriesRepo categoriesRepo;
	
	public Categories addCategories(Categories categories) {
		return categoriesRepo.save(categories);
	}
	
	public List<Categories> getAllCategories(){
		return categoriesRepo.findAll();
	}
	
	public Categories get(int id) {
		return categoriesRepo.getById(id);
	}
	
	public void delete(Integer id) {
		categoriesRepo.deleteById(id);
	}
}
