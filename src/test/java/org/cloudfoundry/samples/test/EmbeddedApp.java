package org.cloudfoundry.samples.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;

public class EmbeddedApp {

	EmbeddedWebApplicationContext app = null;

	public EmbeddedApp(Class<?> application) {
		System.setProperty("server.port", "0");  // Use available port.
		app = (EmbeddedWebApplicationContext) SpringApplication.run(application, new String[] {});
		
		System.out.println(String.format("Embedded application available at %s", url()));
	}
	
	public String url() {
		return String.format("http://localhost:%d/", app.getEmbeddedServletContainer().getPort());
	}
		
}
