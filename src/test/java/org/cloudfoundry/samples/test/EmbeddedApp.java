package org.cloudfoundry.samples.test;

import java.net.ServerSocket;

import org.cloudfoundry.samples.music.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	private String url;

	private EmbeddedApp(Class<?> application) {
		String tcHttpPort = Integer.toString(availablePort());
		
		System.setProperty("server.port", tcHttpPort);  // Use available port for tc listener.
		System.setProperty("management.port", "-1");  // Turn off actuator http endpoints
		System.setProperty("spring.jmx.enabled", "false");  // Turn off actuator jmx endpoints
		System.setProperty("spring.profiles.active", "cloud");
		
		url = String.format("http://localhost:%s/", tcHttpPort);
		
		Application.main(new String[] {});
		
		log.info("Embedded application available at {}", url);
	}
		
	public String url() {
		return url;
	}
	
	private int availablePort() {
		int port = 0;
		
		try (ServerSocket reconSocket = new ServerSocket(0)) {
			port = reconSocket.getLocalPort();
		} catch (Exception e) {
			log.error("Error finding available port.", e);
		}
		
		return port;
	}
		
}
