package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="categories")
public class Categories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcate")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	public Categories() {}

	
	
	public Categories(String name) {
		super();
		this.name = name;
	}



	public Categories(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	
	
	
	
}
