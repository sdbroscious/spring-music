package org.cloudfoundry.samples.music.web;

import org.cloudfoundry.samples.music.Application;
import org.cloudfoundry.samples.test.RunningApp;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;


public class AppTest {

	@ClassRule
	public static RunningApp app = new RunningApp(Application.class);
	
	@Test
	public void test() {
		Assert.assertTrue(app.url().startsWith("http://"));
	}

}
