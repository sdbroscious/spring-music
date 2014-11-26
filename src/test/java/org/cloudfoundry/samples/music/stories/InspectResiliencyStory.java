package org.cloudfoundry.samples.music.stories;

import static com.jayway.restassured.RestAssured.given;
import net.thucydides.core.annotations.Story;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.cloudfoundry.samples.music.requirements.Application.AppInspection.InspectResiliency;
import org.cloudfoundry.samples.test.RestAcceptanceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;

import com.jayway.restassured.http.ContentType;

@RunWith(ThucydidesRunner.class)
@Story(InspectResiliency.class)
public class InspectResiliencyStory extends RestAcceptanceTest {

	@Test
	public void unhandled_exception_should_return_internal_server_error() {
		
		given().
	    	contentType(ContentType.JSON).
	    when().
	    	get("/errors/throw").
	    then().
	    	assertThat().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		
	}
		
}
