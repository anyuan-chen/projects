package com.chen.graphite.gui;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public abstract class MenuTemplate {
	
	private JFrame frame;
	
	public abstract JPanel buildTitlePanel(String titleName);
	public abstract JPanel buildMainPanel();
	public abstract JPanel buildButtonPanel();
	
	
	public MenuTemplate(JFrame f) {
		frame = f;
	}
	
	public JPanel titlePanel(String titleName){
		JPanel titlePanel = buildTitlePanel(titleName);
		return titlePanel;
	}
	public JPanel mainPanel() {
		JPanel mainPanel = buildMainPanel();
		return mainPanel;
	}
	public JPanel buttonPanel() {
		JPanel buttonPanel = buildButtonPanel();
		return buttonPanel;
	}
	
	public void screen(String title) {
		JPanel titlePanel = titlePanel(title);
		titlePanel.setBackground(new Color (0,200,200));
		ImageIcon image = new ImageIcon("store.PNG");
		JLabel imageLabel = new JLabel(image);
		titlePanel.add(imageLabel);
		
		JPanel mainPanel = mainPanel();
		JPanel buttonPanel = buttonPanel();
		buttonPanel.setBackground(new Color (0,200,200));

	
		frame.add(titlePanel, BorderLayout.NORTH);
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.revalidate();
		frame.repaint();
		
	}
	
}

