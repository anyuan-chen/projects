package com.chen.graphite.gui.manager;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

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
import com.chen.graphite.model.CustomerOrder;


public class ManagerExportProducts extends MenuTemplate {
	JFrame frame;
	JTextField fileNameField;
	PrintWriter writer2;
	PrintWriter writer;
	
	public ManagerExportProducts(JFrame f) {
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
		JPanel exportProductPanel = new JPanel();
		JLabel fileName = new JLabel("File Name", 10);
		fileNameField = new JTextField("output.txt", 10);

		exportProductPanel.add(fileName);
		exportProductPanel.add(fileNameField);

		return exportProductPanel;
	}


	@Override
	public JPanel buildButtonPanel() {

		JPanel buttonPanel = new JPanel();
		JButton submitAdd = new JButton(new AbstractAction("Submit") {
			public void actionPerformed(ActionEvent e) {



				String fileName = fileNameField.getText();
				try {
					File myObj = new File(fileName);
					
					writer = new PrintWriter(System.out);
					writer2 = new PrintWriter(fileName);

					writer.println("######################################################################");
					writer2.println("######################################################################");

					for(int i = 0;  i < Application.orders.size(); i++) {
						CustomerOrder co = Application.orders.get(i);
						String customer = "Customer " + i;
						writer.println(customer + " \tTax Rate: " + co.getTaxRate() + "\tDiscount Code:" + co.getDiscountCode() );
						writer.println("---------------------------------------------------------------------");
						writer.println();
						writer2.println("---------------------------------------------------------------------");
						writer.println();

						ArrayList<CartItem> cartItems = co.getCartItems();
						for(int j = 0; j < cartItems.size(); j++) {
							CartItem item = Application.cartItems.get(j); 
							String itemSummary = item.getName() + "\t\t" + item.getPrice() + "\t\t" + item.getAmountToBuy() + "\n";
							writer.write(itemSummary);
							writer2.write(itemSummary);
						}
					}
					writer.println("The total revenue is " + Application.revenue);
					writer2.println("The total revenue is " + Application.revenue);
					writer.println("######################################################################");
					writer2.println("######################################################################");
					writer.flush();
					writer.close();
					writer2.flush();
					writer2.close();
					
					JOptionPane.showMessageDialog(frame, "Successful export", "Products",
							JOptionPane.INFORMATION_MESSAGE);
					
				} catch (Exception ex) {
					System.out.println("Write File Issue occurred.");
					JOptionPane.showMessageDialog(frame, "Cannot Write to file",
							"File exists; Please choose another name", JOptionPane.ERROR_MESSAGE);
					
					writer2.close();
					
					writer.close();

				}	
				


			}
		});
		
		JButton exitAdd = new JButton(new AbstractAction("Exit to Manager Menu") {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				ManagerMenu mm = new ManagerMenu(frame);
				mm.screen("Manager Menu");
			}
		});
		

		buttonPanel.add(exitAdd);
		buttonPanel.add(submitAdd);
		
		return buttonPanel;
	}
}

