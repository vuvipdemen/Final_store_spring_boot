package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Laptop;

public interface LaptopRepo extends JpaRepository<Laptop, Integer>  {
	
	@Query(value = "SELECT * FROM laptop where idcate =?1", nativeQuery = true)
	public List<Laptop> findAllByCategory(int id);	
}
