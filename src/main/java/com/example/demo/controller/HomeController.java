package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Laptop;
import com.example.demo.service.CategoriesService;
import com.example.demo.service.LaptopService;

@Controller
public class HomeController {
	@Autowired
	CategoriesService categoriesService;
	@Autowired
	LaptopService laptopService;
	
	@GetMapping({"/","/home"})
	public String viewHomePage(Model model) {
		return "index";
	}

	@GetMapping("/shop")
	public String viewShopPage(Model model) {
		model.addAttribute("categories", categoriesService.getAllCategories());
		model.addAttribute("laptops", laptopService.getAllLaptop());
		return "shop";
	}
	
	@GetMapping("/shop/category/{id}")
	public String viewShopPageByCategory(Model model, @PathVariable int id) {
		model.addAttribute("categories", categoriesService.getAllCategories());
		model.addAttribute("laptops", laptopService.getLaptopByCategory(id));
		return "shop";
	}
	
	@GetMapping("/shop/viewlaptop/{id}")
	public String viewDetailLaptop(Model model, @PathVariable int id) {
		model.addAttribute("laptop",laptopService.getLaptopById(id));
		return "viewLaptop";
	}
	
	
}
