package com.bioshirt.dao;

import java.util.List;

import com.bioshirt.dto.Device;
import com.bioshirt.dto.User;

public interface UserDAO {
	
	public List<User> getAllUsers();
	public User getUserByNameAndPass(String name, String password);
	public List<Device> getDevicesByUser(User user);

}
