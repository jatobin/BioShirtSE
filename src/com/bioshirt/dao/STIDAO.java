package com.bioshirt.dao;

import java.sql.Date;
import java.util.List;

import com.bioshirt.dto.SensorTimeInstance;

public interface STIDAO {
	
	public void insertSTI (SensorTimeInstance sti);
	public List<SensorTimeInstance> getSTIForTime (Date date);
	public List<SensorTimeInstance> getSTIForRange(Date start, Date end); 
	public SensorTimeInstance getSTIForIndex(int index);

}
