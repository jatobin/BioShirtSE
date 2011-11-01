package com.bioshirt.dto;

public class Device {
	
	private String deviceID;
	
	public Device(String deviceID) {
		super();
		this.deviceID = deviceID;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	@Override
	public boolean equals(Object obj) {
		Device dev = (Device) obj;
		return this.deviceID.equalsIgnoreCase(dev.getDeviceID());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return deviceID;
	}
	
	
	
	
	
	

}
