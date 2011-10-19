package com.bioshirt.dao;

import java.sql.Date;
import java.util.List;

import com.bioshirt.dto.AccelerometerData;
import com.bioshirt.dto.Device;
import com.bioshirt.dto.User;

public class AccelerometerDataDAOImpl implements AccelerometerDataDAO {

	@Override
	public List<AccelerometerData> getAllAccelerometerForUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccelerometerData> getAllAccelerometerForDevice(Device device) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccelerometerData> getAccelerometerForUserForDayForDevice(
			Date data, User user, Device device) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccelerometerData> getAccelerometerDataForUserForWorkout(
			User user, Device device) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccelerometerData> getAccelerometerDataForDeviceBetweenDates(
			User user, Device device, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
