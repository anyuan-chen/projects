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
import com.chen.graphite.gui.manager.ManagerMenu;

public class DisplayProducts extends MenuTemplate {
	JFrame frame;

	public DisplayProducts(JFrame f) {
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
		gbc.insets = new Insets(5,5,5,5);
		
		JLabel nameLabel = new JLabel("Product Name");
		gbc.gridx = 1;
		gbc.gridy = 1;
		mainPanel.add(nameLabel, gbc);
		
		JLabel priceLabel = new JLabel("Product Price");
		gbc.gridx = 2;
		gbc.gridy = 1;
		mainPanel.add(priceLabel, gbc);
		
		JLabel stockLabel = new JLabel("Product Stock");
		gbc.gridx = 3;
		gbc.gridy = 1;
		mainPanel.add(stockLabel, gbc);
		
		JLabel [] names = new JLabel [Application.products.size()];
		JLabel [] prices = new JLabel [Application.products.size()];
		JLabel [] stock = new JLabel [Application.products.size()];
		
		for (int i = 0; i < Application.products.size(); i++) {
			names [i] = new JLabel(Application.products.get(i).getName());
			gbc.gridx = 1;
			gbc.gridy = i+2;
			mainPanel.add(names [i], gbc);
			
			prices [i] = new JLabel(Double.toString(Application.products.get(i).getPrice()));
			gbc.gridx = 2;
			gbc.gridy = i+2;
			mainPanel.add(prices [i], gbc);
			
			stock [i] = new JLabel(Integer.toString(Application.products.get(i).getStock()));
			gbc.gridx = 3;
			gbc.gridy = i+2;
			mainPanel.add(stock [i], gbc);
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
				CustomerMenu cm = new CustomerMenu(frame);
				cm.screen("Customer Menu");
			}
		});
		buttonPanel.add(exit);
		return buttonPanel;
	}

}
