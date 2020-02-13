package com.chen.graphite.gui.manager;

import com.chen.graphite.gui.Application;
import com.chen.graphite.gui.MainMenu;
import com.chen.graphite.gui.MenuTemplate;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class ManagerMenu extends MenuTemplate {
	JFrame frame;
	JRadioButton addProduct;
	JRadioButton addStock;
	JRadioButton displayProducts;
	JRadioButton displayRevenue;
	JRadioButton impt;
	JRadioButton expt;

	public ManagerMenu(JFrame f) {
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
		JPanel mainPanel = new JPanel(new GridLayout(0,1));
		ButtonGroup bg = new ButtonGroup();

		addProduct = new JRadioButton("Add Product");
		addStock = new JRadioButton("Add Stock");
		displayProducts = new JRadioButton("Display Products");
		displayRevenue = new JRadioButton("Display Revenue");
		impt = new JRadioButton("Import products through file");
		expt = new JRadioButton("Export accounting report through file");

		bg.add(addProduct);
		bg.add(displayProducts);
		bg.add(displayRevenue);
		bg.add(addStock);
		bg.add(impt);
		bg.add(expt);

		mainPanel.add(addProduct);
		mainPanel.add(addStock);
		mainPanel.add(displayProducts);
		mainPanel.add(displayRevenue);
		mainPanel.add(impt);
		mainPanel.add(expt);

		return mainPanel;
	}

	@Override
	public JPanel buildButtonPanel() {
		JPanel buttonPanel = new JPanel();
		JButton submit = new JButton(new AbstractAction("Submit") {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (addProduct.isSelected()) {
					if (Application.products.size() < 10) {
						frame.getContentPane().removeAll();
						AddProduct ap = new AddProduct(frame);
						ap.screen("Add Product");
					} else {
						JOptionPane.showMessageDialog(frame, "The product limit of 10 has been reached", "Faliure",
								JOptionPane.ERROR_MESSAGE);
					}

				} else if (addStock.isSelected()) {
					if (Application.products.size() > 0) {
						frame.getContentPane().removeAll();
						AddStock as = new AddStock(frame);
						as.screen("Add Stock");
					}
					else {
						JOptionPane.showMessageDialog(frame, "No products to add stock to", "Faliure",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (displayProducts.isSelected()) {
					if (Application.products.size() > 0) {
						frame.getContentPane().removeAll();
						DisplayProducts dp = new DisplayProducts(frame);
						dp.screen("Display Products");
					}
					else {
						JOptionPane.showMessageDialog(frame, "No products to display", "Faliure",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (displayRevenue.isSelected()) {
					frame.getContentPane().removeAll();
					DisplayRevenue dr = new DisplayRevenue(frame);
					dr.screen("Display Revenue");
				} else if (impt.isSelected()) {
					frame.getContentPane().removeAll();
					ManagerImportProducts mip = new ManagerImportProducts(frame);
					mip.screen("Import products");
					
				} else if (expt.isSelected()) {
					frame.getContentPane().removeAll();
					ManagerExportProducts mip = new ManagerExportProducts(frame);
					mip.screen("Export products");

				} 
			}
		});
		JButton exit = new JButton(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Exit")) {
					frame.getContentPane().removeAll();
					MainMenu mm = new MainMenu(frame);
					mm.screen("Main Menu");
				}
			}

		});
		buttonPanel.add(exit);
		buttonPanel.add(submit);
		return buttonPanel;
	}

}
