package com.chen.graphite.model;

public class Item {
	
	protected String name;
	protected double price;
	
	public Item() {
		
	}
	protected Item(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}

	protected void setPrice(double price) {
		this.price = price;
	}
}
