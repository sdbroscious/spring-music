package org.cloudfoundry.samples.test;

import org.cloudfoundry.samples.music.Application;
import org.junit.ClassRule;

import com.jayway.restassured.RestAssured;

public class RestAcceptanceTest {

	@ClassRule
	public static RunningApp app = new RunningApp(Application.class);

	static {
		RestAssured.baseURI = app.url();
	}

}
