package com.bioshirt.dto;



public class FlexData {
	public static final Integer EXPECTED_FLEX_VALUE = 3210;
	private String rawHex;
	private Integer convertedValue;
	
	public FlexData(String rawHex) {
		this.rawHex = rawHex;
		this.convertedValue = Integer.decode("0x"+rawHex);
	}

	public String getRawHex() {
		return rawHex;
	}

	public void setRawHex(String rawHex) {
		this.rawHex = rawHex;
	}

	public Integer getConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(Integer convertedValue) {
		this.convertedValue = convertedValue;
	}

}
