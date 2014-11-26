package org.cloudfoundry.samples.music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAutoConfiguration
@ComponentScan("org.cloudfoundry.samples.music")
@EnableJpaRepositories("org.cloudfoundry.samples.music.repositories")
@EntityScan("org.cloudfoundry.samples.music.model")
@EnableWebMvc
public class Application extends SpringBootServletInitializer {

	/**
	 * Spring-boot hook into servlet lifecyle for deployment to servlet container.
	 */
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	
	/**
	 * Spring-boot harness for running application standalone.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
