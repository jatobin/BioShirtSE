package com.bioshirt.dto;

public class CardioData  {
	
	
	private Integer BPMrate;
	
	public CardioData(int bPMrate) {
		super();
		BPMrate = bPMrate;
	}

	public int getBPMrate() {
		return BPMrate;
	}

	public void setBPMrate(int bPMrate) {
		BPMrate = bPMrate;
	}




}
