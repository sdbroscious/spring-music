package org.cloudfoundry.samples.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;

public class EmbeddedApp {

	/*
	 * Singleton scaffolding
	 */
	private static EmbeddedApp INSTANCE = null;
	
	public static synchronized EmbeddedApp instance(Class<?> application) {
		if (INSTANCE == null) {
			INSTANCE = new EmbeddedApp(application);
		}
		return INSTANCE;
	}
	
	/*
	 * Instance implementation
	 */
	private static final Logger log = LoggerFactory.getLogger(EmbeddedApp.class);
	
	EmbeddedWebApplicationContext app = null;

	private EmbeddedApp(Class<?> application) {
		System.setProperty("server.port", "0");  // Use available port for tc listener.
		System.setProperty("management.port", "-1");  // Turn off actuator http endpoints
		System.setProperty("spring.jmx.enabled", "false");  // Turn off actuator jmx endpoints
		
		app = (EmbeddedWebApplicationContext) SpringApplication.run(application, new String[] {});
		
		log.info("Embedded application available at {}", url());
	}
		
	public String url() {
		return String.format("http://localhost:%d/", app.getEmbeddedServletContainer().getPort());
	}
		
}
