package com.chen.graphite.gui.customer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.chen.graphite.gui.Application;
import com.chen.graphite.gui.MenuTemplate;


public class ViewCart extends MenuTemplate{
	JFrame frame;
	public ViewCart(JFrame f) {
		super(f);
		frame = f;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public JPanel buildTitlePanel(String titleName) {
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel(titleName);
		titlePanel.add(title);
		return titlePanel;
	}

	@Override
	public JPanel buildMainPanel() {
		JPanel viewCartSummaryPanel = new JPanel();
		JPanel viewCartPanel = new JPanel();
		
		viewCartPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);

		JLabel[] displayCartItemName = new JLabel[Application.cartItems.size()];
		JLabel[] displayCartItemPrice = new JLabel[Application.cartItems.size()];
		JLabel[] displayCartAmount = new JLabel[Application.cartItems.size()];


		double subtotalPrice = 0;
		JLabel nameOfProductCart = new JLabel("Name");
		gbc.gridx = 1;
		gbc.gridy = 0;
		viewCartPanel.add(nameOfProductCart, gbc);
		
		JLabel priceOfProductCart = new JLabel("Total Price for this Item");
		gbc.gridx = 2;
		gbc.gridy = 0;
		viewCartPanel.add(priceOfProductCart, gbc);
		
		JLabel stockOfProductCart = new JLabel("Amount Added");
		gbc.gridx = 3;
		gbc.gridy = 0;
		viewCartPanel.add(stockOfProductCart, gbc);

		for (int i = 0; i < Application.cartItems.size(); i++) {
			displayCartItemName[i] = new JLabel(Application.cartItems.get(i).getName());
			gbc.gridx = 1;
			gbc.gridy = i+1;
			viewCartPanel.add(displayCartItemName[i], gbc);
			
			displayCartItemPrice[i] = new JLabel(Double.toString(Application.cartItems.get(i).getPrice()));
			gbc.gridx = 2;
			gbc.gridy = i+1;
			viewCartPanel.add(displayCartItemPrice[i], gbc);
			
			displayCartAmount[i] = new JLabel(Integer.toString(Application.cartItems.get(i).getAmountToBuy()));
			gbc.gridx = 3;
			gbc.gridy = i+1;
			viewCartPanel.add(displayCartAmount[i], gbc);
			
			subtotalPrice += Application.cartItems.get(i).getPrice()  * Application.cartItems.get(i).getAmountToBuy();

		}
		
		JLabel subtotal = new JLabel("The subtotal for this order will be " + subtotalPrice);
		gbc.gridx = 2;
		gbc.gridy = Application.cartItems.size() + 2;
		viewCartPanel.add(subtotal, gbc);
		return viewCartPanel;
	}

	@Override
	public JPanel buildButtonPanel() {
		JPanel buttonPanel = new JPanel();
		JButton exit = new JButton(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				CustomerMenu mm = new CustomerMenu(frame);
				mm.screen("Customer Menu");
			}
		});
		buttonPanel.add(exit);
		return buttonPanel;
	}

}
