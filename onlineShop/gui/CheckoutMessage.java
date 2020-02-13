package com.chen.graphite.gui.customer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.chen.graphite.gui.Application;
import com.chen.graphite.gui.MenuTemplate;
import com.chen.graphite.model.CustomerOrder;
import com.chen.graphite.utilities.Utilities;

public class CheckoutMessage extends MenuTemplate{
	JFrame frame;
	public CheckoutMessage(JFrame f) {
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

		GridLayout lyt1 = new GridLayout(0,1);
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(lyt1);

		double totalPrice = Utilities.calculateTotalPrice();

		JLabel totalWithoutTax = new JLabel(
				"Your total without tax and discount is " + Double.toString(Utilities.moneyRounder(totalPrice)));
		JLabel totalWithoutTaxWithDiscount = new JLabel("Your total without tax and with discount is "
				+ Double.toString(Utilities.moneyRounder(totalPrice / TaxDiscount.discountCode)));
		JLabel totalWithTax = new JLabel("Your total with tax and without discount is "
				+ Double.toString(Utilities.moneyRounder(totalPrice * (1 + ((double) TaxDiscount.taxRate / 100)))));
		JLabel totalWithTaxWithDiscount = new JLabel("Your total with tax and discount is " + Double.toString(Utilities.moneyRounder((totalPrice/TaxDiscount.discountCode)* (1 + ((double) TaxDiscount.taxRate/100)))));                     
		JLabel success = new JLabel("Thank for purchasing a Graphite Labs product today");
		JLabel taxRateLabel = new JLabel("Your tax rate is " + Integer.toString(TaxDiscount.taxRate) + "%");
		String percentageDiscount;
		if (TaxDiscount.discountCode == 1) {
			percentageDiscount = "0%";
		}
		else if (TaxDiscount.discountCode == 2) {
			percentageDiscount = "50%";
		}
		else if (TaxDiscount.discountCode == 3) {
			percentageDiscount = "66.6%";
		} 
		else if (TaxDiscount.discountCode == 5) {
			percentageDiscount = "80%";
		}
		else {
			percentageDiscount = "100%";
		}
		JLabel discountLabel = new JLabel("Your discount rate is "+ percentageDiscount);
		contentPanel.add(taxRateLabel);
		contentPanel.add(totalWithoutTax);
		contentPanel.add(totalWithoutTaxWithDiscount);
		contentPanel.add(totalWithTaxWithDiscount);
		contentPanel.add(totalWithTax);
		contentPanel.add(discountLabel);
		contentPanel.add(success);
		
		Application.revenue += (totalPrice/TaxDiscount.discountCode) * (1 + (TaxDiscount.taxRate/100));
		CustomerOrder co = new CustomerOrder(Application.cartItems, TaxDiscount.taxRate, TaxDiscount.discountCode);
		Application.orders.add(co);
		Application.cartItems.clear();
		
		return contentPanel;
	}

	@Override
	public JPanel buildButtonPanel() {
		JPanel buttonPanel = new JPanel ();
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
