package org.cloudfoundry.samples.music.web;

import org.cloudfoundry.samples.music.model.Album;
import org.cloudfoundry.samples.music.web.AlbumArtworkHelper;
import org.junit.Assert;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.util.UrlUtils;

public class AlbumArtworkHelperTest {

	@Test
	public void shouldFindYoshimiBattlesThePinkRobots() {
		
		String artworkThumbnailUrl = AlbumArtworkHelper.artworkUrlFor(
				new Album("The Flaming Lips", "Yoshimi Battles the Pink Robots", null, null));
		
		Assert.assertNotNull(UrlUtils.toUrlSafe(artworkThumbnailUrl));
		
	}
	
	@Test
	public void shouldNotFindExileOnMainStreet() {
		
		String artworkThumbnailUrl = AlbumArtworkHelper.artworkUrlFor(
				new Album("The Rolling Stones", "Exile on Main Street", null, null));
		
		Assert.assertNull(artworkThumbnailUrl);
				
	}

}
