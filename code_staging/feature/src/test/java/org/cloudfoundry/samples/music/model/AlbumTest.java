package org.cloudfoundry.samples.music.model;


import org.junit.Assert;
import org.junit.Test;

import com.google.common.testing.ClassSanityTester;

public class AlbumTest {

	@Test
	public void object_contract_should_be_honored() throws Exception {
		new ClassSanityTester().testEquals(Album.class);
	}
	
	@Test
	public void tostring_should_be_informative() throws Exception {
		Album actual = new Album("A", "B", "C", "D");
		String expectedToString = "Album(id=null, title=A, artist=B, releaseYear=C, genre=D, rating=null, artworkUrl=null)";
		
		Assert.assertEquals(expectedToString, actual.toString());
	}

}
