package com.bioshirt.dao;

import java.sql.Date;
import java.util.List;
import java.sql.Timestamp;

import com.bioshirt.dto.SensorTimeInstance;
import com.bioshirt.util.DatabaseHelper;

public class STIDAOImpl implements STIDAO {
	
	public final String TABLE_NAME = "device_data";

	@Override
	public void insertSTI(SensorTimeInstance sti) {
		String sql = "INSERT INTO " + TABLE_NAME + " (device_id,date,data) " +  "VALUES ('"		
		+ sti.getDevice().getDeviceID() + "', '"
		+ sti.getDate().toString() + "', '"
		+ sti.getRawHex() +"');";
		System.out.println(sql);
		DatabaseHelper.getInstance().executeUpdate(sql);
		
	}

	@Override
	public List<SensorTimeInstance> getSTIForTime(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SensorTimeInstance> getSTIForRange(Date start, Date end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SensorTimeInstance getSTIForIndex(int index) {
		// TODO Auto-generated method stub
		return null;
	}

}
