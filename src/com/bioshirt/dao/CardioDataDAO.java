package com.bioshirt.dao;

import java.sql.Date;
import java.util.List;

import com.bioshirt.dto.CardioData;
import com.bioshirt.dto.Device;
import com.bioshirt.dto.User;

public interface CardioDataDAO {
	
	public List<CardioData> getAllCardioForUser(User user);
	public List<CardioData> getAllCardioForDevice(Device device);
	public List<CardioData> getCardioForUserForDayForDevice(Date data, User user, Device device);
	public List<CardioData> getCardioDataForUserForWorkout(User user, Device device);
	public List<CardioData> getCardioDataForDeviceBetweenDates(User user, Device device, Date startDate, Date endDate);

}
