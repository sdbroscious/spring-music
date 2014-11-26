package org.cloudfoundry.samples.music.model;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.testing.ClassSanityTester;

public class ApplicationInfoTest {

	@Test
	public void object_contract_should_be_honored() throws Exception {
		new ClassSanityTester().testEquals(ApplicationInfo.class);
	}

	@Test
	public void tostring_should_be_informative() throws Exception {
		ApplicationInfo actual = new ApplicationInfo(new String[] {"A"}, new String[] {"B"});
		String expectedToString = "ApplicationInfo(profiles=[A], services=[B])";
		
		Assert.assertEquals(expectedToString, actual.toString());
	}
	
}
