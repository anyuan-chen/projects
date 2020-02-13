package com.chen.graphite.model;
import java.util.ArrayList;
import com.chen.graphite.model.CartItem;

public class CustomerOrder {
	
	private ArrayList<CartItem> cartItems;
	private int taxRate;
	private int discountCode;

	public CustomerOrder (ArrayList<CartItem> cartItems, int taxRate, int discountCode) {
		cartItems = new ArrayList<CartItem>(0);
		this.taxRate = taxRate;
		this.discountCode = discountCode;
	}
	
	public void reset() {
		cartItems.clear();
		taxRate = 0;
		discountCode = 0;
	}


	public ArrayList<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(ArrayList<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public int getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(int discountCode) {
		this.discountCode = discountCode;
	}

	public int getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(int taxRate) {
		this.taxRate = taxRate;
	}


}