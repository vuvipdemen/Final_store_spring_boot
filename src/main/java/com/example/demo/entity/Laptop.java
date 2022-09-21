package com.example.demo.entity;

import javax.persistence.*;

@Entity(name="laptop")
@Table(name = "laptop")
public class Laptop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcate", referencedColumnName = "idcate")
	private Categories category;
	private int price;
	private int ram;
	private String cpu;
	private String description;
	private String image;
	
	
	public Laptop() {}


	public Laptop(int id, String name, Categories category, int price, int ram, String cpu, String description,
			String image) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.ram = ram;
		this.cpu = cpu;
		this.description = description;
		this.image = image;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Categories getCategory() {
		return category;
	}


	public void setCategory(Categories category) {
		this.category = category;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getRam() {
		return ram;
	}


	public void setRam(int ram) {
		this.ram = ram;
	}


	public String getCpu() {
		return cpu;
	}


	public void setCpu(String cpu) {
		this.cpu = cpu;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
	
	
	
}
