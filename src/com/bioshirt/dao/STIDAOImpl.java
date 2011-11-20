package com.bioshirt.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import com.bioshirt.dto.Device;
import com.bioshirt.dto.SensorTimeInstance;
import com.bioshirt.services.DatabaseHelper;

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
	public List<SensorTimeInstance> getSTIForTime(String date) {
		String sql = "select * from device_data where device_data.date like '" + date + "%';";
		System.out.println(sql);
		
		try {
			ResultSet rs = DatabaseHelper.getInstance().executeQuery(sql);
			List<SensorTimeInstance> stis = new ArrayList<SensorTimeInstance	>();
			rs.beforeFirst();
			while (rs.next()) {
				
				
				Device d = new Device(rs.getString(2));
				Timestamp newDate = rs.getTimestamp(3);
				String hex = rs.getString(4);
				SensorTimeInstance tsti = new SensorTimeInstance(d, newDate, hex);
				System.out.println(tsti.toString());
				stis.add(tsti);
			}
			return stis;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<SensorTimeInstance> getSTIForRange(String start, String end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SensorTimeInstance getSTIForIndex(int index) {
		// TODO Auto-generated method stub
		return null;
	}

}
