package org.cloudfoundry.samples.music.stories;

import static com.jayway.restassured.RestAssured.given;
import static org.cloudfoundry.samples.music.steps.AlbumSteps.createNewAlbum;
import static org.cloudfoundry.samples.music.steps.AlbumSteps.findAllAlbums;
import static org.cloudfoundry.samples.music.steps.AlbumSteps.givenAlbum;
import static org.cloudfoundry.samples.music.test.AlbumFixtures.brothers;
import static org.cloudfoundry.samples.music.test.AlbumFixtures.pickinUpThePieces;
import static org.cloudfoundry.samples.music.test.AlbumMatchers.findableById;
import static org.cloudfoundry.samples.music.test.AlbumResources.album;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import net.thucydides.core.annotations.Story;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.cloudfoundry.samples.music.model.Album;
import org.cloudfoundry.samples.music.requirements.Application.ManageAlbums.FindAlbums;
import org.cloudfoundry.samples.music.test.AlbumResources;
import org.cloudfoundry.samples.test.RestAcceptanceTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;

@RunWith(ThucydidesRunner.class)
@Story(FindAlbums.class)
public class FindAlbumsStory extends RestAcceptanceTest {

	@Test
	public void existing_album_should_be_findable_by_id() {
		
		Album album = givenAlbum(brothers());
		
		assertThat(album, is(findableById()));
		
	}

	@Test
	public void lookup_of_nonexistent_album_should_return_not_found() {
		
		given().
	    	contentType(AlbumResources.CONTENT_TYPE).
	    when().
	    	get(album("unknown")).
	    then().
	    	assertThat().statusCode(HttpStatus.NOT_FOUND.value());
		
	}
	
	@Test
	public void all_albums_should_be_findable() {
		
		List<Album> albumsFirstGlance = findAllAlbums();
		
		Album newAlbum = createNewAlbum(pickinUpThePieces());
		
		List<Album> albumsSecondGlance = findAllAlbums();
		
		Assert.assertEquals(1, (albumsSecondGlance.size() - albumsFirstGlance.size()));
		
		albumsFirstGlance.add(newAlbum);
		Assert.assertEquals(albumsFirstGlance, albumsSecondGlance);
		
	}
	
}
