package com.chen.graphite.gui.manager;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.*;

import com.chen.graphite.gui.Application;
import com.chen.graphite.gui.MenuTemplate;
import com.chen.graphite.model.Product;
import com.chen.graphite.utilities.Utilities;

public class AddProduct extends MenuTemplate {
	JFrame frame;
	JTextField name, price, stock;
	public AddProduct(JFrame f) {
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
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);	
		
		JLabel nameLbl = new JLabel("Product Name:");
		gbc.gridx = 1;
		gbc.gridy = 1;
		mainPanel.add(nameLbl, gbc);

		JLabel priceLbl = new JLabel("Product Price");
		gbc.gridx = 1;
		gbc.gridy = 2;
		mainPanel.add(priceLbl, gbc);
		
		JLabel stockLbl = new JLabel("Product Stock");
		gbc.gridx = 1;
		gbc.gridy = 3;
		mainPanel.add(stockLbl, gbc);
		
		name = new JTextField("",10);
		gbc.gridx = 2;
		gbc.gridy = 1;
		mainPanel.add(name,gbc);
		
		price = new JTextField("", 10);
		gbc.gridx = 2;
		gbc.gridy = 2;
		mainPanel.add(price, gbc);

		stock = new JTextField("", 10);
		gbc.gridx = 2;
		gbc.gridy = 3;
		mainPanel.add(stock, gbc);
		
		return mainPanel;
	}

	@Override
	public JPanel buildButtonPanel() {
		JPanel buttonPanel = new JPanel ();
		JButton exit = new JButton(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				ManagerMenu mm = new ManagerMenu(frame);
				mm.screen("Manager Menu");
			}
		});
		JButton submit = new JButton(new AbstractAction("Submit") {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Utilities.alreadyExists(name.getText())) {
					JOptionPane.showMessageDialog(frame, "This product has already been added", "Faliure",
							JOptionPane.ERROR_MESSAGE);
				}
				else if (Application.products.size() > 9) {
					JOptionPane.showMessageDialog(frame, "The max amount has already been added", "Faliure",
							JOptionPane.ERROR_MESSAGE);
				}
				else if (Utilities.isDouble(price.getText()) && Utilities.isInt(stock.getText())) {
					String productName = name.getText();
					Double productPrice = Utilities.moneyRounder(Double.parseDouble(price.getText()));
					int productStock = Integer.parseInt(stock.getText());
					Application.products.add(new Product(productName, productPrice, productStock));
					
					JOptionPane.showMessageDialog(frame, "Product Added Successfully", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					
					name.setText("");	
					price.setText("");
					stock.setText("");
				}
				else {
					JOptionPane.showMessageDialog(frame, "Invalid input", "Faliure",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		buttonPanel.add(exit);
		buttonPanel.add(submit);
		return buttonPanel;
	}

}
