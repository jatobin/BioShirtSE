package com.bioshirt.dao;

import java.sql.Date;
import java.util.List;

import com.bioshirt.dto.CardioData;
import com.bioshirt.dto.Device;
import com.bioshirt.dto.User;

public class CardioDataDAOImpl implements CardioDataDAO {

	@Override
	public List<CardioData> getAllCardioForUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CardioData> getAllCardioForDevice(Device device) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CardioData> getCardioForUserForDayForDevice(Date data,
			User user, Device device) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CardioData> getCardioDataForUserForWorkout(User user,
			Device device) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CardioData> getCardioDataForDeviceBetweenDates(User user,
			Device device, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
