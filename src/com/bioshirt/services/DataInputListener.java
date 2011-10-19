/*
 * With help from http://codeglobe.blogspot.com/2009/02/serial-port-communication-in-java.html
 */

package com.bioshirt.services;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;

public class DataInputListener implements Runnable {

	public DataInputListener() {
		
	}
	@Override
	public void run() {
		CommPortIdentifier portIdentifier = null;
		try {
			portIdentifier = CommPortIdentifier.getPortIdentifier("COM1");
		} catch(NoSuchPortException nse) {
			nse.printStackTrace();
		}
		if (portIdentifier.isCurrentlyOwned()) {
			System.out.println("Port in use!");
		}
		else {
			try {
				//name
				System.out.println(portIdentifier.getName());
	
				SerialPort serialPort = (SerialPort) portIdentifier.open("ListPortClass", 300);
				int b = serialPort.getBaudRate();
				System.out.println(Integer.toString(b));
				
				serialPort.setSerialPortParams(300, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
				OutputStream mOutputToPort = serialPort.getOutputStream();
				InputStream mInputFromPort = serialPort.getInputStream();
				String mValue = "AT\r";
				System.out.println("beginning to Write . \r\n");
				
				mOutputToPort.write(mValue.getBytes());
				System.out.println("AT Command Written to Port. \r\n");
				
				mOutputToPort.flush();
				System.out.println("Waiting for Reply \r\n");
				Thread.sleep(500);
				byte mBytesIn [] = new byte[20];
				mInputFromPort.read(mBytesIn);
				mInputFromPort.read(mBytesIn);
				String value = new String(mBytesIn);
				System.out.println("Response from Serial Device: "+value);
				
				mOutputToPort.close(); 
				mInputFromPort.close();
		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
		}

	}
}
}
	
