package com.chen.graphite.gui.customer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.chen.graphite.gui.Application;
import com.chen.graphite.gui.MenuTemplate;
import com.chen.graphite.model.CartItem;
import com.chen.graphite.utilities.Utilities;

public class AddToCart extends MenuTemplate {
	JFrame frame;
	JTextField[] amounts;
	JLabel[] prices, names;

	public AddToCart(JFrame f) {
		super(f);
		// TODO Auto-generated constructor stub
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
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		JLabel productNameIndicator = new JLabel("Product Name");
		gbc.gridx = 1;
		gbc.gridy = 0;
		mainPanel.add(productNameIndicator, gbc);

		JLabel productPriceIndicator = new JLabel("Product Price");
		gbc.gridx = 2;
		gbc.gridy = 0;
		mainPanel.add(productPriceIndicator, gbc);

		JLabel quantityToAddIndicator = new JLabel("Quantity to Add");
		gbc.gridx = 3;
		gbc.gridy = 0;
		mainPanel.add(quantityToAddIndicator, gbc);

		names = new JLabel[Application.products.size()];
		prices = new JLabel[Application.products.size()];
		amounts = new JTextField[Application.products.size()];

		for (int i = 0; i < Application.products.size(); i++) {
			names[i] = new JLabel(Application.products.get(i).getName());
			gbc.gridx = 1;
			gbc.gridy = i + 1;
			mainPanel.add(names[i], gbc);

			prices[i] = new JLabel(Double.toString(Application.products.get(i).getPrice()));
			gbc.gridx = 2;
			gbc.gridy = i + 1;
			mainPanel.add(prices[i], gbc);

			amounts[i] = new JTextField("", 5);
			gbc.gridx = 3;
			gbc.gridy = i + 1;
			mainPanel.add(amounts[i], gbc);
		}
		return mainPanel;
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
		JButton submit = new JButton(new AbstractAction("Add to Cart") {
			public void actionPerformed(ActionEvent e) {
				boolean notANumber = false;
				boolean notEnough = false;
				boolean belowZero = false;
				boolean blank = false;

				for (int i = 0; i < Application.products.size(); i++) {
					if (Utilities.isInt(amounts[i].getText()) == false && !(amounts[i].getText().equals(""))) {
						notANumber = true;
						JOptionPane.showMessageDialog(frame, "Invalid input. No products were added", "Faliure",
								JOptionPane.ERROR_MESSAGE);
					} else if (!(amounts[i].getText().equals(""))
							&& Integer.parseInt(amounts[i].getText()) > Application.products.get(i).getStock()) {
						notEnough = true;
						JOptionPane.showMessageDialog(frame,
								"Not enough stock for one or more products. No products were added.", "Faliure",
								JOptionPane.ERROR_MESSAGE);
					} else if (!(amounts[i].getText().equals("")) && Integer.parseInt(amounts[i].getText()) < 0) {
						belowZero = true;
						JOptionPane.showMessageDialog(frame, "Invalid Input. No products were added", "Faliure",
								JOptionPane.ERROR_MESSAGE);
					} else if (amounts[i].getText().equals("")) {
						blank = true;
					}

					else {
						try {
							if (Application.cartItems.get(i).getAmountToBuy()
									+ Integer.parseInt(amounts[i].getText()) > Application.products.get(i).getStock()
									&& !(amounts[i].getText().equals(""))) {
								notEnough = true;
								JOptionPane.showMessageDialog(frame,
										"Not enough stock for one or more products. No products were added.", "Faliure",
										JOptionPane.ERROR_MESSAGE);
							}

						} catch (Exception e1) {
							notEnough = false;
						}
					}
				}

				if (notANumber == false && notEnough == false && belowZero == false) {
					for (int i = 0; i < Application.products.size(); i++) {
						boolean productExists = false;
						for (int j = 0; j < Application.cartItems.size(); j++) {
							if (!(amounts[i].getText().equals(""))) {
								if (Application.products.get(i).getName()
										.equals(Application.cartItems.get(j).getName())) {
									Application.cartItems.get(j)
											.setAmountToBuy(Application.cartItems.get(j).getAmountToBuy()
													+ Integer.parseInt(amounts[i].getText()));
								
									productExists = true;
								}
							}

						}
						if (productExists == false) {
							if (!(amounts[i].getText().equals(""))) {

								Application.cartItems
										.add(new CartItem(names[i].getText(), Double.parseDouble(prices[i].getText()),
												Integer.parseInt(amounts[i].getText())));
								
							}
						}
					}
					
					JOptionPane.showMessageDialog(frame,
							"Products and amounts to buy was successfully updated", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			
				for (int i = 0; i < amounts.length; i++) {
					amounts [i].setText("");
				}
			}
			
			
			
			
		});
		buttonPanel.add(submit);
		buttonPanel.add(exit);
		return buttonPanel;
	}

}
