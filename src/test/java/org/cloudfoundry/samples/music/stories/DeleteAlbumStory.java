package org.cloudfoundry.samples.music.stories;

import static com.jayway.restassured.RestAssured.when;
import static org.cloudfoundry.samples.music.steps.AlbumSteps.deleteAlbum;
import static org.cloudfoundry.samples.music.steps.AlbumSteps.givenAlbum;
import static org.cloudfoundry.samples.music.test.AlbumFixtures.elephant;
import static org.cloudfoundry.samples.music.test.AlbumMatchers.findableById;
import static org.cloudfoundry.samples.music.test.AlbumResources.album;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Story;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.cloudfoundry.samples.music.model.Album;
import org.cloudfoundry.samples.music.requirements.Application.ManageAlbums.DeleteAlbum;
import org.cloudfoundry.samples.test.RestAcceptanceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;

@RunWith(ThucydidesRunner.class)
@Story(DeleteAlbum.class)
public class DeleteAlbumStory extends RestAcceptanceTest {

	@Test
	public void deleted_album_should_not_be_findable() {
		
		Album album = givenAlbum(elephant());
		
		deleteAlbum(album);
		
		assertThat(album, is(not(findableById())));
		
	}

	@Test
	public void deleting_unknown_album_should_return_success() {
		when().
			delete(album("unknown")).
		then().
			assertThat().statusCode(HttpStatus.OK.value());
	}
	
	@Pending
	@Test
	public void creating_invalid_album_should_fail_and_list_exceptions() {
	}
	
}
