package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Laptop;
import com.example.demo.repository.LaptopRepo;

@Service
public class LaptopService {

	@Autowired
	public LaptopRepo laptopRepo;
	
	public List<Laptop> getAllLaptop(){
		return laptopRepo.findAll();
	}
	
	public List<Laptop> getLaptopByCategory(int id){
		return laptopRepo.findAllByCategory(id);
	}
	
	
	public void addLaptop(Laptop laptop) {
		laptopRepo.save(laptop);
	}
	
	public Laptop getLaptopById(int id) {
		return laptopRepo.getById(id);
	}
	
	public void deleteLaptop(Integer id) {
		laptopRepo.deleteById(id);;
	}
	
}
