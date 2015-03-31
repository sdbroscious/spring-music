package org.cloudfoundry.samples.music.stories;

import static com.jayway.restassured.RestAssured.given;
import net.thucydides.core.annotations.Story;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.cloudfoundry.samples.music.requirements.Application.AppInspection.InspectEnvironment;
import org.cloudfoundry.samples.test.RestAcceptanceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;

import com.jayway.restassured.http.ContentType;

@RunWith(ThucydidesRunner.class)
@Story(InspectEnvironment.class)
public class InspectEnvironmentStory extends RestAcceptanceTest {

	@Test
	public void should_get_spring_info() {
		
		given().
	    	contentType(ContentType.JSON).
	    when().
	    	get("/info").
	    then().
	    	assertThat().statusCode(HttpStatus.OK.value());
		
	}

	@Test
	public void should_get_environment_info() {
		
		given().
			contentType(ContentType.JSON).
		when().
			get("/env").
		then().
			assertThat().statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	public void should_get_cloud_services_info() {
		
		given().
	    	contentType(ContentType.JSON).
	    when().
	    	get("/service").
	    then().
	    	assertThat().statusCode(HttpStatus.OK.value());
		
		// This will return a 500 status code when running as junit test in 
		// Eclipse, but is fine when launched via spring-boot.
		// 
		// TODO Figure out why H2 cloud service is causing exception with 
		//      cloud.getServiceInfo in embedded app instance.
		
	}
	
}
