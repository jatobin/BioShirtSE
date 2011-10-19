package com.bioshirt.util;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import com.bioshirt.dto.User;

public class DatabaseHelperTester {

	@Test
	public void test() {
		Assert.assertNotNull(DatabaseHelper.getInstance().getConnection());
	}

}
