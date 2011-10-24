package com.bioshirt.util;

import junit.framework.Assert;

import org.junit.Test;

public class DatabaseHelperTester {

	@Test
	public void test() {
		Assert.assertNotNull(DatabaseHelper.getInstance().getConnection());
	}

}
