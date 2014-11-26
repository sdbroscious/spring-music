package org.cloudfoundry.samples.music.stories;

import static org.cloudfoundry.samples.music.steps.AlbumSteps.createNewAlbum;
import static org.cloudfoundry.samples.music.test.AlbumFixtures.welcomeToTheMonkeyHouse;
import static org.cloudfoundry.samples.music.test.AlbumMatchers.assignedAnId;
import static org.cloudfoundry.samples.music.test.AlbumMatchers.findableById;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Story;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.cloudfoundry.samples.music.model.Album;
import org.cloudfoundry.samples.music.requirements.Application.ManageAlbums.CreateAlbum;
import org.cloudfoundry.samples.test.RestAcceptanceTest;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ThucydidesRunner.class)
@Story(CreateAlbum.class)
public class CreateAlbumStory extends RestAcceptanceTest {

	@Test
	public void new_album_should_be_createable_then_findable() {
		
		Album album = createNewAlbum(welcomeToTheMonkeyHouse());
		
		assertThat(album, is(assignedAnId()));
		assertThat(album, is(findableById()));
		
	}

	@Pending
	@Test
	public void creating_existing_album_should_fail_with_error() {
	}
	
	@Pending
	@Test
	public void creating_invalid_album_should_fail_and_list_exceptions() {
	}
	
}
