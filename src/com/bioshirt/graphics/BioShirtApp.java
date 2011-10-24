package com.bioshirt.graphics;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

public class BioShirtApp extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BioShirtApp frame = new BioShirtApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BioShirtApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		sl_contentPane.putConstraint(SpringLayout.NORTH, tabbedPane, 15, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, tabbedPane, -10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, tabbedPane, 410, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, tabbedPane, 640, SpringLayout.WEST, contentPane);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Login", null, panel, null);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		txtUsername = new JTextField();
		sl_panel.putConstraint(SpringLayout.SOUTH, txtUsername, -261, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, txtUsername, -246, SpringLayout.EAST, panel);
		txtUsername.setText("Username");
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		pwdPassword = new JPasswordField();
		sl_panel.putConstraint(SpringLayout.NORTH, pwdPassword, 6, SpringLayout.SOUTH, txtUsername);
		sl_panel.putConstraint(SpringLayout.WEST, pwdPassword, 0, SpringLayout.WEST, txtUsername);
		sl_panel.putConstraint(SpringLayout.EAST, pwdPassword, -246, SpringLayout.EAST, panel);
		pwdPassword.setText("Password");
		panel.add(pwdPassword);
		
		JButton btnLogin = new JButton("Login");
		sl_panel.putConstraint(SpringLayout.SOUTH, btnLogin, -10, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnLogin, -10, SpringLayout.EAST, panel);
		panel.add(btnLogin);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Graph", null, panel_1, null);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		JPanel panel_2 = new JPanel();
		sl_panel_1.putConstraint(SpringLayout.NORTH, panel_2, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, panel_2, 44, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, panel_2, 327, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, panel_2, 404, SpringLayout.WEST, panel_1);
		panel_2.setBackground(Color.WHITE);
		panel_1.add(panel_2);
		
		JComboBox comboBox = new JComboBox();
		sl_panel_1.putConstraint(SpringLayout.NORTH, comboBox, 35, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, comboBox, 6, SpringLayout.EAST, panel_2);
		sl_panel_1.putConstraint(SpringLayout.EAST, comboBox, -10, SpringLayout.EAST, panel_1);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Accelerometer : All", "Accelerometer : Date", "Cardiogram : All", "Cardiogram : Date", "Flex : All", "Flex : Date"}));
		panel_1.add(comboBox);
		
		JButton btnDate = new JButton("Choose Date");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnDate, 6, SpringLayout.SOUTH, comboBox);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnDate, -10, SpringLayout.EAST, panel_1);
		panel_1.add(btnDate);
		
		JButton btnExecute = new JButton("Execute");
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnExecute, 0, SpringLayout.SOUTH, panel_2);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnExecute, -10, SpringLayout.EAST, panel_1);
		panel_1.add(btnExecute);
	}
}
