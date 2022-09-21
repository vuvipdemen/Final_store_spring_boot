package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Categories;
import com.example.demo.entity.Laptop;
import com.example.demo.service.CategoriesService;
import com.example.demo.service.LaptopService;


@Controller
public class AdminController {
	
	@Autowired
	CategoriesService categoriesService;
	@Autowired
	LaptopService laptopService;
	
	
	
	// admin Page
	@GetMapping("/admin")
	public String viewAdminPage() {
		return "adminHome";
	}
	
	// view categories page
	@GetMapping("/admin/categories")
	public String viewCategories(Model model) {
		model.addAttribute("categories", categoriesService.getAllCategories());
		return "categories";
	}

	
	// view add categories page
	@GetMapping("/admin/categories/add")
	public String viewAddCategories(Model model) {
		model.addAttribute("newCategory", new Categories());
		return "categoriesAdd";
	}
	
	
	// save new categories and reload the categoires page to view list categories
	@PostMapping("/admin/categories/save")
	public String saveNewCategories(Model model, Categories categories, RedirectAttributes redic) {
		Categories cate = categoriesService.addCategories(categories);
		return "redirect:/admin/categories";
	}
	
	// delete categories by id and reload the categoires page to view list categories
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategories(Model model, @PathVariable(name = "id") Integer id, RedirectAttributes redic) {
		categoriesService.delete(id);
		return "redirect:/admin/categories"; 
	}
	
	// update categories by id and reload the categories page to view list categories
	@GetMapping("/admin/categories/update/{id}")
	public String updateCategories(Model model, @PathVariable(name = "id") Integer id, RedirectAttributes redic) {
		Categories cate = categoriesService.get(id);
		model.addAttribute("currentCategory",cate);
		return "categoriesUpdate";
	}
	
	
	// -----------------------Product---------------------
	@GetMapping("/admin/laptops")
	public String viewLaptopPage(Model model) {
		model.addAttribute("laptops",laptopService.getAllLaptop());
		return "laptops";
	}
	
	@GetMapping("/admin/laptops/add")
	public String viewAddLaptopPage(Model model) {
		model.addAttribute("newLaptop", new Laptop());
		model.addAttribute("categories", categoriesService.getAllCategories());
		return "laptopAdd";
	}
	
	@PostMapping("/admin/laptops/save")
	public String saveNewLaptop(Model model, Laptop laptop, @RequestParam("laptopImage") MultipartFile file, @RequestParam("imgName") String imgName) throws IOException {
		Laptop saveLaptop = new Laptop();
		saveLaptop.setId(laptop.getId());
		saveLaptop.setName(laptop.getName());
		saveLaptop.setCategory(categoriesService.get(laptop.getCategory().getId()));
		saveLaptop.setCpu(laptop.getCpu());
		saveLaptop.setPrice(laptop.getPrice());
		saveLaptop.setRam(laptop.getRam());
		saveLaptop.setDescription(laptop.getDescription());
		String image;
		if(file.isEmpty() == false) {
			image = file.getOriginalFilename();
			String uploadDirection = System.getProperty("user.dir") + "/src/main/resources/static/laptopImages";
			/*
			File path = new  File(ResourceUtils.getURL("classpath:static/laptopImages").getPath()).getAbsoluteFile();
			String uploadDir = path.getAbsolutePath();
			*/
			Path fileNamePath = Paths.get(uploadDirection, image);
			Files.write(fileNamePath, file.getBytes());
			
		}
		else {
			image = imgName;
		}
		laptop.setImage(image);
		
		//call laptop service save database
		laptopService.addLaptop(laptop);
		return "redirect:/admin/laptops";
	}
	
	@GetMapping("admin/laptops/delete/{id}")
	public String deleteLaptop(@PathVariable int id) {
		laptopService.deleteLaptop(id);
		return "redirect:/admin/laptops";
	}
	
	@GetMapping("admin/laptops/update/{id}")
	public String updateLaptop(@PathVariable int id, Model model) {
		Laptop laptop = laptopService.getLaptopById(id);
		Laptop saveLaptop = new Laptop();
		saveLaptop.setId(laptop.getId());
		saveLaptop.setName(laptop.getName());
		saveLaptop.setCategory(categoriesService.get(laptop.getCategory().getId()));
		saveLaptop.setCpu(laptop.getCpu());
		saveLaptop.setPrice(laptop.getPrice());
		saveLaptop.setRam(laptop.getRam());
		saveLaptop.setDescription(laptop.getDescription());
		saveLaptop.setImage(laptop.getImage());
		
		model.addAttribute("newLaptop", saveLaptop);
		model.addAttribute("categories", categoriesService.getAllCategories());
		
		return "laptopAdd";
	}
	
}
