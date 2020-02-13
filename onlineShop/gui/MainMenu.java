package com.chen.graphite.gui;

import java.awt.event.ActionEvent;

import javax.swing.*;

import com.chen.graphite.gui.customer.CustomerMenu;
import com.chen.graphite.gui.manager.ManagerMenu;

public class MainMenu extends MenuTemplate {
	JFrame frame;
	JRadioButton manager, customer;

	public MainMenu(JFrame f) {
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
		manager = new JRadioButton("Manager");
		customer = new JRadioButton("Customer");
		ButtonGroup bg = new ButtonGroup();
		bg.add(manager);
		bg.add(customer);
		mainPanel.add(manager);
		mainPanel.add(customer);
		return mainPanel;
	}

	@Override
	public JPanel buildButtonPanel() {
		JPanel buttonPanel = new JPanel();
		JButton exit = new JButton(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		JButton submit = new JButton(new AbstractAction("Submit") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (manager.isSelected()) {
					frame.getContentPane().removeAll();
					ManagerMenu mm = new ManagerMenu(frame);
					mm.screen("Manager Menu");
				} else if (customer.isSelected()) {
					if (Application.products.size() > 0) {
						frame.getContentPane().removeAll();
						CustomerMenu cm = new CustomerMenu(frame);
						cm.screen("Customer Menu");
					} else {
						JOptionPane.showMessageDialog(frame, "No products have been added to the shop", "Faliure",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Please select an option", "Faliure",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonPanel.add(exit);
		buttonPanel.add(submit);
		return buttonPanel;
	}

}
