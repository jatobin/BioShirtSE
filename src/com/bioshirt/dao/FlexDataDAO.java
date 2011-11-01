package com.bioshirt.dao;

import java.sql.Date;
import java.util.List;

import com.bioshirt.dto.FlexData;
import com.bioshirt.dto.User;

public interface FlexDataDAO {
	
	public List<FlexData> getFlexDataForUser(User user);
	public List<FlexData> getFlexDataForTimeAndUser(Date date, User user);
	public void insertFlexDataWithTime(FlexData fData, Date date);
	
}
