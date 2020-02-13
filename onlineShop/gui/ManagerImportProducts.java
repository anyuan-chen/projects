package com.chen.graphite.gui.manager;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.chen.graphite.gui.Application;
import com.chen.graphite.gui.MenuTemplate;
import com.chen.graphite.model.Product;



public class ManagerImportProducts extends MenuTemplate {
	JFrame frame;
	JTextField fileNameField;
	public static ArrayList <String> addedFiles = new ArrayList <String>();

	public ManagerImportProducts(JFrame f) {
		super(f);
		this.frame = f;
		System.out.println(this);
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
		JPanel importProductPanel = new JPanel();

		JLabel fileName = new JLabel("File Name", 10);
		fileNameField = new JTextField("input.txt", 10);

		importProductPanel.add(fileName);
		importProductPanel.add(fileNameField);

		return importProductPanel;
	}

	@Override
	public JPanel buildButtonPanel() {
		JPanel buttonPanel = new JPanel();

		JButton submitAdd = new JButton(new AbstractAction("Submit") {
			public void actionPerformed(ActionEvent e) {
				boolean invalid = false;

				String fileName = fileNameField.getText();
				/////////////////////////
				try {
					File file = new File(fileName);
					Scanner sc = new Scanner(file);
					while (sc.hasNextLine()) {
						String line = sc.nextLine();

						if (!line.startsWith("#") && !"".equals(line.trim())) {
							String fields[] = line.split(" ");
							Product p = new Product(fields[0].trim(), Double.parseDouble(fields[1].trim()),
									Integer.parseInt(fields[2].trim()));
							Application.products.add(p);
						}
					}
					JOptionPane.showMessageDialog(frame, "Successful import", "Products",
							JOptionPane.INFORMATION_MESSAGE);
					sc.close();
				} catch (FileNotFoundException ex) {
					System.out.println("FileNotFoundException occurred.");
					JOptionPane.showMessageDialog(frame, "File Not Found", "Not Enough Products",
							JOptionPane.ERROR_MESSAGE);
					invalid = true;
				} catch (Exception ex) {
					System.out.println("Exception occurred.");
					JOptionPane.showMessageDialog(frame, "Read file failed", "Read file failed", JOptionPane.ERROR_MESSAGE);
					invalid = true;
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
