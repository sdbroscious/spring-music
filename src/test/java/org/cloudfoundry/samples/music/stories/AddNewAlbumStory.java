package org.cloudfoundry.samples.music.stories;

import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Story;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.cloudfoundry.samples.music.model.Album;
import org.cloudfoundry.samples.music.requirements.Application.ManageAlbums.AddNewAlbum;
import org.cloudfoundry.samples.test.RestAcceptanceTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jayway.restassured.RestAssured.*;

//@RunWith(ThucydidesRunner.class)
//@Story(AddNewAlbum.class)
public class AddNewAlbumStory extends RestAcceptanceTest {

	@Test
	public void creating_new_album_should_succeed() {
		given().
	    	contentType("application/json").
	    	body(album()).
	    when().
	    	post("/albums").
	    then().
	    	assertThat().statusCode(200);
	}

	@Pending
	@Test
	public void creating_existing_album_should_fail_with_error() {
	}
	
	@Pending
	@Test
	public void creating_invalid_album_should_fail_and_list_exceptions() {
	}

	Album album() {
		Album album = new Album();
		
		album.setArtist("The Flaming Lips");
		album.setTitle("Yoshimi Battles the Pink Robots");
		album.setGenre("Space Rock");
		album.setReleaseYear("2002");
		
		return album;
	}
	
}
