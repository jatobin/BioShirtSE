package com.bioshirt.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class SensorTimeInstance {

	AccelerometerData acdata;
	CardioData cardata;
	FlexData flexdata;
	Device device;
	Timestamp date;
	String rawHex;
	
	public SensorTimeInstance(Device device, Timestamp date, String rawHex) {
		parseRawHex(rawHex);
		this.rawHex = rawHex;
		this.device = device;
		this.date = date;
	}
	
	public String getRawHex() {
		return rawHex;
	}

	public void setRawHex(String rawHex) {
		this.rawHex = rawHex;
	}
	
	public AccelerometerData getAcdata() {
		return acdata;
	}
	public void setAcdata(AccelerometerData acdata) {
		this.acdata = acdata;
	}
	public CardioData getCardata() {
		return cardata;
	}
	public void setCardata(CardioData cardata) {
		this.cardata = cardata;
	}
	public FlexData getFlexdata() {
		return flexdata;
	}
	public void setFlexdata(FlexData flexdata) {
		this.flexdata = flexdata;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}


	
	private void parseRawHex(String rawHex) {
		if (rawHex.length() >= 4 ) {
			String flexsubsequence = rawHex.substring(0, 4);
			this.flexdata = new FlexData(flexsubsequence);
		} 
		//TODO as 
		if (rawHex.length() >= 10) {
			String acclsubsequence = rawHex.substring(4, 9);
			acdata = new AccelerometerData();
			acdata.setAccData(Integer.decode("0x" +acclsubsequence));
		}
		
		if (rawHex.length() >= 12) {
			String cardsubsequence = rawHex.substring(9, rawHex.length() - 1);
			this.cardata = new CardioData(Integer.decode("0x" + cardsubsequence));
		}
	}
	
	@Override
	public String toString() {
		return "" + device.getDeviceID() + " " + date.toString() + " " + rawHex;
		
		
	}
	

}
