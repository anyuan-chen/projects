package com.chen.graphite.gui;
import javax.swing.*;
import java.util.*;
import com.chen.graphite.model.*;

public class Application {
	public static ArrayList <Product> products = new ArrayList <Product> (0);
	public static ArrayList <CartItem> cartItems = new ArrayList <CartItem> (0);
	public static ArrayList <CustomerOrder> orders = new ArrayList<CustomerOrder> (0);
	public static int revenue = 0;

	public static void main(String [] args) {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(600,800);
		MainMenu mm = new MainMenu(frame);
		mm.screen("I am a:");
	}
}
 