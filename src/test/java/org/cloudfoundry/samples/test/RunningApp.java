package org.cloudfoundry.samples.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.rules.ExternalResource;

public class RunningApp extends ExternalResource {

	public static final String PROPERTY_TEST_APP_URL = "test.app.url";

	private EmbeddedApp embeddedApp = null;

	public RunningApp(Class<?> application) {
		if (!isTargetingRemoteApp()) {
			embeddedApp = new EmbeddedApp(application);
		}
		
		System.out.println(String.format("Targetting running app at %s", url()));
	}

	boolean isTargetingRemoteApp() {
		String appUrl = System.getProperty(PROPERTY_TEST_APP_URL);
		return appUrl != null && isValidUrl(appUrl);
	}

	boolean isValidUrl(String url) {
		boolean validUrl = false;
		try {

			new URL(url);
			validUrl = true;

		} catch (MalformedURLException e) {
			Assert.fail(String.format("Specified url '%s' is not valid.", url));
		}
		return validUrl;
	}

	public String url() {
		String url = System.getProperty(PROPERTY_TEST_APP_URL);

		if (embeddedApp != null) {
			url = embeddedApp.url();
		}

		return url;
	}

}
