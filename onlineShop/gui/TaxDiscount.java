package com.chen.graphite.gui.customer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.chen.graphite.gui.MenuTemplate;
import com.chen.graphite.utilities.Utilities;

public class TaxDiscount extends MenuTemplate {
	String provinceSelected = "Ontario";
	JFrame frame;
	JTextField discountField;
	public static int discountCode;
	public static int taxRate;
	
	public TaxDiscount(JFrame f) {
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
		GridLayout gl = new GridLayout(2,1);
		mainPanel.setLayout(gl);
		
		JPanel taxPanel = new JPanel();
		JLabel whatProvince = new JLabel("What province do you live in?");
		String[] provinces = { "Ontario", "Quebec", "Nova Scotia", "New Brunswick", "Prince Edward Island",
				"British Columbia", "Alberta", "Saskatchewan", "Manitoba", "Yukon Territory", "Northwest Territory",
				"Nunavut", "Newfoundland and Labrador" };
		JComboBox provinceList = new JComboBox(provinces);
		provinceList.setEditable(true);
		provinceList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				provinceSelected = (String) cb.getSelectedItem();
			}
		});
		taxPanel.add(whatProvince);
		taxPanel.add(provinceList);
		
		JPanel discountPanel = new JPanel();
		JLabel discountLabel = new JLabel("Enter a number from 1 to 100 for a random discount");
		discountField = new JTextField("", 15);
		discountPanel.add(discountLabel);
		discountPanel.add(discountField);
		
		mainPanel.add(taxPanel);
		mainPanel.add(discountPanel);
		
		return mainPanel;
	}

	@Override
	public JPanel buildButtonPanel() {
		JPanel buttonPanel = new JPanel ();
		JButton exit = new JButton(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				CustomerMenu mm = new CustomerMenu(frame);
				mm.screen("Manager Menu");
			}
		});
		
		JButton cont = new JButton(new AbstractAction("Continue") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Utilities.isInt(discountField.getText()) == true && Integer.parseInt(discountField.getText()) < 101 && Integer.parseInt(discountField.getText()) > 0) {
					int userDiscount = Integer.parseInt(discountField.getText());
					discountCode = Utilities.discountGiver(userDiscount);
					taxRate = Utilities.taxGiver(provinceSelected);
					frame.getContentPane().removeAll();
					CheckoutMessage cm = new CheckoutMessage(frame);
					cm.screen("Final Checkout");
				}
				else {
					JOptionPane.showMessageDialog(frame, "Invalid input.", "Faliure",
							JOptionPane.ERROR_MESSAGE);
				}
				
					
			}
		});		
		
		buttonPanel.add(exit);
		buttonPanel.add(cont);
		return buttonPanel;
	}

}
