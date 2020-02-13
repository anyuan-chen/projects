package com.chen.graphite.gui.manager;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.chen.graphite.gui.Application;
import com.chen.graphite.gui.MenuTemplate;

public class DisplayRevenue extends MenuTemplate {
	JFrame frame;

	public DisplayRevenue(JFrame f) {
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
		JLabel revenue = new JLabel("The total revenue is " + Double.toString(Application.revenue));
		mainPanel.add(revenue);
		return mainPanel;
	}

	@Override
	public JPanel buildButtonPanel() {
		JPanel buttonPanel = new JPanel();
		JButton exit = new JButton(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				ManagerMenu mm = new ManagerMenu(frame);
				mm.screen("Manager Menu");
			}
		});
		buttonPanel.add(exit);
		return buttonPanel;
	}

}
