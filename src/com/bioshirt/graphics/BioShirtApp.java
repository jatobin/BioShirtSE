package com.bioshirt.graphics;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import org.apache.commons.codec.binary.Hex;

import com.bioshirt.dao.STIDAO;
import com.bioshirt.dao.STIDAOImpl;
import com.bioshirt.dto.Device;
import com.bioshirt.dto.SensorTimeInstance;
import com.bioshirt.services.SimpleRead;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BioShirtApp extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
//	protected static SimpleRead sr = null;
	private static final JLabel flexValue = new JLabel("Flex Value");
	private static final JLabel cardValue = new JLabel("Card Value");
	private static final JLabel acceValue = new JLabel("Acce Value");
	private static final Random r = new Random();
//	private static final STIDAO stidao = new STIDAOImpl();
	
//	private static Device device = new Device("TESTDEVICEID");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (true) {
					
					System.out.println("Running poller for new characters");

//					 sr = SimpleRead.getInstance();
//					flexValue.setText(String.valueOf((r.nextLong())));
					byte[] bytes = new byte[2];
					//replace next lines with code to get the input from serial io
					r.nextBytes(bytes);
					String randomHex = Hex.encodeHexString(bytes);
					char c = randomHex.charAt(0);
					randomHex = randomHex.replace(c, '0');
					flexValue.setText(randomHex);
					Integer value = Integer.decode("0x"+randomHex);
					flexValue.setForeground(colorMapper(2457, value ));
					
					//replace next lines with code to get the input from serial io
					r.nextBytes(bytes);
					String randomHexTwo = Hex.encodeHexString(bytes);
					c = randomHexTwo.charAt(0);
					randomHexTwo = randomHexTwo.replace(c, '0');
					acceValue.setText(randomHexTwo);
					value = Integer.decode("0x"+randomHexTwo);
					acceValue.setForeground(colorMapper(2457, value ));
					
					r.nextBytes(bytes);
					String randomHexThree = Hex.encodeHexString(bytes);
					c = randomHexThree.charAt(0);
					randomHexThree = randomHexThree.replace(c, '0');
					cardValue.setText(randomHexThree);
					value = Integer.decode("0x"+randomHexThree);
					cardValue.setForeground(colorMapper(2457, value ));
					
					
					System.out.println("random hex:  " + randomHex + randomHexTwo + randomHexThree);
					
//					Timestamp date = new Timestamp(System.currentTimeMillis());
//					stidao.insertSTI(new SensorTimeInstance(device, date, randomHex));


					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.print("Interrupted");
						e.printStackTrace();
					}
				}

			}
			
			private Color colorMapper(Integer expected, Integer actual) {
                //increase if too high
                int red = 0;
                //increase if too low
                int blue = 0;
                //decrease based on deviation
                int green = 200;
                //TODO fix this again
                
                if (actual < expected) {
                    blue +=Math.abs(expected - actual);
                    green -= Math.abs(expected - actual);
                } else if (expected < actual) {
                    red +=Math.abs(expected - actual);
                    green -= Math.abs(expected - actual);
                } else {
                    green += 50;
                }
                
                if (red > 256) {
                    red = 254;
                    green = 0;
                }
                
                if (blue > 256) {
                    blue = 254;
                    green = 0;
                }
                
                if (green > 256) {
                	green = 256;
                } else if (green < 0) {
                	green = 0;
                }

                Color c = new Color(red,green,blue);
                System.out.println(c);
                return c;
                
            }

        });
		
		
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
		
