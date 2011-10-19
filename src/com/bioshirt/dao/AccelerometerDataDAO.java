package com.bioshirt.dao;

import java.sql.Date;
import java.util.List;

import com.bioshirt.dto.AccelerometerData;
import com.bioshirt.dto.Device;
import com.bioshirt.dto.User;

public interface AccelerometerDataDAO {

	public List<AccelerometerData> getAllAccelerometerForUser(User user);
	public List<AccelerometerData> getAllAccelerometerForDevice(Device device);
	public List<AccelerometerData> getAccelerometerForUserForDayForDevice(Date data, User user, Device device);
	public List<AccelerometerData> getAccelerometerDataForUserForWorkout(User user, Device device);
	public List<AccelerometerData> getAccelerometerDataForDeviceBetweenDates(User user, Device device, Date startDate, Date endDate);
}
