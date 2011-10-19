package com.bioshirt.dao;

import java.util.ArrayList;
import java.util.List;

//import junit.framework.Assert;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bioshirt.dto.Device;
import com.bioshirt.dto.User;

public class UserDAOTester {

	private User a;
	private User b;

	private Device deviceA;
	private Device deviceB;
	private UserDAO userDAO;
	
	@Before
	public void setUp() throws Exception {

		User a = new User();
		a.setAll(1, "Austin", "testpass");
		System.out.println(a.getUserid());
		User b = new User();
		b.setAll(2, "peter", "testpasstwo");
		userDAO = new UserDAOImpl();
		deviceA = new Device();
		deviceA.setDeviceID("TESTDEVICEID");
		deviceB = new Device();
		deviceB.setDeviceID("TESTDEVICETWO");


	}
	

	@Test
	public void testOne() {
		User austin = userDAO.getUserByNameAndPass("Austin", "testpass");
		Assert.assertEquals(austin, a);
	}
	
	@Test
	public void testTwo() {
		List<Device> devices = new ArrayList<Device>();
		devices.add(deviceA);
		devices.add(deviceB);
		List<Device> devicesReturns = userDAO.getDevicesByUser(a);
		for (int i = 0; i < devices.size(); i++ ) {
			System.out.printf("Expecteds:%15s Returns: %15s\n", devicesReturns.get(i).toString(), devices.get(i).toString());
		}
		Assert.assertArrayEquals(devicesReturns.toArray(), devices.toArray());
	}
	
	@Test
	public void testThree() {
		List<User> users = new ArrayList<User>();
		users.add(User.setAllForUser(1, "austin", "testpass"));
		users.add(User.setAllForUser(2, "peter", "testpasstwo"));
		List<User> rUsers = userDAO.getAllUsers();
		for (User s : users) {
			System.out.println(s.toString());
		}
		for (int i = 0; i < users.size(); i++ ) {
			System.out.printf("Expecteds:%15s Returns: %15s\n", users.get(i).toString(), rUsers.get(i).toString());
		}
		
		Assert.assertArrayEquals(users.toArray(), rUsers.toArray());
		
	}

}
