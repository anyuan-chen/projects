package com.chen.graphite.gui.customer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

import com.chen.graphite.gui.Application;
import com.chen.graphite.gui.MainMenu;
import com.chen.graphite.gui.MenuTemplate;
import com.chen.graphite.gui.customer.DisplayProducts;

public class CustomerMenu extends MenuTemplate {
	JFrame frame;
	JRadioButton displayProducts, addToCart, removeFromCart, viewCart, checkout;

	public CustomerMenu(JFrame f) {
		super(f);
		frame = f;
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
		ButtonGroup bg = new ButtonGroup();
		JPanel mainPanel = new JPanel(new GridLayout(0,1));

		displayProducts = new JRadioButton("Display All Products");
		addToCart = new JRadioButton("Add a product to cart");
		removeFromCart = new JRadioButton("Remove a product from cart");
		viewCart = new JRadioButton("View products in your cart");
		checkout = new JRadioButton("Checkout products in your cart");

		bg.add(displayProducts);
		bg.add(addToCart);
		bg.add(removeFromCart);
		bg.add(viewCart);
		bg.add(checkout);

		mainPanel.add(displayProducts);
		mainPanel.add(addToCart);
		mainPanel.add(removeFromCart);
		mainPanel.add(viewCart);
		mainPanel.add(checkout);
		return mainPanel;
	}

	@Override
	public JPanel buildButtonPanel() {
		JPanel buttonPanel = new JPanel();
		JButton exit = new JButton(new AbstractAction("Exit") {
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				MainMenu mm = new MainMenu(frame);
				mm.screen("Main Menu");
			}
		});
		JButton submit = new JButton(new AbstractAction("Submit") {
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent e) {
				if (displayProducts.isSelected()) {
					frame.getContentPane().removeAll();
					DisplayProducts dp = new DisplayProducts(frame);
					dp.screen("Product display");
				} else if (addToCart.isSelected()) {
					frame.getContentPane().removeAll();
					AddToCart atc = new AddToCart(frame);
					atc.screen("Add to Cart");
				} else if (removeFromCart.isSelected()) {
					if (Application.cartItems.size() > 0) {
						frame.getContentPane().removeAll();
						RemoveFromCart rfc = new RemoveFromCart(frame);
						rfc.screen("Remove from cart");
					} else {
						JOptionPane.showMessageDialog(frame, "There are no products to remove", "Faliure",
								JOptionPane.ERROR_MESSAGE);
					}

				} else if (viewCart.isSelected()) {
					if (Application.cartItems.size() > 0) {
						frame.getContentPane().removeAll();
						ViewCart vc = new ViewCart(frame);
						vc.screen("View Cart");
					} else {
						JOptionPane.showMessageDialog(frame, "There are no products to view", "Faliure",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (checkout.isSelected()) {
					if (Application.cartItems.size() > 0) {
						frame.getContentPane().removeAll();
						TaxDiscount td = new TaxDiscount(frame);
						td.screen("Tax and Discount");
					} else {
						JOptionPane.showMessageDialog(frame, "No products to check out", "Faliure",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		buttonPanel.add(exit);
		buttonPanel.add(submit);
		return buttonPanel;
	}

}
