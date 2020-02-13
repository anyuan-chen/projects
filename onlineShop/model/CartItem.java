package com.chen.graphite.model;

public class CartItem extends Item{
	
	private int amountToBuy;
	
	public int getAmountToBuy() {
		return amountToBuy;
	}

	public void setAmountToBuy(int amountToBuy) {
		this.amountToBuy = amountToBuy;
	}

	public CartItem(String name, double price, int amountToBuy) {
		super(name, price);
		this.amountToBuy = amountToBuy;
	}
	

}
