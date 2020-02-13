package com.chen.graphite.model;


public class Product extends Item{
	
	private int stock;
	
	public Product() {
		super();
	}
	
	public Product(String name, double price, int stock) {
		super(name, price);
		this.stock = stock;
	}



	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	
	public void incrementStock(int incrementAmount) {
		stock += incrementAmount;
	}
	
	
	public String toString() {
		return "Product [name=" + name + ", stock=" + stock + ", price=" + price + "]";
	}


	

}