//		try {
//			sr = SimpleRead.getInstance();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
	
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
		
		JPanel loginPanel = new JPanel();
		tabbedPane.addTab("Login", null, loginPanel, null);
		SpringLayout sl_loginPanel = new SpringLayout();
		loginPanel.setLayout(sl_loginPanel);
		
		txtUsername = new JTextField();
		sl_loginPanel.putConstraint(SpringLayout.SOUTH, txtUsername, -261, SpringLayout.SOUTH, loginPanel);
		sl_loginPanel.putConstraint(SpringLayout.EAST, txtUsername, -246, SpringLayout.EAST, loginPanel);
		txtUsername.setText("Username");
		loginPanel.add(txtUsername);
		txtUsername.setColumns(10);
		
		pwdPassword = new JPasswordField();
		sl_loginPanel.putConstraint(SpringLayout.NORTH, pwdPassword, 6, SpringLayout.SOUTH, txtUsername);
		sl_loginPanel.putConstraint(SpringLayout.WEST, pwdPassword, 0, SpringLayout.WEST, txtUsername);
		sl_loginPanel.putConstraint(SpringLayout.EAST, pwdPassword, -246, SpringLayout.EAST, loginPanel);
		pwdPassword.setText("Password");
		loginPanel.add(pwdPassword);
		
		JButton btnLogin = new JButton("Login");
		sl_loginPanel.putConstraint(SpringLayout.SOUTH, btnLogin, -10, SpringLayout.SOUTH, loginPanel);
		sl_loginPanel.putConstraint(SpringLayout.EAST, btnLogin, -10, SpringLayout.EAST, loginPanel);
		loginPanel.add(btnLogin);
		
		JPanel livePanel = new JPanel();
		tabbedPane.addTab("Live", null, livePanel, null);
		SpringLayout sl_livePanel = new SpringLayout();
		livePanel.setLayout(sl_livePanel);
		
		JPanel displayPanel = new JPanel();
		sl_livePanel.putConstraint(SpringLayout.NORTH, displayPanel, 10, SpringLayout.NORTH, livePanel);
		sl_livePanel.putConstraint(SpringLayout.WEST, displayPanel, 44, SpringLayout.WEST, livePanel);
		sl_livePanel.putConstraint(SpringLayout.SOUTH, displayPanel, 327, SpringLayout.NORTH, livePanel);
		sl_livePanel.putConstraint(SpringLayout.EAST, displayPanel, 404, SpringLayout.WEST, livePanel);
		displayPanel.setBackground(Color.WHITE);
		livePanel.add(displayPanel);
		SpringLayout sl_displayPanel = new SpringLayout();
		displayPanel.setLayout(sl_displayPanel);
		
		JLabel lblFlex = new JLabel("Flex");
		sl_displayPanel.putConstraint(SpringLayout.NORTH, flexValue, 0, SpringLayout.NORTH, lblFlex);
		sl_displayPanel.putConstraint(SpringLayout.NORTH, lblFlex, 29, SpringLayout.NORTH, displayPanel);
		displayPanel.add(lblFlex);
		displayPanel.add(flexValue);
		
		JLabel acceLabel = new JLabel("Acce");
		sl_displayPanel.putConstraint(SpringLayout.WEST, lblFlex, 0, SpringLayout.WEST, acceLabel);
		sl_displayPanel.putConstraint(SpringLayout.NORTH, acceLabel, 91, SpringLayout.NORTH, displayPanel);
		displayPanel.add(acceLabel);
		
		
		sl_displayPanel.putConstraint(SpringLayout.EAST, flexValue, 0, SpringLayout.EAST, acceValue);
		sl_displayPanel.putConstraint(SpringLayout.NORTH, acceValue, 91, SpringLayout.NORTH, displayPanel);
		sl_displayPanel.putConstraint(SpringLayout.WEST, acceValue, 163, SpringLayout.WEST, displayPanel);
		sl_displayPanel.putConstraint(SpringLayout.EAST, acceLabel, -5, SpringLayout.WEST, acceValue);
		displayPanel.add(acceValue);
		
		JLabel cardLabel = new JLabel("Card");
		sl_displayPanel.putConstraint(SpringLayout.WEST, cardLabel, 0, SpringLayout.WEST, acceLabel);
		sl_displayPanel.putConstraint(SpringLayout.SOUTH, cardLabel, -137, SpringLayout.SOUTH, displayPanel);
		displayPanel.add(cardLabel);
		
		
		sl_displayPanel.putConstraint(SpringLayout.NORTH, cardValue, 0, SpringLayout.NORTH, cardLabel);
		sl_displayPanel.putConstraint(SpringLayout.WEST, cardValue, 6, SpringLayout.EAST, cardLabel);
		displayPanel.add(cardValue);
		
		JPanel graphPanel = new JPanel();
		tabbedPane.addTab("Graph", null, graphPanel, null);
		SpringLayout sl_graphPanel = new SpringLayout();
		graphPanel.setLayout(sl_graphPanel);
		
		JPanel graphDisplay = new JPanel();
		sl_graphPanel.putConstraint(SpringLayout.WEST, graphDisplay, 10, SpringLayout.WEST, graphPanel);
		sl_graphPanel.putConstraint(SpringLayout.EAST, graphDisplay, 475, SpringLayout.WEST, graphPanel);
		graphDisplay.setBackground(Color.WHITE);
		sl_graphPanel.putConstraint(SpringLayout.NORTH, graphDisplay, 10, SpringLayout.NORTH, graphPanel);
		sl_graphPanel.putConstraint(SpringLayout.SOUTH, graphDisplay, 339, SpringLayout.NORTH, graphPanel);
		graphPanel.add(graphDisplay);
		
		JButton button = new JButton("Choose Date");
		sl_graphPanel.putConstraint(SpringLayout.EAST, button, -10, SpringLayout.EAST, graphPanel);
		graphPanel.add(button);
		
		JButton button_1 = new JButton("Execute");
		sl_graphPanel.putConstraint(SpringLayout.NORTH, button_1, 41, SpringLayout.SOUTH, button);
		sl_graphPanel.putConstraint(SpringLayout.EAST, button_1, -10, SpringLayout.EAST, graphPanel);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		graphPanel.add(button_1);
		
		JComboBox comboBox = new JComboBox();
		sl_graphPanel.putConstraint(SpringLayout.WEST, comboBox, 20, SpringLayout.EAST, graphDisplay);
		sl_graphPanel.putConstraint(SpringLayout.EAST, comboBox, -10, SpringLayout.EAST, graphPanel);
		sl_graphPanel.putConstraint(SpringLayout.NORTH, button, 6, SpringLayout.SOUTH, comboBox);
		sl_graphPanel.putConstraint(SpringLayout.NORTH, comboBox, 10, SpringLayout.NORTH, graphPanel);
		graphPanel.add(comboBox);
	}
}
