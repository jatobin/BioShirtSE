package com.bioshirt.dao;

import java.sql.Date;
import java.util.List;

import com.bioshirt.dto.SensorTimeInstance;

public interface STIDAO {
	
	public void insertSTI (SensorTimeInstance sti);
	public List<SensorTimeInstance> getSTIForTime (String date);
	public List<SensorTimeInstance> getSTIForRange(String start, String end); 
	public SensorTimeInstance getSTIForIndex(int index);

}
