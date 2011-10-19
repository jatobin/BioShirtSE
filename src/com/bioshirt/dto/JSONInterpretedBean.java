package com.bioshirt.dto;

import java.sql.Date;


public class JSONInterpretedBean {
	
	private CardioData cardioDato;
	private AccelerometerData accData;
	private FlexData flexData;
	private Device device;
	private Date date;

	public JSONInterpretedBean(CardioData cardioDato, AccelerometerData accData, FlexData flexData, Device device, Date date) {
		super();
		this.cardioDato = cardioDato;
		this.accData = accData;
		this.flexData = flexData;
		this.device = device;
		this.date = date;
	}

	
	public CardioData getCardioDato() {
		return cardioDato;
	}

	public void setCardioDato(CardioData cardioDato) {
		this.cardioDato = cardioDato;
	}

	public AccelerometerData getAccData() {
		return accData;
	}

	public void setAccData(AccelerometerData accData) {
		this.accData = accData;
	}
	
	public FlexData getFlexData() {
		return flexData;
	}


	public void setFlexData(FlexData flexData) {
		this.flexData = flexData;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	
	
	
	
	

}
