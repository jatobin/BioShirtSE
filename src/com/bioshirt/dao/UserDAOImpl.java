package com.bioshirt.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bioshirt.dto.Device;
import com.bioshirt.dto.User;
import com.bioshirt.services.DatabaseHelper;

public class UserDAOImpl implements UserDAO {

	@Override
	public List<User> getAllUsers() {
		String sql = "SELECT * FROM users;";
		try {
			ResultSet rs = DatabaseHelper.getInstance().executeQuery(sql);
			return User.mapResultSet(rs);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User getUserByNameAndPass(String name, String password) {
		String sql = "SELECT * FROM users WHERE user_name = " + name + " AND password = " + password + ";" ;
		try {
			ResultSet rs = DatabaseHelper.getInstance().executeQuery(sql);
			return User.mapResultSet(rs).get(0);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Device> getDevicesByUser(User user) {
		System.out.println("1");
		String sql = "SELECT * FROM users_devices WHERE user_id = 1;";
		System.out.println(sql);
		try {
			ResultSet rs = DatabaseHelper.getInstance().executeQuery(sql);
			List<Device> devices = new ArrayList<Device>();
			rs.beforeFirst();
			while (rs.next()) {
				Device d = new Device("TESTDEVICEID");
				d.setDeviceID(rs.getString(2));
				devices.add(d);
			}
			return devices;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
