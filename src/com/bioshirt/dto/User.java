package com.bioshirt.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {


	private int userid;
	private String username;
	private String password;
	

	public User(){}
	
	public User(int userid, String username, String password) {
		this.userid = userid;
		this.username = username;
		this.password = password;
	}
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAll(int userid, String username, String password) {
		this.userid = userid;
		this.username = username;
		this.password = password;
	}
	
	public static User setAllForUser(int userid, String username, String password) {
		User u = new User();
		u.setAll(userid, username, password);
		return u;
	}
	
	
	public static List<User> mapResultSet(ResultSet rs) {
		List<User> users = new ArrayList<User>();
		try {
			rs.beforeFirst();
			while (rs.next()) {
				int userid = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				users.add( User.setAllForUser(userid, username, password));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "" + userid + " " + username + " " + password;
	}

	@Override
	public boolean equals(Object o) {
		User user;
		 //TODO add logic for checking if it is the sameclass
		if ( o == null ){
			System.out.println("o is null");
			return false;
		} else {
			user = (User) o;
		}
		
//		boolean b = (user.getUserid() == userid);
//		System.out.printf("id's are equal: %b", b);
//		b = b && (user.getUsername().equalsIgnoreCase(this.username));
//		System.out.printf("usernames are equal: %s %s" , user.getUsername() , this.username);
//		b = b && (user.getPassword().equalsIgnoreCase(this.password));
//		System.out.printf("passwords are equal: %s %s" , user.getPassword() , this.password);
//		return b;
		
		return (user.getUserid() == this.userid
				&& user.getUsername().equalsIgnoreCase(this.username)
				&& user.getPassword().equalsIgnoreCase(this.password));
	}
	
	
	
}
