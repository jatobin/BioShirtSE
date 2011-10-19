package com.bioshirt.dto;

import java.util.ArrayList;

public class FlexData {

	private ArrayList<Double> doublePoints;

	public FlexData(ArrayList<Double> doublePoints) {
		super();
		this.doublePoints = doublePoints;
	}

	public ArrayList<Double> getDoublePoints() {
		return doublePoints;
	}

	public void setDoublePoints(ArrayList<Double> DoublePoints) {
		this.doublePoints = doublePoints;
	}
}
