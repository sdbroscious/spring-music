package org.cloudfoundry.samples.music.test;

import org.cloudfoundry.samples.music.model.Album;
import org.cloudfoundry.samples.music.steps.AlbumSteps;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public final class AlbumMatchers {
	
	@Factory
	public static <T> Matcher<Album> assignedAnId() {
		return new AssignedAnId();
	}

	@Factory
	public static <T> Matcher<Album> findableById() {
		return new FindableById();
	}

	public static class AssignedAnId extends TypeSafeMatcher<Album> {

		@Override
		public void describeTo(Description description) {
			description.appendText("assigned an ID");
		}

		@Override
		protected boolean matchesSafely(Album album) {
			return hasId(album);
		}
	}

	public static class FindableById extends TypeSafeMatcher<Album> {

		@Override
		public void describeTo(Description description) {
			description.appendText("findable by ID");
		}

		@Override
		protected boolean matchesSafely(Album expected) {
			boolean matches = false;
			
			if (hasId(expected)) {
				Album actual = AlbumSteps.findAlbumById(expected.getId());
				matches = expected.equals(actual);
			}
			
			return matches;
		}
	}
	
	private static boolean hasId(Album album) {
		return album != null && album.getId() != null;
	}
	
}
