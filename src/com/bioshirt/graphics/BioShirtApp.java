package com.bioshirt.graphics;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.bioshirt.dto.FlexData;
import com.bioshirt.dto.SensorTimeInstance;
import com.bioshirt.services.SimpleRead;
import com.bioshirt.services.Utility;

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
	private static final STIDAO stidao = new STIDAOImpl();
	private JComboBox sensorComboBox;
	
	private static Device device = new Device("TESTDEVICEID");
	private JTextField txtYyyymmdd;
	private JPanel graphDisplay;

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
					
					randomHex = randomHex + randomHexTwo + randomHexThree;
					System.out.println("random hex:  " + randomHex + randomHexTwo + randomHexThree);
					
					Timestamp date = new Timestamp(System.currentTimeMillis());
					stidao.insertSTI(new SensorTimeInstance(device, date, randomHex));


					try {
						Thread.sleep(10000);
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
		
		graphDisplay = new JPanel();
		sl_graphPanel.putConstraint(SpringLayout.NORTH, graphDisplay, 10, SpringLayout.NORTH, graphPanel);
		sl_graphPanel.putConstraint(SpringLayout.WEST, graphDisplay, 33, SpringLayout.WEST, graphPanel);
		sl_graphPanel.putConstraint(SpringLayout.SOUTH, graphDisplay, 339, SpringLayout.NORTH, graphPanel);
		graphDisplay.setBackground(Color.WHITE);
		graphPanel.add(graphDisplay);
		
		JButton button = new JButton("Choose Date");
		sl_graphPanel.putConstraint(SpringLayout.EAST, button, -10, SpringLayout.EAST, graphPanel);
		graphPanel.add(button);
		
		JButton button_1 = new JButton("Execute");
		sl_graphPanel.putConstraint(SpringLayout.NORTH, button_1, 41, SpringLayout.SOUTH, button);
		sl_graphPanel.putConstraint(SpringLayout.EAST, button_1, -10, SpringLayout.EAST, graphPanel);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				Graphics2D g = (Graphics2D) graphDisplay.getGraphics();
		
				Integer height = graphDisplay.getHeight();
				Integer width = graphDisplay.getWidth();
				System.out.println("Height: " + height);
				System.out.println("Width: "  + width);
				
				String date = txtYyyymmdd.getText();
				String sensor = (String) sensorComboBox.getSelectedItem();
				
				ArrayList<SensorTimeInstance> stis = (ArrayList<SensorTimeInstance>) stidao.getSTIForTime(date);
				
				if (stis.size() == 0) {
					System.out.println("No data for date!");
					return;
				} else {
				g.drawString("Time", width / 2, height - 20);
				
					//scale the height
				
					//scale the width
					//for each point, scale it so it is a range of 10 to width, 10 to height.
	
				//calculate the interval
				//max - min / total
				Timestamp max = stis.get(stis.size() - 1).getDate();
				
				Integer tempMaxSum = 0;
				tempMaxSum += max.getHours() * 3600;
				tempMaxSum += max.getMinutes() * 60;
				tempMaxSum += max.getSeconds();
				System.out.println("MaxTime: " + tempMaxSum);
				
				
				Timestamp min = stis.get(0).getDate();
				Integer tempMinSum = 0;
				tempMinSum += min.getHours() * 3600;
				tempMinSum += min.getMinutes() * 60;
				tempMinSum += min.getMinutes();
				int range = tempMaxSum - tempMinSum;
				System.out.println("MinSum: " + tempMinSum);
				
				int rangeStep = width / stis.size();
				System.out.println("Range:"+ rangeStep);
				
//				int newHeight = tempMaxSum > 600 ? 600 : (image.getHeight() * 800)/image.getWidth();
				
				//divide everything by scale factor.
				//scale factor is determined by the max height of values / height of screen.
				// maxWidth of Values / width of screen
				
//				g.setBackground(Color.black);
//				g.drawOval(20, 20, 5, 5);
//				g.fillOval(20, 20, 5, 5);
//				g.fillOval(0, 0, 5, 5);
//				g.fillOval(10, 10, 5, 5);
//				g.fillOval(30, 30, 5, 5);
//					
				
				//get the data
				//parse for accelerometer
				if (sensor.equalsIgnoreCase("Accelerometer")) {
					g.setColor(Color.green);
					//TODO remaining logic goes in each of these
					Integer maxY = Integer.MIN_VALUE;
					Integer minY = Integer.MAX_VALUE;
					
					//get Maximum and minimum
					for (SensorTimeInstance s : stis) {
						if (s.getAcdata().getAccData() > maxY) {
							maxY = s.getAcdata().getAccData();
						}
						
						if (s.getFlexdata().getConvertedValue() < minY) {
							minY = s.getAcdata().getAccData();
						}
					}
					//scale factor is max 
					int scaleX = Math.round(range / width);
					int scaleY = Math.round((maxY - minY) / height);
					//TODO change this
					g.drawLine(0, FlexData.EXPECTED_FLEX_VALUE / scaleY, width, FlexData.EXPECTED_FLEX_VALUE / scaleY);
					g.setColor(Color.black);
					Integer tempX = Utility.getValue(stis.get(0).getDate()) / scaleX;
					Integer tempY = stis.get(0).getAcdata().getAccData() / scaleY;
					Integer curX = 0;
					Integer curY = 0;
					System.out.println(tempX + " " + tempY);
					for (SensorTimeInstance s : stis) {
						curY = (s.getAcdata().getAccData() / scaleY) ;
						curX += rangeStep;
						System.out.println("ScaledX: " + curX + ", ScaledY: " + curY);
						g.fillOval(curX, height - curY, 5, 5);
						g.drawLine(curX, height - curY, tempX, height - tempY);
						tempX = curX;
						tempY = curY;
					}
					
				}
				
				//parse for cardiometer
				else if (sensor.equalsIgnoreCase("Cardiometer")) {
					g.setColor(Color.green);
					//TODO remaining logic goes in each of these
					Integer maxY = Integer.MIN_VALUE;
					Integer minY = Integer.MAX_VALUE;
					
					//get Maximum and minimum
					for (SensorTimeInstance s : stis) {
						if (s.getCardata().getBPMrate() > maxY) {
							maxY = s.getCardata().getBPMrate();
						}
						
						if (s.getCardata().getBPMrate() < minY) {
							minY = s.getCardata().getBPMrate();
						}
					}
					//scale factor is max 
					int scaleX = Math.round(range / width);
					int scaleY = Math.round((maxY - minY) / height);
					if (scaleY == 0) {
						scaleY = 1;
					}
					System.out.println("Scale X: " + scaleX + ", Scale Y: " + scaleY);
					//TODO change this
					g.drawLine(0, 140, width, 140);
					g.setColor(Color.black);
					Integer tempX =  Utility.getValue(stis.get(0).getDate()) / scaleX;
					Integer tempY = stis.get(0).getCardata().getBPMrate()  / scaleY;
					Integer curX = 0;
					Integer curY = 0;
					System.out.println(tempX + " " + tempY);
					for (SensorTimeInstance s : stis) {
						curY = (s.getCardata().getBPMrate() / scaleY) ;
						curX += rangeStep;
						System.out.println("ScaledX: " + curX + ", ScaledY: " + curY);
						g.fillOval(curX, height - curY, 5, 5);
						g.drawLine(curX, height - curY, tempX, height - tempY);
						tempX = curX;
						tempY = curY;
					}
					
				}
					
					
				
				
				//parse for flex sensor
				else if (sensor.equalsIgnoreCase("Flex Sensor")) {
					g.setColor(Color.green);
					//TODO remaining logic goes in each of these
					Integer maxY = Integer.MIN_VALUE;
					Integer minY = Integer.MAX_VALUE;
					
					//get Maximum and minimum
					for (SensorTimeInstance s : stis) {
						if (s.getFlexdata().getConvertedValue() > maxY) {
							maxY = s.getFlexdata().getConvertedValue();
						}
						
						if (s.getFlexdata().getConvertedValue() < minY) {
							minY = s.getFlexdata().getConvertedValue();
						}
					}
					//scale factor is max 
					int scaleX = Math.round(range / width);
					int scaleY = Math.round((maxY - minY) / height);
					g.drawLine(0, FlexData.EXPECTED_FLEX_VALUE / scaleY, width, FlexData.EXPECTED_FLEX_VALUE / scaleY);
					g.setColor(Color.black);
					Integer tempX = stis.get(0).getFlexdata().getConvertedValue() / scaleX;
					Integer tempY = Utility.getValue(stis.get(0).getDate()) / scaleY;
					Integer curX = 0;
					Integer curY = 0;
					System.out.println(tempX + " " + tempY);
					for (SensorTimeInstance s : stis) {
						curY = (s.getFlexdata().getConvertedValue() / scaleY) ;
						curX += rangeStep;
						System.out.println("ScaledX: " + curX + ", ScaledY: " + curY);
						g.fillOval(curX, height - curY, 5, 5);
						g.drawLine(curX, height - curY, tempX, height - tempY);
						tempX = curX;
						tempY = curY;
					}
					
				}
				
				else {
				}
				//make calculate the range of time
				//calculate the range of y's
				//zip the points together
				//for each point 
				
				//if not in the correct format, dont execute
				//else execute
				
				
				
				}
				
			}
		});
		graphPanel.add(button_1);
		
		sensorComboBox = new JComboBox();
		sl_graphPanel.putConstraint(SpringLayout.EAST, graphDisplay, -6, SpringLayout.WEST, sensorComboBox);
		sl_graphPanel.putConstraint(SpringLayout.WEST, sensorComboBox, 478, SpringLayout.WEST, graphPanel);
		sl_graphPanel.putConstraint(SpringLayout.EAST, sensorComboBox, -10, SpringLayout.EAST, graphPanel);
		sensorComboBox.setModel(new DefaultComboBoxModel(new String[] {"Accelerometer", "Cardiometer", "Flex Sensor"}));
		sensorComboBox.setSelectedIndex(0);
		sl_graphPanel.putConstraint(SpringLayout.NORTH, button, 6, SpringLayout.SOUTH, sensorComboBox);
		sl_graphPanel.putConstraint(SpringLayout.NORTH, sensorComboBox, 10, SpringLayout.NORTH, graphPanel);
		graphPanel.add(sensorComboBox);
		
		txtYyyymmdd = new JTextField();
		sl_graphPanel.putConstraint(SpringLayout.WEST, txtYyyymmdd, 481, SpringLayout.WEST, graphPanel);
		txtYyyymmdd.setText("YYYY-MM-DD");
		sl_graphPanel.putConstraint(SpringLayout.SOUTH, txtYyyymmdd, -6, SpringLayout.NORTH, button_1);
		graphPanel.add(txtYyyymmdd);
		txtYyyymmdd.setColumns(10);
	}
}
