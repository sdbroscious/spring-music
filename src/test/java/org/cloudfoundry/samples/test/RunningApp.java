package org.cloudfoundry.samples.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunningApp extends ExternalResource {

	public static final String PROPERTY_TEST_APP_URL = "test.app.url";

	private static final Logger log = LoggerFactory.getLogger(RunningApp.class);
	
	private final String appUrl;

	public RunningApp(Class<?> application) {
		
		if (isTargetingRemoteApp()) {
			this.appUrl = remoteUrl();
		} else {
			this.appUrl = embeddedUrl(application);
		}
		
		log.warn("Tests targetting running app at {}", url());
	}

	boolean isTargetingRemoteApp() {
		return remoteUrl() != null && isValidUrl(remoteUrl());
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
	
	String remoteUrl() {
		return System.getProperty(PROPERTY_TEST_APP_URL);
	}
	
	String embeddedUrl(Class<?> application) {
		return EmbeddedApp.instance(application).url();
	}

	public String url() {
		return this.appUrl;
	}

}
