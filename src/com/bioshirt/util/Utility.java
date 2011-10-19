package com.bioshirt.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bioshirt.dto.AccelerometerData;
import com.bioshirt.dto.CardioData;
import com.bioshirt.dto.Device;
import com.bioshirt.dto.FlexData;
import com.bioshirt.dto.JSONInterpretedBean;

public class Utility {
	
	public static JSONInterpretedBean getJSONBean(JSONObject jsonObj) {
		CardioData cardioData;
		AccelerometerData accData;
		FlexData flexData;
		Device device;
		Date date;
		
		try {
			device = new Device();
			device.setDeviceID(jsonObj.getString("deviceid"));
			accData = new AccelerometerData(jsonObj.getDouble("acc"));
			cardioData = new CardioData(jsonObj.getInt("hrt"));
			
			//TODO work on date format, json time code = "time"
			date = new Date(0);
			
			ArrayList<Double> points = new ArrayList<Double>();
			JSONArray arr = jsonObj.getJSONArray("flex");
			for (int i = 0; i < arr.length(); i++ ) {
				points.add(arr.getDouble(i));
			}
			flexData = new FlexData(points);
			
			
			
			
			
			
			
//			// get the device
//			String dev = jsonObj.getString("deviceid");
//			Device d = new Device();
//			d.setDeviceID(dev);
//		   
//			
//			
			// get the sensor values
//			JSONArray svals = jsonObj.getJSONArray("svals");
//			//stored as an array of json objects. for each object 
//			//i need to get the keys for them, then I need to get the values for the keys.
//			
//			ArrayList<String> keys = new ArrayList<String>();
//			for (int i = 0; i < svals.length(); i++) {
//				Iterator iterator = svals.getJSONObject(i).keys(); 
//				//get the keys
//				while (iterator.hasNext()) {
//					String s = iterator.next().toString();
//					if (s.equalsIgnoreCase("acc")) { 
//						svals.get(i).
//						accData = new AccelerometerData();
//						
//					} else if (s.equalsIgnoreCase("hrt")) {
//						
//					} else if (s.equalsIgnoreCase("flex")) {
//						
//					} else {
//						
//					}
//				}		
//			}
			
			return new JSONInterpretedBean(cardioData, accData, flexData, device, date);
			
			
			// get the time from the thing.

		} catch(JSONException je) {
			je.printStackTrace();
			return null;
		}
		

		
	}

}
