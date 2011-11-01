package com.bioshirt.services;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.comm.UnsupportedCommOperationException;

import org.apache.commons.codec.binary.Hex;

public class SimpleRead implements Runnable, SerialPortEventListener {
    static CommPortIdentifier portId;
    static Enumeration portList;
    private static SimpleRead sr;
    InputStream inputStream;
    SerialPort serialPort;
    Thread readThread;
    static char[] readHex = null;

    public static void main(String[] args) {
        portList = CommPortIdentifier.getPortIdentifiers();
        System.out.println("Waiting for serial input..."); // Display the string.
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            System.out.printf("Identifier: %s\n", portId.getName());
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                 if (portId.getName().equals("/dev/tty.usbserial-FTF87YWQ")) {
                	 System.out.print("Found an input");
                    SimpleRead reader = new SimpleRead();
                }
            }
        }
    }
    
    public static SimpleRead getInstance() {
    	if (sr != null) {
    		return sr;
    	} else {
    		sr = new SimpleRead();
    		return sr;
    	}
    	
    }

    private SimpleRead() {
//    	  portList = CommPortIdentifier.getPortIdentifiers();
//    	  assert portId.getName().equals("COM5");
//    		System.out.println("Waiting for serial input..."); // Display the string.
//    	        while (portList.hasMoreElements()) {
//    	            portId = (CommPortIdentifier) portList.nextElement();
//    	            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
//    	                 if (portId.getName().equals("COM5")) {
//    	                	 break;
//    	                }
//    	            }    
//    	        }
        try {
            serialPort = (SerialPort) portId.open("SimpleReadApp", 2000);
        } catch (PortInUseException e) {System.out.println(e);}
        try {
            inputStream = serialPort.getInputStream();
        } catch (IOException e) {System.out.println(e);}
	try {
            serialPort.addEventListener(this);
	} catch (TooManyListenersException e) {System.out.println(e);}
        serialPort.notifyOnDataAvailable(true);
        try {
            serialPort.setSerialPortParams(1000000,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException e) {System.out.println(e);}
        readThread = new Thread(this);
        readThread.start();
    }

    public void run() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {System.out.println(e);}
    }

    public void serialEvent(SerialPortEvent event) {
        switch(event.getEventType()) {
        case SerialPortEvent.BI:
        case SerialPortEvent.OE:
        case SerialPortEvent.FE:
        case SerialPortEvent.PE:
        case SerialPortEvent.CD:
        case SerialPortEvent.CTS:
        case SerialPortEvent.DSR:
        case SerialPortEvent.RI:
        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
            break;
        case SerialPortEvent.DATA_AVAILABLE:
            byte[] readBuffer = new byte[20];
            try {
                while (inputStream.available() > 0) {
                    int numBytes = inputStream.read(readBuffer);
                }
                readHex = Hex.encodeHexString(readBuffer).toCharArray();
                System.out.print(readHex + "\n" );
		// ignore random chars appearing in the command line, it`s cuz of hex
            } catch (IOException e) {System.out.println(e);}
            break;
        }
    }
    
    public synchronized char[] getHexString() {
    	return readHex;
    	
    }
}