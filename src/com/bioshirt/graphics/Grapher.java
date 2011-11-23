package com.bioshirt.graphics;

import java.util.ArrayList;

import javax.swing.JPanel;

import com.bioshirt.dao.STIDAO;
import com.bioshirt.dao.STIDAOImpl;
import com.bioshirt.dto.SensorTimeInstance;



public class Grapher {
	
	private static final STIDAO stidao = new STIDAOImpl();
	
	public JPanel getPanelDataForDate(String date, String sensor) {

		ArrayList<SensorTimeInstance> stis = (ArrayList<SensorTimeInstance>) stidao.getSTIForTime(date);
		
		//parse for accelerometer
		if (sensor.equalsIgnoreCase("Accelerometer")) {
			for (SensorTimeInstance instance : stis) {
				System.out.println("X : " + instance.getDate() + " Y: " + instance.getAcdata().getAccData());
			}
			
		}
		
		//parse for cardiometer
		else if (sensor.equalsIgnoreCase("Cardiometer")) {
			for (SensorTimeInstance instance : stis) {
				System.out.println("X : " + instance.getDate() + " Y: " + instance.getCardata().getBPMrate());
			}
			
			
		}
		
		//parse for flex sensor
		else if (sensor.equalsIgnoreCase("Flex Sensor")) {
			for (SensorTimeInstance instance : stis) {
				System.out.println("X : " + instance.getDate() + " Y: " + instance.getFlexdata().getConvertedValue());
			}
			
		}
		
		else {
			return new JPanel();
		}
		return new JPanel();
		
	}

}
