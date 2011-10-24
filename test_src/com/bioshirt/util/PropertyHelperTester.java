package com.bioshirt.util;

import junit.framework.Assert;

import org.junit.Test;

public class PropertyHelperTester {

	@Test
	public void testGetAndSave() {
		String property = "database.name";
		String value = "slipdb";
		
		//save the values
		PropertyHelper.getInstance().saveProperty(property, value);
		String returnString = PropertyHelper.getInstance().getProperty(property);
		Assert.assertEquals(value ,returnString);
		
		//check that the returned value is the same
		
	}
	
	@Test
	public void testRemove() {
		String remove = "database.name";
		PropertyHelper.getInstance().removeProperty(remove);
		String result = PropertyHelper.getInstance().getProperty(remove);
		Assert.assertNull(result);
	}

}
