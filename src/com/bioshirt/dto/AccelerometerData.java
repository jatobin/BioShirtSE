package com.bioshirt.dto;

public class AccelerometerData {

	private Integer accData;
	
	public AccelerometerData() {
		super();
		this.accData = 0;
	}

	public Integer getAccData() {
		return accData;
	}

	public void setAccData(Integer accData) {
		this.accData = accData;
	}
	
	public void increment(Integer newStep) {
		accData = accData + newStep;
	}

}
