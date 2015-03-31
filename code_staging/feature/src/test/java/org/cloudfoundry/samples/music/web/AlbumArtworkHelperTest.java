package org.cloudfoundry.samples.music.web;

import org.cloudfoundry.samples.music.model.Album;
import org.cloudfoundry.samples.music.web.AlbumArtworkHelper;
import org.junit.Assert;
import org.junit.Test;

public class AlbumArtworkHelperTest {

	@Test
	public void shouldFindYoshimiBattlesThePinkRobots() {
		
		String artworkThumbnailUrl = AlbumArtworkHelper.artworkUrlFor(
				new Album("The Flaming Lips", "Yoshimi Battles the Pink Robots", null, null));
		
		Assert.assertEquals("http://a2.mzstatic.com/us/r30/Music/45/3d/72/mzi.mvvbwags.100x100-75.jpg",
				            artworkThumbnailUrl);
		
	}
	
	@Test
	public void shouldNotFindExileOnMainStreet() {
		
		String artworkThumbnailUrl = AlbumArtworkHelper.artworkUrlFor(
				new Album("The Rolling Stones", "Exile on Main Street", null, null));
		
		Assert.assertNull(artworkThumbnailUrl);
				
	}

}
