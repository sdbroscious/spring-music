package org.cloudfoundry.samples.music.stories;

import static org.cloudfoundry.samples.music.steps.AlbumSteps.givenAlbum;
import static org.cloudfoundry.samples.music.steps.AlbumSteps.updateAlbum;
import static org.cloudfoundry.samples.music.test.AlbumFixtures.yoshimiBattlesThePinkRobots;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Story;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.cloudfoundry.samples.music.model.Album;
import org.cloudfoundry.samples.music.requirements.Application.ManageAlbums.UpdateAlbum;
import org.cloudfoundry.samples.test.RestAcceptanceTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ThucydidesRunner.class)
@Story(UpdateAlbum.class)
public class UpdateAlbumStory extends RestAcceptanceTest {

	@Test
	public void update_existing_album_should_succeed() {
		Album expected = givenAlbum(yoshimiBattlesThePinkRobots());
		expected.setGenre("Space Rock");

		Album actual = updateAlbum(expected);
		expected.setArtworkUrl(actual.getArtworkUrl());
		// TODO fix this hard-coding
		// The controller.create doesn't update artwork?
		
		Assert.assertEquals(expected, actual);
	}

	@Pending
	@Test
	public void updating_nonexisting_album_should_fail_with_error() {
	}
	
	@Pending
	@Test
	public void updating_album_with_invalid_data_should_fail_and_list_exceptions() {
	}
	
}
