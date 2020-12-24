package org.vinay.learnJdbc.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Circle {

	@Id
	private int id;
	private String name;
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
	
	public Circle(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Circle() {
		// TODO Auto-generated constructor stub
	}
	
	
}
